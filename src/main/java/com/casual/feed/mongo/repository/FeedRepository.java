package com.casual.feed.mongo.repository;

import java.util.List;
import com.casual.feed.mongo.domain.Feed;

/**
 * @author nanzhao
 */
public interface FeedRepository {

    public Feed getFeedById (String feedId);

    public void saveFeed (Feed feed);

    List <Feed> getFeedByUserId (String userId);
}
