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
import com.sniper.survey.model.AdminRight;
import com.sniper.survey.service.impl.AdminGroupService;

@Controller
@Scope("prototype")
@Namespace("/admin/admin-group")
@ParentPackage("default")
public class AdminGroupAction extends BaseAction<AdminGroup> {

	@Resource
	AdminGroupService adminGroupService;

	// 用户组所拥有的权限列表
	private List<AdminRight> adminRights = new ArrayList<>();

	// 用户提交的权限列表,或者被选中的值的集合
	private Integer[] fromRight;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Actions({ @Action(value = "index") })
	public String list() {

		super.sniperUrl = "/amdin-group/delete";
		
		Map<String, Object> map = new HashMap<>();
		adminGroupService.setOrder("id desc");
		map = adminGroupService.pageList(getListRow());
		pageHtml = (String) map.get("pageHtml");
		list = (List<AdminGroup>) map.get("rows");

		return SUCCESS;
	}

	@Action(value = "save", results = {
			@Result(name = "success", location = "save.jsp"),
			@Result(name = "input", location = "save.jsp") })
	public String save() {
		setWebPageTitle("权限添加");
		if (getMethod().equalsIgnoreCase("post")) {
			adminGroupService.saveOrUpdateEntiry(model);
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

		if (getMethod().equalsIgnoreCase("post")) {
			adminGroupService.saveOrUpdateEntiry(model);
		}

		if (getMethod().equalsIgnoreCase("get")) {
			this.model = adminGroupService.getEntity(this.model.getId());
		}
		return SUCCESS;
	}

	@Action(value = "delete", results = { @Result(name = "success", type = "json", params = {
			"root", "ajaxResult" }) })
	@SkipValidation
	@Override
	public String delete() {
		super.delete();
		String where = "";
		switch (menuType) {
		case "delete":
			if (adminRightService.deleteAdminRight(delid)) {
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} else {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", "删除失败");
			}
			break;
		case "IsShow":
			where = "UPDATE AdminRight SET theShow=? WHERE id in("
					+ StringUtils.join(delid, ",") + ") ";
			try {
				adminGroupService.batchEntiryByHQL(where, getMenuValue());
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} catch (Exception e) {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", e.getMessage());
			}

			break;
		case "IsPublic":
			where = "UPDATE AdminRight SET thePublic=? WHERE id in("
					+ StringUtils.join(delid, ",") + ") ";
			try {
				adminGroupService.batchEntiryByHQL(where, getMenuValue());
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} catch (Exception e) {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", e.getMessage());
			}

			break;
		case "IsMenu":
			where = "UPDATE AdminRight SET theMenu=? WHERE id in("
					+ StringUtils.join(delid, ",") + ") ";
			try {
				adminGroupService.batchEntiryByHQL(where, getMenuValue());
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
