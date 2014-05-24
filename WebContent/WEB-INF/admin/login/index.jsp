<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title><s:text name="login.page.title" />%{getText("login.page.title")} </title>
<base
	href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<script type="text/javascript" src="myfiles/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="myfiles/Plugin/Bootstrap/js/bootstrap.min.js"></script>

<link href="myfiles/Plugin/Bootstrap/css/bootstrap.min.css"
	media="screen" rel="stylesheet" type="text/css">

<link href="myfiles/Plugin/Bootstrap/css/bootstrap-theme.min.css"
	media="screen" rel="stylesheet" type="text/css">


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	<script src="myfiles/Plugin/Bootstrap/js/html5shiv.min.js"></script>
<![endif]-->

<script type="text/javascript"
	src="myfiles/js/jquery.backstretch.min.js"></script>
<style type="text/css">
.form-signin {
	margin: 0 auto;
	max-width: 330px;
	padding: 15px;
}
</style>

</head>
<body>
<div class="container">

	<form data-status="正在登录..." data-url="<s:url action="rightList"  namespace="/admin" />" 
			class="form-signin" role="form" name="login" action="<s:url action="loginAjaxValid" namespace="/admin"/>">
		<h2 class="form-signin-heading">Please sign in</h2>
		
		<div class="form-group input-group-lg">		
			<label for="accout">Email address</label>
			<input type="text" id="accout" name="account" class="form-control"
				placeholder="Email address" required autofocus>
		</div>
		<div class="form-group input-group-lg">
			<label for="password">Password</label>
			<input id="password" type="password" name="passwd" class="form-control"
				placeholder="Password" required>
		</div>	
			
		<div class="form-group input-group-lg" >
			<label for="verifycode" class="col-sm-2 control-label sr-only">输入验证码</label>      		
	     	<input type="text" name="verifycode" style=" display: inline;width: 44%;  float: left;" placeholder="输入验证码" id="verifycode" class="form-control">			
			<img alt="" style="cursor: pointer; margin-left:2%" src="<s:url action="verify" namespace="/public" />" class="fl" >
		</div>
		<div class="form-group input-group-lg">
			<label class="checkbox"> 
				<input type="checkbox" value="remember-me"> Remember me
			</label>
		</div>
		
		<button class="btn btn-lg btn-primary btn-block" type="button">Sign in</button>
	</form>

</div><!-- /container -->
%{getText('login.page.title) }
<div id="back-mask" class="back-mask"></div>
<script language="javascript">
	$(function() {
		$("form").keydown(function(e) {
			if (e.keyCode == 13) {			
				onlogin();
			}
		});
		$('form button[type="button"]').click(function(){		
		    onlogin();
		});
		$('form img').click(function(){
			fleshVerify();
		});
	});

	function onlogin() {		
		var params	= $('form input').serialize();
		var bottonname = $('form button[type="button"]').html();
		var posturl = $('form').attr('action');
		var postredir = $('form').attr('data-url');
		var datastatus = $('form').attr('data-status');
	
		$.ajax({
			type : "post",
			dataType : 'json',
			url : posturl,
			data : params,
			async:true,
			beforeSend : function(XMLHttpRequest) {$('form button[type="button"]').html(datastatus)},
			success : function(data, textStatus) {
				//var data = $.parseJSON(data);
				$('form button[type="button"]').html(bottonname);
				
				if (data != null && data.id == 1){
					window.location = postredir;
				}else {
					if (data.id != 0){
						var $input = $('form input[name="'+data.id+'"]');
						$input.focus();
						shake($input.parent(), "has-error", 3);
					}
					fleshVerify();
				}
				
			},
			complete : function(XMLHttpRequest, textStatus) {
				
				$('form button[type="button"]').html(bottonname);
				
			},
			error : function() {
				$('form button[type="button"]').html('Sign Error');
			}
		});
	}
	
	function fleshVerify() {
		var timenow = new Date().getTime();
		var src = $('form img').attr("src");
		$('form img').attr("src", src + '?d=' + timenow);
	}

	//闪烁
	function shake(ele,cls,times){
		var i = 0,t= false ,o =ele.attr("class")+" ",c ="",times=times||2;
		if(t) return;
		t= setInterval(function(){
			i++;
			c = i%2 ? o+cls : o;
			ele.attr("class",c);
			if(i==2*times){
				clearInterval(t);
				ele.removeClass(cls);
			}
		},200);
	};	

	var images = [
			"http://www.shandongbusiness.gov.cn/public/attachment/kindeditor/image/20140108/6c4c88a205dd312d0e66daae2c4c4375.jpg",
			"http://www.shandongbusiness.gov.cn/public/attachment/kindeditor/image/20140108/09689b61a08a7df12faf79f25996b870.jpg" ];

	$(images).each(function() {
		$('<img/>')[0].src = this;
	});
	var index = 0;
	$.backstretch(images[index], {
		speed : 800
	});
	setInterval(function() {
		index = (index >= images.length - 1) ? 0 : index + 1;
		$.backstretch(images[index]);
	}, 5000);
</script>

</body>
</html>