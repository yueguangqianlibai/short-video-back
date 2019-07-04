package com.xtang.pojo;

import java.util.Date;

public class UsersReport {
    private String id;

    private String dealUserId;

    private String dealVideoId;

    private String title;

    private String content;

    private String userId;

    private Date createTime;

    public UsersReport(String id, String dealUserId, String dealVideoId, String title, String content, String userId, Date createTime) {
        this.id = id;
        this.dealUserId = dealUserId;
        this.dealVideoId = dealVideoId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createTime = createTime;
    }

    public UsersReport() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDealUserId() {
        return dealUserId;
    }

    public void setDealUserId(String dealUserId) {
        this.dealUserId = dealUserId == null ? null : dealUserId.trim();
    }

    public String getDealVideoId() {
        return dealVideoId;
    }

    public void setDealVideoId(String dealVideoId) {
        this.dealVideoId = dealVideoId == null ? null : dealVideoId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}