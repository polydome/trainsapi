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
        }.map { (relation, departureTime) ->
            val arrivalTime = relation.destinations.last().arrivalTime
            val startStation =
                relation.destinations.find { it.stationName == startStation } ?: throw IllegalStateException()
            requireNotNull(arrivalTime)
            val departureRelative = departureTime + MinuteOfDay(startStation.departureTime ?: throw IllegalStateException())
            val arrivalRelative = departureTime + MinuteOfDay(arrivalTime)
            Connection(
                relation = relation.name,
                departureTime = departureRelative.toString(),
                arrivalTime = arrivalRelative.toString()
            )
        }
    }
}
