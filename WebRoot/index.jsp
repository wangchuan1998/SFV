<!doctype html>  
<head>
<meta charset="UTF-8">
<title>Think simple - Home</title>
<link rel="icon" href="images/favicon.gif" type="image/x-icon"/>
 <!--[if lt IE 9]>
 <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->

<link rel="shortcut icon" href="images/favicon.gif" type="image/x-icon"/> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css"/>
<link type="text/css" href="<%=request.getContextPath()%>/css/css3.css" rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.js"></script>
<link href="<%=request.getContextPath()%>/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.pikachoose.js"></script>
<script src="<%=request.getContextPath()%>/js/login.js" type="text/javascript"></script>
<script language="javascript">
	$(document).ready(
		function (){
			$("#pikame").PikaChoose();
		});
</script>
		


</head>
<body>

<!--start container-->
<div id="container">

<!--start header-->

<header>

<!--start logo-->
<a href="#" id="logo"><img src="images/logo.png" width="221" height="100" alt="logo"/></a>    

<!--end logo-->

<!--start menu-->

<nav>
   <ul id="indexMenu">
   <li><a href="#" class="current">Home</a></li>
   <li><a href="#">About us</a></li>
<li><a href="#">Services</a></li>
<li><a href="#">Portfolio</a></li>
   <li><a href="#">News</a></li>
   <li id='login_info'>
   	<a href="#" id='login_btn'>登录</a>
   </li>
   
   <!-- Login box -->
   <span>
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
</span>
<!-- Login box end -->


</ul>
</nav>
<!--end menu-->


<!--end header-->
</header>


  <!--start intro-->

<div id="intro">

<div class="group_bannner_right">
<img src="images/picture.png" width="550" height="316"  alt="baner">
</div>

<header class="group_bannner_left">
<hgroup>
<h1>Simple.Think. </h1>
<h2>“The little things are infinitely the most important.“ 
</h2>
</hgroup>
</header>
</div>
<!--end intro-->



<!--start holder-->

  <div class="holder_content">
  
  <section class="group1">
  <h3>About us</h3>
  	<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec molestie. Sed aliquam sem ut arcu. Phasellus sollicitudin. 
Vestibulum condimentum  facilisis nulla. In hac habitasse platea dictumst. Nulla nonummy. Cras quis libero.</p>
   <a href=""><span class="read_more">Read more...</span></a>

</section>


    
  <section class="group2">
  <h3>Services</h3>
  	<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec molestie. Sed aliquam sem ut arcu. Phasellus sollicitudin. 
Vestibulum condimentum  facilisis nulla. In hac habitasse platea dictumst. Nulla nonummy. Cras quis libero.</p>
<a href=""><span class="read_more">Read more ...</span></a>

</section>


      
  <section class="group3">
  <h3>News</h3>
  	<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec molestie. Sed aliquam sem ut arcu. Phasellus sollicitudin. 
Vestibulum condimentum  facilisis nulla. In hac habitasse platea dictumst. Nulla nonummy. Cras quis libero.</p>
<a href=""><span class="read_more">Read more...</span></a>

</section>

 
  </div>
  <!--end holder-->

<!--start holder-->

  <div class="holder_content1">
   <section class="group4">
  <h3>Gallery</h3>
  	<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec molestie. Sed aliquam sem ut arcu. Phasellus sollicitudin. 
Vestibulum condimentum  facilisis nulla. In hac habitasse platea dictumst. Nulla nonummy. Cras quis libero.</p>
   <div class="pikachoose">
<ul id="pikame" >
	<li><a href="#"><img src="images/picture3.jpg" width="500" height="250"  alt="picture"/></a><span>“Simplicity is the nature of great souls.”</span></li>
	<li><a href="#"><img src="images/picture2.jpg" width="500" height="250" alt="picture"/></a><span>“The little things are infinitely the most important. “ </span></li>
	<li><a href="#"><img src="images/picture1.jpg" width="500" height="250" alt="picture"/></a><span>“Simplicity is the essence of happiness.”</span></li>
   </ul>
      </div>

</section>


      
  <section class="group5">
  <h3>Testimonials</h3>
  	<p><span class="purple">1)</span>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec molestie. Sed aliquam sem ut arcu. Phasellus sollicitudin. 
Vestibulum condimentum  facilisis nulla. In hac habitasse platea dictumst. Nulla nonummy. Cras quis libero.</p>
     	<p><span class="purple">2)</span>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec molestie. Sed aliquam sem ut arcu. Phasellus sollicitudin. 
Vestibulum condimentum  facilisis nulla. In hac habitasse platea dictumst. Nulla nonummy. Cras quis libero.</p>
  	<p><span class="purple">3)</span>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec molestie. Sed aliquam sem ut arcu. Phasellus sollicitudin. 
Vestibulum condimentum  facilisis nulla. In hac habitasse platea dictumst. Nulla nonummy. Cras quis libero.</p>


</section>

  
   </div>
   <!--end holder-->



</div>
<!--end container-->

<!--start footer-->
<footer>
<div class="container">  
<div id="FooterTwo"> © 2011 Think simple </div>
<div id="FooterTree"> Valid html5, design and code by <a href="http://www.marijazaric.com">marija zaric - creative simplicity</a>   </div> 
</div>
</footer>
   <!--end footer-->
<!-- Free template distributed by http://freehtml5templates.com -->   
 </body></html>
