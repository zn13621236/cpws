package com.casual.poll.repository;

import com.casual.poll.mongo.WriteResultChecking;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import org.mongojack.JacksonDBCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * @author: ayang
 */
abstract public class BaseRepository {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DB db;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WriteResultChecking writeResultChecking;

    protected <T> JacksonDBCollection<T, String> getCollection(Class<T> clazz, String collectionName) {
        DBCollection dbCollection = db.getCollection(collectionName);
        return JacksonDBCollection.wrap(dbCollection, clazz, String.class, objectMapper);
    }

    protected void handleAnyWriteResultErrors(WriteResult wr, DBObject query, String operation) {
        if (WriteResultChecking.NONE == writeResultChecking) {
            return;
        }
        String error = wr.getError();
        int n = wr.getN();
        if (error != null) {
            String message = String.format("Execution of [%s] using [%s] query failed: %s", operation, query == null ? "" : query.toString(), error);
            if (WriteResultChecking.EXCEPTION == writeResultChecking) {
                throw new DataIntegrityViolationException(message);
            } else {
                logger.error(message);
            }
        } else if (n == 0) {
            String message = String.format("Execution of [%s] using [%s] query did not succeed: 0 documents updated", operation, query == null ? "" : query.toString());
            logger.info(message);
        }
    }
}
