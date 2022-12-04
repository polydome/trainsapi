package com.github.polydome.trainsapi.endpoint

import com.github.polydome.trainsapi.model.Relation
import com.github.polydome.trainsapi.repository.RelationRepository
import com.github.polydome.trainsapi.validation.requireParamNotNull
import org.jboss.resteasy.reactive.RestForm
import javax.ws.rs.*
import javax.ws.rs.core.Response

@Path("/relations")
class RelationsEndpoint(
    private val relationRepository: RelationRepository
) {
    @POST
    fun createRelation(
        @RestForm name: String?
    ): Response {
        requireParamNotNull("name", name)

        relationRepository.createRelation(name)

        return Response
            .status(200)
            .build()
    }

    @GET
    fun listRelations(): List<Relation> =
        relationRepository.findAllRelations()
}
