package com.casual.feed.service;

import com.casual.feed.mongo.domain.Session;

/**
 * @author: ayang
 */
public interface SessionService {
	Session getSessionByToken(String token);
}
