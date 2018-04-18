package com.ymkj.cms.biz.common.util;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author YM10161
 *
 */
public class ValidataUtil {
	/**
	 * 检测传入的流程状态值是否在取值范围
	 * @param status
	 * @return
	 */
	public static boolean isExtisStatus(String status){
		boolean flag=true;
		List<String> statusList=new ArrayList<String>();
		statusList.add("SQLR");
		statusList.add("LRFH");
		statusList.add("CSFP");
		statusList.add("XSCS");
		statusList.add("ZSFP");
		statusList.add("XSZS");
		statusList.add("QYFP");
		statusList.add("HTQY");
		statusList.add("HTQR");
		statusList.add("FKSH");
		statusList.add("FKQR");
		if(statusList.contains(status)){
			return flag;
		}else{
			flag=false;
		}
		return flag;
	}
	/**
	 * 检测借款单子状态是否符合规范
	 * @return
	 */
	public static boolean checkLoanNoStatus(String status){
		boolean flag=true;
		List<String> StatusList=new ArrayList<String>();
		StatusList.add("APPLY");
		StatusList.add("PASS");
		StatusList.add("CANCEL");
		StatusList.add("REFUSE");
		StatusList.add("NORMAL");
		if(StatusList.contains(status)){
			return flag;
		}else{
			flag=false;
		}
		return flag;	
	}
	
	
	
	/**
	 * 初审的流程节点状态
	 * @param status
	 * @return
	 */
	public static boolean isExtisCsRtfNodeStatus(String status){
		boolean flag=true;
		List<String> StatusList=new ArrayList<String>();
		StatusList.add("XSCS-ASSIGN");
		StatusList.add("XSCS-HANHUP");
		StatusList.add("XSCS-PASS");
		StatusList.add("XSCS-REJECT");
		StatusList.add("XSCS-RETURN");
		StatusList.add("HIGH-PASS");
		StatusList.add("CSFP-SUBMIT");//两种
		StatusList.add("CSFP-REJECT");
		StatusList.add("CSFP-RETURN");
		if(StatusList.contains(status)){
			return flag;
		}else{
			flag=false;
		}
		return flag;
	} 
	
	/**
	 * 终审的流程节点状态
	 * @param status
	 * @return
	 */
	public static boolean isExtisZsRtfNodeStatus(String status){
		boolean flag=true;
		List<String> StatusList=new ArrayList<String>();
		StatusList.add("XSZS-ASSIGN");
		StatusList.add("XSZS-HANGUP");
		StatusList.add("XSZS-RTNCS");
		StatusList.add("XSZS-RETURN");
		StatusList.add("XSZS-REJECT");
		StatusList.add("XSZS-PASS");
		
		StatusList.add("XSZS-SUBMIT-HIGH");
		StatusList.add("XSZS-SUBMIT-BACK");
		StatusList.add("XSZS-SUBMIT-APPROVAL");
		if(StatusList.contains(status)){
			return flag;
		}else{
			flag=false;
		}
		return flag;
	}
	/**
	 * 验证复核节点状态是否在取值范围内
	 * @return
	 */
	public static boolean isCheckNodeStatus(String status){
		boolean flag=true;
		List<String> StatusList=new ArrayList<String>();
		StatusList.add("NO_CHECK");
		StatusList.add("CHECK");
		StatusList.add("CHECK_PASS");
		StatusList.add("CHECK_NO_PASS");
		if(StatusList.contains(status)){
			return flag;
		}else{
			flag=false;
		}
		return flag;
	}
	/**
	 * 验证流程状态与流程节点状态的关系
	 * @param status
	 * @param rtfNodeStatus
	 * @return
	 */
	public static boolean isExtisRtfNodeStatus(String status,String rtfNodeStatus){
		boolean flag=true;
		flag=isExtisStatus(status);
		if(!flag){
			return false;
		}
		if(status.equals("XSCS")){
			flag=isExtisCsRtfNodeStatus(rtfNodeStatus);
			if(!flag){
				 flag=false;
			}
		}else{
			flag=isExtisZsRtfNodeStatus(rtfNodeStatus);
			if(!flag){
				 flag=false;
			}
		}
		return flag;
	}
	
	/**
	 * 验证是否为新生件状态值判断
	 * @param ifNewLoanNoStatus
	 * @return
	 */
	public static boolean isExtisNewOrOldStatus(String ifNewLoanNoStatus){
		boolean flag=true;
		List<String> StatusList=new ArrayList<String>();
		StatusList.add("0");//优先件
		StatusList.add("1");//正常件
		StatusList.add("2");//未分派
		if(StatusList.contains(ifNewLoanNoStatus)){
			return flag;
		}else{
			flag=false;
		}
		return flag;		
	}
	/**
	 * 验证值只能是1或2
	 * @param flag
	 * @return
	 */
	public static boolean isExtisFlag(String value){
		boolean flag=true;
		List<String> flagList=new ArrayList<String>();
		flagList.add("1");
		flagList.add("2");
		if(flagList.contains(value)){
			return flag;
		}else{
			flag=false;
		}
		return flag;
	}
	
	
	
	/**
	 * 验证caseType值只能是1或2或3
	 * @param flag
	 * @return
	 */
	public static boolean isExtisCaseType(String value){
		boolean flag=true;
		List<String> caseTypeList=new ArrayList<String>();
		caseTypeList.add("1");
		caseTypeList.add("2");
		caseTypeList.add("3");
		if(caseTypeList.contains(value)){
			return flag;
		}else{
			flag=false;
		}
		return flag;
	}
	/**
	 * 验证字符串是否是日期类型
	 * @param strDate
	 * @return
	 */
	public static boolean validataDate(String strDate){
		 Pattern a=Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$"); 
         Matcher b=a.matcher(strDate); 
         if(b.matches()) {
               return true;
         } else {
               return false;
         }
	}
	/**
	 * 验证是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("-?[0-9]*.?[0-9]*");
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
	}
	/**
	 * 验证是否为IP地址
	 * @param addr
	 * @return
	 */
	public static boolean isIP(String addr)
	{
		if(addr.length() < 7 || addr.length() > 15 || "".equals(addr))
		{
			return false;
		}
		/**
		 * 判断IP格式和范围
		 */
		String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		
		Pattern pat = Pattern.compile(rexp);  
		
		Matcher mat = pat.matcher(addr);  
		
		boolean ipAddress = mat.find();

		return ipAddress;
	}
}
