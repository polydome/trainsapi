package com.github.polydome.trainsapi.endpoint

import com.github.polydome.trainsapi.model.Connection
import com.github.polydome.trainsapi.validation.requireParamNotNull
import org.jboss.resteasy.reactive.RestQuery
import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/connections")
class ConnectionsEndpoint {
    @GET
    fun listConnections(
        @RestQuery startStation: String?,
        @RestQuery endStation: String?
    ): List<Connection> {
        requireParamNotNull("startStation", startStation)
        requireParamNotNull("endStation", endStation)

        return listOf(
            Connection("12:01", "13:03"),
            Connection("14:15", "16:55"),
        )
    }
}
