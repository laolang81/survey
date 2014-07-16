<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
				<s:property value="id"/>
				</s:a>
			</td>
			<td><s:a action="update" target="_blank">
				<s:param name="id">${id }</s:param>
				<s:property value="name"/>
				</s:a>
			</td>
			<td><s:property value="statusList[status]"/></td>
			<td><s:property value="channelTop[pn_cid]"/> <s:property value="channels.name"/> </td>
			<td><s:date name="stime" format="yyyy-mm-dd HH:mm:ss"/> </td>
		</tr>
		</s:iterator>
		
	</tbody>
</table>
<div class="meneame">${pageHtml }</div>

