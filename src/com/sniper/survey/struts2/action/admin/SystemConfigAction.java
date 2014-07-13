package com.sniper.survey.struts2.action.admin;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.SystemConfig;

@Controller
@Scope("prototype")
@Namespace("/admin/system-config")
@ParentPackage("default")
public class SystemConfigAction extends BaseAction<SystemConfig> {

	private static final long serialVersionUID = 1L;

	@Action("index")
	public String index() {
		return SUCCESS;
	}

	@Action(value="save",results= {@Result(name="input",location="save.jsp")})
	public String save() {
		return SUCCESS;
	}
	@Action(value="update",results= {@Result(name="input",location="save.jsp")})
	public String update() {
		return SUCCESS;
	}
	
	@Action(value = "delete", results = { @Result(name = "success", type = "json", params = {
			"root", "ajaxResult" }) })
	@SkipValidation
	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return super.delete();
	}
}
