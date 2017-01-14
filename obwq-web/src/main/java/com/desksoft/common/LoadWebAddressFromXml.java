package com.desksoft.common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.InitializingBean;

import cn.obwq.util.FileUtil;

import com.desksoft.entity.xml.XmlBaseData;
import com.desksoft.entity.xml.XmlCategory;
import com.desksoft.entity.xml.XmlWebAddress;
import com.desksoft.util.XMLUtil;


public class LoadWebAddressFromXml implements InitializingBean {
	
	private static  XmlBaseData xba = new XmlBaseData() ;
	
	private final Timer timer = new Timer();
	
	private Long lastModify = 0l ;
	
	private Long _one_min = 10 *  60 * 1000l ;
	
	
	public void init(){
		loadContent();
		startSchedule() ;
	}
	
	private void startSchedule(){
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("------start schedule nav data---------");
				String xmlDataPath = getFilePath() ;
				File file = new File(xmlDataPath);
				long lf = file.lastModified();
				if(lastModify != lf){
					loadContent();
					lastModify = lf ;
				}
			}
		}, _one_min, _one_min);
	}
	
	private void loadContent(){
		try{
			String xmlDataPath = getFilePath() ;
			File file = new File(xmlDataPath);
			if(file.exists()){
				String fileData = FileUtil.readFile(file);
				Map<String, Class> cm = new HashMap<String, Class>();
				cm.put("XmlCategory", XmlCategory.class);
				cm.put("XmlWebAddress", XmlWebAddress.class);
				cm.put("XmlBaseData", XmlBaseData.class);
				XmlBaseData xb = (XmlBaseData) XMLUtil.xml2Bean(cm, fileData);
				xba = xb ;
				lastModify = file.lastModified();
				System.out.println("------start load content---------");
			}
		}catch (Exception e) {
			System.out.println("init xml file error");
			e.printStackTrace();
		}
	}
	
	public static XmlBaseData getBaseXmlData(){
		return xba ;
	}
	
	public static void main(String[] args) {
		new LoadWebAddressFromXml().init();
	}

	public void afterPropertiesSet() throws Exception {
		init();
	}
	
	
	private String getFilePath(){
		String path = "/home/data/data.xml" ;  //如果存在就使用这里的。
		if(new File(path).exists()){
			return path;
		}
		return LoadWebAddressFromXml.class.getResource("/data/data.xml").getFile();
	}
}
