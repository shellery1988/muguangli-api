package com.muguangli.api.mapper;

import java.util.List;

import com.muguangli.api.pojo.UserStudy;

public interface UserStudyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserStudy record);

    int insertSelective(UserStudy record);

    UserStudy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserStudy record);

    int updateByPrimaryKey(UserStudy record);
    
    List<UserStudy> selectList(UserStudy record);
    
    Long queryLessonUserNumbers(Integer lessonId);
}