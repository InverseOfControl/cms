$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSTmAppProvidentInfo();
	    }
	});
});
function initDatagrid() {
		$("#new_tmAppProvidentInfoDatagrid").datagrid({
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
			}, {
				field : 'appNo',
				title : '申请件编号(APP_NO)',
				width : 220,
			},{
				field : 'org',
				title : '机构号(ORG)',
				width : 220,
			},{
				field : 'providentId',
				title : '公积金信息流水号(PROVIDENT_ID)',
				width : 220,
			},{
				field : 'openAccountDate',
				title : '开户时间(OPEN_ACCOUNT_DATE)',
				width : 220,
			},{
				field : 'depositBase',
				title : '缓存基数(DEPOSIT_BASE)',
				width : 220,
			},{
				field : 'depositRate',
				title : '缓存比例(DEPOSIT_RATE)',
				width : 220,
			},{
				field : 'monthDepositAmt',
				title : '月缓存额(MONTH_DEPOSIT_AMT)',
				width : 220,
			},{
				field : 'providentInfo',
				title : '公积金材料(PROVIDENT_INFO)',
				width : 220,
			},{
				field : 'paymentUnit',
				title : '缴纳单位同申请单位(PAYMENT_UNIT)',
				width : 220,
			},{
				field : 'paymentMonthNum',
				title : '申请单位已缴月数(PAYMENT_MONTH_NUM)',
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

function queryBMSTmAppProvidentInfo() {
	/*var productId = $('#pro_productId').combobox('getValue');*/
	//借款编号
	var loanNo = $('#queryTmAppProvidentInfoDiv').find('input[name="loanNo"]').val();
	//申请件编号
	var appNo = $('#queryTmAppProvidentInfoDiv').find('input[name="appNo"]').val();
	
	var loanBaseId = $('#queryTmAppProvidentInfoDiv').find('input[name="loanBaseId"]').val();
	
	//查询
	var options = $('#new_tmAppProvidentInfoDatagrid').datagrid('options');
	options.url='tmAppProvidentInfo/listPage';
	options.queryParams={loanNo:loanNo,appNo:appNo,loanBaseId:loanBaseId};
	if(loanNo=="" && appNo=="" && loanBaseId==""){
		$.messager.show({
			title : '提示',
			msg : '请输入查询条件！'
		});
		return false;
	}
	setFirstPage("#new_tmAppProvidentInfoDatagrid");
	$('#new_tmAppProvidentInfoDatagrid').datagrid('options');
	$("#new_tmAppProvidentInfoDatagrid").datagrid('reload');
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








