$(function() {
	initDatagrid();

	// 查询按钮
	$('#searchBt').bind('click', search);

	$("#panelBMSTmParameterInfo").window({
		inline : true
	});

});
// 加载数据
function initDatagrid() {
	$("#new_tmParameterDatagrid").datagrid({
		url : 'tmParameter/listPage',
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		fitColumns : false,
		pageList : [ 10, 20, 50 ],
		scrollbarSize : 0,
		columns : [ [ {
			field : 'code',
			title : '属性代码 ',
			width : 200,
		}, {
			field : 'name',
			title : '属性名称',
			width : 200,
		}, {
			field : 'parameterValue',
			title : '属性值',
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
	var queryParams = $('#new_tmParameterDatagrid').datagrid('options').queryParams;
	queryParams.code = $('#code').val();
	queryParams.name = $('#name').val();
	setFirstPage("#new_tmParameterDatagrid");
	$('#new_tmParameterDatagrid').datagrid('options').queryParams = queryParams;
	$("#new_tmParameterDatagrid").datagrid('reload');
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
	operations += '<a href="javascript:void(0)" style="font-weight:bolder;color:blue;" onclick="deleteBMSTmParameter('
			+ row.id + ',\'' + row.name + '\')">删除 &nbsp;&nbsp;</a>';
	return operations;

};
// 弹出新增枚举窗口
function addBMSTmParameterInfo() {
	$('#panelBMSTmParameterInfo').window({
		modal : true,
		closed : false,
	});
	$('#panelBMSTmParameterInfo').form('clear');
};

// 保存枚举
function saveBMSTmParameterInfo() {
	// 必填校验
	var boo = BMSChanneFromInfoExam('#addBMSTmParameterInfoForm');
	if (!boo)
		return;
	var id = $('#panelId').val();
	id = (id == "" ? null : id);
	var code = $('#panelCode').val();
	var name = $('#panelName').val();
	var parameterValue = $('#panelParameterValue').val();
	var version = $('#panelVersion').val();
	var remark = $('#panelRemark').val();
	var url = 'tmParameter/addTmParameter';
	if (id != null) {
		url = 'tmParameter/updateTmParameter';
	}
	$.ajax({
		type : "POST",
		url : url,
		data : {
			'id' : id,
			'code' : code,
			'name' : name,
			'parameterValue' : parameterValue,
			'version' : version,
			'remark' : remark
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
				$('#panelBMSTmParameterInfo').window('close');
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
	/*
	 * $('#panelBMSEnumCodeInfo').window({ modal : true, closed : true, });
	 */
	$('#panelBMSTmParameterInfo').window('close');
	$('#searchBt').trigger('click');
}

// 校验
function BMSChanneFromInfoExam(divName) {
	var code = $(divName).find('input[id="panelCode"]').val();
	if (!code) {
		$.messager.show({
			title : '提示',
			msg : '请填写属性代码'
		});
		return false;
	}

	var name = $(divName).find('input[id="panelName"]').val();
	if (!name) {
		$.messager.show({
			title : '提示',
			msg : '请填写属性名称'
		});
		return false;
	}

	var parameterValue = $(divName).find('input[id="panelParameterValue"]')
			.val();
	if (!parameterValue) {
		$.messager.show({
			title : '提示',
			msg : '请填写属性值'
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
function deleteBMSTmParameter(id, name) {
	if (!id) {
		return;
	}
	$.messager.confirm('确认', '您确认想要删除[' + name + ']吗？', function(r) {
		if (r) {
			$.ajax({
				url : 'tmParameter/deleteTmParameter',
				data : {
					'id' : id
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
function loadUpdateBMSTmParameterToWindow(id) {
	if (!id) {
		return;
	}
	$.ajax({
		url : 'tmParameter/updateTmParameterInit',
		data : {
			'id' : id
		},
		type : "POST",
		success : function(result) {
			$('#panelBMSTmParameterInfo').window({
				modal : true,
				closed : false,
			});
			$('#panelBMSTmParameterInfo').form('clear');
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
	$('#panelBMSTmParameterInfo').form('load', result);
	$('#panelId').val(result.id);
	$('#panelCode').textbox("setValue", result.code);
	$('#panelName').textbox("setValue", result.name);
	$('#panelParameterValue').textbox("setValue", result.parameterValue);
	$('#panelVersion').textbox("setValue", result.version);
	$('#panelRemark').textbox("setValue", result.remark);
}