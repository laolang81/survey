/**
 * 用户列表也的所有操作
 */
(function ($) {
   $.fn.extend({
        "listFormOper": function (options) {
            //设置默认值
            options = $.extend({
            	//class，统一class
            	classname: '.btn-group',
            	//div name
            	div: '#table',
            	data: ''
            }, options);
            
            var objtd;
            //绑定操作
            var objul;
            //当前操作id
            var id;
            //url
            var url;
            //弹出信息
            var info;
            //配合url使用
            var action;
            //配合getVar
            var oper;
            //绑定a操作
            $(options.classname + ' > a').click(function(){start(this);});
            

            //初始化ul
            function start(obj){         	
            	
            	//获取当前操作框的顶级
            	classobj	= $(obj).parentsUntil('btn-group');
            	objtd		= classobj.parent('td').parent('tr');
            	//alert(objtd.html());
            	//获取订单处理情况
            	//ul obj
            	objul		= classobj.children('ul');
            	//获取操作id
            	id			= classobj.attr('id').substring(1);
            	url			= classobj.attr('data-url');
            	
            	//隐藏所有的
            	$(options.div + " " + options.classname + " > ul").not(objul).hide();
            	//获取操作狂状态
            	ulstatus	= objul.css("display");
            	//alert(ulstatus);
            	if(ulstatus=='none'){
            		
            		objul.show();
            		//绑定ul》li下的单击操作
            		objul.children('li').children('a').click( function(){
            			getVar(this); 
            			operaction();
            			});

            	}else{
            		
            		close();
            	}
            	
            }
            //获取变量
            function getVar(obj)
            {         	
            	info	= $(obj).attr('data-info');
            	action	= $(obj).attr('data-action');
            	oper	= $(obj).attr('data-oper');
            	if($(obj).attr('data-url')){
            		url	= $(obj).attr('data-url');
            	}
            }
            
            function operaction()
            {
            	switch(oper){
	        		//跳转的，包含编辑
	            	case '1':          		
	            		window.open(url, "_blank");           		
	            		break;
	            		//非跳转，及当前页操作的，包含删除，审核，等等
	            	case '2':          		
	            		getcode();           		
	            		break;
	            	case '3':	            	  		
	            		break;           	
	        	}   	
            	//关闭窗口            	
	        	close();
	        	
            }      
            //关闭窗口
            function close(){
            	//删除一切绑定
            	objul.children('li').children('a').off();
            	//隐藏窗口
            	objul.hide();
            }
            
            //获取验证码code
            function getcode()
            {
            	
            	$.post('/public/code',     
            		function (data, textStatus){
            			
            			if(data.code && id){ 				
            				del(data.code);
            			}
                	}, "json");
            }
            //执行相关操作
            function del(code)
            {	       	
            	//get	= JSON.stringify(options.data);
            	//alert(options.data);
            	art.dialog({
            		lock:true,
            	    content: info,
            	    ok: function () {  	    	
            	    	$.post(url, {code:code, id: id,action:action,get:options.data},     
                			function (data, textStatus){               	    		
            	    		if(data.message==1){
            	    			
            	    			switch(action){
            		    			case 'Delete':
            		    				
            		    				//删除
            		    				objtd.remove();
            					  		//末尾新增
            					  		if(data.html!=false){
            					  			$(options.div).append(data.html);
            			    			}
            					  		break;
            					  	default:
            					  		if(data.html!=false){
            					  			objtd.replaceWith(data.html);
            			    			}
            					  		break;
            	    			}
            	    			if(data.html!=false){
            	    				//删除返回id
            	    				if(data.id)
            	    					id	= data.id;
                	    			$('#d'+id+' > a').bind({ click:function(){start(this);}});
    			    			}
            				    
            			   	} else{
            				   art.dialog({ time: 1,content: 'Fail'});
            			  	}         	    		     
            	    	}, "json");   	    	
            	        return true;
            	    },
            	    cancelVal: '取消',
            	    cancel: true 
            	});         	
            }       
        }            
    });
})(jQuery);


