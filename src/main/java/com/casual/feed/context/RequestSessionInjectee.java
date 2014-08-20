package com.casual.feed.context;

import com.casual.feed.mongo.domain.Session;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;

/**
 * @author: ayang
 */
public class RequestSessionInjectee implements RequestSession {
    private final Session session;
    private final String requestId;

    @Inject
    public RequestSessionInjectee(ContainerRequestContext request) {
        this.session = (Session) request.getProperty("session");
        requestId = RequestContextHolder.getRequestContext().getRequestId();
    }

    @Override
    public String getClientId() {
        return session.getClientId();
    }

    @Override
    public String getUserId() {
        return session.getUserId();
    }

    @Override
    public String getRequestId() {
        return requestId;
    }
}
