package com.desksoft.dao;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.desksoft.common.BaseDao;
import com.desksoft.entity.Language;
import com.desksoft.entity.mapper.LanguageMapper;


@Repository(value="languageDao")
public class LanguageDao extends BaseDao {

	@Inject
	private LanguageMapper  languageMapper ;
	

	/**
	 * 插入
	 * @return
	 */
	public Integer insertLanguage(Language language) {
		return languageMapper.insertSelective(language);
	}
	
	/**
	 * 所有的
	 * @return
	 */
	public List<Language> findAll(){
		return languageMapper.selectAll();
	}
}
