<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title><sitemesh:write property="title" /> - 后台管理</title>
<sitemesh:write property='head' />
<base href="<%=basePath%>">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script type="text/javascript" src="myfiles/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="myfiles/js/js.config.js"></script>
<script type="text/javascript"
	src="myfiles/Plugin/Bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="myfiles/Plugin/artDialog/jquery.artDialog.js?skin=green"></script>

<link href="myfiles/Plugin/Bootstrap/css/bootstrap.min.css"
	media="screen" rel="stylesheet" type="text/css">

<link href="myfiles/Plugin/Bootstrap/css/bootstrap-theme.min.css"
	media="screen" rel="stylesheet" type="text/css">

<link href="myfiles/Plugin/font-awesome/css/font-awesome.min.css"
	media="screen" rel="stylesheet" type="text/css">


<link href="myfiles/css/admin.css" media="screen" rel="stylesheet"
	type="text/css">

</head>

<body>
	<nav class="navbar navbar-default" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="">Sniper</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">LOGO</a></li>
				<li><a href="#">网站站点</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">人员采集 <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="admin/meet-user/">人员列表</a></li>
						<li><a href="admin/meet-user/save">人员添加</a></li>
						<li><a href="admin/meet-user/export">人员导出</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">权限管理 <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="admin/admin-right/">资源列表</a></li>
						<li><a href="admin/admin-right/save">资源添加</a></li>
						<li class="divider"></li>
						<li><a href="admin/admin-group/">用户组列表</a></li>
						<li><a href="admin/admin-group/save">用户组添加</a></li>
						<li class="divider"></li>
						<li><a href="admin/admin-user/">管理员列表</a></li>
						<li><a href="admin/admin-user/save">管理员添加</a></li>
					</ul></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Holle, Sniper <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#">我的设置</a></li>
						<li><a href="#">网站配置</a></li>
						<li><a href="#">更改密码</a></li>
						<li class="divider"></li>
						<li><a href="j_spring_security_logout">退出</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</nav>


	<div class="container">
		<!-- 从被装饰页面获取body标签内容 -->
		<sitemesh:write property='body' />
	</div>

	<div id="footer" class="bs-footer" role="contentinfo">
		<div class="container">
			<p class="text-right">Thanks for using</p>
			<p id="footer-upgrade" class="text-right">Version 1.0</p>
		</div>
	</div>
</body>
</html>