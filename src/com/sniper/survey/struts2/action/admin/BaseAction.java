package com.sniper.survey.struts2.action.admin;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.service.impl.AdminUserService;
import com.sniper.survey.struts2.RootAction;

public abstract class BaseAction<T> extends RootAction implements
		ModelDriven<T>, Preparable, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	@Resource
	AdminUserService adminUserService;

	private HttpServletRequest request;
	/**
	 * sniper_menu菜单
	 */
	protected String menuType;
	protected Boolean menuValue;
	protected Integer[] delid;

	protected Map<String, Map<Boolean, String>> sniperMenu = new HashMap<>();

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuValue(Boolean menuValue) {
		this.menuValue = menuValue;
	}

	public Boolean getMenuValue() {
		return menuValue;
	}

	public Map<String, Map<Boolean, String>> getSniperMenu() {
		return sniperMenu;
	}

	public void setDelid(Integer[] delid) {
		this.delid = delid;
	}

	public Integer[] getDelid() {
		return delid;
	}

	/**
	 * 分页参数
	 */
	protected int pageNo;
	protected String pageHtml;
	protected int listRow = 20;
	// 用于ajax返回信息
	protected Map<String, Object> ajaxResult = new HashMap<>();

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

	/**
	 * 读取记录数
	 */
	protected List<T> list;
	protected List<T> AjaxList;

	public List<T> getList() {
		return list;
	}

	public List<T> getAjaxList() {
		return AjaxList;
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
	public void setMethod(String method) {
		this.method = method;

	}

	public String getMethod() {

		if (null == this.method) {
			this.method = this.request.getMethod();
		}
		return method;
	}

	protected boolean isXMLHttpRequest() {
		String header = this.request.getHeader("X-Requested-With");
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

	@JSON(serialize = false)
	public Map<String, Object> getAjaxResult() {
		return ajaxResult;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;

	}

}
