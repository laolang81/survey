package com.sniper.survey.struts2.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.custom.authentication.AuthenticateResultInfoInterface;
import com.sniper.survey.custom.authentication.AuthenticationService;
import com.sniper.survey.custom.authentication.AuthenticationServiceInterface;
import com.sniper.survey.custom.authentication.DbTable;
import com.sniper.survey.custom.authentication.ResultInterface;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.service.impl.AdminUserService;
import com.sniper.survey.util.BeanToMapUtil;
import com.sniper.survey.util.DataUtil;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<AdminUser> implements SessionAware {

	private static final long serialVersionUID = 1L;

	private String account;
	private String passwd;
	private String verifycode;

	private Map<String, Object> sessionMap;

	@Resource
	private AdminUserService adminUserService;

	// 存放返回结果
	private String result;
	// 存放返回之前的结果
	private Map<String, String> resultMap = new HashMap<String, String>();

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
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

		// 用户验证
		DbTable dbTable = new DbTable(adminUserService, "au_name",
				"au_password", "MD5(CONCAT(?,au_rand)) AND au_status=1");
		dbTable.setCredential(DataUtil.md5(this.passwd));
		dbTable.setIdentity(this.account);

		// 调用用户验证,及其用户管理
		AuthenticationServiceInterface auth = new AuthenticationService();
		auth.setSession(sessionMap);
		AuthenticateResultInfoInterface loginResult = auth
				.authenticate(dbTable);
		// System.out.println(loginResult.getCode());
		ResultInterface codeNum = loginResult.getCode();
		switch (codeNum.getCode()) {
		case 0:
			resultMap.put("result", "0");
			resultMap.put("message", "登录失败");
			resultMap.put("id", "account");
			break;
		case 1:
			// 登录成功,写入session
			// 这里session返回的是一个map数据,不过可以封装成对象
			AdminUser user = null;
			try {				
				resultMap.put("result", "1");
				resultMap.put("message", "登录成功");
				resultMap.put("id", "1");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case -1:
			resultMap.put("result", "-1");
			resultMap.put("message", "用户名不存在");
			resultMap.put("id", "account");
			break;
		case -2:
			resultMap.put("result", "-2");
			resultMap.put("message", "用户未知");
			resultMap.put("id", "account");
			break;
		case -3:
			resultMap.put("result", "-3");
			resultMap.put("message", "密码不匹配");
			resultMap.put("id", "password");
			break;
		}
		setResultMapJson(resultMap);
		return SUCCESS;
	}

	public String prepareDoLogin() {

		if (this.account == null || this.account.isEmpty()) {
			resultMap.put("message", "用户名必须");
			resultMap.put("id", "account");
		}

		if (this.passwd == null || this.passwd.isEmpty()) {
			resultMap.put("message", "密码必须");
			resultMap.put("id", "password");
		}

		if (this.verifycode == null || this.verifycode.isEmpty()) {
			resultMap.put("message", "验证码必须");
			resultMap.put("id", "login_verify");
		}
		return SUCCESS;

	}

	/**
	 * 将map专程json
	 * 
	 * @param map
	 */
	private void setResultMapJson(Map<String, String> map) {
		// 将要返回的map对象进行json处理
		JSONObject json = JSONObject.fromObject(map);
		setResult(json.toString());
	}

	public String logout() {
		return "logiout";
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;

	}

}