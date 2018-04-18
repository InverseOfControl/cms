$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSSysLogEntity();
	    }
	});
});
function initDatagrid() {
		$("#new_sysLogEntityDatagrid").datagrid({
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
				field : 'systemCode',
				title : '所属系统编码(SYSTEM_CODE)',
				width : 220,
			},
			{
				field : 'systemName',
				title : '所属系统名称(SYSTEM_NAME)',
				width : 220,
			} ,{
				field : 'twoLevelDir',
				title : '二级目录(TWO_LEVEL_DIR)',
				width : 220,
			},{
				field : 'optModule',
				title : '操作模块(OPT_MODULE)',
				width : 220,
			},{
				field : 'optName',
				title : '操作人姓名(OPT_NAME)',
				width : 220,
			},{
				field : 'optTime',
				title : '操作时间(OPT_TIME)',
				width : 220,
			},{
				field : 'requestUri',
				title : '请求地址(REQUEST_URI)',
				width : 220,
			},{
				field : 'employeeType',
				title : '所属岗位(EMPLOYEE_TYPE)',
				width : 220,
			},{
				field : 'firstLevelDir',
				title : '一级目录(FIRST_LEVEL_DIR)',
				width : 220,
			},{
				field : 'optType',
				title : '操作类型(OPT_TYPE)',
				width : 220,
			},{
				field : 'optCode',
				title : '操作人工号(OPT_CODE)',
				width : 220,
			},{
				field : 'params',
				title : '异常信息(PARAMS)',
				width : 220,
			},{
				field : 'romoteAddr',
				title : 'ip地址(ROMOTE_ADDR)',
				width : 220,
			},{
				field : 'memo',
				title : '备注(MEMO)',
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

function queryBMSSysLogEntity() {
	//系统编码
	var systemCode = $('#querySysLogEntityDiv').find('input[name="systemCode"]').val();
	//二级目录
	var twoLevelDir = $('#querySysLogEntityDiv').find('input[name="twoLevelDir"]').val();
	//操作模块
	var optModule = $('#querySysLogEntityDiv').find('input[name="optModule"]').val();
	/*alert($('#querySysLogEntityDiv'));
	alert($('#querySysLogEntityDiv').find('input[name="systemCode"]').val());
	alert($('#systemCode').val());*/
	//查询
	var options = $('#new_sysLogEntityDatagrid').datagrid('options');
	options.url="sysLogEntity/listPage";
	options.queryParams={systemCode:systemCode,twoLevelDir:twoLevelDir,optModule:optModule};
	if(systemCode=="" && twoLevelDir=="" && optModule==""){
		$.messager.show({
			title : '提示',
			msg : '请输入查询条件！'
		});
		return false;
	}
	setFirstPage("#new_sysLogEntityDatagrid");
	$('#new_sysLogEntityDatagrid').datagrid('options');
	$("#new_sysLogEntityDatagrid").datagrid('reload');
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








