package com.muguangli.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.muguangli.util.StringUtil;

public class AbstractController{
	
	private static Logger log = Logger.getLogger(AbstractController.class);
	
	@Autowired
    private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	public HttpServletRequest getReq() {
		return request;
	}

	public HttpServletResponse getRes() {
		return response;
	}
	
    public void responseJson(Object obj) {
        getRes().setContentType("text/html; charset=utf-8");
        try {
            getRes().getWriter().print(JSONObject.toJSON(obj));
        } catch (IOException e) {
            throw new RuntimeException("render json error", e);
        }
    }

    public void responseJson(String jsonString) {
        getRes().setContentType("text/html; charset=utf-8");
        try {
            getRes().getWriter().print(jsonString);
        } catch (IOException e) {
            throw new RuntimeException("render json error" + jsonString, e);
        }
    }

    public String getParam(String nm) {
		return getReq().getParameter(nm);
	}
    
    protected String getParamNonNull(String param) {
        return StringUtil.isEmpty(getParam(param)) ? "" : getParam(param);
    }
    
    /**
     * @param param
     *            入参json
     * @param isPage
     *            是否需要回写页面
     * @return
     * @Description:校验接口入参是否有效
     */
    protected JSONObject checkParam(String param, boolean isPage) {
        if (StringUtils.isBlank(param)) {
            if (isPage) {
                responseJson(new RespJson(ErrorEnum.PARAM_NULL));
            }
            return null;
        }
        try {
            param = URLDecoder.decode(param, "utf-8");
        } catch (UnsupportedEncodingException e) {
            if (isPage) {
                responseJson(new RespJson(ErrorEnum.PARAM_DECODE_ERROR));
            }
        }
        JSONObject json = null;
        try {
            json = JSONObject.parseObject(param);
        } catch (Exception e1) {
            if (isPage) {
                responseJson(new RespJson(ErrorEnum.STRING_TO_JSON_ERROR));
            }
        }
        return json;
    }
    
    /**
     * @param param
     * @Description:获取页面参数,通过反射映入实体,json类型适用
     */
    protected <T> T getParamJson(Class<T> respClass) {
        T resp = null;

        String param = getParamNonNull("param");
        JSONObject json = checkParam(param, false);
        // 入参格式错误
        if (json == null) {
            return null;
        }
        try {
            // 需要赋值的类
            resp = respClass.newInstance();

            // 拿到该类
            Class<?> clz = resp.getClass();
            // 获取实体类的所有属性，返回Field数组
            Field[] fields = clz.getDeclaredFields();
            // 解决vo继承自实体问题
            Field[] fafields = clz.getSuperclass().getDeclaredFields();

            Field[] both = (Field[]) ArrayUtils.addAll(fields, fafields);

            for (Field field : both) {// --for() begin
                // log.debug(field.getName() + "-----" + field.getGenericType());//
                // 打印该类的所有属性类型

                String name = field.getName(); // 获取属性的名字
                String nameUp = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
                String type = field.getGenericType().toString(); // 获取属性的类型
                // 去除序列化
                if ("serialVersionUID".equals(name)) {
                    continue;
                }

                String val = json.getString(name);

                // 值为null的情况下不用赋值
                if (val == null) {
                    continue;
                }

                // String 类型处理
                if ("class java.lang.String".equals(type)) {
                    // 获取set方法
                    Method m = clz.getMethod("set" + nameUp, String.class);// 获取set方法
                    // string直接转
                    m.invoke(resp, val);
                }
                if ("class java.lang.Long".equals(type)) {
                    Long longVal = null;
                    try {
                        longVal = Long.parseLong(val);
                    } catch (NumberFormatException e) {
                        log.error("longVal parseLong error:", e);
                    }
                    // 获取set方法
                    Method m = clz.getMethod("set" + nameUp, Long.class);// 获取set方法
                    // string直接转
                    m.invoke(resp, longVal);
                }
                if ("class java.lang.Double".equals(type)) {

                    Double doubleVal = null;
                    try {
                        doubleVal = Double.parseDouble(val);
                    } catch (NumberFormatException e) {
                        log.error("val parseDouble error:", e);
                    }
                    Method m = clz.getMethod("set" + nameUp, Double.class);// 获取set方法
                    m.invoke(resp, doubleVal);

                }
                if ("class java.lang.Integer".equals(type)) {
                    Integer intVal = null;
                    try {
                        intVal = Integer.parseInt(val);
                    } catch (NumberFormatException e) {
                        log.error("val parseInt error:", e);
                    }
                    // 获取set方法
                    Method m = clz.getMethod("set" + nameUp, Integer.class);// 获取set方法
                    m.invoke(resp, intVal);
                }
                if ("class java.util.Date".equals(type)) {
                    Date dateVal = null;
                    try {
                    if(val instanceof String){
                        dateVal = new SimpleDateFormat("yyyy-MM-dd").parse(val);
                    }else{
                        dateVal = new Date(Long.parseLong(val) * 1000);
                     }
                    } catch (Exception e) {
                        log.error("val SimpleDateFormat error:", e);
                    }
                    // 获取set方法
                    Method m = clz.getMethod("set" + nameUp, Date.class);// 获取set方法
                    m.invoke(resp, dateVal);
                }
            }

        } catch (Exception e) {
            log.error("调用接口异常" + resp, e);
            // throw e;
        }
        return resp;
    }
    
    protected <T> T getParamForInterface(Class<T> respClass, String type) {
        T resp = getParamJson(respClass);
        // 获取json错误
        if (resp == null) {
            responseJson(new RespJson(ErrorEnum.STRING_TO_JSON_ERROR));
            return null;
        }
        // 校验参数
        ValidateResult result = AnnotationValidator.validate(resp, type);

        if (!result.isValid()) {
            responseJson(new RespJson(ErrorEnum.PARAM_ERROR.getErrorCode(), result.getMessage()));
            return null;
        }

        return resp;
    }
    
}
