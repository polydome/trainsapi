package com.github.polydome.trainsapi.endpoint

import com.github.polydome.trainsapi.model.MinuteOfDay
import com.github.polydome.trainsapi.model.RelationId
import com.github.polydome.trainsapi.repository.CourseRepository
import com.github.polydome.trainsapi.validation.requireParamNotNull
import org.jboss.resteasy.reactive.RestPath
import javax.ws.rs.DELETE
import javax.ws.rs.Path

@Path("/relations/{relationId}/courses/{departureTime}")
class CourseEndpoint(
    private val courseRepository: CourseRepository
) {
    @DELETE
    fun deleteCourse(
        @RestPath relationId: RelationId,
        @RestPath departureTime: String?
    ) {
        requireParamNotNull("departureTime", departureTime)

        courseRepository.removeCourse(
            relationId = relationId,
            departureTime = MinuteOfDay.parse(departureTime)
        )
    }
}