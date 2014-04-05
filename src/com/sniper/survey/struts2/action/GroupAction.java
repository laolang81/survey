package com.sniper.survey.struts2.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminGroup;

@Controller
@Scope("prototype")
public class GroupAction extends BaseAction<AdminGroup> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String index()
	{
		return "index";
	}
}