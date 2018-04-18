$(function() {
	initDatagrid_1();
	$("#panelBMSDebtRadionManageInfo").window({
		inline : true
	});
	});
	//获取下拉框
	
// 加载数据
function initDatagrid_1() {
	
	$("#new_datagrid_DateRadio").datagrid({
		
		url : 'debtRadio/listPage',
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		fitColumns : false,
		scrollbarSize : 0,
		columns : [ [
		    {
			field : 'customerTypeName',
			title : '客户类型',
			width : 260
		},{
			field : 'internalDebtRadio',
			title : '内部负债率(%)',
			formatter:dabt_formatter,
			width : 200
		},{
			field : 'totalDebtRadio',
			title : '总负债率(%)',
			formatter:dabt_formatter,
			width : 200
		},{
			field : 'operation',
			title : '操作',
			formatter : debt_formatOperations,
			width : 250
		} ] ]
	});
}

function dabt_formatter(value, row, index){
	  if(value!=null){
		  var rate =Math.round(value*100*100)/100;
	  }
		 return rate;

	}





function setFirstPage(ids) {
	var opts = $(ids).datagrid('options');
	var pager = $(ids).datagrid('getPager');
	opts.pageNumber = 1;
	opts.pageSize = opts.pageSize;
	pager.pagination('refresh', {
		pageNumber : 1,
		pageSize : opts.pageSize
	});
}
// 操作
function debt_formatOperations(value, row, index) {
	var operations = '';
	operations += '<a href="javascript:void(0)" style="font-weight:bolder;color:blue;" onclick="OpenBMSDebtRadioToWindow('
			+ row.id + ')">修改 &nbsp;&nbsp;</a>';
	return operations;

};


function OpenBMSDebtRadioToWindow(id){
	if(id==null){
		return;
	}
	$.ajax({
		url : 'debtRadio/queryDebtRadioInit',
		data : {
			'id' : id
		},
		type : "POST",
		success : function(result) {
			$('#panelBMSDebtRadionManageInfo').window({
				modal : true,
				closed : false,
				width : 800, 
				height : 500
			});
			$('#updateDebtRadionManageInfoForm').form('clear');
			stuffDabtRadio(result.info);
		},
		error : function(data) {
			$.messager.show({
				title : 'warning',
				msg : data.responseText
			});
		}
	});
	
}
function BMSEditDebtRadioFromInfoExam(divName){
	//总负债率
	
	var totalDebtRadio = $(divName).find('input[name="totalDebtRadio"]').val();
	var reg = new RegExp("^[0-9]*$");  
	if (!totalDebtRadio) {
		$.messager.show({
			title : '提示',
			msg : '请填写总负债率'
		});
		return false;
	}
	if(!reg.test(totalDebtRadio) ||totalDebtRadio.length>3 ){  
			parent.$.messager.show({
				title : '提示',
				msg : '总负债率只能输入[0,100]的正整数'
			});
			return false;
		}
	if(totalDebtRadio<0 || totalDebtRadio>100){
		$.messager.show({
			title : '提示',
			msg : '总负债率只能输入[0,100]的正整数！'
		});
	    return false;
	}
	var internalDebtRadio = $(divName).find('input[name="internalDebtRadio"]').val();
	var reg = new RegExp("^[0-9]*$");  
	if (!internalDebtRadio) {
		$.messager.show({
			title : '提示',
			msg : '请填写内部负债率'
		});
		return false;
	}
	if(!reg.test(internalDebtRadio) ||internalDebtRadio.length>3){  
			parent.$.messager.show({
				title : '提示',
				msg : '内部负债率只能输入[0,100]的正整数'
			});
			return false;
		}
	if(internalDebtRadio<0 || internalDebtRadio>100){
		$.messager.show({
			title : '提示',
			msg : '内部负债率只能输入[0,100]的正整数！'
		});
	    return false;
	}
return true;	
}

function saveBMSDebtRadioInfo(){
	// 必填校验
	var boo = BMSEditDebtRadioFromInfoExam('#panelBMSDebtRadionManageInfo');
	if (!boo)
		return;

	var totalDebtRadio=$('#edit_totalDebtRadio').val();
	var internalDebtRadio = $('#edit_internalDebtRadio').val();
	var id = $('#editid').val();

	var url = 'debtRadio/editDebtRadio';	
	
	$.ajax({
		type : "POST",
		url : url,
		data : {
			'id' : id,
			'totalDebtRadio' : totalDebtRadio,
			'internalDebtRadio':internalDebtRadio,
		},
		dataType : 'json',
		async : false, // 设置同步方式
		cache : false,
		success : function(result) {
			initDatagrid_1();
			if (result.isSuccess) {
				$.messager.show({
					title : '提示',
					msg : '修改成功！'
				});
				$('#panelBMSDebtRadionManageInfo').window({ 
					modal : true,
					closed : true 
					
				});
				 
				
				
				
			} else {
				parent.$.messager.show({
					title : 'Error',
					msg : result
				});
			}
		},
		error : function(data) {
			$.messager.show({
				title : 'warning',
				msg : data.responseText
			});
		}
	});
}
//关闭新增弹窗按钮
function debt_closeWindow() {
	$('#panelBMSDebtRadionManageInfo').window({
		modal:true,
		closed:true,
	});
	initDatagrid_1();
}
function stuffDabtRadio(result) {

	$("#edit_customerTypeName").textbox('setValue',result.customerTypeName);
	$("#edit_totalDebtRadio").textbox('setValue',Math.round(result.totalDebtRadio*100*100)/100);
	$("#edit_internalDebtRadio").textbox('setValue',Math.round(result.internalDebtRadio*100*100)/100);
	$("#editid").textbox('setValue',result.id);
	
	
}