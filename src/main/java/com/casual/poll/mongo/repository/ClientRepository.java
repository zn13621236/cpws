package com.casual.poll.mongo.repository;

import com.casual.poll.mongo.domain.Client;

/**
 * @author: ayang
 */
public interface ClientRepository {

    public Client getClientById (String clientId);

    public void saveClient (Client client);
}
