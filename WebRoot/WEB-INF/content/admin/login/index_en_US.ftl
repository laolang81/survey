<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>用户登录</title>
<base href="${request.scheme }://${request.serverName}:#{request.serverPort}${request.contextPath}/">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<script type="text/javascript" src="myfiles/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="myfiles/Plugin/Bootstrap/js/bootstrap.min.js"></script>

<link href="myfiles/Plugin/Bootstrap/css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css">
<link href="myfiles/Plugin/Bootstrap/css/bootstrap-theme.min.css" media="screen" rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	<script src="myfiles/Plugin/Bootstrap/js/html5shiv.min.js"></script>
<![endif]-->

<script type="text/javascript" src="myfiles/js/jquery.backstretch.min.js"></script>
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
		<form data-status='正在登录...' data-url="/survey/admin/" class="form-signin" role="form" name="login" action="j_spring_security_check" method="post">
			<h2 class="form-signin-heading">请登录</h2>
			<#if Parameters.error?exists && Parameters.error = "true">
				<div class="alert alert-warning alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<#if Session.SPRING_SECURITY_LAST_EXCEPTION.message?exists >
					${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
					<#else>
					登录失败
					</#if>
				</div>
			</#if>
			<input type="hidden" name="" value="" />
			<div class="form-group input-group-lg">
				<label for="username">登录名</label> 
				<input type="text" id="username" name="username" class="form-control" placeholder="登录名" required autofocus>
			</div>
			<div class="form-group input-group-lg">
				<label for="password">登录密码</label>
				<input id="password" type="password" name="password" class="form-control" placeholder="登录密码" required>
			</div>

			<div class="form-group input-group-lg">
				<label for="verifycode" class="col-sm-2 control-label sr-only">输入验证码</label>
				<input type="text" name="sessionVerifyName" style=" display: inline;width: 44%;  float: left;" placeholder="输入验证码" id="verifycode" class="form-control">
				<img alt="点击我刷新验证码"style="cursor: pointer; margin-left:2%" src="/survey/verify" class="fl">
			</div>
			<div class="form-group input-group-lg">
				<label class="">
					<input type="checkbox"name="_spring_security_remember_me"> Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		</form>
	</div>
	<!-- /container -->

	<div id="back-mask" class="back-mask"></div>
	<script language="javascript">
		$(function() {
			$("form").keydown(function(e) {
				if (e.keyCode == 13) {
					onlogin();
				}
			});
			$('form button[type="button"]').click(function() {
				onlogin();
			});
			$('form img').click(function() {
				fleshVerify();
			});
		});

		function onlogin() {
			var params = $('form input').serialize();
			var bottonname = $('form button[type="button"]').html();
			var posturl = $('form').attr('action');
			var postredir = $('form').attr('data-url');
			var datastatus = $('form').attr('data-status');

			$.ajax({
					type : "post",
					dataType : 'json',
					url : posturl,
					data : params,
					async : true,
					beforeSend : function(XMLHttpRequest) {
						$('form button[type="button"]').html(datastatus)
					},
					success : function(data, textStatus) {
						//var data = $.parseJSON(data);
						$('form button[type="button"]').html(bottonname);

						if (data != null && data.id == 1) {
							window.location = postredir;
						} else {
							if (data.id != 0) {
								var $input = $('form input[name="' + data.id + '"]');
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
			var indexof = src.indexOf("?");
			if (indexof != -1) {
				src = src.substring(0, src.indexOf("?"));
			}
			$('form img').attr("src", src + '?d=' + timenow);
		}

		//闪烁
		function shake(ele, cls, times) {
			var i = 0, t = false, o = ele.attr("class") + " ", c = "", times = times || 2;
			if (t)
				return;
			t = setInterval(function() {
				i++;
				c = i % 2 ? o + cls : o;
				ele.attr("class", c);
				if (i == 2 * times) {
					clearInterval(t);
					ele.removeClass(cls);
				}
			}, 200);
		};

		var images = [];

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