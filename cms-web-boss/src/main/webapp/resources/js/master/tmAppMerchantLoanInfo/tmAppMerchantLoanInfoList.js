$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSTmAppMerchantLoanInfo();
	    }
	});
});
function initDatagrid() {
		$("#new_tmAppMerchantLoanInfoDatagrid").datagrid({
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
			url : 'tmAppMerchantLoanInfo/listPage',
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
				field : 'perosnId',
				title : '客户主表ID(PERSON_ID)',
				width : 220,
			},{
				field : 'org',
				title : '机构号(ORG)',
				width : 220,
			},{
				field : 'merchantLoanId',
				title : '淘宝商户贷流水号(MERCHANT_LOAN_ID)',
				width : 220,
			},{
				field : 'setupShopDate',
				title : '开店时间(SETUP_SHOP_DATE)',
				width : 220,
			},{
				field : 'sellerCreditLevel',
				title : '卖家信用等级(SELLER_CREDIT_LEVEL)',
				width : 220,
			},{
				field : 'sellerCreditType',
				title : '卖家信用类型(SELLER_CREDIT_TYPE)',
				width : 220,
			},{
				field : 'regardedNum',
				title : '近半年好评数(REGARDED_NUM)',
				width : 220,
			},{
				field : 'biullAmt1',
				title : '进一个月账单金额(BIULL_AMT1)',
				width : 220,
			},{
				field : 'biullAmt2',
				title : '近两个月账单金额(BIULL_AMT2)',
				width : 220,
			}
			,{
				field : 'biullAmt3',
				title : '近三个月账单金额(BIULL_AMT3)',
				width : 220,
			},{
				field : 'biullAmt4',
				title : '近四个月账单金额(BIULL_AMT4)',
				width : 220,
			},{
				field : 'biullAmt5',
				title : '近五个月账单金额(BIULL_AMT5)',
				width : 220,
			},{
				field : 'biullAmt6',
				title : '近六个月账单金额(BIULL_AMT6)',
				width : 220,
			},{
				field : 'payMonthAmt',
				title : '月均账单金额(PAY_MONTH_AMT)',
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

function queryBMSTmAppMerchantLoanInfo() {
	/*var productId = $('#pro_productId').combobox('getValue');*/
	//借款编号
	var loanNo = $('#querytmAppMerchantLoanInfoDiv').find('input[name="loanNo"]').val();
	//申请件编号
	var appNo = $('#querytmAppMerchantLoanInfoDiv').find('input[name="appNo"]').val();
	
	var loanBaseId = $('#querytmAppMerchantLoanInfoDiv').find('input[name="loanBaseId"]').val();
	
	//查询
	var queryParams = $('#new_tmAppMerchantLoanInfoDatagrid').datagrid('options').queryParams;
	queryParams.loanNo = loanNo;
	queryParams.appNo = appNo;
	queryParams.loanBaseId = loanBaseId;
	setFirstPage("#new_tmAppMerchantLoanInfoDatagrid");
	$('#new_tmAppMerchantLoanInfoDatagrid').datagrid('options').queryParams = queryParams;
	$("#new_tmAppMerchantLoanInfoDatagrid").datagrid('reload');
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








