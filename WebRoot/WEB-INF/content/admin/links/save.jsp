<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:form method="post"  namespace="/admin/file-upload" id="sniperForm" cssClass="form-horizontal"
	role="form" >

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
		<label for="nameTitle" class="col-sm-2 control-label">别名(用于title)</label>
		<div class="col-sm-10">
			<s:textfield name="nameTitle" cssClass="form-control" id="nameTitle" />
			<div class="help-block">
				<s:fielderror fieldName="nameTitle" />
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="url" class="col-sm-2 control-label">url</label>
		<div class="col-sm-10">
			<s:textfield name="url" cssClass="form-control" id="url" />
			<div class="help-block">
				<s:fielderror fieldName="url" />
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="url" class="col-sm-2 control-label">是否下载</label>
		<div class="col-sm-10">
			<s:checkbox name="isFile"></s:checkbox>
		</div>
	</div>


	<div class="form-group">
		<label for="attachement" class="col-sm-2 control-label">附件(图片)</label>
		<div class="col-sm-10">
			<s:textfield name="attachement" cssClass="form-control"
				id="attachement" />
			<div class="help-block">
				<s:fielderror fieldName="url" />
			</div>
		</div>
	</div>


	<div class="form-group">
		<label for="width" class="col-sm-2 control-label">图片宽度</label>
		<div class="col-sm-10">
			<s:textfield name="width" cssClass="form-control" id="width" />
			<div class="help-block">
				<s:fielderror fieldName="width" />
			</div>
		</div>
	</div>


	<div class="form-group">
		<label for="height" class="col-sm-2 control-label">图片高度</label>
		<div class="col-sm-10">
			<s:textfield name="height" cssClass="form-control" id="height" />
			<div class="help-block">
				<s:fielderror fieldName="height" />
			</div>
		</div>
	</div>


	<s:if test="sort > 0">
		<s:set name="time" value="%{sort}"></s:set>
	</s:if>
	<s:else>
		<s:set name="time" value="%{new java.util.Date().getTime()}"></s:set>
	</s:else>

	<div class="form-group">
		<label for="sort" class="col-sm-2 control-label">Sort</label>
		<div class="col-sm-10">
			<s:textfield name="sort" cssClass="form-control" id="sort"
				value="%{#time }" placeholder="sort" />
			<div class="help-block">
				<s:fielderror fieldName="sort" />
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="enabled" class="col-sm-2 control-label">启用</label>
		<div class="col-sm-10">
			<s:checkbox name="enabled"></s:checkbox>
		</div>
	</div>

	<div class="form-group">
		<label for="timeStart" class="col-sm-2 control-label">开始时间</label>
		<div class="col-sm-3">
			<s:textfield name="timeStart" cssClass="form-control" id="timeStart"
				readonly="true" data-date-format="yyyy-mm-dd hh:ii:ss">
				<s:param name="value">
					<s:date name="timeStart" format="yyyy-MM-dd HH:mm:ss" />
				</s:param>
			</s:textfield>
		</div>
	</div>

	<div class="form-group">
		<label for="timeEnd" class="col-sm-2 control-label">结束时间</label>
		<div class="col-sm-3">
			<s:textfield name="timeEnd" cssClass="form-control" id="timeEnd"
				readonly="true" data-date-format="yyyy-mm-dd hh:ii:ss">
				<s:param name="value">
					<s:date name="timeEnd" format="yyyy-MM-dd HH:mm:ss" />
				</s:param>
			</s:textfield>
		</div>
	</div>

	<div class="form-group">
		<label for="note" class="col-sm-2 control-label">备注</label>
		<div class="col-sm-10">
			<s:textarea name="note" rows="6" cssClass="form-control"
				cssStyle="height:300px"></s:textarea>
		</div>
	</div>
	
	
	<div class="form-group">
		<label for="note" class="col-sm-2 control-label">备注</label>
		<div class="col-sm-10">
			<s:file name="note"></s:file>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-10 col-md-offset-2">
			<button type="submit" class="btn btn-danger">保存</button>
		</div>
	</div>
</s:form>

<link href="myfiles/Plugin/kindeditor/themes/default/default.css"
	media="screen" rel="stylesheet" type="text/css">

<script type="text/javascript"
	src="myfiles/Plugin/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript"
	src="myfiles/Plugin/kindeditor/lang/zh_CN.js"></script>
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
	$('#timeStart').datetimepicker({
		language : 'zh-CN'
	});
	$('#timeEnd').datetimepicker({
		language : 'zh-CN'
	});

	KindEditor.ready(function(K) {
		var editor1 = KindEditor.create('textarea[name="note"]', {
			uploadJson : 'admin/file-upload/upload',
			fileManagerJson : 'admin/file-upload/htmlmanager',
			allowFileManager : true,
			allowImageUpload : true,
			urlType : 'domain',
			//filePostName : 'imgFile',
			afterBlur : function() {
				this.sync();
			}

		});

		K('#attachement').click(function() {
			editor1.loadPlugin('image', function() {
				editor1.plugin.imageDialog({
					showLocal : true,
					showRemote : true,
					imageUrl : K('#attachement').val(),
					clickFn : function(url, title) {
						K('#attachement').val(url);
						editor1.hideDialog();
					}
				});
			});
		});
	});

	
</script>