$(function() {
	initDatagrid();

	// 查询按钮
	$('#searchLinksBt').bind('click', search);
	// 获取下拉框
	$('#operation_module').combobox({
		url : 'reasonReLinks/listByModule',
		valueField : 'code',
		textField : 'nameCN',
		queryParams: {'codeType':"operationModule"},
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
	$('#reason_type').combobox({
		url : 'reasonReLinks/listByModule',
		valueField : 'code',
		textField : 'nameCN',
		queryParams: {'codeType':"operationType"},
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

});

// 加载表头
function load_Table() {// 加载本地表头
	$
	.ajax({
		url : 'reasonReLinks/listByModule',
		data: {'codeType':"operationModule"},
		async : false, // 注意此处需要同步，因为先绑定表头，才能绑定数据
		type : "POST",
		dataType : "json",
		success : function(jsonObj) {
			var columnsAll = new Array();
			// 如果返回的数据不为空,则添加遍历该数据集合
			if (jsonObj.length > 0) {
				columnsAll.push({
					field : 'reason',
					title : '原因',
					formatter : formatOperations,
					"width" : 200
				}, {
					field : 'code',
					title : '原因编码',
					width : 200,
				}, {
					field : 'operationType',
					title : '原因类型',
					width : 200,
				},{
					field:'firstReason',
					title : '一级原因',
					width : 200,
				})
				for (var i = 0; i < jsonObj.length; i++) {
					// 把返回的数据封装到一个对象中
					var col = {}
					col['title'] = jsonObj[i].nameCN;
					col['field'] = jsonObj[i].code.replace(
							/(^\s*)|(\s*$)/g, "");
					col['width'] = 100;
					col['align'] = 'center'
						// 把这个对象添加到列集合中
						columnsAll.push(col);
				}
				$("#new_reasonReLinksDatagrid")
				.datagrid(
						{
							url : 'reasonReLinks/listPage',
							fitColumns : false,
							border : false,
							singleSelect : true,
							pagination : true,
							striped : true,
							rownumbers : true,
							fit : true,
							columns : [ columnsAll ],
							onLoadSuccess : function(data) {
								var array = new Array();
								var dataRole = eval(data.rows)
								for (var i = 0; i < dataRole.length; i++) {
									var obj = new Object();
									obj.reason = dataRole[i].reason;
									obj.code = dataRole[i].code;
									obj.id = dataRole[i].id;
									obj.operationType = dataRole[i].operationType;
									obj.firstReason=dataRole[i].firstReason;
									var moduleName = dataRole[i].moduleName;
									if (moduleName != null) {
										var moduleObj = eval("("+ moduleName+ ")")[0];
										for ( var o in moduleObj) {
											obj[o] = moduleObj[o];
										}
									}

									array.push(obj);
								}
							},

						});
			}
		}
	});
}


// 加载数据
function initDatagrid() {
	load_Table();
	
}
// 查询按钮
function search() {
	var operationType = $('#reason_type').combobox('getValue');
	var reason = $("#reason_models").val();
	var operationModule=$("#operation_module").combobox('getValue');
	var queryParams = $('#new_reasonReLinksDatagrid').datagrid('options').queryParams;
	queryParams.operationType = operationType;
	queryParams.reason = reason;
	queryParams.operationModule=operationModule;
	setFirstPage("#new_reasonReLinksDatagrid");
	$('#new_reasonReLinksDatagrid').datagrid('options').queryParams = queryParams;
	$("#new_reasonReLinksDatagrid").datagrid('reload');
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
	operations += '<a href="javascript:void(0)" style="font-weight:bolder;color:blue;" onclick="loadUpdateBMSReasonLinksToWindow('
			+"'"+ row.code+"'" + ')">'+value+' &nbsp;&nbsp;</a>';
	return operations;

};

function BMSEditChanneFromInfoExam_1(divName){
	//原因
	var reason=$('#r_editreason').val();
	if (!reason) {
		$.messager.show({
			title : '提示',
			msg : '请填写原因'
		});
		return false;
	}
	if (reason.length>200) {
		$.messager.show({
			title : '提示',
			msg : '原因输入过长'
		});
		return false;
	}
	var editcanRequestDays = $('#r_editcanRequestDays').val();
	if(editcanRequestDays!=undefined){
		var reg = new RegExp("^[0-9]*$");  
		if(!reg.test(editcanRequestDays)){  
			strs=editcanRequestDays.split(".")[1]==0
			if(strs==false ||editcanRequestDays<1){
				parent.$.messager.show({
					title : '提示',
					msg : '天数只能是正整数'
				});
				return false;
			}
		}
	}
	if(editcanRequestDays.length>10){
		$.messager.show({
			title : '提示',
			msg : '限制天数输入过长！'
		});
	    return false;
	}
	var remark=$('#r_editremark').val();
	if (remark.length>100) {
		$.messager.show({
			title : '提示',
			msg : '备注值字符过长！'
		});
		return false;
	}
	var operationModules= $("#r_operation_module_1").combobox('getValue');
	if (!operationModules) {
		$.messager.show({
			title : '提示',
			msg : '请选择操作环节！'
		});
		return false;
	}
	return true;
}
// 编辑保存
function editBMSReasonLinksInfo() {
	/*alert('该功能正在维护中...');*/
	var boo = BMSEditChanneFromInfoExam_1('#editBMSReasonManageInfoForm');
	if (!boo)
		return;
	var reasonTexplain =$('#r_editRedioQiyong').combobox('getValues').join(',');
	var operationModule = $('#r_operation_module_1').combobox('getValues').join(',');
	var editOldReason = $('#r_editOldReason').val();
	var moduleName = $('#r_moduleName').val();
//	var operationType = $('#r_operationType').val();
	var Id = $('#r_editId').val();
	var type = $('#r_editType').val();
	var editOldReason=$('#r_editOldReason').val();
	var parentId = $('#r_edit_parent_id').val();
	var conditionType=$('#r_edit_condition_Type').combobox('getValues').join(',');
	
	var operationType = $('#r_editoperationType').val();
	if(operationType=="挂起"){
		operationType="hang";
	}else if(operationType=="退回"){
		operationType="return";
	}else if(operationType=="拒绝"){
		operationType="reject";
	}else if(operationType=="取消"){
		operationType="cancel";
	}
	var canRequestDays = $('#r_editcanRequestDays').val();
	var reason = $('#r_editreason').val();
	var remark = $('#r_editremark').val();
	var id = $('#r_editid').val();
	var version = $('#r_editversion').val();
	var relationCode=$('#r_edit_relationCode').val();
	var isDisabled = $('input:radio[name="r_editredio"]:checked').val();
	var code=$('#r_editcode').val();
//	var url = 'reasonManage/editReasonManage';	
	var url = 'reasonReLinks/editReasonReLinks';
	$.ajax({
		type : "POST",
		url : url,
		data : {
			'id' : Id,
			'operationModules' : operationModule,
			'firstReason' : editOldReason,
			'moduleName' : moduleName,
			//'operationType' : operationType,
			'type' : type,
			'parentId' : parentId,
			'conditionType' : conditionType,
			'operationType' : operationType,
			'canRequestDays' : canRequestDays,
			'reason' : reason,
            'firstReason':editOldReason,
            'reasonTexplain':reasonTexplain,
			'remark' : remark,
			'version' : version,
			'isDisabled':isDisabled,
			'code':code
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

				$('#panelBMSEnumCodeInfo').window({
					modal : true,
					closed : true,
				});

				$('#editPanelBMSReasonLinksInfo').window('close');
				$('#searchLinksBt').trigger('click');
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

// 关闭编辑弹窗按钮
function editcloseWindow() {
	$('#editPanelBMSReasonLinksInfo').window('close');
	$('#searchLinksBt').trigger('click');
}

// 编辑初始化
function loadUpdateBMSReasonLinksToWindow(code) {
	if (!code) {
		return;
	}
	$.ajax({
		url : 'reasonReLinks/queryReasonManagementInit',
		data : {
			'code' : code
		},
		type : "POST",
		success : function(result) {
			if(result.isNotExit==false){
				$.messager.show({
					title : '提示',
					msg : '该原因数据不存在,请刷新！'
				});
			}else{
				$('#editPanelBMSReasonLinksInfo').window({
					modal : true,
					closed : false,
					width : 800,
					height : 500
				});
			}
			$('#r_editOldReasons').next().hide();
			$('#r_moduleName').next().hide();
			//$('#r_operationType').next().hide();
			$('#r_editId').next().hide();
			$('#r_edit_parent_id').next().hide();
			$("#r_edit_relationCode").next().hide();
			$('#r_operation_module_1').combobox({
				url : 'reasonReLinks/listByModule',
				valueField : 'code',
				textField : 'nameCN',
				queryParams: {'codeType':"operationModule"},
				multiple : true,
				onHidePanel: function (oneR) {
					var cbqyvalues=	$("#r_editRedioQiyong").combobox("getValues");
					$("#r_editRedioQiyong").combobox("clear");
					var selectRow =[];
					var cbtextsArr= $("#r_operation_module_1").combobox("getText").split(",");
					var cbvalues=	$("#r_operation_module_1").combobox("getValues");
					var cbArr=[];
					var cbqy =[];

					for (var i = 0; i < cbvalues.length; i++) {
						var cb ={};
						cbArr[i] =cbvalues[i];
						cb.code=cbvalues[i];
						cb.nameCN=cbtextsArr[i];
						selectRow[i] = cb;
					}
					var j= 0;
					for (var i = 0; i < cbqyvalues.length; i++) {	
						if($.inArray(cbqyvalues[i], cbArr)!=-1){
							cbqy[j]=cbqyvalues[i];
							j++;
						};

					}
					if(selectRow==""){
						$("#r_editRedioQiyong").combobox("clear");
						$("#r_editRedioQiyong").combobox("loadData",null);    
					}else{
						//loadData 下拉框的值
						$("#r_editRedioQiyong").combobox("loadData",selectRow);	
						//setValues 回显的值
						$("#r_editRedioQiyong").combobox("setValues",cbqy);
					}	

				}


			});
			$('#r_edit_condition_Type').combobox({
				url:'reasonManage/listByModule',  
				queryParams: {'codeType':"operationrule"},
				valueField:'code',    
				textField:'nameCN',
				multiple:true,  
				filter: function(q, row){
					var opts = $(this).combobox('options');
					return row[opts.textField].indexOf(q)>-1;//将从头位置匹配改为任意匹配 
				},
				formatter: function(row){
					return '<span>'+row.code+""+row.nameCN+'</span>'; 
				}
			});
			$('#editBMSReasonLinksInfoForm').form('clear');
			stuShowReasonText(result.ReasonShow);
			stuffUpdatePage_1(result.info);
		},
		error : function(data) {
			$.messager.show({
				title : 'warning',
				msg : data.responseText
			});
		}
	});
}

function stuShowReasonText(result){
	$('#r_editRedioQiyong').combobox({
		valueField : 'code',
		textField : 'nameCN',
		multiple : true,
		data:result
	});
}

// 编辑填充
function stuffUpdatePage_1(result) {
	if (result.moduleName != null) {
		$('#r_operation_module_1').combobox('setValues', result.moduleName.replace(/\s/g, ""));
	}
	if(result.reasonTexplain!=null){
		$('#r_editRedioQiyong').combobox('setValues', result.reasonTexplain.replace(/\s/g, ""));
	}
	$('#r_editOldReasons').textbox('setValue', result.reason);
	$('#r_moduleName').textbox('setValue', result.moduleName);
	//$('#r_operationType').textbox('setValue', result.operationType);
	$('#r_editId').textbox('setValue', result.id);
	var vc;
	if(result.operationType=='hang'){
		vc="挂起";
	}else if(result.operationType=="return"){
		vc="退回";
	}else if(result.operationType=="reject"){
		vc="拒绝";
	}else if(result.operationType=="cancel"){
		vc="取消";
	}
	$("#r_edit_relationCode").textbox('setValue',result.relationCode);
	$("#r_edit_parent_id").textbox('setValue',result.parentId);
	$("#r_editfirstreason").textbox('setValue',result.firstReason);
	$("#r_editid").textbox('setValue',result.id);
	$('#r_editversion').textbox('setValue',result.version);
	$("#r_editcode").textbox('setValue',result.code);
	$('#r_editType').textbox("setValue", result.type);
	$('#r_editparentId').textbox('setValue',result.parentId);
	$('#r_editoperationType').textbox('setValue',vc);
	$('#r_editcanRequestDays').textbox('setValue',result.canRequestDays);
	$('#r_editreason').textbox('setValue',result.reason);
	$('#r_editreasonTexplain').textbox('setValue',result.reasonTexplain);
	$('#r_editremark').textbox('setValue',result.remark);
	//$('#editOldReason').textbox('setValue',result.reason);
	if(result.operationType=='hang' || result.operationType=='return' || result.operationType=='cancel'){
		$('#r_editcanRequestDays').textbox('textbox').attr('disabled',true);
		$('#r_edit_condition_Type').textbox('textbox').attr('disabled',true);
		$('#r_edit_condition_Type').combobox('textbox').prev().hide(); 
		$('#r_edit_condition_Type').combobox('clear');
	}else{
		$('#r_editcanRequestDays').textbox('textbox').attr('disabled',false);
		$('#r_edit_condition_Type').textbox('textbox').attr('disabled',false);
		$('#r_edit_condition_Type').combobox('textbox').prev().show(); 
	}

	if (result.isDisabled == 0) {
		$('#editPanelBMSReasonLinksInfo input[name="r_editredio"]').get(0).checked = true;
	} else {
		$('#editPanelBMSReasonLinksInfo input[name="r_editredio"]').get(1).checked = true;
	}
	if(result.conditionType!=null && result.conditionType!=""){
		$('#r_edit_condition_Type').combobox("setValues", result.conditionType);
	}
}
function exportReason(){
	window.location.href="reasonManage/exportReason";
}
$(window).keydown(function(event) {
    if(event.keyCode == 13) {
    	search();
    }
});