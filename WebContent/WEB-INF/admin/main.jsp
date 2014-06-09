<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%
     response.setHeader("Pragma", "no-cache");
     response.setHeader("Cache-Control", "no-cache");
     response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html lang="zh-cn">
<!-- 第一个装饰页面 -->
<head>
<!-- 从被装饰页面获取title标签内容,并设置默认值-->
<title><decorator:title default="默认title" /></title>
<!-- 从被装饰页面获取head标签内容 -->
<decorator:head />
</head>

<body>
	<div class="col-md-3">
		<div data-spy="affix" data-offset-top="100" >
			<s:property value="#attr.leftUserMenu" />
			导航菜单<br><br><br><br><br><br><br><br><br><br><br><br><br>导航菜单
			导航菜单<br><br><br><br><br><br><br><br><br><br><br><br><br>导航菜单
			导航菜单<br><br><br><br><br><br><br><br><br><br><br><br><br>导航菜单
			导航菜单<br><br><br><br><br><br><br><br><br><br><br><br><br>导航菜单
		</div>
	</div>
	<!-- 从被装饰页面获取body标签内容 -->
	<div class="container">
		<decorator:body />
	</div>
	
	<div id="footer" class="bs-footer" role="contentinfo">
	<p class="alignleft">
		<s:text name="Thanks for using"></s:text>
		<s:property value="#configInfo['cg_webname']"/>(<s:property value="#attr.TimeSpent"/>,<s:property value="#attr.phpMemory"/>)
	</p>
	<p id="footer-upgrade" class="alignright">
	${attr.htmlPath }
		<s:text name="Version" /><s:property value="#Version"/>
	</p>
	

</body>
</html>