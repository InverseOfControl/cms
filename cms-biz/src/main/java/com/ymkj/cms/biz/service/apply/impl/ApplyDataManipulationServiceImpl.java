package com.ymkj.cms.biz.service.apply.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.bds.biz.api.service.IInternalMatchingExecuter;
import com.ymkj.bds.biz.api.vo.request.IdentifyingAntiFraudReqVO;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.apply.ApplyEntryVO;
import com.ymkj.cms.biz.api.vo.request.apply.ApplyInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.AssetsInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.AuditAmendEntryVO;
import com.ymkj.cms.biz.api.vo.request.apply.AuditEntryVO;
import com.ymkj.cms.biz.api.vo.request.apply.BasicInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.CarInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.CardLoanInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ContactInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.EstateInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.FindHistoryLoanVO;
import com.ymkj.cms.biz.api.vo.request.apply.MasterLoanInfoAVO;
import com.ymkj.cms.biz.api.vo.request.apply.MasterLoanInfoBVO;
import com.ymkj.cms.biz.api.vo.request.apply.MasterLoanInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.MerchantLoanInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.api.vo.request.apply.PersonInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.PolicyInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.PrivateOwnerInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ProvidentInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.RepayListVo;
import com.ymkj.cms.biz.api.vo.request.apply.RepayVo;
import com.ymkj.cms.biz.api.vo.request.apply.ReqApplyEntryVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqLoanUrgentVO;
import com.ymkj.cms.biz.api.vo.request.apply.SalaryLoanInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.WorkInfoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResLoanUrgentVO;
import com.ymkj.cms.biz.common.util.ApplyEnterEncapsulate;
import com.ymkj.cms.biz.common.util.BeanAttributeUtils;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.ParametersType;
import com.ymkj.cms.biz.dao.apply.LoanBaseDao;
import com.ymkj.cms.biz.entity.apply.APPCarInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPCardLoanInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPContactInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPEstateInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPMasterLoanInfoAEntity;
import com.ymkj.cms.biz.entity.apply.APPMasterLoanInfoBEntity;
import com.ymkj.cms.biz.entity.apply.APPMasterLoanInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPMerchantLoanInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPPersonEntity;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPPolicyInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPProvidentInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPSalaryLoanInfoEntity;
import com.ymkj.cms.biz.entity.apply.AppContactHeadEntity;
import com.ymkj.cms.biz.entity.apply.BmsLoanChangeLogEntity;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.apply.LoanBaseRelaEntity;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.apply.LoanProductEntity;
import com.ymkj.cms.biz.entity.apply.PrivateOwnerInfoEntity;
import com.ymkj.cms.biz.entity.audit.LoanAuditEntity;
import com.ymkj.cms.biz.entity.master.BMSEnumCode;
import com.ymkj.cms.biz.entity.master.BMSOrgLimitChannel;
import com.ymkj.cms.biz.entity.master.BMSProduct;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.master.BMSTmParameter;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.APPCarInfoService;
import com.ymkj.cms.biz.service.apply.APPCardLoanInfoService;
import com.ymkj.cms.biz.service.apply.APPContactHeadService;
import com.ymkj.cms.biz.service.apply.APPContactInfoService;
import com.ymkj.cms.biz.service.apply.APPEstateInfoService;
import com.ymkj.cms.biz.service.apply.APPMasterLoanInfoService;
import com.ymkj.cms.biz.service.apply.APPMerchantLoanInfoService;
import com.ymkj.cms.biz.service.apply.APPPersonInfoService;
import com.ymkj.cms.biz.service.apply.APPPersonService;
import com.ymkj.cms.biz.service.apply.APPPolicyInfoService;
import com.ymkj.cms.biz.service.apply.APPProvidentInfoService;
import com.ymkj.cms.biz.service.apply.APPSalaryInfoService;
import com.ymkj.cms.biz.service.apply.ApplyDataManipulationService;
import com.ymkj.cms.biz.service.apply.LoanBaseRelaService;
import com.ymkj.cms.biz.service.apply.LoanBaseService;
import com.ymkj.cms.biz.service.apply.LoanChangeLogService;
import com.ymkj.cms.biz.service.apply.LoanExtService;
import com.ymkj.cms.biz.service.apply.LoanProductService;
import com.ymkj.cms.biz.service.audit.BMSAPPHistoryService;
import com.ymkj.cms.biz.service.audit.ILoanAuditService;
import com.ymkj.cms.biz.service.http.CreditHttpService;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSEnumCodeService;
import com.ymkj.cms.biz.service.master.IBMSLoanUrgentConfigServic;
import com.ymkj.cms.biz.service.master.IBMSOrgLimitChannelService;
import com.ymkj.cms.biz.service.master.IBMSParameterService;
import com.ymkj.cms.biz.service.master.IBMSRejectReasonService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.master.IBMSTmParameterService;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;

@Service
public class ApplyDataManipulationServiceImpl implements ApplyDataManipulationService {

	private static final Logger logger = LoggerFactory.getLogger(ApplyDataManipulationServiceImpl.class);

	private Gson gson = new Gson();

	@Autowired
	private APPCardLoanInfoService appCardLoanInfoService;
	@Autowired
	private APPCarInfoService appCarInfoService;
	@Autowired
	private APPContactInfoService appContactInfoService;
	@Autowired
	private APPEstateInfoService appEstateInfoService;
	@Autowired
	private APPMasterLoanInfoService appMasterLoanInfoService;
	@Autowired
	private APPMerchantLoanInfoService appMerchantLoanInfoService;
	@Autowired
	private APPPersonInfoService appPersonInfoService;
	@Autowired
	private APPPersonService appPersonService;
	@Autowired
	private APPPolicyInfoService appPolicyInfoService;
	@Autowired
	private APPProvidentInfoService appProvidentInfoService;
	@Autowired
	private APPSalaryInfoService appSalaryInfoService;
	@Autowired
	private LoanBaseService loanBaseService;
	@Autowired
	private LoanExtService loanExtService;
	@Autowired
	private LoanProductService loanProductService;
	@Autowired
	private IBMSParameterService parameterService;
	@Autowired
	private BMSAPPHistoryService aPPHistoryService;
	@Autowired
	private IBMSRejectReasonService rejectReasonService;
	@Autowired
	private LoanBaseRelaService loanBaseRelaService;
	@Autowired
	private IBMSEnumCodeService demoService;
	@Autowired
	private ILoanAuditService iLoanAuditService;
	@Autowired
	private IBMSSysLoanLogService sysLoanLogService;
	@Autowired
	private ICoreHttpService coreHttpService;
	@Autowired
	private LoanChangeLogService loanChangeLogService;
    @Autowired
    private IBMSTmParameterService ibmsTmParameterService;
	@Autowired
	private IBMSOrgLimitChannelService ibmsOrgLimitChannelService;
	@Autowired
	private APPContactHeadService aPPContactHeadService;
	@Autowired
	private LoanBaseDao  loanBaseDao;
	@Autowired
	private CreditHttpService creditHttpService;
	@Autowired
	private IInternalMatchingExecuter iInternalMatchingExecuter;
	@Autowired
	private IBMSSysLogService ibmsSysLogService;

	@Autowired
	private IBMSLoanUrgentConfigServic iBMSLoanUrgentConfigServic;

	@Autowired
	private IOrganizationExecuter OrganizationExecuter;

	@Autowired
	private IEmployeeExecuter iEmployeeExecuter;
	@Autowired
	private IBMSOrgLimitChannelService orgLimitChannelService;

	@Override
	public Response<ReqApplyEntryVO> save(ApplyEntryVO applyEntryVO) {
		// 操作录入员工Code
		if (org.apache.commons.lang.StringUtils.isBlank(applyEntryVO.getServiceCode())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "serviceCode" });
		}
		// 操作录入员工Code
		if (org.apache.commons.lang.StringUtils.isBlank(applyEntryVO.getServiceName())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "serviceName" });
		}
		// 创建员工id
		if (applyEntryVO.getCreatorId() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "creatorId" });
		}
		// 创建员工姓名
		if (org.apache.commons.lang.StringUtils.isBlank(applyEntryVO.getCreator())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "creator" });
		}
		// 录入门店ID
		if (applyEntryVO.getOwningBranchId() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "owningBranchId" });
		}
		// 录入门店
		if (org.apache.commons.lang.StringUtils.isBlank(applyEntryVO.getOwningBranch())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "owningBranch" });
		}
		// //录入门店属性
		// if(org.apache.commons.lang.StringUtils.isBlank(applyEntryVO.getOwningBranchAttribute())){
		// throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new
		// Object[]{"owningBranchAttribute"});
		// }
		// 录入区域
		if (org.apache.commons.lang.StringUtils.isBlank(applyEntryVO.getLoggedArea())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "applyInfoVO.LoggedArea" });
		}
		// 录入区域
		if (org.apache.commons.lang.StringUtils.isBlank(applyEntryVO.getLoggedAreaName())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "applyInfoVO.LoggedAreaName" });
		}

		// 构造实体类
		APPCardLoanInfoEntity appCardLoanInfoEntity = new APPCardLoanInfoEntity();
		APPCarInfoEntity appCarInfoEntity = new APPCarInfoEntity();
		List<APPContactInfoEntity> appContactInfoEntityList = new ArrayList<APPContactInfoEntity>();
		APPEstateInfoEntity appEstateInfoEntity = new APPEstateInfoEntity();
		APPMasterLoanInfoEntity appMasterLoanInfoEntity = new APPMasterLoanInfoEntity();
		APPMerchantLoanInfoEntity appMerchantLoanInfoEntity = new APPMerchantLoanInfoEntity();
		APPPersonEntity appPersonEntity = new APPPersonEntity();
		APPPersonInfoEntity appPersonInfoEntity = new APPPersonInfoEntity();
		APPPolicyInfoEntity appPolicyInfoEntity = new APPPolicyInfoEntity();
		APPProvidentInfoEntity appProvidentInfoEntity = new APPProvidentInfoEntity();
		APPSalaryLoanInfoEntity appSalaryLoanInfoEntity = new APPSalaryLoanInfoEntity();
		LoanBaseEntity loanBaseEntity = new LoanBaseEntity();
		LoanExtEntity loanExtEntity = new LoanExtEntity();
		LoanProductEntity loanProductEntity = new LoanProductEntity();
		BMSSysLoanLog sysLoanLog = new BMSSysLoanLog();
		AppContactHeadEntity appContactHeadEntity = new AppContactHeadEntity();

		// 获取参数值
		ApplyInfoVO applytInfoVO = applyEntryVO.getApplyInfoVO() != null ? applyEntryVO.getApplyInfoVO() : new ApplyInfoVO();
		BasicInfoVO basicInfoVO = applyEntryVO.getBasicInfoVO() != null ? applyEntryVO.getBasicInfoVO() : new BasicInfoVO();
		AssetsInfoVO assetsInfoVO = applyEntryVO.getAssetsInfoVO() != null ? applyEntryVO.getAssetsInfoVO() : new AssetsInfoVO();
		List<ContactInfoVO> contactInfoVOList = applyEntryVO.getContactInfoVOList() != null ? applyEntryVO.getContactInfoVOList() : new ArrayList<ContactInfoVO>();
		// 资产信息
		EstateInfoVO estateInfoVO = assetsInfoVO.getEstateInfoVO() != null ? assetsInfoVO.getEstateInfoVO() : new EstateInfoVO();
		CarInfoVO carInfoVO = assetsInfoVO.getCarInfoVO() != null ? assetsInfoVO.getCarInfoVO() : new CarInfoVO();
		PolicyInfoVO policyInfoVO = assetsInfoVO.getPolicyInfoVO() != null ? assetsInfoVO.getPolicyInfoVO() : new PolicyInfoVO();
		ProvidentInfoVO providentInfoVO = assetsInfoVO.getProvidentInfoVO() != null ? assetsInfoVO.getProvidentInfoVO() : new ProvidentInfoVO();
		CardLoanInfoVO cardLoanInfoVO = assetsInfoVO.getCardLoanInfoVO() != null ? assetsInfoVO.getCardLoanInfoVO() : new CardLoanInfoVO();
		SalaryLoanInfoVO salaryLoanInfoVO = assetsInfoVO.getSalaryLoanInfoVO() != null ? assetsInfoVO.getSalaryLoanInfoVO() : new SalaryLoanInfoVO();
		MasterLoanInfoVO masterLoanInfoVO = assetsInfoVO.getMasterLoanInfoVO() != null ? assetsInfoVO.getMasterLoanInfoVO() : new MasterLoanInfoVO();
		MerchantLoanInfoVO merchantLoanInfoVO = assetsInfoVO.getMerchantLoanInfoVO() != null ? assetsInfoVO.getMerchantLoanInfoVO() : new MerchantLoanInfoVO();
		// 基本信息
		PersonInfoVO personInfoVO = basicInfoVO.getPersonInfoVO() != null ? basicInfoVO.getPersonInfoVO() : new PersonInfoVO();
		PrivateOwnerInfoVO privateOwnerInfoVO = basicInfoVO.getPrivateOwnerInfoVO() != null ? basicInfoVO.getPrivateOwnerInfoVO() : new PrivateOwnerInfoVO();
		WorkInfoVO workInfoVO = basicInfoVO.getWorkInfoVO() != null ? basicInfoVO.getWorkInfoVO() : new WorkInfoVO();

		// 实体类赋值
		// 借款人信息 BMS_APP_PERSON
		BeanUtils.copyProperties(personInfoVO, appPersonEntity);
		// 申请信息 bms_loan_base
		BeanUtils.copyProperties(applytInfoVO, loanBaseEntity);

		BeanUtils.copyProperties(applyEntryVO, loanBaseEntity);
		// 主办申请人 BMS_APP_PERSON_INFO
		BeanUtils.copyProperties(personInfoVO, appPersonInfoEntity);
		// 借款信息产品 BMS_LOAN_PRODUCT
		BeanUtils.copyProperties(applytInfoVO, loanProductEntity);
		// 借款扩展 BMS_LOAN_EXT
		BeanUtils.copyProperties(applytInfoVO, loanExtEntity);
		// 房产信息 TM_APP_ESTATE_INFO
		BeanUtils.copyProperties(estateInfoVO, appEstateInfoEntity);
		// 车辆信息 TM_APP_CAR_INFO
		BeanUtils.copyProperties(carInfoVO, appCarInfoEntity);
		// 保单信息 TM_APP_POLICY_INFO
		BeanUtils.copyProperties(policyInfoVO, appPolicyInfoEntity);
		// 公积金信息 TM_APP_POLICY_INFO
		BeanUtils.copyProperties(providentInfoVO, appProvidentInfoEntity);
		// 卡友贷信息 TM_APP_CARD_LOAN_INFO
		BeanUtils.copyProperties(cardLoanInfoVO, appCardLoanInfoEntity);
		// 随薪贷信息 TM_APP_SALARY_LOAN_INFO
		BeanUtils.copyProperties(salaryLoanInfoVO, appSalaryLoanInfoEntity);
		// 网购达人贷 TM_APP_MASTER_LOAN_INFO
		BeanUtils.copyProperties(masterLoanInfoVO, appMasterLoanInfoEntity);
		// 淘宝商户贷信息 TM_APP_MERCHANT_LOAN_INFO
		BeanUtils.copyProperties(merchantLoanInfoVO, appMerchantLoanInfoEntity);
		// 工作信息 BMS_APP_PERSON_INFO
		BeanUtils.copyProperties(workInfoVO, appPersonInfoEntity);
		// 私营业主信息 BMS_APP_PERSON_INFO
		BeanUtils.copyProperties(privateOwnerInfoVO, appPersonInfoEntity);
		// 联系人信息 TM_APP_CONTACT_INFO
		for (ContactInfoVO contactInfoVO : contactInfoVOList) {
			APPContactInfoEntity contactInfoEntity = new APPContactInfoEntity();
			BeanUtils.copyProperties(contactInfoVO, contactInfoEntity);
			appContactInfoEntityList.add(contactInfoEntity);
		}
		// 基本参数获取
		Long creatorId = applyEntryVO.getCreatorId();
		// String creator = applyEntryVO.getCreator();
		String creator = applyEntryVO.getServiceCode();
		Date date = new Date();

		appPersonEntity.setIdType("身份证");// 目前入单暂无证件类型选择，暂时入
		appPersonEntity.setCreatedTime(date);
		appPersonEntity.setCreator(creator);
		appPersonEntity.setCreatorId(creatorId);

		// 查看申请件的类型
		logger.info("111111111111：String applyType = this.getApplyType(applyEntryVO.getApplyInfoVO().getIdNo());");

		String applyType = this.getApplyType(applyEntryVO.getApplyInfoVO().getIdNo());
		loanBaseEntity.setApplyType(applyType);
		//是否优惠费率用户
		logger.info("111111111111：优惠费率判断开始");

		if (applyEntryVO.getApplyInfoVO().getApplyInputFlag().equals("directApplyInput")) {
			//是否优惠费率用户
			String ifPreferentialUser = ifReloanGreaterThan12MonthsUser(applyEntryVO.getApplyInfoVO().getName(),applyEntryVO.getApplyInfoVO().getIdNo())?"Y":"N";
			loanBaseEntity.setIfPreferentialUser(ifPreferentialUser);
		} else {
			loanBaseEntity.setIfPreferentialUser("N");
		}
		logger.info("111111111111：优惠费率结束");
		
		

		loanBaseEntity.setCreatedTime(date);
		loanBaseEntity.setCreator(creator);
		loanBaseEntity.setCreatorId(creatorId);
		if (applyEntryVO.getApplyInfoVO().getAppInputFlag() != null && applyEntryVO.getApplyInfoVO().getAppInputFlag().equals("app_input")) {
			loanBaseEntity.setAppApplyDate(applyEntryVO.getApplyInfoVO().getAppinputDate());// app进件时间
		}

		// 加急 直通车进件 默认是加急，判断当前是否剩余可加急件
		if (applyEntryVO.getApplyInfoVO().getApplyInputFlag().equals("directApplyInput")) {
			// 如果是加急件
			if (applyEntryVO.getApplyInfoVO().getIfPri() != null && applyEntryVO.getApplyInfoVO().getIfPri() == 1) {
				String ifPri = ifPri(applyEntryVO.getOwningBranchId());
				loanProductEntity.setIfPri(new Integer(ifPri));
			}
		}

		appPersonInfoEntity.setCreatedTime(date);
		appPersonInfoEntity.setCreator(creator);
		appPersonInfoEntity.setCreatorId(creatorId);

		loanProductEntity.setInitProductCd(applytInfoVO.getInitProductCd());// 原产品编号
		loanProductEntity.setInitProductName(applytInfoVO.getInitProductName());// 原产品名称
		loanProductEntity.setProductCd(applytInfoVO.getProductCd());
		loanProductEntity.setProductName(applytInfoVO.getProductName());
		loanProductEntity.setInitApplyLmt(applytInfoVO.getApplyLmt());// 原产品金额
		loanProductEntity.setInitApplyTerm(applytInfoVO.getApplyTerm());// 原产品期限
		loanProductEntity.setCreatedTime(date);
		loanProductEntity.setCreator(creator);
		loanProductEntity.setCreatorId(creatorId);

		loanExtEntity.setLoggedArea(applyEntryVO.getLoggedArea());
		loanExtEntity.setLoggedAreaName(applyEntryVO.getLoggedAreaName());
		loanExtEntity.setCreatedTime(date);
		loanExtEntity.setCreator(creator);
		loanExtEntity.setCreatorId(creatorId);

		appEstateInfoEntity.setCreatedTime(date);
		appEstateInfoEntity.setCreator(creator);
		appEstateInfoEntity.setCreatorId(creatorId);

		appCarInfoEntity.setCreatedTime(date);
		appCarInfoEntity.setCreator(creator);
		appCarInfoEntity.setCreatorId(creatorId);

		appPolicyInfoEntity.setCreatedTime(date);
		appPolicyInfoEntity.setCreator(creator);
		appPolicyInfoEntity.setCreatorId(creatorId);

		appProvidentInfoEntity.setCreatedTime(date);
		appProvidentInfoEntity.setCreator(creator);
		appProvidentInfoEntity.setCreatorId(creatorId);
		appCardLoanInfoEntity.setCreatedTime(date);
		appCardLoanInfoEntity.setCreator(creator);
		appCardLoanInfoEntity.setCreatorId(creatorId);

		appSalaryLoanInfoEntity.setCreatedTime(date);
		appSalaryLoanInfoEntity.setCreator(creator);
		appSalaryLoanInfoEntity.setCreatorId(creatorId);

		appMasterLoanInfoEntity.setCreatedTime(date);
		appMasterLoanInfoEntity.setCreator(creator);
		appMasterLoanInfoEntity.setCreatorId(creatorId);

		appMerchantLoanInfoEntity.setCreatedTime(date);
		appMerchantLoanInfoEntity.setCreator(creator);
		appMerchantLoanInfoEntity.setCreatorId(creatorId);

		// 生成借款编号
//		String loanNo = ApplyEnterEncapsulate.createAppNo(date);
		logger.info("111111111111：生成借款编号");
		String loanNo = loanBaseDao.createLoanNo(date);
		logger.info("新申请的编号loanNo：" + loanNo);
		// 生成客户编号
		String personNo = ApplyEnterEncapsulate.createPersonNo(date);
		logger.info("新申请的编号personNo：" + loanNo);
		// 数据库操作 insert
		appPersonEntity.setPerosnNo(personNo); // 客户编号
		logger.info("111111111111：		Long personId = appPersonService.saveOrUpdate(appPersonEntity);");
		Long personId = appPersonService.saveOrUpdate(appPersonEntity);

		loanBaseEntity.setPersonId(personId);
		loanBaseEntity.setLoanNo(loanNo); // 借款编号
		loanBaseEntity.setStatus(EnumConstants.LoanStatus.APPLY.getValue()); // 申请件状态
		loanBaseEntity.setRtfState(EnumConstants.RtfState.SQLR.getValue()); // 流程状态
		loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_SAVE.getValue());

		loanBaseEntity.setServiceCode(applyEntryVO.getServiceCode());// 客服code
		loanBaseEntity.setServiceName(applyEntryVO.getServiceName());// 客服名称

		/** app，普通门店进件，签约客服就是录入客服 **/
		if (applyEntryVO.getApplyInfoVO().getApplyInputFlag().equals("applyInput")) {
			loanBaseEntity.setSignCode(applyEntryVO.getServiceCode());// 客服code
			loanBaseEntity.setSignName(applyEntryVO.getServiceName());// 客服名称
		}

		loanBaseEntity.setApplyDate(date);
		logger.info("111111111111：		Long loanBaseId = loanBaseService.saveOrUpdate(loanBaseEntity);");

		Long loanBaseId = loanBaseService.saveOrUpdate(loanBaseEntity);

		appPersonInfoEntity.setPersonId(personId);
		appPersonInfoEntity.setApplyPersonId(personId);
		appPersonInfoEntity.setLoanBaseId(loanBaseId);
		appPersonInfoEntity.setLoanNo(loanNo);
		appPersonInfoEntity.setOrg(EnumConstants.BMS_Org);
		logger.info("111111111111：		Long personInfoId = appPersonInfoService.saveOrUpdate(appPersonInfoEntity);");

		Long personInfoId = appPersonInfoService.saveOrUpdate(appPersonInfoEntity);

		loanProductEntity.setLoanBaseId(loanBaseId);
		loanProductEntity.setLoanNo(loanNo);
		
		logger.info("111111111111：loanProductService.saveOrUpdate(loanProductEntity);");

		loanProductService.saveOrUpdate(loanProductEntity);

		loanExtEntity.setLoanBaseId(loanBaseId);
		loanExtEntity.setLoanNo(loanNo);
		loanExtEntity.setApplyStartTime(date);
		logger.info("111111111111：loanExtService.saveOrUpdate(loanExtEntity);");

		loanExtService.saveOrUpdate(loanExtEntity);

		appEstateInfoEntity.setLoanBaseId(loanBaseId);
		appEstateInfoEntity.setOrg(EnumConstants.BMS_Org);
		appEstateInfoEntity.setEstateId(new Long(0));
		appEstateInfoEntity.setLoanNo(loanNo);
		logger.info("111111111111：appEstateInfoService.saveOrUpdate(appEstateInfoEntity)");

		Long appEstateInfoId = appEstateInfoService.saveOrUpdate(appEstateInfoEntity);

		appCarInfoEntity.setLoanBaseId(loanBaseId);
		appCarInfoEntity.setOrg(EnumConstants.BMS_Org);
		appCarInfoEntity.setCheckId(new Long(0));
		appCarInfoEntity.setLoanNo(loanNo);
		
		logger.info("111111111111：appCarInfoService.saveOrUpdate(appCarInfoEntity)");

		Long appCarInfoId = appCarInfoService.saveOrUpdate(appCarInfoEntity);

		appPolicyInfoEntity.setLoanBaseId(loanBaseId);
		appPolicyInfoEntity.setOrg(EnumConstants.BMS_Org);
		appPolicyInfoEntity.setPolicyId(new Long(0));
		appPolicyInfoEntity.setLoanNo(loanNo);
		
		logger.info("111111111111：appPolicyInfoService.saveOrUpdate(appPolicyInfoEntity);");

		Long appPolicyInfoId = appPolicyInfoService.saveOrUpdate(appPolicyInfoEntity);

		// 公积金贷
		appProvidentInfoEntity.setLoanBaseId(loanBaseId);
		appProvidentInfoEntity.setOrg(EnumConstants.BMS_Org);
		appProvidentInfoEntity.setProvidentId(new Long(0));
		appProvidentInfoEntity.setLoanNo(loanNo);
		
		logger.info("111111111111：appProvidentInfoService.saveOrUpdate(appProvidentInfoEntity)");

		Long appProvidentInfoId = appProvidentInfoService.saveOrUpdate(appProvidentInfoEntity);

		appCardLoanInfoEntity.setOrg(EnumConstants.BMS_Org);
		appCardLoanInfoEntity.setLoanBaseId(loanBaseId);
		appCardLoanInfoEntity.setLoanNo(loanNo);
		
		logger.info("111111111111：appCardLoanInfoService.saveOrUpdate(appCardLoanInfoEntity)");

		Long appCardLoanInfoId = appCardLoanInfoService.saveOrUpdate(appCardLoanInfoEntity);

		appSalaryLoanInfoEntity.setOrg(EnumConstants.BMS_Org);
		appSalaryLoanInfoEntity.setLoanBaseId(loanBaseId);
		appSalaryLoanInfoEntity.setLoanNo(loanNo);
		
		logger.info("111111111111：appSalaryInfoService.saveOrUpdate(appSalaryLoanInfoEntity)");

		Long appSalaryLoanInfoId = appSalaryInfoService.saveOrUpdate(appSalaryLoanInfoEntity);

		appMasterLoanInfoEntity.setOrg(EnumConstants.BMS_Org);
		appMasterLoanInfoEntity.setLoanBaseId(loanBaseId);
		appMasterLoanInfoEntity.setMasterLoadId(new Long(0));
		appMasterLoanInfoEntity.setLoanNo(loanNo);
		
		logger.info("111111111111：appMasterLoanInfoService.saveOrUpdate(appMasterLoanInfoEntity)");

		Long appMasterLoanInfoId = appMasterLoanInfoService.saveOrUpdate(appMasterLoanInfoEntity);

		appMerchantLoanInfoEntity.setOrg(EnumConstants.BMS_Org);
		appMerchantLoanInfoEntity.setLoanBaseId(loanBaseId);
		appMerchantLoanInfoEntity.setMerchantLoanId(new Long(0));
		appMerchantLoanInfoEntity.setLoanNo(loanNo);
		
		logger.info("111111111111：appMerchantLoanInfoService.saveOrUpdate(appMerchantLoanInfoEntity)");

		Long appMerchantLoanInfoId = appMerchantLoanInfoService.saveOrUpdate(appMerchantLoanInfoEntity);

		appContactHeadEntity.setLoanNo(loanNo);
		appContactHeadEntity.setLoanBaseId(loanBaseId);
		appContactHeadEntity.setPersonId(personId);
		appContactHeadEntity.setCreatedTime(date);
		appContactHeadEntity.setCreator(creator);
		appContactHeadEntity.setCreatorId(creatorId);
		
		logger.info("111111111111：aPPContactHeadService.saveOrUpdate(appContactHeadEntity)");

		Long aPPContactHeadid = aPPContactHeadService.saveOrUpdate(appContactHeadEntity);

		if (appContactInfoEntityList != null && appContactInfoEntityList.size() > 0) {
			for (int i = 0; i < appContactInfoEntityList.size(); i++) {
				appContactInfoEntityList.get(i).setHeadId(aPPContactHeadid);
				appContactInfoEntityList.get(i).setOrg(EnumConstants.BMS_Org);
				appContactInfoEntityList.get(i).setLoanNo(loanNo);
				appContactInfoEntityList.get(i).setLoanBaseId(loanBaseId);
				appContactInfoEntityList.get(i).setPersonId(personId);
				appContactInfoEntityList.get(i).setCreatedTime(date);
				appContactInfoEntityList.get(i).setCreator(creator);
				appContactInfoEntityList.get(i).setCreatorId(creatorId);
			}
			logger.info("111111111111：appContactInfoService.saveList(appContactInfoEntityList)");

			appContactInfoService.saveList(appContactInfoEntityList);
		}
		LoanBaseRelaEntity loanBaseRelaEntity = new LoanBaseRelaEntity();
		loanBaseRelaEntity.setTmAppContatcInfoId(aPPContactHeadid);
		loanBaseRelaEntity.setBmsAppPersonInfoId(personInfoId);
		loanBaseRelaEntity.setLoanBaseId(loanBaseId);
		loanBaseRelaEntity.setPersonId(personId);
		loanBaseRelaEntity.setTmAppCarInfoId(appCarInfoId);
		loanBaseRelaEntity.setTmAppEstateInfoId(appEstateInfoId);
		loanBaseRelaEntity.setTmAppMasterLoanInfoId(appMasterLoanInfoId);
		loanBaseRelaEntity.setTmAppMerchanLoanInfoId(appMerchantLoanInfoId);
		loanBaseRelaEntity.setTmAppPolicyInfoId(appPolicyInfoId);
		loanBaseRelaEntity.setTmAppProvidentInfoId(appProvidentInfoId);
		loanBaseRelaEntity.setTmAppSalaryLoanInfoId(appSalaryLoanInfoId);
		loanBaseRelaEntity.setTmAppCardLoanInfoId(appCardLoanInfoId);
		loanBaseRelaEntity.setCreatedTime(date);
		loanBaseRelaEntity.setCreator(creator);
		loanBaseRelaEntity.setCreatorId(creatorId);
		
		logger.info("111111111111：loanBaseRelaService.saveOrUpdate(loanBaseRelaEntity);");

		loanBaseRelaService.saveOrUpdate(loanBaseRelaEntity);

		sysLoanLog.setLoanBaseId(loanBaseId);
		sysLoanLog.setLoanNo(loanNo);
		sysLoanLog.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
		sysLoanLog.setRtfState(EnumConstants.RtfState.SQLR.getValue());
		sysLoanLog.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_SAVE.getValue());
		sysLoanLog.setOperationModule(EnumConstants.OptionModule.APPLY_TASK.getValue());
		sysLoanLog.setOperationType(EnumConstants.OptionType.SAVE.getValue());
		sysLoanLog.setOperationTime(date);
		sysLoanLog.setOperator(applyEntryVO.getServiceName());
		sysLoanLog.setOperatorCode(applyEntryVO.getServiceCode());
		sysLoanLog.setRemark(applyEntryVO.getApplyInfoVO().getRemark());
		
		logger.info("111111111111：sysLoanLogService.saveOrUpdate(sysLoanLog);");

		sysLoanLogService.saveOrUpdate(sysLoanLog);

		// 返回申请件编号
		Response<ReqApplyEntryVO> response = new Response<ReqApplyEntryVO>();
		ReqApplyEntryVO vo = new ReqApplyEntryVO();
		vo.setLoanBaseId(loanBaseId);
		vo.setLoanNo(loanNo);
		response.setData(vo);
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response<ReqApplyEntryVO> update(ApplyEntryVO applyEntryVO) {
		Response<ReqApplyEntryVO> response = new Response<ReqApplyEntryVO>();
		// 是否需要校验
		boolean isVerify = false;
		// 如果提交需要校验参数
		if (applyEntryVO.getOptionType().equals(EnumConstants.OptionType.SUBMIT.getValue())) {
			isVerify = true;
		}

		// 是否第一次数据
		boolean ifFirstSubmit = false;

		// 操作员工Code
		if (org.apache.commons.lang.StringUtils.isBlank(applyEntryVO.getServiceCode())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "serviceCode" });
		}
		// 操作员工Code
		if (org.apache.commons.lang.StringUtils.isBlank(applyEntryVO.getServiceName())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "serviceName" });
		}

		// 更新员工id
		if (applyEntryVO.getModifierId() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "modifierId" });
		}
		// 更新员工姓名
		if (org.apache.commons.lang.StringUtils.isBlank(applyEntryVO.getModifier())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "modifier" });
		}

		
		logger.info("111111111111：update   loanBaseRelaService.getByBaseId;");

		
		// 获取配置总表
		LoanBaseRelaEntity LoanBaseRela = loanBaseRelaService.getByBaseId(applyEntryVO.getApplyInfoVO().getLoanBaseId());
		if (LoanBaseRela == null) {
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}

		// 构造实体类
		APPCardLoanInfoEntity appCardLoanInfoEntity = new APPCardLoanInfoEntity();
		APPCarInfoEntity appCarInfoEntity = new APPCarInfoEntity();
		List<APPContactInfoEntity> appContactInfoEntityList = new ArrayList<APPContactInfoEntity>();
		APPEstateInfoEntity appEstateInfoEntity = new APPEstateInfoEntity();
		APPMasterLoanInfoEntity appMasterLoanInfoEntity = new APPMasterLoanInfoEntity();
		APPMerchantLoanInfoEntity appMerchantLoanInfoEntity = new APPMerchantLoanInfoEntity();
		APPPersonEntity appPersonEntity = new APPPersonEntity();
		APPPersonInfoEntity appPersonInfoEntity = new APPPersonInfoEntity();
		APPPolicyInfoEntity appPolicyInfoEntity = new APPPolicyInfoEntity();
		APPProvidentInfoEntity appProvidentInfoEntity = new APPProvidentInfoEntity();
		APPSalaryLoanInfoEntity appSalaryLoanInfoEntity = new APPSalaryLoanInfoEntity();
		LoanBaseEntity loanBaseEntity = new LoanBaseEntity();
		LoanExtEntity loanExtEntity = new LoanExtEntity();
		LoanProductEntity loanProductEntity = new LoanProductEntity();
		BMSSysLoanLog sysLoanLog = new BMSSysLoanLog();
		// 获取参数值
		ApplyInfoVO applytInfoVO = applyEntryVO.getApplyInfoVO() != null ? applyEntryVO.getApplyInfoVO() : new ApplyInfoVO();
		BasicInfoVO basicInfoVO = applyEntryVO.getBasicInfoVO() != null ? applyEntryVO.getBasicInfoVO() : new BasicInfoVO();
		AssetsInfoVO assetsInfoVO = applyEntryVO.getAssetsInfoVO() != null ? applyEntryVO.getAssetsInfoVO() : new AssetsInfoVO();
		List<ContactInfoVO> contactInfoVOList = applyEntryVO.getContactInfoVOList() != null ? applyEntryVO.getContactInfoVOList() : new ArrayList<ContactInfoVO>();
		// 资产信息
		EstateInfoVO estateInfoVO = assetsInfoVO.getEstateInfoVO() != null ? assetsInfoVO.getEstateInfoVO() : new EstateInfoVO();
		CarInfoVO carInfoVO = assetsInfoVO.getCarInfoVO() != null ? assetsInfoVO.getCarInfoVO() : new CarInfoVO();
		PolicyInfoVO policyInfoVO = assetsInfoVO.getPolicyInfoVO() != null ? assetsInfoVO.getPolicyInfoVO() : new PolicyInfoVO();
		ProvidentInfoVO providentInfoVO = assetsInfoVO.getProvidentInfoVO() != null ? assetsInfoVO.getProvidentInfoVO() : new ProvidentInfoVO();
		CardLoanInfoVO cardLoanInfoVO = assetsInfoVO.getCardLoanInfoVO() != null ? assetsInfoVO.getCardLoanInfoVO() : new CardLoanInfoVO();
		SalaryLoanInfoVO salaryLoanInfoVO = assetsInfoVO.getSalaryLoanInfoVO() != null ? assetsInfoVO.getSalaryLoanInfoVO() : new SalaryLoanInfoVO();
		MasterLoanInfoVO masterLoanInfoVO = assetsInfoVO.getMasterLoanInfoVO() != null ? assetsInfoVO.getMasterLoanInfoVO() : new MasterLoanInfoVO();
		MerchantLoanInfoVO merchantLoanInfoVO = assetsInfoVO.getMerchantLoanInfoVO() != null ? assetsInfoVO.getMerchantLoanInfoVO() : new MerchantLoanInfoVO();
		// 基本信息
		PersonInfoVO personInfoVO = basicInfoVO.getPersonInfoVO() != null ? basicInfoVO.getPersonInfoVO() : new PersonInfoVO();
		PrivateOwnerInfoVO privateOwnerInfoVO = basicInfoVO.getPrivateOwnerInfoVO() != null ? basicInfoVO.getPrivateOwnerInfoVO() : new PrivateOwnerInfoVO();
		WorkInfoVO workInfoVO = basicInfoVO.getWorkInfoVO() != null ? basicInfoVO.getWorkInfoVO() : new WorkInfoVO();

		// 实体类赋值
		// 借款人信息 BMS_APP_PERSON
		BeanUtils.copyProperties(personInfoVO, appPersonEntity);

		BeanUtils.copyProperties(applytInfoVO, appPersonEntity);
		// 申请信息 bms_loan_base
		BeanUtils.copyProperties(applytInfoVO, loanBaseEntity);

		// 主办申请人 BMS_APP_PERSON_INFO
		BeanUtils.copyProperties(personInfoVO, appPersonInfoEntity);
		// 借款信息产品 BMS_LOAN_PRODUCT
		BeanUtils.copyProperties(applytInfoVO, loanProductEntity);
		// 借款扩展 BMS_LOAN_EXT
		BeanUtils.copyProperties(applytInfoVO, loanExtEntity);
		// 房产信息 TM_APP_ESTATE_INFO
		BeanUtils.copyProperties(estateInfoVO, appEstateInfoEntity);
		// 车辆信息 TM_APP_CAR_INFO
		BeanUtils.copyProperties(carInfoVO, appCarInfoEntity);
		// 保单信息 TM_APP_POLICY_INFO
		BeanUtils.copyProperties(policyInfoVO, appPolicyInfoEntity);
		// 公积金信息 TM_APP_POLICY_INFO
		BeanUtils.copyProperties(providentInfoVO, appProvidentInfoEntity);
		// 卡友贷信息 TM_APP_CARD_LOAN_INFO
		BeanUtils.copyProperties(cardLoanInfoVO, appCardLoanInfoEntity);
		// 随薪贷信息 TM_APP_SALARY_LOAN_INFO
		BeanUtils.copyProperties(salaryLoanInfoVO, appSalaryLoanInfoEntity);
		// 网购达人贷 TM_APP_MASTER_LOAN_INFO
		BeanUtils.copyProperties(masterLoanInfoVO, appMasterLoanInfoEntity);
		// 淘宝商户贷信息 TM_APP_MERCHANT_LOAN_INFO
		BeanUtils.copyProperties(merchantLoanInfoVO, appMerchantLoanInfoEntity);
		// 工作信息 BMS_APP_PERSON_INFO
		BeanUtils.copyProperties(workInfoVO, appPersonInfoEntity);
		// 私营业主信息 BMS_APP_PERSON_INFO
		BeanUtils.copyProperties(privateOwnerInfoVO, appPersonInfoEntity);
		// 联系人信息 TM_APP_CONTACT_INFO
		for (int i = 0; i < contactInfoVOList.size(); i++) {
			APPContactInfoEntity contactInfoEntity = new APPContactInfoEntity();
			BeanUtils.copyProperties(contactInfoVOList.get(i), contactInfoEntity);
			appContactInfoEntityList.add(contactInfoEntity);
		}

		// 基本参数获取
		Long modifierId = applyEntryVO.getModifierId();
		// String modifier = applyEntryVO.getModifier();
		String modifier = applyEntryVO.getServiceCode();

		Date date = new Date();

		if (isVerify) {
			// 参数 规则验证
			// 申请信息
			Response<ReqApplyEntryVO> responseApplyInfo = Validate.getInstance().validate(loanProductEntity);
			if (!responseApplyInfo.isSuccess()) {
				logger.debug("loanProductEntity 参数 规则验证: 校验不通过");
				return responseApplyInfo;
			}

			Response<ReqApplyEntryVO> responseLoanBase = Validate.getInstance().validate(loanBaseEntity);
			if (!responseLoanBase.isSuccess()) {
				logger.info("loanBaseEntity 参数 规则验证: 校验不通过");
				return responseLoanBase;
			}

			// 借款人信息
			Response<ReqApplyEntryVO> responsePerson = Validate.getInstance().validate(appPersonEntity);
			if (!responsePerson.isSuccess()) {
				logger.info("appPersonEntity 参数 规则验证: 校验不通过");
				return responsePerson;
			}
			// 借款用途
			Response<ReqApplyEntryVO> responseLoanExt = Validate.getInstance().validate(loanExtEntity);
			if (!responseLoanExt.isSuccess()) {
				logger.info("loanExtEntity 参数 规则验证: 校验不通过");
				return responseLoanExt;
			}

			// 个人基本信息 工作信息
			Response<ReqApplyEntryVO> responseAppPersonInfo = Validate.getInstance().validate(appPersonInfoEntity);
			if (!responseAppPersonInfo.isSuccess()) {
				logger.info("appPersonInfoEntity 参数 规则验证: 校验不通过");
				return responseAppPersonInfo;
			}else{
				if(appPersonInfoEntity.getHouseType().equals(ParametersType.HouseType._00004)){
					//住宅类型选择“租房”时必填  并且大于0
					if(appPersonInfoEntity.getHouseRent() == null || appPersonInfoEntity.getHouseRent().intValue() < 0){
						throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "当前住宅类型是租房，租金必须大于0" });
					}
				}
				//QQ、微信、电子邮箱至少填写一项
				if(appPersonInfoEntity.getQqNum() ==null && appPersonInfoEntity.getEmail() == null && appPersonInfoEntity.getWeChatNum() == null){
					throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "QQ、微信、电子邮箱至少填写一项" });
				}
				
			}
			//自动填充 职业
			//appPersonInfoEntity.setOccupation(getOccupation(appPersonInfoEntity.getCusWorkType()));
			

			Response<ReqApplyEntryVO> responPrivateOwnerInfo = new Response<ReqApplyEntryVO>();
			// 私营业主信息 客户工作类型选“私营业主”时必填，选择“自雇人士”时选填
			if (!StringUtils.isEmpty(appPersonInfoEntity.getCusWorkType()) && appPersonInfoEntity.getCusWorkType().equals(EnumConstants.WorkType.PrivateOwners.getValue())) {

				PrivateOwnerInfoEntity privateOwnerInfoEntity = new PrivateOwnerInfoEntity();
				BeanUtils.copyProperties(appPersonInfoEntity, privateOwnerInfoEntity);
				responPrivateOwnerInfo = Validate.getInstance().validate(privateOwnerInfoEntity);
			}
			if (!responPrivateOwnerInfo.isSuccess()) {
				logger.info("responPrivateOwnerInfo 参数 规则验证: 校验不通过");
				return responPrivateOwnerInfo;
			}

			// 联系人
			if (appContactInfoEntityList.size() < 5) {
				logger.info("responseContactInfoEntityList.size<5 参数 规则验证: 校验不通过");
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "contactInfoVOList个数不满足" });
			}
			for (APPContactInfoEntity appContactInfoEntity : appContactInfoEntityList) {
				Response<ReqApplyEntryVO> responseContactInfoEntityList = Validate.getInstance().validate(appContactInfoEntity);
				if (!responseContactInfoEntityList.isSuccess()) {
					logger.info("responseContactInfoEntityList 参数 规则验证: 校验不通过");
					return responseContactInfoEntityList;
				}
				
				if(StringUtils.isEmpty(appContactInfoEntity.getContactRelation())){
					throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "联系人 	与申请人关系不能为空!" });
				}
				
				//联系人新增配置选择 
				if(appContactInfoEntity.getContactRelation() != null && appContactInfoEntity.getContactRelation().equals(ParametersType.Relationship._00013) ){
					// 如果婚姻状况是  “已婚”、“再婚”、“复婚”  需要校验配偶身份证
					if(appPersonInfoEntity.getMaritalStatus() != null && (
							appPersonInfoEntity.getMaritalStatus().equals(ParametersType.MaritalStatus._0002) ||
							appPersonInfoEntity.getMaritalStatus().equals(ParametersType.MaritalStatus._0006) ||
							appPersonInfoEntity.getMaritalStatus().equals(ParametersType.MaritalStatus._0007)
							)){
						if(StringUtils.isEmpty(appContactInfoEntity.getContactIdNo())){
							logger.info("联系人为配偶，身份证号不为空!");
							throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "联系人为配偶，身份证号不为空!" });
						}
					}
				}
			}

			/** 获取商品资产配置表 */

			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("productCode", loanProductEntity.getProductCd());
			logger.info("111111111111：update   List<BMSEnumCode> ecnumCodeList = demoService.getProducAssetsInfoByCode(paraMap);");

			List<BMSEnumCode> ecnumCodeList = demoService.getProducAssetsInfoByCode(paraMap);
			if (ecnumCodeList != null && ecnumCodeList.size() > 0) {
				String model;
				for (int i = 0; i < ecnumCodeList.size(); i++) {
					model = ecnumCodeList.get(i).getCode();
//					if (model.equals(EnumConstants.productModule.SALARYLOAN.getValue())) {// 随薪贷
//						// 随薪贷
//						Response<ReqApplyEntryVO> responseSalaryLoanInfoEntity = Validate.getInstance().validate(appSalaryLoanInfoEntity);
//						if (!responseSalaryLoanInfoEntity.isSuccess()) {
//							logger.info("appSalaryLoanInfoEntity 参数 规则验证: 校验不通过");
//							return responseSalaryLoanInfoEntity;
//						}
//					} else 
					if (model.equals(EnumConstants.productModule.ESTATE.getValue())) {// 房产贷
						// 房产信息
						Response<ReqApplyEntryVO> responseEstateInfoEntity = Validate.getInstance().validate(appEstateInfoEntity);
						if (!responseEstateInfoEntity.isSuccess()) {
							logger.info("appEstateInfoEntity 参数 规则验证: 校验不通过");
							return responseEstateInfoEntity;
						}
						// 当 房贷情况 还款中时 房贷金额，月供，已还期数 不能为空
						if (appEstateInfoEntity.getEstateLoan().equals("ING")) {
//							if (appEstateInfoEntity.getEstateLoanAmt() == null) {
//								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "estateLoanAmt 房贷金额" });
//							}
							if (appEstateInfoEntity.getMonthPaymentAmt() == null) {
								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "monthPaymentAmt  月供" });
							}
							if(appEstateInfoEntity.getEstateLoanIssueDate() == null){
								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "estateLoanIssueDate  房贷发放年月" });
							}
							
//							if (appEstateInfoEntity.getHasRepaymentNum() == null) {
//								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "hasRepaymentNum  已还期数" });
//							}
						}else{
							if(appEstateInfoEntity.getIfMe() == null){
								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "房产信息模块   单据户名为本人" });
							}
						}

					} else if (model.equals(EnumConstants.productModule.CAR.getValue())) {// 车辆贷
						// 车辆信息
						Response<ReqApplyEntryVO> responseCarInfoEntity = Validate.getInstance().validate(appCarInfoEntity);
						if (!responseCarInfoEntity.isSuccess()) {
							logger.debug("appCarInfoEntity 参数 规则验证: 校验不通过");
							return responseCarInfoEntity;
						}
						// 是否有车贷
						if (appCarInfoEntity.getCarLoan().equals(ParametersType.Indicator._Y)) {
							if (appCarInfoEntity.getMonthPaymentAmt() == null) {
								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "monthPaymentAmt  月供" });
							}
//							if (appCarInfoEntity.getCarLoanTerm() == null) {
//								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "carLoanTerm  贷款剩余期数" });
//							}
							if(appCarInfoEntity.getCarLoanIssueDate() == null){
								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "carLoanIssueDate  车贷发放年月贷" });
							}
						}

//						// 一手车 购车价和裸车价 都必填 二手车 二者必填一
//						if (appCarInfoEntity.getCarType().equals(ParametersType.CarType._ONE)) {
//							if (appCarInfoEntity.getNakedCarPrice() == null) {
//								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "nakedCarPrice  裸车价" });
//							}
//							if (appCarInfoEntity.getCarBuyPrice() == null) {
//								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "carBuyPrice  购买价" });
//							}
//						} else {
//							if (appCarInfoEntity.getNakedCarPrice() == null && appCarInfoEntity.getCarBuyPrice() == null) {
//								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "二手车  裸车价和购买价二者必填一" });
//							}
//						}

					} else if (model.equals(EnumConstants.productModule.PROVIDENT.getValue())) {// 公积金贷
						// 公积金信息
						Response<ReqApplyEntryVO> responseProvidentInfoEntity = Validate.getInstance().validate(appProvidentInfoEntity);
						if (!responseProvidentInfoEntity.isSuccess()) {
							logger.info("appProvidentInfoEntity 参数 规则验证: 校验不通过");
							return responseProvidentInfoEntity;
						}
					} else if (model.equals(EnumConstants.productModule.POLICY.getValue())) {// 保单贷
						// 保单信息
						Response<ReqApplyEntryVO> responsePolicyInfoEntity = Validate.getInstance().validate(appPolicyInfoEntity);
						if (!responsePolicyInfoEntity.isSuccess()) {
							logger.info("appPolicyInfoEntity 参数 规则验证: 校验不通过");
							return responsePolicyInfoEntity;
						}
					} else if (model.equals(EnumConstants.productModule.MASTERLOAN_A.getValue())) {// 网购达人贷A
						APPMasterLoanInfoAEntity appMasterLoanInfoAEntity = new APPMasterLoanInfoAEntity();
						BeanUtils.copyProperties(appMasterLoanInfoEntity, appMasterLoanInfoAEntity);

						// 网购达人贷A
						Response<ReqApplyEntryVO> responseMasterLoanInfoEntity = Validate.getInstance().validate(appMasterLoanInfoAEntity);
						if (!responseMasterLoanInfoEntity.isSuccess()) {
							logger.info("appMasterLoanInfoEntity 参数 规则验证: 校验不通过");
							return responseMasterLoanInfoEntity;
						}
					} else if (model.equals(EnumConstants.productModule.MASTERLOAN_B.getValue())) {// 网购达人贷B
						APPMasterLoanInfoBEntity appMasterLoanInfoBEntity = new APPMasterLoanInfoBEntity();
						BeanUtils.copyProperties(appMasterLoanInfoEntity, appMasterLoanInfoBEntity);

						// 网购达人贷B
						Response<ReqApplyEntryVO> responseMasterLoanInfoEntity = Validate.getInstance().validate(appMasterLoanInfoBEntity);
						if (!responseMasterLoanInfoEntity.isSuccess()) {
							logger.info("appMasterLoanInfoEntity 参数 规则验证: 校验不通过");
							return responseMasterLoanInfoEntity;
						}
					} else if (model.equals(EnumConstants.productModule.MERCHANTLOAN.getValue())) {// 淘宝商户贷
						// 淘宝商户贷信息
						Response<ReqApplyEntryVO> responseMerchantLoanInfoEntity = Validate.getInstance().validate(appMerchantLoanInfoEntity);
						if (!responseMerchantLoanInfoEntity.isSuccess()) {
							logger.info("appMerchantLoanInfoEntity 参数 规则验证: 校验不通过");
							return responseMerchantLoanInfoEntity;
						}
					} else if (model.equals(EnumConstants.productModule.CARDLOAN.getValue())) {// 卡友贷
						// 卡友信息
						Response<ReqApplyEntryVO> responseCardLoanInfoEntity = Validate.getInstance().validate(appCardLoanInfoEntity);
						if (!responseCardLoanInfoEntity.isSuccess()) {
							logger.info("appCardLoanInfoEntity 参数 规则验证: 校验不通过");
							return responseCardLoanInfoEntity;
						}
					}
				}
			}
			// 如果校验成功, 继续业务处理
		}
		logger.info("111111111111：update  LoanBaseEntity loanBaseEntitySelect = loanBaseService.getById(applytInfoVO.getLoanBaseId());");

		LoanBaseEntity loanBaseEntitySelect = loanBaseService.getById(applytInfoVO.getLoanBaseId());
		//查询姓名和身份证号号，和传过来的信息是否一致
		if(!loanBaseEntitySelect.getName().equals(personInfoVO.getName()) || !loanBaseEntitySelect.getIdNo().equals(personInfoVO.getIdNo())){
			APPPersonInfoEntity reportEntitys = new APPPersonInfoEntity();
			reportEntitys.setId(LoanBaseRela.getBmsAppPersonInfoId());
			reportEntitys.setFlag("REP");
			reportEntitys.setReportId(null);
		    reportEntitys.setIfReportId("N");
			reportEntitys.setModifiedTime(date);
			reportEntitys.setModifier(modifier);
			reportEntitys.setModifierId(modifierId);
			appPersonInfoService.saveOrUpdate(reportEntitys);
		}

		// 查看审批表是否有数据，如果没有，说明是新数据
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", loanBaseEntitySelect.getLoanNo());
		logger.info("111111111111：update  LoanAuditEntity loanAuditEntitySelect = iLoanAuditService.getBy(paramMap);");

		LoanAuditEntity loanAuditEntitySelect = iLoanAuditService.getBy(paramMap);

		logger.info("查询借款审核表结果数据为[" + (loanAuditEntitySelect == null) + "]，改借款数据属于退件！！");
		if (loanAuditEntitySelect == null) {
			// 是第一次提交
			ifFirstSubmit = true;
		}

		if (applyEntryVO.getOptionModule().equals(EnumConstants.OptionModule.APPLY_CHECK.getValue()) && applyEntryVO.getOptionType().equals(EnumConstants.OptionType.SUBMIT.getValue())) {
			if (ifFirstSubmit) {
				// 复核提交 并且是第一次提交将 将录入门店信息补充
				loanBaseEntity.setEnterBranchId(applyEntryVO.getOwningBranchId());
				loanBaseEntity.setEnterBranch(applyEntryVO.getOwningBranch());
				loanBaseEntity.setEnterBranchAttribute(applyEntryVO.getOwningBranchAttribute());
			}

			// 业务主任查询
			ResEmployeeVO queryDirector = queryDirector(applyEntryVO.getApplyInfoVO().getBranchManagerCode());
			logger.info("查询业务主任结果：" + (queryDirector == null));
			if (queryDirector != null) {
				loanBaseEntity.setDirector(queryDirector.getName());
				loanBaseEntity.setDirectorCode(queryDirector.getUsercode());
				loanBaseEntity.setGroupForDirector(queryDirector.getOrgName());
				loanBaseEntity.setGroupForDirectorId(queryDirector.getOrgId());
			}
		}

		// 如果提交需要校验参数
		if (applyEntryVO.getOptionType().equals(EnumConstants.OptionType.SUBMIT.getValue())) {
			logger.info("OptionType:[" + applyEntryVO.getOptionType() + "],进行参数传值校验 ");
			// 下拉框校验
			// BmsVerifyHelper.verifyApplyEntryVO(applyEntryVO);

			// 网点，产品，产品期限可用校验
			Map<String, Object> paras = new HashMap<String, Object>();
			paras.put("orgId", loanBaseEntitySelect.getOwningBranchId());
			paras.put("productCode", applyEntryVO.getApplyInfoVO().getProductCd());
			paras.put("applyLmt", applyEntryVO.getApplyInfoVO().getApplyLmt());
			paras.put("auditLimit", applyEntryVO.getApplyInfoVO().getApplyTerm());
			
			logger.info("111111111111：update  List<BMSOrgLimitChannel> orgLimitChannelList = orgLimitChannelService.listOrgProductLimitByOrgProApp(paras);");

			List<BMSOrgLimitChannel> orgLimitChannelList = orgLimitChannelService.listOrgProductLimitByOrgProApp(paras);
			if (orgLimitChannelList == null || orgLimitChannelList.isEmpty()) {
				response.setRepCode("000003");
				response.setRepMsg("录入借款信息错误：营业网点下，对应网点产品配置不存在!");
				return response;
			}
		}

		// ----------------------------操作start
		// 默认借款状态是申请
		loanBaseEntity.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
		if (applyEntryVO.getOptionModule().equals(EnumConstants.OptionModule.APPLY_ENTRY.getValue()) || applyEntryVO.getOptionModule().equals(EnumConstants.OptionModule.APPLY_MODIFY.getValue())) {
			// 申请录入 录入修改
			if (!loanBaseEntitySelect.getRtfNodeState().equals(EnumConstants.RtfNodeState.SQLR_SAVE.getValue())) {
				throw new BizException(BizErrorCode.RTF_NOT_STATUS_EOERR);
			}

			if (applyEntryVO.getOptionType().equals(EnumConstants.OptionType.SUBMIT.getValue())) {
				// 提交
				loanBaseEntity.setRtfState(EnumConstants.RtfState.LRFH.getValue());
				loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_SUBMIT.getValue());

				loanExtEntity.setApplyEndTime(date);
				loanExtEntity.setAuditStartTime(date);
				
				loanExtEntity.setApplyEndTime(date);
				loanExtEntity.setAuditStartTime(date);

				// 查看申请件的类型
				String applyType = this.getApplyType(applyEntryVO.getApplyInfoVO().getIdNo());
				loanBaseEntity.setApplyType(applyType);
		/*		if(com.ymkj.cms.biz.common.util.StringUtils.isEmpty(loanBaseEntitySelect.getReviewCode())){
					ResEmployeeVO  resEmployeeVO=queryReviwCode(applyEntryVO.getServiceCode(),loanBaseEntitySelect.getLoanNo());
					loanBaseEntity.setReviewCode(resEmployeeVO.getUsercode());// 复核人Code
					loanBaseEntity.setReviewName(resEmployeeVO.getName());// 复核人名称
				}*/
				
				loanBaseEntity.setLogoFlag(0);//录入修改中标黄的单子提交信审,标识去除，借款前台标识,1(黄色)

				
				sysLoanLog.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
				sysLoanLog.setRtfState(EnumConstants.RtfState.SQLR.getValue());
				sysLoanLog.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_SUBMIT.getValue());
				sysLoanLog.setOperationModule(applyEntryVO.getOptionModule());// 操作类型
				sysLoanLog.setOperationType(EnumConstants.OptionType.SUBMIT.getValue());
				sysLoanLog.setRemark(applyEntryVO.getApplyInfoVO().getRemark());

			} else if (applyEntryVO.getOptionType().equals(EnumConstants.OptionType.SAVE.getValue())) {
				// 保存
				loanBaseEntity.setRtfState(EnumConstants.RtfState.SQLR.getValue());
				loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_SAVE.getValue());
			}
		} else if (applyEntryVO.getOptionModule().equals(EnumConstants.OptionModule.APPLY_CHECK.getValue())) {
			// 录入复核
			if (!loanBaseEntitySelect.getRtfNodeState().equals(EnumConstants.RtfNodeState.SQLR_SUBMIT.getValue())) {
				throw new BizException(BizErrorCode.RTF_NOT_STATUS_EOERR);
			}
			if (applyEntryVO.getOptionType().equals(EnumConstants.OptionType.SUBMIT.getValue())) {
				// 提交
				loanBaseEntity.setRtfState(EnumConstants.RtfState.CSFP.getValue());
				loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.CSFPSUBMIT.getValue());
				
				loanBaseEntity.setReviewCode(applyEntryVO.getServiceCode());// 复核人Code
				loanBaseEntity.setReviewName(applyEntryVO.getServiceName());// 复核人名称
				
				loanBaseEntity.setLogoFlag(0);//录入修改中标黄的单子提交信审,标识去除，借款前台标识,1(黄色)

			
				sysLoanLog.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
				sysLoanLog.setRtfState(EnumConstants.RtfState.LRFH.getValue());
				sysLoanLog.setRtfNodeState(EnumConstants.RtfNodeState.CSFPSUBMIT.getValue());
				sysLoanLog.setOperationModule(EnumConstants.OptionModule.APPLY_CHECK.getValue());
				sysLoanLog.setOperationType(EnumConstants.OptionType.SUBMIT.getValue());
				sysLoanLog.setRemark(applyEntryVO.getApplyInfoVO().getRemark());
				loanExtEntity.setAuditEndTime(date);

				loanProductEntity.setIfSuspectCheat(ifSuspectCheat(applyEntryVO));
			} else if (applyEntryVO.getOptionType().equals(EnumConstants.OptionType.SAVE.getValue())) {
				// 保存
				loanBaseEntity.setRtfState(EnumConstants.RtfState.LRFH.getValue());
				loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_SUBMIT.getValue());
			}
		} else if (applyEntryVO.getOptionModule().equals(EnumConstants.OptionModule.BACK_APPLY_MODIFY.getValue())) {
			// 退件箱
			if (loanBaseEntitySelect.getRtfNodeState().equals(EnumConstants.RtfNodeState.LRFH_RETURN.getValue()) || loanBaseEntitySelect.getRtfNodeState().equals(EnumConstants.RtfNodeState.CSFPCANCEL.getValue()) || loanBaseEntitySelect.getRtfNodeState().equals(EnumConstants.RtfNodeState.XSCSRETURN.getValue())
					|| loanBaseEntitySelect.getRtfNodeState().equals(EnumConstants.RtfNodeState.XSZSRETURN.getValue())) {
				if (applyEntryVO.getOptionType().equals(EnumConstants.OptionType.SUBMIT.getValue())) {
					// 提交
					loanBaseEntity.setRtfState(EnumConstants.RtfState.LRFH.getValue());
					loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_SUBMIT.getValue());
					
					loanBaseEntity.setLogoFlag(0);//录入修改中标黄的单子提交信审,标识去除，借款前台标识,1(黄色)


					sysLoanLog.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
					sysLoanLog.setRtfState(EnumConstants.RtfState.SQLR.getValue());
					sysLoanLog.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_SUBMIT.getValue());
					sysLoanLog.setOperationModule(applyEntryVO.getOptionModule());// 操作类型
					sysLoanLog.setOperationType(EnumConstants.OptionType.SUBMIT.getValue());
					sysLoanLog.setRemark(applyEntryVO.getApplyInfoVO().getRemark());
					loanExtEntity.setApplyEndTime(date);
					loanExtEntity.setAuditStartTime(date);
				}
			} else {
				throw new BizException(BizErrorCode.RTF_NOT_STATUS_EOERR);
			}
		}

		/**
		 * 取消阶段处理
		 */
		if (applyEntryVO.getOptionType().equals(EnumConstants.OptionType.CANCEL.getValue())) {
			// 取消
			loanBaseEntity.setRtfState(EnumConstants.LoanStatus.CANCEL.getValue());
		}
		// ----------------------------操作end

		// 更新改变字段比对
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("loanBaseId", applytInfoVO.getLoanBaseId());

		/**
		 * applyLmt 借款信息产品表
		 */
		
		logger.info("111111111111：update  List<LoanProductEntity> loanIdList = loanProductService.listBy(obj);");

		List<LoanProductEntity> loanIdList = loanProductService.listBy(obj);
		if (loanIdList == null) {
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}

		String msg = "";
		loanProductEntity.setId(loanIdList.get(0).getId());
		
		
		logger.info("111111111111：update  LoanProductEntity LoanProductEntitySelect = loanProductService.getById(loanIdList.get(0).getId());");

		LoanProductEntity LoanProductEntitySelect = loanProductService.getById(loanIdList.get(0).getId());

		if (LoanProductEntitySelect.getIfPri() != null) {
			// 如果当前自己是不加急
			if (LoanProductEntitySelect.getIfPri() == 0 && applyEntryVO.getApplyInfoVO().getIfPri() == 1) {
				// 如果是加急件
				String ifPri = ifPri(applyEntryVO.getOwningBranchId());
				// 如果没有加急件。拦截
				if (ifPri.equals("0")) {
					throw new BizException(BizErrorCode.IF_FRI_EOERR);
				}
				loanProductEntity.setIfPri(new Integer(ifPri));
			}
		}

		loanProductEntity.setInitProductCd(applytInfoVO.getInitProductCd());// 原产品编号
		loanProductEntity.setInitProductName(applytInfoVO.getInitProductName());// 原产品名称
		loanProductEntity.setInitApplyLmt(applytInfoVO.getApplyLmt());// 原产品金额
		loanProductEntity.setInitApplyTerm(applytInfoVO.getApplyTerm());// 原产品期限
		loanProductEntity.setLoanNo(LoanProductEntitySelect.getLoanNo());
		Map<String, String> LoanProductEntityMap = BeanAttributeUtils.classOfSrc(loanProductEntity, LoanProductEntitySelect);
		if (LoanProductEntityMap.size() > 0) {
			msg += BeanAttributeUtils.getMassge(LoanProductEntitySelect, loanProductEntity, LoanProductEntityMap);
			loanProductEntity.setModifierId(modifierId);
			loanProductEntity.setModifier(modifier);
			loanProductEntity.setModifiedTime(date);
			
			logger.info("111111111111：update  loanProductService.saveOrUpdate(loanProductEntity);");
			//如果不是录入复核提交,审批产品不允许修改
			if(loanProductEntity.getId() != null&&applyEntryVO.getOptionModule().equals(EnumConstants.OptionModule.APPLY_CHECK.getValue())&&applyEntryVO.getOptionType().equals(EnumConstants.OptionType.SUBMIT.getValue())){
				loanProductEntity.setProductCd(applytInfoVO.getInitProductCd());// 原产品编号
				loanProductEntity.setProductName(applytInfoVO.getInitProductName());// 原产品名称
			}

			loanProductService.saveOrUpdate(loanProductEntity);
		}

		/**
		 * 借款主表
		 */

		loanBaseEntity.setId(applytInfoVO.getLoanBaseId());
		loanBaseEntity.setApplyDate(loanBaseEntitySelect.getApplyDate());
		Map<String, String> loanBaseMap = BeanAttributeUtils.classOfSrc(loanBaseEntity, loanBaseEntitySelect);
		if (loanBaseMap.size() > 0) {
			msg += BeanAttributeUtils.getMassge(loanBaseEntitySelect, loanBaseEntity, loanBaseMap);
			loanBaseEntity.setModifierId(modifierId);
			loanBaseEntity.setModifier(modifier);
			loanBaseEntity.setModifiedTime(date);
			if (loanBaseEntity.getId() != null) {// 修改判断
				/** 普通门店进件 **/
				if ("applyInput".equals(applyEntryVO.getApplyInfoVO().getApplyInputFlag())) {

					// 是否进行过签约改派判断
					if (loanBaseEntitySelect.getContractBranchId() != null && loanBaseEntitySelect.getSignCode() != null) {
						// 不对签约门店进行更新
						loanBaseEntity.setContractBranch(null);
						loanBaseEntity.setContractBranchId(null);
					}
					/** 直通车进件 **/
				} else {
					// 签约门店无修改
					if (loanBaseEntity.getContractBranchId().equals(loanBaseEntitySelect.getContractBranchId())) {
						// 不对签约门店进行更新
						loanBaseEntity.setContractBranch(null);
						loanBaseEntity.setContractBranchId(null);
					}
				}

			}
			logger.info("111111111111：update  loanBaseService.saveOrUpdate(loanBaseEntity);");

			loanBaseService.saveOrUpdate(loanBaseEntity);
		}

		/**
		 * 客户主表
		 */
		appPersonEntity.setId(LoanBaseRela.getPersonId());
		
		logger.info("111111111111：update  APPPersonEntity appPersonSelect = appPersonService.getById(LoanBaseRela.getPersonId());");

		APPPersonEntity appPersonSelect = appPersonService.getById(LoanBaseRela.getPersonId());
		Map<String, String> appPersonMap = BeanAttributeUtils.classOfSrc(appPersonEntity, appPersonSelect);
		if (appPersonMap.size() > 0) {
			msg += BeanAttributeUtils.getMassge(appPersonSelect, appPersonEntity, appPersonMap);
			appPersonEntity.setModifiedTime(date);
			appPersonEntity.setModifier(modifier);
			appPersonEntity.setModifierId(modifierId);
			logger.info("111111111111：update  appPersonService.saveOrUpdate(appPersonEntity);");

			appPersonService.saveOrUpdate(appPersonEntity);
		}

		/**
		 * 扩展表
		 */
		logger.info("111111111111：update  List<LoanExtEntity> loanList = loanExtService.listBy(obj);");

		List<LoanExtEntity> loanList = loanExtService.listBy(obj);
		if (loanList == null) {
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		loanExtEntity.setId(loanList.get(0).getId());
		logger.info("111111111111：update  LoanExtEntity loanExtEntitySelect = loanExtService.getById(loanList.get(0).getId());");

		LoanExtEntity loanExtEntitySelect = loanExtService.getById(loanList.get(0).getId());
		Map<String, String> loanExtEntityMap = BeanAttributeUtils.classOfSrc(loanExtEntity, loanExtEntitySelect);
		if (loanExtEntityMap.size() > 0) {
			msg += BeanAttributeUtils.getMassge(loanExtEntitySelect, loanExtEntity, loanExtEntityMap);
			loanExtEntity.setModifiedTime(date);
			loanExtEntity.setModifier(modifier);
			loanExtEntity.setModifierId(modifierId);
			logger.info("111111111111：update  loanExtService.saveOrUpdate(loanExtEntity);");

			loanExtService.saveOrUpdate(loanExtEntity);
		}

		/**
		 * 个人信息
		 */
		appPersonInfoEntity.setId(LoanBaseRela.getBmsAppPersonInfoId());
		logger.info("111111111111：update  APPPersonInfoEntity appPersonInfoEntitySelect = appPersonInfoService.getById(LoanBaseRela.getBmsAppPersonInfoId());");

		APPPersonInfoEntity appPersonInfoEntitySelect = appPersonInfoService.getById(LoanBaseRela.getBmsAppPersonInfoId());
		Map<String, String> appPersonInfoEntityMap = BeanAttributeUtils.classOfSrc(appPersonInfoEntity, appPersonInfoEntitySelect);
		if (appPersonInfoEntityMap.size() > 0) {
			updateCellRecordState(appPersonInfoEntity,applyEntryVO.getApplyInfoVO().getLoanBaseId());
			msg += BeanAttributeUtils.getMassge(appPersonInfoEntitySelect, appPersonInfoEntity, appPersonInfoEntityMap);
			appPersonInfoEntity.setModifiedTime(date);
			appPersonInfoEntity.setModifier(modifier);
			appPersonInfoEntity.setModifierId(modifierId);
			logger.info("111111111111：update  appPersonInfoService.saveOrUpdate(appPersonInfoEntity);");

			appPersonInfoService.saveOrUpdate(appPersonInfoEntity);
		}

		/**
		 * 房产贷
		 */
		appEstateInfoEntity.setId(LoanBaseRela.getTmAppEstateInfoId());
		logger.info("111111111111：update  APPEstateInfoEntity appEstateInfoEntitySelect = appEstateInfoService.getById(LoanBaseRela.getTmAppEstateInfoId());");

		APPEstateInfoEntity appEstateInfoEntitySelect = appEstateInfoService.getById(LoanBaseRela.getTmAppEstateInfoId());
		Map<String, String> appEstateInfoEntityMap = BeanAttributeUtils.classOfSrc(appEstateInfoEntity, appEstateInfoEntitySelect);
		if (appEstateInfoEntityMap.size() > 0) {
			msg += BeanAttributeUtils.getMassge(appEstateInfoEntitySelect, appEstateInfoEntity, appEstateInfoEntityMap);
			appEstateInfoEntity.setModifiedTime(date);
			appEstateInfoEntity.setModifier(modifier);
			appEstateInfoEntity.setModifierId(modifierId);
			logger.info("111111111111：update  appEstateInfoService.saveOrUpdate(appEstateInfoEntity);");

			appEstateInfoService.saveOrUpdate(appEstateInfoEntity);
		}

		/**
		 * 车辆贷
		 */
		appCarInfoEntity.setId(LoanBaseRela.getTmAppCarInfoId());
		logger.info("111111111111：update  APPCarInfoEntity appCarInfoEntitySelect = appCarInfoService.getById(LoanBaseRela.getTmAppCarInfoId());");

		APPCarInfoEntity appCarInfoEntitySelect = appCarInfoService.getById(LoanBaseRela.getTmAppCarInfoId());
		Map<String, String> appCarInfoEntityMap = BeanAttributeUtils.classOfSrc(appCarInfoEntity, appCarInfoEntitySelect);
		if (appCarInfoEntityMap.size() > 0) {
			msg += BeanAttributeUtils.getMassge(appCarInfoEntitySelect, appCarInfoEntity, appCarInfoEntityMap);
			appCarInfoEntity.setModifiedTime(date);
			appCarInfoEntity.setModifier(modifier);
			appCarInfoEntity.setModifierId(modifierId);
			logger.info("111111111111：update  appCarInfoService.saveOrUpdate(appCarInfoEntity);");

			appCarInfoService.saveOrUpdate(appCarInfoEntity);
		}

		/**
		 * 保单贷
		 */
		appPolicyInfoEntity.setId(LoanBaseRela.getTmAppPolicyInfoId());
		logger.info("111111111111：update  APPPolicyInfoEntity appPolicyInfoEntitySelect = appPolicyInfoService.getById(LoanBaseRela.getTmAppPolicyInfoId());");

		APPPolicyInfoEntity appPolicyInfoEntitySelect = appPolicyInfoService.getById(LoanBaseRela.getTmAppPolicyInfoId());
		Map<String, String> appPolicyInfoEntityMap = BeanAttributeUtils.classOfSrc(appPolicyInfoEntity, appPolicyInfoEntitySelect);
		if (appPolicyInfoEntityMap.size() > 0) {
			msg += BeanAttributeUtils.getMassge(appPolicyInfoEntitySelect, appPolicyInfoEntity, appPolicyInfoEntityMap);
			appPolicyInfoEntity.setModifiedTime(date);
			appPolicyInfoEntity.setModifier(modifier);
			appPolicyInfoEntity.setModifierId(modifierId);
			logger.info("111111111111：update  appPolicyInfoService.saveOrUpdate(appPolicyInfoEntity);");

			appPolicyInfoService.saveOrUpdate(appPolicyInfoEntity);
		}

		/**
		 * 公积金贷
		 */
		appProvidentInfoEntity.setId(LoanBaseRela.getTmAppProvidentInfoId());
		
		logger.info("111111111111：update  APPProvidentInfoEntity appProvidentInfoEntitySelect = appProvidentInfoService.getById(LoanBaseRela.getTmAppProvidentInfoId());");

		APPProvidentInfoEntity appProvidentInfoEntitySelect = appProvidentInfoService.getById(LoanBaseRela.getTmAppProvidentInfoId());
		Map<String, String> appProvidentInfoEntityMap = BeanAttributeUtils.classOfSrc(appProvidentInfoEntity, appProvidentInfoEntitySelect);
		if (appProvidentInfoEntityMap.size() > 0) {
			msg += BeanAttributeUtils.getMassge(appProvidentInfoEntitySelect, appProvidentInfoEntity, appProvidentInfoEntityMap);
			appProvidentInfoEntity.setModifiedTime(date);
			appProvidentInfoEntity.setModifier(modifier);
			appProvidentInfoEntity.setModifierId(modifierId);
			
			logger.info("111111111111：update  appProvidentInfoService.saveOrUpdate(appProvidentInfoEntity);");

			appProvidentInfoService.saveOrUpdate(appProvidentInfoEntity);
		}

		/**
		 * 卡友贷贷
		 */
		appCardLoanInfoEntity.setId(LoanBaseRela.getTmAppCardLoanInfoId());
		logger.info("111111111111：update  APPCardLoanInfoEntity appCardLoanInfoEntitySelect = appCardLoanInfoService.getById(LoanBaseRela.getTmAppCardLoanInfoId());");

		APPCardLoanInfoEntity appCardLoanInfoEntitySelect = appCardLoanInfoService.getById(LoanBaseRela.getTmAppCardLoanInfoId());
		Map<String, String> appCardLoanInfoEntityMap = BeanAttributeUtils.classOfSrc(appCardLoanInfoEntity, appCardLoanInfoEntitySelect);
		if (appCardLoanInfoEntityMap.size() > 0) {
			msg += BeanAttributeUtils.getMassge(appCardLoanInfoEntitySelect, appCardLoanInfoEntity, appCardLoanInfoEntityMap);
			appCardLoanInfoEntity.setModifiedTime(date);
			appCardLoanInfoEntity.setModifier(modifier);
			appCardLoanInfoEntity.setModifierId(modifierId);
			logger.info("111111111111：update appCardLoanInfoService.saveOrUpdate(appCardLoanInfoEntity);");

			appCardLoanInfoService.saveOrUpdate(appCardLoanInfoEntity);
		}

//		/**
//		 * 随薪贷
//		 */
//		appSalaryLoanInfoEntity.setId(LoanBaseRela.getTmAppSalaryLoanInfoId());
//		APPSalaryLoanInfoEntity appSalaryLoanInfoEntitySelect = appSalaryInfoService.getById(LoanBaseRela.getTmAppSalaryLoanInfoId());
//		Map<String, String> appSalaryLoanInfoEntityMap = BeanAttributeUtils.classOfSrc(appSalaryLoanInfoEntity, appSalaryLoanInfoEntitySelect);
//		if (appSalaryLoanInfoEntityMap.size() > 0) {
//			msg += BeanAttributeUtils.getMassge(appSalaryLoanInfoEntitySelect, appSalaryLoanInfoEntity, appSalaryLoanInfoEntityMap);
//			appSalaryLoanInfoEntity.setModifiedTime(date);
//			appSalaryLoanInfoEntity.setModifier(modifier);
//			appSalaryLoanInfoEntity.setModifierId(modifierId);
//			appSalaryInfoService.saveOrUpdate(appSalaryLoanInfoEntity);
//		}

		/**
		 * 淘宝达人贷
		 */
		appMasterLoanInfoEntity.setId(LoanBaseRela.getTmAppMasterLoanInfoId());
		logger.info("111111111111：update APPMasterLoanInfoEntity appMasterLoanInfoEntitySelect = appMasterLoanInfoService.getById(LoanBaseRela.getTmAppMasterLoanInfoId());");

		APPMasterLoanInfoEntity appMasterLoanInfoEntitySelect = appMasterLoanInfoService.getById(LoanBaseRela.getTmAppMasterLoanInfoId());
		Map<String, String> appMasterLoanInfoEntityMap = BeanAttributeUtils.classOfSrc(appMasterLoanInfoEntity, appMasterLoanInfoEntitySelect);
		if (appMasterLoanInfoEntityMap.size() > 0) {
			msg += BeanAttributeUtils.getMassge(appMasterLoanInfoEntitySelect, appMasterLoanInfoEntity, appMasterLoanInfoEntityMap);
			appMasterLoanInfoEntity.setModifiedTime(date);
			appMasterLoanInfoEntity.setModifier(modifier);
			appMasterLoanInfoEntity.setModifierId(modifierId);
			
			logger.info("111111111111：update appMasterLoanInfoService.saveOrUpdate(appMasterLoanInfoEntity);");

			appMasterLoanInfoService.saveOrUpdate(appMasterLoanInfoEntity);
		}

		/**
		 * 淘宝商户贷
		 */
		appMerchantLoanInfoEntity.setId(LoanBaseRela.getTmAppMerchanLoanInfoId());
		logger.info("111111111111：update APPMerchantLoanInfoEntity appMerchantLoanInfoEntitySelect = appMerchantLoanInfoService.getById(LoanBaseRela.getTmAppMerchanLoanInfoId());");

		APPMerchantLoanInfoEntity appMerchantLoanInfoEntitySelect = appMerchantLoanInfoService.getById(LoanBaseRela.getTmAppMerchanLoanInfoId());
		Map<String, String> appMerchantLoanInfoEntityMap = BeanAttributeUtils.classOfSrc(appMerchantLoanInfoEntity, appMerchantLoanInfoEntitySelect);
		if (appMerchantLoanInfoEntityMap.size() > 0) {
			msg += BeanAttributeUtils.getMassge(appMerchantLoanInfoEntitySelect, appMerchantLoanInfoEntity, appMerchantLoanInfoEntityMap);
			appMerchantLoanInfoEntity.setModifiedTime(date);
			appMerchantLoanInfoEntity.setModifier(modifier);
			appMerchantLoanInfoEntity.setModifierId(modifierId);
			logger.info("111111111111：update appMerchantLoanInfoService.saveOrUpdate(appMerchantLoanInfoEntity);");

			appMerchantLoanInfoService.saveOrUpdate(appMerchantLoanInfoEntity);
		}

		/**
		 * 联系人信息
		 */
		Map<String, Object> paramMapCont = new HashMap<String, Object>();
		paramMapCont.put("loanNo", loanIdList.get(0).getLoanNo());
		
		//删除录入前台去掉的联系人
		Map<String,Object> mapDelteleContact=new HashMap<String,Object>();
		mapDelteleContact.put("loanNo", loanIdList.get(0).getLoanNo());
		mapDelteleContact.put("appContactInfoEntityList", appContactInfoEntityList);
		
		logger.info("111111111111：update appContactInfoService.deleteByLoanAndNumAll(mapDelteleContact);");

		appContactInfoService.deleteByLoanAndNumAll(mapDelteleContact);
		
		
		for (APPContactInfoEntity contactInfoEntity : appContactInfoEntityList) {
			// 查询是否有该数据
			paramMapCont.put("sequenceNum", contactInfoEntity.getSequenceNum());
			logger.info("111111111111：update APPContactInfoEntity query = appContactInfoService.getBy(paramMapCont);");

			APPContactInfoEntity query = appContactInfoService.getBy(paramMapCont);
			if (query == null) {
				contactInfoEntity.setId(null);
				contactInfoEntity.setOrg(EnumConstants.BMS_Org);
				contactInfoEntity.setLoanNo(loanIdList.get(0).getLoanNo());
				contactInfoEntity.setLoanBaseId(applytInfoVO.getLoanBaseId());
				contactInfoEntity.setPersonId(LoanBaseRela.getPersonId());
				logger.info("111111111111：update appContactInfoService.saveOrUpdate(contactInfoEntity);");

				appContactInfoService.saveOrUpdate(contactInfoEntity);
			} else {
				contactInfoEntity.setId(query.getId());
				logger.info("111111111111：update APPContactInfoEntity contactInfoEntitySelect = appContactInfoService.getById(query.getId());");

				APPContactInfoEntity contactInfoEntitySelect = appContactInfoService.getById(query.getId());
				Map<String, String> contactInfoEntityMap = BeanAttributeUtils.classOfSrc(contactInfoEntity, contactInfoEntitySelect);
				if (contactInfoEntityMap.size() > 0) {
					msg += BeanAttributeUtils.getMassge(contactInfoEntitySelect, contactInfoEntity, contactInfoEntityMap);
					contactInfoEntity.setModifiedTime(date);
					contactInfoEntity.setModifier(modifier);
					contactInfoEntity.setModifierId(modifierId);
					logger.info("111111111111：update appContactInfoService.saveOrUpdate(contactInfoEntity);");

					appContactInfoService.saveOrUpdate(contactInfoEntity);
				}

			}
		}

		// 复核模块，并且提交
		if (applyEntryVO.getOptionModule().equals(EnumConstants.OptionModule.APPLY_CHECK.getValue()) && applyEntryVO.getOptionType().equals(EnumConstants.OptionType.SUBMIT.getValue())) {
			// 录入复核 提交 新增审批
			logger.info("复核，提交模块--校验数据！是否第一次提交复核[" + (loanAuditEntitySelect == null) + "]");
			if (loanAuditEntitySelect == null) {
				LoanAuditEntity loanAuditEntity = new LoanAuditEntity();
				loanAuditEntity.setLoan_base_id(applytInfoVO.getLoanBaseId());
				loanAuditEntity.setLoanNo(loanIdList.get(0).getLoanNo());
				loanAuditEntity.setCheck_node_state(EnumConstants.ChcekNodeState.NOCHECK.getValue());
				loanAuditEntity.setCreated_time(date);
				loanAuditEntity.setCreator(modifier);
				loanAuditEntity.setCreator_id(modifierId);
				logger.info("111111111111：update iLoanAuditService.insert(loanAuditEntity);");
				iLoanAuditService.insert(loanAuditEntity);
			} else {
				// 修改
				LoanAuditEntity loanAuditEntity = loanAuditEntitySelect;
				loanAuditEntity.setCheck_node_state(EnumConstants.ChcekNodeState.NOCHECK.getValue());
				loanAuditEntity.setModified_time(date);
				loanAuditEntity.setModifier(modifier);
				loanAuditEntity.setModifier_id(modifierId);
				loanAuditEntity.setIfCheckReturn(null);
				loanAuditEntity.setIfLastCheckReturn("2");
				logger.info("111111111111：update iLoanAuditService.update(loanAuditEntity);");

				iLoanAuditService.update(loanAuditEntity);

				LoanBaseEntity lb = new LoanBaseEntity();
				lb.setId(applytInfoVO.getLoanBaseId());
				lb.setIfNewLoanNo(new Long("0"));
				logger.info("111111111111：update loanBaseService.saveOrUpdate(lb);");

				loanBaseService.saveOrUpdate(lb);
			}

			// 录入复核提交绑定 人行征信报告
			PersonHistoryLoanVO personHistoryLoanVO = new PersonHistoryLoanVO();
			personHistoryLoanVO.setSysCode(EnumConstants.BMS_SYSCODE);
			personHistoryLoanVO.setName(loanBaseEntity.getName());
			personHistoryLoanVO.setIdNo(loanBaseEntity.getIdNo());
			personHistoryLoanVO.setLoanNo(loanIdList.get(0).getLoanNo());
			String fixedCreditReport = fixedCreditReportByStatus(personHistoryLoanVO);
			APPPersonInfoEntity reportEntitys = new APPPersonInfoEntity();
			reportEntitys.setId(LoanBaseRela.getBmsAppPersonInfoId());
			reportEntitys.setFlag("REP");
			if (fixedCreditReport != null) {
				if(fixedCreditReport.equals("overdue")){
					reportEntitys.setReportId(null);
					reportEntitys.setIfReportId("Y");
				}else{
					reportEntitys.setReportId(Long.parseLong(fixedCreditReport));
				}

				//判断标准  目前数据库里面征信报告id为空   或者  查询征信报告id与数据库查询id不相等
				String reportId = ObjectUtils.toString(appPersonInfoEntitySelect.getReportId());
				logger.info("报告ID比较：reportId="+reportId+"===reportId2="+reportEntitys.getReportId());
				if(org.apache.commons.lang.StringUtils.isBlank(reportId) || !reportId.equals(ObjectUtils.toString(reportEntitys.getReportId()))){
					reportEntitys.setIfReportId("Y");
				}
			}/*else{
				reportEntitys.setReportId(null);
				reportEntitys.setIfReportId("Y");
			}*/
			reportEntitys.setModifiedTime(date);
			reportEntitys.setModifier(modifier);
			reportEntitys.setModifierId(modifierId);
			logger.info("111111111111：update appPersonInfoService.saveOrUpdate(reportEntitys);");

			appPersonInfoService.saveOrUpdate(reportEntitys);
			
			
			
			//查看上海资信报告开关
			Map<String, Object> SHZparamMap = new HashMap<String, Object>();
			SHZparamMap.put("code", "LOAN_SHZX_VALUE");
			logger.info("111111111111：update BMSTmParameter loanBase = (BMSTmParameter) ibmsTmParameterService.getBy(SHZparamMap, queryByCode);");

			BMSTmParameter loanBase = (BMSTmParameter) ibmsTmParameterService.getBy(SHZparamMap, "queryByCode");
			//判断调用益博睿开关 0开 1关
			logger.info("调用上海资信审开关：0开 1关--" + gson.toJson(loanBase));
			if(loanBase != null && loanBase.getParameterValue() !=null && loanBase.getParameterValue().equals("0")){
				// 录入复核提交绑定 上海资信报告
				String fixedShangHaiCreditReport = fixedShangHaiCreditReport(personHistoryLoanVO);
				APPPersonInfoEntity reportEntity = new APPPersonInfoEntity();
				reportEntity.setId(LoanBaseRela.getBmsAppPersonInfoId());
				reportEntitys.setFlag("NFC");
				if (fixedShangHaiCreditReport != null) {
					reportEntity.setNfcsId(new Long(fixedShangHaiCreditReport));
				}else{
					reportEntity.setNfcsId(null);
				}
				reportEntity.setModifiedTime(date);
				reportEntity.setModifier(modifier);
				reportEntity.setModifierId(modifierId);
				logger.info("111111111111：update appPersonInfoService.saveOrUpdate(reportEntity);");

				appPersonInfoService.saveOrUpdate(reportEntity);
			}
			// 算话评分
			Map<String, Object> suanHuaAntifraud = getSuanHuaAntifraud(loanBaseEntitySelect.getLoanNo(), applyEntryVO);
			if (suanHuaAntifraud != null) {
				LoanExtEntity updateLoanExtEntity = new LoanExtEntity();
				updateLoanExtEntity.setAntiFraudScore(suanHuaAntifraud.get("appRst").toString());// 反欺诈评分
				updateLoanExtEntity.setAntiFraudWarning(suanHuaAntifraud.get("stanFrdLevel").toString());// 反欺诈预警
				updateLoanExtEntity.setAntiRiskRate(suanHuaAntifraud.get("stanRiskRate").toString());// 欺诈风险评估
				updateLoanExtEntity.setId(loanExtEntitySelect.getId());
				updateLoanExtEntity.setModifiedTime(date);
				updateLoanExtEntity.setModifier(modifier);
				updateLoanExtEntity.setModifierId(modifierId);
				logger.info("111111111111：update loanExtService.saveOrUpdate(updateLoanExtEntity);");

				loanExtService.saveOrUpdate(updateLoanExtEntity);
			}

			// 添加快照版本
			auditDifferences(loanIdList.get(0).getLoanNo(), EnumConstants.differences.peview_submit.getValue());
		}

		msg = DateUtil.defaultFormatDate(date) + "由用户" + modifier + "操作：" + msg;
		// 操作日志
		if (applyEntryVO.getOptionType().equals(EnumConstants.OptionType.SUBMIT.getValue())) {
			sysLoanLog.setLoanBaseId(applytInfoVO.getLoanBaseId());
			sysLoanLog.setLoanNo(loanIdList.get(0).getLoanNo());
			sysLoanLog.setOperator(applyEntryVO.getServiceName());
			sysLoanLog.setOperatorCode(applyEntryVO.getServiceCode());
			sysLoanLog.setOperationTime(date);
			sysLoanLog.setRemark(applyEntryVO.getApplyInfoVO().getRemark());
			logger.info("111111111111：update sysLoanLogService.saveOrUpdate(sysLoanLog);");

			sysLoanLogService.saveOrUpdate(sysLoanLog);
		}

		/**
		 * 变更日志
		 */
		if (applyEntryVO.getOptionModule().equals(EnumConstants.OptionModule.APPLY_CHECK.getValue())) {
			BmsLoanChangeLogEntity bmsLoanChangeLogEntity = new BmsLoanChangeLogEntity();
			bmsLoanChangeLogEntity.setLoanBaseId(applytInfoVO.getLoanBaseId().intValue());
			bmsLoanChangeLogEntity.setLoanNo(loanIdList.get(0).getLoanNo());
			bmsLoanChangeLogEntity.setOperationModule(EnumConstants.RtfState.LRFH.getValue());
			// bmsLoanChangeLogEntity.setOperator(modifier);
			// bmsLoanChangeLogEntity.setOperatorCode(modifierId + "");
			bmsLoanChangeLogEntity.setOperator(applyEntryVO.getModifier());
			bmsLoanChangeLogEntity.setOperatorCode(applyEntryVO.getServiceCode());
			bmsLoanChangeLogEntity.setOperationStart(date);
			bmsLoanChangeLogEntity.setContent(msg);
			bmsLoanChangeLogEntity.setCreatedTime(date);
			logger.info("111111111111：update loanChangeLogService.saveOrUpdate(bmsLoanChangeLogEntity);");

			loanChangeLogService.saveOrUpdate(bmsLoanChangeLogEntity);
			logger.info("111111111111：update 结束");

		}

		ReqApplyEntryVO vo = new ReqApplyEntryVO();
		vo.setLoanBaseId(applytInfoVO.getLoanBaseId());
		vo.setLoanNo(loanIdList.get(0).getLoanNo());
		response.setData(vo);
		return response;
	}

	/**
	 * 查看当前用户的贷款状态 申请类型 NEW 新申请 TOPUP 追加贷款 RELOAN 结清再贷
	 * 
	 * @param idNo
	 *            取消 结清 逾期 正常
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getApplyType(String idNo) {
		String ApplyType = "";
		try {
			ApplyType = "NEW";
			// 查看是否
			Map<String, Object> httpParamMap = new HashMap<String, Object>();
			httpParamMap.put("idnum", idNo);
			System.out.println("===========>调用核心查询数据该用户是否有借款信息:" + gson.toJson(httpParamMap));
			HttpResponse queryLoan = coreHttpService.loanStatus(httpParamMap);
			Map<String, Object> contentMap = JsonUtils.decode(queryLoan.getContent(), Map.class);
			if (contentMap.get("code").equals("000000")) {
				if (contentMap.get("msg").equals("该用户不在系统中")) {
					return ApplyType;
				}

				List<Map<String, String>> resQueryLoanVo = (List<Map<String, String>>) contentMap.get("loan");
				if (resQueryLoanVo != null && resQueryLoanVo.size() > 0) {
					for (Map<String, String> loanBaseVo : resQueryLoanVo) {
						if ("正常".equals(loanBaseVo.get("loanState")) || "逾期".equals(loanBaseVo.get("loanState")) || "预结清".equals(loanBaseVo.get("loanState"))) {
							ApplyType = "TOPUP";
							return ApplyType;
						} else if ("结清".equals(loanBaseVo.get("loanState"))) {
							ApplyType = "RELOAN";
						}
					}
				}
			}
		} catch (Exception e) {
			BmsLogger.info(e.getMessage());
			return ApplyType;
		}
		return ApplyType;
	}

	/**
	 * 查询当前用户是否疑似欺诈
	 * 
	 * @return
	 */
	private Integer ifSuspectCheat(ApplyEntryVO applyEntryVO) {

		Response<String> identifyingAntiFraud = new Response<String>();

		try {
			String cellPhone = applyEntryVO.getBasicInfoVO().getPersonInfoVO().getCellphone();
			if (applyEntryVO.getBasicInfoVO().getPersonInfoVO().getCellphoneSec() != null && !applyEntryVO.getBasicInfoVO().getPersonInfoVO().getCellphoneSec().equals("")) {
				cellPhone += "," + applyEntryVO.getBasicInfoVO().getPersonInfoVO().getCellphoneSec();
			}

			String corpPhone = applyEntryVO.getBasicInfoVO().getWorkInfoVO().getCorpPhone();
			if (applyEntryVO.getBasicInfoVO().getWorkInfoVO().getCorpPhoneSec() != null && !applyEntryVO.getBasicInfoVO().getWorkInfoVO().getCorpPhoneSec().equals("")) {
				corpPhone += "," + applyEntryVO.getBasicInfoVO().getWorkInfoVO().getCorpPhoneSec();
			}

			for (ContactInfoVO contactInfoVO : applyEntryVO.getContactInfoVOList()) {
				cellPhone += "," + contactInfoVO.getContactCellPhone();
				if (contactInfoVO.getContactCellPhone_1() != null && !contactInfoVO.getContactCellPhone_1().equals("")) {
					cellPhone += "," + contactInfoVO.getContactCellPhone_1();
				}

				if (contactInfoVO.getContactCorpPhone() != null && !contactInfoVO.getContactCorpPhone().equals("")) {
					corpPhone += "," + contactInfoVO.getContactCorpPhone();
				}

				if (contactInfoVO.getContactCorpPhone_1() != null && !contactInfoVO.getContactCorpPhone_1().equals("")) {
					corpPhone += "," + contactInfoVO.getContactCorpPhone_1();
				}
			}
			IdentifyingAntiFraudReqVO identifyingAntiFraudReq = new IdentifyingAntiFraudReqVO();
			identifyingAntiFraudReq.setName(applyEntryVO.getApplyInfoVO().getName());// 姓名
			identifyingAntiFraudReq.setIdNo(applyEntryVO.getApplyInfoVO().getIdNo());// 证件号
			identifyingAntiFraudReq.setCellPhone(cellPhone);// 手机号码
			identifyingAntiFraudReq.setCorpName(applyEntryVO.getBasicInfoVO().getWorkInfoVO().getCorpName());// 公司名称
			identifyingAntiFraudReq.setCorpPhone(corpPhone);// 公司电话
			identifyingAntiFraudReq.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
			BmsLogger.info("调用行为库入参：" + gson.toJson(identifyingAntiFraudReq));
			identifyingAntiFraud = iInternalMatchingExecuter.identifyingAntiFraud(identifyingAntiFraudReq);
			BmsLogger.info("调用行为库出参：" + gson.toJson(identifyingAntiFraud));
			if (identifyingAntiFraud.getData().equals("1")) {
				BmsLogger.info("返回结果，用户[" + applyEntryVO.getApplyInfoVO().getName() + "][" + applyEntryVO.getApplyInfoVO().getIdNo() + "]疑似欺诈");
				return ParametersType.ifSuspectCheat._1;
			}
		} catch (Exception e) {
			BmsLogger.info("[" + applyEntryVO.getApplyInfoVO().getName() + "][" + applyEntryVO.getApplyInfoVO().getIdNo() + "]查询联欺诈信息失败，失败原因：" + e.getMessage() + ",接口返回原因:" + identifyingAntiFraud.getRepMsg());
			return ParametersType.ifSuspectCheat._0;
		}
		return ParametersType.ifSuspectCheat._0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response<ReqApplyEntryVO> auditUpdate(AuditAmendEntryVO applyEntryVO) {

		boolean isVerify = true;
		if (org.apache.commons.lang.StringUtils.isBlank(applyEntryVO.getVersion())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "version" });
		}

		// 更新员工id
		if (applyEntryVO.getModifierId() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "modifierId" });
		}
		// 更新员工姓名
		if (org.apache.commons.lang.StringUtils.isBlank(applyEntryVO.getModifier())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "modifier" });
		}

		if (org.apache.commons.lang.StringUtils.isBlank(applyEntryVO.getLoanNo())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "loanNo" });
		}

		if (applyEntryVO.getLoanBaseId() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "loanBaseId" });
		}

		if (org.apache.commons.lang.StringUtils.isBlank(applyEntryVO.getProductCd())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "productCd" });
		}

		String version = applyEntryVO.getVersion();

		Map<String, Object> versionMap = new HashMap<String, Object>();
		versionMap.put("loanNo", applyEntryVO.getLoanNo());
		versionMap.put("version", version);
		long updateVersion = loanBaseService.updateVersion(versionMap);
		if (updateVersion <= 0) {
			throw new BizException(BizErrorCode.VERSION_EOERR);
		}

		// 获取配置总表
		LoanBaseRelaEntity LoanBaseRela = loanBaseRelaService.getByBaseId(applyEntryVO.getLoanBaseId());
		if (LoanBaseRela == null) {
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}

		LoanBaseEntity loanBaseEntity = loanBaseService.getById(applyEntryVO.getLoanBaseId());
		if (loanBaseEntity == null) {
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}

		if (!applyEntryVO.getLoanNo().equals(loanBaseEntity.getLoanNo())) {
			throw new BizException(CoreErrorCode.VALIDATE_ILLEGAL_MULTIVALUE, new Object[] { applyEntryVO.getLoanNo(), loanBaseEntity.getLoanNo() });
		}

		// 构造实体类
		APPCardLoanInfoEntity appCardLoanInfoEntity = new APPCardLoanInfoEntity();
		APPCarInfoEntity appCarInfoEntity = new APPCarInfoEntity();
		List<APPContactInfoEntity> appContactInfoEntityList = new ArrayList<APPContactInfoEntity>();
		APPEstateInfoEntity appEstateInfoEntity = new APPEstateInfoEntity();
		APPMasterLoanInfoEntity appMasterLoanInfoEntity = new APPMasterLoanInfoEntity();
		APPMerchantLoanInfoEntity appMerchantLoanInfoEntity = new APPMerchantLoanInfoEntity();
		APPPersonInfoEntity appPersonInfoEntity = new APPPersonInfoEntity();
		APPPolicyInfoEntity appPolicyInfoEntity = new APPPolicyInfoEntity();
		APPProvidentInfoEntity appProvidentInfoEntity = new APPProvidentInfoEntity();
		APPSalaryLoanInfoEntity appSalaryLoanInfoEntity = new APPSalaryLoanInfoEntity();
		// 获取参数值
		BasicInfoVO basicInfoVO = applyEntryVO.getBasicInfoVO() != null ? applyEntryVO.getBasicInfoVO() : new BasicInfoVO();
		AssetsInfoVO assetsInfoVO = applyEntryVO.getAssetsInfoVO() != null ? applyEntryVO.getAssetsInfoVO() : new AssetsInfoVO();
		List<ContactInfoVO> contactInfoVOList = applyEntryVO.getContactInfoVOList() != null ? applyEntryVO.getContactInfoVOList() : new ArrayList<ContactInfoVO>();
		// 资产信息
		EstateInfoVO estateInfoVO = assetsInfoVO.getEstateInfoVO() != null ? assetsInfoVO.getEstateInfoVO() : new EstateInfoVO();
		CarInfoVO carInfoVO = assetsInfoVO.getCarInfoVO() != null ? assetsInfoVO.getCarInfoVO() : new CarInfoVO();
		PolicyInfoVO policyInfoVO = assetsInfoVO.getPolicyInfoVO() != null ? assetsInfoVO.getPolicyInfoVO() : new PolicyInfoVO();
		ProvidentInfoVO providentInfoVO = assetsInfoVO.getProvidentInfoVO() != null ? assetsInfoVO.getProvidentInfoVO() : new ProvidentInfoVO();
		CardLoanInfoVO cardLoanInfoVO = assetsInfoVO.getCardLoanInfoVO() != null ? assetsInfoVO.getCardLoanInfoVO() : new CardLoanInfoVO();
		SalaryLoanInfoVO salaryLoanInfoVO = assetsInfoVO.getSalaryLoanInfoVO() != null ? assetsInfoVO.getSalaryLoanInfoVO() : new SalaryLoanInfoVO();
		MasterLoanInfoVO masterLoanInfoVO = assetsInfoVO.getMasterLoanInfoVO() != null ? assetsInfoVO.getMasterLoanInfoVO() : new MasterLoanInfoVO();
		MerchantLoanInfoVO merchantLoanInfoVO = assetsInfoVO.getMerchantLoanInfoVO() != null ? assetsInfoVO.getMerchantLoanInfoVO() : new MerchantLoanInfoVO();
		// 基本信息
		PersonInfoVO personInfoVO = basicInfoVO.getPersonInfoVO() != null ? basicInfoVO.getPersonInfoVO() : new PersonInfoVO();
		PrivateOwnerInfoVO privateOwnerInfoVO = basicInfoVO.getPrivateOwnerInfoVO() != null ? basicInfoVO.getPrivateOwnerInfoVO() : new PrivateOwnerInfoVO();
		WorkInfoVO workInfoVO = basicInfoVO.getWorkInfoVO() != null ? basicInfoVO.getWorkInfoVO() : new WorkInfoVO();

		// 实体类赋值
		// //借款人信息 BMS_APP_PERSON
		// BeanUtils.copyProperties(personInfoVO, appPersonEntity);
		// 主办申请人 BMS_APP_PERSON_INFO
		BeanUtils.copyProperties(personInfoVO, appPersonInfoEntity);
		// 房产信息 TM_APP_ESTATE_INFO
		BeanUtils.copyProperties(estateInfoVO, appEstateInfoEntity);
		// 车辆信息 TM_APP_CAR_INFO
		BeanUtils.copyProperties(carInfoVO, appCarInfoEntity);
		// 保单信息 TM_APP_POLICY_INFO
		BeanUtils.copyProperties(policyInfoVO, appPolicyInfoEntity);
		// 公积金信息 TM_APP_POLICY_INFO
		BeanUtils.copyProperties(providentInfoVO, appProvidentInfoEntity);
		// 卡友贷信息 TM_APP_CARD_LOAN_INFO
		BeanUtils.copyProperties(cardLoanInfoVO, appCardLoanInfoEntity);
		// 随薪贷信息 TM_APP_SALARY_LOAN_INFO
		BeanUtils.copyProperties(salaryLoanInfoVO, appSalaryLoanInfoEntity);
		// 网购达人贷 TM_APP_MASTER_LOAN_INFO
		BeanUtils.copyProperties(masterLoanInfoVO, appMasterLoanInfoEntity);
		// 淘宝商户贷信息 TM_APP_MERCHANT_LOAN_INFO
		BeanUtils.copyProperties(merchantLoanInfoVO, appMerchantLoanInfoEntity);
		// 工作信息 BMS_APP_PERSON_INFO
		BeanUtils.copyProperties(workInfoVO, appPersonInfoEntity);
		// 私营业主信息 BMS_APP_PERSON_INFO
		BeanUtils.copyProperties(privateOwnerInfoVO, appPersonInfoEntity);
		// 联系人信息 TM_APP_CONTACT_INFO
		for (int i = 0; i < contactInfoVOList.size(); i++) {
			APPContactInfoEntity contactInfoEntity = new APPContactInfoEntity();
			BeanUtils.copyProperties(contactInfoVOList.get(i), contactInfoEntity);
			appContactInfoEntityList.add(contactInfoEntity);
		}

		// 基本参数获取
		Long modifierId = applyEntryVO.getModifierId();
		String modifier = applyEntryVO.getModifier();

		Date date = new Date();

		if (isVerify) {
			// 参数 规则验证

			// 个人基本信息 工作信息
			Response<ReqApplyEntryVO> responseAppPersonInfo = Validate.getInstance().validate(appPersonInfoEntity);
			if (!responseAppPersonInfo.isSuccess()) {
				logger.info("appPersonInfoEntity 参数 规则验证: 校验不通过");
				return responseAppPersonInfo;
			}else{
				if(appPersonInfoEntity.getHouseType().equals(ParametersType.HouseType._00004)){
					//住宅类型选择“租房”时必填  并且大于0
					if(appPersonInfoEntity.getHouseRent() == null || appPersonInfoEntity.getHouseRent().intValue() < 0){
						throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "当前住宅类型是租房，租金必须大于0" });
					}
				}
				//QQ、微信、电子邮箱至少填写一项
				if(appPersonInfoEntity.getQqNum() ==null && appPersonInfoEntity.getEmail() == null && appPersonInfoEntity.getWeChatNum() == null){
					throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "QQ、微信、电子邮箱至少填写一项" });
				}
				
			}
			
			//自动填充 职业
			//appPersonInfoEntity.setOccupation(getOccupation(appPersonInfoEntity.getCusWorkType()));

			Response<ReqApplyEntryVO> responPrivateOwnerInfo = new Response<ReqApplyEntryVO>();
			// 私营业主信息 客户工作类型选“私营业主”时必填，选择“自雇人士”时选填
			if (!StringUtils.isEmpty(appPersonInfoEntity.getCusWorkType()) && appPersonInfoEntity.getCusWorkType().equals(EnumConstants.WorkType.PrivateOwners.getValue())) {

				PrivateOwnerInfoEntity privateOwnerInfoEntity = new PrivateOwnerInfoEntity();
				BeanUtils.copyProperties(appPersonInfoEntity, privateOwnerInfoEntity);
				responPrivateOwnerInfo = Validate.getInstance().validate(privateOwnerInfoEntity);
			}
			if (!responPrivateOwnerInfo.isSuccess()) {
				logger.info("responPrivateOwnerInfo 参数 规则验证: 校验不通过");
				return responPrivateOwnerInfo;
			}

			// 联系人
			if (appContactInfoEntityList.size() < 5) {
				logger.info("responseContactInfoEntityList.size<5 参数 规则验证: 校验不通过");
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "contactInfoVOList个数不满足" });
			}
			for (APPContactInfoEntity appContactInfoEntity : appContactInfoEntityList) {
				Response<ReqApplyEntryVO> responseContactInfoEntityList = Validate.getInstance().validate(appContactInfoEntity);
				if (!responseContactInfoEntityList.isSuccess()) {
					logger.info("responseContactInfoEntityList 参数 规则验证: 校验不通过");
					return responseContactInfoEntityList;
				}
				//联系人新增配置选择
				if(appContactInfoEntity.getContactRelation() != null && appContactInfoEntity.getContactRelation().equals(ParametersType.Relationship._00013) ){
					// 如果婚姻状况是  “已婚”、“再婚”、“复婚”  需要校验配偶身份证
					if(appPersonInfoEntity.getMaritalStatus() != null && (
							appPersonInfoEntity.getMaritalStatus().equals(ParametersType.MaritalStatus._0002) ||
							appPersonInfoEntity.getMaritalStatus().equals(ParametersType.MaritalStatus._0006) ||
							appPersonInfoEntity.getMaritalStatus().equals(ParametersType.MaritalStatus._0007)
							)){
						if(StringUtils.isEmpty(appContactInfoEntity.getContactIdNo())){
							logger.info("联系人为配偶，身份证号不为空!");
							throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "联系人为配偶，身份证号不为空!" });
						}
					}
				}
			}

			/** 获取商品资产配置表 */

			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("productCode", new Long(applyEntryVO.getProductCd()));
			List<BMSEnumCode> ecnumCodeList = demoService.getProducAssetsInfoByCode(paraMap);
			if (ecnumCodeList != null && ecnumCodeList.size() > 0) {
				String model;
				for (int i = 0; i < ecnumCodeList.size(); i++) {
					model = ecnumCodeList.get(i).getCode();
//					if (model.equals(EnumConstants.productModule.SALARYLOAN.getValue())) {// 随薪贷
//						// 随薪贷
//						Response<ReqApplyEntryVO> responseSalaryLoanInfoEntity = Validate.getInstance().validate(appSalaryLoanInfoEntity);
//						if (!responseSalaryLoanInfoEntity.isSuccess()) {
//							logger.info("appSalaryLoanInfoEntity 参数 规则验证: 校验不通过");
//							return responseSalaryLoanInfoEntity;
//						}
//					} else 
					if (model.equals(EnumConstants.productModule.ESTATE.getValue())) {// 房产贷
						// 房产信息
						Response<ReqApplyEntryVO> responseEstateInfoEntity = Validate.getInstance().validate(appEstateInfoEntity);
						if (!responseEstateInfoEntity.isSuccess()) {
							logger.info("appEstateInfoEntity 参数 规则验证: 校验不通过");
							return responseEstateInfoEntity;
						}
						// 当 房贷情况 还款中时 房贷金额，月供，已还期数 不能为空
						if (appEstateInfoEntity.getEstateLoan().equals("ING")) {
//							if (appEstateInfoEntity.getEstateLoanAmt() == null) {
//								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "estateLoanAmt 房贷金额" });
//							}
							if (appEstateInfoEntity.getMonthPaymentAmt() == null) {
								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "monthPaymentAmt  月供" });
							}
							if(appEstateInfoEntity.getEstateLoanIssueDate() == null){
								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "estateLoanIssueDate  房贷发放年月" });
							}
							
//							if (appEstateInfoEntity.getHasRepaymentNum() == null) {
//								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "hasRepaymentNum  已还期数" });
//							}
						}else{
							if(appEstateInfoEntity.getIfMe() == null){
								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "房产信息模块   单据户名为本人" });
							}
						}

					} else if (model.equals(EnumConstants.productModule.CAR.getValue())) {// 车辆贷
						// 车辆信息
						Response<ReqApplyEntryVO> responseCarInfoEntity = Validate.getInstance().validate(appCarInfoEntity);
						if (!responseCarInfoEntity.isSuccess()) {
							logger.info("appCarInfoEntity 参数 规则验证: 校验不通过");
							return responseCarInfoEntity;
						}
						// 是否有车贷
						if (appCarInfoEntity.getCarLoan().equals(ParametersType.Indicator._Y)) {
							if (appCarInfoEntity.getMonthPaymentAmt() == null) {
								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "monthPaymentAmt  月供" });
							}
//							if (appCarInfoEntity.getCarLoanTerm() == null) {
//								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "carLoanTerm  贷款剩余期数" });
//							}
							if(appCarInfoEntity.getCarLoanIssueDate() == null){
								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "carLoanIssueDate  车贷发放年月贷" });
							}
						}

//						// 一手车 购车价和裸车价 都必填 二手车 二者必填一
//						if (appCarInfoEntity.getCarType().equals(ParametersType.CarType._ONE)) {
//							if (appCarInfoEntity.getNakedCarPrice() == null) {
//								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "nakedCarPrice  裸车价" });
//							}
//							if (appCarInfoEntity.getCarBuyPrice() == null) {
//								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "carBuyPrice  购买价" });
//							}
//						} else {
//							if (appCarInfoEntity.getNakedCarPrice() == null && appCarInfoEntity.getCarBuyPrice() == null) {
//								throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "二手车  裸车价和购买价二者必填一" });
//							}
//						}

					} else if (model.equals(EnumConstants.productModule.PROVIDENT.getValue())) {// 公积金贷
						// 公积金信息
						Response<ReqApplyEntryVO> responseProvidentInfoEntity = Validate.getInstance().validate(appProvidentInfoEntity);
						if (!responseProvidentInfoEntity.isSuccess()) {
							logger.info("appProvidentInfoEntity 参数 规则验证: 校验不通过");
							return responseProvidentInfoEntity;
						}
					} else if (model.equals(EnumConstants.productModule.POLICY.getValue())) {// 保单贷
						// 保单信息
						Response<ReqApplyEntryVO> responsePolicyInfoEntity = Validate.getInstance().validate(appPolicyInfoEntity);
						if (!responsePolicyInfoEntity.isSuccess()) {
							logger.info("appPolicyInfoEntity 参数 规则验证: 校验不通过");
							return responsePolicyInfoEntity;
						}
					} else if (model.equals(EnumConstants.productModule.MASTERLOAN_A.getValue())) {// 网购达人贷A
						APPMasterLoanInfoAEntity appMasterLoanInfoAEntity = new APPMasterLoanInfoAEntity();
						BeanUtils.copyProperties(appMasterLoanInfoEntity, appMasterLoanInfoAEntity);

						// 网购达人贷A
						Response<ReqApplyEntryVO> responseMasterLoanInfoEntity = Validate.getInstance().validate(appMasterLoanInfoAEntity);
						if (!responseMasterLoanInfoEntity.isSuccess()) {
							logger.info("appMasterLoanInfoEntity 参数 规则验证: 校验不通过");
							return responseMasterLoanInfoEntity;
						}
					} else if (model.equals(EnumConstants.productModule.MASTERLOAN_B.getValue())) {// 网购达人贷B
						APPMasterLoanInfoBEntity appMasterLoanInfoBEntity = new APPMasterLoanInfoBEntity();
						BeanUtils.copyProperties(appMasterLoanInfoEntity, appMasterLoanInfoBEntity);

						// 网购达人贷B
						Response<ReqApplyEntryVO> responseMasterLoanInfoEntity = Validate.getInstance().validate(appMasterLoanInfoBEntity);
						if (!responseMasterLoanInfoEntity.isSuccess()) {
							logger.info("appMasterLoanInfoEntity 参数 规则验证: 校验不通过");
							return responseMasterLoanInfoEntity;
						}
					} else if (model.equals(EnumConstants.productModule.MERCHANTLOAN.getValue())) {// 淘宝商户贷
						// 淘宝商户贷信息
						Response<ReqApplyEntryVO> responseMerchantLoanInfoEntity = Validate.getInstance().validate(appMerchantLoanInfoEntity);
						if (!responseMerchantLoanInfoEntity.isSuccess()) {
							logger.info("appMerchantLoanInfoEntity 参数 规则验证: 校验不通过");
							return responseMerchantLoanInfoEntity;
						}
					} else if (model.equals(EnumConstants.productModule.CARDLOAN.getValue())) {// 卡友贷
						// 卡友信息
						Response<ReqApplyEntryVO> responseCardLoanInfoEntity = Validate.getInstance().validate(appCardLoanInfoEntity);
						if (!responseCardLoanInfoEntity.isSuccess()) {
							logger.info("appCardLoanInfoEntity 参数 规则验证: 校验不通过");
							return responseCardLoanInfoEntity;
						}
					}
				}
			}
			// 如果校验成功, 继续业务处理
		}
		// 更新改变字段比对
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("loanBaseId", applyEntryVO.getLoanBaseId());

		/**
		 * 个人信息
		 */
		appPersonInfoEntity.setId(LoanBaseRela.getBmsAppPersonInfoId());
		appPersonInfoEntity.setModifiedTime(date);
		appPersonInfoEntity.setModifier(modifier);
		appPersonInfoEntity.setModifierId(modifierId);
//		 appPersonInfoService.saveOrUpdate(appPersonInfoEntity);
	
		updateCellRecordState(appPersonInfoEntity,applyEntryVO.getLoanBaseId());
		appPersonInfoService.updateAll(appPersonInfoEntity);
		/**
		 * 房产贷
		 */
		appEstateInfoEntity.setId(LoanBaseRela.getTmAppEstateInfoId());
		appEstateInfoEntity.setModifiedTime(date);
		appEstateInfoEntity.setModifier(modifier);
		appEstateInfoEntity.setModifierId(modifierId);
		// appEstateInfoService.saveOrUpdate(appEstateInfoEntity);
		appEstateInfoService.updateAll(appEstateInfoEntity);

		/**
		 * 车辆贷
		 */
		appCarInfoEntity.setId(LoanBaseRela.getTmAppCarInfoId());
		appCarInfoEntity.setModifiedTime(date);
		appCarInfoEntity.setModifier(modifier);
		appCarInfoEntity.setModifierId(modifierId);
		// appCarInfoService.saveOrUpdate(appCarInfoEntity);
		appCarInfoService.updateAll(appCarInfoEntity);

		/**
		 * 保单贷
		 */
		appPolicyInfoEntity.setId(LoanBaseRela.getTmAppPolicyInfoId());
		appPolicyInfoEntity.setModifiedTime(date);
		appPolicyInfoEntity.setModifier(modifier);
		appPolicyInfoEntity.setModifierId(modifierId);
		// appPolicyInfoService.saveOrUpdate(appPolicyInfoEntity);
		appPolicyInfoService.updateAll(appPolicyInfoEntity);
		/**
		 * 公积金贷
		 */
		appProvidentInfoEntity.setId(LoanBaseRela.getTmAppProvidentInfoId());
		appProvidentInfoEntity.setModifiedTime(date);
		appProvidentInfoEntity.setModifier(modifier);
		appProvidentInfoEntity.setModifierId(modifierId);
		// appProvidentInfoService.saveOrUpdate(appProvidentInfoEntity);
		appProvidentInfoService.updateAll(appProvidentInfoEntity);
		/**
		 * 卡友贷贷
		 */
		appCardLoanInfoEntity.setId(LoanBaseRela.getTmAppCardLoanInfoId());
		appCardLoanInfoEntity.setModifiedTime(date);
		appCardLoanInfoEntity.setModifier(modifier);
		appCardLoanInfoEntity.setModifierId(modifierId);
		// appCardLoanInfoService.saveOrUpdate(appCardLoanInfoEntity);
		appCardLoanInfoService.updateAll(appCardLoanInfoEntity);
//		/**
//		 * 随薪贷
//		 */
//		appSalaryLoanInfoEntity.setId(LoanBaseRela.getTmAppSalaryLoanInfoId());
//		appSalaryLoanInfoEntity.setModifiedTime(date);
//		appSalaryLoanInfoEntity.setModifier(modifier);
//		appSalaryLoanInfoEntity.setModifierId(modifierId);
//		// appSalaryInfoService.saveOrUpdate(appSalaryLoanInfoEntity);
//		appSalaryInfoService.saveOrUpdate(appSalaryLoanInfoEntity);

		/**
		 * 淘宝达人贷
		 */
		appMasterLoanInfoEntity.setId(LoanBaseRela.getTmAppMasterLoanInfoId());
		appMasterLoanInfoEntity.setModifiedTime(date);
		appMasterLoanInfoEntity.setModifier(modifier);
		appMasterLoanInfoEntity.setModifierId(modifierId);
		// appMasterLoanInfoService.saveOrUpdate(appMasterLoanInfoEntity);
		appMasterLoanInfoService.updateAll(appMasterLoanInfoEntity);
		/**
		 * 淘宝商户贷
		 */
		appMerchantLoanInfoEntity.setId(LoanBaseRela.getTmAppMerchanLoanInfoId());
		appMerchantLoanInfoEntity.setModifiedTime(date);
		appMerchantLoanInfoEntity.setModifier(modifier);
		appMerchantLoanInfoEntity.setModifierId(modifierId);
		// appMerchantLoanInfoService.saveOrUpdate(appMerchantLoanInfoEntity);
		appMerchantLoanInfoService.updateAll(appMerchantLoanInfoEntity);

		/**
		 * 联系人信息
		 */
		Map<String, Object> paramMapCont = new HashMap<String, Object>();
		paramMapCont.put("loanNo", applyEntryVO.getLoanNo());
		for (APPContactInfoEntity contactInfoEntity : appContactInfoEntityList) {
			// 查询是否有该数据
			paramMapCont.put("sequenceNum", contactInfoEntity.getSequenceNum());
			APPContactInfoEntity query = appContactInfoService.getBy(paramMapCont);
			if (query == null) {
				contactInfoEntity.setId(null);
			} else {
				contactInfoEntity.setId(query.getId());
			}
			contactInfoEntity.setOrg(EnumConstants.BMS_Org);
			contactInfoEntity.setLoanNo(applyEntryVO.getLoanNo());
			contactInfoEntity.setLoanBaseId(applyEntryVO.getLoanBaseId());
			contactInfoEntity.setPersonId(LoanBaseRela.getPersonId());

			contactInfoEntity.setModifiedTime(date);
			contactInfoEntity.setModifier(modifier);
			contactInfoEntity.setModifierId(modifierId);
			appContactInfoService.saveOrUpdateNew(contactInfoEntity);
		}

		Response<ReqApplyEntryVO> response = new Response<ReqApplyEntryVO>();
		ReqApplyEntryVO vo = new ReqApplyEntryVO();
		vo.setLoanBaseId(applyEntryVO.getLoanBaseId());
		vo.setLoanNo(applyEntryVO.getLoanNo());
		response.setData(vo);
		return response;
	}

	/**
	 * 获取央行征信报告
	 * 
	 * @param personHistoryLoanVO
	 * @return
	 */
	@Override
	public String fixedCreditReport(PersonHistoryLoanVO personHistoryLoanVO) {
		Map<String, Object> appDataMap = new HashMap<String, Object>();

		String msg = "";
		try {
			appDataMap.put("customerIdCard", personHistoryLoanVO.getIdNo());// 身份证
			appDataMap.put("customerName", personHistoryLoanVO.getName());// 用户名
			appDataMap.put("appNo", personHistoryLoanVO.getLoanNo());// 申请件编号
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			appDataMap.put("queryDate", df.format(new Date()));// 首次录入复核时间
			JSONObject resObject = creditHttpService.queryBMSReport(appDataMap);
			if (resObject.has("code") && "000000".equals(resObject.getString("code"))) { // 查询成功，并且有报告
				msg = " --------------- 申请件号为【" + personHistoryLoanVO.getLoanNo() + "查询央行报告成功，将返回的央行征信报告id[" + resObject.get("reportId") + "]";
				logger.info(msg);
				return resObject.get("reportId").toString();
			} else {
				msg = "--------------- 申请件号为【" + personHistoryLoanVO.getLoanNo() + "查询央行报告失败，失败原因: " + resObject.get("messages");
				logger.info(msg);
				return null;
			}
		} catch (Exception e) {
			logger.info("---------------申请件号为【" + personHistoryLoanVO.getLoanNo() + "查询央行报告成功异常：" + e.getMessage());
			return null;
		}
	}

	/**
	 * 获取上海资信报告
	 * 
	 * @param personHistoryLoanVO
	 * @return
	 */
	public String fixedShangHaiCreditReport(PersonHistoryLoanVO personHistoryLoanVO) {
		Map<String, Object> appDataMap = new HashMap<String, Object>();
		String msg = "";
		try {
			appDataMap.put("idnum", personHistoryLoanVO.getIdNo());// 身份证
			appDataMap.put("name", personHistoryLoanVO.getName());// 用户名
			appDataMap.put("appNo", personHistoryLoanVO.getLoanNo());//借款编号
			HttpResponse resObject = creditHttpService.queryShangHaiCreditReport(appDataMap);

			if (EnumConstants.HTTP_CODE_SUCCESS == resObject.getCode()) {// 请求成功
				Map resMap = JsonUtils.decode(resObject.getContent(), Map.class);
				if (EnumConstants.RES_CODE_SUCCESS.equals(resMap.get("code"))) {
					msg = " --------------- 申请件号为【" + personHistoryLoanVO.getLoanNo() + "查询上海资信报告成功，将返回的上海资信报告id[" + resMap.get("id") + "]";
					logger.info(msg);
					String id = (String) resMap.get("id");
					if (id == null || id.trim().equals("")) {
						return null;
					}
					return id;
				} else {
					msg = "--------------- 申请件号为【" + personHistoryLoanVO.getLoanNo() + "查询上海资信报告失败，失败原因: " + resMap.get("message");
					logger.info(msg);
					return null;
				}
			}
		} catch (Exception e) {
			logger.info("---------------查询上海资信报告成功异常：" + e.getMessage());
			return null;
		}
		return null;
	}

	/**
	 * 算话征信查询
	 * 
	 * @param loanNo
	 * @param applyEntryVO
	 * @return
	 */
	public Map<String, Object> getSuanHuaAntifraud(String loanNo, ApplyEntryVO applyEntryVO) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			map.put("creatorId", applyEntryVO.getServiceCode());// 操作员
			map.put("sources", "BMS");
			map.put("appNo", loanNo);// 借款编号
			map.put("idCard", applyEntryVO.getApplyInfoVO().getIdNo());// 身份证
			map.put("name", applyEntryVO.getApplyInfoVO().getName());// 姓名
			map.put("cellphone", applyEntryVO.getBasicInfoVO().getPersonInfoVO().getCellphone());// 手机号
			map.put("address", applyEntryVO.getBasicInfoVO().getPersonInfoVO().getHomeAddress());// 家庭地址
			map.put("homePhone", applyEntryVO.getBasicInfoVO().getPersonInfoVO().getHomePhone1());// 家庭电话
			map.put("companyName", applyEntryVO.getBasicInfoVO().getWorkInfoVO().getCorpName());// 工作单位名称
			map.put("companyAddress", applyEntryVO.getBasicInfoVO().getWorkInfoVO().getCorpAddress());// 工作单位地址
			map.put("companyPhone", applyEntryVO.getBasicInfoVO().getWorkInfoVO().getCorpPhone());// 工作单位电话
			map.put("contactPhone1", applyEntryVO.getContactInfoVOList().get(0).getContactCellPhone());//
			map.put("timestamp", String.valueOf(System.currentTimeMillis()));
			JSONObject resObject = creditHttpService.querySuanHuaAntifraud(map);
			if (resObject.has("code") && "000000".equals(resObject.getString("code")) && resObject.getJSONObject("data") != null) { // 查询成功，并且有报告
				logger.info(" --------------- 申请件号为【" + loanNo + "算话征信查询成功:[" + resObject.toString() + "]");
				map = new HashMap<String, Object>();
				map.put("appRst", resObject.getJSONObject("data").getString("appRst"));// appRst
																						// 欺诈得分
				map.put("stanFrdLevel", resObject.getJSONObject("data").getString("stanFrdLevel"));// 欺诈预警
				map.put("stanRiskRate", resObject.getJSONObject("data").getString("stanRiskRate"));// 欺诈风险评级
																									// 欺诈风险程度细分为A~L，按字母顺序越靠前风险越高(A~C：高欺诈风险,D~F：中欺诈风险,G~I：低欺诈风险,J~L：无欺诈偏向)
				return map;
			} else {
				logger.info(" --------------- 申请件号为【" + loanNo + "算话征信查询失败:[" + resObject.getString("messages") + "]");
				return null;
			}
		} catch (Exception e) {
			logger.info("---------------算话征信查询成功异常：" + e.getMessage());
			return null;
		}
	}

	/**
	 * 获取华征征信报告
	 * 
	 * @param personHistoryLoanVO
	 * @return
	 */
	public String fixedHZZXCreditReport(PersonHistoryLoanVO personHistoryLoanVO) {
		return null;
	}

	@Override
	public Response<ApplyEntryVO> queryApplyEntry(PersonHistoryLoanVO personHistoryLoanVO) {

		if (org.apache.commons.lang.StringUtils.isBlank(personHistoryLoanVO.getLoanNo())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "loanNo" });
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", personHistoryLoanVO.getLoanNo());

		ApplyEntryVO applyEntryVO = new ApplyEntryVO();
		// 申请信息
		ApplyInfoVO applytInfoVO = new ApplyInfoVO();
		// ----start
		// 资产信息
		AssetsInfoVO assetsInfoVO = new AssetsInfoVO();
		// 房产信息
		EstateInfoVO estateInfoVO = new EstateInfoVO();
		// 车辆信息
		CarInfoVO carInfoVO = new CarInfoVO();
		// 保单信息
		PolicyInfoVO policyInfoVO = new PolicyInfoVO();
		// 公积金信息
		ProvidentInfoVO providentInfoVO = new ProvidentInfoVO();
		// 卡友贷信息
		CardLoanInfoVO cardLoanInfoVO = new CardLoanInfoVO();
//		// 随薪贷信息
//		SalaryLoanInfoVO salaryLoanInfoVO = new SalaryLoanInfoVO();
		// 网购达人贷信息
		MasterLoanInfoVO masterLoanInfoVO = new MasterLoanInfoVO();
		//网购达人贷A信息
		MasterLoanInfoAVO masterLoanInfoAVo=new MasterLoanInfoAVO();
		//网购达人贷B信息
		MasterLoanInfoBVO masterLoanInfoBVo=new MasterLoanInfoBVO();
	
		// 淘宝商户贷信息
		MerchantLoanInfoVO merchantLoanInfoVO = new MerchantLoanInfoVO();
		// ----end

		// 基本信息 start
		BasicInfoVO basicInfoVO = new BasicInfoVO();
		// 个人信息
		PersonInfoVO personInfoVO = new PersonInfoVO();
		// 工作信息
		WorkInfoVO workInfoVO = new WorkInfoVO();
		// 私营业主信息
		PrivateOwnerInfoVO privateOwnerInfoVO = new PrivateOwnerInfoVO();
		// 基本信息 end

		// 联系人信息 satart
		List<ContactInfoVO> contactInfoVOList = new ArrayList<ContactInfoVO>();
		// 联系人信息 end

		List<LoanBaseEntity> listLoanVo = loanBaseService.getByMap(paramMap);
		if (listLoanVo == null || listLoanVo.size() == 0) {
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}

		// 构造实体类
		// 申请表
		LoanBaseEntity loanBaseEntity = listLoanVo.get(0);

		applyEntryVO.setVersion(loanBaseEntity.getVerson());

		// 获取配置总表
		LoanBaseRelaEntity LoanBaseRela = loanBaseRelaService.getByBaseId(loanBaseEntity.getId());
		if (LoanBaseRela == null) {
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}

		// 卡友贷
		APPCardLoanInfoEntity appCardLoanInfoEntity = appCardLoanInfoService.getById(LoanBaseRela.getTmAppCardLoanInfoId());
		if(appCardLoanInfoEntity == null){
			appCardLoanInfoEntity = new APPCardLoanInfoEntity();
		}
		
		// 车辆贷
		APPCarInfoEntity appCarInfoEntity = appCarInfoService.getById(LoanBaseRela.getTmAppCarInfoId());
		if(appCarInfoEntity == null){
			appCarInfoEntity = new APPCarInfoEntity();
		}
		
		// 房产贷
		APPEstateInfoEntity appEstateInfoEntity = appEstateInfoService.getById(LoanBaseRela.getTmAppEstateInfoId());
		if(appEstateInfoEntity == null){
			appEstateInfoEntity = new APPEstateInfoEntity();
		}
		
		// 网购达人贷达人贷
		APPMasterLoanInfoEntity appMasterLoanInfoEntity = appMasterLoanInfoService.getById(LoanBaseRela.getTmAppMasterLoanInfoId());
		if(appMasterLoanInfoEntity == null){
			appMasterLoanInfoEntity = new APPMasterLoanInfoEntity();
		}
		// 淘宝商户贷
		APPMerchantLoanInfoEntity appMerchantLoanInfoEntity = appMerchantLoanInfoService.getById(LoanBaseRela.getTmAppMerchanLoanInfoId());
		if(appMerchantLoanInfoEntity == null){
			appMerchantLoanInfoEntity = new APPMerchantLoanInfoEntity();
		}
		
		
		// 客户主表
		APPPersonEntity appPersonEntity = appPersonService.getById(LoanBaseRela.getPersonId());
		if(appPersonEntity == null){
			appPersonEntity = new APPPersonEntity();
		}
		
		// 用户信息详情
		APPPersonInfoEntity appPersonInfoEntity = appPersonInfoService.getById(LoanBaseRela.getBmsAppPersonInfoId());
		if(appPersonInfoEntity == null){
			appPersonInfoEntity = new APPPersonInfoEntity();
		}
		
		// 保单贷 OK
		APPPolicyInfoEntity appPolicyInfoEntity = appPolicyInfoService.getById(LoanBaseRela.getTmAppPolicyInfoId());
		//保单贷只做必传校验 保险金额 保险年限  已缴年限 年缴金额
		if(appPolicyInfoEntity == null){
			appPolicyInfoEntity = new APPPolicyInfoEntity();
		}
		
		// 公积金贷 OK
		APPProvidentInfoEntity appProvidentInfoEntity = appProvidentInfoService.getById(LoanBaseRela.getTmAppProvidentInfoId());
		if(appProvidentInfoEntity == null){
			appProvidentInfoEntity = new APPProvidentInfoEntity();
		}
		
//		// 随薪贷
//		APPSalaryLoanInfoEntity appSalaryLoanInfoEntity = appSalaryInfoService.getById(LoanBaseRela.getTmAppSalaryLoanInfoId());
//		if(appSalaryLoanInfoEntity == null){
//			appSalaryLoanInfoEntity = new APPSalaryLoanInfoEntity();
//		}
		/**
		 * 扩展表
		 */
		
		Map<String, Object> paramMapLoan = new HashMap<String, Object>();
		
		paramMapLoan.put("loanBaseId", loanBaseEntity.getId());
		List<LoanExtEntity> loanList = loanExtService.listBy(paramMapLoan);
		if (loanList == null) {
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		// 扩展表
		LoanExtEntity loanExtEntity = loanExtService.getById(loanList.get(0).getId());

		/**
		 * 借款信息产品表
		 */
		List<LoanProductEntity> loanIdList = loanProductService.listBy(paramMapLoan);
		if (loanIdList == null) {
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		LoanProductEntity loanProductEntity = loanProductService.getById(loanIdList.get(0).getId());

		// 联系人信息
		List<APPContactInfoEntity> appContactInfoEntityList = appContactInfoService.listBy(paramMapLoan);

		// 实体类赋值
		// 借款人信息 BMS_APP_PERSON
		BeanUtils.copyProperties(appPersonEntity, personInfoVO);

		BeanUtils.copyProperties(appPersonEntity, applytInfoVO);
		// 申请信息 bms_loan_base
		BeanUtils.copyProperties(loanBaseEntity, applytInfoVO);
		// 主办申请人 BMS_APP_PERSON_INFO
		BeanUtils.copyProperties(appPersonInfoEntity, personInfoVO);

		BeanUtils.copyProperties(loanBaseEntity, personInfoVO);
		// 借款信息产品 BMS_LOAN_PRODUCT
		BeanUtils.copyProperties(loanProductEntity, applytInfoVO);
		// 借款扩展 BMS_LOAN_EXT
		BeanUtils.copyProperties(loanExtEntity, applytInfoVO);
		// 房产信息 TM_APP_ESTATE_INFO
		BeanUtils.copyProperties(appEstateInfoEntity, estateInfoVO);
		// 车辆信息 TM_APP_CAR_INFO
		BeanUtils.copyProperties(appCarInfoEntity, carInfoVO);
		// 保单信息 TM_APP_POLICY_INFO
		BeanUtils.copyProperties(appPolicyInfoEntity, policyInfoVO);
		// 公积金信息 TM_APP_POLICY_INFO
		BeanUtils.copyProperties(appProvidentInfoEntity, providentInfoVO);
		// 卡友贷信息 TM_APP_CARD_LOAN_INFO
		BeanUtils.copyProperties(appCardLoanInfoEntity, cardLoanInfoVO);
		// 随薪贷信息 TM_APP_SALARY_LOAN_INFO
//		BeanUtils.copyProperties(appSalaryLoanInfoEntity, salaryLoanInfoVO);
		// 网购达人贷 TM_APP_MASTER_LOAN_INFO 分为A和B
		BeanUtils.copyProperties(appMasterLoanInfoEntity, masterLoanInfoVO);
		// 淘宝商户贷信息 TM_APP_MERCHANT_LOAN_INFO
		BeanUtils.copyProperties(appMerchantLoanInfoEntity, merchantLoanInfoVO);
		// 工作信息 BMS_APP_PERSON_INFO
		BeanUtils.copyProperties(appPersonInfoEntity, workInfoVO);
		// 私营业主信息 BMS_APP_PERSON_INFO
		BeanUtils.copyProperties(appPersonInfoEntity, privateOwnerInfoVO);
		 //网购达人贷A
		BeanUtils.copyProperties(appMasterLoanInfoEntity, masterLoanInfoAVo);
		 //网购达人贷B	
	    BeanUtils.copyProperties(appMasterLoanInfoEntity, masterLoanInfoBVo);

		if (appContactInfoEntityList != null && appContactInfoEntityList.size() > 0) {
			for (int j = 0; j < appContactInfoEntityList.size(); j++) {
				ContactInfoVO contactInfo = new ContactInfoVO();
				BeanUtils.copyProperties(appContactInfoEntityList.get(j), contactInfo);
				contactInfoVOList.add(contactInfo);
			}
		}
		// 排序，将配偶放前面
		Collections.sort(contactInfoVOList, new Comparator<ContactInfoVO>() {
			@Override
			public int compare(ContactInfoVO o1, ContactInfoVO o2) {
				if (StringUtils.isEmpty(o1.getContactRelation()) || StringUtils.isEmpty(o2.getContactRelation())) {
					return 0;
				}

				if (o1.getContactRelation().equals(ParametersType.Relationship._00013) && !o2.getContactRelation().equals(ParametersType.Relationship._00013)) {
					return -1;
				} else if (!o1.getContactRelation().equals(ParametersType.Relationship._00013) && o2.getContactRelation().equals(ParametersType.Relationship._00013)) {
					return 1;
				} else {
					return 0;
				}
			}
		});

		assetsInfoVO.setCardLoanInfoVO(cardLoanInfoVO);
		assetsInfoVO.setCarInfoVO(carInfoVO);
		assetsInfoVO.setEstateInfoVO(estateInfoVO);
		assetsInfoVO.setMasterLoanInfoVO(masterLoanInfoVO);
		assetsInfoVO.setMerchantLoanInfoVO(merchantLoanInfoVO);
		assetsInfoVO.setPolicyInfoVO(policyInfoVO);
		assetsInfoVO.setProvidentInfoVO(providentInfoVO);
		assetsInfoVO.setSalaryLoanInfoVO(new SalaryLoanInfoVO());
		assetsInfoVO.setMasterLoanInfoAVO(masterLoanInfoAVo);
		assetsInfoVO.setMasterLoanInfoBVO(masterLoanInfoBVo);
		assetsInfoVO = setAssetsInfoVOIfEmpty(assetsInfoVO);

		basicInfoVO.setPersonInfoVO(personInfoVO);
		basicInfoVO.setPrivateOwnerInfoVO(privateOwnerInfoVO);
		basicInfoVO.setWorkInfoVO(workInfoVO);

		applyEntryVO.setApplyInfoVO(applytInfoVO); // 申请信息
		applyEntryVO.setAssetsInfoVO(assetsInfoVO);// 资产信息
		applyEntryVO.setBasicInfoVO(basicInfoVO); // 基本信息
		applyEntryVO.setContactInfoVOList(contactInfoVOList); // 联系人信息

		// 查看审批表是否有数据，如果没有，说明是新数据
		Map<String, Object> paramMapAudit = new HashMap<String, Object>();
		paramMapAudit.put("loanNo", loanBaseEntity.getLoanNo());
		LoanAuditEntity loanAuditEntitySelect = iLoanAuditService.getBy(paramMapAudit);
		// 有数据，已经复核提交过一次
		if (loanAuditEntitySelect == null) {
			applyEntryVO.setIfFirstSubmit(new Long("1"));
		} else {
			applyEntryVO.setIfFirstSubmit(new Long("0"));
		}
		
		
		//添加复核开始时间
		if(loanExtEntity != null && loanExtEntity.getApplyEndTime() != null){
			applyEntryVO.setApplyEndTime(loanExtEntity.getApplyEndTime());
			applyEntryVO.setAuditEndTime(loanExtEntity.getAuditEndTime());
		}else{
			applyEntryVO.setApplyEndTime(new Date());
		}

		Response<ApplyEntryVO> resApplyEntryVo = new Response<ApplyEntryVO>();
		resApplyEntryVo.setData(applyEntryVO);
		return resApplyEntryVo;
	}

	  public AssetsInfoVO setAssetsInfoVOIfEmpty(AssetsInfoVO assetsInfoVO) {
		if (assetsInfoVO == null)
			return null;
		
		EstateInfoVO estateInfoVO = assetsInfoVO.getEstateInfoVO();
		String regis = null;
		if(estateInfoVO != null){
			regis = assetsInfoVO.getEstateInfoVO().getEstateSameRegistered();
			estateInfoVO.setEstateSameRegistered(null);
		}

		if (BeanAttributeUtils.classIfNull(estateInfoVO == null ? null : estateInfoVO)) {
			assetsInfoVO.getEstateInfoVO().setIfEmpty(1L);
			assetsInfoVO.getEstateInfoVO().setEstateSameRegistered(regis);
		}else{
			assetsInfoVO.getEstateInfoVO().setEstateSameRegistered(regis);
		}

		if (BeanAttributeUtils.classIfNull(assetsInfoVO.getCarInfoVO() == null ? null : assetsInfoVO.getCarInfoVO())) {
			if(assetsInfoVO.getCarInfoVO().getCarBuyPrice() == null && assetsInfoVO.getCarInfoVO().getCarBuyDate() == null && assetsInfoVO.getCarInfoVO().getPlateNum() == null && assetsInfoVO.getCarInfoVO().getCarLoan() == null && assetsInfoVO.getCarInfoVO().getCarLoanIssueDate() == null && assetsInfoVO.getCarInfoVO().getMonthPaymentAmt() == null){
				assetsInfoVO.getCarInfoVO().setIfEmpty(0L);
			}else{
				assetsInfoVO.getCarInfoVO().setIfEmpty(1L);
			}
		}

		if (BeanAttributeUtils.classIfNull(assetsInfoVO.getPolicyInfoVO() == null ? null : assetsInfoVO.getPolicyInfoVO())) {
			if(assetsInfoVO.getPolicyInfoVO().getInsuranceAmt()==null && assetsInfoVO.getPolicyInfoVO().getInsuranceTerm()==null && assetsInfoVO.getPolicyInfoVO().getYearPaymentAmt()==null && assetsInfoVO.getPolicyInfoVO().getPaidTerm()==null ){
				assetsInfoVO.getPolicyInfoVO().setIfEmpty(0L);
			}else{
				assetsInfoVO.getPolicyInfoVO().setIfEmpty(1L);
			}
		}
		if (BeanAttributeUtils.classIfNull(assetsInfoVO.getProvidentInfoVO() == null ? null : assetsInfoVO.getProvidentInfoVO())) {
			assetsInfoVO.getProvidentInfoVO().setIfEmpty(1L);
		}

		if (BeanAttributeUtils.classIfNull(assetsInfoVO.getCardLoanInfoVO() == null ? null : assetsInfoVO.getCardLoanInfoVO())) {
			assetsInfoVO.getCardLoanInfoVO().setIfEmpty(1L);
		}

		if (BeanAttributeUtils.classIfNull(assetsInfoVO.getSalaryLoanInfoVO() == null ? null : assetsInfoVO.getSalaryLoanInfoVO())) {
			assetsInfoVO.getSalaryLoanInfoVO().setIfEmpty(1L);
		}

		if (BeanAttributeUtils.classIfNull(assetsInfoVO.getMasterLoanInfoVO() == null ? null : assetsInfoVO.getMasterLoanInfoVO())) {
			assetsInfoVO.getMasterLoanInfoVO().setIfEmpty(1L);
		}

		if (BeanAttributeUtils.classIfNull(assetsInfoVO.getMerchantLoanInfoVO() == null ? null : assetsInfoVO.getMerchantLoanInfoVO())) {
			assetsInfoVO.getMerchantLoanInfoVO().setIfEmpty(1L);
		}
		
		if (BeanAttributeUtils.classIfNull(assetsInfoVO.getMasterLoanInfoAVO() == null ? null : assetsInfoVO.getMasterLoanInfoAVO())) {
			if(assetsInfoVO.getMasterLoanInfoAVO().getBuyerCreditLevel()==null && assetsInfoVO.getMasterLoanInfoAVO().getBuyerCreditType()==null && assetsInfoVO.getMasterLoanInfoAVO().getSesameCreditValue()==null){
				assetsInfoVO.getMasterLoanInfoAVO().setIfEmpty(0L);
			}else{
				assetsInfoVO.getMasterLoanInfoAVO().setIfEmpty(1L);
			}
		}

		if (BeanAttributeUtils.classIfNull(assetsInfoVO.getMasterLoanInfoBVO() == null ? null : assetsInfoVO.getMasterLoanInfoBVO())) {
			if(assetsInfoVO.getMasterLoanInfoBVO() .getJiDongUserLevel()==null && assetsInfoVO.getMasterLoanInfoBVO().getWhiteCreditValue()==null){
				assetsInfoVO.getMasterLoanInfoBVO().setIfEmpty(0L);
			}else{
				assetsInfoVO.getMasterLoanInfoBVO().setIfEmpty(1L);
			}
		}
		return assetsInfoVO;
	  }

	public AssetsInfoVO setIfEmpty(AssetsInfoVO assetsInfoVO) {
		if (BeanAttributeUtils.classIfNull(assetsInfoVO.getEstateInfoVO() == null ? null : assetsInfoVO.getEstateInfoVO())) {
			assetsInfoVO.getEstateInfoVO().setIfEmpty(1L);
		}
		if (BeanAttributeUtils.classIfNull(assetsInfoVO.getCarInfoVO() == null ? null : assetsInfoVO.getCarInfoVO())) {
			assetsInfoVO.getCarInfoVO().setIfEmpty(1L);
		}

		if (BeanAttributeUtils.classIfNull(assetsInfoVO.getPolicyInfoVO() == null ? null : assetsInfoVO.getPolicyInfoVO())) {
			assetsInfoVO.getPolicyInfoVO().setIfEmpty(1L);
		}

		if (BeanAttributeUtils.classIfNull(assetsInfoVO.getProvidentInfoVO())) {
			assetsInfoVO.getProvidentInfoVO().setIfEmpty(1L);
		}

		if (BeanAttributeUtils.classIfNull(assetsInfoVO.getCardLoanInfoVO())) {
			assetsInfoVO.getCardLoanInfoVO().setIfEmpty(1L);
		}

		if (BeanAttributeUtils.classIfNull(assetsInfoVO.getSalaryLoanInfoVO())) {
			assetsInfoVO.getSalaryLoanInfoVO().setIfEmpty(1L);
		}

		if (BeanAttributeUtils.classIfNull(assetsInfoVO.getMasterLoanInfoVO())) {
			assetsInfoVO.getMasterLoanInfoVO().setIfEmpty(1L);
		}

		if (BeanAttributeUtils.classIfNull(assetsInfoVO.getMerchantLoanInfoVO())) {
			assetsInfoVO.getMerchantLoanInfoVO().setIfEmpty(1L);
		}
		return assetsInfoVO;
	}

	/**
	 * 做快照备份 1 复核提交 2 初审退回 3 初审提交 4 终审退回
	 * 
	 * @param loanNo
	 * @param version
	 */
	@Override
	public void auditDifferences(String loanNo, String version) {
		logger.info(loanNo + "快照备份开始!");
		if (loanNo == null || version == null) {
			return;
		}

		try {
			Map<String, Object> obj = new HashMap<String, Object>();
			obj.put("loanNo", loanNo);
			LoanExtEntity loanExtEntitySelect = loanExtService.getBy(obj);
			PersonHistoryLoanVO personHistoryLoanVO = new PersonHistoryLoanVO();
			personHistoryLoanVO.setLoanNo(loanNo);
			Response<ApplyEntryVO> queryApplyEntry = new Response<ApplyEntryVO>();

			// 获取快照记录
			if (version.equals(EnumConstants.differences.peview_submit.getValue())) {
				// 1 复核提交
				queryApplyEntry = queryApplyEntry(personHistoryLoanVO);
			} else if (version.equals(EnumConstants.differences.audit_back.getValue())) {
				// 2 初审退回
				queryApplyEntry = queryApplyEntry(personHistoryLoanVO);
			} else if (version.equals(EnumConstants.differences.audit_submit.getValue())) {
				// 3 初审提交
				queryApplyEntry = queryApplyValueEntry(personHistoryLoanVO);
			} else if (version.equals(EnumConstants.differences.final_audit_back.getValue())) {
				// 4 终审退回
				queryApplyEntry = queryApplyValueEntry(personHistoryLoanVO);
			}
			if (!queryApplyEntry.isSuccess()) {
				return;
			}

			AuditEntryVO auditEntryVO = new AuditEntryVO();
			BeanUtils.copyProperties(queryApplyEntry.getData(), auditEntryVO);

			LoanExtEntity updateLoanExtEntity = new LoanExtEntity();
			String json = gson.toJson(auditEntryVO);

			if (version.equals(EnumConstants.differences.peview_submit.getValue())) {
				// 1 复核提交
				updateLoanExtEntity.setPeviewSnapVersion(json);
			} else if (version.equals(EnumConstants.differences.audit_back.getValue())) {
				// 2 初审退回
				updateLoanExtEntity.setAuditBackSnapVersion(json);
			} else if (version.equals(EnumConstants.differences.audit_submit.getValue())) {
				// 3 初审提交
				updateLoanExtEntity.setAuditSnapVersion(json);
			} else if (version.equals(EnumConstants.differences.final_audit_back.getValue())) {
				// 4 终审退回
				updateLoanExtEntity.setFinalAuditBackSnapVersion(json);
			}

			updateLoanExtEntity.setId(loanExtEntitySelect.getId());
			updateLoanExtEntity.setModifiedTime(new Date());
			updateLoanExtEntity.setModifier("");
			loanExtService.saveOrUpdate(updateLoanExtEntity);
		} catch (Exception e) {
			logger.info("快照备份错误，借款编号[" + loanNo + "],错误原因：" + e.getMessage());
		}

	}

	/**
	 * 校验是否可以加急
	 * 
	 * @param owningBranchId
	 * @return
	 */
	public String ifPri(Long owningBranchId) {
		if (owningBranchId == null || owningBranchId == 0)
			return "0";
		ReqLoanUrgentVO vo = new ReqLoanUrgentVO();
		vo.setOwningBranchId(owningBranchId);
		ResLoanUrgentVO resLoanUrgentVO = iBMSLoanUrgentConfigServic.getLoanUrgentSize(vo);
		if (resLoanUrgentVO.getIsUrgent()) {
			return "1";
		} else {
			return "0";
		}
	}

	/**
	 * 根据客户经理查询业务主任 850
	 * 
	 * @return
	 */
	public ResEmployeeVO queryDirector(String branchManagerCode) {
		if (StringUtils.isEmpty(branchManagerCode))
			return null;
		logger.info("根据客户经理code[" + branchManagerCode + "]，查询业务主任----开始");
		ResEmployeeVO resVo = new ResEmployeeVO();

		ReqParamVO reqParamVO = new ReqParamVO();
		reqParamVO.setSysCode(EnumConstants.BMS_SYSCODE);
		reqParamVO.setUsercode(branchManagerCode);
		Response<List<ResOrganizationVO>> findGroupByAccount = OrganizationExecuter.findGroupByAccount(reqParamVO);
		if (!findGroupByAccount.isSuccess() || findGroupByAccount.getData().size() == 0) {
			logger.info("根据客户经理code[" + branchManagerCode + "]，未查询到改用户的组织信息");
			return null;
		}
		logger.info("根据客户经理code[" + branchManagerCode + "]，查询到改组织信息--" + gson.toJson(findGroupByAccount.getData().get(0)));
		List<String> roleCodes = new ArrayList<String>();
		roleCodes.add("director");
		reqParamVO.setRoleCodes(roleCodes);// 业务主任角色
		reqParamVO.setOrgId(findGroupByAccount.getData().get(0).getId());// 组织id
		reqParamVO.setUsercode(null);
		reqParamVO.setStatus(0);// 可用
		reqParamVO.setInActive("t");
		Response<List<ResEmployeeVO>> findByDeptAndRole = iEmployeeExecuter.findByDeptAndRole(reqParamVO);
		if (!findByDeptAndRole.isSuccess() || findByDeptAndRole.getData().size() == 0) {
			logger.info("根据客户经理code[" + branchManagerCode + "]，角色[director],组织id[" + findGroupByAccount.getData().get(0).getId() + "],未查询到改用户的信息");
			return null;
		}
		ResEmployeeVO employeeVO = findByDeptAndRole.getData().get(0);
		employeeVO.setOrgId(findGroupByAccount.getData().get(0).getId());
		employeeVO.setOrgName(findGroupByAccount.getData().get(0).getName());
		return findByDeptAndRole.getData().get(0);
	}
			
		/**
		* 根据登录用户顺序指定复核客服
		* YM10139 liufz
		* @return
		*/
		/*@SuppressWarnings({"unchecked", "rawtypes" })
		private ResEmployeeVO queryReviwCode(String serviceCode,String loanNo){
			//分配的复核客服
			ResEmployeeVO resEmployeeVO = new ResEmployeeVO();
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
			roleCodes.add("customerService");
			reqParamVO2.setRoleCodes(roleCodes);
			// 根据营业部查询指定角色默认在职可用
			Response<List<ResEmployeeVO>> res2 = iEmployeeExecuter.findByDeptAndRole(reqParamVO2);
			List<ResEmployeeVO> resEmployeeVOs =res2.getData();
			//查询机构分派前一客服
			Map paramMap =new HashMap();
			paramMap.put("owningBranchId", orgId);
			paramMap.put("loanNo",loanNo);
			String prevReviewCode=	loanBaseService.getOrgPrevReviewCode(paramMap);
			//上一次分配客服位置
			int index=-1;
			if(resEmployeeVOs==null||resEmployeeVOs.size() <0){
				throw new BizException(BizErrorCode.ORG_NOTFOUND, "该客服所在营业部未找到可分配的客服");
			}
			//如果上一次分配的客服为空 或者 上次分配的客服为客服列队的最后一个 则返回第一个
			if(com.ymkj.cms.biz.common.util.StringUtils.isEmpty(prevReviewCode)){
				index=0;
			}else{
				//找出上一次分配客服所在的位置
				for (int i = 0; i < resEmployeeVOs.size(); i++) {
					if(resEmployeeVOs.get(i).getUsercode().equals(prevReviewCode)){
						//指定上一次分配的客服下一个客服	
						index =i;
						break;
					}
				}
			}
			//当前分配的客服为上一个客服下一位置
			int currentIndex=index+1;
			//当前分配的客服为最后客服取第一个客服
			if(currentIndex ==resEmployeeVOs.size()){
				currentIndex=0;
			}
			//当前客服分配客服
			resEmployeeVO =resEmployeeVOs.get(currentIndex);
			//如果当前分配客服为当前提交客服 取下一个 
			if(resEmployeeVO.getUsercode().equals(serviceCode)){
				if(currentIndex == resEmployeeVOs.size()){
					currentIndex =0;
				}
				currentIndex =currentIndex+1;	
			}
			//当前客服分配客服
			resEmployeeVO =resEmployeeVOs.get(currentIndex);
			return resEmployeeVO;
		}*/

	
	@Override
	public Response<ApplyEntryVO> queryApplyValueEntry(PersonHistoryLoanVO personHistoryLoanVO) {
		logger.info("掉这个查询！！！！！！！！！！！！！！！！！！！！！！！！");
		// 借款编号
		if (org.apache.commons.lang.StringUtils.isBlank(personHistoryLoanVO.getLoanNo())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "loanNo" });
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", personHistoryLoanVO.getLoanNo());

		ApplyEntryVO applyEntryVO = new ApplyEntryVO();
		// 申请信息
		ApplyInfoVO applytInfoVO = new ApplyInfoVO();
		// ----start
		// 资产信息
		AssetsInfoVO assetsInfoVO = new AssetsInfoVO();
		// 房产信息
		EstateInfoVO estateInfoVO = new EstateInfoVO();
		// 车辆信息
		CarInfoVO carInfoVO = new CarInfoVO();
		// 保单信息
		PolicyInfoVO policyInfoVO = new PolicyInfoVO();
		// 公积金信息
		ProvidentInfoVO providentInfoVO = new ProvidentInfoVO();
		// 卡友贷信息
		CardLoanInfoVO cardLoanInfoVO = new CardLoanInfoVO();
//		// 随薪贷信息
//		SalaryLoanInfoVO salaryLoanInfoVO = new SalaryLoanInfoVO();
		// 网购达人贷信息
		MasterLoanInfoVO masterLoanInfoVO = new MasterLoanInfoVO();
		//网购达人贷A信息
		MasterLoanInfoAVO masterLoanInfoAVo=new MasterLoanInfoAVO();
		//网购达人贷B信息
		MasterLoanInfoBVO masterLoanInfoBVo=new MasterLoanInfoBVO();
		// 淘宝商户贷信息
		MerchantLoanInfoVO merchantLoanInfoVO = new MerchantLoanInfoVO();
		// ----end

		// 基本信息 start
		BasicInfoVO basicInfoVO = new BasicInfoVO();
		// 个人信息
		PersonInfoVO personInfoVO = new PersonInfoVO();
		// 工作信息
		WorkInfoVO workInfoVO = new WorkInfoVO();
		// 私营业主信息
		PrivateOwnerInfoVO privateOwnerInfoVO = new PrivateOwnerInfoVO();
		// 基本信息 end

		// 联系人信息 satart
		List<ContactInfoVO> contactInfoVOList = new ArrayList<ContactInfoVO>();
		// 联系人信息 end

		List<LoanBaseEntity> listLoanVo = loanBaseService.getByMap(paramMap);
		if (listLoanVo == null || listLoanVo.size() == 0) {
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}

		// 构造实体类
		// 申请表
		LoanBaseEntity loanBaseEntity = listLoanVo.get(0);

		applyEntryVO.setVersion(loanBaseEntity.getVerson());

		// 获取配置总表
		LoanBaseRelaEntity LoanBaseRela = loanBaseRelaService.getByBaseId(loanBaseEntity.getId());
		if (LoanBaseRela == null) {
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}

		// 卡友贷
		APPCardLoanInfoEntity appCardLoanInfoEntity = appCardLoanInfoService.getById(LoanBaseRela.getTmAppCardLoanInfoId());
		if(appCardLoanInfoEntity == null){
			appCardLoanInfoEntity = new APPCardLoanInfoEntity();
		}
		
		// 车辆贷
		APPCarInfoEntity appCarInfoEntity = appCarInfoService.getById(LoanBaseRela.getTmAppCarInfoId());
		if(appCarInfoEntity == null){
			appCarInfoEntity = new APPCarInfoEntity();
		}
		// 房产贷
		APPEstateInfoEntity appEstateInfoEntity = appEstateInfoService.getById(LoanBaseRela.getTmAppEstateInfoId());
		if(appEstateInfoEntity == null){
			appEstateInfoEntity = new APPEstateInfoEntity();
		}
		
		// 淘宝达人贷
		APPMasterLoanInfoEntity appMasterLoanInfoEntity = appMasterLoanInfoService.getById(LoanBaseRela.getTmAppMasterLoanInfoId());
		if(appMasterLoanInfoEntity == null){
			appMasterLoanInfoEntity = new APPMasterLoanInfoEntity();
		}
		
		
		// 淘宝商户贷
		APPMerchantLoanInfoEntity appMerchantLoanInfoEntity = appMerchantLoanInfoService.getById(LoanBaseRela.getTmAppMerchanLoanInfoId());
		if(appMerchantLoanInfoEntity == null){
			appMerchantLoanInfoEntity = new APPMerchantLoanInfoEntity();
		}
		
		// 客户主表
		APPPersonEntity appPersonEntity = appPersonService.getById(LoanBaseRela.getPersonId());
		if(appPersonEntity == null){
			appPersonEntity = new APPPersonEntity();
		}
		
		// 用户信息详情
		APPPersonInfoEntity appPersonInfoEntity = appPersonInfoService.getById(LoanBaseRela.getBmsAppPersonInfoId());
		if(appPersonInfoEntity == null){
			appPersonInfoEntity = new APPPersonInfoEntity();
		}
		
		logger.info("掉这个查询！！---------------appPersonInfoEntity值"+gson.toJson(appPersonInfoEntity));
		
		// 保单贷 OK
		APPPolicyInfoEntity appPolicyInfoEntity = appPolicyInfoService.getById(LoanBaseRela.getTmAppPolicyInfoId());
		if(appPolicyInfoEntity == null){
			appPolicyInfoEntity = new APPPolicyInfoEntity();
		}
		
		// 公积金贷 OK
		APPProvidentInfoEntity appProvidentInfoEntity = appProvidentInfoService.getById(LoanBaseRela.getTmAppProvidentInfoId());
		if(appProvidentInfoEntity == null){
			appProvidentInfoEntity = new APPProvidentInfoEntity();
		}
		
		
//		// 随薪贷
//		APPSalaryLoanInfoEntity appSalaryLoanInfoEntity = appSalaryInfoService.getById(LoanBaseRela.getTmAppSalaryLoanInfoId());
//		if(appSalaryLoanInfoEntity == null){
//			appSalaryLoanInfoEntity = new APPSalaryLoanInfoEntity();
//		}
		
		/**
		 * 扩展表
		 */
		paramMap.put("loanBaseId", loanBaseEntity.getId());
		List<LoanExtEntity> loanList = loanExtService.listBy(paramMap);
		if (loanList == null) {
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		// 扩展表
		LoanExtEntity loanExtEntity = loanExtService.getById(loanList.get(0).getId());
		if(loanExtEntity == null){
			loanExtEntity = new LoanExtEntity();
		}
		/**
		 * 借款信息产品表
		 */
		List<LoanProductEntity> loanIdList = loanProductService.listBy(paramMap);
		if (loanIdList == null) {
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		LoanProductEntity loanProductEntity = loanProductService.getById(loanIdList.get(0).getId());
		if(loanProductEntity == null){
			loanProductEntity = new LoanProductEntity();
		}
		// 联系人信息
		List<APPContactInfoEntity> appContactInfoEntityList = appContactInfoService.listBy(paramMap);

		// 实体类赋值
		// 借款人信息 BMS_APP_PERSON
		BeanUtils.copyProperties(appPersonEntity, personInfoVO);

		BeanUtils.copyProperties(appPersonEntity, applytInfoVO);
		// 申请信息 bms_loan_base
		BeanUtils.copyProperties(loanBaseEntity, applytInfoVO);
		// 主办申请人 BMS_APP_PERSON_INFO
		BeanUtils.copyProperties(appPersonInfoEntity, personInfoVO);

		BeanUtils.copyProperties(loanBaseEntity, personInfoVO);
		// 借款信息产品 BMS_LOAN_PRODUCT
		BeanUtils.copyProperties(loanProductEntity, applytInfoVO);
		// 借款扩展 BMS_LOAN_EXT
		BeanUtils.copyProperties(loanExtEntity, applytInfoVO);
		// 房产信息 TM_APP_ESTATE_INFO
		BeanUtils.copyProperties(appEstateInfoEntity, estateInfoVO);
		// 车辆信息 TM_APP_CAR_INFO
		BeanUtils.copyProperties(appCarInfoEntity, carInfoVO);
		// 保单信息 TM_APP_POLICY_INFO
		BeanUtils.copyProperties(appPolicyInfoEntity, policyInfoVO);
		// 公积金信息 TM_APP_POLICY_INFO
		BeanUtils.copyProperties(appProvidentInfoEntity, providentInfoVO);
		// 卡友贷信息 TM_APP_CARD_LOAN_INFO
		BeanUtils.copyProperties(appCardLoanInfoEntity, cardLoanInfoVO);
		// 随薪贷信息 TM_APP_SALARY_LOAN_INFO
//		BeanUtils.copyProperties(appSalaryLoanInfoEntity, salaryLoanInfoVO);
		// 网购达人贷 TM_APP_MASTER_LOAN_INFO
		BeanUtils.copyProperties(appMasterLoanInfoEntity, masterLoanInfoVO);
		// 淘宝商户贷信息 TM_APP_MERCHANT_LOAN_INFO
		BeanUtils.copyProperties(appMerchantLoanInfoEntity, merchantLoanInfoVO);
		// 工作信息 BMS_APP_PERSON_INFO
		BeanUtils.copyProperties(appPersonInfoEntity, workInfoVO);
		// 私营业主信息 BMS_APP_PERSON_INFO
		BeanUtils.copyProperties(appPersonInfoEntity, privateOwnerInfoVO);
	    //网购达人贷A
		BeanUtils.copyProperties(appMasterLoanInfoEntity, masterLoanInfoAVo);
		 //网购达人贷B	
	    BeanUtils.copyProperties(appMasterLoanInfoEntity, masterLoanInfoBVo);
			
		
		if (appContactInfoEntityList != null && appContactInfoEntityList.size() > 0) {
			for (int j = 0; j < appContactInfoEntityList.size(); j++) {
				ContactInfoVO contactInfo = new ContactInfoVO();
				BeanUtils.copyProperties(appContactInfoEntityList.get(j), contactInfo);
				contactInfoVOList.add(contactInfo);
			}
		}
		// 排序，将配偶放前面
		Collections.sort(contactInfoVOList, new Comparator<ContactInfoVO>() {
			@Override
			public int compare(ContactInfoVO o1, ContactInfoVO o2) {
				if (StringUtils.isEmpty(o1.getContactRelation()) || StringUtils.isEmpty(o2.getContactRelation())) {
					return 0;
				}
				if (o1.getContactRelation().equals(ParametersType.Relationship._00013) && !o2.getContactRelation().equals(ParametersType.Relationship._00013)) {
					return -1;
				} else if (!o1.getContactRelation().equals(ParametersType.Relationship._00013) && o2.getContactRelation().equals(ParametersType.Relationship._00013)) {
					return 1;
				} else {
					return 0;
				}
			}
		});

		try {
			// 卡友贷信息
			assetsInfoVO.setCardLoanInfoVO(cardLoanInfoVO);
			if (!StringUtils.isEmpty(carInfoVO.getCarType())) {// 车辆类型
//				carInfoVO.setCarType(EnumCHConstants.CarType.valueOf("_" + carInfoVO.getCarType()).getValue());
				carInfoVO.setCarType(demoService.getVaule("CarType", carInfoVO.getCarType()));
			}
			if (!StringUtils.isEmpty(carInfoVO.getCarLoan())) {// 是否有车贷
//				carInfoVO.setCarLoan(EnumCHConstants.Indicator.valueOf("_" + carInfoVO.getCarLoan()).getValue());
				carInfoVO.setCarLoan(demoService.getVaule("Indicator", carInfoVO.getCarLoan()));
			}
			if (!StringUtils.isEmpty(carInfoVO.getLocalPlate())) {// 本地车牌
//				carInfoVO.setLocalPlate(EnumCHConstants.Indicator.valueOf("_" + carInfoVO.getLocalPlate()).getValue());
				carInfoVO.setLocalPlate(demoService.getVaule("Indicator", carInfoVO.getLocalPlate()));
			}
			// 车辆信息
			assetsInfoVO.setCarInfoVO(carInfoVO);

			if (!StringUtils.isEmpty(estateInfoVO.getEstateType())) {// 房产类型
//				estateInfoVO.setEstateType(EnumCHConstants.FangType.valueOf("_" + estateInfoVO.getEstateType()).getValue());
				estateInfoVO.setEstateType(demoService.getVaule("FangType", estateInfoVO.getEstateType()));
			}
			if (!StringUtils.isEmpty(estateInfoVO.getEstateLoan())) {// 房贷情况
//				estateInfoVO.setEstateLoan(EnumCHConstants.EstateType.valueOf("_" + estateInfoVO.getEstateLoan()).getValue());
				estateInfoVO.setEstateLoan(demoService.getVaule("EstateType", estateInfoVO.getEstateLoan()));
			}
			if (!StringUtils.isEmpty(estateInfoVO.getHouseOwnership())) {// 房产所有权
//				estateInfoVO.setHouseOwnership(EnumCHConstants.HouseOwnerType.valueOf("_" + estateInfoVO.getHouseOwnership()).getValue());
				estateInfoVO.setHouseOwnership(demoService.getVaule("HouseOwnerType", estateInfoVO.getHouseOwnership()));
			}
			if (!StringUtils.isEmpty(estateInfoVO.getIfMe())) {// 单据户名为本人
//				estateInfoVO.setIfMe(EnumCHConstants.Indicator.valueOf("_" + estateInfoVO.getIfMe()).getValue());
				estateInfoVO.setIfMe(demoService.getVaule("Indicator", estateInfoVO.getIfMe()));
			}
			// 房产信息
			assetsInfoVO.setEstateInfoVO(estateInfoVO);
			
			if (!StringUtils.isEmpty(masterLoanInfoVO.getBuyerCreditLevel())) {// 买家信用等级
//				masterLoanInfoVO.setBuyerCreditLevel(EnumCHConstants.SellerCreditLevel.valueOf("_" + masterLoanInfoVO.getBuyerCreditLevel()).getValue());
				masterLoanInfoVO.setBuyerCreditLevel(demoService.getVaule("SellerCreditLevel", masterLoanInfoVO.getBuyerCreditLevel()));
			}
			if (!StringUtils.isEmpty(masterLoanInfoVO.getBuyerCreditType())) {// 买家信用类型
//				masterLoanInfoVO.setBuyerCreditType(EnumCHConstants.SellerCreditType.valueOf("_" + masterLoanInfoVO.getBuyerCreditType()).getValue());
				masterLoanInfoVO.setBuyerCreditType(demoService.getVaule("SellerCreditType", masterLoanInfoVO.getBuyerCreditType()));
			}
			if(!StringUtils.isEmpty(masterLoanInfoVO.getJiDongUserLevel())){
//				masterLoanInfoVO.setJiDongUserLevel(EnumCHConstants.JiDongUserLevel.valueOf("_" + masterLoanInfoVO.getJiDongUserLevel()).getValue());
				masterLoanInfoVO.setJiDongUserLevel(demoService.getVaule("JiDongUserLevel", masterLoanInfoVO.getJiDongUserLevel()));
			}
			// 网购达人贷信息
			assetsInfoVO.setMasterLoanInfoVO(masterLoanInfoVO);
			
			if (!StringUtils.isEmpty(masterLoanInfoAVo.getBuyerCreditLevel())) {// 买家信用等级
//				masterLoanInfoVO.setBuyerCreditLevel(EnumCHConstants.SellerCreditLevel.valueOf("_" + masterLoanInfoVO.getBuyerCreditLevel()).getValue());
				masterLoanInfoAVo.setBuyerCreditLevel(demoService.getVaule("SellerCreditLevel", masterLoanInfoAVo.getBuyerCreditLevel()));
			}
			if (!StringUtils.isEmpty(masterLoanInfoAVo.getBuyerCreditType())) {// 买家信用类型
//				masterLoanInfoVO.setBuyerCreditType(EnumCHConstants.SellerCreditType.valueOf("_" + masterLoanInfoVO.getBuyerCreditType()).getValue());
				masterLoanInfoAVo.setBuyerCreditType(demoService.getVaule("SellerCreditType", masterLoanInfoAVo.getBuyerCreditType()));
			}
			
			assetsInfoVO.setMasterLoanInfoAVO(masterLoanInfoAVo);
			if(!StringUtils.isEmpty(masterLoanInfoBVo.getJiDongUserLevel())){
//				masterLoanInfoVO.setJiDongUserLevel(EnumCHConstants.JiDongUserLevel.valueOf("_" + masterLoanInfoVO.getJiDongUserLevel()).getValue());
				masterLoanInfoBVo.setJiDongUserLevel(demoService.getVaule("JiDongUserLevel", masterLoanInfoBVo.getJiDongUserLevel()));
			}
			
			assetsInfoVO.setMasterLoanInfoBVO(masterLoanInfoBVo);
			if (!StringUtils.isEmpty(merchantLoanInfoVO.getSellerCreditLevel())) { // 卖家信用等级
//				merchantLoanInfoVO.setSellerCreditLevel(EnumCHConstants.SellerCreditLevel.valueOf("_" + merchantLoanInfoVO.getSellerCreditLevel()).getValue());
				merchantLoanInfoVO.setSellerCreditLevel(demoService.getVaule("SellerCreditLevel", merchantLoanInfoVO.getSellerCreditLevel()));
			}
			if (!StringUtils.isEmpty(merchantLoanInfoVO.getSellerCreditType())) {// 卖家信用类型
//				merchantLoanInfoVO.setSellerCreditType(EnumCHConstants.SellerCreditType.valueOf("_" + merchantLoanInfoVO.getSellerCreditType()).getValue());
				merchantLoanInfoVO.setSellerCreditType(demoService.getVaule("TaobaoSellerCreditType", merchantLoanInfoVO.getSellerCreditType()));
			}
			// 淘宝商户贷信息
			assetsInfoVO.setMerchantLoanInfoVO(merchantLoanInfoVO);
			if (!StringUtils.isEmpty(policyInfoVO.getPaymentMethod())) {// 缴费方式
//				policyInfoVO.setPaymentMethod(EnumCHConstants.PaymentMethod.valueOf("_" + policyInfoVO.getPaymentMethod()).getValue());
				policyInfoVO.setPaymentMethod(demoService.getVaule("PaymentMethod", policyInfoVO.getPaymentMethod()));
			}
			if (!StringUtils.isEmpty(policyInfoVO.getPolicyRelation())) {// 与被保险人关系
//				policyInfoVO.setPolicyRelation(EnumCHConstants.PolicyRelation.valueOf("_" + policyInfoVO.getPolicyRelation()).getValue());
				policyInfoVO.setPolicyRelation(demoService.getVaule("PolicyRelation", policyInfoVO.getPolicyRelation()));
			}
			if (!StringUtils.isEmpty(policyInfoVO.getPolicyCheck())) {// 保单真伪核实方式
//				policyInfoVO.setPolicyCheck(EnumCHConstants.PolicyCheck.valueOf("_" + policyInfoVO.getPolicyCheck()).getValue());
				policyInfoVO.setPolicyCheck(demoService.getVaule("PolicyCheck", policyInfoVO.getPolicyCheck()));
			}
			// 保单信息
			assetsInfoVO.setPolicyInfoVO(policyInfoVO);
			if (!StringUtils.isEmpty(providentInfoVO.getProvidentInfo())) {// 公积金材料
//				providentInfoVO.setProvidentInfo(EnumCHConstants.ProvidentInfo.valueOf("_" + providentInfoVO.getProvidentInfo()).getValue());
				providentInfoVO.setProvidentInfo(demoService.getVaule("ProvidentInfo", providentInfoVO.getProvidentInfo()));
			}
			if (!StringUtils.isEmpty(providentInfoVO.getPaymentUnit())) {// 缴纳单位同申请单位
//				providentInfoVO.setPaymentUnit(EnumCHConstants.Indicator.valueOf("_" + providentInfoVO.getPaymentUnit()).getValue());
				providentInfoVO.setPaymentUnit(demoService.getVaule("Indicator", providentInfoVO.getPaymentUnit()));
			}
			// 公积金信息
			assetsInfoVO.setProvidentInfoVO(providentInfoVO);
//			if (!StringUtils.isEmpty(salaryLoanInfoVO.getConditionType())) {// 条件类型
////				salaryLoanInfoVO.setConditionType(EnumCHConstants.ConditionType.valueOf("_" + salaryLoanInfoVO.getConditionType()).getValue());
//				salaryLoanInfoVO.setConditionType(demoService.getVaule("ConditionType", salaryLoanInfoVO.getConditionType()));
//			}
//			// 随薪贷信息
			assetsInfoVO.setSalaryLoanInfoVO(new SalaryLoanInfoVO());
			if (!StringUtils.isEmpty(personInfoVO.getMaritalStatus())) {// 婚姻状况
//				personInfoVO.setMaritalStatus(EnumCHConstants.MaritalStatus.valueOf("_" + personInfoVO.getMaritalStatus()).getValue());
				personInfoVO.setMaritalStatus(demoService.getVaule("MaritalStatus", personInfoVO.getMaritalStatus()));
			}
			if (!StringUtils.isEmpty(personInfoVO.getQualification())) {// 最高学历
//				personInfoVO.setQualification(EnumCHConstants.EducationType.valueOf("_" + personInfoVO.getQualification()).getValue());
				personInfoVO.setQualification(demoService.getVaule("EducationType", personInfoVO.getQualification()));
			}
			if (!StringUtils.isEmpty(personInfoVO.getHouseType())) {// 住宅类型
//				personInfoVO.setHouseType(EnumCHConstants.HouseType.valueOf("_" + personInfoVO.getHouseType()).getValue());
				personInfoVO.setHouseType(demoService.getVaule("HouseType", personInfoVO.getHouseType()));
			}
			// 个人信息
			basicInfoVO.setPersonInfoVO(personInfoVO);
			if (!StringUtils.isEmpty(privateOwnerInfoVO.getPriEnterpriseType())) {// 私营企业类型
//				privateOwnerInfoVO.setPriEnterpriseType(EnumCHConstants.PriEnterpriseType.valueOf("_" + privateOwnerInfoVO.getPriEnterpriseType()).getValue());
				privateOwnerInfoVO.setPriEnterpriseType(demoService.getVaule("PriEnterpriseType", privateOwnerInfoVO.getPriEnterpriseType()));
			}
			if (!StringUtils.isEmpty(privateOwnerInfoVO.getBusinessPlace())) {// 经营场所
//				privateOwnerInfoVO.setBusinessPlace(EnumCHConstants.BusinessPlace.valueOf("_" + privateOwnerInfoVO.getBusinessPlace()).getValue());
				privateOwnerInfoVO.setBusinessPlace(demoService.getVaule("BusinessPlace", privateOwnerInfoVO.getBusinessPlace()));
			}
			// 私营业主信息
			basicInfoVO.setPrivateOwnerInfoVO(privateOwnerInfoVO);
			if (!StringUtils.isEmpty(workInfoVO.getBusinessNetWork())) {// 工商网信息
//				workInfoVO.setBusinessNetWork(EnumCHConstants.BusinessNetWork.valueOf("_" + workInfoVO.getBusinessNetWork()).getValue());
				workInfoVO.setBusinessNetWork(demoService.getVaule("BusinessNetWork", workInfoVO.getBusinessNetWork()));
			}
			if (!StringUtils.isEmpty(workInfoVO.getCorpStructure())) {// 公司性质
//				workInfoVO.setCorpStructure(EnumCHConstants.CorpStructure.valueOf("_" + workInfoVO.getCorpStructure()).getValue());
				workInfoVO.setCorpStructure(demoService.getVaule("CorpStructure", workInfoVO.getCorpStructure()));
			}
			if (!StringUtils.isEmpty(workInfoVO.getCorpType())) {// 公司行业类别
//				workInfoVO.setCorpType(EnumCHConstants.EmpType.valueOf("_" + workInfoVO.getCorpType()).getValue());
				workInfoVO.setCorpType(demoService.getVaule("EmpType", workInfoVO.getCorpType()));
			}
			if (!StringUtils.isEmpty(workInfoVO.getCorpPost())) {// 职务
//				workInfoVO.setCorpPost(EnumCHConstants.NoGovInstitution.valueOf("_" + workInfoVO.getCorpPost()).getValue());
				workInfoVO.setCorpPost(demoService.getVaule("NoGovInstitution", workInfoVO.getCorpPost()));
			}
			if (!StringUtils.isEmpty(workInfoVO.getOccupation())) {// 职业
//				workInfoVO.setOccupation(EnumCHConstants.OccupationType.valueOf("_" + workInfoVO.getOccupation()).getValue());
				workInfoVO.setOccupation(demoService.getVaule("professionType", workInfoVO.getOccupation()));
			}
			logger.info("掉这个查询！！---------------workInfoVO值"+gson.toJson(workInfoVO.getOccupation()));
			if (!StringUtils.isEmpty(workInfoVO.getCusWorkType())) {// 客户工作类型
//				workInfoVO.setCusWorkType(EnumCHConstants.JobType.valueOf("_" + workInfoVO.getCusWorkType()).getValue());
				workInfoVO.setCusWorkType(demoService.getVaule("JobType", workInfoVO.getCusWorkType()));
			}
			if (!StringUtils.isEmpty(workInfoVO.getCorpPayWay())) {// 发薪方式
//				workInfoVO.setCorpPayWay(EnumCHConstants.CorpPayWay.valueOf("_" + workInfoVO.getCorpPayWay()).getValue());
				workInfoVO.setCorpPayWay(demoService.getVaule("CorpPayWay", workInfoVO.getCorpPayWay()));
			}
			// 工作信息
			basicInfoVO.setWorkInfoVO(workInfoVO);
			if (!StringUtils.isEmpty(applytInfoVO.getCustomerSource())) {// 客户来源
//				applytInfoVO.setCustomerSource(EnumCHConstants.CustomerSource.valueOf("_" + applytInfoVO.getCustomerSource()).getValue());
				applytInfoVO.setCustomerSource(demoService.getVaule("CustomerSource",applytInfoVO.getCustomerSource()));
			}
			if (!StringUtils.isEmpty(applytInfoVO.getCreditApplication())) {// 贷款用途
//				applytInfoVO.setCreditApplication(EnumCHConstants.CreditApplication.valueOf("_" + applytInfoVO.getCreditApplication()).getValue());
				applytInfoVO.setCreditApplication(demoService.getVaule("CreditApplication", applytInfoVO.getCreditApplication()));
			}
			// 申请信息
			applyEntryVO.setApplyInfoVO(applytInfoVO);

			assetsInfoVO = setAssetsInfoVOIfEmpty(assetsInfoVO);

			// 资产信息
			applyEntryVO.setAssetsInfoVO(assetsInfoVO);
			// 基本信息
			applyEntryVO.setBasicInfoVO(basicInfoVO);
			for (ContactInfoVO contactInfo : contactInfoVOList) {
				if (!StringUtils.isEmpty(contactInfo.getContactRelation())) { // 与申请人关系
//					contactInfo.setContactRelation(EnumCHConstants.Relationship.valueOf("_" + contactInfo.getContactRelation()).getValue());
					contactInfo.setContactRelation(demoService.getVaule("Relationship", contactInfo.getContactRelation()));
				}
				if (!StringUtils.isEmpty(contactInfo.getIfKnowLoan())) {// 是否知晓贷款
//					contactInfo.setIfKnowLoan(EnumCHConstants.Indicator.valueOf("_" + contactInfo.getIfKnowLoan()).getValue());
					contactInfo.setIfKnowLoan(demoService.getVaule("Indicator", contactInfo.getIfKnowLoan()));
				}
				if (!StringUtils.isEmpty(contactInfo.getContactCorpPost())) {// 职务
//					contactInfo.setContactCorpPost(EnumCHConstants.EmpPositionAttrType.valueOf("_" + contactInfo.getContactCorpPost()).getValue());
					contactInfo.setContactCorpPost(demoService.getVaule("EmpPositionAttrType", contactInfo.getContactCorpPost()));
				}
				if(null!=contactInfo){
					if(null!=contactInfo.getIfForeignPenple()){
						 if(contactInfo.getIfForeignPenple().equals("Y")){
							contactInfo.setIfForeignPenple("是");
						}else{
							contactInfo.setIfForeignPenple("否");
						}
					}
				}
			}
		} catch (IllegalArgumentException e) {
			throw new BizException(BizErrorCode.ENUM_EOERR, new Object[] { e.getMessage() });
		}
		// 联系人信息
		applyEntryVO.setContactInfoVOList(contactInfoVOList);

		Response<ApplyEntryVO> resApplyEntryVo = new Response<ApplyEntryVO>();
		resApplyEntryVo.setData(applyEntryVO);
		logger.info("掉这个查询！！---------------resApplyEntryVo值"+gson.toJson(resApplyEntryVo));
		return resApplyEntryVo;
	}

	/**
	 * 解读征信报告
	 * 
	 * @param personHistoryLoanVO
	 * @return
	 */
	public boolean getCreditInfo(PersonHistoryLoanVO personHistoryLoanVO) {
		boolean res = true;
		
		Map<String, Object> appDataMap = new HashMap<String, Object>();

		try {
			appDataMap.put("customerIdCard", personHistoryLoanVO.getIdNo());// 身份证
			appDataMap.put("customerName", personHistoryLoanVO.getName());// 用户名
			appDataMap.put("reportId", personHistoryLoanVO.getReportId());// 央行报告id
			logger.info("解析央行报告入参：" + gson.toJson(appDataMap));
			JSONObject resObject = creditHttpService.getBadCreditInfo(appDataMap);
			logger.info("解析央行报告出参："+gson.toJson(resObject));
			
			if ("000000".equals(resObject.getString("code"))){
				String status = resObject.getString("status");
				// 信用不良
				if ("NO".equals(status)) {
					res = false;
				}         
		    }
		} catch (Exception e) {
			logger.info("---------------解析央行报告成功异常：" + e.getMessage());
			return res;
		}
		return res;
	}
	
	
	/**
	 * 获取央行征信报告
	 * 
	 * @param personHistoryLoanVO
	 * @return
	 */
	public String fixedCreditReportNotAppNo(PersonHistoryLoanVO personHistoryLoanVO) {
		Map<String, Object> appDataMap = new HashMap<String, Object>();

		String msg = "";
		try {
			appDataMap.put("customerIdCard", personHistoryLoanVO.getIdNo());// 身份证
			appDataMap.put("customerName", personHistoryLoanVO.getName());// 用户名
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			appDataMap.put("queryDate", df.format(new Date()));// 首次录入复核时间
			JSONObject resObject = creditHttpService.queryBMSReportNotAppNo(appDataMap);
			if (resObject.has("code") && "000000".equals(resObject.getString("code"))) { // 查询成功，并且有报告
				msg = " --------------- 申请件号为【" + personHistoryLoanVO.getLoanNo() + "查询央行报告成功，将返回的央行征信报告id[" + resObject.get("reportId") + "]";
				logger.info(msg);
				return resObject.get("reportId").toString();
			} else {
				msg = "--------------- 申请件号为【" + personHistoryLoanVO.getLoanNo() + "查询央行报告失败，失败原因: " + resObject.get("messages");
				logger.info(msg);
				return null;
			}
		} catch (Exception e) {
			logger.info("---------------申请件号为【" + personHistoryLoanVO.getLoanNo() + "查询央行报告成功异常：" + e.getMessage());
			return null;
		}
	}
	/**
	 * 获取央行征信报告状态
	 * 000000为有记录，000001未查询到记录，000002报告过期，999999缺少参数或异常
	 * @param personHistoryLoanVO
	 * @return
	 */
	@Override
	public String fixedCreditReportByStatus( PersonHistoryLoanVO personHistoryLoanVO) {
		Map<String, Object> appDataMap = new HashMap<String, Object>();
		String msg = "";
		try {
			appDataMap.put("customerIdCard", personHistoryLoanVO.getIdNo());// 身份证
			appDataMap.put("customerName", personHistoryLoanVO.getName());// 用户名
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			appDataMap.put("queryDate", df.format(new Date()));// 首次录入复核时间
			JSONObject resObject = creditHttpService.queryBMSReportNotAppNo(appDataMap);
			if (resObject.has("code") && "000000".equals(resObject.getString("code"))) { // 查询成功，并且有报告
				msg = " --------------- 申请件号为【" + personHistoryLoanVO.getLoanNo() + "查询央行报告成功，将返回的央行征信报告id[" + resObject.get("reportId") + "]";
				logger.info(msg);
				return resObject.get("reportId").toString();
			}else if(resObject.has("code") && "000002".equals(resObject.getString("code"))){
				msg = " --------------- 申请件号为【" + personHistoryLoanVO.getLoanNo() + "查询央行报告过期，将返回的央行征信报告id[" + resObject.get("reportId") + "]";
				logger.info(msg);
				return "overdue";
			} else {
				msg = "--------------- 申请件号为【" + personHistoryLoanVO.getLoanNo() + "查询央行报告失败，失败原因: " + resObject.get("messages");
				logger.info(msg);
				return null;
			}
		} catch (Exception e) {
			logger.info("---------------申请件号为【" + personHistoryLoanVO.getLoanNo() + "查询央行报告成功异常：" + e.getMessage());
			return null;
		}
	}

	public void query(String name, String idName, String loanNo) {
		boolean creditGood = true;
		
		// 录入复核提交绑定 人行征信报告
		PersonHistoryLoanVO personHistoryLoanVO = new PersonHistoryLoanVO();
		personHistoryLoanVO.setSysCode(EnumConstants.BMS_SYSCODE);
		personHistoryLoanVO.setName(name);
		personHistoryLoanVO.setIdNo(idName);
		String fixedCreditReport = fixedCreditReportNotAppNo(personHistoryLoanVO);
		// 无征信报告
		if (fixedCreditReport != null) {
			personHistoryLoanVO.setReportId(fixedCreditReport);
			boolean creditInfo = getCreditInfo(personHistoryLoanVO);
			//true 信用良好或者无综合信用信息
			if(creditInfo){
				
			}
		}
		
		
		

		/*if ("000000".equals(resCode)){
        //有征信报告
        hasCreditReport = "1";
        applyState="1";
        
        //APP获取央行征信信用判断结果
        String reportId = objZX.getString("reportId");
        
        String requestUrlZXResult = creditZxProperties.getServiceUrl() + "/creditReport/getCreditInfo";
        JSONObject paramZXResult = new JSONObject();
        paramZXResult.put("customerName", vo_600005.getName());
        paramZXResult.put("customerIdCard", idCardNo);
        paramZXResult.put("reportId", reportId);
        paramZXResult.put("queryDate",df.format(new Date()));
        paramZXResult.put("timestamp", date);
        
        JSONObject paramZXgc = new JSONObject();
        paramZXgc.put("param", paramZXResult.toString());
        String resultZXResult = httpUrlConnection.postForEntity(requestUrlZXResult, paramZXgc, String.class);
        JSONObject objZXResult = JSONObject.parseObject(resultZXResult);
        
        logger.info("----600005 getCreditInfo 返回: "+resultZXResult);
        
        String code = objZXResult.getString("code");
        
        if ("000000".equals(code)){
          String type = objZXResult.getString("type");
          String applyType = obj.getString("applyType");
          
          JSONArray bHProductList = obj.getJSONArray("bHProductList");
          
          //符合无综合信用条件
          if ("YES".equals(type) && !"RELOAN".equals(applyType) && !bHProductList.contains(productCd)){
            promptZXMessage = "初判客户是征信白户，并且不是结清再贷客户，申请的产品除"+obj.getString("bHProductStrs")+"外均不允许提交，请检查客户申请的产品类型";
            applyState="0";
            isThrowE = true;
          }else{
            String status = objZXResult.getString("status");
            //信用不良
            if ("NO".equals(status)){
              String data = objZXResult.getString("data");
              //逾期
              if (data != null){
                promptZXMessage = data;
              }else{
                promptZXMessage = "初判客户信用不良";
              }
            }
          }          
        }
        
      }else{
        //无征信报告
        hasCreditReport = "0";
        applyState="0";
      }*/

	}
	
	//更新用户通话详单状态
	public 	APPPersonInfoEntity updateCellRecordState(APPPersonInfoEntity appPersonInfoEntity,long loanBaseId){
		LoanBaseEntity baseEnity=loanBaseService.getById(loanBaseId);
		APPPersonInfoEntity appPersionInfoOLd=appPersonInfoService.getById(loanBaseId);
		String inputFlag=baseEnity.getAppInputFlag();
		if(appPersionInfoOLd!=null&&"app_input".equals(inputFlag)){
			if(org.apache.commons.lang.StringUtils.isNotEmpty(appPersonInfoEntity.getCellphone())){
				if(!appPersonInfoEntity.getCellphone().equals(appPersionInfoOLd.getCellphone())){
					appPersonInfoEntity.setCellPhoneStatus("2");
				}
			}
			if(org.apache.commons.lang.StringUtils.isNotEmpty(appPersonInfoEntity.getCellphoneSec())){
				if(!appPersonInfoEntity.getCellphoneSec().equals(appPersionInfoOLd.getCellphoneSec())){
					appPersonInfoEntity.setCellPhoneSecStatus("2");
				}
			}
		}
		return appPersonInfoEntity;
	}
	
	/**
	 * 根据工作类型自动填充职业
	 * @param cusWorkType
	 * @return
	 */
	public String getOccupation(String cusWorkType){
		if(cusWorkType == null){
			return null;
		}
		if("00001".equals(cusWorkType)){//私营业主
			return "00003";// 00003 自营
		}else if("00002".equals(cusWorkType)){//受薪人士
			return "00001";// 00001 工薪
		}else if("00003".equals(cusWorkType)){//自雇人士
			return "00003";// 00003 自营
		}
		return null;
	}
	
	@Override
	public LoanAuditEntity findByAudit(PersonHistoryLoanVO personHistoryLoanVO) {
		return iLoanAuditService.findByAudit(personHistoryLoanVO);
	}

	@Override
	public List<LoanBaseEntity> findLoanNoByNotBelongTo(PersonHistoryLoanVO personHistoryLoanVO) {
		return loanBaseService.findLoanNoByNotBelongTo(personHistoryLoanVO);
	}

	@Override
	public BMSProduct findProductData(String productCd) {
		return loanProductService.findProductData(productCd);
	}

	@Override
	public Map<String, Object> findRadioByApplyType(String applyType) {
		return loanBaseService.findRadioByApplyType(applyType);
	}

	@SuppressWarnings("unchecked")
	public boolean ifReloanGreaterThan12MonthsUser(String name,String idNo) {
		if(StringUtils.isEmpty(idNo)){
			return false;
		}
		try {
			String loanNo = "";
			String createTime = null;
			// 查看是否
			Map<String, Object> httpParamMap = new HashMap<String, Object>();
			httpParamMap.put("idnum", idNo);
			System.out.println("===========>调用核心查询数据该用户是否有借款信息:" + gson.toJson(httpParamMap));
			HttpResponse queryLoan = coreHttpService.loanStatus(httpParamMap);
			Map<String, Object> contentMap = JsonUtils.decode(queryLoan.getContent(), Map.class);
			if (contentMap.get("code").equals("000000")) {
				if (contentMap.get("msg").equals("该用户不在系统中")) {
					return false;
				}

				List<Map<String, String>> resQueryLoanVo = (List<Map<String, String>>) contentMap.get("loan");
				if (resQueryLoanVo != null && resQueryLoanVo.size() > 0) {
					for (Map<String, String> loanBaseVo : resQueryLoanVo) {
						if ("正常".equals(loanBaseVo.get("loanState"))) {
							return false;
						} else if ("结清".equals(loanBaseVo.get("loanState"))) {
							//确定保留最近一笔  
							if (StringUtils.isEmpty(loanNo)) {
								loanNo = loanBaseVo.get("loanNo");
								createTime = loanBaseVo.get("createTime");
								//结清日期
								createTime = DateUtil.defaultFormatDay(DateUtil.strToDateTimeDay(createTime));
							}
						}
					}
				}
			} else {
				return false;
			}
			if (!StringUtils.isEmpty(loanNo)) {
				Map<String, Object> httpParamMap1 = new HashMap<String, Object>();
				httpParamMap1.put("name", name);
				httpParamMap1.put("idnum", idNo);
				HttpResponse findHistoryLoan = coreHttpService.findHistoryLoan(httpParamMap1);
				
				
				logger.info("" + gson.toJson(findHistoryLoan));
				Map<String, Object> contentMap1 = JsonUtils.decode(findHistoryLoan.getContent(), Map.class);
				if (contentMap1.get("code").equals("000000")) {
					if (contentMap1.get("message").equals("该用户不在系统中")) {
						return false;
					} else {
						FindHistoryLoanVO decode = JsonUtils.decode(gson.toJson(contentMap1.get("infos")), FindHistoryLoanVO.class);
						logger.info("查询历史数据返回 :" + gson.toJson(decode));
						
						int count = 0;
						
						if (decode.getLoanList() != null && decode.getLoanList().size() > 0) {
							RepayListVo loanListVal = decode.getLoanList().get(0);
							for (RepayVo repayVo : loanListVal.getRepayList()) {
								//实际还款日期
								String realityRepaymentDate = repayVo.getRealityRepaymentDate();
								
								if(!StringUtils.isEmpty(realityRepaymentDate) && !StringUtils.isEmpty(createTime)
										&& !realityRepaymentDate.equals(createTime)){
									count ++;
								}
							}
						}
						
						if(count >= 11){
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			logger.info("根据身份证["+idNo+"]，校验是否还款期数大于12期异常："+e.getLocalizedMessage());
			return false;
		}
		return false;
	}
	
	


}
