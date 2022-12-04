package com.github.polydome.trainsapi.model

import kotlinx.serialization.Serializable

@Serializable
data class Destination(
    val stationName: String,
    val platform: PlatformIndex,
    val track: TrackIndex,
    val arrivalTime: Int?,
    val departureTime: Int?
) {
    @Serializable
    @JvmInline
    value class TrackIndex(val value: Int)

    @Serializable
    @JvmInline
    value class PlatformIndex(val value: Int)
}
