package com.github.polydome.trainsapi.data

import com.github.polydome.trainsapi.data.entity.RelationEntity
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import io.quarkus.arc.DefaultBean
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection
import javax.enterprise.context.Dependent
import javax.ws.rs.Produces

class MongoConfiguration {
    @Produces
    @Dependent
    @DefaultBean
    fun relationsCollection(database: MongoDatabase): MongoCollection<RelationEntity> =
        database.getCollection<RelationEntity>("relations")

    @Produces
    @Dependent
    @DefaultBean
    fun database(client: MongoClient): MongoDatabase {
        return client.getDatabase("trains")
    }

    @Produces
    @Dependent
    @DefaultBean
    fun client(): MongoClient {
        return KMongo.createClient()
    }
}