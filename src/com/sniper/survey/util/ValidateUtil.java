package com.sniper.survey.util;

import java.util.Collection;

/**
 * 校验工具类
 * 
 * @author sniper
 * 
 */
public class ValidateUtil {

	/**
	 * 验证字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isValid(String str) {

		if (str == null || "".equals(str.trim())) {
			return false;
		}
		return true;
	}

	/**
	 * 验证整数是否为正整数
	 * 
	 * @param i
	 * @return
	 */
	public static boolean isValid(int i) {

		return i <= 0 ? false : true;
	}

	/**
	 * 集合验证
	 * 
	 * @param cel
	 * @return
	 */
	public static boolean isValid(Collection cel) {
		if (cel == null || cel.isEmpty()) {
			return false;
		}
		return false;
	}

	public static boolean isValid(Class[] paramType) {
		if (paramType != null && paramType.length > 0) {
			return true;
		}
		return false;
	}
}
