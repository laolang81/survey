<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<s:form method="post" id="sniperForm" cssClass="form-horizontal"
	role="form">

	<s:hidden name="id" />

	<div class="form-group">
		<label for="keyName" class="col-sm-2 control-label">Key</label>
		<div class="col-sm-10">
			<s:textfield name="keyName" cssClass="form-control" id="keyName" />
		</div>
	</div>

	<div class="form-group">
		<label for="keyValue" class="col-sm-2 control-label">Value</label>
		<div class="col-sm-10">
			<s:textfield name="keyValue" cssClass="form-control" id="keyValue" />
		</div>
	</div>

	<div class="form-group">
		<label for="autoload" class="col-sm-2 control-label">autoload</label>
		<div class="col-sm-10">
			<s:checkbox name="autoload" />
		</div>
	</div>

	<div class="form-group">
		<label for="keyInfo" class="col-sm-2 control-label">keyInfo</label>
		<div class="col-sm-10">
			<s:textarea rows="4" cssClass="form-control" name="keyInfo"
				id="keyInfo"></s:textarea>

		</div>
	</div>


	<div class="form-group">
		<div class="col-sm-10 col-md-offset-2">
			<button type="submit" class="btn btn-danger">保存</button>
		</div>
	</div>
</s:form>

