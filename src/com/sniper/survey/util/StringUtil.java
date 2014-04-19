package com.sniper.survey.util;

public class StringUtil {
	/**
	 * 判断字符是否存在数组之中
	 * 
	 * @param values
	 * @param value
	 * @return
	 */
	public static boolean contains(String[] values, String value) {
		if (ValidateUtil.isValid(values)) {
			for (String s : values) {
				if (s.equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 数组连接
	 * 
	 * @param col
	 * @return
	 */
	public static String arr2Str(Object[] col) {
		String str = "";

		if (ValidateUtil.isValid(col)) {
			for(Object s: col){
				str = str + s + ",";
			}
			return str.substring(1, str.length() - 1);
		}
		return str;
		
	}
	/**
	 * 字符串截取
	 * @param str
	 * @return
	 */
	public static String getDescString(String str){
		
		if(str != null && str.trim().length() > 30){
			return str.substring(0, 30);
		}
		return str;
	}
	
	
}
