package com.casual.feed.service;

import com.casual.feed.mongo.domain.Client;
import com.casual.feed.mongo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ayang
 */
@Service
public class DefaultClientService implements ClientService {
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Client getClientById(String clientId) {
		return clientRepository.getClientById(clientId);
	}
}
