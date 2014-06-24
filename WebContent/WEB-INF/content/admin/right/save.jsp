<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:form action="saveorupdate" namespace="/admin/right" method="post"
	id="sniperForm" cssClass="form-horizontal" role="form">
	<s:hidden name="id" />
	<s:hidden name="code" />
	<s:hidden name="pos" />
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">名称</label>
		<div class="col-sm-10">
			<s:textfield name="name" cssClass="form-control" id="name"
				placeholder="name" />
			<p class="help-block"><s:fielderror fieldName="name" /></p>
		</div>
	</div>
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">isPublic</label>
		<div class="col-sm-10">
			<s:checkbox name="isPublic" fieldValue="1"/>
			<p class="help-block"><s:fielderror fieldName="isPublic" /></p>
		</div>
	</div>
	<div class="form-group">
		<label for="isMenu" class="col-sm-2 control-label">isMenu</label>
		<div class="col-sm-10">
			<s:checkbox name="isMenu" fieldValue="1" />
			<p class="help-block"><s:fielderror fieldName="isMenu" /></p>
		</div>
	</div>
	<div class="form-group">
		<label for="url" class="col-sm-2 control-label">Url</label>
		<div class="col-sm-10">
			<s:textfield name="url" cssClass="form-control" id="url" placeholder="url" />
			<p class="help-block"><s:fielderror fieldName="url" /></p>
		</div>
	</div>
	<div class="form-group">
		<label for="desc" class="col-sm-2 control-label">desc描述</label>
		<div class="col-sm-10">
			<s:textarea rows="3" name="desc" cssClass="form-control" id="desc" placeholder="desc" />
			<p class="help-block"><s:fielderror fieldName="url" /></p>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-12 col-md-offset-2">
			<button type="submit" class="btn btn-danger">Save</button>
		</div>
	</div>
</s:form>