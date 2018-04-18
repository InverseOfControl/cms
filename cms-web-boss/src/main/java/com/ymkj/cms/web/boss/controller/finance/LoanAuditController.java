package com.ymkj.cms.web.boss.controller.finance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumCHConstants.EnumApplyInputFlagConstants;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.master.IBMSLoanLogExecuter;
import com.ymkj.cms.biz.api.vo.request.channel.ExcelObjVo;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryLoanLogVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.channel.ResBmsLineOfferVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLinePaymentOfferBatch;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanExportInfoVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSQueryLoanLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.web.boss.common.DateUtil;
import com.ymkj.cms.web.boss.common.FileProperties;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.common.StringUtils;
import com.ymkj.cms.web.boss.common.SystemCode;
import com.ymkj.cms.web.boss.controller.channel.LinePaymentController;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.channel.IExcelExport;
import com.ymkj.cms.web.boss.service.finance.ILoanAuditService;
import com.ymkj.cms.web.boss.service.master.IChannelProductService;
import com.ymkj.cms.web.boss.service.master.IChannelService;
import com.ymkj.cms.web.boss.service.master.IIntegratedSearchService;
import com.ymkj.cms.web.boss.service.master.ILoanLogService;
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
@RequestMapping("loanAudit")
public class LoanAuditController {
	@Autowired
	private IChannelProductService chaProService;
	
	@Autowired
	private IChannelService channelService;
	
	@Autowired
	private ILoanAuditService loanAuditService;
	
	@Autowired
	private ITmReasonService iTmReasonService;
	
	@Autowired
	private IRoleExecuter iRoleExecuter;
	
	@Autowired
	private IIntegratedSearchService iIntegratedSearchService;

	@Autowired
	private FileProperties fileProperties;
	
	@Autowired
	private IExcelExport excelExport;
	
	private static String[] LOANAUDIT_EXP_FIELDNAMES = { "签约网点",	"客户姓名"	,"借款产品"	,"签约期限"	,"身份证号码",	"渠道名称"	,"合同编号",	"合同金额",	"放款金额"	,
			 "所属银行",	"所属分行",	"银行卡号",	"签约日期",	"还款起日"	,"还款止日"	,"咨询费"	,"管理费"	,"丙方管理费"	,"丁方管理费","评估费",	"费用合计",	"风险金",	"合同确认时间" };

	private static String[] LOANAUDIT_EXP_FIELDCODES = {  "contractBranch",  "name", "productName", "contractTrem","idNo","channelName","contractNum", "pactMoney", "grantMoney",  "bank", "bankFullName" ,"applyBankCardNo", "signDate", "startrdate",
			 "endrdate", "referRate&dm", "manageRate&dm", "managerRateForPartyc&dm",  "managerRateForPartyd&dm", "evalRate&dm", "rateSum&dm", "risk&dm",  "confirmEndDate" };
	
	private static Class<?> LOANAUDIT_CLASS = ResLoanExportInfoVo.class;
	
	
	@RequestMapping("view")
	public String view( HttpServletRequest request) { 
			//ShiroUser user=ShiroUtils.getShiroUser();
			String picFileDataUrl =fileProperties.getPicfiledataUrl();
			ResEmployeeVO user=ShiroUtils.getCurrentUser();
			request.setAttribute("userCode", user.getUsercode());
			request.setAttribute("userName", user.getName());
			request.setAttribute("picFileDataUrl", picFileDataUrl);
			/*ResEmployeeVO resEmployeeVO= user.getEmployeeVO();
			String usrCode=resEmployeeVO.getUsercode();
			boolean isFinanceUser= isFinanceAuditUser(usrCode);
			if(isFinanceUser){*/
				return "finance/loanAudit/loanAudit";
			/*}else{//非管理员和放款审核角色不可进入
				request.setAttribute("errorCode", "200090");
				request.setAttribute("ex", "警告：非财务人员管理员不可进入放款审核");
				return "test_error";
			}*/
	
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
		reqLoanVo.setRtfNodeState(EnumConstants.RtfNodeState.HTQRSUBMIT.getValue());
		reqLoanVo.setRtfState(EnumConstants.RtfState.FKSH.getValue());
		//财务CODE
		ShiroUser user=ShiroUtils.getShiroUser();
		ResEmployeeVO resEmployeeVO= user.getEmployeeVO();
		String usrCode=resEmployeeVO.getUsercode();
		reqLoanVo.setServiceCode(usrCode);
		reqLoanVo.setServiceName(resEmployeeVO.getName());
		PageResult<ResLoanVo> pageResult = loanAuditService.listPage(reqLoanVo);
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
		reqLoanVo.setTaskName(EnumConstants.WF_FKSH);
		reqLoanVo.setRtfNodeState(EnumConstants.RtfNodeState.HTQRSUBMIT.getValue());
		reqLoanVo.setRtfState(EnumConstants.RtfState.FKSH.getValue());
		ShiroUser user=ShiroUtils.getShiroUser();
		ResEmployeeVO resEmployeeVO= user.getEmployeeVO();
		String usrCode=resEmployeeVO.getUsercode();
		reqLoanVo.setServiceCode(usrCode);
		reqLoanVo.setServiceName(resEmployeeVO.getName());
		reqLoanVo.setIp(StringUtils.getLocalIP());
		PageResult<ResLoanVo> pageResult = loanAuditService.doneListPage(reqLoanVo);
		ResponsePage<ResLoanVo> pageList = new ResponsePage<ResLoanVo>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
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
		reqBMSTMReasonVO.setOperationModule("FKSH");
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
		reqBMSTMReasonVO.setOperationModule("FKSH");
		reqBMSTMReasonVO.setOperationType("return");
		reqBMSTMReasonVO.setType("1");
		ResListVO<ReqBMSTMReasonVO>	list = iTmReasonService.oneLevel(reqBMSTMReasonVO);
		List<ReqBMSTMReasonVO>  reasonList=	list.getCollections();
		return reasonList;
	}
	
	
	/**
	 *查询日志
	 * 
	 * @return
	 */
	@RequestMapping(value = "findloanLog")
	@ResponseBody
	public List<ResBMSQueryLoanLogVO> findloanLog(ReqQueryLoanLogVO reqQueryLoanLogVO) {
		reqQueryLoanLogVO.setLoanNo(reqQueryLoanLogVO.getLoanNo());
		ShiroUser user=ShiroUtils.getShiroUser();
		ResEmployeeVO resEmployeeVO= user.getEmployeeVO();
		String usrCode=resEmployeeVO.getUsercode();
		reqQueryLoanLogVO.setServiceCode(usrCode);
		reqQueryLoanLogVO.setServiceName(resEmployeeVO.getName());
		reqQueryLoanLogVO.setIp(StringUtils.getLocalIP());
		List<ResBMSQueryLoanLogVO>	list = iIntegratedSearchService.queryLoanLog(reqQueryLoanLogVO);
		return list;
	}
	
	
	
	/**
	 * 
	 * 获取所有未删除的渠道信息
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
		/*reqBMSChannelVO.setSysCode(EnumConstants.BMS_SYSCODE);
		List<ResBMSChannelVO> userChannellist = new ArrayList<>();
		userChannellist = channelService.getAllChannel(reqBMSChannelVO);*/
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
		try {
			list = chaProService.findAllProduct(reqBMSProductVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

/*	if (value == "applyInput") {
   	 return '营业部';
   }
   else if(value == "directApplyInput"){
   	 return '直通车';
   }else{
       return '';
   }*/
	/**
	 * 申请件类型
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
	
	/**
	 * 获取机构审核状态
	 * @return
	 */
	@RequestMapping(value = "findOrgAuditState")
	@ResponseBody
	public Object findOrgAuditState(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO){
	List<Map<String ,String>> orgAuditStateList =new ArrayList<Map<String ,String>>();
			Map<String,String> orgAuditStateMap =null;
			for(EnumConstants.OrgAuditState orgAuditState:EnumConstants.OrgAuditState.values()){
				orgAuditStateMap=new HashMap<String,String>();
				orgAuditStateMap.put("code", orgAuditState.getCode());
				orgAuditStateMap.put("value", orgAuditState.getValue());
				orgAuditStateList.add(orgAuditStateMap);
			}
			return orgAuditStateList;
	}
	
	@RequestMapping(value = "passLoanAudit")
	@ResponseBody
	public Map<String,Object> passLoanAudit(ReqLoanVo reqLoanVo) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		ShiroUser user=ShiroUtils.getShiroUser();
		ResEmployeeVO resEmployeeVO= user.getEmployeeVO();
		String usrCode=resEmployeeVO.getUsercode();
		reqLoanVo.setServiceCode(usrCode);
		reqLoanVo.setServiceName(resEmployeeVO.getName());
		reqLoanVo.setIp(StringUtils.getLocalIP());
		Response<String>  isRes= loanAuditService.valiProductIsDisabled(reqLoanVo);
		if(EnumConstants.YES.equals(isRes.getData())){
			retMap.put("result",reqLoanVo.getLoanNo()+":产品不可用");
		}else{
			Response<ResLoanVo>	res = loanAuditService.passAuditLoan(reqLoanVo);
			boolean isSuccess =res.isSuccess();
			if(isSuccess){
				retMap.put("isSuccess",isSuccess);
				retMap.put("result",reqLoanVo.getLoanNo()+":放款审核通过");
			}else{
				retMap.put("result",reqLoanVo.getLoanNo()+":放款审核失败-"+res.getRepMsg());
			}
		}
		return retMap;
	}
	
	@RequestMapping(value = "bacthPassLoanAudit")
	@ResponseBody
	public Map<String,Object> bacthPassLoanAudit(ReqLoanVo reqLoanVo) {	
		Map<String,Object> retMap = new HashMap<String,Object>();
		boolean isSuccess=true;
		String successMsg =StringUtils.SYMBOL_NULL_STRING;//批量审核通过信息
		String failMsg =StringUtils.SYMBOL_NULL_STRING;//批量审核失败信息
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
		List<String> owningBranchIds =reqLoanVo.getOwningBranchIds();
		List<String> channelCds =reqLoanVo.getChannelCds();
		List<String> productCds =reqLoanVo.getProductCds();
		List<BigDecimal> contractLmts =reqLoanVo.getContractLmts();
		List<Integer> contractTrems =reqLoanVo.getContractTrems();
		if(loanNos ==null || loanNos.size() < 1 ){
			retMap.put("result","loanList对象为空");
			return retMap;
		}
		//借款集合
		List<ReqLoanVo> loanList =new ArrayList<ReqLoanVo>() ;
		ReqLoanVo reqLoanVo2 =null;
		for (int i = 0; i < loanNos.size(); i++) {
			if(!StringUtils.isEmpty(ids.get(i))&&!StringUtils.isEmpty(loanNos.get(i))){
				reqLoanVo2 = new ReqLoanVo();
				reqLoanVo2.setId(Long.parseLong(ids.get(i)));
				reqLoanVo2.setLoanNo(loanNos.get(i));
				reqLoanVo2.setOwningBranchId(owningBranchIds.get(i));
				reqLoanVo2.setChannelCd(channelCds.get(i));
				reqLoanVo2.setProductCd(productCds.get(i));
				reqLoanVo2.setContractLmt(contractLmts.get(i));
				reqLoanVo2.setContractTrem(contractTrems.get(i));
				Response<String>  isRes= loanAuditService.valiProductIsDisabled(reqLoanVo2);
				if(EnumConstants.YES.equals(isRes.getData())){
					vilMsg.append(reqLoanVo2.getLoanNo());
					vilMsg.append(":产品不可用");
				}else{
					loanList.add(reqLoanVo2);
				}
			}
		}
		if(loanList != null && loanList.size() > 0 ){
			reqLoanVo.setLoanList(loanList);
			res = loanAuditService.bacthPassAudit(reqLoanVo);
			ResLoanVo loanVo=	res.getData();
			//得到成功失败借款
			isSuccess =res.isSuccess();
			if(loanVo !=null){
				List<ResLoanVo> successList =loanVo.getSuccessList();
				successMsg =ListAddByStr(successList,StringUtils.SYMBOL_COLON,"放款审核成功");
				List<ResLoanVo> failList =loanVo.getFailList();
				failMsg =ListAddByStr(failList,StringUtils.SYMBOL_COLON,"放款审核失败");
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
	@RequestMapping(value = "bacthBackLoanAudit")
	@ResponseBody
	public Map<String,Object> bacthBackLoanAudit(ReqLoanVo reqLoanVo) {	
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
				loanList.add(reqLoanVo2);
			}
		}
		if(loanList != null && loanList.size() > 0 ){
			reqLoanVo.setLoanList(loanList);
			res = loanAuditService.bacthBackLoanAudit(reqLoanVo);
			ResLoanVo loanVo=	res.getData();
			//得到成功失败借款
			isSuccess =res.isSuccess();
			if(loanVo !=null){
				List<ResLoanVo> successList =loanVo.getSuccessList();
				successMsg =ListAddByStr(successList,StringUtils.SYMBOL_COLON,"放款审核退回成功");
				List<ResLoanVo> failList =loanVo.getFailList();
				failMsg =ListAddByStr(failList,StringUtils.SYMBOL_COLON,"放款审核退回失败");
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
	
	
	
	
	@RequestMapping(value = "backAudit")
	@ResponseBody
	public Map<String,Object> backAudit(ReqLoanVo reqLoanVo) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		ShiroUser user=ShiroUtils.getShiroUser();
		ResEmployeeVO resEmployeeVO= user.getEmployeeVO();
		String usrCode=resEmployeeVO.getUsercode();
		reqLoanVo.setServiceCode(usrCode);
		reqLoanVo.setServiceName(resEmployeeVO.getName());
		reqLoanVo.setIp(StringUtils.getLocalIP());
		Response<ResLoanVo> res  = loanAuditService.backAudit(reqLoanVo);
		boolean isSuccess =res.isSuccess();
		if(isSuccess){
			retMap.put("isSuccess",isSuccess);
			retMap.put("result",reqLoanVo.getLoanNo()+":退回成功");
		}else{
			retMap.put("result",reqLoanVo.getLoanNo()+":退回失败-"+res.getRepMsg());
		}
		return retMap;
	}
	
	/**
	 * 放款审核导出
	 * @param reqLoanVo
	 * @return
	 * @author lifz YM10139
	 * @date 2017年7月4日下午5:06:18
	 */
	@RequestMapping(value = "queryLoanAuditInfo")
	@ResponseBody
	public Map queryLoanAuditInfo(ReqLoanVo reqLoanVo,HttpServletResponse response){
		Map<String,Object> retMap = new HashMap<String,Object>();
		boolean queryFlag=true;
			// 请求参数构造
			reqLoanVo.setSysCode(EnumConstants.BMS_SYSCODE);
			// 接口调用
			reqLoanVo.setRtfNodeState(EnumConstants.RtfNodeState.HTQRSUBMIT.getValue());
			reqLoanVo.setRtfState(EnumConstants.RtfState.FKSH.getValue());
		    ResListVO<ResLoanExportInfoVo> res = loanAuditService.findLoanExportInfo(reqLoanVo);
			List<ResLoanExportInfoVo> resLoanExportInfoVos =res.getCollections();
			
			if(resLoanExportInfoVos==null||resLoanExportInfoVos.size()<1){
				queryFlag=false;
				retMap.put("result","导出未查询到数据");
			}
			retMap.put("isSuccess",queryFlag);
			return retMap;
	}
	
	/**
	 * 放款审核导出查询
	 * @param reqLoanVo
	 * @return
	 * @author lifz YM10139
	 * @date 2017年7月4日下午5:06:18
	 */
	@RequestMapping(value = "exportLoanAuditInfo")
	@ResponseBody
	public String exportLoanAuditInfo(ReqLoanVo reqLoanVo,HttpServletResponse response){
		try {
			// 请求参数构造
			reqLoanVo.setSysCode(EnumConstants.BMS_SYSCODE);
			// 接口调用
			reqLoanVo.setRtfNodeState(EnumConstants.RtfNodeState.HTQRSUBMIT.getValue());
			reqLoanVo.setRtfState(EnumConstants.RtfState.FKSH.getValue());
		    ResListVO<ResLoanExportInfoVo> res = loanAuditService.findLoanExportInfo(reqLoanVo);
			List<ResLoanExportInfoVo> resLoanExportInfoVos =res.getCollections();
			Date nowDate = new Date();
			String fileName ="放款审核结果表-"+DateUtil.defaultFormatDay(nowDate) + ".xls";
			ExcelObjVo excelVoSum = new ExcelObjVo(fileName, "", 1, 0);
			Map<String, String[]> fieldsSum = new HashMap<String, String[]>();
			fieldsSum.put("fieldNames", LOANAUDIT_EXP_FIELDNAMES);
			fieldsSum.put("fieldCodes", LOANAUDIT_EXP_FIELDCODES);
			
			Workbook workBook = excelExport.buildWorkFormat(resLoanExportInfoVos, fieldsSum,LOANAUDIT_CLASS, excelVoSum);
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
}
