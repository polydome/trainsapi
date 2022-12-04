package com.github.polydome.trainsapi.data

import com.github.polydome.trainsapi.model.Relation
import com.github.polydome.trainsapi.model.RelationId
import com.github.polydome.trainsapi.repository.RelationRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class InMemoryRelationRepository : RelationRepository {
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
}