package com.desksoft.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desksoft.entity.ReceiveXmlEntity;
import com.desksoft.handler.WxProcessHandler;
import com.desksoft.util.SimpleLruUtil;
import com.desksoft.util.XMLUtil;



/**
 * @author forever
 */
@Controller
@RequestMapping("/wx")
public class WxController {
	
	private static Logger loger = LoggerFactory.getLogger(WxController.class);
	
	@Autowired
	private HashMap<String, WxProcessHandler> mapHandler ;
	
	@ResponseBody
	@RequestMapping(value="/wxRequest")
	public String index(HttpServletRequest request,HttpServletResponse response){
		String echostr = request.getParameter("echostr");  
		if(StringUtils.isNotEmpty(echostr)){
			return echostr ;
		}
		String xml = readXmlFromRequest(request);
		if(StringUtils.isEmpty(xml)){
			return "" ;
		}
		
		
		ReceiveXmlEntity xmlEntity = XMLUtil.getWxMsgEntity(xml);
		if(StringUtils.isEmpty(xmlEntity.getMsgType())){
			xmlEntity.setMsgType("default");
		}
		//使用默认的
		WxProcessHandler wxProcess = mapHandler.get(xmlEntity.getMsgType());
		if(wxProcess == null){
			wxProcess = mapHandler.get("default");
		}
		
		//记忆处理
		String content = StringUtils.trim(xmlEntity.getContent()) ;
		
		
		String userId = xmlEntity.getFromUserName();
		if("1".equals(content)){
			String preContent  = SimpleLruUtil.getData(userId);
			if(StringUtils.isNotBlank(preContent)){
				xmlEntity.setContent(preContent);
				content = preContent ;
				SimpleLruUtil.putData(userId, preContent);
			}else{
				wxProcess = mapHandler.get("default");
			}
		}else{
			SimpleLruUtil.putData(userId, content);
		}
		
		if("?".equals(content) || "？".equals(content)){
			wxProcess = mapHandler.get("default");
		}
		
		if("en".equals(content)){
			wxProcess = mapHandler.get("englishWord");
		}
		
		String result = wxProcess.handler(xmlEntity);
		
		try {  
            OutputStream os = response.getOutputStream();  
            os.write(result.getBytes("UTF-8"));  
            os.flush();  
            os.close();  
        } catch (Exception e) {  

        } 
		
		return null ;
	} 
	
	
	
	
	private String readXmlFromRequest(HttpServletRequest request){
		/** 读取接收到的xml消息 */  
		StringBuffer sb = new StringBuffer();  
		try {
	        InputStream is = request.getInputStream();  
	        InputStreamReader isr = new InputStreamReader(is, "UTF-8");  
	        BufferedReader br = new BufferedReader(isr);  
	        String s = "";  
	        while ((s = br.readLine()) != null) {  
	            sb.append(s);  
	        }  
		}catch (Exception e) {
			loger.error("read_xml_exception");
		}
		return sb.toString() ;
	}
	
}
