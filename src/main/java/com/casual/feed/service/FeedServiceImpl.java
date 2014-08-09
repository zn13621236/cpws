package com.casual.feed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casual.feed.mongo.domain.Feed;
import com.casual.feed.mongo.repository.FeedRepository;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    FeedRepository fr;

    @Override
    public void addFeed (String content,String userId) {
        Feed feed=new Feed ();
        feed.setContent (content);
        feed.setCreatedTime (System.currentTimeMillis ());
        feed.setUserId (userId);
        fr.saveFeed (feed);
    }
    
    
    @Override
    public void likeFeed (String feedId,String userId) {
       Feed fd=fr.getFeedById (feedId);
       fd.setLikeCount(fd.getLikeCount ()+1L);
       fr.saveFeed (fd);
    }
    
    
    @Override
    public void dislikeFeed (String feedId,String userId) {
       Feed fd=fr.getFeedById (feedId);
       fd.setDislikeCount (fd.getDislikeCount ()+1L);
       fr.saveFeed (fd);
    }
    
}
