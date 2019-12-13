package com.muguangli.api.pojo;

import java.util.Date;

public class UserStudyRecord {
    private Integer id;

    private Integer lessonId;

    private String openId;

    private Integer taskStage;

    private Date createTime;

    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Integer getTaskStage() {
        return taskStage;
    }

    public void setTaskStage(Integer taskStage) {
        this.taskStage = taskStage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}