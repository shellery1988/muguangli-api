package com.muguangli.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @Description:http链接公共类
 * @Author:forgive56
 * @Since:2017年2月13日
 * @Version:1.1.0
 */
public class HttpClientUtil {

    /**
     * 
     * @param url
     * @param paramsMap
     * @Description:post方法
     */
    public static String post(String url, Map<String, String> paramsMap) {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        Map<String, String> params = paramsMap;
        if (params == null) {
            params = new HashMap<String, String>();
        }
        try {
            if (params.size() > 0) {
                // 创建参数队列
                List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
                Set<String> keySet = params.keySet();
                for (String key : keySet) {
                    formparams.add(new BasicNameValuePair(key, params.get(key)));
                }
                UrlEncodedFormEntity uefEntity;
                uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
                httppost.setEntity(uefEntity);
            }
            //System.out.println("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // System.out.println("--------------------------------------");
                    // System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                    // System.out.println("--------------------------------------");
                    return EntityUtils.toString(entity, "UTF-8");
                }
            } finally {
                response.close();

            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 
     * @param url
     * @param paramsMap
     * @Description:post方法
     */
    public static String postWithHeader(String url, String paramsStr, String contentType, String encode) {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        try {
            if(encode==null || "".equals(encode)){
                encode = "UTF-8";
            }
            if (paramsStr!=null && !"".equals(paramsStr)) {
                StringEntity entity = new StringEntity(paramsStr, Charset.forName(encode));
                entity.setContentEncoding(encode);
                httppost.setEntity(entity);
            }
            if(contentType!=null && !"".equals(contentType)){
                httppost.setHeader("Content-type",contentType);
            }
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity, "UTF-8");
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * 
     * @param url
     * @Description:post方法,无参数
     */
    public static String post(String url) {
        return post(url, null);
    }

    /**
     * 
     * @param url
     * @Description:get方法
     */
    public static String get(String url) {

        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet(url);

            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // System.out.println("--------------------------------------");
                // // 打印响应状态
                // System.out.println(response.getStatusLine());
                if (entity != null) {
                    // // 打印响应内容长度
                    // System.out.println("Response content length: " + entity.getContentLength());
                    // // 打印响应内容
                    // System.out.println("Response content: " + EntityUtils.toString(entity,"UTF-8"));
                    return EntityUtils.toString(entity, "UTF-8");
                }
                // System.out.println("------------------------------------");
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 
     * @param url
     * @param paramsMap
     * @Description:get方法,参数拼接
     */
    public static String get(String url, Map<String, String> paramsMap) {
        StringBuffer urlget = new StringBuffer(url);
        Map<String, String> params = paramsMap;
        if (params == null) {
            params = new HashMap<String, String>();
        }
        if (params.size() > 0) {
            urlget.append("?");
            // 创建参数队列
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                urlget.append(key).append("=").append(params.get(key)).append("&");
            }

        }
        return get(urlget.toString());
    }
}
