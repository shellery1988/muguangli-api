package com.muguangli.api.mapper;

import com.muguangli.api.pojo.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String openId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String openId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    
    int addScoreForUser(UserInfo record);
    
}