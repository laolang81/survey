package com.sniper.survey.struts2.action.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.sniper.survey.model.Survey;
import com.sniper.survey.service.impl.SurveyService;
import com.sniper.survey.util.DataUtil;

@Namespace("/admin/survey")
public class SurveyAaction extends BaseAction<Survey> {

	private static final long serialVersionUID = 1L;

	@Resource
	SurveyService surveyService;

	@Actions({ @Action(value = "index") })
	public String index() {

		
		
		
		super.sniperUrl = "/syrvey/delete";
		Map<Boolean, String> menu = new HashMap<>();
		menu.put(false, "否");
		menu.put(true, "是");
		sniperMenu.put("LOCKED", menu);
		
		surveyService.setOrder("id desc");
		surveyService.pageList(getListRow());
		pageHtml = surveyService.getPageHtml();
		list = surveyService.getLists();

		return SUCCESS;
	}

	@Action(value = "save", results = {
			@Result(name = "input", location = "save.jsp"),
			@Result(name = "success", location = "save.jsp") })
	public String save() {
		if (getMethod().equalsIgnoreCase("post")) {
			surveyService.saveOrUpdateEntiry(model);
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
			this.model = surveyService.getEntity(this.model.getId());
		}

		if (getMethod().equalsIgnoreCase("post")) {

			surveyService.saveOrUpdateEntiry(model);
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
			if (surveyService.deleteBatchEntityById(delid)) {
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} else {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", "删除失败");
			}
			break;
		case "LOCKED":
			try {
				surveyService.batchFiledChange("locked",
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
