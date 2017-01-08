package com.desksoft.util;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.Response;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	static OkHttpClient client = new OkHttpClient();

	public static String getHttpContent_bak(String url) {
		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

		HttpGet httpGet = new HttpGet(url);
		try {
			// 执行get请求
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			// 获取响应消息实体
			HttpEntity entity = httpResponse.getEntity();
			// 判断响应实体是否为空
			if (entity != null) {
				return EntityUtils.toString(entity);
			}
		} catch (IOException e) {
			// e.printStackTrace();
		} finally {
			try { // 关闭流并释放资源
				closeableHttpClient.close();
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}
		return StringUtils.EMPTY;
	}


	public static String getHttpContent(String url) {
		try {
			return run(url);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null ;
	}

	private static String run(String url) throws IOException {
		
	    Request request = new Request.Builder()
	        .url(url)
	        .build();

		Response response = client.newCall(request).execute();
//	    try (Response response = client.newCall(request).execute()) {
//	      return response.body().string();
//	    }

		return response.body().string();
	  }

	public static void main(String args[]) throws Exception {
		String url = "https://raw.githubusercontent.com/square/okhttp/master/samples/guide/src/main/java/okhttp3/guide/GetExample.java" ;
		String u = HttpClientUtil.run(url) ;
		System.out.println(u);
	}
}