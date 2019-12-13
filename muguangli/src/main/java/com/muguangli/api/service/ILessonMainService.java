package com.muguangli.api.service;

import java.util.List;

import com.muguangli.api.pojo.LessonMain;
import com.muguangli.api.vo.LessonMainVO;
import com.muguangli.core.Page;

public interface ILessonMainService {
	
	List<LessonMainVO> getListPage(LessonMain queryBean, Page page);
	
	LessonMainVO getLessonMainById(Integer lessonId);
	
}
