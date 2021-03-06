package com.sniper.survey.spring.security;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sniper.survey.model.AdminUser;
import com.sniper.survey.service.impl.AdminUserService;
import com.sniper.survey.util.DataUtil;

public class MyUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {

	// 必须要和验证码保存那一段一致 //com.sniper.survey.struts2.action.admin.VerifyAction
	public static final String VALIDATE_CODE = "sessionVerifyName";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";

	private AdminUserService adminUserService;

	public AdminUserService getAdminUserService() {
		return adminUserService;
	}

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");

		if (!request.getMethod().equalsIgnoreCase("post")) {
			throw new AuthenticationServiceException("Invalid request");
		}

		checkValidateCode(request);

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		username = username.trim();
		password = password.trim();

		if (StringUtils.isEmpty(username)) {
			throw new AuthenticationServiceException("用户名不能为空");
		}

		// 保存最后提交的用户名
		request.getSession().setAttribute("SPRING_SECURITY_LAST_USERNAME_KEY",
				username);

		if (StringUtils.isEmpty(password)) {
			throw new AuthenticationServiceException("密码不能为空");
		}
		// 获取数据库比对密码
		AdminUser adminUser = adminUserService.validateByName(username);

		if (adminUser == null) {
			throw new AuthenticationServiceException("用户名不存在");
		}
		password = DataUtil.md5(password) + adminUser.getRand();
		password = DataUtil.md5(password);
		if (adminUser.getPassword() != null && !adminUser.getPassword().equals(password)) {

			/*
			 * request.getSession().setAttribute(
			 * SPRING_SECURITY_FORM_USERNAME_KEY,
			 * TextEscapeUtils.escapeEntities(username));
			 */
			/*
			 * 在我们配置的simpleUrlAuthenticationFailureHandler处理登录失败的处理类在这么一段
			 * 这样我们可以在登录失败后，向用户提供相应的信息。
			 */
			/*
			 * if (forwardToDestination) {
			 * request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
			 * exception); } else { HttpSession session =
			 * request.getSession(false);
			 * 
			 * if (session != null || allowSessionCreation) {
			 * request.getSession(
			 * ).setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
			 * exception); } }
			 */
			throw new AuthenticationServiceException("用户名密码不匹配！");
		}

		// UsernamePasswordAuthenticationToken实现 Authentication
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);
		// Place the last username attempted into HttpSession for views
		// 允许子类设置详细属性
		setDetails(request, authenticationToken);
		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager()
				.authenticate(authenticationToken);
	}

	/**
	 * 验证session验证码是否一致
	 * 
	 * @param request
	 */
	private void checkValidateCode(HttpServletRequest request) {

		HttpSession session = request.getSession();
		String sessionValidatecode = obtailSessionValidateCode(session);
		// 让上一次的验证码失效,这里被调用2次,第二次调用在myUserDetail回调
		// session.setAttribute(VALIDATE_CODE, null);
		String validateCodeParamter = obtainValidateCodeParamter(request);

		if (StringUtils.isEmpty(validateCodeParamter)
				|| !sessionValidatecode.equalsIgnoreCase(validateCodeParamter)) {
			// throw new AuthenticationServiceException("验证码不匹配！");
		}
	}

	/**
	 * 获取参数中的验证码
	 * 
	 * @param request
	 * @return
	 */
	private String obtainValidateCodeParamter(HttpServletRequest request) {
		Object obj = request.getParameter(VALIDATE_CODE);
		return null == obj ? "" : obj.toString();
	}

	/**
	 * 获取出存在session中的验证码
	 * 
	 * @param httpSession
	 * @return
	 */
	private String obtailSessionValidateCode(HttpSession httpSession) {

		Object object = httpSession.getAttribute(VALIDATE_CODE);
		return object == null ? "" : object.toString();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter(PASSWORD);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter(USERNAME);
		return null == obj ? "" : obj.toString();
	}

}
