package com.desksoft.util;

import java.util.UUID;

public class TokenUtil {

	public static String generatorToken(){
		return UUID.randomUUID().toString() ;
	}
}
