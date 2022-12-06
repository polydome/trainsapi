package com.github.polydome.trainsapi.data

import com.github.polydome.trainsapi.data.entity.CourseEntity
import com.github.polydome.trainsapi.data.entity.toObjectId
import com.github.polydome.trainsapi.model.MinuteOfDay
import com.github.polydome.trainsapi.model.RelationId
import com.github.polydome.trainsapi.repository.CourseRepository
import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MongoCourseRepository : CourseRepository, PanacheMongoRepository<CourseEntity> {
    override fun addCourse(relationId: RelationId, departureTime: MinuteOfDay) {
        val entity = CourseEntity(
            relationId = relationId.toObjectId(),
            departureTime = departureTime.value
        )

        persist(entity)
    }

    override fun removeCourse(relationId: RelationId, departureTime: MinuteOfDay) {
        delete("relationId = ?1 and departureTime = ?2", relationId.toObjectId(), departureTime.value)
    }

    override fun findCoursesByRelationId(relationId: RelationId): List<MinuteOfDay> {
        return find("relationId = ?1", relationId.toObjectId())
            .list()
            .map { MinuteOfDay(it.departureTime) }
    }
}