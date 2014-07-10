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

@Controller
@Scope("prototype")
@Namespace("/admin/admin-right")
@ParentPackage("default")
public class AdminRightAction extends BaseAction<AdminRight> {

	private static final long serialVersionUID = 2799348891231755561L;

	/**
	 * 读取记录数
	 */
	private List<AdminRight> allRight;
	/**
	 * ajax返回列表
	 */
	private Map<String, List<AdminRight>> result = new HashMap<>();

	@Resource
	private AdminRightService adminRightService;

	public List<AdminRight> getAllRight() {
		return allRight;
	}

	@JSON(serialize = false)
	public Map<String, List<AdminRight>> getResult() {
		return result;
	}

	@SuppressWarnings("unchecked")
	@Actions({ @Action(value = "index") })
	@SkipValidation
	public String index() {
		Map<String, Object> map = new HashMap<>();
		adminRightService.setOrder("id desc");
		map = adminRightService.pageList(getListRow());
		pageHtml = (String) map.get("pageHtml");
		allRight = (List<AdminRight>) map.get("rows");
		return SUCCESS;
	}

	/**
	 * 返回ajax获取的数据
	 * 
	 * @return
	 */
	@Action(value = "ajaxlist", results = { @Result(name = "success", type = "json") })
	public String ajaxList() {
		this.allRight = adminRightService.findAllEntitles();
		result.put("aaData", allRight);
		return SUCCESS;
	}

	@Action(value = "save", results = {
			@Result(name = "success", location = "save.jsp"),
			@Result(name = "input", location = "save.jsp") })
	public String save() {
		setWebPageTitle("权限添加");
		System.out.println(getMethod());
		if (getMethod().equals("POST")) {
			adminRightService.saveOrUpdate(model);
		}
		return SUCCESS;
	}

	@Action(value = "saveData", results = {
			@Result(name = "input", location = "save.jsp"),
			@Result(name = "success", location = "save", type = "redirectAction") })
	public String saveData() {
		if (getMethod().equals("POST")) {
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
			@Result(name = "success", location = "save.jsp", params = { "id",
					"${id}" }),
			@Result(name = "input", location = "save.jsp", params = { "id",
					"${id}" }) })
	@SkipValidation
	public String update() {
		if (null == model.getId()) {
			return ERROR;
		}
		if (getMethod().equals("GET")) {
			this.model = adminRightService.getEntity(this.model.getId());
		}

		if (getMethod().equals("POST")) {
			adminRightService.saveOrUpdate(model);
		}
		return SUCCESS;
	}

	// 更新数据
	@Action(value = "updateData", results = {
			@Result(name = "input", location = "save.jsp", params = { "id",
					"${id}" }),
			@Result(name = "edit", location = "update", type = "redirectAction", params = {
					"id", "${id}" }),
			@Result(name = "success", location = "update", type = "redirectAction", params = {
					"id", "${id}" }), })
	public String updateData() {
		if (getMethod().equals("POST")) {
			adminRightService.saveOrUpdate(model);
		}
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@Action(value = "delete", results = {
			@Result(name = "input", location = "list", type = "redirectAction"),
			@Result(name = "success", location = "list", type = "redirectAction") })
	@SkipValidation
	public String delete() {
		AdminRight right = new AdminRight();
		if (model.getId() == 0) {
			return INPUT;
		}
		right.setId(this.model.getId());
		adminRightService.deleteEntiry(right);
		return SUCCESS;
	}

}
