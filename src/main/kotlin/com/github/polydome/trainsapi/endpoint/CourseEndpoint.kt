package com.github.polydome.trainsapi.endpoint

import com.github.polydome.trainsapi.model.RelationId
import org.jboss.resteasy.reactive.RestPath
import javax.ws.rs.DELETE
import javax.ws.rs.Path

@Path("/relations/{relationId}/courses/{departureTime}")
class CourseEndpoint {
    @DELETE
    fun deleteCourse(
        @RestPath relationId: RelationId,
        @RestPath departureTime: String
    ) {

    }
}