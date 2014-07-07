package com.sniper.survey.struts2.action.web;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.MeetUser;


@Controller()
@Scope("prototype")

@Namespace("/")
@ParentPackage("convention-default")

@ResultPath("/WEB-INF/content/web")
public class IndexAction extends BaseAction<MeetUser>{


	private static final long serialVersionUID = 1L;

	
	
	@Actions(value = {
			@Action(""), 
			@Action("/")
			
	})
	
	
	public String index()
	{
		System.out.println("web-index");
		
		return SUCCESS;
		
	}
	
}
