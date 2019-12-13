package com.muguangli.api.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.muguangli.api.enums.StageScoreEnum;
import com.muguangli.api.mapper.UserInfoMapper;
import com.muguangli.api.mapper.UserStudyMapper;
import com.muguangli.api.mapper.UserStudyRecordMapper;
import com.muguangli.api.pojo.UserInfo;
import com.muguangli.api.pojo.UserStudy;
import com.muguangli.api.pojo.UserStudyRecord;
import com.muguangli.api.service.IUserStudyRecordService;
import com.muguangli.api.vo.UserStudyRecordVO;
import com.muguangli.util.DateFormatUtil;

@Service
public class UserStudyRecordServiceImpl implements IUserStudyRecordService{
	
	
	@Resource
	UserInfoMapper userInfoMapper;
	
	@Resource
	UserStudyMapper userStudyMapper;
	
	@Resource
	UserStudyRecordMapper userStudyRecordMapper;
	
	@Override
	public Integer getTotalUnlockScoreWithLesson(UserStudyRecord record) {
		return userStudyRecordMapper.queryTotalUnlockScoreWithLesson(record);
	}

	@Override
	public List<UserStudyRecordVO> getUserStudyRecordInAllStage(UserStudyRecord record) {
		return userStudyRecordMapper.queryUserStudyRecordInAllStage(record);
	}
	
	@Override
	public int saveUserStudyRecord(UserStudyRecord record) {
		return userStudyRecordMapper.insertSelective(record);
	}

	@Override
	public int modifyUserStudyRecord(UserStudyRecord record) {
		return userStudyRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<UserStudyRecord> getUserStudyRecordList(UserStudyRecord record) {
		return userStudyRecordMapper.queryUserStudyRecordList(record);
	}

	@Override
	public void userHandleShareRecord(UserStudyRecord record, Boolean isComment){
		/**
		 * 分享学习信息需要处理
		 */
		try {
			List<UserStudyRecord> studyRecords = userStudyRecordMapper.queryUserStudyRecordList(record);
			if(CollectionUtils.isNotEmpty(studyRecords)){
				for(UserStudyRecord studyRecord : studyRecords) {
					if(null == studyRecord.getScore()) {
						Date createTime = studyRecord.getCreateTime();
						if(null != createTime) {
							LocalDate createDate = DateFormatUtil.date2LocalDate(createTime);
							LocalDate currentDate = LocalDate.now();
							if(isComment || createDate.compareTo(currentDate) < 0){
								/**
								 * 更新分享记录
								 */
								studyRecord.setScore(StageScoreEnum.STAGE_3_SCORE.getScore());
								userStudyRecordMapper.updateByPrimaryKeySelective(studyRecord);
								
								/**
								 * 更新用户当前学习进度
								 */
								UserStudy queryBean = new UserStudy();
								queryBean.setLessonId(studyRecord.getLessonId());
								queryBean.setOpenId(studyRecord.getOpenId());
								
								List<UserStudy> studies = userStudyMapper.selectList(queryBean);
								if(studies!=null && studies.size()>0){
									UserStudy userStudy = studies.get(0);
									userStudy.setCurrentTaskStage(StageScoreEnum.STAGE_3_SCORE.getStage());
									userStudyMapper.updateByPrimaryKeySelective(userStudy);
								}
							}
						}
					}
				}
				
				/**
				 * 记录用户总积分
				 */
				UserInfo user = new UserInfo(record.getOpenId());
				userInfoMapper.addScoreForUser(user);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public void handleUserCommentRecord(UserStudyRecord record) {
		try {
			List<UserStudyRecord> studyRecords = userStudyRecordMapper.queryUserStudyRecordList(record);
			if(CollectionUtils.isEmpty(studyRecords) || studyRecords.size()==0){
				/**
				 * 创建点评记录
				 */
				record.setTaskStage(StageScoreEnum.STAGE_4_SCORE.getStage());
				record.setScore(StageScoreEnum.STAGE_4_SCORE.getScore());
				userStudyRecordMapper.insertSelective(record);
				
				/**
				 * 更新用户当前学习进度
				 */
				UserStudy queryBean = new UserStudy();
				queryBean.setLessonId(record.getLessonId());
				queryBean.setOpenId(record.getOpenId());
				
				List<UserStudy> studies = userStudyMapper.selectList(queryBean);
				if(studies!=null && studies.size()>0){
					UserStudy userStudy = studies.get(0);
					userStudy.setCurrentTaskStage(StageScoreEnum.STAGE_4_SCORE.getStage());
					userStudy.setEndTime(new Date());
					userStudyMapper.updateByPrimaryKeySelective(userStudy);
				}
			}
			
			/**
			 * 记录用户总积分
			 */
			UserInfo user = new UserInfo(record.getOpenId());
			userInfoMapper.addScoreForUser(user);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void teacherEnsureShareRecord(UserStudyRecord studyRecord, Boolean isComment) {
		/**
		 * 分享学习信息需要处理
		 */
		try {
			if(null == studyRecord.getScore()) {
				Date createTime = studyRecord.getCreateTime();
				if(null != createTime) {
					LocalDate createDate = DateFormatUtil.date2LocalDate(createTime);
					LocalDate currentDate = LocalDate.now();
					if(isComment || createDate.compareTo(currentDate) < 0){
						/**
						 * 更新分享记录
						 */
						studyRecord.setScore(StageScoreEnum.STAGE_3_SCORE.getScore());
						userStudyRecordMapper.updateByPrimaryKeySelective(studyRecord);
						
						/**
						 * 更新用户当前学习进度
						 */
						UserStudy queryBean = new UserStudy();
						queryBean.setLessonId(studyRecord.getLessonId());
						queryBean.setOpenId(studyRecord.getOpenId());
						
						List<UserStudy> studies = userStudyMapper.selectList(queryBean);
						if(studies!=null && studies.size()>0){
							UserStudy userStudy = studies.get(0);
							userStudy.setCurrentTaskStage(StageScoreEnum.STAGE_3_SCORE.getStage());
							userStudyMapper.updateByPrimaryKeySelective(userStudy);
						}
					}
				}
			}
				
			/**
			 * 记录用户总积分
			 */
			UserInfo user = new UserInfo(studyRecord.getOpenId());
			userInfoMapper.addScoreForUser(user);
		} catch (Exception e) {
			throw e;
		}
	}
	
}
