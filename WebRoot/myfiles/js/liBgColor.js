//插件编写
(function ($) {
    $.fn.extend({
        "liBgColor": function (options) {
            //设置默认值
            options = $.extend({
                id: "table", /*样式*/
                odd: "alternate", /* 偶数行样式*/
                even: "", /* 奇数行样式*/
                selected: "selected", /* 选中行样式*/
                over: "alternatehover" /* 滑动样式*/
            }, options);
           // alert($("#"+options.id+" li:odd").html());
            //$("ul li").click(function(){alert('ss')});
            $("#"+options.id+" li:odd").addClass(options.odd);
            $("#"+options.id+" li:even").addClass(options.even);
            $("#"+options.id+" li").click(function () {
            	
                //判断当前是否选中
              var hasSelected = $(this).hasClass(options.selected);
                //如果选中，则移出selected类，否则就加上selected类
                $(this)[hasSelected ? "removeClass" : "addClass"](options.selected)
                //查找内部的checkbox,设置对应的属性。
    			 .find(':checkbox').attr('checked', !hasSelected);
            });
            //滑动换测
            $("#"+options.id+" li").hover(
            		function () {
            			$(this).addClass(options.over);
            		},
            		function () {
            			$(this).removeClass(options.over);
            		}
            );
            
            //表头中的checkbox （全选 反选）
            $("#"+options.id+" li :checkbox:first ").click(function () {
                //判断当前是否选中
             // var hasSelected = $(this).attr("checked");
              var hasSelected = $(this).is(':checked');
                //如果选中，则移出selected类，否则就加上selected类
              
              
                $('#'+options.id+' li')[!hasSelected ? "removeClass" : "addClass"](options.selected);    
                if (hasSelected)
                	$('#'+options.id+' li :checkbox').attr("checked",true);
                else
                	$('#'+options.id+' li :checkbox').attr("checked",false);
            });
            //表头中的checkbox （全选 反选）
            $("#"+options.id+" li :checkbox:last ").click(function () {
              //判断当前是否选中
              var hasSelected = $(this).is(':checked');
                //如果选中，则移出selected类，否则就加上selected类
                      
                $('#'+options.id+' li')[!hasSelected ? "removeClass" : "addClass"](options.selected); 
                
                if (hasSelected)
                	$('#'+options.id+' li :checkbox').attr("checked",true);
                else
                	$('#'+options.id+' li :checkbox').attr("checked", false);
            });
            // 如果单选框默认情况下是选择的，则高色.
            $('#'+options.id+' li:has(:checked)').addClass(options.selected);
            return this;  //返回this，使方法可链。
        }
    });
})(jQuery);

//插件应用
$(function(){
 $("#table").liBgColor()  //应用插件
   //.find("th").css("color","red");//可以链式操作
});