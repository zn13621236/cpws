package com.casual.feed.mongo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.mongojack.ObjectId;

import javax.persistence.Entity;

/**
 * @author: ayang
 */
@Entity(name = RepositoryConstants.Collections.FEEDS)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feed {
    @ObjectId
    @JsonProperty(RepositoryConstants.Fields._ID)
    private String id;

    private String content;

    @JsonProperty(RepositoryConstants.Fields.CREATED_TIME)
    private long createdTime;

    @JsonProperty(RepositoryConstants.Fields.VOTE_COUNT)
    private long voteCount;

    @JsonProperty(RepositoryConstants.Fields.USER_ID)
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }
}
