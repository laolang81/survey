/**
 * 不断的获取用户聊天信息
 */
function getvar()
{
	setTimeout("getvar()", 10000);
	//获取信息
	$.post('/doftec/index/getvar', {type:'time'}, 
			function (data, textStatus){			
			$('#varpost').html(data.post);		
			$('#vargbk').html(data.gbk);			
		},'json');
}
getvar();