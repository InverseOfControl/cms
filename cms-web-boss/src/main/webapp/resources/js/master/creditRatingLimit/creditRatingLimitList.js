$(function() {
	//新增页面默认关闭
	initDatagrid1();
	$("#addCreditRatingLimitInfo").window({inline:true});
	$("#updateCreditRatingLimitInfo").window({inline:true});
});

function initDatagrid1() {
		$("#new_datagrid_creditRatingLimit").datagrid({
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
			url : 'creditRatingLimit/listPage',
			striped : true,
			singleSelect : true,
			rownumbers : true,
			pagination : true,
			fitColumns : false,
			//pageList : [ 10, 20, 50 ],
			scrollbarSize : 0,
			columns : [ [{
				field : 'id',
				title : 'id',
				//checkbox : true,
				width : 30,
				hidden:true
			}, 
		  {
				field : 'grade',
				title : '征信等级',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if (value == 1) {
						return "白户/无综合信用";			
					} else if(value == 2){
						return "当前逾期造成信用不良";
					}else{
						return "非当前逾期造成信用不良";
					}
				}
			}, {
				field : 'customerType',
				title : '申请类型',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if (value == 'NEW') {
						return "NEW";			
					} else if(value == 'RELOAN'){
						return "RELOAN";			
					}else if(value == 'TOPUP'){
						return "TOPUP";
					}else{
						return "";
					}
				}
			},
			{
				field : 'productName',
				title : '可选产品',
				width : 220,
			},
			{
				field : 'productType',
				title : '可选产品CODE',
				width : 220,
				hidden:true
			},
			{
				field : 'creditType',
				title : '贷记类型',
				width : 200,
				formatter : function(value, rowData, rowIndex) {
					if (value == '1') {
						return "贷款";			
					} else if(value == '2'){
						return "贷记卡";			
					}else{
						return "";			
					}
				}
			},
			{
				field : 'remark',
				title : '提示语配置',
				width : 250,
			}
			,{
				field : 'operation',
				title : '操作',
				formatter : formatOperations1,
				width : 250
			}
			] ]
		});
}

function queryCreditRatingLimitInfo() {
	var creditRating = $('#creditRating').combobox('getValue');
	//查询
	var queryParams = $('#new_datagrid_creditRatingLimit').datagrid('options').queryParams;
	queryParams.creditRating = creditRating;
	setFirstPage("#new_datagrid_creditRatingLimit");
	$('#new_datagrid_creditRatingLimit').datagrid('options').queryParams = queryParams;
	$("#new_datagrid_creditRatingLimit").datagrid('reload');
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

//弹出新增窗口
function addCreditRatingLimitInfoWindow() {
	$('#addCreditRatingLimitInfo').window({
		modal:true,
		closed:false,
	});
	$('#addCreditRatingLimitInfo').form('clear');
	$('#addCustomerType').combobox('enable', true);
	$('#addProduct').combobox('enable', true);
	$('#addCreaditType').combobox('enable', true);
	
};

//关闭表单
function closeForm(flag) {
	if(flag=='add'){
		var a= $('#addProduct').combotree('getValues');
		$('#addCreditRatingLimitInfo').window('close');
	}else{
		$('#updateCreditRatingLimitInfo').window('close');
	}
	initDatagrid1();
}


$('#addCreditRating').combobox({
	onSelect: function(param){
		if(param.value=='1'){//征信白户
			$('#addCustomerType').combobox('clear');
			$('#addProduct').combotree('clear');
			$('#addCreaditType').combobox('clear');
			
			$('#addCustomerType').combobox('enable', true);
			$('#addProduct').combotree('enable', true);
			$('#addCreaditType').combobox('disable', true);
		}else if(param.value=='2'){//当前逾期造成信用不良
			$('#addCustomerType').combobox('clear');
			$('#addProduct').combotree('clear');
			$('#addCreaditType').combobox('clear');
			
			$('#addCustomerType').combobox('disable', true);
			$('#addProduct').combobox('disable', true);
			$('#addCreaditType').combobox('enable', true);
		}else{//非当前逾期造成信用不良
			$('#addCustomerType').combobox('clear');
			$('#addProduct').combotree('clear');
			$('#addCreaditType').combobox('clear');
			
			$('#addCustomerType').combobox('disable', true);
			$('#addProduct').combobox('disable', true);
			$('#addCreaditType').combobox('disable', true);
		}
		
	
	}
});

$('#updateCreditRating').combobox({
	onSelect: function(param){
		if(param.value=='1'){//征信白户
			$('#updateCustomerType').combobox('clear');
			$('#updateProduct').combotree('clear');
			$('#updateCreaditType').combobox('clear');
			
			$('#updateCustomerType').combobox('enable', true);
			$('#updateProduct').combobox('enable', true);
			$('#updateCreaditType').combobox('disable', true);
		}else if(param.value=='2'){//当前逾期造成信用不良
			$('#updateCustomerType').combobox('clear');
			$('#updateProduct').combotree('clear');
			$('#updateCreaditType').combobox('clear');
			
			$('#updateCustomerType').combobox('disable', true);
			$('#updateProduct').combobox('disable', true);
			$('#updateCreaditType').combobox('enable', true);
			
		}else{//非当前逾期
			$('#updateCustomerType').combobox('clear');
			$('#updateProduct').combotree('clear');
			$('#updateCreaditType').combobox('clear');
			
			$('#updateCustomerType').combobox('disable', true);
			$('#updateProduct').combobox('disable', true);
			$('#updateCreaditType').combobox('disable', true);
		}
		
	
	}
});



//保存征信信息
function saveBMSBankInfo() {
	//必填校验
	var addCreditRating=$('#addCreditRating').combobox('getValue');
	var addCustomerType=$('#addCustomerType').combobox('getValue');
	var addCreaditType=$('#addCreaditType').combobox('getValue');
	var addProductCodeArray=$('#addProduct').combotree('getValues');
	var addRemark=$('#addRemark').val();
	if(addCreditRating==null||addCreditRating==''){
		$.messager.show({
			title : '提示',
			msg : '请填写征信等级！'
		});
		return;
	}
	if(addCreditRating=='1'){
		if(addCustomerType==null||addCustomerType==''){
			$.messager.show({
				title : '提示',
				msg : '请填写申请类型！'
			});
			return;
		}
		if(addProductCodeArray==null||addProductCodeArray.length==0){
			$.messager.show({
				title : '提示',
				msg : '请填写产品！'
			});
			return;
		}
		if(addRemark==null||addRemark==''){
			$.messager.show({
				title : '提示',
				msg : '请填写提示语！'
			});
			return;
		}
		if(addRemark.length>100){
			$.messager.show({
				title : '提示',
				msg : '提示语输入过长！'
			});
			return;
		}
	}else if(addCreditRating=='2'){//当前逾期
		if(addRemark==null||addRemark==''){
			$.messager.show({
				title : '提示',
				msg : '请填写提示语！'
			});
			return;
		}
		if(addRemark.length>100){
			$.messager.show({
				title : '提示',
				msg : '提示语输入过长！'
			});
			return;
		}
		if(addCreaditType==null||addCreaditType==''){
			$.messager.show({
				title : '提示',
				msg : '请填写贷记类型！'
			});
			return;
		}
		
	}else{//非当前逾期
		if(addRemark==null||addRemark==''){
			$.messager.show({
				title : '提示',
				msg : '请填写提示语！'
			});
			return;
		}
		if(addRemark.length>100){
			$.messager.show({
				title : '提示',
				msg : '提示语输入过长！'
			});
			return;
		}
	}
	var addProductCode='';
	var addProductText='';
	if(addCreditRating=='1'){
		addProductCode=addProductCodeArray.join(',');
		addProductText=$('#addProduct').combotree('getText');
	}
	
	  $.ajax({
		   url : 'creditRatingLimit/addCreditRatingLimit',
		   data : {
			   			'addCreditRating' : addCreditRating,
			   			'addCustomerType' : addCustomerType,
			   			'addProductCode' : addProductCode,
			   			'addProductText' : addProductText,
			   			'addRemark' :addRemark,
			   			'addCreaditType' : addCreaditType
			    },
		   type:"POST",
		   async:false,  // 设置同步方式
	       cache:false,
		   success : function(result){
			   $.messager.progress('close');
		   		if(result.isSuccess){
		   			initDatagrid1();
		   			$.messager.show({
						title : '提示',
						msg : '保存成功！'
					});
		   			
		   			$('#addCreditRatingLimitInfo').window({
		   				modal:true,
		   				closed:true,
		   			});
		   			
		   		}else{
		   			parent.$.messager.show({
						title: '提示',
						msg: result.message
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



function formatOperations1(value,row,index) {
	var operations = '';
	var bankId = row.id;
	operations += '<a href="javascript:void(0)" onclick="loadUpdateCreditToWindow('+row.id+')">修改 &nbsp;&nbsp;</a>';
	operations += '<a href="javascript:void(0)" onclick="deleteBMSCreditInfo('+row.id+')">删除 &nbsp;&nbsp;</a>';
    return operations;
 
 };

function deleteBMSCreditInfo(id) {
	$.messager.confirm('确认','您确认想要删除该银行信息吗？',function(r){    
	    if (r){    
	 $.ajax({
         url : 'creditRatingLimit/deleteCreditRatingLimit',
         data : {id:id},
         success : function(result){
        	 if(result.isSuccess){
	   			  $.messager.show({
					    title : '提示',
					    msg : '删除成功！'
				   });
	   			  initDatagrid1();
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
	 });}
});
}
//编辑初始化
function loadUpdateCreditToWindow(id) {
	
	 $.ajax({
         url : 'creditRatingLimit/loadCreditRatingLimit',
         data : {id:id},
         type:"POST",
         success : function(result){
        	 $('#updateCreditRatingLimitInfo').window({
        			modal:true,
        			closed:false,
        		});
        		$('#updateCreditRatingLimitInfoForm').form('clear');
         		stuffUpdateCreditPage(result.data);
         		var updateCreditRating=$('#updateCreditRating').combobox('getValue');
         		if(updateCreditRating=='1'){
         			$('#updateCustomerType').combobox('enable', true);
         			$('#updateProduct').combobox('enable', true);
         			$('#updateCreaditType').combobox('disable', true);
         		}else if(updateCreditRating=='2'){
         			$('#updateCustomerType').combobox('disable', true);
         			$('#updateProduct').combobox('disable', true);
         			$('#updateCreaditType').combobox('enable', true);
         			
         		}else{
         			$('#updateCustomerType').combobox('disable', true);
         			$('#updateProduct').combobox('disable', true);
         			$('#updateCreaditType').combobox('disable', true);
         		}
         },
         error:function(data){
        	 $.messager.show({
					title: 'warning',
					msg: data
				});
         }
	 });
}
//编辑填充
function stuffUpdateCreditPage(result) {
	$('#updateId').val(result.id);
	$('#updateCreditRating').combobox('setValue',result.grade);
	$('#updateCustomerType').combobox('setValue',result.customerType);
	$('#updateCreaditType').combobox('setValue',result.creditType);
	var productType=result.productType;
	if(null!=productType){
		var productArray=productType.split(',');
		$('#updateProduct').combotree('setValues',productArray);
	}
	$('#updateRemark').val(result.remark);
 }

//修改渠道信息
function saveUpdateBMSCreditInfo() {
	//必填校验
	var updateCreditRating=$('#updateCreditRating').combobox('getValue');
	var updateCustomerType=$('#updateCustomerType').combobox('getValue');
	var updateProductCodeArray=$('#updateProduct').combotree('getValues');
	var updateCreaditType=$('#updateCreaditType').combobox('getValue');
	var updateRemark=$('#updateRemark').val();
	var updateId=$('#updateId').val();
	if(updateCreditRating==null||updateCreditRating==''){
		$.messager.show({
			title : '提示',
			msg : '请填写征信等级！'
		});
		return;
	}
	if(updateCreditRating=='1'){
		if(updateCustomerType==null||updateCustomerType==''){
			$.messager.show({
				title : '提示',
				msg : '请填写申请类型！'
			});
			return;
		}
		if(updateProductCodeArray==null||updateProductCodeArray.length==0){
			$.messager.show({
				title : '提示',
				msg : '请填写产品！'
			});
			return;
		}
		if(updateRemark==null||updateRemark==''){
			$.messager.show({
				title : '提示',
				msg : '请填写提示语！'
			});
			return;
		}
		
		if(addRemark.length>100){
			$.messager.show({
				title : '提示',
				msg : '提示语输入过长！'
			});
			return;
		}
	}else if(updateCreditRating=='2'){
		if(updateRemark==null||updateRemark==''){
			$.messager.show({
				title : '提示',
				msg : '请填写提示语！'
			});
			return;
		}
		if(addRemark.length>100){
			$.messager.show({
				title : '提示',
				msg : '提示语输入过长！'
			});
			return;
		}
		if(updateCreaditType==null||updateCreaditType==''){
			$.messager.show({
				title : '提示',
				msg : '请填写贷记类型！'
			});
			return;
		}
	}else{
		if(updateRemark==null||updateRemark==''){
			$.messager.show({
				title : '提示',
				msg : '请填写提示语！'
			});
			return;
		}
		if(updateRemark.length>100){
			$.messager.show({
				title : '提示',
				msg : '提示语输入过长！'
			});
			return;
		}
	}
	var updateProductCode='';
	var updateProductText='';
	if(updateCreditRating=='1'){
		updateProductCode=updateProductCodeArray.join(',');
		updateProductText=$('#updateProduct').combotree('getText');
	}
	
	
	
	$.ajax({
		   url : 'creditRatingLimit/updateCreditRatingLimit',
		   data : {
			   			'updateCreditRating' : updateCreditRating,
			   			'updateCustomerType' : updateCustomerType,
			   			'updateProductCode' : updateProductCode,
			   			'updateProductText' : updateProductText,
			   			'updateRemark' :updateRemark,
			   			'id' : updateId,
			   			'updateCreaditType' :updateCreaditType
			    },
		   type:"POST",
		   async:false,  // 设置同步方式
	       cache:false,
		   success : function(result){
			   $.messager.progress('close');
		   		if(result.isSuccess){
		   			initDatagrid1();
		   			$.messager.show({
						title : '提示',
						msg : '保存成功！'
					});
		   			
		   			$('#updateCreditRatingLimitInfo').window({
		   				modal:true,
		   				closed:true,
		   			});
		   			
		   		}else{
		   			parent.$.messager.show({
						title: '提示',
						msg: result.message
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
