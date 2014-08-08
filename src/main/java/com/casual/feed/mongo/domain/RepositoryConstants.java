package com.casual.feed.mongo.domain;

/**
 * @author: ayang
 */
public final class RepositoryConstants {
    private RepositoryConstants() {
    }

    public static abstract class Collections {
        public static final String CLIENTS = "clients";
        public static final String USERS = "users";
        public static final String FEEDS = "feeds";
        public static final String SESSIONS = "sessions";
    }

    /**
     * Database field names.
     */
    public static abstract class Fields {
        public static final String CLIENT_ID = "client_id";
        public static final String _ID = "_id";
        public static final String USER_ID = "user_id";
        public static final String CREATED_TIME = "created_time";
        public static final String CLIENT_SECRET = "client_secret";
        public static final String EXPIRED_TIME = "expired_time";
        public static final String VOTE_COUNT = "vote_count";
        public static final String STATUS = "status";
        public static final String TOKEN = "token";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        //for feed collection
        public static final String FEED_ID = "feed_id";
    }

}
