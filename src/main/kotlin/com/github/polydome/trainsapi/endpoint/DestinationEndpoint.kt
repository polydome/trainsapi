package com.github.polydome.trainsapi.endpoint

import com.github.polydome.trainsapi.model.RelationId
import org.jboss.resteasy.reactive.RestPath
import javax.ws.rs.DELETE
import javax.ws.rs.Path

@Path("/relations/{relationId}/destinations/{index}")
class DestinationEndpoint {
    @DELETE
    fun deleteDestination(
        @RestPath relationId: RelationId,
        @RestPath index: Int
    ) {

    }
}