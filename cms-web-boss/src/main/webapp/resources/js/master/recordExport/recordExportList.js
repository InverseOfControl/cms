$(function() {
	//新增页面默认关闭
	initDatagrid1();
	initDatagrid2();
	$('#ydGridPage').attr("style","display:none");
});

function initDatagrid1() {
		$("#new_datagrid_recordExportSZ").datagrid({
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
			url : 'recordExport/listPageSZ',
			striped : true,
			singleSelect : true,
			rownumbers : true,
			pagination : true,
			fitColumns : false,
			pageList : [ 10, 20, 50 ],
			scrollbarSize : 0,
			columns : [ [{
				field : 'id',
				title : 'id',
				//checkbox : true,
				width : 30,
				hidden:true
			}, 
		  {
				field : 'merchantCode',
				title : '商户代码',
				width : 120,
			}, {
				field : 'expenditure',
				title : '费项',
				width : 120,
			},
			{
				field : 'bankType',
				title : '行别',
				width : 120,
			},
			{
				field : 'accountNumber',
				title : '账号',
				width : 120,
			},
			{
				field : 'name',
				title : '户名',
				width : 120,
			}
			] ]
		});
}


function initDatagrid2() {
	$("#new_datagrid_recordExportYD").datagrid({
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
		url : 'recordExport/listPageYD',
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		fitColumns : false,
		pageList : [ 10, 20, 50 ],
		scrollbarSize : 0,
		columns : [ [{
			field : 'id',
			title : 'id',
			//checkbox : true,
			width : 30,
			hidden:true
		}, 
	  {
			field : 'merchantCode',
			title : '商户代码',
			width : 120,
		}, {
			field : 'expenditure',
			title : '费项',
			width : 120,
		},
		{
			field : 'bankType',
			title : '行别',
			width : 120,
		},
		{
			field : 'bankNo',
			title : '行号',
			width : 120,
		},
		{
			field : 'accountNumber',
			title : '账号',
			width : 120,
		},
		{
			field : 'name',
			title : '户名',
			width : 120,
		},
		{
			field : 'remark',
			title : '备注',
			width : 120,
		}
		] ]
	});
}

function queryBMSRecordExportInfo() {
	var startDate=$('#startDate').datebox('getValue');	
	var endDate=$('#endDate').datebox('getValue');
	var queryAreaType=$('#queryAreaType').combobox('getValue');
	var contractNo=$('#contractNo').val();
	if(queryAreaType==01){//深圳地区
		//查询
		var queryParams = $('#new_datagrid_recordExportSZ').datagrid('options').queryParams;
		queryParams.startDate = startDate;
		queryParams.endDate = endDate;
		queryParams.queryAreaType = queryAreaType;
		queryParams.contractNo = contractNo;
		setFirstPage("#new_datagrid_recordExportSZ");
		$('#new_datagrid_recordExportSZ').datagrid('options').queryParams = queryParams;
		$("#new_datagrid_recordExportSZ").datagrid('reload');
	}else{//异地
		//查询
		var queryParams = $('#new_datagrid_recordExportYD').datagrid('options').queryParams;
		queryParams.startDate = startDate;
		queryParams.endDate = endDate;
		queryParams.queryAreaType = queryAreaType;
		queryParams.contractNo = contractNo;
		setFirstPage("#new_datagrid_recordExportYD");
		$('#new_datagrid_recordExportYD').datagrid('options').queryParams = queryParams;
		$("#new_datagrid_recordExportYD").datagrid('reload');
	}
	
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

//导出
function uploadRecordInfo(){
	var queryAreaType=$('#queryAreaType').combobox('getValue');
	var startDate=$('#startDate').datebox('getValue');	
	var endDate=$('#endDate').datebox('getValue');
	var contractNo=$('#contractNo').val();
	if(queryAreaType==01){
		window.location.href="recordExport/uploadExcelSZ?startDate="+startDate+"&endDate="+endDate+"&contractNo="+contractNo;
	}else{
		window.location.href="recordExport/uploadExcelYD?startDate="+startDate+"&endDate="+endDate+"&contractNo="+contractNo;
	}
}

$('#queryAreaType').combobox({
	onSelect: function(param){
		if(param.value==01){//深圳
			queryBMSRecordExportInfo();
			$('#szGridPage').attr("style","display:block");
			$('#ydGridPage').attr("style","display:none");
		}else{//异地
			queryBMSRecordExportInfo();
			$('#ydGridPage').attr("style","display:block");
			$('#szGridPage').attr("style","display:none");
		}
	}
});

