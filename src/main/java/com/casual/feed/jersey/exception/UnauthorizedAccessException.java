/**
 *
 */
package com.casual.feed.jersey.exception;

import javax.ws.rs.core.Response;

/**
 * @author Aaron Yang
 */
public class UnauthorizedAccessException extends CpwsServiceException {
    public UnauthorizedAccessException(String messageCode, Object... parameters) {
        super(Response.Status.UNAUTHORIZED.getStatusCode(), messageCode, parameters);
    }

}
