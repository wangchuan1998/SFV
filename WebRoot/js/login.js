$(document).ready(function(){

    $("#login_box_close").click(function(){
    	$("#login_box").hide();
    });
    
 	$("#login_btn").click(function(){
    	$("#login_box").show();
    });
    
    $("#password").keydown(function(e){
    	if(e.keyCode == 13){
    		$("#login_submit").click();
    	}
    });
    $("#email").mouseover(function(e){
    	$("#email").focus();
    });
    $("#password").mouseover(function(e){
    	$("#password").focus();
    });
	$("#login_submit").click(function(){
		if($.trim($("#email").val()) == '')
		{
			$("#error_message").html("用户名不能为空").show();
			return;
		}
		if($.trim($("#password").val()) == '')
		{
			$("#error_message").html("密码不能为空").show();
			return;
		}
		$("#login_submit").hide();
		$("#login_loading").show();
	    $.ajax({
			url: 'login.do',
			data:$('#login_form').serialize(),
			type: 'POST',
			success:function(json){
				var data = $.parseJSON(json);
				if(data['retCode'] == '006')
				{
					$("#error_message").show().html("用户名不存在或密码错误");
				}
				else
				{
	    			$("#login_box").hide();
					$("#login_span").hide();
					$("#login_info").html("<a href='index.do'>"+data['realname']+"</a>").show();
				}
			},
			error:function(){
				
			}
		});
    
    });
    
	$("#login_regedit").click(function(){
		window.location = "user/register.jsp";
    
    });
 }); 