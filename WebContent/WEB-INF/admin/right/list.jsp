<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:include value="../public/header.jsp"></s:include>

<s:include value="../public/navigate.jsp">
	<s:param name="actionName">ddddddddddddddd</s:param>
</s:include>

<style type="text/css">
      @import "myfiles/Plugin/DataTables/media/css/demo_page.css";
      @import "myfiles/Plugin/DataTables/media/css/demo_table.css";
</style>
<script src="myfiles/Plugin/DataTables/media/js/jquery.dataTables.js"></script>
${attr.htmlPath }
<div class="list_content">
	<table class="table striped hovered dataTable" id="dataTables">
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
			$('#dataTables')
					.dataTable(
							{
								
								"bJQueryUI"		: false,
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
								"sAjaxSource" : '<s:url action="rightListAjax" namespace="/admin" />',
								/* "oLanguage" : {
									"sUrl": "cn.txt"
								}, */
								/* "aoColumnDefs": [
							                        { "sClass": "center", "aTargets": [0,1,2,3,4,5] }
							                    ], */
								"aoColumns" : [
										{
											"mData" : "id",
											"mRender" : function(data, type,
													row) {
												return '<a href="<s:url action="rightUpdate" namespace="/admin" />?updateid='
														+ row.id
														+ '">'
														+ row.id + '</a>';
											}
											
											

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