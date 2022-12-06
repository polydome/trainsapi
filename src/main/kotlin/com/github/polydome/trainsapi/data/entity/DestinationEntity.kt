package com.github.polydome.trainsapi.data.entity

import com.github.polydome.trainsapi.model.Destination
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId

data class DestinationEntity @BsonCreator constructor(
    @BsonId val id: ObjectId = ObjectId.get(),
    @BsonProperty("stationName") val stationName: String,
    @BsonProperty("platform") val platform: Int,
    @BsonProperty("track") val track: Int,
    @BsonProperty("arrivalTime") val arrivalTime: Int?,
    @BsonProperty("departureTime") val departureTime: Int?
)

fun DestinationEntity.toDestination(): Destination = Destination(
    stationName = stationName,
    platform = Destination.PlatformIndex(platform),
    track = Destination.TrackIndex(track),
    arrivalTime = arrivalTime,
    departureTime = departureTime
)

fun Destination.toEntity() = DestinationEntity(
    stationName = stationName,
    platform = platform.value,
    track = track.value,
    arrivalTime = arrivalTime,
    departureTime = departureTime
)