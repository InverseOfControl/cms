package com.ymkj.cms.biz.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 产品相关工具类
 * 
 * @author YM10079
 *
 */
public class BeanAttributeUtils {

	private static Map<String, Map<String, String>> filterClassMap = null;
	private static Map<String, String> ClassMap = null;
	static { 
		filterClassMap = new HashMap<String, Map<String, String>>();
		ClassMap = new HashMap<String, String>();
		ClassMap.put("id", "1");
		ClassMap.put("loanBaseId", "1");
		ClassMap.put("appNo", "1");
		ClassMap.put("creatorId", "1");
		ClassMap.put("creator", "1");
		ClassMap.put("createdTime", "1");
		ClassMap.put("modifierId", "1");
		ClassMap.put("modifier", "1");
		ClassMap.put("modifiedTime", "1");
		ClassMap.put("isDelete", "1");
		ClassMap.put("verson", "1");
		ClassMap.put("initProductName", "1");
		ClassMap.put("initProductCd", "1");
		ClassMap.put("applyLmt", "1");
		filterClassMap.put("LoanProductEntity", ClassMap);

		ClassMap = new HashMap<String, String>();
		ClassMap.put("appApplyDate", "1");
		ClassMap.put("owningBranch", "1");
		ClassMap.put("createdTime", "1");
		ClassMap.put("modifiedTime", "1");
		ClassMap.put("ifNewLoanNo", "1");
		ClassMap.put("creatorId", "1");
		ClassMap.put("verson", "1");
		ClassMap.put("loanNo", "1");
		ClassMap.put("owningBranchId", "1");
		ClassMap.put("serviceCode", "1");
		ClassMap.put("serviceName", "1");
		ClassMap.put("applyDate", "1");
		ClassMap.put("creator", "1");
		ClassMap.put("isDelete", "1");
		ClassMap.put("modifier", "1");
		ClassMap.put("modifierId", "1");
		ClassMap.put("personId", "1");
		ClassMap.put("signName", "1");
		ClassMap.put("signCode", "1");
		filterClassMap.put("LoanBaseEntity", ClassMap);

		ClassMap = new HashMap<String, String>();
		ClassMap.put("isDelete", "1");
		ClassMap.put("modifier", "1");
		ClassMap.put("perosnNo", "1");
		ClassMap.put("idType", "1");
		ClassMap.put("modifierId", "1");
		ClassMap.put("createdTime", "1");
		ClassMap.put("modifiedTime", "1");
		ClassMap.put("creatorId", "1");
		ClassMap.put("creator", "1");
		filterClassMap.put("APPPersonEntity", ClassMap);

		ClassMap = new HashMap<String, String>();
		ClassMap.put("isDelete", "1");
		ClassMap.put("modifier", "1");
		ClassMap.put("modifierId", "1");
		ClassMap.put("auditStartTime", "1");
		ClassMap.put("createdTime", "1");
		ClassMap.put("modifiedTime", "1");
		ClassMap.put("creatorId", "1");
		ClassMap.put("applyEndTime", "1");
		ClassMap.put("loanNo", "1");
		ClassMap.put("creator", "1");
		ClassMap.put("applyStartTime", "1");
		filterClassMap.put("LoanExtEntity", ClassMap);

		ClassMap = new HashMap<String, String>();
		ClassMap.put("birthday", "1");
		ClassMap.put("createdTime", "1");
		ClassMap.put("modifiedTime", "1");
		ClassMap.put("creatorId", "1");
		ClassMap.put("verson", "1");
		ClassMap.put("loanNo", "1");
		ClassMap.put("org", "1");
		ClassMap.put("creator", "1");
		ClassMap.put("isDelete", "1");
		ClassMap.put("modifier", "1");
		ClassMap.put("applyPersonId", "1");
		ClassMap.put("modifierId", "1");
		ClassMap.put("personId", "1");
		ClassMap.put("loanBaseId", "1");
		filterClassMap.put("APPPersonInfoEntity", ClassMap);

		ClassMap = new HashMap<String, String>();
		ClassMap.put("loanBaseId", "1");
		ClassMap.put("creator", "1");
		ClassMap.put("org", "1");
		ClassMap.put("loanNo", "1");
		ClassMap.put("estateId", "1");
		ClassMap.put("verson", "1");
		ClassMap.put("creatorId", "1");
		ClassMap.put("modifiedTime", "1");
		ClassMap.put("createdTime", "1");
		ClassMap.put("modifierId", "1");
		ClassMap.put("modifier", "1");
		ClassMap.put("isDelete", "1");
		filterClassMap.put("APPEstateInfoEntity", ClassMap);

		ClassMap = new HashMap<String, String>();
		ClassMap.put("checkId", "1");
		ClassMap.put("loanBaseId", "1");
		ClassMap.put("modifierId", "1");
		ClassMap.put("modifier", "1");
		ClassMap.put("isDelete", "1");
		ClassMap.put("creator", "1");
		ClassMap.put("org", "1");
		ClassMap.put("loanNo", "1");
		ClassMap.put("creatorId", "1");
		ClassMap.put("modifiedTime", "1");
		ClassMap.put("createdTime", "1");
		filterClassMap.put("APPCarInfoEntity", ClassMap);

		ClassMap = new HashMap<String, String>();
		ClassMap.put("isDelete", "1");
		ClassMap.put("modifier", "1");
		ClassMap.put("modifierId", "1");
		ClassMap.put("createdTime", "1");
		ClassMap.put("policyId", "1");
		ClassMap.put("modifiedTime", "1");
		ClassMap.put("creatorId", "1");
		ClassMap.put("verson", "1");
		ClassMap.put("loanNo", "1");
		ClassMap.put("org", "1");
		ClassMap.put("creator", "1");
		ClassMap.put("loanBaseId", "1");
		filterClassMap.put("APPPolicyInfoEntity", ClassMap);

		ClassMap = new HashMap<String, String>();
		ClassMap.put("loanBaseId", "1");
		ClassMap.put("creator", "1");
		ClassMap.put("org", "1");
		ClassMap.put("loanNo", "1");
		ClassMap.put("verson", "1");
		ClassMap.put("creatorId", "1");
		ClassMap.put("modifiedTime", "1");
		ClassMap.put("createdTime", "1");
		ClassMap.put("modifierId", "1");
		ClassMap.put("providentId", "1");
		ClassMap.put("modifier", "1");
		ClassMap.put("isDelete", "1");
		filterClassMap.put("APPProvidentInfoEntity", ClassMap);

		ClassMap = new HashMap<String, String>();
		ClassMap.put("loanBaseId", "1");
		ClassMap.put("creator", "1");
		ClassMap.put("org", "1");
		ClassMap.put("loanNo", "1");
		ClassMap.put("verson", "1");
		ClassMap.put("creatorId", "1");
		ClassMap.put("modifiedTime", "1");
		ClassMap.put("createdTime", "1");
		ClassMap.put("modifierId", "1");
		ClassMap.put("modifier", "1");
		ClassMap.put("isDelete", "1");
		filterClassMap.put("APPCardLoanInfoEntity", ClassMap);

		ClassMap = new HashMap<String, String>();
		ClassMap.put("loanBaseId", "1");
		ClassMap.put("creator", "1");
		ClassMap.put("org", "1");
		ClassMap.put("loanNo", "1");
		ClassMap.put("verson", "1");
		ClassMap.put("creatorId", "1");
		ClassMap.put("modifiedTime", "1");
		ClassMap.put("createdTime", "1");
		ClassMap.put("modifierId", "1");
		ClassMap.put("modifier", "1");
		ClassMap.put("isDelete", "1");
		filterClassMap.put("APPSalaryLoanInfoEntity", ClassMap);

		ClassMap = new HashMap<String, String>();
		ClassMap.put("loanBaseId", "1");
		ClassMap.put("creator", "1");
		ClassMap.put("org", "1");
		ClassMap.put("loanNo", "1");
		ClassMap.put("masterLoadId", "1");
		ClassMap.put("verson", "1");
		ClassMap.put("creatorId", "1");
		ClassMap.put("modifiedTime", "1");
		ClassMap.put("createdTime", "1");
		ClassMap.put("modifierId", "1");
		ClassMap.put("modifier", "1");
		ClassMap.put("isDelete", "1");
		filterClassMap.put("APPMasterLoanInfoEntity", ClassMap);

		ClassMap = new HashMap<String, String>();
		ClassMap.put("loanBaseId", "1");
		ClassMap.put("creator", "1");
		ClassMap.put("org", "1");
		ClassMap.put("loanNo", "1");
		ClassMap.put("verson", "1");
		ClassMap.put("creatorId", "1");
		ClassMap.put("merchantLoanId", "1");
		ClassMap.put("modifiedTime", "1");
		ClassMap.put("createdTime", "1");
		ClassMap.put("modifierId", "1");
		ClassMap.put("modifier", "1");
		ClassMap.put("isDelete", "1");
		filterClassMap.put("APPMerchantLoanInfoEntity", ClassMap);

		ClassMap = new HashMap<String, String>();
		ClassMap.put("loanBaseId", "1");
		ClassMap.put("creator", "1");
		ClassMap.put("org", "1");
		ClassMap.put("loanNo", "1");
		ClassMap.put("verson", "1");
		ClassMap.put("personId", "1");
		ClassMap.put("headId", "1");
		ClassMap.put("modifiedTime", "1");
		ClassMap.put("createdTime", "1");
		ClassMap.put("modifierId", "1");
		ClassMap.put("modifier", "1");
		ClassMap.put("isDelete", "1");
		filterClassMap.put("APPContactInfoEntity", ClassMap);
	}

	public static boolean filterClass(String className, String nameKey) {
		if (filterClassMap.get(className) != null) {
			Map<String, String> keyMap = filterClassMap.get(className);
			if (keyMap.get(nameKey) != null) {
				return true;
			}
		}
		return false;
	}

	private static final Logger logger = Logger.getLogger(BeanAttributeUtils.class);

	/**
	 * 源目标为非MAP类型时
	 * 
	 * @param source
	 * @param target
	 * @param rv
	 * @return
	 */
	public static Map classOfSrc(Object source, Object target) {
		Map<String, String> map = new HashMap<String, String>();
		String srcClassName = source.getClass().getSimpleName();// 类名
		Class<?> srcClass = source.getClass();
		Field[] fields = srcClass.getDeclaredFields();
		for (Field field : fields) {
			String nameKey = field.getName();
			if (filterClass(srcClassName, nameKey)) {
				continue;
			}

			if (target instanceof Map) {
				HashMap<String, String> tarMap = new HashMap<String, String>();
				tarMap = (HashMap) target;
				String srcValue = getClassValue(source, nameKey) == null ? "" : getClassValue(source, nameKey).toString();
				if (tarMap.get(nameKey) == null) {
					if (tarMap.get(nameKey) != srcValue) {
						map.put(nameKey, srcValue);
					}

				} else if (!tarMap.get(nameKey).equals(srcValue)) {
					map.put(nameKey, srcValue);
					// break;
				}
			} else {
				String srcValue = getClassValue(source, nameKey) == null ? "" : getClassValue(source, nameKey).toString();
				String tarValue = getClassValue(target, nameKey) == null ? "" : getClassValue(target, nameKey).toString();
				if (srcValue == null) {
					if (srcValue != tarValue) {
						map.put(nameKey, srcValue);
					}
				} else if (!srcValue.equals(tarValue)) {
					map.put(nameKey, srcValue);
				}
			}
		}
		return map;
	}
	
	
	
	/**
	 * 判断VO是否全是null
	 * 
	 * @param source
	 * @param target
	 * @param rv
	 * @return
	 */
	public static boolean classIfNull(Object source) {
		boolean res = false;
		if(source == null)return res;
		
		Map<String, String> map = new HashMap<String, String>();
		Class<?> srcClass = source.getClass();
		Field[] fields = srcClass.getDeclaredFields();
		for (Field field : fields) {
			String nameKey = field.getName();
			
			if(nameKey.equals("id") || nameKey.equals("ifEmpty")
					|| nameKey.equals("sysCode") || nameKey.equals("bizType")){
				continue;
			}

			String srcValue = getClassValue(source, nameKey) == null ? "" : getClassValue(source, nameKey).toString();
			if(!StringUtils.isEmpty(srcValue)){
				res = true;
				break;
			}
		}
		return res;
	}

	/**
	 * 源目标为非MAP类型时
	 * 
	 * @param source
	 * @param target
	 * @param rv
	 * @return 返回第一个差异
	 */
	public static Map classOfSrcOne(Object source, Object target) {
		Map<String, String> map = new HashMap<String, String>();
		Class<?> srcClass = source.getClass();
		Field[] fields = srcClass.getDeclaredFields();
		for (Field field : fields) {
			String nameKey = field.getName();
			if (target instanceof Map) {
				HashMap<String, String> tarMap = new HashMap<String, String>();
				tarMap = (HashMap) target;
				String srcValue = getClassValue(source, nameKey) == null ? "" : getClassValue(source, nameKey).toString();
				if (tarMap.get(nameKey) == null) {
					if (tarMap.get(nameKey) != srcValue) {
						map.put(nameKey, srcValue);
					}

				} else if (!tarMap.get(nameKey).equals(srcValue)) {
					map.put(nameKey, srcValue);
					// break;
				}
			} else {
				String srcValue = getClassValue(source, nameKey) == null ? "" : getClassValue(source, nameKey).toString();
				String tarValue = getClassValue(target, nameKey) == null ? "" : getClassValue(target, nameKey).toString();
				if (srcValue == null) {
					if (srcValue != tarValue) {
						map.put(nameKey, srcValue);
					}
				} else if (!srcValue.equals(tarValue)) {
					map.put(nameKey, srcValue);
					break;
				}
			}
		}
		return map;
	}

	/**
	 * 根据字段名称取值
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Object getClassValue(Object obj, String fieldName) {
		if (obj == null) {
			return null;
		}
		try {
			Class beanClass = obj.getClass();
			Method[] ms = beanClass.getMethods();
			for (int i = 0; i < ms.length; i++) {
				// 非get方法不取
				if (!ms[i].getName().startsWith("get")) {
					continue;
				}
				Object objValue = null;
				try {
					objValue = ms[i].invoke(obj, new Object[] {});
				} catch (Exception e) {
					logger.info("反射取值出错：" + e.toString());
					continue;
				}
				if (objValue == null) {
					continue;
				}
				if (ms[i].getName().toUpperCase().equals(fieldName.toUpperCase()) || ms[i].getName().substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
					return objValue;
				} else if (fieldName.toUpperCase().equals("SID") && (ms[i].getName().toUpperCase().equals("ID") || ms[i].getName().substring(3).toUpperCase().equals("ID"))) {
					return objValue;
				}
			}
		} catch (Exception e) {
			// logger.info("取方法出错！" + e.toString());
		}
		return null;
	}

	public static String getMassge(Object objAll, Object obj, Map<String, String> fieldNameMap) {
		if (fieldNameMap == null)
			return "";
		String msg = "";
		for (Map.Entry<String, String> entry : fieldNameMap.entrySet()) {
			msg += "字段：" + entry.getKey();
			msg += "原值[" + getClassValue(objAll, entry.getKey()) + "]，改后值[" + getClassValue(obj, entry.getKey()) + "]";
		}
		return msg;
	}
}
