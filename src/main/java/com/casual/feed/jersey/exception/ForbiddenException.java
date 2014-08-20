/**
 *
 */
package com.casual.feed.jersey.exception;

import javax.ws.rs.core.Response;

/**
 * @author Aaron Yang
 */
public class ForbiddenException extends CpwsServiceException {
    public ForbiddenException(String messageCode, Object... parameters) {
        super(Response.Status.FORBIDDEN.getStatusCode(), messageCode, parameters);
    }
}
