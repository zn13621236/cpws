package com.casual.feed.mongo.repository;

import com.casual.feed.mongo.domain.Feed;
import com.mongodb.BasicDBObject;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;
import java.util.List;
import static com.casual.feed.mongo.domain.RepositoryConstants.Collections.FEEDS;
import static com.casual.feed.mongo.domain.RepositoryConstants.Fields.USER_ID;
import static com.casual.feed.mongo.domain.RepositoryConstants.Fields._ID;

/**
 * @author nanzhao
 */
@Repository
public class DefaultFeedRepository extends AbstractMongoRepository implements FeedRepository, InitializingBean {
    private JacksonDBCollection<Feed, String> feedCollection;

    @Override
    public Feed getFeedById(String feedId) {
        return feedCollection.findOne(DBQuery.is(_ID, feedId));
    }

    @Override
    public List<Feed> getFeedByUserId(String userId) {
        return feedCollection.find(DBQuery.is(USER_ID, userId)).toArray();
    }

    @Override
    public void saveFeed(Feed feed) {
        WriteResult<Feed, String> result = feedCollection.save(feed);
        feed.setId(result.getSavedId());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        feedCollection = getCollection(Feed.class, FEEDS);
        feedCollection.createIndex(new BasicDBObject(USER_ID, 1));
    }
}
