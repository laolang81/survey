package com.sniper.survey.struts2.action;

import java.util.List;

import javax.annotation.Resource;

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
import com.sniper.survey.util.DataUtil;
import com.sniper.survey.util.ValidateUtil;

//加注解
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<AdminUser> {

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
		
		//select md5("21232F297A57A5A743894A0E4A801FC31456"); 91f99af155ccba7488bbdbe3ca8e0dd6
		//select concat(md5("admin"),"1456");21232f297a57a5a743894a0e4a801fc31456
		//select md5(concat(md5("admin"),"1456")); 919e8405b90e3b50d7e1239a962c5e1f
		//System.out.println(DataUtil.md5("admin"));
		//System.out.println(DataUtil.md5("21232F297A57A5A743894A0E4A801FC31456"));
		//System.out.println(DataUtil.md5( DataUtil.md5("admin") + "1456" ));
		
		
		
		
		//添加完毕之后自动定向到编辑页面
		this.id = model.getId();
		//要保持关联
		//model.setAdminGroup(adminGroup);
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

}