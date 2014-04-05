$(document).ready(function(){
	$("#friend").addClass("current");
	//加载附近人信息
	loadnearbyuser();
});

//初始化
function init(){
	dwr.engine.setActiveReverseAjax(true);
	dwr.engine.setNotifyServerOnPageUnload(true);
}

//加为好友
function addFriend(userid){
	$.ajax({
			url: "addFriend.do",
			dataType:'json',
			data:'refUserId='+userid,
			type: 'POST',
			success:function(){
				var dialog;
			    dialog = new Dialog_Notitle({width:300,height:100,title:""});
			    dialog.show();
			    var html = "";
			    html += "<font color='#FF6C1E' style='font-weight:bold;'>好友请求已经发送!</font>";
			    dialog.content.innerHTML = html;			    
			    setTimeout(function(){
			    	dialog.hide();
			    },1000);
			},
			error:function(){
				var dialog;
			    dialog = new Dialog_Notitle({width:300,height:100,title:""});
			    dialog.show();
			    var html = "";
			    html += "<font color='#FF6C1E' style='font-weight:bold;'>请求发送失败，请稍候再试!</font>";
			    dialog.content.innerHTML = html;			    
			    setTimeout(function(){
			    	dialog.hide();
			    },1000);
			}
	});
}

function loadnearbyuser(){
	//加载附近人信息
    $.ajax({
			url: "queryFriend.do",
			dataType:'json',
			type: 'POST',
			success:function(json){
				var flag = json.dataMap.retCode;
				if(flag == "success"){
					var main = $("#mainbox");
					main.empty();
					$("#loading").show();
					var data = json.dataMap.data;
					for(var i=0; i<data.length; i++){
						var user = data[i];
						var dl = $("<dl class='box2' id='dl_"+user.id+"'>");
						
						var dt = $("<dt>");
						var a = "<a href='profile!viewIndex.action?userid="+user.id+"'><img src='" + getRootPath() + user.headUrl + "'/></a>";
						dt.append(a);
						
						var dd = $("<dd>");
						var span1 = $("<span>");
						var b = $("<b>");
						var b_a = "<a title='" + user.realname + "'>" + user.realname + "</a>";
						b.append(b_a);
						span1.append(b);
						var span2 = "<span><i>" + (user.sex == "m" ? "男生" : "女生") + "</i><i>|</i><i>" + user.height + "</i></span>";
						var span3 = "<span><i>" + user.degree + "</i><i>|</i></span>";
						var sendMsg_a = "<a onclick='chatWindow(\"" + user.id + "\",\"" + user.realname + "\");' class='m_dzh'>短信息</a>";
						
						dd.append(span1);
						dd.append(span2);
						dd.append(span3);
						dd.append(sendMsg_a);
						
						dl.append(dt);
						dl.append(dd);
						main.append(dl);
					}
					$("#loading").hide();
				}
			},
			error:function(){
				var dialog;
    			dialog = new Dialog({width:200,height:120,title:"错误"});
    			dialog.show();
		      	var photoForm = "获取附近好友信息失败";
		      	dialog.content.innerHTML = photoForm;
			}
	});
}

//加载聊天窗口
function chatWindow(id, name){
	$("#chat_template").show();
	$("#chat_tabpanels").show();
	//聊天窗口如果已经加载，则直接激活
	if(isOpenChatWnd(id)){
		setTab(id);
		return;
	}
	
	createChatWindow(id, name);
	setTab(id);
}
//创建聊天窗口
function createChatWindow(id, name){
	//聊天窗口
	var div_window = $("<dl class='chat_window' style='display:block;' id='" + id + "'>");
	var div_header = $("<dl class='chat_header'>");
	div_header.html(name+"<span onclick='closeChatWindow(\"" + id +"\")' style='float: right; margin-right: 10px; cursor: pointer;'>x</span>");
	var chat_content = $("<dl class='chat_content'>");
	var chat_inputbox = $("<dl class='chat_inputbox'>");
	var chat_inputbox_header = $("<dl class='chat_inputbox_header'>");
	var chat_inputbox_msg = $("<dl class='chat_inputbox_msg'>");
	chat_inputbox_msg.append("<form id='chatForm'><textarea></textarea></form>");
	var chat_inputbox_send = $("<dl class='chat_inputbox_send'>");
	chat_inputbox_send.append("<a class='chat_send_btn' onclick='sendMessage(\"" + id + "\")'>发送</a>");
	chat_inputbox.append(chat_inputbox_header);
	chat_inputbox.append(chat_inputbox_msg);
	chat_inputbox.append(chat_inputbox_send);
	
	div_window.append(div_header);
	div_window.append(chat_content);
	div_window.append(chat_inputbox);
	div_window.append("");
	$("#chat_template").append(div_window);
	
	//菜单tab
	var chat_tab = "<li id='tab_" + id + "' onclick='setTab(\"" + id + "\")'><span>" + name + "</span></li>";
	$("#chat_tab").append(chat_tab);
}

//发送聊天消息
function sendMessage(id){
	var dl = $("#" + id);
	var textarea = dl.children().eq(2).children().eq(1).find("textarea");
	var chat_content = dl.children().eq(1);
	// 获得消息内容
	var msg = textarea.val();
	/**
	*发送消息
	*id：接收者ID
	*msg：消息内容
	*/
	ChatManager.send(id, msg);
	
	//显示发送消息
	var section = $("<div class='chat_content_section_self'>");
	section.html("<font color='#888888'>我 " + getCurDate() + "<br></font><span>" + msg + "</span>");
	chat_content.append(section);
	textarea.val("");
}
//接收聊天消息
function showMessage(msg, id, name){
	var dl = $("#" + id);
	//如果聊天窗口不存在，则首先创建聊天窗口
	if(dl.length == 0){
		createChatWindow(id,name);
		$("#chat_tabbottom").css({"background-color":"#FF6C1E"});
	}
	dl = $("#" + id);
	var chat_content = dl.children().eq(1);
	//显示接收消息
	var section = "<div class='chat_content_section'>" + msg + "</div>";
	chat_content.append(section);
}

/**
*功能说明：关闭聊天窗口
*		 如果只有一个聊天窗口，则关闭整个聊天面板
*        如果有多个聊天窗口，则关闭当前窗口，激活下一个聊天窗口
*/
function closeChatWindow(id){
	var chatWindow = $("#" + id);
	var chatTab = $("#tab_" + id);
	if($("#chat_template").children().length > 1){
		if(chatWindow.next().length > 0){
			setTab(chatWindow.next().attr("id"));
		}else{
			setTab(chatWindow.prev().attr("id"));
		}
	}else{
		$("#chat_template").hide();
		$("#chat_tabpanels").hide();
	}
	chatWindow.remove();
	chatTab.remove();
}
//隐藏聊天面板
function hideChatWindow(){
	$("#chat_template").hide();
	$("#chat_tabpanels").hide();
}
//显示聊天面板
function showChatWindow(){
	$("#chat_tabbottom").css({"background-color":"#E9E9E8"});
	if($("#chat_tabpanels").is(":hidden")){
		$("#chat_template").show();
		$("#chat_tabpanels").show();
	}else{
		hideChatWindow();
	}
}

//判断聊天窗口是否已经加载
function isOpenChatWnd(id){
	if($("#"+id).length > 0){
		return true;
	}
	return false;
}
//激活指定聊天窗口，只能有一个聊天窗口处于激活状态
function setTab(id){
	var chatWindows = $("#chat_template").children();
	var chatTabs = $("#chat_tab").children();
	chatWindows.each(function(i,n){
		if(id == $(n).attr("id")){
			$(n).show();
		}else{
			$(n).hide();
		}
	});
	chatTabs.each(function(i,n){
		if(("tab_"+id) == $(n).attr("id")){
			$(n).addClass("current");
		}else{
			$(n).removeClass("current");
		}
	});
}
//获取当前时间yyyy-MM-dd hh:mm
function getCurDate(){
	var myDate = new Date();
    var year = myDate.getFullYear();   //获取完整的年份(4位,1970-????)
    var month = myDate.getMonth();      //获取当前月份(0-11,0代表1月)
    var day = myDate.getDate();       //获取当前日(1-31)
    var hour = myDate.getHours();      //获取当前小时数(0-23)
    var minute = myDate.getMinutes();    //获取当前分钟数(0-59)
    return year + "-" + month + "-" + day + " " + hour + ":" + minute;
 	
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