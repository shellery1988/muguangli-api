package com.muguangli.api.pojo;

import java.util.Date;

public class LessonMain {
    private Integer lessonId;

    private String lessonName;

    private String teacherName;

    private Integer unlockScore;

    private Integer status;

    private Date onlineDateTime;

    private String videoUrl;

    private Integer needUnlock;

    private Integer unlockLessonId;

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName == null ? null : lessonName.trim();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public Integer getUnlockScore() {
        return unlockScore;
    }

    public void setUnlockScore(Integer unlockScore) {
        this.unlockScore = unlockScore;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOnlineDateTime() {
        return onlineDateTime;
    }

    public void setOnlineDateTime(Date onlineDateTime) {
        this.onlineDateTime = onlineDateTime;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    public Integer getNeedUnlock() {
        return needUnlock;
    }

    public void setNeedUnlock(Integer needUnlock) {
        this.needUnlock = needUnlock;
    }

    public Integer getUnlockLessonId() {
        return unlockLessonId;
    }

    public void setUnlockLessonId(Integer unlockLessonId) {
        this.unlockLessonId = unlockLessonId;
    }
}