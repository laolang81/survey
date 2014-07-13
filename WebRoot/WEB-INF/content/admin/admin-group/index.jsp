<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:set name="sm_url">/amdin/meet-user/delete</s:set>
<s:include value="../public/sniper_menu.jsp"></s:include>

<table class="table table-hover">
	<thead>
		<tr>
			<th><s:text name="ID"></s:text></th>
			<th><s:text name="名称"></s:text></th>
			<th><s:text name="性别"></s:text></th>
			<th><s:text name="职务"></s:text></th>
			<th><s:text name="单位"></s:text></th>
			<th><s:text name="手机"></s:text></th>
			<th><s:text name="住宿要求"></s:text></th>
			<th><s:text name="报道时间"></s:text></th>
			<th><s:text name="车次"></s:text></th>
			<th><s:text name="是否接站"></s:text></th>
		</tr>
	</thead>
	<tbody>
		<tr>
		<s:iterator value="meetUsers">
			<td><s:checkbox fieldValue="%{id}" value="false" name="id" />
			<s:a action="update" target="_blank">
				<s:param name="id">${id }</s:param>
				<s:property value="id"/>
				</s:a>
			</td>
			<td><s:a action="update" target="_blank">
				<s:param name="id">${id }</s:param>
				<s:property value="name"/>
				</s:a>
			</td>
			<td><s:property value="sex"/></td>
			<td><s:property value="post"/></td>
			<td><s:property value="unit"/></td>
			<td><s:property value="mobilePhone"/></td>
			<td><s:property value="shopInfo"/></td>
			<td><s:property value="reportTime"/></td>
			<td><s:property value="carNum"/></td>
			<td><s:property value="carPeople"/></td>
		</tr>
		</s:iterator>
		
	</tbody>
</table>
<div class="meneame">${pageHtml }</div>

