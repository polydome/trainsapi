package com.github.polydome.trainsapi.endpoint

import com.github.polydome.trainsapi.model.RelationId
import com.github.polydome.trainsapi.repository.DestinationRepository
import org.jboss.resteasy.reactive.RestPath
import javax.ws.rs.DELETE
import javax.ws.rs.Path

@Path("/relations/{relationId}/destinations/{index}")
class DestinationEndpoint(
    private val destinationRepository: DestinationRepository
) {
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