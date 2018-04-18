$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSLoanAuditInfo();
	    }
	});
});
function initDatagrid() {
		$("#new_loanAuditDatagrid").datagrid({
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
			columns : [ [ {
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
				field : 'accLmt',
				title : '审批额度(ACC_LMT)',
				width : 220,
			},{
				field : 'accTerm',
				title : '审批期限(ACC_TERM)',
				width : 220,
			},{
				field : 'accDate',
				title : '审批日期(ACC_DATE)',
				width : 220,
			},{
				field : 'pointResult',
				title : '评分值(POINT_RESULT)',
				width : 220,
			},{
				field : 'proNum',
				title : '流程实例号(PRO_NUM)',
				width : 220,
			},{
				field : 'proName',
				title : '流程节点名称(PRO_NAME)',
				width : 220,
			},{
				field : 'checkNodeState',
				title : '复核节点状态(CHECK_NODE_STATE)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if (value == 'NO_CHECK') {
						return "不需要复核";
					} else if(value =='CHECK'){
						return "待复核";
					} else if(value =='CHECK_PASS'){
						return "复核同意";
					} else if(value =='CHECK_NO_PASS'){
						return "复核不同意";
					}else{
						return "";
					}
				}
			},{
				field : 'refuseCode',
				title : '拒绝原因码(REFUSE_CODE)',
				width : 220,
			},{
				field : 'checkPersonCode',
				title : '初审员(CHECK_PERSON_CODE)',
				width : 220,
			},{
				field : 'checkAllotDate',
				title : '初审分配时间(CHECK_ALLOT_DATE)',
				width : 220,
			},{
				field : 'finalPersonCode',
				title : '终审员(FINAL_PERSON_CODE)',
				width : 220,
			},{
				field : 'finalRole',
				title : '终审权限(FINAL_ROLE)',
				width : 220,
			},{
				field : 'finalAllotDate',
				title : '终审分配时间(FINAL_ALLOT_DATE)',
				width : 220,
			},{
				field : 'approvalPerson',
				title : '协审员(APPROVAL_PERSON)',
				width : 220,
			},{
				field : 'amoutIncome',
				title : '收入证明金额(AMOUT_INCOME)',
				width : 220,
			},{
				field : 'sysCheckLmt',
				title : '系统建议核实收入(SYS_CHECK_LMT)',
				width : 220,
			},{
				field : 'sysAccTrem',
				title : '系统建议审批期限(SYS_ACC_TREM)',
				width : 220,
			},{
				field : 'sysAccLmt',
				title : '系统建议审批金额(SYS_ACC_LMT)',
				width : 220,
			},{
				field : 'isRollback',
				title : '是否刚回退到录入修改(ISROLLBACK)',
				width : 220,
			},{
				field : 'minApprovalAmt',
				title : '最小可审批金额(MIN_APPROVAL_AMT)',
				width : 220,
			},{
				field : 'ifCreditRecord',
				title : '有无信息记录(IF_CREDIT_RECORD)',
				width : 220,
			},{
				field : 'maxApprovalAmt',
				title : '最大可审批金额(MAX_APPROVAL_AMT)',
				width : 220,
			},{
				field : 'startDate',
				title : '起始完成时间(START_DATE)',
				width : 220,
			},{
				field : 'endDate',
				title : '截止完成时间(END_DATE)',
				width : 220,
			},{
				field : 'refuseDate',
				title : '拒绝时间(REFUSE_DATE)',
				width : 220,	
			},{
				field : 'auditReviewTime',
				title : '审核复核时间(AUDIT_REVIEW_TIME)',
				width : 220,
			},{
				field : 'adviceVerifyIncome',
				title : '建议核实收入(规则)(ADVICE_VERIFY_INCOME)',
				width : 220,
			},{
				field : 'adviceAuditLines',
				title : '建议审批额度(规则)(ADVICE_AUDIT_LINES)',
				width : 220,
			},{
				field : 'internalDebtRadio',
				title : '内部负载率(规则)(INTERNAL_DEBT_RATIO)',
				width : 220,
			},{
				field : 'toalDebtRadio',
				title : '总负载率(规则)(TOTAL_DEBT_RATIO)',
				width : 220,
			},{
				field : 'scoreCardScore',
				title : '评分卡分数(规则)(SCORECARD_SCORE)',
				width : 220,	
			},{
				field : 'ccRuleSet',
				title : '经过的CC规则集的名称(规则)(CC_RULE_SET)',
				width : 220,
			},{
				field : 'ifCheckReturn',
				title : '是否复核不通过(不通过:1否则null)(IF_CHECK_RETURN)',
				width : 220,
			},{
				field : 'ifLastCheckReturn',
				title : '是否终审退回初审(是:1,否(退回门店):2)(IF_LASTCHECK_RETURN)',
				width : 220,
			},{
				field : 'creator',
				title : '创建用户(CREATOR)',
				width : 220,
			},{
				field : 'createdTime',
				title : '创建时间(CREATED_TIME)',
				width : 220,
			},{
				field : 'creatorId',
				title : '创建用户Id(CREATOR_ID)',
				width : 220,
			},{
				field : 'modifier',
				title : '修改用户(MODIFIER)',
				width : 220,
			},{
				field : 'modifiedTime',
				title : '修改时间(MODIFIED_TIME)',
				width : 220,
			},{
				field : 'modifierId',
				title : '修改用户Id(MODIFIER_ID)',
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

function queryBMSLoanAuditInfo() {
	/*var productId = $('#pro_productId').combobox('getValue');*/
	//借款编号
	var loanNo = $('#queryLoanAuditDiv').find('input[name="loanNo"]').val();
	//申请件编号
	var appNo = $('#queryLoanAuditDiv').find('input[name="appNo"]').val();
	var loanBaseId = $('#queryLoanAuditDiv').find('input[name="loanBaseId"]').val();
	
	//查询
	var options = $('#new_loanAuditDatagrid').datagrid('options');
	options.url='master/loanAudit/listPage';
	options.queryParams={loanNo:loanNo,appNo:appNo,loanBaseId:loanBaseId};
	if(loanNo=="" && appNo=="" && loanBaseId==""){
		$.messager.show({
			title : '提示',
			msg : '请输入查询条件！'
		});
		return false;
	}
	setFirstPage("#new_loanAuditDatagrid");
	$('#new_loanAuditDatagrid').datagrid('options');
	$("#new_loanAuditDatagrid").datagrid('reload');
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








