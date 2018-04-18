$(function() {
	initDatagrid();
});
$(window).keydown(function(event) {
    if(event.keyCode == 13) {
    	queryBMSPatchBoltInfo();
    }
});

function initDatagrid() {
		$("#new_datagrid_patchBolt").datagrid({
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
			url : 'patchBolt/listPage',
			striped : true,
			rownumbers : true,
			pagination : true,
			fitColumns : false,
			checkOnSelect: false,
			selectOnCheck: false,
			singleSelect: false,
			checkbox:true,
			fit:true,
			scrollbarSize : 0,
			columns : [ [ 
		/*{
			 	field : 'loanNo',
			 	title : '借款单号',
			 	hidden:true,
			 	width : 220,
		},*/
		  {
				field : 'customerName',
				title : '客户姓名',
				width : 220,
			}, {
				field : 'customerIDNO',
				title : '身份证号码',
				width : 220,
			},{
				field : 'productName',
				title : '产品名称',
				width : 220
			},{
				field : 'branchManagerName',
				title : '客服经理',
				width : 220
			},{
				field : 'serviceName',
				title : '客服',
				width : 220
			},{
				field : 'contractLmt',
				title : '签约金额',
				width : 100
			},{
				field : 'applyLmt',
				title : '合同金额',
				width : 100
			},{
				field : 'contractTerm',
				title : '签约期限',
				width : 100
			},
			{
				field : 'status',
				title : '借款状态',
				width : 100,
				formatter : function(value, rowData, rowIndex) {
					if (value =="NORMAL") {
						return "放款确认";			
					} 
				}
			},
			{
				field : 'operation',
				title : '操作',
				formatter : formatOperations,
				width : 250
			}
			] ]
		});
}

function queryBMSPatchBoltInfo() {
	var customerName = $('#customerName').val();
	var customerIDNO=$('#customerIDNO').val();
	//查询
	var queryParams = $('#new_datagrid_patchBolt').datagrid('options').queryParams;
	queryParams.customerName = customerName;
	queryParams.customerIDNO = customerIDNO;
	setFirstPage("#new_datagrid_patchBolt");
	$('#new_datagrid_patchBolt').datagrid('options').queryParams = queryParams;
	$("#new_datagrid_patchBolt").datagrid('reload');
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
function formatOperations(value,row,index) {
	var operations = '';
	operations += '<a href="javascript:void(0)" onclick="loadPatchBoltToWindow(\''+row.loanNo+'\',\''+row.patchBoltUrl+'\')">补件 &nbsp;&nbsp;</a>';
    return operations;
 
 };
 
 function loadPatchBoltToWindow(loanNo,url){
	 var opertorCode=$('#loginCode').val();
	 var patchUrl=url+'?nodeKey=patchManagement&sysName=aps&operator=bms&jobNumber='+opertorCode+'&appNo='+loanNo;
	 var title='补件信息';
	 var content = '<iframe src="' + patchUrl + '" width="98%" height="98%" ></iframe>';  
	 var boarddiv = '<div id="hovertreewindow" title="' + title + '"></div>'//style="overflow:hidden;"可以去掉滚动条  
	 $(document.body).append(boarddiv);  
	 var win = $('#hovertreewindow').dialog({  
	       content: content,  
	       width: '1100px',  
	       height: '600px',   
	       title: title,  
	       onClose: function () {  
	            $(this).dialog('destroy');//后面可以关闭后的事件  
	        }  
	    });  
	    win.dialog('open');  
 }