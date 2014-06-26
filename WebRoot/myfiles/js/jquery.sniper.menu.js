/**
 * 用户列表也的所有操作
 */
(function ($) {
   $.fn.extend({
        "snipermenu": function (options) {
            //设置默认值
            options = $.extend({
            	baseurl		: '/',
            	id			: '#sniper_menu',
            	div			: '#table',
            	//下拉选择传递值
            	selectname	: 'del',
            	//没条记录的值
            	valuename	: 'delid',
            	//记录区分前缀
            	prefix		: '#sl_',         	
            	//审核css
            	audit		: '',
            	//为审css
            	naudit		: 'a_ok_no',
            	checkbox	: 1,
            	licolor		: 1,
            	odd			: "alternate", /* 偶数行样式*/
                even		: "", /* 奇数行样式*/
                selected	: "selected", /* 选中行样式*/
                over		: "alternatehover", /* 滑动样式*/
                //数据处理url
                url			: '',
                //数据导出url
                exporturl	: ''
            }, options);
            
            
            var chk_value	= [];
            var value		= '';
            var ulvalue		= '';
            if(options.url==''){
            	alery('请设置操作url');
            }
            //组装url
            var url			= options.baseurl+sniperConfig['baseAdminPath']+options.url;
            var exporturl	= options.baseurl+sniperConfig['baseAdminPath']+options.exporturl;
            var type		= ["exportword", "exportexecl", "print"];
           //区分记录状态
            function makedaudit(){
            	if($(options.div+" tbody>tr[data-status]").val()){
            		 $(options.div+" tbody>tr[data-status='1']").addClass(options.audit);
                     $(options.div+" tbody>tr[data-status!='1']").addClass(options.naudit);
            	}     	
            }
            
            //获取checkbox的值
            function getcheckboxdata(){
            	chk_value	= [];
            	$(options.div).find('input[name="'+options.valuename+'"]:checked').each(function(){chk_value.push($(this).val());}); 
            }
            //隔行换色
            if(options.licolor){
            	 $(options.div+" tbody>tr:odd").addClass(options.odd);
                 $(options.div+" tbody>tr:even").addClass(options.even);
            }
           
            //选狂操作
            if(options.checkbox){
            	 $(options.div+" tbody>tr").click(function () {           	
                     //判断当前是否选中
                    var hasSelected = $(this).hasClass(options.selected);
                      //如果选中，则移出selected类，否则就加上selected类
                      $(this)[hasSelected ? "removeClass" : "addClass"](options.selected).find(':checkbox').attr('checked', !hasSelected);
                     
                  });
                  //滑动换测
                  $(options.div+" tbody>tr").hover(function () {$(this).addClass(options.over);},function () {$(this).removeClass(options.over);});
                  
                  //全选，全部选操作
                  $(options.id).find("input[name='"+options.selectname+"']").click(function () {
                  	  var hasSelected = $(this).is(':checked');
                      if (hasSelected)	$(options.div+' input[type="checkbox"]').attr("checked",true);
                      else				$(options.div+' input[type="checkbox"]').attr("checked",false);
                  });
            }
           
            //单个选操作
            //$(options.div+' tbody>tr :checkbox').click(function () { getcheckboxdata();});
            function singleopenclose(obj)
            {
            	if(obj.css('display')=='block'){
            		obj.hide();        		
            	}else{
            		obj.show();
            	} 
            }
           //ul li span
            function clicksingle()
            {
                $(options.id + ' > ul > li:gt(0) > span').click(function(){
                	var spanlength	= $(this).parent('li').find('ul').length;              	
                	if(spanlength==0){
                		single(this);
                		return false;
                	}else{
                		var ul	= $(this).parent().find('ul');
                    	var displayself	= ul.css('display');    	
                    	$(options.id+' li ul:visible').css('display','none');
                    	var display	= ul.css('display',displayself);
                    	singleopenclose(ul);
                	}
                });
            }
            //del旁边的
            function selectdel()
            {
            	 $(options.id + ' > ul > li:eq(0) > span > i').click(function(){
            		var ul	= $(this).parent().parent().find('ul');
                 	var displayself	= ul.css('display');
                 	$(options.id+' li ul:visible').css('display','none');
                 	var display	= ul.css('display',displayself);
                 	singleopenclose(ul);  
                 });
            }
           
            //没有下属ul的操作函数
            function single(obj)
            {
            	value	= $(obj).attr('data-value'); 
            	ulvalue	= $(obj).attr('data-value'); 
            	//alert(value);
            	switch(value){
            		case 'delete':send();break;
            		case 'refresh':window.location.reload();break;
            	}           	
            };
            //ul li ul li span点击操作
            function clickulli()
            {
            	$(options.id + ' ul > li > ul > li > span').click(function(){
            		var ul		= $(this).parent().parent();
            		value		= $(this).attr('data-value'); 
            		ulvalue		= ul.attr('class');
            		
                	var displayself	= ul.css('display');               	
                	if(displayself=='block'){
                		ul.hide();
                		
                		//执行选择操作
                		if(ulvalue=='select'){
                			switch(value){
                				case '1':$(options.div+' tbody>tr :checkbox').attr("checked",true);break;
                				case '2':$(options.div+' tbody>tr :checkbox').attr("checked",false);break;
                				case '3':$(options.div+' tbody>tr :checkbox').each(function (){$(this).attr("checked", !$(this).attr("checked"));}); break;
                				case '4':$(options.div+' tbody>tr :checkbox').attr("checked",false);$(options.div+" tbody>tr[data-status='1'] :checkbox").attr("checked",true);break;
                				case '5':$(options.div+' tbody>tr :checkbox').attr("checked",false);$(options.div+" tbody>tr[date-status!='1'] :checkbox").attr("checked",true);break;
                			}
                			return false;
                		}               		
                		send();   		
                	}else{ul.show();}
        		});
            }	
            //点击其他页面关闭下拉页面
            $(document).bind("click",function(e){ 
            	var target = $(e.target); 
            	if(target.closest(options.id).length == 0 && target.closest(options.div).length == 0 ){ 
            		$(options.id+' li ul:visible').css('display','none');
            	} 
            });    
            //文件导出喊出，方式是新页打开
            function exportword(chk_value)
            {
            	if(exporturl.length < options.baseurl.length){
            		alert('请设置导出url');
            		return false;
            	}
            	//?a[]=1&a[]=3
            	paramid	= chk_value.join('&id[]=');
            	//alert(id);
            	//alert(exporturl+'?id[]='+paramid);
            	window.open(exporturl+'?id[]='+paramid+'&type='+value);
            }
           
            //数据发送，及获取
            function send()
            {       	
            	getcheckboxdata();
            	if(chk_value.length==0) {
            		
            		art.dialog({
            			title:'提示',
            		    content: '没有选择记录',
            		    okVal:'确定',
            		    ok: true,
            		    padding:'10px 40px'
            		   
            		});
            		return false; 
            	}
            	if(!confirm('你确定继续？')){		
        			return false;
        		}
            	
            	
            	//新页打开检测
            	if(type.toString().indexOf(value) > -1) {
            		exportword(chk_value);
            		return false;
            	}
            	art.dialog({content: '后台操作中...'});
            	
            	$.post(url, {value:value,ulvalue:ulvalue, id: chk_value}, 
            			function (data, textStatus){ 
	            		//关闭所有窗口
		    			var list = art.dialog.list;
		    			for (var i in list) {
		    			    list[i].close();
		    			};
		        		//信息反馈
			    		if(textStatus=='success'){
			    			art.dialog({ time: 1, content: '操作成功'});
			    			if(data.message>=1){
	        	    			
	        	    			switch(value){		
	    	    					case 'delete':
		    	    					for(var i in chk_value){
		        	    					if($(options.prefix+chk_value[i]).length!=0){
		        	    						$(options.prefix+chk_value[i]).remove();
		        	    					}
		        	       				}
		    	    					break;
	    	    					default:
	    	    						if(data.html!=false){
	    	    							for(i in data.html){
	    	    								if($(options.prefix+i).length!=0){
	                	    						$(options.prefix+i).replaceWith(data.html[i]);
	                	    					}
	    	    							}  	    							
	        	    						makedaudit();
	    	        	    			}  		
	        	    			}       	    			      	    			
	        			  	}
			    		}else{
			    			art.dialog({ time: 1, content: '网络链接不通'});
			    		}
			    			
			    		
        	    		  	    		     
        	    	}, "json");   	
            };
            $(options.id).divAddress();
            makedaudit();
            selectdel();
            clicksingle();
            clickulli();
            //end
        }            
    });
})(jQuery);

/**
 * div浮动
 */
(function($){
	$.fn.divAddress=function(options){
			//设置默认值
	        options = $.extend({           
	            top: 50, /* 顶部偏移*/
	        }, options);
			var obj			= $(this);
			//获取div的距顶高度
			var divTop		= obj.offset().top;
			//获取原始div样式
			var marginTop	= obj.css('margin-top');
			var topNum		= obj.css('top');
			var position	= obj.css('position');
			
			$(window).scroll(function(){
				topScroll=$(window).scrollTop();
				if(topScroll>=divTop){
					numTop	= options.top?options.top:0;
					obj.css('position','fixed');
					obj.css('z-index',1000);
					numTop	= (options.top?options.top:0)+'px';
					obj.css('top',numTop);
					//如果div带有margin-top一定要清除
					//obj.css('margin-top','0px');
				}else{
					//还原div源样式
					obj.css('position',position);
					obj.css('top',topNum);
					//obj.css('margin-top',marginTop);
				}
			});
		}
})(jQuery)