package com.sniper.survey.struts2.action.admin;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.sniper.survey.struts2.MethodAware;

@Results({ @Result(name = "error", location = "/WEB_INF/content/error/error.jsp") })
public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T>, Preparable, MethodAware {

	private static final long serialVersionUID = 1L;

	public T model;
	/**
	 * 获取用户提交的方式
	 */
	private String method;

	/**
	 * 定义模板文件地址
	 */
	private String htmlPath = "admin";

	public String getHtmlPath() {
		return htmlPath;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseAction() {
		// 得到泛型话的超类，
		Type type = this.getClass().getGenericSuperclass();
		// 如果type集成与ParameterizedType,以为内ParameterizedType可以带有参数，可以从他里面获取参数
		Type[] args = null;
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			// 获取参数列表
			args = parameterizedType.getActualTypeArguments();
			if (args != null && args.length > 0) {
				// 获取地一个参数
				Type arg = args[0];
				if (arg instanceof Class) {
					Class clazz = (Class<T>) arg;
					try {
						model = (T) clazz.newInstance();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		// 以上简单写法
		// ParameterizedType Type = (ParameterizedType)
		// this.getClass().getGenericSuperclass();;
		// clazz = (Class<T>) Type.getActualTypeArguments()[0];
	}

	@Override
	public void prepare() throws Exception {

	}

	@Override
	public T getModel() {
		return model;
	}

	/**
	 * 获取用户的提交方式 get post
	 */
	@Override
	public void setMethod(String method) {

		this.method = method;

	}

	public String getMethod() {
		if (null == this.method) {
			this.method = ServletActionContext.getRequest().getMethod();
		}
		return method;
	}

	@SuppressWarnings("unused")
	private boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header))
			return true;
		else
			return false;
	}

	/**
	 * 获取用户信息
	 * 
	 * @return
	 */
	protected UserDetails getUserInfo() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return userDetails;

	}

	/**
	 * 获取用户权限
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Collection<GrantedAuthority> getAuthorities() {
		UserDetails userDetails = getUserInfo();
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) userDetails
				.getAuthorities();
		return authorities;

	}

}
