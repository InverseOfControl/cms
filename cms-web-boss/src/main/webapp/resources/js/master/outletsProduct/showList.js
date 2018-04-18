$(function() {
	initOrgDatagrid();
	$('#org_limit_form #iState').switchbutton({
		checked : true,
		onChange : function(checked) {
			if(checked){
				$('#org_limit_form #isDisabled').val('0'); 
			} else {
				$('#org_limit_form #isDisabled').val('1'); 
			}
		}
	})
	$.extend($.fn.validatebox.defaults.rules, {
		integerCheck : {
			validator : function(value) {
				if (/^[+]?[0-9]\d*$/.test(value)) {
				} else {
					this.value = "";
				}
				return true;
				// return /^[+]?[0-9]\d*$/.test(value);
			},
			message : '请输入整数'
		}
	});
});
var channelHis={};

$(window).keydown(function(event) {
    if(event.keyCode == 13) {
    	org_query_cha_pro_Info();
    }
});

function initOrgDatagrid() {
	$("#org_cha_pro_datagrid").datagrid({
		url : 'outletsProduct/listPage',
		fitColumns : true,
		border : false,
		singleSelect : true,
		pagination : true,
		striped : true,
		rownumbers : true,
		fit : true,
		columns : [ [ {
			field : 'orgName',
			title : '网点名称',
			width : 220
		},
		{
			field : 'channelName',
			title : '渠道名称',
			width : 220
		},
		{
			field : 'productName',
			title : '产品名称',
			width : 220
		}, {
			field : 'auditLimit',
			title : '产品期限',
			width : 220
		}, {
			field : 'floorLimit',
			title : '审批额度下限（元）',
			width : 220
		}, {
			field : 'upperLimit',
			title : '审批额度上限（元）',
			width : 220
		}, {
			field : 'isDisabled',
			title : '是否启用',
			width : 220,
			formatter : function(value, rowData, rowIndex) {
				if (value != null && value != undefined) {

					if (value == 0) {
						return "是";
					} else if (value == 1) {
						return "否";
					} else {
						return "";
					}
				}
			}
		},
		{
			field : 'configureConflict',
			title : '配置冲突',
			width : 120,
			formatter : function(value, rowData, rowIndex) {
				if (value != null && value != undefined) {
					if (value == 'N') {
						return "否";
					} else if (value == 'Y') {
						return "是";
					} else {
						return "";
					}
				}
			}
		}
		, {
			field : 'isCanPreferential',
			title : '是否可优惠',
			width : 220,
			formatter : function(value, rowData, rowIndex) {
				if (value != null && value != undefined) {

					if (value == 0) {
						return "是";
					} else if (value == 1) {
						return "否";
					} else {
						return "";
					}
				}
			}
		}, {
			field : 'operation',
			title : '操作',
			width : 250,
			formatter : function(value, rowData, rowIndex){
				var operations = '';
				// 传入审批期限ID,是否可用，审批上限，审批下限
				// 1.启用，2,禁用
				operations += '<a href="javascript:void(0)" onclick="changeLimit('
						+ rowData.auditLimitId + ',' + rowData.id + ',' + rowData.isDisabled + ','
						+ rowData.floorLimit + ',' + rowData.upperLimit + ',' + rowData.proFloorLimit
						+ ',' + rowData.proUpperLimit + ',' + rowData.proAdjustBase + ',' + rowData.channelId + ',' + rowData.productId
						+ ')">修改 &nbsp;&nbsp;</a>';
				return operations;
			}
			
		} ] ],
		onLoadSuccess : function(data) {
			if (data.total == 0) {
				$.messager.show({
					title : '结果',
					msg : '没查到符合条件的数据！',
					showType : 'slide'
				});
			}
		}
	});
}
/**
 * 门店渠道产品操作
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function format_org_Operations(value, row, index) {
	var operations = '';
	// 传入审批期限ID,是否可用，审批上限，审批下限
	// 1.启用，2,禁用
	operations += '<a href="javascript:void(0)" onclick="changeLimit('
			+ row.auditLimitId + ',' + row.id + ',' + row.isDisabled + ','
			+ row.floorLimit + ',' + row.upperLimit + ',' + row.proFloorLimit
			+ ',' + row.proUpperLimit + ',' + row.proAdjustBase
			+ ')">修改 &nbsp;&nbsp;</a>';
	return operations;
};
/**
 * 查询函数
 */
function org_query_cha_pro_Info() {
	var orgId = buildValues($('#org_cha_pro').combobox('getValues'));
	var channelId = buildValues($('#org_cha_pro_channelId').combobox('getValues'));
	var productId = buildValues($('#org_cha_pro_productId').combobox('getValues'));
	var auditLimitId = buildValues($('#org_cha_pro_auditLimit').combobox('getValues'));
	var configure = $('#configure').combobox('getValue');
	var isDisabled = $("#org_cha_pro_queryForm #org_cha_pro_isDisabled").combobox('getValue');
	// 查询
	var queryParams = $('#org_cha_pro_datagrid').datagrid('options').queryParams;
	queryParams.orgs = orgId;
	queryParams.channs = channelId;
	queryParams.prods = productId;
	queryParams.limits = auditLimitId;
	queryParams.isDisabled = isDisabled;
	queryParams.configure = configure;
	
	org_cha_pro_FirstPage("#org_cha_pro_datagrid");
	$('#org_cha_pro_datagrid').datagrid('options').queryParams = queryParams;
	$("#org_cha_pro_datagrid").datagrid('reload');
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
function org_cha_pro_FirstPage(ids) {
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
 * 更改产品审批期限
 * 
 * @param auditLimitId
 * @param isDisabled
 */
function changeLimit(auditLimitId, id, isDisabled, floorLimit, upperLimit,
		proFloorLimit, proUpperLimit, proAdjustBase, channelId, productId) {
	// 给修改页面赋值
	// 审批期限ID、审批期限上限、下限
	$("#org_limit_form").find('input[name="productId"]').val(productId);
	$("#org_limit_form").find('input[name="channelId"]').val(channelId);
	$("#org_limit_form").find('input[name="auditLimitId"]').val(auditLimitId);
	$("#org_limit_form").find('input[name="id"]').val(id);
	$("#org_limit_form").find('input[name="floorLimit"]').val(floorLimit);
	$("#org_limit_form").find('input[name="upperLimit"]').val(upperLimit);
	// 将原审批期限上下限进行保存
	$("#org_limit_form").find('input[name="oldFloorLimit"]').val(floorLimit);
	$("#org_limit_form").find('input[name="oldUpperLimit"]').val(upperLimit);
	// 产品上限、下限、调整基数
	$("#org_limit_form").find('input[name="proFloorLimit"]').val(proFloorLimit);
	$("#org_limit_form").find('input[name="proUpperLimit"]').val(proUpperLimit);
	$("#org_limit_form").find('input[name="proAdjustBase"]').val(proAdjustBase);
	// 是否可用
	if (isDisabled == 0) {
		$('#org_limit_form #iState').switchbutton("check");
		$('#org_limit_form #isDisabled').val('0'); 
//		$("#org_limit_form_abled").click();
	} else if (isDisabled == 1) {
		$('#org_limit_form #iState').switchbutton("uncheck");
		$('#org_limit_form #isDisabled').val('1'); 
//		$("#org_limit_form_disabled").click();
	}
	// 修改审批期限弹出框
	$('#orgproductAuditInfo').window({
		modal : true,
		closed : false,
	});
}
// 修改审批期限保存函数
function add_out_pro_Info() {
	// 验证输入数据的正确性 begin---
	var floor = $("#org_limit_form").find('input[name="floorLimit"]').val();
	var upper = $("#org_limit_form").find('input[name="upperLimit"]').val();
	var profloor = $("#org_limit_form").find('input[name="proFloorLimit"]')
			.val();// 产品下限
	var proupper = $("#org_limit_form").find('input[name="proUpperLimit"]')
			.val();// 产品上限
	var proadjust = $("#org_limit_form").find('input[name="proAdjustBase"]')
			.val();// 基数
	var oldfloor = $("#org_limit_form").find('input[name="oldFloorLimit"]')
			.val();
	var oldupper = $("#org_limit_form").find('input[name="oldUpperLimit"]')
			.val();
	if (floor != null && '' != floor && undefined != floor) {// 审批额度下限
		// 获取输入值与原值差额绝对值
		var floorabs = Math.abs(parseInt(oldfloor) - parseInt(floor));
		if (parseInt(floor) < parseInt(profloor)
				|| parseInt(floor) > parseInt(proupper)) {
			$.messager.show({
				title : '提示',
				msg : '审批下限配置请输入' + profloor + "到" + proupper + "之间的数字"
			});
			return;
		}/* else if (floorabs % parseInt(proadjust) != 0) {
			$.messager.show({
				title : '提示',
				msg : '审批下限调整基数必须为' + proadjust + "的整数倍数字"
			});
			return;
		}*/
	} else {
		$.messager.show({
			title : '提示',
			msg : '审批下限不能为空'
		});
		return;
	}
	if (upper != null && '' != upper && undefined != upper) {// 审批额度上限
		var upperabs = Math.abs(parseInt(oldupper) - parseInt(upper));
		if (parseInt(upper) < parseInt(profloor)
				|| parseInt(upper) > parseInt(proupper)) {
			$.messager.show({
				title : '提示',
				msg : '审批上限配置请输入' + profloor + "到" + proupper + "之间的数字"
			});
			return;
		}/* else if (upperabs % parseInt(proadjust) != 0) {
			$.messager.show({
				title : '提示',
				msg : '审批上限调整基数必须为' + proadjust + "的整数倍数字"
			});
			return;
		}*/
	} else {
		$.messager.show({
			title : '提示',
			msg : '审批上限不能为空'
		});
		return;
	}
	if (floor != null && '' != floor && undefined != floor && upper != null
			&& '' != upper && undefined != upper) {
		if (parseInt(upper) < parseInt(floor)) {
			$.messager.show({
				title : '提示',
				msg : '审批下限不能大于审批上限'
			});
			return;
		}
	}
	
	var isDisabled = $('#org_limit_form #isDisabled').val(); 
	//上级配置启用，禁用判断
	if('1' == isDisabled){
		// 验证输入数据的正确性 end
		// 配置修改页面
		$('#orgproductAuditInfo').window('close');
		// 改派确认页面
		$('#orgproductAuditInfoConfirmWindow').window('open');
	} else {
		var id = $("#org_limit_form").find('input[name="id"]').val();
		var channelId = $("#org_limit_form").find('input[name="channelId"]').val();
		var auditLimitId = $("#org_limit_form").find('input[name="auditLimitId"]').val();
		var productId = $("#org_limit_form").find('input[name="productId"]').val();
		$.ajax({
			url : 'outletsProduct/orgProductLimitDisableCheck',
			data : {
				id : id,
				channelId : channelId,
				auditLimitId : auditLimitId,
				productId : productId
			},
			type : "POST",
			success : function(result) {
				if (result.isSuccess) {
					// 验证输入数据的正确性 end
					// 配置修改页面
					$('#orgproductAuditInfo').window('close');
					// 改派确认页面
					$('#orgproductAuditInfoConfirmWindow').window('open');
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
/**
 * 修改审批期限确认保存
 */
function orgproductAuditInfoSave(){
	$.ajax({
		url : 'outletsProduct/updateOrgProductLimit',
		data : $("#org_limit_form").serialize(),
		type : "POST",
		success : function(result) {
			if (result.isSuccess) {
				// 确认页面
				$('#orgproductAuditInfoConfirmWindow').window('close');
				$.messager.show({
					title : '提示',
					msg : '修改成功！'
				});
				$('#orgproductAuditInfo').window('close');
				$("#org_cha_pro_datagrid").datagrid('reload');

			} else {
				parent.$.messager.show({
					title : 'Error',
					msg : "修改失败！"
				});
			}
		}
	});
}
/**
 * 修改审批期限取消保存
 */
function orgproductAuditInfoCancle(){
	// 改派确认页面
	$('#orgproductAuditInfoConfirmWindow').window('close');
	//配置页面
	$('#orgproductAuditInfo').window('open');
}



/**
 * 审批期限修改取消函数
 */
function cancel_out_pro_Info() {
	$('#orgproductAuditInfo').window('close');
}
//防止取消时出现弹框
var busiOrgTreeCheckNum = 1;
/**
 * 网点配置弹出页面函数
 */
function busi_org_cha_pro_Info() {
	// 左边树
	channelHis={};
	$('#busi_orgTree')
			.tree(
					{
						url : 'outletsProduct/findAllDeptsTree',
						checkbox : true,
						onlyLeafCheck : true,
						onCheck : function(node) {
							var id=node.id
							var curHisChecks=channelHis;
							var hisChecks={};
							if(node.checked){
								hisChecks=recordChannel(node.checked,node.id,"busi_channelProTree");
								channelHis[node.id]=[];
								unCheckedHis(hisChecks,'busi_channelProTree',node.id);
								checkedHis(channelHis,'busi_channelProTree',node.id);
								return;
							}
							if (node.deep == "5") {
								$.ajax({
									url : 'outletsProduct/findChannelProTree?orgId='+ node.id,
									method:'post',
									success:function(data){
										$('#busi_channelProTree').tree('loadData',data);
										saveHisRecord(node.id,"busi_channelProTree");
										checkedHis(curHisChecks,'busi_channelProTree',node.id);
									}
									
								});
							}
						},
						// 去掉单选样式
						onBeforeSelect : function(node) {
							return false;
						}
					});
	// 右边树
	$('#busi_channelProTree').tree({
		url : 'outletsProduct/findChannelProTree',
		checkbox : true,
		onLoadSuccess : function() {
		}
	});

	// 渠道产品配置弹出框
	$('#busi_orgChannelProductInfo').window({
		modal : true,
		closed : false,
	});
}
/**
 * 网点产品优惠费率配置弹出页面函数
 */
function busi_org_cha_pro_rate_Info() {
	// 左边树
	$('#busi_rate_orgTree')
			.tree(
					{
						url : 'outletsProduct/findAllDeptsTree',
						checkbox : true,
						onlyLeafCheck : true,
						onCheck : function(node) {
							var nodes = $('#busi_rate_orgTree').tree('getChecked');
							if (nodes.length > busiOrgTreeCheckNum) {
								$.messager.show({
									title : '提示',
									msg : '只能选择一个门店营业部！'
								});
							} else if (nodes.length == 1
									&& nodes[0].deep == "5") {
								$.ajax({
									url : 'outletsProduct/findChannelProRateTree',
									data : {
										orgId : nodes[0].id
									},
									type : "POST",
									success : function(result) {
										if(result){
											// 右边树
											$('#busi_rate_channelProTree').tree({
												data : result,
												checkbox : true,
												onLoadSuccess : function() {
												}
											});
										} else {
											$('#busi_rate_channelProTree').tree({
												url : '',
												data: [],
												checkbox : true,
												onLoadSuccess : function() {
												}
											});
										}
									}
								});
								
							} else {
								$('#busi_rate_channelProTree').tree({
									url : '',
									data: [],
									checkbox : true,
									onLoadSuccess : function() {
									}
								});
							}
							if( nodes.length<1){
								busiOrgTreeCheckNum = 1
							} else {
								busiOrgTreeCheckNum = nodes.length;
							}
						},
						// 去掉单选样式
						onBeforeSelect : function(node) {
							return false;
						}
					});
	// 右边树
	$('#busi_rate_channelProTree').tree({
		url : '',
		data: [],
		checkbox : true,
		onLoadSuccess : function() {
		}
	});

	// 渠道产品配置弹出框
	$('#busi_rate_orgChannelProductInfo').window({
		modal : true,
		closed : false,
	});
}


/**
 * 网点配置保存函数
 */
function busi_add_cha_pro_rate_Info() {
	// 门店节点，只能选择一个
	var orgNodes = $('#busi_rate_orgTree').tree('getChecked');
	if (orgNodes.length < 1) {
		$.messager.show({
			title : '提示',
			msg : '请选择门店营业部！'
		});
		return false;
	}
	if (orgNodes.length == 1 && orgNodes[0].deep != 5) {
		$.messager.show({
			title : '提示',
			msg : '请选择门店营业部！'
		});
		return false;
	}
	if (orgNodes.length > 1) {
		$.messager.show({
			title : '提示',
			msg : '只能选择一个门店营业部！'
		});
		return false;
	}
	//配置页面
	$('#busi_rate_orgChannelProductInfo').window('close');
	// 确认页面
	$('#busi_rate_orgChannelProductInfoConfirmWindow').window('open');
}


/**
 * 网点配置保存函数
 */
function busi_add_cha_pro_Info() {
	// 门店节点，只能选择一个
	var orgNodes = $('#busi_orgTree').tree('getChecked');
	if (orgNodes.length < 1) {
		$.messager.show({
			title : '提示',
			msg : '请选择门店营业部！'
		});
		return false;
	}
	var isTrue=true;
	for(var i=0;i<orgNodes.length;i++){
		if(orgNodes[i].deep != 5){
			isTrue=false;
		}
	}
	if (!isTrue) {
		$.messager.show({
			title : '提示',
			msg : '请选择门店营业部！'
		});
		return false;
	}
	// 渠道产品期限
	/*var channelProNodes = $('#busi_channelProTree').tree('getChecked');
	if (channelProNodes.length < 1) {
		$.messager.show({
			title : '提示',
			msg : '请选择渠道产品期限！'
		});
		return false;
	}*/
	// channelLimitStr
	
	//配置页面
	$('#busi_orgChannelProductInfo').window('close');
	// 确认页面
	$('#busi_orgChannelProductInfoConfirmWindow').window('open');
}
/**
 * 网点-录单产品确认保存
 */
function busi_orgChannelProductInfoSave() {
	var orgNodes = $('#busi_orgTree').tree('getChecked');
	var channelIds =[];
	var orgIds=[];
	var k=0;
	var j=0;
	var channelProNodes = $('#busi_channelProTree').tree('getChecked');
	for (var i = 0; i < channelProNodes.length; i++) {
		if (channelProNodes[i].deep == "4") {
			channelIds[j] =channelProNodes[i].id;
			j++;
		}
	}
	for (var i = 0; i < orgNodes.length; i++) {
		if (orgNodes[i].deep == "5") {
			orgIds[k] =orgNodes[i].id;
			k++;
		}
	}

	$.ajax({
		url : 'outletsProduct/saveOrgChannelLimit',
		data : {
			orgid : orgIds,
			channelid : channelIds,
			standard:"0"
		},
		type : "POST",
		success : function(result) {
			if (result.isSuccess) {
				// 确认页面
				$('#busi_orgChannelProductInfoConfirmWindow').window('close');
				$.messager.show({
					title : '提示',
					msg : '配置成功！'
				});
				$('#busi_orgChannelProductInfo').window('close');
				$("#org_cha_pro_datagrid").datagrid('reload');

			} else {
				parent.$.messager.show({
					title : 'Error',
					msg : "配置失败！"
				});
			}
		}
	});
}

/**
 * 网点-录单产品优惠费率确认保存
 */
function busi_rate_orgChannelProductInfoSave() {
	var orgNodes = $('#busi_rate_orgTree').tree('getChecked');
	var channelLimitStr = "";
	var channelProNodes = $('#busi_rate_channelProTree').tree('getChecked');
	for (var i = 0; i < channelProNodes.length; i++) {
		if (channelProNodes[i].deep == "4") {
			channelLimitStr = channelLimitStr + channelProNodes[i].id + ",";
		}
	}
	$.ajax({
		url : 'outletsProduct/saveRateOrgChannelLimit',
		data : {
			orgId : orgNodes[0].id,
			channelLimitStr : channelLimitStr
		},
		type : "POST",
		success : function(result) {
			if (result.isSuccess) {
				// 确认页面
				$('#busi_rate_orgChannelProductInfoConfirmWindow').window('close');
				$.messager.show({
					title : '提示',
					msg : '配置成功！'
				});
				$('#busi_rate_orgChannelProductInfo').window('close');
				$("#org_cha_pro_datagrid").datagrid('reload');

			} else {
				parent.$.messager.show({
					title : 'Error',
					msg : "配置失败！"
				});
			}
		}
	});
}


/**
 *  网点-产品配置确认取消
 */
function busi_orgChannelProductInfoCancle() {
	//配置页面
	$('#busi_orgChannelProductInfo').window('open');
	// 确认页面
	$('#busi_orgChannelProductInfoConfirmWindow').window('close');
}


/**
 *  网点-产品优惠费率配置确认取消
 */
function busi_rate_orgChannelProductInfoCancle() {
	//配置页面
	$('#busi_rate_orgChannelProductInfo').window('open');
	// 确认页面
	$('#busi_rate_orgChannelProductInfoConfirmWindow').window('close');
}

/**
 * 网点-录单产品配置返回函数
 */
function busi_cancel_cha_pro_rate_Info() {
	$('#busi_rate_orgChannelProductInfo').window('close');
}

/**
 * 网点-录单产品配置返回函数
 */
function busi_cancel_cha_pro_Info() {
	$('#busi_orgChannelProductInfo').window('close');
}
//防止取消时出现弹框
var busiOrgProductTreeNum = 1;
/**
 * 产品配置弹出页面函数
 */
function busi_pro_org_cha_Info() {
	// 左边树
	channelHis={};
	$('#busi_org_productTree')
	.tree(
			{
				url : 'outletsProduct/findChannelProTree',
				checkbox : true,
				onlyLeafCheck : true,
				onCheck : function(node) {
					var id=node.id
					var curHisChecks=channelHis;
					var hisChecks={};
					if(node.checked){
						hisChecks=recordChannel(node.checked,node.id,"busi_orgProTree");
						channelHis[node.id]=[];
						unCheckedHis(hisChecks,'busi_orgProTree',node.id);
						checkedHis(channelHis,'busi_orgProTree',node.id);
						return;
					}
					
					if (node.deep == "4") {
						$.ajax({
							url : 'outletsProduct/findAllDeptsTree?channelLimitStr='+ node.id,
							type:'post',
							success:function(data){
								$('#busi_orgProTree').tree('loadData',data);
								saveHisRecord(node.id,"busi_orgProTree");
								checkedHis(curHisChecks,'busi_orgProTree',node.id);
							}
							
						});
					}
			
					/*if( nodes.length<1){
						busiOrgProductTreeNum = 1
					} else {
						busiOrgProductTreeNum = nodes.length;
					}*/
					
				},
				// 去掉单选样式
				onBeforeSelect : function(node) {
					return false;
				}
			});
	// 右边树
	$('#busi_orgProTree').tree({
		url : 'outletsProduct/findAllDeptsTree',
		checkbox : true
	});
	// 渠道产品配置弹出框
	$('#busi_orgProductInfo').window({
		modal : true,
		closed : false,
	});
}
/**
 * 产品配置弹出页面函数(优惠费率)
 */
function busi_pro_org_cha_rate_Info() {
	// 左边树
	$('#busi_rate_org_productTree')
			.tree(
					{
						url : 'outletsProduct/findChannelProRateTree',
						checkbox : true,
						onlyLeafCheck : true,
						onCheck : function(node) {
							var nodes = $('#busi_rate_org_productTree').tree(
									'getChecked');
							if (nodes.length > busiOrgProductTreeNum) {
								$.messager.show({
									title : '提示',
									msg : '只能选择一个产品期限！'
								});

								// 取消选中
								// $('#busi_orgTree').tree('uncheck',
								// node.target);
							} else if (nodes.length == 1
									&& nodes[0].deep == "4") {
								$.ajax({
									url : 'outletsProduct/findAllDeptsRateTree',
									data : {
										channelLimitStr : nodes[0].id
									},
									type : "POST",
									success : function(result) {
										if(result){
											// 右边树
											$('#busi_rate_orgProTree').tree({
												data : result,
												checkbox : true,
												onLoadSuccess : function() {
												}
											});
										} else {
											$('#busi_rate_orgProTree').tree({
												url : '',
												data: [],
												checkbox : true,
												onLoadSuccess : function() {
												}
											});
										}
									}
								});
							} else {
								$('#busi_rate_orgProTree').tree({
									url : '',
									data: [],
									checkbox : true,
									onLoadSuccess : function() {
									}
								});
							}
							if( nodes.length<1){
								busiOrgProductTreeNum = 1
							} else {
								busiOrgProductTreeNum = nodes.length;
							}
							
						},
						// 去掉单选样式
						onBeforeSelect : function(node) {
							return false;
						}
					});
	// 右边树
	$('#busi_rate_orgProTree').tree({
		url : '',
		data: [],
		checkbox : true,
		onLoadSuccess : function() {
		}
	});
	// 渠道产品配置弹出框
	$('#busi_rate_orgProductInfo').window({
		modal : true,
		closed : false,
	});
}
/**
 * 产品-网点录单配置保存
 */
function busi_org_add_cha_pro_Info() {
	// 产品期限节点，只能选择一个
	var limitNodes = $('#busi_org_productTree').tree('getChecked');
	if (limitNodes.length < 1) {
		$.messager.show({
			title : '提示',
			msg : '请选择产品期限！'
		});
		return false;
	}
	/*if (limitNodes.length == 1 && limitNodes[0].deep != "4") {
		$.messager.show({
			title : '提示',
			msg : '请选择产品期限！'
		});
		return false;
	}*/
/*	if (limitNodes.length > 1) {
		$.messager.show({
			title : '提示',
			msg : '只能选择一个产品期限！'
		});
		return false;
	}*/
	//配置页面
	$('#busi_orgProductInfo').window('close');
	// 确认页面
	$('#busi_orgProductInfoConfirmWindow').window('open');
	
}
/**
 * 产品-网点录单配置保存(优惠费率)
 */
function busi_org_add_cha_pro_rate_Info() {
	// 产品期限节点，只能选择一个
	var limitNodes = $('#busi_rate_org_productTree').tree('getChecked');
	if (limitNodes.length < 1) {
		$.messager.show({
			title : '提示',
			msg : '请选择产品期限！'
		});
		return false;
	}
	if (limitNodes.length == 1 && limitNodes[0].deep != "4") {
		$.messager.show({
			title : '提示',
			msg : '请选择产品期限！'
		});
		return false;
	}
	if (limitNodes.length > 1) {
		$.messager.show({
			title : '提示',
			msg : '只能选择一个产品期限！'
		});
		return false;
	}
	//配置页面
	$('#busi_rate_orgProductInfo').window('close');
	// 确认页面
	$('#busi_rate_orgProductInfoConfirmWindow').window('open');
	
}
/**
 * 产品-网点确认保存
 */
function busi_orgProductInfoSave() {
	var limitNodes = $('#busi_org_productTree').tree('getChecked');
	var orgNodes = $('#busi_orgProTree').tree('getChecked');
	var orgIds=[];
	var channelIds=[];
	var k=0;
	var j=0;
	for (var i = 0; i < orgNodes.length; i++) {
		if (orgNodes[i].deep == "5") {
			orgIds[j] =orgNodes[i].id;
			j++;
		}
	}
	
	for (var i = 0; i < limitNodes.length; i++) {
			channelIds[k] =limitNodes[i].id;
			k++;
	}
	$.ajax({
		url : 'outletsProduct/saveOrgChannelLimit',
		data : {
			orgid : orgIds,
			channelid : channelIds,
			standard:"1"
		},
		type : "POST",
		success : function(result) {
			if (result.isSuccess) {
				// 确认页面
				$('#busi_orgProductInfoConfirmWindow').window('close');
				$.messager.show({
					title : '提示',
					msg : '配置成功！'
				});
				$('#busi_orgProductInfo').window('close');
				$("#org_cha_pro_datagrid").datagrid('reload');
				
			} else {
				parent.$.messager.show({
					title : 'Error',
					msg : "配置失败！"
				});
			}
		}
	});
}
/**
 * 产品-网点确认保存(优惠费率)
 */
function busi_rate_orgProductInfoSave() {
	// 产品期限节点，只能选择一个
	var limitNodes = $('#busi_rate_org_productTree').tree('getChecked');
	// 门店树
	var orgNodes = $('#busi_rate_orgProTree').tree('getChecked');
	var orgIdStr = "";
	for (var i = 0; i < orgNodes.length; i++) {
		if (orgNodes[i].deep == "5") {
			orgIdStr = orgIdStr + orgNodes[i].id + ",";
		}
	}
	$.ajax({
		url : 'outletsProduct/saveRateOrgChannelLimit',
		data : {
			channelLimitStr : limitNodes[0].id,
			orgIdStr : orgIdStr
		},
		type : "POST",
		success : function(result) {
			if (result.isSuccess) {
				// 确认页面
				$('#busi_rate_orgProductInfoConfirmWindow').window('close');
				$.messager.show({
					title : '提示',
					msg : '配置成功！'
				});
				$('#busi_rate_orgProductInfo').window('close');
				$("#org_cha_pro_datagrid").datagrid('reload');

			} else {
				parent.$.messager.show({
					title : 'Error',
					msg : "配置失败！"
				});
			}
		}
	});
}
/**
 * 产品-网点确认取消
 */
function busi_orgProductInfoCancle() {
	//配置页面
	$('#busi_orgProductInfo').window('open');
	// 确认页面
	$('#busi_orgProductInfoConfirmWindow').window('close');
}
/**
 * 产品-网点确认取消(优惠费率)
 */
function busi_rate_orgProductInfoCancle() {
	//配置页面
	$('#busi_rate_orgProductInfo').window('open');
	// 确认页面
	$('#busi_rate_orgProductInfoConfirmWindow').window('close');
}

/**
 * 网点配置返回函数
 */
function busi_org_cancel_cha_pro_Info() {
	$('#busi_orgProductInfo').window('close');
}

/**
 * 网点配置返回函数
 */
function busi_org_cancel_cha_pro_rate_Info() {
	$('#busi_rate_orgProductInfo').window('close');
}

/**
 * 记录渠道历史
 */
function recordChannel(isCheck,id,treeId){
	if(isCheck){
		var his=channelHis[id];
		channelHis[id]=[];
		return his;
	}
	return channelHis;
	
}

function checkedHis(hisRecords,treeId,id){
	if(hisRecords){
		var treeObj=$("#"+treeId);
		for(var obj in hisRecords){
			var records=hisRecords[obj];
			for(var i=0;i<records.length;i++){
				var node=treeObj.tree('find',records[i].id);
				treeObj.tree('check',node.target);
			}
		}
		
	}
}

function saveHisRecord(id,treeId){
	var nodes = $('#'+treeId).tree('getChecked');
	channelHis[id]=nodes;
}

function unCheckedHis(hisRecords,treeId,id){
	if(hisRecords){
		var treeObj=$("#"+treeId);
			for(var i=0;i<hisRecords.length;i++){
				var node=treeObj.tree('find', hisRecords[i].id);
				treeObj.tree('uncheck',node.target);
			}
	}
}
