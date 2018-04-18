var successFlag =false;
$(function() {
		//初始化
		unDoneInitDatagrid();
		doneInitDatagrid();
		// 渠道配置进来
		$('#loanAuditTT').tabs({
		    border:false,
		    onSelect:function(title,index){
		    	
		    	if(title.substring(title.length-4)=='待办任务'){
		 			$("#bacthPassAuditBtn").show();
		 			$("#bacthBackAuditBtn").show();
		 			$("#exportLoanAuditBtn").show();
		 			
		    		if(successFlag){
		    			query_loanAudit_Info();
		    			$(".loanAudit_doneDate").hide();
			    		$(".loanAudit_undoneDate").show();
		    		}
		    	}else if(title.substring(title.length-3)=="已完成"){
		    		
		    		$("#bacthPassAuditBtn").hide();
		    		$("#bacthBackAuditBtn").hide();
		    		$("#exportLoanAuditBtn").hide();
		    		if(successFlag){
		    			query_loanAudit_Info();
		    			$(".loanAudit_doneDate").show();
			    		$(".loanAudit_undoneDate").hide();
			    		
		    		}
		    	}
		    }
		});
	//退回备注字数限制
	$("#loanAudit_remark").keyup(function(){
		 var maxLength = 100;  
         var len = $("#loanAudit_remark").val().length;  
         $('#loanAudit_remarkCount').html(maxLength - len);  
         if(parseInt($('#loanAudit_remarkCount').text()) < 0){  
             $('#loanAudit_remarkCount').html('0');  
             var res = $(this).val().substring(0,100);  
             $(this).val(res);  
         }  
	});
	
});

$(window).keydown(function(event) {
    if(event.keyCode == 13) {
    	query_loanAudit_Info();
    }
});


function unDoneInitDatagrid() {
	$("#unDoneLoanAudit_datagrid").datagrid({
		onLoadSuccess : function(data) {
			if(!successFlag){
				$(".loanAudit_doneDate").hide();
				
			}
			unDoneLoanAuditOnLoad($("#unDoneLoanAudit_datagrid"));
			successFlag=true;
			if (data.total == 0) {
				var title = $('.tabs-selected').text();
				if(title.substring(title.length-4)=="待办任务"){
				$.messager.show({
					title : '结果',
					msg : '没查到符合条件的数据！',
					showType : 'slide',
				})
				};
			}

		},
		idField : 'id',
		url : 'loanAudit/listPage',
		striped : true,
		singleSelect : false,
		rownumbers : true,
		pagination : true,
		checkOnSelect : false,
		selectOnCheck : false,
		fitColumns : false,
		autoRowHeight : true,
/*		checkbox : true,*/
		fit : true,
		columns : [ [ {
			field : 'id',
			checkbox : true,
			width : 30
		}, {
			field : 'identificationCase',
			title : '案件标识',
			align : 'center',
			width : 100,
			formatter : function(value,rowData,rowIndex){
				var html = '';
				if(rowData.ifPri && rowData.ifPri == '1'){//加急
					html += '<img name="img_urgent" title="加急" class="easyui-tooltip"  src="resources/images/identificationCase/urgent.png"></img>';
				} 
				if(rowData.appInputFlag && rowData.appInputFlag == 'app_input'){//app进件
					html += '<img name="img_appPart" title="app进件" class="easyui-tooltip"  src="resources/images/identificationCase/appPart.png"></img>';
				}  
				if(rowData.ifPreferentialUser && rowData.ifPreferentialUser == 'Y'){//费率优惠客户
					html += '<img name="img_reLoan" title="费率优惠客户" class="easyui-tooltip"  src="resources/images/identificationCase/reLoan.jpg"></img>';
				}
				/*if(rowData.ifSuspectCheat && rowData.ifSuspectCheat == 'Y'){//疑似欺诈
					html += '<img src="resources/images/identificationCase/fraudRules.png"></img>';
				}*/
				return html;
			}
		},
		{
			field : 'loanNo',
			title : '借款编号',
			width : 160,
		},
		{
			field : 'owningBranchId',
			title : '进件营业部ID',
			hidden:true,
		},
		{
			field : 'channelCd',
			title : '渠道code',
			width : 160,
			hidden:true,
		},
		{
			field : 'productCd',
			title : '产品code',
			width : 160,
			hidden:true,
		},
		{
			field : 'channelName',
			title : '渠道名称',
			width : 100,
		},  {
			field : 'productName',
			title : '产品名称',
			width : 100,
		}, {
			field : 'pactMoney',
			title : '合同金额',
			width : 120,
		}, 
		{
			field : 'contractLmt',
			title : '签约金额',
			width : 120,
			hidden:true,
		}, 
		 {
			field : 'contractTrem',
			title : '签约期限',
			width : 100,
		}, 
		{
			field : 'name',
			title : '客户姓名',
			width : 100,
		},
		{
			field : 'idNo',
			title : '身份证号码',
			width : 160,
		},{
			field : 'applyBankName',
			title : '银行名称',
			width : 130,
		},
		/*{
			field : 'applyBankBranch',
			title : '开户银行',
			width : 150,
		}, */
		{
			field : 'applyBankCardNo',
			title : '银行卡号',
			width : 160,
		},	
		/*{
			field : 'bankPhone',
			title : '手机号码',
			width : 120,
		},*/
		
		/*{
			field : 'contractBranch',
			title : '签约网点',
			width : 200,
		},*/
	
		/*{
			field : 'applyType',
			title : '申请类型',
			width : 120,
		},*/
	
		{
			field : 'applyInputFlag',
			title : '交件类型',
			formatter : applyInputFlag_fm,
			width : 120,
		}, {
			field : 'operation',
			title : '操作',
			formatter :format_loanAudit_Operations,
			width : 200
		}] ]
	});
}


function applyInputFlag_fm(value, row, index){
	 
    if (value == "applyInput") {
    	 return '营业部';
    }
    else if(value == "directApplyInput"){
    	 return '直通车';
    }else{
        return '';
    }
    
}

function doneInitDatagrid() {
	$("#doneloanAudit_datagrid").datagrid({
		onLoadSuccess : function(data) {
			successFlag=true;
			var title = $('.tabs-selected').text();
			if (data.total == 0) {
				if(title.substring(title.length-3)=="已完成"){
				$.messager.show({
					title : '结果',
					msg : '没查到符合条件的数据！',
					showType : 'slide',
				});
				}
			}
			;
		},
		url : "",
		border : false,
		fitColumns: false,
		singleSelect : true,
		pagination : true,
		striped : true,
		rownumbers : true,
		fit : true,
		columns : [ [ {
			field : 'identificationCase',
			title : '案件标识',
			align : 'center',
			width : 100,
			formatter : function(value,rowData,rowIndex){
				var html = '';
				if(rowData.ifPri && rowData.ifPri == '1'){//加急
					html += '<img  title="加急" class="easyui-tooltip"  src="resources/images/identificationCase/urgent.png"></img>';
				} 
				if(rowData.appInputFlag && rowData.appInputFlag == 'app_input'){//app进件
					html += '<img title="app进件" class="easyui-tooltip" src="resources/images/identificationCase/appPart.png"></img>';
				}  
				if(rowData.ifPreferentialUser && rowData.ifPreferentialUser == 'Y'){//费率优惠客户
					html += '<img name="img_reLoan" title="费率优惠客户" class="easyui-tooltip"  src="resources/images/identificationCase/reLoan.jpg"></img>';
				}
				/*if(rowData.ifSuspectCheat && rowData.ifSuspectCheat == 'Y'){//疑似欺诈
					html += '<img src="resources/images/identificationCase/fraudRules.png"></img>';
				}*/
				return html;
			}
		},
		{
			field : 'loanNo',
			title : '借款编号',
			width : 200,
		}, {
			field : 'channelName',
			title : '渠道名称',
			width : 100,
		},  {
			field : 'productName',
			title : '产品名称',
			width : 100,
		}, {
			field : 'pactMoney',
			title : '合同金额',
			width : 120,
			formatter : function(value,rowData,rowIndex){
				var pactMoneyStr = '';
				if(rowData.version > 999){//老数据
					pactMoneyStr=rowData.contractLmt;
				}else{//新数据
					pactMoneyStr =value;
				}  
				return pactMoneyStr;
			}
		}, 
		 {
			field : 'contractTrem',
			title : '签约期限',
			width : 100,
		}, 
		{
			field : 'name',
			title : '客户姓名',
			width : 100,
		},
		{
			field : 'idNo',
			title : '身份证号码',
			width : 180,
		},{
			field : 'applyBankName',
			title : '银行名称',
			width : 150,
		},
		/*{
			field : 'applyBankBranch',
			title : '开户银行',
			width : 150,
		}, */
		{
			field : 'applyBankCardNo',
			title : '银行卡号',
			width : 170,
		},	
		/*{
			field : 'bankPhone',
			title : '手机号码',
			width : 120,
		},*/
		
		/*{
			field : 'contractBranch',
			title : '签约网点',
			width : 200,
		},
	
		{
			field : 'applyType',
			title : '申请类型',
			width : 120,
		},*/
	
		{
			field : 'applyInputFlag',
			title : '交件类型',
			formatter : applyInputFlag_fm,
			width : 120,
		},
		{
			field : 'oprateType',
			title : '操作结果',
			width : 120,
		}]  ]
	});
}






/**
 * 查询函数
 */
function query_loanAudit_Info() {
	var title = $('#loanAuditTT').tabs('getSelected').panel('options').title;
	var loanNos = $('#queryLoanAuditDiv').find('input[name="loanNos"]').val();
	var name = $('#queryLoanAuditDiv').find('input[name="name"]').val();
	var idNo = $('#queryLoanAuditDiv').find('input[name="idNo"]').val();
	var channelCd = $('#loanAudit_channelId').combobox('getValue');
	var productId = $('#loanAudit_productId').combobox('getValue');
	var applyInputFlag = $('#loanAudit_applyInputFlag').combobox('getValue');
	var orgAuditState = $('#loanAudit_orgAuditState').combobox('getValue');
	//参数校验

	if(title =="待办任务"){
		var confirmDate = $("#loanAudit_confirmDate").datebox("getValue");
		var confirmDate2=$("#loanAudit_confirmDate2").datebox('getValue');
		var queryParams = $('#unDoneLoanAudit_datagrid').datagrid('options').queryParams;
		queryParams.loanNos=loanNos;
		queryParams.channelCd = channelCd;
		queryParams.productId = productId;
		queryParams.name=name;
		queryParams.idNo=idNo;
		queryParams.applyInputFlag=applyInputFlag;
		queryParams.confirmDate=confirmDate;
		queryParams.confirmDate2=confirmDate2;
		queryParams.orgAuditState =orgAuditState;
		setloanAudit_FirstPage("#unDoneLoanAudit_datagrid");
		$('#unDoneLoanAudit_datagrid').datagrid('options').queryParams = queryParams;
		$("#unDoneLoanAudit_datagrid").datagrid('reload',queryParams);
	}else if(title=="已完成"){
		var queryCount = $("#queryLoanAuditDiv").find('input[name="queryCount"]').val();
		if(queryCount == "0"){
			$("#queryLoanAuditDiv").find('input[name="queryCount"]').val(queryCount+1);
			$("#loanAudit_financeAuditDate").datebox("setValue",getDateBefore(2));
			 $("#loanAudit_financeAuditDate2").datebox('setValue',getDateBefore(0));
		}
		var financeAuditDate = $("#loanAudit_financeAuditDate").datebox("getValue");
		var financeAuditDate2 = $("#loanAudit_financeAuditDate2").datebox('getValue');
		var queryParams2 = $('#doneloanAudit_datagrid').datagrid('options').queryParams;
		var queryParams2 = {};
		queryParams2.loanNos=loanNos;
		queryParams2.channelCd = channelCd;
		queryParams2.productId = productId;
		queryParams2.name=name;
		queryParams2.idNo=idNo;
		queryParams2.applyInputFlag=applyInputFlag;
		queryParams2.financeAuditDate=financeAuditDate;
		queryParams2.financeAuditDate2=financeAuditDate2;
		queryParams2.orgAuditState =orgAuditState;
		queryParams2.queryCount=queryCount;
		/*queryParams2.signDate=signDate;*/
		/*queryParams2.contractBranchId=contractBranchId;*/
		setloanAudit_FirstPage("#doneloanAudit_datagrid");
		$("#doneloanAudit_datagrid").datagrid('options').url='loanAudit/doneListPage';
		$('#doneloanAudit_datagrid').datagrid('options').queryParams = queryParams2;
		$("#doneloanAudit_datagrid").datagrid('reload',queryParams2);
	}
	
}
/**
 * 设置查询分页信息
 * 
 * @param ids
 */
function setloanAudit_FirstPage(ids) {
	var opts = $(ids).datagrid('options');
	var pager = $(ids).datagrid('getPager');
	opts.pageNumber = 1;
	opts.pageSize = opts.pageSize;
	pager.pagination('refresh', {
		pageNumber : 1,
		pageSize : opts.pageSize
	});
}

 /* 操作函数
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function format_loanAudit_Operations(value, row, index) {
		var operations = '';
		var name= row.name;
		var loanStr=row.loanNo;
		var owningBranchId=row.owningBranchId;
		var channelCd=row.channelCd;
		var productCd=row.productCd;
		var contractTrem=row.contractTrem;
		var contractLmt=row.contractLmt;
		if('00016' == channelCd){
			//老财宝渠道，无通过按钮
		} else {
			operations += '<a href="javascript:void(0)" onclick="passAudit('+ row.id  
			+','+ "'"+loanStr+ "'" +','+"'"+owningBranchId+ "'" +','+"'"+channelCd+ "'" 
			+','+"'"+productCd+ "'"+','+"'"+contractTrem+ "'"+','+"'"+contractLmt+ "'"+')">通过 &nbsp;&nbsp;</a>';
		}
		operations += '<a href="javascript:void(0)" onclick="openBackAuditW('+ row.id  
		+','+"'"+loanStr+ "'" +',' + "'"+name+"'"+',' + "'"+channelCd+"'"+ ')">退回&nbsp;&nbsp;</a>';
		
		operations += '<a href="javascript:void(0)" onclick="showAuditFiledataDialog('
		+"'"+loanStr+ "'" +')">附件 &nbsp;&nbsp;</a>';
		
		operations += '<a href="javascript:void(0)" onclick="openLoanLogW('
			+ row.id +','+"'"+loanStr+ "'" + ')">日志 &nbsp;&nbsp;</a>';
	
	return operations;

};


function showAuditFiledataDialog(loanNo) {
	var nodeKey = "credit";
	  var userName = $("#userName").val();
	  var userCode = $("#userCode").val();
	  var picFileDataUrl =$("#picFileDataUrl").val();
	var url = picFileDataUrl+"?nodeKey="+nodeKey+"&sysName=bms&appNo="+loanNo+"&operator="+userName+"&jobNumber="+userCode;
	window.open(url);
  /*  var content = '<iframe src="' + url + '" width="100%" height="99%" frameborder="0" scrolling="yes"></iframe>';  
    var boarddiv = '<div id="auditFileDatewindow" title="' + "附件预览" + '"></div>'//style="overflow:hidden;"可以去掉滚动条  
    $(document.body).append(boarddiv);  
    var win = $('#auditFileDatewindow').window({  
        content: content,  
        width: 700,  
        height: 800,  
        modal: true,  
        title: "附件预览",  
        onClose: function () {  
            $(this).dialog('destroy');//后面可以关闭后的事件  
        }  
    });  
    win.dialog('open');  */
}  



function PrefixInteger(num, n) {
    return (Array(n).join(0) + num).slice(-n);
}

function passAudit(id,loanNo,owningBranchId,channelCd,productCd,contractTrem,contractLmt) {
	$.messager.confirm('放款审核通过确认','放款审核通过，将进入放款确认列表！', function(r) {
		if (r) {
			$.ajax({
				url : 'loanAudit/passLoanAudit.do',
				data : {
					'id' : id,
					'loanNo':loanNo,
					'owningBranchId':owningBranchId,
					'channelCd':channelCd,
					'productCd':productCd,
					'contractTrem':contractTrem,
					'contractLmt':contractLmt,
				},
				async:false,  // 设置同步方式
				success : function(result) {
					if (result.isSuccess) {
						$.messager.show({
							title : '提示',
							msg : result.result
						});

					} else {
						//alert(result);
						parent.$.messager.show({
							title : '提示',
							msg : result.result
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
		$("#unDoneLoanAudit_datagrid").datagrid('reload');
		$("#doneloanAudit_datagrid").datagrid('reload');
		$("#unDoneLoanAudit_datagrid").datagrid('clearChecked');
		/*$('#unDoneLoanAudit_datagrid').datagrid('clearSelections');  */ 
	});
	

}

function openLoanLogW(id,loanNo) {
	$('#auditLogInfo').window({
		modal : true,
		closed : false,
	});
	$('#audit_LoanLog').datagrid({
    url:'loanAudit/findloanLog?loanNo='+loanNo,
    columns:[[
		{field:'operationModule',title:'环节',width:70},
		{field:'operationType',title:'操作',width:50},
		{field:'operator',title:'操作人',width:70},
		{field:'operationTime',title:'操作时间',formatter:date_formatter,width:150},
		{field:'remark',title:'备注详情',width:120},
		{field:'firstLevleReasons',title:'备注原因',width:120}
    ]]
});
	
}


function date_formatter(value, row, index){
		var oldTime = new Date(value).format("yyyy-MM-dd hh:mm:ss");
    	 return oldTime;;
  
}

function openBacthAuditBackW(){
	$('#backAuditInfo').window({
		modal : true,
		closed : false,
	});
	$('#backAuditInfo').form('clear');
	$("#backAudit_isBacth").val(true);
	$(".loanAudit_name").hide()
}

function openBackAuditW(id,loanNo,name,channelCd) {
	$('#backAuditInfo').window({
		modal : true,
		closed : false,
	});
	$('#backAuditInfo').form('clear');
	$(".loanAudit_name").show();
	$("#backAudit_isBacth").val(false);
	$("#backAudit_loanNo").val(loanNo);
	$("#loanAudit_name").html(name);
	$("#backAudit_id").val(id);
	$("#backAudit_channelCd").val(channelCd);
}

function backAudit(){
	
	var vilidFlag=auditBackFromInfoExam("#backAuditInfo");
	if(!vilidFlag){
		return;
	}
	if($("#backAudit_isBacth").val()=='true'){
		bacthBackLoanAudit();
		return;
	}
	var param ={};
	param.firstLevleReasons =$("#backAudit_oneReason").combobox('getText');
	param.firstLevleReasonsCode=$("#backAudit_oneReason").combobox('getValue');
	
	param.twoLevleReasonsCode=$("#backAudit_twoReason").combobox('getValue');
	param.twoLevleReasons =$("#backAudit_twoReason").combobox('getText');

	param.loanNo=$("#backAudit_loanNo").val();
	param.remark=$("#loanAudit_remark").val();
	param.id=$("#backAudit_id").val();
	param.channelCd=$("#backAudit_channelCd").val();
	$.messager.confirm('放款审核退回确认','放款审核退回，将进入退件箱列表！', function(r) {
	if (r) {
	 $.ajax({
		   url : 'loanAudit/backAudit.do',
		   data : param,
		   type:"POST",
		   async:false,  // 设置同步方式
	       cache:false,
		   success : function(result){ 
			   $.messager.progress('close');
		   		if(result.isSuccess){
		   			$.messager.show({
						title : '提示',
						msg : result.result
					});
		   		 
		   		}else{
		   			$.messager.show({
						title : '提示',
						msg : result.result
					});
		   		}
		   		
		   },
		   error:function(data){
		 		 $.messager.show({
						title: '提示',
						msg: data.responseText
					});
		   }
	});	
	 	common_closeWindow('#backAuditInfo') ;
		$("#unDoneLoanAudit_datagrid").datagrid('reload');
		$("#doneloanAudit_datagrid").datagrid('reload');
		$("#unDoneLoanAudit_datagrid").datagrid('clearChecked');
	}
	})
	
}

function bacthBackLoanAudit() {
	var rows = $('#unDoneLoanAudit_datagrid').datagrid('getChecked');
	if (rows.length == 0) {
		$.messager.show({
			title : '提示',
			msg : '请至少选择一条数据'
		});
		return;
	};
	var param={};
	var ids = '';
	var loanNos='';
	var channelCds='';
	var errorCheckAite = false;
	for ( var i in rows) {
		if(rows[i].id &&rows[i].loanNo && rows[i].channelCd ){
			/*if('00016' == rows[i].channelCd){
				errorCheckAite = true;
				break;
			}*/
			ids += rows[i].id + ',';
			loanNos+=rows[i].loanNo + ',';
			channelCds+=rows[i].channelCd + ',';
		}
	}
	param.firstLevleReasons =$("#backAudit_oneReason").combobox('getText');
	param.firstLevleReasonsCode=$("#backAudit_oneReason").combobox('getValue');
	param.twoLevleReasonsCode=$("#backAudit_twoReason").combobox('getValue');
	param.twoLevleReasons =$("#backAudit_twoReason").combobox('getText');
	param.loanNo=$("#backAudit_loanNo").val();
	param.remark=$("#loanAudit_remark").val();
	param.ids=ids;
	param.loanNos=loanNos;
	param.channelCds=channelCds;
	if(errorCheckAite){
		$.messager.show({
			title : '提示',
			msg : '不可勾选捞财宝渠道数据，进行批量退回！'
		});
		$("#unDoneLoanAudit_datagrid").datagrid('clearChecked');
		return;
	}

	$.messager.confirm('确认', '确认批量退回选中的' + rows.length + '条吗？', function(r) {
		if (r) {
			$.ajax({
				url : 'loanAudit/bacthBackLoanAudit.do',
				data : param,
				async:false,
				success : function(result) {
					if (result.isSuceess) {
						$.messager.show({
							title : '提示',
							width:450,
							height:280,
							msg : result.result
						});
					} else {
						parent.$.messager.show({
							title : '提示',
							msg : result.result
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
		 	common_closeWindow('#backAuditInfo') ;
			$("#unDoneLoanAudit_datagrid").datagrid('reload');
			$("#doneloanAudit_datagrid").datagrid('reload');
			$("#unDoneLoanAudit_datagrid").datagrid('clearChecked');	
		}
	});
	
}

function bacthPassLoanAudit() {
	var rows = $('#unDoneLoanAudit_datagrid').datagrid('getChecked');
	if (rows.length == 0) {
		$.messager.show({
			title : '提示',
			msg : '请至少选择一条数据'
		});
		return;
	}
	var ids = '';
	var loanNos='';
	var owningBranchIds = '';
	var channelCds='';
	var productCds='';
	var contractTrems='';
	var contractLmts='';
	var errorCheckAite = false;
	for ( var i in rows) {
		if(rows[i].id &&rows[i].loanNo && rows[i].channelCd ){
			if('00016' == rows[i].channelCd){
				errorCheckAite = true;
				break;
			}
			ids += rows[i].id + ',';
			loanNos+=rows[i].loanNo + ',';
			owningBranchIds+=rows[i].owningBranchId + ',';
			channelCds+=rows[i].channelCd + ',';
			productCds+=rows[i].productCd + ',';
			contractTrems+=rows[i].contractTrem + ',';
			contractLmts+=rows[i].contractLmt + ',';
		}
	}
	if(errorCheckAite){
		$.messager.show({
			title : '提示',
			msg : '不可勾选捞财宝渠道数据，进行批量通过！'
		});
		$("#unDoneLoanAudit_datagrid").datagrid('clearChecked');
		return;
	}
	$.messager.confirm('确认', '确认批量通过选中的' + rows.length + '条吗？', function(r) {
		if (r) {
			$.ajax({
				url : 'loanAudit/bacthPassLoanAudit.do',
				data : {
					'ids' : ids,
					'loanNos':loanNos,
					'owningBranchIds':owningBranchIds,
					'channelCds':channelCds,
					'productCds':productCds,
					'contractTrems':contractTrems,
					'contractLmts':contractLmts,
				},
				async:false,
				success : function(result) {
					if (result.isSuceess) {
						$.messager.show({
							title : '提示',
							width:450,
							height:280,
							msg : result.result
						});
					} else {
						parent.$.messager.show({
							title : '提示',
							msg : result.result
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
			$("#unDoneLoanAudit_datagrid").datagrid('reload');
			$("#doneloanAudit_datagrid").datagrid('reload');
			$("#unDoneLoanAudit_datagrid").datagrid('clearChecked');	
		}
	});
	
}

//根据渠道控制未完成是否显示
function unDoneLoanAuditOnLoad(unDone){
	debugger;
	var channelCd = $('#loanAudit_channelId').combobox('getValue'); 
	if('00018'==channelCd||'00021'==channelCd){
		$(".loanAudit_orgAuditState").show();
	}else{
		$(".loanAudit_orgAuditState").hide();
	}
	
	var channelCd = $('#loanAudit_channelId').combobox('getValue'); 
	if('00016'==channelCd){
		$("#bacthPassAuditBtn").hide();
	}else{
		$("#bacthPassAuditBtn").show();
	}
}

//校验
function auditBackFromInfoExam(divName) {
	//$("#twoReason").combobox('getValue')
	var oneReason = $(divName).find('input[id="backAudit_oneReason"]').combobox("getValue");
	if (oneReason==null ||oneReason=="") {
		$.messager.show({
			title : '提示',
			msg : '请选择一级原因'
		});
		return false;
	}
	return true;
}


function exportLoanAuditResult(){
	var loanNos = $('#queryLoanAuditDiv').find('input[name="loanNos"]').val();
	var name = $('#queryLoanAuditDiv').find('input[name="name"]').val();
	var idNo = $('#queryLoanAuditDiv').find('input[name="idNo"]').val();
	var channelCd = $('#loanAudit_channelId').combobox('getValue');
	var productId = $('#loanAudit_productId').combobox('getValue');
	var applyInputFlag = $('#loanAudit_applyInputFlag').combobox('getValue');
	var confirmDate = $("#loanAudit_confirmDate").datebox("getValue");
	var confirmDate2=$("#loanAudit_confirmDate2").datebox('getValue');
	var param ={};
	param.loanNos=loanNos;
	param.name=name;
	param.idNo=idNo;
	param.channelCd=channelCd;
	param.productId=productId;
	param.applyInputFlag=applyInputFlag;
	param.confirmDate=confirmDate;
	param.confirmDate2=confirmDate2;
	 $.ajax({
		   url : 'loanAudit/queryLoanAuditInfo.do',
		   data : param,
		   type:"POST",
		   async:false,  // 设置同步方式
	       cache:false,
		   success : function(result){ 
		   		if(result.isSuccess){
	   			window.location.href = "loanAudit/exportLoanAuditInfo.do" + "?loanNos=" + loanNos+"&name="+name+"&idNo="+idNo+
	   			"&channelCd="+channelCd+"&productId="+productId+"&applyInputFlag"+applyInputFlag+"&confirmDate"+confirmDate
	   			+"&confirmDate2"+confirmDate2;
		   		}else{
		   			$.messager.show({
						title : '提示',
						msg : result.result
					});
		   		}	
		   },
		   error:function(data){
		 		 $.messager.show({
						title: '提示',
						msg: data.responseText
					});
		   }
	});	
}



