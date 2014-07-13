package com.sniper.survey.struts2.action.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminGroup;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.service.impl.AdminGroupService;

@Controller
@Scope("prototype")
@Namespace("/admin/admin-user")
@ParentPackage("default")
public class AdminUserAction extends BaseAction<AdminUser> {

	private static final long serialVersionUID = 1L;

	@Resource
	AdminGroupService adminGroupService;

	private String password_old;
	private String password_c;
	// 用户组列表
	private List<AdminGroup> adminGroups = new ArrayList<>();

	// 结果列表
	private Integer[] fromGroups;

	private List<Integer> valueFromGroups = new ArrayList<>();

	public List<AdminGroup> getAdminGroups() {
		if (adminGroups.size() == 0) {
			adminGroups = adminGroupService.getGroupSelectList(null);
		}

		return adminGroups;
	}

	public Integer[] getFromGroups() {
		return fromGroups;
	}

	public void setFromGroups(Integer[] fromGroups) {
		this.fromGroups = fromGroups;
	}

	public List<Integer> getValueFromGroups() {
		return valueFromGroups;
	}

	public String getPassword_old() {
		return password_old;
	}

	public void setPassword_old(String password_old) {
		this.password_old = password_old;
	}

	public void setPassword_c(String password_c) {
		this.password_c = password_c;
	}

	public String getPassword_c() {
		return password_c;
	}

	@SuppressWarnings("unchecked")
	@Actions({ @Action(value = "index") })
	@SkipValidation
	public String index() {
		
		super.sniperUrl = "/amdin/amdin-user/delete";

		Map<Boolean, String> menu = new HashMap<>();
		menu.put(false, "否");
		menu.put(true, "是");
		sniperMenu.put("enables", menu);

		Map<Boolean, String> ispublic = new HashMap<>();
		ispublic.put(false, "否");
		ispublic.put(true, "是");
		sniperMenu.put("locked", ispublic);

		Map<String, Object> map = new HashMap<>();
		adminUserService.setOrder("id desc");
		map = adminUserService.pageList(getListRow());
		pageHtml = (String) map.get("pageHtml");
		list = (List<AdminUser>) map.get("rows");
		return SUCCESS;
	}

	@Action(value = "save", results = {
			@Result(name = "success", location = "save.jsp"),
			@Result(name = "input", location = "save.jsp") })
	public String save() {
		if (getMethod().equalsIgnoreCase("post")) {
			adminUserService.saveOrUpdateEntiry(model);
		}
		return SUCCESS;
	}

	@Action(value = "saveData", results = {
			@Result(name = "input", location = "save.jsp"),
			@Result(name = "success", location = "save", type = "redirectAction") })
	public String saveData() {
		if (getMethod().equalsIgnoreCase("post")) {
			adminUserService.saveOrUpdateEntiry(model);
		}
		return SUCCESS;
	}

	@Action(value = "update", results = {
			@Result(name = "success", location = "save.jsp"),
			@Result(name = "input", location = "save.jsp") })
	@SkipValidation
	public String update() {

		if (null == this.model.getId()) {
			return ERROR;
		}

		List<AdminGroup> ags = new ArrayList<>();

		if (getMethod().equalsIgnoreCase("post")) {

			if (!password_c.isEmpty()) {
				model.setPassword(password_c);
			}

			ags = adminGroupService.getGroupList(fromGroups);
			this.model.getAdminGroup().clear();
			this.model.setAdminGroup(ags);
			adminUserService.saveOrUpdateEntiry(this.model);

		}
		if (getMethod().equalsIgnoreCase("get")) {
			this.model = adminUserService.getEntity(this.model.getId());
		}

		ags = this.model.getAdminGroup();
		// 未表单设置选中答案
		for (AdminGroup ag : ags) {
			if (ag != null) {
				valueFromGroups.add(ag.getId());
			}
		}
		return SUCCESS;
	}

	@SkipValidation
	public void prepareDoUpdate() {
		System.out.println("prepareDoUpdate");

	}

	public String updateData() {
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@Action(value = "delete", results = { @Result(name = "success", type = "json", params = {
			"root", "ajaxResult" }) })
	@SkipValidation
	@Override
	public String delete() {
		// code 小于1表示有错误,大于0表示ok,==0表示未操作

		super.delete();

		String hql = "";

		switch (menuType) {
		case "delete":
			hql = "DELETE AdminUser WHERE id in("
					+ StringUtils.join(delid, ",") + ") ";

			try {
				adminUserService.batchEntiryByHQL(hql);
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} catch (Exception e) {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", "删除失败");
			}

			break;
		case "enables":
			hql = "UPDATE AdminUser SET enables=? WHERE id in("
					+ StringUtils.join(delid, ",") + ") ";
			try {
				adminUserService.batchEntiryByHQL(hql, getMenuValue());
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} catch (Exception e) {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", e.getMessage());
			}

			break;
		case "locked":
			hql = "UPDATE AdminUser SET locked=? WHERE id in("
					+ StringUtils.join(delid, ",") + ") ";
			try {
				adminUserService.batchEntiryByHQL(hql, getMenuValue());
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} catch (Exception e) {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", e.getMessage());
			}
			break;

		default:
			break;
		}

		return SUCCESS;

	}

}
