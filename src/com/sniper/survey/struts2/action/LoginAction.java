package com.sniper.survey.struts2.action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminUser;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<AdminUser> {

	private static final long serialVersionUID = 1L;

	private String account;
	private String password;
	private String verify;

	// 存放返回结果
	private String result;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	@JSON(name = "RESULT")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String index() {

		return "login";
	}

	public String login() {

		// 用一个Map做例子
		Map<String, String> map = new HashMap<String, String>();

		// 为map添加一条数据，记录一下页面传过来loginName
		map.put("name", this.account);
		map.put("a", "1111");
		map.put("b", "2222");
		map.put("c", "3333");
		// 将要返回的map对象进行json处理
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();

		return SUCCESS;
	}

	public String logout() {
		return "logiout";
	}

}