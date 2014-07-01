<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
     response.setHeader("Pragma", "no-cache");
     response.setHeader("Cache-Control", "no-cache");
     response.setDateHeader("Expires", 0);
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<!-- 第一个装饰页面 -->
<head>
<meta charset="utf-8">
<!-- 从被装饰页面获取title标签内容,并设置默认值-->
<title><sitemesh:write property="title" /> - 后台管理</title>
<!-- 从被装饰页面获取head标签内容 -->
<sitemesh:write property='head'/>
<base href="<%= basePath %>">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script type="text/javascript" src="myfiles/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="myfiles/Plugin/Bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="myfiles/Plugin/ztree/js/jquery.ztree.core-3.5.min.js"></script>


<link href="myfiles/Plugin/Bootstrap/css/bootstrap.min.css"
	media="screen" rel="stylesheet" type="text/css">

<link href="myfiles/Plugin/Bootstrap/css/bootstrap-theme.min.css"
	media="screen" rel="stylesheet" type="text/css">

<link href="myfiles/Plugin/font-awesome/css/font-awesome.min.css"
	media="screen" rel="stylesheet" type="text/css">

<link href="myfiles/Plugin/ztree/css/zTreeStyle/zTreeStyle.css"
	media="screen" rel="stylesheet" type="text/css">

<link href="myfiles/css/admin.css"
	media="screen" rel="stylesheet" type="text/css">

</head>

<body>
<nav class="navbar navbar-default" role="navigation">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#bs-example-navbar-collapse-1">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="">Sniper</a>
	</div>

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse"
		id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li class="active"><a href="#">网站站点</a></li>
			<li><a href="#">Link</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">权限管理 <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="admin/right/list">资源列表</a></li>
					<li><a href="admin/right/save">资源添加</a></li>
					<li><a href="#">Something else here</a></li>
					<li class="divider"></li>
					<li><a href="#">Separated link</a></li>
					<li class="divider"></li>
					<li><a href="#">One more separated link</a></li>
				</ul>
			</li>
		</ul>

		<ul class="nav navbar-nav navbar-right">
			<li><a href="#">Link</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Dropdown <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="#">Action</a></li>
					<li><a href="#">Another action</a></li>
					<li><a href="#">Something else here</a></li>
					<li class="divider"></li>
					<li><a href="j_spring_security_logout">Logout</a></li>
				</ul>
			</li>
		</ul>
	</div>
	<!-- /.navbar-collapse -->
</nav>


<div class="container">
	<div class="row">
		<div class="col-md-2">
			<div data-spy="affix" data-offset-top="100" >
				导航菜单
			</div>
		</div>
		<div class="col-md-10" role="main">
			
			
			<!-- 从被装饰页面获取body标签内容 -->
			<sitemesh:write property='body'/>
		</div>
	</div>
	
</div>
<div id="footer" class="bs-footer" role="contentinfo">
	<div class="container">
		<p class="text-left">
			<decorator:getProperty property="Thanks for using"></decorator:getProperty>
			<decorator:getProperty property="configInfo['cg_webname']"/>(<decorator:getProperty property="attr.TimeSpent"></decorator:getProperty>,<decorator:getProperty property="attr.phpMemory"></decorator:getProperty>)
		</p>
		<p id="footer-upgrade" class="text-right">
			Version <decorator:getProperty property="Version"></decorator:getProperty>
		</p>
	</div>
</div>
</body>
</html>