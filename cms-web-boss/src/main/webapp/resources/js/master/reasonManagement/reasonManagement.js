$(function() {
	initDatagrid();
	
	// 查询按钮
	$('#searchBt').bind('click', search);

	$("#panelBMSReasonManagementInfo").window({
		inline : true
	});

});


// 加载数据
function initDatagrid() {
	$("#new_reasonManageDatagrid").datagrid({
		url : 'reasonManagement/listPage',
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		fitColumns : false,
		pageList : [ 10, 20, 50 ],
		scrollbarSize : 0,
		columns : [ [ {
			field : 'id',
			title : 'id ',
			width : 50,
			hidden:true
		}, {
			field : 'reason',
			title : '原因',
			width : 200
		}, {
			field : 'code',
			title : '原因码',
			width : 200
		}, {
			field : 'reasonTexplain',
			title : '原因解释',
			width : 200
		},
		{
			field : 'type',
			title : '分类',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				if (value == 1) {
					return "一级";			
				} else{
					return "二级";
				}
			}
		},
		{
			field : 'parentId',
			title : '父原因ID',
			width : 200,
			hidden:true
		},
		{
			field : 'levelOrder',
			title : '排序',
			width : 50
		},
		{
			field : 'operationModule',
			title : '操作模块',
			width : 100
		},
		{
			field : 'operationType',
			title : '操作类型',
			width : 100			
		},
		{
			field : 'canRequestDays',
			title : '限制在申请天数',
			width : 100
		},
		{
			field : 'remark',
			title : '备注',
			width : 200
		},
		{
			field : 'creator',
			title : '创建用户',
			width : 100,
			hidden:true
		},
		{
			field : 'creatorDate',
			title : '创建时间',
			width : 100,
			hidden:true
		},
		{
			field : 'creatorId',
			title : '创建用户ID',
			width : 100,
			hidden:true
		},
		{
			field : 'modifier',
			title : '修改用户',
			width : 100,
			hidden:true
		},
		{
			field : 'modifiedDate',
			title : '修改时间',
			width : 100,
			hidden:true
		},
		{
			field : 'modifierId',
			title : '修改用户ID',
			width : 100,
			hidden:true
		},
		{
			field : 'version',
			title : '版本',
			width : 50,
			hidden:true
		},
		{
			field : 'isDeleted',
			title : '是否删除',
			width : 50,
			hidden:true,
			formatter : function(value, rowData, rowIndex) {
				if (value == 1) {
					return "是";			
				} else{
					return "否";
				}
			}
		},
		{
			field : 'isBlacklist',
			title : '是否擦入黑名单',
			width : 100,
			//hidden:true,
			formatter : function(value, rowData, rowIndex) {
				if (value == 0) {
					return "是";			
				} else{
					return "否";
				}
			}
		},
		{
			field : 'operation',
			title : '操作',
			formatter : formatOperations,
			width : 260
		} ] ]
	});
}




// 查询按钮
function search() {
	var queryParams = $('#new_reasonManageDatagrid').datagrid('options').queryParams;
	queryParams.operationModule = $('#model').val();
	queryParams.operationType = $('#type').val();
	setFirstPage("#new_reasonManageDatagrid");
	$('#new_reasonManageDatagrid').datagrid('options').queryParams = queryParams;
	$("#new_reasonManageDatagrid").datagrid('reload');
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
	operations += '<a href="javascript:void(0)" style="font-weight:bolder;color:blue;" onclick="loadUpdateBMSReasonManagementToWindow('
			+ row.id + ')">修改 &nbsp;&nbsp;</a>';
	operations += '<a href="javascript:void(0)" style="font-weight:bolder;color:blue;" onclick="deleteBMSTmParameter('
			+ row.id + ',\'' + row.version + '\')">删除 &nbsp;&nbsp;</a>';
	return operations;

};
// 弹出新增枚举窗口
function addBMSReasonManagementInfo() {
	$('#panelBMSReasonManagementInfo').window({
		modal : true,
		closed : false,
		width : 800, 
		height : 500
	});
	$('#addBMSReasonManageInfoForm').form('clear');
};

// 保存
function saveBMSReasonManageInfo() {
	// 必填校验
	var boo = BMSChanneFromInfoExam('#addBMSReasonManageInfoForm');
	if (!boo)
		return;
	var code = $('#code').val();	
	var type = $('#addType').combobox('getValue');
	var parentId = $('#parentId').val();
	var levelOrder = $('#levelOrder').val();
	var operationModule = $('#operationModule').val();
	var isBlacklist = $('#isBlacklist').combobox('getValue');	
	var operationType = $('#operationType').val();
	var canRequestDays = $('#canRequestDays').val();
	var reason = $('#reason').val();
	var reasonTexplain = $('#reasonTexplain').val();
	var remark = $('#remark').val();
	
	var url = 'reasonManagement/addReasonManagement';	
	$.ajax({
		type : "POST",
		url : url,
		data : {
			'code' : code,
			'type' : type,
			'parentId' : parentId,
			'levelOrder' : levelOrder,
			'operationModule' : operationModule,
			'isBlacklist' : isBlacklist,
			'operationType' : operationType,
			'canRequestDays' : canRequestDays,
			'reason' : reason,
			'reasonTexplain' : reasonTexplain,
			'remark' : remark,
			
		},
		dataType : 'json',
		async : false, // 设置同步方式
		cache : false,
		success : function(result) {
			$.messager.progress('close');
			if (result.isSuccess) {
				$.messager.show({
					title : '提示',
					msg : '保存成功！'
				});
				/*
				 * $('#panelBMSEnumCodeInfo').window({ modal : true, closed :
				 * true, });
				 */
				$('#panelBMSReasonManagementInfo').window('close');
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
//编辑保存
function editBMSReasonManageInfo(){
	// 必填校验
	var boo = BMSEditChanneFromInfoExam('#editBMSReasonManageInfoForm');
	if (!boo)
		return;
	debugger;
	var code = $('#editcode').val();	
	var type = $('#editType').combobox('getValue');
	var parentId = $('#editparentId').val();
	var levelOrder = $('#editlevelOrder').val();
	var operationModule = $('#editoperationModule').val();
	var isBlacklist = $('#editisBlacklist').combobox('getValue');	
	var operationType = $('#editoperationType').val();
	var canRequestDays = $('#editcanRequestDays').val();
	var reason = $('#editreason').val();
	var reasonTexplain = $('#editreasonTexplain').val();
	var remark = $('#editremark').val();
	var id = $('#editid').val();
	var version = $('#editversion').val();
	var url = 'reasonManagement/editReasonManagement';	
	$.ajax({
		type : "POST",
		url : url,
		data : {
			'code' : code,
			'type' : type,
			'parentId' : parentId,
			'levelOrder' : levelOrder,
			'operationModule' : operationModule,
			'isBlacklist' : isBlacklist,
			'operationType' : operationType,
			'canRequestDays' : canRequestDays,
			'reason' : reason,
			'reasonTexplain' : reasonTexplain,
			'remark' : remark,
			'id' : id,
			'version' : version
		},
		dataType : 'json',
		async : false, // 设置同步方式
		cache : false,
		success : function(result) {
			$.messager.progress('close');
			if (result.isSuccess) {
				$.messager.show({
					title : '提示',
					msg : '保存成功！'
				});
				/*
				 * $('#panelBMSEnumCodeInfo').window({ modal : true, closed :
				 * true, });
				 */
				$('#editPanelBMSReasonManagementInfo').window('close');
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


// 关闭新增弹窗按钮
function closeWindow() {
	$('#panelBMSReasonManagementInfo').window('close');
	$('#searchBt').trigger('click');
}
//关闭编辑弹窗按钮
function editcloseWindow() {
	$('#editPanelBMSReasonManagementInfo').window('close');
	$('#searchBt').trigger('click');
}

// 校验
function BMSChanneFromInfoExam(divName) {
	var code = $(divName).find('input[id="code"]').val();
	if (!code) {
		$.messager.show({
			title : '提示',
			msg : '请填写原因码'
		});
		return false;
	}

	var type = $('#addType').combobox('getValue');
	if (!type) {
		$.messager.show({
			title : '提示',
			msg : '请填写分类'
		});
		return false;
	}
	return true;
}

function BMSEditChanneFromInfoExam(divName){
	var code = $(divName).find('input[id="editcode"]').val();
	if (!code) {
		$.messager.show({
			title : '提示',
			msg : '请填写原因码'
		});
		return false;
	}

	var type = $('#editType').combobox('getValue');
	if (!type) {
		$.messager.show({
			title : '提示',
			msg : '请填写分类'
		});
		return false;
	}
	return true;
}
// 删除按钮
function deleteBMSTmParameter(id, version) {
	if (!id) {
		return;
	}
	$.messager.confirm('确认', '您确认想要删除[' + name + ']吗？', function(r) {
		if (r) {
			$.ajax({
				url : 'reasonManagement/deleteReasonManage',
				data : {
					'id' : id,
					'version' : version
				},
				success : function(result) {
					if (result.isSuccess) {
						$.messager.show({
							title : '提示',
							msg : '删除成功！'
						});
						initDatagrid();
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
function loadUpdateBMSReasonManagementToWindow(id) {
	if (!id) {
		return;
	}
	$.ajax({
		url : 'reasonManagement/queryReasonManagementInit',
		data : {
			'id' : id
		},
		type : "POST",
		success : function(result) {
			$('#editPanelBMSReasonManagementInfo').window({
				modal : true,
				closed : false,
				width : 800, 
				height : 500
			});
			$('#editBMSReasonManageInfoForm').form('clear');
			stuffUpdatePage(result.info);
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
function stuffUpdatePage(result) {
	debugger;
	//$('#editBMSReasonManageInfoForm').form('load', result);
	$("#editid").textbox('setValue',result.id);
	$('#editversion').textbox('setValue',result.version);
	$("#editcode").textbox('setValue',result.code);
	$('#editType').combobox("setValue", result.type);
	$('#editparentId').textbox('setValue',result.parentId);
	$('#editlevelOrder').textbox('setValue',result.levelOrder);
	$('#editoperationModule').textbox('setValue',result.operationModule);
	$('#editisBlacklist').combobox("setValue", result.isBlacklist);
	$('#editoperationType').textbox('setValue',result.operationType);
	$('#editcanRequestDays').textbox('setValue',result.canRequestDays);
	$('#editreason').textbox('setValue',result.reason);
	$('#editreasonTexplain').textbox('setValue',result.reasonTexplain);
	$('#editremark').textbox('setValue',result.remark);
}