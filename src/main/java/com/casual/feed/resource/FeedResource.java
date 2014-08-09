package com.casual.feed.resource;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import com.casual.feed.mongo.domain.Feed;
import com.casual.feed.service.FeedService;

/**
 * @author nanzhao
 */
@Path ("/v1/feed")
@Component
public class FeedResource {

    @Autowired
    FeedService feedService;

    @GET
    @Path ("/{id}")
    @Produces (MediaType.APPLICATION_JSON)
    public List <Feed> getFeed (@PathVariable ("id") String userId) {
        return feedService.listFeed (userId);
    }

    @PUT
    @Path ("/{id}")
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Feed addFeed (@PathVariable ("id") String userId, Feed feed) {
        return feedService.addFeed (feed.getContent (), userId);
    }
    

    
}
