package com.desksoft.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.InitializingBean;

import cn.obwq.util.FileUtil;

import com.alibaba.fastjson.JSONObject;
import com.desksoft.entity.xml.en.EnglishWord;


public class LoadEnFromJson implements InitializingBean {
	
	private static  List<EnglishWord> xba = new ArrayList<EnglishWord>() ;
	
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
				System.out.println("------start schedule en word---------");
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
				List<EnglishWord> enList = JSONObject.parseArray(fileData, EnglishWord.class);
				xba = enList ;
				lastModify = file.lastModified();
				System.out.println("------start_en_load content---------");
			}
		}catch (Exception e) {
			System.out.println("init xml file error");
			e.printStackTrace();
		}
	}
	
	public static EnglishWord getRandEn(){
		if(xba == null || xba.size() == 0){
			return null ;
		}
		
		return xba.get(new Random().nextInt(xba.size()));
	}
	
	
	public static Integer getEnCout(){
		if(xba == null || xba.size() == 0){
			return 0 ;
		}
		
		return xba.size();
	
	}
	
	
	public static void main(String[] args) {
		new LoadEnFromJson().init();
	}

	public void afterPropertiesSet() throws Exception {
		init();
	}
	
	
	private String getFilePath(){
		String path = "/home/data/en.json" ;  //如果存在就使用这里的。
		if(new File(path).exists()){
			return path;
		}
		return LoadEnFromJson.class.getResource("/data/en.json").getFile();
	}
}
