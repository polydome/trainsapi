package com.github.polydome.trainsapi.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Relation(
    @Contextual val id: RelationId,
    val name: String,
    val destinations: List<Destination> = emptyList()
)
