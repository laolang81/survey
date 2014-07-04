package com.sniper.survey.struts2.action.admin;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.MeetUser;
import com.sniper.survey.service.impl.MeetUserService;
import com.sniper.survey.util.StrutsPage;

@Controller
@Scope("prototype")
@Namespace("/admin/meet-user")
@ParentPackage("default")
public class MeetUserAction extends BaseAction<MeetUser> {

	private static final long serialVersionUID = 1L;

	@Resource
	MeetUserService meetUserService;

	/**
	 * 读取记录数
	 */
	private List<MeetUser> meetUsers;

	/**
	 * @return the meetUsers
	 */
	public List<MeetUser> getMeetUsers() {
		return meetUsers;
	}

	@Actions({ @Action(value = "index") })
	@SkipValidation
	public String index() {

		System.out.println("sssssssssss");
		String hql = "select count(a) from MeetUser a";
		long l = (long) meetUserService.uniqueResult(hql);
		int totalNum = new Long(l).intValue();

		StrutsPage page = new StrutsPage(totalNum, getListRow());
		pageHtml = page.show();

		String hql2 = "from MeetUser order by id desc";
		this.meetUsers = meetUserService.page(hql2, page.getFristRow(),
				page.getListRow());

		return SUCCESS;
	}

	@Action(value = "save", results = {
			@Result(name = "success", location = "save.jsp"),
			@Result(name = "input", location = "save.jsp") })
	@SkipValidation
	public String save() {
		System.out.println("ssssssssssssssssssss");
		System.out.println(getMethod());
		if (getMethod().equals("post")) {
			System.out.println("save");
			meetUserService.saveOrUpdateEntiry(model);
		}
		return SUCCESS;
	}

	@Action(value = "saveData")
	public String saveData() {
		if (getMethod().equals("post")) {
			meetUserService.saveOrUpdateEntiry(model);
		}
		return SUCCESS;
	}
	
	public String update()
	{
		if(null == model.getId()){
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String updateData(){
		return SUCCESS;
	}
	
	public String delete()
	{
		return SUCCESS;
	}
	
}
