<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<s:form method="post" id="sniperForm"
	cssClass="form-horizontal" role="form">
	<s:token />
	<s:hidden name="id" />

	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">名称</label>
		<div class="col-sm-10">
			<s:textfield name="name" cssClass="form-control" id="name" />
			<div class="help-block">
				<s:fielderror fieldName="name" />
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="value" class="col-sm-2 control-label">值</label>
		<div class="col-sm-10">
			<s:textfield name="value" cssClass="form-control" id="value" />
			<div class="help-block">
				一般以ROLE_开头,比如ROLE_ADMIN,ROLE_USER,ROLE_NAME等
				<s:fielderror fieldName="value" />
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="adminGroup" class="col-sm-2 control-label">权限列表</label>
		<div class="col-sm-10">
			<s:checkboxlist list="adminRights" value="valueFromRights"
				listKey="id" listValue="name" name="fromRight" cssClass=""></s:checkboxlist>
		</div>
	</div>

	<div class="form-group">
		<label for="note" class="col-sm-2 control-label">note</label>
		<div class="col-sm-10">
			<s:textarea rows="4" cssClass="form-control" name="note" id="note"></s:textarea>

		</div>
	</div>


	<div class="form-group">
		<div class="col-sm-10 col-md-offset-2">
			<button type="submit" class="btn btn-danger">保存</button>
		</div>
	</div>
</s:form>

