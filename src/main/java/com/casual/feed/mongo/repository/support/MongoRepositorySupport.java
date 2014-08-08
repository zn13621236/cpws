package com.casual.feed.mongo.repository.support;

import com.casual.feed.mongo.WriteResultChecking;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import org.mongojack.JacksonDBCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * @author: ayang
 */
public class MongoRepositorySupport {
    private static final Logger logger = LoggerFactory.getLogger(MongoRepositorySupport.class);

    public <T> JacksonDBCollection<T, String> getCollection(DB db, Class<T> clazz, String collectionName, ObjectMapper objectMapper) {
        return JacksonDBCollection.wrap(db.getCollection(collectionName), clazz, String.class, objectMapper);
    }

    public void handleAnyWriteResultErrors(WriteResult wr, DBObject query, String operation, WriteResultChecking writeResultChecking) {
        if (WriteResultChecking.NONE == writeResultChecking) {
            return;
        }
        @SuppressWarnings("deprecation")
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
