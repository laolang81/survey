package com.sniper.survey.struts2.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.custom.authentication.AuthenticateResultInfoInterface;
import com.sniper.survey.custom.authentication.AuthenticationService;
import com.sniper.survey.custom.authentication.AuthenticationServiceInterface;
import com.sniper.survey.custom.authentication.DbTable;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.service.impl.AdminUserService;
import com.sniper.survey.util.DataUtil;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<AdminUser> {

	private static final long serialVersionUID = 1L;

	private String account;
	private String password;
	private String verify;
	
	@Resource
	private AdminUserService adminUserService;

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
		
		DbTable dbTable = new DbTable(adminUserService, "au_name", "au_password","MD5(CONCAT(?,au_rand)) AND au_status=1");
		dbTable.setCredential(DataUtil.md5(this.password));
		dbTable.setIdentity(this.account);
		
		AuthenticationServiceInterface auth = new AuthenticationService();
		AuthenticateResultInfoInterface loginResult =  auth.authenticate(dbTable);
		System.out.println(loginResult.getCode());
		
		switch(loginResult.getCode().getCode())
		{
		case 0:
		case 1:
		case -1:
		case -2:
		case -3:
			
		}
		
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