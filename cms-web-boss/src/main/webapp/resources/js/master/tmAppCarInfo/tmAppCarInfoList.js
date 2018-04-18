$(function() {
	initDatagrid();

	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSTmAppCarInfo();
	    }
	});
});
function initDatagrid() {
		$("#new_tmAppCarInfoDatagrid").datagrid({
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
				field : 'perosnId',
				title : '客户主表ID(PERSON_ID)',
				width : 220,
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
				field : 'org',
				title : '机构号(ORG)',
				width : 220,
			},{
				field : 'checkId',
				title : '资料核对流水号(CHECK_ID)',
				width : 220,
			},{
				field : 'carType',
				title : '车辆类型(CAR_TYPR)',
				width : 220,
			},{
				field : 'carLoan',
				title : '是否有车贷(CAR_LOAN)',
				width : 220,
			},{
				field : 'monthPaymentAmt',
				title : '月供(MONTH_PAYMENT_AMT)',
				width : 220,
			},{
				field : 'carBuyDate',
				title : '车辆购买时间(CAR_BUY_DATE)',
				width : 220,
			},{
				field : 'nakedCarPrice',
				title : '裸车价/万元(NAKED_CAR_PRICE)',
				width : 220,
			},{
				field : 'carBuyPrice',
				title : '购买价/万元(CAR_BUY_PRICE)',
				width : 220,
			},{
				field : 'transferDate',
				title : '过户时间(TRANSFER_DATE)',
				width : 220,
			}
			,{
				field : 'carLoanTerm',
				title : '贷款剩余期数(CAR_LOAN_TERM)',
				width : 220,
			},{
				field : 'operationStatus',
				title : '营运状况(OPERATION_STATUS)',
				width : 220,
			},{
				field : 'carSeat',
				title : '车辆座位数(CAR_SEAT)',
				width : 220,
			},{
				field : 'localPlate',
				title : '本地车牌(LOCAL_PLATE)',
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

function queryBMSTmAppCarInfo() {
	
	//借款编号
	var loanNo = $('#queryTmAppCarInfoDiv').find('input[name="loanNo"]').val();
	//申请件编号
	var appNo = $('#queryTmAppCarInfoDiv').find('input[name="appNo"]').val();
	
	var loanBaseId = $('#queryTmAppCarInfoDiv').find('input[name="loanBaseId"]').val();
	
	//查询
	var options = $('#new_tmAppCarInfoDatagrid').datagrid('options');
	options.url='tmAppCarInfo/listPage';
	options.queryParams = {loanNo:loanNo,appNo:appNo,loanBaseId:loanBaseId};
	setFirstPage("#new_tmAppCarInfoDatagrid");
	$('#new_tmAppCarInfoDatagrid').datagrid('options');
	$("#new_tmAppCarInfoDatagrid").datagrid('reload');
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








