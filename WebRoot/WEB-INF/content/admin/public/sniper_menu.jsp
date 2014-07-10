<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="myfiles/js/jquery.sniper.menu.js"></script>
<div id="sniper_menu" class="btn-group" data-spy="affix" data-offset-top="100">
	<div class="btn-group">
		<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
  			<i class="fa fa-angle-double-down"></i>
  		</button>
  		<ul class="dropdown-menu">
			<li><a href="javascript:;" data-value="1" data-type="select">全选</a></li>
			<li><a href="javascript:;" data-value="2" data-type="select">不选</a></li>
			<li><a href="javascript:;" data-value="3" data-type="select">反选</a></li>
		</ul>
	</div>
	<div class="btn-group">
		<button type="button" class="btn btn-danger" data-value="delete" data-type="delete"><i class="fa fa-trash-o"></i></button>  		
	</div>
	<s:if test="sniperMenu">
	<s:iterator value="sniperMenu">
	<s:set name="oper"><s:property value="key"/> </s:set>
	<div class="btn-group">
		<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
  			 <s:property value="key"/> <span class="caret"></span>
  		</button>
  		<ul class="dropdown-menu">
  		<s:iterator value="value">
  			<li><a href="javascript:;" data-value='<s:property value="key"/>' data-type='<s:property value="oper"/>'><s:property value="value"/></span></a>
  		</s:iterator>
		</ul>
	</div>
	</s:iterator>
	</s:if>
	
</div>

<!-- 调用 -->
<script type="text/javascript">

$(function(){
 	$().snipermenu({
 		url:'<s:property value="sm_url"/>'
 	 });
});
</script>