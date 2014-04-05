<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<div class="tab_1" id="tab_1">
	<ul>
		<li id="mypage"><a href="<%=request.getContextPath()%>/viewIndex.do?userid=${user.id }"><span>${user.realname }的首页</span></a></li>
		<li id="photo"><a href="<%=request.getContextPath()%>/viewAlbums.do?userid=${user.id }"><span>相册</span></a></li>
		<li id="article"><a href="<%=request.getContextPath()%>/viewIndex.do?userid=${user.id }"><span>日志</span></a></li>
		<li id="setting"><a href="<%=request.getContextPath()%>/viewUser.do?userid=${user.id }"><span>详细资料</span></a></li>
	</ul>
</div>