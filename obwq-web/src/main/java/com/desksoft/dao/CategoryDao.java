package com.desksoft.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.desksoft.entity.Category;
import com.desksoft.entity.mapper.CategoryMapper;


@Repository(value="categoryDao")
public class CategoryDao {
	
	@Inject
	private CategoryMapper  categoryMapper ;
	
	public void insert(Category record){
		categoryMapper.insert(record);
	}
	
	public List<Category> queryCategory(Map<String,Object> params){
		return categoryMapper.queryCategory(params);
	}
	
}