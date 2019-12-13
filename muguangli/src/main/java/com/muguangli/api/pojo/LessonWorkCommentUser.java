package com.muguangli.api.pojo;

import java.util.Date;

public class LessonWorkCommentUser {
    private Integer id;

    private Integer workId;

    private String commentOpenId;

    private String commentNickName;

    private String commentContent;

    private String workOpenId;

    private String workNickName;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getCommentOpenId() {
        return commentOpenId;
    }

    public void setCommentOpenId(String commentOpenId) {
        this.commentOpenId = commentOpenId == null ? null : commentOpenId.trim();
    }

    public String getCommentNickName() {
        return commentNickName;
    }

    public void setCommentNickName(String commentNickName) {
        this.commentNickName = commentNickName == null ? null : commentNickName.trim();
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public String getWorkOpenId() {
        return workOpenId;
    }

    public void setWorkOpenId(String workOpenId) {
        this.workOpenId = workOpenId == null ? null : workOpenId.trim();
    }

    public String getWorkNickName() {
        return workNickName;
    }

    public void setWorkNickName(String workNickName) {
        this.workNickName = workNickName == null ? null : workNickName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}