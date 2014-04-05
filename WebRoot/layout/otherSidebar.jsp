<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<link href="<%=request.getContextPath()%>/css/sidebar.css" rel="stylesheet" type="text/css" />
<div id="side">
	<div class="side">
		<div id="panel_Profile" class="panel">
	    	<ul class="panel_head"><span>个人资料</span></ul>
	    	<ul class="panel_body profile">
		        <div id="blog_userface">
		            <a href="http://my.csdn.net/zhengdjin" target="_blank">
		            	<img src="<%=request.getContextPath()%>${user.headUrl }" title="" style="width:140px;height:158px;"/>
		            </a>
		        </div>
		        <div id="bms_box" style="color:#FF6C1E;"><b></b></div>
				<div id="bms_box">性别：${user.sex=='f' ? '女生' : '男生'}</div>
				<div id="bms_box">等级：${user.grade}级</div>
				<form id="addFriendForm">
					<s:if test="friend">
						
					</s:if>
					<s:else>
						<div class="info_submit" id="addFriend" style="margin-top: 10px;width: 150px;">
							加为好友
							<input type="hidden" name="refUserId" value="${user.id }"/>
						</div>
					</s:else>
				</form>
			  	<div class="fix"></div>
	    	</ul>
		</div>
		
		<div id="panel_Profile" class="panel">
	    	<ul class="panel_head"><span>最近来访</span></ul>
	    	<ul class="panel_body profile">
				<div id="bms_box"></div>
		        <div id="bms_box"></div>
		        <div id="bms_box"></div>
	    	</ul>
		</div>
		
		
		<div id="panel_Profile" class="panel">
	    	<ul class="panel_head"><span>预留模块</span></ul>
	    	<ul class="panel_body profile">
				<div id="bms_box"></div>
		        <div id="bms_box"></div>
		        <div id="bms_box"></div>
	    	</ul>
		</div>
		
		
		
	</div>
	<div id="clear"></div>
</div>