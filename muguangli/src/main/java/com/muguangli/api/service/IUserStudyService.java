package com.muguangli.api.service;

import java.util.List;

import com.muguangli.api.pojo.UserStudy;

public interface IUserStudyService {
	
	List<UserStudy> getList(UserStudy queryBean);
	
	int insertBean(UserStudy record);
	
	int updateBean(UserStudy record);
	
	Long getLessonUserNumbers(Integer lessonId);
	
}
