

function ajaxFileUpload(){
	if(checkType()){
	var formData = new FormData($("#channelBankDataImportInfoForm")[0]);
	/* $('#addBtn').linkbutton({disabled:true});*/
	/* var d= document.getElementById("addBtn");*/
	$("#channelBankDataImportInfoForm").form('submit', {
	    url: 'channelBank/channelBankDataImport',
	    data: formData,
	    type:'post',
	    success: function (result) {
	    	/* $('#addBtn').linkbutton({disabled:false});
	    	 $.messager.progress('close');*/
	    	$.messager.show({
				title : '提示',
				msg : '上传成功!'
			});
	    	$('#channelBankDataImport').window('close');	

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
	
	var formData = new FormData($("#channelBankDataImportInfoForm")[0]);
	/* $('#addBtn').linkbutton({disabled:true});*/
	/* var d= document.getElementById("addBtn");*/
	$("#channelBankDataImportInfoForm").form('submit', {
	    url: 'channelBank/channelBankDataImportDownLoadExcel',
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

function closeWindow(){
	$('#channelBankDataImport').window('close').window('refresh');
	$('#channelBankDataImportInfoForm').form('clear');
	
}

function showWindow(){
	$('#channelBankDataImport').window('open').window('refresh');
	$('#channelBankDataImportInfoForm').form('clear');
} 
//判断传入的文件为excel文件
function checkType(){
	var filePath = $("#channelBankDataLoadfile").filebox('getValue');
	/*alert(filePath);*/
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


