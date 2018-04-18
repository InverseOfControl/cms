$(function() {
	initDatagrid();

	// 查询按钮
	$('#searchBt').bind('click', search);

	$("#panelBMSChannelBankInfo").window({
		inline : true
	});

	// 渠道配置进来
	var channelId = $('#queryChannelBankDiv #channelIdRequest').val();
	if(channelId){
		var eleId='channelId'
		//特殊场景，重现formatter
		$('#channelId').combobox({
			formatter : function(row) {
				var valueField = $(this).combobox("options").valueField;
				var textField = $(this).combobox("options").textField;
				var rowShow;
				if(channelId && row[valueField]==channelId){
					row.selected=true;
				}
				
				if (row.selected == true) {
					rowShow = "<input type='checkbox' style='height:14px; vertical-align:bottom;' checked='checked' selectName='"+eleId+"' id='"+eleId
					+ row[valueField] + "' value='" + row[valueField] + "'>" + row[textField]
					+ "</input>";
				} else {
					rowShow = "<input type='checkbox' style='height:14px; vertical-align:bottom;' selectName='"+eleId+"' id='"+eleId + row[valueField]
					+ "' value='" + row[valueField] + "'>" + row[textField]
					+ "</input>";
				}
				return rowShow;
			}
		});
		$('#channelId').combobox('setValue', channelId);
	}
	
	search();
});


$(window).keydown(function(event) {
    if(event.keyCode == 13) {
    	search();
    }
});


// 加载数据
function initDatagrid() {
	var IsCheckFlag = true;
	$("#new_channelBankDatagrid").datagrid({
		url : 'channelBank/listPage',
		striped : true,
		singleSelect : false,
		rownumbers : true,
		pagination : true,
		checkOnSelect : false,
		selectOnCheck : false,
		singleSelect : false,
		fitColumns : false,
		//pageList : [ 10, 20, 50 ],
		scrollbarSize : 0,
		columns : [ [ {
			field : 'id',
			checkbox : true,
			width : 30,
		}, {
			field : 'channeCode',
			title : '渠道代码 ',
			width : 200,
		}, {
			field : 'channeName',
			title : '渠道名称',
			width : 200,
		}, {
			field : 'bankName',
			title : '银行名称',
			width : 200,
		}, {
			field : 'isDisabled',
			title : '是否启用',
			width : 200,
			formatter : function(value, row, index) {
				if (value == 0) {
					return "是";
				} else {
					return "否 ";
				}
			}
		}, {
			field : 'operation',
			title : '操作',
			formatter : formatOperations,
			width : 260
		} ] ]
	});
}
// 查询按钮
function search() {
	var queryParams = $('#new_channelBankDatagrid').datagrid('options').queryParams;
	var channels=$('#channelId').combobox('getValues');
	var banks=$('#bankId').combobox('getValues');
	var channelIds="";
	var bankIds="";
	for(var i=0;i<channels.length;i++){
		if(i!=channels.length-1){
			channelIds=channels[i]+","+channelIds;
		}else{
			channelIds=channelIds+channels[i];
		}
	}
	
	for(var i=0;i<banks.length;i++){
		if(i!=banks.length-1){
			bankIds=bankIds+banks[i]+","+bankIds;
		}else{
			bankIds=bankIds+banks[i];
		}
	}
	queryParams.channelIds = channelIds;
	queryParams.bankIds =bankIds;
	setFirstPage("#new_channelBankDatagrid");
	$('#new_channelBankDatagrid').datagrid('options').queryParams = queryParams;
	$("#new_channelBankDatagrid").datagrid('reload');
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
function formatOperations(value, row, index) {
	var operations = '';
	operations += '<a href="javascript:void(0)" style="font-weight:bolder;color:blue;" onclick="loadUpdateBMSTmParameterToWindow('
			+ row.id + ')">修改 &nbsp;&nbsp;</a>';
	return operations;
};
// 弹出新增枚举窗口
function addBMSChannelBankInfo() {
	$('#panelBMSChannelBankInfo').window({
		modal : true,
		closed : false,
	});
	
	$('#panelBMSChannelBankInfo').form('clear');
	$('input[name="panelIsDisabled"]').get(0).checked = true;
	
	//先清空下拉框的值
	$('#panelChannelId').combobox('clear');  
	//重写URL
	$('#panelChannelId').combobox('reload','channeProduct/findChannelEqDate');  
	$('#panelChannelId').combobox({ disabled: false });
};

// 保存枚举
function saveBMSChannelBankInfo() {
	// 必填校验
	var boo = BMSChannelBankFromInfoExam('#addBMSChannelBankInfoForm');
	if (!boo)
		return;
	var id = $('#panelId').val();
	id = (id == "" ? null : id);
	var channelId = $('#panelChannelId').combobox("getValue");
	var bankId = $('#panelBankId').combobox("getValue");
	var isDisabled = $('input:radio[name="panelIsDisabled"]:checked').val();
	var url = 'channelBank/addChannelBank';
	var flag="";
	if (id != null) {
		var oldBankId=$('#oldBankId').val();
		if(bankId==oldBankId){
			flag="old";
		}else{
			flag="new";
		}
		url = 'channelBank/updateChannelBank';
	}
	$.ajax({
		type : "POST",
		url : url,
		data : {
			'id' : id,
			'channelId' : channelId,
			'bankId' : bankId,
			'isDisabled' : isDisabled,
			'flag' :flag
		},
		dataType : 'json',
		async : false, // 设置同步方式
		cache : false,
		success : function(result) {
			$.messager.progress('close');
			if(result.hasChanel){
					var panelChannelName=$('#panelChannelId').combobox('getText');
					$.messager.show({
						title : '提示',
						msg : panelChannelName+'已经禁用'
					});
					return;
			}
			if(result.hasStart){
				var bankName=$('#panelBankId').combobox('getText');
				$.messager.show({
					title : '提示',
					msg : bankName+'已经禁用'
				});
				return;
			}
			if(result.isFlag){
				$.messager.show({
					title : '提示',
					msg : '亲！该条记录已经存在！'
				});
				return;
			}	
			if (result.isSuccess) {
				$.messager.show({
					title : '提示',
					msg : '保存成功！'
				});
				/*
				 * $('#panelBMSEnumCodeInfo').window({ modal : true, closed :
				 * true, });
				 */
				$('#panelBMSChannelBankInfo').window('close');
				$('#searchBt').trigger('click');
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
// 关闭弹窗按钮
function closeChBankWindow() {
	$('#panelBMSChannelBankInfo').window('close');
}

// 校验
function BMSChannelBankFromInfoExam(divName) {
	var channelId = $(divName).find('input[id="panelChannelId"]').combobox(
			"getValue");
	if (!channelId) {
		$.messager.show({
			title : '提示',
			msg : '请选择渠道名称'
		});
		return false;
	}

	var bankId = $(divName).find('input[id="panelBankId"]')
			.combobox("getValue");
	if (!bankId) {
		$.messager.show({
			title : '提示',
			msg : '请选择银行名称'
		});
		return false;
	}

	return true;
}
// 删除按钮
function deleteBMSChannelBank() {
	var rows = $('#new_channelBankDatagrid').datagrid('getChecked');
	if (rows.length == 0) {
		$.messager.show({
			title : '提示',
			msg : '请至少选择一条数据'
		});
		return;
	}
	var ids = '';
	for ( var i in rows) {
		ids += rows[i].id + ',';
	}
	$.messager.confirm('确认', '您确认想要删除选中的' + rows.length + '条吗？', function(r) {
		if (r) {
			$.ajax({
				url : 'channelBank/deleteChannelBank',
				data : {
					'ids' : ids
				},
				success : function(result) {
					if (result.isSuccess) {
						$.messager.show({
							title : '提示',
							msg : '删除成功！'
						});
						//initDatagrid();
						$('#searchBt').trigger('click');
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
	});
}
// 编辑初始化
function loadUpdateBMSTmParameterToWindow(id) {
	if (!id) {
		return;
	} else {
		$('#panelBMSChannelBankInfo').form('clear');
	}
	 
	$.ajax({
		url : 'channelBank/updateChannelBankInit',
		data : {
			'id' : id
		},
		type : "POST",
		success : function(result) {
			$('#panelBMSChannelBankInfo').window({
				modal : true,
				closed : false,
			});
			$('#panelBMSChannelBankInfo').form('clear');
			//先清空下拉框的值
			$('#panelChannelId').combobox('clear');
			//重写URL
			$('#panelChannelId').combobox('reload','channeProduct/findChannel'); 
			stuffUpdateChannelBankPage(result.info);
		},
		error : function(data) {
			$.messager.show({
				title : 'warning',
				msg : data.responseText
			});
		}
	});
}
// 编辑填充
function stuffUpdateChannelBankPage(result) {
	$('#panelBMSChannelBankInfo').form('load', result);
	$('#panelId').val(result.id);
	$('#panelChannelId').combobox('setValue', result.channelId).combobox('disable');
	$('#panelBankId').combobox("setValue", result.bankId);
	$('#oldBankId').val(result.bankId);
	if (result.isDisabled == 0) {
		$('input[name="panelIsDisabled"]').get(0).checked = true;
	} else {
		$('input[name="panelIsDisabled"]').get(1).checked = true;
	}

}