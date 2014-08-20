/**
 *
 */
package com.casual.feed.jersey.exception;

import javax.ws.rs.core.Response;

/**
 * @author Aaron Yang
 */
public class BadRequestException extends CpwsServiceException {
    public BadRequestException(String messageCode, Object... parameters) {
        super(Response.Status.BAD_REQUEST.getStatusCode(), messageCode, parameters);
    }
}
