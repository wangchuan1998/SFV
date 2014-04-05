$(document).ready(function(){
	$("#photo").addClass("current");
	//提交用户评论
    $("#submit_btn").click(function(){
    	$.ajax({
			url: 'saveComment.do',
			data:$("#photoComment").serialize(),
			type: 'POST',
			dataType:'json',
			success:function(json){
				if(json.dataMap.retCode == "success")
				{
					$("#comment").val("");
					//加载用户评论
					loadcomment();
				}
				else
				{
					alert("发表评论失败");
				}
			},
			error:function(){
				alert("发表评论失败");
			}
		});
		
	});
	
	//创建相册弹出框
    $("#createAlbum").click(function(){
    	$.layer({
			type: 2,
			title: false,
			fix: false,
			closeBtn: false,
			shadeClose: true,
			shade: [0.1,'#fff', true],
			border : [5, 0.3, '#666', true],
			offset: ['100px',''],
			area: ['400px','200px'],
			iframe: {src: 'photo/createAlbum.jsp'},
			success: function(){
				
			}
		});


    });
});

