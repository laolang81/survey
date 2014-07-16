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
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminRight;
import com.sniper.survey.service.impl.AdminRightService;
import com.sniper.survey.util.DataUtil;

@Controller
@Scope("prototype")
@Namespace("/admin/admin-right")
@ParentPackage("default")
public class AdminRightAction extends BaseAction<AdminRight> {

	private static final long serialVersionUID = 2799348891231755561L;

	/**
	 * ajax返回列表
	 */
	private Map<String, List<AdminRight>> result = new HashMap<>();

	// 顶级right,
	private Map<Integer, String> adminRights = new HashMap<>();

	public Map<Integer, String> getAdminRights() {
		if (adminRights.isEmpty()) {
			adminRights.put(0, "顶级");
			List<AdminRight> list = adminRightService.getAdminRightMenuList();
			for (AdminRight right : list) {
				adminRights.put(right.getId(), right.getName());
			}
		}
		return adminRights;
	}

	@Resource
	private AdminRightService adminRightService;

	@JSON(serialize = false)
	public Map<String, List<AdminRight>> getResult() {
		return result;
	}

	@Actions({ @Action(value = "index") })
	@SkipValidation
	public String index() {

		super.sniperUrl = "/admin-right/delete";

		Map<Boolean, String> menu = new HashMap<>();
		menu.put(false, "否");
		menu.put(true, "是");
		sniperMenu.put("IsMenu", menu);

		Map<Boolean, String> ispublic = new HashMap<>();
		ispublic.put(false, "否");
		ispublic.put(true, "是");
		sniperMenu.put("IsPublic", ispublic);

		Map<Boolean, String> show = new HashMap<>();
		show.put(false, "否");
		show.put(true, "是");
		sniperMenu.put("IsShow", show);

		adminRightService.setOrder("id desc");
		adminRightService.pageList(getListRow());
		pageHtml = adminRightService.getPageHtml();
		list = adminRightService.getLists();
		return SUCCESS;
	}

	/**
	 * 返回ajax获取的数据
	 * 
	 * @return
	 */
	@Action(value = "ajaxlist", results = { @Result(name = "success", type = "json") })
	public String ajaxList() {
		this.list = adminRightService.findAllEntitles();
		result.put("aaData", list);
		return SUCCESS;
	}

	@Action(value = "save", results = {
			@Result(name = "success", location = "save.jsp"),
			@Result(name = "input", location = "save.jsp") })
	public String save() {
		if (getMethod().equalsIgnoreCase("post")) {
			adminRightService.saveOrUpdate(model);
		}

		return SUCCESS;
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	@Action(value = "update", results = {
			@Result(name = "success", location = "save.jsp"),
			@Result(name = "input", location = "save.jsp") })
	@SkipValidation
	public String update() {
		if (null == model.getId()) {
			return ERROR;
		}

		if (getMethod().equalsIgnoreCase("post")) {
			adminRightService.saveOrUpdate(model);
		}

		if (getMethod().equalsIgnoreCase("get")) {
			this.model = adminRightService.getEntity(this.model.getId());
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
				adminRightService.batchFiledChange("theShow",
						DataUtil.stringToBoolean(getMenuValue()), delid);
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} catch (Exception e) {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", e.getMessage());
			}

			break;
		case "IsPublic":

			try {
				adminRightService.batchFiledChange("thePublic",
						DataUtil.stringToBoolean(getMenuValue()), delid);
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} catch (Exception e) {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", e.getMessage());
			}

			break;
		case "IsMenu":
			try {
				adminRightService.batchFiledChange("theMenu",
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
