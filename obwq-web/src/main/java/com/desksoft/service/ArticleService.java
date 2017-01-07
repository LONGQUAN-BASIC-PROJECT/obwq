package com.desksoft.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.obwq.entity.Article;

import com.desksoft.dao.ArticleDao;

/**
 * @author forever
 *
 */
@Service(value="articleService")
public class ArticleService   {

	@Autowired
	private ArticleDao  articleDao;
	
	public Article getById(Long gid){
		return articleDao.getById(gid);
	}
	
	
	public List<Article> queryArticleByGroup(Long groupId,Integer currnetPage,Integer pageSize){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("pageSize", pageSize);
		params.put("offset", (currnetPage -1 ) * pageSize);
		params.put("groupId", groupId);
		return articleDao.queryArticleByGroup(params);
	}

	public Integer queryArticleCountByGroupId(Long groupId){
		return articleDao.queryArticleCountByGroupId(groupId);
	}
}
