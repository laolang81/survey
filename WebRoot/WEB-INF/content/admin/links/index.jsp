<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:include value="../public/sniper_menu.jsp"></s:include>

<table class="table table-hover">
	<thead>
		<tr>
			<th>ID</th>
			<th>username</th>
			<th>url</th>
			<th>groupName</th>
			<th>enabled</th>
			<th>timeEnd</th>
			<th>Sort</th>
			<th>createTime</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="list">
		<tr>
			<td><s:checkbox fieldValue="%{id}" value="false" name="list.id" />
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
			<td><s:a href="%{url}" ><s:property value="url"/></s:a> </td>
			<td><s:property value="channel.name"/></td>
			<td><s:property value="enabled"/></td>
			<td><s:date name="timeEnd" format="yyyy-mm-dd HH:mm:ss"/></td>
			<td><s:property value="sort"/></td>
			<td><s:date name="cTime" format="yyyy-mm-dd HH:mm:ss"/></td>
		</tr>
		</s:iterator>
		
	</tbody>
</table>
<div class="meneame">${pageHtml }</div>

