package com.sniper.survey.struts2.action.admin;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.sniper.survey.model.LinkGroups;
import com.sniper.survey.service.impl.LinkGroupsService;
import com.sniper.survey.util.DataUtil;

@Namespace("/admin/link-groups")
public class LinkGroupsAction extends BaseAction<LinkGroups> {

	private static final long serialVersionUID = 1L;

	@Resource
	LinkGroupsService linkGroupsService;

	@Actions({ @Action(value = "index") })
	public String index() {

		super.sniperUrl = "/link-groups/delete";

		linkGroupsService.setOrder("id desc");
		linkGroupsService.pageList(getListRow());
		pageHtml = linkGroupsService.getPageHtml();
		list = linkGroupsService.getLists();

		return SUCCESS;
	}

	@Action(value = "save", results = {
			@Result(name = "input", location = "save.jsp"),
			@Result(name = "success", location = "save.jsp") })
	public String save() {
		if (getMethod().equalsIgnoreCase("post")) {
			linkGroupsService.saveOrUpdateEntiry(model);
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
			this.model = linkGroupsService.getEntity(this.model.getId());
		}

		if (getMethod().equalsIgnoreCase("post")) {

			linkGroupsService.saveOrUpdateEntiry(model);
		}
		return SUCCESS;
	}

	@Action(value = "delete", results = { @Result(name = "success", type = "json", params = {
			"root", "ajaxResult" }) })
	@SkipValidation
	public String delete() {

		//super.delete();

		switch (menuType) {
		case "delete":
			if (linkGroupsService.deleteBatchEntityById(delid)) {
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} else {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", "删除失败");
			}
			break;
		case "MoveType":
			try {
				linkGroupsService.batchFiledChange("showType",
						DataUtil.stringToInteger(getMenuValue()), delid);
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
