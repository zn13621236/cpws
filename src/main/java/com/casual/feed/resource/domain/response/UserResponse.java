package com.casual.feed.resource.domain.response;

import com.casual.feed.mongo.domain.Session;
import com.casual.feed.mongo.domain.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author: ayang
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserResponse {
    private final String username;
    private final String email;
    private final String token;
    private final String id;

    public UserResponse(User user, Session session) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.id = user.getId();
        this.token = session == null ? null : session.getToken();
    }

    public UserResponse(User user) {
        this(user, null);
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public String getId() {
        return id;
    }
}
