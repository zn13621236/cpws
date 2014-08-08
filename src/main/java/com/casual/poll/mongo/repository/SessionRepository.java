package com.casual.poll.mongo.repository;

import java.util.List;
import com.casual.poll.mongo.domain.Session;

/**
 * 
 * @author nanzhao
 *
 */
public interface SessionRepository {

    public List<Session> getSessionByUserId (String userId);

    public void saveSession (Session session);
}
