package com.casual.feed.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casual.feed.mongo.domain.Feed;
import com.casual.feed.mongo.repository.FeedRepository;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    FeedRepository fr;

    /**
     * add feed
     */
    @Override
    public Feed addFeed (String content, String userId) {
        if (content == null || userId == null)
            return null;
        Feed feed = new Feed ();
        feed.setContent (content);
        feed.setCreatedTime (System.currentTimeMillis ());
        feed.setUserId (userId);
        return fr.saveFeed (feed);
    }

    /**
     * not sure if we need this
     */
    @Override
    public void likeFeed (String feedId, String userId) {
        if (feedId == null || userId == null)
            return;
        Feed fd = fr.getFeedById (feedId);
        fd.setLikeCount (fd.getLikeCount () + 1L);
        fr.saveFeed (fd);
    }

    /**
     * not sure if we need this
     */
    @Override
    public void dislikeFeed (String feedId, String userId) {
        if (feedId == null || userId == null)
            return;
        Feed fd = fr.getFeedById (feedId);
        fd.setDislikeCount (fd.getDislikeCount () + 1L);
        fr.saveFeed (fd);
    }

    /**
     * list feed for the user
     */
    @Override
    public List <Feed> listFeed (String userId) {
        if (userId == null)
            return null;
        List <Feed> feeds = fr.getFeedByUserId (userId);
        return feeds;
    }
}
