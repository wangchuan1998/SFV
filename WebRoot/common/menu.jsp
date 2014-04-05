<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<div id="templatemo_menu_wrapper">
    <div id="templatemo_menu">
        <ul>
            <%@page import="com.sfv.entitybean.system.SystemUser;"%>
            <%
				SystemUser user = (SystemUser)session.getAttribute("USERINFO");
				if(user != null)
					out.print("<span><a href='index.do'>"+user.getRealname()+"</a></span>");
				else
					out.print("<span id='login_span'><a id='login_btn'>登录</a></span>");
			%>
			<span id='login_info'>
				<!-- Login box -->
				<div class="login_box" id="login_box" style="display:none;">
					<div class="login_tm_box"></div>
					<div class="login_form_box">
						<form id="login_form">
							<h1>用户登录</h1>
							<span>账&nbsp; 号：<input class="login_input" type="text" name="email" id="email" value="duanbaiqiang@huawei.com"></span>
							<span style="margin-bottom:5px;">密&nbsp; 码：<input class="login_input" type="password" name="password" id="password" value="admin@123"></span>
							<div class="login_message" id="error_message">用户名不存在或密码错误</div>
							<div class="login_submit" id="login_submit">登录</div>
							<div class="login_loading" id="login_loading">登录中...</div>
							<div class="login_regedit" id="login_regedit">注册</div>
						</form>
						<div class="bottom"><img id='login_box_close' src="images/z_login_close_btn.png" style="width:100%;height:12px;"></div>
					</div>
				</div>
				<!-- Login box end -->
			</span>
        </ul>   
	</div> <!-- end of menu -->

</div> <!-- end of menu wrapper -->



<script src="<%=request.getContextPath()%>/js/login.js" type="text/javascript"></script>