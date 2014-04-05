<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>阿尔萨斯在线</title>
<link href="../css/regedit.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.7.2.js" type="text/javascript"></script>
</head>
<body>
<div id="mainContent">
  	<div class="tit">
  		<b>账号注册</b>
  	</div>
  	<div class="reg_form">
  		<form id="reg_box">
  			<div class="entry">
				<label for="email">邮箱：</label>
				<input type="text" class="form_input" id="email" name="email" value="" />
				<span class="msg"></span>
			</div>
  			<div class="entry">
				<label for="username">昵称：</label>
				<input type="text" class="form_input" id="username" name="username" value="" />
				<span class="msg">2-15个汉字或4-30个字符</span>
			</div>
  			<div class="entry">
				<label for="username">性别：</label>
				<input type="radio" id="sex" name="sex" value="m" checked/>男生
				<input type="radio" id="sex" name="sex" value="f" />女生
				<span class="msg">性别选择后将不能再次修改，请认真填写</span>
			</div>
  			<div class="entry">
				<label for="pswd">密码：</label>
				<input type="password" class="form_input" id="password" name="password" value="" />
				<span class="msg">6-16位数字、字母和符号，区分大小写</span>
				<div class="strength">
					密码强度：弱<span class="pswd_state pswd_state_def">
					<span class="level level_0"></span><span class="level level_0_1"></span>
					<span class="level level_0_2"></span></span><span class="pswd_result">强</span>
				</div>
			</div>
  			<div class="entry">
				<label for="re_pswd">确认密码：</label>
				<input type="password" class="form_input" id="re_password" name="re_password" value="" />
				<span class="msg"></span>
			</div>
			<div class="entry regbtn">
				<a href="#" onclick="register.register_submit();">注册</a>
			</div>
        </form>
  	</div>
	<div class="have_count">
		<h1>已经有账号？</h1>
		<p><a href="../index.jsp">立即登陆</a></p>
	</div>
</div>
</body>
</html>

<script src="../js/register_v.js" type="text/javascript"></script>
<script type="text/javascript">
	register.init();
</script>


