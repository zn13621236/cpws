package com.casual.feed.mongo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.mongojack.ObjectId;

import javax.persistence.Entity;
import java.security.Principal;

/**
 * @author: ayang
 */
@Entity (name = RepositoryConstants.Collections.SESSIONS)
@JsonSerialize (include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties (ignoreUnknown = true)
public class Session implements Principal {
	@ObjectId
	@JsonProperty (RepositoryConstants.Fields._ID)
	private String id;

	@JsonProperty (RepositoryConstants.Fields.CLIENT_ID)
	private String clientId;

	@JsonProperty (RepositoryConstants.Fields.USER_ID)
	private String userId;

	@JsonProperty (RepositoryConstants.Fields.TOKEN)
	private String token;

	@JsonProperty (RepositoryConstants.Fields.CREATED_TIME)
	private long createdTime;

	@JsonProperty (RepositoryConstants.Fields.EXPIRED_TIME)
	private long expiredTime;

	@JsonIgnore
	private Client client;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(long expiredTime) {
		this.expiredTime = expiredTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@JsonIgnore
	public boolean isExpired() {
		return System.currentTimeMillis() >= expiredTime;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	@JsonIgnore
	public String getName() {
		return id;
	}
}
