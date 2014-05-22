<?php
if(!isset($url)){
	throw  new \Exception("url必须定义");
}

$this->headScript()
	->prependFile($this->basePath() . '/myfiles/js/jquery.sniper.div.address.js')
	->prependFile($this->basePath() . '/myfiles/js/jquery.sniper.menu.js');

$this->headLink( )
	->prependStylesheet($this->basePath() . '/myfiles/css/sniper.top.css');
?>
<div id="sniper_menu" class="mt5">
<ul>
<li><span data-value="del-checkbox"><input type="checkbox" name="del"/><i class="ml5 fa fa-sort-asc"></i></span>
<ul class="select">
<li><span data-value="1">全选</span></li>
<li><span data-value="2">不选</span></li>
<li><span data-value="3">反选</span></li>
<li><span data-value="4">未审</span></li>
<li><span data-value="5">已审</span></li>
</ul></li><li
class="ml5 left"><span data-value="delete">删除</span></li><?php if(isset($status) && is_array($status)):?><li
class="center"><span>标记为<i class="ml5 fa fa-sort-asc"></i></span>
<ul class="maked">
<?php
foreach ($status as $k=>$v):
echo '<li><span data-value="'.$k.'">'.$v.'</span></li>';
endforeach;
?>
</ul>
</li><?php endif; if(isset($moveto) && is_array($moveto)):?><li
class="center"><span>移动到<i class="ml5 fa fa-sort-asc"></i></span>
<ul class="moveto">
<?php
foreach ($moveto as $k=>$v):
echo '<li><span data-value="'.$k.'">'.$v.'</span></li>';
endforeach;
?>
</ul></li><?php endif; if (isset($more)):?><li class="center"><span data-value="more">更多<i class="ml5 fa fa-sort-asc"></i></span>
<ul class="export">
<li><span data-value="export">导出</span></li>
</ul></li><?php endif;?><li
class="right"><span data-value="refresh">刷新</span></li>
</ul>
</div>
<!-- 调用 -->
<script>
$(function(){
 	$().snipermenu({
 		url:'<?php echo $url?>'
 	 	});
});
</script>