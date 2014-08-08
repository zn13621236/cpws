package com.casual.feed.mongo.repository;

import com.casual.feed.mongo.domain.Session;

/**
 * @author nanzhao
 */
public interface SessionRepository {
    public Session getSessionByUserIdAndClientId(String userId, String clientId);

    public Session getSessionByToken(String token);

    public void saveSession(Session session);
}
