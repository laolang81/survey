package com.sniper.survey.struts2.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminUser;

//加注解
@Controller
@Scope("prototype")
public class IndexAction extends BaseAction<AdminUser> {
	
	private static final long serialVersionUID = 1L;


	@Override
	public String execute() throws Exception {
		
		return SUCCESS;
	}

}