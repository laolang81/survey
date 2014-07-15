<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:include value="../public/sniper_menu.jsp"></s:include>

<table class="table table-hover">
	<thead>
		<tr>
			<th><s:text name="ID"></s:text></th>
			<th>Key</th>
			<th>Value</th>
			<th>Msg</th>
			<th>AutoLoad</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<s:iterator value="list">
				<td><s:checkbox fieldValue="%{id}" value="false" name="id" />
					<s:a action="update" target="_blank">
						<s:param name="id">${id }</s:param>
						<s:property value="id" />
					</s:a>
				</td>
				<td><s:property value="keyName" /></td>
				<td><s:property value="keyValue" /></td>
				<td><s:property value="keyInfo" /></td>
				<td><s:property value="autoload" /></td>
		</tr>
		</s:iterator>

	</tbody>
</table>
<div class="meneame">${pageHtml }</div>
