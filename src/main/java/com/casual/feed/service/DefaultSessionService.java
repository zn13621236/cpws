package com.casual.feed.service;

import com.casual.feed.mongo.domain.Client;
import com.casual.feed.mongo.domain.Session;
import com.casual.feed.mongo.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ayang
 */
@Service
public class DefaultSessionService implements SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Override
    public Session getSessionByToken(String token) {
        Session session = sessionRepository.getSessionByToken(token);
        if (session != null) {
            Client client = clientService.getClientById(session.getClientId());
            if (client == null) {
                return null;
            }
            session.setClientId(client.getClientId());
        }
        return session;
    }

    public Session createSession(String clientId, String userId) {
        Session session = new Session();
        session.setClientId(clientId);
        session.setUserId(userId);
        session.setToken(tokenGenerator.generateToken());
        sessionRepository.saveSession(session);
        return session;
    }
}
