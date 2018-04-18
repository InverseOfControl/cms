$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSLoanBaseInfo();
	    }
	});
});
function initDatagrid() {
		$("#new_loanBaseDatagrid").datagrid({
		/*	onLoadSuccess:function(data){ 
				  if(data.total==0)
				  {
				    $.messager.show({
		                title:'结果',
		                msg:'没查到符合条件的数据！',
		                showType:'slide',
		            });
				  };
		     },*/
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
			},{
				field : 'personId',
				title : '借款人Id(PERSON_ID)',
				width : 220,
			},
			             
			{
				field : 'loanNo',
				title : '借款编号(LOAN_NO)',
				width : 220,
			}, {
				field : 'appNo',
				title : '申请件编号(APP_NO)',
				width : 220,
			},{
				field : 'status',
				title : '申请件状态(STATUS)',
				width : 220,
			},
			{
				field : 'rtfState',
				title : '流程状态(RTF_STATE)',
				width : 220,
			},{
				field : 'rtfNodeState',
				title : '流程节点状态(RTF_NODE_STATE)',
				width : 220,
			},{
				field : 'signDate',
				title : '签约时间(SIGN_DATE)',
				width : 220,
				formatter:date_formatter,
			},{
				field : 'branchManagerCode',
				title : '客户经理CODE(BRANCH_MANAGER_CODE)',
				width : 220,
			},{
				field : 'branchManagerName',
				title : '客户经理(BRANCH_MANAGER_NAME)',
				width : 220,
			},{
				field : 'serviceName',
				title : '客服姓名(SERVICE_NAME)',
				width : 220,
			},{
				field : 'remark',
				title : '备注(REMARK)',
				width : 220,
			},{
				field : 'reviewName',
				title : '复核人员名字(REVIEW_NAME)',
				width : 220,
			},{
				field : 'signName',
				title : '签约客服名字(SIGN_NAME)',
				width : 220,
			},{
				field : 'contractBranch',
				title : '签约营业部(CONTRACT_BRANCH)',
				width : 220,
			},{
				field : 'loanBranch',
				title : '放款营业部(LOAN_BRANCH)',
				width : 220,
			},{
				field : 'manageBranch',
				title : '管理营业部(MANAGE_BRANCH)',
				width : 220,
			},{
				field : 'manageUpdateDate',
				title : '管理营业部更新时间(MANAGE_UPDATE_DATE)',
				width : 240,
				formatter:date_formatter,
			},{
				field : 'groupForDirector',
				title : '业务组(GROUP_FOR_DIRECTOR)',
				width : 220,
			},{
				field : 'director',
				title : '业务主任(DIRECTOR)',
				width : 220,
			},{
				field : 'loanDate',
				title : '放款日期(LOAN_DATE)',
				width : 220,
				formatter:date_formatter,
			},{
				field : 'owningBranch',
				title : '录单门店(OWNING_BRANCH)',
				width : 220,
			},{
				field : 'handleType',
				title : '处理类型(HANDLE_TYPE)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if (value == 10) {
						return "录入复核退回";
					} else if(value == 20){
						return "信审退回";
					} else if(value == 30){
						return "合同确认退回";
					} else if(value == 40){
						return "财务放款退回";
					} else{
						return "";
					}
				},
			},{
				field : 'owningBranchAttribute',
				title : '进件门店属性(OWNING_BRANCH_ATTRIBUTE)',
				width : 220,
			},{
				field : 'applyType',
				title : '申请类型(APPLY_TYPE)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if (value == 'NEW') {
						return "新生件";
					} else if(value =='TOPUP'){
						return "追加贷款";
					} else if(value == 'RELOAN'){
						return "结清再贷";
					} else{
						return "";
					}
				}
			},{
				field : 'applyInputFlag',
				title : '申请件渠道标识(APPLY_INPUT_FLAG)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if (value == 'applyInput') {
						return "普通进件";
					} else if(value== 'directApplyInput'){
						return "直通车进件";
					}else{
						return "";
					}
				}
			},{
				field : 'appInputFlag',
				title : 'app进件标识(APP_INPUT_FLAG)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if (value == 'app_input') {
						return "APP进件";
					} else{
						return "";
					}
				}
			},{
				field : 'loanId',
				title : '债券Id(LOAN_ID)',
				width : 220,
			},{
				field : 'idNo',
				title : '身份证号(ID_NO)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					
						return "***************"+value;
				},
			},{
				field : 'name',
				title : '姓名(NAME)',
				width : 220,
			},{
				field : 'appApplyDate',
				title : 'app进件申请时间(APP_APPLY_DATE)',
				width : 220,
			},{
				field : 'applyDate',
				title : '申请时间(APPLY_DATE)',
				width : 220,
				formatter:date_formatter,
			},
			{
				field : 'signEndDate',
				title : '签约结束时间(SIGN_END_DATE)',
				width : 220,
				formatter:date_formatter,
			},
			{
				field : 'confirmDate',
				title : '合同确认时间(CONFIRM_DATE)',
				width : 220,
				formatter:date_formatter,
			},
			{
				field : 'confirmEndDate',
				title : '合同确认结束时间(CONFIRM_END_DATE)',
				width : 220,
				formatter:date_formatter,
			},
			{
				field : 'financeAuditTime',
				title : '财务审核时间(FINANCE_AUDIT_TIME)',
				width : 220,
				formatter:date_formatter,
			},
			{
				field : 'auditDate',
				title : '复核时间(AUDIT_DATE)',
				width : 220,
				formatter:date_formatter,
			},
			{
				field : 'lockTarget',
				title : '锁定标的(LOCK_TARGET)',
				width : 220,
			},
			{
				field : 'ifNewLoanNo',
				title : '是否新生件(初审)(IF_NEW_LOAN_NO)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if (value == 0) {
						return "否";
					} else if(value == 1){
						return "是";
					} 
				},
			},
			{
				field : 'managerCode',
				title : '经理code(MANAGER_CODE)',
				width : 220,
			},
			{
				field : 'managerName',
				title : '经理名称(MANAGER_NAME)',
				width : 220,
			},
			{
				field : 'zsIfNewLoanNo',
				title : '终审 是否新生件(ZS_IF_NEW_LOAN_NO)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if (value == 0) {
						return "否";
					} else if(value == 1){
						return "是";
					} 
				},
			},
			{
				field : 'enterBranch',
				title : '进件门店(ENTER_BRANCH)',
				width : 220,
			},
			{
				field : 'enterBranchAttribute',
				title : '进件门店属性(ENTER_BRANCH_ATTRIBUTE)',
				width : 220,
			},
			{
				field : 'logoFlag',
				title : '借款前台标识,1(黄色)(logo_flag)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if (value == 1) {
						return "黄色";
					}
				},
			},
			/*{
				field : 'ifPreferentialUser',
				title : '否优惠费率用户',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if (value == 1) {
						return "黄色";
					}
				},
			},*/
			{
			field : 'ifNeedPatchbolt',
			title : '是否需要补件',
			width : 220,
			formatter : function(value, rowData, rowIndex) {
				if (value == 0) {
					return "不需要";
				}else if(value == 1){
					return "需要";
				}else if(value == 2){
					return "已补件";
				}
			},
			},
			{
				field : 'patchboltTime',
				title : '补件时间',
				width : 220,
			formatter : function(value, rowData, rowIndex) {
					
						return date_formatter(value);
					
				},
				},
			{
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
		
	 $('#status').combobox({
	    	url:'loanBase/findStatus',    
	  	    valueField:'code',    
	  	    textField:'name',
	  	    multiple:true,
	     filter: function(q, row){
	      var opts = $(this).combobox('options');
	      return row[opts.textField].indexOf(q)>-1;//将从头位置匹配改为任意匹配 
	      },
	     onHidePanel: function() {
	                var valueField = $(this).combobox("options").valueField;
	                var val = $(this).combobox("getValue");  //当前combobox的值
	                var allData = $(this).combobox("getData");   //获取combobox所有数据
	                var result = true;      //为true说明输入的值在下拉框数据中不存在
	                for (var i = 0; i < allData.length; i++) {
	                    if (val == allData[i][valueField]) {
	                        result = false;
	                    }
	                }
	                if (result) {
	                    $(this).combobox("clear");
	                }

	            }
	    })

}

function queryBMSLoanBaseInfo() {
	
	//借款编号
	var loanNo = $('#queryLoanBaseDiv').find('input[name="loanNo"]').val();
	//申请件编号
	var appNo = $('#queryLoanBaseDiv').find('input[name="appNo"]').val();
	//姓名
	var name = $('#queryLoanBaseDiv').find('input[name="name"]').val();
	//身份证
	var idNo = $('#queryLoanBaseDiv').find('input[name="idNo"]').val();
   //开始日期
	var startDate=$("#queryLoanBaseDiv").find('input[name="startDate"]').val();
	//结束日期
	var endDate=$("#queryLoanBaseDiv").find('input[name="endDate"]').val();
	//开始区间
	var startVersion=$("#queryLoanBaseDiv").find('input[name="startVersion"]').val();
	//结束区间
	var endVersion=$("#queryLoanBaseDiv").find('input[name="endVersion"]').val();
	//申请件状态
	var chooseStatus =$("#status").combobox('getValues');
	//录单门店
	var owningBranch=$('#queryLoanBaseDiv').find('input[name="owningBranch"]').val();
	//签约营业部
	var contractBranch=$('#queryLoanBaseDiv').find('input[name="contractBranch"]').val();
	//客服CODE
	var serviceCode=$('#queryLoanBaseDiv').find('input[name="serviceCode"]').val();
	//客服名称
	var serviceName=$('#queryLoanBaseDiv').find('input[name="serviceName"]').val();
	//是否APP进件
	var appInputFlag=$("#appInputFlag").combobox('getValue');
	//查询
	var options = $('#new_loanBaseDatagrid').datagrid('options');
	var status ="";
	for(var i =0 ;i<chooseStatus.length;i++){
		status +=chooseStatus[i];
		if(i<chooseStatus.length-1){
			status +=",";
		}
	}
	options.url = 'loanBase/listPage';
	options.queryParams = { loanNo: loanNo, appNo: appNo, name:name,idNo:idNo,startDate:startDate,endDate:endDate,startVersion:startVersion,endVersion:endVersion,
			                status:status,owningBranch:owningBranch,contractBranch:contractBranch,serviceCode:serviceCode,serviceName:serviceName,appInputFlag:appInputFlag};
	if(loanNo=="" && appNo=="" && name=="" && idNo=="" && startDate=="" && endDate==""&&startVersion=="" && endVersion=="" && status==""
		 && owningBranch=="" && contractBranch=="" && serviceCode=="" && serviceName=="" && appInputFlag==""){
		$.messager.show({
			title : '提示',
			msg : '请输入查询条件！'
		});
		return false;
	}
	setFirstPage("#new_loanBaseDatagrid");
	$('#new_loanBaseDatagrid').datagrid('options');
	$("#new_loanBaseDatagrid").datagrid('reload');
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








