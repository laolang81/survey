package com.sniper.survey.struts2.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.struts2.action.BaseAction;
import com.sniper.survey.struts2.action.LoginAction;

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
		
	}

	@Override
	public void init() {
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
	
		System.out.println(arg0.getProxy().getMethod());
		BaseAction action = (BaseAction) arg0.getAction();
		System.out.println(action.getClass().getName());
		/*if(action instanceof LoginAction){
			
			return arg0.invoke();
		}else{
			AdminUser adminUser = (AdminUser) arg0.getInvocationContext().getSession().get("adminUser");
			if(adminUser == null){
				return "login";
			}else{
				return arg0.invoke();
			}
		}*/
		return arg0.invoke();
		
	}

	

}
