package com.sniper.survey.util;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sniper.survey.model.AdminRight;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.struts2.UserAware;
import com.sniper.survey.struts2.action.BaseAction;

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
	
	public static boolean isValid(Object[] paramType) {
		if(paramType != null && paramType.length > 0){
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param nameSpace
	 * @param actionName
	 * @param request
	 * @param action
	 * @return
	 */
	public static boolean hasRight(String nameSpace, String actionName,
			HttpServletRequest request, BaseAction action) {
		if (ValidateUtil.isValid(nameSpace) || "/".equals(actionName)) {
			nameSpace = "";
		}
		// 将超链接参数部分去掉?=ssssxxx
		if (actionName.contains("?")) {
			actionName = actionName.substring(0, actionName.indexOf("?"));
		}
		String url = nameSpace + "/" + actionName;
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		Map<String, AdminRight> map = (Map<String, AdminRight>) context
				.getAttribute("all_rights_map");
		AdminRight right = map.get(url);
		if (right == null || right.isPublic()) {
			return true;
		} else {
			AdminUser user = (AdminUser) session.getAttribute("user");
			//是否登录
			if (user == null) {
				return false;
			} else {
				if (action != null && action instanceof UserAware) {
					((UserAware) action).setUser(user);
				}
				//超级管理员
				if (user.isSuperAdmin()) {
					return true;
				} else {
					//有权限
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
