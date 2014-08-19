package com.casual.feed.service;

import com.casual.feed.mongo.domain.Client;

/**
 * @author: ayang
 */
public interface ClientService {
	Client getClientById(String clientId);
}
