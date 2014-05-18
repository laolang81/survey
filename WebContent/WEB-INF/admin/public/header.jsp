<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>${attr.title}</title>
<base
	href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="myfiles/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="myfiles/Plugin/Bootstarp/js/bootstrap.min.js"></script>
<script type="text/javascript" src="myfiles/Plugin/ztree/js/jquery.ztree.core-3.5.min.js"></script>

<link href="myfiles/Plugin/Bootstarp/css/bootstrap.min.css"
	media="screen" rel="stylesheet" type="text/css">
	
<link href="myfiles/Plugin/font-awesome/css/font-awesome.min.css"
	media="screen" rel="stylesheet" type="text/css">
	
<link href="myfiles/Plugin/ztree/css/zTreeStyle/zTreeStyle.css"
	media="screen" rel="stylesheet" type="text/css">
	


<link href="myfiles/css/topHeader.css" media="screen" rel="stylesheet"
	type="text/css">
<link href="myfiles/css/leftMenu.css" media="screen" rel="stylesheet"
	type="text/css">
<link href="myfiles/css/centerBody.css" media="screen" rel="stylesheet"
	type="text/css">



</head>
<body class="metro">

	<div class="navbar">
		<nav class="navigation-bar white border">
			<div class="navigation-bar-content">
				<a class="element" href="/"><span class="icon-grid-view"></span>
					METRO UI CSS <sup>2.0</sup></a> <span class="element-divider"></span> <a
					href="#" class="pull-menu"></a>
				<ul class="element-menu">
					<li><a href="#" class="dropdown-toggle">Base CSS</a>
						<ul data-role="dropdown" class="dropdown-menu">
							<li><a href="requirements.html">Requirements</a></li>
							<li><a class="dropdown-toggle" href="#">General CSS</a>
								<ul data-role="dropdown" class="dropdown-menu">
									<li><a href="global.html">Global styles</a></li>
									<li><a href="grid.html">Grid system</a></li>
									<div class="divider"></div>
									<li><a href="typography.html">Typography</a></li>
									<li><a href="tables.html">Tables</a></li>
									<li><a href="forms.html">Forms</a></li>
									<li><a href="buttons.html">Buttons</a></li>
									<li><a href="images.html">Images</a></li>
								</ul></li>
							<li class="divider"></li>
							<li><a href="responsive.html">Responsive</a></li>
							<li class="disabled"><a href="layouts.html">Layouts and
									templates</a></li>
							<li class="divider"></li>
							<li><a href="icons.html">Icons</a></li>
						</ul></li>
					<li><a href="#" class="dropdown-toggle">Community</a>
						<ul data-role="dropdown" class="dropdown-menu"
							style="display: none;">
							<li class="disabled"><a href="http://blog.metroui.net">Blog</a></li>
							<li class="disabled"><a href="http://forum.metroui.net">Community
									Forum</a></li>
							<li class="divider"></li>
							<li><a href="https://github.com/olton/Metro-UI-CSS">Github</a></li>
							<li class="divider"></li>
							<li><a
								href="https://github.com/olton/Metro-UI-CSS/blob/master/LICENSE">License</a></li>
						</ul></li>
				</ul>

				<div class="no-tablet-portrait">
					<span class="element-divider"></span> <a href="#"
						class="element brand"><span class="icon-spin"></span></a> <a
						href="#" class="element brand"><span class="icon-printer"></span></a>
					<span class="element-divider"></span>

					<div class="element input-element">
						<form>
							<div class="input-control text">
								<input type="text" placeholder="Search...">
								<button class="btn-search"></button>
							</div>
						</form>
					</div>

					<div class="element place-right">
						<a href="#" class="dropdown-toggle"> <span class="icon-cog"></span>
						</a>
						<ul data-role="dropdown" class="dropdown-menu place-right">
							<li><a href="#">Products</a></li>
							<li><a href="#">Download</a></li>
							<li><a href="#">Support</a></li>
							<li><a href='<s:url action="userLogout" namespace="/admin" />'>Logout</a></li>
						</ul>

					</div>
					<span class="element-divider place-right"></span>
					<button class="element image-button image-left place-right">
						Sergey Pimenov <img src="myfiles/Plugin/Metro/docs/images/me.jpg">
					</button>
				</div>
			</div>
		</nav>
	</div>
	<div class="container-fluid">
		<div class="body-main">
			<jsp:include page="../public/left.jsp" />