package com.sniper.survey.struts2.action.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.sniper.survey.model.SystemConfig;
import com.sniper.survey.service.impl.SystemConfigService;
import com.sniper.survey.util.DataUtil;

@Namespace("/admin/admin-config")
public class AdminConfigAction extends BaseAction<SystemConfig> {

	private static final long serialVersionUID = 1L;

	@Resource
	SystemConfigService configService;

	@Action("index")
	@SkipValidation
	public String index() {

		super.sniperUrl = "/admin-config/delete";

		Map<Boolean, String> menu = new HashMap<>();
		menu.put(false, "否");
		menu.put(true, "是");
		sniperMenu.put("AutoLoad", menu);

		configService.setOrder("id desc");
		configService.pageList(getListRow());
		pageHtml = configService.getPageHtml();
		list = configService.getLists();
		return SUCCESS;
	}

	@Action(value = "save", results = {
			@Result(name = "success", location = "save.jsp"),
			@Result(name = "input", location = "save.jsp") })
	public String save() {
		if (getMethod().equalsIgnoreCase("post")) {
			configService.saveOrUpdateEntiry(model);
			// 每次修改配置或者删除配置都要更新网站配置
			updateSystemConfig();
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
			configService.saveOrUpdateEntiry(model);
			updateSystemConfig();
		}

		if (getMethod().equalsIgnoreCase("get")) {
			this.model = configService.getEntity(this.model.getId());
		}
		return SUCCESS;
	}

	@Action(value = "delete", results = { @Result(name = "success", type = "json", params = {
			"root", "ajaxResult" }) })
	@SkipValidation
	public String delete() {

		switch (menuType) {
		case "delete":
			if (configService.deleteBatchEntityById(delid)) {
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} else {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", "删除失败");
			}
			break;
		case "AutoLoad":

			try {
				configService.batchFiledChange("autoload",
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

	/**
	 * delete公共调用的类
	 * 
	 * @return
	 */
	public void prepareDoDelete() {
		super.ajaxResultDelete();
	}

	/**
	 * 更新网站配置
	 */
	private void updateSystemConfig() {

		getSystemConfig().clear();

	}

}
