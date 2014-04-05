<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<title>阿尔萨斯在线</title>
<link href="<%=request.getContextPath()%>/css/layout.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/include/uploadify.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="container">
  <%@ include file="../layout/header.jsp" %>
  <%@ include file="../layout/menu.jsp" %>
  <div id="mainContent">
   	<%@ include file="../layout/sidebar.jsp" %>
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.2.js" type="text/javascript"></script>
	<script language="javascript" src="<%=request.getContextPath()%>/include/swfobject.js"></script>
	<script language="javascript" src="<%=request.getContextPath()%>/include/jquery.uploadify.v2.1.0.js"></script>
	<script src="<%=request.getContextPath()%>/js/userinfo.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.location.js" type="text/javascript"></script>
	<%@ taglib prefix="s" uri="/struts-tags" %> 
	<div id="right_home" class="right_home">
		<%@ include file="/layout/tab.jsp" %>
		<div class="maincontent">		
			<!-- 布局 -->
			<div class="layout">
				<div id="main" class="main">
					
					<form id="basicInfoForm" action="save!save.action" method="post">  
						<div class="tit">
							<b>基本信息</b>
						</div>
						<div class="info_form">
				  			<div class="entry">
								<label>邮箱：</label>
								${user.email }
							</div>
				  			<div class="entry">
								<label>昵称：</label>
								<input type="text" name="realname" id="realname" value="${user.realname }" class="form_input" title="2-15个汉字或4-30个字符"/>
								<span class="msg">2-15个汉字或4-30个字符</span>
							</div>
				  			<div class="entry">
								<label>性别：</label>
								${user.sex=='f'?'女':'男' }
							</div>
				  			<div class="entry">
								<label>身高：</label>
								<input type="text" name="height" id="height" value="${user.height }" class="form_input" title="请输入整数"/>cm
								<span class="msg"></span>
							</div>
				  			<div class="entry">
								<label>出生日期：</label>
								<input type="text" id="birthDay" name="birthDay" class="Wdate" onFocus="WdatePicker({isShowWeek:true, skin:'twoer'})"/>
							</div>
				  			<div class="entry">
								<label>学历：</label>
								<select id="degree" name="degree">
                      				<option value='<s:text name="userinfo.field.degree.1"></s:text>'><s:text name="userinfo.field.degree.1"></s:text></option>
                      				<option value='<s:text name="userinfo.field.degree.2"></s:text>'><s:text name="userinfo.field.degree.2"></s:text></option>
                      				<option value='<s:text name="userinfo.field.degree.3"></s:text>'><s:text name="userinfo.field.degree.3"></s:text></option>
                      				<option value='<s:text name="userinfo.field.degree.4"></s:text>'><s:text name="userinfo.field.degree.4"></s:text></option>
                      				<option value='<s:text name="userinfo.field.degree.5"></s:text>'><s:text name="userinfo.field.degree.5"></s:text></option>
                      				<option value='<s:text name="userinfo.field.degree.6"></s:text>'><s:text name="userinfo.field.degree.6"></s:text></option>
                      				<option value='<s:text name="userinfo.field.degree.7"></s:text>'><s:text name="userinfo.field.degree.7"></s:text></option>
                      				<option value='<s:text name="userinfo.field.degree.8"></s:text>'><s:text name="userinfo.field.degree.8"></s:text></option>
  	                            </select>
							</div>
							<div class="entry">
								<label>所在地：</label>
								<div id="location" style="float: right;width: 574px;"></div>
								<input id="region" name="region" type="hidden"/>
								<script>
									$("#location").location({xmlpatch:"js/Location.xml"});
								</script>
							</div>
					  	<div id="clear"></div>
					  	</div>
					  	<div class="tit"></div>
						
						<div class="tit">
							<b>个人头像</b>
						</div>
						<div id="uploadPhotoDiv" style="display:'';">
							<form id="uploadMyPhotoForm">
								<div style="text-align:left;margin-top:10px;" class="info_form">
									<div class="entry">
										<div>
											<img id="review_pic" src="<%=request.getContextPath()%>/images/default_f_s.jpg" title="" style="width:70px;height:80px;"/>
			    							<input type="file" id="fileInput" name="fileInput" />
			    							<a href="javascript:$('#fileInput').uploadifyUpload();">开始上传</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="javascript:$('#fileInput').uploadifyClearQueue();">取消上传队列</a>
											<input type="hidden" name="headUrl" id="headUrl" />
											<div id="fileQueue"></div>
											<div id="result"></div>
										</div>
									</div>
								</div>
							</form>
						</div>
				    </form>
					<div class="tit"></div>					
				</div>
				<div class="info_submit" style="margin-left:30px;" id="info_submit" onclick="basicInfo._submit()">提交</div>
			</div>
		</div>
	</div>
   	<div id="clear"></div>
  </div>
  <%@ include file="../layout/footer.jsp" %>
</div>

</body>
</html>
<script>
$(document).ready(function(){
	basicInfo.init();
    $('#fileInput').uploadify({   
        'uploader': 'include/uploadify.swf',
        'script': 'uploadHead!upload.action;jsessionid=<%=session.getId()%>',
        'folder': 'temp',   
        'cancelImg': 'include/cancel.png',
        'fileDataName': 'fileInput', 
        'queueID': 'fileQueue',   
        'auto': true,//是否选取文件后自动上传   
        'multi': true,//是否支持多文件上传
        'simUploadLimit' : 5,//每次最大上传文件数   
        'buttonText': 'BrowseFiles',//按钮上的文字 
          
        'displayData': 'speed',//有speed和percentage两种，一个显示速度，一个显示完成百分比    
        'onComplete': function (event, queueID, fileObj, response, data){
			$('#review_pic').attr('src',getRootPath()+response);
			$('#headUrl').val(response);
          }  
           
    });
});
</script>