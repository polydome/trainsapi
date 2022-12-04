package com.github.polydome.trainsapi.validation

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val error: String
)