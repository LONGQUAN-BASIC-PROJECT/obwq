package com.desksoft.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import cn.obwq.entity.Article;

import com.desksoft.common.BaseDao;
import com.desksoft.entity.mapper.ArticleMapper;


@Repository(value="articleDao")
public class ArticleDao extends BaseDao {

	@Inject
	private ArticleMapper  articleMapper ;
	

	public Article getById(Long gid){
		return articleMapper.selectByPrimaryKey(gid);
	}
	
	
	public Boolean isExits(Article article){
		Integer count = articleMapper.queryArticleCount(article);
		if(count == 0){
			return false;
		}else{
			return true ;
		}
	}
	
	
    public void insert(Article article){
    	articleMapper.insert(article);
    }

	public List<Article> queryArticleByGroup(Map<String,Object> params){
		return articleMapper.queryArticleByGroup(params);
	}

	public Integer queryArticleCountByGroupId(Long groupId){
		return articleMapper.queryArticleCountByGroupId(groupId);
	}

	public Integer queryCrawArticleCount() {
		return articleMapper.queryCrawArticleCount();
	}


	public List<Article> queryHotArticle(Map<String, Object> params) {
		return articleMapper.queryHotArticle(params);
	}
}
