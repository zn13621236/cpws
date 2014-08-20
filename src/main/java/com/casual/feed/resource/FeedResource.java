package com.casual.feed.resource;

import com.casual.feed.context.RequestSession;
import com.casual.feed.jersey.security.CredentialAuth;
import com.casual.feed.mongo.domain.Feed;
import com.casual.feed.service.FeedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

/**
 * @author nanzhao
 */
@Path("/v1/feeds")
@Component
public class FeedResource {
    private static final Logger logger = LoggerFactory.getLogger(FeedResource.class);

    @Autowired
    private FeedService feedService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Feed> getFeed(@PathParam("id") String userId, @HeaderParam("token") String token) {
        return feedService.listFeed(userId);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Feed addFeed(@PathParam("id") String userId, Feed feed) {
        return feedService.addFeed(feed.getContent(), userId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @CredentialAuth
    public List<Feed> getFeeds(@Context RequestSession session) {
        logger.info("request_id={},client_id={},user_id={}", session.getRequestId(), session.getClientId(), session.getUserId());
        Feed feed = new Feed();
        feed.setContent("content");
        feed.setId("id");
        feed.setUserId("user_id");
        return Arrays.asList(feed);
    }


}
