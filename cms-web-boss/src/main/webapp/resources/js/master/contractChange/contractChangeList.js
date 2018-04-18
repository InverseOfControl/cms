$(function() {

	// 初始化数据列表
	initDatagrid();

	// 渠道名称下拉框
	$('#channelName').combobox({
		url : 'channeProduct/findChannel',
		valueField : 'code',
		textField : 'name',
		filter : function(q, row) {
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) > -1;// 将从头位置匹配改为任意匹配
		},
		onHidePanel : function() {
			var valueField = $(this).combobox("options").valueField;
			var val = $(this).combobox("getValue"); // 当前combobox的值
			var allData = $(this).combobox("getData"); // 获取combobox所有数据
			var result = true; // 为true说明输入的值在下拉框数据中不存在
			for (var i = 0; i < allData.length; i++) {
				if (val == allData[i][valueField]) {
					result = false;
				}
			}
			if (result) {
				$(this).combobox("clear");
			}

		}
	});

	// 产品名称下拉框
	$('#productName').combobox({
		url : 'channeProduct/findProduct',
		valueField : 'code',
		textField : 'name',
		filter : function(q, row) {
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) > -1;// 将从头位置匹配改为任意匹配
		},
		onHidePanel : function() {
			var valueField = $(this).combobox("options").valueField;
			var val = $(this).combobox("getValue"); // 当前combobox的值
			var allData = $(this).combobox("getData"); // 获取combobox所有数据
			var result = true; // 为true说明输入的值在下拉框数据中不存在
			for (var i = 0; i < allData.length; i++) {
				if (val == allData[i][valueField]) {
					result = false;
				}
			}
			if (result) {
				$(this).combobox("clear");
			}

		}
	});

	// 下拉框:网点名称
	$('#contractBranch').combobox({
		url : 'contractChange/findDataOrgIdsByAccount',
		valueField : 'id',
		textField : 'name',
		filter : function(q, row) {
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) > -1;// 将从头位置匹配改为任意匹配
		},
		onHidePanel : function() {
			var valueField = $(this).combobox("options").valueField;
			var val = $(this).combobox("getValue"); // 当前combobox的值
			var allData = $(this).combobox("getData"); // 获取combobox所有数据
			var result = true; // 为true说明输入的值在下拉框数据中不存在
			for (var i = 0; i < allData.length; i++) {
				if (val == allData[i][valueField]) {
					result = false;
				}
			}
			if (result) {
				$(this).combobox("clear");
			}

		},
		onSelect : function(record) {
			// 依据选中加载签约客服人员
			var url = 'contractChange/findEmployeeByDeptAndRole';
			$.ajax({
				type : "POST",
				url : url,
				data : {
					'roleCodes' : "customerService",
					'orgId' : record.id
				},
				dataType : 'json',
				async : false, // 设置同步方式
				cache : false,
				success : function(result) {
					// 签约客服下拉框
					$('#signName').combobox({
						data : result
					});
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
	// 签约客服下拉框
	$('#signName').combobox({
		// url : 'channeProduct/findProduct',
		valueField : 'usercode',
		textField : 'name',
		filter : function(q, row) {
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) > -1;// 将从头位置匹配改为任意匹配
		},
		onHidePanel : function() {
			var valueField = $(this).combobox("options").valueField;
			var val = $(this).combobox("getValue"); // 当前combobox的值
			var allData = $(this).combobox("getData"); // 获取combobox所有数据
			var result = true; // 为true说明输入的值在下拉框数据中不存在
			for (var i = 0; i < allData.length; i++) {
				if (val == allData[i][valueField]) {
					result = false;
				}
			}
			if (result) {
				$(this).combobox("clear");
			}
		}
	});
	// 业务环节
	$('#businessSegment').combobox({
		//获取可见业务环节
		url : 'contractChange/getBusinessSegment',
		valueField : 'code',
		textField : 'nameCN',
		filter : function(q, row) {
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) > -1;// 将从头位置匹配改为任意匹配
		},
		onHidePanel : function() {
			var valueField = $(this).combobox("options").valueField;
			var val = $(this).combobox("getValue"); // 当前combobox的值
			var allData = $(this).combobox("getData"); // 获取combobox所有数据
			var result = true; // 为true说明输入的值在下拉框数据中不存在
			for (var i = 0; i < allData.length; i++) {
				if (val == allData[i][valueField]) {
					result = false;
				}
			}
			if (result) {
				$(this).combobox("clear");
			}
		}
	});
	
	
	/*//弹框初始化
	$('#panelContractChange').window({
		modal : true,
		closed : true
	});
	$('#panelContractChangeConfirm').window({
		modal : true,
		closed : true
	});
	$('#panelContractChangeCheck').window({
		modal : true,
		closed : true
	});
	$('#panelContractChangeFailed').window({
		modal : true,
		closed : true
	});*/
});

$(window).keydown(function(event) {
    if(event.keyCode == 13) {
    	contract_search();
    }
});

/**
 * 初始化数据列表函数
 */
function initDatagrid() {
	$("#contractChangeDatagrid").datagrid({
		onLoadSuccess : function(data) {
			if (data.total == 0) {
				$.messager.show({
					title : '结果',
					msg : '没查到符合条件的数据！',
					showType : 'slide',
				});
			}
			;
		},
		idField : 'id',//表示列，解决翻页后勾选项不见
		url : 'contractChange/listPage',
		striped : true,
		singleSelect : false,
		rownumbers : true,
		pagination : true,
		checkOnSelect : false,
		selectOnCheck : false,
		fitColumns : true,
		autoRowHeight : true,
		pageList : [ 10, 20, 30, 40, 50 ],
		scrollbarSize : 0,
		columns : [ [ {
			field : 'id',
			checkbox : true,
			width : 30
		}, {
			field : 'identificationCase',
			title : '案件标识',
			align : 'center',
			width : 120,
			formatter : function(value,rowData,rowIndex){
				var html = '';
				if(rowData.ifPri && rowData.ifPri == '1'){//加急
					html += '<img title="加急" class="easyui-tooltip" src="resources/images/identificationCase/urgent.png" title="加急"></img>';
				} 
				if(rowData.appInputFlag && rowData.appInputFlag == 'app_input'){//app进件
					html += '<img title="app进件" class="easyui-tooltip" src="resources/images/identificationCase/appPart.png" title="app进件"></img>';
				}  
				if(rowData.ifPreferentialUser && rowData.ifPreferentialUser == 'Y'){//费率优惠客户
					html += '<img name="img_reLoan" title="费率优惠客户" class="easyui-tooltip"  src="resources/images/identificationCase/reLoan.jpg"></img>';
				}
				/*if(rowData.ifSuspectCheat && rowData.ifSuspectCheat == 'Y'){//疑似欺诈
					html += '<img src="resources/images/identificationCase/fraudRules.png"></img>';
				}*/
				return html;
			}
		}, {
			field : 'loanNo',
			title : '借款编号',
			width : 220
		}, {
			field : 'personName',
			title : '客户姓名',
			width : 220
		}, {
			field : 'channelName',
			title : '渠道名称',
			width : 220
		}, {
			field : 'productName',
			title : '产品名称',
			width : 220,
		}, {
			field : 'owningBranch',
			title : '录单网点',
			width : 220
		}, {
			field : 'contractBranch',
			title : '签约网点',
			width : 220
		}, {
			field : 'signName',
			title : '签约客服',
			width : 220
		}, {
			field : 'productCode',
			title : '产品Code',
			width : 220,
			hidden : true
		}, {
			field : 'owningBranchId',
			title : '录单网点ID',
			width : 220,
			hidden : true
		}, {
			field : 'contractBranchId',
			title : '签约网点Id',
			width : 220,
			hidden : true
		} ] ]
	});
}

/**
 * 查询函数
 */
function contract_search() {
	//清除所有勾选的行
	$('#contractChangeDatagrid').datagrid('clearChecked');
	var queryParams = $('#contractChangeDatagrid').datagrid('options').queryParams;
	queryParams = {};
	queryParams.loanNo = $('#loanNo').val().trim();
	queryParams.personName = $('#personName').val().trim();
	queryParams.channelCode = $('#channelName').combobox('getValue');
	queryParams.productCode = $('#productName').combobox('getValue');
	queryParams.contractBranchId = $('#contractBranch').combobox('getValue');
	queryParams.signCode = $('#signName').combobox('getValue');
	queryParams.businessSegment = $('#businessSegment').combobox('getValue');
	
	setFirstPage("#contractChangeDatagrid");
	$('#contractChangeDatagrid').datagrid('options').queryParams = queryParams;
	$("#contractChangeDatagrid").datagrid('reload');
}

/**
 * 设置查询分页信息
 * 
 * @param ids
 */
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

/**
 * 改派函数
 */
function contract_change() {

	var rows = $('#contractChangeDatagrid').datagrid('getChecked');

	if (rows.length == 0) {
		$.messager.show({
			title : '提示',
			msg : '请至少选择一条数据'
		});
		return;
	}
	var productCodes = [];
	for ( var i in rows) {
		productCodes.push(rows[i].productCode);
	}
	
	// 默认显示当前签约网点，可选范围视产品而定（满足“网点签约产品”中的配置）----------------------需求取消
	// 批量改派：可批量选中要改派的即可，弹框中的“签约网点”下拉列表取满足所选借款的签约条件网点的交集------需求取消
//	var url = 'contractChange/findOrgByProductCodeListIntersect';
	var url = 'contractChange/findDataOrgIdsByAccount';
	$.ajax({
		type : "POST",
		url : url,
		data : {
//			'productCodes' : productCodes.join(",")
		},
		dataType : 'json',
		async : false, // 设置同步方式
		cache : false,
		success : function(resultData) {
			var result = [];
			//可签约网点不包含直通车营业部
			for ( var i in resultData) {
				var organization = resultData[i];
//				直通车判断  营业部类型(0:普通, 1直通车)
				if(organization.saleDeptType && 1==organization.saleDeptType){
					
				} else {
					result.push(organization);
				}
			}
			
			// 下拉框:签约网点
			$('#contract_Branch').combobox({
				// url : 'outletsProduct/findBusinessProduct',
				valueField : 'id',
				textField : 'name',
				data : result,
				filter : function(q, row) {
					var opts = $(this).combobox('options');
					return row[opts.textField].indexOf(q) > -1;// 将从头位置匹配改为任意匹配
				},
				onHidePanel : function() {
					var valueField = $(this).combobox("options").valueField;
					var val = $(this).combobox("getValue"); // 当前combobox的值
					var allData = $(this).combobox("getData"); // 获取combobox所有数据
					var result = true; // 为true说明输入的值在下拉框数据中不存在
					for (var i = 0; i < allData.length; i++) {
						if (val == allData[i][valueField]) {
							result = false;
						}
					}
					if (result) {
						$(this).combobox("clear");
					}

				},
				onSelect : function(record) {
					var rows = $('#contractChangeDatagrid').datagrid('getChecked');
					var signCodeRejects = [];//需要剔除人员
					for ( var i in rows) {
						signCodeRejects.push(rows[i].signCode);
					}
					// 依据选中加载签约客服人员
					//已勾选人剔除
					var url = 'contractChange/findEmployeeBy';
					$.ajax({
						type : "POST",
						url : url,
						data : {
							'roleCodes' : "customerService",
							'signCodeRejects' : signCodeRejects.join(","),
							'orgId' : record.id
						},
						dataType : 'json',
						async : false, // 设置同步方式
						cache : false,
						success : function(result) {
							// 签约客服下拉框
							$('#sign_Name').combobox({
								data : result
							});
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
			// 签约客服下拉列表
			$('#sign_Name').combobox({
				// url : 'channeProduct/findProduct',
				valueField : 'usercode',
				textField : 'name',
				data : {},
				filter : function(q, row) {
					var opts = $(this).combobox('options');
					return row[opts.textField].indexOf(q) > -1;// 将从头位置匹配改为任意匹配
				},
				onHidePanel : function() {
					var valueField = $(this).combobox("options").valueField;
					var val = $(this).combobox("getValue"); // 当前combobox的值
					var allData = $(this).combobox("getData"); // 获取combobox所有数据
					var result = true; // 为true说明输入的值在下拉框数据中不存在
					for (var i = 0; i < allData.length; i++) {
						if (val == allData[i][valueField]) {
							result = false;
						}
					}
					if (result) {
						$(this).combobox("clear");
					}

				}
			});
			// 改派弹出页面
			$('#panelContractChange').window('open');
			
			// 清空form表单数据
			$('#panelContractChange').form('clear');
		},
		error : function(data) {
			$.messager.show({
				title : 'warning',
				msg : data.responseText
			});
		}
	});

}

// 改派确定
function changeContract() {
	// 必填校验
	/*
	 * var boo = contractChangeExam('#changeContractForm'); if (!boo) return;
	 */
	
	// 获取并验证签约网点，必填
	var contractBranchId = $('#contract_Branch').combobox("getValue");
	var contractBranch = $('#contract_Branch').combobox("getText");
	if (!contractBranchId) {
		$.messager.show({
			title : '提示',
			msg : '请选择签约网店'
		});
		return false;
	}
	//网点下可改派人员
	var peopleList = $('#sign_Name').combobox("getData");
	if(!peopleList || peopleList.length == 0){
		$.messager.show({
			title : '提示',
			msg : '网点下无可改派人员'
		});
		return false;
	}
	
	
	// 校验产品限制时，以录单营业网点的产品配置为准。此时会提示:当前借款对应的录单网点产品配置失效，确定继续吗？
	var rows = $('#contractChangeDatagrid').datagrid('getChecked');
	var branchProductRelevanceList = [];
	for ( var i in rows) {
		if(rows[i].owningBranchId && rows[i].productCode && rows[i].loanNo){
			var branchProductRelevances = '';
			branchProductRelevances += rows[i].owningBranchId + ',' + rows[i].productCode + ',' + rows[i].loanNo;
			branchProductRelevanceList.push(branchProductRelevances);
		}
	}
	//
	var checkUrl = 'contractChange/branchProductRelevanceCheck';
	$.ajax({
		type : "POST",
		url : checkUrl,
		data : {
			'branchProductRelevances' : branchProductRelevanceList.join(';')
		},
		dataType : 'json',
		async : false, // 设置同步方式
		cache : false,
		success : function(result) {
			$.messager.progress('close');
			if (result.isSuccess) {
				$('#panelContractChange').window('close');
				// 改派确认弹出页面
				$('#panelContractChangeConfirm').window("open")
				

			} else if(result.isFailed){
				//配置失效，确认页面
				var loanNoArr = [];
				if(result.errorVoList){
					var errorVoList = result.errorVoList;
					for ( var index in errorVoList) {
						loanNoArr.push(errorVoList[index].loanNo);
					}
				}
				
				
				$('#panelContractChange').window('close');
				$('#panelContractChangeCheckMessage').html(loanNoArr.join('、')+'申请录单网点产品配置失效！<br/>若跨门店改派，合同签约流程将重新开始！是否继续？');
				$('#panelContractChangeCheck').window("open");
				
			}
		},
		error : function(data) {
			$.messager.show({
				title : 'warning',
				msg : data.responseText
			});
		}
	});
	return;

}
// 改派二次确认
function changeContractConfirm() {
	var rows = $('#contractChangeDatagrid').datagrid('getChecked');
	var loanNos = [];
	var signCodeRejects = [];
	for ( var i in rows) {
		loanNos.push(rows[i].loanNo);
		if(rows[i].signCode && rows[i].signCode != ""){
			signCodeRejects.push(rows[i].signCode);
		}
	}

	// 获取签约客服，可为空
	var signCode = $('#sign_Name').combobox("getValue");
	var signName = $('#sign_Name').combobox("getText");
	var contractBranchId = $('#contract_Branch').combobox("getValue");
	var contractBranch = $('#contract_Branch').combobox("getText");
	var signCodeRejectsStr = signCodeRejects.join(',');
	if(!signCodeRejectsStr){
		signCodeRejectsStr = "";
	} 
		
	var url = 'contractChange/changeContract';
	$.ajax({
		type : "POST",
		url : url,
		data : {
			'loanNos' : loanNos.join(','),
			'contractBranchId' : contractBranchId,
			'contractBranch' : contractBranch,
			'signCode' : signCode,
			'signName' : signName,
			'signCodeRejects' : signCodeRejectsStr
		},
		dataType : 'json',
		async : false, // 设置同步方式
		cache : false,
		success : function(result) {
			$.messager.progress('close');
			if (result.isSuccess) {
				$.messager.show({
					title : '提示',
					msg : '改派成功！'
				});
				$('#panelContractChangeConfirm').window('close');
				$('#panelContractChangeCheck').window('close');
				$('#panelContractChangeFailed').window('close');
				$('#searchBt').trigger('click');
			} else if(result.isFailed){
				//改派失效，确认页面
				var loanNoArr = [];
				if(result.isFailedLoanNoList){
					var failedLoanNoList = result.isFailedLoanNoList;
					for ( var index in failedLoanNoList) {
						loanNoArr.push(failedLoanNoList[index]);
					}
				}
				
				
				$('#panelContractChangeConfirm').window('close');
				$('#panelContractChangeFailedMessage').html(loanNoArr.join('、')+'申请改派失败');
				$('#panelContractChangeFailed').window("open");
				
			}else {
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

// 改派网点，签约人员选择关闭弹窗按钮
function closeWindow() {
	$('#panelContractChange').window('close');
//	$('#searchBt').trigger('click');
}
// 最后确认，关闭弹窗按钮
function closeConfirmWindow() {
	$('#panelContractChangeConfirm').window('close');
	$('#panelContractChange').window('open');
	
}
// 网点-产品配置失效关闭弹窗按钮
function closeCheckWindow() {
	$('#panelContractChangeCheck').window('close');
	$('#panelContractChange').window('open');
}
// 签约失败关闭弹窗按钮
function closeFailedWindow() {
	$('#panelContractChangeFailed').window('close');
	$('#panelContractChangeCheck').window('close');
	$('#searchBt').trigger('click');
}
