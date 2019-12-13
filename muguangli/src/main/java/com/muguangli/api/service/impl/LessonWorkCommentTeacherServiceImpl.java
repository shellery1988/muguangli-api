package com.muguangli.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.muguangli.api.mapper.LessonWorkCommentTeacherMapper;
import com.muguangli.api.pojo.LessonWorkCommentTeacher;
import com.muguangli.api.service.ILessonWorkCommentTeacherService;

@Service
public class LessonWorkCommentTeacherServiceImpl implements ILessonWorkCommentTeacherService {
	
	@Resource
	LessonWorkCommentTeacherMapper lessonWorkCommentTeacherMapper;
	
	@Override
	public List<LessonWorkCommentTeacher> getLessonWorkCommentTeacherList(LessonWorkCommentTeacher record) {
		return lessonWorkCommentTeacherMapper.queryLessonWorkCommentTeacherList(record);
	}

	@Override
	public void saveLessonWorkCommentTeacher(LessonWorkCommentTeacher record) {
		lessonWorkCommentTeacherMapper.insertSelective(record);
	}
	
}
