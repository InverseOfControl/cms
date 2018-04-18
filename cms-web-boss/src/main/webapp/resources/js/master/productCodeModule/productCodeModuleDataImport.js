function ajaxFileUpload(){
	if(checkType()){
	var formData = new FormData($("#productCodeModuleDataImportInfoForm")[0]);
	/* $('#addBtn').linkbutton({disabled:true});*/
	/* var d= document.getElementById("addBtn");*/
	$("#productCodeModuleDataImportInfoForm").form('submit', {
	    url: 'productCodeModule/productCodeModuleDataImport',
	    data: formData,
	    type:'post',
	    success: function (result) {
	    	/* $('#addBtn').linkbutton({disabled:false});
	    	 $.messager.progress('close');*/
	    	$.messager.show({
				title : '提示',
				msg : '上传成功!'
			});
	    	$('#productCodeModuleDataImport').window('close');	

	    }, error: function (data,status,e){
	    	$.messager.show({
				title : 'error',
				msg : e
			});
        }
	}); 
	}
}

function ajaxFileUploadDown(){
	if(checkType()){
	var formData = new FormData($("#productCodeModuleDataImportInfoForm")[0]);
	/* $('#addBtn').linkbutton({disabled:true});*/
	/* var d= document.getElementById("addBtn");*/
	$("#productCodeModuleDataImportInfoForm").form('submit', {
	    url: 'productCodeModule/productCodeModuleDataImportDownLoadExcel',
	    data: formData,
	    type:'post',
	    success: function (result) {
	    	/* $('#addBtn').linkbutton({disabled:false});
	    	 $.messager.progress('close');*/
	    	$.messager.show({
				title : '提示',
				msg : '上传成功!'
			});
	    	

	    }, error: function (data,status,e){
	    	$.messager.show({
				title : 'error',
				msg : e
			});
        }
	}); 
	}
}

function closeWindow(){
	$('#productCodeModuleDataImport').window('close').window('refresh');	
	$('#productCodeModuleDataImportInfoForm').form('clear');
}

//判断传入的文件是否为excel文件
function checkType(){
	var filePath = $("#productCodeModuleUploadfile").filebox('getValue');
	/*alert(filePath);*/
	 if(filePath.length < 1)
	 {
		 $.messager.alert("操作提示", "请选择要上传的文件！");
	  return false ;
	 }
	 else
	 {
	  var fileLx = filePath.toString().substring(filePath.toString().lastIndexOf(".")+1) ;
	  if(fileLx == "xls"||fileLx == "xlsx")
	  {
	   return true ;
	  }
	  else
	  {
		  $.messager.alert("操作提示", "只支持excel文件的上传！");
	   return false ;
	  }
	 }
	
}


