package com.desksoft.util;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtil {

	public static String getHttpContent_bak(String url,String charset) {
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
			if (entity != null && cn.obwq.util.StringUtils.isNotEmpty(charset)) {
				return EntityUtils.toString(entity,charset);
			}else if(entity != null){
				return EntityUtils.toString(entity,"UTF-8");
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
			String str = null ;
			if(url.startsWith("https:")){
				str = 	runHttps(url);
			}else{
				String charset = UrlHelp.getCharset(url);
				if(cn.obwq.util.StringUtils.isNotBlank(charset)){
					str =  getHttpContent_bak(url,charset);
				}else{
					str =  getHttpContent_bak(url,null);
				}

			}
			return str ;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null ;
	}


	private static String runHttps(String url){
		try {
			SSLClient sslclient = SSLClient.getClient() ;

			HttpGet httpGet = new HttpGet(url);
			// 执行get请求
			HttpResponse httpResponse = sslclient.execute(httpGet);
			// 获取响应消息实体
			HttpEntity entity = httpResponse.getEntity();
			// 判断响应实体是否为空
			if (entity != null) {
				return EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {


		}

		return null ;
	}

}