package com.muguangli.api.mapper;

import com.muguangli.api.pojo.SuggestFeedback;

public interface SuggestFeedbackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SuggestFeedback record);

    int insertSelective(SuggestFeedback record);

    SuggestFeedback selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SuggestFeedback record);

    int updateByPrimaryKey(SuggestFeedback record);
}