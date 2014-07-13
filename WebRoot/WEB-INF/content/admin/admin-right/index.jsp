<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:set name="sm_url">/admin-right/delete</s:set>
<s:include value="../public/sniper_menu.jsp"></s:include>

<table class="table table-hover">
	<thead>
		<tr>
			<th><s:text name="table.id"></s:text></th>
			<th><s:text name="table.name"></s:text></th>
			<th><s:text name="table.url"></s:text></th>
			<th><s:text name="table.theMenu"></s:text></th>
			<th><s:text name="table.thePublic"></s:text></th>
			<th><s:text name="table.theShow"></s:text></th>
			<th><s:text name="table.pos"></s:text></th>
			<th><s:text name="table.code"></s:text></th>
		</tr>
	</thead>
	<tbody>
		
		<s:iterator value="list">
		<tr id="sl_<s:property value="id"/>">
			<td><s:checkbox fieldValue="%{id}" name="list.id" />
				<s:a action="update" target="_blank">
				<s:param name="id">${id }</s:param>
				<s:property value="id"/>
				</s:a>
			</td>
			<td><s:property value="name"/></td>
			<td><s:property value="url"/></td>
			<td><s:property value="theMenu"/></td>
			<td><s:property value="thePublic"/></td>
			<td><s:property value="theShow"/></td>
			<td><s:property value="pos"/></td>
			<td><s:property value="code"/></td>
		</tr>
		</s:iterator>
		
	</tbody>
</table>
<div class="meneame">${pageHtml }</div>