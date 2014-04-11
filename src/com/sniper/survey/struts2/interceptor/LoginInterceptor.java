package com.sniper.survey.struts2.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.struts2.MethodAware;
import com.sniper.survey.struts2.UserAware;
import com.sniper.survey.struts2.action.BaseAction;
import com.sniper.survey.struts2.action.LoginAction;

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
	
	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation ai) throws Exception {

		//System.out.println(arg0.getProxy().getMethod());
		BaseAction action = (BaseAction) ai.getAction();
		//System.out.println(action.getClass().getName());
		if(action instanceof MethodAware){
			String method = ai.getProxy().getMethod();
			action.setMethod(method);
		}
		
		if (action instanceof LoginAction) {
			return ai.invoke();
		} else {
			AdminUser adminUser = (AdminUser) ai.getInvocationContext()
					.getSession().get("user");
			if (adminUser == null) {
				return "login";
			} else {
				//放行,注入用户对象
				if(action instanceof UserAware){
					((UserAware) action).setUser(adminUser);;
					
				}
				return ai.invoke();
			}
		}

	}

}
