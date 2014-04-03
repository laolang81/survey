//插件编写
(function ($) {
    $.fn.extend({
        "alterBgColor": function (options) {
            //设置默认值
            options = $.extend({
                id: "table", /*样式*/
                odd: "alternate", /* 偶数行样式*/
                even: "", /* 奇数行样式*/
                selected: "selected", /* 选中行样式*/
                over: "alternatehover" /* 滑动样式*/
            }, options);
            $("tbody>tr:odd", this).addClass(options.odd);
            $("tbody>tr:even", this).addClass(options.even);
            $('tbody>tr', this).click(function () {
                //判断当前是否选中
              var hasSelected = $(this).hasClass(options.selected);
                //如果选中，则移出selected类，否则就加上selected类
                $(this)[hasSelected ? "removeClass" : "addClass"](options.selected)
                //查找内部的checkbox,设置对应的属性。
    			 .find(':checkbox').attr('checked', !hasSelected);
            });
            //滑动换测
            $('tbody>tr', this).hover(
            		function () {
            			$(this).addClass(options.over);
            		},
            		function () {
            			$(this).removeClass(options.over);
            		}
            );
            
            //表头中的checkbox （全选 反选）
            $("thead>tr th:first :checkbox:first ",this).click(function () {
                //判断当前是否选中
              
              var hasSelected = $(this).is(':checked');
              
              //如果选中，则移出selected类，否则就加上selected类
              //alert(hasSelected);
              
                $('#'+options.id+' tbody>tr')[!hasSelected ? "removeClass" : "addClass"](options.selected);    
                
                if (hasSelected)
                    $('#'+options.id+' tbody>tr :checkbox').attr("checked",true);
               else
                	$('#'+options.id+' tbody>tr :checkbox').attr("checked",false);
              
                	
            });
            
                       
            //表尾中的checkbox （全选 反选）
            $("tfoot>tr th:first :checkbox:first ",this).click(function () {
                //判断当前是否选中
              
              var hasSelected = $(this).is(':checked');
                //如果选中，则移出selected类，否则就加上selected类
              $('#'+options.id+' tbody>tr')[!hasSelected ? "removeClass" : "addClass"](options.selected);    
               if (hasSelected)
                    $('#'+options.id+' tbody>tr :checkbox').attr("checked",true);
               else
                	$('#'+options.id+' tbody>tr :checkbox').attr("checked",false);
            });
            // 如果单选框默认情况下是选择的，则高色.
            $('tbody>tr:has(:checked)', this).addClass(options.selected);
            return this;  //返回this，使方法可链。
        }
    });
})(jQuery);

//插件应用
$(function(){
 $("#table").alterBgColor()  //应用插件
   //.find("th").css("color","red");//可以链式操作
});