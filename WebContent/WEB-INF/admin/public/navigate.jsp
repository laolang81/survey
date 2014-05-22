<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="breadcrumbs">
<!-- †’ -->
	<div id="pma_navigation_collapser" title="" class="fl">Home†</div>
	<ul class="breadcrumb">
		<li>
			<i class="fa fa-home"></i>
			<a href="<?php echo $this->url('admin'); ?>">Home</a>
			
		</li>
		<li class="active"><s:property value="#controllerName"/></li>
		<li class="active"><s:property value="#actionName"/></li>
	</ul>
</div>
