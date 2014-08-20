package com.casual.feed.jersey.security;

import com.casual.feed.mongo.domain.Client;
import com.casual.feed.mongo.domain.Session;
import com.casual.feed.service.ClientService;
import com.google.common.base.Charsets;
import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.internal.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author: ayang
 */
@Provider
@CredentialAuth
public class AuthenticationFilter implements ContainerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    private static final String BASIC_PREFIX = "Basic ";
    private static final String COLON = ":";

    @Inject
    private ClientService clientService;

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        String authorization = StringUtils.trimToNull(request.getHeaderString(AuthorizationFilter.AUTHORIZATION_HEADER));
        if (authorization == null) {
            throw new ForbiddenException("missing_field.client");
        }
        if (!authorization.startsWith(BASIC_PREFIX)) {
            throw new ForbiddenException("invalid_value.client");
        }
        String credential = base64ToPlainText(authorization.substring(BASIC_PREFIX.length()));
        int delimiter = credential.indexOf(COLON);
        String clientId = null, password = null;
        if (delimiter > 0) {
            clientId = credential.substring(0, delimiter);
            password = credential.substring(delimiter + 1);
        }
        logger.debug("Authorization header found for client [{}]", clientId);
        if (StringUtils.isBlank(clientId) || StringUtils.isEmpty(password)) {
            throw new ForbiddenException("invalid_value.client");
        }
        Client client = clientService.getClientById(clientId);
        if (client == null) {
            throw new ForbiddenException("not_found.client");
        }
        if (!client.getClientSecret().equals(password)) {
            throw new ForbiddenException("invalid_value.client");
        }
        request.setProperty("session", new Session(client));
        logger.debug("client auth takes {} milliseconds", stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
    }

    private static String base64ToPlainText(String base64) {
        try {
            return new String(decodeBase64(base64), Charsets.UTF_8);
        } catch (IllegalArgumentException e) {
            throw new ForbiddenException("invalid_value.client");
        }
    }

    private static byte[] decodeBase64(String base64) {
        return Base64.decode(base64.getBytes(Charsets.UTF_8));
    }
}
