<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="myfiles/Plugin/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="myfiles/Plugin/kindeditor/lang/zh_CN.js"></script>
<link
	href="myfiles/Plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">

<s:form action="save" method="post" id="sniperForm"
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
		<label for="name" class="col-sm-2 control-label">性别</label>
		<div class="col-sm-2">
			<s:select list="#{ '男':'男','女':'女' }" name="sex"
				cssClass="form-control"></s:select>
		</div>
	</div>
	<div class="form-group">
		<label for="nation" class="col-sm-2 control-label">民族</label>
		<div class="col-sm-10">
			<s:textfield name="nation" cssClass="form-control" id="nation" />
			<div class="help-block">
				<s:fielderror fieldName="nation" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="post" class="col-sm-2 control-label">职务</label>
		<div class="col-sm-10">
			<s:textfield name="post" cssClass="form-control" id="post" />

		</div>
	</div>
	<div class="form-group">
		<label for="unit" class="col-sm-2 control-label">单位</label>
		<div class="col-sm-10">
			<s:textfield name="unit" cssClass="form-control" id="unit" />

		</div>
	</div>
	<div class="form-group">
		<label for="mobilePhone" class="col-sm-2 control-label">移动电话</label>
		<div class="col-sm-10">
			<s:textfield name="mobilePhone" cssClass="form-control"
				id="mobilePhone" />

		</div>
	</div>

	<div class="form-group">
		<label for="shopInfo" class="col-sm-2 control-label">住宿要求</label>
		<div class="col-sm-10">
			<s:textfield name="shopInfo" cssClass="form-control" id="shopInfo" />
		</div>
	</div>

	<div class="form-group">
		<label for="moneyType" class="col-sm-2 control-label">会务费用支付方式</label>
		<div class="col-sm-10">
			<s:textfield name="moneyType" cssClass="form-control" id="moneyType" />
		</div>
	</div>
	<div class="form-group">
		<label for="reportTime" class="col-sm-2 control-label">报道时间</label>
		<div class="col-sm-2">
			<s:textfield name="reportTime" cssClass="form-control" id="reportTime" readonly="true" data-date-format="yyyy-mm-dd hh:ii" />
		</div>
	</div>
	<div class="form-group">
		<label for="carNum" class="col-sm-2 control-label">车次(航班)</label>
		<div class="col-sm-10">
			<s:textfield name="carNum" cssClass="form-control" id="carNum" />

		</div>
	</div>
	<div class="form-group">
		<label for="carPeople" class="col-sm-2 control-label">是否接站</label>
		<div class="col-sm-10">
			<s:checkbox name="carPeople" cssClass="checkbox-inline" />
		</div>
	</div>

	<div class="form-group">
		<label for="leaveTime" class="col-sm-2 control-label">离开时间</label>
		<div class="col-sm-2">
			<s:textfield name="leaveTime" cssClass="form-control" id="leaveTime" readonly="true" data-date-format="yyyy-mm-dd hh:ii" />
		</div>
	</div>
	

	<div class="form-group">
		<label for="carLeavePeople" class="col-sm-2 control-label">是否送站</label>
		<div class="col-sm-10">
			<s:checkbox name="carLeavePeople" cssClass="checkbox-inline" />
		</div>
	</div>
	<div class="form-group">
		<label for="carLeaveNum" class="col-sm-2 control-label">返程车次(航班)</label>
		<div class="col-sm-10">
			<s:textfield name="carLeaveNum" cssClass="form-control"
				id="carLeaveNum" />

		</div>
	</div>
	<div class="form-group">
		<label for="other" class="col-sm-2 control-label">其他要求</label>
		<div class="col-sm-10">
			<s:textarea cols="4" cssClass="form-control" name="other" id="other"></s:textarea>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-10 col-md-offset-2">
			<button type="submit" class="btn btn-danger">保存</button>
		</div>
	</div>
</s:form>

<script type="text/javascript"
	src="myfiles/Plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="myfiles/Plugin/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"
	charset="UTF-8"></script>
<script type="text/javascript">
	
$('#leaveTime').datetimepicker({ language:  'zh-CN'});
$('#reportTime').datetimepicker({ language:  'zh-CN'});

</script>
<script type="text/javascript">
$(function() {
	var editor = KindEditor.create('textarea[name="other"]',{
		uploadJson : 'admin/file-upload/upload',
		fileManagerJson : 'admin/file-upload/htmlmanager',
		allowFileManager : true,
		filePostName	: "file", 
			
		afterBlur: function(){this.sync();}
	});
});
</script>
