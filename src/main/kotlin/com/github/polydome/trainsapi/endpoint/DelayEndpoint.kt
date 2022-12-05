package com.github.polydome.trainsapi.endpoint

import com.github.polydome.trainsapi.model.RelationId
import io.quarkus.redis.datasource.RedisDataSource
import org.jboss.resteasy.reactive.RestForm
import org.jboss.resteasy.reactive.RestPath
import javax.ws.rs.GET
import javax.ws.rs.PUT
import javax.ws.rs.Path

@Path("relations/{relationId}/courses/{departureTime}/delay")
class DelayEndpoint(redisDataSource: RedisDataSource) {
    private val commands = redisDataSource.value(Int::class.java)

    @PUT
    fun updateDelay(
        @RestPath relationId: RelationId,
        @RestPath departureTime: String,
        @RestForm delayTime: Int
    ) {
        commands.set(makeKey(relationId, departureTime), delayTime)
    }

    @GET
    fun getDelay(
        @RestPath relationId: RelationId,
        @RestPath departureTime: String
    ): Int {
        return commands.get(makeKey(relationId, departureTime))
    }

    private fun makeKey(relationId: RelationId, departureTime: String) =
        relationId.value + "/" + departureTime
}