package com.github.polydome.trainsapi.endpoint

import com.github.polydome.trainsapi.model.RelationId
import org.jboss.resteasy.reactive.RestPath
import javax.ws.rs.POST
import javax.ws.rs.Path

@Path("/relations/{relationId}/destinations")
class DestinationsEndpoint {
    @POST
    fun addDestination(
        @RestPath relationId: RelationId
    ) {

    }
}