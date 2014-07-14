<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<s:form method="post" id="sniperForm" cssClass="form-horizontal"
	role="form">

	<s:hidden name="id" />

	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">名称</label>
		<div class="col-sm-10">
			<s:textfield name="name" cssClass="form-control" id="name"
				readonly="true" />
		</div>
	</div>

	<div class="form-group">
		<label for="nickName" class="col-sm-2 control-label">昵称</label>
		<div class="col-sm-10">
			<s:textfield name="nickName" cssClass="form-control" id="nickName" />
		</div>
	</div>

	<div class="form-group">
		<label for="email" class="col-sm-2 control-label">Email</label>
		<div class="col-sm-10">
			<s:textfield name="email" cssClass="form-control" id="email" />
			<div class="help-block">
				<s:fielderror fieldName="email" />
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="password_old" class="col-sm-2 control-label">旧密码</label>
		<div class="col-sm-10">
			<s:password name="password_old" cssClass="form-control"
				id="password_old" />
			<div class="help-block">
				<s:fielderror fieldName="password_old" />
				<s:actionerror name="password_old"  />
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="password_new" class="col-sm-2 control-label">新密码</label>
		<div class="col-sm-10">
			<s:password name="password_new" cssClass="form-control"
				id="password_new" />
			<div class="help-block">
				<s:fielderror fieldName="password_new" />
				<s:actionerror name="password_new"  />
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="password_c" class="col-sm-2 control-label">确认密码</label>
		<div class="col-sm-10">
			<s:password name="password_c" cssClass="form-control" id="password_c" />
			<div class="help-block">
				<s:fielderror fieldName="password_c" />
				<s:actionerror name="password_c" />
			</div>
		</div>
	</div>


	<div class="form-group">
		<div class="col-sm-10 col-md-offset-2">
			<button type="submit" class="btn btn-danger">保存</button>
		</div>
	</div>
</s:form>