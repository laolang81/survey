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
		$(document).ready(function() {
			/* Add a click handler to the rows - this could be used as a callback */
			$("#dataTables tbody tr").click(function(e) {
				if ($(this).hasClass('row_selected')) {
					$(this).removeClass('row_selected');
				} else {
					oTable.$('tr.row_selected').removeClass('row_selected');
					$(this).addClass('row_selected');
				}
			});

			/* Add a click handler for the delete row */
			$('#delete').click(function() {
				var anSelected = fnGetSelected(oTable);
				if (anSelected.length !== 0) {
					oTable.fnDeleteRow(anSelected[0]);
				}
			});

			/* Init the table */
			oTable = $('#dataTables')
				.dataTable(
					{
						"bJQueryUI" : true,
						"bProcessing" : true,
						"bServerSide" : true,
						"iDisplayLength" : 25,
						"aLengthMenu" : [ [ 25, 50, -1 ],
								[ 25, 50, "显示所有" ] ],
						"bLengthChange" : true,
						//"sPaginationType": "full_numbers",
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
						"oLanguage" : {
						//"sUrl": "cn.txt"
						},
						"aoColumns" : [
								{
									/* "mData" : "id",
									"bSearchable" : false,
									"bSortable" : false, */
									"mRender": function ( data, type, row ) {
										return '<a href=\"Details/' + row.id + '\">' + row.id + '</a>';
					                },
					                "aTargets": [ 0 ]
				                
								}, {
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

		/* Get the rows which are currently selected */
		function fnGetSelected(oTableLocal) {
			return oTableLocal.$('tr.row_selected');
		}

		
	</script>
</div>
<s:include value="../public/footer.jsp"></s:include>