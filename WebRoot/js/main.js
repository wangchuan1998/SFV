$(document).ready(function(){
    $("#tab_1 ul li:first-child").addClass("current");
    $("div.maincontent").find("div.layout:not(:first-child)").hide();
    $("div.maincontent div.layout").attr("id", function(){return "maincontent_layout"+ $("div.maincontent div.layout").index(this)});
    $("ul li").click(function(){
        var c = $("ul li");
        var index = c.index(this);
        show(c,index,"maincontent_layout");
    });

    function show(controlMenu,num,prefix){
        var content= prefix + num;
        $('#'+content).siblings().hide();
        $('#'+content).show();
        controlMenu.eq(num).addClass("current").siblings().removeClass("current");
    };
    
    //添加好友
    $("#addFriend").click(function(){
    	var dialog;
    	dialog = new Dialog_Normal({width:200,height:120,title:"添加好友中..."});
		var loading = "<span style='margin:27px 0 0 43px;float:left;'><img src='" + getRootPath() + "/images/loading2.gif'></span>";
		dialog.content.innerHTML = loading;
    	dialog.show();
    	$.ajax({
			url: 'addFriend.do',
			data:$("#addFriendForm").serialize(),
			type: 'POST',
			dataType:'json',
			success:function(json){
				if(json.dataMap.retCode == "success")
				{
					$("#addFriendForm").html("");
					dialog.hide();
				}
				else
				{
					alert("添加好友失败");
					dialog.hide();
				}
			},
			error:function(){
				alert("添加好友失败");
				dialog.hide();
			}
		});
    	
		
	});
    
    //异步提交用户签名
    $("#submit_btn").click(function(){
    	var dialog;
    	dialog = new Dialog_Normal({width:200,height:120,title:"更新签名中..."});
    	dialog.show();
		var photoForm = "<span style='margin:27px 0 0 43px;float:left;'><img src='" + getRootPath() + "/images/loading2.gif'></span>";
		dialog.content.innerHTML = photoForm;
		
    	$.ajax({
			url: 'saveSign!save.action',
			data:$("#main").serialize(),
			type: 'POST',
			dataType:'json',
			success:function(json){
				if(json.dataMap.retCode == "success")
				{
					loadusersign();
					dialog.hide();
				}
				else
				{
					alert("发表签名失败");
					dialog.hide();
				}
			},
			error:function(){
				alert("发表签名失败");
				dialog.hide();
			}
		});
    	
		
	});
    
    
    //打开首页时加载用户签名
    loadusersign();
 });
/**
*通用异步提交方法
*/
var Main = function(){
    var options = arguments[0] || {};
    this.url = options.url;
	this.formId = options.id;
};
Main.prototype = {
	submit: function() {
		$.ajax({
			url: this.url,
			data:$("#"+this.formId).serialize(),
			type: 'POST',
			success:function(json){

			},
			error:function(){
			
			}
		});
	}
};

function loadusersign(){
	layer.load();
	//加载用户签名
    $.ajax({
			url: "query.do",
			dataType:'json',
			type: 'POST',
			success:function(json){
				var flag = json.dataMap.retCode;
				if(flag == "success"){
					var content = $("#mycontent ul");
					content.empty();
					$("#loading").show();
					var data = json.dataMap.data;
					for(var i=0; i<data.length; i++){
						var li = $("<li>");
						var user = data[i].user;
						//该条动态用户头像节点
						var userhead = $("<div class='userhead'>");
						var a = "<a class='head'><img src='" + getRootPath() + user.headUrl + "'/></a>";
						userhead.append(a);
						li.append(userhead);
						li.append("<i class='fix'></i>");
						content.append(li);
						//该条动态详细信息
						var usermsg = $("<div class='usermsg'>");
						var p = $("<p class='msgtl'>");
						var p_span = $("<span>");
						p_span.html(user.realname);
						p.append(p_span);
						p.append("："+data[i].actionDesc);
						var sign = $("<div class='sign'>");
						var sign_span = $("<span>");
						var info = data[i].info;
						info = info.length>40 ? info.substring(0, 40)+"..." : info;
						sign_span.html(info);
						sign_span.attr("title", data[i].info);
						sign.append(sign_span);
						usermsg.append(p);
						usermsg.append("<span class='gray'>" + data[i].time + "</span>");
						usermsg.append(sign);
						
						li.append(userhead);
						li.append(usermsg);
						li.append("<i class='fix'></i>");
						content.append(li);
						$("#loading").hide();
						
					}
				}
				layer.loadClose();
			},
			error:function(){
		      	layer.alert('获取好友动态失败', 2, -1);
			}
	});

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