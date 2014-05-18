package com.sniper.survey.struts2.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

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
import com.sniper.survey.service.impl.AdminRightService;
import com.sniper.survey.service.impl.AdminUserService;
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
	@Resource
	private AdminRightService rightService;

	// 存放返回之前的结果
	private Map<String, String> result = new HashMap<String, String>();

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

	@JSON(serialize = false)
	public Map<String, String> getResult() {
		return result;
	}

	public void setResult(Map<String, String> result) {
		this.result = result;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	/**
	 * ajax 登录验证
	 * @return
	 */
	public String loginAjaxValid() {

		if (result.size() > 1) {
			return SUCCESS;
		}
		// 用户验证,只负责用户验证不负责保存
		DbTable dbTable = new DbTable(adminUserService, "au_name",
				"au_password", "MD5(CONCAT(?,au_rand)) AND au_status=1");
		dbTable.setCredential(DataUtil.md5(this.passwd));
		dbTable.setIdentity(this.account);
		
		//执行验证
		AuthenticationServiceInterface auth = new AuthenticationService();
		//出啊安迪session
		auth.setSession(sessionMap);
		AuthenticateResultInfoInterface loginResult = auth
				.authenticate(dbTable);

		ResultInterface codeNum = loginResult.getCode();
		switch (codeNum.getCode()) {
		case 0:
			
			result.put("result", "0");
			result.put("message", "登录失败");
			result.put("id", "account");
			break;
		case 1:
			try {
				//获取map数据
				Map map =  (Map) loginResult.getObj();
				AdminUser user	 = adminUserService.getEntity((Integer) map.get("au_id"));
				//获取最大权限位
				int maxPos =  rightService.getMaxRightPos();
				user.setRightSum(new long[maxPos + 1]);
				//计算权限综合
				user.calucateRightSum();
				//保存用户xx 				
				auth.getSession().put(auth.getStorage(), user);
				
				result.put("result", "10");
				result.put("message", "登录成功");
				result.put("id", "1");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case -1:
			result.put("result", "-1");
			result.put("message", "用户名不存在");
			result.put("id", "account");
			break;
		case -2:
			result.put("result", "-2");
			result.put("message", "用户未知");
			result.put("id", "account");
			break;
		case -3:
			result.put("result", "-3");
			result.put("message", "密码不匹配");
			result.put("id", "passwd");
			break;
		}
		return SUCCESS;
	}

	/**
	 * 基本的验证
	 */
	public void prepareDoLoginAjaxValid() {

		if (this.account == null || this.account.isEmpty()) {
			result.put("message", "用户名无效");
			result.put("id", "account");
			return;
		}

		if (this.passwd == null || this.passwd.isEmpty()) {
			result.put("message", "密码无效");
			result.put("id", "passwd");
			return;
		}

		if (this.verifycode == null || this.verifycode.isEmpty()) {
			result.put("message", "验证码无效");
			result.put("id", "verifycode");
			return;
		}

	}

	/**
	 * 清空登录信息
	 * 
	 * @return
	 */
	public String logout() {
		AuthenticationServiceInterface auth = new AuthenticationService();
		auth.setSession(sessionMap);
		auth.clearIdentity();
		return "login";
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;

	}

}