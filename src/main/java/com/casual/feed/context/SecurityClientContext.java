package com.casual.feed.context;

import com.casual.feed.mongo.domain.Client;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.Set;

/**
 * @author: ayang
 */
public class SecurityClientContext implements SecurityContext {
	public static final String TOKEN_AUTH = "TOKEN";
	private final Client client;

	public SecurityClientContext(Client client) {
		this.client = client;
	}

	@Override
	public Principal getUserPrincipal() {
		return client;
	}

	@Override
	public boolean isUserInRole(String role) {
		Set<String> roles = client == null ? null : client.getRoles();
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
		return BASIC_AUTH;
	}
}
