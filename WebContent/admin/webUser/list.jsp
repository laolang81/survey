<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:include value="../public/header.jsp"></s:include>
<script src="myfiles/Plugin/Metro/docs/js/jquery/jquery.dataTables.js"></script>
<div class="list_content">
	<table class="table striped hovered dataTable" id="dataTables-1">
		<thead>
			<tr>
				<th class="text-left">Engine</th>
				<th class="text-left">Browser</th>
				<th class="text-left">Platform</th>
				<th class="text-left">Version</th>
				<th class="text-left">CSS grade</th>
			</tr>
		</thead>

		<tbody>
		</tbody>

		<tfoot>
			<tr>
				<th class="text-left">Engine</th>
				<th class="text-left">Browser</th>
				<th class="text-left">Platform</th>
				<th class="text-left">Version</th>
				<th class="text-left">CSS grade</th>
			</tr>
		</tfoot>
	</table>
	
	<script>
		$(function() {
			$('#dataTables-1').dataTable({
				"bProcessing" : true,
				"sAjaxSource" : '<s:url action="webUserdoajaxlist" namespace="/admin" />',
				"aoColumns" : [ {
					"mData" : "nickName"
				}, {
					"mData" : "password"
				}, {
					"mData" : "email"
				}, {
					"mData" : "ctime"
				}, {
					"mData" : "nickName"
				} ]
			});
		});
	</script>
</div>
<s:include value="../public/footer.jsp"></s:include>