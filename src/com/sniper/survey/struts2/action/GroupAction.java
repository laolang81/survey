package com.sniper.survey.struts2.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminGroup;

@Controller
@Scope("prototype")
public class GroupAction extends BaseAction<AdminGroup> {

	public String index()
	{
		return "index";
	}
}
