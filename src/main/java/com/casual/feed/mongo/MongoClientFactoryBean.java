package com.casual.feed.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: ayang
 */
@Component("mongoClient")
public class MongoClientFactoryBean implements FactoryBean<MongoClient>, InitializingBean {
    @Value("${mongo.url}")
    private String url;

    private MongoClient mongoClient;

    @Override
    public MongoClient getObject() throws Exception {
        return mongoClient;
    }

    @Override
    public Class<?> getObjectType() {
        return MongoClient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        mongoClient = new MongoClient(new MongoClientURI(url));
    }
}
