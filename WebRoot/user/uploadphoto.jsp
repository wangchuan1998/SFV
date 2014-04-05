<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<title>阿尔萨斯在线</title>
<link href="<%=request.getContextPath()%>/css/layout.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/sidebar.css" rel="stylesheet" type="text/css" />
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
	<%@ taglib prefix="s" uri="/struts-tags" %> 
	<div id="right_home" class="right_home">
		<%@ include file="/layout/tab.jsp" %>
		<div class="maincontent">		
			<div class="layout">
				<div class="tit"><label><b>上传照片</b></label></div>
				<!-- 照片上传窗口 -->
				<div id="uploadPhotoDiv">
					<form id="uploadMyPhotoForm">
							<div style="float: left;margin:10px 0 0 20px;width: 100px;">
								<input type="file" id="fileInput" name="fileInput" onchange="javascript:$('#fileInput').uploadifyUpload();"/>
							</div>
							<div style="margin:14px 0 0 0;width:590px;float:right">
								<select name="ablumid">
									<s:iterator value="%{albums}">
										<option value="${id}">${name}</option>
									</s:iterator>
								</select>
							</div>
							<div id="fileQueue" style="float: left;margin: 14px 20% 10px 20%;width: 60%;"></div>
							<div id="uploadFileList" style="margin: 14px 0 10px 2%;">
								<img id="review" src="<%=request.getContextPath()%>/images/default_f_l.jpg" title="" style="width:150px;height:150px;margin:10px;"/>
							</div>
							<div class="info_submit" style="float: left;margin: 14px 20px 5px;width: 50;" id="info_submit">提交</div>
					</form>
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

<script>

function deleteSelected(serverPath, fileNameId){
	$.ajax({
		url: baseUrl + '/deletephoto!deleteSelected.action',
		data:{fileNameAtServer:serverPath},
		type: 'POST',
		dataType:'json',
		success:function(json){
			if(json.dataMap.retCode == "success")
			{
				$("#"+fileNameId).remove();
				$("#uploadMyPhotoForm").serialize();
			}
			else
			{
				$("#"+fileNameId).append("<font color=red>照片删除失败,请重试</font>");
			}
		},
		error:function(){
			alert("删除照片失败");
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
$(document).ready(function(){
	$("#photo").addClass("current");
	$('#fileInput').uploadify({   
        'uploader': 'include/uploadify.swf',
        'script': 'uploadPhoto.do;jsessionid=<%=session.getId()%>',   //指定服务端处理类的入口
        'folder': 'temp',   
        'cancelImg': 'include/cancel.png',
        'fileDataName': 'fileInput', //和input的name属性值保持一致就好，Struts2就能处理了   
        'queueID': 'fileQueue',   
        'auto': true,//是否选取文件后自动上传   
        'multi': true,//是否支持多文件上传
        'simUploadLimit' : 5,//每次最大上传文件数   
        'buttonText': 'Browse',//按钮上的文字
        'displayData': 'percentage',//有speed和percentage两种，一个显示速度，一个显示完成百分比    
        'onComplete': function (event, queueID, fileObj, response, data){
			var data = eval("(" + response + ")");
			var imgHTML = "<img src='<%=request.getContextPath()%>"+data.filePath+"' style='width:150px;height:150px;margin:10px;'/>";
          	var hiddenHTML = "<input type='hidden' name='fileNameAtServer' value='"+data.fileNameAtServer+"' />";
          	var fileRealNameHTML = "<input type='hidden' name='fileRealName' value='"+data.fileRealName+"' />";
          	$("#uploadFileList").prepend(imgHTML);
          	$("#uploadFileList").prepend(hiddenHTML);
          	$("#uploadFileList").prepend(fileRealNameHTML);
          }  
    });
    
    $("#info_submit").click(function(){
     	$.ajax({
			url: baseUrl + '/savephoto.do',
			data:$("#uploadMyPhotoForm").serialize(),
			type: 'POST',
			dataType:'json',
			success:function(json){
				if(json.dataMap.retCode == "success")
				{
					alert("照片保存成功");
					window.location.href = baseUrl + '/queryAlbums.do';
				}
				else
				{
					alert("照片保存失败");
				}
			},
			error:function(){
				alert("保存照片失败");
			}
		});
    });
});
</script>
