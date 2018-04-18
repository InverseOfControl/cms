package com.ymkj.cms.biz.entity.apply.input;

import java.util.List;

import com.ymkj.cms.biz.api.vo.request.apply.ApplyEntryVO;
import com.ymkj.cms.biz.api.vo.request.apply.ApplyInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.AssetsInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.BasicInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ContactInfoVO;
import com.ymkj.cms.biz.common.util.BmsVerifyHelper;
import com.ymkj.cms.biz.common.util.ParametersType;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;

public class ApplyEntryVOCheck {
	
	
	
	/**
	 * 结构体校验
	 * @param applyEntryVO
	 */
	public static void ApplyEntryCheck(ApplyEntryVO applyEntryVO){
		// 申请信息
		if(!isEmptyObject(applyEntryVO.getApplyInfoVO())){
			applyInfoVOCheck(applyEntryVO.getApplyInfoVO());
		}
		// 资产信息
		if(!isEmptyObject(applyEntryVO.getAssetsInfoVO())){
			assetsInfoVOCheck(applyEntryVO.getAssetsInfoVO());
		}
		// 基本信息
		if(!isEmptyObject(applyEntryVO.getBasicInfoVO())){
			basicInfoVOCheck(applyEntryVO.getBasicInfoVO());
		}
		// 联系人信息
		if(!isEmptyObject(applyEntryVO.getContactInfoVOList())){
			contactInfoVOListCheck(applyEntryVO.getContactInfoVOList());
		}
		
		//更新员工id
		if(!isEmptyLong(applyEntryVO.getModifierId())){
			
		}
		
		//更新员工姓名
		if(!isEmptyString(applyEntryVO.getModifier())){
					
		}
		//新增员工id
		if(!isEmptyLong(applyEntryVO.getCreatorId())){
			
		}
		//新增员工姓名
		if(!isEmptyString(applyEntryVO.getCreator())){
			
		}
		//录入员工Code
		if(!isEmptyString(applyEntryVO.getServiceCode())){
			
		}
		//录入员工名称
		if(!isEmptyString(applyEntryVO.getServiceName())){
			
		}
		//录入门店ID
		if(!isEmptyLong(applyEntryVO.getOwningBranchId())){
			
		}
		//录入门店
		if(!isEmptyString(applyEntryVO.getOwningBranch())){
			
		}
		//进件门店属性
		if(!isEmptyString(applyEntryVO.getOwningBranchAttribute())){
			
		}
		//操作模块		1 申请录入  2 录入修改 3 录入复核 10退件箱
		if(!isEmptyString(applyEntryVO.getOptionModule())){
			BmsVerifyHelper.verifyContainsKeyName("操作模块 optionModule", applyEntryVO.getOptionModule(), ParametersType.optionModule.class);
		}
		//操作类型	101 提交 102保存 
		if(!isEmptyString(applyEntryVO.getOptionType())){
			BmsVerifyHelper.verifyContainsKeyName("操作类型 optionType", applyEntryVO.getOptionType(), ParametersType.optionType.class);
		}
		//录入区域
		if(!isEmptyString(applyEntryVO.getLoggedArea())){
			
		}
		//录入区域名称
		if(!isEmptyString(applyEntryVO.getLoggedAreaName())){
			
		}
//		//当前版本号
//		if(isEmptyLong(applyEntryVO.getVersion())){
//			
//		}
	}
	/**
	 * 申请信息
	 * @param applyInfoVO
	 */
	public static void applyInfoVOCheck(ApplyInfoVO applyInfoVO){
		
	}
	/**
	 * 资产信息
	 * @param assetsInfoVO
	 */
	public static void assetsInfoVOCheck (AssetsInfoVO assetsInfoVO){
		
	}
	/**
	 * 基本信息
	 * @param assetsInfoVO
	 */
	public static void basicInfoVOCheck (BasicInfoVO basicInfoVO){
		
	}
	/**
	 * 联系人信息
	 * @param assetsInfoVO
	 */
	public static void contactInfoVOListCheck(List<ContactInfoVO> contactInfoVOList){
		if(contactInfoVOList != null && contactInfoVOList.size() > 0){
			for (ContactInfoVO contactInfo:contactInfoVOList) {
				if(!isEmptyString(contactInfo.getContactName())){// 姓名	
					
				}
				if(!isEmptyString(contactInfo.getContactRelation())){//与申请人关系
					//与申请人关系
					BmsVerifyHelper.verifyContainsKeyName("与申请人关系 contactRelation", contactInfo.getContactRelation(), ParametersType.Relationship.class);
				}
				if(!isEmptyString(contactInfo.getContactCellPhone())){// 手机
					
				}
				if(!isEmptyString(contactInfo.getContactCellPhone_1())){ //手机号2
					
				}
				if(!isEmptyString(contactInfo.getIfKnowLoan())){// 是否知晓贷款
					// 是否知晓贷款
					BmsVerifyHelper.verifyContainsKeyName("是否知晓贷款 ifKnowLoan", contactInfo.getIfKnowLoan(), ParametersType.Indicator.class);
				}
				if(!isEmptyString(contactInfo.getContactEmpName())){//公司名称
					
				}
				if(!isEmptyString(contactInfo.getContactCorpPost())){// 职务
					//职务
					BmsVerifyHelper.verifyContainsKeyName("职务 contactCorpPost", contactInfo.getContactCorpPost(), ParametersType.EmpPositionAttrType.class);
				}
				if(!isEmptyString(contactInfo.getContactCorpPhone())){// 公司电话号码
					
				}
				if(!isEmptyString(contactInfo.getContactCorpPhone_1())){//公司电话号码2
					
				}
				if(!isEmptyInteger(contactInfo.getSequenceNum())){// 排序号
					
				}
			}
		}
	}
	
	
	
	
	
	
	
	/** 字符校验 **/
	public static boolean isEmptyString(String paramString){
		return ((paramString == null) || ("".equals(paramString.trim())) || "null".equals(paramString.trim()));
	}
	/** 长整形校验 **/
	public static boolean isEmptyLong(Long paramLong){
		return ((paramLong == null));
	}
	/** 整形校验 **/
	public static boolean isEmptyInteger(Integer paramInteger){
		return ((paramInteger == null));
	}
	/** 对象校验 **/
	public static boolean isEmptyObject(Object object){
		return ((object == null));
	}
	
	public static void verifyKeyStringlength(String keyName,int length,String value){
		if(keyName.length() > length){
			throw new BizException(BizErrorCode.LENGTH_EOERR,new Object[]{value,length});
		}
	}
	
	
	
}
