package com.muguangli.api.service.impl;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muguangli.api.mapper.UserInfoMapper;
import com.muguangli.api.pojo.UserInfo;
import com.muguangli.api.service.ILoginService;
import com.muguangli.util.DateFormatUtil;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
    private UserInfoMapper userInfoMapper;
	
	@Override
	public UserInfo getUserInfoByOpenId(String openId) {
		
		return userInfoMapper.selectByPrimaryKey(openId);
	}

	@Override
	public int saveUserInfo(UserInfo userInfo) {
		return userInfoMapper.insertSelective(userInfo);
	}

	@Override
	public int modifyUserInfo(UserInfo userInfo) {
		return userInfoMapper.updateByPrimaryKeySelective(userInfo);
	}

	@Override
	public void recordLogin(String openId) {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(openId);
		if(userInfo==null){
			//新增用户
			userInfo = new UserInfo();
			userInfo.setOpenId(openId);
			userInfo.setLastloginTime(new Date());
			userInfoMapper.insertSelective(userInfo);
		} else {
			//比较当天日期是否为最后登录日期(天)
			Date lastLoginTime = userInfo.getLastloginTime();
			LocalDate lastLoginDate = DateFormatUtil.date2LocalDate(lastLoginTime);
			LocalDate currentDate = LocalDate.now();
			if(! lastLoginDate.isEqual(currentDate)) {
				//更新最近登录日期
				userInfo.setLastloginTime(new Date());
			}
			userInfoMapper.updateByPrimaryKeySelective(userInfo);
		}
	}
	
	public static void main(String[] args) {
		Date d1 = new Date();
		LocalDate ld1 = DateFormatUtil.date2LocalDate(d1);
		System.out.println(ld1);
		System.out.println(LocalDate.now());
	}

}
