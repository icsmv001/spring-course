package com.springcourse.Service.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {
	// classe responsavel para gerar Hash
	// metodo static, para uso sem necessidade de instanciar em outras classes.
	
	public static String getSecureHash(String text) {
		String hash  = DigestUtils.sha256Hex(text);
		return hash;
	}

}
