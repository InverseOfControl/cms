$(function() {
	initDatagrid();

	// 查询按钮
	$('#searchBt').bind('click', search);

	$("#panelBMSEnumCodeInfo").window({
		inline : true
	});

});
// 加载数据
function initDatagrid() {
	$("#new_enumCodeDatagrid").datagrid({
		url : 'enumCode/listPage',
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		fitColumns : false,
		pageList : [ 10, 20, 50 ],
		scrollbarSize : 0,
		columns : [ [ {
			field : 'codeType',
			title : '数据类型 ',
			width : 200,
		}, {
			field : 'code',
			title : '数据代码',
			width : 200,
		}, {
			field : 'nameCN',
			title : '数据值',
			width : 200,
		}, {
			field : 'version',
			title : '版本',
			width : 200,
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
	var queryParams = $('#new_enumCodeDatagrid').datagrid('options').queryParams;
	queryParams.codeType = $('#codeType').val();
	queryParams.code = $('#code').val();
	queryParams.nameCN = $('#nameCN').val();
	setFirstPage("#new_enumCodeDatagrid");
	$('#new_enumCodeDatagrid').datagrid('options').queryParams = queryParams;
	$("#new_enumCodeDatagrid").datagrid('reload');
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
	var channelId = row.id;
	operations += '<a href="javascript:void(0)" style="font-weight:bolder;color:blue;" onclick="loadUpdateBMSEnumCodeToWindow('
			+ row.codeId + ')">修改 &nbsp;&nbsp;</a>';
	operations += '<a href="javascript:void(0)" style="font-weight:bolder;color:blue;" onclick="deleteBMSEnumCode('
			+ row.codeId + ',\'' + row.nameCN + '\')">删除 &nbsp;&nbsp;</a>';
	return operations;

};
// 弹出新增枚举窗口
function addBMSEnumCodeInfo() {
	$('#panelBMSEnumCodeInfo').window({
		modal : true,
		closed : false,
	});
	$('#panelBMSEnumCodeInfo').form('clear');
};

// 保存枚举
function saveBMSEnumCodeInfo() {
	// 必填校验
	var boo = BMSChanneFromInfoExam('#addBMSEnumCodeInfoForm');
	if (!boo)
		return;
	var codeId = $('#panelCodeId').val();
	codeId = (codeId == "" ? null : codeId);
	var codeType = $('#panelCodeType').val();
	var code = $('#panelCode').val();
	var nameCN = $('#panelNameCN').val();
	var version = $('#panelVersion').val();
	var url = 'enumCode/addEnumCode';
	if (codeId != null) {
		url = 'enumCode/updateEnumCode';
	}
	$.ajax({
		type : "POST",
		url : url,
		data : {
			'codeId' : codeId,
			'codeType' : codeType,
			'code' : code,
			'nameCN' : nameCN,
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
				/*$('#panelBMSEnumCodeInfo').window({
					modal : true,
					closed : true,
				});*/
				$('#panelBMSEnumCodeInfo').window('close');
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
function closeWindow() {
	/*$('#panelBMSEnumCodeInfo').window({
		modal : true,
		closed : true,
	});*/
	$('#panelBMSEnumCodeInfo').window('close');
	$('#searchBt').trigger('click');
}

// 校验
function BMSChanneFromInfoExam(divName) {
	var codeType = $(divName).find('input[id="panelCodeType"]').val();
	if (!codeType) {
		$.messager.show({
			title : '提示',
			msg : '请填写数据类型'
		});
		return false;
	}

	var code = $(divName).find('input[id="panelCode"]').val();
	if (!code) {
		$.messager.show({
			title : '提示',
			msg : '请填写数据代码'
		});
		return false;
	}

	var nameCN = $(divName).find('input[id="panelNameCN"]').val();
	if (!nameCN) {
		$.messager.show({
			title : '提示',
			msg : '请填写数据值'
		});
		return false;
	}

	var version = $(divName).find('input[id="panelVersion"]').val();
	if (!version) {
		$.messager.show({
			title : '提示',
			msg : '请填写版 本'
		});
		return false;
	}
	return true;
}
// 删除按钮
function deleteBMSEnumCode(codeId, nameCN) {
	if (!codeId) {
		return;
	}
	$.messager.confirm('确认', '您确认想要删除[' + nameCN + ']吗？', function(r) {
		if (r) {
			$.ajax({
				url : 'enumCode/deleteEnumCode',
				data : {
					codeId : codeId
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
function loadUpdateBMSEnumCodeToWindow(codeId) {
	if (!codeId) {
		return;
	}
	$.ajax({
		url : 'enumCode/updateEnumCodeInit',
		data : {
			codeId : codeId
		},
		type : "POST",
		success : function(result) {
			$('#panelBMSEnumCodeInfo').window({
				modal : true,
				closed : false,
			});
			$('#panelBMSEnumCodeInfo').form('clear');
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
	$('#panelBMSEnumCodeInfo').form('load', result);
	$('#panelCodeId').val(result.codeId);
	$('#panelCodeType').textbox("setValue", result.codeType);
	$('#panelCode').textbox("setValue", result.code);
	$('#panelNameCN').textbox("setValue", result.nameCN);
	$('#panelVersion').textbox("setValue", result.version);
}