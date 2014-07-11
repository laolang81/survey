package com.sniper.survey.struts2.action.admin;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.sniper.survey.service.impl.AdminUserService;

@Controller
@Scope("prototype")
@Namespace("/admin/admin-user")
@ParentPackage("default")
public class AdminUserAction extends BaseAction<AdminUser> {

	private static final long serialVersionUID = 1L;

	@Resource
	AdminGroupService adminGroupService;

	@Resource
	AdminUserService adminUserService;

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
		setWebPageTitle("管理人员列表");
		System.out.println("index");
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
		if (getMethod().equals("POST")) {
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

	@Action(value = "update", results = {
			@Result(name = "success", location = "save.jsp"),
			@Result(name = "input", location = "save.jsp") })
	@SkipValidation
	public String update() {

		if (null == model.getId()) {
			return ERROR;
		}

		this.model = adminUserService.getEntity(model.getId());
		List<AdminGroup> ags = model.getAdminGroup();
		// 未表单设置选中答案\
		for (AdminGroup ag : ags) {
			if (ag != null) {
				valueFromGroups.add(ag.getId());
			}
		}
		
		if (getMethod().equals("POST")) {
			model.getAdminGroup().clear();
			System.out.println(Arrays.asList(fromGroups));
			ags = adminGroupService.getGroupList(fromGroups);
			model.setAdminGroup(ags);
			if(!StringUtils.isEmpty(password_c)){
				model.setPassword(password_c);
			}
			System.out.println(model.getPassword());
			adminUserService.saveOrUpdateEntiry(model);
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
	public String delete() {
		// code 小于1表示有错误,大于0表示ok,==0表示未操作

		ajaxResult.put("code", 0);
		ajaxResult.put("msg", "非有效请求");
		if (!getMethod().equals("POST") || !isXMLHttpRequest()) {
			return SUCCESS;
		}
		ajaxResult.put("msg", "参数不全");
		if (getMenuType() == null || getMenuValue() == null) {
			return SUCCESS;
		}
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
		case "IsMenu":
			System.out.println("IsMenu");
			break;

		default:
			break;
		}

		return SUCCESS;

	}

}
