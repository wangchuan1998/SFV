<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<div class="tab_1" id="tab_1">
	<ul>
		<li id="mypage"><a href="<%=request.getContextPath()%>/index.do"><span>个人首页</span></a></li>
		<li id="nearby"><a href="<%=request.getContextPath()%>/user/nearby.jsp"><span>随便看看</span></a></li>
		<li id="friend"><a href="<%=request.getContextPath()%>/user/myfriend.jsp"><span>我的好友</span></a></li>
		<li id="photo"><a href="<%=request.getContextPath()%>/queryAlbums.do"><span>我的相册</span></a></li>
		<li id="article"><a href="<%=request.getContextPath()%>/index.do"><span>我的日志</span></a></li>
		<li id="setting"><a href="<%=request.getContextPath()%>/userinfo.do"><span>资料维护</span></a></li>
		<li id="other"><a href="<%=request.getContextPath()%>/index.do"><span>其他</span></a></li>
	</ul>
</div>