<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/myfiles/Plugin/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="/myfiles/Plugin/kindeditor/lang/zh_CN.js"></script>
</head>
<body>

<script type="text/javascript">

KindEditor.ready(function(K) {
	var editor = K.editor({
		allowFileManager : true
	});
	K('#image1').click(function() {
		editor.loadPlugin('image', function() {
			editor.plugin.imageDialog({
				imageUrl : K('#url1').val(),
				clickFn : function(url, title, width, height, border, align) {
					K('#url1').val(url);
					editor.hideDialog();
				}
			});
		});
	});
	K('#image2').click(function() {
		editor.loadPlugin('image', function() {
			editor.plugin.imageDialog({
				showLocal : false,
				imageUrl : K('#url2').val(),
				clickFn : function(url, title, width, height, border, align) {
					K('#url2').val(url);
					editor.hideDialog();
				}
			});
		});
	});
	K('#image3').click(function() {
		editor.loadPlugin('image', function() {
			editor.plugin.imageDialog({
				showRemote : false,
				imageUrl : K('#url3').val(),
				clickFn : function(url, title, width, height, border, align) {
					K('#url3').val(url);
					editor.hideDialog();
				}
			});
		});
	});
});
</script>

<p><input type="text" id="url1" value="" /> <input type="button" id="image1" value="选择图片" />（网络图片 + 本地上传）</p>
<p><input type="text" id="url2" value="" /> <input type="button" id="image2" value="选择图片" />（网络图片）</p>
<p><input type="text" id="url3" value="" /> <input type="button" id="image3" value="选择图片" />（本地上传）</p>
	
</body>
</html>