package com.muguangli.api.vo;

import java.util.List;

import com.muguangli.api.pojo.LessonMain;

public class LessonMainVO extends LessonMain{
	
	/**
	 * 是否可解锁 0否 1是
	 */
	private Integer canUnlock = 0;

	/**
	 * 课程学习状态 0未学习 1 已学习 2学习结束
	 */
	private Integer studyStatus = 0;
	
	/**
	 * 当前学习进度
	 */
	private Integer currentTaskStage = 0;
	
	/**
	 * 学习进度记录列表信息
	 */
	private List<UserStudyRecordVO> studyRecords;
	
	/**
	 * 已参与学习的人次数
	 */
	private Long userNumbers = 0L;
	
	/**
	 * 课程标识
	 */
	private String lessonDesc;
	
	public Integer getCanUnlock() {
		return canUnlock;
	}

	public void setCanUnlock(Integer canUnlock) {
		this.canUnlock = canUnlock;
	}

	public Integer getStudyStatus() {
		return studyStatus;
	}

	public void setStudyStatus(Integer studyStatus) {
		this.studyStatus = studyStatus;
	}

	public List<UserStudyRecordVO> getStudyRecords() {
		return studyRecords;
	}

	public void setStudyRecords(List<UserStudyRecordVO> studyRecords) {
		this.studyRecords = studyRecords;
	}

	public Integer getCurrentTaskStage() {
		return currentTaskStage;
	}

	public void setCurrentTaskStage(Integer currentTaskStage) {
		this.currentTaskStage = currentTaskStage;
	}

	public Long getUserNumbers() {
		return userNumbers;
	}

	public void setUserNumbers(Long userNumbers) {
		this.userNumbers = userNumbers;
	}

	public String getLessonDesc() {
		return lessonDesc;
	}

	public void setLessonDesc(String lessonDesc) {
		this.lessonDesc = lessonDesc;
	}
	
}
