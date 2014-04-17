package com.sniper.survey.struts2.interceptor;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sniper.survey.service.impl.AdminRightService;
import com.sniper.survey.util.ValidateUtil;

public class CatchUrlInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

	/**
	 * 拦截器,拦截一些action, 检查并插入数据库,插入一些数据库没有的action
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 获取actionProxy代理
		ActionProxy proxy = invocation.getProxy();
		// 名称空间
		String ns = proxy.getNamespace();
		// actionName
		String actionName = proxy.getActionName();

		if (!ValidateUtil.isValid(ns) || ns.equals("/")) {
			ns = "";
		}
		String url = ns + "/" + actionName;
		// 获取spring
		// ApplicationContext context = (ApplicationContext)
		// invocation.getInvocationContext().getContextMap().get(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		// 获取spring的第二中方法,比较推荐第二种方法
		ServletContext context2 = ServletActionContext.getServletContext();
		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(context2);

		AdminRightService service = (AdminRightService) ac
				.getBean("adminRightService");
		service.appendRightByURL(url);

		return invocation.invoke();
	}

}
