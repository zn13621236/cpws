/**
 *
 */
package com.casual.feed.jersey.exception.mapper;

import com.casual.feed.jersey.exception.CpwsServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * Customize jersey exception
 *
 * @author Aaron Yang
 */
@Provider
public class JerseyExceptionMapper extends ExceptionMapperAdapter<WebApplicationException> {
    public final static int UNSUPPORTED_METHOD = 405;

    private static final Logger logger = LoggerFactory.getLogger(JerseyExceptionMapper.class);

    private static final Map<Integer, String> messageCodes = new HashMap<Integer, String>() {
        {
            put(Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "content_type_invalid");
            put(Response.Status.NOT_FOUND.getStatusCode(), "uri_not_found");
            put(UNSUPPORTED_METHOD, "unsupported_method");
        }
    };

    @Override
    protected CpwsServiceException translateExceptionIfPossible(Throwable throwable) {
        WebApplicationException exception = (WebApplicationException) throwable;
        Throwable cause = exception.getCause();
        if ((cause != null) && (cause instanceof NumberFormatException)) {
            return new CpwsServiceException(throwable, Response.Status.BAD_REQUEST.getStatusCode(), cause.getMessage());
        } else if ((cause != null) && (cause instanceof SocketException)) {
            return new CpwsServiceException(cause, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "network_error");
        }

        int status = exception.getResponse().getStatus();
        String errorCode = messageCodes.get(status);
        logger.info("The status code is '{}'.", status);
        if (errorCode != null) {
            return new CpwsServiceException(throwable, Response.Status.BAD_REQUEST.getStatusCode(), errorCode);
        } else {
            return super.translateExceptionIfPossible(throwable);
        }
    }
}
