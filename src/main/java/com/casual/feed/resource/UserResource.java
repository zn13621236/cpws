package com.casual.feed.resource;

import com.casual.feed.context.RequestSession;
import com.casual.feed.jersey.security.CredentialAuth;
import com.casual.feed.jersey.security.TokenAuth;
import com.casual.feed.resource.domain.request.CreateUserRequest;
import com.casual.feed.resource.domain.request.LoginRequest;
import com.casual.feed.resource.domain.request.UpdatePasswordRequest;
import com.casual.feed.resource.domain.request.UpdateUserRequest;
import com.casual.feed.resource.domain.response.UserResponse;
import com.casual.feed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * @author jinglongyang
 */
@Path("/v1/users")
@Component
public class UserResource {
    @Autowired
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @CredentialAuth
    public UserResponse createUser(@Context RequestSession session, CreateUserRequest createUserRequest) {
        return userService.createUser(session, createUserRequest);
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @CredentialAuth
    public UserResponse login(@Context RequestSession session, LoginRequest loginRequest) {
        return userService.login(session, loginRequest);
    }

    @GET
    @Path("/me")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TokenAuth
    public UserResponse getMyInfo(@Context RequestSession session) {
        return getUserById(session.getUserId());
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TokenAuth
    public UserResponse getUserById(@PathParam("id") String id) {
        return userService.getUserById(id);
    }

    @POST
    @Path("/me")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TokenAuth
    public void updateMyInfo(@Context RequestSession session, UpdateUserRequest updateUserRequest) {
    }


    @POST
    @Path("/me/password")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TokenAuth
    public void updateMyPassword(@Context RequestSession session, UpdatePasswordRequest updatePasswordRequest) {
    }
}
