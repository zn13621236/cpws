package com.casual.feed.jersey.exception.mapper;

import com.casual.feed.jersey.exception.CpwsServiceException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.EOFException;

/**
 * Convert json parse exception to bad request
 *
 * @author ayang
 */
@Provider
public class EofExceptionMapper extends ExceptionMapperAdapter<EOFException> {
    @Override
    protected CpwsServiceException translateExceptionIfPossible(Throwable cause) {
        return new CpwsServiceException(cause, Response.Status.BAD_REQUEST.getStatusCode(), "invalid_json");
    }
}
