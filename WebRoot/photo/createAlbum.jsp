<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<title>阿尔萨斯在线</title>
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.2.js" type="text/javascript"></script>
	<link href="<%=request.getContextPath()%>/css/layout.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css" />
</head>
<style>     
	input[type=text], input[type=password], input[type=textarea] {
	        border-color: #bbb;        
	        height: 38px;        
	        font-size: 14px;        
	        border-radius: 2px;        
	        outline: 0;        
	        border: #ccc 1px solid;        
	        padding: 0 10px;        
	        width: 250px;        
	        -webkit-transition: box-shadow .5s;        
	        margin-top: 15px;    
	 }        
	 input[type=text]:hover,  input[type=text]:focus, input[type=password]:hover,  input[type=password]:focus {
	             border: 1px solid #56b4ef;            
	             box-shadow: inset 0 1px 3px rgba(0,0,0,.05),0 0 8px rgba(82,168,236,.6);            
	             -webkit-transition: box-shadow .5s;        
	 }    
	 input::-webkit-input-placeholder {        
	 color: #999;        
	 -webkit-transition: color .5s;    
	 }    
	 input:focus::-webkit-input-placeholder,  input:hover::-webkit-input-placeholder {        
	 color: #c2c2c2;        
	 -webkit-transition: color .5s;    
	 }
</style>
<body>
	<form id='submit_create_ablum'>
		<div class='info_form' style='text-align:left;margin-top:10px;'>
			<div class='entry'>
				<input type='text' placeholder="请输入相册名称" id='name' name='name' />
			</div>
			<div class='entry'>
				<input type='textarea' placeholder="相册描述" id='description' name='description' />
			</div>
			<div class="info_submit" id="submit_btn" style="float: right; margin-left: 115px; position: absolute;">提交</div>
		</div>
	</form>

</body>
</html>


<script type="text/javascript">
$("#submit_btn").click(function(){
	$.ajax({
		url: 'saveAlbum.do',
		data:$("#submit_create_ablum").serialize(),
		type: 'POST',
		dataType:'json',
		success:function(json){
			if(json.dataMap.retCode == "success")
			{
				alert("新建相册成功");
				parent.location.reload();
			}
			else
			{
				alert("新建相册失败");
			}
		},
		error:function(){
			alert("新建相册失败");
		}
	});
})
</script>