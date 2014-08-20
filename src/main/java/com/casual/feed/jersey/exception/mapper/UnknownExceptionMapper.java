/**
 *
 */
package com.casual.feed.jersey.exception.mapper;

import org.springframework.stereotype.Component;

import javax.ws.rs.ext.Provider;

/**
 * Transfer {@link Throwable} to {@link javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR} with detail error message
 *
 * @author Aaron Yang
 */
@Provider
public class UnknownExceptionMapper extends ExceptionMapperAdapter<Throwable> {
}
