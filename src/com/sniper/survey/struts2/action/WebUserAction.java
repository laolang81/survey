package com.sniper.survey.struts2.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.WebUser;
import com.sniper.survey.service.impl.WebUserService;

//加注解
@Controller
@Scope("prototype")
public class WebUserAction extends BaseAction<WebUser> {

	private static final long serialVersionUID = 1L;

	@Resource
	private WebUserService webUserService;

	private Map<String, List<WebUser>> result = new HashMap<>();
	//private String result;
	public Integer id;
	
	//没也显示多少行
	public int iDisplayLength;
	//每页开始行
	public int iDisplayStart;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@JSON(serialize = false)
	public Map<String, List<WebUser>> getResult() {
		return result;
	}

	public void setResult(Map<String, List<WebUser>> result) {
		this.result = result;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	/**
	 * 用户列表
	 * 
	 * @return
	 */
	public String list() {

		List<WebUser> users = webUserService.getUserList(getiDisplayLength(), getiDisplayStart());
		System.out.println(users);
		return SUCCESS;
	}

	/**
	 * 返回ajax获取的数据
	 * 
	 * @return
	 */
	public String doAjaxList() {
		List<WebUser> users = webUserService.getUserList(getiDisplayLength(), getiDisplayStart());
		result.put("aaData", users);
		//setResultMapJson();
		return SUCCESS;
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	@SkipValidation
	public String doAdd() {

		// 添加完毕之后自动定向到编辑页面
		this.id = model.getId();
		// 要保持关联
		// model.setAdminGroup(adminGroup);
		return INPUT;
	}

	/**
	 * 默认prepare拦截器先调用do开头的 做一些准备或者可以提前处理的工作
	 */
	public void prepareDoAdd() {

		// 添加
		if (getMethod().equalsIgnoreCase("post")) {
			System.out.println(model);
		} else {
			// 设置用户组

		}
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

	private void setResultMapJson() {
		// 将要返回的map对象进行json处理
		/*JSONObject json = JSONObject.fromObject(resultMap);
		System.out.println(json.toString());
		setResult(json.toString());*/
	}

}