package com.casual.poll.repository;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.mongodb.BasicDBObject;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

/**
 * @author: ayang
 */
@Repository
public class DefaultClientRepository extends BaseRepository implements ClientRepository, InitializingBean {
    private JacksonDBCollection<Client, String> clientCollection;

    private final CacheLoader<String, Client> clientLoader = new CacheLoader<String, Client>() {
        public Client load(String clientId) {
            return clientCollection.findOne(new BasicDBObject(FieldName.CLIENT_ID, clientId).append(FieldName.STATUS, "active"));
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
        WriteResult<Client, String> writeResult = clientCollection.insert(client);
        String id = writeResult.getSavedId();
        client.setId(id);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        clientCollection = getCollection(Client.class, "clients");
        clientCollection.createIndex(new BasicDBObject(FieldName.CLIENT_ID, 1).append("unique", "true"));
    }
}
