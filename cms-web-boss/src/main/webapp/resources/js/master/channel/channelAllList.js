$(function() {
	initDatagrid();
});

	function initDatagrid() {
		$("#new_datagrid_channel")
		.datagrid({
			url : 'channel/listPage',
			striped : true,
			rownumbers : true,
			pagination : true,
			fitColumns : false,
			checkOnSelect : false,
			selectOnCheck : false,
			singleSelect : false,
			checkbox : true,
			scrollbarSize : 0,
			columns : [ [
			             {
			            	 field : 'code',
			            	 title : '渠道CODE(CODE)',
			            	 width : 220,
			             },
			             {
			            	 field : 'name',
			            	 title : '渠道名称(NAME)',
			            	 width : 220,
			             },
			             {
			            	 field : 'calculateUrl',
			            	 title : '计算url(CALCULATE_URL)',
			            	 width : 320,
			             }, {
			            	 field : 'startSalesTime',
			            	 title : '起售日期(START_SALES_TIME)',
			            	 width : 150,
			             }, {
			            	 field : 'stopSalesTime',
			            	 title : '停售日期(STOP_SALES_TIME)',
			            	 width : 150,
			             },{
								field:'creator',
								title:'创建人(CREATOR)',
								width:150,
							},{
								field : 'creatorId',
								title : '创建人Id(CREATOR_ID)',
								width : 150,
							},{
								field : 'creatorDate',
								title : '创建时间(CREATED_TIME)',
								formatter:date_formatter,
								width : 150,
							},{
								field : 'modified',
								title : '修改人(MODIFIER)',
								width : 150,
							},{
								field : 'modifiedDate',
								title : '修改时间(MODIFIED_TIME)',
								formatter:date_formatter,
								width : 150,
							},{
								field : 'modifiedId',
								title : '修改人Id(MODIFIER_ID)',
								width : 150,
							},{
								field : 'version',
								title : '版本号(VERSION)',
								width : 150,
							}, {
			            	 field : 'isDisabled',
			            	 title : '是否启用',
			            	 width : 150,
			            	 formatter : function(value, row, index) {
			            		 if (value == 0) {
			            			 return "否";
			            		 } else {
			            			 return "是 ";
			            		 }
			            	 },
			             }, {
								field : 'isDeleted',
								title : '是否删除(IS_DELETE)',
								width : 150,
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
	function date_formatter(value, row, index){
		if(value!=null){
			
			var oldTime = new Date(value).format("yyyy-MM-dd ");
		}
		 return oldTime;
	}
