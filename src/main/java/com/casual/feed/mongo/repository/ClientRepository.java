package com.casual.feed.mongo.repository;

import com.casual.feed.mongo.domain.Client;

/**
 * @author: ayang
 */
public interface ClientRepository {

    public Client getClientById (String clientId);

    public void saveClient (Client client);
}
