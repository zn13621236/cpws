package com.casual.feed.mongo.repository;

import com.casual.feed.mongo.domain.Session;
import com.mongodb.BasicDBObject;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;
import static com.casual.feed.mongo.domain.RepositoryConstants.Collections.SESSIONS;
import static com.casual.feed.mongo.domain.RepositoryConstants.Fields.*;

/**
 * @author nanzhao
 */
@Repository
public class DefaultSessionRepository extends AbstractMongoRepository implements SessionRepository, InitializingBean {
    private JacksonDBCollection<Session, String> sessionCollection;

    @Override
    public Session getSessionByUserIdAndClientId(String userId, String clientId) {
        return sessionCollection.findOne(DBQuery.is(USER_ID, userId).is(CLIENT_ID, clientId));
    }

    @Override
    public Session getSessionByToken(String token) {
        return sessionCollection.findOne(DBQuery.is(TOKEN, token));
    }

    @Override
    public void saveSession(Session session) {
        WriteResult<Session, String> result = sessionCollection.save(session);
        session.setId(result.getSavedId());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        sessionCollection = getCollection(Session.class, SESSIONS);
        sessionCollection.createIndex(new BasicDBObject(USER_ID, 1).append(CLIENT_ID, 1), UNIQUE_INDEX_OPTIONS);
        sessionCollection.createIndex(new BasicDBObject(TOKEN, 1), UNIQUE_INDEX_OPTIONS);
    }
}
