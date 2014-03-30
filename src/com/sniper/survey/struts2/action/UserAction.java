package com.sniper.survey.struts2.action;

import com.sniper.survey.model.AdminUser;

public class UserAction extends BaseAction<AdminUser> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4349061401310612390L;
	private AdminUser model;

	
	
	@Override
	public AdminUser getModel() {
		return model;

	}

}
