<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<title>阿尔萨斯在线</title>
<link href="<%=request.getContextPath()%>/css/layout.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="container">
  <%@ include file="../layout/header.jsp" %>
  <%@ include file="../layout/otherMenu.jsp" %>
  <div id="mainContent">
   	<%@ include file="../layout/otherSidebar.jsp" %>
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.2.js" type="text/javascript"></script>
	<%@ taglib prefix="s" uri="/struts-tags" %> 
	<div id="right_home" class="right_home">
		<%@ include file="/layout/otherTab.jsp" %>
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
								${user.realname }
							</div>
				  			<div class="entry">
								<label>性别：</label>
								${user.sex=='f'?'女':'男' }
							</div>
				  			<div class="entry">
								<label>身高：</label>
								${user.height }
							</div>
				  			<div class="entry">
								<label>出生日期：</label>
								${user.birthDay }
							</div>
				  			<div class="entry">
								<label>学历：</label>
								${user.degree }
							</div>
							<div class="entry">
								<label>所在地：</label>
								${user.region }
							</div>
					  	<div id="clear"></div>
					  	</div>
					  	<div class="tit"></div>
						
				    </form>				
				</div>
				
			</div>
		</div>
	</div>
   	<div id="clear"></div>
  </div>
  <%@ include file="../layout/footer.jsp" %>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#setting").addClass("current");
});
</script>
</body>
</html>