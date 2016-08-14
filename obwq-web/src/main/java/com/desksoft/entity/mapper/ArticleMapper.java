package com.desksoft.entity.mapper;

import java.util.List;

import com.desksoft.dao.SqlMapper;
import com.desksoft.entity.Article;
import com.desksoft.vo.ArticleVo;

@com.desksoft.common.anno.SqlMapper
public interface ArticleMapper extends SqlMapper{
	
	public void insertArticle(Article article);

	/**
	 * 通过条件查询文章
	 * @param vo
	 * @return
	 */
	public List<Article> queryArticleByCondition(ArticleVo vo);
}
