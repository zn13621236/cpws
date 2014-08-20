package com.casual.feed.jersey.exception.mapper;

import com.casual.feed.jersey.exception.CpwsServiceException;
import com.fasterxml.jackson.core.JsonParseException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Convert json parse exception to bad request
 *
 * @author ayang
 */
@Provider
public class JsonParseExceptionMapper extends ExceptionMapperAdapter<JsonParseException> {
    @Override
    protected CpwsServiceException translateExceptionIfPossible(Throwable cause) {
        return new CpwsServiceException(cause, Response.Status.BAD_REQUEST.getStatusCode(), "invalid_json");
    }
}
