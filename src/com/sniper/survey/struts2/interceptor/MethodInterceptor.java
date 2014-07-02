package com.sniper.survey.struts2.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sniper.survey.struts2.MethodAware;
import com.sniper.survey.struts2.action.admin.BaseAction;

/**
 * 获取action里面的方法名
 * @author sniper
 *
 */
public class MethodInterceptor extends AbstractInterceptor {

	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		
		BaseAction action = (BaseAction) ai.getAction();
		if (action instanceof MethodAware) {
			String method = ai.getProxy().getMethod();
			action.setMethod(method);
		}
		return ai.invoke();
	}

}
