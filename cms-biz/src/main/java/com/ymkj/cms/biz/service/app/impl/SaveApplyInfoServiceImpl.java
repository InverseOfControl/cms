package com.ymkj.cms.biz.service.app.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600001;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600003;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.common.util.ApplyEnterEncapsulate;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.IDCardValidateUtil;
import com.ymkj.cms.biz.dao.app.ISaveApplyInfoDao;
import com.ymkj.cms.biz.dao.apply.LoanBaseDao;
import com.ymkj.cms.biz.dao.apply.LoanExtDao;
import com.ymkj.cms.biz.dao.sign.ILoanContractSignDao;
import com.ymkj.cms.biz.entity.app.BMSSaveApplyInfoEntity;
import com.ymkj.cms.biz.entity.app.asset.AppCarInfo;
import com.ymkj.cms.biz.entity.app.asset.AppCardLoanInfo;
import com.ymkj.cms.biz.entity.app.asset.AppEstateInfo;
import com.ymkj.cms.biz.entity.app.asset.AppMasterLoanInfo;
import com.ymkj.cms.biz.entity.app.asset.AppMerchantLoanInfo;
import com.ymkj.cms.biz.entity.app.asset.AppPolicyInfo;
import com.ymkj.cms.biz.entity.app.asset.AppProvidentInfo;
import com.ymkj.cms.biz.entity.app.input.AppAppContactHeadEntity;
import com.ymkj.cms.biz.entity.app.input.AppAppContactInfoEntity;
import com.ymkj.cms.biz.entity.app.input.AppAppPersonEntity;
import com.ymkj.cms.biz.entity.app.input.AppAppPersonInfoEntity;
import com.ymkj.cms.biz.entity.app.input.AppLoanBaseEntity;
import com.ymkj.cms.biz.entity.app.input.AppLoanExtEntity;
import com.ymkj.cms.biz.entity.app.input.AppLoanProductEntity;
import com.ymkj.cms.biz.entity.apply.APPWhitePersonnelEntity;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.apply.LoanBaseRelaEntity;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.sign.BMSLoanProduct;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.app.AppApplyUtil;
import com.ymkj.cms.biz.service.app.ISaveApplyInfoService;
import com.ymkj.cms.biz.service.apply.APPWhitePersonnelService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.master.IBMSTMReasonService;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;
import com.ymkj.pms.biz.api.vo.response.ResRoleVO;

@Service
public class SaveApplyInfoServiceImpl extends BaseServiceImpl<BMSSaveApplyInfoEntity> implements ISaveApplyInfoService{

	private Log log = LogFactory.getLog(SaveApplyInfoServiceImpl.class);
	public static final String INSERT_LOG_FLAG = "SaveApplyInfoServiceImpl.insertApplyInfo：";
	public final String MARITAL_STATUS = "00002@00006@00007"; //已婚、复婚、再婚

	@Autowired
	private ISaveApplyInfoDao iSaveApplyInfoDao;

	@Autowired
	private IEmployeeExecuter iEmployeeExecuter;
	
	@Autowired
	private APPWhitePersonnelService appWhitePerSonnelService;

	@Autowired
	private LoanExtDao loanExtDao;
	
	@Autowired
	private LoanBaseDao loanBaseDao;
	
	@Autowired
	private IBMSSysLoanLogService ibmsSysLoanLogService;
	
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	
	@Autowired
	private IBMSTMReasonService iBMSTMReasonService;
	
	@Autowired
	private ILoanContractSignDao loanContractSignDao;
	
	@Value("#{env['averageStartTime']?:''}")
	private String averageStartTime;
	
	
	@Autowired
	private IOrganizationExecuter OrganizationExecuter;
	
	@Override
	protected BaseDao<BMSSaveApplyInfoEntity> getDao() {
		return iSaveApplyInfoDao;
	}
	
	/***
	 * @Description:获取员工信息</p>
	 * @uthor YM10159
	 * @date 2017年8月2日 下午2:35:13
	 */
	public ResEmployeeVO getEmployee(String userCode){
		//获取员工信息
		ReqEmployeeVO reqEmployeeVO = new ReqEmployeeVO();
		reqEmployeeVO.setUsercode(userCode);
		reqEmployeeVO.setSysCode("bms");
		
		Response<ResEmployeeVO> responseEmployee = iEmployeeExecuter.findByAccount(reqEmployeeVO);
		if(null == responseEmployee){
			log.info(INSERT_LOG_FLAG+"获取【员工信息】失败！【接口返回：null】");
			throw new CoreException(CoreErrorCode.FACADE_ERROR.getErrorCode(),CoreErrorCode.FACADE_ERROR.getDefaultMessage());
		}

		ResEmployeeVO resEmployeeVO = responseEmployee.getData();
		if(null == resEmployeeVO){
			log.info(INSERT_LOG_FLAG+"获取【员工信息为空】！");
			throw new CoreException(CoreErrorCode.DEFAULT.getErrorCode(),CoreErrorCode.DEFAULT.getDefaultMessage());
		}
				
		return resEmployeeVO;
	}
	
	/**
	 * @Description:获取员工所在机构</p>
	 * @uthor YM10159
	 * @date 2017年8月2日 下午2:36:23
	 */
	public ResOrganizationVO getorganization(String userCode){
		//获取员工信息
		ReqEmployeeVO reqEmployeeVO = new ReqEmployeeVO();
		reqEmployeeVO.setUsercode(userCode);
		reqEmployeeVO.setSysCode("bms");
		
		Response<List<ResOrganizationVO>> organizationResponse = iEmployeeExecuter.findDeptsByAccount(reqEmployeeVO);
		if(null == organizationResponse){
			log.info(INSERT_LOG_FLAG+"获取【组织机构信息】失败！【接口返回：null】");
			throw new CoreException(CoreErrorCode.FACADE_ERROR.getErrorCode(),CoreErrorCode.FACADE_ERROR.getDefaultMessage());
		}
		
		List<ResOrganizationVO> organizationList = organizationResponse.getData();
		if(CollectionUtils.isEmpty(organizationList)){
			log.info(INSERT_LOG_FLAG+"获取【组织机构信息为空】！");
			throw new CoreException(CoreErrorCode.DEFAULT.getErrorCode(),CoreErrorCode.DEFAULT.getDefaultMessage());
		}
		return organizationList.get(0);
	}
	
	/**
	 * 插入申请信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Response<Object> insertApplyInfo(Map<String,Object> allApplyInfoMap) throws Exception {
		Response<Object> response = new Response<>();

		AppAppPersonEntity appAppPersonEntity = null; //客户主表
		AppLoanBaseEntity appLoanBaseEntity = null; //申请主表
		AppLoanProductEntity appLoanProductEntity = null; //申请产品表
		AppLoanExtEntity appLoanExtEntity = null; //申请扩展表
		AppAppPersonInfoEntity appAppPersonInfoEntity = null; //申请人信息表
		AppAppContactHeadEntity appAppContactHeadEntity = null; //联系人主表
		AppAppContactInfoEntity appAppContactInfoEntity = null; //联系人信息表

		Map<String,Serializable> applyInfoMap = (Map<String, Serializable>) allApplyInfoMap.get("applyInfo"); //申请信息
		Map<String,Serializable> persionInfoMap = (Map<String, Serializable>) allApplyInfoMap.get("persionInfo"); //个人信息
		Map<String,Serializable> empItemInfoMap = (Map<String, Serializable>) allApplyInfoMap.get("empItemInfo"); //工作信息
		Map<String,Serializable> baseItemInfoMap = (Map<String, Serializable>) allApplyInfoMap.get("baseItemInfo"); //私营业主信息
		ArrayList<HashMap<String,Serializable>> contactPersonInfoList = (ArrayList<HashMap<String, Serializable>>) allApplyInfoMap.get("contactPersonInfo"); //联系人信息列表
		Map<String,Serializable> mateInfoMap = (Map<String, Serializable>) allApplyInfoMap.get("mateInfo"); //配偶信息
		
		String idNo = ObjectUtils.toString(applyInfoMap.get("idNo"));
        String reasonCode = ObjectUtils.toString(allApplyInfoMap.get("refuseReason"));//原因code
        
		String isMoved = ObjectUtils.toString(applyInfoMap.get("isMoved"));
		String userCode = ObjectUtils.toString(allApplyInfoMap.get("userCode"));
		String loanNo =  ObjectUtils.toString(allApplyInfoMap.get("loanNo")); //loanBaseDao.createLoanNo(new Date()); //生成loanNo
		String personNo = ApplyEnterEncapsulate.createPersonNo(new Date()); //生成personNo
		String applyDate = ObjectUtils.toString(allApplyInfoMap.get("applyDate"));
		log.info("借款编号【"+loanNo+"】"+INSERT_LOG_FLAG+"开始");
		
		Long personId = 0L; //客户主表ID
		Long loanBaseId = 0L; //申请主表ID
		Long creatorId = 0L; //创建人ID
		String creator = ""; //创建人
		
		String serviceCode = "";
		String serviceName = "";
		String branchManagerCode = "";
		String branchManagerName = "";
		
		//获取员工信息
		ResEmployeeVO resEmployeeVO = getEmployee(userCode);
		creatorId = resEmployeeVO.getId();
		creator = ObjectUtils.toString(resEmployeeVO.getName());
		
		//获取机构信息
		ResOrganizationVO resOrganizationVO = getorganization(userCode);
		Long orgId = resOrganizationVO.getId();
		String orgName = resOrganizationVO.getName();
		
		//撤销不记录客服
		if(StringUtils.isBlank(reasonCode)){
		//获取客服信息
		if(StringUtils.isBlank(isMoved)){//客服未认领，分派客服
			Map<String,Object> serviceMap = this.getEffectiveEmployeeV1(orgId,loanNo);
			serviceCode = ObjectUtils.toString(serviceMap.get("nextServiceCode"));
			serviceName = ObjectUtils.toString(serviceMap.get("nextServiceName"));
			branchManagerCode = userCode;
			branchManagerName = creator;
		}else{//客服认领
			serviceCode = userCode;
			serviceName = creator;
			//获取客服经理信息
			ResEmployeeVO resBranchManager = getEmployee(ObjectUtils.toString(applyInfoMap.get("branchManager")));
			branchManagerCode = ObjectUtils.toString(applyInfoMap.get("branchManager"));
			branchManagerName = ObjectUtils.toString(resBranchManager.getName());
		}
		}else{//撤销只记录客户经理信息
			//获取客服经理信息
			ResEmployeeVO resBranchManager = getEmployee(ObjectUtils.toString(applyInfoMap.get("branchManager")));
			branchManagerCode = ObjectUtils.toString(applyInfoMap.get("branchManager"));
			branchManagerName = ObjectUtils.toString(resBranchManager.getName());
		}
		
		//插入客户主表
		appAppPersonEntity = new AppAppPersonEntity(personNo, creatorId, userCode);
		appAppPersonEntity.setName(ObjectUtils.toString(applyInfoMap.get("name")));
		appAppPersonEntity.setIdNo(ObjectUtils.toString(applyInfoMap.get("idNo")));
		appAppPersonEntity.setIdType("身份证");

		iSaveApplyInfoDao.insertAppPerson(appAppPersonEntity);
		log.info(INSERT_LOG_FLAG+"插入【bms_app_person】表成功！");
		
		personId = appAppPersonEntity.getId();

		//插入申请主表
		appLoanBaseEntity = new AppLoanBaseEntity(personId, loanNo, creatorId, userCode);
		
		appLoanBaseEntity.setIdNo(ObjectUtils.toString(applyInfoMap.get("idNo")));
		appLoanBaseEntity.setName(ObjectUtils.toString(applyInfoMap.get("name")));
		appLoanBaseEntity.setRemark(ObjectUtils.toString(applyInfoMap.get("remark")));
		appLoanBaseEntity.setBranchManagerCode(branchManagerCode);
		appLoanBaseEntity.setBranchManagerName(branchManagerName);
		appLoanBaseEntity.setOwningBranch(orgName);
		if(!StringUtils.isBlank(isMoved)){
			appLoanBaseEntity.setIsAppClaim(EnumConstants.YES);
		}
		appLoanBaseEntity.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
		appLoanBaseEntity.setRtfState(EnumConstants.RtfState.SQLR.getValue());
		appLoanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_SAVE.getValue());
		appLoanBaseEntity.setOwningBranchId(orgId);
		appLoanBaseEntity.setApplyType("NEW");
		appLoanBaseEntity.setAppApplyDate(DateUtil.strToDate(applyDate, "yyyy-MM-dd HH:mm:ss"));
		appLoanBaseEntity.setServiceCode(serviceCode);
		appLoanBaseEntity.setServiceName(serviceName);
		
		//查询客户经理的组id
		ReqParamVO reqParamVO = new ReqParamVO();
		reqParamVO.setSysCode(EnumConstants.BMS_SYSCODE);
		reqParamVO.setUsercode(branchManagerCode);
		Response<List<ResOrganizationVO>> findGroupByAccount = OrganizationExecuter.findGroupByAccount(reqParamVO);
		if (!findGroupByAccount.isSuccess() || findGroupByAccount.getData().size() == 0) {
			log.info("根据客户经理code[" + branchManagerCode + "]，未查询到该用户的组织信息");
			response.setRepCode(BizErrorCode.EOERR.getErrorCode());
			response.setRepMsg("该客户经理没有分配组信息！");
			return response;
		}
		//插入业务主任
		ReqParamVO param1 = new ReqParamVO();
		param1.setOrgId(findGroupByAccount.getData().get(0).getId());
		param1.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		param1.setRoleCodes(new ArrayList<String>(){{add("director");}});
		param1.setStatus(0);// 可用
		param1.setInActive("t");
		Response<List<ResEmployeeVO>>  employeeResponse = iEmployeeExecuter.findByDeptAndRole(param1);
		if(null == employeeResponse){
			log.info(INSERT_LOG_FLAG+"获取【业务主任信息】失败！【接口返回：null】");
		}else{
			List<ResEmployeeVO> employeeList = employeeResponse.getData();
			if(CollectionUtils.isEmpty(employeeList)){
				log.info(INSERT_LOG_FLAG+"获取【业务主任信息】为空！");
			}else{
				appLoanBaseEntity.setDirector(employeeList.get(0).getName());
				appLoanBaseEntity.setDirectorCode(employeeList.get(0).getUsercode());
			}
		}
		iSaveApplyInfoDao.updateLoanBase(appLoanBaseEntity);
		log.info(INSERT_LOG_FLAG+"更新【bms_loan_base】表成功！");
		
		//根据借款编号查询id
		loanBaseId = iSaveApplyInfoDao.findIdByLoanNo(loanNo);

		//插入申请产品表
		appLoanProductEntity = new AppLoanProductEntity(loanBaseId, loanNo, creatorId, userCode);
		
		appLoanProductEntity.setProductCd(ObjectUtils.toString(applyInfoMap.get("productCd")));
		appLoanProductEntity.setProductName(ObjectUtils.toString(applyInfoMap.get("productCd1")));
		appLoanProductEntity.setApplyLmt(new BigDecimal(ObjectUtils.toString(applyInfoMap.get("applyLmt"))));
		appLoanProductEntity.setApplyTerm(Integer.valueOf(ObjectUtils.toString(applyInfoMap.get("applyTerm1"))));
		appLoanProductEntity.setApplyRate((Double.valueOf(ObjectUtils.toString(applyInfoMap.get("applyRate")))));

		//历史查询
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
		BMSLoanProduct oldLoanProductEntity = loanContractSignDao.findBMSLoanProductByLoanNo(param);
		if(oldLoanProductEntity == null){
			iSaveApplyInfoDao.insertLoanProduct(appLoanProductEntity);
		} else {
			response.setRepMsg("该借款的产品信息已存在!");
			response.setData(loanNo);
			return response;
		} 
		
		log.info(INSERT_LOG_FLAG+"插入【bms_loan_product】表成功！");

		//插入申请扩展表
		appLoanExtEntity = new AppLoanExtEntity(loanBaseId, loanNo, creatorId, userCode);
		appLoanExtEntity.setCreditApplication(ObjectUtils.toString(applyInfoMap.get("creditApplication")));
		appLoanExtEntity.setCustomerSource(ObjectUtils.toString(applyInfoMap.get("customerSource")));
		
		ReqParamVO paramv = new ReqParamVO();
		paramv.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		paramv.setLoginUser(userCode);
		
		/**根据userCode获取机构信息*/
		Response<ResOrganizationVO> resArea = iEmployeeExecuter.findAreaByAccount(paramv);
		ResOrganizationVO resAreaVO = resArea.getData();
		
		appLoanExtEntity.setLoggedArea(ObjectUtils.toString(resAreaVO.getOrgCode()));
		appLoanExtEntity.setLoggedAreaName(ObjectUtils.toString(resAreaVO.getName()));
		iSaveApplyInfoDao.insertLoanExt(appLoanExtEntity);
		log.info(INSERT_LOG_FLAG+"插入【bms_loan_ext】表成功！");

		//插入客户信息表
		appAppPersonInfoEntity = new AppAppPersonInfoEntity(loanBaseId, personId, loanNo, creatorId, userCode);
		if(null != persionInfoMap && !persionInfoMap.isEmpty()){
			
			if((null != empItemInfoMap && !empItemInfoMap.isEmpty())){
				persionInfoMap.putAll(empItemInfoMap);
				//客户工作类型
				String cusWorkType = ObjectUtils.toString(empItemInfoMap.get("cusWorkType"));
				if(cusWorkType.equals("00001")){
					persionInfoMap.putAll(baseItemInfoMap);
					appAppPersonInfoEntity.setPrivateOwnersFlag("Y");
				}else{
					appAppPersonInfoEntity.setPrivateOwnersFlag("N");
				}
			}
			
			BeanUtils.populate(appAppPersonInfoEntity, persionInfoMap);
			
			//户籍省市区
			String issZoneLinkage = ObjectUtils.toString(persionInfoMap.get("issZone-Linkage"));//户籍
			if(StringUtils.isNotBlank(issZoneLinkage)){
				String[] issZoneLinkageArr = AppApplyUtil.convertAreaCode(issZoneLinkage,iSaveApplyInfoDao.getAreaInfo(issZoneLinkage));
				appAppPersonInfoEntity.setIssuerStateId(issZoneLinkage.substring(0, 2)+"0000");
				appAppPersonInfoEntity.setIssuerCityId(issZoneLinkage.substring(0, 4)+"00");
				appAppPersonInfoEntity.setIssuerZoneId(issZoneLinkage);
				appAppPersonInfoEntity.setIssuerState(issZoneLinkageArr[0]);
				appAppPersonInfoEntity.setIssuerCity(issZoneLinkageArr[1]);
				appAppPersonInfoEntity.setIssuerZone(issZoneLinkageArr[2]);
			}
			//家庭省市区
			String homeZoneLinkage = ObjectUtils.toString(persionInfoMap.get("homeZone-Linkage"));//户籍
			if(StringUtils.isNotBlank(homeZoneLinkage)){
				String[] homeZoneLinkageArr = AppApplyUtil.convertAreaCode(homeZoneLinkage,iSaveApplyInfoDao.getAreaInfo(homeZoneLinkage));
				appAppPersonInfoEntity.setHomeStateId(homeZoneLinkage.substring(0, 2)+"0000");
				appAppPersonInfoEntity.setHomeCityId(homeZoneLinkage.substring(0, 4)+"00");
				appAppPersonInfoEntity.setHomeZoneId(homeZoneLinkage);
				appAppPersonInfoEntity.setHomeState(homeZoneLinkageArr[0]);
				appAppPersonInfoEntity.setHomeCity(homeZoneLinkageArr[1]);
				appAppPersonInfoEntity.setHomeZone(homeZoneLinkageArr[2]);
			}
			
			//公司省市区
			String corpZoneLinkage = ObjectUtils.toString(persionInfoMap.get("corpZone-Linkage"));//户籍
			if(StringUtils.isNotBlank(corpZoneLinkage)){
				String[] corpZoneLinkageArr = AppApplyUtil.convertAreaCode(corpZoneLinkage,iSaveApplyInfoDao.getAreaInfo(corpZoneLinkage));
				appAppPersonInfoEntity.setCorpProvinceId(corpZoneLinkage.substring(0, 2)+"0000");
				appAppPersonInfoEntity.setCorpCityId(corpZoneLinkage.substring(0, 4)+"00");
				appAppPersonInfoEntity.setCorpZoneId(corpZoneLinkage);
				appAppPersonInfoEntity.setCorpProvince(corpZoneLinkageArr[0]);
				appAppPersonInfoEntity.setCorpCity(corpZoneLinkageArr[1]);
				appAppPersonInfoEntity.setCorpZone(corpZoneLinkageArr[2]);
			}
		}
		
		appAppPersonInfoEntity.setGender(AppApplyUtil.getGenderByIdNo(idNo));
		appAppPersonInfoEntity.setBirthday(AppApplyUtil.getBirthDayByIdNo(idNo));
		appAppPersonInfoEntity.setAge(AppApplyUtil.getAgeByIdNo(idNo).toString());
		appAppPersonInfoEntity.setName(ObjectUtils.toString(applyInfoMap.get("name")));
		appAppPersonInfoEntity.setIdNo(ObjectUtils.toString(applyInfoMap.get("idNo")));
		
		String graduationDate = ObjectUtils.toString(appAppPersonInfoEntity.getGraduationDate());
		String corpStandFrom = ObjectUtils.toString(appAppPersonInfoEntity.getCorpStandFrom());
		String setupDate = ObjectUtils.toString(appAppPersonInfoEntity.getSetupDate());
		appAppPersonInfoEntity.setGraduationDate(StringUtils.isNotBlank(graduationDate) ? graduationDate+"-01" : "");
		appAppPersonInfoEntity.setCorpStandFrom(StringUtils.isNotBlank(corpStandFrom) ? corpStandFrom+"-01" : "");
		appAppPersonInfoEntity.setSetupDate(StringUtils.isNotBlank(setupDate) ? setupDate+"-01" : "");
		
		//家庭地址是否同户籍地址转换
		String homeSameAddr = appAppPersonInfoEntity.getHomeSameIdIssuerAddress();
		appAppPersonInfoEntity.setHomeSameIdIssuerAddress(homeSameAddr.equals("Y") ? "0" : "1");
		
		iSaveApplyInfoDao.insertAppPersonInfo(appAppPersonInfoEntity);
		log.info(INSERT_LOG_FLAG+"插入【bms_app_person_info】表成功！");

		//插入联系人主表
		appAppContactHeadEntity = new AppAppContactHeadEntity(loanBaseId, personId, loanNo, creatorId, userCode);

		iSaveApplyInfoDao.insertContactHead(appAppContactHeadEntity);
		String headId = appAppContactHeadEntity.getId();
		log.info(INSERT_LOG_FLAG+"插入【bms_tm_app_contact_head】表成功！");

		//插入联系人信息表
		List<AppAppContactInfoEntity> tempContactPersonInfoList = new LinkedList<>();
		//封装配偶信息
		String maritalStatus = ObjectUtils.toString(appAppPersonInfoEntity.getMaritalStatus());
		String mateInfoFlag = "00002|00006|00007"; //已婚|再婚|复婚
		if(mateInfoFlag.indexOf(maritalStatus) != -1){
			appAppContactInfoEntity = new AppAppContactInfoEntity(loanBaseId, personId, loanNo, creatorId, userCode);
			if(null != mateInfoMap && !mateInfoMap.isEmpty()){
				BeanUtils.populate(appAppContactInfoEntity, mateInfoMap);
			}else{
				appAppContactInfoEntity.setContactRelation(maritalStatus);
			}
			tempContactPersonInfoList.add(appAppContactInfoEntity);
		}
		//封装非配偶信息
		if(CollectionUtils.isNotEmpty(contactPersonInfoList)){
			for (int i = 0; i < contactPersonInfoList.size(); i++) {
				appAppContactInfoEntity = new AppAppContactInfoEntity(loanBaseId, personId, loanNo, creatorId, userCode);
				HashMap<String, Serializable> tempMap = contactPersonInfoList.get(i);
				if(null != tempMap && !tempMap.isEmpty()){
					BeanUtils.populate(appAppContactInfoEntity, tempMap);
					tempContactPersonInfoList.add(appAppContactInfoEntity);
				}
			}
		}
		for (int i = 0; i < tempContactPersonInfoList.size(); i++) {
			appAppContactInfoEntity = tempContactPersonInfoList.get(i);
			appAppContactInfoEntity.setHeadId(headId);
			
			if(mateInfoFlag.indexOf(maritalStatus) != -1){
				appAppContactInfoEntity.setSequenceNum(ObjectUtils.toString(Integer.valueOf(i)+1));
			}else{
				appAppContactInfoEntity.setSequenceNum(ObjectUtils.toString(Integer.valueOf(i)+2));
			}
			iSaveApplyInfoDao.insertContactInfo(appAppContactInfoEntity);
		}
		log.info(INSERT_LOG_FLAG+"插入【bms_tm_app_contact_info】表成功！");
		
		//插入资产信息
		Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("loanBaseId", loanBaseId);
		paramsMap.put("loanNo", loanNo);
		paramsMap.put("personId", personId);
		paramsMap.put("creator", userCode);
		paramsMap.put("creatorId", creatorId);
		
		LoanBaseRelaEntity loanBaseRelaEntity = insertAssetInfo(allApplyInfoMap,paramsMap);
		log.info(INSERT_LOG_FLAG+"插入【资产信息(八个表)】成功！");
		
		//插入各表主键
		loanBaseRelaEntity.setPersonId(personId);
		loanBaseRelaEntity.setLoanBaseId(loanBaseId);
		loanBaseRelaEntity.setBmsAppPersonInfoId(appAppPersonInfoEntity.getId());
		loanBaseRelaEntity.setTmAppContatcInfoId(Long.valueOf(appAppContactHeadEntity.getId()));
		loanBaseRelaEntity.setCreator(userCode);
		loanBaseRelaEntity.setCreatorId(creatorId);
		
		iSaveApplyInfoDao.insertLoanBaseRela(loanBaseRelaEntity);
		log.info(INSERT_LOG_FLAG+"插入【主键关联信息表】成功！");
		
		response.setRepMsg("申请信息录入成功!");
		response.setData(loanNo);
		
		//撤销不记录保存的借款的日志，再其它地方已记录
		if(StringUtils.isBlank(reasonCode)){
			recordAppSubmitLog(loanNo, creator, userCode);
		}
		return response;
	}

	/**
	 * 插入资产信息
	 */
	@SuppressWarnings("unchecked")
	public LoanBaseRelaEntity insertAssetInfo(Map<String,Object> allApplyInfoMap,Map<String,Object> paramsMap) throws Exception{
		LoanBaseRelaEntity loanBaseRelaEntity = new LoanBaseRelaEntity();
		
		Long loanBaseId = Long.valueOf(paramsMap.get("loanBaseId").toString());
		Long personId = Long.valueOf(paramsMap.get("personId").toString());
		String loanNo = paramsMap.get("loanNo").toString();
		String creator = paramsMap.get("creator").toString();
		String creatorId = paramsMap.get("creatorId").toString();
		String org = EnumConstants.BMS_Org;
		
		Map<String,Serializable> estateInfoMap = (Map<String, Serializable>) allApplyInfoMap.get("estateInfo"); //房产信息
		Map<String,Serializable> carInfoMap = (Map<String, Serializable>) allApplyInfoMap.get("carInfo"); //车辆信息
		Map<String,Serializable> policyInfoMap = (Map<String, Serializable>) allApplyInfoMap.get("policyInfo"); //保单信息
		Map<String,Serializable> providentInfoMap = (Map<String, Serializable>) allApplyInfoMap.get("providentInfo"); //公积金信息
		Map<String,Serializable> cardLoanInfoMap = (Map<String, Serializable>) allApplyInfoMap.get("cardLoanInfo"); //卡友贷信息
		Map<String,Serializable> masterLoanBInfoMap = (Map<String, Serializable>) allApplyInfoMap.get("masterLoanBInfo"); //网购达人贷B信息
		Map<String,Serializable> masterLoanInfoMap = (Map<String, Serializable>) allApplyInfoMap.get("masterLoanInfo"); //网购达人贷B信息
		Map<String,Serializable> merchantLoanInfoMap = (Map<String, Serializable>) allApplyInfoMap.get("merchantLoanInfo"); //淘宝商户贷信息

		AppEstateInfo appEstateInfo = new AppEstateInfo(loanBaseId, personId, loanNo, org, creator, creatorId);
		if(null != estateInfoMap && !estateInfoMap.isEmpty()){
			BeanUtils.populate(appEstateInfo, estateInfoMap);
			
			String estateZoneLinkage = ObjectUtils.toString(estateInfoMap.get("estateZone-Linkage"));
			if(StringUtils.isNotBlank(estateZoneLinkage)){
				String[] estateZoneLinkageArr = AppApplyUtil.convertAreaCode(estateZoneLinkage,iSaveApplyInfoDao.getAreaInfo(estateZoneLinkage));
				appEstateInfo.setEstateStateId(estateZoneLinkage.substring(0, 2)+"0000");
				appEstateInfo.setEstateCityId(estateZoneLinkage.substring(0, 4)+"00");
				appEstateInfo.setEstateZoneId(estateZoneLinkage);
				appEstateInfo.setEstateState(estateZoneLinkageArr[0]);
				appEstateInfo.setEstateCity(estateZoneLinkageArr[1]);
				appEstateInfo.setEstateZone(estateZoneLinkageArr[2]);
			}
			
			//把年月转换成年月日
			String estateBuyDate = ObjectUtils.toString(appEstateInfo.getEstateBuyDate()); //购买时间
			String estateMortgageGrantDate = ObjectUtils.toString(appEstateInfo.getEstateMortgageGrantDate()); //房贷发放年月
			appEstateInfo.setEstateBuyDate(StringUtils.isNotBlank(estateBuyDate) ? estateBuyDate + "-01" : "");
			appEstateInfo.setEstateMortgageGrantDate(StringUtils.isNotBlank(estateMortgageGrantDate) ? estateMortgageGrantDate + "-01" : "");
		}
		appEstateInfo.setEstateId("0");
		iSaveApplyInfoDao.insertEstateInfo(appEstateInfo);
		log.info(INSERT_LOG_FLAG+"插入【bms_tm_app_estate_info】成功！");
		loanBaseRelaEntity.setTmAppEstateInfoId(appEstateInfo.getId());
		
		//车辆信息
		AppCarInfo appCarInfo = new AppCarInfo(loanBaseId, personId, loanNo, org, creator, creatorId);
		if(null != carInfoMap && !carInfoMap.isEmpty()){
			BeanUtils.populate(appCarInfo, carInfoMap);
			
			//把年月转换成年月日
			String carBuyDate = ObjectUtils.toString(appCarInfo.getCarBuyDate()); //购买时间
			String carLoanDate = ObjectUtils.toString(appCarInfo.getCarLoanDate()); //车贷发放年月
			appCarInfo.setCarBuyDate(StringUtils.isNotBlank(carBuyDate) ? carBuyDate + "-01" : "");
			appCarInfo.setCarLoanDate(StringUtils.isNotBlank(carLoanDate) ? carLoanDate + "-01" : "");
		}
		appCarInfo.setCheckId("0");
		iSaveApplyInfoDao.insertCarInfo(appCarInfo);
		log.info(INSERT_LOG_FLAG+"插入【bms_tm_app_car_info】成功！");
		loanBaseRelaEntity.setTmAppCarInfoId(appCarInfo.getId());
		
		//保单信息
		AppPolicyInfo appPolicyInfo = new AppPolicyInfo(loanBaseId, loanNo, personId, org, creator, creatorId);
		if(null != policyInfoMap && !policyInfoMap.isEmpty()){
			BeanUtils.populate(appPolicyInfo, policyInfoMap);
		}
		appPolicyInfo.setPolicyId("0");
		iSaveApplyInfoDao.insertPolicyInfo(appPolicyInfo);
		log.info(INSERT_LOG_FLAG+"插入【bms_tm_app_policy_info】成功！");
		loanBaseRelaEntity.setTmAppPolicyInfoId(appPolicyInfo.getId());
		
		//公积金信息
		AppProvidentInfo appProvidentInfo = new AppProvidentInfo(loanBaseId, personId, loanNo, org, creator, creatorId);
		if(null != providentInfoMap && !providentInfoMap.isEmpty()){
			BeanUtils.populate(appProvidentInfo, providentInfoMap);
		}
		appProvidentInfo.setProvidentId("0");
		iSaveApplyInfoDao.insertProvidentInfo(appProvidentInfo);
		log.info(INSERT_LOG_FLAG+"插入【bms_tm_app_provident_info】成功！");
		loanBaseRelaEntity.setTmAppProvidentInfoId(appProvidentInfo.getId());
		
		//卡友贷信息
		AppCardLoanInfo appCardLoanInfo = new AppCardLoanInfo(loanBaseId, loanNo, org, creator, creatorId);
		if(null != cardLoanInfoMap && !cardLoanInfoMap.isEmpty()){
			BeanUtils.populate(appCardLoanInfo, cardLoanInfoMap);
		}
		String issuerData = ObjectUtils.toString(appCardLoanInfo.getIssuerData()); //购买时间
		appCardLoanInfo.setIssuerData(StringUtils.isNotBlank(issuerData) ? issuerData + "-01" : "");
		iSaveApplyInfoDao.insertCardLoanInfo(appCardLoanInfo);
		log.info(INSERT_LOG_FLAG+"插入【bms_tm_app_card_loan_info】成功！");
		loanBaseRelaEntity.setTmAppCardLoanInfoId(appCardLoanInfo.getId());
		
		//网购达人贷信息
		AppMasterLoanInfo appMasterLoanInfo = new AppMasterLoanInfo(loanBaseId, personId, loanNo, org, creator, creatorId);
		if(null != masterLoanBInfoMap && !masterLoanBInfoMap.isEmpty()){
			BeanUtils.populate(appMasterLoanInfo, masterLoanBInfoMap);
		}
		if(null != masterLoanInfoMap && !masterLoanInfoMap.isEmpty()){
			BeanUtils.populate(appMasterLoanInfo, masterLoanInfoMap);
		}
		appMasterLoanInfo.setMasterLoadId("0");
		iSaveApplyInfoDao.insertMasterLoanInfo(appMasterLoanInfo);
		log.info(INSERT_LOG_FLAG+"插入【bms_tm_app_master_loan_info】成功！");
		loanBaseRelaEntity.setTmAppMasterLoanInfoId(appMasterLoanInfo.getId());
		
		//淘宝商户贷信息
		AppMerchantLoanInfo appMerchantLoanInfo = new AppMerchantLoanInfo(loanBaseId, personId, loanNo, org, creator, creatorId);
		if(null != merchantLoanInfoMap && !merchantLoanInfoMap.isEmpty()){
			BeanUtils.populate(appMerchantLoanInfo, merchantLoanInfoMap);
		}
		appMerchantLoanInfo.setMerchantLoanId("0");
		String setupShopDate = ObjectUtils.toString(appMerchantLoanInfo.getSetupShopDate()); 
		appMerchantLoanInfo.setSetupShopDate(StringUtils.isNotBlank(setupShopDate) ? setupShopDate + "-01" : "");
		iSaveApplyInfoDao.insertMerchantLoanInfo(appMerchantLoanInfo);
		log.info(INSERT_LOG_FLAG+"插入【bms_tm_app_merchant_loan_info】成功！");
		loanBaseRelaEntity.setTmAppMerchanLoanInfoId(appMerchantLoanInfo.getId());
		
		return loanBaseRelaEntity;
	}
	
	/**
	 * @description:平均分派申请单给可接单客服
	 * @author ym10159
	 * @param orgId 营业部ID
	 * @param loanNo 借款编号
	 * @return 返回有效的可接单客服集合
	 */
	@SuppressWarnings("serial")
	private Map<String,Object> getEffectiveEmployeeV1(Long orgId, String loanNo){
		log.info("借款编号【"+loanNo+"】APP进件客服平均分派：机构ID："+orgId);
		
		//查询营业部下的所有客服
		ReqParamVO paramv = new ReqParamVO();
		paramv.setOrgId(orgId);
		paramv.setSysCode(EnumConstants.CMS_SYSTEM_CODE);
		paramv.setRoleCodes(new ArrayList<String>(){{add("customerService");}});
		Response<List<ResEmployeeVO>>  employeeResponse= iEmployeeExecuter.findByDeptAndRole(paramv);
		List<ResEmployeeVO> employeeList = null;
		
		if(null == employeeResponse){
			log.info("借款编号【"+loanNo+"】平台接口findByDeptAndRole返回null");
			throw new BizException(BizErrorCode.EOERR, "分派客服失败！");
		}
		employeeList = employeeResponse.getData();
		if(CollectionUtils.isEmpty(employeeList)){
			log.info("借款编号【"+loanNo+"】平台接口findByDeptAndRole返回空集合");
			throw new BizException(BizErrorCode.EOERR, "营业部下没有可分派客服！");
		}
		
		Collections.sort(employeeList, new Comparator<ResEmployeeVO>() {
			@Override
			public int compare(ResEmployeeVO o1, ResEmployeeVO o2) {
				Long a = o1.getId();
				Long b = o2.getId();
				return a.compareTo(b);
			}
		});
		
		//获取最近一次分派的客服
		Map<String,Object> latestServiceMap = iSaveApplyInfoDao.getLatestAssignService(ObjectUtils.toString(orgId));
		String latestServiceCode = ObjectUtils.toString(latestServiceMap.get("service_code"));
		
		boolean latestServiceStatus = false;
		//过滤禁止接单的客服
		List<APPWhitePersonnelEntity> whitePersonnellist = appWhitePerSonnelService.listBy(new HashMap<String,Object>());
		if(CollectionUtils.isNotEmpty(whitePersonnellist)){
			Iterator<ResEmployeeVO> iterator = employeeList.iterator();
			while(iterator.hasNext()){
				String userCode = iterator.next().getUsercode();
				for (APPWhitePersonnelEntity person : whitePersonnellist) {
					if(latestServiceCode.equals(person.getUserCode())){
						latestServiceStatus = true;
					}
					if(userCode.equals(person.getUserCode()) && !userCode.equals(latestServiceCode)){
						iterator.remove();	
						break;
					}
				}
			}
		}
		//获取最近分派客服的下一个客服信息
		Map<String,Object> nextServiceMap = this.getNextService(employeeList, latestServiceCode);
		if(latestServiceStatus && employeeList.size() == 1){ //如果营业只有一个客服，且这个客服已禁止接单
			throw new BizException(BizErrorCode.EOERR, "营业部下没有可接单的客服！");
		}
		log.info("借款编号【"+loanNo+"】APP进件客服平均分派结果："+nextServiceMap);
		
		return nextServiceMap;
	}
	
	
	/**
	 * @param employeeList 所有客服集合
	 * @param serviceCode 最新分派的客服
	 * @param loopFlag 循环标识
	 * @return 返回最新分派客服的下一个客服信息集合
	 */
	private Map<String,Object> getNextService(List<ResEmployeeVO> employeeList, String serviceCode){
		Map<String,Object> returnMap = new HashMap<>();
		String nextServiceCode = null, nextServiceName = null, inActive = null, status = null;
		for (int i = 0; i < employeeList.size(); i++) {
			if(serviceCode.equals(employeeList.get(i).getUsercode())){
				int j = (i == (employeeList.size()-1)) ? 0 : (i+1);
				inActive = employeeList.get(j).getInActive();
				status = ObjectUtils.toString(employeeList.get(j).getStatus());
				if(inActive.equals("t") && status.equals("0")){ //在职并可用
					nextServiceCode = employeeList.get(j).getUsercode();
					nextServiceName = employeeList.get(j).getName();
					returnMap.put("nextServiceCode", nextServiceCode);
					returnMap.put("nextServiceName", nextServiceName);
					break;
				}
			}
		}
		
		if(returnMap.isEmpty()){ //没有找到下一个客服，从客服列表的第一个开始
			returnMap.put("nextServiceCode", employeeList.get(0).getUsercode());
			returnMap.put("nextServiceName", employeeList.get(0).getName());
		}
		return returnMap;
	}
	
	/**
	 * 平均分派申请单给可接单客服
	 */
	public Map<String,Object> getEffectiveEmployee(Long orgId){
		log.info("APP进件客服平均分派：机构ID："+orgId);
		//响应结果
		Map<String,Object> resultMap = new HashMap<>();

		//调权限系统获取员工信息
		ReqParamVO paramv = new ReqParamVO();
		paramv.setOrgId(orgId);
		paramv.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		paramv.setRoleCodes(new ArrayList<String>(){{add("customerService");}});
		paramv.setStatus(0);// 可用
		paramv.setInActive("t");
		
		Response<List<ResEmployeeVO>>  employeeResponse= iEmployeeExecuter.findByDeptAndRole(paramv);
		if(null == employeeResponse){
			log.info(INSERT_LOG_FLAG+"获取【客服列表信息】失败！【接口返回：null】");
			throw new BizException(BizErrorCode.EOERR, "分派客服失败！【平台接口返回null】");
		}
		
		List<ResEmployeeVO> employeeList = employeeResponse.getData();
		if(CollectionUtils.isEmpty(employeeList)){
			log.info(INSERT_LOG_FLAG+"获取【客服列表信息】为空！");
			throw new BizException(BizErrorCode.EOERR, "营业部下没有可分配客服！");
		}
		log.info("APP进件客服平均分派：平台返回客服总数："+employeeList.size());
		for (ResEmployeeVO resEmployeeVO : employeeList) {
			log.info("APP进件客服平均分派：平台返回客服列表："+"客服姓名："+resEmployeeVO.getName()+" 客服工号："+resEmployeeVO.getUsercode());
		}
	
		//查出目前所有禁止接单的列表
		List<APPWhitePersonnelEntity> whitePersonnellist = appWhitePerSonnelService.listBy(new HashMap<String,Object>());
		
		//过滤不可接单的客服
		if(CollectionUtils.isNotEmpty(whitePersonnellist)){
			Iterator<ResEmployeeVO> iterator = employeeList.iterator();
			while(iterator.hasNext()){
				String userCode = iterator.next().getUsercode();
				for (APPWhitePersonnelEntity person : whitePersonnellist) {
					if(userCode.equals(person.getUserCode())){
						iterator.remove();	
						break;
					}
				}
			}
		}
		log.info("APP进件客服平均分派：过滤禁止接单客服总数："+employeeList.size());
		for (ResEmployeeVO resEmployeeVO : employeeList) {
			log.info("APP进件客服平均分派：过滤禁止接单客服列表："+"客服姓名："+resEmployeeVO.getName()+" 客服工号："+resEmployeeVO.getUsercode());
		}
		
		//已在借款系统中处理申请单的客服列表
		Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("averageStartTime", averageStartTime);
		paramsMap.put("orgId", ObjectUtils.toString(orgId));
		List<Map<String,Object>> serviceList = iSaveApplyInfoDao.getApplyServiceCount(paramsMap);
		
		log.info("APP进件客服平均分派：接口系统中已分配客服数量："+serviceList.size());
		log.info("APP进件客服平均分派：接口系统中已分配客服列表："+serviceList);
		
		//已接口客服列表
		List<Map<String,Object>> hasServiceList = new ArrayList<Map<String,Object>>();
		//未接单客服列表
		List<ResEmployeeVO>  noServiceList = new ArrayList<ResEmployeeVO>();
		
		//循环出已接单的客服
		for (int i = 0; i < employeeList.size(); i++) {
			String serviceCode = employeeList.get(i).getUsercode();
			for (Map<String, Object> map : serviceList) {
				String serviceCodeTemp = ObjectUtils.toString(map.get("service_code"));
				if(serviceCode.equals(serviceCodeTemp)){
					hasServiceList.add(map);
					break;
				}
			}
		}
		log.info("APP进件客服平均分派：已接单客服数量："+hasServiceList.size());
		log.info("APP进件客服平均分派：已接单客服列表："+hasServiceList);
		
		//循环出未接单的客服
		if(CollectionUtils.isNotEmpty(hasServiceList)){
			Iterator<ResEmployeeVO> iterator = employeeList.iterator();
			while(iterator.hasNext()){
				String userCode = iterator.next().getUsercode();
				for (Map<String,Object> map : hasServiceList) {
					if(userCode.equals(map.get("service_code"))){
						iterator.remove();
						break;
					}
				}
			}
		}
		noServiceList = employeeList;
		log.info("APP进件客服平均分派：未接单客服数量："+noServiceList.size());
		for (ResEmployeeVO resEmployeeVO : noServiceList) {
			log.info("APP进件客服平均分派：未接单客服列表："+"客服姓名："+resEmployeeVO.getName()+" 客服工号："+resEmployeeVO.getUsercode());
		}
		
		String serviceCode = "";
		String serviceName = "";
		int averageFlag = 0;
		BmsLogger.info("已提交申请单的客服列表数量："+hasServiceList.size());
		Collections.sort(hasServiceList, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String,Object> o1, Map<String,Object> o2) {
				Integer a = Integer.valueOf(o1.get("count").toString());
				Integer b = Integer.valueOf(o2.get("count").toString());
				return a.compareTo(b);
			}
		});
		
		log.info("APP进件客服平均分派：已接单客服排序："+hasServiceList);
		int minAverageFlag = 1;
		int maxAverageFlag = 1;
		if(CollectionUtils.isNotEmpty(hasServiceList)){
			minAverageFlag = Integer.valueOf(hasServiceList.get(0).get("count").toString());
			maxAverageFlag = Integer.valueOf(hasServiceList.get(hasServiceList.size()-1).get("count").toString());
		}
		log.info("APP进件客服平均分派：已接单客服排序结果：最大数量："+maxAverageFlag+" 最小数量："+minAverageFlag);
		
		if(CollectionUtils.isNotEmpty(noServiceList)){ //不为空，选择客服分派
			serviceCode = ObjectUtils.toString(noServiceList.get(0).getUsercode());
			serviceName = ObjectUtils.toString(noServiceList.get(0).getName());
			averageFlag = maxAverageFlag;
		}else{ //为空，客服都已分派单子，此时平均分派
			serviceCode = ObjectUtils.toString(hasServiceList.get(0).get("service_code"));
			serviceName = ObjectUtils.toString(hasServiceList.get(0).get("service_name"));
			if(minAverageFlag == maxAverageFlag){
				averageFlag = minAverageFlag+1;
			}else{
				averageFlag = maxAverageFlag;
			}
		}
		log.info("APP进件客服平均分派：平均分派值："+averageFlag);
		log.info("APP进件客服平均分派：分配结果：客服姓名："+serviceName+" 客服工号："+serviceCode+" 平均标识："+averageFlag);
		
        resultMap.put("userCode", serviceCode);
		resultMap.put("name", serviceName);
		resultMap.put("averageFlag", averageFlag);
		
		return resultMap;
	}

	@Override
	public Response<Object> insertCancelApplyInfo(Map<String, Object> allApplyInfoMap) throws Exception{
		//保存申请信息
		Response<Object> response =	insertApplyInfo(allApplyInfoMap);
		String loanNo =(String) response.getData();
		if(loanNo != null){ //借款编号不为空 那么保存成功
		//取消申请件
			cancelApplyInfo(allApplyInfoMap,loanNo);
		}
		return response;
	}
	
	private boolean cancelApplyInfo(Map<String, Object> allApplyInfoMap,String loanNo) {
		String userCode = ObjectUtils.toString(allApplyInfoMap.get("userCode"));
		String reasonCode =allApplyInfoMap.get("refuseReason").toString();
		Map paramMap =new HashMap();
		paramMap.put("code", reasonCode);

		String reason =iBMSTMReasonService.getBy(paramMap).getReason();
		//获取员工信息
		ReqEmployeeVO reqEmployeeVO = new ReqEmployeeVO();
		reqEmployeeVO.setUsercode(userCode);
		reqEmployeeVO.setSysCode("bms");
		
		Response<ResEmployeeVO> responseEmployee = iEmployeeExecuter.findByAccount(reqEmployeeVO);
		if(null == responseEmployee){
			log.info(INSERT_LOG_FLAG+"获取【员工信息】失败！【接口返回：null】");
			throw new CoreException(CoreErrorCode.FACADE_ERROR.getErrorCode(),CoreErrorCode.FACADE_ERROR.getDefaultMessage());
		}
		
		
		ResEmployeeVO resEmployeeVO = responseEmployee.getData();
		
		String operatorCode = resEmployeeVO.getUsercode();
		String operatorName = resEmployeeVO.getName();
		Long operatorId = resEmployeeVO.getId();
		//app自动取消
		if("CA0014".equals(reasonCode)){
			//默认为app自动取消
			if(allApplyInfoMap.get("operatorCode")== null){
				operatorCode = EnumConstants.APP_SYSTEM_CODE;
			} else {
				operatorCode = allApplyInfoMap.get("operatorCode").toString();
			}
			if(allApplyInfoMap.get("operatorName")== null){
				operatorName=EnumConstants.DEFAULT_SYSTEM_NAME;
				
			} else {
				operatorName = allApplyInfoMap.get("operatorName").toString();
			}
			operatorId = 1L;
		}
		
		LoanBaseEntity	loanBaseEntity =new LoanBaseEntity();
		loanBaseEntity.setLoanNo(loanNo);
		loanBaseEntity.setStatus(EnumConstants.LoanStatus.CANCEL.getValue());
		loanBaseEntity.setRtfState(EnumConstants.RtfStatus.SQLR.getCode());
		loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_CANCEL.getValue());
		loanBaseEntity.setModifiedTime(new Date());
		loanBaseEntity.setModifier(operatorCode);
		loanBaseEntity.setModifierId(operatorId);
		loanBaseDao.update(loanBaseEntity);
		//记一二级原因
		LoanExtEntity loanExtEntity = new LoanExtEntity();
		loanExtEntity.setLoanNo(loanNo);
		loanExtEntity.setPrimaryReason(reason);
		loanExtEntity.setFirstLevleReasonsCode(reasonCode);
		loanExtEntity.setModifier(operatorCode);
		loanExtEntity.setModifierId(operatorId);
		loanExtEntity.setModifiedTime(new Date());
		loanExtDao.update(loanExtEntity);
		//借款日志
		BMSSysLoanLog loanLog= new BMSSysLoanLog();
		loanLog.setOperationModule(EnumConstants.OptionModule.APPLY_ENTRY.getValue());
		loanLog.setLoanNo(loanNo);
		loanLog.setOperationTime(new Date());
		loanLog.setOperator(operatorName);
		loanLog.setOperatorCode(operatorCode);
		loanLog.setStatus(EnumConstants.LoanStatus.CANCEL.getValue());
		loanLog.setRtfState(EnumConstants.RtfState.APPSQLR.getValue());
		loanLog.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_CANCEL.getValue());
		loanLog.setOperationType(EnumConstants.OptionType.CANCEL.getValue());
		loanLog.setFirstLevleReasons(reason);
		loanLog.setFirstLevleReasonsCode(reasonCode);
		ibmsSysLoanLogService.saveOrUpdate(loanLog);
		//系统日志
		ReqLoanContractSignVo demoReq= new ReqLoanContractSignVo();
		demoReq.setServiceCode(operatorCode);
		demoReq.setServiceName(operatorName);
		demoReq.setSysCode(EnumConstants.APP_SYSTEM_CODE);
		demoReq.setIp("127.0.0.1");
		ibmsSysLogService.recordSysLog(demoReq, "申请录入|待提交申请|撤销|cancel","APPExecuter","saveCancelApplyInput");
		return false;
	}

	@Override
	public Response<Object> insertRefuseApplyInfo(Map<String, Object> allApplyInfoMap) throws Exception {
		//保存申请信息
		Response<Object> response =	insertApplyInfo(allApplyInfoMap);
		String loanNo =(String) response.getData();
		if(loanNo != null){ //借款编号不为空 那么保存成功
			//拒绝申请件
			refuseApplyInfo(allApplyInfoMap,loanNo);
		}
		return response;
	}
	private boolean refuseApplyInfo(Map<String, Object> allApplyInfoMap,String loanNo) {
		String userCode = ObjectUtils.toString(allApplyInfoMap.get("userCode"));
		String reasonCode =allApplyInfoMap.get("refuseReason").toString();
		Map paramMap =new HashMap();
		paramMap.put("code", reasonCode);
		
		String reason =iBMSTMReasonService.getBy(paramMap).getReason();
		//获取员工信息
		ReqEmployeeVO reqEmployeeVO = new ReqEmployeeVO();
		reqEmployeeVO.setUsercode(userCode);
		reqEmployeeVO.setSysCode("bms");
		
		Response<ResEmployeeVO> responseEmployee = iEmployeeExecuter.findByAccount(reqEmployeeVO);
		if(null == responseEmployee){
			log.info(INSERT_LOG_FLAG+"获取【员工信息】失败！【接口返回：null】");
			throw new CoreException(CoreErrorCode.FACADE_ERROR.getErrorCode(),CoreErrorCode.FACADE_ERROR.getDefaultMessage());
		}
		ResEmployeeVO resEmployeeVO = responseEmployee.getData();
		
		String operatorCode = resEmployeeVO.getUsercode();
		String operatorName = resEmployeeVO.getName();
		Long operatorId = resEmployeeVO.getId();
		//app自动取消
		if("CA00037".equals(reasonCode)){
			//默认为app自动取消
			if(allApplyInfoMap.get("operatorCode")== null){
				operatorCode = EnumConstants.APP_SYSTEM_CODE;
			} else {
				operatorCode = allApplyInfoMap.get("operatorCode").toString();
			}
			if(allApplyInfoMap.get("operatorName")== null){
				operatorName=EnumConstants.DEFAULT_SYSTEM_NAME;
				
			} else {
				operatorName = allApplyInfoMap.get("operatorName").toString();
			}
			operatorId = 1L;
		}
		
		LoanBaseEntity	loanBaseEntity =new LoanBaseEntity();
		loanBaseEntity.setLoanNo(loanNo);
		loanBaseEntity.setStatus(EnumConstants.LoanStatus.REFUSE.getValue());
		loanBaseEntity.setRtfState(EnumConstants.RtfStatus.SQLR.getCode());
		loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.LRCS_REJECT.getValue());
		loanBaseEntity.setModifiedTime(new Date());
		loanBaseEntity.setModifier(operatorCode);
		loanBaseEntity.setModifierId(operatorId);
		loanBaseDao.update(loanBaseEntity);
		//记一二级原因
		LoanExtEntity loanExtEntity = new LoanExtEntity();
		loanExtEntity.setLoanNo(loanNo);
		loanExtEntity.setPrimaryReason(reason);
		loanExtEntity.setFirstLevleReasonsCode(reasonCode);
		loanExtEntity.setModifier(operatorCode);
		loanExtEntity.setModifierId(operatorId);
		loanExtEntity.setModifiedTime(new Date());
		loanExtDao.update(loanExtEntity);
		//借款日志
		BMSSysLoanLog loanLog= new BMSSysLoanLog();
		loanLog.setOperationModule(EnumConstants.OptionModule.APPLY_ENTRY.getValue());
		loanLog.setLoanNo(loanNo);
		loanLog.setOperationTime(new Date());
		loanLog.setOperator(operatorName);
		loanLog.setOperatorCode(operatorCode);
		loanLog.setStatus(EnumConstants.LoanStatus.REFUSE.getValue());
		loanLog.setRtfState(EnumConstants.RtfState.APPSQLR.getValue());
		loanLog.setRtfNodeState(EnumConstants.RtfNodeState.LRCS_REJECT.getValue());
		loanLog.setOperationType(EnumConstants.OptionType.REJECT.getValue());
		loanLog.setFirstLevleReasons(reason);
		loanLog.setFirstLevleReasonsCode(reasonCode);
		ibmsSysLoanLogService.saveOrUpdate(loanLog);
		//系统日志
		ReqLoanContractSignVo demoReq= new ReqLoanContractSignVo();
		demoReq.setServiceCode(operatorCode);
		demoReq.setServiceName(operatorName);
		demoReq.setSysCode(EnumConstants.APP_SYSTEM_CODE);
		demoReq.setIp("127.0.0.1");
		ibmsSysLogService.recordSysLog(demoReq, "申请录入|待提交申请|录入超时拒绝|cancel","APPExecuter","saveCancelApplyInput");
		return true;
	}

	@Override
	public Response<Object> insertLoanLog(Req_VO_600001 req_VO_600001) {
		Response<Object> response = new Response<Object>();
		
		//借款日志
		BMSSysLoanLog loanLog= new BMSSysLoanLog();
		loanLog.setOperationModule(EnumConstants.OptionModule.APPLY_ENTRY.getValue());
		loanLog.setLoanNo(req_VO_600001.getLoanNo());
		loanLog.setOperationTime(new Date());
		loanLog.setOperator(req_VO_600001.getServiceName());
		loanLog.setOperatorCode(req_VO_600001.getServiceCode());
		loanLog.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
		loanLog.setRtfState(EnumConstants.RtfState.SQLR.getValue());
		loanLog.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_SAVE.getValue());
		loanLog.setOperationType(EnumConstants.OptionType.SAVE.getValue());
		ibmsSysLoanLogService.saveOrUpdate(loanLog);
		return response;
	}
	
	@Override
	public Response<Object> saveLoanBaseInfo(Map<String, Object> applyInfoMap) {

		Response<Object> res =new Response<Object>();
		String userCode =ObjectUtils.toString(applyInfoMap.get("userCode"));
		//获取机构信息
		ResOrganizationVO resOrganizationVO = getorganization(userCode);
		Long orgId = resOrganizationVO.getId();
		String orgName = resOrganizationVO.getName();

		//获取员工信息
		ResEmployeeVO resEmployeeVO = getEmployee(userCode);
        
        ReqEmployeeVO reqEmployeeVO = new ReqEmployeeVO();
        reqEmployeeVO.setUsercode(userCode);
        reqEmployeeVO.setSysCode("bms");
        Response<List<ResRoleVO>> rolesRes = iEmployeeExecuter.findRolesByAccount(reqEmployeeVO);
        List<ResRoleVO> roles =rolesRes.getData();
        boolean isCManager=false;
        if(roles !=null && roles.size()>0){
            for (ResRoleVO role : roles) {
                if(role.getCode().equals("customerManager")||role.getCode().equals("customerService")){
                    isCManager=true;
                    break;
                }
            }
        }
        if(!isCManager){
            throw new BizException(BizErrorCode.EOERR, "非客户经理和客服不可贷款申请");
        }
		
		long creatorId = resEmployeeVO.getId();
		String creator = ObjectUtils.toString(resEmployeeVO.getName());
		//插入申请主表
		String loanNo =  ObjectUtils.toString(applyInfoMap.get("appNo"));
		long loanBaseId = iSaveApplyInfoDao.findIdByLoanNo(loanNo);
		AppLoanBaseEntity	appLoanBaseEntity = new AppLoanBaseEntity();
		appLoanBaseEntity.setLoanNo(loanNo);
		appLoanBaseEntity.setPersonId(000000L);
		appLoanBaseEntity.setIdNo(ObjectUtils.toString(applyInfoMap.get("idNo")));
		appLoanBaseEntity.setName(ObjectUtils.toString(applyInfoMap.get("name")));
		appLoanBaseEntity.setRemark(ObjectUtils.toString(applyInfoMap.get("remark")));
		appLoanBaseEntity.setBranchManagerCode(userCode);
		appLoanBaseEntity.setBranchManagerName(creator);
		appLoanBaseEntity.setOwningBranch(orgName);
		appLoanBaseEntity.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
		appLoanBaseEntity.setRtfState(EnumConstants.RtfState.SQLR.getValue());
		appLoanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_SAVE.getValue());
		appLoanBaseEntity.setOwningBranchId(orgId);
		appLoanBaseEntity.setApplyType("NEW");
		appLoanBaseEntity.setAppApplyDate(DateUtil.getTodayHHmmss());	
		//根据借款编号查询id
		//未生成或不存在借款插入
		if(loanBaseId ==0||StringUtils.isBlank(loanNo)){
			loanNo =loanBaseDao.createLoanNo(new Date()); //生成loanNo
			appLoanBaseEntity.setLoanNo(loanNo);
			int opFlag=iSaveApplyInfoDao.insertLoanBase(appLoanBaseEntity);
			if(opFlag ==1){
				log.info(INSERT_LOG_FLAG+"插入【bms_loan_base】表成功！");
				res.setRepMsg("保存申请主表信息成功!");
				res.setData(loanNo);
				//借款日志
				BMSSysLoanLog loanLog= new BMSSysLoanLog();
				loanLog.setOperationModule(EnumConstants.OptionModule.APPLY_ENTRY.getValue());
				loanLog.setLoanNo(loanNo);
				loanLog.setOperationTime(new Date());
				loanLog.setOperator(creator);
				loanLog.setOperatorCode(userCode);
				loanLog.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
				loanLog.setRtfState(EnumConstants.RtfState.APPSQLR.getValue());
				loanLog.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_SAVE.getValue());
				loanLog.setOperationType(EnumConstants.OptionType.SAVE.getValue());
				ibmsSysLoanLogService.saveOrUpdate(loanLog);
				//系统日志
				ReqLoanContractSignVo demoReq= new ReqLoanContractSignVo();
				demoReq.setServiceCode(userCode);
				demoReq.setServiceName(creator);
				demoReq.setSysCode("app");
				if(applyInfoMap.get("ip") != null){
					demoReq.setIp(applyInfoMap.get("ip").toString());
				} else {
					demoReq.setIp("172.0.0.1");
				}
				ibmsSysLogService.recordSysLog(demoReq, "申请录入|待提交申请|保存|save","SaveApplyInfoServiceImpl","saveLoanBaseInfo");

			}
			//存在更新
		}else{
			iSaveApplyInfoDao.updateLoanBase(appLoanBaseEntity);
			log.info(INSERT_LOG_FLAG+"更新【bms_loan_base】表成功！");
			res.setData(loanNo);
			res.setRepMsg("更新申请主表信息成功!");
		}
		return res;
	}
	
	/**
	 * app申请信息字段校验
	 * @param req_VO_600003 申请信息对象
	 * @return respose
	 */
	@Override
	public Response<Object> validateField(Req_VO_600003 req_VO_600003) {
		Response<Object> response = new Response<>();
		
		Map<String,Object> applyMap = req_VO_600003.getApplyInfoMap();
		JSONObject json = new JSONObject(applyMap);
		String loanNo = json.getString("loanNo");
		//个人信息
		JSONObject persionInfoJson = json.getJSONObject("persionInfo");
		//配偶信息
		String ifForeignPeople = "";
		JSONObject mateInfoJson = json.getJSONObject("mateInfo");
		if(mateInfoJson.length() != 0){
			ifForeignPeople = mateInfoJson.getString("ifForeignPenple");
		}
		
		String maritalStatus = persionInfoJson.getString("maritalStatus");
		
		log.info("借款编号【"+loanNo+"】app字段特殊校验开始");
		//手机号重复校验
		response = validatePhoneRepeat(applyMap);
		if(!response.isSuccess()) return response;
		//身份证信息和性别信息校验
		response = validateFieldSpecialUtil(applyMap,maritalStatus,ifForeignPeople);
		if(!response.isSuccess()) return response;
		log.info("借款编号【"+loanNo+"】app字段特殊校验结束");
		
		log.info("借款编号【"+loanNo+"】app字段公共校验开始");
		//申请信息校验
		response = validateFieldUtil(json.getJSONObject("applyInfo"), "applyInfo", "");
		if(!response.isSuccess()) return response;
		//个人信息校验
		response = validateFieldUtil(json.getJSONObject("persionInfo"), "persionInfo", "");
		if(!response.isSuccess()) return response;
		//工作信息校验
		response = validateFieldUtil(json.getJSONObject("empItemInfo"), "empItemInfo", "");
		if(!response.isSuccess()) return response;
		//工作信息（私营业主）校验
		response = validateFieldUtil(json.getJSONObject("baseItemInfo"), "", "baseItemInfo");
		if(!response.isSuccess()) return response;
		//联系人信息校验
		response = validateFieldUtil(json.getJSONArray("contactPersonInfo"), "contactPersonInfo", "");
		if(!response.isSuccess()) return response;
		//配偶信息校验
		if(this.MARITAL_STATUS.indexOf(maritalStatus) != -1){
			response = validateFieldUtil(json.getJSONObject("mateInfo"), "mateInfo", "");
			if(!response.isSuccess()) return response;
		}
		//资产信息（房产）校验
		response = validateFieldUtil(json.getJSONObject("estateInfo"), "", "estateInfo");
		if(!response.isSuccess()) return response;
		//资产信息（车辆）校验
		response = validateFieldUtil(json.getJSONObject("carInfo"), "", "carInfo");
		if(!response.isSuccess()) return response;
		//资产信息（保单）校验
		response = validateFieldUtil(json.getJSONObject("policyInfo"), "", "policyInfo");
		if(!response.isSuccess()) return response;
		//资产信息（公积金）校验
		response = validateFieldUtil(json.getJSONObject("providentInfo"), "", "providentInfo");
		if(!response.isSuccess()) return response;
		//资产信息（卡友贷）校验
		response = validateFieldUtil(json.getJSONObject("cardLoanInfo"), "", "cardLoanInfo");
		if(!response.isSuccess()) return response;
		//资产信息（网购达人贷B）校验
		response = validateFieldUtil(json.getJSONObject("masterLoanBInfo"), "", "masterLoanBInfo");
		if(!response.isSuccess()) return response;
		//资产信息（网购达人贷）校验
		response = validateFieldUtil(json.getJSONObject("masterLoanInfo"), "", "masterLoanInfo");
		if(!response.isSuccess()) return response;
		//资产信息（淘宝商户贷）校验
		response = validateFieldUtil(json.getJSONObject("merchantLoanInfo"), "", "merchantLoanInfo");
		if(!response.isSuccess()) return response;
		log.info("借款编号【"+loanNo+"】app字段公共校验结束");
		return response;
	}
	
	/**
	 * 字段公共校验方法
	 * @param applyInfoJson 申请信息
	 * @param parentGroupCode 申请信息所属组编码
	 * @param hideLabelCode 申请信息所属隐藏组编码
	 * @return response
	 */
	public Response<Object> validateFieldUtil(Object applyInfoJson,String parentGroupCode,String hideLabelCode) {
		Response<Object> response = null;
		Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("parentGroupCode", parentGroupCode);
		paramsMap.put("hideLabelCode", hideLabelCode);
		List<Map<String,Object>> regexList = iSaveApplyInfoDao.getFieldInfoList(paramsMap);
		JSONObject applyInfoJsonObj = null;
		JSONArray applyInfoJsonArr = null;
		
		if(applyInfoJson.getClass().equals(JSONObject.class)){
			applyInfoJsonObj = (JSONObject) applyInfoJson;
			for (Map<String, Object> regexMap : regexList) {
				String fieldCode = ObjectUtils.toString(regexMap.get("fieldCode"));
				String fieldRegex = ObjectUtils.toString(regexMap.get("fieldRegex"));
				String fieldTip = ObjectUtils.toString(regexMap.get("fieldTip"));
				response = returnValidateFieldInfo(applyInfoJsonObj, fieldCode, fieldRegex, fieldTip);
			}
		}else{
			applyInfoJsonArr = (JSONArray) applyInfoJson;
			JSONObject applyInfoJsonArrObj = null;
			for (Map<String, Object> regexMap : regexList) {
				String fieldCode = ObjectUtils.toString(regexMap.get("fieldCode"));
				String fieldRegex = ObjectUtils.toString(regexMap.get("fieldRegex"));
				String fieldTip = ObjectUtils.toString(regexMap.get("fieldTip"));
				for (Object object : applyInfoJsonArr) {
					applyInfoJsonArrObj = (JSONObject) object;
					response = returnValidateFieldInfo(applyInfoJsonArrObj, fieldCode, fieldRegex, fieldTip);
				}
			}
		}
		return response;
	}
	
	/**
	 * @description 封装字段校验响应信息
	 * @param applyInfoJson 校验信息
	 * @param fieldCode 校验的字段名
	 * @param fieldRegex 字段名对应的正则表达式
	 * @param fieldTip 字段名对应的正则表达式的提示语
	 * @return Response<Object>
	 */
	private Response<Object> returnValidateFieldInfo(JSONObject applyInfoJson,String fieldCode,String fieldRegex,String fieldTip){
		Response<Object> response = new Response<>();
		
		if(applyInfoJson.isNull(fieldCode)){
			return response;
		}
		//如果校验配偶信息中的身份证号，“外籍人士”为“是”时，不校验身份证号
		if(fieldCode.equals("idNo") && applyInfoJson.has("ifForeignPenple")){
			String ifForeignPenple = applyInfoJson.getString("ifForeignPenple");
			if(ifForeignPenple.equals("Y")){
				return response;
			}
		}
		
		String fieldValue = ObjectUtils.toString(applyInfoJson.get(fieldCode));
		if(StringUtils.isNotBlank(fieldValue) && StringUtils.isNotBlank(fieldRegex)){
			String[] fieldRegexArr = fieldRegex.split("@split@");
			String[] fieldTipArr = fieldTip.split("@split@");
			for (int i = 0; i < fieldRegexArr.length; i++) {
				Pattern pattern = Pattern.compile(fieldRegexArr[i]);
				boolean bool = pattern.matcher(fieldValue).find();
				if(!bool){
					response.setRepCode("000002");
					response.setRepCode(fieldTipArr[i]);
					return response;
				}
			}
		}
		return response;
	}
	
	/**
	 * 字段特殊校验方法
	 * @param map 特殊字段校验的相关属性
	 * @return response
	 */
	@SuppressWarnings("unchecked")
	public Response<Object> validateFieldSpecialUtil(Map<String,Object> allApplyInfoMap,String maritalStatus,String ifForeignPeople) {
		ObjectMapper om = new ObjectMapper();
		Response<Object> response = new Response<>();
		Map<String,Serializable> applyInfoMap = om.convertValue(allApplyInfoMap.get("applyInfo"), Map.class);
		Map<String,Serializable> mateInfoMap = om.convertValue(allApplyInfoMap.get("mateInfo"), Map.class);
		
		String idNo = ObjectUtils.toString(applyInfoMap.get("idNo")); //申请人身份证号
		String mateIdNo = ObjectUtils.toString(mateInfoMap.get("idNo")); //配偶身份证号
		
		//身份证有效性校验
		boolean idNoBool = IDCardValidateUtil.isIdentityCode(idNo);
		if(!idNoBool){
			response.setRepCode("000002");
			response.setRepMsg("申请人身份证号非有效身份证，请重新输入");
			return response;
		}
		//已婚、复婚、再婚，并且外籍人士为否时，才要校验配偶身份证号
		if(this.MARITAL_STATUS.indexOf(maritalStatus) != -1 && ifForeignPeople.equals("N")){
			boolean mateIdNoBool = IDCardValidateUtil.isIdentityCode(mateIdNo);
			if(!mateIdNoBool){
				response.setRepCode("000002");
				response.setRepMsg("配偶身份证号非有效身份证，请重新输入");
				return response;
			}
			//申请人身份证与配偶省份证性别必须是一男一女
			String mateIdNoGender = AppApplyUtil.getGenderByIdNo(mateIdNo);
			String idNoGender = AppApplyUtil.getGenderByIdNo(idNo);
			if(mateIdNoGender.equals(idNoGender)){
				response.setRepCode("000002");
				response.setRepMsg("客户配偶非外籍人士，配偶性别不能与客户相同！");
				return response;
			}
		}
		return response;
	}
	
	/**
	 * 手机号重复校验
	 * @param allApplyInfoMap 申请信息
	 * @return resposne
	 */
	@SuppressWarnings("unchecked")
	public Response<Object> validatePhoneRepeat(Map<String,Object> allApplyInfoMap) {
		Response<Object> response = new Response<>();
		Map<String,Object> resultMap = this.getValidatePhoneList(allApplyInfoMap);
		
		List<String> cellphoneList = (List<String>) resultMap.get("cellphoneList");
		List<String> cellphoneInfoList = (List<String>) resultMap.get("cellphoneInfoList");
		List<String> cellphoneCompareList = new ArrayList<>();
		
		if(CollectionUtils.isNotEmpty(cellphoneList)){
			for (int i = 0; i < cellphoneList.size(); i++) {
				String cellphone = cellphoneList.get(i).split("\\|")[2];
				for (int j = 0; j < cellphoneList.size(); j++) {
					String cellphoneTmp = cellphoneList.get(j).split("\\|")[2];
					if(cellphone.equals(cellphoneTmp)){
						cellphoneCompareList.add(cellphoneInfoList.get(j));
					}
				}
				if(cellphoneCompareList.size() >= 2 ){
					break;
				}else{
					cellphoneCompareList.clear();
				}
			}
		}
		if(cellphoneCompareList.size() >= 2 ){
			String msg1 = cellphoneCompareList.get(0);
			String msg2 = cellphoneCompareList.get(1);
			response.setRepCode("000002");
			response.setRepMsg(msg1+"与"+msg2+"重复");
		}
		return response;
	}
	
	/**
	 * 手机号重复校验，获取所有需要对比的手机号
	 * @param allApplyInfoMap 申请信息
	 * @return Map<String,Object> 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> getValidatePhoneList(Map<String,Object> allApplyInfoMap) {
		Map<String,Object> resultMap = new HashMap<>(); 
		ObjectMapper om = new ObjectMapper();
		Map<String,Serializable> persionInfoMap = om.convertValue(allApplyInfoMap.get("persionInfo"), Map.class);
		ArrayList<HashMap<String,Serializable>> contactPersonInfoList = om.convertValue(allApplyInfoMap.get("contactPersonInfo"), ArrayList.class);
		Map<String,Serializable> mateInfoMap = om.convertValue(allApplyInfoMap.get("mateInfo"), Map.class);
		
		List<String> cellphoneInfoList = new ArrayList<>();
		List<String> cellphoneList = new ArrayList<>();
		
		//个人信息中的手机号
		if(null != persionInfoMap && !persionInfoMap.isEmpty()){
			String cellphone1 = ObjectUtils.toString(persionInfoMap.get("cellphone"));
			String cellphone2 = ObjectUtils.toString(persionInfoMap.get("cellphoneSec"));
			if(StringUtils.isNotBlank(cellphone1)){
				cellphoneInfoList.add("个人信息中的常用手机");
				cellphoneList.add("persionInfo|cellphone|"+cellphone1);
			}
			if(StringUtils.isNotBlank(cellphone2)){
				cellphoneInfoList.add("个人信息中的备用手机");
				cellphoneList.add("persionInfo|cellphoneSec|"+cellphone2);
			}
		}
		//配偶信息中的手机号
		if(null != mateInfoMap && !mateInfoMap.isEmpty()){
			String cellphone1 = ObjectUtils.toString(mateInfoMap.get("contactCellphone1"));
			if(StringUtils.isNotBlank(cellphone1)){
				cellphoneInfoList.add("配偶信息中的常用手机");
				cellphoneList.add("mateInfo|contactCellphone1|"+cellphone1);
			} 
		}
		//联系人信息中的手机号
		if(CollectionUtils.isNotEmpty(contactPersonInfoList)){
			for (int i = 0; i < contactPersonInfoList.size(); i++) {
				String cellphone = ObjectUtils.toString(contactPersonInfoList.get(i).get("contactCellphone1"));
				String contactName = ObjectUtils.toString(contactPersonInfoList.get(i).get("contactName"));
				String tab = ObjectUtils.toString(contactPersonInfoList.get(i).get("tab"));
				if(StringUtils.isNotBlank(cellphone)){
					cellphoneInfoList.add("联系人"+contactName+"的常用手机");
					cellphoneList.add("contactPersonInfo"+tab+"|contactCellphone1|"+cellphone);
				}
			}
		}
		resultMap.put("cellphoneInfoList", cellphoneInfoList);
		resultMap.put("cellphoneList", cellphoneList);
		return resultMap;
	}
	
	/**
	 * @Description:记录app保存和提交的日志
	 * @author YM10159
	 */
	public void recordAppSubmitLog(String loanNo,String creator,String userCode){
		//借款日志
		BMSSysLoanLog loanLog= new BMSSysLoanLog();
		loanLog.setOperationModule(EnumConstants.OptionModule.APPLY_ENTRY.getValue());
		loanLog.setLoanNo(loanNo);
		loanLog.setOperationTime(new Date());
		loanLog.setOperator(creator);
		loanLog.setOperatorCode(userCode);
		loanLog.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
		loanLog.setRtfState(EnumConstants.RtfState.APPSQLR.getValue());
		loanLog.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_SAVE.getValue());
		loanLog.setOperationType(EnumConstants.OptionType.SUBMIT.getValue());
		ibmsSysLoanLogService.saveOrUpdate(loanLog);
		
		//系统日志
		ReqLoanContractSignVo demoReq= new ReqLoanContractSignVo();
		demoReq.setServiceCode(userCode);
		demoReq.setServiceName(creator);
		demoReq.setSysCode("app");
		demoReq.setIp("127.0.0.1");
		ibmsSysLogService.recordSysLog(demoReq, "APP申请录入|提交申请|提交|submit","APPExecuter","saveApplyInfo");
	}
}
