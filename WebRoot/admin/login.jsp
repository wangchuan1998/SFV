<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>后台内容管理系统</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="viewport" content="width=device-width">
<link href="public/css/base.css" rel="stylesheet" type="text/css">
<link href="public/css/login.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="login">
<form method="post" action="adminLogin.do" id="form">
	<div class="logo"></div>
    <div class="login_form">
    	<div class="user">
        	<input class="text_value" value="" name="email" type="text" id="email">
            <input class="text_value" value="" name="password" type="password" id="password">
        </div>
        <button class="button" id="submit" type="submit">登录</button>
    </div>
    
    <div id="tip"></div>
    <div class="foot">
	
    </div>
    </form>
</div>
</body>
</html>