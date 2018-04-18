$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSLoanProductInfo();
	    }
	});
});
function initDatagrid() {
		$("#new_loanProductDatagrid").datagrid({
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
				field : 'repayDate',
				title : '还款日(REPAY_DATE)',
				width : 220,
			},{
				field : 'ifGrey',
				title : '是否在灰名单中(IF_GREY)',
				width : 220,
			},{
				field : 'ifOldOrNewLogo',
				title : '是否新老客户标识(IF_OLD_OR_NEW_LOGO)',
				width : 220,
			},{
				field : 'bankPhone',
				title : '银行预留手机号(BANK_PHONE)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {	
					if(value == null ||value == ""){
						return "";
					}else{
						return "*******"+value;
					}
			},
			},{
				field : 'applyBankCardNo',
				title : '银行卡号(APPLY_BANK_CARD_NO)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {	
					if(value == null ||value == ""){
						return "";
					}else{
						return "*************"+value;
					}
				}
			},{
				field : 'applyBankBranch',
				title : '开户行(APPLY_BANK_BRANCH)',
				width : 220,
			},{
				field : 'applyBankName',
				title : '所属银行(APPLY_BANK_NAME)',
				width : 220,
			},{
				field : 'contractNum',
				title : '合同编号(CONTRACT_NUM)',
				width : 220,
			},{
				field : 'contractSource',
				title : '合同来源(CONTRACT_SOURCE)',
				width : 220,
			},{
				field : 'initProductCd',
				title : '原产品(INIT_PRODUCT_CD)',
				width : 220,
			},{
				field : 'productCd',
				title : '申请产品(PRODUCT_CD)',
				width : 220,
			},{
				field : 'applyLmt',
				title : '申请额度(APPLY_LMT)',
				width : 220,
			},{
				field : 'applyTerm',
				title : '申请期限(APPLY_TERM)',
				width : 220,
			},{
				field : 'contractLmt',
				title : '合同金额(CONTRACT_LMT)',
				width : 220,
			},{
				field : 'contractTrem',
				title : '合同期限(CONTRACT_TREM)',
				width : 220,
			},{
				field : 'ifEnd',
				title : '是否处理完成件(IF_END)',
				width : 220,
			},{
				field : 'ifSuspectCheat',
				title : '是否疑似欺诈(IF_SUSPECT_CHEAT)',
				width : 220,
			},{
				field : 'ifLoanAgain',
				title : '是否结清再贷(IF_LOAN_AGAIN)',
				width : 220,
			},{
				field : 'fRefuse',
				title : '是否拒绝(IF_REFUSE)',
				width : 220,
			},{
				field : 'applyRate',
				title : '费率(APPLY_RATE)',
				width : 220,
			},{
				field : 'ifPatchBolt',
				title : '是否补件(IF_PATCH_BOLT)',
				width : 220,
			},{
				field : 'ifPri',
				title : '是否加急(IF_PRI)',
				width : 220,
			},{
				field : 'ifUrgent',
				title : '加急等级(IF_URGENT)',
				width : 220,
			},
			{
				field : 'initApplyLmt',
				title : '原产品金额(INIT_APPLY_LMT)',
				width : 220,
			},
			{
				field : 'initApplyTerm',
				title : '原产品期限(INIT_APPLY_TERM)',
				width : 220,
			},
			{
				field : 'loanBankIdBorrow',
				title : '借款银行Id(LOAN_BANK_ID_BORROW)',
				width : 220,
			},
			{
				field : 'loanBankIdStill',
				title : ' 还款银行Id(LOAN_BANK_ID_STILL)',
				width : 220,
			},
			{//0：纸质版，1电子版
				field : 'contractType',
				title : '合同类型 (contract_Type)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if (value == 0) {
						return "纸质版";
					} else if(value == 1){
						return "电子版";
					} else{
						return "";
					}
				},
				
			},
			{
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

function queryBMSLoanProductInfo() {
	/*var productId = $('#pro_productId').combobox('getValue');*/
	//借款编号
	var loanNo = $('#queryLoanProductDiv').find('input[name="loanNo"]').val();
	//申请件编号
	var appNo = $('#queryLoanProductDiv').find('input[name="appNo"]').val();
	
	var loanBaseId = $('#queryLoanProductDiv').find('input[name="loanBaseId"]').val();
	
	//查询
	var options = $('#new_loanProductDatagrid').datagrid('options');
	options.url='loanProduct/listPage';
	options.queryParams={loanNo:loanNo,appNo:appNo,loanBaseId:loanBaseId};
	if(loanNo=="" && appNo=="" && loanBaseId==""){
		$.messager.show({
			title : '提示',
			msg : '请输入查询条件！'
		});
		return false;
	}
	setFirstPage("#new_loanProductDatagrid");
	$('#new_loanProductDatagrid').datagrid('options');
	$("#new_loanProductDatagrid").datagrid('reload');
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








