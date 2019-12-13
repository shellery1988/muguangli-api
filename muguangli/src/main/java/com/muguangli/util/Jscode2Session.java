package com.muguangli.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class Jscode2Session {
	
	private static Logger log = Logger.getLogger(Jscode2Session.class);
	
	public static String getOpenIdByJsCode(String jsCode) {
		try {
			StringBuffer url = new StringBuffer("https://api.weixin.qq.com/sns/jscode2session");
			String appId = PropertiesUtil.getBusinessProperties("appId");
			String secret = PropertiesUtil.getBusinessProperties("appSecret");
			url.append("?").append("appid=").append(appId).append("&").append("secret=").append(secret).append("&").append("js_code=").append(jsCode);
			String result = HttpClientUtil.get(url.toString());
			JSONObject resultJson = JSONObject.parseObject(result);
			if(resultJson==null) {
				log.error("resultJson is null");
				return null;
			}
			if(StringUtils.isNotBlank(resultJson.getString("errcode"))){
				log.error("errmsg:"+resultJson.getString("errmsg"));
				return null;
			}
			return resultJson.getString("openid");
		} catch (Exception e) {
			log.error("getOpenIdByJsCode error:"+e.getMessage());
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getOpenIdByJsCode("011qwpoX1TO5ZZ0x3IpX1WPhoX1qwpoC"));
	}
	
}
