<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../public/header.jsp"/>
<jsp:include page="../public/left.jsp"/>
<s:form action="userDoAdd" namespace="/admin" method="post"
	id="surveyAdd" cssClass="SFrom">
	<ul>
		<li>
			<div class="w100">
				<s:text name="username" />
			</div>
			<div>
				<s:textfield name="name" title="name" />
				<s:fielderror name="name" />
			</div>
		</li>

		<li>
			<div class="w100">
				<s:text name="username" />
				用户昵称
			</div>
			<div>
				<s:textfield name="nickName" />
				<s:fielderror name="nickName" />
			</div>
		</li>

		<li>
			<div class="w100">
				<s:text name="username" />
				用户密码
			</div>
			<div>
				<s:password name="password" />
				<s:fielderror name="password" />
			</div>
		</li>

		<li>
			<div class="w100">
				<s:text name="username" />
				用户确认密码
			</div>
			<div>
				<s:password name="confirmPassword" />
				<s:fielderror name="confirmPassword" />
			</div>
		</li>

		<li>
			<div class="w100">
				<s:text name="username" />
				Email
			</div>
			<div>
				<s:textfield name="email" />
				<s:fielderror name="email" />
			</div>
		</li>

		<li>
			<div class="w100">
				<s:text name="username" />
				用户组
			</div>
			<div>
				<s:select name="group" list="%{#attr.adminGroupsSelect.iterator}"
					listKey="name" listValue="value" />
				<s:fielderror name="email" />
			</div>
		</li>
		<li>
			<div class="w100">
				<s:text name="username" />
				状态
			</div>
			<div>
				<s:select list="#{0:'关闭',1:'开启'} " name="status" />			
				<s:fielderror name="status" />
			</div>
		</li>

		<li><s:submit name="" key="save" cssClass="subs" /></li>

	</ul>



</s:form>

<jsp:include page="../public/footer.jsp"/>