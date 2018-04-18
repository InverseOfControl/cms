
package com.ymkj.cms.biz.service.app.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600005;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.app.IAppValidateService;
import com.ymkj.pms.biz.api.service.ICalendarExecuter;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqCalendarVO;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResCalendarVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;
import com.ymkj.rule.biz.api.message.MapResponse;
import com.ymkj.rule.biz.api.message.RuleEngineRequest;
import com.ymkj.rule.biz.api.service.IRuleEngineExecuter;
import com.ymkj.rule.biz.api.vo.ApplyRuleExecVo;

@Service
public class AppValidateServiceImpl implements IAppValidateService{
	
	public static String LOG_TAG = "AppValidateServiceImpl.";
	
	private Log log = LogFactory.getLog(AppValidateServiceImpl.class);
	
	@Autowired
	private IRuleEngineExecuter ruleEngineExecuter;
	
	@Autowired
	private IEmployeeExecuter employeeExecuter;
	
	@Autowired
	private ICalendarExecuter calendarExecuter;
	
	public static final int IDENTITYCODE_OLD = 15; // 老身份证15位
	public static final int IDENTITYCODE_NEW = 18; // 新身份证18位
	
	@Override
	public Response<Object> validate(Req_VO_600005 vo_600005){
		Response<Object> response = new Response<>();
		Map<String,Object> resultMap = new HashMap<>();
		
		@SuppressWarnings("unused")
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		
		Response<Object> validateResponse = Validate.getInstance().validate(vo_600005);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}
		
		//校验身份证号是否正确
		boolean bool = this.isIdentityCode(vo_600005.getIdCardNo());
		if(!bool){
			throw new BizException(BizErrorCode.EOERR,"身份证校验失败！");
		}
		
		//根据员工工号获取员工所在营业部
		ReqEmployeeVO reqEmployeeVO = new ReqEmployeeVO();
		reqEmployeeVO.setSysCode("bms");
		reqEmployeeVO.setUsercode(vo_600005.getUserCode());
		
		Response<List<ResOrganizationVO>> organizationResponse = employeeExecuter.findDeptsByAccount(reqEmployeeVO);
		
		List<ResOrganizationVO> organizationList = null;
		if(organizationResponse.isSuccess()){
			organizationList = organizationResponse.getData();
		}
		
		if(CollectionUtils.isEmpty(organizationList)){
			throw new BizException(BizErrorCode.EOERR, "通过员工工号获取员工所在营业部返回结果为空！");
		}
		//员工所在机构ID
		String owningBranchId = ObjectUtils.toString(organizationList.get(0).getId());
		
		//调用益博睿录单接口进行录单校验
		ApplyRuleExecVo applyRuleExecVo = new ApplyRuleExecVo();
		applyRuleExecVo.setName(vo_600005.getName());
		applyRuleExecVo.setIdNo(vo_600005.getIdCardNo());
		applyRuleExecVo.setExecuteType(vo_600005.getLink());
		applyRuleExecVo.setProductCode(vo_600005.getProductCd());
		applyRuleExecVo.setOwningBranchId(owningBranchId);
		applyRuleExecVo.setExecuteType(vo_600005.getExecuteType());
		applyRuleExecVo.setAppStatus(vo_600005.getLink());
		if(vo_600005.getApplyInfoMap() !=null){
			String loanNo =  ObjectUtils.toString(vo_600005.getApplyInfoMap().get("appNo"));
			if(!StringUtils.isBlank(loanNo)){
				applyRuleExecVo.setLoanNo(loanNo);
			}
		}
		RuleEngineRequest ruleRequest = new RuleEngineRequest();
		ruleRequest.setSysCode(EnumConstants.BMS_SYSCODE);
		ruleRequest.setBizType("loanApply");
		ruleRequest.setData(applyRuleExecVo);
		
		com.ymkj.rule.biz.api.message.Response ruleResponse = ruleEngineExecuter.executeRuleEngine(ruleRequest);
		
		if(null == ruleResponse){
			throw new BizException(BizErrorCode.EOERR, "调用益博睿录单接口失败！返回null");
		}
		if(!ruleResponse.getRepCode().equals("000000")){
			throw new BizException(BizErrorCode.EOERR, "调用益博睿录单接口失败！返回非000000");
		}
		
		MapResponse mapRuleResponse = (MapResponse) ruleResponse;
		Map<String,Object> ruleMap = mapRuleResponse.getMap();
		log.info(log+"validate：规则网关返回校验结果【"+ruleMap.get("action")+"】");
		
		String action = ObjectUtils.toString(ruleMap.get("action"));
		String hint = ObjectUtils.toString(ruleMap.get("hint"));
		String link = ObjectUtils.toString(vo_600005.getLink());
		
		Map<String,Object> validateMap = this.getAppValidateResult(link,action);
		
		String isExists = vo_600005.getIsExists();
		if(StringUtils.isNotBlank(isExists) && "N".equals(isExists)){
			resultMap.putAll(this.getAPPWorkDate());
		}
		
		resultMap.put("ifNext", validateMap.get("ifNext"));
		response.setData(resultMap);
		response.setRepMsg(hint);
		
		return response;
	}
	
	public Map<String,Object> getAppValidateResult(String link, String action){
		Map<String,Object> resultMap = new HashMap<>();
		
		if(action.equals("限制") || action.equals("拒绝")) resultMap.put("ifNext", "N");
		if(action.equals("通过")) resultMap.put("ifNext", "Y");
	
		return resultMap;
	}
	
	/**
	 * <p>Description:APP未提交列表获取当前日期之后第三天，之后最后一天，第六天工作日</p>
	 * @uthor YM10159
	 * @date 2017年6月13日 下午3:49:49
	 * @param paramDate
	 */
	public Map<String, Object> getAPPWorkDate() {
		Map<String,Object> returnMap = new HashMap<>();
		
		returnMap.put("lastThreeDay", this.getNextWorkday(3));
		returnMap.put("lastDay", this.getNextWorkday(5));
		returnMap.put("lastSubmitTime", this.getNextWorkday(6));
		
		return returnMap;
	}
	
	/**
	 * <p>Description:获取当前日期的后几天的工作日日期</p>
	 * @uthor YM10159
	 * @date 2017年6月13日 下午4:01:30
	 */
	public String getNextWorkday(int nextDay){
		String nextDayDate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentTime = sdf.format(date);
		
		ReqCalendarVO reqCalendarVO = new ReqCalendarVO();
		reqCalendarVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		try{
			reqCalendarVO.setPageSize(nextDay);
			reqCalendarVO.setSomeDay(DateUtil.getDate(DateUtil.strToDate(currentTime, "yyyy-MM-dd"), "yyyy-MM-dd"));
			Response<ResCalendarVO> nextWorkdayResponse = calendarExecuter.getNextWorkday(reqCalendarVO);
			nextDayDate = ObjectUtils.toString(nextWorkdayResponse.getData().getSomeDay());
		}catch(Exception e){}
		
		return nextDayDate;
	}
	
	//判断身份证号是否正确
	public static boolean isIdentityCode(String code) {

		if (StringUtils.isEmpty(code)) {
			return false;
		}

		code = code.trim();

		if ((code.length() != IDENTITYCODE_OLD)
				&& (code.length() != IDENTITYCODE_NEW)) {
			return false;
		}

		// 身份证号码必须为数字(18位的新身份证最后一位可以是x)
		Pattern pt = Pattern.compile("\\d{17}([\\dxX]{1})?");
		Matcher mt = pt.matcher(code);
		if (!mt.find()) {
			return false;
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		simpleDateFormat.setLenient(false);

		// 最后一位校验码验证
		if (code.length() == IDENTITYCODE_NEW) {
			String lastNum = getVerify(code.substring(0, IDENTITYCODE_NEW - 1));
			// check last digit
			if (!("" + code.charAt(IDENTITYCODE_NEW - 1)).toUpperCase().equals(
					lastNum)) {
				return false;
			}
		}

		return true;
	}
	
	public static String getVerify(String eightcardid) {
		int remaining = 0;
		int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
		int[] vi = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };
		int[] ai = new int[18];
		String returnStr = null;
		try {
			if (eightcardid.length() == 18) {
				eightcardid = eightcardid.substring(0, 17);
			}
			if (eightcardid.length() == 17) {
				int sum = 0;
				String k = null;
				for (int i = 0; i < 17; i++) {
					k = eightcardid.substring(i, i + 1);
					ai[i] = Integer.parseInt(k);
					k = null;
				}
				for (int i = 0; i < 17; i++) {
					sum = sum + wi[i] * ai[i];
				}
				remaining = sum % 11;
			}
			returnStr = remaining == 2 ? "X" : String.valueOf(vi[remaining]);
		} catch (Exception ex) {
			return null;
		} finally {
			wi = null;
			vi = null;
			ai = null;
		}
		return returnStr;
	}
}



