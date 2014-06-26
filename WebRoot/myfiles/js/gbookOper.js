/**
 * 用户列表也的所有操作
 */
(function ($) {
   $.fn.extend({
        "listFormOper": function (options) {
            //设置默认值
            options = $.extend({
            	//class，统一class
            	bindname: '.oper',
            	//div name
            	div: '.gbook',
            	data: ''
            }, options);
            
            var objli;
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
            var type;
            //其他内容，恢复内容
            var reply	= '';
            //绑定a操作
            $(options.bindname).click(function(){start(this);});
            //初始化ul
            function start(obj){         	
            	//获取当前操作框的顶级 li
            	objli		= $(obj).parent('div').parent('li');
            	//获取操作id
            	id			= objli.attr('id').substring(1);
            	url			= objli.attr('data-url');
            	type		= objli.attr('data-type');
            	getVar(obj);
            	if(action=='Reply' && !reply){
            		alert('回复不得为空');
            		return false;
            	}         	
            	operaction();	
            	
            }
            //获取变量
            function getVar(obj)
            {         	
            	info	= $(obj).attr('title');
            	action	= $(obj).attr('data-action');
            	oper	= $(obj).attr('data-oper');
            	if($(obj).attr('data-url')){
            		url	= $(obj).attr('data-url');
            	}
            	switch(action){
            		case 'Offices':
            			reply	= $(objli).find('select[name="Departments"]').val();
            			break;
            		case 'Reply':
            			reply	= $(objli).find("textarea").val();
            			break;
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
            	
            	art.dialog({
            		lock:true,
            	    content: info,
            	    ok: function () {  	    	
            	    	$.post(url, {code:code, id: id,action:action,reply:reply,type:type,get:options.data},     
                			function (data, textStatus){               	    		
            	    		if(data.message==1){          	    			
            	    			switch(action){
            		    			case 'Delete':
            		    				//删除
            		    				objli.remove();
            					  		//末尾新增
            					  		if(data.html!=false){
            					  			$(options.div).append(data.html);
            			    			}
            					  		break;
            		    			case 'DeleteTrue':
            		    				$('#l'+id).remove();
            		    				break;
            					  	default:
            					  		if(data.html!=false){
            					  			objli.replaceWith(data.html);
            			    			}
            					  		break;
            	    			}
            	    			if(data.html!=false){
                	    			$('#l' + data.id + ' ' + options.bindname).bind({ click:function(){start(this);}});
                	    			//$(options.bindname).click(function(){start(this);});
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


