package com.github.polydome.trainsapi.repository

import com.github.polydome.trainsapi.model.Relation
import com.github.polydome.trainsapi.model.RelationId

interface RelationRepository {
    fun createRelation(name: String)
    fun findRelation(id: RelationId): Relation?
    fun findAllRelations(): List<Relation>
    fun findRelationsBetweenStations(
        startStation: String,
        endStation: String
    ): List<Relation>
}