package com.casual.feed.mongo.repository;

import com.casual.feed.mongo.domain.User;

/**
 * @author nanzhao
 */
public interface UserRepository {
    public User getUserById(String userId);

    public User getUserByEmail(String email);

    public User saveUser(User user);
}
