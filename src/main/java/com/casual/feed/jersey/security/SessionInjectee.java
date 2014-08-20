package com.casual.feed.jersey.security;

import com.casual.feed.mongo.domain.Session;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;

/**
 * @author: ayang
 */
public class SessionInjectee extends Session {
    private final Session session;

    @Inject
    public SessionInjectee(ContainerRequestContext request) {
        this.session = (Session) request.getProperty("session");
    }

}
