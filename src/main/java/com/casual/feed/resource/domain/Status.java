package com.casual.feed.resource.domain;

/**
 * @author: ayang
 */
public class Status {
    private final String name;
    private final String status;
    private final String version;

    public Status(String status) {
        name = "feed";
        version = "0.01";
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getVersion() {
        return version;
    }
}
