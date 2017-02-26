package com.desksoft.service;

import java.util.Arrays;
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
		params.put("groupIds", Arrays.asList(new Long[]{groupId}));
		return articleDao.queryArticleByGroup(params);
	}

	public List<Article> queryArticleByGroup(List<String> groupIds,Integer currnetPage,Integer pageSize){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("pageSize", pageSize);
		params.put("offset", (currnetPage -1 ) * pageSize);
		params.put("groupIds", groupIds);
		return articleDao.queryArticleByGroup(params);
	}

	public Integer queryCrawArticleCount(){
		return articleDao.queryCrawArticleCount();
	}

	public Integer queryArticleCountByGroupId(Long groupId){
		return articleDao.queryArticleCountByGroupId(groupId);
	}

	public List<Article> queryHotArticleByGroup(Integer currnetPage, Integer pageSize) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("pageSize", pageSize);
		params.put("offset", (currnetPage -1 ) * pageSize);
		return articleDao.queryHotArticle(params);
	}


	public Boolean isExits(Article article){
		return articleDao.isExits(article);
	}

	public void insert(Article article){
		articleDao.insert(article);
	}

	public Integer queryArticleCountByGroupIds(List<String> groupIdList) {
		return articleDao.queryArticleCountByGroupIds(groupIdList);
	}
}
