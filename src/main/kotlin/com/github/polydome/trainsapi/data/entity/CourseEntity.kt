package com.github.polydome.trainsapi.data.entity

import io.quarkus.mongodb.panache.common.MongoEntity
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId

@MongoEntity(collection = "courses")
data class CourseEntity @BsonCreator constructor(
    @BsonId val id: ObjectId = ObjectId.get(),
    @BsonProperty("relationId") val relationId: ObjectId,
    @BsonProperty("departureTime") val departureTime: Int
)