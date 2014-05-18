package com.sniper.survey.struts2.adminAction;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminUser;

//加注解
@Controller
@Scope("prototype")
public class AdminIndexAction extends BaseAction<AdminUser> {
	
	private static final long serialVersionUID = 4778199629985576472L;

	@Override
	public String execute() throws Exception {
		
		System.out.println("execute---------");
		return SUCCESS;
	}
	
	public String index()
	{
		System.out.println("index---------");
		return SUCCESS;
	}

}