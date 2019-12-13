package com.muguangli.api.mapper;

import com.muguangli.api.pojo.LessonTask;

public interface LessonTaskMapper {
    int deleteByPrimaryKey(Integer taskId);

    int insert(LessonTask record);

    int insertSelective(LessonTask record);

    LessonTask selectByPrimaryKey(Integer taskId);

    int updateByPrimaryKeySelective(LessonTask record);

    int updateByPrimaryKey(LessonTask record);
}