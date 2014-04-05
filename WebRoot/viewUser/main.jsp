<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<script src="<%=request.getContextPath()%>/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/main.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/common_windows.js" type="text/javascript"></script>
<form id="main" method="POST" action="">
	<div id="right_home" class="right_home">
		<%@ include file="/layout/otherTab.jsp" %>
		<div class="maincontent">
			<!-- 签名 -->
			<div class="send_box">
			  <div class="info_submit" id="submit_btn" style="float: right; margin-left: 675px; position: absolute;  margin-top: 35px;">提交</div>
              <textarea name="info" id="info" onblur="if($(this).val()==''){$(this).text('发表一条状态，让朋友知道你~');}" onfocus="if($(this).text()=='发表一条状态，让朋友知道你~'){$(this).text('');}">发表一条状态，让朋友知道你~</textarea>
            </div>
            
            <div class="tab_1" style="margin:10px;"></div>
            
            <!-- 首页动态 -->
            <div class="mycontent" id="mycontent">
            	<span id="loading" class="loading">
            		<img src="<%=request.getContextPath()%>/images/loading.gif"/>
            	</span>
            	<ul></ul>
            </div>
		</div>
		
		
	</div>
</form>