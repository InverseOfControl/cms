package com.ymkj.cms.biz.service.sign.lujs.ifc;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.CommonUtil;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.base.core.common.http.HttpUtil;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.apply.IApplyEnterExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLufax800001Vo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLujsInformVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLufax800001Vo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLujsInformVo;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.MD5Util;
import com.ymkj.cms.biz.common.util.MD5UtilLujs;
import com.ymkj.cms.biz.dao.sign.IBMSCreditRuleInformDao;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.dao.sign.ILujsDao;
import com.ymkj.cms.biz.entity.sign.BMSCreditRuleInformEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.CreditHttpService;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.http.ILuJSHttpService;
import com.ymkj.cms.biz.service.sign.lujs.LUJSUtils;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqOrganizationVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationInfo;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description:陆金所接口服务</p>
 * @uthor YM10159
 * @date 2017年7月7日 上午11:15:33
 */
@Service
public class LUJSInterfaceService{

	@Autowired
	private ICoreHttpService coreHttpService;

	@Autowired
	private ILuJSHttpService luJSHttpService;
	
	@Autowired
	private IOrganizationExecuter iOrganizationExecuter;
	
	@Autowired
	private ILoanBaseEntityDao loanBaseDao;
	
	@Autowired
	private IBMSCreditRuleInformDao creditRuleInformDao;

	@Value("#{env['requestByUrl']?:''}")
	private String requestByUrl;

	@Value("#{env['requestByKey']?:''}")
	private String sxKey;
	
	@Value("#{env['creditReportAnalyze']?:''}")
	private String creditReportAnalyze;
	
	@Value("#{env['amsWebApiUrl']?:''}")
    private String amsWebApiUrl;

	@Autowired
	private ILujsDao lujsDao;

	private Logger logger = LoggerFactory.getLogger(LUJSInterfaceService.class);
	
	@Autowired
	private IApplyEnterExecuter applyEnterExecuter;
	
	@Autowired
	private CreditHttpService creditHttpService; 

	/**
	 * @Description:陆金所文件上传接口</p>
	 * @uthor YM10159
	 * @date 2017年7月7日 下午4:37:43
	 */
	public String upLoadFileByLujs(String url,String appNo, String fileName,String classSort) {
		JSONObject requestMap = new JSONObject();
		JSONObject params = new JSONObject();
		
		String result = "";
		
		String dataTime = DateUtil.format(new Date(), "yyyyMMddHHmmss");
		try {
			//获取文件内容
			byte[] picContent = LUJSUtils.getByteFromUrl(url);
			String encryptContent = Base64.encodeBase64String(picContent);
			
			//调渠道网关上传文件
			requestMap.put("fileName", fileName); // 文件名
			requestMap.put("fileContent", encryptContent); // 文件内容
			params.put("requestId", "pic" + dataTime);
			params.put("projectNo", "pic");
			params.put("reqParam", requestMap);
			String encryptionString = "lufaxupload" + params.toString() + dataTime + sxKey;
			
			JSONObject fileUploadObj = new JSONObject();
			fileUploadObj.put("funcId", "lufaxupload");
			fileUploadObj.put("key", dataTime);
			fileUploadObj.put("params", params);
			fileUploadObj.put("sign", MD5Util.md5Hex(encryptionString));

			HttpResponse httpResponse = luJSHttpService.lufaxPreGataway(fileUploadObj);
			logger.info("陆金所文件上传接口返回结果："+ httpResponse.getContent());
			
			JSONObject obj = new JSONObject(httpResponse.getContent());
			JSONObject infosObj = obj.getJSONObject("infos");
			if("0000".equals(obj.getString("resCode")) && 200 == infosObj.getInt("HttpStatus")){
				result = infosObj.getString("resKey");
			}else{
				logger.error("陆金所文件上传接口调用失败："+obj.get("resCode"));
				throw new BizException(BizErrorCode.EOERR, "陆金所文件上传接口调用失败："+obj.get("resCode"));
			}
		} catch (Exception e) {
			logger.error("陆金所文件上传接口调用失败："+e);
		}
		return result;
	}

	/**
	 * @Description:陆金所借款申请信息提交接口</p>
	 * @uthor YM10159
	 * @date 2017年7月8日 下午1:46:54
	 */
	public Map<String,Object> lujsSignInfoSubmit(Map<String,Object> submitMap){
		
		String loanNo = ObjectUtils.toString(submitMap.get("loanNo"));
		JSONArray fileList = (JSONArray) submitMap.get("jsonArray");
		String applyType = ObjectUtils.toString(submitMap.get("applyType"));
		String productCode = ObjectUtils.toString(submitMap.get("productCode"));
		String reportId = ObjectUtils.toString(submitMap.get("reportId"));
		
		logger.info("借款编号【"+loanNo+"】陆金所接口入参："+ submitMap);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String dataTime =DateUtil.format(new Date(), DateUtil.default_pattern_minutes_ss);

		Map<String,Object> applyInfoMap = lujsDao.getApplicationList(loanNo);
		logger.info("借款编号【"+loanNo+"】陆金所接口信息："+ applyInfoMap);
		
		if(null == applyInfoMap || applyInfoMap.isEmpty()){
			returnMap.put("resCode", "-1");
			returnMap.put("resMsg", "陆金所进件信息为空！");
			return returnMap;
		}
		
		JSONObject requestMap=new JSONObject();
		try{	
			//客户信息
			JSONObject CUSTOMER_INFO = new JSONObject();

			//申请信息
			JSONObject APPLICATION_LIST = new JSONObject();
			APPLICATION_LIST.put("APPL_NO_HOST", "ZDJR_"+loanNo);//订单编号
			APPLICATION_LIST.put("LUFAX_BORROWER_USERNAME", getStr(applyInfoMap,"lujs_name"));//陆金所网站用户名
			
			APPLICATION_LIST.put("lufax_loan_req_id", getStr(applyInfoMap,"lujs_loan_req_id"));
			APPLICATION_LIST.put("APPL_PURPOSE", "个人消费");//贷款目的

			APPLICATION_LIST.put("APPL_DATE", getStr(applyInfoMap,"apply_date")); //申请日期
			APPLICATION_LIST.put("APPL_TYPE", submitMap.get("applyType")); //申请类型   01-新案件；02-补充材料
			APPLICATION_LIST.put("APPL_TOTAL_AMOUNT", getStr(applyInfoMap,"contract_lmt"));//借款金额传合同金额
			APPLICATION_LIST.put("APPL_TERM", getStr(applyInfoMap,"apply_term"));//贷款期限
			APPLICATION_LIST.put("APPL_TERM_UNIT", "月");//贷款期限单位
			APPLICATION_LIST.put("APPL_INSTALMENT_NUM", "1");//贷款期数
			APPLICATION_LIST.put("LOAN_RATE", Double.valueOf(getStr(applyInfoMap,"rate"))*100);//贷款年利率
			APPLICATION_LIST.put("PAYEE_BANK_NAME", getStr(applyInfoMap,"apply_bank_name"));//还款银行
			APPLICATION_LIST.put("PAYEE_BANK_CODE", getStr(applyInfoMap,"apply_bank_code"));//还款银行编码
			APPLICATION_LIST.put("PAYEE_BANK_BRANCE", getStr(applyInfoMap,"apply_bank_branch"));//还款银行分行
			APPLICATION_LIST.put("PAYEE_ACCOUNT", getStr(applyInfoMap,"apply_bank_card_no"));//还款帐户
			APPLICATION_LIST.put("PAYEE_NAME", getStr(applyInfoMap,"name"));//还款姓名
			APPLICATION_LIST.put("APPL_FLAG", StringUtils.isBlank(getStr(applyInfoMap,"lujs_apply_no")) ? "0" : "1" );//申请件类型
			APPLICATION_LIST.put("AUDIT_INFO", "审批通过");//审批意见
			APPLICATION_LIST.put("REPAYMENT_METHOD", "EQ_CI");//还款方式
			APPLICATION_LIST.put("RETURN_PERIOD", getStr(applyInfoMap,"apply_term"));//还款周期
			APPLICATION_LIST.put("ACCOUNT_TYPE", "new");//账户类型
			APPLICATION_LIST.put("IDENDITY_PHONE", getStr(applyInfoMap,"bank_phone"));//银行卡鉴权手机号码
			APPLICATION_LIST.put("RETAIN_PHONE", getStr(applyInfoMap,"bank_phone"));//银行预留手机号码
			APPLICATION_LIST.put("LOAN_TYPE", "3");//贷款类型
			if(productCode.equals(LUJSUtils.WGDRD)){
				APPLICATION_LIST.put("LOAN_CLASS", "网购达人贷");//贷款种类
				APPLICATION_LIST.put("PRODUCT_CODE", "1000500010001");//产品小类编码(证盈e)
			}else if(productCode.equals(LUJSUtils.BDD)){
				APPLICATION_LIST.put("LOAN_CLASS", "保单贷");
				APPLICATION_LIST.put("PRODUCT_CODE", "1000500010002");
			}else if(productCode.equals(LUJSUtils.KYD)){
				APPLICATION_LIST.put("LOAN_CLASS", "卡友贷");
				APPLICATION_LIST.put("PRODUCT_CODE", "1000500010003");
			}
			APPLICATION_LIST.put("PARENT_PRODUCT_CODE", "1000500010");//产品大类编码
			APPLICATION_LIST.put("CHANNEL_CODE", "XDY_ZDJR");//产品大类
			APPLICATION_LIST.put("service_company_code", "ZDJR_SHENZHEN");//服务方
			APPLICATION_LIST.put("DEPT_NO", "WD001");//机构编码证大机构码

			//个人信息
			JSONObject CUSTOMER_BASE_LIST = new JSONObject();
			CUSTOMER_BASE_LIST.put("CUST_TYPE", "1");//客户类型
			CUSTOMER_BASE_LIST.put("CUST_NAME", getStr(applyInfoMap,"name"));//客户姓名
			CUSTOMER_BASE_LIST.put("IDENTITY_TYPE", "Ind01");//证件类型
			CUSTOMER_BASE_LIST.put("IDENTITY", getStr(applyInfoMap,"id_no"));//证件编号
			CUSTOMER_BASE_LIST.put("CREDIT_CERT_END_TIME", getStr(applyInfoMap,"id_last_date"));//证件到期日
			CUSTOMER_BASE_LIST.put("mobile", getStr(applyInfoMap,"cellphone"));//手机号码
			CUSTOMER_BASE_LIST.put("SEX", getStr(applyInfoMap,"gender"));//性别
			CUSTOMER_BASE_LIST.put("BIRTHDAY", getStr(applyInfoMap,"id_no").substring(6, 14));//出生年月直接截取身份证
			CUSTOMER_BASE_LIST.put("MARITAL_STATUS", getStr(applyInfoMap,"marital_status"));//婚姻状况
			CUSTOMER_BASE_LIST.put("IS_WORK", getStr(applyInfoMap,"occupation"));//是否有工作
			CUSTOMER_BASE_LIST.put("AGE", LUJSUtils.getAgeByIdNo(getStr(applyInfoMap,"id_no")));//年龄
			CUSTOMER_BASE_LIST.put("EDUCATION", getStr(applyInfoMap,"qualification"));//教育程度 

			String residenceArea="2";
			String id_issuer_address = getStr(applyInfoMap,"id_issuer_address");
			String cityName = "";
			
			//获取录单门店所在区域
			ReqOrganizationVO organizationVO = new ReqOrganizationVO();
			organizationVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
			organizationVO.setId(Long.valueOf(ObjectUtils.toString(applyInfoMap.get("owning_branch_id"))));
			Response<ResOrganizationInfo> orgInfoByID = iOrganizationExecuter.getOrgInfoByID(organizationVO);
			if (orgInfoByID.isSuccess()) {
				ResOrganizationInfo res = orgInfoByID.getData();
				cityName = res.getCityName();
			}
			
			if(id_issuer_address.indexOf(cityName) != -1){
				residenceArea = "1";
			}
			CUSTOMER_BASE_LIST.put("RESIDENCE_AREA", residenceArea);//户口所在地1：本地   2：非本地

			//收入信息
			JSONArray CUSTOMER_INCOME_LIST = new JSONArray();
			JSONObject customerIncomeList = new JSONObject();
			
			customerIncomeList.put("INCOME_IDENTITY", getStr(applyInfoMap,"cus_work_type"));//雇佣类型
			customerIncomeList.put("MONTHLY_INCOME", getStr(applyInfoMap,"month_salary"));//月收入（有小数点）
			CUSTOMER_INCOME_LIST.put(customerIncomeList);
			
			//职业信息
			JSONArray CUSTOMER_OCCUPATION_LIST = new JSONArray();
			JSONObject CustomerOccupationList = new JSONObject();
			CustomerOccupationList.put("UNIT_NAME", getStr(applyInfoMap,"corp_name"));//单位名称
			CustomerOccupationList.put("DEPARTMENT", getStr(applyInfoMap,"corp_depapment"));//部门
			
			String profession = ObjectUtils.toString(applyInfoMap.get("occupation"));
			String statetype="在职";
			if("00004".equals(profession)) statetype="无业";
			
			//职业
			if("00001".equals(profession)) profession="工薪";
			if("00002".equals(profession)) profession="白领";
			if("00003".equals(profession)) profession="自营";
			if("00004".equals(profession)) profession="学生";
			if("00005".equals(profession)) profession="公务员";
			if("A".equals(profession)) profession="个体业主";
			if("B".equals(profession)) profession="企业主";
			
			CustomerOccupationList.put("PROFESSION", profession);//职业
			CustomerOccupationList.put("STATETYPE", statetype);//职业状况
			CustomerOccupationList.put("WORKING_AREA", getStr(applyInfoMap,"corp_province")+getStr(applyInfoMap,"corp_city"));//工作的区
			CUSTOMER_OCCUPATION_LIST.put(CustomerOccupationList);
			
			//地址信息
			JSONArray CUSTOMER_ADDRESS_LIST = new JSONArray();
			JSONObject CustomeAddressList = new JSONObject();
			CustomeAddressList.put("RELATION", "本人");//与借款人的关系
			String addressType="";
			if("00001".equals(getStr(applyInfoMap,"house_type"))){
				addressType="房产";
			}else if("00002".equals(getStr(applyInfoMap,"house_type"))){
				addressType="单位";
			}else{
				addressType="居住地";
			}
			CustomeAddressList.put("ADDRESS_TYPE", addressType);//地址类型 
			CustomeAddressList.put("ADDRESS_COUNTRY", "中国");//国家
			CustomeAddressList.put("ADDRESS_PROVINCE", getStr(applyInfoMap,"home_state"));//省
			CustomeAddressList.put("ADDRESS_CITY", getStr(applyInfoMap,"home_city"));//市
			CustomeAddressList.put("ADDRESS_DISTRICT", getStr(applyInfoMap,"home_zone"));//区
			CustomeAddressList.put("ADDRESS", getStr(applyInfoMap,"home_address"));//地址详情
			CUSTOMER_ADDRESS_LIST.put(CustomeAddressList);
			
			//贷款材料流水
			JSONArray CUSTOMER_SERIALNO_LIST = new JSONArray();
			JSONObject customerSerialnoList = new JSONObject();
			CUSTOMER_SERIALNO_LIST.put(customerSerialnoList);

			JSONArray APPLICATION_IMAGE_LIST = new JSONArray();
			int s4 = 1, s8 = 1, s10 = 1, s12 = 1, l = 1, n = 1;
			int b = 1;
			for(int i = 0; i < fileList.length(); i++){
				JSONObject tempObj = fileList.getJSONObject(i);
				JSONObject ApplicationImageList = new JSONObject();
				String code = tempObj.getString("subclassSort");
				if("S4".equals(code)){
					ApplicationImageList.put("DOCUMENT_TYPE", "201");//身份证
					ApplicationImageList.put("DOCUMENT_NO", String.valueOf(s4));//文档序号
					s4++;
				}else if("S8".equals(code) || "S11".equals(code)){
					ApplicationImageList.put("DOCUMENT_TYPE", "20303");//咨询服务协议和风险金服务协议
					ApplicationImageList.put("DOCUMENT_NO", String.valueOf(s8));
					s8++;
				}else if("S10".equals(code)){
					ApplicationImageList.put("DOCUMENT_TYPE", "20302");//面签照片
					ApplicationImageList.put("DOCUMENT_NO", String.valueOf(s10));
					s10++;
				}else if("S12".equals(code)){
					ApplicationImageList.put("DOCUMENT_TYPE", "20301");//身份证验证结果
					ApplicationImageList.put("DOCUMENT_NO", String.valueOf(s12));
					s12++;
				}else if("L".equals(code)){
					ApplicationImageList.put("DOCUMENT_TYPE", "20601");//征信报告
					ApplicationImageList.put("DOCUMENT_NO", String.valueOf(l));
					l++;
				}else if("N".equals(code) || "H".equals(code) || ("M".equals(code) && productCode.equals(LUJSUtils.KYD))){
					ApplicationImageList.put("DOCUMENT_TYPE", "20602");//支付宝相关信息
					ApplicationImageList.put("DOCUMENT_NO", String.valueOf(n));
					n++;
				}else if("B".equals(code) || "C".equals(code) || ("M".equals(code) && !productCode.equals(LUJSUtils.KYD))){
					ApplicationImageList.put("DOCUMENT_TYPE", "207");
					ApplicationImageList.put("DOCUMENT_NO", String.valueOf(b));
					b++;
				}else{
					continue;
				}
				
				ApplicationImageList.put("BARCODE_NO", tempObj.getString("resKey"));//图片名称
				ApplicationImageList.put("URL", tempObj.getString("resKey"));//索引
				ApplicationImageList.put("NET_TYPE", "outer");//内外网
				ApplicationImageList.put("IMAGE_TYPE", "imc");//影像类型
				APPLICATION_IMAGE_LIST.put(ApplicationImageList);
			}
			
			for(int i = 0; i < fileList.length(); i++){
				JSONObject tempObj = fileList.getJSONObject(i);
				JSONObject objM = new JSONObject();
				String code = tempObj.getString("subclassSort");
				String url = ObjectUtils.toString(tempObj.getString("resKey"));
				if("L".equals(code) && url.indexOf("html") == -1){
					objM.put("DOCUMENT_TYPE", "207");
					objM.put("DOCUMENT_NO", String.valueOf(b));
					b++;
				}else{
					continue;
				}
				objM.put("BARCODE_NO", url);
				objM.put("URL", url);
				objM.put("NET_TYPE", "outer");
				objM.put("IMAGE_TYPE", "imc");
				APPLICATION_IMAGE_LIST.put(objM);
			}	

			//客户信息原始数据
			JSONObject CUSTOMER_TYPE_ORIGINAL = new JSONObject();
			CUSTOMER_TYPE_ORIGINAL.put("RESIDENCE_AREA", residenceArea);//户口所在地
			CUSTOMER_TYPE_ORIGINAL.put("EMPLOYEE_TYPE", getStr(applyInfoMap,"cus_work_type"));//雇佣类型
			CUSTOMER_TYPE_ORIGINAL.put("MARITAL_STATUS", getStr(applyInfoMap,"marital_status"));//婚姻状况
			
			CUSTOMER_INFO.put("aPPLICATION_LIST", APPLICATION_LIST);
			CUSTOMER_INFO.put("cUSTOMER_BASE_LIST", CUSTOMER_BASE_LIST);
			CUSTOMER_INFO.put("cUSTOMER_INCOME_LIST", CUSTOMER_INCOME_LIST);
			CUSTOMER_INFO.put("cUSTOMER_SERIALNO_LIST", CUSTOMER_SERIALNO_LIST);
			CUSTOMER_INFO.put("aPPLICATION_IMAGE_LIST", APPLICATION_IMAGE_LIST);
			CUSTOMER_INFO.put("cUSTOMER_TYPE_ORIGINAL", CUSTOMER_TYPE_ORIGINAL);
			CUSTOMER_INFO.put("cUSTOMER_ADDRESS_LIST", CUSTOMER_ADDRESS_LIST);
			CUSTOMER_INFO.put("cUSTOMER_OCCUPATION_LIST", CUSTOMER_OCCUPATION_LIST);
			requestMap.put("CUSTOMER_INFO", CUSTOMER_INFO);
			
			//征信信息
			JSONObject CREDIT_INFO = new JSONObject();
			if(StringUtils.isNotBlank(reportId)){
				Map<String,Object> appDataMap = new HashMap<>();
				appDataMap.put("param", "reportId="+reportId+"&applNoHost="+"ZDJR_"+loanNo);
				JSONObject obj = creditHttpService.creditReportAnalyze(appDataMap);
				CREDIT_INFO = obj;
			}
			requestMap.put("CREDIT_INFO", CREDIT_INFO);
			
			//推送申请信息到陆金所
			JSONObject json=new JSONObject();
			JSONObject params=new JSONObject();
			
			params.put("requestId", "aps"+dataTime);
			params.put("projectNo", "aps");
			params.put("reqParam", requestMap);
			
			json.put("funcId", "lufax100001");
			json.put("key", dataTime);
			json.put("params", params);
			
			logger.info("借款编号【"+loanNo+"】陆金所进件入参："+ params.toString());
			String encryptionString="lufax100001"+params.toString()+dataTime+sxKey;

			json.put("sign", MD5Util.md5Hex(encryptionString));
			
			HttpResponse httpResponse = luJSHttpService.lufaxPreGataway(json);
			JSONObject content = new JSONObject(httpResponse.getContent());
			JSONObject infosObject = null;
			
			returnMap.put("resCode", "-1");
			if(!"0000".equals(content.getString("resCode"))){
				returnMap.put("resMsg", content.getString("respDesc"));
				return returnMap;
			}else{
				infosObject = content.getJSONObject("infos");
				if(!"0000".equals(infosObject.getString("ret_code"))){
					returnMap.put("resMsg", infosObject.getString("ret_msg"));
				}else{
					JSONObject infoContenetJson = infosObject.getJSONObject("info_content");
					returnMap.put("resCode", "000000");
					returnMap.put("lujsApplyNo",infoContenetJson.getString("aps_apply_no"));
					
					//保存进件流水号
					returnMap.put("loanNo", loanNo);
					int count = lujsDao.saveApsApplyNo(returnMap);
					if(count == 0){
						returnMap.put("resCode", "-1");
						returnMap.put("resMsg", "陆金所进件流水号保存失败！");
					}else{
						//如果是补件，修改陆金所通知结果表bms_lujs_inform，防止录单页面重复提示补件
						if("02".equals(applyType)){
							lujsDao.delLujsManualAnditInfo(loanNo);
						}
					}
				}
			}
		}catch(Exception e){
			logger.error("调用借款申请提交接口抛出异常",e);
		}
		return returnMap;
	}
	
	public String getStr(Map<String,Object> applyInfoMap,String str){
		String result = ObjectUtils.toString(applyInfoMap.get(str));
		
		if(str.equals("apply_bank_code")){
			if("04105840".equals(result)){
				result = "307";
			}
		}
		
		//教育程度
		if(str.equals("qualification")){
			if("00001".equals(result)){//硕士及以上
				result="03";
			}else if("00002".equals(result)){//本科
				result="04";
			}else if("00003".equals(result)){//大专
				result="05";
			}else if("00004".equals(result)){//中专
				result="06";
			}else if("00005".equals(result)){//高中
				result="07";
			}else if("00006".equals(result)){//初中及以下
				result="08";
			}
			return result;
		}
		
		//陆金所名称
		if(str.equals("lujs_name") && StringUtils.isBlank(result)){
			return "lujs_name";
		}

		//证件到期日
		if(str.equals("id_last_date")){ 
			if( StringUtils.isNotBlank(result)){
				try {
					result = DateUtil.format(DateUtil.strToDate(result, DateUtil.default_pattern_day), DateUtil.pattern_day);
				} catch (ParseException e) {}
			}else{
				result = DateUtil.format(DateUtil.getNowDateAfter(12,new Date()), DateUtil.pattern_day);
			}
		}
		
		//婚姻
		if(str.equals("marital_status")){
			if("00001".equals(result)) result = "1";
			if("00002".equals(result)) result = "2";
			if("00003".equals(result)) result = "4";
			if("00005".equals(result)) result = "3";
			if("00006".equals(result)||"00007".equals(result)) result = "5";
		}
		
		//职业
		if(str.equals("occupation")){
			if(StringUtils.isNotBlank(result)){
				if("00004".equals(result)) result="N";
				else result="Y";
			}else{
				result="Y";
			}
		}

		//雇佣类型
		if(str.equals("cus_work_type")){
			if(StringUtils.isNotBlank(result)){
				if("00001".equals(result)) result = "1";
				else if("00002".equals(result)) result = "2";
				else if("00003".equals(result)) result = "2";
			}else{
				result = "6";
			}
		}
		
		//性别
		if(str.equals("gender")){
			String sex = result.substring(16, 17);
			if(Integer.parseInt(sex)%2 == 0){
				return "F";
			}else{
				return "M";
			}
		}
		
		//申请日期
		if(str.equals("apply_date")){
			try {
				result = DateUtil.format(DateUtil.strToDate(result, DateUtil.default_pattern_day), DateUtil.default_pattern_day);
			} catch (ParseException e) {}
		}
		
		return result;
	}

	/**
	 * 陆金所调用核心获取合同金额接口
	 * @param reqLoanContractSignVo
	 * @param res
	 * @return
	 * @author lix YM10160
	 * @date 2017年7月10日下午4:48:14
	 */
	public boolean queryContractMoney(Map<String, Object> httpParamMap, Response<ResLoanContractSignVo> res){
		HttpResponse httpResponse = coreHttpService.queryContractMoney(httpParamMap);
		if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
			String content = httpResponse.getContent();
			Map contentMap = JsonUtils.decode(content, Map.class);
			if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("resCode"))){
				Map attachment = (Map) contentMap.get("attachment");
				if(attachment != null){
					Double pactMoney = Double.valueOf(attachment.get("pactMoney").toString());
					//校验合同金额是否高于10W，若高于10W，则提示“合同金额不得高于10万，请重新修改后保存 ！”
					if(pactMoney > 100000){
						setError(new BizException(CoreErrorCode.REQUEST_PARAM_ERROR,"合同金额不得高于10万，请重新修改后保存 ！"), res);
						return false;
					}
					return true;
				} else {
					setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"接口返回参数attachment错误！"), res);
					return false;
				}
				// 成功

			} else {
				setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,contentMap.get("resMsg")), res);
				return false;
			}
		} else{
			setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"接口响应失败"), res);
			return false;
		}

	}

	/**
	 * 异常返回值处理
	 * 
	 * @param biz
	 * @param res
	 * @return
	 */
	protected Response setError(BizException biz, Response res) {
		res.setRepCode(biz.getRealCode());
		res.setRepMsg(biz.getMessage());
		return res;
	}

	/**
	 * 防欺诈接口调用
	 * @param httpParamMap
	 * @param res
	 * @author lix YM10160
	 * @date 2017年7月10日下午5:54:02
	 */
	public boolean lufaxGateAntiFraud(Map<String, Object> httpParamMap, Response<ResLoanContractSignVo> res) {
		Map<String, Object> params = (Map<String, Object>) httpParamMap.get("params");
		try{
			String dataTime =DateUtil.defaultFormatMss(new Date());
			httpParamMap.put("key", dataTime);
			params.put("requestId", "aps"+dataTime);
			String paramsString= JsonUtils.encode(params);
			String encryptionString="lufax100008"+paramsString+dataTime+sxKey;
			logger.info("【陆金所 反欺诈】生成验签的字符串:{}，生成的签名{}",encryptionString,MD5UtilLujs.md5Hex(encryptionString));
			httpParamMap.put("sign", MD5UtilLujs.md5Hex(encryptionString));

			HttpResponse httpResponse = luJSHttpService.lufaxGate(httpParamMap);

			if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
				String content = httpResponse.getContent();
				Map contentMap = JsonUtils.decode(content, Map.class);
				if(contentMap != null && EnumConstants.RES_CODE_SUCCESS_SHORT.equals(contentMap.get("resCode"))){
					Map resInfo = (Map) contentMap.get("infos");
					if (resInfo != null && EnumConstants.RES_CODE_SUCCESS_SHORT.equals(resInfo.get("ret_code")) && resInfo.get("info_content") != null) {
						Map resInfoCont = (Map) resInfo.get("info_content");
						if(resInfoCont !=null && resInfoCont.get("receiveSuccess") != null){
							if("0".equals(resInfoCont.get("receiveSuccess"))){
								httpParamMap.put("lockTarget", "Y");
								return true;
							}else {
								setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,resInfoCont.get("message").toString()), res);
								return false;
							}
						}else if(resInfoCont != null){
							setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,resInfoCont.get("message").toString()), res);
							return false;
						} else {
							setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"返回参数错误resInfoCont"), res);
							return false;
						}
					} else if(resInfo != null){
						setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,resInfo.get("ret_msg").toString()), res);
						return false;
					} else{
						setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"返回参数错误resInfo"), res);
						return false;
					}

				} else if(contentMap != null){
					setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,contentMap.get("respDesc").toString()), res);
					return false;
				} else {
					setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"返回参数错误contentMap"), res);
					return false;
				}

			} else{
				setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"接口响应失败"), res);
				return false;
			}

		}catch(Exception e){
			setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"获取反欺诈接口入参错误"+e), res);
			return false;
		}
	}

	public boolean lufaxGateIdCheck(Map<String, Object> httpParamMap, Response<ResLoanContractSignVo> res) {
		Map<String, Object> params = (Map<String, Object>) httpParamMap.get("params");
		try{
			String dataTime =DateUtil.defaultFormatMss(new Date());
			httpParamMap.put("key", dataTime);
			params.put("requestId", "aps"+dataTime);
			String paramsString= JsonUtils.encode(params);
			String encryptionString="lufax100004"+paramsString+dataTime+sxKey;
			logger.info("【陆金所 id校验】生成验签的字符串:{}，生成的签名{}",encryptionString,MD5UtilLujs.md5Hex(encryptionString));
			httpParamMap.put("sign", MD5UtilLujs.md5Hex(encryptionString));

			HttpResponse httpResponse = luJSHttpService.lufaxGate(httpParamMap);

			if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
				String content = httpResponse.getContent();
				Map contentMap = JsonUtils.decode(content, Map.class);
				if(contentMap != null && EnumConstants.RES_CODE_SUCCESS_SHORT.equals(contentMap.get("resCode"))){
					Map resInfo = (Map) contentMap.get("infos");
					if (resInfo != null && EnumConstants.RES_CODE_SUCCESS_SHORT.equals(resInfo.get("ret_code"))) {
						Map resInfoCont = (Map) resInfo.get("info_content");
						if(resInfoCont !=null && resInfoCont.get("lufax_loan_req_id") != null){
							httpParamMap.put("lufax_loan_req_id", resInfoCont.get("lufax_loan_req_id").toString());
							httpParamMap.put("lockTarget", "Y");
							return true;
						}else if(resInfoCont != null){
							setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,resInfoCont.get("message").toString()), res);
							return false;
						} else {
							setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"返回参数错误resInfoCont"), res);
							return false;
						}
					} else if(resInfo != null){
						setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,resInfo.get("ret_msg").toString()), res);
						return false;
					} else{
						setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"返回参数错误resInfo"), res);
						return false;
					}

				} else if(contentMap != null){
					setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,contentMap.get("respDesc").toString()), res);
					return false;
				} else {
					setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"返回参数错误contentMap"), res);
					return false;
				}
			} else{
				setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"接口响应失败"), res);
				return false;
			}

		}catch(Exception e){
			setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"获取反欺诈接口入参错误"+e), res);
			return false;
		}
	}
	
	//调用核心合同确认接口
	/**
	 * @Description:陆金所合同确认接口</p>
	 * @uthor YM10139
	 * @date 2017年7月10日 下午2:46:54
	 */
	public boolean lujsConfirmContract(Map paramMap, Response<ResLoanVo> res){
		boolean confirmFlag =true;
		//调用核心合同确认接口
		HttpResponse httpResponse = coreHttpService.lujsContractConfirm(paramMap);
		if(httpResponse.getCode() !=200){
			confirmFlag=false;
			setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "调用核心合同确认接口异常"),res);
		}
		Map retMap =JsonUtils.decode(httpResponse.getContent(),Map.class);
		
		if(!"000000".equals(retMap.get("resCode"))){
			confirmFlag=false;
			BmsLogger.info( "调用陆金所通知接口失败");
			setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "确认上架推送失败"),res);
		}
		return confirmFlag;
	}
	

	/**
	 * @Description:通知陆金所接口；外部机构通知进件接口</p>
	 * @uthor YM10139
	 * @date 2017年7月10日 下午2:46:54
	 */
	public boolean  requestNotifyLujs(Map paramMap,Response res){
		boolean	requestNotifyFlag=true;
		String loanNo=String.valueOf(paramMap.get("loanNo"));
		String dataTime =DateUtil.defaultFormatMss(new Date());
		Map retMap =new HashMap();
			JSONObject json=new JSONObject();
			JSONObject requestMap=new JSONObject();
			JSONObject params=new JSONObject();	
			json.put("funcId", "lufax100005");
			json.put("key", dataTime);
			params.put("requestId", "aps"+dataTime);
			params.put("projectNo", "aps");
			requestMap.put("aps_apply_no", paramMap.get("aps_apply_no"));		//进件流水号
			requestMap.put("apply_no",paramMap.get("apply_no"));  			//申请单号
			requestMap.put("notify_code", 2);		//通知状态1：订单成功；2：订单终止
			params.put("reqParam", requestMap);
			json.put("params", params);
			String encryptionString="lufax100005"+params.toString()+dataTime+sxKey;
			BmsLogger.info("生成验签的字符串:{}，生成的签名{}");
			String md5Str="";
			try {
				md5Str = MD5Util.md5Hex(encryptionString);
			} catch (Exception e) {
				setError(new BizException(BizErrorCode.EOERR, "MD5加密异常"),res);
			}
			json.put("sign", md5Str);
			HttpResponse httpResponse = luJSHttpService.lufaxGate(json);
			if(httpResponse==null||httpResponse.getCode() !=200){
				requestNotifyFlag=false;
				BmsLogger.info( "调用通知陆金所接口失败");
				setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "调用通知陆金所接口失败"),res);
				return requestNotifyFlag;
			}
			retMap = JsonUtils.decode(httpResponse.getContent(), Map.class);
			if(retMap !=null){
				retMap=(Map) retMap.get("infos");
			}
			if(retMap !=null){
				retMap=(Map) retMap.get("info_content");
			}
			if(retMap==null ||!"0000".equals(retMap.get("ret_code"))){
				requestNotifyFlag=false;
				BmsLogger.info("调用陆金所通知返回失败消息|"+httpResponse.getContent());
				setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "调用陆金所通知返回失败消息"),res);
				return requestNotifyFlag;
			}
		return requestNotifyFlag;
	}
	
	
	/**
	 * @Description: 陆金所进件通知外部接口</p>
	 * @uthor YM10159
	 * @date 2017年7月15日 下午1:44:03
	 */
	public Map<String,Object> inNoticeExternal(ReqLufax800001Vo reqVo){
		Map<String,Object> returnMap = new HashMap<>();
		
		String notifyCode = ObjectUtils.toString(reqVo.getNotify_code());
		String notifyDesc = ObjectUtils.toString(reqVo.getNotify_desc());
		if("00301".equals(notifyCode)){ //反欺诈
			if(StringUtils.isBlank(notifyDesc)){
				returnMap.put("return_code", "9999");
				returnMap.put("return_msg", "未提供反欺诈拒绝原因");
				returnMap.put("return_type", reqVo.getNotify_type());
			} else {
				returnMap.put("return_code", notifyCode);
				returnMap.put("return_msg", notifyDesc);
				returnMap.put("return_type", reqVo.getNotify_type());
			}
		}else{
			returnMap.put("return_code", notifyCode);
			returnMap.put("return_msg", notifyDesc);
			returnMap.put("return_type", reqVo.getNotify_type());
		}
		
		//保存结果
		lujsDao.saveLufaxNoticeExternal(reqVo);
		
		return returnMap;
	} 
	
	/**
	 * @Description:陆金所人工审核结果</p>
	 * @uthor YM10159
	 * @date 2017年7月20日 下午7:38:43
	 * @param lujsInformVo
	 */
	public Response<ResLujsInformVo> getAuditReturnResult(ReqLujsInformVo lujsInformVo){
		Response<ResLujsInformVo> response = new Response<ResLujsInformVo>();
		ResLujsInformVo resLujsInformVo = new ResLujsInformVo();
		
		Map<String,Object> map = lujsDao.getAuditReturnResult(lujsInformVo.getLoanNo());
		
		if(null == map || map.size() == 0){
			resLujsInformVo.setInformType("001");
			resLujsInformVo.setInformResult("渠道处理中，请等待...");
			
			response.setData(resLujsInformVo);
			return response;
		}
		
		String informDesc = ObjectUtils.toString(map.get("informDesc"));
		String infromResult = "";
		
		String currentTaskNode = lujsInformVo.getCurrentTaskNode();
		if(currentTaskNode.equals("LUJS_MANUAL_AUDIT")){
			//005-信审通过,00401-信审不通过,013-信审缺乏材料,00201-申请进件额度不够时的人工取消
			System.out.println(map.get("informResult"));
			infromResult = ObjectUtils.toString(map.get("informResult"));
			if("00401".equals(infromResult)){//审核未通过-点击“办理”进入第一个保存页面，系统提示“渠道审核未通过，请改签其他渠道
				resLujsInformVo.setInformType("00401");
				resLujsInformVo.setInformResult("渠道审核未通过，请改签其他渠道");
			}
			if("013".equals(infromResult)){//审核返回补充材料时-点击“办理”进入合同签订按钮亮起的页面
				resLujsInformVo.setInformType("013");
				resLujsInformVo.setInformResult(informDesc);
			}
			if("00201".equals(infromResult)){//返回申请进件额度不够时的人工取消时-点击“办理”进入第一个保存页面
				resLujsInformVo.setInformType("00201");
				resLujsInformVo.setInformResult("申请进件额度不够，陆金所人工取消，请改签其他渠道");
			}
			if("005".equals(infromResult)){//返回审核通过
				resLujsInformVo.setInformType("005");
				resLujsInformVo.setInformResult("审核通过");
			}
		}
		
		response.setData(resLujsInformVo);
		return response;
	}
	
	/**
	 * @Description:封装人工审核到合同确认流程数据</p>
	 * @uthor YM10159
	 * @date 2017年7月23日 上午10:43:33
	 */
	public ReqLoanContractSignVo packageContractConfirmInfo(String loanNo){
		ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo();
		//根据借款编号或去loanBaseId
		String loanBaseId = lujsDao.getLoanBaseIdByLoanNo(loanNo);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanBaseId", loanBaseId);
		BMSLoanBaseEntity loanBaseEntity = loanBaseDao.findByloanBaseId(paramMap);
		
		reqLoanContractSignVo.setId(Long.valueOf(loanBaseId));
		reqLoanContractSignVo.setLoanNo(loanNo);
		reqLoanContractSignVo.setServiceCode(loanBaseEntity.getSignCode());
		reqLoanContractSignVo.setServiceName(loanBaseEntity.getSignName());
		reqLoanContractSignVo.setChannelCd(EnumConstants.channelCode.LUJS.getValue());
		reqLoanContractSignVo.setIp("127.0.0.1");
		return reqLoanContractSignVo;
	}
	
	/**
	 * @Description:删除陆金所人工审核信息</p>
	 * @uthor YM10159
	 * @date 2017年7月23日 下午3:12:50
	 */
	public void delLujsManualAnditInfo(String loanNo){
		lujsDao.delLujsManualAnditInfo(loanNo);
	}
	
	/**
	 * 获取征信报告
	 * @param loanNo
	 * @return
	 * @throws ParseException 
	 */
	public String getReportId(String loanNo){
		Map<String,Object> applyInfoMap = lujsDao.getPersonInfo(loanNo);
		
		PersonHistoryLoanVO personHistoryLoanVO = new PersonHistoryLoanVO();
		personHistoryLoanVO.setSysCode("bms");
		personHistoryLoanVO.setIdNo(ObjectUtils.toString(applyInfoMap.get("idNo")));
		personHistoryLoanVO.setName(ObjectUtils.toString(applyInfoMap.get("name")));
		personHistoryLoanVO.setLoanNo(loanNo);
		
		Response<Object> response = applyEnterExecuter.fixedCreditReport(personHistoryLoanVO);
		if(null != response){
			return ObjectUtils.toString(response.getData());
		}
		return null;
	}
	/**
	 * 陆金所签约限制，央行报告限制
	 * @param paramMap
	 * @param res
	 * @return
	 * @author lix YM10160
	 * @date 2017年8月16日上午10:37:40
	 */
	public boolean lujsApplyCreditReportRule(Map<String, Object> paramMap, Response<ResLoanContractSignVo> res){
		//调用核心合同确认接口
		HttpResponse httpResponse = creditHttpService.applyCreditReportRule(paramMap);
		if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
			String content = httpResponse.getContent();
			Map<String, Object> contentMap = JsonUtils.decode(content, Map.class);
			if(contentMap != null && Boolean.parseBoolean(contentMap.get("result").toString())){
//				第二个保存页面，后台要保留通过结果
				BMSCreditRuleInformEntity entity = new BMSCreditRuleInformEntity();
				entity.setLoanBaseId(paramMap.get("loanBaseId")==null?0:Long.valueOf(paramMap.get("loanBaseId").toString()));
				entity.setLoanNo(paramMap.get("loanNo")==null?null:paramMap.get("loanNo").toString());
				entity.setChannelCode(paramMap.get("channelCode")==null?null:paramMap.get("channelCode").toString());
				entity.setResult(contentMap.get("result")==null?null:contentMap.get("result").toString());
				entity.setCheckRule(contentMap.get("checkRule")==null?null:contentMap.get("checkRule").toString());
				entity.setMessage(contentMap.get("message")==null?null:contentMap.get("message").toString());
				entity.setCreator(paramMap.get("serviceCode")==null?null:paramMap.get("serviceCode").toString());
				
				creditRuleInformDao.insert(entity);
				return true;
			} else if(contentMap != null){
				setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"该客户不符合陆金所准入标准，请选择其他签约渠道"), res);
//				setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,contentMap.get("message").toString()), res);
				//后台要保留拒绝结果和拒绝原因
				BMSCreditRuleInformEntity entity = new BMSCreditRuleInformEntity();
				entity.setLoanBaseId(paramMap.get("loanBaseId")==null?0:Long.valueOf(paramMap.get("loanBaseId").toString()));
				entity.setLoanNo(paramMap.get("loanNo")==null?null:paramMap.get("loanNo").toString());
				entity.setChannelCode(paramMap.get("channelCode")==null?null:paramMap.get("channelCode").toString());
				entity.setResult(contentMap.get("result")==null?null:contentMap.get("result").toString());
				entity.setCheckRule(contentMap.get("checkRule")==null?null:contentMap.get("checkRule").toString());
				entity.setMessage(contentMap.get("message")==null?null:contentMap.get("message").toString());
				entity.setCreator(paramMap.get("serviceCode")==null?null:paramMap.get("serviceCode").toString());
				
				creditRuleInformDao.insert(entity);
				
				return false;
			} else {
				setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"返回参数错误contentMap"), res);
				return false;
			}
		} else{
			setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"接口响应失败"), res);
			return false;
		}
	}
	
	/**
	 * 调用信审接口获取核实收入
	 * @param loanNo 借款编号
	 * @param serviceCode 操作人
	 * @return 返回核实收入
	 */
	public String getCheckMoney(String loanNo, String serviceCode){
		String checkIncome = "";
		logger.info("借款编号【"+loanNo+"】陆金所调用信审接口获取核实收入url:"+amsWebApiUrl);
        Map<String, String> strMap = new HashMap<>();
        strMap.put("loanNo", loanNo);
        strMap.put("rtfNodeState", "XSZS-PASS");
        strMap.put("sysCode", EnumConstants.BMS_SYSTEM_CODE);
        strMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        strMap.put("userCode",serviceCode);
        String urlParam = CommonUtil.map2UrlParams(strMap);
        
        logger.info("借款编号【"+loanNo+"】陆金所调用信审接口入参:"+urlParam);
        HttpResponse amsResponse = HttpUtil.post(amsWebApiUrl, urlParam, false);
        logger.info("借款编号【"+loanNo+"】陆金所调用信审接口返回:"+amsResponse);
        
        if(null == amsResponse){
        	logger.info("借款编号【"+loanNo+"】陆金所调用信审接口返回:null");
        }else{
        	if(amsResponse.getCode() == 200){
        		String content = ObjectUtils.toString(amsResponse.getContent());
        		if(StringUtils.isNotBlank(content)){
        			JSONObject obj = new JSONObject(content);
        			if("000000".equals(obj.getString("repCode"))){
        				String data = ObjectUtils.toString(obj.getString("data"));
        				if(StringUtils.isNotBlank(data)){
        					JSONObject dataObj = new JSONObject(data);
        					checkIncome = ObjectUtils.toString(dataObj.getString("approvalCheckIncome"));
        				}
        			}
        		}
        	}
        }
        logger.info("借款编号【"+loanNo+"】陆金所调用信审接口返回:checkIncome="+checkIncome);
        return checkIncome;
	}
	
	public static void main(String args[]){
		JSONObject ApplicationImageList = new JSONObject();
		ApplicationImageList.put("DOCUMENT_TYPE", "20601");//身份证
		ApplicationImageList.put("DOCUMENT_NO", 1);//文档序号
		
		ApplicationImageList.put("DOCUMENT_TYPE", "207");//身份证
		ApplicationImageList.put("DOCUMENT_NO", 1);//文档序号
		
	}
}
