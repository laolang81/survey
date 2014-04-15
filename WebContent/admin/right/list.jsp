<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:include value="../public/header.jsp"></s:include>
<script src="myfiles/Plugin/DataTables/media/js/jquery.dataTables.js"></script>
<div class="list_content">
	<table class="table striped hovered dataTable" id="dataTables-1">
		<thead>
			<tr>
				<th class="text-left">ID</th>
				<th class="text-left">Name</th>
				<th class="text-left">Url</th>
				<th class="text-left">Code</th>
				<th class="text-left">Pos</th>
				<th class="text-left">Publc</th>
			</tr>
		</thead>

		<tbody>
		</tbody>

		<tfoot>
			<tr>
				<th class="text-left">ID</th>
				<th class="text-left">Name</th>
				<th class="text-left">Url</th>
				<th class="text-left">Code</th>
				<th class="text-left">Pos</th>
				<th class="text-left">Publc</th>
			</tr>
		</tfoot>
	</table>

	<script>
		$(function() {
			$('#dataTables-1')
					.dataTable(
							{
								"bJQueryUI"		: true,
								"bProcessing" : true,
								"bServerSide": true,
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
						        "sAjaxSource" : "<s:url action="rightdoajaxlist" namespace="/admin" />",
								"oLanguage": {
									//"sUrl": "cn.txt"
								},
								
								"aoColumns" : [ {
									"mData" : "id"
								}, {
									"mData" : "name"
								}, {
									"mData" : "url"
								}, {
									"mData" : "code"
								}, {
									"mData" : "pos"
								}, {
									"mData" : "public"
								} ]
							});
		});
	</script>
</div>
<s:include value="../public/footer.jsp"></s:include>