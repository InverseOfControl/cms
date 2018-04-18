function ajaxFileUpload(){
	if(checkType()){
	var formData = new FormData($("#addBMSBaseAreaInfoForm")[0]);
	 $('#addBtn').linkbutton({disabled:true});
	/* var d= document.getElementById("addBtn");*/
	$("#addBMSBaseAreaInfoForm").form('submit', {
	    url: 'baseArea/add.do',
	    data: formData,
	    type:'post',
	    success: function (result) {
	    	/* $('#addBtn').linkbutton({disabled:false});
	    	 $.messager.progress('close');*/
	    
	    		$.messager.show({
					title : '提示',
					msg : '上传成功!'
				});
	    		$('#dd').window('close');
	    }, error: function (data,status,e){
	    	$.messager.show({
				title : 'error',
				msg : e
			});
        }
	}); 
	}
	
}
//判断传入的文件为txt文件
function checkType(){
	var filePath = $("#uploadfile").filebox('getValue');
	/*alert(filePath);*/
	 if(filePath.length < 1)
	 {
	  $.messager.alert("操作提示", "请选择要上传的文件！");
	  return false ;
	 }
	 else
	 {
	  var fileLx = filePath.toString().substring(filePath.toString().lastIndexOf(".")+1) ;
	  if(fileLx == "txt")
	  {
	   return true ;
	  }
	  else
	  {
	  $.messager.alert("操作提示", "只支持txt文件的上传！");
	   return false ;
	  }
	 }
	
}


