$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSLoanChangeLog();
	    }
	});
});
function initDatagrid() {
		$("#new_loanChangeLogDatagrid").datagrid({
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
			},
			{
				field : 'loanBaseId',
				title : 'LOAN_BASE_ID',
				width : 220,
			},
			{
				field : 'operationModule',
				title : '操作模块code(OPERATION_MODULE)',
				width : 220,
			} ,{
				field : 'operator',
				title : '操作人(OPERATOR)',
				width : 220,
			},{
				field : 'idType',
				title : '证件类型(ID_TYPE)',
				width : 220,
			},{
				field : 'operatorCode',
				title : '操作人工号(OPERATOR_CODE)',
				width : 220,
			},{
				field : 'operationStart',
				title : '操作时间(OPERATION_START)',
				width : 220,
			},{
				field : 'content',
				title : '变更内容(CONTENT)',
				width : 220,
			},{
				field : 'createdTime',
				title : '创建时间(CREATED_TIME)',
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

function queryBMSLoanChangeLog() {
	/*var productId = $('#pro_productId').combobox('getValue');*/
	//loanBaseId
	var loanBaseId = $('#queryLoanChangeLogDiv').find('input[name="loanBaseId"]').val();
	//操作模块code
	var operationModule = $('#queryLoanChangeLogDiv').find('input[name="operationModule"]').val();
	//操作人
	var operator = $('#queryLoanChangeLogDiv').find('input[name="operator"]').val();
	
	//查询
	var options = $('#new_loanChangeLogDatagrid').datagrid('options');
	options.url='loanChangeLog/listPage';
	options.queryParams={loanBaseId:loanBaseId,operationModule:operationModule,operator:operator};
	if(operationModule=="" && operator=="" && loanBaseId==""){
		$.messager.show({
			title : '提示',
			msg : '请输入查询条件！'
		});
		return false;
	}
	setFirstPage("#new_loanChangeLogDatagrid");
	$('#new_loanChangeLogDatagrid').datagrid('options');
	$("#new_loanChangeLogDatagrid").datagrid('reload');
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








