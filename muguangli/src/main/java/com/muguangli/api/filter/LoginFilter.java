package com.muguangli.api.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.muguangli.api.service.ILoginService;
import com.muguangli.core.RespJson;

/**
 * 
 * @Description:拦截器，获取不到登录信息的重新登录
 * @Version:1.1.0
 */
public class LoginFilter implements Filter{
	
	private List<String> ignoreAllList = new ArrayList<String>();
	
	private List<String> ignoreLoginList = new ArrayList<String>();
    
    public void destroy() {
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        String url = req.getPathInfo();
        if (url == null) {
            url = req.getServletPath();
        }
        String openId = req.getParameter("openId");
        if(!checkIgnoreAllList(url)){
        	if(!checkIgnoreLoginList(url)){
            	if(StringUtils.isEmpty(openId)) {
    				responseJson(rep, new RespJson("0","缺少必要参数openId"));
    				return;
    	        }
        	} else {
            	String jsCode = req.getParameter("jsCode");
                if(StringUtils.isBlank(jsCode)) {
                    responseJson(rep, new RespJson("0","缺少必要参数jsCode"));
                	return;
                }
        	}
        }
        
        //用户更新用户最新登录时间
		/*
		 * if(StringUtils.isNotBlank(openId)){ ServletContext sc =
		 * req.getSession().getServletContext(); XmlWebApplicationContext cxt =
		 * (XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext
		 * (sc);
		 * 
		 * if(cxt != null && null != cxt.getBean("loginServiceImpl")) { ILoginService
		 * usersService = (ILoginService) cxt.getBean("loginServiceImpl");
		 * usersService.recordLogin(openId); } }
		 */
        chain.doFilter(req, rep);
		return;
    }

    public void init(FilterConfig fConfig) throws ServletException {
    	ignoreAllList.add("/lesson/getListPage");
    	ignoreAllList.add("/work/uploadImage");
    	ignoreAllList.add("/work/showImage");
    	ignoreAllList.add("/work/showConvas");
    	ignoreAllList.add("/images/");
    	ignoreAllList.add("/pages/");
    	ignoreAllList.add("/bgRemove");
    	
    	ignoreLoginList.add("/login/checkLogin");
    }
    
    protected boolean checkIgnoreAllList(String path) {
        boolean rtn = false;
        for (String s : ignoreAllList) {
            if (s.endsWith("/*")) {
                String str = s.substring(0, s.length() - 2);
                if (path.startsWith(str)) {
                    rtn = true;
                    break;
                }
            } else {
                if (path.equals(s) || path.contains(s)) {
                    rtn = true;
                    break;
                }
            }
        }
        return rtn;
    }
    
    protected boolean checkIgnoreLoginList(String path) {
        boolean rtn = false;
        for (String s : ignoreLoginList) {
            if (s.endsWith("/*")) {
                String str = s.substring(0, s.length() - 2);
                if (path.startsWith(str)) {
                    rtn = true;
                    break;
                }
            } else {
                if (path.equals(s) || path.contains(s)) {
                    rtn = true;
                    break;
                }
            }
        }
        return rtn;
    }
    
    public void responseJson(HttpServletResponse rep, Object obj) {
    	rep.setContentType("text/html; charset=utf-8");
        try {
        	rep.getWriter().print(JSONObject.toJSON(obj));
        } catch (IOException e) {
            throw new RuntimeException("render json error", e);
        }
    }

    public void responseJson(HttpServletResponse rep, String jsonString) {
    	rep.setContentType("text/html; charset=utf-8");
        try {
        	rep.getWriter().print(jsonString);
        } catch (IOException e) {
            throw new RuntimeException("render json error" + jsonString, e);
        }
    }
    
}
