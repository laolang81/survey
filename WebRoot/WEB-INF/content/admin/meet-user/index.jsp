<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="seach">
	<form id="searchFrom" class="form-inline" role="form" name="searchFrom" method="get" action="">
		<div class="form-group">
			<label for="" class="sr-only">性别</label>
			<s:select  list="sexs" name="searchInteger.sex" cssClass="form-control" />
		</div>
		<div class="form-group">
			<label for="" class="sr-only">时间选项</label>
			<s:select list="#{0:'报道时间',1:'离开时间',2:'创建时间'}" name="searchInteger.timeType" cssClass="form-control" />
		</div> 
		<div class="form-group">
			<label for="channel" class="sr-only">开始日期</label>
			<s:textfield name="searchDate.stime" cssClass="form-control" placeholder="开始日期"
				readonly="true"
				data-date-format="yyyy-mm-dd">
				<s:param name="value">
					<s:date name="searchDate.stime" format="yyyy-MM-dd" />
				</s:param>
			</s:textfield>
		</div>
		<div class="form-group">
			<label for="channel" class="sr-only">结束日期</label>
			<s:textfield name="searchDate.etime" cssClass="form-control" placeholder="结束日期"
				readonly="true"
				data-date-format="yyyy-mm-dd">
				<s:param name="value">
					<s:date name="searchDate.etime" format="yyyy-MM-dd" />
				</s:param>
			</s:textfield>
		</div> 
		<div class="form-group">
			<label for="seachName" class="sr-only">关键词</label>
			<s:textfield name="searchString.name" cssClass="form-control" placeholder="关键词" />
			
		</div>
		
		<div class="form-group">
			<div class="btn-group">
				<button class="btn btn-success" type="submit" name="searchString.submit" value="search">
		  			查询
		  		</button>
			</div>
			<div class="btn-group">
		  		<button class="btn btn-success" type="submit" name="searchString.submit" value="export">
		  			查询并导出
		  		</button>
			</div>
		</div>
	</form>
</div>

<s:include value="../public/sniper_menu.jsp"></s:include>

<table class="table table-hover">
	<thead>
		<tr>
			<th><s:text name="ID"></s:text></th>
			<th><s:text name="名称"></s:text></th>
			<th><s:text name="性别"></s:text></th>
			<th><s:text name="职务"></s:text></th>
			<th><s:text name="单位"></s:text></th>
			<th><s:text name="手机"></s:text></th>
			<th><s:text name="住宿要求"></s:text></th>
			<th><s:text name="报道时间"></s:text></th>
			<th><s:text name="车次"></s:text></th>
			<th><s:text name="是否接站"></s:text></th>
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
			<td><s:property value="sex"/></td>
			<td><s:property value="post"/></td>
			<td><s:property value="unit"/></td>
			<td><s:property value="mobilePhone"/></td>
			<td><s:property value="shopInfo"/></td>
			<td><s:date name="reportTime" format="yyyy-MM-dd HH:mm:ss" /></td>
			<td><s:property value="carNum"/></td>
			<td><s:property value="carPeople"/></td>
		</tr>
		</s:iterator>
		
	</tbody>
</table>
<div class="meneame">${pageHtml }</div>

<link
	href="myfiles/Plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
<script type="text/javascript"
	src="myfiles/Plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="myfiles/Plugin/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"
	charset="UTF-8"></script>
<script type="text/javascript">
	$('#searchDate_stime').datetimepicker({
		language : 'zh-CN',
		autoclose : true,
		format : 'yyyy-mm-dd',
		todayBtn: true
		
	});
	$('#searchDate_etime').datetimepicker({
		language : 'zh-CN',
		autoclose : true,
		format : 'yyyy-mm-dd',
		todayBtn: true
	});
</script>