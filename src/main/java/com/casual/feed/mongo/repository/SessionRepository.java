package com.casual.feed.mongo.repository;

import com.casual.feed.mongo.domain.Session;

import java.util.List;

/**
 * @author nanzhao
 */
public interface SessionRepository {

    public Session getSessionByUserId(String userId, String clientId);

    public void saveSession(Session session);
}
