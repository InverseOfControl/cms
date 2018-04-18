$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSTmAppEstateInfo();
	    }
	});
});
function initDatagrid() {
		$("#new_tmAppEstateInfoDatagrid").datagrid({
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
				field : 'perosnId',
				title : '客户主表ID(PERSON_ID)',
				width : 220,
			},{
				field : 'loanNo',
				title : '借款编号(LOAN_NO)',
				width : 220,
			}, {
				field : 'appNo',
				title : '申请件编号(APP_NO)',
				width : 220,
			},{
				field : 'estateId',
				title : '房产信息流水号(ESTATE_ID)',
				width : 220,
			},{
				field : 'org',
				title : '机构号(ORG)',
				width : 220,
			},{
				field : 'estateType',
				title : '房产类型(ESTATE_TYPE)',
				width : 220,
			},{
				field : 'estateLoan',
				title : '房贷情况(ESTATE_LOAN)',
				width : 220,
			},{
				field : 'estateBuyDate',
				title : '购买时间(ESTATE_BUY_DATE)',
				width : 220,
			},{
				field : 'estateAmt',
				title : '购买总价值/万元(ESTATE_AMT)',
				width : 220,
			},{
				field : 'referenceAmt',
				title : '市参考价/万元(REFERENCE_AMT)',
				width : 220,
			},{
				field : 'estateLoanAmt',
				title : '房贷金额/万元(ESTATE_LOAN_AMT)',
				width : 220,
			},{
				field : 'monthPaymentAmt',
				title : '月供(MONTH_PAYMENT_AMT)',
				width : 220,
			}
			,{
				field : 'hasRepaymentNum',
				title : '已还期数(HAS_REPAYMENT_NUM)',
				width : 220,
			},{
				field : 'builtupArea',
				title : '建筑面积(BUILTUP_AREA)',
				width : 220,
			},{
				field : 'houseOwnership',
				title : '房产所有权(HOUSE_OWNERSHIP)',
				width : 220,
			},{
				field : 'equityRate',
				title : '产权比例(EQUITY_RATE)',
				width : 220,
			},{
				field : 'otherName',
				title : '共有人姓名(OTHER_NAME)',
				width : 220,
			},{
				field : 'otherIdNo',
				title : '共有人身份证号(OTHER_ID_NO)',
				width : 220,
			},{
				field : 'estateState',
				title : '房产所在省(ESTATE_STATE)',
				width : 220,
			},{
				field : 'estateCity',
				title : '房产所在市(ESTATE_CITY)',
				width : 220,
			},{
				field : 'estateZone',
				title : '房产所在区/县(ESTATE_ZONE)',
				width : 220,
			},{
				field : 'estateAddress',
				title : '房产地址(ESTATE_ADDRESS)',
				width : 220,
			},{
				field : 'ifMe',
				title : '单据户名为本人(IF_ME)',
				width : 220,
			},{
				field : 'pengyuanCheck',
				title : '鹏元验证共有人(PENGYUAN_CHECK)',
				width : 220,
			},{
				field : 'personBankCheck',
				title : '人行是否获取到住址或房产地址(PERSON_BANK_CHECK)',
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

function queryBMSTmAppEstateInfo() {
	/*var productId = $('#pro_productId').combobox('getValue');*/
	//借款编号
	var loanNo = $('#queryTmAppEstateInfoDiv').find('input[name="loanNo"]').val();
	//申请件编号
	var appNo = $('#queryTmAppEstateInfoDiv').find('input[name="appNo"]').val();
	
	var loanBaseId = $('#queryTmAppEstateInfoDiv').find('input[name="loanBaseId"]').val();
	
	//查询
	var options = $('#new_tmAppEstateInfoDatagrid').datagrid('options');
	options.url='tmAppEstateInfo/listPage';
	options.queryParams={loanNo:loanNo, appNo:appNo, loanBaseId:loanBaseId};
	if(loanNo=="" && appNo=="" && loanBaseId==""){
		$.messager.show({
			title : '提示',
			msg : '请输入查询条件！'
		});
		return false;
	}
	setFirstPage("#new_tmAppEstateInfoDatagrid");
	$('#new_tmAppEstateInfoDatagrid').datagrid('options');
	$("#new_tmAppEstateInfoDatagrid").datagrid('reload');
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








