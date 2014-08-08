package com.casual.feed.mongo.repository;

import com.casual.feed.mongo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: ayang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class DefaultUserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() throws Exception {
        User user = new User();
        user.setEmail("a@b.com");
        user.setPassword("a@b.com");
        user.setStatus(User.UserStatus.CONFORMED);
        userRepository.saveUser(user);
    }
}
