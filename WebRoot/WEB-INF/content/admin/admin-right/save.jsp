<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title><s:property value="webPageTitle"/></title>

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
			<s:checkbox name="theMenu" value="1" />
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
			<div class="help-block"><s:fielderror fieldName="url" /></div>
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
		<label for="desc" class="col-sm-2 control-label">desc描述</label>
		<div class="col-sm-10">
			<s:textarea rows="3" name="desc" cssClass="form-control" id="desc" placeholder="desc" />
			<div class="help-block"><s:fielderror fieldName="desc" /></div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-10 col-md-offset-2">
			<button type="submit" class="btn btn-danger">Save</button>
		</div>
	</div>
</s:form>