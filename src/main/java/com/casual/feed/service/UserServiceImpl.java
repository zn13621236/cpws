package com.casual.feed.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casual.feed.mongo.domain.Session;
import com.casual.feed.mongo.domain.User;
import com.casual.feed.mongo.domain.User.UserStatus;
import com.casual.feed.mongo.repository.SessionRepository;
import com.casual.feed.mongo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository    userRepo;
    @Autowired
    SessionRepository sessionRepo;

    /**
     * for user sign up
     */
    @Override
    public void createUser (String email, String passWord, String clientId) {
        // save in user collection
        User user = new User ();
        user.setEmail (email);
        user.setPassword (passWord);
        user.setStatus (UserStatus.CONFORMED);
        user = userRepo.saveUser (user);
        // save in session collection
        Session session = new Session ();
        session.setClientId (clientId);
        session.setUserId (user.getId ());
        session.setToken (UUID.randomUUID ().toString ());
        sessionRepo.saveSession (session);
    }

    /**
     * for user log in
     */
    @Override
    public boolean authenticateUser (String email, String passWord) {
        return true;
    }

    /**
     * for remove user
     */
    @Override
    public void removeUser (String email) {
        if (email == null)
            return;
        User user = userRepo.getUserByEmail (email);
        if (user == null)
            return;
        user.setStatus (UserStatus.DELETED);
        userRepo.saveUser (user);
    }

    /**
     * for update user
     */
    @Override
    public void updateUser (String email, String newEmail) {
        if (email == null)
            return;
        User user = userRepo.getUserByEmail (email);
        if (user == null)
            return;
        user.setEmail (newEmail);
        userRepo.saveUser (user);
    }
}
