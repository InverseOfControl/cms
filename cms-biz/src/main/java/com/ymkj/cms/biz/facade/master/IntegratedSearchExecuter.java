package com.ymkj.cms.biz.facade.master;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.enums.EnumCHConstants;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.master.IIntegratedSearchExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSAduitPersonVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqIntegratedSearchVO;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryLoanDetailVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryLoanLogVO;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryLoanVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryRepaymentDetailVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryRepaymentSummaryVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryflowVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAduitPersonVo;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResIntegratedSearchVO;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryLoanDetailVo;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryLoanVo;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryRepaymentDetailVo;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryRepaymentSummaryVo;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryflowVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSQueryLoanLogVO;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.master.IBMSIntegratedSearchDao;
import com.ymkj.cms.biz.entity.master.BMSIntegraedSearchEntity;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.facade.audit.AduitPersonExecuter;
import com.ymkj.cms.biz.service.http.impl.CreditHttpServiceImpl;
import com.ymkj.cms.biz.service.ifc.IPMSInterface;
import com.ymkj.cms.biz.service.master.IBMSIntegratedSearchService;
import com.ymkj.pms.biz.api.enums.RoleEnum;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.request.ReqOrganizationVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;
import com.ymkj.pms.biz.api.vo.response.ResRoleVO;


@Service
public class IntegratedSearchExecuter implements IIntegratedSearchExecuter {

	public static String LOG_TAG = "IntegratedSearchExecuter.";

	private Log log = LogFactory.getLog(IntegratedSearchExecuter.class);

	@Autowired
	private IBMSIntegratedSearchService iBMSIntegratedSearchService;

	@Autowired
	private IOrganizationExecuter OrganizationExecuter;

	@Autowired
	private AduitPersonExecuter aduitPersonExecuter;

	@Autowired
	private CreditHttpServiceImpl creditHttpServiceImpl;

	@Autowired
	private IEmployeeExecuter iEmployeeExecuter;
	
	@Autowired
	private IBMSIntegratedSearchDao integratedSearchDao;
	@Autowired
	private IPMSInterface iPMSInterface;

	@Value("#{env['antifraudInfoUrl']?:''}")
	private String antifraudInfoUrl;
	
	/**
	 * 根据前台传过来的数据，信息排序整理
	 * @param reqIntegratedSearchVO
	 * @return
	 */
	public static String getSortValue(ReqIntegratedSearchVO reqIntegratedSearchVO){
		if(StringUtils.isEmpty(reqIntegratedSearchVO.getFieldSort())){
			return "lb.apply_date desc";
		}
		String sort = " desc";
		if(reqIntegratedSearchVO.getRulesSort() == 1){
			sort = " asc";
		}
		String field = "lb.apply_date";
		if("loanNo".equals(reqIntegratedSearchVO.getFieldSort())){
			field = "lb.loan_no";
		}else if("name".equals(reqIntegratedSearchVO.getFieldSort())){
			field = "CONVERT(lb.NAME USING gbk)";
		}else if("idNo".equals(reqIntegratedSearchVO.getFieldSort())){
			field = "lb.ID_NO";
		}else if("applyType".equals(reqIntegratedSearchVO.getFieldSort())){
			field = "lb.APPLY_TYPE";
		}else if("corpName".equals(reqIntegratedSearchVO.getFieldSort())){
			if(reqIntegratedSearchVO.getCorpName()!=null && !("").equals(reqIntegratedSearchVO.getCorpName())){
				field = "CONVERT(t.corp_name USING gbk)";
			}else{
				field = "CONVERT(api.corp_name USING gbk)";
			}
		}else if("status".equals(reqIntegratedSearchVO.getFieldSort())){
			field = "lb.status";
		}else if("branch".equals(reqIntegratedSearchVO.getFieldSort())){
			field = "lb.owning_branch_id";
		}else if("branchAttr".equals(reqIntegratedSearchVO.getFieldSort())){
			field = "lb.OWNING_BRANCH_ATTRIBUTE";
		}else if("initProductName".equals(reqIntegratedSearchVO.getFieldSort())){
			field = "CONVERT(lp.init_product_name USING gbk)";
		}else if("productName".equals(reqIntegratedSearchVO.getFieldSort())){
			field = " CONVERT(lp.product_name USING gbk)";
		}else if("applyDate".equals(reqIntegratedSearchVO.getFieldSort())){
			field = "la.created_time";
		}else{
			field = "lb.apply_date";
		}
		return field + sort;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResponse<ResIntegratedSearchVO> search(ReqIntegratedSearchVO reqIntegratedSearchVO) {
        long s = System.currentTimeMillis();
        log.info("综合查询接口开始时间："+s);

		PageResponse<ResIntegratedSearchVO> response = new PageResponse<ResIntegratedSearchVO>();
		List<ResIntegratedSearchVO> records = new ArrayList<ResIntegratedSearchVO>();
		List<Long> owningBranchIdList = new ArrayList<Long>();

		// 参数校验 
		Response<Object> validateResponse = Validate.getInstance().validate(reqIntegratedSearchVO);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}
		String userCode = reqIntegratedSearchVO.getServiceCode();

		reqIntegratedSearchVO.setCaseIdentify(ObjectUtils.toString(dataAsc(reqIntegratedSearchVO.getCaseIdentify())));
		reqIntegratedSearchVO.setApplyType(ObjectUtils.toString(dataAsc(reqIntegratedSearchVO.getApplyType())));
		
		List<String> customerManagerList = reqIntegratedSearchVO.getCustomerManagerList();
		List<String> caseIdentifyList = reqIntegratedSearchVO.getCaseIdentifyList();
        List<String>addressList=reqIntegratedSearchVO.getAddressList();
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanKit.bean2map(reqIntegratedSearchVO);
            log.info("综合查询请求参数："+paramMap);
		} catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		paramMap.put("caseIdentifyList", caseIdentifyList);
		paramMap.put("customerManagerList", customerManagerList);
		paramMap.put("addressList", addressList);
		
		//录单系统查询
		if(reqIntegratedSearchVO.getSysCode().equals(EnumConstants.CFS_SYSTEM_CODE)){
			try{
				paramMap = throughTrain(userCode, paramMap);
			}catch(Exception e){
				log.error("录单个性化条件综合查询异常：", e);
				return response;
			}
		}
		
		String startTime = ObjectUtils.toString(reqIntegratedSearchVO.getStartTime());
		String endTime = ObjectUtils.toString(reqIntegratedSearchVO.getEndTime());
		String startCreateTime = ObjectUtils.toString(reqIntegratedSearchVO.getStartCreatedTime());
		String endCreateTime = ObjectUtils.toString(reqIntegratedSearchVO.getEndCreatedTime());
		
		if(StringUtils.isNotBlank(startTime)) paramMap.put("startTime", startTime + " 00:00:00");
		if(StringUtils.isNotBlank(endTime)) paramMap.put("endTime", endTime + " 23:59:59");
		if(StringUtils.isNotBlank(startCreateTime)) paramMap.put("startCreateTime", startCreateTime + " 00:00:00");
		if(StringUtils.isNotBlank(endCreateTime)) paramMap.put("endCreateTime", endCreateTime + " 23:59:59");
		
		paramMap.put("sortValue", getSortValue(reqIntegratedSearchVO));
		//新的查询
		paramMap.put("pageNum", reqIntegratedSearchVO.getPageNum());
		paramMap.put("pageSize", reqIntegratedSearchVO.getPageSize());
		PageBean<BMSIntegraedSearchEntity> pageBean = integratedSearchDao.mainSelet(paramMap);
		List<BMSIntegraedSearchEntity> integraedSearchEntitys = pageBean.getRecords();
        log.info("综合查询数据库结果时间:"+(System.currentTimeMillis() - s));

		if(CollectionUtils.isEmpty(integraedSearchEntitys)){
			response.setRepMsg("综合查询列表为空");
			return response;
		}

		//获取结构ID列表
		for (BMSIntegraedSearchEntity integraedSearchEntity : integraedSearchEntitys) {
			String owningBranchId = ObjectUtils.toString(integraedSearchEntity.getOwningBranchId());
			if(StringUtils.isNotBlank(owningBranchId)){
				owningBranchIdList.add(Long.valueOf(owningBranchId));
			}
		}

		//调权限系统的接口：根据机构ID列表获取机构列表
		List<ResOrganizationVO> organizationList = iPMSInterface.findByIds(owningBranchIdList);
		if(CollectionUtils.isEmpty(organizationList)){
			return response;
		}

		//设置贷后信息
		for (BMSIntegraedSearchEntity integraedSearchEntity : integraedSearchEntitys) {
			ResIntegratedSearchVO resIntegratedSearchVO = new ResIntegratedSearchVO();
			String reqOrgId =  integraedSearchEntity.getOwningBranchId();
			for (ResOrganizationVO resOrganizationVO : organizationList) {
				String resOrgId = ObjectUtils.toString(resOrganizationVO.getId());
				if(resOrgId.equals(reqOrgId)){
					integraedSearchEntity.setBranch(resOrganizationVO.getName());
					integraedSearchEntity.setBranchAttr(resOrganizationVO.getDepLevel());
					break;
				}
			}
			BeanUtils.copyProperties(integraedSearchEntity, resIntegratedSearchVO);
			
			/**
			 * 1、通过债权ID(loan_id)到核心获取逾期、结清状态。
			 * 2、loan_id为空在借款系统中获取，如果loan_id有值去核心获取 
			 */
			String loanId = integraedSearchEntity.getLoanId();
			if(StringUtils.isNotBlank(loanId)){//有值，去核心获取
				ReqQueryLoanVo ReqQueryLoanVo = new ReqQueryLoanVo();
				ReqQueryLoanVo.setLoanNo(integraedSearchEntity.getLoanNo());
				ReqQueryLoanVo.setUserCode(reqIntegratedSearchVO.getServiceCode());

				Response<ResQueryLoanVo> queryLoanResponse = this.queryLoan(ReqQueryLoanVo);
				if(null != queryLoanResponse.getData()){
					ResQueryLoanVo resQueryLoanVo = queryLoanResponse.getData();
					String loanState = resQueryLoanVo.getLoanState();
					if(loanState.equals("结清")){
						resIntegratedSearchVO.setStatus(resQueryLoanVo.getSettlementType());
					}else{
						resIntegratedSearchVO.setStatus(loanState);
					}
				}
			}
			
			//设置审批结论
			String status = ObjectUtils.toString(resIntegratedSearchVO.getStatus());
			if(status.equals("正常")||status.equals("逾期")||status.equals("结清")||status.equals("通过")){
				resIntegratedSearchVO.setConclusion(integraedSearchEntity.getAccLmt());
			}else if(status.equals("拒绝")){
				resIntegratedSearchVO.setConclusion(integraedSearchEntity.getPrimaryReason());
			}else if(status.equals("取消")){
				if(StringUtils.isNotBlank(ObjectUtils.toString(integraedSearchEntity.getSecondReason()))){
					resIntegratedSearchVO.setConclusion(integraedSearchEntity.getPrimaryReason()+"|"+integraedSearchEntity.getSecondReason());
				}else{
					resIntegratedSearchVO.setConclusion(integraedSearchEntity.getPrimaryReason());
				}
			}
			records.add(resIntegratedSearchVO);
		}
        log.info("综合查询接口结束时间:"+(System.currentTimeMillis() - s));

		BeanUtils.copyProperties(pageBean, response, "records");
		response.setRecords(records);

		return response;
	}
	
	/**
	 * 录单个性化查询条件封装
	 * 直通车和普通营业部
	 * @author YM10159
	 */
	public Map<String,Object> throughTrain(String userCode, Map<String,Object> paramMap){
		log.info("录单个性化条件综合查询:userCode="+userCode);
		paramMap.put("cfs", "cfs");
		
		List<String> customerManagerList = (List<String>) paramMap.get("customerManagerList");
		boolean bool = false;
		
		//根据工号查询所在营业部信息
		List<ResOrganizationVO> organizationList = iPMSInterface.findDataDeptsByAccount(userCode);
		if(CollectionUtils.isEmpty(organizationList)) throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL);
		
		String deptType = ObjectUtils.toString(organizationList.get(0).getSaleDeptType()); //1-直通车   0-普通营业部
		deptType = StringUtils.isBlank(deptType) || deptType.equals("0") ? "0" : "1"; 
		String reqEmpRole = deptType.equals("1") ? "telSale" : "customerManager"; //要传入的员工角色 
		
		List<ResRoleVO> roleList = iPMSInterface.findRolesByAccount(userCode);
		if(CollectionUtils.isEmpty(roleList)) throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL);

		for (ResRoleVO resRoleVO : roleList) {
			String code = ObjectUtils.toString(resRoleVO.getCode());
			log.info("录单个性化条件综合查询:角色code="+code);
			if("superAdmin|admin".indexOf(resRoleVO.getCode()) != -1){ //管理员和超级管理员，默认分页查询全表
				bool = true;
				break;
			}else if("director".equals(code) || "telSaleMaster".equals(code)){ //业务主任、电销主管，默认查询他组下所有的客户经理的进件
				if(CollectionUtils.isEmpty(customerManagerList)){ //如果查询条件客户经理为空，默认查询所有客户经理
					customerManagerList = new ArrayList<String>();
					customerManagerList.add(userCode);
					List<ResEmployeeVO> employeeList = iPMSInterface.findEmpByUsercode(userCode, reqEmpRole , null);
					if(CollectionUtils.isEmpty(employeeList)) throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL);
					for (ResEmployeeVO resEmployeeVO : employeeList) {
						customerManagerList.add(resEmployeeVO.getUsercode());
					}
					log.info("录单个性化条件综合查询:客户经理或电销主管列表customerManagerList="+customerManagerList);
					paramMap.put("customerManagerList", customerManagerList);
				}
				bool = true;
				break;
			}
		}
		
		//如何是客户经理、客服、电销专员，根据其所在营业部ID查询
		if(!bool){
			List<Long> tempList = new ArrayList<>();
			for(ResOrganizationVO organization : organizationList){
				tempList.add(organization.getId());
			}
			paramMap.put("orgId", tempList);
		}
		return paramMap;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Response<ResQueryLoanVo> queryLoan(ReqQueryLoanVo reqQueryLoanVo) {
		Response<ResQueryLoanVo> response = new Response<ResQueryLoanVo>();
		// 参数校验
		Response<Object> validateResponse = Validate.getInstance().validate(reqQueryLoanVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}

		//调用核心接口
		HttpResponse httpResponse = iBMSIntegratedSearchService.queryLoan(reqQueryLoanVo);
		if(null == httpResponse){
			log.info(LOG_TAG+"queryLoan接口响应：null;");
			return response;
		}
		
		Map<String,Object> contentMap = JsonUtils.decode(httpResponse.getContent(), Map.class);
		if(!"000000".equals(contentMap.get("code"))){
			log.info(LOG_TAG+"queryLoan接口响应：非000000;");
			return response;
		}
		ResQueryLoanVo resQueryLoanVo = JsonUtils.decode(JsonUtils.encode(contentMap.get("loan")), ResQueryLoanVo.class);
		response.setData(resQueryLoanVo);
		response.setRepMsg(ObjectUtils.toString(contentMap.get("message")));
		return response;
	}

	@Override
	public Response<ResQueryRepaymentSummaryVo> queryRepaymentSummary( ReqQueryRepaymentSummaryVo reqQueryRepaymentSummaryVo) {

		boolean bool = false;
		Response<ResQueryRepaymentSummaryVo> response = new Response<ResQueryRepaymentSummaryVo>();

		// 参数校验
		Response<Object> validateResponse = Validate.getInstance().validate(reqQueryRepaymentSummaryVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}

		//调用核心接口
		HttpResponse httpResponse = iBMSIntegratedSearchService.queryRepaymentSummary(reqQueryRepaymentSummaryVo);

		Map<String,Object> contentMap = JsonUtils.decode(httpResponse.getContent(), Map.class);

		if(contentMap.get("code").equals("000000")){
			ResQueryRepaymentSummaryVo resQueryRepaymentSummaryVo = JsonUtils.decode(JsonUtils.encode(contentMap.get("repayInfo")), ResQueryRepaymentSummaryVo.class);
			response.setData(resQueryRepaymentSummaryVo);
		}

		response.setRepMsg(ObjectUtils.toString(contentMap.get("message")));
		return response;
	}

	@Override
	public Response<List<ResQueryRepaymentDetailVo>> queryRepaymentDetailAll( ReqQueryRepaymentDetailVo reqQueryRepaymentDetailVo) {

		boolean bool = false;
		Response<List<ResQueryRepaymentDetailVo>> response = new Response<List<ResQueryRepaymentDetailVo>>();

		// 参数校验
		Response<Object> validateResponse = Validate.getInstance().validate(reqQueryRepaymentDetailVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}

		HttpResponse httpResponse = iBMSIntegratedSearchService.queryRepaymentDetailAll(reqQueryRepaymentDetailVo);
		Map<String,Object> contentMap = JsonUtils.decode(httpResponse.getContent(), Map.class);

		if(contentMap.get("code").equals("000000")){
			List<ResQueryRepaymentDetailVo> resQueryRepaymentDetailVoList = JsonUtils.decode(JsonUtils.encode(contentMap.get("repaymentDetail")), List.class);
			response.setData(resQueryRepaymentDetailVoList);
		}

		response.setRepMsg(ObjectUtils.toString(contentMap.get("message")));
		return response;
	}

	@Override
	public Response<List<ResQueryflowVo>> queryflow(ReqQueryflowVo reqQueryflowVo) {

		boolean bool = false;
		Response<List<ResQueryflowVo>> response = new Response<List<ResQueryflowVo>>();

		// 参数校验
		Response<Object> validateResponse = Validate.getInstance().validate(reqQueryflowVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}

		HttpResponse httpResponse = iBMSIntegratedSearchService.queryflow(reqQueryflowVo);

		Map<String,Object> contentMap = JsonUtils.decode(httpResponse.getContent(), Map.class);

		if(contentMap.get("code").equals("000000")){
			List<ResQueryflowVo> resQueryflowVoList = JsonUtils.decode(JsonUtils.encode(contentMap.get("accountCardVOs")), List.class);
			response.setData(resQueryflowVoList);
		}
		response.setRepMsg(ObjectUtils.toString(contentMap.get("message")));

		return response;
	}

	@Override
	public Response<List<ResBMSQueryLoanLogVO>> queryLoanLog(ReqQueryLoanLogVO reqQueryLoanLogVO) {
		log.info(LOG_TAG+"queryLoanLog：借款日志查询开始，loanNo="+reqQueryLoanLogVO.getLoanNo());
		
		Response<List<ResBMSQueryLoanLogVO>> response = new Response<List<ResBMSQueryLoanLogVO>>();
		List<ResBMSQueryLoanLogVO> records = new ArrayList<ResBMSQueryLoanLogVO>();

		// 参数校验 
		Response<Object> validateResponse = Validate.getInstance().validate(reqQueryLoanLogVO);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}

		//获取员工查询借款日志的特殊角色信息
		ReqEmployeeVO reqEmployeeVO = new ReqEmployeeVO("bms");
		reqEmployeeVO.setUsercode(reqQueryLoanLogVO.getServiceCode());

		Response<List<ResRoleVO>> roleResponse  = iEmployeeExecuter.findRolesByAccount(reqEmployeeVO);
		if(!roleResponse.isSuccess()){
			log.info(LOG_TAG+"queryLoanLog：调用平台接口【根据工号查询拥有的角色列表】失败");
			return response;
		}
		List<ResRoleVO> roleList = roleResponse.getData();

		StringBuffer specialRole = new StringBuffer(); 
		for (ResRoleVO resRoleVO : roleList) {
			String roleCode = resRoleVO.getCode();
			if(roleCode.equals(EnumCHConstants.LoanLogRole.XSXXRZ.getValue())){
				specialRole.append(roleCode).append(",");
			}
			if(roleCode.equals(EnumCHConstants.LoanLogRole.XSXXYY.getValue())){
				specialRole.append(roleCode).append(",");
			}
			if(roleCode.equals(EnumCHConstants.LoanLogRole.LDXXYY.getValue())){
				specialRole.append(roleCode).append(",");
			}
		}
		log.info(LOG_TAG+"queryLoanLog：借款日志查询开始，特殊日志角色："+specialRole);
		
		//根据角色查询借款日志
		Map<String,Object> paramsMap = new HashMap<>();
		if(specialRole.toString().indexOf(EnumCHConstants.LoanLogRole.XSXXRZ.getValue()) != -1){
			paramsMap.put("specialRole", "hasAmsDetailLog");
		}else{
			paramsMap.put("specialRole", "noAmsDetailLog");
		}
		paramsMap.put("loanNo", reqQueryLoanLogVO.getLoanNo());
		List<BMSSysLoanLog> loanLogList = iBMSIntegratedSearchService.queryLoanLog(paramsMap);
		
		//审核需要复核的操作需要替换展示(有信审详细日志角色才能看到)
		if(specialRole.toString().indexOf(EnumCHConstants.LoanLogRole.XSXXRZ.getValue()) != -1){
			loanLogList = updateCheckLongLog(loanLogList);
		}
		
		//重构根据角色查询借款日志
		loanLogList = this.queryLoanLogByRole(specialRole.toString(), loanLogList);

		for (int i = 0; i < loanLogList.size(); i++) {
			BMSSysLoanLog bmsSysLoanLog = loanLogList.get(i);
			String optionType = bmsSysLoanLog.getOperationType();
			String optionTModule = bmsSysLoanLog.getOperationModule();
			String operator = bmsSysLoanLog.getOperator();
			String status = bmsSysLoanLog.getStatus();
			String rtfNodeState = bmsSysLoanLog.getRtfNodeState();
			try{
				if(optionTModule.equals("信审")){ //没有信审详细日志角色
					if(optionType.equals("信审中")){
						loanLogList.get(i).setOperationTime(null);
						bmsSysLoanLog.setFirstLevleReasons("");
					}else{
						bmsSysLoanLog.setOperationType(EnumCHConstants.OptionType.valueOf("OPTION_"+bmsSysLoanLog.getOperationType()).getValue());
						//信审环节的复核通过进行转换
						if(optionType.equals("112")){
							if(rtfNodeState.equals("XSCS-REJECT")) {
								bmsSysLoanLog.setOperationType("拒绝");
							}else if(rtfNodeState.equals("XSCS-RETURN")){
								bmsSysLoanLog.setOperationType("退回");
							}
						}
						//退回门店
						if(optionType.equals("123")) bmsSysLoanLog.setOperationType("退回");
						//信审终审提交
						if(rtfNodeState.equals("XSZS-PASS") && optionType.equals("101")) bmsSysLoanLog.setOperationType("通过");
					}
				}else{
					bmsSysLoanLog.setOperationModule(EnumCHConstants.RtfState.valueOf(bmsSysLoanLog.getOperationModule()).getValue());
					bmsSysLoanLog.setOperationType(EnumCHConstants.OptionType.valueOf("OPTION_"+bmsSysLoanLog.getOperationType()).getValue());

					//系统自动取消、拒绝的案件，操作环节设置为“规则评分”
					if(operator.equals("系统") && (status.equals("REFUSE") || status.equals("CANCEL"))){
						bmsSysLoanLog.setOperationModule("规则评分");
					}
					//信审初审复核环节的展示进行转换
					if(optionTModule.equals("XSCS") && (optionType.equals("113") || optionType.equals("112"))){
						bmsSysLoanLog.setOperationModule("复核确认");
						if(optionType.equals("113")) bmsSysLoanLog.setOperationType("退回");
						if(optionType.equals("112")) bmsSysLoanLog.setOperationType("确认");
					}
					//初审自动派单环节转换
					if(rtfNodeState.equals("XSCS-ASSIGN") || (rtfNodeState.equals("CSFP-SUBMIT") && optionType.equals("104"))) bmsSysLoanLog.setOperationModule("初审分派");
					//终审自动派单环节转换
					if(rtfNodeState.equals("XSZS-ASSIGN")) bmsSysLoanLog.setOperationModule("终审分派");
					//签约分派环节转换
					if(optionTModule.equals("QYFP") && optionType.equals("122")) bmsSysLoanLog.setOperationModule("合同签约");
					//初审、终审自动分派
					if(optionType.equals("114") || optionType.equals("116")) bmsSysLoanLog.setOperationType("分派");
					//终审分派退回、拒绝
					if(optionType.equals("129") || optionType.equals("130") || optionType.equals("131")){
						bmsSysLoanLog.setOperationModule("终审分派");
						if(optionType.equals("129") || optionType.equals("130")) bmsSysLoanLog.setOperationType("退回");
						if(optionType.equals("131")) bmsSysLoanLog.setOperationType("拒绝");
					}
				}
			}catch(Exception e){}

			ResBMSQueryLoanLogVO queryLoanLogVO = new ResBMSQueryLoanLogVO();
			BeanUtils.copyProperties(bmsSysLoanLog, queryLoanLogVO);
			records.add(queryLoanLogVO);
		}

		response.setData(records);
		return response;
	}
	
	/**
	 * 若需要组长复核且复核中的
	 * @param loanLogList 
	 */
	public List<BMSSysLoanLog> updateCheckLongLog(List<BMSSysLoanLog> loanLogList){
		if(CollectionUtils.isEmpty(loanLogList)){
			return loanLogList;
		}
		for (int i = 0; i < loanLogList.size(); i++) {
			BMSSysLoanLog loanl = loanLogList.get(i);
			String checkNodeState = ObjectUtils.toString(loanl.getCheckNodeState());
			String operationType = ObjectUtils.toString(loanl.getOperationType());
			//如果当前需要复核，那么替换操作类型
			if (StringUtils.isNotBlank(checkNodeState) && checkNodeState.equals(EnumConstants.ChcekNodeState.CHECK.getValue())) {
				String checkOpt = "108|109|115"; //通过、退回、拒绝
				if(checkOpt.indexOf(operationType) != -1){
					loanLogList.get(i).setOperationType(operationType+"0");
				}
			}
		}
		return loanLogList;
	}

	/**
	 * <p>Description:根据不同特殊角色查询借款日志</p>
	 * 1、特殊日志角色有三种：信审详细日志角色、信审原因角色、录单原因角色
	 * 2、信审详细日志角色：能看到信审环节的详细操作
	 * 3、信审原因角色：能看到信审环节拒绝的一级原因和二级原因
	 * 4、录单原因角色：能看到录单环节拒绝的一级原因和二级原因
	 * @uthor YM10159
	 * @date 2017年6月8日 下午2:11:04
	 * @param specialRole  特殊角色
	 * @param loanLogList  借款日志列表
	 */
	public List<BMSSysLoanLog> queryLoanLogByRole(String specialRole, List<BMSSysLoanLog> loanLogList){
		
		String[] specialRoleArr = {};
		if(StringUtils.isNotBlank(specialRole)){
			specialRoleArr = specialRole.split(",");
		}
		//信审中的操作
		String applyStatus = "111|113|114|116|117|118|119|120|130"; 
		String rejectLink = "XSZS-REJECT|CSFP-REJECT"; //拒绝环节
		String returnLink = "XSZS-RETURN|XSZS-PASS|CSFP-RETURN"; //退回环节
		String ldLink = "LRFH|SQLR"; //录单环节
				
		for (int i = 0; i < loanLogList.size(); i++) {
			BMSSysLoanLog loanLog = loanLogList.get(i);	
			String status = ObjectUtils.toString(loanLog.getStatus());
			String reason = ObjectUtils.toString(loanLog.getFirstLevleReasons());
			String operationType = ObjectUtils.toString(loanLog.getOperationType());
			String operationModule = ObjectUtils.toString(loanLog.getOperationModule());
			String rtfNodeState = ObjectUtils.toString(loanLog.getRtfNodeState());
            String checkNodeState=ObjectUtils.toString(loanLog.getCheckNodeState());
            String remark=ObjectUtils.toString(loanLog.getRemark());
            
            /**
             * 没有信审详细日志角色
             * 1.没有信审详细日志角色的，操作人都是空的
             * 2.备注详情会在五个节点显示：初审拒绝、终审拒绝、初审退回、终审退回门店、终审通过
             */
            if(specialRoleArr.length == 0 || (specialRoleArr.length > 0 && specialRole.indexOf("amsDetailLog") == -1)){
				if(operationModule.indexOf("XS") != -1){
					//环节
					loanLogList.get(i).setOperationModule("信审");
					//操作
					if(!rtfNodeState.equals("XSZS-PASS") && applyStatus.indexOf(operationType) != -1){
						loanLogList.get(i).setOperationType("信审中");
					}
					//信审初审 拒绝、退回、通过，复核中
					if(checkNodeState.equals("CHECK")){
						if(rtfNodeState.equals("XSCS-REJECT") || rtfNodeState.equals("XSCS-RETURN") || rtfNodeState.equals("XSCS-PASS")){
							loanLogList.get(i).setOperationType("信审中");
						}
					}
					loanLogList.get(i).setOperator("");
					loanLogList.get(i).setRemark("");
					loanLogList.get(i).setFirstLevleReasons("");
				}
				//信审初审、终审拒绝  展示备注和一级原因
				if((rtfNodeState.equals("XSCS-REJECT") && (checkNodeState.equals("CHECK_PASS") || checkNodeState.equals("NO_CHECK")))
						||  rejectLink.indexOf(rtfNodeState) != -1){
					loanLogList.get(i).setRemark(remark);
					loanLogList.get(i).setFirstLevleReasons(specialRole.indexOf("amsDetailReason") == -1 ? reason.split("；")[0] : reason);
				}
				//信审初审、终审退回  展示备注和一二级原因
				if((rtfNodeState.equals("XSCS-RETURN") && (checkNodeState.equals("CHECK_PASS") || checkNodeState.equals("NO_CHECK")))
						|| returnLink.indexOf(rtfNodeState) != -1){
					loanLogList.get(i).setOperationType(operationType);
					loanLogList.get(i).setRemark(remark);
					loanLogList.get(i).setFirstLevleReasons(reason);
				}
			}
			
			/**
			 * 有特殊角色，根据日志角色查看相关日志
			 */
			if(specialRoleArr.length == 1){
				if(specialRole.indexOf("amsDetailLog") != -1){ //只有信审日志角色的，拒绝操作的只能看到拒绝一级原因
					loanLogList.get(i).setFirstLevleReasons(status.equals("REFUSE") ? reason.split("；")[0] : reason);
				}	
				if(specialRole.indexOf("amsDetailReason") != -1){ //只有信审原因角色的，录单环节只能看到拒绝的一级原因
					loanLogList.get(i).setFirstLevleReasons(ldLink.indexOf(operationModule) != -1 && status.equals("REFUSE") ? reason.split("；")[0] : reason);
				}
				if(specialRole.indexOf("cfsDetailReason") != -1){ //只有录单原因角色的，信审环节只能看到拒绝的一级原因
					loanLogList.get(i).setFirstLevleReasons(operationModule.indexOf("XS") != -1 && status.equals("REFUSE") ? reason.split("；")[0] : reason);
				}
			}
			
			if(specialRoleArr.length == 2){
				if(specialRole.indexOf("amsDetailLog") != -1 && specialRole.indexOf("amsDetailReason") != -1){ //有信审日志角色和信审原因角色，录单环节只能看到拒绝的一级原因
					loanLogList.get(i).setFirstLevleReasons(ldLink.indexOf(operationModule) != -1 && status.equals("REFUSE") ? reason.split("；")[0] : reason);
				}	
				if(specialRole.indexOf("amsDetailLog") != -1 && specialRole.indexOf("cfsDetailReason") != -1){ //有信审日志角色和录单原因角色，信审环节只能看到拒绝的一级原因
					loanLogList.get(i).setFirstLevleReasons(operationModule.indexOf("XS") != -1 && status.equals("REFUSE") ? reason.split("；")[0] : reason);
				}
			}
		}
		
		return loanLogList;
	}

	@Override
	public Response<ResQueryLoanDetailVo> queryLoanDetail(ReqQueryLoanDetailVo reqQueryLoanDetailVo) {

		Response<ResQueryLoanDetailVo> response = new Response<ResQueryLoanDetailVo>();
		ResQueryLoanDetailVo resQueryLoanDetailVo = new ResQueryLoanDetailVo();

		// 参数校验 
		Response<Object> validateResponse = Validate.getInstance().validate(reqQueryLoanDetailVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}
		String loanNo = reqQueryLoanDetailVo.getLoanNo();
		String userCode = reqQueryLoanDetailVo.getUserCode();

		Map<String,Object> loanDetailMap = iBMSIntegratedSearchService.queryLoanDetail(loanNo);

		if(null != loanDetailMap && !loanDetailMap.isEmpty()){
			String owningBranchId = ObjectUtils.toString(loanDetailMap.get("owningBranchId"));

			//获取机构名称
			try {
				org.apache.commons.beanutils.BeanUtils.populate(resQueryLoanDetailVo, loanDetailMap);
			} catch (Exception e) {
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			} 

			ReqOrganizationVO reqOrganizationVO = new ReqOrganizationVO();
			reqOrganizationVO.setSysCode(EnumConstants.BMS_SYSCODE);
			reqOrganizationVO.setId(Long.valueOf(owningBranchId));
			Response<ResOrganizationVO> organizationResponse = OrganizationExecuter.findById(reqOrganizationVO);

			if(organizationResponse.getRepCode().equals("000000")){
				resQueryLoanDetailVo.setOwningBranchName(organizationResponse.getData().getName());
			}

			//查询反欺诈信息
			ReqBMSAduitPersonVo reqBMSAduitPersonVo = new ReqBMSAduitPersonVo();
			reqBMSAduitPersonVo.setLoanNo(loanNo);
			Response<ResBMSAduitPersonVo> aduitPersonResponse = aduitPersonExecuter.findAduitPersonInfo(reqBMSAduitPersonVo);

			ResBMSAduitPersonVo resBMSAduitPersonVo = aduitPersonResponse.getData();

			Map<String,String> tempMap = new HashMap<>();
			tempMap.put("appNo", loanNo);
			tempMap.put("idCard", ObjectUtils.toString(resBMSAduitPersonVo.getIdNum()));
			tempMap.put("name", ObjectUtils.toString(resBMSAduitPersonVo.getName()));
			tempMap.put("cellphone", ObjectUtils.toString(resBMSAduitPersonVo.getCellphone()));
			tempMap.put("address", ObjectUtils.toString(resBMSAduitPersonVo.getAddress()));
			tempMap.put("homePhone", ObjectUtils.toString(resBMSAduitPersonVo.getHomeTel()));
			tempMap.put("companyName", ObjectUtils.toString(resBMSAduitPersonVo.getCompanyName()));
			tempMap.put("companyAddress", ObjectUtils.toString(resBMSAduitPersonVo.getCompanyAddress()));
			tempMap.put("companyPhone", ObjectUtils.toString(resBMSAduitPersonVo.getCompanyPhone()));
			tempMap.put("contactPhone1",ObjectUtils.toString( resBMSAduitPersonVo.getContactPhone1()));
			tempMap.put("sources", "bms");
			tempMap.put("timestamp", ObjectUtils.toString(new Date().getTime()));
			tempMap.put("creatorId", userCode);


			Map<String,String> paramMap = new HashMap<>();
			paramMap.put("param", new JSONObject(tempMap).toString());

			JSONObject obj = creditHttpServiceImpl.postBaseMethod(antifraudInfoUrl, paramMap);
			if("000000".equals(obj.getString("code"))){
				obj = obj.getJSONObject("data");
				resQueryLoanDetailVo.setAppRst(obj.getString("appRst"));
				resQueryLoanDetailVo.setStanFrdLevel(obj.getString("stanFrdLevel"));
				resQueryLoanDetailVo.setAppRstRema(obj.getString("stanRiskRate"));
			}
		}

		response.setData(resQueryLoanDetailVo);
		return response;
	}
	public String dataAsc(String DataType){
		if(DataType!=null){
			StringBuffer sb=new StringBuffer();
			String[] str=DataType.split("\\|");
			Arrays.sort(str);
			for(int i=0;i<str.length;i++){
				sb.append(str[i]); 
			}
			return sb.toString();
		}else{
			return null;	
		}
	}
	
}
