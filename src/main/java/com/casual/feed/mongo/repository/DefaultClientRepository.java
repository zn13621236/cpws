package com.casual.feed.mongo.repository;

import com.casual.feed.mongo.domain.Client;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.UncheckedExecutionException;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import static com.casual.feed.mongo.domain.RepositoryConstants.Collections.CLIENTS;
import static com.casual.feed.mongo.domain.RepositoryConstants.Fields.CLIENT_ID;
import static com.casual.feed.mongo.domain.RepositoryConstants.Fields.STATUS;

/**
 * @author: ayang
 */
@Repository
public class DefaultClientRepository extends AbstractMongoRepository implements ClientRepository, InitializingBean {
    private JacksonDBCollection<Client, String> clientCollection;

    private final CacheLoader<String, Client> clientLoader = new CacheLoader<String, Client>() {
        public Client load(String clientId) {
            return clientCollection.findOne(DBQuery.is(CLIENT_ID, clientId).is(STATUS, "active"));
        }
    };

    private final LoadingCache<String, Client> clients = CacheBuilder.newBuilder().maximumSize(50).build(clientLoader);

    @Override
    public Client getClientById(String clientId) {
        try {
            return clients.getUnchecked(clientId);
        } catch (UncheckedExecutionException | CacheLoader.InvalidCacheLoadException e) {
            //invalid client id
        }
        return null;
    }

    @Override
    public void saveClient(Client client) {
        clientCollection.insert(client);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        clientCollection = getCollection(Client.class, CLIENTS);
    }
}
