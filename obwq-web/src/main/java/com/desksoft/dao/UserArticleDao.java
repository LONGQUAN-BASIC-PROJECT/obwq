package com.desksoft.dao;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import cn.obwq.entity.UserArticle;

import com.desksoft.common.BaseDao;
import com.desksoft.entity.mapper.UserArticleMapper;


@Repository(value="userArticleDao")
public class UserArticleDao extends BaseDao {

	@Inject
	private UserArticleMapper  userArticleMapper ;
	

	public UserArticle getUserById(Long id){
		return userArticleMapper.selectByPrimaryKey(id);
	}
	

	public void insert(UserArticle userArticle) {
		userArticleMapper.insert(userArticle);
	}
}
