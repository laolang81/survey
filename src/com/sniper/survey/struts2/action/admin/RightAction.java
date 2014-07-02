package com.sniper.survey.struts2.action.admin;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import com.sniper.survey.model.AdminRight;
import com.sniper.survey.service.impl.AdminRightService;

@Controller
@Scope("prototype")
@Namespace("/admin/right")
@ParentPackage("default")
// @InterceptorRef("loginInterceptor")
@Results({
		@Result(name = "error", location = "%{htmlPath}/error/error.jsp"),
		@Result(name = "login", location = "login", params = { "namespace",
				"/admin" }, type = "redirectAction") })
public class RightAction extends BaseAction<AdminRight> {

	private static final long serialVersionUID = 2799348891231755561L;

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

	public void setAllRight(List<AdminRight> allRight) {
		this.allRight = allRight;
	}

	@JSON(serialize = false)
	public Map<String, List<AdminRight>> getResult() {
		return result;
	}

	public void setResult(Map<String, List<AdminRight>> result) {
		this.result = result;
	}

	@Action(value = "", results = { @Result(name = "success", location = "list.jsp") })
	public String index() {
		String hql = "from AdminRight";
		this.allRight = adminRightService.page(hql, 0, 200);
		return SUCCESS;
	}

	/**
	 * 数据列表
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "list.ftl", type = "freemarker") })
	@SkipValidation
	public String list() {
		// 如果想在程序中获得当前登陆用户对应的对象。
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		System.out.println(userDetails);
		// 如果想获得当前登陆用户所拥有的所有权限。
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) userDetails
				.getAuthorities();
		;

		System.out.println(authorities);

		String hql = "from AdminRight order by sort desc";
		this.allRight = adminRightService.page(hql, 0, 200);
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
			@Result(name = "success", location = "save.ftl", type = "freemarker"),
			@Result(name = "input", location = "save.ftl", type = "freemarker") })
	@Validations(requiredStrings = {
			@RequiredStringValidator(type = ValidatorType.SIMPLE, trim = true, fieldName = "name", message = "请输入名称"),
			@RequiredStringValidator(type = ValidatorType.SIMPLE, trim = true, fieldName = "url", message = "请输入资源地址") })
	public String save() {
		System.out.println("save");
		if (getMethod().equals("POST")) {
			adminRightService.saveEntiry(model);
		}
		return SUCCESS;
	}

	@Action(value = "savedata")
	public String saveData() {
		return SUCCESS;
	}

	/**
	 * 更新操作 redirectAction
	 * 
	 * @return
	 */
	@Action(value = "saveorupdate", results = {
			@Result(name = "add", location = "save", type = "redirectAction"),
			@Result(name = "edit", location = "update", type = "redirectAction", params = {
					"id", "${id}" }),
			@Result(name = "input", location = "save", type = "redirectAction") })
	public String saveOrUpdate() {

		// spring多库分布实例
		// 绑定token到当前线程
		/*
		 * RightToken token = new RightToken(); token.setRight(getModel()); //
		 * 绑定令牌 RightToken.bindToken(token);
		 */
		adminRightService.saveOrUpdate(model);
		if (model.getId() == 0) {
			return "add";
		}
		return "edit";
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
		System.out.println("update");
		this.model = adminRightService.getEntity(this.model.getId());
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "save.jsp") })
	@SkipValidation
	public String delete() {
		AdminRight right = new AdminRight();
		right.setId(getUpdateid());
		adminRightService.deleteEntiry(right);
		return "list";
	}

}
