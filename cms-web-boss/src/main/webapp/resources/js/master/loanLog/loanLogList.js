$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
		debugger;
	    if(event.keyCode == 13) {
	    	queryBMSLoanLog();
	    }
	});
});
function initDatagrid() {
		$("#new_loanLogDatagrid").datagrid({
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
			url : '',
			striped : true,
			/*singleSelect : true,*/
			rownumbers : true,
			pagination : true,
			fitColumns : false,
			checkOnSelect: false,
			selectOnCheck: false,
			singleSelect: false,
			checkbox:true,
			scrollbarSize : 0,
			columns : [ [  {
				field : 'id',
				title : 'ID',
				width : 60,
			},{
				field : 'loanBaseId',
				title : 'LOAN_BASE_ID',
				width : 120,
			},
			{
				field : 'loanNo',
				title : '借款编号(LOAN_NO)',
				width : 220,
			}, {
				field : 'appNo',
				title : '申请件编号(APP_NO)',
				width : 220,
			},{
				field : 'status',
				title : '申请件状态(STATUS)',
				width : 220,
			},{
				field : 'rtfState',
				title : '流程状态(RTF_STATE)',
				width : 220,
			},{
				field : 'rtfNodeState',
				title : '节点状态(rtfNodeState)',
				width : 220,
			},{
				field : 'checkNodeState',
				title : '借款审核环节节点状态(CHECK_NODE_STATE)',
				width : 220,
			},{
				field : 'checkPersonCode',
				title : '初审人员code(CHECK_PERSON_CODE)',
				width : 220,
			},{
				field : 'checkPerson',
				title : '初审人员(CHECK_PERSON)',
				width : 220,
			},{
				field : 'finalPersonCode',
				title : '终审人员code(FINAL_PERSON_CODE)',
				width : 220,
			},{
				field : 'finalPerson',
				title : '终审人员(FINAL_PERSON)',
				width : 220,
			},{
				field : 'apppovalPersonCode',
				title : '协审人员code(APPPOVAL_PERSON_CODE)',
				width : 220,
			}
			,{
				field : 'apppovalPerson',
				title : '协审人员(APPPOVAL_PERSON)',
				width : 220,
			},{
				field : 'complexPersonCode',
				title : '复核人员code(COMPLEX_PERSON_CODE)',
				width : 220,
			},{
				field : 'complexPerson',
				title : '复核人员(COMPLEX_PERSON)',
				width : 220,
			},{
				field : 'firstLevleReasonsCode',
				title : '一级原因code(FIRST_LEVLE_REASONS_CODE)',
				width : 220,
			},{
				field : 'firstLevleReasons',
				title : '一级原因(FIRST_LEVLE_REASONS)',
				width : 220,
			},{
				field : 'twoLevleReasonsCode',
				title : '二级原因code(TWO_LEVLE_REASONS_CODE)',
				width : 220,
			},{
				field : 'twoLevleReasons',
				title : '二级原因(TWO_LEVLE_REASONS)',
				width : 220,
			},{
				field : 'operationModule',
				title : '操作模块code(OPERATION_MODULE)',
				width : 220,
			},{
				field : 'operationType',
				title : '操作类型code(OPERATION_TYPE)',
				width : 220,
			},{
				field : 'operator',
				title : '操作人(OPERATOR)',
				width : 220,
			},{
				field : 'operatorCode',
				title : '操作工号(OPERATOR_CODE)',
				width : 220,
			},{
				field : 'operationTime',
				title : '操作时间(OPERATION_TIME)',
				width : 220,
			},{
				field : 'remark',
				title : '备注(REMARK)',
				width : 220,
			},{
				field : 'version',
				title : '版本号(VERSION)',
				width : 220,
			},{
				field : 'isDelete',
				title : '是否逻辑删除(IS_DELETE)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if (value == 0) {
						return "否";
					} else if(value == 1){
						return "是";
					} else{
						return "";
					}
				},
			}
			] ]
		});
}

function queryBMSLoanLog() {
	//申请件状态
	var status =  $('#queryLoanLogDiv').find('input[name="status"]').val();
	//借款编号
	var loanNo = $('#queryLoanLogDiv').find('input[name="loanNo"]').val();
	//流程状态
	var rtfState = $('#queryLoanLogDiv').find('input[name="rtfState"]').val();
	//loanBaseId
	var loanBaseId = $('#queryLoanLogDiv').find('input[name="loanBaseId"]').val();

	
	//查询
	var options = $('#new_loanLogDatagrid').datagrid('options');
	options.url="loanLog/listPage";
	options.queryParams={rtfState:rtfState,loanNo:loanNo,loanBaseId:loanBaseId,status:status};
	if(rtfState=="" && loanNo=="" && loanBaseId=="" && status==""){
		$.messager.show({
			title : '提示',
			msg : '请输入查询条件！'
		});
		return false;
	}
	setFirstPage("#new_loanLogDatagrid");
	$('#new_loanLogDatagrid').datagrid('options');
	$("#new_loanLogDatagrid").datagrid('reload');
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








