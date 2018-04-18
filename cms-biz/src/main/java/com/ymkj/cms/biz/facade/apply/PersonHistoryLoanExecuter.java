package com.ymkj.cms.biz.facade.apply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.apply.IPersonHistoryLoanExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResAPPCarInfoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResAPPCardLoanInfoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResAPPContactInfoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResAPPEstateInfoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResAPPMasterLoanInfoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResAPPPersonInfoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResAPPPersonVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResAPPPolicyInfoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResAPPProvidentInfoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResAPPSalaryLoanInfoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResDemoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResLoanBaseVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResLoanExtVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResLoanProductVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResMerchantLoanInfoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResPersonHistoryLoanVO;
import com.ymkj.cms.biz.entity.apply.APPCarInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPCardLoanInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPContactInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPEstateInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPMasterLoanInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPMerchantLoanInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPPersonEntity;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPPolicyInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPProvidentInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPSalaryLoanInfoEntity;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.apply.LoanProductEntity;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.APPCarInfoService;
import com.ymkj.cms.biz.service.apply.APPCardLoanInfoService;
import com.ymkj.cms.biz.service.apply.APPContactInfoService;
import com.ymkj.cms.biz.service.apply.APPEstateInfoService;
import com.ymkj.cms.biz.service.apply.APPMasterLoanInfoService;
import com.ymkj.cms.biz.service.apply.APPMerchantLoanInfoService;
import com.ymkj.cms.biz.service.apply.APPPersonInfoService;
import com.ymkj.cms.biz.service.apply.APPPersonService;
import com.ymkj.cms.biz.service.apply.APPPolicyInfoService;
import com.ymkj.cms.biz.service.apply.APPProvidentInfoService;
import com.ymkj.cms.biz.service.apply.APPSalaryInfoService;
import com.ymkj.cms.biz.service.apply.LoanBaseService;
import com.ymkj.cms.biz.service.apply.LoanExtService;
import com.ymkj.cms.biz.service.apply.LoanProductService;
import com.ymkj.cms.biz.service.master.IBMSParameterService;


/**
 * 服务提供者demo:
 * 1. 请求信息验证
 * 2. 调用业务层实现业务操作
 * 3. 封装 响应response
 * 4. 对于必须捕获的异常采用 参考 listPage 的用法
 * @author user
 *		
 */
@Service
public class PersonHistoryLoanExecuter implements IPersonHistoryLoanExecuter {
	
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
	private  IBMSParameterService  parameterService;

	@Override
	public Response<ResPersonHistoryLoanVO> loanInfo(PersonHistoryLoanVO personHistoryLoanVO) {
		//校验参数
		if(StringUtils.isBlank(personHistoryLoanVO.getIdNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"idNo"});
		}else if(StringUtils.isBlank(personHistoryLoanVO.getName())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"name"});
		}
		 Map<String, Object>  paramHisMap=new HashMap<String, Object>();
		 paramHisMap.put("name", personHistoryLoanVO.getName());
		 paramHisMap.put("IdNo", personHistoryLoanVO.getIdNo());
		 APPPersonEntity  person= (APPPersonEntity) appPersonService.getBy(paramHisMap);
		 if(person==null){
				throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL, new Object[]{""});
		 }
		 //3   查出上一笔拒绝 取消借款 或者 结清  新建的状态
		 Map<String, Object>  loanMap=new HashMap<String, Object>();
		 loanMap.put("personId", person.getId());
		 List<String> statusList=new ArrayList<String>();
//		 statusList.add(EnumConstants.LoanStatus.取消.getValue());
//		 statusList.add(EnumConstants.LoanStatus.拒绝.getValue());
//		 statusList.add(EnumConstants.LoanStatus.结清.getValue());
//		 statusList.add(EnumConstants.LoanStatus.新建.getValue());
		 loanMap.put("statusList", statusList );
		 LoanBaseEntity  loanBaseEntity= (LoanBaseEntity) loanBaseService.getBy(loanMap,"getLatelyLoanByPersonIdAndStatus");
		 //根据loanbaseId查出具体信息
		   //获取借款人信息
		 Map<String, Object>  personMap=new HashMap<String, Object>();
		 personMap.put("idNo",personHistoryLoanVO.getIdNo());
	     APPPersonEntity appPersonEntity =appPersonService.getBy(personMap);
	       //获取主卡申请人信息
	     Map<String, Object>  paramMap=new HashMap<String, Object>();
	     paramMap.put("loanBaseId",loanBaseEntity.getId());
		 APPPersonInfoEntity appPersonInfoEntity =appPersonInfoService.getBy(paramMap);
		  //获取申请产品信息
	     LoanProductEntity loanProductEntity =loanProductService.getBy(paramMap);
	     //获取借款扩展信息
	     LoanExtEntity loanExtEntity =loanExtService.getBy(paramMap);
	     //获取房产信息
	     APPEstateInfoEntity estateInfoEntity =appEstateInfoService.getBy(paramMap);
	     //获取车贷信息
	     APPCarInfoEntity appCarInfoEntity =appCarInfoService.getBy(paramMap);
	     //获取保单信息
	     APPPolicyInfoEntity policyInfoEntity =appPolicyInfoService.getBy(paramMap);
	     //获取公积金信息
	     APPProvidentInfoEntity appProvidentInfoEntity =appProvidentInfoService.getBy(paramMap);
	     //获取卡友信息
	     APPCardLoanInfoEntity appCardLoanInfoEntity =appCardLoanInfoService.getBy(paramMap);
	     //获取随薪信息
	     APPSalaryLoanInfoEntity appSalaryLoanInfoEntity =appSalaryInfoService.getBy(paramMap);
	     //获取达人信息
	     APPMasterLoanInfoEntity appMasterLoanInfoEntity =appMasterLoanInfoService.getBy(paramMap);
	     //获取商户信息
	     APPMerchantLoanInfoEntity appMerchantLoanInfoEntity =appMerchantLoanInfoService.getBy(paramMap);
	     //获取联系人信息
	     List<APPContactInfoEntity>  appContactInfoEntityList=appContactInfoService.listBy(paramMap);
	     ResLoanProductVO loanProductVO = new ResLoanProductVO();
	     ResLoanExtVO loanExtVO = new ResLoanExtVO();
	     ResAPPPersonInfoVO appPersonInfoVO = new ResAPPPersonInfoVO();	
	     ResLoanBaseVO loanBaseVO = new ResLoanBaseVO();	
	     ResAPPPersonVO appPersonVO =new ResAPPPersonVO();
	     ResAPPEstateInfoVO estateInfoVO=new ResAPPEstateInfoVO();
	     ResAPPCarInfoVO appCarInfoVO=new ResAPPCarInfoVO();
	     ResAPPPolicyInfoVO appPolicyVO=new ResAPPPolicyInfoVO();
	     ResAPPProvidentInfoVO appProvidentInfoVO=new ResAPPProvidentInfoVO();
	     ResAPPCardLoanInfoVO appCardLoanLoanVO =new ResAPPCardLoanInfoVO();
	     ResAPPSalaryLoanInfoVO appSalaryLoanInfoVO =new ResAPPSalaryLoanInfoVO();
	     ResAPPMasterLoanInfoVO appMasterLoanInfoVO =new ResAPPMasterLoanInfoVO();
	     ResMerchantLoanInfoVO appMerchantLoanInfoVO =new ResMerchantLoanInfoVO();
	     List<ResAPPContactInfoVO> aPPContactInfoVOList=new  ArrayList<ResAPPContactInfoVO>();
	     //返回信息封装
	     BeanUtils.copyProperties(loanProductEntity,loanProductVO);
	     BeanUtils.copyProperties(loanExtEntity,loanExtVO);
	     BeanUtils.copyProperties(appPersonInfoEntity,appPersonInfoVO);
	     BeanUtils.copyProperties(loanBaseEntity,loanBaseVO);
	     BeanUtils.copyProperties(appPersonEntity,appPersonVO);
	     BeanUtils.copyProperties(estateInfoEntity,estateInfoVO);
	     BeanUtils.copyProperties(appCarInfoVO,appCarInfoEntity);
	     BeanUtils.copyProperties(appPolicyVO,policyInfoEntity);
	     BeanUtils.copyProperties(appProvidentInfoVO,appProvidentInfoEntity);
	     BeanUtils.copyProperties(appCardLoanLoanVO,appCardLoanInfoEntity);
	     BeanUtils.copyProperties(appSalaryLoanInfoVO,appSalaryLoanInfoEntity);
	     BeanUtils.copyProperties(appMasterLoanInfoVO,appMasterLoanInfoEntity);
	     BeanUtils.copyProperties(appMerchantLoanInfoVO,appMerchantLoanInfoEntity);
	     for(APPContactInfoEntity contactInfoEntity :appContactInfoEntityList){
	    	 ResAPPContactInfoVO resAPPContactInfoVO = new ResAPPContactInfoVO();
				BeanUtils.copyProperties(contactInfoEntity, resAPPContactInfoVO);
				aPPContactInfoVOList.add(resAPPContactInfoVO);
			}
	     
	     ResPersonHistoryLoanVO resPersonHistoryLoanVO=new ResPersonHistoryLoanVO();
	     resPersonHistoryLoanVO.setAppCardLoanLoanVO(appCardLoanLoanVO);
	     resPersonHistoryLoanVO.setAppCarInfoVO(appCarInfoVO);
	     resPersonHistoryLoanVO.setAppContactInfoVOList(aPPContactInfoVOList);
	     resPersonHistoryLoanVO.setAppMasterLoanInfoVO(appMasterLoanInfoVO);
	     resPersonHistoryLoanVO.setAppMerchantLoanInfoVO(appMerchantLoanInfoVO);
	     resPersonHistoryLoanVO.setAppPersonInfoVO(appPersonInfoVO);
	     resPersonHistoryLoanVO.setAppPersonVO(appPersonVO);
	     resPersonHistoryLoanVO.setAppPolicyVO(appPolicyVO);
	     resPersonHistoryLoanVO.setAppProvidentInfoVO(appProvidentInfoVO);
	     resPersonHistoryLoanVO.setAppSalaryLoanInfoVO(appSalaryLoanInfoVO);
	     resPersonHistoryLoanVO.setEstateInfoVO(estateInfoVO);
	     resPersonHistoryLoanVO.setLoanBaseVO(loanBaseVO);
	     resPersonHistoryLoanVO.setLoanExtVO(loanExtVO);
	     resPersonHistoryLoanVO.setLoanProductVO(loanProductVO);
	     Response<ResPersonHistoryLoanVO> response = new Response<ResPersonHistoryLoanVO>();
	     response.setData(resPersonHistoryLoanVO);
	     
	     
		return response;
	}
 

}
