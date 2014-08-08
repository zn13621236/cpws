package com.casual.poll.mongo.repository;

import java.util.List;
import com.casual.poll.mongo.domain.Feed;

/**
 * @author nanzhao
 */
public interface FeedRepository {

    public Feed getFeedById (String feedId);

    public void saveFeed (Feed feed);

    List <Feed> getFeedByUserId (String userId);
}
