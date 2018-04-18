$(function() {
	initDatagrid();
});
function initDatagrid() {
	
		$("#new_appPersonDatagrid").datagrid({
			url : 'product/listPage',
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
				field : 'productId',
				title : '产品ID(PRODUCT_ID)',
				width : 220,
			},
			{
				field : 'name',
				title : '产品名称(NAME)',
				width : 220,
			},
			{
				field : 'code',
				title : '产品代码(CODE)',
				width : 220,
			} ,{
				field : 'depict',
				title : '产品描述(DEPICT)',
				width : 220,
			},{
				field : 'floorLimit',
				title : '额度下限(FLOOR_LIMIT)',
				width : 220,
			},{
				field : 'upperLimit',
				title : '额度上限(UPPER_LIMIT)',
				width : 220,
			},{
				field:'rate',
				title:'费率(RATE)',
				width:220,
			},{
				field:'adjustBase',
				title:'调整基数(ADJUST_BASE)',
				width:220,
			},{
				field:'creator',
				title:'创建人(CREATOR)',
				width:220,
			},{
				field : 'creatorId',
				title : '创建用户Id(CREATOR_ID)',
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
				title : '修改用户Id(MODIFIER_ID)',
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

function date_formatter(value, row, index){
  if(value!=null){
	  var oldTime = new Date(value).format("yyyy-MM-dd");
	  
  }
	 return oldTime;;

}
