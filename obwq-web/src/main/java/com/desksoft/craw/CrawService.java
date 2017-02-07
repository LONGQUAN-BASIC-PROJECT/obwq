package com.desksoft.craw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.desksoft.util.UrlHelp;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.obwq.entity.Article;

import com.alibaba.fastjson.JSONObject;
import com.desksoft.common.constants.AgroupEnum;
import com.desksoft.dao.AgroupDao;
import com.desksoft.dao.ArticleDao;
import cn.obwq.dto.SechCrawDto;
import com.desksoft.handler.ParseArticlesHandler;
import com.desksoft.handler.impl.ZhihuParseImpl;
import com.desksoft.util.CollectionUtil;
import com.desksoft.util.HttpClientUtil;


@Service(value="crawService")
public class CrawService {

	public static Logger loger = LoggerFactory.getLogger(CrawService.class);
	
	@Autowired
	private HashMap<String, ParseArticlesHandler> handlerMap ; 
	
	@Autowired
	private ArticleDao articleDao; 
	
	@Autowired
	private AgroupDao agroupDao; 
	
	public Boolean crawFromWeb(SechCrawDto sechCrawDto){
		try {

			String crawType = UrlHelp.getCrawType(sechCrawDto);
			ParseArticlesHandler handler = handlerMap.get(crawType);
			if(handler == null){
				return null ;
			}
			String pageContent = HttpClientUtil.getHttpContent(sechCrawDto.getUrl());
			if(StringUtils.isBlank(pageContent)){
				return false; //进入重试队列
			}

			List<Article> listArt = handler.parser(pageContent,sechCrawDto);
			if(CollectionUtil.isEmpty(listArt)){
				return false;//进入重试队列
			}
			for(Article article : listArt){
				article.setGroupId(sechCrawDto.getId());
				saveArticle(article);
			}
			return true ;
		}catch (Exception e) {
			loger.error("error@crawWebError,crawDto=" + JSONObject.toJSONString(sechCrawDto));
			return false;
		}
	}
	
	
	
	private synchronized void saveArticle(Article article) {
		try {
			Boolean isExits = articleDao.isExits(article);
			if(isExits){
				return ;
			}
			article.setPraiseCount(0l);
			article.setCommentCount(0l);
			article.setCollectionCount(0l);

			articleDao.insert(article);

		}catch (Exception e){
			loger.error("error@save_article_error,do=",JSONObject.toJSONString(article),e);
		}
	}


	private String getCrawType(SechCrawDto sechCrawDto) {
		//if()

		return null ;
	}

	
	
	public static void main(String[] args) {
		CrawService cw = new CrawService();
		
		Map<String, ParseArticlesHandler> handlerMap = new HashMap<String, ParseArticlesHandler>();
		handlerMap.put(AgroupEnum.ZHIHU.name(), new ZhihuParseImpl());
		//cw.setHandlerMap(handlerMap);
		
		
		SechCrawDto sec = new SechCrawDto () ;
		sec.setName(AgroupEnum.ZHIHU.name());
		sec.setUrl("http://daily.zhihu.com/");
		
		
		cw.crawFromWeb(sec);
	}
	
	
	
}
