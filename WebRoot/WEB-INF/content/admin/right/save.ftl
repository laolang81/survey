<title>权限保存更新</title>

<@s.form action="save"  method="post" id="sniperForm" cssClass="form-horizontal" role="form">
	<@s.hidden name="id" />
	<@s.hidden name="code" />
	<@s.hidden name="pos" />
	<input name="_csrf" type="hidden" value="${token?if_exists }" />
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">名称</label>
		<div class="col-sm-10">
			<@s.textfield name="name" cssClass="form-control" id="name" placeholder="name" />
			<div class="help-block"><@s.fielderror fieldName="name" /></div>
		</div>
	</div>
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">isPublic</label>
		<div class="col-sm-10">
			<@s.checkbox name="isPublic" fieldValue="1"/>
			<div class="help-block"><@s.fielderror fieldName="isPublic" /></div>
		</div>
	</div>
	<div class="form-group">
		<label for="isMenu" class="col-sm-2 control-label">isMenu</label>
		<div class="col-sm-10">
			<@s.checkbox name="isMenu" fieldValue="1" />
			<div class="help-block"><@s.fielderror fieldName="isMenu" /></div>
		</div>
	</div>
	<div class="form-group">
		<label for="url" class="col-sm-2 control-label">Url</label>
		<div class="col-sm-10">
			<@s.textfield name="url" cssClass="form-control" id="url" placeholder="url" />
			<div class="help-block"><@s.fielderror fieldName="url" /></div>
		</div>
	</div>
	<div class="form-group">
		<label for="desc" class="col-sm-2 control-label">desc描述</label>
		<div class="col-sm-10">
			<@s.textarea rows="3" name="desc" cssClass="form-control" id="desc" placeholder="desc" />
			<div class="help-block"><@s.fielderror fieldName="desc" /></div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-12 col-md-offset-2">
			<button type="submit" class="btn btn-danger">Save</button>
		</div>
	</div>
</@s.form>