package com.ymkj.cms.biz.common.util;

/**
 * 大小类，工具类
 * 
 * @author 李璇
 * @date 2017年3月14日 下午6:26:18
 */
public class ClassesTypeUtils {
	/**
	 * 借款人行业信息,code翻译成对应显示
	 * @param code
	 * @return
	 */
	public static String translateCorpType(String code) {
		if (null != code && !"".equals(code)) {
			if ("00001".equals(code)) {
				return "农、林、牧、渔业，能源、采矿业";
			} else if ("00002".equals(code)) {
				return "食品、药品、工业原料、服装、日用品等制造业";
			} else if ("00003".equals(code)) {
				return "电力、热力、燃气及水生产和供应业";
			} else if ("00004".equals(code)) {
				return "建筑业";
			} else if ("00005".equals(code)) {
				return "批发和零售业";
			} else if ("00006".equals(code)) {
				return "交通运输、仓储和邮政业";
			} else if ("00007".equals(code)) {
				return "住宿、旅游、餐饮业";
			} else if ("00008".equals(code)) {
				return "信息传输、软件和信息技术服务业";
			} else if ("00009".equals(code)) {
				return "金融业";
			} else if ("00010".equals(code)) {
				return "房地产业";
			} else if ("00011".equals(code)) {
				return "租赁和商务服务业";
			} else if ("00012".equals(code)) {
				return "科学研究和技术服务业";
			} else if ("00013".equals(code)) {
				return "水利、环境和公共设施管理业";
			} else if ("00014".equals(code)) {
				return "居民服务、修理和其他服务业";
			} else if ("00015".equals(code)) {
				return "教育、培训";
			} else if ("00016".equals(code)) {
				return "卫生、医疗、社会保障、社会福利";
			} else if ("00017".equals(code)) {
				return "文化、体育和娱乐业";
			} else if ("00018".equals(code)) {
				return "政府、非赢利机构和社会组织";
			} else if ("00019".equals(code)) {
				return "警察、消防、军人";
			} else {
				return "其他";
			}
		} else {
			return "其他";
		}
	}
	/**
	 * 借款人是否有车贷,code翻译成对应显示
	 * @param code
	 * @return
	 */
	public static String translateCarLoan(String code){
		if ("Y".equals(code)) {
			return "1";// 借款人有车贷
		} else {
			return "0";// 借款人没车贷
		}
	}
	/**
	 * 借款人是否有房贷,code翻译成对应显示
	 * @param code
	 * @return
	 */
	public static String translateEstateLoan(String code){
		if("ING".equals(code))
		{
			return "1";//借款人有房贷
		}else
		{
			return "0";//借款人没房贷
		}
	}
	
	/**
	 * 借款人单位性质,code翻译成对应显示
	 * @param code
	 * @return
	 */
	public static String translateCorpStructure(String code) {
		if (null != code && !"".equals(code)) {
			if ("00001".equals(code)) {
				return "3";
			} else if ("00002".equals(code)) {
				return "3";
			} else if ("00003".equals(code)) {
				return "3";
			} else if ("00004".equals(code)) {
				return "2";
			} else if ("00005".equals(code)) {
				return "0";
			} else if ("00006".equals(code)) {
				return "0";
			} else if ("00007".equals(code)) {
				return "100";
			} else if ("00008".equals(code)) {
				return "1";
			} else {
				return "100";
			}
		} else {
			return "100";
		}
	}
	/**
	 * 借款用途,code翻译成对应显示
	 * @param code
	 * @return
	 */
	public static String translatePurpose(String code) {
		if (null != code && !"".equals(code)) {
			if ("00001".equals(code)) {
				return "资金周转";
			} else if ("00002".equals(code)) {
				return "扩大经营";
			} else if ("00003".equals(code)) {
				return "购生活品";
			} else if ("00004".equals(code)) {
				return "购原材料";
			} else if ("00005".equals(code)) {
				return "购设备";
			} else if ("00006".equals(code)) {
				return "教育支出";
			} else if ("00007".equals(code)) {
				return "装修家居";
			} else if ("00008".equals(code)) {
				return "医疗";
			} else if ("00009".equals(code)) {
				return "旅游";
			} else if ("00010".equals(code)) {
				return "购买";
			} else {
				return "其他";
			}
		} else {
			return "2";
		}
	}
	/**
	 * 借款人婚姻状况
	 * @param code
	 * @return
	 */
	public static String translateMaritalStatus(String code) {
		if (null != code && !"".equals(code)) {
			if ("00001".equals(code)) {
				return "2";
			} else if ("00002".equals(code)) {
				return "1";
			} else if ("00003".equals(code)) {
				return "4";
			} else {
				return "2";
			}
		} else {
			return "2";
		}
	}
	/**
	 * 借款人教育程度
	 * @param code
	 * @return
	 */
	public static Object translateQualification(String code) {
		if (null != code && !"".equals(code)) {
			if ("00001".equals(code)) {
				return "7";
			} else if ("00002".equals(code)) {
				return "6";
			} else if ("00003".equals(code)) {
				return "5";
			} else if ("00004".equals(code)) {
				return "4";
			} else if ("00005".equals(code)) {
				return "4";
			} else {
				return "4";
			}
		} else {
			return "4";
		}
	}
}
