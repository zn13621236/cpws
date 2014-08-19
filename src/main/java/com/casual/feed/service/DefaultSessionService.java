package com.casual.feed.service;

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

	@Override
	public Session getSessionByToken(String token) {
		Session session = sessionRepository.getSessionByToken(token);
		if (session != null) {
			session.setClient(clientService.getClientById(session.getClientId()));
		}
		return session;
	}
}
