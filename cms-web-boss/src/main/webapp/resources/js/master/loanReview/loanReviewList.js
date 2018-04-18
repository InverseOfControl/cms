$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSLoanReview();
	    }
	});
});
function initDatagrid() {
		$("#new_loanReviewDatagrid").datagrid({
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
			},{
				field : 'name',
				title : '客户姓名(NAME)',
				width : 220,
			},{
				field : 'idNo',
				title : '证件编号(ID_NO)',
				width : 220,
			},{
				field : 'applyTerm',
				title : '申请期限(APPLY_TERM)',
				width : 220,
			},{
				field : 'productCd',
				title : '申请产品(PRODUCT_CD)',
				width : 220,
			},{
				field : 'productName',
				title : '申请产品名称(PRODUCT_NAME)',
				width : 220,
			},{
				field : 'refuseDate',
				title : '被拒绝时间(REFUSE_DATE)',
				width : 220,
			},{
				field : 'refuseDate',
				title : '拒绝一级原因(REFUSE_DATE)',
				width : 220,
			},{
				field : 'branchManagerCode',
				title : '客户经理code(BRANCH_MANAGER_CODE)',
				width : 220,
			},{
				field : 'branchManagerName',
				title : '客户经理名字(BRANCH_MANAGER_NAME)',
				width : 220,
			}
			,{
				field : 'status',
				title : '状态(STATUS)',
				width : 220,
			},{
				field : 'isRead',
				title : '是否已读(IS_READ)',
				width : 220,
			},{
				field : 'reviewReason',
				title : '复议理由(REVIEW_REASON)',
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

function queryBMSLoanReview() {
	/*var productId = $('#pro_productId').combobox('getValue');*/
	//借款编号
	var loanNo = $('#queryLoanReviewDiv').find('input[name="loanNo"]').val();
	
	
	
	//查询
	var options = $('#new_loanReviewDatagrid').datagrid('options');
	options.url='loanReview/listPage';
	options.queryParams={loanNo:loanNo};
	if(loanNo==""){
		$.messager.show({
			title : '提示',
			msg : '请输入查询条件！'
		});
		return false;
	}
	setFirstPage("#new_loanReviewDatagrid");
	$('#new_loanReviewDatagrid').datagrid('options');
	$("#new_loanReviewDatagrid").datagrid('reload');
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








