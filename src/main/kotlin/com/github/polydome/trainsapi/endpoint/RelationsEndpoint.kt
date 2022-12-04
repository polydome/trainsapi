package com.github.polydome.trainsapi.endpoint

import com.github.polydome.trainsapi.model.Relation
import com.github.polydome.trainsapi.model.RelationId
import com.github.polydome.trainsapi.validation.requireParamNotNull
import org.jboss.resteasy.reactive.RestForm
import javax.ws.rs.*
import javax.ws.rs.core.Response

@Path("/relations")
class RelationsEndpoint {
    @POST
    fun createRelation(
        @RestForm name: String?
    ): Response {
        requireParamNotNull("name", name)

        return Response
            .status(200)
            .build()
    }

    @GET
    fun listRelations(): List<Relation> {
        return listOf(
            Relation(
                id = RelationId("a4w4"),
                name = "Rubenstein"
            ),
            Relation(
                id = RelationId("r2d2"),
                name = "Chrobry"
            )
        )
    }
}
