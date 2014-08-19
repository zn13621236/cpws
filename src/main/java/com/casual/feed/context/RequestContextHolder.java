package com.casual.feed.context;

import com.casual.feed.util.Guid;

/**
 * @author ayang
 */
public class RequestContextHolder {
	private static final ThreadLocal<RequestContext> contextHolder = new ThreadLocal<>();

	/**
	 * Gets the RequestContext from the holder, or null if one has not been set in this thread.
	 *
	 * @return The RequestContext that was set into this thread earlier, or null.
	 */
	public final static RequestContext getRequestContext() {
		RequestContext context = RequestContextHolder.contextHolder.get();
		if (context == null) {
			context = new RequestContext(Guid.createRandomGuid().toGuidString());
			setRequestContext(context);
		}
		return context;
	}

	public final static void setRequestContext(RequestContext context) {
		RequestContextHolder.contextHolder.set(context);
	}

	public final static void removeRequestContext() {
		RequestContextHolder.contextHolder.remove();
	}
}
