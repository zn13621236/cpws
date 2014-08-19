package com.casual.feed.mongo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.mongojack.ObjectId;

import javax.persistence.Entity;
import java.security.Principal;
import java.util.Set;

/**
 * @author: ayang
 */
@Entity (name = RepositoryConstants.Collections.CLIENTS)
@JsonSerialize (include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties (ignoreUnknown = true)
public class Client implements Principal {
	@JsonProperty (RepositoryConstants.Fields._ID)
	@ObjectId
	private String clientId;

	@JsonProperty (RepositoryConstants.Fields.CLIENT_SECRET)
	private String clientSecret;

	@JsonProperty (RepositoryConstants.Fields.STATUS)
	private ClientStatus status;

	private Set<String> roles;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public ClientStatus getStatus() {
		return status;
	}

	public void setStatus(ClientStatus status) {
		this.status = status;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	@Override
	@JsonIgnore
	public String getName() {
		return clientId;
	}

	public static enum ClientStatus {
		PENDING,
		ACTIVE,
		DISABLED,
		DELETED;
	}
}
