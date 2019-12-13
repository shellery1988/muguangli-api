package com.muguangli.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.muguangli.api.mapper.LessonMainMapper;
import com.muguangli.api.pojo.LessonMain;
import com.muguangli.api.service.ILessonMainService;
import com.muguangli.api.vo.LessonMainVO;
import com.muguangli.core.Page;

@Service
public class LessonMainServiceImpl implements ILessonMainService {
	
	@Resource
	LessonMainMapper lessonMainMapper;
	
	@Override
	public List<LessonMainVO> getListPage(LessonMain record, Page page) {
		return lessonMainMapper.getListPage(record, page);
	}

	@Override
	public LessonMainVO getLessonMainById(Integer lessonId) {
		return lessonMainMapper.queryLessinInfoById(lessonId);
	}

}
