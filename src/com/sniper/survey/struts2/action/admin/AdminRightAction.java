package com.sniper.survey.struts2.action.admin;

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
	 * sniper_menu菜单
	 * 
	 */
	/**
	 * 操作类型
	 */
	private String menuType;
	private Integer menuValue;

	private Map<String, Map<Boolean, String>> sniperMenu = new HashMap<>();
	{

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
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuValue(Integer menuValue) {
		this.menuValue = menuValue;
	}

	public Integer getMenuValue() {
		return menuValue;
	}

	public Map<String, Map<Boolean, String>> getSniperMenu() {
		return sniperMenu;
	}

	/**
	 * 读取记录数
	 */
	private List<AdminRight> list;
	private Integer[] delid;
	/**
	 * ajax返回列表
	 */
	private Map<String, List<AdminRight>> result = new HashMap<>();

	@Resource
	private AdminRightService adminRightService;

	public List<AdminRight> getList() {
		return list;
	}

	public void setDelid(Integer[] delid) {
		this.delid = delid;
	}

	public Integer[] getDelid() {
		return delid;
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
		list = (List<AdminRight>) map.get("rows");
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
		setWebPageTitle("权限添加");
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
			@Result(name = "success", location = "save.jsp"),
			@Result(name = "input", location = "save.jsp") })
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
	@Action(value = "delete", results = { @Result(name = "success", type = "json", params = {
			"root", "ajaxResult" }) })
	@SkipValidation
	public String delete() {
		ajaxResult.put("code", 1);
		ajaxResult.put("msg", "error");
		if (!getMethod().equals("POST")) {
			return SUCCESS;
		}

		if (getMenuType() == null || getMenuValue() == null) {
			return SUCCESS;
		}

		switch (menuType) {
		case "delete":
			if (adminRightService.deleteAdminRight(delid)) {
				ajaxResult.put("code", 0);
				ajaxResult.put("msg", "success");
			} else {
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "删除失败");
			}
			break;
		case "IsShow":
			String where = "UPDATE AdminRight SET theShow=? WHERE id in(?)";
			adminRightService.batchEntiryByHQL(where, getMenuValue(), StringUtils.join(delid, ","));
			break;
		case "IsPublic":
			System.out.println("IsPublic");
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
