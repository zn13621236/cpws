package com.casual.poll;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * @author: ayang
 */
public class Application extends ResourceConfig {
    /**
     * Register JAX-RS application components.
     */
    public Application() {
        register(RequestContextFilter.class);
        packages("com.casual.poll.resource");
    }
}
