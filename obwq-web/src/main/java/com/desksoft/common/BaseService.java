package com.desksoft.common;

import java.util.List;
import java.util.Map;

public abstract class BaseService<T> {

	public abstract Integer delete(String id);
	
//	public abstract Integer edit(T t);
	
	public abstract Integer updateSequ(List<Map<String,Object>> listSequ);
}
