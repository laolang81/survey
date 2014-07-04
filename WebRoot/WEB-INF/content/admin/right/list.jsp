<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<table class="table table-hover">
	<thead>
		<tr>
			<th><s:text name="table.id"></s:text></th>
			<th><s:text name="table.name"></s:text></th>
			<th><s:text name="table.url"></s:text></th>
			<th><s:text name="table.theMenu"></s:text></th>
			<th><s:text name="table.thePublic"></s:text></th>
			<th><s:text name="table.thePublic"></s:text></th>
			<th><s:text name="table.pos"></s:text></th>
			<th><s:text name="table.code"></s:text></th>
		</tr>
	</thead>
	<tbody>
		<tr>
		<s:iterator value="allRight">
			<td><s:checkbox fieldValue="%{id}" value="false" name="id" /> <s:property value="id"/></td>
			<td><s:a action="update" target="_blank">
				<s:param name="id">${id }</s:param>
				<s:property value="name"/>
				</s:a>
			</td>
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