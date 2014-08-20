package com.casual.feed.context;

/**
 * @author: ayang
 */
public interface RequestSession {
    public String getClientId();

    String getUserId();

    String getRequestId();
}
