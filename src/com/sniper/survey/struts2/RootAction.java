package com.sniper.survey.struts2;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 所有action的跟action定义一些所有acntion公共使用的方法或者变量
 * @author sniper
 *
 */
@Results({ 								
	@Result(name = "error", location = "/WEB-INF/content/error/error.jsp"),
	@Result(name = "login", location = "login" ,type ="redirectAction" , params = {"namespace","/admin"})
	})
public abstract class RootAction extends ActionSupport {

	
	private static final long serialVersionUID = -4888324940150304798L;

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
