package com.github.polydome.trainsapi.endpoint

import org.jboss.resteasy.reactive.RestQuery
import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/connections")
class ConnectionsEndpoint {
    @GET
    fun listConnections(
        @RestQuery startStation: String,
        @RestQuery endStation: String
    ) {

    }
}