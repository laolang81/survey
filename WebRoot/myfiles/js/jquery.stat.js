/**
 * 访问统计
 * 全局浏览记录统计请设置all=1
 * 处室栏目统计，设置office=1,siteid是处室id，itemid是栏目id
 * 新闻浏览量统计view=1，sid是新闻id，back是否返回新闻浏览数据
 */
//跨与访问jsonp
jQuery.support.cors = true;
(function ($) {
   $.fn.extend({
        "stat": function (options) {
            //设置默认值
            options = $.extend({
            	baseurl		: 'http://www.shandongbusiness.gov.cn',
            	//每一次的点击都会执行这个数据插入
            	all			: 1,
            	allurl		: '/stats/ajaxhistory',
            	//处室栏目统计
            	office		: 1,
            	siteid		: 0,
            	itemid		: 0,
            	officeurl	: '/Office-',
            	//新闻
            	view		:  1,
            	viewurl		: '/new/viewcallback/',
            	sid			: 0,
            	//之否返回数据
            	back		: 0
            	
            }, options);
            
           
            var hostname	= window.location.hostname;
            switch(hostname)
            {
            	case 'shandongbusiness.gov.cn':
            		options.baseurl	= 'http://shandongbusiness.gov.cn';
            		break;
            }
            //所有的统计,调用页面所有
            function historyall(){
            	if(options.all!=1) return false;
            	 //获取当前打开的url路径，不包含域名 
            	var openurl	= window.location.pathname+window.location.search+window.location.hash;
            	var baseurl		= options.baseurl+options.allurl;
            	$.post(baseurl, {url:openurl}, function (data, textStatus){},'json');
            }
            
            //处室栏目统计，调用页面处室栏目主页及其列表页
            function historyoffice(){
            	
            	if(!options.siteid || options.office!=1) return false;
            	var baseurl	= options.baseurl+options.officeurl+options.siteid+'-'+options.itemid+'.json';
            	$.get(baseurl, function (data, textStatus){}, "json");
            }
            //文章，调用页面新闻内容展示也，专题内容展示页
            function historyview(){
            	if(!options.sid || options.view!=1) return false;
            	if(options.back==1){
            		var baseurl	= options.baseurl+options.viewurl+options.sid+'?callback=?';
            		$.get(baseurl, {back:options.back},function (data,textStatus){for(var i in data){if($('#'+i).length>0){$('#'+i).html(data[i]);}}}, "jsonp");
            	}else{
            		var baseurl	= options.baseurl+options.viewurl+options.sid;
            		$.get(baseurl);
            	}
            	              
            }
            //调用        
            historyall(); 
            historyoffice();
            historyview();
            
           
            //end
        }
   	 });
})(jQuery);