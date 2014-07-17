package com.sniper.survey.struts2.action.web;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.sniper.survey.struts2.RootAction;

public abstract class BaseAction<T> extends RootAction implements
		ModelDriven<T>, Preparable {

	private static final long serialVersionUID = 1L;

	/**
	 * 读取记录数
	 */
	protected List<T> list;

	public List<T> getList() {
		return list;
	}

	/**
	 * 分页参数
	 */
	// 当前的页数
	private int pageNo;
	public String pageHtml;
	// 每页显示的记录数
	public int listRow = 20;

	public T model;
	/**
	 * 获取用户提交的方式
	 */
	private String method;

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
