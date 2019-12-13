package com.muguangli.api.mapper;

import java.util.List;

import com.muguangli.api.dto.LessonWorkDTO;
import com.muguangli.api.pojo.LessonWork;

public interface LessonWorkMapper {
    int deleteByPrimaryKey(Integer workId);

    int insert(LessonWork record);

    int insertSelective(LessonWork record);

    LessonWork selectByPrimaryKey(Integer workId);

    int updateByPrimaryKeySelective(LessonWork record);

    int updateByPrimaryKey(LessonWork record);
    
    List<LessonWorkDTO> queryLessonWorkList(LessonWork record);
}