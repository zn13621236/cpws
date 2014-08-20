package com.casual.feed.resource;

import com.casual.feed.context.RequestSession;
import com.casual.feed.jersey.security.TokenAuth;
import com.casual.feed.mongo.domain.Feed;
import com.casual.feed.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author nanzhao
 */
@Path("/v1/feeds")
@Component
public class FeedResource {
    @Autowired
    private FeedService feedService;

    @PUT
    @Path("/{id}")
    @TokenAuth
    public void likeFeed(@Context RequestSession session, @PathParam("id") String feedId) {
        feedService.likeFeed(feedId, session.getUserId());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TokenAuth
    public Feed addFeed(@Context RequestSession session, Feed feed) {
        return feedService.addFeed(feed.getContent(), session.getUserId());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @TokenAuth
    public List<Feed> getFeeds(@Context RequestSession session) {
        return feedService.listFeed(session.getUserId());
    }
}
