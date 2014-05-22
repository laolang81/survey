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
<script type="text/javascript"
	src="myfiles/Plugin/bootstarp/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="myfiles/Plugin/ztree/js/jquery.ztree.core-3.5.min.js"></script>

<link href="myfiles/Plugin/bootstarp/css/bootstrap.min.css"
	media="screen" rel="stylesheet" type="text/css">

<link href="myfiles/Plugin/bootstarp/css/bootstrap-theme.min.css"
	media="screen" rel="stylesheet" type="text/css">

<link href="myfiles/Plugin/font-awesome/css/font-awesome.min.css"
	media="screen" rel="stylesheet" type="text/css">

<link href="myfiles/Plugin/ztree/css/zTreeStyle/zTreeStyle.css"
	media="screen" rel="stylesheet" type="text/css">





</head>
<body>

	<jsp:include page="../public/left.jsp" />