$(function() {
	initDatagrid1();
});

function initDatagrid1() {
		$("#new_datagrid_contract").datagrid({
			onLoadSuccess:function(data){ 
				  if(data.total==0)
				  {
				    $.messager.show({
		                title:'结果',
		                msg:'没查到符合条件的数据！',
		                showType:'slide',
		            });
				  };
		     },
			url : 'lineNumberSet/listPage',
			striped : true,
			singleSelect : false,
			rownumbers : true,
			pagination : true,
			fitColumns : false,
			pageList : [ 10, 20, 50 ],
			scrollbarSize : 0,
			columns : [ [{
				field : 'id',
				title : 'id',
				checkbox : true,
				width : 30,
			}, 
		  {
				field : 'contractNum',
				title : '合同编号',
				width : 200,
			}, {
				field : 'channelName',
				title : '渠道名称',
				width : 200,
				formatter : function(value, rowData, rowIndex) {
					if (value == '00014') {
						return "外贸信托";			
					} else{
						return "";
					}
				}
			},
			{
				field : 'borrowerName',
				title : '客户姓名',
				width : 120,
			},
			{
				field : 'idNum',
				title : '身份证号码',
				width : 200,
			},
			{
				field : 'account',
				title : '银行账号',
				width : 220,
			},
			{
				field : 'bank',
				title : '银行名称',
				width : 220,
			},
			{
				field : 'bankFullName',
				title : '支行名称',
				width : 220,
			},
			{
				field : 'lineDontDo',
				title : '行别',
				width : 100,
			},
			{
				field : 'lineNumber',
				title : '行号',
				width : 100,
			}
			] ]
		});
}

function queryContractInfo() {
	var borrowerName=$('#borrowerName').val();
	var idNum=$('#idNum').val();
	var cobtractNum=$('#cobtractNum').val();
	var queryAreaType=$('#queryAreaType').combobox("getValue");
	
	//查询
	var queryParams = $('#new_datagrid_contract').datagrid('options').queryParams;
	queryParams.borrowerName = borrowerName;
	queryParams.idNum = idNum;
	queryParams.cobtractNum = cobtractNum;
	queryParams.queryAreaType = queryAreaType;
	setFirstPage("#new_datagrid_contract");
	$('#new_datagrid_contract').datagrid('options').queryParams = queryParams;
	$("#new_datagrid_contract").datagrid('reload');
}
function setFirstPage(ids){
    var opts = $(ids).datagrid('options');
    var pager = $(ids).datagrid('getPager');
    opts.pageNumber = 1;
    opts.pageSize = opts.pageSize;
    pager.pagination('refresh',{
	pageNumber:1,
	pageSize:opts.pageSize
    });
}

function setClear(){
	$('#areaType').combobox('clear');
}

//配置行别行号
function setNumberLine(){
	var areaType=$('#areaType').combobox('getValue');
	if(areaType==null||areaType==''){
		$.messager.show({
			title : '提示',
			msg : '请选择银行卡所属地区'
		});
	    return;
	}
	var rows=$('#new_datagrid_contract').datagrid('getChecked');
	if(rows.length==0){
		$.messager.show({
			title : '提示',
			msg : '请选择至少一条数据'
		});
	    return;
	}
	var ids="";
	for(var a=0;a<rows.length;a++){
		ids=ids+rows[a].id+',';
	}
	$.ajax({
		   url : 'lineNumberSet/updateLineNumber',
		   data : {
			   			'areaType' : areaType,
			   			'bankIds' : ids,
			    },
		   type:"POST",
		   async:false,  // 设置同步方式
	       cache:false,
		   success : function(result){
			   //$.messager.progress('close');
//			   if(result.isFlag){
//				   $.messager.show({
//						title : '提示',
//						msg : '该条记录已存在！'
//					});
//				   return;
//			   }		
			   debugger;
			   if(result.isSuccess==0){
		   			$.messager.show({
						title : '提示',
						msg : result.returnMsg
					});
		   			return;
		   		}
		   		if(result.isSuccess==1){
		   			initDatagrid1();
		   			$.messager.show({
						title : '提示',
						msg : '保存成功！'
					});
		   			
		   		}else{
		   			parent.$.messager.show({
						title: 'Error',
						msg: result
					});
		   		}
		   },
		   error:function(data){
		 		 $.messager.show({
						title: 'warning',
						msg: data.responseText
					});
		   }
	});
}


function showWindow(){
	$('#productDataImport').window('open').window('refresh');
	$('#productDataImportInfoForm').form('clear');
} 



function ajaxFileUpload(){
	var importExcelAreaType=$('#importExcelAreaType').combobox('getValue');
	if(importExcelAreaType==null||importExcelAreaType==''){
		$.messager.show({
			title : '提示',
			msg : '请选择文件类型！'
		});
		return;
	}
	if(checkType()){
	var formData = new FormData($("#productDataImportInfoForm")[0]);
	/* $('#addBtn').linkbutton({disabled:true});*/
	/* var d= document.getElementById("addBtn");*/
	$("#productDataImportInfoForm").form('submit', {
	    url: 'lineNumberSet/LineNumberUploadFile?importExcelAreaType='+importExcelAreaType,
	    data: formData,
	    type:'post',
	    success: function (result) {
	    	/* $('#addBtn').linkbutton({disabled:false});
	    	 $.messager.progress('close');*/
	    	if(result.nullFile){
	    		$.messager.show({
					title : '提示',
					msg : '请上传EXCEL!'
				});
	    		return;
	    	}
	    	if(result.isSuccess){
	    		$.messager.show({
					title : '提示',
					msg : '上传成功!!!'
				});
	    	}
	    	$('#productDataImport').window('close');	

	    }, error: function (data,status,e){
	    	$.messager.show({
				title : 'error',
				msg : e
			});
        }
	}); 
	//ajaxFileUploadDown();
	closeWindow();
	}
}


//判断传入的文件为excel文件
function checkType(){
	var filePath = $("#productUploadfile").filebox('getValue');
	 //alert(filePath);
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

//function ajaxFileUploadDown(){
//	
//	var formData = new FormData($("#productDataImportInfoForm")[0]);
//	 /*$('#addBtn').linkbutton({disabled:true});*/
//	/* var d= document.getElementById("addBtn");*/
//	$("#productDataImportInfoForm").form('submit', {
//	    url: 'product/productDataImportDownLoadExcel',
//	    data: formData,
//	    type:'post',
//	    success: function (result) {
//	    	/* $('#addBtn').linkbutton({disabled:false});
//	    	 $.messager.progress('close');*/
//	    	$.messager.show({
//				title : '提示',
//				msg : '上传成功!'
//			});
//	    	
//
//	    }, error: function (data,status,e){
//	    	$.messager.show({
//				title : 'error',
//				msg : e
//			});
//        }
//	}); 
//	
//}

function closeWindow(){
	$('#productDataImport').window('close').window('refresh');	
	$('#productDataImportInfoForm').form('clear');
}






