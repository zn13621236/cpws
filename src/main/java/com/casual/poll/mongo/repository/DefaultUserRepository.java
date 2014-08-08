package com.casual.poll.mongo.repository;

import org.mongojack.JacksonDBCollection;
import org.springframework.beans.factory.InitializingBean;
import com.casual.poll.mongo.domain.FieldName;
import com.casual.poll.mongo.domain.User;
import com.mongodb.BasicDBObject;

/**
 * @author nanzhao
 */
public class DefaultUserRepository extends BaseRepository implements UserRepository, InitializingBean {

    private JacksonDBCollection <User, String> userCollection;

    @Override
    public User getUserById (String userId) {
        return userCollection.findOne (new BasicDBObject ("guid", userId).append (FieldName.STATUS, "active"));
    }

    @Override
    public void saveUser (User user) {
        userCollection.save (user);
    }

    @Override
    public void afterPropertiesSet () throws Exception {
        userCollection = getCollection (User.class, "users");
        userCollection.createIndex (new BasicDBObject (FieldName.USER_ID, 1).append ("unique", "true"));
    }
}
