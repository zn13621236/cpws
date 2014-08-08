package com.casual.poll.repository;

/**
 * @author: ayang
 */
public interface ClientRepository {
    public Client getClientById(String clientId);

    public void saveClient(Client client);
}
