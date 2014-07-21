<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="seach">
	<form id="searchFrom" class="form-inline" role="form" name="searchFrom" method="get" action="">
		<div class="form-group">
			<label for="status" class="sr-only">状态</label>
			<s:select id="status" list="{true,false}" name="searchBoolean.status" cssClass="form-control" ></s:select>
			
		</div> 
		<div class="form-group">
			<label for="channel" class="sr-only">channel</label>
			<s:select list="{1,2,3,4,5}" name="searchInteger.channel" headerKey="0" headerValue="0" cssClass="form-control" ></s:select>
			
		</div> 
		<div class="form-group">
			<label for="seachName" class="sr-only">关键词</label>
			<s:textfield name="searchString.name" cssClass="form-control ml5"></s:textfield>
			
		</div>
		<div class="form-group">
			<input type="submit" value="Search" class="btn btn-success ml10" name="">
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
				<s:property value="id"/>
				</s:a>
			</td>
			<td><s:property value="name"/></td>
			<td><s:property value="statusList[status]"/></td>
			<td><s:property value="channelTop[pn_cid]"/> <s:property value="channels.name"/> </td>
			<td><s:date name="stime" format="yyyy-mm-dd HH:mm:ss"/> </td>
		</tr>
		</s:iterator>
		
	</tbody>
</table>
<div class="meneame">${pageHtml }</div>

