package com.sniper.survey.struts2.action.admin;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
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
import com.sniper.survey.service.impl.AdminRightService;
import com.sniper.survey.service.impl.AdminUserService;
import com.sniper.survey.struts2.RootAction;

/**
 * @author laolang
 * 
 * @param <T>
 */

public abstract class BaseAction<T> extends RootAction implements
		ModelDriven<T>, Preparable, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	@Resource
	AdminUserService adminUserService;
	@Resource
	AdminRightService adminRightService;

	// 执行原始的request方法
	private HttpServletRequest request;

	/**
	 * 搜索变量
	 */
	// 存放字符串
	protected Map<String, String> searchString = new HashMap<>();
	// 存放数字
	protected Map<String, Integer> searchInteger = new HashMap<>();
	// 存放boolean
	protected Map<String, Boolean> searchBoolean = new HashMap<>();
	protected Map<String, Date> searchDate = new HashMap<>();

	public Map<String, String> getSearchString() {
		return searchString;
	}

	public void setSearchString(Map<String, String> searchString) {
		this.searchString = searchString;
	}

	public Map<String, Integer> getSearchInteger() {
		return searchInteger;
	}

	public void setSearchInteger(Map<String, Integer> searchInteger) {
		this.searchInteger = searchInteger;
	}

	public Map<String, Boolean> getSearchBoolean() {
		return searchBoolean;
	}

	public void setSearchBoolean(Map<String, Boolean> searchBoolean) {
		this.searchBoolean = searchBoolean;
	}

	public void setSearchDate(Map<String, Date> searchDate) {
		this.searchDate = searchDate;
	}

	public Map<String, Date> getSearchDate() {
		return searchDate;
	}

	/**
	 * sniper_menu菜单
	 */
	protected Map<String, Map<Integer, String>> sniperMenuInt = new HashMap<>();
	protected Map<String, Map<Boolean, String>> sniperMenu = new HashMap<>();
	protected String menuType = "";
	protected String menuValue = "";
	protected Integer[] delid;
	// 菜单处理url
	protected String sniperUrl;

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuValue(String menuValue) {
		this.menuValue = menuValue;
	}

	public String getMenuValue() {
		return menuValue;
	}

	public Map<String, Map<Boolean, String>> getSniperMenu() {
		return sniperMenu;
	}

	public Map<String, Map<Integer, String>> getSniperMenuInt() {
		return sniperMenuInt;
	}

	public String getSniperUrl() {
		return sniperUrl;
	}

	public void setDelid(Integer[] delid) {
		this.delid = delid;
	}

	public Integer[] getDelid() {
		return delid;
	}

	protected String ip;

	public String getIp() {
		ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;

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
	 * 定义模板文件地址 暂不用武之地
	 */
	private String htmlPath = "admin";

	public String getHtmlPath() {
		return htmlPath;
	}

	/**
	 * 读取记录数
	 */
	protected List<T> list;
	/**
	 * ajax返回形式
	 */
	protected List<T> AjaxList;

	public List<T> getList() {
		return list;
	}

	public List<T> getAjaxList() {
		return AjaxList;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;

	}

	public HttpServletRequest getRequest() {
		return request;
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

	/**
	 * 每个action之前执行的方法
	 */
	@Override
	public void prepare() throws Exception {

		String url = this.request.getRequestURI().replace(
				this.request.getContextPath(), "");
		// 此时的url不会携带?后面的东西
		// 当不是一斜杠结尾的url,
		if (url.lastIndexOf(".") != -1) {
			// 去除结尾的后缀
			url = url.substring(0, url.lastIndexOf("."));
		}

		if (url.lastIndexOf("index") != -1) {
			url = url.substring(0, url.length() - 5);
		}
		System.out.println("getWebPageTitle" + url);
		String title = adminRightService.getCUrlName(url);
		setWebPageTitle(title);
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
	public static UserDetails userInfo() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return userDetails;

	}

	/**
	 * 获取用户id用于和其他表关联
	 * 
	 * @return
	 */
	public AdminUser AdminUser() {
		String username = userInfo().getUsername();
		AdminUser adminUser = adminUserService.validateByName(username);
		if (null != adminUser) {
			return adminUser;
		}
		return null;
	}

	/**
	 * 获取用户权限
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Collection<GrantedAuthority> authorities() {
		UserDetails userDetails = userInfo();
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

	/**
	 * delete公共调用的类
	 * 
	 * @return
	 */
	public void ajaxResultDelete() {
		// code 小于1表示有错误,大于0表示ok,==0表示未操作
		ajaxResult.put("code", -1);
		ajaxResult.put("msg", "error");
		ajaxResult.put("data", "");
		
		if (!getMethod().equalsIgnoreCase("post") || !isXMLHttpRequest()) {
			ajaxResult.put("code", -2);
			ajaxResult.put("msg", "非有效请求");
		}
		if ("".equals(getMenuType()) || "".equals(getMenuValue())) {
			ajaxResult.put("code", -3);
			ajaxResult.put("msg", "非有效参数");
		}
		
		ajaxResult.put("menuType", getMenuType());
		ajaxResult.put("code", 0);
		ajaxResult.put("msg", "Go On");
		
	}
}
