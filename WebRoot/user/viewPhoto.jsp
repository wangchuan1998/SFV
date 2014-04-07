<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<title>阿尔萨斯在线</title>
<link href="<%=request.getContextPath()%>/css/layout.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.clearfix:after{content: ".";display: block;height: 0;clear: both;visibility: hidden;}
.clearfix{zoom:1;}
ul,li{list-style:none;}
img{border:0;}
.wrapper{width:720px;margin:0 auto;padding-bottom:50px;}
h1{height:50px;line-height:50px;font-size:22px;font-weight:normal;font-family:"Microsoft YaHei",SimHei;margin-bottom:20px;}
/* focus */
#focus{width:720px;height:380px;overflow:hidden;position:relative;}
#focus ul{height:380px;position:absolute;}
#focus ul li{float:left;width:720px;height:380px;overflow:hidden;position:relative;background:#000;}
#focus ul li div{position:absolute;overflow:hidden;}
#focus .btnBg{position:absolute;width:720px;height:20px;left:0;bottom:0;background:#000;}
#focus .btn{position:absolute;width:720px;height:10px;padding:5px 10px;right:0;bottom:0;text-align:right;}
#focus .btn span{display:inline-block;_display:inline;_zoom:1;width:25px;height:10px;_font-size:0;margin-left:5px;cursor:pointer;background:#fff;}
#focus .btn span.on{background:#fff;}
#focus .preNext{width:45px;height:100px;position:absolute;top:90px;background:url(../js/photo/img/sprite.png) no-repeat 0 0;cursor:pointer;}
#focus .pre{left:0;}
#focus .next{right:0;background-position:right top;}
</style>
</head>
<body>
<div id="container">
  <%@ include file="../layout/header.jsp" %>
  <%@ include file="../layout/menu.jsp" %>
  <div id="mainContent">
   	<%@ include file="../layout/sidebar.jsp" %>
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.2.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/photo/viewphoto.js" type="text/javascript"></script>
	<%@ taglib prefix="s" uri="/struts-tags" %> 
	<div id="right_home" class="right_home">
		<%@ include file="/layout/tab.jsp" %>
		<div class="maincontent">		
			<!-- 相册 -->
			<div class="layout">
				<div id="photopage" class="photopage">
					<div class="uploadphoto">
						<a class="uploadbtn" id="uploadPhoto" href="selectPhoto!selectPhoto.action">上传照片</a>
						<a class="uploadbtn" id="createAlbum" href="#">创建相册</a>
					</div>		

					<div class="wrapper">					
						<div id="focus">
							<ul id="photolist">
							</ul>
						</div><!--focus end-->
					
					</div><!-- wrapper end -->
			
					<div class="tit"></div>	
					<!-- 加载评论 -->
		            <div id="mycontent">
		            	<span id="loading" class="loading">
		            		<img src="<%=request.getContextPath()%>/images/loading.gif"/>
		            	</span>
		            	<ul></ul>
		            </div>
					<div class="send_box" style="text-align:center;">
		  			  <form id="photoComment">
			  			  <div class="info_submit" id="submit_btn" style="float: right; margin-left: 655px; position: absolute;  margin-top: 35px;">提交</div>
			              <textarea style="width:700px;" id="comment" name="comment"></textarea>
			              <input name="imgId" type="hidden" value="${photoForm.id }"></input>
		              </form>
		            </div>
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