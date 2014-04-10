package com.sniper.survey.struts2.interceptor;

import com.sniper.survey.model.AdminUser;

/**
 * 注入用户,
 * 这里注入的用户在用户登录拦截器中可以获取到
 * @author sniper
 *
 */
public interface UserAware {

	public void setUser(AdminUser user);
}
