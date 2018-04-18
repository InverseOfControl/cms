$(function() {
	
	// 新增页面默认关闭
	initDatagrid();
	$("#addBMSProductAuditLimitInfo").window({
		inline : true
	});
	$("#updateBMSProductAuditLimitInfo").window({
		inline : true
	});
	/* switchState(); */
	switchState();
});

function initDatagrid() {
	var productId = $("#queryProductAuditLimitDiv #productId").val();
	$("#new_datagrid").datagrid({
		onLoadSuccess : function(data) {
			$("#new_datagrid").datagrid("hideColumn", "auditLimitId");
		},
		url : 'listPage',
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		fitColumns : false,
		pageList : [ 10, 20, 50 ],
		scrollbarSize : 0,
		queryParams : {
			productId : productId
		},
		columns : [ [ {
			field : 'auditLimitId',
			title : '审批期限Id',
			width : 220,
		}, {
			field : 'auditLimit',
			title : '产品期限',
			formatter : function(value, rowData, rowIndex) {
				return value + "期";
			},
			width : 220,
		}, {
			field : 'floorLimit',
			title : '审批额度下限',
			width : 220,
		}, {
			field : 'upperLimit',
			title : '审批额度上限',
			width : 220,
		}, {
			field : 'isDisabled',
			title : '是否启用',
			formatter : function(value, rowData, rowIndex) {
				if (value == 0) {
					return "启用";
				} else {
					return "禁用";
				}
			},
			width : 220,
		},
		{
			field : 'configureConflict',
			title : '配置冲突',
			formatter : function(value, rowData, rowIndex) {
				if (value == 'N') {
					return "否";
				} else if(value == 'Y'){
					return "是";
				}else{
					return "";
				}
			},
			width : 120,	
		}
		, {
			field : 'operation',
			title : '操作',
			formatter : formatOperations,
			width : 250
		} ] ]
	});
}



function formatOperations(value, row, index) {
 
	var operations = '';
	var auditLimitId = row.auditLimitId;
	operations += '<a href="javascript:void(0)" onclick="loadUpdateProductAuditLimitToWindow('
			+ auditLimitId +')">修改 &nbsp;&nbsp;</a>';
	operations += '<a href="javascript:void(0)" onclick="loadDeleteProductLimitWindows('
		   + auditLimitId + ')">删除 &nbsp;&nbsp;</a>';
	return operations;

};

// 弹出新增产品窗口
function addBMSProductAuditLimitInfo() {
	var title = $('.tabs-selected').text();  
//新增 期限和模块分别处理
	if(title=='期限'){
		$('#addBMSProductAuditLimitInfo').window({
			modal : true,
			closed : false,
		});
		
		$('#addBMSProductAuditLimitInfo').form('clear');
		var thisSwitchbuttonObj = $('#addBMSProductAuditLimitInfo #isDisabled').switchbutton("check");
	}else if(title=='模块'){
		addProductCodeModule()
	}

};

function closeWindow() {
	$('#addBMSProductAuditLimitInfo').window({
		modal : true,
		closed : true,
	});
	$('#addBMSProductAuditLimitInfo').form('clear');
	initDatagrid();
}

function closeUpdateWindow() {
	$('#updateBMSProductAuditLimitInfo').window({
		modal : true,
		closed : true,
	});
	initDatagrid();
}
function BMSProductAuditLimitFromInfoExam(divName) {
	// 产品期限
	var auditLimit = $(divName).find('input[name="auditLimit"]').val();
	if (!auditLimit) {
		$.messager.show({
			title : '提示',
			msg : '请填写产品期限'
		});
		return false;
	}
	
	var reg = new RegExp("^[0-9]*$");  
	  if(!reg.test(auditLimit)){  
	    strs=auditLimit.split(".")[1]==0
	    if(strs==false){
	      parent.$.messager.show({
	        title : '提示',
	        msg : '产品期限必须为整数'
	      });
	      return false;
	    }
	  }
	  if(auditLimit.length>5){
			$.messager.show({
				title : '提示',
				msg : '产品期限值输入过大！'
			});
		    return false;
		}
	// 审批额度上限 upperLimit
	var upperLimit = $(divName).find('input[name="upperLimit"]').val();
	if (!upperLimit) {
		$.messager.show({
			title : '提示',
			msg : '请填写审批额度上限'
		});
		return false;
	}
	var reg = new RegExp("^[0-9]*$");  
	if(!reg.test(upperLimit)){  
		strs=upperLimit.split(".")[1]==0
		if(strs==false){
			parent.$.messager.show({
				title : '提示',
				msg : '金额必须为整数'
			});
			return false;
		}
	}
	// 审批额度下限 floorLimit
	var floorLimit = $(divName).find('input[name="floorLimit"]').val();
	
	if (!floorLimit) {
		$.messager.show({
			title : '提示',
			msg : '请填写审批额度下限'
		});
		return false;
	}
	var reg = new RegExp("^[0-9]*$");  
	if(!reg.test(floorLimit)){  
		strs=floorLimit.split(".")[1]==0
		if(strs==false){
			parent.$.messager.show({
				title : '提示',
				msg : '金额必须为整数'
			});
			return false;
		}
	}
	
	// 判断审批额度是否小于0
	if (upperLimit < 0 || floorLimit < 0) {
		$.messager.show({
			title : '提示',
			msg : '审批额度不能小于0'
		});
		return false;

	}
	// 审批额度下限不能大于审批额度上限
	if (floorLimit - upperLimit > 0) {
		$.messager.show({
			title : '提示',
			msg : '审批额度下限不能大于审批额度上限'
		});
		return false;

	}
	return true;
}

var iState=$('#iState').val(false); // 定义全局变量用于来接收'是否启用'
iState=true;
function switchState() {
	$(function() {
		$('#addBMSProductAuditLimitInfoForm #isDisabled').switchbutton({
			checked : true,
			onChange : function(checked) {
				var iState=$('#iState').val(checked); 
				//iState = checked;
			}
		})
	})

	$(function() {
		$('#updateBMSProductAuditLimitInfoForm #isDisabled').switchbutton({
			checked : true,
			onChange : function(checked) {
				var iState=$('#iState').val(checked); 
				//iState = checked;
			}
		})
	})

}
// 保存审批期限信息
function saveBMSProductAuditLimitInfo() {
	// 必填校验
	var iState=$('#iState').val();
	if(iState==undefined){
		iState==true;	
	}
	var boo = BMSProductAuditLimitFromInfoExam('#addBMSProductAuditLimitInfoForm');
	if (!boo)
		return;
	// 获取输入的值
	var productId = $("#queryProductAuditLimitDiv #productId").val();
	var productCode = $("#queryProductAuditLimitDiv #productCode").val();
	var auditLimit = $('#addBMSProductAuditLimitInfo').find(
			'input[name="auditLimit"]').val();
	var upperLimit = $('#addBMSProductAuditLimitInfo').find(
			'input[name="upperLimit"]').val();
	var floorLimit = $('#addBMSProductAuditLimitInfo').find(
			'input[name="floorLimit"]').val();
	if (iState) {
		isDisabled = 0;
	} else {
		isDisabled = 1;
	}
	if(iState=="false"){
		isDisabled = 1;
	}else{
		isDisabled = 0;
	}
	$.ajax({
		url : 'addProductAuditLimit',
		data : {
			productId : productId,
			productCode : productCode,
			auditLimit : auditLimit,
			upperLimit : upperLimit,
			floorLimit : floorLimit,
			isDisabled : isDisabled
		},
		type : "POST",
		async : false, // 设置同步方式
		cache : false,
		success : function(result) {
			$.messager.progress('close');
			if(result.isFloorLimit==false){
				parent.$.messager.show({
					title : '提示',
					msg : '审批额度下限必须大于该产品额度下限！'
				});
			}
			if(result.isUpperLimit==false){
				parent.$.messager.show({
					title : '提示',
					msg : '审批额度上限必须小于该产品额度上限！'
				});
			}
			
			if(undefined!=result.isSuccess){
				if (result.isSuccess) {
					$.messager.show({
						title : '提示',
						msg : '保存成功！'
					});
					$('#addBMSProductAuditLimitInfo').window({
						modal : true,
						closed : true,
					});
					initDatagrid();
				} else {
					if(result.isRepeat){
						parent.$.messager.show({
							title : '提示',
							msg : '该产品期限已存在!'
						});
					}else{
						parent.$.messager.show({
							title : 'Error',
							msg : result
						});
					}

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

// 编辑初始化
function loadUpdateProductAuditLimitToWindow(auditLimitId) {
	$.ajax({
		url : 'updateProductAuditLimitInit',
		data : {
			auditLimitId : auditLimitId
		},
		type : "POST",
		success : function(result) {
			$('#updateBMSProductAuditLimitInfo').window({
				modal : true,
				closed : false,
			});
			$('#updateBMSProductAuditLimitInfo').form('clear');
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
	// 审批期限Id
	$('#updateBMSProductAuditLimitInfo').find('input[name="auditLimitId"]')
			.val(result.auditLimitId);
	// 审批期限
	$('#updateBMSProductAuditLimitInfo #auditLimit').textbox('setValue',
			result.auditLimit);
	// 审批期限上限
	$('#updateBMSProductAuditLimitInfo #upperLimit').textbox('setValue',
			result.upperLimit);
	// 审批期限下限
	$('#updateBMSProductAuditLimitInfo #floorLimit').textbox('setValue',
			result.floorLimit);
	//产品Id
	$('#updateBMSProductAuditLimitInfo #productId').textbox('setValue',
			result.productId);
	// 是否启用switchbutton
	var thisSwitchbuttonObj = $("#updateBMSProductAuditLimitInfo").find(
			"[switchbuttonName='isDisabled']");// 获取switchbutton对象
	var unitState;
	if (result.isDisabled == 0) {
		unitState = true;// 是否选中的值
		thisSwitchbuttonObj.switchbutton("check");
	} else if (result.isDisabled == 1) {
		unitState = false;
		thisSwitchbuttonObj.switchbutton("uncheck");
	}

}
function onBlur() {
	/*if (!iState) {*/
		/*var thisSwitchbuttonObj = $("#updateBMSProductAuditLimitInfo").find(
				"[switchbuttonName='isDisabled']");// 获取switchbutton对象
*/		$.messager.confirm('确认', '您确认想要把该期限设为禁用状态吗？如果继续改操作,该期限下面的门店和渠道也将被禁用!',
				function(r) {
					if (r) {
						return true;
					} else {
						return false;

					}
				});
	/*}*/
}

// 修改产品信息
function saveUpdateBMSProductAuditLimitInfo() {
	// 必填校验
	var iState=$('#iState').val();
	var boo = BMSProductAuditLimitFromInfoExam('#updateBMSProductAuditLimitInfoForm');
	if (!boo)
		return;
	// 获取输入的值
	var productId = $("#queryProductAuditLimitDiv #productId").val();
	var auditLimitId = $('#updateBMSProductAuditLimitInfo').find(
			'input[name="auditLimitId"]').val();
	var auditLimit = $('#updateBMSProductAuditLimitInfo').find(
			'input[name="auditLimit"]').val();
	var upperLimit = $('#updateBMSProductAuditLimitInfo').find(
			'input[name="upperLimit"]').val();
	var floorLimit = $('#updateBMSProductAuditLimitInfo').find(
			'input[name="floorLimit"]').val();
	var isDisabled;
	if (iState == "true") {
		isDisabled = 0;
	} else {
		isDisabled = 1;
	}
	
	$.ajax({
		   url : 'queryUpdateProducOnOff',
		   data : {
			   'auditLimitId':auditLimitId,
			   'upperLimit':upperLimit,
			   'floorLimit':floorLimit
		   },
		   type:"POST",
//		   async:false,  // 设置同步方式
//	       cache:false,
		   success : function(result){
		   		
			   if(result.ifTwoConflict==true){
				   $.messager.confirm('确认',result.returnStr,function(r){    
					    if (r){ 
					    	
					    	

							   if(isDisabled==1){
									$.messager.confirm('确认', '您确认想要把该期限设为禁用状态吗？如果继续改操作,该期限下面的门店和渠道也将被禁用!',
											function(r) {
										if(r){
									$.ajax({
										url : 'updateProductAuditLimit',
										data : {
											auditLimitId : auditLimitId,
											auditLimit : auditLimit,
											upperLimit : upperLimit,
											floorLimit : floorLimit,
											productId  : productId,
											isDisabled : isDisabled
										},
										type : "POST",
										async : false, // 设置同步方式
										cache : false,
										success : function(result) {
											
											if(result.isFloorLimit==false){
												parent.$.messager.show({
													title : '提示',
													msg : '审批额度下限必须大于该产品额度下限！'
												});
											}
											if(result.isUpperLimit==false){
												parent.$.messager.show({
													title : '提示',
													msg : '审批额度上限必须小于该产品额度上限！'
												});
											}
										
											if(undefined!=result.isSuccess){
												if (result.isSuccess) {
													$.messager.show({
														title : '提示',
														msg : '保存成功！'
													});
													$('#updateBMSProductAuditLimitInfo').window({
														modal : true,
														closed : true,
													});
													$('#updateBMSProductAuditLimitInfo').form('clear');
													initDatagrid();
												} else {
													if(result.isRepeat){
														parent.$.messager.show({
															title : '提示',
															msg : '该产品期限已存在!'
														});
													}else{
														parent.$.messager.show({
															title : 'Error',
															msg : result
														});
													}
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
										}else{
											return;
										}	
									});
									}else{
										$.ajax({
											url : 'updateProductAuditLimit',
											data : {
												auditLimitId : auditLimitId,
												auditLimit : auditLimit,
												upperLimit : upperLimit,
												floorLimit : floorLimit,
												productId :  productId,
												isDisabled : isDisabled
											},
											type : "POST",
											async : false, // 设置同步方式
											cache : false,
											success : function(result) {
												if(result.isFloorLimit==false){
													parent.$.messager.show({
														title : '提示',
														msg : '审批额度下限必须大于该产品额度下限！'
													});
												}
												if(result.isUpperLimit==false){
													parent.$.messager.show({
														title : '提示',
														msg : '审批额度上限必须小于该产品额度上限！'
													});
												}
												if(undefined!=result.isSuccess){
													if (result.isSuccess) {
														$.messager.show({
															title : '提示',
															msg : '保存成功！'
														});
														$('#updateBMSProductAuditLimitInfo').window({
															modal : true,
															closed : true,
														});
														$('#updateBMSProductAuditLimitInfo').form('clear');
														initDatagrid();
													}else {
														if(result.isRepeat){
															parent.$.messager.show({
																title : '提示',
																msg : '该产品期限已存在!'
															});
														}else{
															parent.$.messager.show({
																title : 'Error',
																msg : result
															});
														}
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
					    	
					    	
					    }
					});
			   }else{
				   
				   
				   

				   if(isDisabled==1){
						$.messager.confirm('确认', '您确认想要把该期限设为禁用状态吗？如果继续改操作,该期限下面的门店和渠道也将被禁用!',
								function(r) {
							if(r){
						$.ajax({
							url : 'updateProductAuditLimit',
							data : {
								auditLimitId : auditLimitId,
								auditLimit : auditLimit,
								upperLimit : upperLimit,
								floorLimit : floorLimit,
								productId  : productId,
								isDisabled : isDisabled
							},
							type : "POST",
							async : false, // 设置同步方式
							cache : false,
							success : function(result) {
								
								if(result.isFloorLimit==false){
									parent.$.messager.show({
										title : '提示',
										msg : '审批额度下限必须大于该产品额度下限！'
									});
								}
								if(result.isUpperLimit==false){
									parent.$.messager.show({
										title : '提示',
										msg : '审批额度上限必须小于该产品额度上限！'
									});
								}
							
								if(undefined!=result.isSuccess){
									if (result.isSuccess) {
										$.messager.show({
											title : '提示',
											msg : '保存成功！'
										});
										$('#updateBMSProductAuditLimitInfo').window({
											modal : true,
											closed : true,
										});
										$('#updateBMSProductAuditLimitInfo').form('clear');
										initDatagrid();
									} else {
										if(result.isRepeat){
											parent.$.messager.show({
												title : '提示',
												msg : '该产品期限已存在!'
											});
										}else{
											parent.$.messager.show({
												title : 'Error',
												msg : result
											});
										}
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
							}else{
								return;
							}	
						});
						}else{
							$.ajax({
								url : 'updateProductAuditLimit',
								data : {
									auditLimitId : auditLimitId,
									auditLimit : auditLimit,
									upperLimit : upperLimit,
									floorLimit : floorLimit,
									productId :  productId,
									isDisabled : isDisabled
								},
								type : "POST",
								async : false, // 设置同步方式
								cache : false,
								success : function(result) {
									if(result.isFloorLimit==false){
										parent.$.messager.show({
											title : '提示',
											msg : '审批额度下限必须大于该产品额度下限！'
										});
									}
									if(result.isUpperLimit==false){
										parent.$.messager.show({
											title : '提示',
											msg : '审批额度上限必须小于该产品额度上限！'
										});
									}
									if(undefined!=result.isSuccess){
										if (result.isSuccess) {
											$.messager.show({
												title : '提示',
												msg : '保存成功！'
											});
											$('#updateBMSProductAuditLimitInfo').window({
												modal : true,
												closed : true,
											});
											$('#updateBMSProductAuditLimitInfo').form('clear');
											initDatagrid();
										}else {
											if(result.isRepeat){
												parent.$.messager.show({
													title : '提示',
													msg : '该产品期限已存在!'
												});
											}else{
												parent.$.messager.show({
													title : 'Error',
													msg : result
												});
											}
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
				   
				   
			   }
			   
			   
			   
		   },
		   error:function(data){
		 		 $.messager.show({
						title: 'warning',
						msg: data.responseText
					});
		   }
	});
	
	
}
/*//----------------------模块START---------------*/
//删除产品模块
function deleteEnumCode(obj){
	$(obj).parent().remove();
}
//新增产品模块
function addProductCodeModule(){
	var addmoduleEnumCodeDiv = $("#moduleEnumCodeDiv").clone(true);
	addmoduleEnumCodeDiv.show();
	var id=$("#idVal").val()+1;
	addmoduleEnumCodeDiv.attr("id","addmoduleEnumCodeDiv"+id);
	$("#idVal").val(id)
	$("#productCodeModuleForm").children().last().append(addmoduleEnumCodeDiv);
}
function loadDeleteProductLimitWindows(auditLimitId){
	$.messager.confirm('确认','删除后相应的网点产品配置将失效！确定删除吗？',function(r){
		if (r){ 
			$.ajax({
				url : 'deleteProductTerm',
				data : {
					auditLimitId:auditLimitId
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
						initDatagrid();
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

function saveProductModuleCode(){
	/* * 以之前查询产品已有的code 比较 如果减少code在module中的需要更新状态删除为“1”
	   如果增加module中需要add*/
//	var productId=$("#productId").val();
	$.ajax({
		url : 'saveProductCodeModules',
		data :$("#productCodeModuleForm").serialize(),
		type : "POST",
		async : false, // 设置同步方式
		cache : false,
		success : function(result) {
			$.messager.progress('close');
			if (result.isSuccess) {
				$.messager.show({
					title : '提示',
					msg : '保存成功！'
				}); 
				//window.location.reload();
				//window.location.href="view?productId="+productId;
				//$("#productCodeModulePage").load(location.href="view?productId="+productId);
			} else {
				parent.$.messager.show({
					title : '提示',
					msg : "请输入产品名称!"
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
	
}
/*//----------------------模块END---------------*/