package com.sniper.survey.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class App {

	public static String sniperMd5(String md5) throws NoSuchAlgorithmException{
		
		StringBuffer buffer = new StringBuffer();
		char[] chars = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		byte [] bytes = md5.getBytes();
		MessageDigest digest = MessageDigest.getInstance("MD5");
		byte[] bs = digest.digest(bytes);
		for(byte b: bs){
			buffer.append(chars[(b >> 4) & 0x0F]);
			buffer.append(chars[b  & 0x0F]);
		}
		return buffer.toString().toLowerCase();
	}
}
