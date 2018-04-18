$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSBankInfo();
	    }
	});
});
function initDatagrid() {
		$("#new_datagrid_bank").datagrid({
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
			url : 'bank/listPage',
			striped : true,
			singleSelect : true,
			rownumbers : true,
			pagination : true,
			fitColumns : false,
			pageList : [ 10, 20, 50 ],
			scrollbarSize : 0,
			columns : [ [ 
		  {
			    field:'id',
			    title:'银行ID(ID)',
			    width:220,
		   },{
				field : 'code',
				title : '银行编码(CODE)',
				width : 220,
			}, {
				field : 'name',
				title : '银行名称(NAME)',
				width : 220,
			},
			{
				field : 'isDisabled',
				title : '是否启用(IS_DISABLED)',
				width : 150,
				formatter : function(value, rowData, rowIndex) {
				if (value == 0) {
					return "是";			
				} else{
					return "否";
				}
				}
			},
			{
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

function queryBMSBankInfo() {
	var name = $('#queryBankDiv').find('input[name="name"]').val();
	//查询
	var queryParams = $('#new_datagrid_bank').datagrid('options').queryParams;
	queryParams.name = name;
	setFirstPage("#new_datagrid_bank");
	$('#new_datagrid_bank').datagrid('options').queryParams = queryParams;
	$("#new_datagrid_bank").datagrid('reload');
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
function date_formatter(value, row, index){
	if(value!=null){	
		var oldTime = new Date(value).format("yyyy-MM-dd ");
	}
	 return oldTime;;

}


