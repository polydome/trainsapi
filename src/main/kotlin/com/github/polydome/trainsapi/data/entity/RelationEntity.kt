package com.github.polydome.trainsapi.data.entity

import com.github.polydome.trainsapi.model.Relation
import com.github.polydome.trainsapi.model.RelationId
import io.quarkus.mongodb.panache.common.MongoEntity
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId

@MongoEntity(collection = "relations")
data class RelationEntity @BsonCreator constructor(
    @BsonId val id: ObjectId = ObjectId.get(),
    @BsonProperty("name") val name: String,
    @BsonProperty("destinations") val destinations: List<DestinationEntity> = emptyList()
)

fun RelationEntity.toRelation() =
    Relation(
        id = RelationId(id.toString()),
        name = name,
        destinations = destinations.map { it.toDestination() }
    )

fun RelationId.toObjectId() =
    ObjectId(value)