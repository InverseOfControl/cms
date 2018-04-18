$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSAuditLimit();
	    }
	});
	
});
function initDatagrid() {
		$("#new_ProductAuditLimitDatagrid").datagrid({
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
			url : 'productAuditLimit/listPage',
			striped : true,
			singleSelect : true,
			rownumbers : true,
			pagination : true,
			fitColumns : false,
			pageList : [ 10, 20, 50 ],
			scrollbarSize : 0,
			columns : [ [  {
				field : 'auditLimitId',
				title : '产品期限ID(AUDIT_LIMIT_ID)',
				width : 220,
			},{
				field : 'productId',
				title : '产品ID(PRODUCT_ID)',
				width : 220,
			},
			{
				field : 'productCode',
				title : '产品代码(PRODUCT_CODE)',
				width : 220,
			}, {
				field : 'auditLimit',
				title : '审核期限(AUDIT_LIMIT)',
				width : 220,
			},{
				field : 'upperLimit',
				title : '阀值上限(UPPER_LIMIT)',
				width : 220,
			},{
				field : 'floorLimit',
				title : '阀值下限(FLOOR_LIMIT)',
				width : 220,
			},{
				field:'creator',
				title:'创建人(CREATOR)',
				width:220,
			},{
				field : 'creatorId',
				title : '创建人Id(CREATOR_ID)',
				width : 220,
			},{
				field : 'creatorDate',
				title : '创建时间(CREATED_TIME)',
				formatter:date_formatter,
				width : 220,
			},{
				field : 'modified',
				title : '修改人(MODIFIER)',
				width : 220,
			},{
				field : 'modifiedDate',
				title : '修改时间(MODIFIED_TIME)',
				formatter:date_formatter,
				width : 220,
			},{
				field : 'modifiedId',
				title : '修改人Id(MODIFIER_ID)',
				width : 220,
			},{
				field : 'version',
				title : '版本号(VERSION)',
				width : 220,
			},{
				field : 'isDeleted',
				title : '是否删除(IS_DELETE)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if (value == 0) {
						return "否";
					} else if(value == 1){
						return "是";
					} else{
						return "";
					}
				}
				},{
					field:'isDisabled',
					title:'是否可用(IS_DISABLED)',
					width:220,
					formatter:function(value, rowData, rowIndex) {
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
  function date_formatter(value, row, index){
	if(value!=null){
		
		var oldTime = new Date(value).format("yyyy-MM-dd ");
	}
	 return oldTime;
}
function queryBMSAuditLimit() {

	//产品ID
	var productId =  $('#queryProductAuditLimitDiv').find('input[name="productId"]').val();
	//查询
	var queryParams = $('#new_ProductAuditLimitDatagrid').datagrid('options').queryParams;
	queryParams.productId = productId;
	setFirstPage("#new_ProductAuditLimitDatagrid");
	$('#new_ProductAuditLimitDatagrid').datagrid('options').queryParams = queryParams;
	$("#new_ProductAuditLimitDatagrid").datagrid('reload');
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








