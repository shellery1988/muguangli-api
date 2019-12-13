package com.muguangli.api.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.muguangli.api.mapper.UserInfoMapper;
import com.muguangli.api.pojo.UserInfo;
import com.muguangli.api.service.IUserInfoService;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

	@Resource
	UserInfoMapper userInfoMapper;
	
	@Override
	public UserInfo getUserInfoByOpendId(String openId) {
		return userInfoMapper.selectByPrimaryKey(openId);
	}

}
