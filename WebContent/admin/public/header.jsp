<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>${attr.title }</title>
<base href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="myfiles/js/jquery-1.9.1.min.js"></script>
<link href="myfiles/css/admin.css" media="screen" rel="stylesheet" type="text/css">
<link href="myfiles/css/topHeader.css" media="screen" rel="stylesheet" type="text/css">
<link href="myfiles/css/leftMenu.css" media="screen" rel="stylesheet" type="text/css">
<link href="myfiles/css/centerBody.css" media="screen" rel="stylesheet" type="text/css">
<link href="myfiles/Plugin/font-awesome/css/font-awesome.min.css" media="screen" rel="stylesheet" type="text/css">
</head>
<body>
<div class="navbar">
	<div class="navigation">
		<ul class="pull-left">
			<li class="logo"><a href="<s:url action="userLogin" namespace="/admin" />">
			<img alt="Logo+网站的名称" src="myfiles/images/images/logo.png">
			<span>Logo+网站的名称</span>		
			</a></li>
			<li class="webName"><a href="">Logo+网站的名称</a></li>
		</ul>
		
		<ul class="pull-right">
			<li><a href="">
				<i class="fa fa-user"></i>
				<span>登录者的名称</span>
				<span class="caret"></span>
			</a>
			<ul class="dropdown-menu">
				<li><a href="">设置</a></li>
				<li class="divider"></li>
				<li><a href='<s:url action="logout" namespace="/admin" />'>登出</a></li>
			</ul>
			
			</li>
		</ul>	
	</div>
</div>
<div class="body-main">
<jsp:include page="../public/left.jsp"/>