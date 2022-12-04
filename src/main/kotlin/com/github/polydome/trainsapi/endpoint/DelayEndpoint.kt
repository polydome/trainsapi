package com.github.polydome.trainsapi.endpoint

import com.github.polydome.trainsapi.model.RelationId
import org.jboss.resteasy.reactive.RestForm
import org.jboss.resteasy.reactive.RestPath
import javax.ws.rs.PUT
import javax.ws.rs.Path

@Path("/relations/{relationId}/courses/{departureTime}/delay")
class DelayEndpoint {
    @PUT
    fun updateDelay(
        @RestPath relationId: RelationId,
        @RestPath departureTime: String,
        @RestForm delayTime: Int
    ) {

    }
}