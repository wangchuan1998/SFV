$(document).ready(function(){
	$("#photo").addClass("current");
	$('#fileInput').uploadify({   
        'uploader': 'include/uploadify.swf',
        'script': 'uploadPhoto;jsessionid='+jsessionid,   //指定服务端处理类的入口
        'folder': 'temp',   
        'cancelImg': 'include/cancel.png',
        'fileDataName': 'fileInput', //和input的name属性值保持一致就好，Struts2就能处理了   
        'queueID': 'fileQueue',   
        'auto': true,//是否选取文件后自动上传   
        'multi': true,//是否支持多文件上传
        'simUploadLimit' : 5,//每次最大上传文件数   
        'buttonText': 'Browse',//按钮上的文字 
        'width':70,
        'height':30,
        'displayData': 'percentage',//有speed和percentage两种，一个显示速度，一个显示完成百分比    
        'fileExt': '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//允许的格式 
        'onComplete': function (response){
			
          }  
    });
});