package com.desksoft.handler.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.obwq.constants.ApplicationConstants;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.desksoft.entity.ReceiveXmlEntity;
import com.desksoft.entity.dto.WxNewsReplyDto;
import com.desksoft.handler.WxProcessHandler;

public class TulingProcessHandlerImpl extends WxProcessHandler {

	private static Logger LOGER = LoggerFactory.getLogger(TulingProcessHandlerImpl.class);

	public String handler(ReceiveXmlEntity xmlEntity) {
		
		
		LOGER.error("ask_tuling,content={}",new Object[]{xmlEntity.getContent()});
		//100000 文本类
		//200000 链接类
		//302000 新闻类
		//308000 菜谱类
		JSONObject json = getTulingResult(xmlEntity.getToUserName(),xmlEntity.getContent());
		if(json == null){
			LOGER.info("ask_tuling_return null");
			//返回默认
			return formartTextResponse(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), ApplicationConstants.WX_DEFAULT_REPLY);
		}
		
		Integer code = json.getInteger("code") ;
		if(code == 100000){
			return genTextResponse(xmlEntity,json);
		}else if(code == 200000){
			return genSingleNewsResponse(xmlEntity,json);
		}else if(code == 302000){
			return genMutilNewsResponse(xmlEntity,json);
		}else{
			return formartTextResponse(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), "小主，您好");
		}
	}

	
	
	private String genMutilNewsResponse(ReceiveXmlEntity xmlEntity, JSONObject json) {
		JSONArray arr = json.getJSONArray("list");
		if(arr == null || arr.size() == 0){
			return formartTextResponse(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), ApplicationConstants.WX_DEFAULT_REPLY);
		}
		
		List<WxNewsReplyDto> list = new ArrayList<WxNewsReplyDto>();
		for(int i = 0 ; i < arr.size() && i < 10 ;i++){
			JSONObject object = (JSONObject) arr.get(i);
			WxNewsReplyDto wxReply = new WxNewsReplyDto() ;
			wxReply.setDescription(object.getString("article"));
			wxReply.setTitle(object.getString("article"));
			wxReply.setUrl(object.getString("detailurl"));
			wxReply.setPicUrl(object.getString("icon"));
			list.add(wxReply);
		}
		
		return fromartNewResponse(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),list);
	}



	private String genSingleNewsResponse(ReceiveXmlEntity xmlEntity, JSONObject json) {
		String text = json.getString("text");
		String url = json.getString("url");

		
		List<WxNewsReplyDto> list = new ArrayList<WxNewsReplyDto>();
		WxNewsReplyDto wxReply = new WxNewsReplyDto() ;
		wxReply.setDescription(text);
		wxReply.setTitle(xmlEntity.getContent());
		wxReply.setUrl(url);
		wxReply.setPicUrl("http://www.obwq.cn/images/g/d_f.png");
		list.add(wxReply);
		return fromartNewResponse(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),list);
	}



	private String genTextResponse(ReceiveXmlEntity xmlEntity,JSONObject json) {
		String text = json.getString("text");
		return formartTextResponse(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), text) ;
	}

	/**
	 * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需结果
	 * 
	 * @param content
	 * @return
	 */
	public JSONObject getTulingResult(String userId,String content) {
		/** 此处为图灵api接口，参数key需要自己去注册申请，先以11111111代替 */
		String apiUrl = "http://www.tuling123.com/openapi/api?userid=" + userId + "&key=c1324093939d73f6279369e57fb29025&info=";
		String param = "";
		try {
			param = apiUrl + URLEncoder.encode(content, "utf-8");
		} catch (UnsupportedEncodingException e1) {
		} // 将参数转为url编码

		/** 发送httpget请求 */
		HttpGet request = new HttpGet(param);
		String result = "";
		try {
			HttpResponse response = HttpClients.createDefault().execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e) {
			LOGER.error("requst_tuling_erro", e);
		}
		/** 请求失败处理 */
		if (null == result) { 
//			return "对不起，你说的话真是太高深了……";
			return null ;
		}

		try {
			return JSONObject.parseObject(result);
		} catch (JSONException e) {

		}
		return null;
	}

	
	public static void main(String[] args) {
		String userId = "sadfasdfasd";
		String content = "我想要看新闻" ;
		ReceiveXmlEntity xml = new ReceiveXmlEntity();
		xml.setToUserName(userId);
		xml.setFromUserName("sdfasdf");
		xml.setContent(content);
		String result = new TulingProcessHandlerImpl().handler(xml);
		System.out.println(result);
	}
	
}
