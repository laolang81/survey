<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:include value="../public/sniper_menu.jsp"></s:include>

<table class="table table-hover">
	<thead>
		<tr>
			<th><s:text name="ID"></s:text></th>
			<th>name</th>
			<th>note</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<s:iterator value="list">
				<td><s:checkbox fieldValue="%{id}" value="false" name="list.id" />
					<s:a action="update" target="_blank">
						<s:param name="id">${id }</s:param>
						<s:property value="id" />
					</s:a></td>
				<td><s:property value="name" /></td>
				<td><s:property value="note" /></td>
		</tr>
		</s:iterator>

	</tbody>
</table>
<div class="meneame">${pageHtml }</div>
