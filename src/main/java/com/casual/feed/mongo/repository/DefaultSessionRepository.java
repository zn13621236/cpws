package com.casual.feed.mongo.repository;

import com.casual.feed.mongo.domain.RepositoryConstants;
import com.casual.feed.mongo.domain.Session;
import com.mongodb.BasicDBObject;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author nanzhao
 */
public class DefaultSessionRepository extends AbstractMongoRepository implements SessionRepository, InitializingBean {
    private JacksonDBCollection<Session, String> sessionCollection;

    @Override
    public Session getSessionByUserId(String userId, String clientId) {
        return sessionCollection.findOne(DBQuery.is(RepositoryConstants.Fields.USER_ID, userId).is(RepositoryConstants.Fields.CLIENT_ID, clientId));
    }

    @Override
    public void saveSession(Session session) {
        sessionCollection.save(session);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        sessionCollection = getCollection(Session.class, RepositoryConstants.Collections.SESSIONS);
        sessionCollection.createIndex(new BasicDBObject(RepositoryConstants.Fields.USER_ID, 1).append(RepositoryConstants.Fields.CLIENT_ID, 1).append("unique", "true"));
    }
}
