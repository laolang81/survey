package com.sniper.survey.struts2.action.admin;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminGroup;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.service.impl.AdminGroupService;
import com.sniper.survey.service.impl.AdminUserService;
import com.sniper.survey.service.impl.WebUserService;
import com.sniper.survey.struts2.UserAware;
import com.sniper.survey.util.ValidateUtil;

//加注解
@Controller
@Scope("prototype")
@Namespace("/admin/user")
@ParentPackage("default")
public class UserAction extends BaseAction<AdminUser> implements UserAware {

	private static final long serialVersionUID = 1L;

	@Resource
	private AdminUserService adminUserService;

	@Resource
	private AdminGroupService adminGroupService;

	@Resource
	private WebUserService webUserService;
	// 用户组列表
	private List<AdminGroup> adminGroupsSelect;

	// 登录用户信息
	private AdminUser user;

	//
	private AdminGroup adminGroup;
	/**
	 * 编辑更新记录标识id
	 */
	public Integer updateid;

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
	@Action("index")
	public String index() {

		return SUCCESS;
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	@SkipValidation
	public String save() {

		// 添加完毕之后自动定向到编辑页面
		this.updateid = model.getId();
		// 要保持关联
		// model.setAdminGroup(adminGroup);
		return INPUT;
	}

	/**
	 * 默认prepare拦截器先调用do开头的 做一些准备或者可以提前处理的工作
	 */
	public void prepareDoSave() {

		// 添加
		if (getMethod().equalsIgnoreCase("post")) {
			System.out.println(model);
		} else {
			// 设置用户组
			//setAdminGroupsSelect(adminGroupService.getGroupSelectList());
		}
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

	public AdminGroup getAdminGroup() {
		return adminGroup;
	}

	public void setAdminGroup(AdminGroup adminGroup) {
		this.adminGroup = adminGroup;
	}
}