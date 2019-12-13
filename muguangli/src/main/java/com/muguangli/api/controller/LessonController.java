package com.muguangli.api.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muguangli.api.dto.LessonWorkDTO;
import com.muguangli.api.enums.StageScoreEnum;
import com.muguangli.api.pojo.LessonMain;
import com.muguangli.api.pojo.LessonWork;
import com.muguangli.api.pojo.LessonWorkCommentTeacher;
import com.muguangli.api.pojo.UserInfo;
import com.muguangli.api.pojo.UserStudy;
import com.muguangli.api.pojo.UserStudyRecord;
import com.muguangli.api.service.ILessonMainService;
import com.muguangli.api.service.ILessonWorkCommentTeacherService;
import com.muguangli.api.service.ILessonWorkService;
import com.muguangli.api.service.IUserInfoService;
import com.muguangli.api.service.IUserStudyRecordService;
import com.muguangli.api.service.IUserStudyService;
import com.muguangli.api.vo.LessonMainVO;
import com.muguangli.api.vo.UserStudyRecordVO;
import com.muguangli.core.AbstractController;
import com.muguangli.core.Page;
import com.muguangli.core.RespJson;
import com.muguangli.util.StringUtil;

@Controller
@RequestMapping("/lesson")
public class LessonController extends AbstractController{
	
	private static Logger log = Logger.getLogger(LessonController.class);
	
	@Resource
	ILessonMainService lessonMainService;
	
	@Resource
	IUserInfoService userInfoService;
	
	@Resource
	IUserStudyService userStudyService;
	
	@Resource
	IUserStudyRecordService userStudyRecordService;
	
	@Resource
	ILessonWorkService lessonWorkService;
	
	@Resource
	ILessonWorkCommentTeacherService lessonWorkCommentTeacherService;
	
	@RequestMapping(value="/getListPage", produces = "application/json;charset=UTF-8")
	@ResponseBody 
	public void getLessonMainListpage(){
		Page page = new Page();
		Long pageNo = Long.parseLong(StringUtil.isEmpty(getParamNonNull("pageNo")) ? "1":getParamNonNull("pageNo"));
		page.setPageNo(pageNo);
		LessonMain queryBean = new LessonMain();
		queryBean.setStatus(1);
		List<LessonMainVO> lessonList = lessonMainService.getListPage(queryBean, page);
		if(lessonList!=null && lessonList.size()>0){
			String openId = getParamNonNull("openId");
			for(LessonMainVO lessonMainVO : lessonList){
				if(lessonMainVO.getNeedUnlock()==0){
					lessonMainVO.setCanUnlock(1);
				} else {
					lessonMainVO.setCanUnlock(0);
					if (StringUtils.isNotBlank(openId)) {
						//查询上节课学习总积分
						UserStudyRecord record = new UserStudyRecord();
						record.setOpenId(openId);
						record.setLessonId(lessonMainVO.getUnlockLessonId());
						Integer unlockScore = userStudyRecordService.getTotalUnlockScoreWithLesson(record);
						if(unlockScore!=null && unlockScore.intValue()>= lessonMainVO.getUnlockScore()) {
							lessonMainVO.setCanUnlock(1);
						}
					}
				}
				
				if (StringUtils.isNotBlank(openId)) {
					/**
					 * 查询是否存在课程学习记录
					 */
					UserStudy userStudy = new UserStudy();
					userStudy.setLessonId(lessonMainVO.getLessonId());
					userStudy.setOpenId(openId);
					
					List<UserStudy> studies = userStudyService.getList(userStudy);
					if(studies!=null && studies.size()>0) {
						userStudy= studies.get(0);
						if(userStudy.getCurrentTaskStage().intValue()==4) {
							lessonMainVO.setStudyStatus(2);
						} else {
							lessonMainVO.setStudyStatus(1);
						}
					} else {
						lessonMainVO.setStudyStatus(0);
					}
					
					Long userNumbers = userStudyService.getLessonUserNumbers(lessonMainVO.getLessonId());
					lessonMainVO.setUserNumbers(userNumbers);
				}
				
			}
		}
		responseJson(new RespJson(lessonList));
	}
	
	@RequestMapping(value="/getLessonMainById", produces = "application/json;charset=UTF-8")
	@ResponseBody 
	public void getLessonMainById() {
		try {
			Integer lessonId = Integer.parseInt(getParamNonNull("lessonId"));
			String openId = getParamNonNull("openId");
			LessonMainVO lessonMainVO = getLessonInfo(lessonId, openId);
			String lessonName = lessonMainVO.getLessonName();
			if(StringUtils.isNotBlank(lessonName) && lessonName.indexOf("第")>0 && lessonName.indexOf("课")>0){
				int startIndex = lessonName.lastIndexOf("第");
				int endIndex = lessonName.lastIndexOf("课");
				if(startIndex>0 && endIndex >0 && endIndex>startIndex){
					String lessonDesc = lessonName.substring(startIndex+1, endIndex);
					lessonMainVO.setLessonDesc(lessonDesc);
				}
			}
			responseJson(new RespJson(lessonMainVO));
		} catch (Exception e) {
			log.error("获取课程信息错误！", e);
			responseJson(new RespJson("0","获取课程信息错误！"));
		}
	}
	
	private LessonMainVO getLessonInfo(Integer lessonId, String openId) {
		try {
			LessonMainVO lessonMainVO = lessonMainService.getLessonMainById(lessonId);
			
			/**
			 * 回去解锁状态
			 */
			if(lessonMainVO.getNeedUnlock()==0){
				lessonMainVO.setCanUnlock(1);
			} else {
				lessonMainVO.setCanUnlock(0);
				if (StringUtils.isNotBlank(openId)) {
					/**
					 * 查询上节课学习总积分
					 */
					UserStudyRecord record = new UserStudyRecord();
					record.setOpenId(openId);
					record.setLessonId(lessonMainVO.getUnlockLessonId());
					Integer unlockScore = userStudyRecordService.getTotalUnlockScoreWithLesson(record);
					if(unlockScore!=null && unlockScore.intValue()>= lessonMainVO.getUnlockScore()) {
						lessonMainVO.setCanUnlock(1);
					}
				}
			}
			
			if (StringUtils.isNotBlank(openId)) {
				/**
				 * 处理分享记录信息
				 */
				UserStudyRecord record = new UserStudyRecord();
				record.setOpenId(openId);
				record.setLessonId(lessonId); 
				record.setTaskStage(StageScoreEnum.STAGE_3_SCORE.getStage());
				userStudyRecordService.userHandleShareRecord(record, false);
				
				/**
				 * 获取学习状态
				 */
				UserStudy userStudy = new UserStudy();
				userStudy.setLessonId(lessonMainVO.getLessonId());
				userStudy.setOpenId(openId);
				
				List<UserStudy> studies = userStudyService.getList(userStudy);
				if(studies!=null && studies.size()>0) {
					userStudy= studies.get(0);
					if(userStudy.getCurrentTaskStage().intValue()==4) {
						lessonMainVO.setStudyStatus(2);
					} else {
						lessonMainVO.setStudyStatus(1);
					}
				} else {
					lessonMainVO.setStudyStatus(0);
				}
				lessonMainVO.setCurrentTaskStage(userStudy.getCurrentTaskStage());
				
				/**
				 * 获取学习进度记录
				 */
				List<UserStudyRecordVO> studyRecordVOs = userStudyRecordService.getUserStudyRecordInAllStage(record);
				lessonMainVO.setStudyRecords(studyRecordVOs);
			}
			return lessonMainVO;
		} catch (Exception e) {
			log.error("getLessonInfo error:"+e.getMessage());
			return null;
		}
		
	}
	
	@RequestMapping(value="/recordStudy", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void recordLessonStudy() {
		String lessonIdParam = getParamNonNull("lessonId");
		String currentTaskStageParam = getParamNonNull("currentTaskStage");
		if(StringUtils.isEmpty(lessonIdParam) || StringUtils.isEmpty(currentTaskStageParam)) {
			responseJson(new RespJson("0","缺少必要参数"));
			return;
		}
		String openId = getParamNonNull("openId");
		try {
			Integer lessonId = Integer.parseInt(lessonIdParam);
			Integer currentTaskStage = Integer.parseInt(currentTaskStageParam);
			
			UserStudy queryBean = new UserStudy();
			queryBean.setLessonId(lessonId);
			queryBean.setOpenId(openId);
			
			/**
			 * 查询是否存在课程学习记录
			 */
			List<UserStudy> studies = userStudyService.getList(queryBean);
			if(studies!=null && studies.size()>0){
				UserStudy userStudy = studies.get(0);
				if(currentTaskStage > userStudy.getCurrentTaskStage()){
					if(currentTaskStage - userStudy.getCurrentTaskStage() == 1) {
						userStudy.setCurrentTaskStage(currentTaskStage);
						if(currentTaskStage.intValue()==4){
							userStudy.setEndTime(new Date());
						}
						//更新学习阶段记录
						userStudyService.updateBean(userStudy);
						responseJson(new RespJson());
						return;
					} else {
						responseJson(new RespJson("0","请完成上一阶段的学习任务！"));
						return;
					}
				}
			} else {
				if(currentTaskStage.intValue()==0){
					//新增第一阶段记录
					queryBean.setCurrentTaskStage(currentTaskStage);
					queryBean.setStartTime(new Date());
					userStudyService.insertBean(queryBean);
					responseJson(new RespJson());
					return;
				} else {
					responseJson(new RespJson("0","请先学习完本课视频后再操作！"));
					return;
				}
			}
			responseJson(new RespJson("2","success"));
		} catch (Exception e) {
			responseJson(new RespJson("0","很抱歉，系统开了点小差，请稍后重试^_^"));
			return;
		}
	}
	
	@RequestMapping(value="/checkUnlock", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void checkLessonUnlock() {
		String openId = getParamNonNull("openId");
		String lessonIdParam = getParamNonNull("lessonId");
		if(StringUtils.isBlank(lessonIdParam)) {
			responseJson(new RespJson("0","很抱歉，系统开了点小差，请稍后重试^_^"));
			return;
		}
		try {
			Integer lessonId = Integer.parseInt(lessonIdParam);
			LessonMain lessonMain = lessonMainService.getLessonMainById(lessonId);
			if(lessonMain==null || lessonMain.getStatus().intValue()==0) {
				responseJson(new RespJson("0","课程可能已下线，请下拉刷新课程列表试试^_^"));
				return;
			}
			//查询用户信息
			UserInfo userInfo = userInfoService.getUserInfoByOpendId(openId);
			
			if(lessonMain.getNeedUnlock().intValue()==0) {
				responseJson(new RespJson(userInfo));
				return;
			}
			//查询上节课学习总积分
			UserStudyRecord record = new UserStudyRecord();
			record.setOpenId(openId);
			record.setLessonId(lessonMain.getUnlockLessonId());
			Integer unlockScore = userStudyRecordService.getTotalUnlockScoreWithLesson(record);
			if(unlockScore!=null && unlockScore.intValue()>= lessonMain.getUnlockScore()) {
				
				responseJson(new RespJson(userInfo));
				return;
			}
			responseJson(new RespJson("0","当前课程未满足解锁条件，请完成上节课的学习任务"));
			return;
		} catch (Exception e) {
			responseJson(new RespJson("0","很抱歉，系统开了点小差，请稍后重试^_^"));
			return;
		}
	}
	
	@RequestMapping(value="/recordShare", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void recordShare() {
		String lessonIdParam = getParamNonNull("lessonId");
		if(StringUtils.isEmpty(lessonIdParam)) {
			responseJson(new RespJson("0","缺少必要参数"));
			return;
		}
		String openId = getParamNonNull("openId");
		try {
			Integer lessonId = Integer.parseInt(lessonIdParam);
			Integer currentTaskStage = new Integer(3);
			UserStudyRecord userStudyRecord = new UserStudyRecord();
			userStudyRecord.setOpenId(openId);
			userStudyRecord.setLessonId(lessonId);
			userStudyRecord.setTaskStage(currentTaskStage);
			List<UserStudyRecord> studyRecords = userStudyRecordService.getUserStudyRecordList(userStudyRecord);
			if(CollectionUtils.isEmpty(studyRecords)) {
				userStudyRecord.setCreateTime(new Date());
				userStudyRecordService.saveUserStudyRecord(userStudyRecord);
			}
			responseJson(new RespJson());
		} catch (Exception e) {
			responseJson(new RespJson("0","很抱歉，系统开了点小差，请稍后重试^_^"));
			return;
		}
	}
	
	@RequestMapping(value="/getLessonWorkByLessonId", produces = "application/json;charset=UTF-8")
	@ResponseBody 
	public void getLessonWorkByLessonId() {
		String lessonIdParam = getParamNonNull("lessonId");
		if(StringUtils.isEmpty(lessonIdParam)) {
			responseJson(new RespJson("0","缺少必要参数"));
			return;
		}
		LessonWork lessonWork = new LessonWork();
		lessonWork.setLessonId(Integer.parseInt(lessonIdParam));
		List<LessonWorkDTO> lessonWorkDTOs = lessonWorkService.getLessonWorkList(lessonWork);
		if(!CollectionUtils.isEmpty(lessonWorkDTOs)) {
			for(LessonWorkDTO lessonWorkDTO:lessonWorkDTOs){
				//获取老师点评信息
				LessonWorkCommentTeacher teacher = new LessonWorkCommentTeacher();
				teacher.setWorkId(lessonWorkDTO.getWorkId());
				List<LessonWorkCommentTeacher> teachers = 
						lessonWorkCommentTeacherService.getLessonWorkCommentTeacherList(teacher);
				if(CollectionUtils.isNotEmpty(teachers)) {
					teacher = teachers.get(0);
					lessonWorkDTO.setTeacher(teacher);
				}
			}
		}
		responseJson(new RespJson(lessonWorkDTOs));
	}
	
}
