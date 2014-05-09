<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>用户登录</title>
<base
	href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script
	src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
        <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
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

		<form class="form-signin" role="form" name="login">
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
				<label for="exampleInputEmail2" class="col-sm-2 control-label sr-only">输入验证码</label>
	      		
	      		<input type="text" name="verifycode" style=" display: inline;width: 44%;  float: left;" placeholder="输入验证码" id="exampleInputEmail2" class="form-control">
				
				<img alt="" style=" margin-left:2%" src="<s:url action="verify" namespace="/" />" class="fl" id="verifyImg" onclick="fleshVerify()">
					
			</div>
			<div class="form-group input-group-lg">
			<label class="checkbox"> 
				<input type="checkbox" value="remember-me"> Remember me
			</label>
			</div>
			<span id="result"></span>
			<button class="btn btn-lg btn-primary btn-block" type="button">Sign in</button>
		</form>

</div><!-- /container -->

<div id="back-mask" class="back-mask"></div>
<script language="javascript">
		$(function() {
			$("#login").keydown(function(e) {
				if (e.keyCode == 13) {
					onlogin();
					return false;
				}
			});
		});

		function onlogin() {
			
			var params	= $('input').serialize();
			
			//$("#button").attr("disabled","true");

			$.ajax({
						type : "post",
						dataType : 'json',
						url : '<s:url action="loginAjaxValid" namespace="/admin"/>',
						data : params,
						beforeSend : function(XMLHttpRequest) {$('button[type="button"]').html('正在登录...');},
						success : function(data, textStatus) {
							//var data = $.parseJSON(data);
							if (data != null && data.id == 1)
								window.location = '<s:url action="rightList" namespace="/admin" />';
							else {
								$('#result').html(data.message);
								$('button[type="button"]').html('登录');
								if (data.id != 0)
									$('#' + data.id).focus();
								fleshVerify();
							}
						},
						complete : function(XMLHttpRequest, textStatus) {
							$('#button').val('登录');
						},
						error : function() {
							$('#result').html('登录错误...');
							$('button[type="button"]').html('登录');
						}
					});
		}
		function fleshVerify() {
			var timenow = new Date().getTime();
			$('#verifyImg').attr("src",
					'<s:url action="verify" namespace="/" />?d=' + timenow);
		}
		$().ready(function(){$('button[type="button"]').click(onlogin);});
		
	</script>
	<script type="text/javascript">
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