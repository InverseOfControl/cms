$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSLoanBaseRela();
	    }
	});
});
function initDatagrid() {
		$("#new_loanBaseRelaDatagrid").datagrid({
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
			},{
				field : 'personId',
				title : '客户主表ID(PERSON_ID)',
				width : 120,
			},{
				field : 'bmsAppPersonInfoId',
				title : '主卡申请人信息表主键(BMS_APP_PERSON_INFO_ID)',
				width : 220,
			}, {
				field : 'bmsTmAppEstateInfoId',
				title : '房产信息表主键(BMS_TM_APP_ESTATE_INFO_ID)',
				width : 220,
			},{
				field : 'bmsTmAppProvidentInfoId',
				title : '公积金信息表主键(BMS_TM_APP_PROVIDENT_INFO_ID)',
				width : 220,
			},{
				field : 'bmsTmAppPolicyInfoId',
				title : '保单信息表主键(BMS_TM_APP_POLICY_INFO_ID)',
				width : 220,
			},{
				field : 'bmsTmAppMasterLoanInfoId',
				title : '淘宝达人贷信息表主键(BMS_TM_APP_MASTER_LOAN_INFO_ID)',
				width : 220,
			},{
				field : 'bmsTmAppMerchantLoanInfoId',
				title : '淘宝商户贷信息表主键(BMS_TM_APP_MERCHANT_LOAN_INFO_ID)',
				width : 220,
			},{
				field : 'bmsTmAppCarInfoId',
				title : '车辆信息表主键(BMS_TM_APP_CAR_INFO_ID)',
				width : 220,
			},{
				field : 'bmsTmAppSalaryLoanInfoId',
				title : '随薪贷信息表主键(BMS_TM_APP_SALARY_LOAN_INFO_ID)',
				width : 220,
			},{
				field : 'bmsTmAppCardLoanInfoId',
				title : '卡友贷信息表主键(BMS_TM_APP_CARD_LOAN_INFO_ID)',
				width : 220,
			},{
				field : 'bmsTmAppContactInfoId',
				title : '卡友贷信息表主键(BMS_TM_APP_CARD_LOAN_INFO_ID)',
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

function queryBMSLoanBaseRela() {
	/*var productId = $('#pro_productId').combobox('getValue');*/
	//借款编号
	/*var loanNo = $('#queryLoanBaseRelaDiv').find('input[name="loanNo"]').val();*/
	//申请件编号
	var personId = $('#queryLoanBaseRelaDiv').find('input[name="personId"]').val();
	
	var loanBaseId = $('#queryLoanBaseRelaDiv').find('input[name="loanBaseId"]').val();
	
	//查询
	var options = $('#new_loanBaseRelaDatagrid').datagrid('options');
	options.url='loanBaseRela/listPage';
	options.queryParams={personId:personId,loanBaseId:loanBaseId};
	if(personId=="" && loanBaseId==""){
		$.messager.show({
			title : '提示',
			msg : '请输入查询条件！'
		});
		return false;
	}
	setFirstPage("#new_loanBaseRelaDatagrid");
	$('#new_loanBaseRelaDatagrid').datagrid('options');
	$("#new_loanBaseRelaDatagrid").datagrid('reload');
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








