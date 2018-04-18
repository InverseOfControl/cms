$(function() {
	initDatagrid();

	// 查询按钮
	$('#searchBts').bind('click', search);

	$("#panelBMSReasonManagementInfo").window({
		inline : true
	});
	//下拉框获取原因类型
	$('#r_operationType').combobox({
		url:'reasonManage/listByModule',  
		queryParams: {'codeType':"operationType"},
		valueField:'code',    
		textField:'nameCN',
		//刷选code为‘全部’的枚举
		loadFilter:function(data){
             var data1 = [];  
             for(var i in data)  
             {  
            //注意条件项的变化，-1 代表未匹配  
            if(data[i].code != 'all')  
                 {  
                    data1.push(data[i]);  
                 }  
             }  
             return data1;  
         } ,
		onHidePanel: function(){
			var operationType = $('#r_operationType').combobox('getValue');
			if(operationType=='hang' || operationType=='return' || operationType=='cancel'){
				$('#canRequestDays').textbox('textbox').attr('disabled',true);
				$('#condition_Type').textbox('textbox').attr('disabled',true);
				$('#condition_Type').combobox('textbox').prev().hide(); 
				$('#condition_Type').combobox('clear');
			}else{
				$('#canRequestDays').textbox('textbox').attr('disabled',false);
				$('#condition_Type').textbox('textbox').attr('disabled',false);
				$('#condition_Type').combobox('textbox').prev().show(); 
			}
			
			$.ajax({
				type: "POST",
				url: "reasonManage/findByValue",
				data : {operationType:operationType},
				success: function(data){
				$("#r_firstReason").combobox("loadData",data);
							   }
						}); 
					}
	           });
	
	//下框获取所有操作环节
	$('#r_operationRelation').combobox({
		url : 'reasonReLinks/listByModule',
		valueField : 'code',
		textField : 'nameCN',
		queryParams: {'codeType':"operationModule"},
		multiple : true,
		onHidePanel: function (oneR) {
			debugger;
			var cbqyvalues=	$("#r_redioQiyong").combobox("getValues");
			$("#r_redioQiyong").combobox("clear");
			var selectRow =[];
			var cbtextsArr= $("#r_operationRelation").combobox("getText").split(",");
			var cbvalues=	$("#r_operationRelation").combobox("getValues");
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
				$("#r_redioQiyong").combobox("clear");
				$("#r_redioQiyong").combobox("loadData",null);    
			}else{
			//loadData 下拉框的值
			$("#r_redioQiyong").combobox("loadData",selectRow);	
			//setValues 回显的值
			$("#r_redioQiyong").combobox("setValues",cbqy);
		    }	
			
		}
	});

	$('#r_redioQiyong').combobox({
		valueField : 'code',
		textField : 'nameCN',
		multiple : true,
	});

	$('#r_firstReason').combobox({ 
		 formatter: function(row){
			  return '<span>'+row.code+"-"+row.reason+'</span>'; 
		 }
	   });
	
	$('#r_type').combobox({
		onHidePanel: function(){
		var type = $('#r_type').combobox('getValue');	
		 if(type=='1'){
			$('#r_firstReason').textbox('textbox').attr('disabled',true);
			$('#r_firstReason').combobox('textbox').prev().hide(); 
			$('#r_firstReason').combobox('clear');
		 }else{
			$('#r_firstReason').textbox('textbox').attr('disabled',false);
			$('#r_firstReason').combobox('textbox').prev().show(); 
		 }
			
		}
	});
	$('#condition_Type').combobox({
		url:'reasonManage/listByModule',  
		queryParams: {'codeType':"operationrule"},
		valueField:'code',    
		textField:'nameCN',
		 multiple:true,  
		filter: function(q, row){
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q)>-1;//将从头位置匹配改为任意匹配 
		},
		onHidePanel: function() {
			var valueField = $(this).combobox("options").valueField;
			var val = $(this).combobox("getValue");  //当前combobox的值
			var allData = $(this).combobox("getData");   //获取combobox所有数据
			var result = true;      //为true说明输入的值在下拉框数据中不存在
			for (var i = 0; i < allData.length; i++) {
				if (val == allData[i][valueField]) {
					result = false;
				}
			}
			if (result) {
				$(this).combobox("clear");
			}
		},
		 formatter: function(row){
			  return '<span>'+row.code+""+row.nameCN+'</span>'; 
		 }
	});
	$('#reason_type_1').combobox({
		url:'reasonManage/listByModule',  
		queryParams: {'codeType':"operationType"},
		valueField:'code',    
		textField:'nameCN', 
		filter: function(q, row){
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q)>-1;//将从头位置匹配改为任意匹配 
		},
		onHidePanel: function() {
			var valueField = $(this).combobox("options").valueField;
			var val = $(this).combobox("getValue");  //当前combobox的值
			var allData = $(this).combobox("getData");   //获取combobox所有数据
			var result = true;      //为true说明输入的值在下拉框数据中不存在
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
	




// 加载数据
function initDatagrid() {
	$("#new_reasonManageDatagrid").datagrid({
		url : 'reasonManage/listPage',
		fitColumns : false,
		border : false,
		singleSelect : true,
		pagination : true,
		striped : true,
		rownumbers : true,
		fit : true,
		columns : [ [
		    {
			field : 'reason',
			title : '原因',
			width : 280
		},{
			field : 'operationType',
			title : '原因类型',
			width : 80,
			formatter : function(value, rowData, rowIndex) {
				if (value =="hang") {
					return "挂起";			
				} else if(value=="return"){
					return "退回";
				}else if(value=="reject"){
					return "拒绝";
				}else if(value=="cancel"){
					return "取消";
				}
			}
		},{
			field : 'code',
			title : '原因编码',
			width : 200
		}, {
			field : 'firstReason',
			title : '一级原因',
			width : 200
		},{
			field : 'canRequestDays',
			title : '限制再申请天数',
			width : 100
		},{
			field : 'conditionType',
			title : '规则',
			width : 100
		},{
			field : 'remark',
			title : '备注',
			width : 200
		},{
			field : 'isDisabled',
			title : '是否启用',
			width : 100,
			
			formatter : function(value, rowData, rowIndex) {
				if (value == 1) {
					return "否";			
				} else{
					return "是";
				}
			}
		},{
			field : 'operation',
			title : '操作',
			formatter : formatOperations,
			width : 250
		} ] ]
	});
}




// 查询按钮
function search() {
	var operationType = $('#reason_type_1').combobox('getValue');
	var reason=$("#reason_model").val();
	var queryParams = $('#new_reasonManageDatagrid').datagrid('options').queryParams;
	queryParams.operationType = operationType;
	queryParams.reason=reason;
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
			+"'"+ row.code+"'" + ')">修改 &nbsp;&nbsp;</a>';
	operations += '<a href="javascript:void(0)" style="font-weight:bolder;color:blue;" onclick="loadDeleteBMSReasonManagementToWindow('
		+"'"+ row.code+"'" + ')">删除 &nbsp;&nbsp;</a>';
	return operations;

};
// 弹出新增枚举窗口
function addBMSReasonManagementInfo() {
	/*alert('该功能正在维护中...');*/
	$('#panelBMSReasonManagementInfo').window({
		modal : true,
		closed : false,
		width : 800, 
		height : 500
	});
	$("#r_redioQiyong").combobox("loadData",'');
	$('#addBMSReasonManageInfoForm').form('clear');
	debugger;
	/*$("#r_redioQiyong").combobox('clear');*/
	
	$('#editOldReason').next().hide();
	$('input[name="redio"]').get(0).checked = true;
};

// 保存
function saveBMSReasonManageInfo() {
	// 必填校验
	var boo = BMSChanneFromInfoExam('#addBMSReasonManageInfoForm');
	if (!boo)
		return;
	//原因CODE
	var reasonCode=$('#reasonCode').val();
	//是否禁用
	var isDisabled = $('input:radio[name="redio"]:checked').val();
	//原因类型
	var operationType = $('#r_operationType').combobox('getValue');
	//原因级别
	var type=$('#r_type').combobox('getValue');
	//一级原因
	var firstReason=$('#r_firstReason').combobox('getValue');
	//限制申请天数
	var canRequestDays=$('#canRequestDays').val();
	//规则
	var conditionType=$('#condition_Type').combobox('getValues').join(',');
	
	//原因
	var reason=$('#reason').val();
	//备注
	var remark=$('#remark').val();
	//显示环节
	var disPlayRelation=$('#r_redioQiyong').combobox('getValues').join(',');
	
	//操作环节
	var operationRelation=$('#r_operationRelation').combobox('getValues').join(',');
	
	var url = 'reasonManage/addReasonManagement';
	$.ajax({
		type : "POST",
		url : url,
		data : {
			'code':reasonCode,
			'canRequestDays' : canRequestDays,
			'type' : type,
			'remark' : remark,
			'reason' : reason,
			'operationType' : operationType,
			'firstReason' :firstReason,
			"isDisabled":isDisabled,
			'conditionType':conditionType,
			'disPlayRelation':disPlayRelation,
			'operationRelation':operationRelation
		},
		dataType : 'json',
		async : false, // 设置同步方式
		cache : false,
		success : function(result) {
			$.messager.progress('close');
			if(result.isDisabled==false){
				$.messager.show({
					title : '提示',
					msg : '一级原因状态为禁用,二级原因状态必须为禁用！'
				});
			}
			if(result.existence==false){
				$.messager.show({
					title : '提示',
					msg : '原因编码已存在,请重新输入！'
				});	
			}
		 if(undefined!=result.isSuccess){	
			if (result.isSuccess) {
				$.messager.show({
					title : '提示',
					msg : '保存成功！'
				});
				
				$('#panelBMSEnumCodeInfo').window({ 
					modal : true, 
					closed : true,
					});
				$('#panelBMSReasonManagementInfo').window('close');
				$('#searchBts').trigger('click');
			} else {
				parent.$.messager.show({
					title : 'Error',
					msg : result
				});
			}
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
	var type = $('#editType').val();
	var editOldReason=$('#editOldReason').val();
	var parentId = $('#edit_parent_id').val();
	var reasonTexplain =$('#editRedioQiyong').combobox('getValues').join(',');
	var operationModule = $('#editOperationRelation').combobox('getValues').join(',');
	var conditionType=$('#edit_condition_Type').combobox('getValues').join(',');
	var moduleName=$('#edit_moduleName').val();
	var operationType = $('#editoperationType').val();
	if(operationType=="挂起"){
		operationType="hang";
	}else if(operationType=="退回"){
		operationType="return";
	}else if(operationType=="拒绝"){
		operationType="reject";
	}else if(operationType=="取消"){
		operationType="cancel";
	}
	var canRequestDays = $('#editcanRequestDays').val();
	var reason = $('#editreason').val();
	var remark = $('#editremark').val();
	var id = $('#editid').val();
	var version = $('#editversion').val();
	var relationCode=$('#edit_relationCode').val();
	var isDisabled = $('input:radio[name="editredio"]:checked').val();
	var code=$('#editcode').val();
	var url = 'reasonReLinks/editReasonReLinks';	
	$.ajax({
		type : "POST",
		url : url,
		data : {
			'type' : type,
			'parentId' : parentId,
			'operationModules' : operationModule,
			'conditionType' : conditionType,
			'operationType' : operationType,
			'canRequestDays' : canRequestDays,
			'reason' : reason,
            'firstReason':editOldReason,
			'remark' : remark,
			'id' : id,
			'version' : version,
			'isDisabled':isDisabled,
			'relationCode':relationCode,
			'reasonTexplain':reasonTexplain,
			'moduleName':moduleName,
			'code':code
		},
		dataType : 'json',
		async : false, // 设置同步方式
		cache : false,
		success : function(result) {
			$.messager.progress('close');
			if(result.isDisabled==false){
				$.messager.show({
					title : '提示',
					msg : '一级原因状态为禁用,二级原因状态必须为禁用！'
				});
			}
		 if(undefined!=result.isSuccess){	
			if (result.isSuccess) {
				$.messager.show({
					title : '提示',
					msg : '保存成功！'
				});
				
				 $('#panelBMSEnumCodeInfo').window({ modal : true,
					 closed : true, });
				 
				$('#editPanelBMSReasonManagementInfo').window('close');
				$('#searchBts').trigger('click');
			} else {
				parent.$.messager.show({
					title : 'Error',
					msg : result
				});
			}
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
	$('#searchBts').trigger('click');
}
//关闭编辑弹窗按钮
function editReasoncloseWindow() {
	$('#editPanelBMSReasonManagementInfo').window('close');
	$('#searchBts').trigger('click');
}

// 校验
function BMSChanneFromInfoExam(divName) {
	//原因CODE校验
	var code=$('#reasonCode').val();
	var reg = new RegExp("^[A-Za-z0-9]{1,20}$");   
	if(!code){
		$.messager.show({
			title : '提示',
			msg : '请填写原因编码！'
		});
		return false;	
	}
	if(!reg.test(code)){  
		$.messager.show({
			title : '提示',
			msg : '原因编码填写不符合规范！'
		});
		return false;	
		
	}
	
	//原因类型
	var operationType = $('#r_operationType').combobox('getValue');
	if(!operationType){
		$.messager.show({
			title : '提示',
			msg : '请选择原因类型'
		});
		return false;
	}
	
	//原因级别
	var type=$('#r_type').combobox('getValue');
	if(!type){
		$.messager.show({
			title : '提示',
			msg : '请选择原因级别'
		});
		return false;
	}
	
	var firstReason=$('#r_firstReason').combobox('getValue');
	if (!firstReason && type==2) {
		$.messager.show({
			title : '提示',
			msg : '请选择一级原因'
		});
		return false;
	}
	
	var canRequestDays = $(divName).find('input[name="canRequestDays"]').val();
		var reg = new RegExp("^[0-9]*$");  
		if(!reg.test(canRequestDays)){  
		strs=canRequestDays.split(".")[1]==0
		if(strs==false || canRequestDays<1){
			parent.$.messager.show({
				title : '提示',
				msg : '天数只能是正整数'
			});
			return false;
		}
	}
		if(canRequestDays.length>6){
			$.messager.show({
				title : '提示',
				msg : '限制天数输入有误！'
			});
		    return false;
		}
		
	//原因
	var reason=$('#reason').val();
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
	
	var remark=$('#remark').val();
	if (remark.length>100) {
		$.messager.show({
			title : '提示',
			msg : '备注值字符过长'
		});
		return false;
	}
   //判断操作环节的值是必填的
	var operationRel=$('#r_operationRelation').combobox('getValue');
	if (!operationRel) {
		$.messager.show({
			title : '提示',
			msg : '请选择操作环节！'
		});
		return false;
	}
	
	return true;
}

function BMSEditChanneFromInfoExam(divName){
	//原因
	var reason=$('#editreason').val();
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
	
	var editcanRequestDays = $(divName).find('input[name="editcanRequestDays"]').val();
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
	if(editcanRequestDays.length>10){
		$.messager.show({
			title : '提示',
			msg : '限制天数输入过长！'
		});
	    return false;
	}
	var remark=$('#editremark').val();
	if (remark.length>100) {
		$.messager.show({
			title : '提示',
			msg : '备注值字符过长！'
		});
		return false;
	}
	  //判断操作环节的值是必填的
	var operationRel=$('#editOperationRelation').combobox('getValue');
	if (!operationRel) {
		$.messager.show({
			title : '提示',
			msg : '请选择操作环节！'
		});
		return false;
	}
	return true;
}
 function loadDeleteBMSReasonManagementToWindow(code){
	/* alert('该功能正在维护中...');*/
		$.messager.confirm('确认','确定要删除该原因吗?',function(r){
			if (r){ 
				$.ajax({
					url : 'reasonManage/delReasonByCode',
					data : {
						"code":code
					},
					type : "POST",
					async : false, // 设置同步方式
					cache : false,
					success : function(result) {
						$.messager.progress('close');
						if(result.isSuccess==true){
							parent.$.messager.show({
								title : '提示',
								msg : '删除成功！'
							});
							$('#editPanelBMSReasonManagementInfo').window('close');
							$('#searchBts').trigger('click');	
						}else{
							parent.$.messager.show({
								title : '提示',
								msg : '删除失败！'
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
function loadUpdateBMSReasonManagementToWindow(code) {
	/*alert('该功能正在维护中...');*/
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
			$('#editPanelBMSReasonManagementInfo').window({
				modal : true,
				closed : false,
				width : 800, 
				height : 500
			});
			$('#edit_moduleName').next().hide();
			$('#edit_parent_id').next().hide();
			$("#edit_relationCode").next().hide();
			$('#edit_condition_Type').combobox({
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
			$('#editOperationRelation').combobox({
				url : 'reasonReLinks/listByModule',
				valueField : 'code',
				textField : 'nameCN',
				queryParams: {'codeType':"operationModule"},
				multiple : true,
				onHidePanel: function (oneR) {
					var cbqyvalues=	$("#editRedioQiyong").combobox("getValues");
					$("#editRedioQiyong").combobox("clear");
					var selectRow =[];
					var cbtextsArr= $("#editOperationRelation").combobox("getText").split(",");
					var cbvalues=	$("#editOperationRelation").combobox("getValues");
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
						$("#editRedioQiyong").combobox("clear");
						$("#editRedioQiyong").combobox("loadData",null);    
					}else{
					//loadData 下拉框的值
					$("#editRedioQiyong").combobox("loadData",selectRow);	
					//setValues 回显的值
					$("#editRedioQiyong").combobox("setValues",cbqy);
				    }	
					
				}
			});
			$('#editBMSReasonManageInfoForm').form('clear');
			stuShowReasonText_1(result.ReasonShow);
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
function stuShowReasonText_1(result){
	$('#editRedioQiyong').combobox({
		valueField : 'code',
		textField : 'nameCN',
		data:result,
		multiple : true
	});
}

// 编辑填充
function stuffUpdatePage(result) {
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
	if (result.moduleName != null) {
		$('#editOperationRelation').combobox('setValues', result.moduleName.replace(/\s/g, ""));
	}
	if(result.reasonTexplain!=null){
		$('#editRedioQiyong').combobox('setValues', result.reasonTexplain.replace(/\s/g, ""));
	}
	$("#edit_parent_id").textbox('setValue',result.parentId);
	$("#editfirstreason").textbox('setValue',result.firstReason);
	$("#editid").textbox('setValue',result.id);
	$('#editversion').textbox('setValue',result.version);
	$("#editcode").textbox('setValue',result.code);
	$('#editType').textbox("setValue", result.type);
	$('#editparentId').textbox('setValue',result.parentId);
	$('#edit_moduleName').textbox('setValue', result.moduleName);
	$('#editoperationModule').textbox('setValue',result.operationModule);
	$('#editoperationType').textbox('setValue',vc);
	$('#editcanRequestDays').textbox('setValue',result.canRequestDays);
	$('#editreason').textbox('setValue',result.reason);
	$('#editreasonTexplain').textbox('setValue',result.reasonTexplain);
	$('#editremark').textbox('setValue',result.remark);
	$('#editOldReason').textbox('setValue',result.reason);
	if(result.operationType=='hang' || result.operationType=='return' || result.operationType=='cancel'){
		$('#editcanRequestDays').textbox('textbox').attr('disabled',true);
		$('#edit_condition_Type').textbox('textbox').attr('disabled',true);
		$('#edit_condition_Type').combobox('textbox').prev().hide(); 
		$('#edit_condition_Type').combobox('clear');
	}else{
		$('#editcanRequestDays').textbox('textbox').attr('disabled',false);
		$('#edit_condition_Type').textbox('textbox').attr('disabled',false);
		$('#edit_condition_Type').combobox('textbox').prev().show(); 
	}
	
	if (result.isDisabled == 0) {
		$('#editPanelBMSReasonManagementInfo input[name="editredio"]').get(0).checked = true;
	} else {
		$('#editPanelBMSReasonManagementInfo input[name="editredio"]').get(1).checked = true;
	}
	if(result.conditionType!=null && result.conditionType!=""){
		$('#edit_condition_Type').combobox("setValues", result.conditionType);
	}
}
$(window).keydown(function(event) {
    if(event.keyCode == 13) {
    	search();
    }
});

  
