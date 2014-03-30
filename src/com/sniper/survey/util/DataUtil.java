package com.sniper.survey.util;

import java.security.MessageDigest;

public class DataUtil {

	public static String md5(String md5) {
		String result = "";
		try {
			StringBuffer buffer = new StringBuffer();
			char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'A', 'B', 'C', 'D', 'E', 'F' };
			byte[] bytes = md5.getBytes();
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] bs = digest.digest(bytes);
			for (byte b : bs) {
				buffer.append(chars[(b >> 4) & 0x0F]);
				buffer.append(chars[b & 0x0F]);
			}
			result = buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
