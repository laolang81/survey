<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:include value="../public/header.jsp"></s:include>

<s:form action="rightsaveorupdate" namespace="/admin" method="post"
	id="doAdd" cssClass="SFrom">
	<s:hidden name="id" />
	<s:hidden name="code" />
	<s:hidden name="pos" />
	<fieldset>
		<legend>Legend</legend>
		<label>名称</label>
		<div data-role="input-control" class="input-control text size3">
			<s:textfield name="name" cssClass="size3" id="username"
				placeholder="type text" size="100" />
			<span class="help-inline" title=""><s:fielderror
					fieldName="name" /></span>
			<button tabindex="-1" class="btn-clear" type="button"></button>
		</div>

		<label>Public</label>
		<div class="input-control checkbox size3" data-role="input-control">
			<label><s:checkbox name="isPublic" /> <span class="check"></span>
				<s:fielderror fieldName="isPublic" /> </label>
		</div>

		<label>Menu</label>
		<div class="input-control checkbox size3" data-role="input-control">
			<label><s:checkbox name="isMenu" /> <span class="check"></span>
				<s:fielderror fieldName="isMenu" /></label>
		</div>

		<label>Url</label>
		<div data-role="input-control" class="input-control text size7">
			<s:textfield name="url" cssClass="size7" id="url" />
			<span class="help-inline" title=""><s:fielderror
					fieldName="url" /></span>
			<button tabindex="-1" class="btn-clear" type="button"></button>
		</div>

		<label>desc描述</label>

		<div class="input-control textarea">
			<s:textarea cols="20" rows="2" name="desc" id="desc" />
			<span class="help-inline" title=""><s:fielderror
					fieldName="desc" /></span>
		</div>

		<input type="submit" class="button success" value="Submit">

	</fieldset>
</s:form>


<s:include value="../public/footer.jsp"></s:include>