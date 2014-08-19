package com.casual.feed.filter;

import com.casual.feed.context.RequestContext;
import com.casual.feed.context.RequestContextHolder;
import com.google.common.base.Charsets;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: ayang
 */
@Component
@Priority (Priorities.HEADER_DECORATOR)
public class ResponseFilter implements ContainerResponseFilter {
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		MultivaluedMap<String, Object> headers = responseContext.getHeaders();
		headers.putSingle("Access-Control-Allow-Origin", "*");
		headers.putSingle("Access-Control-Allow-Headers", "Content-Type, Content-Length, Date, Authorization, X-Requested-With, X-Request-Id, Accept, Accept-Encoding, Accept-Language");
		headers.putSingle("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE, HEAD");
		RequestContext context = RequestContextHolder.getRequestContext();
		if (context != null) {
			headers.putSingle("X-Request-Id", context.getRequestId());
		}

		final MediaType mediaType = responseContext.getMediaType();
		if (mediaType != null) {
			Map<String, String> parameters = new HashMap<>();
			Map<String, String> originalParameters = mediaType.getParameters();
			if (!CollectionUtils.isEmpty(originalParameters)) {
				parameters.putAll(originalParameters);
			}
			parameters.put("charset", Charsets.UTF_8.name());
			headers.putSingle(HttpHeaders.CONTENT_TYPE, new MediaType(mediaType.getType(), mediaType.getSubtype(), parameters));
		}
	}
}
