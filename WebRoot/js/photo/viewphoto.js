$(document).ready(function(){
	$("#photo").addClass("current");
	//加载照片，并默认当前照片在最前面
	loadphotobyid($.getUrlParam('imgId'),$.getUrlParam('ablumid'));
	//加载用户评论
	loadcomment();
	
});
function loadphotobyid(imgId,ablumid){
	//加载照片
    $.ajax({
			url: 'queryPhotos.do',
			dataType:'json',
			data:'ablumid='+ablumid,
			type: 'POST',
			success:function(json){
				var data = json.photos;
				if(null != data && data.length>0){
					var ul = $("#photolist");
					for(var i=0; i<data.length; i++)
					{			
						var li = $("<li>");
						var a = $("<a>");
						var img = "<img style='width:750px;height:100%;' src='"+getRootPath()+"/userfiles"+data[i].imgUrl+"."+data[i].extendName+"'/>";
						a.append(img);
						li.append(a);
						ul.append(li);
					}
				}
				afterload();
			}
	});
}

function afterload(){
	var sWidth = $("#focus").width(); //获取焦点图的宽度（显示面积）
	var len = $("#focus ul li").length; //获取焦点图个数
	var index = 0;
	var picTimer;
	
	//以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
	var btn = "<div class='btnBg'></div><div class='btn'>";
	for(var i=0; i < len; i++) {
		btn += "<span></span>";
	}
	btn += "</div><div class='preNext pre'></div><div class='preNext next'></div>";
	$("#focus").append(btn);
	$("#focus .btnBg").css("opacity",0.5);

	//为小按钮添加鼠标滑入事件，以显示相应的内容
	$("#focus .btn span").css("opacity",0.4).mouseover(function() {
		index = $("#focus .btn span").index(this);
		showPics(index);
	}).eq(0).trigger("mouseover");

	//上一页、下一页按钮透明度处理
	$("#focus .preNext").css("opacity",0.2).hover(function() {
		$(this).stop(true,false).animate({"opacity":"0.5"},300);
	},function() {
		$(this).stop(true,false).animate({"opacity":"0.2"},300);
	});

	//上一页按钮
	$("#focus .pre").click(function() {
		index -= 1;
		if(index == -1) {index = len - 1;}
		showPics(index);
	});

	//下一页按钮
	$("#focus .next").click(function() {
		index += 1;
		if(index == len) {index = 0;}
		showPics(index);
	});

	//本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
	$("#focus ul").css("width",sWidth * (len));
	
	//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
	$("#focus").hover(function() {
		clearInterval(picTimer);
	},function() {
		picTimer = setInterval(function() {
			showPics(index);
			index++;
			if(index == len) {index = 0;}
		},40000000); //此4000代表自动播放的间隔，单位：毫秒
	}).trigger("mouseleave");
	
	//显示图片函数，根据接收的index值显示相应的内容
	function showPics(index) { //普通切换
		var nowLeft = -index*sWidth; //根据index值计算ul元素的left值
		$("#focus ul").stop(true,false).animate({"left":nowLeft},300); //通过animate()调整ul元素滚动到计算出的position
		//$("#focus .btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果
		$("#focus .btn span").stop(true,false).animate({"opacity":"0.4"},300).eq(index).stop(true,false).animate({"opacity":"1"},300); //为当前的按钮切换到选中的效果
	}
}
			
function loadcomment(){
	//加载评论
    $.ajax({
			url: "queryComment.do",
			dataType:'json',
			data:$("#photoComment").serialize(),
			type: 'POST',
			success:function(json){
				var flag = json.dataMap.retCode;
				if(flag == "success"){
					var content = $("#mycontent ul");
					content.empty();
					var data = json.dataMap.data;
					for(var i=0; i<data.length; i++){
						var li = $("<li style='float:none;'>");
						//该条动态用户节点
						li.append("<i class='fix'></i>");
						content.append(li);
						//该条动态详细信息
						var usermsg = $("<div class='usermsg'>");
						var p = $("<p>");
						var p_span = $("<span>");
						p_span.html(data[i].username);
						p.append(p_span);
						
						var oldTime = (new Date("2011/11/11 20:10:10")).getTime(); //得到毫秒数 
						var newTime = new Date(data[i].commentTime); //得到普通时间 
	
						p.append("<span class='gray'>" + newTime + "</span>");
						var sign = $("<div class='sign'>");
						var sign_span = $("<span>");
						var comment = data[i].comment;
						comment = comment.length>40 ? comment.substring(0, 40)+"..." : comment;
						sign_span.html(comment);
						sign_span.attr("title", data[i].comment);
						sign.append(sign_span);
						usermsg.append(p);
						usermsg.append(sign);
						
						li.append(usermsg);
						li.append("<i class='fix'></i>");
						content.append(li);
						
					}
					$("#loading").hide();
				}
			}
	});
}

(function($){
	$.getUrlParam = function(name)
	{
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r!=null) return unescape(r[2]); return null;
	}
})(jQuery);

//获取网站根路径
function getRootPath(){
	var strFullPath=window.document.location.href;
	var strPath=window.document.location.pathname;
	var pos=strFullPath.indexOf(strPath);
	var prePath=strFullPath.substring(0,pos);
	var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
	return(prePath+postPath);
}
