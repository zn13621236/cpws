package com.casual.feed.context;

/**
 * @author: ayang
 */
public class RequestContext {
    private final String requestId;

    public RequestContext(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }
}
