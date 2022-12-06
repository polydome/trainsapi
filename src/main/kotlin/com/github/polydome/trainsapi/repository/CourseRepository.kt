package com.github.polydome.trainsapi.repository

import com.github.polydome.trainsapi.model.MinuteOfDay
import com.github.polydome.trainsapi.model.RelationId

interface CourseRepository {
    fun addCourse(relationId: RelationId, departureTime: MinuteOfDay)
    fun removeCourse(relationId: RelationId, departureTime: MinuteOfDay)
    fun findCoursesByRelationId(relationId: RelationId): List<MinuteOfDay>
}