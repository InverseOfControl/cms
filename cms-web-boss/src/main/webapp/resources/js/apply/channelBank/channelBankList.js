$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryChannelBankEntity();
	    }
	});
});
function initDatagrid() {
		$("#new_datagrid_channelBank").datagrid({
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
			url : 'channelBank/listPage',
			rownumbers : true,
			pagination : true,
			fitColumns : false,
			checkOnSelect: false,
			selectOnCheck: false,
			singleSelect: false,
			checkbox:true,
			scrollbarSize : 0,
			columns : [ [ 
			{
				field : 'channelId',
				title : '渠道ID(CHANNEL_ID)',
				width : 220,
			},{
				field : 'bankId',
				title : '银行ID(BANK_ID)',
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
   function queryChannelBankEntity() {
	/*var productId = $('#pro_productId').combobox('getValue');*/
	//期限ID
	var channelId = $('#queryChannelBankEntityDiv').find('input[name="channelId"]').val();
	//渠道ID
	var bankId = $('#queryChannelBankEntityDiv').find('input[name="bankId"]').val();
	//查询
	var queryParams = $('#new_datagrid_channelBank').datagrid('options').queryParams;
	
	queryParams.channelId = channelId;
	queryParams.bankId = bankId;
	setFirstPage("#new_datagrid_channelBank");
	$('#new_datagrid_channelBank').datagrid('options').queryParams = queryParams;
	$("#new_datagrid_channelBank").datagrid('reload');
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








