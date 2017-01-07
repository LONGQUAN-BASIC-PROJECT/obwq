package com.desksoft.entity.mapper;

import java.util.List;
import java.util.Map;

import cn.obwq.entity.Article;

import com.desksoft.dao.SqlMapper;

public interface ArticleMapper  extends SqlMapper  {

	public Article selectByPrimaryKey(Long gid);

    public void updateByPrimaryKeySelective(Article article) ;
    
    public void insert(Article article);

	public Integer queryArticleCount(Article article);
	
	public Integer queryArticleCountByGroupId(Long groupId);
	
	public List<Article> queryArticleByGroup(Map<String,Object> params);

}


