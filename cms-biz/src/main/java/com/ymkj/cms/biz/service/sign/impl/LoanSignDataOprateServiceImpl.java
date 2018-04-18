package com.ymkj.cms.biz.service.sign.impl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.sign.aite.IAiTeLoanContractExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;
import com.ymkj.cms.biz.common.util.ClassesTypeUtils;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.MoneyUtil;
import com.ymkj.cms.biz.common.util.NumberUtils;
import com.ymkj.cms.biz.common.util.ProductUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.common.util.ValidationUtils;
import com.ymkj.cms.biz.dao.apply.APPCarInfoDao;
import com.ymkj.cms.biz.dao.apply.APPCardLoanInfoDao;
import com.ymkj.cms.biz.dao.apply.APPContactInfoDao;
import com.ymkj.cms.biz.dao.apply.APPEstateInfoDao;
import com.ymkj.cms.biz.dao.apply.APPMasterLoanInfoDao;
import com.ymkj.cms.biz.dao.apply.APPMerchantLoanInfoDao;
import com.ymkj.cms.biz.dao.apply.APPPersonInfoDao;
import com.ymkj.cms.biz.dao.apply.APPPolicyInfoDao;
import com.ymkj.cms.biz.dao.apply.APPProvidentInfoDao;
import com.ymkj.cms.biz.dao.apply.APPSalaryInfoDao;
import com.ymkj.cms.biz.dao.audit.BMSAPPHistoryDao;
import com.ymkj.cms.biz.dao.audit.ILoanAuditDao;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.apply.APPCarInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPCardLoanInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPContactInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPEstateInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPMasterLoanInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPMerchantLoanInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPPolicyInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPProvidentInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPSalaryLoanInfoEntity;
import com.ymkj.cms.biz.entity.audit.BMSAPPHistoryEntity;
import com.ymkj.cms.biz.entity.audit.LoanAuditEntity;
import com.ymkj.cms.biz.entity.finance.BMSLoanBank;
import com.ymkj.cms.biz.entity.master.BMSBank;
import com.ymkj.cms.biz.entity.master.BMSEnumCode;
import com.ymkj.cms.biz.entity.master.BMSTmAppContactInfo;
import com.ymkj.cms.biz.entity.sign.BMSContractLoan;
import com.ymkj.cms.biz.entity.sign.BMSLoanApp;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanContract;
import com.ymkj.cms.biz.entity.sign.BMSPushLoanData;
import com.ymkj.cms.biz.entity.sign.BMSRepaymentDetail;
import com.ymkj.cms.biz.entity.sign.HttpContractReturnEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.finance.ILoanBankService;
import com.ymkj.cms.biz.service.http.CreditHttpService;
import com.ymkj.cms.biz.service.master.IBMSBankService;
import com.ymkj.cms.biz.service.master.IBMSEnumCodeService;
import com.ymkj.cms.biz.service.master.IBMSTmAppContactInfoService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;

@Service
public class LoanSignDataOprateServiceImpl implements ILoanSignDataOprateService {
	private static final Logger logger = LoggerFactory.getLogger(LoanSignDataOprateServiceImpl.class);
	
	@Autowired
	private ILoanBaseEntityDao loanBaseEntityDao;
	@Autowired
	private APPPersonInfoDao appPersonInfoDao;
	@Autowired
	private IBMSBankService bankService;
	@Autowired
	private APPCardLoanInfoDao appCardLoanInfoDao;
	@Autowired
	private APPCarInfoDao appCarInfoDao;
	@Autowired
	private APPContactInfoDao appContactInfoDao;
	@Autowired
	private APPEstateInfoDao appEstateInfoDao;
	@Autowired
	private APPMasterLoanInfoDao appMasterLoanInfoDao;
	@Autowired
	private APPMerchantLoanInfoDao appMerchantLoanInfoDao;
	@Autowired
	private APPPolicyInfoDao appPolicyInfoDao;
	@Autowired
	private APPProvidentInfoDao appProvidentInfoDao;
	@Autowired
	private APPSalaryInfoDao appSalaryInfoDao;
	@Autowired
	private ILoanBankService loanBankService;
	@Autowired
	private IEmployeeExecuter iEmployeeExecuter;
	@Autowired
	private ILoanBaseEntityService loanBaseEntityService;
	@Autowired
	private IBMSTmAppContactInfoService appContactInfoService;
	@Autowired
	private CreditHttpService creditHttpService;
	@Autowired
	private ILoanAuditDao loanAuditDao;
	@Autowired
	private IBMSEnumCodeService enumCodeService;

	public Map<String, Object> setValueToContract(HttpContractReturnEntity contractReturnEntity)
			throws IllegalAccessException, InvocationTargetException, IntrospectionException {

		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(ProductUtils.bean2map(contractReturnEntity.getContract()));
		map.putAll(ProductUtils.bean2map(contractReturnEntity.getLoan()));
		// 银行账号
		Map paramMap = new HashMap();
		paramMap.put("loanNo", String.valueOf(map.get("loanNo")));
		BMSLoanBaseEntity loanBaseEntity = loanBaseEntityDao.getLoanInfoByLoanNo(paramMap);
		map.put("account", loanBaseEntity.getApplyBankCardNo());

		// 配偶姓名
		paramMap.clear();
		paramMap.put("loanNo", String.valueOf(map.get("loanNo")));
		paramMap.put("contactRelation", EnumConstants.Relationship._00013.getCode());
		BMSTmAppContactInfo appContactInfo = appContactInfoService.getBy(paramMap);
		if (appContactInfo != null) {
			map.put("peiOuName", appContactInfo.getContactName());
		}

		// 分割还款起止日期
		if (map.get("startRDate") != null) {
			// 拿到日期的格式如--2015-07-13 00:00:00.000
			String[] data = map.get("startRDate").toString().split("\\ ");
			String[] ye = data[0].split("\\-");
			map.put("year2", ye[0]);// 年
			map.put("month2", ye[1]);// 月
			map.put("day2", ye[2]);// 日
		}
		if (map.get("endRDate") != null) {
			// 拿到日期的格式如--2015-07-13 00:00:00.000
			String[] data = map.get("endRDate").toString().split("\\ ");
			String[] ye = data[0].split("\\-");
			map.put("year3", ye[0]);// 年
			map.put("month3", ye[1]);// 月
			map.put("day3", ye[2]);// 日
		}

		// 分割签约日期
		if (map.get("signDate") != null) {
			// 拿到签约日期的格式如--2015-07-13 00:00:00.000
			String[] data = map.get("signDate").toString().split("\\ ");
			String[] ye = data[0].split("\\-");
			map.put("year", ye[0]);// 年
			map.put("month", ye[1]);// 月
			map.put("day", ye[2]);// 日
		}
		// 分割签约地点
		if (map.get("signingSite") != null) {
			if (map.get("signingSite").toString().indexOf("市") > 0) {
				String[] addre = map.get("signingSite").toString().split("\\市");
				if (addre[1].toString().indexOf("区") > 0) {
					String[] ye = addre[1].split("\\区");
					map.put("zone", ye[0]);// 区
				} else {
					map.put("zone", "");// 区
				}
				map.put("city", addre[0]);// 市
			} else {
				map.put("city", map.get("signingSite"));// 市
			}
		}
		// 还款起止日期
		if (map.get("startRDate") != null && map.get("endRDate") != null) {
			String startRDate = map.get("startRDate").toString();
			String endRDate = map.get("endRDate").toString();
			map.put("startRDate", startRDate.split("\\ ")[0]);// 分割空格
			map.put("endRDate", endRDate.split("\\ ")[0]);
		}
		// 借款金额（大写）
		if (map.get("pactMoney") != null) {
			map.put("pactMoney1", MoneyUtil.NumberToChinese(Double.valueOf(map.get("pactMoney").toString())));
			// 借款金额--表格中金额（需要分隔借款金额）
			String pacyMoney = map.get("pactMoney").toString();
			MoneyUtil.setMoneyToMap(map, pacyMoney, 1);
		}
		// 需偿还的贷款本息总额
		if (map.get("repaymentTotal") != null) {
			map.put("repaymentTotal1", MoneyUtil.NumberToChinese(Double.valueOf(map.get("repaymentTotal").toString())));
			// 需偿还的贷款本息总额--表格中金额（需要分隔需偿还的贷款本息总额）
			String repaymentTotal = map.get("repaymentTotal").toString();
			MoneyUtil.setMoneyToMap(map, repaymentTotal, 2);
		}
		// 月偿还本金额（大写）
		if (map.get("monthlyRepayment") != null) {
			map.put("monthlyRepayment1",
					MoneyUtil.NumberToChinese(Double.valueOf(map.get("monthlyRepayment").toString())));
			// 月偿还本金额--表格中金额（需要分隔月偿还本金额）
			String monthlyRepayment = map.get("monthlyRepayment").toString();
			MoneyUtil.setMoneyToMap(map, monthlyRepayment, 3);
		}
		// 风险金（大写）
		if (map.get("risk") != null) {
			map.put("risk1", MoneyUtil.NumberToChinese(Double.valueOf(map.get("risk").toString())));
		}
		// 咨询费(大写)
		if (map.get("referRate") != null) {
			map.put("referRate1", MoneyUtil.NumberToChinese(Double.valueOf(map.get("referRate").toString())));
		}
		// 评估费(大写)
		if (map.get("evalRate") != null) {
			map.put("evalRate1", MoneyUtil.NumberToChinese(Double.valueOf(map.get("evalRate").toString())));
		}
		// 管理费(大写)
		if (map.get("manageRate") != null) {
			map.put("manageRate1", MoneyUtil.NumberToChinese(Double.valueOf(map.get("manageRate").toString())));
		}
		// 向丙方支付管理费（大写）
		if (map.get("managerRateForPartyC") != null) {
			map.put("managerRateForPartyC1",
					MoneyUtil.NumberToChinese(Double.valueOf(map.get("managerRateForPartyC").toString())));
		}
		// 融资服务费(大写)
		if (map.get("managerRateForPartyCFinance") != null) {
			map.put("managerRateForPartyCFinance1",
					MoneyUtil.NumberToChinese(Double.valueOf(map.get("managerRateForPartyCFinance").toString())));
		}
		// 技术服务费(大写)
		if (map.get("managerRateForPartyCTechnology") != null) {
			map.put("managerRateForPartyCTechnology1",
					MoneyUtil.NumberToChinese(Double.valueOf(map.get("managerRateForPartyCTechnology").toString())));
		}
		// 费用合计（大写）
		if (map.get("rateSum") != null) {
			map.put("rateSum1", MoneyUtil.NumberToChinese(Double.valueOf(map.get("rateSum").toString())));
		}
		// 换算系数/补偿利率去掉百分号乘以100
		if (map.get("accrualem") != null) {
			map.put("accrualem1", Double.valueOf(map.get("accrualem").toString()) * 100);
		}
		// 月还款利率去掉百分号乘以100
		if (map.get("rateEM") != null) {
			// map.put("rateEM1",
			// Double.valueOf(map.get("rateEM").toString())*100);
			map.put("rateEM1",
					(new BigDecimal(map.get("rateEM").toString()).multiply(new BigDecimal(100))).doubleValue());
		}
		return map;
	}

	/**
	 * 还款函中填充值
	 * 
	 * @param map
	 *            公用map
	 * @param responseObject
	 *            接口返回值
	 * @throws JSONException
	 */

	public void setRepayArrayToMap(Map<String, Object> map, List<BMSRepaymentDetail> repaylist) {
		List<String> reList = new ArrayList<String>();
		// 按当前期数进行排序
		Collections.sort(repaylist, new Comparator<BMSRepaymentDetail>() {
			@Override
			public int compare(BMSRepaymentDetail o1, BMSRepaymentDetail o2) {
				return o1.getCurrentTerm().compareTo(o2.getCurrentTerm());
			}
		});
		for (BMSRepaymentDetail bmsRepaymentDetail : repaylist) {
			// 把html标签拼接成字符串，循环赋值
			String repay = "<tr> <td style = \"border:solid black 0.5pt\">" + bmsRepaymentDetail.getCurrentTerm()
					+ "</td>" + "<td style = \" border:solid black 0.5pt\">" + bmsRepaymentDetail.getReturnSimpDate()
					+ "</td>" + "<td style = \" border:solid black 0.5pt\">" + bmsRepaymentDetail.getReturneterm()
					+ "</td>" + "<td style = \" border:solid black 0.5pt;text-align:center \">"
					+ bmsRepaymentDetail.getRepaymentAll() + "</td> </tr>";
			reList.add(repay);
			map.put("repayList", reList);
		}
	}

	/**
	 * 组装推送债权参数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String fetchAllApplyData(ReqLoanContractSignVo reqLoanContractSignVo) {
		// String org = OrganizationContextHolder.getCurrentOrg(); // 机构号
		Map paramMap = new HashMap();
		paramMap.put("loanBaseId", reqLoanContractSignVo.getId());
		BMSPushLoanData bmsPushLoanData = new BMSPushLoanData();
		// 基本信息
		BMSLoanApp tmAppMain = loanBaseEntityDao.findLoanEntity(paramMap);
		// 将银行ID转为CODE 未提交事务 需要手动赋值银行信息推送
		ReqBMSBankVO reqBank = new ReqBMSBankVO();
		reqBank.setId(Long.parseLong(String.valueOf(reqLoanContractSignVo.getApplyBankNameId())));
		BMSBank bank = bankService.findOne(reqBank);
		tmAppMain.setApplyBankName(bank.getCode());
		tmAppMain.setApplyBankBranch(reqLoanContractSignVo.getApplyBankBranch());
		tmAppMain.setApplyBankCardNo(reqLoanContractSignVo.getApplyBankCardNo());
		tmAppMain.setBankPhone(reqLoanContractSignVo.getBankPhone());
		// 核心推送期限取得是签约期限和额度 借款为contractLmt和contractTrem 接口文档中取得为accLmt 和accTerm
		tmAppMain.setAccLmt(String.valueOf(reqLoanContractSignVo.getContractLmt()));
		tmAppMain.setAccTerm(String.valueOf(reqLoanContractSignVo.getContractTrem()));
		// 陆金所信息
		tmAppMain.setLujsName(String.valueOf(reqLoanContractSignVo.getLujsName()));
		tmAppMain.setLujsLoanReqId(String.valueOf(reqLoanContractSignVo.getLujsLoanReqId()));
		//添加渠道业务流水号
		tmAppMain.setChannelPushNo(ValidationUtils.composeAitePushLoanNo(tmAppMain.getLoanNo(), tmAppMain.getChannelPushFrequency()));
		bmsPushLoanData.setTmAppMain(tmAppMain);
		// 申请人信息
		APPPersonInfoEntity tmAppPersonInfo = appPersonInfoDao.findPersonByLoanNo(paramMap);
		// 核心推送 借款的houseType为核心的House_ownrSShip住宅类型 核心HouseType未录入
		String houseType = tmAppPersonInfo.getHouseType();
		tmAppPersonInfo.setHouseOwnership(houseType);
		tmAppPersonInfo.setHouseType(null);
		bmsPushLoanData.setTmAppPersonInfo(tmAppPersonInfo);
		// 联系人信息
		List<APPContactInfoEntity> tmAppContactInfos = appContactInfoDao.listBy(paramMap);// 联系人信息
		bmsPushLoanData.setTmAppContactInfos(tmAppContactInfos);
		// 审批历史表
		/*
		 * List<BMSAPPHistoryEntity> tmAppHistories =
		 * appHistoryDao.listBy(paramMap);
		 * bmsPushLoanData.setTmAppHistories(tmAppHistories);
		 */
		// 房产信息表
		APPEstateInfoEntity tmAppEstateInfo = appEstateInfoDao.getBy(paramMap);// 房产信息表
		bmsPushLoanData.setTmAppEstateInfo(tmAppEstateInfo);
		// 车辆信息表
		APPCarInfoEntity tmAppCarInfo = appCarInfoDao.getBy(paramMap);// 车辆信息表
		bmsPushLoanData.setTmAppCarInfo(tmAppCarInfo);
		// 保单信息表
		APPPolicyInfoEntity tmAppPolicyInfo = appPolicyInfoDao.getBy(paramMap);// 保单信息表
		bmsPushLoanData.setTmAppPolicyInfo(tmAppPolicyInfo);
		// 公积金信息表
		APPProvidentInfoEntity tmAppProvidentInfo = appProvidentInfoDao.getBy(paramMap);// 公积金信息表
		bmsPushLoanData.setTmAppProvidentInfo(tmAppProvidentInfo);
		// 卡友贷信息
		APPCardLoanInfoEntity tmAppCardLoanInfo = appCardLoanInfoDao.getBy(paramMap);// 卡友贷信息
		bmsPushLoanData.setTmAppCardLoanInfo(tmAppCardLoanInfo);
		// 随薪贷信息表
		APPSalaryLoanInfoEntity tmAppSalaryLoanInfo = appSalaryInfoDao.getBy(paramMap);// 随薪贷信息表
		bmsPushLoanData.setTmAppSalaryLoanInfo(tmAppSalaryLoanInfo);
		// 淘宝商户贷信息表
		APPMerchantLoanInfoEntity tmAppMerchantLoanInfo = appMerchantLoanInfoDao.getBy(paramMap);// 淘宝商户贷信息表
		bmsPushLoanData.setTmAppMerchantLoanInfo(tmAppMerchantLoanInfo);
		// 淘宝达人贷信息表
		APPMasterLoanInfoEntity tmAppMasterLoanInfo = appMasterLoanInfoDao.getBy(paramMap);// 淘宝达人贷信息表
		bmsPushLoanData.setTmAppMasterLoanInfo(tmAppMasterLoanInfo);
		String jsonStr = JsonUtils.encode2DayFormat(bmsPushLoanData);
		return jsonStr;
	}

	@Override
	public Map<String, String> convertToRequestMap(BMSLoanBaseEntity loanInfo, BMSContractLoan contractLoan,
			BMSLoanContract loanContract, APPPersonInfoEntity tmAppPersonInfo, APPPersonInfoEntity tmAppPersonInfo2,
			APPCarInfoEntity tmAppCarInfo, APPEstateInfoEntity tmAppEstateInfo,LoanAuditEntity loanAuditEntity) {
		logger.info("数据封装  开始");
		Map<String, String> requestMap = new HashMap<String, String>();

		requestMap.put("borrowNo", loanInfo.getLoanNo());// 借款编号
		requestMap.put("borrowAmount", NumberUtils.isNull(contractLoan.getPactMoney(), 2).toString());// 借款／合同金额
		requestMap.put("borrowTerm", NumberUtils.isNull(loanInfo.getContractTrem(), 0).toString());// 借款期限

		requestMap.put("remitMoney", NumberUtils.isNull(contractLoan.getGrantMoney(), 2).toString());// 划拨金额
		requestMap.put("yearRate", NumberUtils.isNull(contractLoan.getRateey(), 2).toString());// 年化利率

		BigDecimal serviceFee = filterServiceFee(loanContract);

		requestMap.put("serviceFee", serviceFee.toString());// 服务费咨询费
		requestMap.put("riskFund", NumberUtils.isNull(loanContract.getRisk(), 2).toString());// 风险金
		requestMap.put("consultFee", NumberUtils.isNull(loanContract.getReferRate(), 2).toString());// 咨询费
		requestMap.put("auditFee", NumberUtils.isNull(loanContract.getEvalRate(), 2).toString());// 审核费
		requestMap.put("managementFee", NumberUtils.isNull(loanContract.getManageRate(), 2).toString());// 管理费1
		requestMap.put("otherManagementFee", NumberUtils.isNull(loanContract.getManagerRateForPartyC(), 2).toString());// 管理费2
		requestMap.put("borrowRate", NumberUtils.isNull(contractLoan.getRate(), 2).toString());// 借款费率
		requestMap.put("repaymentType", "等额本息");// 还款方式
		requestMap.put("borrowPurpose", StringUtils.isEmpty(loanContract.getPurpose()) ? "2" : loanContract.getPurpose());// 借款用途
		requestMap.put("productName", loanInfo.getProductName());// 借款产品名称
		requestMap.put("borrowerName", loanInfo.getName());// 借款人姓名
//		requestMap.put("borrowerPhone", loanInfo.getBankPhone());// 借款人手机号码
		requestMap.put("borrowerPhone", tmAppPersonInfo.getCellphone());// 借款人手机号码，使用申请录入时的常用手机号
		requestMap.put("idType", "1");// 借款人证件类型
		requestMap.put("idNo", loanInfo.getIdNo());// 借款人证件号码

		requestMap.put("sex", ("F".equals(tmAppPersonInfo.getGender()) ? "1" : "2"));// 借款人性别
		requestMap.put("maritalStatus", ClassesTypeUtils.translateMaritalStatus(tmAppPersonInfo.getMaritalStatus()));// 借款人婚姻状况
		if (loanInfo.getIdNo() != null && loanInfo.getIdNo().length() == 18) {// 二代身份证id无效
			requestMap.put("birth", loanInfo.getIdNo().substring(6, 10) + '-' + loanInfo.getIdNo().substring(10, 12)
					+ "-" + loanInfo.getIdNo().substring(12, 14));// 借款人出生年份
		} else {
			requestMap.put("birth", "");// 借款人出生年份
		}
		requestMap.put("city", tmAppPersonInfo.getHomeCity() == null ? "-1" : tmAppPersonInfo.getHomeCity());// 借款人所在城市
		requestMap.put("educationLevel",
				ClassesTypeUtils.translateQualification(tmAppPersonInfo.getQualification()).toString());// 借款人教育程度

		if (null == tmAppCarInfo) {
			requestMap.put("hasCar", "0");// 借款人是否有车
			requestMap.put("hasCarLoan", "0");// 借款人是否有车贷
		} else {
			requestMap.put("hasCar", "1");// 借款人是否有车
			requestMap.put("hasCarLoan", ClassesTypeUtils.translateCarLoan(tmAppCarInfo.getCarLoan()));// 借款人是否有车贷
		}

		if (null == tmAppEstateInfo) {
			requestMap.put("hasHourse", "0");// 借款人是否有房
			requestMap.put("hasHourseLoan", "0");// 借款人是否有房贷
		} else {
			requestMap.put("hasHourse", "1");// 借款人是否有房
			requestMap.put("hasHourseLoan", ClassesTypeUtils.translateEstateLoan(tmAppEstateInfo.getEstateLoan()));// 借款人是否有车贷
		}
		requestMap.put("trade", ClassesTypeUtils.translateCorpType(
				tmAppPersonInfo.getCorpType() == null ? "" : tmAppPersonInfo.getCorpType().toString()));// 借款人行业信息
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String post=tmAppPersonInfo.getCorpPost();
		paramMap.put("code", post);

		if(null != post &&!"".equals(post) && post.length()>1)
		{
			paramMap.put("codeType", "noGovInstitution");
			BMSEnumCode enumPost = enumCodeService.getBy(paramMap);
			if(null != enumPost)
			{
				requestMap.put("post", enumPost.getNameCN());	// 借款人岗位信息
			}else{
				requestMap.put("post", "职员");// 借款人岗位信息
			}	
		}else if(null != post &&!"".equals(post) && post.length()==1){
			paramMap.put("codeType", "EmpPositionAttrType");

			BMSEnumCode enumPost = enumCodeService.getBy(paramMap);
			if(null != enumPost)
			{
				requestMap.put("post", enumPost.getNameCN());	// 借款人岗位信息
			}else{
				requestMap.put("post", "职员");// 借款人岗位信息
			}
		} else {
			requestMap.put("post", "职员");// 借款人岗位信息
		}
		
//		requestMap.put("post", tmAppPersonInfo.getCorpPost());// 借款人岗位信息

		requestMap.put("companyNature", ClassesTypeUtils.translateCorpStructure(
				tmAppPersonInfo.getCorpStructure() == null ? "" : tmAppPersonInfo.getCorpStructure().toString()));// 借款人单位性质

//		if (null != tmAppPersonInfo.getTotalMonthSalary()) {
//			requestMap.put("monthlyIncome", NumberUtils.isNull(tmAppPersonInfo.getTotalMonthSalary(), 2).toString());// 借款人月收入信息
//		}
		
		requestMap.put("monthlyIncome", NumberUtils.format(Double.valueOf(loanAuditEntity.getSys_check_lmt()), 0));// 终审核实收入信息
		
		//获取征信报告信息
		Map<String,Object> appDataMap = new  HashMap<String,Object>();
		appDataMap.put("customerIdCard", tmAppPersonInfo.getIdNo());
		appDataMap.put("customerName", tmAppPersonInfo.getName());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		String queryDate=sdf.format(tmAppPersonInfo.getAuditEndTime());  
		
		appDataMap.put("queryDate", queryDate);  //首次录入符合时间yyyy-mm-dd
		appDataMap.put("appNo", tmAppPersonInfo.getLoanNo());
		JSONObject resJsonByCreditzx = creditHttpService.queryBMSReport(appDataMap);
		
		logger.info("获取征信报告返回：[{}]"+resJsonByCreditzx.toString());
		if(resJsonByCreditzx != null && "000000".equals(resJsonByCreditzx.get("code"))){
			Object cardTotal = resJsonByCreditzx.get("cardTotal");
			Object loanTotal = resJsonByCreditzx.get("loanTotal");
			if(cardTotal != null){
				requestMap.put("creditNums", cardTotal.toString());//借款人信用卡数
			} else {
				requestMap.put("creditNums", "");//借款人信用卡数
			}
			if(loanTotal != null){
				requestMap.put("loanNums", loanTotal.toString());//借款人贷款笔数
			} else {
				requestMap.put("loanNums", "");//借款人信用卡数
			}
		}else{
			requestMap.put("creditNums", "");//借款人信用卡数
			requestMap.put("loanNums", "");//借款人贷款笔数
		}
		
		requestMap.put("attachmentUrl", "");// 附件下载地址
		logger.info("数据封装 结束");
		return requestMap;
	}

	@Override
	public BigDecimal filterServiceFee(BMSLoanContract loanContract) {
		BigDecimal allFree = new BigDecimal("0.00");
		if (loanContract.getEvalRate() != null)// 评估费
		{
			allFree=allFree.add(loanContract.getEvalRate());
		}
		if (loanContract.getManageRate() != null)// 管理费
		{
			allFree=allFree.add(loanContract.getManageRate());
		}
		if (loanContract.getRisk() != null)// 风险金
		{
			allFree=allFree.add(loanContract.getRisk());
		}
		if (loanContract.getManagerRateForPartyC() != null)// 丙方管理费
		{
			allFree=allFree.add(loanContract.getManagerRateForPartyC());
		}
		if (loanContract.getReferRate() != null)// 咨询费
		{
			allFree=allFree.add(loanContract.getReferRate());
		}
		return allFree;
	}

	@Override
	public BMSLoanBaseEntity aiTeConventionCheck(RequestVo requestVo) {
		// 空验证
		if (requestVo.getBorrowNo() == null || "".equals(requestVo.getBorrowNo())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "borrowNo" });
		}
		// 存在验证
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", requestVo.getBorrowNo());
		BMSLoanBaseEntity loanBaseEntity = loanBaseEntityDao.getBy(paramMap);
		if (loanBaseEntity == null) {
			throw new BizException(CoreErrorCode.DB_RESULT_ISNULL, new Object[] { "borrowNo" });
		}
		// 非法请求,传入的合同编号不属于捞财宝合同来源
		if (!EnumConstants.channelCode.AITE.getValue().equals(loanBaseEntity.getChannelCd())) {
			throw new BizException(CoreErrorCode.NO_RULE_EXECUTE_ERROR, "非法请求,传入的合同编号不属于捞财宝合同来源");
		}
		String taskDefKey = loanBaseEntity.getRtfNodeState();
		// 财务已放款，不能执行此操作
		if (null == taskDefKey || "".equals(taskDefKey)) {
			throw new BizException(CoreErrorCode.NO_RULE_EXECUTE_ERROR, "财务已放款，不能执行此操作");
		}

		return loanBaseEntity;
	}

	/**
	 * 构建借款人的银行信息
	 * 
	 * @param loanBase
	 * @param sourceMap
	 */
	public BMSLoanBank handleBanks(ReqLoanContractSignVo reqLoanContractSignVo) {
		BMSLoanBank loanBank = null;
		Map pramMap = new HashMap();
		pramMap.put("loanBaseId", reqLoanContractSignVo.getId());
		List<BMSLoanBank> loanBankList = loanBankService.listBy(pramMap);
		if (loanBankList != null && !loanBankList.isEmpty()) {
			// 银行信息已存在，查询获取该银行信息
			loanBank = loanBankList.get(0);
		} else {
			// 银行信息不存，创建银行信息
			loanBank = new BMSLoanBank();
		}
		if ("00005".equals(reqLoanContractSignVo.getChannelCd())) {
			/*
			 * bank = null; bank = loanBankDao.findByAccount(account); if(bank
			 * == null) { throw new Exception("没有查到绑卡信息，请确认是否已绑卡？"); }
			 * bank.setBankCode(bankCode);
			 * bank.setBranchBankCode(branchBankCode);//存的ID
			 * bank.setAccount(account);
			 * bank.setBankName(offerYongyoubankHeadDao.findByCode(bankCode).
			 * getName());
			 * bank.setFullName(offerYongyoubankBranchDao.findByBankId(
			 * branchBankCode).getBankName()); bank.setBankDicType("dic2");
			 */
		} else {
			ReqBMSBankVO reqBank = new ReqBMSBankVO();
			reqBank.setId(Long.parseLong(String.valueOf(reqLoanContractSignVo.getApplyBankNameId())));
			BMSBank bmsbank = bankService.findOne(reqBank);
			loanBank.setBranchBankCode(null);// 为空
			loanBank.setAccount(reqLoanContractSignVo.getApplyBankCardNo());
			loanBank.setBankName(reqLoanContractSignVo.getApplyBankName());
			loanBank.setBankCode(bmsbank.getCode());
			loanBank.setBankDicType("dic1");
		}
		return loanBank;
	}

	/**
	 * 得到经理副理
	 * 
	 * @param serviceCode
	 * @param managerType
	 * @return
	 */
	public ResEmployeeVO getSignManager(String serviceCode, String managerType) {
		ResEmployeeVO branchAssManager = null;
		ReqEmployeeVO reqEmployeeVO = new ReqEmployeeVO();
		reqEmployeeVO.setSysCode(EnumConstants.BMS_SYSCODE);
		reqEmployeeVO.setUsercode(serviceCode);
		// 根据客服code 查询营业部
		Response<List<ResOrganizationVO>> res = iEmployeeExecuter.findDeptsByAccount(reqEmployeeVO);
		List<ResOrganizationVO> resOrganizationVOs = res.getData();
		if (resOrganizationVOs == null) {
			throw new BizException(BizErrorCode.ORG_NOTFOUND, "未找到该客服所在营业部");
		}
		ResOrganizationVO org = resOrganizationVOs.get(0);
		Long orgId = org.getId();
		ReqParamVO reqParamVO2 = new ReqParamVO();
		reqParamVO2.setSysCode(EnumConstants.BMS_SYSCODE);
		reqParamVO2.setOrgId(orgId);
		reqParamVO2.setStatus(0);// 可用
		reqParamVO2.setInActive("t");
		List<String> roleCodes = new ArrayList<String>();
		roleCodes.add(managerType);
		reqParamVO2.setRoleCodes(roleCodes);
		// 根据营业部查询指定角色默认在职
		Response<List<ResEmployeeVO>> res2 = iEmployeeExecuter.findByDeptAndRole(reqParamVO2);
		List<ResEmployeeVO> list = res2.getData();
		if (managerType.equals(EnumConstants.BRANCH_MANAGER)) {
			if (list != null && list.size() > 0) {
				branchAssManager = list.get(0);
			}
			return branchAssManager;

		} else if (managerType.equals(EnumConstants.BRANCH_ASSISTANT_MANAGER)) {
			// 根据营业部查询已分配的客服经理 从小至大顺序排列
			List<Map<String, Object>> confirmMapList = loanBaseEntityService
					.getManagerCodeListByOrgId(String.valueOf(orgId));
			// 如果有未分配的客服经理分配给其办理
			if (list != null && list.size() > 0) {
				for (ResEmployeeVO resEmployeeVO : list) {
					boolean existsFlag = false;// 是否已分配单子
					if (confirmMapList != null && confirmMapList.size() > 0) {
						for (Map<String, Object> map : confirmMapList) {
							if (resEmployeeVO.getUsercode().equals(map.get("managerCode"))) {
								existsFlag = true;
								break;
							}
						}
					}
					if (!existsFlag) {// 不存在则是没有被分配，则分配单子
						return resEmployeeVO;
					}
				}
			}
			boolean isOrgFlag = false;// 是否还是此机构下的员工
			// 遍历取当前单子最小且在当前营业部下的副理
			if (confirmMapList != null && confirmMapList.size() > 0) {
				for (Map<String, Object> map : confirmMapList) {
					if (list != null && list.size() > 0) {
						for (ResEmployeeVO resEmployeeVO : list) {
							if (resEmployeeVO.getUsercode().equals(map.get("managerCode"))) {
								isOrgFlag = true;
							}
							if (isOrgFlag) {// 遍历最少存在于当前营业部，则分配单子
								return resEmployeeVO;
							}
						}
					}
				}
			}
		}
		return branchAssManager;
	}

	@Override
	public Map<String, Object> getPreliminaryAuditData(String loanNo) {
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 接收调用接口返回值map
		Map<String, Object> paramMap = new HashMap<String, Object>(); // 查询参数
		paramMap.put("loanNo", loanNo);
		// 基本信息
		BMSLoanApp appMain = loanBaseEntityDao.findLoanEntity(paramMap);
		// 申请人信息
		APPPersonInfoEntity appPersonInfo = appPersonInfoDao.findPersonByLoanNo(paramMap);
		// 车辆信息表
		APPCarInfoEntity appCarInfo = appCarInfoDao.getBy(paramMap);// 车辆信息表
		// 房产信息表
		APPEstateInfoEntity appEstateInfo = appEstateInfoDao.getBy(paramMap);// 房产信息表
		// 联系人信息，00013配偶  
		paramMap.put("contactRelation", "00013");
		APPContactInfoEntity tmAppContactInfo = appContactInfoDao.getBy(paramMap);// 联系人信息  配偶  
		// 审核基本信息
		LoanAuditEntity loanAuditEntity = loanAuditDao.getBy(paramMap);

		Map<String, Object> json = new HashMap<String, Object>(); // 请求接口输入的参数json
		Map<String, Object> requestMap = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		json.put("funcId", "wm3_2004");
		params.put("projectNo", "aps");
		requestMap.put("sysSourceWm3", "005");
		requestMap.put("projNoWm3", "30213");
		requestMap.put("name", appPersonInfo.getName());
		requestMap.put("brNo", "005");
		requestMap.put("prePactNo", loanNo);
		requestMap.put("custName", appPersonInfo.getName());
		requestMap.put("idType", "0");
		requestMap.put("idNo", appPersonInfo.getIdNo());
		String clientType = "99";
		try {
			if (appMain.getClientType() != null) {
				if (appMain.getClientType().equals("A00") || appMain.getClientType().equals("A01")
						|| appMain.getClientType().equals("B05") || appMain.getClientType().equals("B06")
						|| appMain.getClientType().equals("B09") || appMain.getClientType().equals("B10")
						|| appMain.getClientType().equals("B11") || appMain.getClientType().equals("C01")
						|| appMain.getClientType().equals("C04")) {
					clientType = "02";
				} else if (appMain.getClientType().equals("B08") || appMain.getClientType().equals("B12")
						|| appMain.getClientType().equals("B13") || appMain.getClientType().equals("C02")
						|| appMain.getClientType().equals("E01") || appMain.getClientType().equals("E02")
						|| appMain.getClientType().equals("C10") || appMain.getClientType().equals("C08")) {
					clientType = "03";
				} else {
					clientType = "99";
				}
			} else {
				clientType = "99";
			}
		} catch (Exception ex) {
			clientType = "99";
		}
		requestMap.put("custType", clientType); // 客户类型
		requestMap.put("sex", "M".equals(appPersonInfo.getGender()) ? "1" : "2");
		requestMap.put("birth", (appPersonInfo.getIdNo() == null ? "" : appPersonInfo.getIdNo().substring(6, 14))); // 出生日期
		String marriageStatus = "90";
		String childrenNum = "2";
		if ("00001".equals(appPersonInfo.getMaritalStatus())) {
			marriageStatus = "10";
		} else if ("00002".equals(appPersonInfo.getMaritalStatus())) {
			marriageStatus = "20";
		} else if ("00003".equals(appPersonInfo.getMaritalStatus())) {
			marriageStatus = "40";
		} else if ("00004".equals(appPersonInfo.getMaritalStatus())) {
			marriageStatus = "90";
		} else {
			marriageStatus = "90";
		}
		requestMap.put("marriage", marriageStatus);
		try {
			if (0 < appPersonInfo.getChildrenNum()) {
				childrenNum = "1";
			} else if (appPersonInfo.getChildrenNum() == 0) {
				childrenNum = "0";
			} else {
				childrenNum = "2";
			}
		} catch (Exception ee) {
			childrenNum = "2";
		}
		requestMap.put("children", childrenNum);
		String edu = "99";
		if ("00001".equals(appPersonInfo.getQualification())) {
			edu = "10";
		} else if ("00002".equals(appPersonInfo.getQualification())) {
			edu = "20";
		} else if ("00003".equals(appPersonInfo.getQualification())) {
			edu = "30";
		} else if ("00004".equals(appPersonInfo.getQualification())) {
			edu = "40";
		} else if ("00005".equals(appPersonInfo.getQualification())) {
			edu = "60";
		} else if ("00006".equals(appPersonInfo.getQualification())) {
			edu = "70";
		} else {
			edu = "99";
		}
		requestMap.put("edu", edu); // 最高学历
		requestMap.put("degree", "9"); // 最高学位
		String telNo = "";
		String phoneNo = "000-00000000";
		if ("".equals(appPersonInfo.getHomePhone1()) || appPersonInfo.getHomePhone1() == null) {
			if ("".equals(appPersonInfo.getHomePhone2()) || appPersonInfo.getHomePhone2() == null) {
				if ("".equals(appPersonInfo.getCorpPhone()) || appPersonInfo.getCorpPhone() == null) {
					phoneNo = "000-00000000";
				} else {
					phoneNo = appPersonInfo.getCorpPhone();
				}
			} else {
				phoneNo = appPersonInfo.getHomePhone2();
			}
		} else {
			phoneNo = appPersonInfo.getHomePhone1();
		}
		if ("".equals(appPersonInfo.getCellphone()) || appPersonInfo.getCellphone() == null) {
			if ("".equals(appPersonInfo.getCellphoneSec()) || appPersonInfo.getCellphoneSec() == null) {
				returnMap.put("resCode", "-1");
				returnMap.put("resMsg", "手机号码不能为空。");
				return returnMap;
			} else {
				telNo = appPersonInfo.getCellphoneSec();
			}
		} else {
			telNo = appPersonInfo.getCellphone();
		}
		requestMap.put("telNo", phoneNo); // 联系电话
		requestMap.put("phoneNo", telNo); // 电话号码
		
//		requestMap.put("postCode", appPersonInfo.getHomePostcode()==null?"":appPersonInfo.getHomePostcode()); // 通讯邮编
//		requestMap.put("postAddr", appPersonInfo.getHomeAddress()==null?"":appPersonInfo.getHomeAddress()); // 通讯地址
		if(StringUtils.isEmpty(appPersonInfo.getIssuerPostcode())){
			requestMap.put("homeArea","000000");// 户籍归属地
		} else {
			requestMap.put("homeArea",appPersonInfo.getIssuerPostcode());// 户籍归属地
		}
		
//		requestMap.put("homeTel", appPersonInfo.getHomePhone1()==null?"":appPersonInfo.getHomePhone1());//住宅电话
//		requestMap.put("homeCode", appPersonInfo.getHomePostcode()==null?"":appPersonInfo.getHomePostcode());//住宅邮编
//		requestMap.put("homeAddr", appPersonInfo.getHomeAddress()==null?"":appPersonInfo.getHomeAddress());//住宅地址
		
		String homeSts = "9";
		if ("00001".equals(appPersonInfo.getHouseType())) {
			homeSts = "1";
		} else if ("00002".equals(appPersonInfo.getHouseType())) {
			homeSts = "4";
		} else if ("00003".equals(appPersonInfo.getHouseType())) {
			homeSts = "3";
		} else if ("00004".equals(appPersonInfo.getHouseType())) {
			homeSts = "5";
		} else {
			homeSts = "9";
		}
		requestMap.put("homeSts", homeSts); // 居住状况
//		requestMap.put("income",appPersonInfo.getTotalMonthSalary() != null ? appPersonInfo.getTotalMonthSalary().toString() : "0"); // 月输入
		requestMap.put("income", NumberUtils.isNull(loanAuditEntity.getSys_check_lmt(), 2).toString());// 终审核实收入信息

		if(tmAppContactInfo != null){
//			requestMap.put("mateName", tmAppContactInfo.getContactName()); // 配偶名称
//			requestMap.put("mateIdtype", "0"); // 配偶证件类型
//			requestMap.put("mateIdno",tmAppContactInfo.getContactIdNo()); // 配偶证件号码
//			requestMap.put("mateWork", tmAppContactInfo.getContactEmpName()); // 配偶工作单位
//			requestMap.put("mateTel", tmAppContactInfo.getContactCellPhone()); // 配偶联系电话
		}
		
		requestMap.put("projNo", "30213"); // 信托项目编号
		requestMap.put("prdtNo", prdtNoByCode(appMain.getProductCd())); // 产品号
		requestMap.put("pactAmt", appMain.getContractLmt() != null ? appMain.getContractLmt().toString() : "0"); // 合同金额
																													// ContractLmt
		requestMap.put("lnRate", "0"); // 利率
		requestMap.put("appArea", "000000"); // 申请地点 无法填报默认000000（中国）
		if (appMain.getCreditApplication() != null) {
			requestMap.put("appUse", appMain.getCreditApplication()); // 申请用途
		} else {
			returnMap.put("resCode", "-1");
			returnMap.put("resMsg", "申请用途不能为空。");
			return returnMap;
		}
		requestMap.put("termMon", appMain.getContractTrem() != null ? appMain.getContractTrem().toString() : "0"); // 合同期限月
																													// 默认0
		requestMap.put("termDay", "0"); // 合同期限日 默认 0
		requestMap.put("vouType", "4"); // 担保方式
//		requestMap.put("endDate", ""); // 到期日期
		requestMap.put("payType", "02"); // 扣款类型
//		requestMap.put("payDay", ""); // 扣款日期
		
//		requestMap.put("workType", appPersonInfo.getOccupation()); // 职业
//		requestMap.put("workName", appPersonInfo.getCorpName()); // 工作单位名称
//		requestMap.put("workSts", appPersonInfo.getEmpStatus()); // 工作状态
//		requestMap.put("workWay", appPersonInfo.getCorpType()); // 工作单位所属行业
//		requestMap.put("workCode", appPersonInfo.getCorpPostcode()); // 工作单位邮编
//		requestMap.put("workAddr", appPersonInfo.getCorpAddress()); // 工作单位地址
//		requestMap.put("workDuty", appPersonInfo.getCorpPost()); // 职务
//		requestMap.put("workTitle", ""); // 职称
//		requestMap.put("workYear", appPersonInfo.getCorpStandFrom()); // 工作起始年份
		//
		if (appCarInfo != null && appCarInfo.getCarBuyDate() != null) {
			requestMap.put("ifCar", "1"); // 是否有车
			if (appCarInfo.getCarLoan() != null) {
				requestMap.put("ifCarCred", appCarInfo.getCarLoan().equals("Y") ? "1" : "0"); // 车是否按揭
			} else {
				requestMap.put("ifCarCred", "2"); // 车是否按揭
			}
		} else {
			requestMap.put("ifCar", "2"); // 是否有车
			requestMap.put("ifCarCred", "2"); // 车是否按揭
		}

		if (appEstateInfo != null
				&& (appEstateInfo.getEstateBuyDate() != null || appEstateInfo.getEstateType() != null)) {
			requestMap.put("ifRoom", "1"); // 是否有房
			if (appEstateInfo.getEstateType() != null) {
				requestMap.put("ifMort", appEstateInfo.getEstateType().equals("ING") ? "1" : "0"); // 房是否按揭
			} else {
				requestMap.put("ifMort", "2"); // 房是否按揭
			}
		} else {
			requestMap.put("ifRoom", "2"); // 是否有房
			requestMap.put("ifMort", "2"); // 房是否按揭
		}
		requestMap.put("ifCard", "2"); // 是否有贷记卡
		requestMap.put("cardAmt", "2"); // 贷记卡最低额度 默认0
		requestMap.put("ifApp", "2"); // 是否填写申请表
		requestMap.put("ifId", "1"); // 是否有身份证信息
		requestMap.put("ifPact", "0"); // 是否签订合同
		params.put("reqParam", requestMap);
		json.put("params", params);

		return json;

	}

	private String prdtNoByCode(String value) {
		if ("00014".equals(value)) {
			return "500001";
		} else if ("00010".equals(value)) {
			return "500002";
		} else if ("00015".equals(value)) {
			return "500003";
		} else if ("00018".equals(value)) {
			return "500004";
		} else {
			return null;
		}
	}
}
