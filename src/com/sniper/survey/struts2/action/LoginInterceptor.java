package com.sniper.survey.struts2.action;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sniper.survey.model.AdminUser;

/**
 * 登录拦截器
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
	
		System.out.println(arg0.getProxy().getMethod());
		BaseAction action = (BaseAction) arg0.getAction();
		System.out.println(action.getClass().getName());
		if(action instanceof LoginAction){
			
			return arg0.invoke();
		}else{
			AdminUser adminUser = (AdminUser) arg0.getInvocationContext().getSession().get("adminUser");
			if(adminUser == null){
				return "login";
			}else{
				return arg0.invoke();
			}
		}
		
	}

	

}
