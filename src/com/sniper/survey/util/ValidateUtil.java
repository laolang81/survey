package com.sniper.survey.util;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sniper.survey.model.AdminRight;
import com.sniper.survey.model.AdminUser;

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

	public static boolean hasRight(String nameSpace, String actionName,
			HttpServletRequest request) {
		if (ValidateUtil.isValid(nameSpace) || "/".equals(actionName)) {
			nameSpace = "";
		}
		// 将超链接参数部分去掉
		if (actionName.contains("?")) {
			actionName = actionName.substring(0, actionName.indexOf("?"));
		}
		String url = nameSpace + "/" + actionName;
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		Map<String, AdminRight> map = (Map<String, AdminRight>) context
				.getAttribute("all_right_map");
		AdminRight right = map.get(url);
		if (request == null || right.isPublic()) {
			return true;
		} else {
			AdminUser user = (AdminUser) session.getAttribute("user");
			if (user == null) {
				return false;
			} else {
				if (user.isSuperAdmin()) {
					return true;
				} else {
					if (user.hasRight(right)) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
	}
}
