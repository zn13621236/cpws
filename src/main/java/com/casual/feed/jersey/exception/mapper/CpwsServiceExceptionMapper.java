/**
 *
 */
package com.casual.feed.jersey.exception.mapper;

import com.casual.feed.jersey.exception.CpwsServiceException;
import com.casual.feed.resource.domain.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Locale;

/**
 * Transfer {@link CpwsServiceException} to json format
 *
 * @author Aaron Yang
 */
@Provider
public class CpwsServiceExceptionMapper implements ExceptionMapper<CpwsServiceException> {
    private static final Logger logger = LoggerFactory.getLogger(CpwsServiceExceptionMapper.class);

    @Inject
    @Named("messageSource")
    private MessageSource messageSource;

    @Override
    public Response toResponse(CpwsServiceException exception) {
        if (exception.isLoggable()) {
            logger.error(exception.getMessageCode(), exception);
        }
        String messageCode = exception.getMessageCode();
        String message = messageCode == null ? null : messageSource.getMessage(messageCode, exception.getParameters(),
                messageCode, Locale.US);
        logger.info("Status code is '{}', message code is '{}', message is '{}'", exception.getStatus(),
                exception.getMessageCode(), message);
        return Response.status(exception.getStatus()).entity(new ErrorResponse(exception.getMessageCode(), message)).type(MediaType.APPLICATION_JSON).build();
    }
}
