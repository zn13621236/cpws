package com.casual.poll.mongo.repository;

import java.util.List;
import org.mongojack.JacksonDBCollection;
import org.springframework.beans.factory.InitializingBean;
import com.casual.poll.mongo.domain.Feed;
import com.casual.poll.mongo.domain.FieldName;
import com.mongodb.BasicDBObject;

/**
 * @author nanzhao
 */
public class DefaultFeedRepository extends BaseRepository implements FeedRepository, InitializingBean {

    private JacksonDBCollection<Feed, String> feedCollection;

//    private final CacheLoader<String, List<Feed> > feedLoader = new CacheLoader<String, List<Feed> >() {
//        public List<Feed> load(String voteNum) {
//            return feedCollection.find(new BasicDBObject(FieldName.FEED_ID, voteNum).append(FieldName.STATUS, "active")).sort (DBSort.desc("voteNum")).limit (200).toArray ();
//        }
//    };
//
//    private final LoadingCache<String, List<Feed> > feeds = CacheBuilder.newBuilder().maximumSize(50).build(feedLoader);
//    
    

    @Override
    public Feed getFeedById (String feedId) {
     return   feedCollection.findOne(new BasicDBObject("guid", feedId).append(FieldName.STATUS, "active"));
    }
    
    @Override
    public List<Feed> getFeedByUserId (String userId) {
     return   feedCollection.find(new BasicDBObject(FieldName.USER_ID, userId).append(FieldName.STATUS, "active")).toArray ();
    }

    @Override
    public void saveFeed (Feed feed) {
        feedCollection.save (feed);
    }
    
    @Override
    public void afterPropertiesSet () throws Exception {
        feedCollection = getCollection(Feed.class, "feeds");
        feedCollection.createIndex(new BasicDBObject(FieldName.FEED_ID, 1).append("unique", "true"));
    }
}
