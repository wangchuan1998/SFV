$(document).ready(function(){
	$("#setting").addClass("current");
});

var basicInfo = {
	init:function(){
		var username = $("#realname");
		var height = $("#height");
		username.blur(this.chk_username);
		height.blur(this.chk_height);
	},
	chk_username: function(){
		var username = $('#realname');
		username.next('span').innerHTML = "";
		username.removeClass('form_input_error');
		var msg = 'init';
		var sum = 0; 

		for(var i=0;i<username.val().length;i++){
		   var c = username.val().charCodeAt(i);
		   if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) { 
				sum++;
		   }else{
				sum+=2;}

			var str = username.val().charAt(i).charCodeAt(0);
			if(str==32){
				msg = '只能使用中文、字母、数字、下划线或减号';
			}else if( ((str>=48 && str<=57) || 
					(str>=65 && str<=90) || 
					(str>=97 && str<=122)||
					 str==45 || str==95 || //包含_ -
					(str>19543 && str<41652) //41377-63486:GB2312
				)==false ){
				msg = '只能使用中文、字母、数字、下划线或减号';
			}
		}
		
		if(trim(username.val())=='') {
			msg = '请输入昵称';
		}else if(sum>30){
			msg = '昵称不能超过15个汉字或30个字符';
		}else if(sum<4){
			msg = '昵称至少要有2个汉字或4个字符';
		}else if(isNaN(username.val())==false && username.val()>0){
			msg = '昵称不能全部使用数字';
		}
		
		if(msg != 'init'){
			username.next('span').removeClass('eg').addClass('error').html(msg);
			username.addClass('form_input_error');
			basicInfo.username = false;
			return false;
		}else{
			username.next('span').removeClass('msg').addClass('eg').html("");
			basicInfo.username = true;
			return true;
		}
	},
	chk_height: function(){
		var height = $('#height');
		height.next('span').innerHTML = "";
		height.removeClass('form_input_error');
		var msg = 'init';
		if(isNaN(trim(height.val()))){
			msg = '输入格式不正确';
		}
		
		if(msg != 'init'){
			height.next('span').removeClass('eg').addClass('error').html(msg);
			height.addClass('form_input_error');
			basicInfo.height = false;
			return false;
		}else{
			height.next('span').removeClass('msg').addClass('eg').html("");
			basicInfo.height = true;
			return true;
		}
	},
	//返回组成类型：1种类型字符 2种类型字符 3种类型字符
	chars: function(str){
        var a=0,b=0,c=0;
		for(var i=0;i<str.length;i++){
			if(str.charAt(i).charCodeAt(0)>=48 && str.charAt(i).charCodeAt(0) <=57){//数字
				a = 1;
			}else if( (str.charAt(i).charCodeAt(0)>=65 && str.charAt(i).charCodeAt(0)<=90) || (str.charAt(i).charCodeAt(0)>=97 && str.charAt(i).charCodeAt(0)<=122) ){//字母
				b = 1;
			}else{//符号
				c = 1;
			}
		}
		return (a+b+c);
	},
	//提交基本信息
	_submit: function(){
		$("#region").val($("#province option:selected").text()+"/"+$("#city option:selected").text()+"/"+$("#district option:selected").text());
		$("#basicInfoForm").submit();
	}
}

function trim(s){ 
	var _s = s.replace( /^(\s*|　*)/, ""); 
	return _s.replace( /(\s*|　*)$/, "");
} 



//获取网站根路径
function getRootPath(){
	var strFullPath=window.document.location.href;
	var strPath=window.document.location.pathname;
	var pos=strFullPath.indexOf(strPath);
	var prePath=strFullPath.substring(0,pos);
	var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
	return(prePath+postPath);
} 