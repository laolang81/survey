<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<s:form method="post"
	id="sniperForm" cssClass="form-horizontal" role="form">
	<s:token/>
	<s:hidden name="id" />
	<s:hidden name="code" />
	<s:hidden name="pos" />
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">名称</label>
		<div class="col-sm-10">
			<s:textfield name="name" cssClass="form-control" id="name"
				placeholder="name" />
			<div class="help-block"><s:fielderror fieldName="name" /></div>
		</div>
	</div>
	<div class="form-group">
		<label for="thePublic" class="col-sm-2 control-label">thePublic</label>
		<div class="col-sm-10">
			<s:checkbox name="thePublic" />
			<div class="help-block"><s:fielderror fieldName="thePublic" /></div>
		</div>
	</div>
	<div class="form-group">
		<label for="theMenu" class="col-sm-2 control-label">theMenu</label>
		<div class="col-sm-10">
			<s:checkbox name="theMenu"/>
			<div class="help-block"><s:fielderror fieldName="theMenu" /></div>
		</div>
	</div>
	<div class="form-group">
		<label for="theShow" class="col-sm-2 control-label">theShow</label>
		<div class="col-sm-10">
			<s:checkbox name="theShow"  />
			<div class="help-block"><s:fielderror fieldName="theShow" /></div>
		</div>
	</div>
	<div class="form-group">
		<label for="url" class="col-sm-2 control-label">Url</label>
		<div class="col-sm-10">
			<s:textfield name="url" cssClass="form-control" id="url" placeholder="url" />
			<div class="help-block">注意url地址 / 等于 /index struts出现斜杠会自动寻找index,请在添加url的时候不要加index,只写/即可</div>
		</div>
	</div>
	
	<div class="form-group">
		<label for="fid" class="col-sm-2 control-label">Fid</label>
		<div class="col-sm-10">
			<s:select list="adminRights" listKey="key" listValue="value" name="fid"></s:select>
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
			<s:textfield name="sort" cssClass="form-control" id="sort" value="%{#time }" placeholder="sort" />
			<div class="help-block"><s:fielderror fieldName="sort" /></div>
		</div>
	</div>
	<div class="form-group">
		<label for="note" class="col-sm-2 control-label">desc描述</label>
		<div class="col-sm-10">
			<s:textarea rows="3" name="note" cssClass="form-control" id="desc" placeholder="note" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-10 col-md-offset-2">
			<button type="submit" class="btn btn-danger">Save</button>
		</div>
	</div>
</s:form>