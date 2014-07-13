package com.sniper.survey.struts2.action.web;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.opensymphony.xwork2.ActionContext;
import com.sniper.survey.struts2.RootAction;

public abstract class WebBaseAction extends RootAction {

	private static final long serialVersionUID = 1L;

	/**
	 * 分页参数
	 */
	private int pageNo;
	public String pageHtml;
	public int listRow = 20;

	@SuppressWarnings("unused")
	private boolean isAjaxRequest() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		HttpServletRequest request2 = ServletActionContext.getRequest();

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

}
