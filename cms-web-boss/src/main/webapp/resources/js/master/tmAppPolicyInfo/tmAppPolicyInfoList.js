$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSTmAppPolicyInfo();
	    }
	});
});
function initDatagrid() {
		$("#new_tmAppPolicyInfoDatagrid").datagrid({
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
			}
			,{
				field : 'loanNo',
				title : '借款编号(LOAN_NO)',
				width : 120,
			},
			{
				field : 'perosnId',
				title : '客户主表ID(PERSON_ID)',
				width : 220,
			}, {
				field : 'appNo',
				title : '申请件编号(APP_NO)',
				width : 220,
			},{
				field : 'org',
				title : '机构号(ORG)',
				width : 220,
			},{
				field : 'policyId',
				title : '保单信息流水号(POLICY_ID)',
				width : 220,
			},{
				field : 'insuranceAmt',
				title : '保险金额(INSURANCE_AMT)',
				width : 220,
			},{
				field : 'insuranceTerm',
				title : '保险金额(INSURANCE_TERM)',
				width : 220,
			},{
				field : 'paidTerm',
				title : '已缴年限(PAID_TERM)',
				width : 220,
			},{
				field : 'lastPaymentDate',
				title : '最近一次缴费时间(LAST_PAYMENT_DATE)',
				width : 220,
			},{
				field : 'paymentMethod',
				title : '缴费方式(PAYMENT_METHOD)',
				width : 220,
			},{
				field : 'policyRelation',
				title : '与被保险人关系(APP_ORG_NAME)',
				width : 220,
			},{
				field : 'yearPaymentAmt',
				title : '年缴金额(YAER_PAYMENT_AMT)',
				width : 220,
			}
			,{
				field : 'policyCheck',
				title : '保单真伪核实方式(POLICY_CHECK)',
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

function queryBMSTmAppPolicyInfo() {
	/*var productId = $('#pro_productId').combobox('getValue');*/
	//借款编号
	var loanNo = $('#queryTmAppPolicyInfoDiv').find('input[name="loanNo"]').val();
	//申请件编号
	var appNo = $('#queryTmAppPolicyInfoDiv').find('input[name="appNo"]').val();
	
	var loanBaseId = $('#queryTmAppPolicyInfoDiv').find('input[name="loanBaseId"]').val();
	
	//查询
	var options = $('#new_tmAppPolicyInfoDatagrid').datagrid('options');
	options.url='tmAppPolicyInfo/listPage';
	options.queryParams={loanNo:loanNo,appNo:appNo,loanBaseId:loanBaseId};
	if(loanNo=="" && appNo=="" && loanBaseId==""){
		$.messager.show({
			title : '提示',
			msg : '请输入查询条件！'
		});
		return false;
	}
	setFirstPage("#new_tmAppPolicyInfoDatagrid");
	$('#new_tmAppPolicyInfoDatagrid').datagrid('options');
	$("#new_tmAppPolicyInfoDatagrid").datagrid('reload');
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








