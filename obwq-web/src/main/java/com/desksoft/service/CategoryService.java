package com.desksoft.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.obwq.util.UUIDGenerator;

import com.desksoft.dao.CategoryDao;
import com.desksoft.dao.CategoryDetailDao;
import com.desksoft.entity.Category;
import com.desksoft.entity.Categorydetail;

/**
 * @author forever
 *
 */
@Service(value="categoryService")
public class CategoryService  {

	public static Logger loger = LoggerFactory.getLogger(CategoryService.class);
	
	@Autowired
	private CategoryDao categoryDao ;
	
	@Autowired
	private CategoryDetailDao categoryDetailDao ;
	
	
	public void insertCategory(String phone ,String desc){
		Category category = new Category();
		category.setId(UUIDGenerator.get());
		category.setPhone(phone);
		category.setDescr(desc);
		category.setGmtCreate(new Date());
		category.setGmtModify(new Date());
		
		categoryDao.insert(category);
	}
	
	
	public void insertCategoryDetail(String pid , String  name,String doTypeKey,String  doTypeValue ,Integer xseqt ,String buttonType){
		
		Categorydetail categorydetail = new Categorydetail() ;
		categorydetail.setButtonType(buttonType);
		categorydetail.setCgId(pid);
		categorydetail.setId(UUIDGenerator.get());
		categorydetail.setDoTypeKey(doTypeKey);
		categorydetail.setDoTypeValue(doTypeValue);
		categorydetail.setName(name);
		categorydetail.setXseqt(xseqt);
		categorydetail.setGmtCreate(new Date());
		categorydetail.setGmtModify(new Date());
		categoryDetailDao.insert(categorydetail);
	}
	
	
	public List<Category> queryCategory(Map<String,Object> params){
		return categoryDao.queryCategory(params);
	}
	
	
	public List<Categorydetail> queryCategoryDetail(String phone){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("phone", phone);
		return categoryDetailDao.queryCategoryDetail(params);
	}
	
	
	
}
