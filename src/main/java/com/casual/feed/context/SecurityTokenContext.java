package com.casual.feed.context;

import com.casual.feed.mongo.domain.Session;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.Set;

/**
 * @author: ayang
 */
public class SecurityTokenContext implements SecurityContext {
	public static final String TOKEN_AUTH = "TOKEN";
	private final Session session;

	public SecurityTokenContext(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

	@Override
	public Principal getUserPrincipal() {
		return session;
	}

	@Override
	public boolean isUserInRole(String role) {
		Set<String> roles = session.getClient() == null ? null : session.getClient().getRoles();
		if (roles != null && !roles.contains(role)) {
			throw new WebApplicationException(Response.status(Response.Status.FORBIDDEN).entity("Permission Denied").build());
		}
		return true;
	}

	@Override
	public boolean isSecure() {
		return true;
	}

	@Override
	public String getAuthenticationScheme() {
		return TOKEN_AUTH;
	}
}
