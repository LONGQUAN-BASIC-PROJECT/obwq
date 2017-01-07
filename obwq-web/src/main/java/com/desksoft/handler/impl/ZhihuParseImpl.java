package com.desksoft.handler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.obwq.entity.Article;

import com.desksoft.entity.dto.SechCrawDto;
import com.desksoft.handler.ParseArticlesHandler;

public class ZhihuParseImpl implements ParseArticlesHandler {

	public List<Article> parser(String pageContent,SechCrawDto sechCrawDto) {
		Document jsoup =  Jsoup.parse(pageContent);
		Elements els = jsoup.getElementsByClass("col-lg-4");
		if(els == null || els.size() == 0){
			return null ;
		}
		
		List<Article>  artList = new ArrayList<Article>();
		
		for(Element el : els){
			Elements wraps = el.getElementsByClass("wrap");
			if(wraps == null || wraps.size() == 0){
				continue ;
			}
			
			for(Element wel : wraps){
				Article article = new Article () ;
				String title = wel.getElementsByTag("span").text();
				String url = wel.getElementsByTag("a").attr("href");
				if(!url.startsWith("http")){
					url = sechCrawDto.getUrl() + url ;
				}
				url = url.replace("//", "/");
				
				article.setTitle(title);
				article.setUrl(url);
				article.setGmtCreate(new Date());
				article.setGetModify(new Date());
				artList.add(article);
			}
		}
		
		return artList;
	}

}
