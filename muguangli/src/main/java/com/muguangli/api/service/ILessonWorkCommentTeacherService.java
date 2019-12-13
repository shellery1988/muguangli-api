package com.muguangli.api.service;

import java.util.List;

import com.muguangli.api.pojo.LessonWorkCommentTeacher;

public interface ILessonWorkCommentTeacherService {
	
	void saveLessonWorkCommentTeacher(LessonWorkCommentTeacher record);
	
	List<LessonWorkCommentTeacher> getLessonWorkCommentTeacherList(LessonWorkCommentTeacher record);
	
}
