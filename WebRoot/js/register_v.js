function trim(s){ 
	var _s = s.replace( /^(\s*|　*)/, ""); 
	return _s.replace( /(\s*|　*)$/, "");
} 
var register = {
	init:function(){
		var email = $("#email");
		var username = $("#username");
		var password = $("#password");
		var re_password = $("#re_password");
		
		email.focus();
		email.blur(this.chk_mail);
		username.blur(this.chk_username);
		password.blur(this.chk_password);
		password.keyup(this.keyup_password);
		re_password.keyup(this.keyup_re_password);
	},
	chk_mail: function(){
		var email = $("#email");
		var re = /^([a-zA-Z0-9]+[\_]*)+[\_]*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,4}$/;

		email.next('span').innerHTML = "";
		email.removeClass('form_input_error');
		var msg = 'init';
		if(trim(email.val()) ==''){
			msg = '邮箱不能为空';
		}else if(!re.test(email.val())){
			msg = '请输入正确邮箱地址';
		}else if(email.val().length>50){
			msg = '邮箱名太长';
		}else if(email.val().length<6){
			msg = '邮箱名太短';
		}
		if(msg != 'init'){
			email.next('span').addClass('error').html(msg);
			email.addClass('form_input_error');
			register.email = false;
			return false;
		}else{
			email.next('span').addClass('pass').html("");
			register.email = true;
			return true;
		}
	},
	chk_username: function(){
		var username = $('#username');
		username.next('span').innerHTML = "";
		username.removeClass('form_input_error');
		var msg = 'init';
		var sum = 0; 

		for(var i=0;i<username.val().length;i++){
		   var c = username.val().charCodeAt(i);
		   if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) { 
				sum++;
		   }else{
				sum+=2;}

			var str = username.val().charAt(i).charCodeAt(0);
			if(str==32){
				msg = '只能使用中文、字母、数字、下划线或减号';
			}else if( ((str>=48 && str<=57) || 
					(str>=65 && str<=90) || 
					(str>=97 && str<=122)||
					 str==45 || str==95 || //包含_ -
					(str>19543 && str<41652) //41377-63486:GB2312
				)==false ){
				msg = '只能使用中文、字母、数字、下划线或减号';
			}
		}
		
		if(trim(username.val())=='') {
			msg = '请输入昵称';
		}else if(sum>30){
			msg = '昵称不能超过15个汉字或30个字符';
		}else if(sum<4){
			msg = '昵称至少要有2个汉字或4个字符';
		}else if(isNaN(username.val())==false && username.val()>0){
			msg = '昵称不能全部使用数字';
		}
		
		if(msg != 'init'){
			username.next('span').addClass('error').html(msg);
			username.addClass('form_input_error');
			register.username = false;
			return false;
		}else{
			username.next('span').addClass('pass').html("");
			register.username = true;
			return true;
		}
	},
	chk_password: function(){
		var password = $("#password");
		var re_password = $("#re_password");
		password.next('span').removeClass('error').html('');
		var chars = register.chars(password.val());
		var msg = 'init';
		
		if(password.val()==''){
			msg = '请输入密码';
		}else if(password.val().length>16){
			msg = '密码不能超过16位 ';
		}else if(password.val().length<6){
			msg = '密码至少要有6位';
		}else if(re_password.val()!='' && password.val()!='' && re_password.val()!=password.val()){//两次密码输入不正确
			msg = '两遍输入的密码不一致';	
		}
		if(msg!='init'){
			password.next('span').addClass('error').html(msg);
			password.addClass('form_input_error');
			re_password.next().removeClass('pass').removeClass('error').html('');
			register.password = false;
			return false;
		}else{
			password.next('span').addClass('pass').html("");
			register.password = true;
			return true;;
		}
	},
	keyup_password: function(){
		var password = $("#password");
		password.next('span').removeClass('error').html('');
		var chars = register.chars(password.val());
		
		if(password.val().length>0 && password.val().length<6){
			password.next().next().html('密码强度：弱<span class="pswd_state pswd_state_def"><span class="level level_0"></span><span class="level level_0_1"></span><span class="level level_0_2"></span></span><span class="pswd_result">强</span></div>');
		}else if( 9>=password.val().length && password.val().length>=6 && chars==1 ){
			password.next().next().html("密码强度：<span class='pswd_state'><span class='level level_1'></span></span><span class='pswd_result'>弱");
			return false;
		}else if( (9>=password.val().length && password.val().length>=6 && chars==2) || (16>=password.val().length && password.val().length>=10 && chars==1) ){
			password.next().next().html("密码强度：<span class='pswd_state'><span class='level level_1'></span><span class='level level_2'></span></span><span class='pswd_result'>中");
			return false;
		}else if( (9>=password.val().length && password.val().length>=6 && chars==3) || (16>=password.val().length && password.val().length>=10 && chars==2) ){ 
			password.next().next().html("密码强度：<span class='pswd_state'><span class='level level_1'></span><span class='level level_2'></span><span class='level level_3'></span></span><span class='pswd_result'>强");
			return;
		}else if(16<password.val().length){
			password.next('span').addClass('error').html("<span class='ico__error'></span>" + '密码不能超过16位');
			password.addClass('form_input_error');
			return false;
		}
		return true;
	},
	keyup_re_password: function(){
		var password = $("#password");
		var re_password = $("#re_password");
		re_password.next('span').removeClass('error');
		re_password.removeClass('form_input_error');
		re_password.next('.msg').innertHTML = '';
		var msg = 'init';

		if( re_password.val() =='' ){
			msg = '请再输入一遍密码';
		}else if(re_password.val().length<6){
			msg = '密码至少要有6位';
		}else if( re_password.val()!=password.val() && password.val()!='' ){//两次密码输入不正确
			msg = "两遍输入的密码不一致";
			password.next('span').addClass('error').html("<span class='ico__error'></span>" + msg);
			password.addClass('form_input_error');
			register.re_password = false;
			return false;
		}else if(re_password.val()!=password.val() && password.val()=='' ){//只输入如B
			password.next('span').removeClass("error").html('');		
		}else if(re_password.val()==password.val() && re_password.val().length>5 && re_password.val().length<17){//两次密码正确
			re_password.next('span').addClass('pass').html("<span class='ico__pass'></span>");
			register.re_password = true;
			return true;
		}
		if(msg!='init'){
			password.next('span').removeClass('pass');
			re_password.next('span').addClass('error').html("<span class='ico__error'></span>" + msg);
			re_password.addClass('form_input_error');
			register.re_password = false;
			return false;
		}
	},
	//返回组成类型：1种类型字符 2种类型字符 3种类型字符
	chars: function(str){
        var a=0,b=0,c=0;
		for(var i=0;i<str.length;i++){
			if(str.charAt(i).charCodeAt(0)>=48 && str.charAt(i).charCodeAt(0) <=57){//数字
				a = 1;
			}else if( (str.charAt(i).charCodeAt(0)>=65 && str.charAt(i).charCodeAt(0)<=90) || (str.charAt(i).charCodeAt(0)>=97 && str.charAt(i).charCodeAt(0)<=122) ){//字母
				b = 1;
			}else{//符号
				c = 1;
			}
		}
		return (a+b+c);
	},
	//提交注册信息
	register_submit: function(){
		if(register.email && register.username && register.password && register.re_password){
			$.ajax({
				url: 'saveUser.do',
				data:$('#reg_box').serialize(),
				type: 'POST',
				success:function(json){
					window.location = "mypage!index.action";
				},
				error:function(){
				
				}
			});
		}
	}
	
}