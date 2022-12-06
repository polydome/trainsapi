package com.github.polydome.trainsapi.data

import com.github.polydome.trainsapi.data.entity.RelationEntity
import com.github.polydome.trainsapi.data.entity.toObjectId
import com.github.polydome.trainsapi.data.entity.toRelation
import com.github.polydome.trainsapi.model.Relation
import com.github.polydome.trainsapi.model.RelationId
import com.github.polydome.trainsapi.repository.RelationRepository
import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MongoRelationRepository : RelationRepository, PanacheMongoRepository<RelationEntity> {
    override fun createRelation(name: String) {
        val entity = RelationEntity(
            name = name
        )

        persist(entity)
    }

    override fun findRelation(id: RelationId): Relation? =
        findById(id.toObjectId())?.toRelation()

    override fun findAllRelations(): List<Relation> =
        findAll().list().map { it.toRelation() }

    override fun findRelationsBetweenStations(startStation: String, endStation: String): List<Relation> {
        return find("{ 'destinations.stationName': 'echo' }").list().map { it.toRelation() }
    }

    override fun removeById(relationId: RelationId) {
        deleteById(relationId.toObjectId())
    }

}