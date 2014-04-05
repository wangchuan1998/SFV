<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<title>阿尔萨斯在线</title>
<link href="<%=request.getContextPath()%>/css/layout.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css" />
<style type="text/css"> 
/* 无缝滚动图片 */
.marqueeleft{height:136px;margin:10px;overflow:hidden;}
.marqueeleft ul{float:left;}
.marqueeleft li{float:left;margin:0 5px;display:inline;width:148px;height:133px;overflow:hidden;}
.marqueeleft li .pic{display:block;border:#ccc 1px solid;width:135px;height:104px;padding:2px;overflow:hidden;}
.marqueeleft li .txt{text-align:center;height:23px;line-height:23px;}
</style>

<script type="text/javascript"> 
//js无缝滚动代码
function marquee(i, direction){
	var obj = document.getElementById("marquee" + i);
	var obj1 = document.getElementById("marquee" + i + "_1");
	var obj2 = document.getElementById("marquee" + i + "_2");
	if (obj2.offsetWidth - obj.scrollLeft <= 0){
		obj.scrollLeft -= obj1.offsetWidth;
	}else{
		obj.scrollLeft++;
	}
}
 
function marqueeStart(i, direction){
	var obj = document.getElementById("marquee" + i);
	var obj1 = document.getElementById("marquee" + i + "_1");
	var obj2 = document.getElementById("marquee" + i + "_2");
 
	obj2.innerHTML = obj1.innerHTML;
	var marqueeVar = window.setInterval("marquee("+ i +", '"+ direction +"')", 40);
	obj.onmouseover = function(){
		window.clearInterval(marqueeVar);
	}
	obj.onmouseout = function(){
		marqueeVar = window.setInterval("marquee("+ i +", '"+ direction +"')", 40);
	}
}
</script>
</head>
<body>
<div id="container">
  <%@ include file="../layout/header.jsp" %>
  
  <%@ include file="../layout/menu.jsp" %>
  <div id="mainContent">
   	<%@ include file="../layout/sidebar.jsp" %>
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.2.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/photo.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/layer/layer.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/layer/layer.min.js" type="text/javascript"></script>
	<%@ taglib prefix="s" uri="/struts-tags" %> 
	<div id="right_home" class="right_home">
		<%@ include file="/layout/tab.jsp" %>
		<div class="maincontent">		
			<!-- 相册 -->
			<div class="layout">
				<div id="photopage" class="photopage">
					<div class="uploadphoto">
						<a class="uploadbtn" id="uploadPhoto" href="selectPhoto.do">上传照片</a>
						<a class="uploadbtn" id="createAlbum" href="#">创建相册</a>
					</div>
					<s:iterator value="%{albums}">
							<div class="tit">
								<label><b>${name }</b></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span>${description }</span>
							</div>
							<div id="marquee${id }" class="marqueeleft">
								<div style="width:8000px;">
								 <ul id="marquee${id}_1">
									<script type="text/javascript">
											$.ajax({
												url: 'queryPhotos.do',
												data:'ablumid=${id}',
												type: 'POST',
												dataType:'json',
												success:function(json){
													var data = json.photos;
													if(null != data && data.length>0){
														for(var i=0; i<data.length; i++){
															var liHTML = "<li><a href='user/viewPhoto.jsp?imgId="+data[i].id+"&ablumid=${id}"
																+"'><img style='width:135px;height:104px;' src='<%=request.getContextPath()%>/userfiles"
																+data[i].imgUrlS+"."+data[i].extendName+"'/></a>"
																+"<div class='txt'>"+data[i].name+"</div></li>";
															$("#marquee"+${id}+"_1").append(liHTML);
														}
													}
													marqueeStart("${id}", "up");
												},
												error:function(){
												
												}
											});				
									</script>
								</ul>
								<ul id="marquee${id}_2"></ul>
							</div>
						</div>
						<script type="text/javascript"></script>
						<div class="tit"></div>
					</s:iterator>
				</div>		
			</div>
		</div>
	</div>
   	<div id="clear"></div>
  </div>
  <%@ include file="../layout/footer.jsp" %>
</div>


</body>
</html>

