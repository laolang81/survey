<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:include value="../public/header.jsp"></s:include>

<s:form action="userDoAdd" namespace="/admin" method="post"
	id="surveyAdd" cssClass="SFrom">
	<s:hidden name="id" />
	<fieldset>
		<legend>Legend</legend>
		<label><s:text name="username" /></label>
		<div data-role="input-control" class="input-control text">
			<s:textfield name="name" id="username" placeholder="type text"
				size="100" />
			<span class="help-inline" title=""><s:fielderror
					fieldName="name" /></span>
			<button tabindex="-1" class="btn-clear" type="button"></button>
		</div>

		<label><s:text name="username" /></label>
		<div data-role="input-control" class="input-control text">
			<s:textfield name="url" id="url" />
			<span class="help-inline" title=""><s:fielderror
					fieldName="url" /></span>
			<button tabindex="-1" class="btn-clear" type="button"></button>
		</div>

		<label>Label name</label>
		<div data-role="input-control" class="input-control password">
			<s:password name="desc" id="desc" placeholder="type password" />
			<span class="help-inline" title=""><s:fielderror
					fieldName="desc" /></span>
			<button tabindex="-1" class="btn-reveal" type="button"></button>
		</div>

		<label>Label name</label>
		<div data-role="input-control" class="input-control password">
			<s:password name="pos" id="pos"
				placeholder="type password" />
			<span class="help-inline" title=""><s:fielderror
					fieldName="pos" /></span>
			<button tabindex="-1" class="btn-reveal" type="button"></button>
		</div>

		<label><s:text name="email" /></label>
		<div data-role="input-control" class="input-control text">
			<s:textfield name="code" id="code" />
			<span class="help-inline" title=""><s:fielderror
					fieldName="code" /></span>
			<button tabindex="-1" class="btn-clear" type="button"></button>
		</div>
		<label><s:text name="user group" /></label>
		<div data-role="input-control" class="input-control select">
			<s:select list="adminGroupsSelect" listKey="value" listValue="name"
				name="au_group"></s:select>
		</div>
		



		<input type="submit" class="button success" value="Submit">


	</fieldset>
</s:form>


<s:include value="../public/footer.jsp"></s:include>