package com.desksoft.entity.mapper;

import java.util.List;
import java.util.Map;

import com.desksoft.dao.SqlMapper;
import com.desksoft.entity.WebAddress;
import com.desksoft.vo.WebAddressVo;

public interface WebAddressMapper   extends SqlMapper  {
	/**
	 * 查询公共模块
	 * @return
	 */
	public List<WebAddress> selectCommonWebAddress();

	public Integer insertWebAddress(WebAddress webAddress);
	
	public List<WebAddressVo> selectMainContent();

	public List<WebAddress> selectByParentId(String categoryId);

	public Integer delete(String id);

	public Integer updateSequ(Map<String, Object> map);
}
