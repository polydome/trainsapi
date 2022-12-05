package com.github.polydome.trainsapi.endpoint

import com.github.polydome.trainsapi.model.MinuteOfDay
import com.github.polydome.trainsapi.model.RelationId
import com.github.polydome.trainsapi.repository.CourseRepository
import com.github.polydome.trainsapi.validation.requireParamNotNull
import org.jboss.resteasy.reactive.RestForm
import org.jboss.resteasy.reactive.RestPath
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path

@Path("relations/{relationId}/courses")
class CoursesEndpoint(
    private val courseRepository: CourseRepository
) {
    @POST
    fun addCourse(
        @RestPath relationId: RelationId,
        @RestForm departureTime: String?
    ) {
        requireParamNotNull("departureTime", departureTime)

        courseRepository.addCourse(
            relationId = relationId,
            departureTime = MinuteOfDay.parse(departureTime)
        )
    }

    @GET
    fun listCourses(
        @RestPath relationId: RelationId
    ): List<String> = courseRepository
        .findAllCourses(relationId)
        .map { it.toString() }

    @Path("{departureTime}")
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