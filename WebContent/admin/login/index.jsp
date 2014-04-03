<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">

<title>用户登录</title>
<base
	href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="myfiles/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="myfiles/js/jquery.backstretch.min.js"></script>
<style type="text/css">
* {
	font: 12px Segoe UI, Tahoma, Arial, Verdana, simsun, sans-serif,
		"Microsoft YaHei";
	margin: 0;
	padding: 0;
	vertical-align: text-top
}

label {
	color: #777777;
	font-size: 14px;
	display: block;
	overflow: hidden;
}

h1 a {
	display: block;
	height: 67px;
	overflow: hidden;
	padding-bottom: 15px;
	text-indent: -9999px;
	width: 326px;
}

.my_login {
	margin: 7em auto;
	width: 350px;
}

::-webkit-input-placeholder {
	color: #ccc;
}

input:-moz-placeholder {
	color: #ccc;
}

form {
	FILTER: alpha(opacity = 70);
	opacity: 0.7;
	background: none repeat scroll 0 0 #FFFFFF;
	border: 1px solid #E5E5E5;
	box-shadow: 0 4px 10px -1px rgba(200, 200, 200, 0.7);
	font-weight: normal;
	padding: 26px 24px 30px;
	overflow: hidden;
}

body form .input {
	box-shadow: 1px 1px 2px rgba(200, 200, 200, 0.2) inset;
	font-family: "HelveticaNeue-Light", "Helvetica Neue Light",
		"Helvetica Neue", sans-serif;
	margin-bottom: 10px;
	margin-top: 2px;
	outline: medium none;
	vertical-align: middle;
	background: none repeat scroll 0 0 #FFFFFF;
	border: 1px solid #E6E6E6;
	border-radius: 6px;
	color: #333333;
	display: block;
	font-size: 18px;
	font-weight: bold;
	height: 20px;
	line-height: 20px;
	padding: 12px 10px;
}

input[type="text"],input[type="password"] {
	background-color: #FFFFFF;
	border-color: #DFDFDF;
	color: #555555;
}

input[type="text"]:focus,input[type="password"]:focus {
	background: none repeat scroll 0 0 #FDFDFD;
	border-color: rgba(82, 168, 236, 0.8);
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px
		rgba(82, 168, 236, 0.6);
	outline: 0 none
}

.my_log_bom {
	background: #ff5500;
	color: #FFF;
	font-weight: bold;
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.3);
	border: 1px solid;
	border-radius: 10px;
	cursor: pointer;
	font-family: sans-serif;
	font-size: 14px;
	padding: 4px 10px;
	text-decoration: none;
}

#backtoblog {
	margin: 0 0 0 16px;
	padding: 16px 16px 0;
	text-shadow: 0 1px 0 #FFFFFF;
}
</style>

</head>
<body>

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
			var u = $('#account').val();
			var p = $('#password').val();
			var y = $('#verify').val();
			//$("#button").attr("disabled","true");

			$.ajax({
				type : "post",
				dataType : 'json',
				url : '<s:url action="login/login"/>',
				data : {
					account : u,
					password : p,
					verify : y
				},
				beforeSend : function(XMLHttpRequest) {
					$('#button').val('正在登录...');
				},
				success : function(data, textStatus) {
					if (data.message == 1)
						window.location = '<s:url action="login/login" namespace="/admin"/>';
					else {
						//alert(data.message);
						$('#result').html(data.message);
						$('#button').val('登录');
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
					$('#button').val('登录');
				}
			});
		}
		function fleshVerify() {
			var timenow = new Date().getTime();
			$('#verifyImg').attr("src",
					'<s:url action="verify" namespace="/" />?d=' + timenow);
		}
	</script>

	<div class="my_login">
		<h1>
			<a href="/" title="请管理员们登录">请管理员们登录</a>
		</h1>
		<s:form id="login" name="login" method="post" theme="simple">
			<s:textfield label="UserName" name="account" cssClass="input"
				placeholder="登录名称" />
			<s:password label="PassWord" name="password" cssClass="input"
				placeholder="登录密码" />
			<label for="verify">
			<s:textfield label="验证码" name="verify" cssClass="input" size="5" cssStyle="float:left"
				placeholder="验证码" />
			<img alt="" src="<s:url action="verify" namespace="/" />" class="fl" id="verifyImg" onclick="fleshVerify()" style="cursor: pointer;float:left">
			</label><p>
			<s:submit onclick="onlogin();" value="登 录" cssClass="my_log_bom" id="button" name="submit" type="botton"/>
			
			<span id="result" style="margin-left:10px; line-height:30px; color:#F00;"></span>
			</p>

		</s:form>

		
		<p id="backtoblog">
			<a href="/" title="回首页">← 回到 首页</a>
		</p>
	</div>
	<div id="back-mask" class="back-mask"></div>
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