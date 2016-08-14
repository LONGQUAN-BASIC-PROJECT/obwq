package cn.obwq.util;

import java.util.UUID;

public class UUIDGenerator {
	
	
	/**
	 * 全球通用唯一标识
	 * 
	 * @return
	 */
	public static String get() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
