package com.xtang.pojo;

import java.util.Date;

public class Bgm {
    private String id;

    private String author;

    private String name;

    private String path;

    private Date createTime;

    private Date updateTime;

    public Bgm(String id, String author, String name, String path, Date createTime, Date updateTime) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.path = path;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Bgm() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
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
}