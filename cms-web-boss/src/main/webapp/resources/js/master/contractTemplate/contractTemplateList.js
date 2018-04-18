$(function() {
	initDatagrid();
});
function initDatagrid() {
	$("#new_contractTemplate_datagrid").datagrid({
						onLoadSuccess : function(data) {
							if (data.total == 0) {
								$.messager.show({
									title : '结果',
									msg : '没查到符合条件的数据！',
									showType : 'slide',
								});
							}
							;
						},
						url : 'contractTemplate/listPage',
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
						        	field : 'id',
						        	title:'ID',
						        	width:220
						        }, {
									field : 'code',
									title : '模板编码(CODE)',
									width : 220
								},{
									field : 'name',
									title : '模板名称(NAME)',
									width : 220,
								},{
									field : 'templateContent',
									title : '模板内容',
									width : 220,
									hidden : true
								},{
									field : 'templateUrl',
									title : '模板文件地址(TEMPLATE_URL)',
									width : 220,
									hidden : true
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
									field : 'printType',
									title : '打印类型',
									width : 220,
									formatter : function(value, row, index) {
										if (value != null && value != ''
												&& value != undefined) {
											if (value == 1) {
												return "套打";
											} else if (value == 2) {
												return "非套打";
											} else {
												return "";
											}
										}
									},
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
									},
								}] ]
					});
}

function date_formatter(value, row, index){
	if(value!=null){
		
		var oldTime = new Date(value).format("yyyy-MM-dd ");
	}
	 return oldTime;
}

/**
 * 设置查询分页信息
 * 
 * @param ids
 */
function setcha_pro_FirstPage(ids) {
	var opts = $(ids).datagrid('options');
	var pager = $(ids).datagrid('getPager');
	opts.pageNumber = 1;
	opts.pageSize = opts.pageSize;
	pager.pagination('refresh', {
		pageNumber : 1,
		pageSize : opts.pageSize
	});
}
