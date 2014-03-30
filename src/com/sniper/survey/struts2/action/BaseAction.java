package com.sniper.survey.struts2.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T>, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public abstract T getModel();
	
}
