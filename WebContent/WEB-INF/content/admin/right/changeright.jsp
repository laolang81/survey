<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.beans.factory.FactoryBean"%>
<%@page
	import="org.springframework.security.web.access.intercept.FilterSecurityInterceptor"%>
<%@page
	import="org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource"%>
<%
	ApplicationContext ctx = WebApplicationContextUtils
			.getWebApplicationContext(application);
	FactoryBean factoryBean = (FactoryBean) ctx
			.getBean("&filterInvocationSecurityMetadataSource");
	FilterInvocationSecurityMetadataSource fids = (FilterInvocationSecurityMetadataSource) factoryBean
			.getObject();
	FilterSecurityInterceptor filter = (FilterSecurityInterceptor) ctx
			.getBean("filterSecurityInterceptor");
	filter.setSecurityMetadataSource(fids);
%>
<jsp:forward page="/" />
目前存在的问题是，系统会在初始化时一次将所有资源加载到内存中，即使在数据库中修改了资源信息，系统也不会再次去从数据库中读取资源信息。这就造成了每次修改完数据库后，都需要重启系统才能时资源配置生效。

解决方案是，如果数据库中的资源出现的变化，需要刷新内存中已加载的资源信息时，使用下面代码：
