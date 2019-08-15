package com.xtang.vo;

import java.util.Date;

public class CommentsVo {
    private String id;

    private String fatherCommentId;

    private String toUserId;

    private String videoId;

    private String fromUserId;

    private Date createTime;

    private Date updateTime;

    private String comment;

    private String faceImage;

    private String nickName;

    private String timeAgoStr;

    private String toNickName;

    public CommentsVo(String id, String fatherCommentId, String toUserId, String videoId, String fromUserId, Date createTime, Date updateTime, String comment, String faceImage, String nickName,String toNickName) {
        this.id = id;
        this.fatherCommentId = fatherCommentId;
        this.toUserId = toUserId;
        this.videoId = videoId;
        this.fromUserId = fromUserId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.comment = comment;
        this.faceImage = faceImage;
        this.nickName = nickName;
        this.toNickName = toNickName;
    }

    public CommentsVo() {
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

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTimeAgoStr() {
        return timeAgoStr;
    }

    public void setTimeAgoStr(String timeAgoStr) {
        this.timeAgoStr = timeAgoStr;
    }

    public String getToNickName() {
        return toNickName;
    }

    public void setToNickName(String toNickName) {
        this.toNickName = toNickName;
    }
}