<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<title><s:property value="webPageTitle"/></title>
<s:set name="sm_url">/amdin/admin-user/delete</s:set>
<s:include value="../public/sniper_menu.jsp"></s:include>
<s:debug></s:debug>
<table class="table table-hover">
	<thead>
		<tr>
			<th>ID</th>
			<th>username</th>
			<th>nickname</th>
			<th>email</th>
			<th>enables</th>
			<th>usernameExpired</th>
			<th>passwordExpired</th>
			<th>locked</th>
			<th>createTime</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="list">
		<tr>
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
			<td><s:property value="nickName"/></td>
			<td><s:property value="email"/></td>
			<td><s:property value="enables"/></td>
			<td><s:property value="usernameExpired"/></td>
			<td><s:property value="passwordExpired"/></td>
			<td><s:property value="locked"/></td>
			<td><s:date name="ctime" format="yyyy-mm-dd HH:mm:ss"/> </td>
		</tr>
		</s:iterator>
		
	</tbody>
</table>
<div class="meneame">${pageHtml }</div>

