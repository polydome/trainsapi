package com.github.polydome.trainsapi.repository

import com.github.polydome.trainsapi.model.Destination
import com.github.polydome.trainsapi.model.RelationId

interface DestinationRepository {
    fun addDestination(relationId: RelationId, destination: Destination)
    fun removeDestination(relationId: RelationId, destinationIndex: Int)
}