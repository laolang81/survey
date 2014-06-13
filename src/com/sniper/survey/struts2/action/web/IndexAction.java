package com.sniper.survey.struts2.action.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller()
@Scope("prototype")
public class IndexAction extends BaseAction{


	private static final long serialVersionUID = 1L;

	
	@Override
	public String execute() throws Exception {
		
		System.out.println("web-index--------");
		return SUCCESS;
	}
	
}
