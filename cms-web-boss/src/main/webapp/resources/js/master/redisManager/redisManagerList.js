$(function() {
	initDatagrid();

	// 查询按钮
	$('#redisManagerDiv #searchBt').bind('click', search);

});
// 加载数据
function initDatagrid() {
	$("#redisManagerDatagrid").datagrid({
		url : 'redisManager/listPage',
		striped : true,
		singleSelect : false,
		rownumbers : true,
		fitColumns : false,
		scrollbarSize : 0,
//		pagination : true,
//		pageList : [ 10, 20, 50 ],
		columns : [ [ {
			field : 'key',
			checkbox : true,
			width : 30
		}, {
			field : 'name',
			title : '属性代码 ',
			width : 200,
		}/*, {
			field : 'operation',
			title : '操作',
			formatter : formatOperations,
			width : 260
		}*/ ] ]
	});
}
// 查询按钮
function search() {
	var queryParams = $('#redisManagerDatagrid').datagrid('options').queryParams;
	queryParams.name = $('#name').val();
	setFirstPage("#redisManagerDatagrid");
	$('#redisManagerDatagrid').datagrid('options').queryParams = queryParams;
	$("#redisManagerDatagrid").datagrid('reload');
}
function removeRedisKeys(){
	var rows = $('#redisManagerDatagrid').datagrid('getChecked');

	if (rows.length == 0) {
		$.messager.show({
			title : '提示',
			msg : '请至少选择一条数据'
		});
		return;
	}
	var keyList = [];
	for ( var i in rows) {
		keyList.push(rows[i].key);
	}
	var url = 'redisManager/remove';
	$.messager.confirm('确认', '您确认想要删除[' + name + ']吗？', function(r) {
		if (r) {
			$.ajax({
				url : url,
				data : {
					'keys' : keyList.join(",")
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
							title : '提示',
							msg : result
						});
					}
				},
				error : function(data) {
					$.messager.show({
						title : '提示',
						msg : data.responseText
					});
				}
			});
		}
	});
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
