package com.github.polydome.trainsapi.model

import kotlinx.serialization.Serializable

@Serializable
data class Connection(
    val departureTime: String,
    val arrivalTime: String
)