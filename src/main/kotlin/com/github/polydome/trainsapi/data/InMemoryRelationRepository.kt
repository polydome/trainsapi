package com.github.polydome.trainsapi.data

import com.github.polydome.trainsapi.model.Destination
import com.github.polydome.trainsapi.model.Relation
import com.github.polydome.trainsapi.model.RelationId
import com.github.polydome.trainsapi.repository.DestinationRepository
import com.github.polydome.trainsapi.repository.RelationRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class InMemoryRelationRepository : RelationRepository, DestinationRepository {
    private val relations = mutableListOf<Relation>()

    override fun createRelation(name: String) {
        val id = RelationId(name.hashCode().toString())
        val relation = Relation(
            name = name,
            id = id
        )
        relations.add(relation)
    }

    override fun findRelation(id: RelationId): Relation? {
        return relations.find { it.id == id }
    }

    override fun findAllRelations(): List<Relation> {
        return relations
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
}