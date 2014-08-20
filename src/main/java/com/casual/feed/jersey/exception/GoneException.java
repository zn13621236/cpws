/**
 *
 */
package com.casual.feed.jersey.exception;

import javax.ws.rs.core.Response;

/**
 * @author Aaron Yang
 */
public class GoneException extends CpwsServiceException {
    public GoneException(String messageCode, Object... parameters) {
        super(Response.Status.GONE.getStatusCode(), messageCode, parameters);
    }

}
