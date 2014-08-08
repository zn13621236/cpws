package com.casual.feed.mongo.repository;

import com.casual.feed.mongo.domain.Feed;
import com.casual.feed.mongo.domain.RepositoryConstants;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * @author nanzhao
 */
public class DefaultFeedRepository extends AbstractMongoRepository implements FeedRepository, InitializingBean {
    private JacksonDBCollection<Feed, String> feedCollection;

    @Override
    public Feed getFeedById(String feedId) {
        return feedCollection.findOne(DBQuery.is(RepositoryConstants.Fields._ID, feedId));
    }

    @Override
    public List<Feed> getFeedByUserId(String userId) {
        return feedCollection.find(DBQuery.is(RepositoryConstants.Fields.USER_ID, userId)).toArray();
    }

    @Override
    public void saveFeed(Feed feed) {
        WriteResult<Feed, String> result = feedCollection.save(feed);
        feed.setId(result.getSavedId());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        feedCollection = getCollection(Feed.class, RepositoryConstants.Collections.FEEDS);
    }
}
