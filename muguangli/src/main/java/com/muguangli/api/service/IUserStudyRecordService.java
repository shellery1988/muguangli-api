package com.muguangli.api.service;

import java.util.List;

import com.muguangli.api.pojo.UserStudyRecord;
import com.muguangli.api.vo.UserStudyRecordVO;

public interface IUserStudyRecordService {
	
	Integer getTotalUnlockScoreWithLesson(UserStudyRecord record);
	
	List<UserStudyRecordVO> getUserStudyRecordInAllStage(UserStudyRecord record);
	
	int saveUserStudyRecord(UserStudyRecord record);
	
	int modifyUserStudyRecord(UserStudyRecord record);
	
	List<UserStudyRecord> getUserStudyRecordList(UserStudyRecord record);
	
	void userHandleShareRecord(UserStudyRecord record, Boolean isComment);
	
	void teacherEnsureShareRecord(UserStudyRecord record, Boolean isComment);
	
	void handleUserCommentRecord(UserStudyRecord record);
}
