package com.sniper.survey.struts2.action.admin;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.sniper.survey.model.AdminUser;

@Namespace("/admin")
public class AdminAction extends BaseAction<AdminUser> {

	private static final long serialVersionUID = 2859201181567637434L;

	/**
	 * 
	 * @return
	 */
	@Actions(value = {
			@Action(value = "/admin", results = { @Result(name = "success", location = "index/index.jsp") }),
			@Action(value = "index", results = { @Result(name = "success", location = "index/index.jsp") }) })
	public String index() {

		System.out.println("admin-index");
		return SUCCESS;

	}

}
