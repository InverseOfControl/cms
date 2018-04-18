$(function() {
	confirm_unDoneInitDatagrid();
	confirm_doneInitDatagrid();
	
	// 渠道配置进来
	//var channelId = $('#channelIdRequest').val();
	//$('#loanConfirm_channelId').combobox('select',00001);
	//query_loanConfirm_Info();

	$('#loanConfirmTT').tabs({
	    border:false,
	    onSelect:function(title,index){
	    	if(title.substring(title.length-4)=='待办任务'){
	    		 var valueCode = $('#loanConfirm_channelId').combobox('getValue'); 
		    		if(valueCode=='00014'){
		    			$("#bacthPassConfirmBtn").hide();
			    		 $("#unDoneloanConfirm_datagrid").datagrid('reload');
		    		}else{
		    			$("#bacthPassConfirmBtn").show();
			    		 $("#unDoneloanConfirm_datagrid").datagrid('reload');
		    		}
	    	}else if(title.substring(title.length-3)=="已完成"){
	    		$("#bacthPassConfirmBtn").hide();
	    		 $("#doneloanConfirm_datagrid").datagrid('reload');
	    	}
	    }
	});
	
	//退回备注字数限制
	$("#loanConfirm_remark").keyup(function(){
		 var maxLength = 100;  
         var len = $("#loanConfirm_remark").val().length;  
         $('#loanConfirm_remarkCount').html(maxLength - len);  
         if(parseInt($('#loanConfirm_remarkCount').text()) < 0){  
             $('#loanConfirm_remarkCount').html('0');  
             var res = $(this).val().substring(0,100);  
             $(this).val(res);  
         }  
	});
});

function confirm_unDoneInitDatagrid() {
	$("#unDoneloanConfirm_datagrid").datagrid({
		onLoadSuccess : function(data) {
			if (data.total == 0) {
				var title = $('.tabs-selected').text();
				if(title.substring(title.length-4)=="待办任务"){
					$.messager.show({
						title : '结果',
						msg : '没查到符合条件的数据！',
						showType : 'slide',
					});
				}
				;
			}

		},
		idField : 'id',
		url : 'loanConfirm/listPage',
		checkOnSelect : false,
		selectOnCheck : false,
		singleSelect : false,
		checkbox : true,
		scrollbarSize : 0,
		fitColumns : false,
		border : false,
		singleSelect : true,
		pagination : true,
		striped : true,
		rownumbers : true,
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
//				debugger;
				var html = '';
				if(rowData.ifPri && rowData.ifPri == '1'){//加急
					html += '<img title="加急" class="easyui-tooltip" src="resources/images/identificationCase/urgent.png"></img>';
				} 
				if(rowData.appInputFlag && rowData.appInputFlag == 'app_input'){//app进件
					html += '<img title="app进件" class="easyui-tooltip" src="resources/images/identificationCase/appPart.png"></img>';
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
			field : 'contractLmt',
			title : '签约额度',
			width : 120,
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
		{
			field : 'applyBankBranch',
			title : '开户银行',
			width : 150,
		}, 
		{
			field : 'applyBankCardNo',
			title : '银行卡号',
			width : 170,
		},	
		{
			field : 'bankPhone',
			title : '手机号码',
			width : 120,
		},
		{
			field : 'contractBranchId',
			title : '签约网点Id',
			width : 200,
		},
		{
			field : 'contractBranch',
			title : '签约网点',
			width : 200,
		},
	
		{
			field : 'applyType',
			title : '申请类型',
			width : 120,
		},
	
		{
			field : 'applyInputFlag',
			title : '交件类型',
			formatter : applyInputFlag_fm,
			width : 120,
		}, {
			field : 'operation',
			title : '操作',
			formatter : format_loanConfirm_Operations,
			width : 250
		}] ],
		onLoadSuccess:function(data){
			unDoneOnLoad($("#unDoneloanConfirm_datagrid"));
		}
	});
}

//根据渠道控制未完成是否显示
function unDoneOnLoad(unDone){
	var channelCode = $('#loanConfirm_channelId').combobox('getValue'); 
	if(window.glChannelList.indexOf(channelCode)>-1){//国民信托,龙信渠道
		unDone.datagrid("hideColumn", "id");
		unDone.datagrid("hideColumn", "identificationCase");
		unDone.datagrid("hideColumn", "loanNo");
		unDone.datagrid("hideColumn", "bankPhone");
		unDone.datagrid("hideColumn", "contractBranchId");
		unDone.datagrid("hideColumn", "contractBranch");
		unDone.datagrid("hideColumn", "applyType");
		unDone.datagrid("hideColumn", "applyInputFlag");
	}else if(window.hmxdChannelCode == channelCode){//海门小贷
		unDone.datagrid("hideColumn", "identificationCase");
		unDone.datagrid("showColumn", "id");
		unDone.datagrid("showColumn", "loanNo");
		unDone.datagrid("showColumn", "bankPhone");
		unDone.datagrid("showColumn", "contractBranchId");
		unDone.datagrid("showColumn", "contractBranch");
		unDone.datagrid("showColumn", "applyType");
		unDone.datagrid("showColumn", "applyInputFlag");
    }
	else if(window.wmxtChannelCode == channelCode){
    	unDone.datagrid("hideColumn", "identificationCase");
    	unDone.datagrid("hideColumn", "contractBranchId");
    	unDone.datagrid("hideColumn", "loanNo");
    	unDone.datagrid("hideColumn", "bankPhone");
    	unDone.datagrid("hideColumn", "applyType");
    	unDone.datagrid("hideColumn", "applyInputFlag");
    	unDone.datagrid("hideColumn", "contractBranch");
    	
    	unDone.datagrid("showColumn", "name");
    	unDone.datagrid("showColumn", "idNo");
    	unDone.datagrid("showColumn", "productName");
    	unDone.datagrid("showColumn", "channelName");
    	unDone.datagrid("showColumn", "contractLmt");
    	unDone.datagrid("showColumn", "contractTrem");
    	unDone.datagrid("showColumn", "applyBankName");
    	unDone.datagrid("showColumn", "applyBankBranch");
    	unDone.datagrid("showColumn", "applyBankCardNo");
    }
	else{
    	unDone.datagrid("showColumn", "id");
    	unDone.datagrid("showColumn", "identificationCase");
    	unDone.datagrid("showColumn", "loanNo");
    	unDone.datagrid("showColumn", "bankPhone");
    	unDone.datagrid("showColumn", "contractBranchId");
    	unDone.datagrid("showColumn", "contractBranch");
    	unDone.datagrid("showColumn", "applyType");
    	unDone.datagrid("showColumn", "applyInputFlag");
	}
}


function confirm_doneInitDatagrid() {

	$("#doneloanConfirm_datagrid").datagrid({
		onLoadSuccess : function(data) {
			if (data.total == 0) {
				var title = $('.tabs-selected').text();
				if(title.substring(title.length-3)=="已完成"){
					$.messager.show({
						title : '结果',
						msg : '没查到符合条件的数据！',
						showType : 'slide',
					});
				}
				;
			}

		},
		url : 'loanConfirm/doneListPage',
		fitColumns : false,
		border : false,
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
//				debugger;
				var html = '';
				if(rowData.ifPri && rowData.ifPri == '1'){//加急
					html += '<img  title="加急" class="easyui-tooltip" src="resources/images/identificationCase/urgent.png"></img>';
				} 
				if(rowData.appInputFlag && rowData.appInputFlag == 'app_input'){//app进件
					html += '<img   title="app进件" class="easyui-tooltip" src="resources/images/identificationCase/appPart.png"></img>';
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
		},
		 {
			field : 'owningBranchId',
			title : '进件营业部ID',
			hidden:true,
		},
		{
			field : 'channelCd',
			title : '渠道CODE',
			hidden:true,
		}, 
		{
			field : 'channelName',
			title : '渠道名称',
			width : 100,
		},
		{
			field : 'productCd',
			title : '产品CODE',
			hidden:true,
		}, 
		{
			field : 'productName',
			title : '产品名称',
			width : 100,
		}, {
			field : 'contractLmt',
			title : '签约额度',
			width : 120,
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
		{
			field : 'applyBankBranch',
			title : '开户银行',
			width : 150,
		}, 
		{
			field : 'applyBankCardNo',
			title : '银行卡号',
			width : 170,
		},	
		{
			field : 'bankPhone',
			title : '手机号码',
			width : 120,
		},
		{
			field : 'contractBranch',
			title : '签约网点',
			width : 200,
		},
	
		{
			field : 'applyType',
			title : '申请类型',
			width : 120,
		},
	
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
		}
		] ],
		onLoadSuccess:function(data){
			unDoneOnLoadSuccess($("#doneloanConfirm_datagrid"));
		}
	});
}

function unDoneOnLoadSuccess(unDones){
	var channelCode = $('#loanConfirm_channelId').combobox('getValue'); 
    if(window.wmxtChannelCode == channelCode){
    	unDones.datagrid("hideColumn", "identificationCase");
    	unDones.datagrid("hideColumn", "loanNo");
    	unDones.datagrid("hideColumn", "bankPhone");
    	unDones.datagrid("hideColumn", "contractBranch");
    	unDones.datagrid("hideColumn", "applyType");
    	unDones.datagrid("hideColumn", "applyInputFlag");
    	
    	
    	unDones.datagrid("showColumn", "name");
    	unDones.datagrid("showColumn", "idNo");
    	unDones.datagrid("showColumn", "productName");
    	unDones.datagrid("showColumn", "channelName");
    	unDones.datagrid("showColumn", "contractLmt");
    	unDones.datagrid("showColumn", "contractTrem");
    	unDones.datagrid("showColumn", "applyBankName");
    	unDones.datagrid("showColumn", "applyBankBranch");
    	unDones.datagrid("showColumn", "applyBankCardNo");
    }
}

/*function applyType_fm (value, row, index){ 
    if (value == "1") {
        return '营业部';
    }
    else if(value == "2"){
        return '直通车';
    }
    
}*/

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


/* 操作函数
* 
* @param value
* @param row
* @param index
* @returns {String}
*/
function format_loanConfirm_Operations(value, row, index) {
		var operations = '';
		var name= row.name;
		var loanStr=row.loanNo.toString();
		var prevLoanNo=loanStr.substring(0,row.loanNo.length-4);
		var lastLoanNo=loanStr.substring(row.loanNo.length-4);
		var owningBranchId=row.owningBranchId;
		var channelCd=row.channelCd;
		var productCd=row.productCd;
		var contractTrem=row.contractTrem;
		var contractLmt=row.contractLmt;
		/*operations += '<a href="javascript:void(0)" onclick="passAudit('+ row.id  
		+','+ "'"+loanStr+ "'" +','+"'"+owningBranchId+ "'" +','+"'"+channelCd+ "'" 
		+','+"'"+productCd+ "'"+','+"'"+contractTrem+ "'"+','+"'"+contractLmt+ "'"+')">通过 &nbsp;&nbsp;</a>';*/
		if('00014' == channelCd || '00016' == channelCd || window.glChannelList.indexOf(channelCd)>-1){//国民信托，龙信小贷没有通过按钮
			//老财宝渠道，无通过按钮
		} else {
			operations += '<a href="javascript:void(0)" onclick="grantLoan('+ row.id  
			+",'"+loanStr+ "',"+"'"+owningBranchId+ "',"+"'"+channelCd+ "',"
			+"'"+productCd+ "'"+','+"'"+contractTrem+ "'"+','+"'"+contractLmt+ "'"+','+"'"+row.contractBranch+ "'"+','+"'"+row.contractBranchId+ "'"+')">通过 &nbsp;&nbsp;</a>';
		}
		// 1.启用，2,禁用onclick="openBackLoanW('+ row.id  + ','+ + prevLoanNo+","+lastLoanNo +","+ row.name+')
		
		operations += '<a href="javascript:void(0)" onclick="openBackLoanW('
			+ row.id + ','+ prevLoanNo+','+"'"+lastLoanNo+ "'" +','+ "'"+name+"'"+','+ "'"+channelCd+"'"+ ')">退回&nbsp;&nbsp;</a>';
		
		operations += '<a href="javascript:void(0)" onclick="showLoanFiledataDialog('
			 + prevLoanNo+","+"'"+lastLoanNo+ "'" +')">附件 &nbsp;&nbsp;</a>';
		
		operations += '<a href="javascript:void(0)" onclick="openLoanLogW('
			+ row.id +','+ prevLoanNo+","+"'"+lastLoanNo+ "'" + ')">日志 &nbsp;&nbsp;</a>';
	return operations;

};



//附件预览

function showLoanFiledataDialog(prevLoanNo,lastLoanNo) {
	var nodeKey = "credit";
	  var userName = $("#userName").val();
	  var userCode = $("#userCode").val();
	  var picFileDataUrl =$("#picFileDataUrl").val();
	  var loanNo =prevLoanNo+""+PrefixInteger(lastLoanNo,4);
	var url = picFileDataUrl+"?nodeKey="+nodeKey+"&sysName=bms&appNo="+loanNo+"&operator="+userName+"&jobNumber="+userCode;
    var content = '<iframe src="' + url + '" width="100%" height="99%" frameborder="0" scrolling="yes"></iframe>';  
    var boarddiv = '<div id="loanFiledata" title="' + "附件预览" + '"></div>'//style="overflow:hidden;"可以去掉滚动条  
    $(document.body).append(boarddiv);  
    var win = $('#loanFiledata').dialog({  
        content: content,  
        width: 700,  
        height: 800,  
        modal: true,   
        title: "附件预览",  
        onClose: function () {  
            $(this).dialog('destroy');//后面可以关闭后的事件  
        }  
    });  
    win.dialog('open');  
} 
/*function openAuditFiledataInfo(prevLoanNo,lastLoanNo){	
  //业务环节
  var nodeKey = "apply";
  var userName = $("#userName").val();
  var userCode = $("#userCode").val();
  var picFileDataUrl =$("#picFileDataUrl").val();
  var loanNo =prevLoanNo+""+PrefixInteger(lastLoanNo,4);
  var url = picFileDataUrl+"?nodeKey="+nodeKey+"&sysName=bms&appNo="+loanNo+"&operator="+userName+"&jobNumber="+userCode;
  var windowStatus = "status:0,menubar=yes,scrollbars=no,toolbar=yes,status=yes,top=150,left=500,width=700,height=800";
  window.open(url,'附件',windowStatus);
  //$('#fileDataWin').window('refresh', url);
}*/


function PrefixInteger(num, n) {
    return (Array(n).join(0) + num).slice(-n);
}

function grantLoan(id,loanNo,owningBranchId,channelCd,productCd,contractTrem,contractLmt,contractBranch,contractBranchId) {
	$.messager.confirm('放款确认','是否确认放款？', function(r) {
		if (r) {
			$.ajax({
				url : 'loanConfirm/grantLoan.do',
				data : {
					'id' : id,
					'loanNo':loanNo,
					'owningBranchId':owningBranchId,
					'channelCd':channelCd,
					'productCd':productCd,
					'contractTrem':contractTrem,
					'contractLmt':contractLmt,
					'contractBranch':contractBranch,
					'contractBranchId':contractBranchId,
				},
				async:false,
				type:"POST",
				success : function(result) {
					if (result.isSuccess) {
						$.messager.show({
							title : '提示',
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
		}
		 $("#unDoneloanConfirm_datagrid").datagrid('reload');
		 $("#doneloanConfirm_datagrid").datagrid('reload');
	});

}



/**
 * 查询函数
 */
function query_loanConfirm_Info(){
	// 查询
	var title = $('.tabs-selected').text();
	var loanNo = $('#queryLoanDiv').find('input[name="loanNo"]').val();
	var name = $('#queryLoanDiv').find('input[name="name"]').val();
	var idNo = $('#queryLoanDiv').find('input[name="idNo"]').val();
	var channelCd = $('#loanConfirm_channelId').combobox('getValue');
	var productId = $('#loanConfirm_productId').combobox('getValue');
	var signDate = $("#loanConfirm_signDate").datebox("getValue");;
	var contractBranchId=$("#loanConfirm_org").combobox('getValue');
	//增加批次号查询
	var batchNum = $('#queryLoanDiv').find('input[name="batchNum"]').val();
	//增加放款状态
	var paymentStatus = $("#paymentStatus").combobox("getValue");
	
	if(channelCd==null||channelCd==""){
		$.messager.show({
			title : '提示',
			msg : '必须选择一个渠道'
		});
		return ;
	}
	
	if(title.substring(title.length-4) =="待办任务"){
		var queryParams = $('#unDoneloanConfirm_datagrid').datagrid('options').queryParams;
		queryParams.channelCd = channelCd;
		queryParams.loanNo = loanNo;
		queryParams.productId = productId;
		queryParams.name=name;
		queryParams.idNo=idNo;
		queryParams.signDate=signDate;
		queryParams.contractBranchId=contractBranchId;
		queryParams.batchNum=batchNum;//批次号
		queryParams.state=paymentStatus;//放款状态
		setloanConfirm_FirstPage("#unDoneloanConfirm_datagrid");
		$('#unDoneloanConfirm_datagrid').datagrid('options').queryParams = queryParams;
		$("#unDoneloanConfirm_datagrid").datagrid('reload',queryParams);
	}else if(title.substring(title.length-3)=="已完成"){
		var queryParams2 = $('#doneloanConfirm_datagrid').datagrid('options').queryParams;
		var queryParams2 = {};
		queryParams2.channelCd = channelCd;
		queryParams2.productId = productId;
		queryParams2.loanNo = loanNo;
		queryParams2.name=name;
		queryParams2.idNo=idNo;
		queryParams2.signDate=signDate;
		queryParams2.contractBranchId=contractBranchId;
		queryParams2.batchNum=batchNum;//批次号
		queryParams2.state=paymentStatus;//放款状态
		setloanConfirm_FirstPage("#doneloanConfirm_datagrid");
		$('#doneloanConfirm_datagrid').datagrid('options').queryParams = queryParams2;
		$("#doneloanConfirm_datagrid").datagrid('reload',queryParams2);
	}
}
/**
 * 设置查询分页信息
 * 
 * @param ids
 */
function setloanConfirm_FirstPage(ids) {
	var opts = $(ids).datagrid('options');
	var pager = $(ids).datagrid('getPager');
	opts.pageNumber = 1;
	opts.pageSize = opts.pageSize;
	pager.pagination('refresh', {
		pageNumber : 1,
		pageSize : opts.pageSize
	});
}

function bacthPassLoanConfirm() {
	var rows = $('#unDoneloanConfirm_datagrid').datagrid('getChecked');
	
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
	if(errorCheckAite){
		$.messager.show({
			title : '提示',
			msg : '不可勾选捞财宝渠道数据，进行批量通过！'
		});
		return;
	}
	$.messager.confirm('确认', '确认批量通过选中的' + rows.length + '条吗？', function(r) {
		if (r) {
			$.ajax({
				url : 'loanConfirm/bacthPassLoan.do',
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
				type:"POST",
				success : function(result) {
					if (result.isSuceess) {
						$.messager.show({
							title : '提示',
							width:280,
							height:300,
							msg : result.result
						});
					} else {
						parent.$.messager.show({
							title : '提示',
							msg : '操作失败'
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
		 $("#unDoneloanConfirm_datagrid").datagrid('reload');
		 $("#doneloanConfirm_datagrid").datagrid('reload');
	});
}



function backLoan(){
	var vilidFlag=loanBackFromInfoExam("#backLoanInfo");
	if(!vilidFlag){
		return;
	}
	var param ={};
	param.firstLevleReasons =$("#backLoan_oneReason").combobox('getText');
	param.firstLevleReasonsCode=$("#backLoan_oneReason").combobox('getValue');
	
	param.twoLevleReasonsCode=$("#backLoan_twoReason").combobox('getValue');
	param.twoLevleReasons =$("#backLoan_twoReason").combobox('getText');

	param.loanNo=$("#backLoan_loanNo").val();
	param.remark=$("#remark").val();
	param.id=$("#backLoan_id").val();
	param.channelCd=$("#backLoan_channleCode").val();
$.messager.confirm('放款确认退回确认','放款确认退回，将进入退件箱列表！', function(r) {
		if (r) {
	 $.ajax({
		   url : 'loanConfirm/backLoan',
		   data : param,
		   type:"POST",
		   async:false,  // 设置同步方式
		   success : function(result){
			   $('#backLoanInfo').window('close');
//			   $.messager.progress('close');
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
	 closeWindow('#backLoanInfo') ;
	 $("#unDoneloanConfirm_datagrid").datagrid('reload');
	 $("#doneloanConfirm_datagrid").datagrid('reload');
		}
	})
}



function openBackLoanW(id,prevLoanNo,lastLoanNo,name,channleCd) {
	$('#backLoanInfo').window({
		modal : true,
		closed : false,
	});

	var loanNo =prevLoanNo+""+PrefixInteger(lastLoanNo,4);

	$('#backLoanInfo').form('clear');
	$("#backLoan_loanNo").val(loanNo);
	$("#loan_name").html(name);
	$("#backLoan_id").val(id);
	$("#backLoan_channleCode").val(channleCd);
}

function openLoanLogW(id,prevLoanNo,lastLoanNo) {
	$('#LoanLogInfo').window({
		modal : true,
		closed : false,
	});
	var loanNo =prevLoanNo+""+PrefixInteger(lastLoanNo,4);
	$('#Loan_LoanLog').datagrid({
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

//校验
function loanBackFromInfoExam(divName) {
	//$("#twoReason").combobox('getValue')
	var oneReason = $(divName).find('input[id="backLoan_oneReason"]').combobox("getValue");
	if (oneReason==null ||oneReason=="") {
		$.messager.show({
			title : '提示',
			msg : '请选择一级原因'
		});
		return false;
	}

	return true;
}
//生成报盘
function createOffer(url){
	var channelCode = $("#loanConfirm_channelId").combobox("getValue");
	var batchNum = $("#batchNum").val();
	if(validate(channelCode)){
		$.ajax({
			url : url,
			data : {"fundsSource":channelCode,"batchNum":batchNum},
			type:"POST",
			async:true,
			success : function(result){
				console.log(result);
				//新增的提示
		        if(result.success){
		        	alertShow('生成报盘文件成功');
		        }else{
		        	alertShow(result.repMsg);
		        } 
			}
		});
	}
}
//messager.show弹出框
function alertShow(message){
	$.messager.show({
		title : '提示',
		msg : message
	});
}

//导出报盘
function exportOffer(url) {
	var channelCode = $("#loanConfirm_channelId").combobox("getValue");
	var batchNum = $("#batchNum").val();
	if (validate(channelCode)) {
		// window.location.href=url+"?fundsSource="+channelCode+"&batchNum="+batchNum;
		$.downloadFile({
			url : url,
			isDownloadBigFile : true,
			params : {
				"fundsSource" : channelCode,
				"batchNum" : batchNum
			},
			successFunc : function(data) {
				if (data == null) {
					alertShow("导出成功!");
				} else {
					if (data.repMsg != null) {
						alertShow(data.repMsg);
					} else {
						alertShow("导出失败");
					}
				}
			},
			failFunc : function(data) {
				alertShow("导出失败");
			}
		});
	}
}

// 导入回盘
function openOfferWindow(){
	$("#importExcelWin").window('open');

}

// 龙信小贷导入放款文件
function lxxdOpenOfferWindow(){
	$("#lxxdImportExcel").window('open');
}

//龙信小贷导入放款文件
function importLxxdOffer(url){
    var file = $("#lxxdOfferFile").filebox("getValue");
    if(!file){
        $.messager.alert('警告','请选择导入文件!','warning');
        return;
    }
    var fileSuffix = file.toString().substring(file.toString().lastIndexOf(".")+1) ;
	if (fileSuffix != window.excel2007Suffix) {
		$.messager.show({
			title : '提示',
			msg : '只支持'+window.excel2007Suffix+'文件的上传!'
		});
		return;
	}
    $.messager.confirm("提示","确认导入放款文件吗？",function(r){
        if(r){
        	$("#lxxdBaseFileForm").form('submit', {
                url: url,
                data: $("#lxxdBaseFileForm").serialize(),
                type: "post",
                success: function (result) {
                	var resultObj=eval("("+result+")");
                	if(resultObj.success){
    		        	alertShow('导入放款文件成功');
    		        }else{
    		        	alertShow(resultObj.repMsg);
    		        } 
//                	setTimeout(closeLxxdImportWindow(),1000);
                },error: function (data,status,e){
                	var dataObj=eval("("+data+")");
                	alertShow(dataObj.repMsg);
                } 
            });
        	setTimeout(closeLxxdImportWindow(),1000);
        }
    });
}

// 关闭龙信小贷导出窗体以及刷新等
function closeLxxdImportWindow() {
	// 关闭批量导入回盘文件
	$("#lxxdImportExcel").window('close');
	// 导入窗口关闭后文件名清空，
	$("#lxxdOfferFile").filebox("setValue", "");
	// 刷新一下
	$("#unDoneloanConfirm_datagrid").datagrid('reload');
	$("#doneloanConfirm_datagrid").datagrid('reload');
}

function importOffer(url){
    var file = $("#offerFile").filebox("getValue");
    if(!file){
        $.messager.alert('警告','请选择导入文件!','warning');
        return;
    }
    //导入文件格式校验
    var fileSuffix = file.toString().substring(file.toString().lastIndexOf(".")+1) ;
	if (fileSuffix != window.excel2003Suffix) {
		$.messager.show({
			title : '提示',
			msg : '只支持'+window.excel2003Suffix+'文件的上传!'
		});
		return;
	}
    $.messager.confirm("提示","确认导入回盘文件吗？",function(r){
        if(r){
        	$("#baseFileForm").form('submit', {
                url: url,
                data: $("#baseFileForm").serialize(),
                type: "post",
                success: function (result) {
                	 setTimeout(function(){
                     	var resultObj=eval("("+result+")");
                    	if(resultObj.success){
        		        	alertShow('导入回盘文件成功');
        		        }else{
        		        	alertShow(resultObj.repMsg);
        		        } 
                         // 关闭批量导入回盘文件
                     	$("#importExcelWin").window('close');
                     	 $("#unDoneloanConfirm_datagrid").datagrid('reload');
                     }, 1000);
                }, error: function (data) {
                	var resultObj=eval("("+data+")");
                    $.messager.alert('警告',resultObj.repMsg,'warning');
                }
            });
        }
    });
}

function validate(channelCode){
	if(!channelCode){
		$.messager.alert('提示','生成报盘时必须选择一个渠道!');
		return false;
	}
	return true;
}


//导入回盘文件
function uploadLoanFile(){
	$('#DataImport').window('open').window('refresh');
	$('#DataImportInfoForm').form('clear');
}




function ajaxFileUpload(){
	if(checkType()){
	var formData = new FormData($("#DataImportInfoForm")[0]);
	$("#DataImportInfoForm").form('submit', {
	    url: 'loanConfirm/auditCommit',
	    data: formData,
	    type:'post',
	    success: function (result) {
	    	if(result=='{"nullFile":true}'){
	    		$.messager.show({
					title : '提示',
					msg : 'txt文档内容为空!'
				});
	    		return;
	    	}
//	    	if(result.isSuccess){
//	    		$.messager.show({
//					title : '提示',
//					msg : '上传成功!!!'
//				});
//	    	}
	    	$('#DataImport').window('close');	

	    }, error: function (data,status,e){
	    	$.messager.show({
				title : '提示',
				msg : e
			});
        }
	}); 
	//ajaxFileUploadDown();
	closeWindow('#DataImport');
	}
}


//判断传入的文件为excel文件
function checkType(){
	var filePath = $("#Uploadfile").filebox('getValue');
	 if(filePath.length < 1)
	 {
		 $.messager.show({
				title : '提示',
				msg : '请选择要上传的文件!'
			});
	  return false ;
	 }
	 else
	 {
	  var fileLx = filePath.toString().substring(filePath.toString().lastIndexOf(".")+1) ;
	  if(fileLx == "txt")
	  {
	   return true ;
	  }
	  else
	  {
		  $.messager.show({
				title : '提示',
				msg : '只支持txt文件的上传!'
			});
	   return false ;
	  }
	 }
	
}



/*function closeWindow(){
	$('#DataImport').window('close').window('refresh');	
	$('#DataImportInfoForm').form('clear');
}*/

//国民信托重置按钮，和吴文倩咨询，渠道名称保留不清空
function setClear(){
	$("#name").textbox('setValue','');
	$("#idNo").textbox('setValue','');
	$("#paymentStatus").combobox('clear');
	$("#batchNum").textbox('setValue','');
}

