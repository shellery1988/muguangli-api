package com.muguangli.api.mapper;

import com.muguangli.api.pojo.LessonWorkCommentUser;

public interface LessonWorkCommentUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LessonWorkCommentUser record);

    int insertSelective(LessonWorkCommentUser record);

    LessonWorkCommentUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LessonWorkCommentUser record);

    int updateByPrimaryKey(LessonWorkCommentUser record);
}