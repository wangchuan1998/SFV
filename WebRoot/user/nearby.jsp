<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<title>阿尔萨斯在线</title>
<link href="<%=request.getContextPath()%>/css/layout.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/js/jquery-1.7.2.js" type="text/javascript"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/ChatManager.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>
<script src="<%=request.getContextPath()%>/js/nearby.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/common_windows.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/layer/layer.js" type="text/javascript"></script>
</head>
<body onload="init()">
<div id="container">
  <%@ include file="../layout/header.jsp" %>
  <%@ include file="../layout/menu.jsp" %>
  <div id="mainContent">
   	<%@ include file="../layout/sidebar.jsp" %>
	<div id="right_home" class="right_home">
		<%@ include file="/layout/tab.jsp" %>
		<div class="maincontent">	
			<div class="main" id="mainbox">
				<span id="loading" class="loading">
            		<img src="<%=request.getContextPath()%>/images/loading.gif"/>
            	</span>
			</div>
		</div>
	</div>
   	<div id="clear"></div>
  </div>
  <%@ include file="../layout/footer.jsp" %>

  <!-- web聊天窗口 start -->
  <input type="hidden" name="userid" value="<%=user.getId() %>"/>
  <span id="msg"></span>
  <div class="webpager" id="chat_div">
  	<div id="chat_template" class="panelbarpanels">
  	</div>
  	<div id="chat_tabpanels" class="tabpanels">
  		<dl class="tabitems">
  			<dt onclick="hideChatWindow()"><span>最近联系</span></dt>
  			<ul id="chat_tab"> 
			</ul> 
  		</dl>
  	</div>
  	<div id="chat_tabbottom" class="tabbottom">
  		<dl>
  			<dt onclick="showChatWindow()"><span>聊天</span></dt>
  		</dl>
  	</div>
  </div>
  <!-- web聊天窗口 end -->
</div>
</body>
</html>
