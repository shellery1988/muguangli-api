package com.muguangli.api.service;

import java.util.List;

import com.muguangli.api.dto.LessonWorkDTO;
import com.muguangli.api.pojo.LessonWork;

public interface ILessonWorkService {
	
	void saveLessonWork(LessonWork lessonWork);
	
	void modifyLessonWork(LessonWork lessonWork);
	
	LessonWork getLessonWorkById(Integer workId);
	
	List<LessonWorkDTO> getLessonWorkList(LessonWork record);
	
}
