package com.sniper.survey.struts2.action.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.config.MeetUserData;
import com.sniper.survey.model.MeetUser;
import com.sniper.survey.service.impl.MeetUserService;

@Controller
@Scope("prototype")
@Namespace("/admin/meet-user")
@ParentPackage("default")
public class MeetUserAction extends BaseAction<MeetUser> {

	private static final long serialVersionUID = 1L;

	private Map<Integer, String> sexs = new HashMap<>();
	{
		sexs = MeetUserData.getSex();
	}
	@Resource
	MeetUserService meetUserService;

	public Map<Integer, String> getSexs() {
		return sexs;
	}

	/**
	 * 读取记录数
	 */
	private List<MeetUser> meetUsers;

	/**
	 * @return the meetUsers
	 */
	public List<MeetUser> getMeetUsers() {
		return meetUsers;
	}

	@Actions({ @Action(value = "index") })
	@SkipValidation
	public String index() {

		super.sniperUrl = "/meet-user/delete";

		meetUserService.setOrder("id desc");
		meetUserService.pageList(getListRow());
		pageHtml = meetUserService.getPageHtml();
		list = meetUserService.getLists();

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
			@Result(name = "input", location = "save.jsp"),
			@Result(name = "success", location = "save.jsp") })
	public String save() {
		if (getMethod().equalsIgnoreCase("post")) {
			System.out.println("save");
			meetUserService.saveOrUpdateEntiry(model);
		}
		return SUCCESS;
	}

	@Action(value = "saveData", results = {
			@Result(name = "input", location = "save", type = "redirectAction"),
			@Result(name = "success", location = "save", type = "redirectAction") })
	public String saveData() {
		if (getMethod().equalsIgnoreCase("post")) {
			meetUserService.saveOrUpdateEntiry(model);
		}
		return SUCCESS;
	}

	@Action(value = "update", results = {
			@Result(name = "input", location = "save.jsp"),
			@Result(name = "success", location = "save.jsp") })
	@SkipValidation
	public String update() {

		if (model.getId() == 0) {
			return ERROR;
		}

		if (getMethod().equalsIgnoreCase("get")) {
			this.model = meetUserService.getEntity(this.model.getId());
		}

		if (getMethod().equals("POST")) {
			System.out.println(model.getLeaveTime());
			System.out.println(model.getReportTime());
			meetUserService.saveOrUpdateEntiry(model);
		}
		return SUCCESS;
	}

	@Action(value = "updateData", results = {
			@Result(name = "input", location = "update", type = "redirectAction", params = {
					"id", "${id}" }),
			@Result(name = "success", location = "update", type = "redirectAction", params = {
					"id", "${id}" }) })
	public String updateData() {
		if (getMethod().equalsIgnoreCase("post")) {
			meetUserService.saveOrUpdateEntiry(model);
		}
		return SUCCESS;
	}

	@Action(value = "delete", results = { @Result(name = "success", type = "json", params = {
			"root", "ajaxResult" }) })
	@SkipValidation
	@Override
	public String delete() {
		// code 小于1表示有错误,大于0表示ok,==0表示未操作

		super.delete();

		switch (menuType) {
		case "delete":

			break;
		case "IsShow":

			break;

		default:
			break;
		}

		return SUCCESS;
	}

}
