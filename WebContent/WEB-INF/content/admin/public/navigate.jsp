<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<div id="breadcrumbs">

<!-- → -->
	<div id="pma_navigation_collapser" title="关闭面板" class="fl">←</div>
	<ol class="breadcrumb">
		<li>
			<i class="fa fa-home"></i>
			<a href=''>Home</a>
		</li>
		<li>controllerName</li>
		<li class="active">${param.actionName }</li>
	</ol>
	${attr.htmlPath }${param.htmlPath }
</div>
