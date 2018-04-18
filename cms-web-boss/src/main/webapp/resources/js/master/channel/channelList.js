var newwin;
$(function() {
	$('#addBMSChannelInfoForm #eIsCanPreferential').switchbutton({
		checked : true,
		onChange : function(checked) {
			if(checked){
				$('#addBMSChannelInfoForm #isCanPreferential').val('0'); 
			} else {
				$('#addBMSChannelInfoForm #isCanPreferential').val('1'); 
			}
		}
	})
	
	$('#addBMSChannelInfoForm #iState').switchbutton({
		checked : true,
		onChange : function(checked) {
			if(checked){
				$('#addBMSChannelInfoForm #isDisabled').val('0'); 
			} else {
				$('#addBMSChannelInfoForm #isDisabled').val('1'); 
			}
		}
	})
	$('#updateBMSChannelInfoForm #upIState').switchbutton({
		checked : true,
		onChange : function(checked) {
			if(checked){
				$('#updateBMSChannelInfoForm #upIsDisabled').val('0'); 
			} else {
				$('#updateBMSChannelInfoForm #upIsDisabled').val('1'); 
			}
		}
	})
	$('#addBMSChannelInfoForm #eContractIState').switchbutton({
		checked : true,
		onChange : function(checked) {
			if(checked){
				$('#addBMSChannelInfoForm #econtractFlag').val('0'); 
			} else {
				$('#addBMSChannelInfoForm #econtractFlag').val('1'); 
			}
		}
	})
	$('#addBMSChannelInfoForm #pContractIState').switchbutton({
		checked : true,
		onChange : function(checked) {
			if(checked){
				$('#addBMSChannelInfoForm #pcontractFlag').val('0'); 
			} else {
				$('#addBMSChannelInfoForm #pcontractFlag').val('1'); 
			}
		}
	})
	$('#updateBMSChannelInfoForm #upEContractIState').switchbutton({
		checked : true,
		onChange : function(checked) {
			if(checked){
				$('#updateBMSChannelInfoForm #upEcontractFlag').val('0'); 
			} else {
				$('#updateBMSChannelInfoForm #upEcontractFlag').val('1'); 
			}
		}
	})
	$('#updateBMSChannelInfoForm #upPContractIState').switchbutton({
		checked : true,
		onChange : function(checked) {
			if(checked){
				$('#updateBMSChannelInfoForm #upPcontractFlag').val('0'); 
			} else {
				$('#updateBMSChannelInfoForm #upPcontractFlag').val('1'); 
			}
		}
	})
	// 重写combobox
	$.extend($.fn.combobox.methods, {
		selectedIndex : function(jq, index) {
			if (!index) {
				index = 0;
			}
			$(jq).combobox(
					{
						onLoadSuccess : function() {
							var opt = $(jq).combobox('options');
							var data = $(jq).combobox('getData');

							for (var i = 0; i < data.length; i++) {
								if (i == index) {
									$(jq).combobox(
											'setValue',
											eval('data[index].'
													+ opt.valueField));
									break;
								}
							}
						}
					});
		}
	});
	//日期格式扩展
	Date.prototype.Format = function(fmt) {
		var o = {
			"M+" : this.getMonth() + 1, //月份 
			"d+" : this.getDate(), //日 
			"h+" : this.getHours(), //小时 
			"m+" : this.getMinutes(), //分 
			"s+" : this.getSeconds(), //秒 
			"q+" : Math.floor((this.getMonth() + 3) / 3), //季度 
			"S" : this.getMilliseconds()
		//毫秒 
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(fmt)) {
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
			}
		}
		return fmt;
	}
	
	initMultCombobox("cxId","channeProduct/findChannel","id","name",true);
	
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
});

$(window).keydown(function(event) {
    if(event.keyCode == 13) {
    	search();
    }
});


$(function() {
	// 新增页面默认关闭
	initDatagrid();

	// 查询按钮
	$('#searchChannelBt').bind('click', search);

	$("#addBMSChannelInfo").window({
		inline : true
	});
	$("#updateBMSChannelInfo").window({
		inline : true
	});
});
function initDatagrid() {
	$("#new_datagrid_channel")
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
						url : 'channel/listPage',
						striped : true,
						rownumbers : true,
						pagination : true,
						fitColumns : false,
						checkOnSelect : false,
						selectOnCheck : false,
						singleSelect : false,
						checkbox : true,
						scrollbarSize : 0,
						columns : [ [
								{
									field : 'id',
									checkbox : true,
									width : 30,
								},
								{
									field : 'code',
									title : '渠道代码',
									width : 220,
									formatter : function(value, row, index) {
										return '<a style="font-weight:bolder;color:blue;" href="javascript:void(0)" onclick="showChannelProduct('
												+ row.id
												+ ')">'
												+ row.code
												+ '</a>';

									},
								},
								{
									field : 'name',
									title : '渠道名称',
									width : 220,
								},
								/*{
									field : 'calculateUrl',
									title : '计算器',
									width : 220,
									formatter : function(value, row, index) {
										return '<a style="font-weight:bolder;color:blue;" href="javascript:void(0)" onclick="outputFile(\''
												+ row.calculateUrl
												+ '\')">'
												+ 'link' + '</a>';

									},
								},*/ {
									field : 'startSalesTime',
									title : '起售日期',
									width : 220,
								}, {
									field : 'stopSalesTime',
									title : '停售日期',
									width : 220,
								}, {
									field : 'econtractFlag',
									title : '电子合同',
									width : 200,
									formatter : function(value, row, index) {
										if (value == 1) {
											return "禁用";
										} else {
											return "启用";
										}
									}
								}, {
									field : 'pcontractFlag',
									title : '纸质合同',
									width : 200,
									formatter : function(value, row, index) {
										if (value == 1) {
											return "禁用";
										} else {
											return "启用";
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
								},{
									field : 'isCanPreferential',
									title : '是否可优惠',
									width : 100,
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
									formatter : formatOperations,
									width : 250
								} ] ]
					});
}
// 查询按钮
function search() {
	var queryParams = $('#new_datagrid_channel').datagrid('options').queryParams;
	var ids="";
	var values = $('#cxId').combobox('getValues');
	for(var i=0;i<values.length;i++){
		if(i!=values.length-1){
			ids=values[i]+""+","+ids;
		}else{
			ids=ids+values[i];
		}
	}
	queryParams.ids = ids;
	setFirstPage("#new_datagrid_channel");
	$('#new_datagrid_channel').datagrid('options').queryParams = queryParams;
	$("#new_datagrid_channel").datagrid('reload');
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
// 跳转至渠道产品
function showChannelProduct(id) {
	var title = '渠道产品配置';
	var jq = top.jQuery;
	if (jq("#layout-container").tabs('exists', title)) {
		// 如果存在，先关闭老的，再重新加载
		jq("#layout-container").tabs('close', title);
		jq("#layout-container").tabs('add', {
			title : title,
			href : 'channeProduct/channelShow?channelId=' + id,
			closable : true
		});
	} else {
		// 加载
		jq("#layout-container").tabs('add', {
			title : title,
			href : 'channeProduct/channelShow?channelId=' + id,
			closable : true
		});
	}
};
// 弹出新增渠道窗口
function addBMSChannelInfo() {
	$('#addBMSChannelInfo').window({
		modal : true,
		closed : false,
	});
	$('#addBMSChannelInfo').form('clear');
	$('#addBMSChannelInfo #isDisabled').val(0);
	$('#addBMSChannelInfoForm #iState').switchbutton("check");
	
	$('#addBMSChannelInfo #econtractFlag').val(1);
	$('#addBMSChannelInfoForm #eContractIState').switchbutton("uncheck");
	
	$('#addBMSChannelInfo #pcontractFlag').val(0);
	$('#addBMSChannelInfoForm #pContractIState').switchbutton("check");


//	$('#addBMSChannelInfo input[name="isDisabled"]').get(1).checked = true;
};

function closechWindow(flag) {
	if(flag=='add'){
		$('#addBMSChannelInfo').window('close');
	}else{
		$('#updateBMSChannelInfo').window('close');
	}
//	initDatagrid();
	$("#new_datagrid_channel").datagrid('reload');
}
// 保存渠道信息
function saveBMSChannelInfo() {
	// 必填校验
	var boo = BMSChanneFromInfoExam('#addBMSChannelInfoForm');
	if (!boo)
		return;
	var aa = $("#addBMSChannelInfoForm").serialize();
	$("#addBMSChannelInfoForm").form('submit', {
		url : 'channel/addChannel',
		data : $("#addBMSChannelInfoForm").serialize(),
		type : "POST",
		dataType: "json", 
		async:false,  // 设置同步方式
	    cache:false,
		success : function(result) {
		   $.messager.progress('close');
		   if(result.indexOf("true")!=-1){
			   $.messager.show({
					title : '提示',
					msg : '该条记录已存在！'
				});
			   return;
		   }		   
	   		if(result.indexOf("yes")!=-1){
	   			$.messager.show({
					title : '提示',
					msg : '保存成功！'
				});
	   			$('#addBMSChannelInfo').window({
	   				modal:true,
	   				closed:true,
	   			});
	   			$("#new_datagrid_channel").datagrid('reload');
	   			initMultCombobox("cxId","channeProduct/findChannel","id","name",true);
//	   			initDatagrid();
	   		}else{
	   			parent.$.messager.show({
					title: 'Error',
					msg: result
				});
	   		}
	   },
		error : function(data) {
			$.messager.show({
				title : 'warning',
				msg : data.responseText
			});
		},
	});
};

// 新增校验
function BMSChanneFromInfoExam(divName) {
	// 渠道id
	var code = $(divName).find('input[name="code"]').val();
	if (!code) {
		$.messager.show({
			title : '提示',
			msg : '请填写渠道代号'
		});
		return false;
	}

	var channelName = $(divName).find('input[name="name"]').val();
	if (!channelName) {
		$.messager.show({
			title : '提示',
			msg : '请填写渠道名称'
		});
		return false;
	}
	if (divName == '#addBMSChannelInfoForm') {
		/*var file = document.getElementById('fileImport').files[0];
		// 判断控件中是否存在文件内容，如果不存在，弹出提示信息，阻止进一步操作
		if (file == null) {
			$.messager.show({
				title : '提示',
				msg : '请选择一个excel文件'
			});
			return;
		} else {
			// 获取文件名称
			var fileName = file.name;
			// 获取文件类型名称
			var file_typename = fileName.substring(fileName.lastIndexOf('.'),
					fileName.length);
			// 这里限定上传文件文件类型必须为.xlsx，如果文件类型不符，提示错误信息
			if (file_typename != '.xlsx') {
				$.messager.show({
					title : '提示',
					msg : '文件类型错误,请选择一个excel文件'
				});
				return false;
			}
		}*/
	}

	// 发售起止日
	var startSalesTime = $(divName).find('input[name="startSalesTime"]').val();
	var stopSalesTime = $(divName).find('input[name="stopSalesTime"]').val();
	if (!startSalesTime || !stopSalesTime) {
		$.messager.show({
			title : '提示',
			msg : '请选择发售起止日'
		});
		return false;
	}
	//去除是时分秒
	var a = new Date().Format("yyyy-MM-dd");
	var nowDate = new Date(Date.parse(new Date().Format("yyyy/MM/dd"))).getTime();
	var startSalesDate = new Date(Date.parse(startSalesTime.replace(/-/g,  "/"))).getTime();
	var stopSalesDate = new Date(Date.parse(stopSalesTime.replace(/-/g,  "/"))).getTime();
	//起售时间必须大于等于系统时间
	/*if(nowDate > startSalesDate){
		$.messager.show({
			title : '提示',
			msg : '起售时间必须大于等于系统时间'
		});
		return false;
	}*/
	//起售时间小于停售时间
	if(stopSalesDate <= startSalesDate){
		$.messager.show({
			title : '提示',
			msg : '起售时间必须小于停售时间'
		});
		return false;
	}
	
	return true;
}

function formatOperations(value, row, index) {

	var operations = '';
	var channelId = row.id;
	operations += '<a href="javascript:void(0)" onclick="loadUpdateChannelToWindow('
			+ row.id + ')">修改 &nbsp;&nbsp;</a>';
	operations += '<a href="javascript:void(0)" onclick="showChannelBank('
			+ row.id + ')">查看签约银行 &nbsp;&nbsp;</a>';
	return operations;

};

// 跳转至签约银行
function showChannelBank(id) {
	var title = '签约银行';
	var jq = top.jQuery;
	if (jq("#layout-container").tabs('exists', title)) {
		// 如果存在，先关闭老的，再重新加载
		jq("#layout-container").tabs('close', title);
		jq("#layout-container").tabs('add', {
			title : title,
			href : 'channelBank/channelShow?channelId=' + id,
			closable : true
		});
	} else {
		// 加载
		jq("#layout-container").tabs('add', {
			title : title,
			href : 'channelBank/channelShow?channelId=' + id,
			closable : true
		});
	}
};

function deleteBMSChannel() {
	var rows = $('#new_datagrid_channel').datagrid('getChecked');
	if (rows.length == 0) {
		$.messager.show({
			title : '提示',
			msg : '请至少选择一条数据'
		});
		return;
	}
	var ids = '';
	var idArr = [];
	
	for ( var i in rows) {
		idArr.push(rows[i].id);
	}
	ids = idArr.join(",");
	//当渠道信息下面存在用它配置的信息
	$.ajax({
		url : 'channel/deleteChannelCheck',
		data : {
			'ids' : ids
		},
		success : function(result) {
			if (result.isSuccess) {
				$.messager.confirm('确认', '您确认想要删除选中的' + rows.length + '条吗？', function(r) {
					if (r) {
						$.ajax({
							url : 'channel/deleteChannel',
							data : {
								'ids' : ids
							},
							success : function(result) {
								if (result.isSuccess) {
									$.messager.show({
										title : '提示',
										msg : '删除成功！'
									});
//									initDatagrid();
									$("#new_datagrid_channel").datagrid('reload');
									initMultCombobox("cxId","channeProduct/findChannel","id","name",true);
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
			} else {
				parent.$.messager.show({
					title : '提示',
					msg : '该渠道下存在有效的配置，不允许删除！'
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
// 编辑初始化
function loadUpdateChannelToWindow(id) {
	$.ajax({
		url : 'channel/updateChannelInit',
		data : {
			id : id
		},
		type : "POST",
		success : function(result) {
			$('#updateBMSChannelInfo').window({
				modal : true,
				closed : false,
			});
			$('#updateBMSChannelInfo').form('clear');
			stuffUpdateChannelPage(result.info);
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
function stuffUpdateChannelPage(result) {
	$('#updateBMSChannelInfo').find('input[name="upId"]').val(result.id);
	$('#updateBMSChannelInfo #name').textbox('setValue', result.name);
	$('#updateBMSChannelInfo #code').textbox('setValue', result.code);
	$('#updateBMSChannelInfo #startSalesTimeUpdate').textbox('setValue',
			result.startSalesTime);
	$('#updateBMSChannelInfo #stopSalesTimeUpdate').textbox('setValue',
			result.stopSalesTime);
	if (result.isDisabled == 1) {
		$('#updateBMSChannelInfo #upIState').switchbutton("uncheck");
	} else {
		$('#updateBMSChannelInfo #upIState').switchbutton("check");
	}
	if (result.econtractFlag == 1) {
		$('#updateBMSChannelInfo #upEContractIState').switchbutton("uncheck");
	} else {
		$('#updateBMSChannelInfo #upEContractIState').switchbutton("check");
	}
	if (result.pcontractFlag == 1) {
		$('#updateBMSChannelInfo #upPContractIState').switchbutton("uncheck");
	} else {
		$('#updateBMSChannelInfo #upPContractIState').switchbutton("check");
	}
	
	if (result.isCanPreferential == 1) {
		$('#updateBMSChannelInfo #upsIsCanPreferential').switchbutton("uncheck");
	} else {
		$('#updateBMSChannelInfo #upsIsCanPreferential').switchbutton("check");
	}
	
}

// 修改渠道信息
function saveUpdateBMSChannelInfo() {
	// 必填校验
	var boo = BMSChanneFromInfoExam('#updateBMSChannelInfoForm');
	if (!boo)
		return;
	var isDisabled = $('#updateBMSChannelInfo #upIsDisabled').val();
	if('1' == isDisabled){
		//禁用提示
		$.messager.confirm('提示', '渠道禁用，将关联禁用其他相关配置，是否禁用?', function(r){
			if (r){
				saveUpdateBMSChannelInfoConfirm();
			}
		});
	} else {
		saveUpdateBMSChannelInfoConfirm();
	}
}
//修改确认
function saveUpdateBMSChannelInfoConfirm(){
	var id=$('#updateBMSChannelInfo #upId').val();
	var name = $('#updateBMSChannelInfo #name').val();
	var code = $('#updateBMSChannelInfo #code').val();
	var startSalesTime = $('#updateBMSChannelInfo #startSalesTimeUpdate').datebox('getValue');
	var stopSalesTime = $('#updateBMSChannelInfo #stopSalesTimeUpdate').datebox('getValue');
	var econtractFlag = $('#updateBMSChannelInfo #upEcontractFlag').val();
	var pcontractFlag = $('#updateBMSChannelInfo #upPcontractFlag').val();
	var isDisabled = $('#updateBMSChannelInfo #upIsDisabled').val();
	var isCanPreferential;
	
	var thisSwitchbuttonObj = $(".easyui-window").find("[switchbuttonName='upsIsCanPreferential']").switchbutton("options").checked;
	if(thisSwitchbuttonObj){
		isCanPreferential = 0;
	}else{
		isCanPreferential = 1;
	}
	
	$.ajax({
		url : 'channel/updateChannel',
		data : {
			'id' : id,
			'name' : name,
			'code' : code,
			'startSalesTime' : startSalesTime,
			'stopSalesTime' : stopSalesTime,
			'econtractFlag' : econtractFlag,
			'pcontractFlag' : pcontractFlag,
			'isDisabled' : isDisabled,
			'isCanPreferential' : isCanPreferential
		},
		type : "POST",
		async : false, // 设置同步方式
		cache : false,
		success : function(result) {
			if(result.isFlag){
				   $.messager.show({
						title : '提示',
						msg : '该条记录已存在！'
					});
				   return;
			   }
			if (result.isSuccess) {
				$.messager.show({
					title : '提示',
					msg : '保存成功！'
				});
				$('#updateBMSChannelInfo').window({
					modal : true,
					closed : true,
				});
				$('#updateBMSChannelInfo').form('clear');
				$("#new_datagrid_channel").datagrid('reload');
//				initDatagrid();
				initMultCombobox("cxId","channeProduct/findChannel","id","name",true);
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


// 新增验证
function addFileUpload() {
	// 获取上传文件控件内容
	var file = document.getElementById('fileImport').files[0];
	fileExam(file);
}
// 修改验证
function updateFileUpload() {
	// 获取上传文件控件内容
	var file = document.getElementById('upFileImport').files[0];
	fileExam(file);
}
// 文件验证方法
function fileExam(file) {
	// 判断控件中是否存在文件内容，如果不存在，弹出提示信息，阻止进一步操作
	if (file == null) {
		$.messager.show({
			title : '提示',
			msg : '请选择一个excel文件'
		});
		return;
	}
	// 获取文件名称
	var fileName = file.name;
	// 获取文件类型名称
	var file_typename = fileName.substring(fileName.lastIndexOf('.'),
			fileName.length);
	// 这里限定上传文件文件类型必须为.xlsx，如果文件类型不符，提示错误信息
	if (file_typename == '.xlsx') {
		// 计算文件大小
		var fileSize = 0;
		// 如果文件大小大于1024字节X1024字节，则显示文件大小单位为MB，否则为KB
		if (file.size > 1024 * 1024) {
			fileSize = Math.round(file.size * 100 / (1024 * 1024)) / 100;
			if (fileSize > 10) {
				$.messager.show({
					title : '提示',
					msg : '错误，文件超过10MB，禁止上传！'
				});
				return;
			}
			fileSize = fileSize.toString() + 'MB';
		} else {
			fileSize = (Math.round(file.size * 100 / 1024) / 100).toString()
					+ 'KB';
		}
	} else {
		$.messager.show({
			title : '提示',
			msg : '文件类型错误,请选择一个excel文件'
		});
	}
}
// 导出excel
function outputFile(calculateUrl) {
	var isExcel = calculateUrl.substring(calculateUrl.lastIndexOf('.'),
			calculateUrl.length);
	var url = 'channel/exportFile?calculateUrl=' + calculateUrl;
	newwin = window.open(url, "_blank");
	if (!newwin.closed) {
		 setTimeout("co();", 2000);
	}
	if (calculateUrl == null || calculateUrl == "null" || calculateUrl == ""
			|| isExcel != '.xlsx') {
		// setTimeout("co();", 1);
		newwin.close()
	}

	/*
	 * $.ajax({ url : url, type : "POST", success : function(result) { if
	 * (result == "success") { $.messager.show({ title : '提示', msg : '下载成功' }); }
	 * else { parent.$.messager.show({ title : 'Error', msg : "导出excel出错" }); } },
	 * error : function(data) { $.messager.show({ title : 'warning', msg :
	 * data.responseText }); } });
	 */
}
function co() {
	newwin.close();// 关闭新窗口
}

