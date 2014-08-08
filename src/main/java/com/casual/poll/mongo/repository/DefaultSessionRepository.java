package com.casual.poll.mongo.repository;

import java.util.List;
import org.mongojack.JacksonDBCollection;
import org.springframework.beans.factory.InitializingBean;
import com.casual.poll.mongo.domain.FieldName;
import com.casual.poll.mongo.domain.Session;
import com.mongodb.BasicDBObject;

/**
 * @author nanzhao
 */
public class DefaultSessionRepository extends BaseRepository implements SessionRepository, InitializingBean {

    private JacksonDBCollection <Session, String> sessionCollection;

    @Override
    public List <Session> getSessionByUserId (String userId) {
        return sessionCollection.find (new BasicDBObject (FieldName.USER_ID, userId).append (FieldName.STATUS, "active")).toArray ();
    }

    @Override
    public void saveSession (Session session) {
        sessionCollection.save (session);
    }

    @Override
    public void afterPropertiesSet () throws Exception {
        sessionCollection = getCollection (Session.class, "session");
        sessionCollection.createIndex (new BasicDBObject (FieldName.USER_ID, 1).append ("unique", "true"));
    }
}
