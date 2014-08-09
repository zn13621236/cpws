package com.casual.feed.service;

import java.util.List;
import com.casual.feed.mongo.domain.Feed;

public interface FeedService {

    Feed addFeed (String content, String userId);

    void likeFeed (String feedId, String userId);

    void dislikeFeed (String feedId, String userId);

    List <Feed> listFeed (String userId);
}
