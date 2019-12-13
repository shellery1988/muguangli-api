package com.muguangli.api.mapper;

import java.util.List;

import com.muguangli.api.pojo.LessonWorkCommentTeacher;

public interface LessonWorkCommentTeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LessonWorkCommentTeacher record);

    int insertSelective(LessonWorkCommentTeacher record);

    LessonWorkCommentTeacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LessonWorkCommentTeacher record);

    int updateByPrimaryKey(LessonWorkCommentTeacher record);
    
    List<LessonWorkCommentTeacher> queryLessonWorkCommentTeacherList(LessonWorkCommentTeacher record);
    
}