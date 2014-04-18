package com.sniper.survey.struts2.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sniper.survey.model.AdminRight;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.struts2.MethodAware;
import com.sniper.survey.struts2.UserAware;
import com.sniper.survey.struts2.action.BaseAction;
import com.sniper.survey.util.ValidateUtil;

/**
 * 登录拦截器
 * 
 * @author laolang
 * 
 */
public class LoginInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	public String intercept(ActionInvocation ai) throws Exception {

		BaseAction action = (BaseAction) ai.getAction();
		// System.out.println(action.getClass().getName());
		if (action instanceof MethodAware) {
			String method = ai.getProxy().getMethod();
			action.setMethod(method);
		}

		ActionProxy proxy = ai.getProxy();
		String ns = proxy.getNamespace();
		String actionName = proxy.getActionName();
		// 当明明空间不存在，action名称等于/的时候，这是根目录
		if (!ValidateUtil.isValid(ns) || "/".equals(actionName)) {
			ns = "";
		}

		String url = ns + "/" + actionName;
		//送appliactiopn中获取right
		Map<String, AdminRight>  map = (Map<String, AdminRight>) ai.getInvocationContext().getApplication().get("all_rights_map"); 
		
		// 通过url查询right对象
		AdminRight right = map.get(url);
		if (right == null || right.isPublic()) {
			return ai.invoke();
		} else {
			AdminUser adminUser = (AdminUser) ai.getInvocationContext()
					.getSession().get("user");
			if (adminUser == null) {
				// return "login";

			} else {
				// 放行,注入用户对象
				if (action instanceof UserAware) {
					((UserAware) action).setUser(adminUser);
					

				}
				// 超级管理yaun
				if (adminUser.isSuperAdmin()) {
					return ai.invoke();
				} else {
					if (adminUser.hasRight(right)) {
						return ai.invoke();
					} else {
						// return "login";
					}
				}

				return ai.invoke();
			}
		}
		return url;

		/*
		 * if (action instanceof LoginAction) { return ai.invoke(); } else {
		 * AdminUser adminUser = (AdminUser) ai.getInvocationContext()
		 * .getSession().get("user"); if (adminUser == null) { //return "login";
		 * 
		 * } else { //放行,注入用户对象 if(action instanceof UserAware){ ((UserAware)
		 * action).setUser(adminUser);;
		 * 
		 * } //return ai.invoke(); } return ai.invoke(); }
		 */
	}

}
