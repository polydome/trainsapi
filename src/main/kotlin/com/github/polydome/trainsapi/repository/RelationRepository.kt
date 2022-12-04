package com.github.polydome.trainsapi.repository

import com.github.polydome.trainsapi.model.RelationId

interface RelationRepository {
    fun createRelation(name: String)
    fun findRelation(id: RelationId): RelationId
}