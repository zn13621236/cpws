package com.casual.feed.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author: ayang
 */
@Component
public class ObjectMapperFactoryBean implements FactoryBean<ObjectMapper>, InitializingBean {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ObjectMapper getObject() throws Exception {
        return objectMapper;
    }

    @Override
    public Class<?> getObjectType() {
        return ObjectMapper.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}
