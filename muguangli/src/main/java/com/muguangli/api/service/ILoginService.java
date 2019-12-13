package com.muguangli.api.service;

import com.muguangli.api.pojo.UserInfo;

public interface ILoginService {
	
	UserInfo getUserInfoByOpenId(String openId);
	
	int saveUserInfo(UserInfo userInfo);
	
	int modifyUserInfo(UserInfo userInfo);
	
	void recordLogin(String openId);
	
}
