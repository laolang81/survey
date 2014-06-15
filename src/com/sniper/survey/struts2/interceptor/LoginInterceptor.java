package com.sniper.survey.struts2.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sniper.survey.struts2.MethodAware;
import com.sniper.survey.struts2.action.admin.BaseAction;
import com.sniper.survey.util.ValidateUtil;

/**
 * 登录拦截器
 * 
 * @author laolang
 * 
 */

public class LoginInterceptor extends AbstractInterceptor {

	
	private static final long serialVersionUID = -7064608312737489636L;

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		BaseAction action = (BaseAction) ai.getAction();
		System.out.println(action.getClass().getName());
		if (action instanceof MethodAware) {
			String method = ai.getProxy().getMethod();
			action.setMethod(method);
		}

		ActionProxy proxy = ai.getProxy();
		String ns = proxy.getNamespace();
		String actionName = proxy.getActionName();
		System.out.println("经过了拦截器");
		// 通过ServletActionContext 获取request
		if (ValidateUtil.hasRight(ns, actionName,
				ServletActionContext.getRequest(), action)) {
			return ai.invoke();
		} else {
			return "login";
		}
	}

}
