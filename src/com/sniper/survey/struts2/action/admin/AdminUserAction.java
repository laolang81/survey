package com.sniper.survey.struts2.action.admin;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminUser;
import com.sniper.survey.service.impl.AdminUserService;

@Controller
@Scope("prototype")
@Namespace("/admin/admin-user")
@ParentPackage("default")
public class AdminUserAction extends BaseAction<AdminUser> {

	private static final long serialVersionUID = 1L;

	@Resource
	AdminUserService adminUserService;

	/**
	 * 读取记录数
	 */
	private List<AdminUser> list;

	/**
	 * @return the meetUsers
	 */
	public List<AdminUser> getList() {
		return list;
	}

	@Actions({ @Action(value = "index") })
	@SkipValidation
	public String index() {
		setWebPageTitle("调查人员列表");
		this.list = adminUserService.adminList(getListRow());
		this.pageHtml = adminUserService.getPageHtml();
		return SUCCESS;
	}

	/**
	 * 用户列表导出
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "export") })
	public String export() {
		return SUCCESS;
	}

	@Action(value = "save", results = {
			@Result(name = "success", location = "save.jsp"),
			@Result(name = "input", location = "save.jsp") })
	@SkipValidation
	public String save() {
		System.out.println(getMethod());
		if (getMethod().equals("post")) {
			System.out.println("save");
			adminUserService.saveOrUpdateEntiry(model);
		}
		return SUCCESS;
	}

	@Action(value = "saveData", results = {
			@Result(name = "input", location = "save.jsp"),
			@Result(name = "success", location = "save", type = "redirectAction") })
	public String saveData() {
		if (getMethod().equals("POST")) {
			adminUserService.saveOrUpdateEntiry(model);
		}
		return SUCCESS;
	}

	public String update() {
		if (null == model.getId()) {
			return ERROR;
		}
		return SUCCESS;
	}

	public String updateData() {
		return SUCCESS;
	}

	public String delete() {
		return SUCCESS;
	}

}
