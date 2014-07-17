package com.sniper.survey.struts2.action.web;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.MeetUser;
import com.sniper.survey.service.impl.MeetUserService;

@Controller()
@Scope("prototype")
@Namespace("/")
@ParentPackage("convention-default")
@ResultPath("/WEB-INF/content/web")
public class IndexAction extends BaseAction<MeetUser> {

	private static final long serialVersionUID = 1L;

	@Resource
	MeetUserService meetUserService;

	@Actions(value = { @Action(value = "index", results = { @Result(name = "success", location = "index/index.jsp") }) })
	public String index() {
		System.out.println("web-index");
		meetUserService.setOrder("id desc");
		meetUserService.pageList(getListRow());
		pageHtml = meetUserService.getPageHtml();
		list = meetUserService.getLists();

		return SUCCESS;

	}

}
