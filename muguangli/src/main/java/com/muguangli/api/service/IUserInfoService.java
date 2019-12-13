package com.muguangli.api.service;

import com.muguangli.api.pojo.UserInfo;

public interface IUserInfoService {
	
	UserInfo getUserInfoByOpendId(String openId);
	
}
