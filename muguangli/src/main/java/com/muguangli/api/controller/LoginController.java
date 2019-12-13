package com.muguangli.api.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.muguangli.api.pojo.UserInfo;
import com.muguangli.api.service.ILoginService;
import com.muguangli.api.service.IUserInfoService;
import com.muguangli.api.service.IUserStudyRecordService;
import com.muguangli.core.AbstractController;
import com.muguangli.core.RespJson;
import com.muguangli.util.Jscode2Session;

@Controller
@RequestMapping("/login")
public class LoginController extends AbstractController{
	
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Resource
	ILoginService loginService;
	
	@Resource
	IUserInfoService userInfoService;
	
	@Resource
	IUserStudyRecordService userStudyRecordService;
	
	@RequestMapping(value="/checkLogin", produces = "application/json;charset=UTF-8")
	@ResponseBody 
	public void checkLogin() {
		try {
			String jsCode = getParamNonNull("jsCode");
			if(StringUtils.isBlank(jsCode)){
				responseJson(new RespJson("0", "缺少必要参数jsCode"));
				return;
			}
			String openId = Jscode2Session.getOpenIdByJsCode(jsCode);
			responseJson(new RespJson(openId));
			return;
		} catch (Exception e) {
			responseJson(new RespJson("0",e.getMessage()));
			return;
		}
	}
	
	@RequestMapping(value="/userLogin", produces = "application/json;charset=UTF-8")
	@ResponseBody 
	public void userLogin() {
		try {
			UserInfo userInfo = null;
			try {
				if(StringUtils.isNotBlank(getParamNonNull("param"))) {
					userInfo = getParamForInterface(UserInfo.class, "param");
				}
			} catch (Exception e) {
				log.info("userInfo is null======");
			}	
			if(userInfo==null) {
				userInfo = new UserInfo();
			} else {
				log.info("userInfo======"+JSONObject.toJSONString(userInfo));
			}
			String openId = getParamNonNull("openId");
			UserInfo temp = loginService.getUserInfoByOpenId(openId);
			
			if(temp!=null) {
				/**
				 * 更新用户最后登录日期
				 */
				userInfo.setOpenId(openId);
				userInfo.setLastloginTime(new Date());
				loginService.modifyUserInfo(userInfo);
			} else {
				/**
				 * 新增用户
				 */
				userInfo.setOpenId(openId);
				userInfo.setLastloginTime(new Date());
				loginService.saveUserInfo(userInfo);
			}
			userInfo = userInfoService.getUserInfoByOpendId(openId);
			responseJson(userInfo);
			return;
		} catch (Exception e) {
			responseJson(new RespJson("0",e.getMessage()));
			return;
		}
	}
	
	@RequestMapping(value="/getUserInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody 
	public void getUserInfo() {
		try {
			String openId = getParamNonNull("openId");
			UserInfo userInfo = userInfoService.getUserInfoByOpendId(openId);
			responseJson(new RespJson(userInfo));
			return;
		} catch (Exception e) {
			responseJson(new RespJson("0",e.getMessage()));
			return;
		}
	}
	
}
