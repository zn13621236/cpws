/**
 *
 */
package com.casual.feed.jersey.exception.mapper;

import com.casual.feed.jersey.exception.CpwsServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * provide adapter to call CpwsServiceExceptionMapper for non CpwsServiceException
 *
 * @author Aaron Yang
 */
public class ExceptionMapperAdapter<E extends Throwable> implements ExceptionMapper<E> {
    @Autowired
    private CpwsServiceExceptionMapper cpwsServiceExceptionMapper;

    @Override
    public Response toResponse(E exception) {
        return cpwsServiceExceptionMapper.toResponse(translateExceptionIfPossible(exception));
    }

    protected CpwsServiceException translateExceptionIfPossible(Throwable cause) {
        return new CpwsServiceException(cause, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "server_error");
    }
}
