<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:include value="../public/header.jsp"></s:include>

<s:form action="userDoSave" namespace="/admin" method="post"
	id="surveyAdd" cssClass="SFrom">



	<fieldset>
		<legend>Legend</legend>
		<label><s:text name="username" /></label>
		<div data-role="input-control" class="input-control text">
			<s:textfield name="name" id="username" placeholder="type text" />
			<span class="help-inline" title=""><s:fielderror name="name" /></span>
			<button tabindex="-1" class="btn-clear" type="button"></button>
		</div>

		<label><s:text name="username" /></label>
		<div data-role="input-control" class="input-control text">
			<s:textfield name="nickName" id="nickName" />
			<span class="help-inline" title=""><s:fielderror
					name="nickName" /></span>
			<button tabindex="-1" class="btn-clear" type="button"></button>
		</div>



		<label>Label name</label>
		<div data-role="input-control" class="input-control password">
			<s:textfield name="password" id="password"
				placeholder="type password" />
			<span class="help-inline" title=""><s:fielderror
					name="password" /></span>
			<button tabindex="-1" class="btn-reveal" type="button"></button>
		</div>

		<label>Label name</label>
		<div data-role="input-control" class="input-control password">
			<s:textfield name="confirmPassword" id="confirmPassword"
				placeholder="type password" />
			<span class="help-inline" title=""><s:fielderror
					name="confirmPassword" /></span>
			<button tabindex="-1" class="btn-reveal" type="button"></button>
		</div>

		<label><s:text name="email" /></label>
		<div data-role="input-control" class="input-control text">
			<s:textfield name="email" id="email" />
			<span class="help-inline" title=""><s:fielderror name="email" /></span>
			<button tabindex="-1" class="btn-clear" type="button"></button>
		</div>
		<div>
		<div data-role="input-control" class="input-control switch">
			<label style="margin-right: 20px" class="inline-block">
				Switch me <input type="checkbox" checked=""> <span
				class="check"></span>
			</label>

		</div>
		</div>



		<input type="submit" class="button success" value="Submit">


	</fieldset>
</s:form>


<s:include value="../public/footer.jsp"></s:include>