package com.casual.feed.service;

import com.casual.feed.context.RequestSession;
import com.casual.feed.resource.domain.request.CreateUserRequest;
import com.casual.feed.resource.domain.request.LoginRequest;
import com.casual.feed.resource.domain.response.UserResponse;

public interface UserService {
    UserResponse login(RequestSession session, LoginRequest loginRequest);

    UserResponse createUser(RequestSession session, CreateUserRequest createUserRequest);

    UserResponse getUserById(String id);
}
