package com.muguangli.api.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.muguangli.core.AbstractController;
import com.muguangli.core.RespJson;

@Controller
@RequestMapping("/bgRemove")
public class BgRemoveTool extends AbstractController{

	private static Logger log = Logger.getLogger(BgRemoveTool.class);
	
	private final static String UPLOAD_RESPONSE_CODE = "error";
	private final static Integer UPLOAD_RESPONSE_SUCCESS = 0;
	
	@RequestMapping(value="/uploadImage",method=RequestMethod.GET)  
	public ModelAndView uploadImage() {
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/upload/uploadImage");  
        return modelAndView;
	}
	
	@RequestMapping(value="/upload")
	@ResponseBody 
	public void upload(@RequestParam("file") MultipartFile file) {
		InputStream is = null;
		OutputStream outputStream = null;
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();

			HttpPost httpPost = new HttpPost("https://api.remove.bg/v1.0/removebg");
			httpPost.addHeader("X-Api-Key", "c4Nza9mnvmns2AZNNSSjzWEg");
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);
			multipartEntityBuilder.addBinaryBody("image_file", file.getInputStream(), ContentType.DEFAULT_BINARY, file.getOriginalFilename());
			multipartEntityBuilder.addTextBody("size", "auto");

			HttpEntity requestEntity = multipartEntityBuilder.build();
			httpPost.setEntity(requestEntity);

			HttpResponse httpResponse = httpClient.execute(httpPost);

			int statusCode= httpResponse.getStatusLine().getStatusCode();
			if (statusCode != 200) throw new Exception("响应状态码为：" + statusCode);
			HttpEntity responseEntity = httpResponse.getEntity();
			is = responseEntity.getContent();
			
			File downloadFile = new File("D:/no_bg.png");
			outputStream = new FileOutputStream(downloadFile);
			int bytesWritten = 0;
			int byteCount = 0;
			byte[] bytes = new byte[1024];
			while ((byteCount = is.read(bytes)) != -1){
				outputStream.write(bytes, bytesWritten, byteCount);
				bytesWritten += byteCount;
			}
			is.close();
			outputStream.close();
			responseJson(new RespJson("下载完成"));
		} catch (Exception e) {
			log.error(e);
			responseJson(new RespJson("0", e.getMessage()));
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
