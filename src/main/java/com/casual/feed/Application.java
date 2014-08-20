package com.casual.feed;

import com.casual.feed.context.RequestSession;
import com.casual.feed.context.RequestSessionInjectee;
import com.casual.feed.jersey.exception.mapper.CpwsServiceExceptionMapper;
import com.casual.feed.jersey.security.AuthenticationFilter;
import com.casual.feed.jersey.security.AuthorizationFilter;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.server.validation.ValidationConfig;
import org.glassfish.jersey.server.validation.internal.InjectingConstraintValidatorFactory;

import javax.validation.ParameterNameProvider;
import javax.validation.Validation;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.ContextResolver;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

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

        register(CpwsServiceExceptionMapper.class);
        // Validation.
        register(ValidationConfigurationContextResolver.class);
        packages("com.casual.feed.resource");
    }

    public static class ValidationConfigurationContextResolver implements ContextResolver<ValidationConfig> {
        @Context
        private ResourceContext resourceContext;

        @Override
        public ValidationConfig getContext(final Class<?> type) {
            return new ValidationConfig()
                    .constraintValidatorFactory(resourceContext.getResource(InjectingConstraintValidatorFactory.class))
                    .parameterNameProvider(new CustomParameterNameProvider());
        }

        /**
         * See ContactCardTest#testAddInvalidContact.
         */
        private class CustomParameterNameProvider implements ParameterNameProvider {
            private final ParameterNameProvider nameProvider;

            public CustomParameterNameProvider() {
                nameProvider = Validation.byDefaultProvider().configure().getDefaultParameterNameProvider();
            }

            @Override
            public List<String> getParameterNames(final Constructor<?> constructor) {
                return nameProvider.getParameterNames(constructor);
            }

            @Override
            public List<String> getParameterNames(final Method method) {
                return nameProvider.getParameterNames(method);
            }
        }
    }
}