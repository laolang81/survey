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
		</div>
	</div>

	<div class="form-group">
		<label for="fid" class="col-sm-2 control-label">Fid</label>
		<div class="col-sm-10">
			<s:select list="channelTop" listKey="key" listValue="value" name="fid"></s:select>
		</div>
	</div>
	
	<s:if test="order > 0">
		<s:set name="time" value="%{order}"></s:set>
	</s:if>
	<s:else>
		<s:set name="time" value="%{new java.util.Date().getTime()}"></s:set>
	</s:else>
	
	<div class="form-group">
		<label for="sort" class="col-sm-2 control-label">排序</label>
		<div class="col-sm-10">
			<s:textfield name="sort" cssClass="form-control" id="sort" value="%{#time }" placeholder="sort" />
			<div class="help-block"><s:fielderror fieldName="sort" /></div>
		</div>
	</div>
	
	
	<div class="form-group">
		<label for="status" class="col-sm-2 control-label">状态</label>
		<div class="col-sm-10">
			<s:checkbox name="status" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="status" class="col-sm-2 control-label">类型</label>
		<div class="col-sm-10">
			<s:select list="channelType" listKey="key" listValue="value" name="showType"></s:select>
		</div>
	</div>
	
	<div class="form-group">
		<label for="Url" class="col-sm-2 control-label">Url</label>
		<div class="col-sm-10">
			<s:textfield name="Url" cssClass="form-control" id="Url"  />
			<div class="help-block"><s:fielderror fieldName="Url" /></div>
		</div>
	</div>

	<div class="form-group">
		<label for="note" class="col-sm-2 control-label">note</label>
		<div class="col-sm-10">
			<s:textarea rows="4" cssClass="form-control" name="note"
				id="note"></s:textarea>

		</div>
	</div>


	<div class="form-group">
		<div class="col-sm-10 col-md-offset-2">
			<button type="submit" class="btn btn-danger">保存</button>
		</div>
	</div>
</s:form>

