$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSAppPerson();
	    }
	});
});
function initDatagrid() {
		$("#new_appPersonDatagrid").datagrid({
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
				field : 'perosnNo',
				title : '客户编号(PERSON_NO)',
				width : 220,
			},
			{
				field : 'name',
				title : '姓名(NAME)',
				width : 220,
			} ,{
				field : 'idNo',
				title : '身份证号(ID_NO)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if(value == null ||value == ""){
						return "";
					}else{
						return "*******"+value;
					}
					
			},
			},{
				field : 'idType',
				title : '证件类型(ID_TYPE)',
				width : 220,
			},{
				field : 'creator',
				title : '创建用户(CREATOR)',
				width : 220,
			},{
				field : 'createdTime',
				title : '创建时间(CREATED_TIME)',
				width : 220,
				formatter:date_formatter,
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
				formatter:date_formatter,
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

function queryBMSAppPerson() {
	/*var productId = $('#pro_productId').combobox('getValue');*/
	//客户编号
	var perosnNo = $('#queryAppPersonDiv').find('input[name="perosnNo"]').val();
	//名字
	var name = $('#queryAppPersonDiv').find('input[name="name"]').val();
	//身份证号
	var idNo = $('#queryAppPersonDiv').find('input[name="idNo"]').val();
	
	//查询
	var options = $('#new_appPersonDatagrid').datagrid('options');
	options.url='appPerson/listPage';
	options.queryParams={perosnNo:perosnNo,name:name,idNo:idNo};
	if(perosnNo==""&& name=="" && idNo==""){
		$.messager.show({
			title : '提示',
			msg : '请输入查询条件！'
		});
		return false;
	}
	setFirstPage("#new_appPersonDatagrid");
	$('#new_appPersonDatagrid').datagrid('options');
	$("#new_appPersonDatagrid").datagrid('reload');
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
		var oldTime = new Date(value).format("yyyy-MM-dd");
	}
	 return oldTime;

}








