package com.muguangli.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.muguangli.api.dto.LessonWorkDTO;
import com.muguangli.api.mapper.LessonWorkMapper;
import com.muguangli.api.pojo.LessonWork;
import com.muguangli.api.service.ILessonWorkService;

@Service
public class LessonWorkServiceImpl implements ILessonWorkService {
	
	@Resource
	LessonWorkMapper lessonWorkMapper;
	
	@Override
	public void saveLessonWork(LessonWork record) {
		lessonWorkMapper.insertSelective(record);
	}

	@Override
	public List<LessonWorkDTO> getLessonWorkList(LessonWork record) {
		return lessonWorkMapper.queryLessonWorkList(record);
	}

	@Override
	public LessonWork getLessonWorkById(Integer workId) {
		return lessonWorkMapper.selectByPrimaryKey(workId);
	}

	@Override
	public void modifyLessonWork(LessonWork lessonWork) {
		lessonWorkMapper.updateByPrimaryKeySelective(lessonWork);
	}

}
