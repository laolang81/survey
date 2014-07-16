package com.sniper.survey.struts2.action.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminGroup;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.service.impl.AdminGroupService;
import com.sniper.survey.util.DataUtil;

@Controller
@Scope("prototype")
@Namespace("/admin/admin-user")
@ParentPackage("default")
public class AdminUserAction extends BaseAction<AdminUser> {

	private static final long serialVersionUID = 1L;

	@Resource
	AdminGroupService adminGroupService;

	private String password_new;
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

	public void setPassword_new(String password_new) {
		this.password_new = password_new;
	}

	public String getPassword_new() {
		return password_new;
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

	@Actions({ @Action(value = "index") })
	public String index() {

		super.sniperUrl = "/admin-user/delete";

		Map<Boolean, String> menu = new HashMap<>();
		menu.put(false, "否");
		menu.put(true, "是");
		sniperMenu.put("enabled", menu);

		Map<Boolean, String> ispublic = new HashMap<>();
		ispublic.put(false, "否");
		ispublic.put(true, "是");
		sniperMenu.put("locked", ispublic);

		adminUserService.setOrder("id desc");
		adminUserService.pageList(getListRow());
		pageHtml = adminUserService.getPageHtml();
		list = adminUserService.getLists();

		return SUCCESS;
	}

	@Action(value = "save")
	public String save() {
		if (getMethod().equalsIgnoreCase("post")) {
			List<AdminGroup> ags = new ArrayList<>();
			if (password_c != null) {
				model.setPassword(password_c);
			}

			ags = adminGroupService.getGroupList(fromGroups);
			this.model.setAdminGroup(ags);
			adminUserService.saveOrUpdateEntiry(this.model);
		}
		return SUCCESS;
	}

	@Action(value = "update", results = {
			@Result(name = "success", location = "save.jsp"),
			@Result(name = "input", location = "save-input.jsp") })
	public String update() {

		if (null == this.model.getId()) {
			return ERROR;
		}

		List<AdminGroup> ags = new ArrayList<>();

		if (getMethod().equalsIgnoreCase("post")) {

			if (null != password_c) {
				model.setPassword(password_c);
			}
			if (fromGroups.length > 0) {
				ags = adminGroupService.getGroupList(fromGroups);
				this.model.getAdminGroup().clear();
				this.model.setAdminGroup(ags);
			}

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

	@Action(value = "changepassword", results = {
			@Result(name = "success", location = "change-password.jsp"),
			@Result(name = "input", location = "change-password.jsp") })
	public String changepassword() {

		Integer uid = UserID();
		if (0 == uid) {
			return ERROR;
		}

		if (getMethod().equalsIgnoreCase("post")) {

			Map<String, List<String>> errorMap = getFieldErrors();
			List<String> list = new ArrayList<>();

			if (password_old == null) {
				list.add("旧密码不得为空");
				errorMap.put("password_old", list);
				setFieldErrors(errorMap);
				return INPUT;
			}

			if (password_new == null || password_new.length() < 6) {
				list.add("新密码必须6位以上");
				errorMap.put("password_new", list);
				setFieldErrors(errorMap);
				return INPUT;
			}

			if (password_c == null || password_c != password_new) {
				list.add("两次密码输入不一致");
				errorMap.put("password_c", list);
				setFieldErrors(errorMap);
				return INPUT;
			}

			// 检查密码
			if (adminUserService.validateByPassword(password_old)) {
				model.setPassword(password_c);
			} else {
				list.add("旧密码不对");
				errorMap.put("password_old", list);
				return INPUT;
			}

			adminUserService.saveOrUpdateEntiry(this.model);

		}
		if (getMethod().equalsIgnoreCase("get")) {
			this.model = adminUserService.getEntity(uid);
		}

		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@Action(value = "delete", results = { @Result(name = "success", type = "json", params = {
			"root", "ajaxResult" }) })
	@Override
	public String delete() {
		// code 小于1表示有错误,大于0表示ok,==0表示未操作

		super.delete();

		switch (menuType) {
		case "delete":

			try {
				adminUserService.deleteBatchEntityById(delid);
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} catch (Exception e) {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", "删除失败");
			}

			break;
		case "enabled":

			try {
				adminUserService.batchFiledChange("enables",
						DataUtil.stringToBoolean(getMenuValue()), delid);
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} catch (Exception e) {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", e.getMessage());
			}

			break;
		case "locked":
			try {
				adminUserService.batchFiledChange("locked",
						DataUtil.stringToBoolean(getMenuValue()), delid);
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
