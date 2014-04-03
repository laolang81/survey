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
public class UserAction extends BaseAction<AdminUser> {
	
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
		
		
		AdminGroup adminGroup = adminGroupService.getEntity(1);
		
		//System.out.println(adminGroup.getName());
		AdminUser adminUser = new AdminUser();
		
		System.out.println(adminUser);
		System.out.println(model.getClass());
		/*adminUser.setAdminGroup(adminGroup);
		//model.setId(1);
		adminUser.setName("admin");
		adminUser.setNickName("原始管理员");
		adminUser.setCtime(new Date());
		String rand = "1456";
		adminUser.setRand(rand);
		adminUser.setPassword(DataUtil.md5("admin" + rand));
		adminUser.setStatus(1);
		System.out.println(adminUser);
		adminUserService.saveEntiry(adminUser);
		
		System.out.println(adminUserService.getEntity(1));*/
		
		
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

	

}
