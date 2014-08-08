package com.casual.poll.mongo.repository;

import com.casual.poll.mongo.domain.User;

/**
 * 
 * @author nanzhao
 *
 */
public interface UserRepository {

    public User getUserById (String userId);

    public void saveUser (User user);
}
