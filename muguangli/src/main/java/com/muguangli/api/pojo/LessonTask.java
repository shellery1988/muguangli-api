package com.muguangli.api.pojo;

public class LessonTask {
    private Integer taskId;

    private String taskName;

    private Integer taskStage;

    private Integer taskScore;

    private Integer taskLevel;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public Integer getTaskStage() {
        return taskStage;
    }

    public void setTaskStage(Integer taskStage) {
        this.taskStage = taskStage;
    }

    public Integer getTaskScore() {
        return taskScore;
    }

    public void setTaskScore(Integer taskScore) {
        this.taskScore = taskScore;
    }

    public Integer getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(Integer taskLevel) {
        this.taskLevel = taskLevel;
    }
}