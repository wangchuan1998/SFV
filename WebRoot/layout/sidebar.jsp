<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<link href="<%=request.getContextPath()%>/css/sidebar.css" rel="stylesheet" type="text/css" />
<div id="side">
	<div class="side">
		<div id="panel_Profile" class="panel">
	    	<ul class="panel_head"><span>个人资料</span></ul>
	    	<ul class="panel_body profile">
		        <div id="blog_userface">
		            <a href="http://my.csdn.net/zhengdjin" target="_blank">
		            	<%
		            		if(user.getHeadUrl() ==null || user.getHeadUrl().trim().equals(""))
		            		{
		            			if(user.getSex().trim().equals("f"))
		            			{
		            	%>
		            			<img src="<%=request.getContextPath()%>/images/default_f_l.jpg" title="" style="width:140px;height:158px;"/>
		            	<%
		            			}
		            			else
		            			{
		            	%>
		            			<img src="<%=request.getContextPath()%>/images/default_m_l.jpg" title="" style="width:140px;height:158px;"/>
		            	<%
		            			}
		            		} 
		            		else
		            		{
		            	%>
		            		<img src="<%=request.getContextPath() + user.getHeadUrl()%>" title="" style="width:140px;height:158px;"/>
		            	<%
		            		}
		            	%>
		            	
		            </a>
		        </div>
		        <div id="bms_box" style="color:#FF6C1E;"><b><%=(user.getRealname())%></b></div>
				<div id="bms_box">性别：<%=(user.getSex()=="f" ? "女生" : "男生")%></div>
				<div id="bms_box">等级：<%=user.getGrade()%>级</div>
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