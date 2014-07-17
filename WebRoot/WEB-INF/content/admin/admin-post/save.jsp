<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<s:form method="post" id="sniperForm" cssClass="form-horizontal"
	role="form">

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
		<label for="source" class="col-sm-2 control-label">来源</label>
		<div class="col-sm-10">
			<s:textfield name="source" cssClass="form-control" id="source" />
			<div class="help-block">
				<s:fielderror fieldName="source" />
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="nickName" class="col-sm-2 control-label">Sort</label>
		<div class="col-sm-10">
			<s:textfield name="order" cssClass="form-control" id="order" />

		</div>
	</div>

	<div class="form-group">
		<label for="adminGroup" class="col-sm-2 control-label">栏目组</label>
		<div class="col-sm-10">
			<s:checkboxlist list="channelTop" value="channelChecked"
				listKey="key" listValue="value" name="channelsPost" cssClass=""></s:checkboxlist>
		</div>
	</div>

	<div class="form-group">
		<label for="enabled" class="col-sm-2 control-label">状态</label>
		<div class="col-sm-2">
			<s:select list="statusList" name="status"></s:select>
		</div>
	</div>

	<div class="form-group">
		<label for="locked" class="col-sm-2 control-label">内容</label>
		<div class="col-sm-10">
			<s:textarea rows="6" cssStyle="height:400px" name="postValue.value" cssClass="form-control"></s:textarea>
		</div>
	</div>
	<s:hidden name="postValue.id"></s:hidden>

	<div class="form-group">
		<div class="col-sm-10 col-md-offset-2">
			<button type="submit" class="btn btn-danger">保存</button>
		</div>
	</div>
</s:form>
<script type="text/javascript" src="myfiles/Plugin/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="myfiles/Plugin/kindeditor/lang/zh_CN.js"></script>
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
	
	
	$(function() {
		var editor = KindEditor.create('textarea[name="postValue.value"]',{
			uploadJson : 'admin/file-upload/upload',
			fileManagerJson : 'admin/file-upload/htmlmanager',
			allowFileManager : true,
				
			afterBlur: function(){this.sync();}
		});
	});
</script>

