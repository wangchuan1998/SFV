<%@page import="com.sfv.entitybean.system.SystemUser;"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<div id="menu">
<%
	SystemUser user = (SystemUser)session.getAttribute("USERINFO");
	out.print("亲爱的 <font color=red><b>");
	if(user != null)out.print(user.getRealname());
	out.print("</b></font>用户，欢迎登录!");
	out.print("<input type='hidden' id='userid' value='" + user.getId() + "'/>");
	out.print("<input type='hidden' id='username' value='" + user.getRealname() + "'/>");
%>
</div>