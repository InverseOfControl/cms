$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSTmAppMasterLoanInfo();
	    }
	});
});
function initDatagrid() {
		$("#new_tmAppMasterLoanInfoDatagrid").datagrid({
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
			}, {
				field : 'perosnId',
				title : '客户主表ID(PERSON_ID)',
				width : 220,
			}, {
				field : 'org',
				title : '机构号(ORG)',
				width : 220,
			},{
				field : 'masterLoadId',
				title : '淘宝达人贷信息流水号(MASTER_LOAD_ID)',
				width : 220,
			},{
				field : 'acctRegisterDate',
				title : '账户注册时间(ACCT_REGISTER_DATE)',
				width : 220,
			},{
				field : 'buyerCreditLevel',
				title : '卖家信用等级(BUYER_CREDIT_LEVEL)',
				width : 220,
			},{
				field : 'buyerCreditType',
				title : '卖家信用类型(BUYER_CREDIT_TYPE)',
				width : 220,
			},{
				field : 'lastYearPayAmt',
				title : '上一年度支出额(LAST_YEAR_PAY_AMT)',
				width : 220,
			},{
				field : 'payAmt1',
				title : '近一个月支出额(PAY_AMT1)',
				width : 220,
			},{
				field : 'payAmt2',
				title : '近两个月支出额(PAY_AMT2)',
				width : 220,
			},{
				field : 'payAmt3',
				title : '近三个月支出额(PAY_AMT3)',
				width : 220,
			},{
				field : 'shoppingAmt1',
				title : '近一个月购物额(SHOPPING_AMT1)',
				width : 220,
			}
			,{
				field : 'shoppingAmt2',
				title : '近两个月购物额(SHOPPING_AMT2)',
				width : 220,
			},{
				field : 'shoppingAmt3',
				title : '近三个月购物额(SHOPPING_AMT3)',
				width : 220,
			},{
				field : 'payMonthAmt',
				title : '月均支出额(PAY_MONTH_AMT)',
				width : 220,
			},{
				field : 'shoppingMonthAmt',
				title : '月均购物额(SHOPPING_MONTH_AMT)',
				width : 220,
			},{
				field : 'goodRate',
				title : '好评率(GOOD_RATE)',
				width : 220,
			},{
				field : 'lastYearShoppingAmt',
				title : '上一年度购物额[淘宝](LAST_YEAR_SHOPPING_AMT)',
				width : 220,
			},{
				field : 'deliveryAddress',
				title : '收货地址(DELIVERY_ADDRESS)',
				width : 220,
			},{
				field : 'jiDongUserLevel',
				title : '京东用户等级(JI_DONG_USER_LEVEL)',
				width : 220,
			},{
				field : 'pastYearShoppingAmount',
				title : '近一年消费总量[京东](PAST_YEAR_SHOPPING_AMOUNT)',
				width : 220,
			},{
				field : 'whiteCreditValue',
				title : '小白信用分(WHITE_CREDIT_VALUE)',
				width : 220,
			},{
				field : 'sesameCreditValut',
				title : '芝麻信用分(SESAME_CREDIT_VALUE)',
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

function queryBMSTmAppMasterLoanInfo() {
	/*var productId = $('#pro_productId').combobox('getValue');*/
	//借款编号
	var loanNo = $('#queryTmAppMasterLoanInfoDiv').find('input[name="loanNo"]').val();
	//申请件编号
	var appNo = $('#queryTmAppMasterLoanInfoDiv').find('input[name="appNo"]').val();
	
	var loanBaseId = $('#queryTmAppMasterLoanInfoDiv').find('input[name="loanBaseId"]').val();
	
	//查询
	var options = $('#new_tmAppMasterLoanInfoDatagrid').datagrid('options');
	options.url="tmAppMasterLoanInfo/listPage";
	options.queryParams={loanNo:loanNo,appNo:appNo,loanBaseId:loanBaseId};
	if(loanNo=="" && appNo=="" && loanBaseId==""){
		$.messager.show({
			title : '提示',
			msg : '请输入查询条件！'
		});
		return false;
	}
	setFirstPage("#new_tmAppMasterLoanInfoDatagrid");
	$('#new_tmAppMasterLoanInfoDatagrid').datagrid('options');
	$("#new_tmAppMasterLoanInfoDatagrid").datagrid('reload');
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








