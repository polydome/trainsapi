package com.github.polydome.trainsapi.endpoint

import com.github.polydome.trainsapi.model.Destination
import com.github.polydome.trainsapi.model.RelationId
import com.github.polydome.trainsapi.repository.DestinationRepository
import com.github.polydome.trainsapi.validation.requireParamNotNull
import org.jboss.resteasy.reactive.RestForm
import org.jboss.resteasy.reactive.RestPath
import javax.ws.rs.DELETE
import javax.ws.rs.POST
import javax.ws.rs.Path

@Path("relations/{relationId}/destinations")
class DestinationsEndpoint(
    private val destinationRepository: DestinationRepository
) {
    @POST
    fun addDestination(
        @RestPath relationId: RelationId,
        @RestForm station: String?,
        @RestForm platform: Int?,
        @RestForm track: Int?,
        @RestForm arrivalTime: Int?,
        @RestForm departureTime: Int?
    ) {
        requireParamNotNull("station", station)
        requireParamNotNull("platform", platform)
        requireParamNotNull("track", track)

        destinationRepository.addDestination(
            relationId = relationId,
            destination = Destination(
                stationName = station,
                platform = Destination.PlatformIndex(platform),
                track = Destination.TrackIndex(track),
                arrivalTime = arrivalTime,
                departureTime = departureTime
            )
        )
    }

    @Path("{index}")
    @DELETE
    fun deleteDestination(
        @RestPath relationId: RelationId,
        @RestPath index: Int
    ) {
        destinationRepository.removeDestination(
            relationId = relationId,
            destinationIndex = index
        )
    }
}