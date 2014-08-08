package com.casual.feed.mongo.repository;

import com.casual.feed.mongo.WriteResultChecking;
import com.casual.feed.mongo.repository.support.MongoRepositorySupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import org.mongojack.JacksonDBCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: ayang
 */
abstract public class AbstractMongoRepository {
    private final static MongoRepositorySupport mongoRepositorySupport = new MongoRepositorySupport();

    @Autowired
    private DB db;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WriteResultChecking writeResultChecking;

    protected <T> JacksonDBCollection<T, String> getCollection(Class<T> clazz, String collectionName) {
        return mongoRepositorySupport.getCollection(db, clazz, collectionName, objectMapper);
    }

    protected void handleAnyWriteResultErrors(WriteResult wr, DBObject query, String operation) {
        mongoRepositorySupport.handleAnyWriteResultErrors(wr, query, operation, writeResultChecking);
    }
}
