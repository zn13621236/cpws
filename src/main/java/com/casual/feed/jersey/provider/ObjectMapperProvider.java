package com.casual.feed.jersey.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * @author: ayang
 */
@Provider
@Component
public class ObjectMapperProvider implements ContextResolver<ObjectMapper> {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }
}
