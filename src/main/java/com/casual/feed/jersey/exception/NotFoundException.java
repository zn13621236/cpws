/**
 *
 */
package com.casual.feed.jersey.exception;

import javax.ws.rs.core.Response;

/**
 * @author Aaron Yang
 */
public class NotFoundException extends CpwsServiceException {
    public NotFoundException(String messageCode, Object... parameters) {
        super(Response.Status.NOT_FOUND.getStatusCode(), messageCode, null, parameters);
    }
}
