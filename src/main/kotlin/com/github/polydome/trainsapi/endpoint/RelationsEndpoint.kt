package com.github.polydome.trainsapi.endpoint

import com.github.polydome.trainsapi.model.Relation
import com.github.polydome.trainsapi.model.RelationId
import javax.ws.rs.*

@Path("/relations")
class RelationsEndpoint {
    @POST
    fun createRelation() {

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
