package com.sniper.survey.struts2.action;

import com.sniper.survey.model.AdminUser;
import com.sun.org.apache.bcel.internal.util.ClassPath;

public class LoginAction extends BaseAction<AdminUser> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String index() {

		System.out.println(ClassPath.getClassPath());
		System.out.println("ssssssssss");
		return "index";
	}

	public String login() {
		
		return "login";
	}

	public void logout() {
		
	}
}