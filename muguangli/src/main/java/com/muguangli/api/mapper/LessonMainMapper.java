package com.muguangli.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muguangli.api.pojo.LessonMain;
import com.muguangli.api.vo.LessonMainVO;
import com.muguangli.core.Page;

public interface LessonMainMapper {
    int deleteByPrimaryKey(Integer lessonId);

    int insert(LessonMain record);

    int insertSelective(LessonMain record);

    LessonMain selectByPrimaryKey(Integer lessonId);
    
    LessonMainVO queryLessinInfoById(Integer lessonId);

    int updateByPrimaryKeySelective(LessonMain record);

    int updateByPrimaryKey(LessonMain record);
    
    List<LessonMainVO> getListPage(@Param("queryBean") LessonMain record, @Param("page") Page page);
    
}