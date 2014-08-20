package com.casual.feed.resource;

import com.casual.feed.resource.domain.response.Status;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author: jinglongyang
 */
@Path("/v1/status")
@Component
public class StatusResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Status getHello() {
        return new Status("ok");
    }
}
