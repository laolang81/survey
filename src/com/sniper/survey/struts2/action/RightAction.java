package com.sniper.survey.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import com.sniper.survey.model.AdminRight;
import com.sniper.survey.service.impl.AdminRightService;

public class RightAction extends BaseAction<AdminRight>{

	
	private static final long serialVersionUID = 1L;

	private List<AdminRight> allRight;
	
	@Resource
	private AdminRightService adminRightService;

	public List<AdminRight> getAllRight() {
		return allRight;
	}

	public void setAllRight(List<AdminRight> allRight) {
		this.allRight = allRight;
	}
	
	public String list()
	{
		return SUCCESS;
	}
	
}
