package com.casual.feed.service;

public interface UserService {

    void createUser (String email, String passWord, String clientId);

    boolean authenticateUser (String email, String passWord);

    void removeUser (String email);

    void updateUser (String email, String newEmail);
}
