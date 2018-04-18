$(function() {
	initDatagrid();
	// 渠道配置进来
	/*var channelId = $('#channelIdRequest').val();
	$('#cha_pro_channelId').combobox('select', channelId);
	query_cha_pro_Info();*/
	$.extend($.fn.validatebox.defaults.rules, {
		integerCheck:{
			validator:function(value){
				if(/^[+]?[0-9]\d*$/.test(value)){
				} else {
					this.value = "";
				}
				return true;
//				return /^[+]?[0-9]\d*$/.test(value);
			},
			message: '请输入整数'
		}
	});
	// 渠道配置进来
	var channelId = $('#cha_pro_toolbar #channelIdRequest').val();
	if(channelId){
		var eleId='cha_pro_channelId'
		//特殊场景，重现formatter
		$('#cha_pro_channelId').combobox({
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
		$('#cha_pro_channelId').combobox('setValue', channelId);
	}
});

$(window).keydown(function(event) {
    if(event.keyCode == 13) {
    	query_cha_pro_Info();
    }
});

function initDatagrid() {
	var channlId=$('#cha_pro_toolbar #channelIdRequest').val();
	$("#cha_pro_datagrid").datagrid({
		onLoadSuccess : function(data) {
			if (data.total == 0) {
				$.messager.show({
					title : '结果',
					msg : '没查到符合条件的数据！',
					showType : 'slide',
				});
			}
		},
//		url : 'channeProduct/listPage?channelId='+channlId,
		url : 'channeProduct/listPage',
		fitColumns : true,
		border : false,
		singleSelect : true,
		pagination : true,
		striped : true,
		rownumbers : true,
		fit : true,
		columns : [ [ {
			field : 'id',
			title : '渠道产品ID',
			width : 220,
			hidden : true
		}, {
			field : 'auditLimitId',
			title : '审批期限ID',
			width : 220,
			hidden : true
		}, {
			field : 'channelCode',
			title : '渠道代码',
			width : 220
		}, {
			field : 'channelName',
			title : '渠道名称',
			width : 220
		}, {
			field : 'productCode',
			title : '产品代码',
			width : 220
		}, {
			field : 'productName',
			title : '产品名称',
			width : 220
		}, {
			field : 'auditLimit',
			title : '产品期限',
			width : 220
		}, {
			field : 'isDisabled',
			title : '是否启用',
			formatter : function(value, row, index) {
				/*if (value != null && value != '' && value != undefined) {*/
				if (value == 0) {
					return "是";
				} else if (value == 1) {
					return "否";
				} else {
					return value;
				}
				/*}*/
			},
			width : 220
		}, {
			field : 'operation',
			title : '操作',
			formatter : format_Cha_pro_Operations,
			width : 250
		} ] ]
	});
}
/**
 * 查询函数
 */
function query_cha_pro_Info() {
	var channelId = buildValues($('#cha_pro_channelId').combobox('getValues'));
	var productId = buildValues($('#cha_pro_productId').combobox('getValues'));
	var auditLimit = buildValues($('#cha_pro_auditLimit').combobox('getValues'));
	// 查询
	var queryParams = $('#cha_pro_datagrid').datagrid('options').queryParams;
	queryParams.channs = channelId;
	queryParams.prods = productId;
	queryParams.limits = auditLimit;
	setcha_pro_FirstPage("#cha_pro_datagrid");
	$('#cha_pro_datagrid').datagrid('options').queryParams = queryParams;
	$("#cha_pro_datagrid").datagrid('reload');
}

function buildValues(datas){
	var params="";
	for(var i=0;i<datas.length;i++){
		if(i!=datas.length-1){
			params=datas[i]+""+","+params;
		}else{
			params=params+datas[i];
		}
	}
	return params;
}
/**
 * 设置查询分页信息
 * 
 * @param ids
 */
function setcha_pro_FirstPage(ids) {
	var opts = $(ids).datagrid('options');
	var pager = $(ids).datagrid('getPager');
	opts.pageNumber = 1;
	opts.pageSize = opts.pageSize;
	pager.pagination('refresh', {
		pageNumber : 1,
		pageSize : opts.pageSize
	});
}
/**
 * 操作函数
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function format_Cha_pro_Operations(value, row, index) {

	var operations = '';
	var isDisabled = row.isDisabled;
	// 0.启用，1,禁用
	if (isDisabled == 0) {
		isDisabled = 1;
		operations += '<a href="javascript:void(0)" onclick="changeEnableState('
				+ row.id + ',' + isDisabled + ',' + row.channelId + ',' + row.auditLimitId + ',' + row.productId +')">禁用 &nbsp;&nbsp;</a>';
	}
	// 1.启用，2,禁用
	else if (isDisabled == 1) {
		isDisabled = 0;
		operations += '<a href="javascript:void(0)" onclick="changeEnableState('
				+ row.id + ',' + isDisabled + ',' + row.channelId + ',' + row.auditLimitId + ',' + row.productId +')">启用 &nbsp;&nbsp;</a>';
	}
	return operations;

};
/**
 * 启用、禁用函数
 * 
 * @param id 渠道产品期限id
 * @param isDisabled
 * @param channelId 渠道id
 * @param auditLimitId 期限id
 * @param productId 产品id
 */
function changeEnableState(id, isDisabled, channelId, auditLimitId, productId) {
	//启用判断，上层数据被禁用不可启用
	if('1' == isDisabled){
		channelLimitDisable(id, isDisabled, channelId, auditLimitId, productId);
	} else {
		$.ajax({
			url : 'channeProduct/channelLimitDisableCheck',
			data : {
				id : id,
				channelId : channelId,
				auditLimitId : auditLimitId,
				productId : productId
			},
			type : "POST",
			success : function(result) {
				if (result.isSuccess) {
					channelLimitDisable(id, isDisabled, channelId, auditLimitId, productId);
				} else {
					parent.$.messager.show({
						title : '提示',
						msg : result.message+",配置不可启用"
					});
				}
			}
		});
	}
}
function channelLimitDisable(id, isDisabled, channelId, auditLimitId, productId){
	if('1' == isDisabled){
		$("#panelChannelProductIsDisableMessage").html("禁用后相应的网点产品配置将失效！确定禁用吗？");
	} else if('0' == isDisabled){
//		$("#panelChannelProductIsDisableMessage").html("启用后相应的网点产品配置将生效！确定启用吗？");
		$("#panelChannelProductIsDisableMessage").html("确定启用吗？");
	}
	$("#panelChannelProductIsDisable").window("open");
	$("#panelChannelProductIsDisableConfirm").off("click");
	$("#panelChannelProductIsDisableConfirm").on("click", function() {
		$.ajax({
			url : 'channeProduct/updateChannelLimit',
			data : {
				id : id,
				isDisabled : isDisabled
			},
			type : "POST",
			success : function(result) {
				if (result.isSuccess) {
					$.messager.show({
						title : '提示',
						msg : '修改成功！'
					});
					$("#cha_pro_datagrid").datagrid('reload');
				} else {
					parent.$.messager.show({
						title : 'Error',
						msg : "修改失败！"
					});
				}
				closeIsDisableWindow();
			}
		});
	});
}
//防止取消时出现弹框
var busiChanTreeCheckNum = 1;
// 渠道产品配置
function deploy_cha_pro_Info() {
	// 左边树
	$('#channelProCenterTree')
			.tree(
					{
						url : 'channeProduct/findChannelTree',
						checkbox : true,
						onlyLeafCheck : true,
						onCheck : function(node) {
							var nodes = $('#channelProCenterTree').tree('getChecked');
							if (nodes.length > busiChanTreeCheckNum) {
								$.messager.show({
									title : '提示',
									msg : '只能选择一个渠道信息！'
								});
								// 取消选中
								// $('#channelProCenterTree').tree('uncheck',
								// node.target);
							}
							if (nodes.length == 1) {
								$('#channelProEastTree')
										.tree(
												{
													url : 'channeProduct/findProductTree?channelId='
															+ nodes[0].id,
													checkbox : true
												});
							}
							if( nodes.length<1){
								busiChanTreeCheckNum = 1
							} else {
								busiChanTreeCheckNum = nodes.length;
							}
						},
						// 去掉单选样式
						onBeforeSelect : function(node) {
							return false;
						}
					});
	// 右边树
	$('#channelProEastTree').tree({
		url : 'channeProduct/findProductTree',
		checkbox : true,
		onLoadSuccess : function() {
		}
	});
	// 渠道产品配置弹出框
	$('#deployChannelProductInfo').window({
		modal : true,
		closed : false
	});
}
/**
 * 保存函数
 * 
 * @returns {Boolean}
 */
function add_cha_pro_Info() {
	// 渠道节点，只能选择一个
	var channelNodes = $('#channelProCenterTree').tree('getChecked');
	//
	if (channelNodes.length < 1) {
		$.messager.show({
			title : '提示',
			msg : '请选择渠道信息！'
		});
		return false;
	}
	if (channelNodes.length > 1) {
		$.messager.show({
			title : '提示',
			msg : '只能选择一个渠道信息！'
		});
		return false;
	}
	// 产品期限
	/*var productNodes = $('#channelProEastTree').tree('getChecked');
	if (productNodes.length < 1) {
		$.messager.show({
			title : '提示',
			msg : '请选择产品期限信息！'
		});
		return false;
	}
	var limitIds = "";
	for (var i = 0; i < productNodes.length; i++) {
		if (productNodes[i].deep == 3) {
			limitIds = limitIds + productNodes[i].id + ",";
		}
	}*/
	$("#panelChannelProductSaveMessage").html("确定保存渠道产品配置信息?");
	$("#panelChannelProductSave").window("open");
	//确定按钮事件解绑和绑定
	$("#panelChannelProductSaveConfirm").off("click");
	$("#panelChannelProductSaveConfirm").on("click", function() {
		
		var channelNodes = $('#channelProCenterTree').tree('getChecked');

		var productNodes = $('#channelProEastTree').tree('getChecked');
		var limitIds = "";
		for (var i = 0; i < productNodes.length; i++) {
			if (productNodes[i].deep == 3) {
				limitIds = limitIds + productNodes[i].id + ",";
			}
		}
		$.ajax({
			url : 'channeProduct/saveChannelLimit',
			data : {
				channelId : channelNodes[0].id,
				channelLimitIds : limitIds
			},
			type : "POST",
			success : function(result) {
				if (result.isSuccess) {
					$.messager.show({
						title : '提示',
						msg : '配置成功！'
					});
					$('#deployChannelProductInfo').window('close');
					$("#cha_pro_datagrid").datagrid('reload');

				} else {
					parent.$.messager.show({
						title : 'Error',
						msg : "配置失败！"
					});
				}
				closeSaveWindow();
			}
		});
	});

}
/**
 * 取消函数
 */
function cancel_cha_pro_Info() {
	$('#deployChannelProductInfo').window('close');
}
/**
 * 启用禁用取消函数
 */
function closeIsDisableWindow() {
	$('#panelChannelProductIsDisable').window('close');
}
/**
 * 配置保存取消函数
 */
function closeSaveWindow() {
	$('#panelChannelProductSave').window('close');
}
