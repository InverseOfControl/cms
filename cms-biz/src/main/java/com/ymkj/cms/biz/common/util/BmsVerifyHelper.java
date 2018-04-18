package com.ymkj.cms.biz.common.util;

import java.lang.reflect.Field;
import java.util.List;

import com.ymkj.cms.biz.api.vo.request.apply.ApplyEntryVO;
import com.ymkj.cms.biz.api.vo.request.apply.ApplyInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.AssetsInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.BasicInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.CarInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ContactInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.EstateInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.MasterLoanInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.MerchantLoanInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.PersonInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.PolicyInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.PrivateOwnerInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ProvidentInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.SalaryLoanInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.WorkInfoVO;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;

public class BmsVerifyHelper {
	public static boolean isNullObject(Object object)
	{
		return object == null;
	}
	
	public static boolean isNotNullObject(Object object)
	{
		return !isNullObject(object);
	}
	
	public static boolean isEmpty(String paramString)
	{
		return ((paramString == null) || ("".equals(paramString.trim())) || "null".equals(paramString.trim()));
	}
	
	/**
	 * 校验key值是否合法
	 * @param key
	 * @param clz
	 * @return
	 * @throws Exception 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static boolean verifyContains(String key, Class<?> clz){
		boolean result = false;
		try {
			Field[] fs = clz.getDeclaredFields();
			for (Field field : fs) {
				String name = field.getName();
				String value = String.valueOf(field.get(name));
				if(key.equalsIgnoreCase(value.trim())){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 校验key值是否合法
	 * @param keyName 参数名称
	 * @param key
	 * @param clz
	 * @throws Exception
	 */
	public static void verifyContainsKeyName(String keyName, String key, Class<?> clz){
		if(BmsVerifyHelper.isEmpty(key)){
			throw new BizException(BizErrorCode.APPLY_VALUE_EOERR,new Object[]{keyName});
		}
		if(!verifyContains(key, clz)){
			throw new BizException(BizErrorCode.APPLY_VALUE_EOERR,new Object[]{keyName});
		}
	}
	
	/**
	 * 入参校验
	 * @param applyEntryVO
	 * @throws Exception 
	 */
	public static void verifyApplyEntryVO(ApplyEntryVO applyEntryVO){
		try {
			
			
			/** 主结构体 */
			verifyContainsKeyName("操作模块 optionModule", applyEntryVO.getOptionModule(), ParametersType.optionModule.class);
			verifyContainsKeyName("操作类型 optionType", applyEntryVO.getOptionType(), ParametersType.optionType.class);
			/** 申请信息 **/
			ApplyInfoVO applyInfoVO = applyEntryVO.getApplyInfoVO();
			if(!StringUtils.isEmpty(applyInfoVO.getCustomerSource())){
				//客户来源
				verifyContainsKeyName("客户来源 customerSource", applyInfoVO.getCustomerSource(), ParametersType.CustomerSource.class);
			}
			if(!StringUtils.isEmpty(applyInfoVO.getCreditApplication())){
				//贷款用途
				verifyContainsKeyName("贷款用途 creditApplication", applyInfoVO.getCreditApplication(), ParametersType.CreditApplication.class);
			}
			/** 基本信息 **/
			BasicInfoVO basicInfoVO = applyEntryVO.getBasicInfoVO();
			PersonInfoVO personInfoVO = basicInfoVO.getPersonInfoVO();//个人信息
			if(!StringUtils.isEmpty(personInfoVO.getMaritalStatus())){
				//婚姻状况
				verifyContainsKeyName("婚姻状况 maritalStatus", personInfoVO.getMaritalStatus(), ParametersType.MaritalStatus.class);
			}
			if(!StringUtils.isEmpty(personInfoVO.getQualification())){
				//最高学历
				verifyContainsKeyName("最高学历 qualification", personInfoVO.getQualification(), ParametersType.EducationType.class);
			}
			if(!StringUtils.isEmpty(personInfoVO.getHouseType())){
				//住宅类型
				verifyContainsKeyName("住宅类型 houseType", personInfoVO.getHouseType(), ParametersType.HouseType.class);
			}
			WorkInfoVO workInfoVO = basicInfoVO.getWorkInfoVO();//工作类型
			if(!StringUtils.isEmpty(workInfoVO.getCusWorkType())){
				//客户工作类型	
				verifyContainsKeyName("客户工作类型 cusWorkType", workInfoVO.getCusWorkType(), ParametersType.JobType.class);
			}
			
			if(!StringUtils.isEmpty(workInfoVO.getBusinessNetWork())){
				//工商网信息
				verifyContainsKeyName("工商网信息 businessNetWork", workInfoVO.getBusinessNetWork(), ParametersType.BusinessNetWork.class);
			}
			if(!StringUtils.isEmpty(workInfoVO.getCorpStructure())){
				//公司性质
				verifyContainsKeyName("公司性质 corpStructure", workInfoVO.getCorpStructure(), ParametersType.CorpStructure.class);
			}
			if(!StringUtils.isEmpty(workInfoVO.getCorpType())){
				//公司行业类别
				verifyContainsKeyName("公司性质 corpType", workInfoVO.getCorpType(), ParametersType.EmpType.class);
			}
			if(!StringUtils.isEmpty(workInfoVO.getCorpPost())){
				//职务
				verifyContainsKeyName("职务 corpPost", workInfoVO.getCorpPost(), ParametersType.NoGovInstitution.class);
			}
			if(!StringUtils.isEmpty(workInfoVO.getOccupation())){
				// 职业
				verifyContainsKeyName("职业 occupation", workInfoVO.getOccupation(), ParametersType.OccupationType.class);
			}
			if(!StringUtils.isEmpty(workInfoVO.getCorpPayWay())){
				//发薪方式
				verifyContainsKeyName("发薪方式 corpPayWay", workInfoVO.getCorpPayWay(), ParametersType.CorpPayWay.class);
			}
			PrivateOwnerInfoVO privateOwnerInfoVO = basicInfoVO.getPrivateOwnerInfoVO();//私营业主信息
			if(!StringUtils.isEmpty(privateOwnerInfoVO.getPriEnterpriseType())){
				//私营企业类型
				verifyContainsKeyName("私营企业类型 priEnterpriseType", privateOwnerInfoVO.getPriEnterpriseType(), ParametersType.PriEnterpriseType.class);
			}
			if(!StringUtils.isEmpty(privateOwnerInfoVO.getBusinessPlace())){
				//经营场所
				verifyContainsKeyName("经营场所 businessPlace", privateOwnerInfoVO.getBusinessPlace(), ParametersType.BusinessPlace.class);
			}
			/** 联系人信息 **/
			List<ContactInfoVO> contactInfoVOList = applyEntryVO.getContactInfoVOList();
			for (int i = 0; i < contactInfoVOList.size(); i++) {
				ContactInfoVO contactInfoVO = contactInfoVOList.get(i);
				if(!StringUtils.isEmpty(contactInfoVO.getContactCorpPost())){
					//职务
					verifyContainsKeyName("职务 contactCorpPost", contactInfoVO.getContactCorpPost(), ParametersType.EmpPositionAttrType.class);
				}
				if(!StringUtils.isEmpty(contactInfoVO.getContactRelation())){
					//与申请人关系
					verifyContainsKeyName("与申请人关系 contactRelation", contactInfoVO.getContactRelation(), ParametersType.Relationship.class);
				}
				if(!StringUtils.isEmpty(contactInfoVO.getIfKnowLoan())){
					// 是否知晓贷款
					verifyContainsKeyName("是否知晓贷款 ifKnowLoan", contactInfoVO.getIfKnowLoan(), ParametersType.Indicator.class);
				}
			}
			/** 资产信息 **/
			AssetsInfoVO assetsInfoVO = applyEntryVO.getAssetsInfoVO();
			EstateInfoVO estateInfoVO = assetsInfoVO.getEstateInfoVO();// 房产信息
			if(!StringUtils.isEmpty(estateInfoVO.getEstateType())){
				//房产类型
				verifyContainsKeyName("房产类型 estateType", estateInfoVO.getEstateType(), ParametersType.FangType.class);
			}
			if(!StringUtils.isEmpty(estateInfoVO.getEstateLoan())){
				//房贷情况
				verifyContainsKeyName("房贷情况 estateLoan", estateInfoVO.getEstateLoan(), ParametersType.EstateType.class);
			}
			if(!StringUtils.isEmpty(estateInfoVO.getHouseOwnership())){
				//房产所有权	
				verifyContainsKeyName("房产所有权 houseOwnership", estateInfoVO.getHouseOwnership(), ParametersType.HouseOwnerType.class);
			}
			if(!StringUtils.isEmpty(estateInfoVO.getIfMe())){
				//单据户名为本人
				verifyContainsKeyName("单据户名为本人 ifMe", estateInfoVO.getIfMe(), ParametersType.Indicator.class);
			}
			CarInfoVO carInfoVO = assetsInfoVO.getCarInfoVO(); //车辆信息	
			if(!StringUtils.isEmpty(carInfoVO.getCarType())){
				//车辆类型
				verifyContainsKeyName("车辆类型 carType", carInfoVO.getCarType(), ParametersType.CarType.class);
			}
			if(!StringUtils.isEmpty(carInfoVO.getCarLoan())){
				//是否有车贷
				verifyContainsKeyName("是否有车贷 carLoan", carInfoVO.getCarLoan(), ParametersType.Indicator.class);
			}
			if(!StringUtils.isEmpty(carInfoVO.getLocalPlate())){
				//本地车牌
				verifyContainsKeyName("本地车牌 localPlate", carInfoVO.getLocalPlate(), ParametersType.Indicator.class);
			}
			PolicyInfoVO policyInfoVO = assetsInfoVO.getPolicyInfoVO();// 保单信息	
			if(!StringUtils.isEmpty(policyInfoVO.getPaymentMethod())){
				//缴费方式
				verifyContainsKeyName("缴费方式 paymentMethod", policyInfoVO.getPaymentMethod(), ParametersType.PaymentMethod.class);
			}
			if(!StringUtils.isEmpty(policyInfoVO.getPolicyRelation())){
				//与被保险人关系
				verifyContainsKeyName("与被保险人关系 policyRelation", policyInfoVO.getPolicyRelation(), ParametersType.PolicyRelation.class);
			}
			if(!StringUtils.isEmpty(policyInfoVO.getPolicyCheck())){
				//保单真伪核实方式
				verifyContainsKeyName("保单真伪核实方式 policyCheck", policyInfoVO.getPolicyCheck(), ParametersType.PolicyCheck.class);
			}
			ProvidentInfoVO providentInfoVO = assetsInfoVO.getProvidentInfoVO();//公积金信息	
			if(!StringUtils.isEmpty(providentInfoVO.getProvidentInfo())){
				//公积金材料
				verifyContainsKeyName("公积金材料 providentInfo", providentInfoVO.getProvidentInfo(), ParametersType.ProvidentInfo.class);
			}
			if(!StringUtils.isEmpty(providentInfoVO.getPaymentUnit())){
				//缴纳单位同申请单位
				verifyContainsKeyName("缴纳单位同申请单位 paymentUnit", providentInfoVO.getPaymentUnit(), ParametersType.Indicator.class);
			}
			SalaryLoanInfoVO salaryLoanInfoVO = assetsInfoVO.getSalaryLoanInfoVO();// 随薪贷信息	
			if(!StringUtils.isEmpty(salaryLoanInfoVO.getConditionType())){
				//条件类型
				verifyContainsKeyName("条件类型 conditionType", salaryLoanInfoVO.getConditionType(), ParametersType.ConditionType.class);
			}
			MasterLoanInfoVO masterLoanInfoVO = assetsInfoVO.getMasterLoanInfoVO();// 网购达人贷信息	
			if(!StringUtils.isEmpty(masterLoanInfoVO.getBuyerCreditLevel())){
				//买家信用等级
				verifyContainsKeyName("买家信用等级 buyerCreditLevel", masterLoanInfoVO.getBuyerCreditLevel(), ParametersType.SellerCreditLevel.class);
			}
			if(!StringUtils.isEmpty(masterLoanInfoVO.getBuyerCreditType())){
				//买家信用类型
				verifyContainsKeyName("买家信用类型 buyerCreditType", masterLoanInfoVO.getBuyerCreditType(), ParametersType.SellerCreditType.class);
			}
			if(!StringUtils.isEmpty(masterLoanInfoVO.getJiDongUserLevel())){
				//京东用户等级
				verifyContainsKeyName("京东用户等级 jiDongUserLevel", masterLoanInfoVO.getJiDongUserLevel(), ParametersType.JiDongUserLevel.class);	
			}
			MerchantLoanInfoVO merchantLoanInfoVO = assetsInfoVO.getMerchantLoanInfoVO();// 淘宝商户贷信息        
			if(!StringUtils.isEmpty(merchantLoanInfoVO.getSellerCreditLevel())){
				//卖家信用等级
				verifyContainsKeyName("卖家信用等级 sellerCreditLevel", merchantLoanInfoVO.getSellerCreditLevel(), ParametersType.SellerCreditLevel.class);
			}
			if(!StringUtils.isEmpty(merchantLoanInfoVO.getSellerCreditType())){
				//卖家信用类型
				verifyContainsKeyName("卖家信用等级 sellerCreditType", merchantLoanInfoVO.getSellerCreditType(), ParametersType.SellerCreditType.class);
			}
		} catch (Exception e) {
			throw new BizException(BizErrorCode.ENUM_EOERR, new Object[]{e.getMessage()});
		}
						
	}
}
