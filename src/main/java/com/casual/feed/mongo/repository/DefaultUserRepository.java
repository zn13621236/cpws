package com.casual.feed.mongo.repository;

import com.casual.feed.mongo.domain.User;
import com.mongodb.BasicDBObject;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import static com.casual.feed.mongo.domain.RepositoryConstants.Collections.USERS;
import static com.casual.feed.mongo.domain.RepositoryConstants.Fields.*;

/**
 * @author nanzhao
 */
@Repository
public class DefaultUserRepository extends AbstractMongoRepository implements UserRepository, InitializingBean {
    private JacksonDBCollection<User, String> userCollection;

    @Override
    public User getUserById(String userId) {
        return userCollection.findOne(DBQuery.is(_ID, userId));
    }

    @Override
    public User getUserByEmail(String email) {
        return userCollection.findOne(DBQuery.is(EMAIL, email).in(STATUS, User.UserStatus.CONFORMED, User.UserStatus.UNCONFORMED));
    }

    @Override
    public User saveUser(User user) {
        WriteResult<User, String> result = userCollection.save(user);
        user.setId(result.getSavedId());
        return user;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        userCollection = getCollection(User.class, USERS);
        userCollection.createIndex(new BasicDBObject(EMAIL, 1), UNIQUE_INDEX_OPTIONS);
        userCollection.createIndex(new BasicDBObject(USERNAME, 1), UNIQUE_INDEX_OPTIONS);
    }
}
