package com.sniper.survey.struts2.action.admin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.sniper.survey.custom.authentication.AuthenticateResultInfoInterface;
import com.sniper.survey.custom.authentication.AuthenticationService;
import com.sniper.survey.custom.authentication.AuthenticationServiceInterface;
import com.sniper.survey.custom.authentication.DbTable;
import com.sniper.survey.custom.authentication.ResultInterface;
import com.sniper.survey.model.AdminGroup;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.service.impl.AdminRightService;
import com.sniper.survey.service.impl.AdminUserService;
import com.sniper.survey.util.DataUtil;

@Controller
@Scope("prototype")
@Namespace("/admin")
@ParentPackage("default")
@Results({
		@Result(name = "success", location = "login/index.jsp", type = "dispatcher"),
		@Result(name = "error_not_right", location = "errorNotRight", type = "redirectAction"),
		 
		})
public class LoginAction extends BaseAction<AdminUser> implements SessionAware {

	private static final long serialVersionUID = 1L;

	private String username;
	private String passwordtext;
	private String verifycode;

	private Map<String, Object> sessionMap;

	@Resource
	private AdminUserService adminUserService;
	@Resource
	private AdminRightService rightService;

	// 存放返回之前的结果
	private Map<String, String> result = new HashMap<String, String>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordtext() {
		return passwordtext;
	}

	public void setPasswordtext(String passwordtext) {
		this.passwordtext = passwordtext;
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
	@Action(value = "login")
	public String execute() throws Exception {
		//System.out.println(getText("login.message.error.fiald"));
		return SUCCESS;
	}

	/**
	 * ajax 登录验证
	 * 
	 * @return
	 */
	@Action(value = "loginAjaxValid", results = {@Result(name = "success", type = "json", params = { "root",
	"result" })} )
	
	public String loginAjaxValid() {
		
		if (this.username == null || this.username.isEmpty()) {
			result.put("code", "0");
			result.put("message", "用户名无效");
			result.put("id", "username");
			return SUCCESS;
		}
		System.out.println(this.username);
		System.out.println(this.verifycode);
		System.out.println(this.passwordtext);
		if (this.passwordtext == null || this.passwordtext.isEmpty()) {
			result.put("code", "0");
			result.put("message", "密码无效");
			result.put("id", "password");
			return SUCCESS;
		}

		if (this.verifycode == null || this.verifycode.isEmpty()) {
			result.put("code", "0");
			result.put("message", "验证码无效");
			result.put("id", "verifycode");
			return SUCCESS;
		}
		
		System.out.println(DataUtil.md5(DataUtil.md5(this.passwordtext)+"1456"));
		// 用户验证,只负责用户验证不负责保存
		DbTable dbTable = new DbTable(adminUserService, "au_name",
				"au_password", "MD5(CONCAT(?,au_rand)) AND ENABLES=1");
		dbTable.setCredential(DataUtil.md5(this.passwordtext));
		dbTable.setIdentity(this.username);

		// 执行验证
		AuthenticationServiceInterface auth = new AuthenticationService();
		// 出啊安迪session
		auth.setSession(sessionMap);
		AuthenticateResultInfoInterface loginResult = auth
				.authenticate(dbTable);

		ResultInterface codeNum = loginResult.getCode();
		System.out.println(codeNum.getCode());
		switch (codeNum.getCode()) {
		case 0:
			result.put("code", "0");
			result.put("message", getText("Login fiald"));
			result.put("id", "username");
			break;
		case 1:
			try {
				// 获取map数据
				@SuppressWarnings("rawtypes")
				Map map = (Map) loginResult.getObj();
				AdminUser user = adminUserService.getEntity((Integer) map
						.get("au_id"));
				// 获取最大权限位
				//int maxPos = rightService.getMaxRightPos();
				//user.setRightSum(new long[maxPos + 1]);
				// 计算权限综合
				//user.calucateRightSum();
				// 保存用户xx,提供spring使用
				System.out.println(user);
				Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
				Set<AdminGroup> groups = user.getAdminGroup();	
				
				for (AdminGroup adminGroup : groups) {
					authSet.add(new SimpleGrantedAuthority(adminGroup.getValue()));
				}

				Authentication authentication = new UsernamePasswordAuthenticationToken(
						user, user.getPassword(), authSet);
				SecurityContext securityContext = SecurityContextHolder
						.getContext();
				securityContext.setAuthentication(authentication);
				
				auth.getSession().put(auth.getStorage(), securityContext);
				//sessionMap.put("SPRING_SECURITY_CONTEXT", securityContext);
				// spring security 将权限及用户信息存入securityContext

				result.put("code", "1");
				result.put("message", "登录成功");
				result.put("id", "1");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case -1:
			result.put("code", "-1");
			result.put("message", "用户名不存在");
			result.put("id", "username");
			break;
		case -2:
			result.put("code", "-2");
			result.put("message", "用户未知");
			result.put("id", "username");
			break;
		case -3:
			result.put("code", "-3");
			result.put("message", "密码不匹配");
			result.put("id", "password");
			break;
			default:
				//String code = "";
				//code.valueOf(codeNum.getCode());
				//result.put("code", code);
				result.put("message", getText("Login fiald"));
				result.put("id", "username");
		}
		return SUCCESS;
	}

	/**
	 * 基本的验证
	 */
	public void prepareDoLoginAjaxValid() {
		
		
	}

	/**
	 * 清空登录信息
	 * 
	 * @return
	 */
	@Action("logout")
	public String logout() {
		AuthenticationServiceInterface auth = new AuthenticationService();
		auth.setSession(sessionMap);
		auth.clearIdentity();
		return "login";
	}

	@Action(value = "errorNotRight" ,results = {@Result(name = "error_not_right", location = "login/error_no_right.jsp", type = "dispatcher")})
	public String errorNotRight() {
		return "error_not_right";
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;

	}

}