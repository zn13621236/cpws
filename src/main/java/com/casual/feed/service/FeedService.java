package com.casual.feed.service;

interface FeedService {

    void addFeed (String content, String userId);

    void likeFeed (String feedId, String userId);

    void dislikeFeed (String feedId, String userId);
}
