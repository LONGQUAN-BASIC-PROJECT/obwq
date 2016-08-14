package com.desksoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desksoft.dao.LanguageDao;
import com.desksoft.entity.Language;

/**
 * 文章service
 * @author forever
 *
 */
@Service(value="languageService")
public class LanguageService /*extends BaseServvice*/ {

	@Autowired
	private LanguageDao  languageDao;
	
	/**
	 * 保存
	 * @param language
	 * @return
	 */
	public Integer insertSelective(Language language){
		return languageDao.insertLanguage(language);
	}
	
	
	/**
	 * 所有的
	 * @return
	 */
	public List<Language> findAll(){
		return languageDao.findAll();
	}
}
