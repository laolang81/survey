/**
 * 用户处室栏目下拉菜单
 */
(function ($) {
   $.fn.extend({
        "siteid": function (options) {
            //设置默认值
            options = $.extend({
            	//提交的之
            	post		: '',
            	//选定之
            	selected	: '',
            	//头部
            	t			: 1,          	
            	//操作selec
            	changeselect: 'siteidSelect',
            	//操作对应的select
            	selectselect: 'itemidSelect',
            	name		: 'itemid',
            	url			: '/doftec/channel/getitems',
            	gettrue		: 0,
            	// 获取class数据参数，c=1为开启
            	c			: 0,
            	cselected	: '',
            	cselect		: 'classSelect',
            	cname		: 'class',
            	curl		: '/doftec/channel/getclass'
            }, options);
            var post	= '';
            //主要参数为空返回
            if(!options.post){
            	if($('#'+options.changeselect).length>0){
            		post	= $('#'+options.changeselect).val();
            	}
            }else{
            	post	= options.post;
            }
           // alert(post);
            if(!post) return false;
            
           
            //帮顶操作
            $('#'+options.changeselect).bind('change',function(){
            	getitem($(this).val());
            	
        	});
            //在帮顶之后如果相关数据都存在调用一次 ，options.post必须存在
            getitem(post,options.selected);
            //选中
            if($('#'+options.changeselect).length>0 && post){
            	$('#'+options.changeselect+' option[value='+post+']').attr("selected", true);
            }
            
            //获取栏目列表
            function getitem(value,selected){
            	 //数据请求
            	if(!value){
            		value	= post;
            	}
            	//alert(options.selected);
            	if(!selected){
            		selected	= options.selected;
            	}
            	//alert(value);
                $.post(options.url,  
        				{id: value,t:options.t,gettrue:options.gettrue},   
        	    		function (data, textStatus){
        					//清空原有数据值
        					if($('#'+options.selectselect).length>0){
        						$('#'+options.selectselect).remove();	
        					}
        					//获取数据之
        	    			if(data.message==1 && data.html){
        	    				//添加下拉菜单
        	    				var selecthtml	= '<select id="'+options.selectselect+'" class="ml10" name="'+options.name+'"></select>';  				
        	    				if($('#'+options.selectselect).length==0){
        	    					$('#'+options.changeselect).after(selecthtml);      	    				      	    					
        	    				}
        	    				
        	    				for(var i in data.html){		
        	    					$('#'+options.selectselect).append('<option value="'+i+'">'+data.html[i]+'</option>'); 					 	
        	    				}
        	    				
        	    				if(selected){			
        	    					$('#'+options.selectselect+' option[value='+selected+']').attr("selected", true);	
        	    				}
        	    				if(options.c==1){
        	    					
        	    					$('#'+options.selectselect).bind('change',function(){
        	    						getclass($(this).val());
        	    					});	
        	    					//调用一次
        	    					getclass(options.selected,options.cselected);
	
        	    				}
        	    			}	    			
        	        	},
        	       "json");
            }          

            
            //获取class数据
            function getclass(value,selected){        	
     	   		//数据请求
            	if(!value){
            		return false
            	}
            	//重新赋值
            	options.selected	= value;
            	if(!selected){
            		selected	= options.cselected;
            	}
                $.post(options.curl,  
        				{id: value},   
        	    		function (data, textStatus){
        					//清空原有数据值
        					if($('#'+options.cselect).length>0){
        						$('#'+options.cselect).remove();	
        					}
        					//获取数据之
        	    			if(data.message==1 && data.html){
        	    				//添加下拉菜单
        	    				var selectclasshtml	= '<select id="'+options.cselect+'" class="ml10" name="'+options.cname+'"></select>';  				
        	    				if($('#'+options.cselect).length==0){
        	    					$('#'+options.selectselect).after(selectclasshtml);  	    					
        	    				}     	    				
        	    				for(var i in data.html){		
        	    					$('#'+options.cselect).append('<option value="'+i+'">'+data.html[i]+'</option>'); 					 	
        	    				}
        	    				if(selected){			
        	    					$('#'+options.cselect+' option[value='+selected+']').attr("selected", true);	
        	    				}	    							
        	    			}
        	    			//调用
        	        	},
        	       "json");  
            }
//end
        }
   	 });
})(jQuery);