<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="seach">
	<form id="searchFrom" class="form-inline" role="form" name="searchFrom"
		method="get" action="">
		<div class="form-group">
			<label for="searchInteger_status" class="sr-only">状态</label>
			<s:select list="statusList" name="searchInteger.status"
				cssClass="form-control" />
		</div>
		<div class="form-group">
			<label for="searchInteger_channel" class="sr-only">channel</label>
			<s:select list="channelTop" name="searchInteger.channel"
				headerKey="" headerValue="select" cssClass="form-control" />

		</div>
		<div class="form-group">
			<label for="searchString_name" class="sr-only">关键词</label>
			<s:textfield name="searchString.name" cssClass="form-control" />

		</div>
		<div class="form-group">
			<input type="submit" value="Search" class="btn btn-success ml10"
				name="">
		</div>
	</form>
</div>

<s:include value="../public/sniper_menu.jsp"></s:include>

<table class="table table-hover">
	<thead>
		<tr>
			<th>ID</th>
			<th>标题</th>
			<th>状态</th>
			<th>栏目</th>
			<th>发布时间</th>
		</tr>
	</thead>
	<tbody>
	
		<s:iterator value="list">
			<tr>
				<td><s:checkbox fieldValue="%{id}" value="false" name="list.id" />
					<s:a action="update" target="_blank">
						<s:param name="id">${id }</s:param>
						<s:property value="id" />
					</s:a></td>
				<td><s:property value="name" /></td>
				<td><s:property value="statusList[status]" /></td>
				<td><s:iterator value="channels"><s:property value="name"/>,</s:iterator> </td>
				<td><s:date name="stime" format="yyyy-mm-dd HH:mm:ss" /></td>
			</tr>
		</s:iterator>

	</tbody>
</table>
<div class="meneame">${pageHtml }</div>

