package com.casual.feed;

import com.casual.feed.context.RequestSession;
import com.casual.feed.context.RequestSessionInjectee;
import com.casual.feed.jersey.security.AuthenticationFilter;
import com.casual.feed.jersey.security.AuthorizationFilter;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
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
        register(AuthenticationFilter.class);
        register(AuthorizationFilter.class);

        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(RequestSessionInjectee.class)
                        .to(RequestSession.class)
                        .proxy(false)
                        .in(RequestScoped.class);
            }
        });

        packages("com.casual.feed.resource");
    }
}
