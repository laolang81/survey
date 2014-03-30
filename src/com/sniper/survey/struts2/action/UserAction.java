package com.sniper.survey.struts2.action;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminUser;

//加注解
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<AdminUser> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//跳过校验
	@SkipValidation
	public String add()
	{
		return null;
		
	}
	public String save()
	{
		return null;
		
	}
	
	public String edit()
	{
		return null;
		
	}
	public String update()
	{
		return null;
		
	}
	
	public String delete()
	{
		return null;
		
	}
	
	
	@Override
	public void validate() {
		//非空
		
		
		if(hasErrors()){
			return ;
		}
	}

	
	
	

}
