package com.desksoft.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.desksoft.entity.Categorydetail;
import com.desksoft.entity.mapper.CategorydetailMapper;


@Repository(value="categoryDetailDao")
public class CategoryDetailDao {
	
	@Inject
	private CategorydetailMapper  categorydetailMapper ;
	
	public void insert(Categorydetail record){
		categorydetailMapper.insert(record);
	}
	
	
	public List<Categorydetail> queryCategoryDetail(Map<String, Object> map){
		return 	categorydetailMapper.queryCategoryDetail(map);
	}

}