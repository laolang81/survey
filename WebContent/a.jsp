<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<base
	href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="myfiles/Plugin/kindeditor/themes/default/default.css" />
<script type="text/javascript" charset="utf-8" src="myfiles/Plugin/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="myfiles/Plugin/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="myfiles/js/jquery-1.9.1.min.js"></script>
</head>
<body>
<script type="text/javascript">



</script>

<p><input type="text" id="url1" value="" /> <input onclick="create(this)" type="button" id="image1" value="1" />1</p>
<p><input type="text" id="url2" value="" /> <input onclick="create(this)" type="button" id="image2" value="2" />2</p>
<p><input type="text" id="url3" value="" /> <input onclick="create(this)" type="button" id="image3" value="3" />3</p>
<input type="button" value="add" name="add" onclick="add()">
<script type="text/javascript">

var K=window.KindEditor;

function add()
{
	id = $('p').size();
	id = id + 1;
	html ='<p><input type="text" id="url'+id+'" value="" /> <input onclick="create(this)" type="button" id="image'+id+'" value="'+id+'" />'+id+'</p>';
	$('p').last().after(html);
	create('#image' + id);
}

	
function create1(input)
{
	id = $(input).prev().attr('id');
	id = "#" + id;
	editor1 = K.editor( {
		allowFileManager : true,
        extraFileUploadParams : {
            item_id : 1000,
            category_id : 1,
            info : $(id).val()
   		 }
	});
		

	
	
		editor1.loadPlugin('image', function() {
			editor1.plugin.imageDialog({
				showRemote : false,
				imageUrl : K(id).val(),
				clickFn : function(url, title, width, height, border, align) {
					//K(id).val(url);
					editor1.hideDialog();
				}
			});
		});
	
}

function create(input)
{
	
	previd = $(input).prev().attr('id');
	previd = "#" + previd;
	
	id = $(input).attr('id');
	id = "#" + id;
	
	var uploadbutton = K.uploadbutton({
		button : K(id)[0],
		fieldName : 'imgFile',
		//url : '../php/upload_json.php?dir=file',
		extraFileUploadParams : {
            item_id : 1000,
            category_id : 1,
            info : $(id).val()
   		},
		afterUpload : function(data) {
			if (data.error === 0) {
				var url = K.formatUrl(data.url, 'absolute');
				K('#url').val(url);
			} else {
				alert(data.message);
			}
		},
		afterError : function(str) {
			alert('自定义错误信息: ' + str);
		}
	});
	uploadbutton.fileBox.change(function(e) {
		uploadbutton.submit();
	});
		
}


</script>
</body>
</html>