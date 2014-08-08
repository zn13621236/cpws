package com.casual.feed.mongo.repository;

import com.casual.feed.mongo.domain.User;
import com.mongodb.BasicDBObject;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.springframework.beans.factory.InitializingBean;

import static com.casual.feed.mongo.domain.RepositoryConstants.Collections.USERS;
import static com.casual.feed.mongo.domain.RepositoryConstants.Fields.*;

/**
 * @author nanzhao
 */
public class DefaultUserRepository extends AbstractMongoRepository implements UserRepository, InitializingBean {
    private JacksonDBCollection<User, String> userCollection;

    @Override
    public User getUserById(String userId) {
        return userCollection.findOne(DBQuery.is(_ID, userId));
    }

    @Override
    public User getUserByEmail(String email) {
        return userCollection.findOne(DBQuery.is(EMAIL, email).is(STATUS, "active"));
    }

    @Override
    public void saveUser(User user) {
        userCollection.save(user);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        userCollection = getCollection(User.class, USERS);
        userCollection.createIndex(new BasicDBObject(EMAIL, 1).append("unique", "true"));
    }
}
