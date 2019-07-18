package com.xtang.vo;

import java.util.Date;

public class VideosVo {
    private String id;

    private String userId;

    private String audioId;

    private String videoDesc;

    private String videoPath;

    private Float videoSeconds;

    private Integer videoWidth;

    private Integer videoHeigh;

    private String coverPath;

    private Long likeCounts;

    private Integer status;

    private Date createTime;

    private String faceImage;

    private String nickName;

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

    public VideosVo(String id, String userId, String audioId, String videoDesc, String videoPath, Float videoSeconds, Integer videoWidth, Integer videoHeigh, String coverPath, Long likeCounts, Integer status, Date createTime,String faceImage,String nickName) {
        this.id = id;
        this.userId = userId;
        this.audioId = audioId;
        this.videoDesc = videoDesc;
        this.videoPath = videoPath;
        this.videoSeconds = videoSeconds;
        this.videoWidth = videoWidth;
        this.videoHeigh = videoHeigh;
        this.coverPath = coverPath;
        this.likeCounts = likeCounts;
        this.status = status;
        this.createTime = createTime;
        this.faceImage = faceImage;
        this.nickName = nickName;
    }

    public VideosVo() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAudioId() {
        return audioId;
    }

    public void setAudioId(String audioId) {
        this.audioId = audioId == null ? null : audioId.trim();
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc == null ? null : videoDesc.trim();
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath == null ? null : videoPath.trim();
    }

    public Float getVideoSeconds() {
        return videoSeconds;
    }

    public void setVideoSeconds(Float videoSeconds) {
        this.videoSeconds = videoSeconds;
    }

    public Integer getVideoWidth() {
        return videoWidth;
    }

    public void setVideoWidth(Integer videoWidth) {
        this.videoWidth = videoWidth;
    }

    public Integer getVideoHeigh() {
        return videoHeigh;
    }

    public void setVideoHeigh(Integer videoHeigh) {
        this.videoHeigh = videoHeigh;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath == null ? null : coverPath.trim();
    }

    public Long getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(Long likeCounts) {
        this.likeCounts = likeCounts;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}