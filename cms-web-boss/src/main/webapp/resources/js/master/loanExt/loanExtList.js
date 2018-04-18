$(function() {
	initDatagrid();
	
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSLoanExtInfo();
	    }
	});
	
});
function initDatagrid() {
		$("#new_loanExtDatagrid").datagrid({
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
			columns : [ [  {
				field : 'id',
				title : 'ID',
				width : 60,
			},{
				field : 'loanBaseId',
				title : 'LOAN_BASE_ID',
				width : 120,
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
				field : 'specialOrg',
				title : '机构(SPECIAL_ORG)',
				width : 220,
			},{
				field : 'regularState',
				title : '规则状态(REGULAR_STATE)',
				width : 220,
			},{
				field : 'specialPlan',
				title : '方案(SPECIAL_PLAN)',
				width : 220,
			},{
				field : 'pushDate',
				title : '数信推送时间(PUSH_DATE)',
				width : 220,
			},{
				field : 'reasonShuxin',
				title : '数信拒绝原因(REASON_SHUXIN)',
				width : 220,
			},{
				field : 'creditApplication',
				title : '贷款用途(CREDIT_APPLICATION)',
				width : 220,
			},{
				field : 'sugLmt',
				title : '系统建议额度(SUG_LMT)',
				width : 220,
			},{
				field : 'appOrgName',
				title : '机构名称(APP_ORG_NAME)',
				width : 220,
			},{
				field : 'priority',
				title : '客户等级(PRIORITY)',
				width : 220,
			}
			,{
				field : 'appLoanPlan',
				title : '借款计划(APP_LOAN_PLAN)',
				width : 220,
			},{
				field : 'ensureAmtAmount',
				title : '保证金(ENSURE_AMT_AMOUNT)',
				width : 220,
			},{
				field : 'clientType',
				title : '客户类型(CLIENT_TYPE)',
				width : 220,
			},
		    //private String busNumber;//包银业务流水申请号
			{
				field : 'busNumber',
				title : '包银业务流水申请号(BUS_NUMBER)',
				width : 220,
			},
		   //private String byState;//包银状态值 0黑名单拒绝 1黑名单通过 2银行卡鉴权拒绝 3鉴权通过 4风控信息推送失败 5风控信息推送成功
		    {
				field : 'byState',
				title : '包银状态值 (BY_STATE)',
				width : 220,
			},
		   // private String byRefusalResult;//包银黑名单拒绝原因
		    {
				field : 'byRefusalResult',
				title : '包银黑名单拒绝原因(BY_REFUSAL_RESULT)',
				width : 220,
			},
		    //private String windControlDate;//授信信息推送时间
		    {
				field : 'windControlDate',
				title : '授信信息推送时间(WIND_CONTROL_DATE)',
				width : 220,
			},
		   // private String windControlResult;//风控结果
		    {
				field : 'windControlResult',
				title : '风控结果(WIND_CONTROL_RESULT)',
				width : 220,
			},
		   // private String auditingState;//'人工审核状态 0审核中 1通过  2拒绝 3是图像资料待上传 4是补件';
		    
			{
				field : 'auditingState',
				title : '人工审核状态(AUDITING_STATE)',
				width : 220,
			},
			{
				field : 'rejectPersonName',
				title : '拒绝人姓名(CLIENT_TYPE)',
				width : 220,
			},
		    //private String rejectPersonCode;    //<!-- 拒绝人编码 -->
		    {
				field : 'rejectPersonCode',
				title : '拒绝人编码(CLIENT_TYPE)',
				width : 220,
			},
		   // private String applyStartTime;   //<!-- 申请开始时间 -->
		     {
				field : 'applyStartTime',
				title : '申请开始时间(APPLY_START_TIME)',
				width : 220,
			},
		    //private String applyEndTime;    //<!-- 申请结束时间 -->
		    
		    {
				field : 'applyStartTime',
				title : '申请结束时间 (APPLY_END_TIME)',
				width : 220,
			},
		    //private String auditStartTime;  //<!-- 复核开始时间 -->
		    {
				field : 'auditStartTime',
				title : '复核开始时间 (AUDIT_START_TIME)',
				width : 220,
			},
		   // private String auditEndTime;     //<!-- 复核结束 -->
		     {
				field : 'auditEndTime',
				title : '复核结束时间(AUDIT_END_TIME)',
				width : 220,
			},
		   // private String blacklistId;     //<!--  灰黑名单ID -->
		     {
				field : 'blacklistId',
				title : '灰黑名单ID(BLACKLIST_ID)',
				width : 220,
			},
		    //private String loggedArea;      //<!-- 录入区域 -->
		    
		    {
				field : 'loggedArea',
				title : '录入区域(LOGGED_AREA)',
				width : 220,
			},
		   // private String loggedAreaName;   //<!-- 录入区域名称 -->
		    
		    {
				field : 'loggedAreaName',
				title : '录入区域名称(LOGGED_AREA_NAME)',
				width : 220,
			},
		    //private String reviewSnapVersion;   //<!--  复核快照版本 -->
		    {
				field : 'reviewSnapVersion',
				title : '复核快照版本(REVIEW_SNAP_VERSION)',
				width : 220,
			},
		    //private String auditSnapVersion;    //<!-- 初审审核快照版本 -->
		    {
				field : 'auditSnapVersion',
				title : '复核快照版本(AUDIT_SNAP_VERSION)',
				width : 220,
			},
		   // private String antiFraudScore;      //<!-- 反欺诈评分 -->
		    
		     {
				field : 'antiFraudScore',
				title : '反欺诈评分(ANTI_FRAUD_SCORE)',
				width : 220,
			},
		   // private String antiFraudWarning;     //<!-- 反欺诈预警 -->
		    
		      {
				field : 'antiFraudWarning',
				title : '反欺诈预警 (ANTI_FRAUD_WARNING)',
				width : 220,
			},
		   // private String antiRiskRate;      //<!--   欺诈风险评估 -->
		    
		      {
				field : 'antiRiskRate',
				title : '欺诈风险评估(ANTI_RISK_RATE)',
				width : 220,
			},
		    //private String auditBackSnapVersion;    //<!-- 初审退回快照版本 -->
		      {
				field : 'auditBackSnapVersion',
				title : '初审退回快照版本(AUDIT_BACK_SNAP_VERSION)',
				width : 220,
				formatter : function(value,rowData,rowIndex){
					var html = '';
					if(value!=null){
						html = '<p  id=\'auditBackSnapVersion\' class="easyui-tooltip" > '+value.substring(0,25)+'</p>';
						$("#auditBackSnapVersion").attr("title",value);
					}
				 
					return html;
				}
			},
		   //private String finalauditBackSnapVersion;   //<!--  终审退回快照版本 -->
		     {
				field : 'finalauditBackSnapVersion',
				title : '终审退回快照版本(FINALAUDIT_BACK_SNAP_VERSION)',
				width : 220,
			},
		    //private String primaryReason;
		     {
				field : 'primaryReason',
				title : '一级原因(CLIENT_TYPE)',
				width : 220,
			},
		    //private String secodeReason; 
			 {
				field : 'secodeReason',
				title : '二级原因(CLIENT_TYPE)',
				width : 220,
			},{
				field : 'lujsName',
				title : '陆金所注册用户名(CLIENT_TYPE)',
				width : 220,
			},{
				field : 'lujsApplyNo',
				title : '陆金所进件流水号(lujs_apply_no)',
				width : 220,
			},{
				field : 'orgAuditState',
				title : '机构审核状态("1审核中" "2通过" ,"3不通过" "4补充材料" "5人工取消")(ORG_AUDIT_STATE)',
				width : 220,
			},{
				field : 'appAverageFlag',
				title : 'APP端客服平均分派标识(APP_AVERAGE_FLAG)',
				width : 220,
			},
		    
			{
				field : 'creator',
				title : '创建用户(CREATOR)',
				width : 220,
			},{
				field : 'createdTime',
				title : '创建时间(CREATED_TIME)',
				width : 220,
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

function queryBMSLoanExtInfo() {
	/*var productId = $('#pro_productId').combobox('getValue');*/
	//借款编号
	var loanNo = $('#queryLoanExtDiv').find('input[name="loanNo"]').val();
	//申请件编号
	var appNo = $('#queryLoanExtDiv').find('input[name="appNo"]').val();
	
	var loanBaseId = $('#queryLoanExtDiv').find('input[name="loanBaseId"]').val();
	
	//查询
	var options = $('#new_loanExtDatagrid').datagrid('options');
	options.url='loanExt/listPage';
	options.queryParams={loanNo:loanNo,appNo:appNo,loanBaseId:loanBaseId}
	if(loanNo=="" && appNo=="" && loanBaseId==""){
		$.messager.show({
			title : '提示',
			msg : '请输入查询条件！'
		});
		return false;
	}
	setFirstPage("#new_loanExtDatagrid");
	$('#new_loanExtDatagrid').datagrid('options');
	$("#new_loanExtDatagrid").datagrid('reload');
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








