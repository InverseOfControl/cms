$(function() {
	initDatagrid();
	$(window).keydown(function(event) {
	    if(event.keyCode == 13) {
	    	queryBMSAppPersonInfo();
	    }
	});
});
function initDatagrid() {
		$("#new_appPersonInfoDatagrid").datagrid({
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
				field : 'loanBaseId',
				title : 'LOAN_BASE_ID',
				width : 220,
			},
			{
				field : 'loanNo',
				title : '借款编号(LOAN_NO)',
				width : 220,
			} ,{
				field : 'appNo',
				title : '申请件编号(APP_NO)',
				width : 220,
			},{
				field : 'applyPersonId',
				title : '申请件信息流水(APPLY_PERSON_ID)',
				width : 220,
			},{
				field : 'org',
				title : '机构号(ORG)',
				width : 220,
			},{
				field : 'birthday',
				title : '生日(BIRTHDAY)',
				width : 220,
			},{
				field : 'gender',
				title : '性别(GENDER)',
				width : 220,
			},{
				field : 'age',
				title : '年龄(AGE)',
				width : 220,
			},{
				field : 'idLastDate',
				title : '证件到期日(ID_LAST_DATE)',
				width : 220,
			},{
				field : 'idIssuerAddress',
				title : '户籍地址(ID_ISSUER_ADDRESS)',
				width : 220,
			},{
				field : 'issuerStateId',
				title : '户籍所在省ID(ISSUER_STATE_ID)',
				width : 220,
			},{
				field : 'issuerCityId',
				title : '户籍所在市ID(ISSUER_CITY_ID)',
				width : 220,
			},{
				field : 'issuerZoneId',
				title : '户籍所在区/县ID(ISSUER_ZONE_ID)',
				width : 220,
			},{
				field : 'issuerState',
				title : '户籍所在省(ISSUER_STATE)',
				width : 220,
			},{
				field : 'issuerCity',
				title : '户籍所在市(ISSUER_CITY)',
				width : 220,
			},{
				field : 'issuerZone',
				title : '户籍所在区/县(ISSUER_ZONE)',
				width : 220,
			},{
				field : 'issuerPostcode',
				title : '户籍邮编(ISSUER_POSTCODE)',
				width : 220,
			},{
				field : 'nationality',
				title : '国籍(NATIONALITY)',
				width : 220,
			},{
				field : 'residencyCountryCd',
				title : '永久居住地国家(RESIDENCY_COUNTRY_CD)',
				width : 220,
			},{
				field : 'maritalStatus',
				title : '婚姻状况(MARITAL_STATUS)',
				width : 220,
			},{
				field : 'childrenNum',
				title : '子女数(CHILDREN_NUM)',
				width : 220,
			},{
				field : 'qualification',
				title : '教育状况(QUALIFICATION)',
				width : 220,
			},{
				field : 'graduationDate',
				title : '毕业时间(GRADUATION_DATE)',
				width : 220,
			},{
				field : 'houseOwnership',
				title : '房屋持有类型(HOUSE_OWNERSHIP)',
				width : 220,
			},{
				field : 'houseType',
				title : '住宅类型(HOUSE_TYPE)',
				width : 220,
			},{
				field : 'houseRent',
				title : '租金/元(HOUSE_RENT)',
				width : 220,
			},{
				field : 'liquidAsset',
				title : '个人资产类型(LIQUID_ASSET)',
				width : 220,
			},{
				field : 'cellphone',
				title : '常用手机(CELLPHONE)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if(value == null ||value == ""){
						return "";
					}else{
						return "*******"+value;
					}
			},
			},{
				field : 'cellphoneSec',
				title : '备用手机(CELLPHONE_SEC)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if(value == null ||value == ""){
						return "";
					}else{
						return "*******"+value;
					}
			},
			},{
				field : 'qqNum',
				title : 'qq号(QQ_NUM)',
				width : 220,
			},{
				field : 'wechatNum',
				title : '微信号(WECHAT_NUM)',
				width : 220,
			},{
				field : 'email',
				title : '电子邮箱(EMAIL)',
				width : 220,
			},{
				field : 'familyMember',
				title : '家庭人口(FAMILY_MEMBER)',
				width : 220,
			},{
				field : 'familyAvgeVenue',
				title : '家庭人均收入(FAMILY_AVGE_VENUE)',
				width : 220,
			},{
				field : 'familyMonthPay',
				title : '每月家庭支出(FAMILY_MONTH_PAY)',
				width : 220,
			},{
				field : 'homeAddrCtryCd',
				title : '家庭国家代码(HOME_ADDR_CTRY_CD)',
				width : 220,
			},{
				field : 'homeStateId',
				title : '家庭所在省ID(HOME_STATE_ID)',
				width : 220,
			},{
				field : 'homeCityId',
				title : '家庭所在市ID(HOME_CITY_ID)',
				width : 220,
			},{
				field : 'homeZoneId',
				title : '家庭所在区/县ID(HOME_ZONE_ID)',
				width : 220,
			},{
				field : 'homeState',
				title : '家庭所在省(HOME_STATE)',
				width : 220,
			},{
				field : 'homeCity',
				title : '家庭所在市(HOME_CITY)',
				width : 220,
			},{
				field : 'homeZone',
				title : '家庭所在区/县(HOME_ZONE)',
				width : 220,
			},{
				field : 'homeAddress',
				title : '家庭住址(HOME_ADDRESS)',
				width : 220,
			},{
				field : 'homePostcode',
				title : '家庭住宅邮编(HOME_POSTCODE)',
				width : 220,
			},{
				field : 'homeSameRegistered',
				title : '家庭住址是否同户籍地址(HOME_SAME_REGISTERED)',
				width : 220,
			},{
				field : 'homePhone1',
				title : '住宅电话1(HOME_PHONE1)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {
					if(value == null ||value == ""){
						return "";
					}else{
						return "*******"+value;
					}
			},
			},{
				field : 'homePhone2',
				title : '住宅电话2(HOME_PHONE2)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {	
					if(value == null ||value == ""){
						return "";
					}else{
						return "*******"+value;
					}
			},
			},{
				field : 'homeStandFrom',
				title : '现住址居住起始年月(HOME_STAND_FROM)',
				width : 220,
			},{
				field : 'prOfCountry',
				title : '是否永久居住(PR_OF_COUNTRY)',
				width : 220,
			},{
				field : 'privateOwnersFlag',
				title : '是否私营业主(PRIVATE_OWNERS_FLAG)',
				width : 220,
			},{
				field : 'corpName',
				title : '单位名称(CORP_NAME)',
				width : 220,
			},{
				field : 'corpStructure',
				title : '公司性质(CORP_STRUCTURE)',
				width : 220,
			},{
				field : 'corpAddrCtryCd',
				title : '公司国家代码(CORP_ADDR_CTRY_CD)',
				width : 220,
			},{
				field : 'corpProvinceId',
				title : '公司所在省ID(CORP_PROVINCE_ID)',
				width : 220,
			},{
				field : 'corpCityId',
				title : '公司所在市ID(CORP_CITY_ID)',
				width : 220,
			},{
				field : 'corpZoneId',
				title : '公司所在区/县ID(CORP_ZONE_ID)',
				width : 220,
			},{
				field : 'corpProvince',
				title : '公司所在省(CORP_PROVINCE)',
				width : 220,
			},{
				field : 'corpCity',
				title : '公司所在市(CORP_CITY)',
				width : 220,
			},{
				field : 'corpZone',
				title : '公司所在区/县(CORP_ZONE)',
				width : 220,
			},{
				field : 'corpAddress',
				title : '公司地址(CORP_ADDRESS)',
				width : 220,
			},{
				field : 'corpPostcode',
				title : '公司邮编(HOME_PHONE1)',
				width : 220,
			},{
				field : 'corpPhone',
				title : '公司电话1(CORP_PHONE)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {	
					if(value == null ||value == ""){
						return "";
					}else{
						return "*******"+value;
					}
			},
				
			},{
				field : 'corpPhoneSec',
				title : '公司电话2(CORP_PHONE_SEC)',
				width : 220,
				formatter : function(value, rowData, rowIndex) {	
					if(value == null ||value == ""){
						return "";
					}else{
						return "*******"+value;
					}
			},
			},{
				field : 'corpFax',
				title : '公司传真(CORP_FAX)',
				width : 220,
			},{
				field : 'corpDepapment',
				title : '任职部门(CORP_DEPAPMENT)',
				width : 220,
			},{
				field : 'corpPost',
				title : '职务(CORP_POST)',
				width : 220,
			},{
				field : 'corpStandFrom',
				title : '现单位工作起始(CORP_STAND_FROM)',
				width : 220,
			},{
				field : 'businessNetWork',
				title : '工商网信息(BUSINESS_NET_WORK)',
				width : 220,
			},{
				field : 'corpmemFlag',
				title : '是否公司员工(CORPMEM_FLAG)',
				width : 220,
			},{
				field : 'corpmemNo',
				title : '本公司员工号(CORPMEM_NO)',
				width : 220,
			},{
				field : 'corpType',
				title : '公司行业类别(CORP_TYPE)',
				width : 220,
			},{
				field : 'corpWorkyears',
				title : '本单位工作年限(CORP_WORKYEARS)',
				width : 220,
			},{
				field : 'corpStability',
				title : '工作稳定性(CORP_STABILITY)',
				width : 220,
			},{
				field : 'occupation',
				title : '职业(OCCUPATION)',
				width : 220,
			},{
				field : 'empStatus',
				title : '是否在职(EMP_STATUS)',
				width : 220,
			},{
				field : 'corpPayWay',
				title : '发薪方式(CORP_PAY_WAY)',
				width : 220,
			},{
				field : 'corpPayday',
				title : '发薪日(CORP_PAYDAY)',
				width : 220,
			},{
				field : 'monthSalary',
				title : '单位月收入/元(MONTH_SALARY)',
				width : 220,
			},{
				field : 'otherIncome',
				title : '其他月收入(OTHER_INCOME)',
				width : 220,
			},{
				field : 'totalMonthSalary',
				title : '月总收入元(TOTAL_MONTH_SALARY)',
				width : 220,
			},{
				field : 'setupDate',
				title : '成立时间(SETUP_DATE)',
				width : 220,
			},{
				field : 'monthMaxRepay',
				title : '可接受月最高还款(MONTH_MAX_REPAY)',
				width : 220,
			},{
				field : 'sharesScale',
				title : '占股比例(SHARES_SCALE)',
				width : 220,
			},{
				field : 'registerFunds',
				title : '注册资本/万元(REGISTER_FUNDS)',
				width : 220,
			},{
				field : 'priEnterpriseType',
				title : '私营企业类型(PRI_ENTERPRISE_TYPE)',
				width : 220,
			},{
				field : 'businessPlace',
				title : '经营场所(BUSINESS_PLACE)',
				width : 220,
			},{
				field : 'monthRent',
				title : '月租金/元(MONTH_RENT)',
				width : 220,
			},{
				field : 'employeeNum',
				title : '员工人数/人(EMPLOYEE_NUM)',
				width : 220,
			},{
				field : 'sharesName',
				title : '企业净利润率/%(ENTERPRISE_RATE)',
				width : 220,
			},{
				field : 'sharesName',
				title : '除客户外最大股东(SHARES_NAME)',
				width : 220,
			},{
				field : 'sharesIdNo',
				title : '股东身份证(SHARES_ID_NO)',
				width : 220,
			},{
				field : 'monthAmt',
				title : '每年净利润额/万元(MONTH_AMT)',
				width : 220,
			},{
				field : 'reportId',
				title : '人行征信ID(REPORT_ID)',
				width : 220,
			},{
				field : 'reportMessage',
				title : '绑定记录信息(REPORT_MESSAGE)',
				width : 220,
			},{
				field : 'cusWorkType',
				title : '客户工作类型(CUS_WORK_TYPE)',
				width : 220,
			},
			{
				field : 'theThirdpartyNote',
				title : '第三方详情(电核) (theThirdpartyNote)',
				width : 220,
			},
			{
				field : 'theThirdpartyNoteDetails',
				title : '第三方详情备注(电核) (theThirdpartyNoteDetails)',
				width : 220,
			},
			{
				field : 'ifReportId',
				title : '判断人行ID是否重新绑定 (ifReportId)',
				width : 220,
			},
			{
				field : 'longOnlineId',
				title : '在网时长ID(longOnlineId)',
				width : 220,
			},
			{
				field : 'realNameAuthId',
				title : '实名认证ID(realNameAuthId)',
				width : 220,
			},
			{
				field : 'educationLoanId',
				title : '学历贷ID(educationLoanId)',
				width : 220,
			},
			{
				field : 'hzReportChangeInfo',
				title : '华征报告信息变更快照(hzReportChangeInfo)',
				width : 220,
			},{
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

function queryBMSAppPersonInfo() {
	/*var productId = $('#pro_productId').combobox('getValue');*/
	//借款编号
	var loanNo = $('#queryAppPersonInfoDiv').find('input[name="loanNo"]').val();
	//申请件编号
	var appNo = $('#queryAppPersonInfoDiv').find('input[name="appNo"]').val();
	//loanBaseId
	var loanBaseId = $('#queryAppPersonInfoDiv').find('input[name="loanBaseId"]').val();
	
	//查询
	var options = $('#new_appPersonInfoDatagrid').datagrid('options');
	options.url='appPersonInfo/listPage';
	options.queryParams={loanNo:loanNo,appNo:appNo,loanBaseId:loanBaseId};
	if(loanNo=="" && appNo=="" && loanBaseId==""){
		$.messager.show({
			title : '提示',
			msg : '请输入查询条件！'
		});
		return false;
	}
	setFirstPage("#new_appPersonInfoDatagrid");
	$('#new_appPersonInfoDatagrid').datagrid('options');
	$("#new_appPersonInfoDatagrid").datagrid('reload');
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








