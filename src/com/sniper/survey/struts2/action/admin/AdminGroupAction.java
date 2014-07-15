package com.sniper.survey.struts2.action.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

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

	private static final long serialVersionUID = 1L;

	@Resource
	AdminGroupService adminGroupService;

	// 用户组所拥有的权限列表
	private List<AdminRight> adminRights = new ArrayList<>();

	// 用户提交的权限列表,或者被选中的值的集合
	private Integer[] fromRight;
	// 被选中的答案
	private List<Integer> valueFromRights = new ArrayList<>();

	public List<AdminRight> getAdminRights() {
		if (adminRights.size() == 0) {
			adminRights = adminRightService.getAdminRightList();
		}
		return adminRights;
	}

	public void setFromRight(Integer[] fromRight) {
		this.fromRight = fromRight;
	}

	public Integer[] getFromRight() {
		return fromRight;
	}

	public List<Integer> getValueFromRights() {
		return valueFromRights;
	}

	@Actions({ @Action(value = "index") })
	public String list() {

		

		adminGroupService.setOrder("id desc");
		adminGroupService.pageList(getListRow());
		pageHtml = adminGroupService.getPageHtml();
		list = adminGroupService.getLists();

		return SUCCESS;
	}

	@Action(value = "save", results = {
			@Result(name = "success", location = "save.jsp"),
			@Result(name = "input", location = "save.jsp") })
	public String save() {

		if (getMethod().equalsIgnoreCase("post")) {

			if (fromRight.length > 0) {
				List<AdminRight> ags = new ArrayList<>();
				ags = adminRightService.getAdminRightList(fromRight);
				Set<AdminRight> set = new HashSet<>(ags);
				this.model.setAdminRight(set);
			}

			adminGroupService.saveOrUpdateEntiry(this.model);
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

		// 被选中的值,被选中的权限列表
		List<AdminRight> ags = new ArrayList<>();

		if (getMethod().equalsIgnoreCase("post")) {
			// 获取所有被选中的权限
			if (fromRight.length > 0) {
				ags = adminRightService.getAdminRightList(fromRight);
				Set<AdminRight> set = new HashSet<>(ags);
				this.model.getAdminRight().clear();
				this.model.setAdminRight(set);
			}

			adminGroupService.saveOrUpdateEntiry(this.model);

		}
		if (getMethod().equalsIgnoreCase("get")) {
			this.model = adminGroupService.getEntity(this.model.getId());
			ags = new ArrayList<>(this.model.getAdminRight());

		}

		// 未表单设置选中答案
		for (AdminRight ag : ags) {
			valueFromRights.add(ag.getId());
		}

		return SUCCESS;
	}

	@Action(value = "delete", results = { @Result(name = "success", type = "json", params = {
			"root", "ajaxResult" }) })
	@SkipValidation
	@Override
	public String delete() {
		super.delete();

		switch (menuType) {
		case "delete":
			if (adminRightService.deleteBatchEntityById(delid)) {
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} else {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", "删除失败");
			}
			break;
		case "IsShow":

			try {
				adminGroupService.batchFiledChange("theShow", getMenuValue(),
						delid);
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} catch (Exception e) {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", e.getMessage());
			}

			break;
		case "IsPublic":

			try {
				adminGroupService.batchFiledChange("thePublic", getMenuValue(),
						delid);
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} catch (Exception e) {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", e.getMessage());
			}

			break;
		case "IsMenu":

			try {
				adminGroupService.batchFiledChange("theMenu", getMenuValue(),
						delid);
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
