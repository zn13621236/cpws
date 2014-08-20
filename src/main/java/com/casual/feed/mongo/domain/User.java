package com.casual.feed.mongo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.mongojack.ObjectId;

import javax.persistence.Entity;

/**
 * @author: ayang
 */
@Entity(name = RepositoryConstants.Collections.USERS)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @ObjectId
    @JsonProperty(RepositoryConstants.Fields._ID)
    private String id;

    @JsonProperty(RepositoryConstants.Fields.USERNAME)
    private String username;

    @JsonProperty(RepositoryConstants.Fields.EMAIL)
    private String email;

    @JsonProperty(RepositoryConstants.Fields.PASSWORD)
    private String password;

    @JsonProperty(RepositoryConstants.Fields.STATUS)
    private UserStatus status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public enum UserStatus {
        UNCONFORMED, CONFORMED, DELETED;

        @JsonCreator
        public static UserStatus fromValue(String status) {
            return UserStatus.valueOf(status);
        }
    }
}
