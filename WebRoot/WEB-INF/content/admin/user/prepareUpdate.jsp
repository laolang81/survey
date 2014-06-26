<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:include value="../public/header.jsp"></s:include>

<s:form action="user-save" namespace="/admin" method="post" id="surveyAdd" cssClass="SFrom">
	<ul>
		<li>
			<div class="w100"><s:text name="username"/></div>
			<div><s:textfield name="name" title="name"/><s:fielderror name="name"/></div>
		</li>
		<li><s:textfield name="nickName"/><s:fielderror name="nickName"/></li>
		<li><s:password  name="password"/><s:fielderror name="password"/></li>
		<li><s:password  name="confirmPassword"/><s:fielderror name="confirmPassword"/></li>
		<li><s:textfield name="email" /><s:fielderror name="email"/></li>
		
		<li><s:submit name="sava" key="save" cssClass="subs" /> </li>
 	
	</ul>
	
	

</s:form>


<s:include value="../public/footer.jsp"></s:include>