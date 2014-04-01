package com.sniper.survey.struts2.action;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminGroup;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.service.impl.AdminGroupService;
import com.sniper.survey.service.impl.AdminUserService;
import com.sniper.survey.util.DataUtil;

//加注解
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<AdminUser> implements
		ServletRequestAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	
	@Resource
	private AdminUserService adminUserService;
	
	@Resource
	private AdminGroupService adminGroupService;

	public String index()
	{
		/*System.out.println(model);
		
		AdminGroup adminGroup = new AdminGroup();
		adminGroup.setName("超级管理员");
		adminGroup.setValue("administrator");
		adminGroup.setNote("超级管理员");*/
		
		//adminGroupService.saveEntiry(adminGroup);
		//adminGroup = adminGroupService.getEntity(1);
		//System.out.println(adminGroupService);
		//System.out.println(adminGroup);
		//model.setAdminGroup(1);
		//model.setId(1);
		/*model.setName("admin");
		model.setNickName("原始管理员");
		model.setCtime(new Date());
		String rand = "1456";
		model.setRand(rand);
		model.setPassword(DataUtil.md5("admin" + rand));
		model.setStatus(1);
		
		adminUserService.saveEntiry(model);*/
		
		//System.out.println(adminUserService.getEntity(1));
		
		
		String methodName = request.getMethod();
		System.out.println(methodName);
		return "index";
	}
	
	// 跳过校验
	@SkipValidation
	public String add() {

		//String methodName = request.getMethod();
		//System.out.println();
		return "add";

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

		if (hasErrors()) {
			return;
		}
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

}
