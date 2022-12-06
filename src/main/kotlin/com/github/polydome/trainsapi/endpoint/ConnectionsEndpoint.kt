package com.github.polydome.trainsapi.endpoint

import com.github.polydome.trainsapi.model.Connection
import com.github.polydome.trainsapi.model.MinuteOfDay
import com.github.polydome.trainsapi.repository.CourseRepository
import com.github.polydome.trainsapi.repository.RelationRepository
import com.github.polydome.trainsapi.validation.requireParamNotNull
import org.jboss.resteasy.reactive.RestQuery
import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("connections")
class ConnectionsEndpoint(
    private val relationRepository: RelationRepository,
    private val courseRepository: CourseRepository
) {
    @GET
    fun listConnections(
        @RestQuery startStation: String?,
        @RestQuery endStation: String?
    ): List<Connection> {
        requireParamNotNull("startStation", startStation)
        requireParamNotNull("endStation", endStation)

        val relations = relationRepository.findRelationsBetweenStations(startStation, endStation)
        return relations.flatMap { relation ->
            courseRepository.findCoursesByRelationId(relation.id).map { departureTime -> relation to departureTime }
        }.mapNotNull { (relation, departureTime) ->
            val collection = relation.destinations.mapIndexed { index, destination ->
                index to destination
            }.filter { (index, destination) ->
                destination.stationName == startStation || destination.stationName == endStation
            }
                .sortedBy { (index, destination) -> index }
            if (collection.first().second.stationName != startStation) {
                return@mapNotNull null
            }

            val startStation = collection.first().second
            val endStation = collection.last().second

            requireNotNull(startStation.departureTime)
            requireNotNull(endStation.arrivalTime)
            val departureRelative = departureTime + MinuteOfDay(startStation.departureTime)
            val arrivalRelative = departureTime + MinuteOfDay(endStation.arrivalTime)
            Connection(
                relation = relation.name,
                departureTime = departureRelative.toString(),
                arrivalTime = arrivalRelative.toString()
            )
        }
    }
}
