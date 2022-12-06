package com.github.polydome.trainsapi.data

import com.github.polydome.trainsapi.data.entity.RelationEntity
import com.github.polydome.trainsapi.data.entity.toEntity
import com.github.polydome.trainsapi.data.entity.toObjectId
import com.github.polydome.trainsapi.model.Destination
import com.github.polydome.trainsapi.model.RelationId
import com.github.polydome.trainsapi.repository.DestinationRepository
import com.github.polydome.trainsapi.repository.NoSuchResourceException
import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MongoDestinationRepository : DestinationRepository, PanacheMongoRepository<RelationEntity> {
    override fun addDestination(relationId: RelationId, destination: Destination) {
        val relation = findById(relationId.toObjectId()) ?: throw NoSuchResourceException()

        val newRelation = relation.copy(
            destinations = relation.destinations + destination.toEntity()
        )

        update(newRelation)
    }

    override fun removeDestination(relationId: RelationId, destinationIndex: Int) {
        deleteById(relationId.toObjectId())
    }
}