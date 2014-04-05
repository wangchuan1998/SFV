<%@page import="com.sfv.entitybean.system.SystemUser;"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<div id="menu">
<%
	//当前登录用户
	SystemUser currentUser = (SystemUser)session.getAttribute("USERINFO");
	out.print("亲爱的 <font color=red><b>");
	if(currentUser != null)out.print(currentUser.getRealname());
	out.print("</b></font>用户，欢迎登录!");
	out.print("<input type='hidden' id='userid' value='" + currentUser.getId() + "'/>");
	out.print("<input type='hidden' id='username' value='" + currentUser.getRealname() + "'/>");

%>
</div>