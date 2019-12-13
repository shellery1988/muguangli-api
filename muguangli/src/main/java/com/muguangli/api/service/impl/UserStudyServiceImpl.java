package com.muguangli.api.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muguangli.api.enums.StageScoreEnum;
import com.muguangli.api.mapper.UserInfoMapper;
import com.muguangli.api.mapper.UserStudyMapper;
import com.muguangli.api.mapper.UserStudyRecordMapper;
import com.muguangli.api.pojo.UserInfo;
import com.muguangli.api.pojo.UserStudy;
import com.muguangli.api.pojo.UserStudyRecord;
import com.muguangli.api.service.IUserStudyService;

@Service
public class UserStudyServiceImpl implements IUserStudyService {
	
	@Autowired
	UserInfoMapper userInfoMapper;
	
	@Autowired
	UserStudyMapper userStudyMapper;
	
	@Autowired
	UserStudyRecordMapper userStudyRecordMapper;
	
	@Override
	public List<UserStudy> getList(UserStudy queryBean) {
		return userStudyMapper.selectList(queryBean);
	}

	@Override
	public int insertBean(UserStudy record) {
		//添加学习记录
		insertRecordBean(record);
		//更新用户积分
		addScoreForUser(record);
		return userStudyMapper.insertSelective(record);
	}

	@Override
	public int updateBean(UserStudy record) {
		//添加学习记录
		insertRecordBean(record);
		//更新用户积分
		addScoreForUser(record);
		return userStudyMapper.updateByPrimaryKeySelective(record);
	}
	
	private int insertRecordBean(UserStudy record) {
		UserStudyRecord studyRecord = new UserStudyRecord();
		studyRecord.setLessonId(record.getLessonId());
		studyRecord.setOpenId(record.getOpenId());
		studyRecord.setTaskStage(record.getCurrentTaskStage());
		studyRecord.setCreateTime(new Date());
		StageScoreEnum scoreEnum = StageScoreEnum.getByStage(record.getCurrentTaskStage());
		studyRecord.setScore(scoreEnum.getScore());
		return userStudyRecordMapper.insertSelective(studyRecord);
	}
	
	private int addScoreForUser(UserStudy record) {
		UserInfo user = new UserInfo();
		user.setOpenId(record.getOpenId());
		return userInfoMapper.addScoreForUser(user);
	}

	@Override
	public Long getLessonUserNumbers(Integer lessonId) {
		Long userNumbers = userStudyMapper.queryLessonUserNumbers(lessonId);
		return userNumbers==null ? 0L : userNumbers;
	}

}
