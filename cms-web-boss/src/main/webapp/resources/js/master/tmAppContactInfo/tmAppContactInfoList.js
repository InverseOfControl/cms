$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSTmAppContactInfo();
	    }
	});
});
function initDatagrid() {
		$("#new_tmAppContactInfoDatagrid").datagrid({
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
			},
			{
				field : 'headId',
				title : '联系人主表ID(HEAD_ID)',
				width : 220,
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
				field : 'personId',
				title : '客户主表ID(PERSON_ID)',
				width : 220,
			},{
				field : 'org',
				title : '机构号(ORG)',
				width : 220,
			},{
				field : 'contactId',
				title : '联系人流水(CONTACT_ID)',
				width : 220,
			},{
				field : 'sequenceNum',
				title : '排序号(SEQUENCE_NUM)',
				width : 220,
			},{
				field : 'ifKnowLoan',
				title : '是否知晓贷款(IF_KNOW_LOAN)',
				width : 220,
			},{
				field : 'contactName',
				title : '姓名(CONTACT_NAME)',
				width : 220,
			},{
				field : 'contactRelation',
				title : '与申请人关系(CONTACT_RELATION)',
				width : 220,
			},{
				field : 'contactGender',
				title : '性别(CONTACT_GENDER)',
				width : 220,
			},{
				field : 'contactCellphone',
				title : '手机号(CONTACT_CELLPHONE)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if(value == null ||value == ""){
						
						return "";
					}else{
						return "*******"+value;
					}
				},
			}
				
			,{
				field : 'contactCellphone1',
				title : '手机号2(CONTACT_CELLPHONE_1)',
				width : 220,
			},{
				field : 'contactIdNo',
				title : '身份证号码(CONTACT_ID_NO)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if(value == null ||value == ""){
						
						return "";
					}else{
						return "*******"+value;
					}
				},
			},{
				field : 'contactEmpName',
				title : '公司名称(CONTACT_EMP_NAME)',
				width : 220,
			},{
				field : 'contactCorpPhone',
				title : '公司电话号码(CONTACT_CORP_PHONE)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if(value == null ||value == ""){
						
						return "";
					}else{
						return "*******"+value;
					}
				},
			},{
				field : 'contactCorpPhone1',
				title : '公司电话号码2(CONTACT_CORP_PHONE_1)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if(value == null ||value == ""){
						
						return "";
					}else{
						return "*******"+value;
					}
				},
			},{
				field : 'contactCorpFax',
				title : '公司传真(CONTACT_CORP_FAX)',
				width : 220,
			},{
				field : 'contactCorpPost',
				title : '职务(CONTACT_CORP_POST)',
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



function queryBMSTmAppContactInfo() {
	/*var productId = $('#pro_productId').combobox('getValue');*/
	//借款编号
	var loanNo = $('#queryTmAppContactInfoDiv').find('input[name="loanNo"]').val();
	//申请件编号
	var appNo = $('#queryTmAppContactInfoDiv').find('input[name="appNo"]').val();
	
	var loanBaseId = $('#queryTmAppContactInfoDiv').find('input[name="loanBaseId"]').val();
	
	//查询
	var options = $('#new_tmAppContactInfoDatagrid').datagrid('options');
	options.url='tmAppContactInfo/listPage';
	options.queryParams={loanNo:loanNo,appNo:appNo,loanBaseId:loanBaseId};
	if(loanNo=="" && appNo=="" && loanBaseId==""){
		$.messager.show({
			title : '提示',
			msg : '请输入查询条件！'
		});
		return false;
	}
	setFirstPage("#new_tmAppContactInfoDatagrid");
	$('#new_tmAppContactInfoDatagrid').datagrid('options');
	$("#new_tmAppContactInfoDatagrid").datagrid('reload');
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










