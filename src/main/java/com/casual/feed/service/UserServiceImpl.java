package com.casual.feed.service;

import com.casual.feed.context.RequestSession;
import com.casual.feed.jersey.exception.GoneException;
import com.casual.feed.jersey.exception.NotFoundException;
import com.casual.feed.jersey.exception.UnauthorizedAccessException;
import com.casual.feed.mongo.domain.User;
import com.casual.feed.mongo.domain.User.UserStatus;
import com.casual.feed.mongo.repository.UserRepository;
import com.casual.feed.resource.domain.request.CreateUserRequest;
import com.casual.feed.resource.domain.request.LoginRequest;
import com.casual.feed.resource.domain.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jinglongyang
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionService sessionService;

    @Override
    public UserResponse login(RequestSession requestSession, LoginRequest loginRequest) {
        loginRequest.getUsername();
        User user = userRepository.getUserByUsername(loginRequest.getUsername());
        if (user == null) {
            throw new UnauthorizedAccessException("not_found.username");
        }
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new UnauthorizedAccessException("invalid_value.password");
        }

        return new UserResponse(user, sessionService.createSession(requestSession.getClientId(), user.getId()));
    }

    @Override
    public UserResponse createUser(RequestSession requestSession, CreateUserRequest createUserRequest) {
        //TODO validation
        User user = new User();
        user.setUsername(createUserRequest.getUsername());
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(createUserRequest.getPassword());
        user.setStatus(UserStatus.CONFORMED);
        userRepository.saveUser(user);

        return new UserResponse(user, sessionService.createSession(requestSession.getClientId(), user.getId()));
    }

    @Override
    public UserResponse getUserById(String id) {
        User user = userRepository.getUserById(id);
        if (user == null) {
            throw new NotFoundException("");
        }
        if (user.getStatus() == UserStatus.DELETED) {
            throw new GoneException("");
        }
        return new UserResponse(user);
    }
}
