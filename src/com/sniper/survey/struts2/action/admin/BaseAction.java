package com.sniper.survey.struts2.action.admin;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.service.impl.AdminUserService;
import com.sniper.survey.struts2.MethodAware;
import com.sniper.survey.struts2.RootAction;

public abstract class BaseAction<T> extends RootAction implements
		ModelDriven<T>, Preparable, MethodAware,
		ServletRequestAware {

	private HttpServletRequest request;
	private static final long serialVersionUID = 1L;

	@Resource
	AdminUserService adminUserService;
	/**
	 * 分页参数
	 */
	private int pageNo;
	public String pageHtml;
	public int listRow = 20;

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
	private boolean isAjaxRequest() {
		String header = ServletActionContext.getRequest().getHeader(
				"X-Requested-With");
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
	public static UserDetails getUserInfo() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return userDetails;

	}

	/**
	 * 获取用户id用于和其他表关联
	 * 
	 * @return
	 */
	public int getUserID() {
		String username = getUserInfo().getUsername();
		AdminUser adminUser = adminUserService.validateByName(username);
		if (null != adminUser) {
			return adminUser.getId();
		}
		return 0;
	}

	/**
	 * 获取用户权限
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Collection<GrantedAuthority> getAuthorities() {
		UserDetails userDetails = getUserInfo();
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) userDetails
				.getAuthorities();
		return authorities;

	}

	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the pageHtml
	 */
	public String getPageHtml() {
		return pageHtml;
	}

	/**
	 * @param pageHtml
	 *            the pageHtml to set
	 */
	public void setPageHtml(String pageHtml) {
		this.pageHtml = pageHtml;
	}

	/**
	 * @return the listRow
	 */
	public int getListRow() {
		return listRow;
	}

	/**
	 * @param listRow
	 *            the listRow to set
	 */
	public void setListRow(int listRow) {
		this.listRow = listRow;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;

	}
	

}
