function ajaxFileUpload(){
	if(checkType()){
	var formData = new FormData($("#orgLimitChannelDataImportInfoForm")[0]);
	$("#orgLimitChannelDataImportInfoForm").form('submit', {
	    url: 'orgLimitChannel/orgLimitChannelDataImport',
	    data: formData,
	    type:'post',
	    success: function (result) {
	    	$.messager.show({
				title : '提示',
				msg : '上传成功!'
			});
	    	$('#orgLimitChannelDataImport').window('close');	

	    }, error: function (data,status,e){
	    	$.messager.show({
				title : 'error',
				msg : e
			});
        }
	}); 
	ajaxFileUploadDown();
	closeWindow();
	}
}

function ajaxFileUploadDown(){
	
	var formData = new FormData($("#orgLimitChannelDataImportInfoForm")[0]);
	$("#orgLimitChannelDataImportInfoForm").form('submit', {
	    url: 'orgLimitChannel/orgLimitChannelDataImportDownLoadExcel',
	    data: formData,
	    type:'post',
	    success: function (result) {
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

function closeWindow(){
	$('#orgLimitChannelDataImport').window('close').window('refresh');
	$('#orgLimitChannelDataImportInfoForm').form('clear');
}

//判断传入的文件为excel文件
function checkType(){
	var filePath = $("#orgLimitChannelUploadfile").filebox('getValue');
	 if(filePath.length < 1)
	 {
		 $.messager.show({
				title : '提示',
				msg : '请选择要上传的文件!'
			});
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
		  $.messager.show({
				title : '提示',
				msg : '只支持excel文件的上传!'
			});
	   return false ;
	  }
	 }
	
}


