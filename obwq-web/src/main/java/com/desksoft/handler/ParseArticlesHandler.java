package com.desksoft.handler;

import java.util.List;

import cn.obwq.entity.Article;

import com.desksoft.entity.dto.SechCrawDto;

public interface  ParseArticlesHandler {

	public  List<Article> parser(String pageContent,SechCrawDto sechCrawDto) ;
	
}
