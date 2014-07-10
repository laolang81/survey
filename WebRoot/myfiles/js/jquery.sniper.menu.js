/**
 * 用户列表也的所有操作
 */
jQuery.ajaxSettings.traditional = true;
(function ($) {
   $.fn.extend({
        "snipermenu": function (options) {
            //设置默认值
            options = $.extend({
            	baseurl		: sniperConfig['baseAdminPath'],
            	id			: '#sniper_menu',
            	div			: '.table',
            	//下拉选择传递值
            	selectname	: 'del',
            	//没条记录的值
            	valuename	: 'list.id',
            	//记录区分前缀
            	prefix		: '#sl_',    	
            	//审核css
            	audit		: 'success',
            	//为审css
            	naudit		: 'warning',
            	odd			: "alternate", /* 偶数行样式*/
                even		: "", /* 奇数行样式*/
                selected	: "selected", /* 选中行样式*/
                over		: "alternatehover", /* 滑动样式*/
                //数据处理url
                url			: '/post/postdel',
                //数据导出url
                exporturl	: '/post/export'
            }, options);
            
            
            var chk_value	= [];
            var menuValue		= '';
            var menuType	= '';
            //组装url
            var url			= options.baseurl + options.url;
            var exporturl	= options.baseurl + options.exporturl;
            var targettype	= ["exportword", "exportexecl", "print"];
           //区分记录状态
           
        	//$(options.div + " tbody>tr[data-status='1']").addClass(options.audit);
            //$(options.div + " tbody>tr[data-status!='1']").addClass(options.naudit);
            
            //获取checkbox的值
            var getcheckboxdata = function(){
            	chk_value	= [];
            	$(options.div).find('input[name="' + options.valuename + '"]:checked').each(function(){chk_value.push($(this).val());}); 
            }
            
            //文件导出喊出，方式是新页打开
            var exportword = function (chk_value)
            {
            	if(exporturl.length < options.baseurl.length){
            		alert('请设置导出url');
            		return false;
            	}
            	//?a[]=1&a[]=3
            	paramid	= chk_value.join('&id[]=');
            	//alert(id);
            	//alert(exporturl+'?id[]='+paramid);
            	window.open(exporturl+'?id[]='+paramid+'&type='+menuValue);
            }
           
            //数据发送，及获取
            var send = function()
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
            	if(targettype.toString().indexOf(menuValue) > -1) {
            		exportword(chk_value);
            		return false;
            	}
            	art.dialog({content: '后台操作中...'});
            	
            	
            	$.post(url, { menuValue:menuValue, menuType:menuType, delid: chk_value}, 
            			function (data, textStatus){ 
	            		//关闭所有窗口
		    			var list = art.dialog.list;
		    			for (var i in list) {
		    			    list[i].close();
		    			};
		        		//信息反馈
			    		if(textStatus=='success')
			    			art.dialog({ time: 1, content: data.message});
			    		else
			    			art.dialog({ time: 1, content: '网络链接不通'});
			    		
        	    		if(data.code > 0){
        	    			
        	    			switch(menuType){    				
    	    					case 'delete':
	    	    					for(var i in chk_value){
	        	    					if($(options.prefix + chk_value[i]).length!=0){
	        	    						$(options.prefix + chk_value[i]).remove();
	        	    					}
	        	       				}
	    	    					break;
    	    					default:
    	    						if(data.html!=false){
    	    							for(i in data.html){
    	    								if($(options.prefix + i).length!=0){
                	    						$(options.prefix + i).replaceWith(data.html[i]);
                	    					}
    	    							}  	    							
    	        	    			}  		
        	    			}       	    			      	    			
        			  	}         	    		     
        	    	}, "json");   	
            };
            
            var click = function(obj){
            	menuValue	= $(obj).attr('data-value'); 
        		menuType	= $(obj).attr('data-type'); 
            	
        		//执行选择操作
        		if(menuType == 'select' ){
        			
        			switch(menuValue){
        				case '1':
        					$(options.div + ' tbody>tr :checkbox').prop("checked",true);
        					break;
        				case '2':
        					$(options.div + ' tbody>tr :checkbox').prop("checked",false);
        					break;
        				case '3':
        					$(options.div+' tbody>tr :checkbox').each(
        							function (){$(this).prop("checked", !$(this).prop("checked"));}
        							); 
        					break;
        				case '4':
        					$(options.div+" tbody>tr[data-status='1'] :checkbox").prop("checked",true);
        					break;
        				case '5':
        					$(options.div+" tbody>tr[date-status!='1'] :checkbox").prop("checked",true);
        					break;
        			}
        			
        		}else{
        			send();
        		}
        		
            }
            //ul li ul li span点击操作
           var run =  function(){
            	//sniper_menu
            	$(options.id + ' a').click(function(event){
            		click(this);
            		//return false;
        		});
            	$(options.id + ' button[data-type="delete"]').click(function(event){
            		click(this);
            		//return false;
        		});
            }	
           
            run();
            //end
        }            
    });
})(jQuery);
