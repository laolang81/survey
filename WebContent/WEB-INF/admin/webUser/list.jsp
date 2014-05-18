<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:include value="../public/header.jsp"></s:include>
<script src="myfiles/Plugin/Metro/docs/js/jquery/jquery.dataTables.js"></script>
<div class="list_content">
	<a id="delete" href="javascript:;">delete</a>
	<table class="table striped hovered dataTable" id="dataTables">
		<thead>
			<tr>
				<th class="text-left">ID</th>
				<th class="text-left">NickName</th>
				<th class="text-left">Password</th>
				<th class="text-left">Email</th>
				<th class="text-left">Create Time</th>
			</tr>
		</thead>

		<tbody>
		</tbody>

		<tfoot>
			<tr>
				<th class="text-left">ID</th>
				<th class="text-left">NickName</th>
				<th class="text-left">Password</th>
				<th class="text-left">Email</th>
				<th class="text-left">Create Time</th>
			</tr>
		</tfoot>
	</table>

	<script>
	
	$(function() {
		$('#dataTables')
				.dataTable(
						{
							"bJQueryUI"		: true,
							"bProcessing" : true,
							"bServerSide": false,
							/*使用post方式
							"fnServerData": function ( sSource, aoData, fnCallback ) {
					            $.ajax( {
					                "dataType": 'json',
					                "type": "POST",
					                "url": sSource,
					                "data": aoData,
					                "success": fnCallback
					            } );
					        }*/
					        "sAjaxSource" : "<s:url action="webUserdoajaxlist" namespace="/admin" />",
							"oLanguage": {
								//"sUrl": "cn.txt"
							},
							
							"aoColumns" : [ {
								"mRender": function ( data, type, row ) {
									return '<a href=\"Details/' + row.id + '\">' + row.id + '</a>';
				                },
				                "aTargets": [ 0 ]
							},  {
								"mData" : "nickName"
							}, {
								"mData" : "password"
							}, {
								"mData" : "email"
							}, {
								"mData" : "ctime"
							} ]
						});
	});
	
	
		
	</script>
</div>
<s:include value="../public/footer.jsp"></s:include>