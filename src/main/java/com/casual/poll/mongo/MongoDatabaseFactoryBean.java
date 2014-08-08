package com.casual.poll.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: ayang
 */
@Component
public class MongoDatabaseFactoryBean implements FactoryBean<DB>, InitializingBean {
    @Autowired
    private MongoClient mongoClient;

    @Value("${mongo.db.name:sucks}")
    private String dbName;

    private DB db;

    @Override
    public DB getObject() throws Exception {
        return db;
    }

    @Override
    public Class<?> getObjectType() {
        return DB.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        db = mongoClient.getDB(dbName);
    }
}
