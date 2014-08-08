package com.casual.feed.mongo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.mongojack.ObjectId;

import javax.persistence.Entity;

/**
 * @author: ayang
 */
@Entity(name = RepositoryConstants.Collections.CLIENTS)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client {
    @JsonProperty(RepositoryConstants.Fields._ID)
    @ObjectId
    private String clientId;

    @JsonProperty(RepositoryConstants.Fields.CLIENT_SECRET)
    private String clientSecret;

    @JsonProperty(RepositoryConstants.Fields.STATUS)
    private ClientStatus status;

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

    public enum ClientStatus {
        PENDING, ACTIVE, DISABLED, DELETED;
    }
}
