package com.casual.feed.repository;


import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.casual.feed.mongo.domain.Client;
import com.casual.feed.mongo.repository.ClientRepository;

/**
 * @author: ayang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class DefaultClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testGetClientById() {
        Client client = clientRepository.getClientById("aa");
        Assert.assertEquals("aa", client.getClientId());
    }

    @Test
    public void testSaveClient() {
        Client client = new Client();
        client.setClientId("aa");
        client.setClientSecret("aa");
        client.setStatus("active");
        clientRepository.saveClient(client);
    }
}
