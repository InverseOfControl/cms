$(function() {
	//新增页面默认关闭
	initDatagrid1();
	$("#addBMSBankInfo").window({inline:true});
	$("#updateBMSBankInfo").window({inline:true});
});

$(window).keydown(function(event) {
    if(event.keyCode == 13) {
    	queryBMSBankInfo();
    }
});


function initDatagrid1() {
		$("#new_datagrid_bank").datagrid({
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
			url : 'bank/listPage',
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
				field : 'code',
				title : '银行编码',
				width : 220,
			}, {
				field : 'name',
				title : '银行名称',
				width : 220,
			},
			{
				field : 'isDisabled',
				title : '是否启用',
				width : 150,
				formatter : function(value, rowData, rowIndex) {
				if (value == 0) {
					return "是";			
				} else{
					return "否";
				}
				}
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

function queryBMSBankInfo() {
	var name = $('#queryBankDiv').find('input[name="name"]').val();
	var code=$('#queryBankDiv').find('input[name="code"]').val();
	//查询
	var queryParams = $('#new_datagrid_bank').datagrid('options').queryParams;
	queryParams.name = name;
	queryParams.code = code;
	setFirstPage("#new_datagrid_bank");
	$('#new_datagrid_bank').datagrid('options').queryParams = queryParams;
	$("#new_datagrid_bank").datagrid('reload');
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

//弹出新增银行窗口
function addBMSBankInfo() {
	$('#addBMSBankInfo').window({
		modal:true,
		closed:false,
	});
	$('#updateBMSBankInfo #code').textbox({ disabled: false });
	$('#addBMSBankInfo').form('clear');
	$('input[name="redio"]').get(0).checked = true;
};

//关闭表单
function closeForm(flag) {
	if(flag=='add'){
		$('#addBMSBankInfo').window('close');
	}else{
		$('#updateBMSBankInfo').window('close');
	}
	initDatagrid1();
}


//保存银行信息
function saveBMSBankInfo() {
	//必填校验
	var boo = BMSBankFromInfoExam('#addBMSBankInfoForm');
	if(!boo) return;
	var isDisabled = $('input:radio[name="redio"]:checked').val();
	var code=$('#addCode').val();
	var name=$('#addName').val();
	name = name.replace(/ /g,'');
	//判断CDDE的格式
		var regu =/^[0-9]*$/;
		if (!regu.test(code)) {
				$.messager.show({
					title : '提示',
					msg : '银行编码请填写数字'
				});
			    return;
		  }
		if(code.length>10){
			$.messager.show({
				title : '提示',
				msg : '银行编码输入过长'
			});
		    return;
		}
		if(name.length>20){
			$.messager.show({
				title : '提示',
				msg : '银行名称输入过长'
			});
		    return;
		}
	  $.ajax({
		   url : 'bank/addBank',
		   data : {
			   			'isDisabled' : isDisabled,
			   			'code' : code,
			   			'name' : name
			    },
		   type:"POST",
		   async:false,  // 设置同步方式
	       cache:false,
		   success : function(result){
			   $.messager.progress('close');
			   if(result.isFlag){
				   $.messager.show({
						title : '提示',
						msg : '该条记录已存在！'
					});
				   return;
			   }		   
		   		if(result.isSuccess){
		   			initDatagrid1();
		   			$.messager.show({
						title : '提示',
						msg : '保存成功！'
					});
		   			
		   			$('#addBMSBankInfo').window({
		   				modal:true,
		   				closed:true,
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

//新增校验
function BMSBankFromInfoExam(divName) {
	//银行id
	var code = $(divName).find('input[name="code"]').val();
	if(!code) {
		$.messager.show({
			title:'提示',
			msg:'请填写银行编码'
		});
		return false;
	}
	
	var bankName = $(divName).find('input[name="name"]').val();
	if(!bankName) {
		$.messager.show({
			title:'提示',
			msg:'请填写银行名称'
		});
		return false;
	}
	return true;
}

function formatOperations1(value,row,index) {
	var operations = '';
	var bankId = row.id;
	operations += '<a href="javascript:void(0)" onclick="loadUpdateBankToWindow('+row.id+')">修改 &nbsp;&nbsp;</a>';
	operations += '<a href="javascript:void(0)" onclick="deleteBMSBankInfo('+row.id+')">删除 &nbsp;&nbsp;</a>';
    return operations;
 
 };

function deleteBMSBankInfo(id) {
	$.messager.confirm('确认','您确认想要删除该银行信息吗？',function(r){    
	    if (r){    
	 $.ajax({
         url : 'bank/deleteBank',
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
	 });}
});
}
//编辑初始化
function loadUpdateBankToWindow(id) {
	 $.ajax({
         url : 'bank/updateBankInit',
         data : {id:id},
         type:"POST",
         success : function(result){
        	 $('#updateBMSBankInfo').window({
        			modal:true,
        			closed:false,
        		});
        		$('#updateBMSBankInfo').form('clear');
         		stuffUpdateBankPage(result.info);
         },
         error:function(data){
        	 $.messager.show({
					title: 'warning',
					msg: data.responseText
				});
         }
	 });
}
//编辑填充
function stuffUpdateBankPage(result) {
	$('#updateBMSBankInfo').find('input[name="id"]').val(result.id);
	$('#updateBMSBankInfo #name').textbox('setValue',result.name);
	$('#updateBMSBankInfo #code').textbox('setValue',result.code).textbox({ disabled: true });
	if (result.isDisabled == 0) {
		$('#updateBMSBankInfoForm input[name="editredio"]').get(0).checked = true;
	} else {
		$('#updateBMSBankInfoForm input[name="editredio"]').get(1).checked = true;
	}
	$('#oldBankCode').val(result.code);
 }

//修改渠道信息
function saveUpdateBMSBankInfo() {
	//必填校验
	var boo = BMSBankFromInfoExam('#updateBMSBankInfoForm');
	if(!boo) return;	
	
	var id=$('#updateBMSBankInfoForm #id').val();
	var code=$('#updateBMSBankInfoForm #code').textbox('getValue');
	var name=$('#updateBMSBankInfoForm #name').textbox('getValue');
	name = name.replace(/ /g,'');
	if(name.length>20){
		$.messager.show({
			title : '提示',
			msg : '银行名称输入过长'
		});
	    return;
	}
		var oldBankCode=$('#oldBankCode').val();
		var newBankCode=$('#updateBMSBankInfo #code').textbox('getValue');
		if(newBankCode==oldBankCode){
			$('#flag').val("old");
		}else{
			$('#flag').val("new");
		}
		var flag=$('#flag').val();
		var isDisabled = $('input:radio[name="editredio"]:checked').val();
	  $.ajax({
		   url : 'bank/updateBank',
		   data : {
			   		'id' : id,
			   		'code' : code,
			   		'name' : name,
			   		'flag' : flag,
			   		'isDisabled' :isDisabled
			    },
		   type:"POST",
		   async:false,  // 设置同步方式
	       cache:false,
		   success : function(result){
			   	if(result.isFlag){
			   		$.messager.show({
						title : '提示',
						msg : '亲！该条记录已经存在！'
					});
			   		return;
			   	}			 
		   		if(result.isSuccess){
		   			initDatagrid1();
		   			$.messager.show({
						title : '提示',
						msg : '保存成功！'
					});
		   			
		   		 $('#updateBMSBankInfo').window({
	        			modal:true,
	        			closed:true,
	        		});
		   			$('#updateBMSBankInfo').form('clear');
		   			
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
