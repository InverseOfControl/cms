package com.ymkj.cms.web.boss.controller.finance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.constant.BmsConstant;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.enums.EnumPaymentStatus;
import com.ymkj.cms.biz.api.enums.EnumCHConstants.EnumApplyInputFlagConstants;
import com.ymkj.cms.biz.api.vo.request.channel.ExcelObjVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqImportDocumentVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqImportExcelVO;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSWmxtVO;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanExportInfoVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.web.boss.common.DateUtil;
import com.ymkj.cms.web.boss.common.FileProperties;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.common.StringUtils;
import com.ymkj.cms.web.boss.common.SystemCode;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.channel.IExcelExport;
import com.ymkj.cms.web.boss.service.channel.ILinePaymentService;
import com.ymkj.cms.web.boss.service.finance.ILoanConfirmService;
import com.ymkj.cms.web.boss.service.master.IChannelProductService;
import com.ymkj.cms.web.boss.service.master.IChannelService;
import com.ymkj.cms.web.boss.service.master.ITmReasonService;
import com.ymkj.pms.biz.api.enums.RoleEnum;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.service.IRoleExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.request.ReqRoleVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;
import com.ymkj.pms.biz.api.vo.response.ResRoleChannelVO;
import com.ymkj.pms.biz.api.vo.response.ResRoleVO;
import com.ymkj.sso.client.ShiroUser;
import com.ymkj.sso.client.ShiroUtils;

@Controller
@RequestMapping("loanConfirm")
public class LoanConfirmController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoanConfirmController.class);
	@Autowired
	private IChannelProductService chaProService;
	
	@Autowired
	private IChannelService channelService;
	
	@Autowired
	private ILoanConfirmService iLoanConfirmService;
	
	@Autowired
	private ITmReasonService iTmReasonService;
	
	@Autowired
	private IEmployeeExecuter iEmployeeExecuter;

	@Autowired
	private FileProperties fileProperties;
	
	@Autowired
	private IExcelExport iExcelExport;
	
	@Autowired
	private IRoleExecuter iRoleExecuter;
	
	@Autowired
	private IExcelExport excelExport;
	
	private static String[] LOANCONFIRM_EXP_FIELDNAMES = { "签约网点",	"客户姓名"	,"借款产品"	,"签约期限"	,"身份证号码",	"渠道名称"	,"合同编号",	"合同金额",	"放款金额"	,
			 "所属银行",	"所属分行",	"银行卡号",	"签约日期",	"还款起日"	,"还款止日"	,"咨询费"	,"管理费"	,"丙方管理费"	,"丁方管理费","评估费",	"费用合计",	"风险金",	"放款审核时间" };

	private static String[] LOANCONFIRM_EXP_FIELDCODES = {  "contractBranch",  "name", "productName", "contractTrem","idNo","channelName","contractNum", "pactMoney", "grantMoney",  "bank", "bankFullName" ,"applyBankCardNo", "signDate", "startrdate",
			 "endrdate", "referRate&dm", "manageRate&dm", "managerRateForPartyc&dm",  "managerRateForPartyd&dm", "evalRate&dm", "rateSum&dm", "risk&dm", "financeAuditTimeStr" };
	
	private static Class<?> LOANCONFIRM_CLASS = ResLoanExportInfoVo.class;
	
	
	
	@RequestMapping("view")
	public String view(HttpServletRequest request) { 
		/*ShiroUser user=ShiroUtils.getShiroUser();
		ResEmployeeVO resEmployeeVO= user.getEmployeeVO();
		String usrCode=resEmployeeVO.getUsercode();
		boolean isFinanceUser= isFinanceAuditUser(usrCode);	
		if(isFinanceUser){*/
			String picFileDataUrl =fileProperties.getPicfiledataUrl();
			ResEmployeeVO user=ShiroUtils.getCurrentUser();
			request.setAttribute("userCode", user.getUsercode());
			request.setAttribute("userName", user.getName());
			request.setAttribute("picFileDataUrl", picFileDataUrl);
			return "finance/loanConfirm/loanConfirm";
	/*	}else{//非管理员和放款审核角色不可进入
			request.setAttribute("errorCode", "200090");
			request.setAttribute("ex", "警告：非财务人员管理员不可进入放款确认");
			return "test_error";
		}
*/
		
	}

	/**
	 * 返回json类型的处理方法 1. 第一步 参数校验 (第一种方式) 2. 第二步 业务调用 3. 第三步 结果处理
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResLoanVo> listPage(ReqLoanVo reqLoanVo) {
		if (reqLoanVo.getPageNum() == 0 || reqLoanVo.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		reqLoanVo.setRtfNodeState(EnumConstants.RtfNodeState.FKSHSUBMIT.getValue());
		reqLoanVo.setRtfState(EnumConstants.RtfState.FKQR.getValue());
		ShiroUser user=ShiroUtils.getShiroUser();
		ResEmployeeVO resEmployeeVO= user.getEmployeeVO();
		String usrCode=resEmployeeVO.getUsercode();
		reqLoanVo.setServiceCode(usrCode);
		reqLoanVo.setServiceName(resEmployeeVO.getName());
		reqLoanVo.setIp(StringUtils.getLocalIP());
		reqLoanVo.setChannelCd(reqLoanVo.getChannelCd());
		reqLoanVo.setTaskName(EnumConstants.WF_FKQR);
		PageResult<ResLoanVo> pageResult = iLoanConfirmService.listPage(reqLoanVo);
		ResponsePage<ResLoanVo> pageList = new ResponsePage<ResLoanVo>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}
	
	/**
	 * 返回json类型的处理方法 1. 第一步 参数校验 (第一种方式) 2. 第二步 业务调用 3. 第三步 结果处理
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "doneListPage")
	@ResponseBody
	public ResponsePage<ResLoanVo> doneListPage(ReqLoanVo reqLoanVo) {
		if (reqLoanVo.getPageNum() == 0 || reqLoanVo.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		reqLoanVo.setRtfNodeState(EnumConstants.RtfNodeState.FKSHSUBMIT.getValue());
		reqLoanVo.setRtfState(EnumConstants.RtfState.FKQR.getValue());
		ShiroUser user=ShiroUtils.getShiroUser();
		ResEmployeeVO resEmployeeVO= user.getEmployeeVO();
		String usrCode=resEmployeeVO.getUsercode();
		reqLoanVo.setServiceCode(usrCode);
		reqLoanVo.setServiceName(resEmployeeVO.getName());
		reqLoanVo.setIp(StringUtils.getLocalIP());
		reqLoanVo.setTaskName(EnumConstants.WF_FKQR);
		PageResult<ResLoanVo> pageResult = iLoanConfirmService.doneListPage(reqLoanVo);
		ResponsePage<ResLoanVo> pageList = new ResponsePage<ResLoanVo>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}
	
	/**
	 * 获取所有未删除的渠道信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "findChannel")
	@ResponseBody
	public Object findChannel(ReqBMSChannelVO reqBMSChannelVO) {
		reqBMSChannelVO.setSysCode(EnumConstants.BMS_SYSCODE);
		Set<String> channelSet = new HashSet<String>();
		List<ResBMSChannelVO> channellist = channelService.getAllChannel(reqBMSChannelVO);
		ShiroUser user=ShiroUtils.getShiroUser();
		List<Long>  roleIds =user.getRoleIds();//得到用户下所有的角色
		ReqRoleVO reqRole =new ReqRoleVO();
		reqRole.setSysCode(EnumConstants.BMS_SYSCODE);
		Response<List<ResRoleChannelVO>> res=null;
		List<ResRoleChannelVO> roleChanelList=null;
		for (Long roleId : roleIds) {//遍历角色id
			reqRole.setId(roleId);
			res=iRoleExecuter.getRoleChannels(reqRole);
			roleChanelList=res.getData();
			for (ResRoleChannelVO resRoleChannelVO : roleChanelList) {//遍历得到角色下可用的渠道
				channelSet.add(resRoleChannelVO.getChannelCode());
			}
		}
		List<ResBMSChannelVO> userChannellist =new ArrayList<ResBMSChannelVO>();
		for (ResBMSChannelVO resBMSChannelVO : channellist) {//遍历所有的渠道
			if(channelSet.contains(resBMSChannelVO.getCode())){//不包含remove掉
				userChannellist.add(resBMSChannelVO);
			}
		}
		return userChannellist;
	}
	/**
	 * 获取所有未删除的产品
	 * 
	 * @return
	 */
	@RequestMapping(value = "findProduct")
	@ResponseBody
	public Object findProduct(ReqBMSProductVO reqBMSProductVO) {
		List<ResBMSProductVO> list = new ArrayList<>();
		reqBMSProductVO.setSysCode(EnumConstants.BMS_SYSCODE);
		try {
			list = chaProService.findAllProduct(reqBMSProductVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取所有未删除的营业网点
	 * @return
	 */
	@RequestMapping(value = "findApplyInputFlag")
	@ResponseBody
	public Object findApplyInputFlag(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO){
		List<Map<String ,String>> applyInputFlagList =new ArrayList<Map<String ,String>>();
		Map<String,String> applyInputFlagMap =null;
		for(EnumApplyInputFlagConstants enumAppStateConstants:EnumApplyInputFlagConstants.values()){
			applyInputFlagMap=new HashMap<String,String>();
			applyInputFlagMap.put("code", enumAppStateConstants.getCode());
			applyInputFlagMap.put("value", enumAppStateConstants.getValue());
			applyInputFlagList.add(applyInputFlagMap);
				
		}
		return applyInputFlagList;
	}
	
	@RequestMapping(value = "grantLoan")
	@ResponseBody
	public Map<String,Object> grantLoan(ReqLoanVo reqLoanVo) {
		//当前登录用户
		String successMsg =StringUtils.SYMBOL_NULL_STRING;//成功返款借款信息
		String failMsg =StringUtils.SYMBOL_NULL_STRING;//失败返款借款信息
		ShiroUser user=ShiroUtils.getShiroUser();
		ResEmployeeVO resEmployeeVO= user.getEmployeeVO();
		String usrCode=resEmployeeVO.getUsercode();
		reqLoanVo.setServiceCode(usrCode);
		reqLoanVo.setServiceName(resEmployeeVO.getName());
		reqLoanVo.setIp(StringUtils.getLocalIP());
		Map<String,Object> retMap = new HashMap<String,Object>();
		
		Response<String>  isRes= iLoanConfirmService.valiProductIsDisabled(reqLoanVo);
			if(EnumConstants.YES.equals(isRes.getData())){
				retMap.put("result",reqLoanVo.getLoanNo()+":产品不可用");
			}else{
				List<ReqLoanVo> loanList =new ArrayList<ReqLoanVo>() ;
				Long  id=reqLoanVo.getId();
				String  loanNo=reqLoanVo.getLoanNo();
				ReqLoanVo reqLoanVo2 = new ReqLoanVo();
				reqLoanVo2.setId(id);
				reqLoanVo2.setLoanNo(loanNo);
				reqLoanVo2.setContractBranch(reqLoanVo.getContractBranch());
				reqLoanVo2.setContractBranchId(reqLoanVo.getContractBranchId());
				loanList.add(reqLoanVo2);
				reqLoanVo.setLoanList(loanList);
				Response<ResLoanVo>	res = iLoanConfirmService.grantLoan(reqLoanVo);	
				if(res.isSuccess()){
					retMap.put("isSuccess",true);
				}
				ResLoanVo loanVo=	res.getData();
				//返回成功放款集合
				if(loanVo !=null){
					List<ResLoanVo> successList =loanVo.getSuccessList();
					 successMsg =ListAddByStr(successList,StringUtils.SYMBOL_COLON,"放款确认成功");
					//返回失败集合
					List<ResLoanVo> failList =loanVo.getFailList();
					 failMsg =ListAddByStr(failList,StringUtils.SYMBOL_COLON,"放款确认失败");
				}
				retMap.put("result",successMsg+failMsg);
		}
		return retMap;
	}
	
	
	@RequestMapping(value = "bacthPassLoan")
	@ResponseBody
	public Map<String,Object> bacthPassLoan(ReqLoanVo reqLoanVo) {
		//借款集合
		List<ReqLoanVo> loanList =new ArrayList<ReqLoanVo>() ;
		boolean isSuccess=true;
		Response<ResLoanVo>	res =null;
		String successMsg =StringUtils.SYMBOL_NULL_STRING;//成功返款借款信息
		String failMsg =StringUtils.SYMBOL_NULL_STRING;//失败返款借款信息
		StringBuffer vilMsg= new StringBuffer();//产品校验信息
		Map<String,Object> retMap = new HashMap<String,Object>();
		//当前登录用户
		ShiroUser user=ShiroUtils.getShiroUser();
		ResEmployeeVO resEmployeeVO= user.getEmployeeVO();
		String usrCode=resEmployeeVO.getUsercode();
		reqLoanVo.setServiceCode(usrCode);
		reqLoanVo.setIp(StringUtils.getLocalIP());
		reqLoanVo.setServiceName(resEmployeeVO.getName());
		//校验产品是否可用
		List<String> loanNos =reqLoanVo.getLoanNos();
		List<String> ids	=reqLoanVo.getIds();
		List<String> owningBranchIds =reqLoanVo.getOwningBranchIds();
		List<String> channelCds =reqLoanVo.getChannelCds();
		List<String> productCds =reqLoanVo.getProductCds();
		List<BigDecimal> contractLmts =reqLoanVo.getContractLmts();
		List<Integer> contractTrems =reqLoanVo.getContractTrems();
		if(loanNos ==null || loanNos.size() < 1 ){
			retMap.put("result","loanList对象为空");
			return retMap;
		}
		ReqLoanVo reqLoanVo2 =null;
		for (int i = 0; i < loanNos.size(); i++) {
			if(!StringUtils.isEmpty(ids.get(i))&&!StringUtils.isEmpty(loanNos.get(i))){
				reqLoanVo2 = new ReqLoanVo();
				reqLoanVo2.setId(Long.parseLong(ids.get(i)));
				reqLoanVo2.setLoanNo(loanNos.get(i));
				reqLoanVo2 = new ReqLoanVo();
				reqLoanVo2.setId(Long.parseLong(ids.get(i)));
				reqLoanVo2.setLoanNo(loanNos.get(i));
				reqLoanVo2.setOwningBranchId(owningBranchIds.get(i));
				reqLoanVo2.setChannelCd(channelCds.get(i));
				reqLoanVo2.setProductCd(productCds.get(i));
				reqLoanVo2.setContractLmt(contractLmts.get(i));
				reqLoanVo2.setContractTrem(contractTrems.get(i));
				Response<String>  isRes= iLoanConfirmService.valiProductIsDisabled(reqLoanVo2);
				if(EnumConstants.YES.equals(isRes.getData())){
					vilMsg.append(reqLoanVo2.getLoanNo());
					vilMsg.append(":产品不可用");
				}else{
					loanList.add(reqLoanVo2);
				}

			}
		}
		//可用的产品进行放款
		if(loanList != null && loanList.size() > 0 ){
			reqLoanVo.setLoanList(loanList);
			res = iLoanConfirmService.grantLoan(reqLoanVo);
			ResLoanVo loanVo=	res.getData();
			isSuccess = res.isSuccess();
			//返回成功放款集合
			if(loanVo !=null){
				List<ResLoanVo> successList =loanVo.getSuccessList();
				successMsg =ListAddByStr(successList,StringUtils.SYMBOL_COLON,"放款确认成功");
				//返回失败集合
				List<ResLoanVo> failList =loanVo.getFailList();
				failMsg =ListAddByStr(failList,StringUtils.SYMBOL_COLON,"放款确认失败");
			}
		}
		if(isSuccess){
			retMap.put("result",vilMsg.toString()+failMsg+successMsg);
			retMap.put("isSuceess",true);
		}else{
			retMap.put("result",res.getRepMsg());
		}
		return retMap;
	}

	
	
	

	@RequestMapping(value = "backLoan")
	@ResponseBody
	public Map<String,Object> backLoan(ReqLoanVo reqLoanVo) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		//当前登录用户
		ShiroUser user=ShiroUtils.getShiroUser();
		ResEmployeeVO resEmployeeVO= user.getEmployeeVO();
		String usrCode=resEmployeeVO.getUsercode();
		reqLoanVo.setServiceCode(usrCode);
		reqLoanVo.setServiceName(resEmployeeVO.getName());
		reqLoanVo.setIp(StringUtils.getLocalIP());
		Response<ResLoanVo>	res = iLoanConfirmService.backLoan(reqLoanVo);
		boolean isSuccess =res.isSuccess();
		if(isSuccess){
			retMap.put("isSuccess",isSuccess);
			retMap.put("result",reqLoanVo.getLoanNo()+":退回成功");
		}else{
			retMap.put("result",reqLoanVo.getLoanNo()+":退回失败-"+res.getRepMsg());
		}
		return retMap;
	}
	
	
	
	@RequestMapping(value = "bacthBackLoanConfirm")
	@ResponseBody
	public Map<String,Object> bacthBackLoanConfirm(ReqLoanVo reqLoanVo) {	
		Map<String,Object> retMap = new HashMap<String,Object>();
		boolean isSuccess=true;
		String successMsg =StringUtils.SYMBOL_NULL_STRING;//批量审核退回通过信息
		String failMsg =StringUtils.SYMBOL_NULL_STRING;//批量审核退回失败信息
		StringBuffer vilMsg= new StringBuffer();
		Response<ResLoanVo>	res =null;
		//用户
		ShiroUser user=ShiroUtils.getShiroUser();
		ResEmployeeVO resEmployeeVO= user.getEmployeeVO();
		String usrCode=resEmployeeVO.getUsercode();
		reqLoanVo.setServiceCode(usrCode);
		reqLoanVo.setServiceName(resEmployeeVO.getName());
		reqLoanVo.setIp(StringUtils.getLocalIP());
		List<String> loanNos =reqLoanVo.getLoanNos();
		List<String> ids	=reqLoanVo.getIds();
		List<String> channelCds	=reqLoanVo.getChannelCds();
		if(loanNos ==null || loanNos.size() < 1 ){
			retMap.put("result","loanList对象为空");
			return retMap;
		}
		//借款集合
		List<ReqLoanVo> loanList =new ArrayList<ReqLoanVo>() ;
		ReqLoanVo reqLoanVo2 =null;
		for (int i = 0; i < loanNos.size(); i++) {
			if(!StringUtils.isEmpty(ids.get(i))&&!StringUtils.isEmpty(loanNos.get(i))&&!StringUtils.isEmpty(channelCds.get(i))){
				reqLoanVo2 = new ReqLoanVo();
				reqLoanVo2.setId(Long.parseLong(ids.get(i)));
				reqLoanVo2.setLoanNo(loanNos.get(i));
				reqLoanVo2.setChannelCd(channelCds.get(i));
				reqLoanVo2.setServiceCode(usrCode);
				reqLoanVo2.setServiceName(resEmployeeVO.getName());
				reqLoanVo2.setIp(StringUtils.getLocalIP());
				Response<ResLoanVo>	isRes = iLoanConfirmService.queryLoanCoreState(reqLoanVo2);
				if(isRes.isSuccess()){
					//退回条件判断，查看核心状态（申请中和放款中，正常）
					List<Map<String,Object>> loanCoreStateList = isRes.getData().getLoanCoreStateList();
					for (Map<String, Object> map : loanCoreStateList) {
						if(map.get("loanState") != null 
								&& (EnumConstants.CoreLoanState.SQZ.getValue().equals(map.get("loanState"))
										|| EnumConstants.CoreLoanState.FKZ.getValue().equals(map.get("loanState"))
										|| EnumConstants.CoreLoanState.ZC.getValue().equals(map.get("loanState")))){
									vilMsg.append(reqLoanVo2.getLoanNo());
									vilMsg.append(":该借款正处于:申请中、放款中、已放款，不可进行退回");
								}else{
									loanList.add(reqLoanVo2);
								}
							break;
						}
					}else{
						vilMsg.append(reqLoanVo2.getLoanNo());
						vilMsg.append(isRes.getRepMsg());
					}
			}
		}
		
		if(loanList != null && loanList.size() > 0){
			reqLoanVo.setLoanList(loanList);
			res = iLoanConfirmService.bacthBackLoanConfirm(reqLoanVo);
			ResLoanVo loanVo=	res.getData();
			//得到成功失败借款
			isSuccess =res.isSuccess();
			if(loanVo !=null){
				List<ResLoanVo> successList =loanVo.getSuccessList();
				successMsg =ListAddByStr(successList,StringUtils.SYMBOL_COLON,"放款确认退回成功");
				List<ResLoanVo> failList =loanVo.getFailList();
				failMsg =ListAddByStr(failList,StringUtils.SYMBOL_COLON,"放款确认退回失败");
			}
		}
		if(isSuccess){
			retMap.put("result",vilMsg.toString()+failMsg+successMsg);
			retMap.put("isSuceess",true);
		}else{
			retMap.put("result",res.getRepMsg());
		}
		return retMap;
	}
	//得到财务人员userCodes
	private  boolean isFinanceAuditUser(String userCode){
		boolean isFlag=false;
		ReqEmployeeVO paramReqEmployeeVO =new ReqEmployeeVO();
		//ReqParamVO paramReqParamVO =new ReqParamVO();
		paramReqEmployeeVO.setSysCode("bms");
		paramReqEmployeeVO.setUsercode(userCode);
		Response<List<ResRoleVO>> response =iEmployeeExecuter.findRolesByAccount(paramReqEmployeeVO);
		List<ResRoleVO> resRoleVOs =response.getData();
		for (ResRoleVO resRoleVo : resRoleVOs) {
			if(RoleEnum.FINANCIAL_LOAN.equals(resRoleVo.getCode())||"admin".equals(resRoleVo.getCode())){//非管理员和放款审核角色
				isFlag=true;
			}
		}
		return isFlag;
	}
	
	
	/**
	 * 二级原因
	 * 
	 * @return
	 */
	@RequestMapping(value = "findTwoReason")
	@ResponseBody
	public Object findTwoReason(ReqBMSTMReasonVO reqBMSTMReasonVO) {
		reqBMSTMReasonVO.setSysCode(EnumConstants.BMS_SYSCODE);
		reqBMSTMReasonVO.setOperationModule("FKQR");
		reqBMSTMReasonVO.setOperationType("return");
		reqBMSTMReasonVO.setType("2");
		reqBMSTMReasonVO.setId(reqBMSTMReasonVO.getParentId());
		ResListVO<ReqBMSTMReasonVO>	list = iTmReasonService.twoLevel(reqBMSTMReasonVO);
		List<ReqBMSTMReasonVO>  reasonList=	list.getCollections();
		return reasonList;
	}
	
	

	/**
	 *一级原因
	 * 
	 * @return
	 */
	@RequestMapping(value = "findOneReason")
	@ResponseBody
	public Object findOneReason(ReqBMSTMReasonVO reqBMSTMReasonVO) {
		reqBMSTMReasonVO.setSysCode(EnumConstants.BMS_SYSCODE);
		reqBMSTMReasonVO.setOperationModule("FKQR");
		reqBMSTMReasonVO.setOperationType("return");
		reqBMSTMReasonVO.setType("1");
		ResListVO<ReqBMSTMReasonVO>	list = iTmReasonService.oneLevel(reqBMSTMReasonVO);
		List<ReqBMSTMReasonVO>  reasonList=	list.getCollections();
		return reasonList;
	}
	
	
	@RequestMapping(value = "/auditCommit", method = RequestMethod.POST) 
	@ResponseBody
	public Map<String, Object> auditCommit(@RequestParam("Uploadfile") MultipartFile multipartFile,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		LOGGER.debug("开始导入-----------------------------");	
		// 获取文件名
		String name = multipartFile.getOriginalFilename();
	    name=new String(name.getBytes("ISO-8859-1"), "UTF-8");  
		System.out.println("name:" + name);
		// 进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
		long size = multipartFile.getSize();
		if (name == null || ("").equals(name) || size == 0){
			hashMap.put("nullFile",true);
			return hashMap;
		}
			

		BufferedReader reader =null;
	    InputStreamReader inputStreamReader = null;
	    List<String []> list = new ArrayList<String []>();
	    LOGGER.debug("开始读txt-----------------------------");	
	        try {
	            inputStreamReader = new InputStreamReader(multipartFile.getInputStream(),"GBK");
	            reader = new BufferedReader(inputStreamReader);
	            //读取操作
	            String line =null;
	            String str [] = null;
	            
	            //读取
	            while(null!=(line=reader.readLine())){
	            	//line=new String(line.getBytes("GB2312"), "UTF-8"); 
	                //转义|+|
	                if(StringUtils.isNotEmpty(line.trim())){
	                    str = line.split("\\u007C\\u002B\\u007C");
	                    if(str!=null && str.length > 1){
	                        list.add(str);
	                    }
	                }
	                
	            }
	        }catch (Exception e) {
	          e.printStackTrace();
	          LOGGER.debug("关闭流报错-----------------------------"+e.getMessage());
	          LOGGER.debug("导入报错-----------------------------"+e.getStackTrace());	
	        }finally{
	            
	            try {
	                if (null != reader) {
	                    reader.close();
	                }
	                if(inputStreamReader != null){
	                    inputStreamReader.close();
	                }
	            } catch (Exception e) {
	               e.printStackTrace();
	               LOGGER.debug("关闭流报错-----------------------------"+e.getMessage());
	               LOGGER.debug("关闭流报错-----------------------------"+e.getStackTrace());	
	            }
	        }
		
		if(null==list||list.size()==0){
			hashMap.put("nullFile",true);
			return hashMap;
		}
		
		
		List<ReqBMSWmxtVO> reqBMSWmxtVOs=validate(list);
		iLoanConfirmService.auditCommit(reqBMSWmxtVOs,response);
		


		return null;
	}
	
	public List<ReqBMSWmxtVO> validate(List<String []> data){
		List<ReqBMSWmxtVO> list = new ArrayList<ReqBMSWmxtVO>();
		
		for (String [] str : data) {
			ReqBMSWmxtVO wmxt=new ReqBMSWmxtVO();
			wmxt.setIsSuccessStatus("true");
			wmxt.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
			//机构代码验证
			if(StringUtils.isEmpty(str[0].trim())){
				wmxt.setIsSuccessStatus("false");
				wmxt.setFailReason("机构号不能为空,");
			}else if(!"016".equals(str[0].trim())){
				wmxt.setIsSuccessStatus("false");
				wmxt.setFailReason("机构号码不对,");
			}
			wmxt.setOrganizationCode(str[0].trim());
			
			//合同编号
			if(StringUtils.isEmpty(str[1].trim())){
				wmxt.setIsSuccessStatus("false");
				wmxt.setFailReason((wmxt.getFailReason()==null?"":wmxt.getFailReason())+"合同号不能为空,");
			}else if(!str[1].trim().startsWith("ZD")){
				wmxt.setIsSuccessStatus("false");
				wmxt.setFailReason((wmxt.getFailReason()==null?"":wmxt.getFailReason())+"合同号格式不对,");
			}
			wmxt.setAccountNo(str[1].trim());
			
			//放款结果
			if(StringUtils.isEmpty(str[2].trim())){
				wmxt.setIsSuccessStatus("false");
				wmxt.setFailReason((wmxt.getFailReason()==null?"":wmxt.getFailReason())+"放款结果不能为空,");
			}else if(!"30".equals(str[2].trim())){
				wmxt.setIsSuccessStatus("false");
				wmxt.setFailReason((wmxt.getFailReason()==null?"":wmxt.getFailReason())+"放款结果非放款成功不予受理,");
			}
			wmxt.setLoanResult(str[2].trim());
			
			//银行回盘时间(yyyyMMddhh24mmss)
			if(StringUtils.isEmpty(str[3].trim())){
				wmxt.setIsSuccessStatus("false");
				wmxt.setFailReason((wmxt.getFailReason()==null?"":wmxt.getFailReason())+"银行回盘时间不能为空,");
			}else{
				String dateRegex = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229))([0-1]?[0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$"; 
				if(!str[3].trim().matches(dateRegex)){
					wmxt.setIsSuccessStatus("false");
					wmxt.setFailReason((wmxt.getFailReason()==null?"":wmxt.getFailReason())+"银行回盘时间格式不正确,");
				}
			}
			wmxt.setBankReturnTime(str[3].trim());
			
			//失败原因
			wmxt.setErrorReason(str[4].trim());
			
			//审批结果
			if(StringUtils.isEmpty(str[5].trim())){
				wmxt.setIsSuccessStatus("false");
				wmxt.setFailReason((wmxt.getFailReason()==null?"":wmxt.getFailReason())+"审批结果不能为空,");
			}else if(!"01".equals(str[5].trim())){
				wmxt.setIsSuccessStatus("false");
				wmxt.setFailReason((wmxt.getFailReason()==null?"":wmxt.getFailReason())+"审批结果非审批通过不予处理,");
			}
			wmxt.setApprovalResult(str[5].trim());
			
			//审批拒绝原因
			if(str.length==7){
				wmxt.setApprovalReason(str[6].trim());
			}
			
			
			list.add(wmxt);
		}
		return list;
	}
	
	/**
	 * 导入龙信小贷放款文件
	 * @param file
	 * @param request
	 * @param response
	 * @param requestVo
	 */
	@RequestMapping(value="/importLoanDocument",method = RequestMethod.POST)
	@ResponseBody
	public Response<Object> importLoanDocument(@RequestParam(value = "lxxdOfferFile") MultipartFile file, HttpServletRequest request, HttpServletResponse response, ReqImportDocumentVo requestVo) {
		Response<Object> resp = new Response<Object>();
		try {
			//解决中文文件名乱码问题
			String fileName = new String(file.getOriginalFilename().getBytes(BmsConstant.ISO_8859_1), BmsConstant.UTF_8);
			Workbook workBook = WorkbookFactory.create(file.getInputStream());
			ResEmployeeVO user = ShiroUtils.getCurrentUser();
			requestVo.setServiceCode(user.getUsercode());
			requestVo.setServiceName(user.getName());
			//读取导入的excel数据
			List<ReqImportExcelVO> importDatas = iExcelExport.importExcel(workBook);
			requestVo.setDatas(importDatas);
			//对导入的数据进行业务校验处理
			iLoanConfirmService.importLoanDocument(requestVo);
			// 把反馈数据追加到excel尾部,需要考虑调用核心接口部分成功，部分失败场景
			iExcelExport.addLxxdDataToExcel(workBook, requestVo.getDatas());
			// 导出数据
			iExcelExport.wirteExcel(workBook, response, fileName);
		}catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("导入数据异常！" + e);
			resp.setRepCode(e.getMessage());
		}
		return resp;
	}
	
	/**
	 * 放款状态查询
	 * @return
	 */
	@RequestMapping(value = "queryPaymentStatus")
	@ResponseBody
	public Object queryPaymentStatus() {
		return EnumPaymentStatus.queryAllPaymetStatus();
	}
	
	/**
	 *字符串 拼接
	 * 
	 * @param strings
	 */
	private static String ListAddByStr(List<ResLoanVo> resloanList,String splitStr,String appendStr) {
		if(resloanList ==null){
			return StringUtils.SYMBOL_NULL_STRING;
		}
		StringBuffer result=new StringBuffer();
		for (ResLoanVo resLoanVo : resloanList) {
			result.append(resLoanVo.getLoanNo());
			result.append(splitStr);
			result.append(appendStr);
			if(!StringUtils.isBlank(resLoanVo.getErrorMessage())){
				result.append(splitStr+resLoanVo.getErrorMessage());
			}	
			result.append("<br/>");
		}
		return result.toString();
	}
	/**
	 * 核心状态查询，退回条件判断，查看核心状态（申请中和放款中，正常）
	 * @param reqLoanVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年6月27日下午5:04:08
	 */
	@RequestMapping(value = "backCheckLoanCoreState")
	@ResponseBody
	public Map<String,Object> backCheckLoanCoreState(ReqLoanVo reqLoanVo) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		//当前登录用户
		ShiroUser user=ShiroUtils.getShiroUser();
		ResEmployeeVO resEmployeeVO= user.getEmployeeVO();
		String usrCode=resEmployeeVO.getUsercode();
		reqLoanVo.setServiceCode(usrCode);
		reqLoanVo.setServiceName(resEmployeeVO.getName());
		reqLoanVo.setIp(StringUtils.getLocalIP());
		
		Response<ResLoanVo>	res = iLoanConfirmService.queryLoanCoreState(reqLoanVo);
		boolean isSuccess = true;
		if(res.isSuccess()){
			//退回条件判断，查看核心状态（申请中和放款中，正常）
			List<Map<String,Object>> loanCoreStateList = res.getData().getLoanCoreStateList();
			for (Map<String, Object> map : loanCoreStateList) {
				if(map.get("loanState") != null 
						&& (EnumConstants.CoreLoanState.SQZ.getValue().equals(map.get("loanState"))
								|| EnumConstants.CoreLoanState.FKZ.getValue().equals(map.get("loanState"))
								|| EnumConstants.CoreLoanState.ZC.getValue().equals(map.get("loanState")))){
					isSuccess = false;
					break;
				}
			}

			retMap.put("isSuccess",isSuccess);
		}else{
			retMap.put("result",res.getRepMsg());
		}
		return retMap;
	}
	/**
	 * 放款审核导出数据查询
	 * @param reqLoanVo
	 * @return
	 * @author lifz YM10160
	 * @date 2017年7月4日下午5:06:18
	 */
	@RequestMapping(value = "exportLoanConfirmInfo")
	@ResponseBody
	public String exportLoanConfirmInfo(ReqLoanVo reqLoanVo,HttpServletResponse response){
		try {
			// 请求参数构造
			reqLoanVo.setSysCode(EnumConstants.BMS_SYSCODE);
			// 接口调用
			reqLoanVo.setRtfNodeState(EnumConstants.RtfNodeState.FKSHSUBMIT.getValue());
			reqLoanVo.setRtfState(EnumConstants.RtfState.FKQR.getValue());
		    ResListVO<ResLoanExportInfoVo> res = iLoanConfirmService.findLoanExportInfo(reqLoanVo);
			List<ResLoanExportInfoVo> resLoanExportInfoVos =res.getCollections();
			Date nowDate = new Date();
			String fileName ="放款确认结果表-"+DateUtil.defaultFormatDay(nowDate) + ".xls";
			ExcelObjVo excelVoSum = new ExcelObjVo(fileName, "", 1, 0);
			Map<String, String[]> fieldsSum = new HashMap<String, String[]>();
			fieldsSum.put("fieldNames", LOANCONFIRM_EXP_FIELDNAMES);
			fieldsSum.put("fieldCodes", LOANCONFIRM_EXP_FIELDCODES);
			Workbook workBook = excelExport.buildWorkFormat(resLoanExportInfoVos, fieldsSum,LOANCONFIRM_CLASS, excelVoSum);
			excelExport.wirteExcel(workBook, response, fileName);
		} catch (IOException e) {
			e.printStackTrace();
			return "导出excel出错";
		} catch (Exception e) {
			e.printStackTrace();
			return "导出excel出错";
		}
		return "success";
	}
	
	/**
	 * 放款审核导出数据查询
	 * @param reqLoanVo
	 * @return
	 * @author lifz YM10160
	 * @date 2017年7月4日下午5:06:18
	 */
	@RequestMapping(value = "queryLoanConfirmInfo")
	@ResponseBody
	public Map queryLoanConfirmInfo(ReqLoanVo reqLoanVo,HttpServletResponse response){
		Map<String,Object> retMap = new HashMap<String,Object>();
		boolean queryFlag=true;
			// 请求参数构造
			reqLoanVo.setSysCode(EnumConstants.BMS_SYSCODE);
			// 接口调用
			reqLoanVo.setRtfNodeState(EnumConstants.RtfNodeState.FKSHSUBMIT.getValue());
			reqLoanVo.setRtfState(EnumConstants.RtfState.FKQR.getValue());
		    ResListVO<ResLoanExportInfoVo> res = iLoanConfirmService.findLoanExportInfo(reqLoanVo);
			List<ResLoanExportInfoVo> resLoanExportInfoVos =res.getCollections();
			if(resLoanExportInfoVos==null||resLoanExportInfoVos.size()<1){
				queryFlag=false;
				retMap.put("result","导出未查询到数据");
			}
			retMap.put("isSuccess",queryFlag);
			return retMap;
		
	}
}
