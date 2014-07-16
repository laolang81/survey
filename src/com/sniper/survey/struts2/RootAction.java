package com.sniper.survey.struts2;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.sniper.survey.service.impl.SystemConfigService;

/**
 * 所有action的跟action定义一些所有acntion公共使用的方法或者变量
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
public abstract class RootAction extends ActionSupport {

	private static final long serialVersionUID = -4888324940150304798L;

	@Resource
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
