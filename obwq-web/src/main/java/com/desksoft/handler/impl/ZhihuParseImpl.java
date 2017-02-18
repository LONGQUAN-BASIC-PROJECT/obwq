package com.desksoft.handler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.desksoft.util.HttpClientUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.obwq.entity.Article;

import cn.obwq.dto.SechCrawDto;
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
				url = "http://daily.zhihu.com" + url;
				
				article.setTitle(title);
				article.setUrl(url.trim());
				article.setGmtCreate(new Date());
				article.setGetModify(new Date());
				artList.add(article);
				article.setGroupId(sechCrawDto.getId());
				System.out.println("title="+title + ",url= "+url + " ,time=" + article.getGmtCreate());

			}
		}
		
		return artList;
	}


	public static void main(String[] s) throws Exception{

		String pageContent = HttpClientUtil.getHttpContent("http://daily.zhihu.com");

		new ZhihuParseImpl().parser(pageContent,null  );

	}

}
