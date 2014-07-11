<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:debug></s:debug>

<s:form method="post" id="sniperForm" cssClass="form-horizontal"
	role="form">
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
		<label for="nickName" class="col-sm-2 control-label">昵称</label>
		<div class="col-sm-10">
			<s:textfield name="nickName" cssClass="form-control" id="nickName" />
			<div class="help-block">
				<s:fielderror fieldName="nickName" />
			</div>
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
		<label for="password" class="col-sm-2 control-label">密码</label>
		<div class="col-sm-10">
			<s:password name="password" cssClass="form-control" id="password" />
			<div class="help-block">
				<s:fielderror fieldName="password" />
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label for="password_c" class="col-sm-2 control-label">确认密码</label>
		<div class="col-sm-10">
			<s:password name="password_c" cssClass="form-control" id="password_c" />
			<div class="help-block">
				<s:fielderror fieldName="password_c" />
				<s:actionerror name="password_c"/>
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label for="adminGroup" class="col-sm-2 control-label">用户组</label>
		<div class="col-sm-10">
			<s:checkboxlist list="adminGroups" value="valueFromGroups" listKey="id" listValue="name" name="fromGroups" cssClass=""></s:checkboxlist>
			
		</div>
	</div>
	
	
	<div class="form-group">
		<label for="enables" class="col-sm-2 control-label">启用</label>
		<div class="col-sm-2">
			<s:checkbox name="enables"></s:checkbox>
		</div>
	</div>
	
	<div class="form-group">
		<label for="usernameExpired" class="col-sm-2 control-label">用户名过期时间</label>
		<div class="col-sm-3">
			<s:textfield name="usernameExpired" cssClass="form-control"
				id="usernameExpired" readonly="true" data-date-format="yyyy-mm-dd hh:ii:ss">
				<s:param name="value">
					<s:date name="usernameExpired" format="yyyy-MM-dd HH:mm:ss" />
				</s:param>
			</s:textfield>
		</div>
	</div>
	
	<div class="form-group">
		<label for="passwordExpired" class="col-sm-2 control-label">密码过期时间</label>
		<div class="col-sm-3">
			<s:textfield name="passwordExpired" cssClass="form-control"
				id="passwordExpired" readonly="true" data-date-format="yyyy-mm-dd hh:ii:ss">
				<s:param name="value">
					<s:date name="passwordExpired" format="yyyy-MM-dd HH:mm:ss" />
				</s:param>
			</s:textfield>
		</div>
	</div>
	
	<div class="form-group">
		<label for="locked" class="col-sm-2 control-label">锁定</label>
		<div class="col-sm-2">
			<s:checkbox name="locked"></s:checkbox>
		</div>
	</div>
	
	<div class="form-group">
		<label for="sort" class="col-sm-2 control-label">Sort</label>
		<div class="col-sm-10">
			<s:textfield name="sort" cssClass="form-control" id="sort" />
			<div class="help-block">
				<s:fielderror fieldName="sort" />
			</div>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-10 col-md-offset-2">
			<button type="submit" class="btn btn-danger">保存</button>
		</div>
	</div>
</s:form>

<link
	href="myfiles/Plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
	
<script type="text/javascript"
	src="myfiles/Plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="myfiles/Plugin/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"
	charset="UTF-8"></script>
<script type="text/javascript">
	$('#usernameExpired').datetimepicker({
		language : 'zh-CN'
	});
	$('#passwordExpired').datetimepicker({
		language : 'zh-CN'
	});
</script>

