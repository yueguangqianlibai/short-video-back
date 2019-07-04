package com.xtang.pojo;

import java.util.Date;

public class Comments {
    private String id;

    private String fatherCommentId;

    private String toUserId;

    private String videoId;

    private String fromUserId;

    private Date createTime;

    private Date updateTime;

    private String comment;

    public Comments(String id, String fatherCommentId, String toUserId, String videoId, String fromUserId, Date createTime, Date updateTime) {
        this.id = id;
        this.fatherCommentId = fatherCommentId;
        this.toUserId = toUserId;
        this.videoId = videoId;
        this.fromUserId = fromUserId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Comments(String id, String fatherCommentId, String toUserId, String videoId, String fromUserId, Date createTime, Date updateTime, String comment) {
        this.id = id;
        this.fatherCommentId = fatherCommentId;
        this.toUserId = toUserId;
        this.videoId = videoId;
        this.fromUserId = fromUserId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.comment = comment;
    }

    public Comments() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFatherCommentId() {
        return fatherCommentId;
    }

    public void setFatherCommentId(String fatherCommentId) {
        this.fatherCommentId = fatherCommentId == null ? null : fatherCommentId.trim();
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId == null ? null : toUserId.trim();
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId == null ? null : videoId.trim();
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId == null ? null : fromUserId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}