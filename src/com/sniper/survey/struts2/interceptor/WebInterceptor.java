package com.sniper.survey.struts2.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sniper.survey.struts2.MethodAware;
import com.sniper.survey.struts2.webAction.BaseAction;

/**
 * 登录拦截器
 * 
 * @author laolang
 * 
 */
public class WebInterceptor implements Interceptor {

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

	
	@Override
	public String intercept(ActionInvocation ai) throws Exception {

		
		BaseAction action = (BaseAction) ai.getAction();
		System.out.println(action.getClass().getName());
		if (action instanceof MethodAware) {
			String method = ai.getProxy().getMethod();
			action.setMethod(method);
		}

		return ai.invoke();
	
	}
}
