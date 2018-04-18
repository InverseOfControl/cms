$(function() {
	initDatagrid();
	
	//字数限制
	$("#editTemplate_name").keyup(function(){
		 var maxLength = 20;  
         var len = $("#editTemplate_name").val().length;  
         $('#editTemplate_name').html(maxLength - len);  
         if(parseInt($('#editTemplate_nameCount').text()) < 0){  
             $('#editTemplate_nameCount').html('0');  
             var res = $(this).val().substring(0,20);  
             $(this).val(res);  
         }  
	});
	

	//字数限制
	$("#addTemplate_name").keyup(function(){
		 var maxLength = 20;  
         var len = $("#addTemplate_name").val().length;  
         $('#addTemplate_name').html(maxLength - len);  
         if(parseInt($('#addTemplate_nameCount').text()) < 0){  
             $('#addTemplate_nameCount').html('0');  
             var res = $(this).val().substring(0,20);  
             $(this).val(res);  
         }  
	});

	// 渠道配置进来
	// 下拉框:渠道
	$('#contract_channelId').combobox({
		url : 'channeProduct/findChannel',
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
			
		}
	});
	//启用 禁用
	$('#addBMSChannelInfoForm #iState').switchbutton({
		checked : true,
		onChange : function(checked) {
			if(checked){
				$('#addBMSChannelInfoForm #isDisabled').val('0'); 
			} else {
				$('#addBMSChannelInfoForm #isDisabled').val('1'); 
			}
		}
	});
	$('#editContractTemplate #upIState').switchbutton({
		checked : true,
		onChange : function(checked) {
			if(checked){
				$('#editContractTemplate #upIsDisabled').val('0'); 
			} else {
				$('#editContractTemplate #upIsDisabled').val('1'); 
			}
		}
	});
	// 下拉框:合同类型
	$('#addTemplate_contractType').combobox({
		url : '',
		valueField : 'code',
		textField : 'name',
		panelHeight: 'auto',
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
	// 下拉框:合同类型
	$('#editTemplate_contractType').combobox({
		url : '',
		valueField : 'code',
		textField : 'name',
		panelHeight: 'auto',
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


$(window).keydown(function(event) {
    if(event.keyCode == 13) {
    	contract_serach();
    }
});

function initDatagrid() {
	$("#contract_datagrid")
			.datagrid(
					{
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
						url : 'contractTemplate/listPage',
						fitColumns : true,
						border : false,
						singleSelect : true,
						pagination : true,
						striped : true,
						rownumbers : true,
						fit : true,
						columns : [ [
								{
									field : 'templateContent',
									title : '模板内容',
									width : 220,
									hidden : true
								},
								{
									field : 'id',
									title : '模板ID',
									width : 220,
									hidden : true
								},
								{
									field : 'templateUrl',
									title : '模板url',
									width : 220,
									hidden : true
								},

								{
									field : 'channelId',
									title : '渠道ID',
									width : 220,
									hidden : true
								},
								{
									field : 'contractChannelId',
									title : '渠道模板关系ID',
									width : 220,
									hidden : true
								},
								{
									field : 'code',
									title : '模板代码',
									width : 220
								},
								{
									field : 'channelName',
									title : '渠道名称',
									width : 220
								},
								{
									field : 'name',
									title : '模板名称',
									width : 220,
								},
								{
									field : 'printType',
									title : '打印类型',
									width : 220,
									formatter : function(value, row, index) {
										if (value != null && value != ''
												&& value != undefined) {
											if (value == 1) {
												return "套打";
											} else if (value == 2) {
												return "直打";
											} else {
												return "";
											}
										}
									},
								},
								{
									field : 'channel',
									title : '模板文件',
									width : 220,
									formatter : function(value, row, index) {
										var result = "<a href='javascript:void(0)' onclick='showTemplateDialog("+'\"'+row.id +'\"' +','+'\"'+row.name +'\"'+")'>" + row.name + "</a>";
										return result;
									},
								}, {
									field : 'contractType',
									title : '合同类型',
									width : 200,
									formatter : function(value, row, index) {
										if (value == 1) {
											return "电子版";
										} else {
											return "纸质版";
										}
									}
								}, {
									field : 'isDisabled',
									title : '是否启用',
									width : 200,
									formatter : function(value, row, index) {
										if (value == 1) {
											return "否";
										} else {
											return "是 ";
										}
									}
								}, {
									field : 'operation',
									title : '操作',
									formatter : operationTemplate,
									width : 250
								} ] ]
					});
}
/**
 * 查询函数
 */
function contract_serach() {
	var channelId = $('#contract_channelId').combobox('getValue');
	// 查询
	var queryParams = $('#contract_datagrid').datagrid('options').queryParams;
	queryParams.channelId = channelId;
	setcha_pro_FirstPage("#contract_datagrid");
	$('#contract_datagrid').datagrid('options').queryParams = queryParams;
	$("#contract_datagrid").datagrid('reload');
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
// function format_Cha_pro_Operations(value, row, index) {
//
// var operations = '';
// var isAvaliable = row.isAvaliable;
// // 1.启用，2,禁用
// if (isAvaliable == 1) {
// isAvaliable = 2;
// operations += '<a href="javascript:void(0)" onclick="changeEnableState('
// + row.id + ',' + isAvaliable + ')">禁用 &nbsp;&nbsp;</a>';
// }
// // 1.启用，2,禁用
// else if (isAvaliable == 2) {
// isAvaliable = 1;
// operations += '<a href="javascript:void(0)" onclick="changeEnableState('
// + row.id + ',' + isAvaliable + ')">启用 &nbsp;&nbsp;</a>';
// }
// return operations;
//
// };
/**
 * 查看、下载文件函数
 */
/*function downTemplate(id, name) {
	var windowStatus = "status:0,menubar=yes,scrollbars=no,toolbar=yes,status=yes,top=150,left=500,width=700,height=800";
	window.open("contractTemplate/download?id="+id,name,windowStatus); 
}*/


function showTemplateDialog(id, name) {
	var url="contractTemplate/download?id="+id;
    var content = '<iframe src="' + url + '" width="100%" height="99%" frameborder="0" scrolling="no"></iframe>';  
    var boarddiv = '<div id="msgwindow" title="' + name+".pdf" + '"></div>'//style="overflow:hidden;"可以去掉滚动条  
    $(document.body).append(boarddiv);  
    var win = $('#msgwindow').dialog({  
        content: content,  
        width: 600,  
        height: 700,  
        modal: false,  
        title: '合同模板预览',  
        onClose: function () {  
            $(this).dialog('destroy');//后面可以关闭后的事件  
        }  
    });  
    win.dialog('open');  
}  


/**
 * 新增函数
 */
function contract_add() {
	// 新增渠道下拉框
	// 下拉框:渠道
	$('#addTemplate_channelId').combobox({
		url : 'contractTemplate/findChannel',
		valueField : 'id',
		textField :'name',
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
		onSelect: function(record){
			// 依据选中加载合同类型
			var url = 'contractTemplate/findContractTypeList';
			$.ajax({
				type : "POST",
				url : url,
				data : {
					'channelId' : record.id
				},
				dataType : 'json',
				async : false, // 设置同步方式
				cache : false,
				success : function(result) {
					// 合同类型
					$('#addTemplate_contractType').combobox({
						data : result
					});
					//默认纸质版
					$('#addTemplate_contractType').combobox("setValue","0");
					
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
	// 打印类型下拉列表
	$('#addTemplate_printType')
			.combobox(
					{
						url : 'contractTemplate/findPrintType?codeType=ContractTemPrintType',
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
	// 清空form表单数据
	$('#templateAddForm #addTemplate_name').textbox('clear');
	$('#templateAddForm #templateUrl').val("");
	$('#templateAddForm #addTemplate_code').textbox('clear');
	$("#file").val("");
	$('#templateAddForm #isDisabled').val(0);
	$('#templateAddForm #iState').switchbutton("check");
	
	// 新增弹出页面
	$('#addContractTemplate').window({
		modal : true,
		closed : false,
	});
	createKindEditor();
}

/**
 * 新增上传合同模板
 */
function uploadTemplate() {
	var file = $("#file").val();
	if (file == "" || file == undefined || null == file) {
		$.messager.show({
			title : '提示',
			msg : '请选择要上传的合同模板!'
		});
		return false;
	}
	$("#templateAddForm").ajaxSubmit({
		type : "POST",
		url : "contractTemplate/upload",
		dataType : "json",
		success : function(obj) {
			if (obj.isSuccess == "success") {
				// 设置模板URL的值
				$("#templateAddForm #templateUrl").val(obj.templateUrl);
				$.messager.show({
					title : '提示',
					msg : '上传成功！'
				});
			} else {
				$("#templateAddForm #templateUrl").val("");
				$.messager.show({
					title : '提示',
					msg : '上传失败！'
				});

			}
		}
	})

	// $.ajaxFileUpload({
	// url : 'contractTemplate/upload',
	// fileElementId:'file',
	// dataType : "json",
	// success: function(result){
	// //将json字符串转json对象
	// var obj = eval('(' + result + ')');
	// if(obj.isSuccess="success"){
	// //设置模板URL的值
	// $("#templateAddForm #templateUrl").val(obj.templateUrl);
	// $.messager.show({
	// title : '提示',
	// msg : '上传成功！'
	// });
	// }else{
	// $("#templateAddForm #templateUrl").val("");
	// $.messager.show({
	// title : '提示',
	// msg : '上传失败！'
	// });
	//	        		   
	// }
	// }
	// });
}
function save_contractTemplate() {
	var channelId = $('#addTemplate_channelId').combobox('getValue');
	var name = $('#templateAddForm #addTemplate_name').val();
	var code = $('#templateAddForm #addTemplate_code').val();
	/* var templateUrl = $('#templateAddForm #templateUrl').val(); */
	var templateContent = $('#templateAddForm #templateContent').val();

	/*
	 * alert($('#templateAddForm #templateContent').html());
	 * alert($('#templateAddForm #templateContent').text());
	 * alert($('#templateAddForm #templateContent').val());
	 * alert($('#templateAddForm #templateContent').isEmpty());
	 */
	var printType = $('#addTemplate_printType').combobox('getValue');
	var contractType = $('#addTemplate_contractType').combobox('getValue');
	if (channelId == "" || channelId == undefined || null == channelId) {
		$.messager.show({
			title : '提示',
			msg : '请选择渠道!'
		});
		return false;
	}
	if (name == "" || name == undefined || null == name) {
		$.messager.show({
			title : '提示',
			msg : '模板名称不能为空!'
		});
		return false;
	}
	
	if(name.length > 20){
		$.messager.show({
			title : '提示',
			msg : '模板名称不能超过20!'
		});
		return false;
	}
	if (printType == "" || printType == undefined || null == printType) {
		$.messager.show({
			title : '提示',
			msg : '请选择打印类型!'
		});
		return false;
	}
	if (contractType == "" || contractType == undefined || null == contractType) {
		$.messager.show({
			title : '提示',
			msg : '请选择合同类型!'
		});
		return false;
	}
	
	if (code == "" || code == undefined || null == code) {
		$.messager.show({
			title : '提示',
			msg : '请输入合同代码!'
		});
		return false;
	}
	if(code.length > 20){
		$.messager.show({
			title : '提示',
			msg : '模板代码长度不能超过20!'
		});
		return false;
	}
	
	var existFlag = false;
	var param={};
	param.code=code;
	param.channelId=channelId;
	$.ajax({
		url : 'contractTemplate/valiContractTempCodeExist',
		data :param,
		async: false,
		type : "POST",
		success : function(result) {
			
			if (result.existflag) {
				existFlag = true;
				
			}
		}
	});
	if(existFlag){
		$.messager.show({
			title : '提示',
			msg : '该渠道下合同模板代码已存在，请重新输入。'
		});
		return false;
	}

	if (templateContent == "" || templateContent == undefined
			|| null == templateContent) {
		$.messager.show({
			title : '提示',
			msg : '合同模板内容不能为空!'
		});
		return false;
	}
	/*
	 * if(templateUrl =="" || templateUrl ==undefined || null == templateUrl){
	 * $.messager.show({ title : '提示', msg : '请选择要上传的合同模板!' }); return false; }
	 */
	//var r = confirm('确定保存渠道合同模板?');
	$.messager.confirm('确定','确定保存渠道合同模板?', function(r) {
		if (r) {
			$.ajax({
				url : 'contractTemplate/saveTemplate',
				data : $("#templateAddForm").serialize(),
				type : "POST",
				success : function(result) {
					if (result.flag == "success") {
						$('#addContractTemplate').window('close');
						$("#contract_datagrid").datagrid('reload');
						$.messager.show({
							title : '提示',
							msg : '新增成功！'
						});
					} else {
						$('#addContractTemplate').window('close');
						$("#contract_datagrid").datagrid('reload');
						parent.$.messager.show({
							title : 'Error',
							msg : "新增失败！:"+result.msg
						});
					}
				}
			});
		}
	});
}
/**
 * 取消函数
 */
function cancel_contractTemplate() {
	
	KindEditor.instances[0].html("");
	$('#addContractTemplate').window('close');
}

/**
 * 修改操作
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function operationTemplate(value, row, index) {
	var operations = '';
	operations += "<a href='javascript:void(0)' onclick='updateTemplate("
			+ row.id + ',' + row.channelId + ',' + row.contractChannelId + ',' + row.contractType + ',\"' + row.code+ '\",' + row.isDisabled + ',' 
			+ row.printType + ",\"" + row.name + "\",\""
			+ encodeURI(row.templateUrl) + "\",\""
			+ escape(row.templateContent) + "\")'>修改 &nbsp;&nbsp;</a>";
	return operations;
}
/**
 * 弹出合同更新页面
 */
function updateTemplate(id, channelId, contractChannelId, contractType,code, isDisabled, printType, name,
		templateUrl, templateContent) {
	// 修改渠道下拉框
	// 下拉框:渠道
	$('#editTemplate_channelId').combobox({
		url : 'contractTemplate/findChannel',
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
		onSelect: function(record){
			// 依据选中加载签约客服人员
			var url = 'contractTemplate/findContractTypeList';
			$.ajax({
				type : "POST",
				url : url,
				data : {
					'channelId' : record.id
				},
				dataType : 'json',
				async : false, // 设置同步方式
				cache : false,
				success : function(result) {
					// 签约客服下拉框
					$('#editTemplate_contractType').combobox({
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
	var url = 'contractTemplate/findContractTypeList';
	$.ajax({
		type : "POST",
		url : url,
		data : {
			'channelId' : channelId
		},
		dataType : 'json',
		async : false, // 设置同步方式
		cache : false,
		success : function(result) {
			// 签约客服下拉框
			$('#editTemplate_contractType').combobox({
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
	
	
	// 打印类型下拉列表
	$('#editTemplate_printType')
			.combobox(
					{
						url : 'contractTemplate/findPrintType?codeType=ContractTemPrintType',
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
	// 修改页面赋值
	$('#editTemplate_channelId').combobox('setValue', channelId);
	$('#editTemplate_printType').combobox('setValue', printType);
	$('#editTemplate_contractType').combobox('setValue', contractType);
	$('#templateEditForm #editTemplate_name').textbox('setValue', name);
	$('#templateEditForm #editTemplate_code').textbox('setValue', code);
	$('#templateEditForm #templateEditUrl').val(decodeURI(templateUrl));
	$('#templateEditForm #templateId').val(id);
	$('#templateEditForm #contractChannelId').val(contractChannelId);
	if (isDisabled == 1) {
		$('#templateEditForm #upIState').switchbutton("uncheck");
	} else {
		$('#templateEditForm #upIState').switchbutton("check");
	}
	$("#editfile").val("");
	// 修改弹出页面
	$('#editContractTemplate').window({
		modal : true,
		closed : false,
		onBeforeClose: function () {    //当窗口关闭前
			KindEditor.remove('#templateContent_update');
			}
	});
	// 弹出合同修改页面
	createUpdateKindEditor(unescape(templateContent));
	/*$('#templateEditForm #templateContent_update').html(unescape(templateContent));*/

}

/**
 * 修改合同模板
 */
function uploadEditTemplate() {
	var file = $("#editfile").val();
	if (file == "" || file == undefined || null == file) {
		$.messager.show({
			title : '提示',
			msg : '请选择要更新的合同模板!'
		});
		return false;
	}
	$("#templateEditForm").ajaxSubmit({
		type : "POST",
		url : "contractTemplate/upload",
		dataType : "json",
		success : function(obj) {
			if (obj.isSuccess == "success") {
				// 设置模板URL的值
				$("#templateEditForm #templateEditUrl").val(obj.templateUrl);
				$.messager.show({
					title : '提示',
					msg : '上传成功！'
				});
			} else {
				$("#templateEditForm #templateEditUrl").val("");
				$.messager.show({
					title : '提示',
					msg : '上传失败！'
				});

			}
		}
	})
	// $.ajaxFileUpload({
	// url : 'contractTemplate/upload',
	// fileElementId:'editfile',
	// dataType : "text",
	// success: function(result){
	// //将json字符串转json对象
	// var obj = eval('(' + result + ')');
	// if(obj.isSuccess="success"){
	// //设置模板URL的值
	// $("#templateEditForm #templateEditUrl").val(obj.templateUrl);
	// $.messager.show({
	// title : '提示',
	// msg : '上传成功！'
	// });
	// }else{
	// $("#templateEditForm #templateEditUrl").val("");
	// $.messager.show({
	// title : '提示',
	// msg : '上传失败！'
	// });
	//	        		   
	// }
	// }
	// });
}
/**
 * 修改确定函数
 */
function edit_contractTemplate() {

	var channelId = $('#editTemplate_channelId').combobox('getValue');
	var name = $('#editTemplate_name').val();
	var templateUrl = $('#templateEditUrl').val();
	var templateContent_update=$('#templateEditForm #templateContent_update').val();
	var printType = $('#editTemplate_printType').combobox('getValue');
	var contractType = $('#editTemplate_contractType').combobox('getValue');
	var code = $('#templateEditForm #editTemplate_code').val();

	var file = $("#editfile").val();
	if (channelId == "" || channelId == undefined || null == channelId) {
		$.messager.show({
			title : '提示',
			msg : '请选择渠道!'
		});
		return false;
	}
	
	if (name == "" || name == undefined || null == name) {
		$.messager.show({
			title : '提示',
			msg : '模板名称不能为空!'
		});
		return false;
	}
	
	if(name.length > 20){
		$.messager.show({
			title : '提示',
			msg : '模板名称不能超过20!'
		});
		return false;
	}
	if (printType == "" || printType == undefined || null == printType) {
		$.messager.show({
			title : '提示',
			msg : '请选择打印类型!'
		});
		return false;
	}
	if (contractType == "" || contractType == undefined || null == contractType) {
		$.messager.show({
			title : '提示',
			msg : '请选择合同类型!'
		});
		return false;
	}
	if (code == "" || code == undefined || null == code) {
		$.messager.show({
			title : '提示',
			msg : '合同模板代码不能为空!'
		});
		return false;
	}
	
		if (templateContent_update == "" || templateContent_update == undefined || null == templateContent_update) {
			$.messager.show({
				title : '提示',
				msg : '模板内容不能为空!'
			});
			return false;
		}
		/*if (templateUrl == "" || templateUrl == undefined || null == templateUrl) {
			$.messager.show({
				title : '提示',
				msg : '请选择要上传的合同模板!'
			});
			return false;
		}*/
		// if(file !=null && file !="" && templateUrl !=null && templateUrl !=""){
		// $.messager.show({
		// title : '提示',
		// msg : '请选择要上传的合同模板!'
		// });
		// return false;
		// }
		//var r = confirm('确定更新渠道合同模板?');
		var aa = $("#templateEditForm").serialize();
		$.messager.confirm('确定','确定更新渠道合同模板?', function(r) {
			if (r) {
				$.ajax({
					url : 'contractTemplate/updateTemplate',
					data : $("#templateEditForm").serialize(),
					type : "POST",
					success : function(result) {
						if (result.flag == "success") {
							$('#editContractTemplate').window('close');
							$("#contract_datagrid").datagrid('reload');
							$.messager.show({
								title : '提示',
								msg : '更新成功！'
							});
						} else {
							$('#editContractTemplate').window('close');
							$("#contract_datagrid").datagrid('reload');
							parent.$.messager.show({
								title : 'Error',
								msg : "更新失败！:"+result.msg
							});
						}
					}
				});
			}
		});
}
/**
 * 修改返回函数
 */
function edit_cancel_contractTemplate() {
	KindEditor.instances[0].html("");
	$('#editContractTemplate').window('close');
}

/**
 * 创建html编辑器
 */
function createKindEditor() {
	var editor;
	editor = KindEditor.create('#templateContent', {
		resizeType : 1,
		allowFileManager : true,
		/*
		 * afterBlur : function() { this.sync(); }, afterChange : function() { //
		 * 编辑器内容发生变化后，将编辑器的内容设置到原来的textarea控件里 this.sync(); }, afterCreate :
		 * function() { this.sync(); }
		 */
		afterFocus : function() {
			$("#addTemplate_printType").combobox("hidePanel");
			$("#addTemplate_channelId").combobox("hidePanel");
		},
		afterBlur : function() {
			this.sync();
		}
	});
	KindEditor.instances[0].html("");
	/* editor.html("你好"); */
	/* editor.sync('#templateContent'); */
	/* document.getElementById("templateContent").value=editor.util.getData('templateContent'); */
};

//编辑窗口
/*$("#editContractTemplate").window({
onBeforeClose: function () {    //当窗口关闭前
KindEditor.remove('#templateContent_update');
}
});*/
/**
 * 创建html编辑器(合同修改)
 */
function createUpdateKindEditor(templateContent) {
	var editor;
	editor = KindEditor.create('#templateContent_update', {
		resizeType : 1,
		allowFileManager : true,
		/*
		 * afterBlur : function() { this.sync(); }, afterChange : function() { //
		 * 编辑器内容发生变化后，将编辑器的内容设置到原来的textarea控件里 this.sync(); }, afterCreate :
		 * function() { this.sync(); }
		 */
		afterFocus : function() {
			$("#editTemplate_printType").combobox("hidePanel");
			$("#editTemplate_channelId").combobox("hidePanel");
		},
		afterBlur : function() {
			this.sync();
		},
	afterChange : function() { // 编辑器内容发生变化后，将编辑器的内容设置到原来的textarea控件里
		this.sync();
	},
	afterCreate : function() {
		this.sync();
	}
	});
	editor.html(templateContent);
	
};
