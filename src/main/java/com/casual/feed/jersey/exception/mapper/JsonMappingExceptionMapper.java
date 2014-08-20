package com.casual.feed.jersey.exception.mapper;

import com.casual.feed.jersey.exception.CpwsServiceException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Convert json parse exception to bad request
 *
 * @author ayang
 */
@Provider
public class JsonMappingExceptionMapper extends ExceptionMapperAdapter<JsonMappingException> {
    @Override
    protected CpwsServiceException translateExceptionIfPossible(Throwable cause) {
        return new CpwsServiceException(cause, Response.Status.BAD_REQUEST.getStatusCode(), "invalid_json");
    }
}
