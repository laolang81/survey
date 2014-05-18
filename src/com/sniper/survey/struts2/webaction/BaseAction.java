package com.sniper.survey.struts2.webaction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.sniper.survey.struts2.MethodAware;

@SuppressWarnings("rawtypes")
public abstract class BaseAction extends ActionSupport implements
		ModelDriven, Preparable, MethodAware {

	private static final long serialVersionUID = 1L;

	
	/**
	 * 获取用户提交的方式
	 */
	private String method;

	public BaseAction() {
		
	}

	@Override
	public void prepare() throws Exception {

	}

	/**
	 * 获取用户的提交方式 get post
	 */
	@Override
	public void setMethod(String method) {

		this.method = method;

	}

	public String getMethod() {
		return method;
	}

	

}
