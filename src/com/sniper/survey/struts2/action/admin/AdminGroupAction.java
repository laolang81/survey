package com.sniper.survey.struts2.action.admin;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminGroup;

@Controller
@Scope("prototype")
public class AdminGroupAction extends BaseAction<AdminGroup> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String list()
	{
		return SUCCESS;
	}
}
