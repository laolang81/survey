package com.sniper.survey.struts2.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.custom.authentication.AuthenticateResultInfoInterface;
import com.sniper.survey.custom.authentication.AuthenticationService;
import com.sniper.survey.custom.authentication.AuthenticationServiceInterface;
import com.sniper.survey.custom.authentication.DbTable;
import com.sniper.survey.custom.authentication.Result;
import com.sniper.survey.model.AdminGroup;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.service.impl.AdminGroupService;
import com.sniper.survey.service.impl.AdminUserService;
import com.sniper.survey.struts2.interceptor.UserAware;
import com.sniper.survey.util.DataUtil;
import com.sniper.survey.util.ValidateUtil;

//加注解
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<AdminUser> implements SessionAware, UserAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private AdminUserService adminUserService;

	@Resource
	private AdminGroupService adminGroupService;
	// 用户组列表
	private List<AdminGroup> adminGroupsSelect;
	
	private Map<String, Object> sessionMap;
	// 登录用户信息
	private AdminUser user;

	public Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// 验证用户密码
	private String confirmPassword;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<AdminGroup> getAdminGroupsSelect() {
		return adminGroupsSelect;
	}

	public void setAdminGroupsSelect(List<AdminGroup> adminGroupsSelect) {
		this.adminGroupsSelect = adminGroupsSelect;
	}
	/**
	 * 用户列表
	 * 
	 * @return
	 */
	public String list() {

		return SUCCESS;
	}
	/**
	 * 添加
	 * 
	 * @return
	 */
	@SkipValidation
	public String doAdd() {
		
		System.out.println("1111111111");
		System.out.println(this.sessionMap.get("user"));
		System.out.println("222222222");
		System.out.println(this.user);
		// 添加完毕之后自动定向到编辑页面
		this.id = model.getId();
		// 要保持关联
		// model.setAdminGroup(adminGroup);
		return INPUT;
	}

	/**
	 * 默认prepare拦截器先调用do开头的
	 */
	public void prepareDoAdd() {
		// 设置用户组
		setAdminGroupsSelect(adminGroupService.getGroupSelectList());

	}

	/**
	 * 不同方法而已
	 * 
	 * @return
	 */
	public String prepareAdd() {

		return SUCCESS;
	}

	public String save() {
		return null;

	}

	public String edit() {
		return null;

	}

	public String update() {
		return null;

	}

	public String delete() {
		return null;

	}

	@Override
	public void validate() {
		// 非空
		if (!ValidateUtil.isValid(model.getName())) {
			addFieldError("name", "用户名必填项");
		}

		if (!ValidateUtil.isValid(model.getNickName())) {
			addFieldError("nickName", "昵称必填项");
		}

		if (!ValidateUtil.isValid(model.getEmail())) {
			addFieldError("email", "用户Email必填项");
		}
		// 当用户密码存在时候
		// 当用户添加时密码为必须
		// 当修改时密码为非必须

		if (ValidateUtil.isValid(model.getPassword())) {
			if (model.getPassword().equals(confirmPassword)) {
				addFieldError("password", "两次密码输入不一至");
			}
		}

		if (hasErrors()) {
			return;
		}

		// 验证用户名是否注册
		if (adminUserService.isRegisted(model.getName())) {
			addFieldError("name", "用户已被占用");
		}
	}

	@Override
	public void setUser(AdminUser user) {

		this.user = user;

	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.sessionMap = session;
		
	}

}