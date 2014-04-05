<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:include value="../public/header.jsp"></s:include>

<s:form action="user-save" namespace="/admin" method="post"
	id="surveyAdd" cssClass="SFrom">
	<ul>
		<li>
			<label for="username" class="contro-label">
				<s:text name="username"  />
			</label>
			<div class="controls">
				<s:textfield name="name" id="username"/>
				<span class="help-inline" title=""><s:fielderror name="name" /></span>
			</div>
		</li>
		
		<li>
			<label for="nickName" class="contro-label">
				<s:text name="nickName" />
			</label>
			<div class="controls">
				<s:textfield name="nickName" id="nickName" />
				<span class="help-inline" title=""><s:fielderror name="nickName" /></span>
			</div>
		</li>
		
		<li>
			<label for="username" class="contro-label">
				<s:text name="password" />
			</label>
			<div class="controls">
				<s:textfield name="password"  id="password"/>
				<span class="help-inline" title=""><s:fielderror name="password" /></span>
			</div>
		</li>
		
		<li>
			<label for="username" class="contro-label">
				<s:text name="confirmPassword"  />
			</label>
			<div class="controls">
				<s:textfield name="confirmPassword"  id="confirmPassword" />
				<span class="help-inline" title=""><s:fielderror name="name" /></span>
			</div>
		</li>
		
		<li>
			<label for="email" class="contro-label">
				<s:text name="email"  />
			</label>
			<div class="controls">
				<s:textfield name="email"  id="email" />
				<span class="help-inline" title=""><s:fielderror name="email" /></span>
			</div>
		</li>

		<li><s:submit name="sava" key="save" cssClass="subs" /></li>

	</ul>



</s:form>


<s:include value="../public/footer.jsp"></s:include>