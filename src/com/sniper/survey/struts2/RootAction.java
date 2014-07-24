package com.sniper.survey.struts2;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sniper.survey.service.impl.SystemConfigService;

/**
 * 定义一些所有action公共使用的方法或者变量 所有的action的基类
 * 
 * @author sniper
 * 
 */
@Results({
		@Result(name = "error_not_right", location = "errorNotRight", type = "redirectAction", params = {
				"namespace", "/admin" }),
		@Result(name = "error", location = "/WEB-INF/content/error/error.jsp"),
		@Result(name = "login", location = "login", type = "redirectAction", params = {
				"namespace", "/admin" }) })
@Controller
@Scope("prototype")
@ParentPackage("default")
@Namespace("/admin/")
public abstract class RootAction extends ActionSupport {

	private static final long serialVersionUID = -4888324940150304798L;

	
	@Autowired
	SystemConfigService configService;
	// 存放网站配置
	private static Map<String, String> systemConfig = new HashMap<>();

	/**
	 * 获取网站配置
	 * 
	 * @return
	 */
	public Map<String, String> getSystemConfig() {
		if (systemConfig.isEmpty()) {
			systemConfig = configService.getAdminConfig(true);
		}
		return systemConfig;
	}

	public void setSystemConfig(Map<String, String> systemConfig) {
		RootAction.systemConfig = systemConfig;
	}

	/**
	 * 网站title
	 */
	private String webPageTitle;

	public String getWebPageTitle() {
		return webPageTitle;
	}

	public void setWebPageTitle(String webPageTitle) {
		this.webPageTitle = webPageTitle;
	}

}
