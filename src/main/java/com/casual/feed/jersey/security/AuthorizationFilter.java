package com.casual.feed.jersey.security;

import com.casual.feed.jersey.exception.ForbiddenException;
import com.casual.feed.mongo.domain.Session;
import com.casual.feed.service.SessionService;
import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author: ayang
 */
@Provider
@TokenAuth
public class AuthorizationFilter implements ContainerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);
    private static final String AUTHORIZATION_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "authorization";

    @Inject
    private SessionService sessionService;

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        String authorization = StringUtils.trimToNull(request.getHeaderString(AUTHORIZATION_HEADER));
        logger.debug("The Authorization header is [{}]", authorization);
        if (authorization == null) {
            throw new ForbiddenException("missing_field.token");
        }
        if (!authorization.startsWith(AUTHORIZATION_PREFIX)) {
            throw new ForbiddenException("invalid_value.token");
        }
        String token = authorization.substring(AUTHORIZATION_PREFIX.length());
        Session session = sessionService.getSessionByToken(token);
        if (session == null) {
            throw new ForbiddenException("not_found.token");
        }
        if (session.isExpired()) {
            throw new ForbiddenException("expired.token");
        }
        request.setProperty("session", session);
        long time = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
        logger.debug("security token check takes {} milliseconds", time);
    }
}
