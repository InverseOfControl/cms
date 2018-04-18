package com.ymkj.cms.web.boss.controller.master;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractChangeVo;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractChangeVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.common.SystemCode;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.IContractChangeService;
import com.ymkj.cms.web.boss.service.master.IEnumCodeService;
import com.ymkj.cms.web.boss.service.master.IOrgLimitChannelService;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;
import com.ymkj.sso.client.ShiroUtils;

/**
 * 签约改派Controller
 * @author YM10166
 *
 */
@Controller
@RequestMapping("contractChange")
public class ContractChangeController extends BaseController {
	
	@Autowired
	private IContractChangeService contractChangeService;
	@Autowired
	private IOrgLimitChannelService orgLimitChannelService;
	@Autowired
	private IEnumCodeService enumCodeService;
	
	/**
	 * 页面跳转
	 * @return
	 */
	@RequestMapping("view")
	public String view() {
		return "master/contractChange/contractChangeList";
	}
	
	/**
	 * 分页列表查询
	 * @param reqBMSContractChangeVo
	 * @return
	 */
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSContractChangeVo> listPage(ReqBMSContractChangeVo reqBMSContractChangeVo) {
		//防止pageNum和PageSize取反   PageNum为页码，，PageSize为每页数据条
		reqBMSContractChangeVo.setPageNum(reqBMSContractChangeVo.getPage());
		reqBMSContractChangeVo.setPageSize(reqBMSContractChangeVo.getRows());
		//验证分页参数是否正确
		if(reqBMSContractChangeVo.getPageNum() == 0 || reqBMSContractChangeVo.getPageSize() == 0){
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[]{"pageNum | pageSize"});
		}
		//当前登录人信息,判断登录人是否属于直通车营业部
		Integer saleDeptType = this.checkSaleDeptTypeOnUser();
		List<String> orgTZCList = new ArrayList<String>();//直通车机构id集合
		
		//当前登录人,权限下营业网点
		List<ResOrganizationVO> orgVoList = this.findDataOrgIdsByAccount();
		if(orgVoList != null && !orgVoList.isEmpty()){
			List<String> contractBranchIdList = new ArrayList<String>();
			for (ResOrganizationVO orgVo : orgVoList) {
				contractBranchIdList.add(orgVo.getId().toString());
//				直通车判断  营业部类型(0:普通, 1直通车)
				if(saleDeptType != 0 && orgVo.getSaleDeptType() != null && 1==orgVo.getSaleDeptType()){
					orgTZCList.add(orgVo.getId().toString());
				}
			}
			reqBMSContractChangeVo.setContractBranchIdList(contractBranchIdList);
		}
		reqBMSContractChangeVo.setSaleDeptType(saleDeptType);
		reqBMSContractChangeVo.setOrgTZCList(orgTZCList);
		
		// 需要要剔除的借款状态集合
		List<String> concatRtfNodeStateNoCheckList = new ArrayList<String>();//联合状态   rtfNodeState+check
		List<String> rtfNodeStateList = new ArrayList<String>();
		
		String businessSegment = reqBMSContractChangeVo.getBusinessSegment();
		if(EnumConstants.BusinessSegmentLoan.LDHJ.getValue().equals(businessSegment)){//录单环节
			rtfNodeStateList.add(EnumConstants.RtfNodeState.SQLR_SAVE.getValue());//申请录入保存
			rtfNodeStateList.add(EnumConstants.RtfNodeState.SQLR_SUBMIT.getValue());//录入复核办理
			rtfNodeStateList.add(EnumConstants.RtfNodeState.LRFH_RETURN.getValue());//录入复核退回
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSCSRETURN.getValue());//初审退回录入(不为CHECK,CHECK_NO_PASS)
			concatRtfNodeStateNoCheckList.add(EnumConstants.RtfNodeState.XSCSRETURN.getValue()+"_"+EnumConstants.ChcekNodeState.CHECK.getValue());//初审退回录入(不为CHECK,CHECK_NO_PASS)
			concatRtfNodeStateNoCheckList.add(EnumConstants.RtfNodeState.XSCSRETURN.getValue()+"_"+EnumConstants.ChcekNodeState.CHECKNOPASS.getValue());//初审退回录入(不为CHECK,CHECK_NO_PASS)
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSZSRETURN.getValue());//终审退回录入

			
		} else if(EnumConstants.BusinessSegmentLoan.XSHJ.getValue().equals(businessSegment)){//信审环节
			 
			rtfNodeStateList.add(EnumConstants.RtfNodeState.CSFPSUBMIT.getValue());//初审分派办理
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSCSASSIGN.getValue());//信审初审办理
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSCSHANGUP.getValue());//信审初审挂起
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSCSPASS.getValue());//信审初审通过
			rtfNodeStateList.add(EnumConstants.RtfNodeState.HIGHPASS.getValue());//初审提交高审复核
			
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSCSRETURN.getValue());//初审退回录入(不为NO_CHECK,CHECK_PASS)
			concatRtfNodeStateNoCheckList.add(EnumConstants.RtfNodeState.XSCSRETURN.getValue()+"_"+EnumConstants.ChcekNodeState.NOCHECK.getValue());//初审退回录入(不为NO_CHECK,CHECK_PASS)
			concatRtfNodeStateNoCheckList.add(EnumConstants.RtfNodeState.XSCSRETURN.getValue()+"_"+EnumConstants.ChcekNodeState.CHECKPASS.getValue());//初审退回录入(不为NO_CHECK,CHECK_PASS)

			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSCSREJECT.getValue());//信审初审拒绝(不为 NO_CHECK,CHECK_PASS)
			concatRtfNodeStateNoCheckList.add(EnumConstants.RtfNodeState.XSCSREJECT.getValue()+"_"+EnumConstants.ChcekNodeState.NOCHECK.getValue());//信审初审拒绝(不为 NO_CHECK,CHECK_PASS)
			concatRtfNodeStateNoCheckList.add(EnumConstants.RtfNodeState.XSCSREJECT.getValue()+"_"+EnumConstants.ChcekNodeState.CHECKPASS.getValue());//信审初审拒绝(不为 NO_CHECK,CHECK_PASS)
			
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSCSSUBMIT.getValue());//终审分派办理
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSZSASSIGN.getValue());//信审终审办理
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSZSHANGUP.getValue());//信审终审挂起
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSZSRTNCS.getValue());//终审退回初审
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSZSSUBMITHIGH.getValue());//终审提交高审
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSZSSUBMITBACK.getValue());//终审回到终审
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSZSSUBMITAPPROVAL.getValue());//终审提交协审
			
		} else if(EnumConstants.BusinessSegmentLoan.QYHJ.getValue().equals(businessSegment)){//签约环节
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSZSPASS.getValue());//信审终审通过
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSZSSUBMIT.getValue());//签约分派办理
			rtfNodeStateList.add(EnumConstants.RtfNodeState.HTQYASSIGN.getValue());//合同签约办理
			rtfNodeStateList.add(EnumConstants.RtfNodeState.HTQRRETURN.getValue());//合同确认退回
			rtfNodeStateList.add(EnumConstants.RtfNodeState.FKSHRETURN.getValue());//放款审核退回
			rtfNodeStateList.add(EnumConstants.RtfNodeState.FKQRRETURN.getValue());//放款确认退回
			

		} else {
			rtfNodeStateList.add(EnumConstants.RtfNodeState.SQLR_SAVE.getValue());//申请录入保存
			rtfNodeStateList.add(EnumConstants.RtfNodeState.SQLR_SUBMIT.getValue());//录入复核办理
			rtfNodeStateList.add(EnumConstants.RtfNodeState.LRFH_RETURN.getValue());//录入复核退回
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSCSRETURN.getValue());//初审退回录入(不为CHECK,CHECK_NO_PASS)
//			concatRtfNodeStateNoCheckList.add(EnumConstants.RtfNodeState.XSCSRETURN.getValue()+"_"+EnumConstants.ChcekNodeState.CHECK.getValue());//初审退回录入(不为CHECK,CHECK_NO_PASS)
//			concatRtfNodeStateNoCheckList.add(EnumConstants.RtfNodeState.XSCSRETURN.getValue()+"_"+EnumConstants.ChcekNodeState.CHECKNOPASS.getValue());//初审退回录入(不为CHECK,CHECK_NO_PASS)
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSZSRETURN.getValue());//终审退回录入
			
			rtfNodeStateList.add(EnumConstants.RtfNodeState.CSFPSUBMIT.getValue());//初审分派办理
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSCSASSIGN.getValue());//信审初审办理
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSCSHANGUP.getValue());//信审初审挂起
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSCSPASS.getValue());//信审初审通过
			rtfNodeStateList.add(EnumConstants.RtfNodeState.HIGHPASS.getValue());//初审提交高审复核
			
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSCSRETURN.getValue());//初审退回录入(不为NO_CHECK,CHECK_PASS)
//			concatRtfNodeStateNoCheckList.add(EnumConstants.RtfNodeState.XSCSRETURN.getValue()+"_"+EnumConstants.ChcekNodeState.NOCHECK.getValue());//初审退回录入(不为NO_CHECK,CHECK_PASS)
//			concatRtfNodeStateNoCheckList.add(EnumConstants.RtfNodeState.XSCSRETURN.getValue()+"_"+EnumConstants.ChcekNodeState.CHECKPASS.getValue());//初审退回录入(不为NO_CHECK,CHECK_PASS)

			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSCSREJECT.getValue());//信审初审拒绝(不为 NO_CHECK,CHECK_PASS)
			concatRtfNodeStateNoCheckList.add(EnumConstants.RtfNodeState.XSCSREJECT.getValue()+"_"+EnumConstants.ChcekNodeState.NOCHECK.getValue());//信审初审拒绝(不为 NO_CHECK,CHECK_PASS)
			concatRtfNodeStateNoCheckList.add(EnumConstants.RtfNodeState.XSCSREJECT.getValue()+"_"+EnumConstants.ChcekNodeState.CHECKPASS.getValue());//信审初审拒绝(不为 NO_CHECK,CHECK_PASS)
			
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSCSSUBMIT.getValue());//终审分派办理
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSZSASSIGN.getValue());//信审终审办理
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSZSHANGUP.getValue());//信审终审挂起
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSZSRTNCS.getValue());//终审退回初审
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSZSSUBMITHIGH.getValue());//终审提交高审
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSZSSUBMITBACK.getValue());//终审回到终审
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSZSSUBMITAPPROVAL.getValue());//终审提交协审

			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSZSPASS.getValue());//信审终审通过
			rtfNodeStateList.add(EnumConstants.RtfNodeState.XSZSSUBMIT.getValue());//签约分派办理
			rtfNodeStateList.add(EnumConstants.RtfNodeState.HTQYASSIGN.getValue());//合同签约办理
			rtfNodeStateList.add(EnumConstants.RtfNodeState.HTQRRETURN.getValue());//合同确认退回
			rtfNodeStateList.add(EnumConstants.RtfNodeState.FKSHRETURN.getValue());//放款审核退回
			rtfNodeStateList.add(EnumConstants.RtfNodeState.FKQRRETURN.getValue());//放款确认退回
			

		}
		if(!concatRtfNodeStateNoCheckList.isEmpty()){
			reqBMSContractChangeVo.setConcatRtfNodeStateNoCheckList(concatRtfNodeStateNoCheckList);
		}
		
		reqBMSContractChangeVo.setRtfNodeStateList(rtfNodeStateList);
		//必须设置请求编码
		reqBMSContractChangeVo.setSysCode(SystemCode.SysCode);
		PageResult<ResBMSContractChangeVo> pageResult = contractChangeService.listPage(reqBMSContractChangeVo);
		ResponsePage<ResBMSContractChangeVo> pageList = new ResponsePage<ResBMSContractChangeVo>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}
	
	/**
	 * 签约改派
	 * @param reqVo
	 * @return
	 */
	@RequestMapping(value = "changeContract")
	@ResponseBody
	public Map<String, Object> changeContract(ReqBMSContractChangeVo reqVo) {
		//验证是否选择数据和签约网点
		if(StringUtils.isBlank(reqVo.getLoanNos())){
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[]{"loanNos"});
		}
		if(StringUtils.isBlank(reqVo.getContractBranchId())){
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[]{"contractBranchId"});
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		ResEmployeeVO currentUser = ShiroUtils.getCurrentUser();
		reqVo.setServiceCode(currentUser.getUsercode());
		reqVo.setServiceName(currentUser.getName());
		// 调用签约改派接口
		ResBMSContractChangeVo resVo = contractChangeService.changeContract(reqVo);
		if(resVo != null && resVo.getIsFailedLoanNoList() != null && !resVo.getIsFailedLoanNoList().isEmpty()){
			hashMap.put("isFailed", true);
			hashMap.put("isFailedLoanNoList", resVo.getIsFailedLoanNoList());
		} else {
			
			hashMap.put("isSuccess", true);
		}
		return hashMap;
	}
	/**
	 * 依据产品，查询配置的对应可用签约网点  ,多产品取交集
	 * @param reqVo
	 * @return
	 */
	@RequestMapping(value = "findOrgByProductCodeListIntersect")
	@ResponseBody
	public List<ResOrganizationVO> findOrgByProductCodeListIntersect(String productCodes) {
		//验证
		if(StringUtils.isBlank(productCodes)){
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[]{"productCodes"});
		}

		ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO = new ReqBMSOrgLimitChannelVO();
		//数据处理
		String[] productCodeArr = productCodes.split(",");
		List<String> productCodeList = new ArrayList<String>();
		for (int i = 0; i < productCodeArr.length; i++) {
			String productCode = productCodeArr[i];
			productCodeList.add(productCode);
		}
		//当前登录人,权限下营业网点
		List<ResOrganizationVO> orgVoList = this.findDataOrgIdsByAccount();
		if(orgVoList != null && !orgVoList.isEmpty()){
			List<Long> contractBranchIdList = new ArrayList<Long>();
			for (ResOrganizationVO orgVo : orgVoList) {
				contractBranchIdList.add(orgVo.getId());
			}
			reqBMSOrgLimitChannelVO.setOrgIdList(contractBranchIdList);
		}
		
		reqBMSOrgLimitChannelVO.setProductCodeList(productCodeList);

		//获取有效机构
		return orgLimitChannelService.findOrgByProductCodeListIntersect(reqBMSOrgLimitChannelVO);
	}
	
	
	/**
	 * 签约网点，产品 配置有效校验
	 * @param reqVo
	 * @return
	 */
	@RequestMapping(value = "branchProductRelevanceCheck")
	@ResponseBody
	public Map<String, Object> branchProductRelevanceCheck(String branchProductRelevances) {
		if(StringUtils.isBlank(branchProductRelevances)){
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[]{"branchProductRelevances"});
		}
		List<ReqBMSOrgLimitChannelVO> reqBMSOrgLimitChannelVOList = new ArrayList<ReqBMSOrgLimitChannelVO>();
		//1111,aaa;222,bbbb   类型String解析
		String[] branchProductRelevanceArr =branchProductRelevances.split(";");
		for (int i = 0; i < branchProductRelevanceArr.length; i++) {
			//接口参数封装
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO = new ReqBMSOrgLimitChannelVO();
			
			String relevance = branchProductRelevanceArr[i];
			String[] relevanceArr = relevance.split(",");
			if(relevanceArr.length > 2){
				reqBMSOrgLimitChannelVO.setOrgId(Long.valueOf(relevanceArr[0]));
				reqBMSOrgLimitChannelVO.setProductCode(relevanceArr[1]);
				reqBMSOrgLimitChannelVO.setLoanNo((relevanceArr[2]));
				reqBMSOrgLimitChannelVO.setSysCode(SystemCode.SysCode);
				
				reqBMSOrgLimitChannelVOList.add(reqBMSOrgLimitChannelVO);
			}
			
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap = orgLimitChannelService.branchProductRelevanceCheck(reqBMSOrgLimitChannelVOList);
		return hashMap;
	}
	
	/**
	 * 根据工号获取机构数据(签约网点)
	 * @param reqVo
	 * @return
	 */
	@RequestMapping(value = "findDataOrgIdsByAccount")
	@ResponseBody
	public List<ResOrganizationVO> findDataOrgIdsByAccount() {
		/*List<ResOrganizationVO> depts = cacheClient.getList("ContractChangeController.findDataOrgIdsByAccount");
		if(depts == null || depts.isEmpty()){
			//当前登录人信息
			
			ShiroUtils.getAccount();
			ReqParamVO vo  = new ReqParamVO();
			vo.setSysCode(SystemCode.SysCode);
			vo.setLoginUser(ShiroUtils.getAccount());
			
			//查询所有营业网店
			depts = contractChangeService.findDataOrgIdsByAccount(vo);
			cacheClient.setList("ContractChangeController.findDataOrgIdsByAccount", depts);
			
		}*/
		//登录人不同机构不同，不可以加入缓存
		
		//当前登录人信息
		ShiroUtils.getAccount();
		ReqParamVO vo  = new ReqParamVO();
		vo.setSysCode(SystemCode.SysCode);
		vo.setLoginUser(ShiroUtils.getAccount());
		
		//查询所有营业网店
		List<ResOrganizationVO> depts = contractChangeService.findDataOrgIdsByAccount(vo);
		//获取有效机构
		return depts;
	}
	/**
	 * 根据机构ID查指定角色的员工
	 * @param roleCodes   多个用‘，’拼接
	 * @param orgId
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月27日上午11:05:15
	 */
	@RequestMapping(value = "findEmployeeByDeptAndRole")
	@ResponseBody
	public List<ResEmployeeVO> findEmployeeByDeptAndRole(String roleCodes,String orgId) {
		List<String> roleCodeList = new ArrayList<String>();
		//参数解析
		if(roleCodes == null || roleCodes.length() == 0){
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[]{"roleCodes"});
		}else{
			String[] roleCodeArr = roleCodes.split(",");
			if(roleCodeArr == null || roleCodeArr.length ==0){
				throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[]{"roleCodes"});
			}
			for (int i = 0; i < roleCodeArr.length; i++) {
				roleCodeList.add(roleCodeArr[i]);
			}
		}
		//参数封装
		ReqParamVO vo  = new ReqParamVO();
		vo.setOrgId(Long.valueOf(orgId));
		vo.setRoleCodes(roleCodeList);
		vo.setSysCode(SystemCode.SysCode);
		//获取有效机构
		List<ResEmployeeVO> employeeVoList = contractChangeService.findEmployeeByDeptAndRole(vo);
		return employeeVoList;
	}
	/**
	 * 根据机构ID查指定角色的员工
	 * @param roleCodes   多个用‘，’拼接
	 * @param orgId
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月27日上午11:05:15
	 */
	@RequestMapping(value = "findEmployeeBy")
	@ResponseBody
	public List<ResEmployeeVO> findEmployeeBy(String roleCodes,String orgId, String signCodeRejects) {
		List<String> roleCodeList = new ArrayList<String>();
		String[] signCodeRejectArr = null;
		//参数解析
		if(signCodeRejects != null || signCodeRejects.length() != 0){
			signCodeRejectArr = signCodeRejects.split(",");
			if(signCodeRejectArr == null || signCodeRejectArr.length ==0){
				throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[]{"signCodeRejects"});
			}
		}
		if(roleCodes == null || roleCodes.length() == 0){
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[]{"roleCodes"});
		}else{
			String[] roleCodeArr = roleCodes.split(",");
			if(roleCodeArr == null || roleCodeArr.length ==0){
				throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[]{"roleCodes"});
			}
			for (int i = 0; i < roleCodeArr.length; i++) {
				roleCodeList.add(roleCodeArr[i]);
			}
		}
		//参数封装
		ReqParamVO vo  = new ReqParamVO();
		vo.setOrgId(Long.valueOf(orgId));
		vo.setRoleCodes(roleCodeList);
		vo.setSysCode(SystemCode.SysCode);
		//获取有效机构
		List<ResEmployeeVO> employeeVoList = contractChangeService.findEmployeeByDeptAndRole(vo);
		List<ResEmployeeVO> result = new ArrayList<ResEmployeeVO>();
		for (ResEmployeeVO employeeVo : employeeVoList) {
			boolean check = true; 
			for (String signCodeReject : signCodeRejectArr) {
				if(signCodeReject.equals(employeeVo.getUsercode())){
					check = false;
					break;
				}
			}
			if(check){
				result.add(employeeVo);
			}
		}
		return result;
	}
	
	/**
	 * 查询签约改派的可见业务环节
	 * @param reqBMSEnumCodeVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月28日下午2:12:29
	 */
	@RequestMapping(value = "getBusinessSegment")
	@ResponseBody
	public List<ResBMSEnumCodeVO> getBusinessSegment(ReqBMSEnumCodeVO reqBMSEnumCodeVO) {
		
		reqBMSEnumCodeVO.setCodeType(EnumConstants.EnumCodeType.BusinessSegmentLoan.getValue());
		
		return enumCodeService.listEnumCodeBy(reqBMSEnumCodeVO).getCollections();
	}
	/**
	 * 检查当前登入人是否属于直通车营业部
	 * 直通车判断  营业部类型(0:普通, 1直通车)
	 * @param reqBMSEnumCodeVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月28日下午2:12:29
	 */
	@RequestMapping(value = "checkSaleDeptTypeOnUser")
	@ResponseBody
	public Integer checkSaleDeptTypeOnUser() {
		Integer saleDeptType = 0;
		List<ResOrganizationVO> currentOrgList = ShiroUtils.getShiroUser().getDepartments();
		for (ResOrganizationVO currentOrg : currentOrgList) {
			if(currentOrg.getSaleDeptType() != null && 1==currentOrg.getSaleDeptType()){
				saleDeptType = currentOrg.getSaleDeptType();
				break;
			}
		}
		return saleDeptType;
	}
}
