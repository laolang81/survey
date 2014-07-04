package com.sniper.survey.struts2.action.admin;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminUser;

@Controller
@Scope("prototype")
@Namespace("/admin")
@ParentPackage("default")

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
