package com.github.polydome.trainsapi.data

import com.github.polydome.trainsapi.model.Destination
import com.github.polydome.trainsapi.model.MinuteOfDay
import com.github.polydome.trainsapi.model.Relation
import com.github.polydome.trainsapi.model.RelationId
import com.github.polydome.trainsapi.repository.CourseRepository
import com.github.polydome.trainsapi.repository.DestinationRepository
import com.github.polydome.trainsapi.repository.NoSuchResourceException
import com.github.polydome.trainsapi.repository.RelationRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class InMemoryRelationRepository : RelationRepository, DestinationRepository, CourseRepository {
    private val relations = mutableListOf<Relation>()
    private val courses = mutableMapOf<RelationId, MutableList<MinuteOfDay>>()

    override fun createRelation(name: String) {
        val id = RelationId(name.hashCode().toString())
        val relation = Relation(
            name = name,
            id = id
        )
        relations.add(relation)
        courses[relation.id] = mutableListOf()
    }

    override fun findRelation(id: RelationId): Relation? {
        return relations.find { it.id == id }
    }

    override fun findAllRelations(): List<Relation> {
        return relations
    }

    override fun findRelationsBetweenStations(startStation: String, endStation: String): List<Relation> {
        return relations.filter {
            it.destinations.first().stationName == startStation &&
                    it.destinations.last().stationName == endStation
        }
    }

    override fun removeById(relationId: RelationId) {
        val relation = relations.find { it.id == relationId } ?: throw NoSuchResourceException()
        relations.remove(relation)
    }

    override fun addDestination(relationId: RelationId, destination: Destination) {
        relations.find { it.id == relationId }?.let {
            val index = relations.indexOf(it)
            relations.removeAt(index)
            relations.add(index, it.copy(destinations = it.destinations.plus(destination)))
        }
    }

    override fun removeDestination(relationId: RelationId, destinationIndex: Int) {
        relations.find { it.id == relationId }?.let { relation ->
            val destination = relation.destinations[destinationIndex]
            val index = relations.indexOf(relation)
            relations.removeAt(index)
            relations.add(index, relation.copy(destinations = relation.destinations.minus(destination)))
        }
    }

    override fun addCourse(relationId: RelationId, departureTime: MinuteOfDay) {
        courses[relationId]?.add(departureTime)
    }

    override fun removeCourse(relationId: RelationId, departureTime: MinuteOfDay) {
        courses[relationId]?.remove(departureTime)
    }

    override fun findCoursesByRelationId(relationId: RelationId): List<MinuteOfDay> {
        return courses.getOrDefault(relationId, emptyList())
    }

    override fun findAllCourses(relationId: RelationId): List<MinuteOfDay> {
        return courses[relationId] ?: throw NoSuchResourceException()
    }
}