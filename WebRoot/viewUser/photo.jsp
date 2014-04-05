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
	<script src="<%=request.getContextPath()%>/js/photo.js" type="text/javascript"></script>
	<%@ taglib prefix="s" uri="/struts-tags" %> 
	<div id="right_home" class="right_home">
		<%@ include file="/layout/otherTab.jsp" %>
		<div class="maincontent">		
			<!-- 相册 -->
			<div class="layout">
				<div id="photopage" class="photopage">
					<s:iterator value="%{albums}">
						<div class="tit"><label><b>${name }</b></label></div>
						<ul class="nav">
							<s:iterator value="%{photos}">
								<li>
									<a href="viewPhoto!viewPhoto.action?imgId=${id }&userid=${user.id }">
										<IMG style="width:200px;height:200px;" src="<%=request.getContextPath()%>/userfiles${imgUrlS }.${extendName }" border=0 />
									</a>
								</li>
							</s:iterator>
						</ul>
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