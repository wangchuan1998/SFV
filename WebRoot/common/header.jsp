<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<title>阿尔萨斯在线</title>
<meta name="keywords" content="Pro Studio, black and white edition, web design, free web template" />
<meta name="description" content="Pro Studio - black and white edition, free web template provided by templatemo.com" />
<link href="<%=request.getContextPath()%>/css/templatemo_style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/head.css" rel="stylesheet" type="text/css" />
<%@ taglib prefix="s" uri="/struts-tags" %>
</head>
<body>
<div class="head">
	<div class="banner">
		<ul class="logout">
			<s:if test="null==#session.USERINFO||#session.USERINFO.isEmpty()">  
				<li> 
					<a href="user/register.jsp">注册</a>
				</li>
			</s:if>  
			<s:else>
				<li> 
					<a href="index.do">${USERINFO.realname }</a>
					<i>|</i>
				</li>
				<li>
					<a href="logout.do">注销</a>
				</li>
			</s:else>  
		</ul>
	</div>
</div>
</body>
</html>