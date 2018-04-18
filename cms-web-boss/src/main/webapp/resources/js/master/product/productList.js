$(function() {
	//新增页面默认关闭
	initDatagrid();
	$("#addBMSProductInfo").window({inline:true});
	$("#updateBMSProductInfo").window({inline:true});
	//重写combobox
	$.extend($.fn.combobox.methods, { 
        selectedIndex: function (jq, index) { 
            if (!index) { 
                index = 0; 
            } 
            $(jq).combobox({ 
                onLoadSuccess: function () { 
                    var opt = $(jq).combobox('options'); 
                    var data = $(jq).combobox('getData');

                    for (var i = 0; i < data.length; i++) { 
                        if (i == index) { 
                            $(jq).combobox('setValue', eval('data[index].' + opt.valueField)); 
                            break; 
                        } 
                    } 
                } 
            }); 
        } 
    });
	 //下拉框:产品
	initMultCombobox("pro_productId","channeProduct/findProduct","productId","name",true);
});

$(window).keydown(function(event) {
    if(event.keyCode == 13) {
    	queryBMSProductInfo();
    }
});

function initDatagrid() {
		$("#new_productDatagrid").datagrid({
			onLoadSuccess:function(data){ 
				  if(data.total==0)
				  {
				    $.messager.show({
		                title:'结果',
		                msg:'没查到符合条件的数据！',
		                showType:'slide',
		            });
				  };
		     },
			url : 'product/listPage',
			striped : true,
			/*singleSelect : true,*/
			rownumbers : true,
			pagination : true,
			fitColumns : false,
			checkOnSelect: false,
			selectOnCheck: false,
			singleSelect: false,
			checkbox:true,
			fit:true,
			scrollbarSize : 0,
			columns : [ [  
			{
				field:'productId',
				checkbox:true
			},{
				field : 'code',
				title : '产品代码',
				width : 220,
				formatter:formatCodeOperations
			}, {
				field : 'name',
				title : '产品名称',
				width : 220,
			}, {
				field : 'floorLimit',
				title : '审批额度下限(元)',
				width : 220,
			}, {
				field : 'upperLimit',
				title : '审批额度上限(元)',
				width : 220,
			}, {
				field : 'adjustBase',
				title : '调整基数(元)',
				width : 220,
			},{
				field : 'rate',
				title : '产品费率(%)',
				formatter:rate_formatter,
				width : 220,
			},{
				field : 'preferentialRate',
				title : '优惠费率(%)',
				formatter:rate_formatter,
				width : 220,
			},{
				field:'debtRadio',
				title:'总负债率(%)',
				formatter:rate_formatter,
				width:220,
			},{
				field : 'operation',
				title : '操作',
				formatter : formatOperations,
				width : 250
			}
			] ]
		});
}

function queryBMSProductInfo() {
	var productIds="";
	var values = $('#pro_productId').combobox('getValues');
	for(var i=0;i<values.length;i++){
		if(i!=values.length-1){
			productIds=values[i]+""+","+productIds;
		}else{
			productIds=productIds+values[i];
		}
	}
	var queryParams={"productIds":productIds};
	setFirstPage("#new_productDatagrid");
	$('#new_productDatagrid').datagrid('options').queryParams = queryParams;
	$("#new_productDatagrid").datagrid('reload');
}
function setFirstPage(ids){
    var opts = $(ids).datagrid('options');
    var pager = $(ids).datagrid('getPager');
    opts.pageNumber = 1;
    opts.pageSize = opts.pageSize;
    pager.pagination('refresh',{
	pageNumber:1,
	pageSize:opts.pageSize
    });
}

//弹出新增产品窗口
function addBMSProductInfo() {
	$('#addBMSProductInfo').window({
		modal:true,
		closed:false,
	});
	$('#addBMSProductInfo').form('clear');
};

function closeWindow() {
	$('#addBMSProductInfo').window({
		modal:true,
		closed:true,
	});
	$('#addBMSProductInfo').form('clear');
	$("#new_productDatagrid").datagrid('reload');
}

function closeUpdateWindow() {
	$('#updateBMSProductInfo').window({
		modal:true,
		closed:true,
	});
	$("#new_productDatagrid").datagrid('reload');
}

//保存渠道信息
function saveBMSProductInfo() {
	//必填校验
	var boo = BMSProductFromInfoExam('#addBMSProductInfoForm');
	if(!boo) return;

	$.ajax({
		url : 'product/addProduct',
		data : $("#addBMSProductInfoForm").serialize(),
		type:"POST",
		async:false,  // 设置同步方式
		cache:false,
		success : function(result){
			$.messager.progress('close');
			if(result.isExisted==false){
				parent.$.messager.show({
					title : '提示',
					msg : '产品代码已经存在！' 

				});
			}
			if(undefined!=result.isSuccess){
				if(result.isSuccess){
					$.messager.show({
						title : '提示',
						msg : '保存成功！'
					});
					$('#addBMSProductInfo').window({
						modal:true,
						closed:true,
					});
					$("#new_productDatagrid").datagrid('reload');
					$('#pro_productId').combobox('reload');
				}else{
					parent.$.messager.show({
						title: 'Error',
						msg: result
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

//新增校验
function BMSProductFromInfoExam(divName) {
	//产品代码
	var code = $(divName).find('input[name="code"]').val();
	if(!code) {
		$.messager.show({
			title:'提示',
			msg:'请填写产品代码'
		});
		return false;
	}
	var reg = new RegExp("^[0-9]*$");  
	if(!reg.test(code)){  
		strs=code.split(".")[1]==0
		if(strs==false){
			parent.$.messager.show({
				title : '提示',
				msg : '产品代码只能是数字！'
			});
			return false;
		}
	}
	
	//产品名称
	var productName = $(divName).find('input[name="name"]').val();
	if(!productName) {
		$.messager.show({
			title:'提示',
			msg:'请填写产品名称'
		});
		return false;
	}
	if(productName.length>50){
		$.messager.show({
			title : '提示',
			msg : '产品名称过长,请重新输入！'
		});
	    return false;
	}
	
	//审核额度下限
	var floorLimit = $(divName).find('input[name="floorLimit"]').val();
	if(!floorLimit) {
		$.messager.show({
			title:'提示',
			msg:'请填写产品额度下限'
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
	//发售起止日
	var upperLimit = $(divName).find('input[name="upperLimit"]').val();
	if(!upperLimit) {
		$.messager.show({
			title:'提示',
			msg:'请填写产品额度上限'
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
	var rate=$(divName).find('input[name="rate"]').val();
	if(!rate) {
		$.messager.show({
			title:'提示',
			msg:'请填写产品费率'
		});
		return false;
	}
	var reg=new RegExp("^(([0-9]+)|([0-9]+\.[0-9]{1,2}))$");
	if(!reg.test(rate) || rate<=0 || rate>=100){
		$.messager.show({
			title:'提示',
			msg:'产品费率错误'
		});
		return false;
	}
	var debtRation=$(divName).find('input[name="debtRadio"]').val();
	if(!debtRation) {
		$.messager.show({
			title:'提示',
			msg:'请填写产品总负债率'
		});
		return false;
	}
	var reg=new RegExp("^(([0-9]+)|([0-9]+\.[0-9]{1,2}))$");
	if(!reg.test(debtRation) || debtRation<=0 || debtRation>=100){
		$.messager.show({
			title:'提示',
			msg:'产品总负债率错误'
		});
		return false;
	}
	
	var preferentialRate=$(divName).find('input[name="preferentialRate"]').val();
	if(!preferentialRate) {
		$.messager.show({
			title:'提示',
			msg:'请填写产品优惠债率'
		});
		return false;
	}
	var reg=new RegExp("^(([0-9]+)|([0-9]+\.[0-9]{1,2}))$");
	if(!reg.test(preferentialRate) || preferentialRate<=0 || preferentialRate>=100){
		$.messager.show({
			title:'提示',
			msg:'产品优惠费率错误'
		});
		return false;
	}
	
	var adjustBase = $(divName).find('input[name="adjustBase"]').val();
	if(!adjustBase) {
		$.messager.show({
			title:'提示',
			msg:'请填写调整基数'
		});
		return false;
	}
	var reg = new RegExp("^[0-9]*$");  
	if(!reg.test(adjustBase)){  
		strs=adjustBase.split(".")[1]==0
		if(strs==false){
			parent.$.messager.show({
				title : '提示',
				msg : '金额必须为整数'
			});
			return false;
		}
	}
	if(floorLimit<=0||upperLimit<=0||adjustBase<=0){
		$.messager.alert("操作提示", "请确保你输入的产品额度上、下限和调整基数大于0！");
		/*$.messager.show({
			title:'提示',
			msg:'请确保你输入的产品额度上、下限和调整基数大于0'
		});*/
		return false;
	}
	if((floorLimit-upperLimit)>0){
		$.messager.alert("操作提示", "输入的产品额度下限不能高于产品额度上限！");
		return false;
	}
	/*if(((floorLimit-upperLimit)%adjustBase)==0){
		$.messager.alert("操作提示", "产品上限和下限的差值必须能被调整基数整除！");
		return false;
	}*/
	return true;
}
//编写正则,用于表单验证
function IsNum(num){
	 var reNum=/^\d*$/;
	 return(reNum.test(num));
	}

function formatOperations(value,row,index) {
	
	var operations = '';
	var productId = row.productId;
	operations += '<a href="javascript:void(0)" onclick="loadUpdateProductToWindow('+row.productId+')">修改 &nbsp;&nbsp;</a>';
    return operations;
 
 };
 //用于弹出产品的期限子页面
 function formatCodeOperations(value,row,index) {
		
		var operations = '';
		var productId = row.productId;
		var code=row.code;
		var bb=row.name;
		operations += '<a href="javascript:void(0)" onclick="loadProductAuditLimitToWindow('+productId+')">'+value+'</a>';
	    return operations;
};

function loadProductAuditLimitToWindow(productId){
	
	var url='productAuditLimit/view';
	var params='?';
	if(productId!=''){
		params+='productId='+productId;
	}else{
		params='';
	}
	url+=params;
	/*window.open ("productAuditLimit/view", "newwindow","toolbar=yes,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,fullscreen=3");*/
	/*window.location.href="productAuditLimit/view";*/
	
	window.open(url);
	 
	 
}

 function getRow(){  
       var rows = $('#new_productDatagrid').datagrid('getChecked');  
       /*for(var i=0; i<rows.length; i++){  
           alert('Item ID:'+rows[i].productId+"\nPrice:"+rows[i].name);  
       }  */
   } 
 
function deleteBMSProductInfo() {
	/*alert('new_datagrid');*/
    var rows = $('#new_productDatagrid').datagrid('getChecked');  
    if(rows.length>0){
    $.messager.confirm('确认','您确认想要永久的删除选中的'+(rows.length)+'条产品信息吗？',function(r){
    	if (r){ 
    	var num=0;
    	var nums=0;
    for(var i=0; i<rows.length; i++){  
       /* alert('Item ID:'+rows[i].productId+"\nPrice:"+rows[i].name); */ 
        $.ajax({
            url : 'product/deleteProduct',
            data : {productId:rows[i].productId},
            async:false, 
            success : function(result){
            	if(result.notSuccess==false){
            		nums=nums+1;
            	}
            if(undefined!=result.isSuccess){
           	 if(result.isSuccess){
           		 num=num+1;
           	 }else{
           		 parent.$.messager.show({
           			 title: 'Error',
           			 msg: result
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
    if(nums>0 && num==0){
    	parent.$.messager.show({
    		title : '提示',
    		msg : '该产品下有期限产品内容不能删除！'
    	});
    	$("#new_productDatagrid").datagrid('reload');
    }
    if(num>0 && nums==0){
    	$.messager.show({
    		title : '提示',
    		msg : '删除成功！'
    	});

    	$("#new_productDatagrid").datagrid('reload');
    	$('#pro_productId').combobox('reload');
    }
    if(num>0 && nums>0){
    	$.messager.show({
    		title : '提示',
    		msg : '部分产品删除成功！'
    	});
    	$("#new_productDatagrid").datagrid('reload');
    	$('#pro_productId').combobox('reload');
    }

    	}
    });
    }else{
    	$.messager.alert("操作提示", "删除请至少选择一条数据！");
    }
}
//编辑初始化
function loadUpdateProductToWindow(productId) {
	 $.ajax({
         url : 'product/updateProductInit',
         data : {productId:productId},
         type:"POST",
         success : function(result){
        	 $('#updateBMSProductInfo').window({
        			modal:true,
        			closed:false,
        		});
        		$('#updateBMSProductInfo').form('clear');
         		stuffUpdatePage(result.info);
         },
         error:function(data){
        	 $.messager.show({
					title: 'warning',
					msg: data.responseText
				});
         }
	 });
}
//编辑填充
function stuffUpdatePage(result) {
	$('#updateBMSProductInfo').find('input[name="productId"]').val(result.productId);
	$('#updateBMSProductInfo #name').textbox('setValue',result.name);
	$('#updateBMSProductInfo #code').textbox('setValue',result.code);
	$('#updateBMSProductInfo #floorLimit').textbox('setValue',result.floorLimit);
	$('#updateBMSProductInfo #upperLimit').textbox('setValue',result.upperLimit);
	$('#updateBMSProductInfo #rate').textbox('setValue',Math.round(result.rate*100*100)/100);
	$('#updateBMSProductInfo #adjustBase').textbox('setValue',result.adjustBase);
	$('#updateBMSProductInfo #debtRadio').textbox('setValue',Math.round(result.debtRadio*100*100)/100);
	
	$('#updateBMSProductInfo #preferentialRate').textbox('setValue',Math.round(result.preferentialRate*100*100)/100);
 }

//修改产品信息
function saveUpdateBMSProductInfo() {
	//必填校验
	var boo = BMSProductFromInfoExam('#F_updateBMSProductInfoForm');
	if(!boo) return;
	
	
	//查询判断修改产品上下线是否会造成产品期限和网店冲突
	$.ajax({
		   url : 'product/queryUpdateOnOff',
		   data : $("#F_updateBMSProductInfoForm").serialize(),
		   type:"POST",
		   async:false,  // 设置同步方式
	       cache:false,
		   success : function(result){
			   
				if(result.ifTwoConflict==true){
				
						
						$.messager.confirm('确认',result.returnStr,function(r){    
						    if (r){ 
						    	$.ajax({
						 		   url : 'product/updateProduct',
						 		   data : $("#F_updateBMSProductInfoForm").serialize(),
						 		   type:"POST",
						 		   async:false,  // 设置同步方式
						 	       cache:false,
						 		   success : function(result){
						 		   		if(result.isSuccess){
						 		   			$.messager.show({
						 						title : '提示',
						 						msg : '保存成功！'
						 					});
						 		   		 $('#updateBMSProductInfo').window({
						 	        			modal:true,
						 	        			closed:true,
						 	        		});
						 		   			$('#updateBMSProductInfo').form('clear');
						 		   		     $("#new_productDatagrid").datagrid('reload');
						 					$('#pro_productId').combobox('reload');
						 		   			
						 		   		}else{
						 		   			parent.$.messager.show({
						 						title: 'Error',
						 						msg: result
						 					});
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
						});
				
					
				}else{
					
					$.ajax({
				 		   url : 'product/updateProduct',
				 		   data : $("#F_updateBMSProductInfoForm").serialize(),
				 		   type:"POST",
				 		   async:false,  // 设置同步方式
				 	       cache:false,
				 		   success : function(result){
				 		   		if(result.isSuccess){
				 		   			$.messager.show({
				 						title : '提示',
				 						msg : '保存成功！'
				 					});
				 		   		 $('#updateBMSProductInfo').window({
				 	        			modal:true,
				 	        			closed:true,
				 	        		});
				 		   			$('#updateBMSProductInfo').form('clear');
				 		   		     $("#new_productDatagrid").datagrid('reload');
				 		   		 $('#pro_productId').combobox('reload');
				 		   		}else{
				 		   			parent.$.messager.show({
				 						title: 'Error',
				 						msg: result
				 					});
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
				
				
			   
		   },
		   error:function(data){
		 		 $.messager.show({
						title: 'warning',
						msg: data.responseText
					});
		   }
	});
	
	
	  
}

function rate_formatter(value, row, index){
	  if(value!=null){
		  var rate =Math.round(value*100*100)/100;
	  }
		 return rate;

	}
