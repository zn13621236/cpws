package com.casual.poll.mongo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.mongojack.ObjectId;
import javax.persistence.Entity;

/**
 * @author: ayang
 */
@Entity
@JsonSerialize (include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties (ignoreUnknown = true)
public class Feed {

    @ObjectId
    private String guid;
    private String content;
    @JsonProperty (FieldName.CREATED_TIME)
    private long   createdTime;
    @JsonProperty (FieldName.VOTE_COUNT)
    private long   voteCount;
    @JsonProperty (FieldName.USER_ID)
    private long   userId;

    public long getUserId () {
        return userId;
    }

    public void setUserId (long userId) {
        this.userId = userId;
    }

    public String getGuid () {
        return guid;
    }

    public void setGuid (String guid) {
        this.guid = guid;
    }

    public String getContent () {
        return content;
    }

    public void setContent (String content) {
        this.content = content;
    }

    public long getCreatedTime () {
        return createdTime;
    }

    public void setCreatedTime (long createdTime) {
        this.createdTime = createdTime;
    }

    public long getVoteCount () {
        return voteCount;
    }

    public void setVoteCount (long voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public String toString () {
        return "Feed [guid=" + guid + ", content=" + content + ", createdTime=" + createdTime + ", voteCount=" + voteCount + "]";
    }
}
