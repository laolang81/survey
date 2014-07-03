<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.freejudge.net/struts/tags" prefix="sniperPage" %>

<style>
.meneame{border-radius: 4px; box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05); display: inline-block; margin: 10px 0;line-height: 30px;}
.meneame A ,.meneame SPAN.current,.meneame SPAN.disabled{-moz-border-bottom-colors: none; -moz-border-left-colors: none; -moz-border-right-colors: none; -moz-border-top-colors: none; background-color: #FFFFFF; border-color: #DDDDDD; border-image: none; border-style: solid; border-width: 1px 1px 1px 0; float: left; line-height: 20px; padding: 4px 12px; text-decoration: none;}
.meneame A:first-child{border-bottom-left-radius: 4px; border-left-width: 1px;border-top-left-radius: 4px;}
.meneame A:last-child{border-bottom-right-radius: 4px; border-top-right-radius: 4px;}
.meneame span:first-child{border-bottom-left-radius: 4px; border-left-width: 1px;border-top-left-radius: 4px;}
.meneame span:last-child{border-bottom-right-radius: 4px; border-top-right-radius: 4px;}
.meneame A:hover{BACKGROUND-COLOR: #f5f5f5;}
.meneame A:active{BACKGROUND-COLOR: #fff}
.meneame SPAN.current{color: #999999;cursor: pointer; background-color: #F5F5F5;}
.meneame SPAN.disabled{cursor: pointer;}
</style>
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

