package com.sniper.survey.struts2.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminUser;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<AdminUser> implements
		ServletRequestAware {

	private HttpServletRequest request;
	private static final long serialVersionUID = 1L;
	
	private String account;
	private String password;
	private String verify;
	private String submit;

	public String index() {
		
		return "index";
	}

	public String login() {

		return "login";
	}

	public void logout() {

	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;

	}
}