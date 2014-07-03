package com.sniper.survey.struts2.action.admin;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.MeetUser;

@Controller
@Scope("proptoty")
@ParentPackage("default")
public class MeetUserAction extends BaseAction<MeetUser> {

	
	private static final long serialVersionUID = 1L;

	public String list()
	{
		return SUCCESS;
	}
	
}
