package com.muguangli.api.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.muguangli.api.dto.ImageResponseDTO;
import com.muguangli.api.dto.LessonWorkDTO;
import com.muguangli.api.enums.StageScoreEnum;
import com.muguangli.api.pojo.LessonWork;
import com.muguangli.api.pojo.LessonWorkCommentTeacher;
import com.muguangli.api.pojo.UserStudy;
import com.muguangli.api.pojo.UserStudyRecord;
import com.muguangli.api.service.ILessonMainService;
import com.muguangli.api.service.ILessonWorkCommentTeacherService;
import com.muguangli.api.service.ILessonWorkService;
import com.muguangli.api.service.IUserStudyRecordService;
import com.muguangli.api.service.IUserStudyService;
import com.muguangli.api.vo.LessonMainVO;
import com.muguangli.core.AbstractController;
import com.muguangli.core.RespJson;
import com.muguangli.util.PropertiesUtil;

@Controller
@RequestMapping("/work")
public class StudyWorkController extends AbstractController{
	
	private static Logger log = Logger.getLogger(StudyWorkController.class);
	
	@Resource
	IUserStudyService userStudyService;
	
	@Resource
	IUserStudyRecordService userStudyRecordService;
	
	@Resource
	ILessonMainService lessonMainService;
	
	@Resource
	ILessonWorkService lessonWorkService;
	
	@Resource
	ILessonWorkCommentTeacherService lessonWorkCommentTeacherService;
	
	@RequestMapping(value="/showImage", method = RequestMethod.GET)
	@ResponseBody
	public void showImage() {
		String imageName = getParamNonNull("imageName");
		String imageUrl = PropertiesUtil.getBusinessProperties("images_url");
		String realPath = imageUrl+imageName;
        String filePath = realPath;
        System.out.println("filePath:"+filePath);
        File file = new File(filePath);
        FileInputStream fis = null;
        try {
            
            getRes().setContentType("image/gif");
            OutputStream out = getRes().getOutputStream();
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
             e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                   fis.close();
                } catch (IOException e) {
                e.printStackTrace();
                }   
             }
        }
	}
	
	@RequestMapping(value="/uploadImage", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void uploadImage(MultipartFile workImage, String openId){
		try {
			if(StringUtils.isBlank(openId)) {
				responseJson(new RespJson("0", "缺少用户信息，无法上传图片！"));
				return;
			}
			if(workImage==null || workImage.isEmpty()){
				responseJson(new RespJson("0", "请上传图片！"));
				return;
			}
			String originalFileName = workImage.getOriginalFilename();
			log.info("originalFileName:" + originalFileName);
			// 新的图片名称
			String newFileName = /* openId + "-" + */UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
		    // 新的图片
			String imageUrl = PropertiesUtil.getBusinessProperties("images_url");
		    File newFile = new File(imageUrl + newFileName);
		    // 将内存中的数据写入磁盘
		    workImage.transferTo(newFile);
		    
		    BufferedImage sourceImg = ImageIO.read(new FileInputStream(newFile));
            int imgWidth = sourceImg.getWidth();
            int imgHeight = sourceImg.getHeight();
		    long imageSize = newFile.length();
		    imageSize = imageSize/1024L;
            
		    DecimalFormat df = new DecimalFormat("#.00");
		    
		    ImageResponseDTO imageResponseDTO = new ImageResponseDTO();
		    imageResponseDTO.setImageName(newFileName);
		    imageResponseDTO.setImageSrc(imageUrl + newFileName);
		    imageResponseDTO.setImagesSize(String.valueOf(df.format(imageSize)));
		    imageResponseDTO.setImageWidth(String.valueOf(imgWidth));
		    imageResponseDTO.setImageHeight(String.valueOf(imgHeight));
		    
		    responseJson(new RespJson(imageResponseDTO));
		    return;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("uploadImage error:"+e);
			responseJson(new RespJson("0", "很抱歉，上传图片出错了哦！"));
			return;
		}
	}
	
	@RequestMapping(value="/showConvas", method = RequestMethod.GET)
	@ResponseBody
	public void showConvas() {
		String imageName = getParamNonNull("imageName");
		String imageUrl = PropertiesUtil.getBusinessProperties("images_url");
		String realPath = imageUrl + "/convas/" +imageName;
        String filePath = realPath;
        System.out.println("filePath:"+filePath);
        File file = new File(filePath);
        FileInputStream fis = null;
        try {
            
            getRes().setContentType("image/gif");
            OutputStream out = getRes().getOutputStream();
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
             e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                   fis.close();
                } catch (IOException e) {
                e.printStackTrace();
                }   
             }
        }
	}
	
	@RequestMapping(value="/submitWork", produces = "application/json;charset=UTF-8")
	@ResponseBody 
	public void submitWork() {
		try {
			String openId = getParamNonNull("openId");
			String imageUrl = getParamNonNull("imageUrl");
			String imageName = getParamNonNull("imageName");
			String nickName = getParamNonNull("nickName");
			String lessonIdParam = getParamNonNull("lessonId");
			String workTitle = getParamNonNull("workTitle");
			if(StringUtils.isBlank(imageUrl)){
				responseJson(new RespJson("0", "请先上传图片哦"));
				return;
			}
			if(StringUtils.isBlank(workTitle)) {
				responseJson(new RespJson("0", "请填写作品主题"));
				return;
			}
			if(StringUtils.isBlank(lessonIdParam)){
				responseJson(new RespJson("0", "缺少课程信息，请返回课程列表页重新操作哦"));
				return;
			}
			if(StringUtils.isBlank(imageName) || StringUtils.isBlank(nickName)){
				responseJson(new RespJson("0", "缺少必要信息"));
				return;
			}
			
			
			Integer lessonId = Integer.parseInt(lessonIdParam);
			Integer currentTaskStage = new Integer(2);
			
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
						//添作业提交记录
						LessonWork lessonWork = new LessonWork();
						lessonWork.setLessonId(lessonId);
						lessonWork.setImageName(imageName);
						lessonWork.setNickName(nickName);
						lessonWork.setOpenId(openId);
						lessonWork.setWorkTitle(workTitle);
						lessonWork.setWorkUrl(imageUrl);
						lessonWork.setCreateTime(new Date());
						lessonWork.setStatus(0);
						lessonWork.setIsHead(0);
						lessonWorkService.saveLessonWork(lessonWork);
						
						responseJson(new RespJson());
						return;
					} else {
						responseJson(new RespJson("0","请完成上一阶段的学习任务！"));
						return;
					}
				} else if(currentTaskStage.intValue() == userStudy.getCurrentTaskStage().intValue()){
					responseJson(new RespJson("0","您已提交过作业，请勿重复提交！"));
					return;
				}else {
					responseJson(new RespJson());
					return;
				}
			} else {
				responseJson(new RespJson("0","请先学习完本课视频后再操作！"));
				return;
			}
		} catch (NumberFormatException e) {
			responseJson(new RespJson("0","很抱歉，作业提交失败，请反馈至我们的客服小暮"));
			return;
		}
		
	}
	
	@RequestMapping(value="/getLessonWork", produces = "application/json;charset=UTF-8")
	@ResponseBody 
	public void getLessonWork() {
		try {
			String openId = getParamNonNull("openId");
			String lessonIdParam = getParamNonNull("lessonId");
			if(StringUtils.isBlank(lessonIdParam)) {
				responseJson(new RespJson("0", "缺少必要信息"));
				return;
			}
			LessonWork lessonWork = new LessonWork();
			lessonWork.setOpenId(openId);
			lessonWork.setLessonId(Integer.parseInt(lessonIdParam));
			List<LessonWorkDTO> lessonWorkDTOs = lessonWorkService.getLessonWorkList(lessonWork);
			if(CollectionUtils.isEmpty(lessonWorkDTOs)) {
				responseJson(new RespJson("0","暂无课程作业信息"));
				return;
			}
			LessonWorkDTO lessonWorkDTO = lessonWorkDTOs.get(0);
			//获取老师点评信息
			LessonWorkCommentTeacher teacher = new LessonWorkCommentTeacher();
			teacher.setWorkId(lessonWorkDTO.getWorkId());
			List<LessonWorkCommentTeacher> teachers = 
					lessonWorkCommentTeacherService.getLessonWorkCommentTeacherList(teacher);
			if(CollectionUtils.isNotEmpty(teachers)) {
				teacher = teachers.get(0);
				lessonWorkDTO.setTeacher(teacher);
			}
			responseJson(new RespJson(lessonWorkDTO));
		} catch (Exception e) {
			log.error("getLessonWork error:"+e.getMessage());
			responseJson(new RespJson("0","课程作业信息获取错误"));
			return;
		}
	}
	

	@RequestMapping(value="/submitComment", produces = "application/json;charset=UTF-8")
	@ResponseBody 
	public void submitComment() {
		try {
			//String openId = getParamNonNull("openId");
			String workIdParam = getParamNonNull("workId");
			String comment = getParamNonNull("comment");
			if(StringUtils.isBlank(workIdParam)) {
				responseJson(new RespJson("0", "缺少必要信息"));
				return;
			}
			if(StringUtils.isBlank(comment)) {
				responseJson(new RespJson("0", "请输入您的点评内容"));
				return;
			}
			
			Integer workId = Integer.parseInt(workIdParam);
			LessonWork lessonWork = lessonWorkService.getLessonWorkById(workId);
			if(null == lessonWork) {
				log.error("submitComment error:作品"+workId+"不存在");
				responseJson(new RespJson("0","作品信息获取错误"));
				return;
			}
			
			//查询课程信息
			Integer lessonId = lessonWork.getLessonId();
			LessonMainVO  lesson = lessonMainService.getLessonMainById(lessonId);
			if(null == lesson) {
				log.error("submitComment error:课程"+lessonId+"不存在");
				responseJson(new RespJson("0","课程信息获取错误"));
				return;
			}
			
			//判断用户是否已分享过
			UserStudyRecord record = new UserStudyRecord();
			record.setOpenId(lessonWork.getOpenId());
			record.setLessonId(lessonId);
			record.setTaskStage(StageScoreEnum.STAGE_3_SCORE.getStage());
			List<UserStudyRecord> studyRecords = userStudyRecordService.getUserStudyRecordList(record);
			if(CollectionUtils.isEmpty(studyRecords)){
				log.error("submitComment error:分享记录为空");
				responseJson(new RespJson("0","该用户暂未分享"));
				return;
			}
			
			//查看用户分享信息并处理
			userStudyRecordService.teacherEnsureShareRecord(studyRecords.get(0), true);
			
			//添加老师点评内容
			LessonWorkCommentTeacher commentTeacher = new LessonWorkCommentTeacher();
			commentTeacher.setWorkId(workId);
			commentTeacher.setTeacherName(lesson.getTeacherName());
			commentTeacher.setCommentContent(comment);
			commentTeacher.setCreateTime(new Date());
			lessonWorkCommentTeacherService.saveLessonWorkCommentTeacher(commentTeacher);
			
			//添加点评学习记录
			record.setTaskStage(StageScoreEnum.STAGE_4_SCORE.getStage());
			record.setCreateTime(new Date());
			userStudyRecordService.handleUserCommentRecord(record);
			
			//更新作业
			lessonWork.setUpdateTime(new Date());
			lessonWork.setStatus(1);
			lessonWorkService.modifyLessonWork(lessonWork);
			responseJson(new RespJson());
		} catch (Exception e) {
			responseJson(new RespJson("0",e.getMessage()));
			return;
		}
		
	}
	
}
