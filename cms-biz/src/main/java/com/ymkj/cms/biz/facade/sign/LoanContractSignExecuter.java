package com.ymkj.cms.biz.facade.sign;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.config.annotation.Service;
import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.TaskOpinion;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.master.IBMSChannelExecuter;
import com.ymkj.cms.biz.api.service.sign.ILoanContractSignExecuter;
import com.ymkj.cms.biz.api.service.sign.aite.IAiTeLoanContractExecuter;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqSignElectronic;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractTemplateVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractInfoVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.SignFactory;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.master.BMSChannel;
import com.ymkj.cms.biz.entity.master.BMSContractTemplate;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanContract;
import com.ymkj.cms.biz.entity.sign.BMSLuJSInform;
import com.ymkj.cms.biz.entity.sign.BMSRepaymentDetail;
import com.ymkj.cms.biz.entity.sign.HttpContractReturnEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.LoanProductService;
import com.ymkj.cms.biz.service.http.IAiTeHttpService;
import com.ymkj.cms.biz.service.master.IBMSChannelService;
import com.ymkj.cms.biz.service.master.IBMSOrgLimitChannelService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.HttpClientService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractService;
import com.ymkj.cms.biz.service.sign.IBMSLoanChannelLockTargetService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;
import com.ymkj.cms.biz.service.sign.ILuJSInformService;
import com.ymkj.cms.biz.service.sign.impl.LoanContractGenerator;
import com.ymkj.cms.biz.service.sign.lujs.impl.LuJSSaveLoanContractSignImpl;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;

@Service
public class LoanContractSignExecuter implements ILoanContractSignExecuter {
	public static Logger logger=Logger.getLogger(LoanContractSignExecuter.class);
	@Autowired
	private ILoanContractSignService loanContractSignService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IBMSOrgLimitChannelService ibmsOrgLimitChannelService;
	@Autowired
	private IEmployeeExecuter iEmployeeExecuter;
	@Autowired
	private IAiTeLoanContractExecuter iAiTeLoanContractExecuter;
	@Autowired
	private ILoanBaseEntityService loanBaseEntityService;
	@Autowired
	private ILoanBaseEntityDao loanBaseEntityDao;
	@Autowired
	private ILoanSignDataOprateService loanSignDataOprateService;
	@Autowired
	private IAiTeLoanContractService aiTeLoanContractService;
	@Autowired
	private LoanContractGenerator loanContractGenerator;
	@Autowired
	private IBMSChannelService channelService;
	@Autowired
	private LoanProductService loanProductService;
	@Autowired
	private IBMSLoanChannelLockTargetService loanChannelLockTargetService;
	@Autowired
	private HttpClientService httpClientService;
	@Autowired
	private IAiTeHttpService aiTeHttpService;
	@Autowired
	private ILuJSInformService luJSInformService;
	@Autowired
	private IBMSLoanChannelLockTargetDao loanChannelLockTargetDao;
	@Autowired
	private IBMSChannelExecuter channelExecuter;
	@Autowired
	private LuJSSaveLoanContractSignImpl luJSSaveLoanContractSign;

	@Value(value = "#{env['get_sign_url']?:''}")
	private String getSignUrl;
	
	@Override
	public PageResponse<ResLoanContractSignVo> unclaimedContractSignListBy(
			ReqLoanContractSignVo reqLoanContractSignVo) {
		PageResponse<ResLoanContractSignVo> response = new PageResponse<ResLoanContractSignVo>();

		// 参数校验
		if (reqLoanContractSignVo.getPageNum() == 0
				|| reqLoanContractSignVo.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR,
					new Object[] { "pageNum | pageSize" });
		}
		if (reqLoanContractSignVo.getServiceCode() == null) {
			throw new BizException(CoreErrorCode.PARAM_ERROR,
					new Object[] { "serviceCode" });
		}
		// 得到客服所在的营业部
		ReqEmployeeVO reqEmployeeVO = new ReqEmployeeVO();
		reqEmployeeVO.setSysCode(EnumConstants.BMS_SYSCODE);
		reqEmployeeVO.setUsercode(reqLoanContractSignVo.getServiceCode());
		Response<List<ResOrganizationVO>> res = iEmployeeExecuter.findDeptsByAccount(reqEmployeeVO);
		List<ResOrganizationVO> resOrganizationVOs = res.getData();
		if (resOrganizationVOs == null) {
			throw new BizException(BizErrorCode.ORG_NOTFOUND, "未找到该客服所在营业部");
		}
		ResOrganizationVO org = resOrganizationVOs.get(0);
		Long contractBranchId = org.getId();
		reqLoanContractSignVo.setContractBranchId(String.valueOf(contractBranchId));
		try {
			// 调用业务逻辑
			PageBean<BMSLoanBaseEntity> pageBean = loanContractSignService
					.unclaimedContractSignListBy(reqLoanContractSignVo);
			// 构造响应参数
			List<ResLoanContractSignVo> records = new ArrayList<ResLoanContractSignVo>();
			List<BMSLoanBaseEntity> demoEntitys = pageBean.getRecords();

			for (BMSLoanBaseEntity demoEntity : demoEntitys) {
				ResLoanContractSignVo resDemoVO = new ResLoanContractSignVo();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRepMsg("suceess");
			response.setRecords(records);

		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage()
					+ e.getLocalizedMessage());
		}
		return response;
	}
	
	@Override
	public Response<ResLoanContractSignVo> claimedContractSign(
			ReqLoanContractSignVo reqLoanContractSignVo) {
		Response<ResLoanContractSignVo> res = new Response<ResLoanContractSignVo>();
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(
				reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK,
					validateResponse.getRepMsg());
		}
		if (reqLoanContractSignVo.getId() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "id" });
		}
		BMSLoanBaseEntity bmsLoanBaseEntity1 = loanBaseEntityService.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		if(!StringUtils.isEmpty(bmsLoanBaseEntity1.getSignCode())){
			throw new BizException(BizErrorCode.CLAIM_EOERR, "该笔申请已被其他客服认领！");
		}
		try {
			loanContractSignService.claimedContractSign(reqLoanContractSignVo);
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.UNKNOWN_ERROR, e.getMessage());
		}

		return res;
	}
	@Override
	public PageResponse<ResLoanContractSignVo> undoneContractSignListBy(
			ReqLoanContractSignVo reqLoanContractSignVo) {
		PageResponse<ResLoanContractSignVo> response = new PageResponse<ResLoanContractSignVo>();

		// 参数校验
		if (reqLoanContractSignVo.getPageNum() == 0
				|| reqLoanContractSignVo.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR,
					new Object[] { "pageNum | pageSize" });
		}
		if (reqLoanContractSignVo.getServiceCode() == null) {
			throw new BizException(CoreErrorCode.PARAM_ERROR,
					new Object[] { "serviceCode" });
		}

		try {
			// 调用业务逻辑
			PageBean<BMSLoanBaseEntity> pageBean = loanContractSignService
					.undoneContractSignListBy(reqLoanContractSignVo);
			// 构造响应参数
			List<ResLoanContractSignVo> records = new ArrayList<ResLoanContractSignVo>();
			List<BMSLoanBaseEntity> demoEntitys = pageBean.getRecords();

			for (BMSLoanBaseEntity demoEntity : demoEntitys) {
				ResLoanContractSignVo resDemoVO = new ResLoanContractSignVo();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRepMsg("suceess");
			response.setRecords(records);

		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage()
					+ e.getLocalizedMessage());
		}
		return response;
	}
	@Override
	public Response<Object> queryContractSignToDoCount(ReqLoanContractSignVo reqLoanContractSignVo) {
		Response<Object> response = new Response<Object>();
		
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}
		
		Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("rtfNodeState", EnumConstants.RtfNodeState.HTQYASSIGN.getValue());
		paramsMap.put("serviceCode", reqLoanContractSignVo.getServiceCode());
		
		long count = loanContractSignService.queryContractSignToDoCount(paramsMap);
		response.setData(count);
		return response;
	}

	@Override
	public PageResponse<ResLoanContractSignVo> doneContractSignListBy(
			ReqLoanContractSignVo reqLoanContractSignVo) {
		PageResponse<ResLoanContractSignVo> response = new PageResponse<ResLoanContractSignVo>();

		// 参数校验
		if (reqLoanContractSignVo.getPageNum() == 0
				|| reqLoanContractSignVo.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR,
					new Object[] { "pageNum | pageSize" });
		}
		if (reqLoanContractSignVo.getServiceCode() == null) {
			throw new BizException(CoreErrorCode.PARAM_ERROR,
					new Object[] { "serviceCode" });
		}
		try {
 
			// 调用业务逻辑
			PageBean<BMSLoanBaseEntity> pageBean = loanContractSignService
					.doneContractSignListBy(reqLoanContractSignVo);

			// 构造响应参数
			List<ResLoanContractSignVo> records = new ArrayList<ResLoanContractSignVo>();
			List<BMSLoanBaseEntity> demoEntitys = pageBean.getRecords();

			for (BMSLoanBaseEntity demoEntity : demoEntitys) {
				ResLoanContractSignVo resDemoVO = new ResLoanContractSignVo();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.UNKNOWN_ERROR, e);
		}
		return response;

	}

	@Override
	public Response<ResLoanContractSignVo> saveLoanContractSign(
			ReqLoanContractSignVo reqLoanContractSignVo) {
		if(SignFactory.containsChannelCd(reqLoanContractSignVo.getChannelCd())){
			return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(),
					EnumConstants.Sign.SAVE.getValue());
		}
		Response<ResLoanContractSignVo> res = new Response<ResLoanContractSignVo>();
		// 参数校验
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(
				reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK,
					validateResponse.getRepMsg());
		}
		if (reqLoanContractSignVo.getContractLmt() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "contractLmt" });
		}
		if (reqLoanContractSignVo.getChannelCd() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "channelCd" });
		}
		if (reqLoanContractSignVo.getContractTrem() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "contractTrem" });
		}
		if (reqLoanContractSignVo.getId() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "id" });
		}
		//签约处理人校验
		BMSLoanBaseEntity bmsLoanBaseEntity1 = loanBaseEntityService.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		if(!StringUtils.isEmpty(bmsLoanBaseEntity1.getSignCode())){
			if (!reqLoanContractSignVo.getServiceCode().equals(bmsLoanBaseEntity1.getSignCode())) {
				throw new BizException(BizErrorCode.OPRATEUSER_EOERR,
						"当前签约客服异常，请刷新");
			}
		}
		try {
			// 调用
			if (!StringUtils.isBlank(bmsLoanBaseEntity1.getApplyBankNameId())) {
				reqLoanContractSignVo.setApplyBankNameId(Integer
						.parseInt(bmsLoanBaseEntity1.getApplyBankNameId()));
			}
			loanContractSignService.saveLoanContractSign(reqLoanContractSignVo);
		} catch (BizException e) {
			res.setRepCode(e.getRealCode());
			res.setRepMsg(e.getMessage());
			return res;
			// throw new
			// BizException(CoreErrorCode.UNKNOWN_ERROR,e.getMessage()+e.getArguments()[0]);
		}
		// 查询版本号 并传给前端
		BMSLoanBaseEntity bmsLoanBaseEntity = loanBaseEntityService
				.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		ResLoanContractSignVo resLoanSignVo = new ResLoanContractSignVo();
		resLoanSignVo.setPversion(bmsLoanBaseEntity.getPversion());
		resLoanSignVo.setVersion(bmsLoanBaseEntity.getVersion());
		res.setData(resLoanSignVo);
		return res;
	}

	@Override
	public Response<ResLoanContractSignVo> saveLoanContractBankAcc(
			ReqLoanContractSignVo reqLoanContractSignVo) {
		if(SignFactory.containsChannelCd(reqLoanContractSignVo.getChannelCd())){
			return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(),
					EnumConstants.Sign.SAVE_BANK_ACC.getValue());
		}
		Response<ResLoanContractSignVo> res = new Response<ResLoanContractSignVo>();
		// 参数校验
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(
				reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK,
					validateResponse.getRepMsg());
		}

		if (reqLoanContractSignVo.getId() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "id" });
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getBankPhone())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "bankPhone" });
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getApplyBankCardNo())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "applyBankCardNo" });
		}
		if (reqLoanContractSignVo.getApplyBankCardNo().length() > 39) {
			throw new BizException(CoreErrorCode.VALIDATE_OVERLENGTH,
					new Object[] { "applyBankCardNo" });
		}
		if (!StringUtils.checkBankCard(reqLoanContractSignVo
				.getApplyBankCardNo())) {
			throw new BizException(BizErrorCode.BANK_CARD_NO_VAULE_EOERR,
					"银行卡格式不正确");
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getApplyBankBranch())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "applyBankBranch" });
		}
		if (reqLoanContractSignVo.getApplyBankBranch().length() > 69) {
			throw new BizException(CoreErrorCode.VALIDATE_OVERLENGTH,
					new Object[] { "applyBankBranch" });
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getApplyBankName())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "applyBankName" });
		}
		if (reqLoanContractSignVo.getApplyBankNameId() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "applyBankNameId" });
		}
/*		if (!StringUtils.pattern(StringUtils.REGEX_MOBILE,
				reqLoanContractSignVo.getBankPhone())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR,
					new Object[] { "bankPhone" });
		}*/

		if (reqLoanContractSignVo.getServiceCode() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "serviceCode" });
		}
		// 业务校验
		// 流程任务校验（非当前节点抛出异常）
		Task task = taskService.findTaskByLoanBaseId(String
				.valueOf(reqLoanContractSignVo.getId()));
		if (task == null || !EnumConstants.WF_BCXX.equals(task.getTaskName())) {
			throw new BizException(BizErrorCode.UFLOTASK_EOERR);
		}
		//签约处理人校验
		BMSLoanBaseEntity bmsLoanBaseEntity1 = loanBaseEntityService.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		if(!StringUtils.isEmpty(bmsLoanBaseEntity1.getSignCode())){
			if (!reqLoanContractSignVo.getServiceCode().equals(bmsLoanBaseEntity1.getSignCode())) {
				throw new BizException(BizErrorCode.OPRATEUSER_EOERR,
						"当前签约客服异常，请刷新");
			}
		}
		reqLoanContractSignVo.setTaskName(task.getTaskName());
		try{
		// 业务调用
		loanContractSignService.saveLoanContractBankAcc(reqLoanContractSignVo);
		// 完成任务
		try {
			taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(),
					EnumConstants.WFPH_BC);
		} catch (BizException e) {
			throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, e);
		}
		} catch (BizException e) {
			res.setRepCode(e.getRealCode());
			res.setRepMsg((e.getErrorMsg()==null?"":e.getErrorMsg()+":") +e.getLocalizedMessage());
		} catch (Exception e) {
			res.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			res.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage()+e.getLocalizedMessage());
		}
		// 查询版本号 并传给前端
		BMSLoanBaseEntity bmsLoanBaseEntity = loanBaseEntityService
				.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		ResLoanContractSignVo resLoanSignVo = new ResLoanContractSignVo();
		resLoanSignVo.setPversion(bmsLoanBaseEntity.getPversion());
		resLoanSignVo.setVersion(bmsLoanBaseEntity.getVersion());
		res.setData(resLoanSignVo);

		return res;
	}

	@Override
	public Response<ResLoanContractSignVo> createLoanContract(
			ReqLoanContractSignVo reqLoanContractSignVo) {
		long begTime=System.currentTimeMillis();
		logger.info("合同生成开始时间:-----------------------------------------"+begTime);
		if(SignFactory.containsChannelCd(reqLoanContractSignVo.getChannelCd())){
			return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(),
					EnumConstants.Sign.CREATE_CONTRACT.getValue());
		}
		Response<ResLoanContractSignVo> res = new Response<ResLoanContractSignVo>();
		// 参数校验
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(
				reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK,
					validateResponse.getRepMsg());
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getLoanNo())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "loanNo" });
		}
		if (reqLoanContractSignVo.getId() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "id" });
		}
		if (reqLoanContractSignVo.getServiceCode() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "serviceCode" });
		}
		
		String contractTypeCode=loanProductService.getContractType(reqLoanContractSignVo.getLoanNo());
		if (contractTypeCode == null) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR,
					new Object[] { "contractTypeCode" });
		}
		// 业务校验
		// 流程任务校验（非当前节点抛出异常）
		Task task = taskService.findTaskByLoanBaseId(String
				.valueOf(reqLoanContractSignVo.getId()));
		if (task == null || !EnumConstants.WF_SCHT.equals(task.getTaskName())) {
			throw new BizException(BizErrorCode.UFLOTASK_EOERR);
		}
		//签约处理人校验
		BMSLoanBaseEntity bmsLoanBaseEntity1 = loanBaseEntityService.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		if(!StringUtils.isEmpty(bmsLoanBaseEntity1.getSignCode())){
			if (!reqLoanContractSignVo.getServiceCode().equals(bmsLoanBaseEntity1.getSignCode())) {
				throw new BizException(BizErrorCode.OPRATEUSER_EOERR,
						"当前签约客服异常，请刷新");
			}
		}
		Map retrunMap =null;
		try {
			retrunMap=loanContractSignService.createLoanContract(reqLoanContractSignVo);
		
		//生成合同开始
		String loanNo =reqLoanContractSignVo.getLoanNo();
		String org=null;
		HttpContractReturnEntity contractReturnEntity= (HttpContractReturnEntity)retrunMap.get("contractReturnEntity");
		List<BMSRepaymentDetail> repaylist =contractReturnEntity.getRepaymentDetail();
		//封装生成合同数据
		Map<String, Object> generatorMap;
		try {
			generatorMap = loanSignDataOprateService.setValueToContract(contractReturnEntity);
		} catch (Exception e1) {
			throw new BizException(BizErrorCode.CREATEFILE_EOERR, "生成合同异常");
		} 
		loanSignDataOprateService.setRepayArrayToMap(generatorMap,repaylist);
		//生成合同
		generatorMap.put("contractTypeCode", contractTypeCode);
		generatorMap.put("channelCd", reqLoanContractSignVo.getChannelCd());
		generatorMap.put("serviceName", reqLoanContractSignVo.getServiceName());
		generatorMap.put("createDate", DateUtil.defaultFormatDay(new Date()));
		generatorMap.put("serviceCode", reqLoanContractSignVo.getServiceCode());
		loanContractGenerator.getContractTemplate(org,reqLoanContractSignVo.getChannelCd(), generatorMap, loanNo);
		
		// 完成任务
		try {
			taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(),
					EnumConstants.WF_SCHT);
		} catch (Exception e) {
			throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR,
					e.getMessage());
		}
		
		} catch (BizException e) {
			res.setRepCode(e.getRealCode());
			res.setRepMsg((e.getErrorMsg()==null?"":e.getErrorMsg()+":") +e.getLocalizedMessage());
		} catch (Exception e) {
			res.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			res.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage()+e.getLocalizedMessage());
		}
		// 查询版本号 并传给前端
		BMSLoanBaseEntity bmsLoanBaseEntity = loanBaseEntityService
				.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		ResLoanContractSignVo resLoanSignVo = new ResLoanContractSignVo();
		resLoanSignVo.setPversion(bmsLoanBaseEntity.getPversion());
		resLoanSignVo.setVersion(bmsLoanBaseEntity.getVersion());
		res.setData(resLoanSignVo);
		long endTime=System.currentTimeMillis();
		logger.info("合同生成结束时间:-----------------------------------------"+endTime);
		return res;
	}

	@Override
	public Response<ResLoanContractSignVo> signLoanContract(
			ReqLoanContractSignVo reqLoanContractSignVo) {
		if(SignFactory.containsChannelCd(reqLoanContractSignVo.getChannelCd())){
			return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(),
					EnumConstants.Sign.SIGN_CONTRACT.getValue());
		}
		Response<ResLoanContractSignVo> res = new Response<ResLoanContractSignVo>();
		try {
			// 参数校验
			@SuppressWarnings("unchecked")
			Response<Object> validateResponse = Validate.getInstance()
					.validate(reqLoanContractSignVo);
			if (!validateResponse.isSuccess()) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK,
						validateResponse.getRepMsg());
			}
			if (StringUtils.isBlank(reqLoanContractSignVo.getLoanNo())) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
						new Object[] { "loanNo" });
			}
			if (reqLoanContractSignVo.getId() == null) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
						new Object[] { "id" });
			}
			if (StringUtils.isBlank(reqLoanContractSignVo.getServiceCode())) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
						new Object[] { "serviceCode" });
			}
			if (StringUtils.isBlank(reqLoanContractSignVo.getChannelCd())) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
						new Object[] { "channelCd" });
			}
			// 流程任务校验（非当前节点抛出异常）
			Task task = taskService.findTaskByLoanBaseId(String
					.valueOf(reqLoanContractSignVo.getId()));
			if (task == null || !EnumConstants.WF_HTQD.equals(task.getTaskName())) {
				throw new BizException(BizErrorCode.UFLOTASK_EOERR);
			}
			//签约处理人校验
			BMSLoanBaseEntity bmsLoanBaseEntity1 = loanBaseEntityService.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
			if(!StringUtils.isEmpty(bmsLoanBaseEntity1.getSignCode())){
				if (!reqLoanContractSignVo.getServiceCode().equals(bmsLoanBaseEntity1.getSignCode())) {
					throw new BizException(BizErrorCode.OPRATEUSER_EOERR,
							"当前签约客服异常，请刷新");
				}
			}
			// 渠道判断分流
			if (EnumConstants.channelCode.AITE.getValue().equals(
					reqLoanContractSignVo.getChannelCd())) {
				signLoanContractloc(reqLoanContractSignVo, res);
			} else {
				signLoanContractloc(reqLoanContractSignVo, res);
			}
		} catch (BizException e) {
			res.setRepCode(e.getRealCode());
			res.setRepMsg((e.getErrorMsg()==null?"":e.getErrorMsg()+":") +e.getLocalizedMessage());
		} catch (Exception e) {
			res.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			res.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage()
					+ e.getLocalizedMessage());
		}

		return res;
	}

	/**
	 * 合同签订，证大P2P渠道具体事件
	 * 
	 * @param reqLoanContractSignVo
	 * @param res
	 * @author lix YM10160
	 * @date 2017年5月23日下午7:40:22
	 */
	private void signLoanContractloc(
			ReqLoanContractSignVo reqLoanContractSignVo,
			Response<ResLoanContractSignVo> res) {
		String confirmUserCode = null;// 合同确认办理CODE
		String confirmUserName = null;// 合同确认办理NAME
		ResEmployeeVO branchManager = null; // 经理
		ResEmployeeVO branchAssistantManager = null; // 副理
		BMSLoanBaseEntity bmsLoanBaseEntity = loanBaseEntityService
				.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		if (bmsLoanBaseEntity == null) {
			throw new BizException(CoreErrorCode.DB_RESULT_ISNULL);
		}
		if (!StringUtils.isBlank(bmsLoanBaseEntity.getManagerCode())) {// 如果确认经理不为空，则为退回重新办理,确认经理为原经理
			confirmUserCode = bmsLoanBaseEntity.getManagerCode();
			confirmUserName = bmsLoanBaseEntity.getManagerName();
		} else {// 如果确认经理为空，则不退回重新办理,从员工上级副理，经理取
				// 根据签约客服得到副理
			branchAssistantManager = loanSignDataOprateService.getSignManager(
					reqLoanContractSignVo.getServiceCode(),
					EnumConstants.BRANCH_ASSISTANT_MANAGER);
			// 根据签约客服得到经理
			branchManager = loanSignDataOprateService.getSignManager(
					reqLoanContractSignVo.getServiceCode(),
					EnumConstants.BRANCH_MANAGER);

			if (branchManager == null && branchAssistantManager == null) {// 未找到经理副理
				throw new BizException(BizErrorCode.MANAGER_NOTFOUND);
			}

			// 如果副理为空 则为分配经理
			if (branchAssistantManager == null) {
				confirmUserCode = branchManager.getUsercode();
				confirmUserName = branchManager.getName();
			} else {
				confirmUserCode = branchAssistantManager.getUsercode();
				confirmUserName = branchAssistantManager.getName();
			}
		}
		reqLoanContractSignVo.setManagerCode(confirmUserCode);
		reqLoanContractSignVo.setManagerName(confirmUserName);
		try {
			// 合同签订处理
			loanContractSignService.signLoanContract(reqLoanContractSignVo);

		} catch (BizException e) {
			throw new BizException(CoreErrorCode.UNKNOWN_ERROR, e.getMessage()
					+ e.getArguments()[0]);
		}
		// 查询版本号 并传给前端
		ResLoanContractSignVo resLoanSignVo = new ResLoanContractSignVo();
		resLoanSignVo.setPversion(bmsLoanBaseEntity.getPversion());
		resLoanSignVo.setVersion(bmsLoanBaseEntity.getVersion());
		res.setData(resLoanSignVo);
		// 完成当前任务 并添加 合同确认环节 经理 ，副理流程变量
		Map<String, Object> variables = new HashMap<String, Object>();
		if (branchManager != null) {
			variables.put(EnumConstants.BRANCH_MANAGER,
					branchManager.getUsercode());
		}
		variables.put(EnumConstants.BRANCH_ASSISTANT_MANAGER, confirmUserCode);
		TaskOpinion option = new TaskOpinion("签订");
		try {
			taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(),
					EnumConstants.WF_HTQD, variables, option);
		} catch (Exception e) {
			throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR,
					e.getMessage());
		}
	}

	// 取消
	// @Override
	public Response<ResLoanContractSignVo> cancelLoan(
			ReqLoanContractSignVo reqLoanContractSignVo) {
		if(EnumConstants.channelCode.BAOSHANG.getValue().equals(reqLoanContractSignVo.getChannelCd())){
			return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(), 
					EnumConstants.Sign.CONTRACT_CANCEL_LOAN.getValue());
		}
		
		if(SignFactory.containsChannelCd(reqLoanContractSignVo.getChannelCd())){
			return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(), 
					EnumConstants.Sign.CANCEL.getValue());
		}
		Response<ResLoanContractSignVo> res = new Response<ResLoanContractSignVo>();
		// 参数校验
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(
				reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK,
					validateResponse.getRepMsg());
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getLoanNo())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "loanNo" });
		}
		if (reqLoanContractSignVo.getId() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "id" });
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getFirstLevleReasons())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "firstLevleReasons" });
		}

		if (StringUtils.isBlank(reqLoanContractSignVo
				.getFirstLevleReasonsCode())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "firstLevleReasonsCode" });
		}
		if ("0".equals(reqLoanContractSignVo.getTwoLevleReasons())) {
			reqLoanContractSignVo.setTwoLevleReasons(null);
		}
		if ("0".equals(reqLoanContractSignVo.getTwoLevleReasonsCode())) {
			reqLoanContractSignVo.setTwoLevleReasonsCode(null);
		}
		BMSLoanBaseEntity bmsLoanBaseEntity1 = loanBaseEntityService
				.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		// 推送过银行id 不为空 赋给请求vo做为操作是否做核心操作判断
		if (!StringUtils.isBlank(bmsLoanBaseEntity1.getApplyBankNameId())) {
			reqLoanContractSignVo.setApplyBankNameId(Integer
					.valueOf(bmsLoanBaseEntity1.getApplyBankNameId()));
		}
		// 渠道判断分流
		if (EnumConstants.channelCode.AITE.getValue().equals(
				reqLoanContractSignVo.getChannelCd())) {
			res = iAiTeLoanContractExecuter.cancelLoan(reqLoanContractSignVo);

			// 不传渠道默认走证大p2p
		} else {
			// 业务校验
			Task task = taskService.findTaskByLoanBaseId(String
					.valueOf(reqLoanContractSignVo.getId()));
			if (bmsLoanBaseEntity1.getSignCode() != null) {
				// 当为系统操作时不校验
				if (EnumConstants.BMS_SYSTEM_CODE.equals(reqLoanContractSignVo.getServiceCode())) {

				} else if (!reqLoanContractSignVo.getServiceCode().equals(bmsLoanBaseEntity1.getSignCode())) {
					throw new BizException(BizErrorCode.OPRATEUSER_EOERR,
							"当前签约客服异常，请刷新");
				}
			}
			try {
				// 调用
				loanContractSignService.cancelLoan(reqLoanContractSignVo);
			} catch (BizException e) {
				throw new BizException(CoreErrorCode.UNKNOWN_ERROR,
						e.getMessage() + e.getArguments()[0]);
			}
			if (task != null && !EnumConstants.WF_KS.equals(task.getTaskName())) {// 开启流程后调用
				// 完成当前任务
				try {
					TaskOpinion option = new TaskOpinion("取消");
					taskService.completeTaskByLoanBaseId(
							reqLoanContractSignVo.getId(),
							EnumConstants.WFPH_JJQX, option);
				} catch (Exception e) {
					throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR,
							e.getMessage());
				}
			}
		}

		return res;
	}

	// 拒绝
	public Response<ResLoanContractSignVo> refuseLoan(
			ReqLoanContractSignVo reqLoanContractSignVo) {
		if(EnumConstants.channelCode.BAOSHANG.getValue().equals(reqLoanContractSignVo.getChannelCd())){
			return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(), 
					EnumConstants.Sign.CONTRACT_REFUSE_LOAN.getValue());
		}
		if(SignFactory.containsChannelCd(reqLoanContractSignVo.getChannelCd())){
			return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(), 
					EnumConstants.Sign.REFUSE.getValue());
		}
		Response<ResLoanContractSignVo> res = new Response<ResLoanContractSignVo>();

		// 参数校
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(
				reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK,
					validateResponse.getRepMsg());
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getLoanNo())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "loanNo" });
		}
		if (reqLoanContractSignVo.getId() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "id" });
		}

		if (StringUtils.isBlank(reqLoanContractSignVo.getFirstLevleReasons())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "firstLevleReasons" });
		}

		if (StringUtils.isBlank(reqLoanContractSignVo
				.getFirstLevleReasonsCode())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "firstLevleReasonsCode" });
		}
		if ("0".equals(reqLoanContractSignVo.getTwoLevleReasons())) {
			reqLoanContractSignVo.setTwoLevleReasons(null);
		}
		if ("0".equals(reqLoanContractSignVo.getTwoLevleReasonsCode())) {
			reqLoanContractSignVo.setTwoLevleReasonsCode(null);
		}
		BMSLoanBaseEntity bmsLoanBaseEntity1 = loanBaseEntityService
				.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		// 推送过银行id 不为空 赋给请求vo做为操作是否做核心操作判断
		if (!StringUtils.isBlank(bmsLoanBaseEntity1.getApplyBankNameId())) {
			reqLoanContractSignVo.setApplyBankNameId(Integer
					.valueOf(bmsLoanBaseEntity1.getApplyBankNameId()));
		}
		// 渠道判断分流
		if (EnumConstants.channelCode.AITE.getValue().equals(
				reqLoanContractSignVo.getChannelCd())) {
			res = iAiTeLoanContractExecuter.refuseLoan(reqLoanContractSignVo);

			// 不传渠道默认走证大p2p
		} else {
			// 业务校验
			Task task = taskService.findTaskByLoanBaseId(String
					.valueOf(reqLoanContractSignVo.getId()));
			if (StringUtils.isEmpty(reqLoanContractSignVo.getServiceCode())) {
				// 当为系统操作时不校验
				if (EnumConstants.BMS_SYSTEM_CODE.equals(reqLoanContractSignVo.getServiceCode())) {

				} else if (!reqLoanContractSignVo.getServiceCode().equals(
						bmsLoanBaseEntity1.getSignCode())) {
					throw new BizException(BizErrorCode.OPRATEUSER_EOERR,
							"当前签约客服异常，请刷新");
				}
			}
			try {
				boolean refuseFlag = loanContractSignService
						.refuseLoan(reqLoanContractSignVo);
				if (refuseFlag) {
					//完成任务
					if (task != null && !EnumConstants.WF_KS.equals(task.getTaskName())) {// 开启流程后调用
						try {
							TaskOpinion option = new TaskOpinion("拒绝");
							taskService.completeTaskByLoanBaseId(
									reqLoanContractSignVo.getId(),
									EnumConstants.WFPH_JJQX, option);
						} catch (Exception e) {
							throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR,
									e.getMessage());
						}
					}
					res.setRepMsg("success");
					res.setRepCode(Response.SUCCESS_RESPONSE_CODE);
				} else {
					res.setRepMsg("拒绝失败");
					res.setRepCode(CoreErrorCode.FACADE_RESPONSE_FAIL
							.getErrorCode());
				}
				
				
			} catch (BizException e) {
				throw new BizException(CoreErrorCode.UNKNOWN_ERROR,
						e.getMessage() + e.getArguments()[0]);
			}
			

		}

		return res;

	}

	// 上一步
	public Response<ResLoanContractSignVo> returnLastStep(
			ReqLoanContractSignVo reqLoanContractSignVo) {
		if(SignFactory.containsChannelCd(reqLoanContractSignVo.getChannelCd())){
			return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(),
					EnumConstants.Sign.RETURN_LAST_STEP.getValue());
		}
		Response<ResLoanContractSignVo> res = new Response<ResLoanContractSignVo>();
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(
				reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK,
					validateResponse.getRepMsg());
		}
		if (reqLoanContractSignVo.getId() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "id" });
		}
		try {

			loanContractSignService.returnLastStep(reqLoanContractSignVo);
		} catch (BizException e) {
			throw new BizException(CoreErrorCode.UNKNOWN_ERROR, e.getMessage()
					+ e.getArguments()[0]);
		}

		return res;

	}

	@Override
	public Response<ResLoanContractSignVo> findSignInfo(
			ReqLoanContractSignVo reqLoanContractSignVo) {
		Response<ResLoanContractSignVo> response = new Response<ResLoanContractSignVo>();
		BMSLoanBaseEntity bmsLoanBaseEntity = null;
		// 参数校验
		if (reqLoanContractSignVo.getId() == null) {
			throw new BizException(CoreErrorCode.PARAM_ERROR,
					new Object[] { "id" });
		}
		// 业务校验
		/*
		 * Task task=
		 * taskService.findTaskByLoanBaseId(String.valueOf(reqLoanContractSignVo
		 * .getId()));
		 */
		try {
			// 调用业务逻辑
			bmsLoanBaseEntity = loanContractSignService
					.findSignInfo(reqLoanContractSignVo);

			ResLoanContractSignVo resLoanContractSignVo = new ResLoanContractSignVo();

			BeanUtils.copyProperties(bmsLoanBaseEntity, resLoanContractSignVo);
			if(bmsLoanBaseEntity.getContractType() != null 
					&& EnumConstants.ContractType.DZB.getCode().equals(bmsLoanBaseEntity.getContractType().toString())){
				resLoanContractSignVo.setContractTypeCode(EnumConstants.ContractType.DZB.getCode());
				resLoanContractSignVo.setContractTypeName(EnumConstants.ContractType.DZB.getDesc());
			} else if(bmsLoanBaseEntity.getContractType() != null 
					&& EnumConstants.ContractType.ZZB.getCode().equals(bmsLoanBaseEntity.getContractType().toString())){
				resLoanContractSignVo.setContractTypeCode(EnumConstants.ContractType.ZZB.getCode());
				resLoanContractSignVo.setContractTypeName(EnumConstants.ContractType.ZZB.getDesc());
			}
			if(EnumConstants.channelCode.LUJS.getValue().equals(bmsLoanBaseEntity.getChannelCd())){
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("loanBaseId", bmsLoanBaseEntity.getId());
				paramMap.put("channelCode", bmsLoanBaseEntity.getChannelCd());
				BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
				//反欺诈调用判断
				if(lockTargetEntity != null && "Y".equals(lockTargetEntity.getLockTarget())){
					Map<String, Object> luJSInformParamMap = new HashMap<String, Object>();
					luJSInformParamMap.put("loanNo", bmsLoanBaseEntity.getLoanNo());
					//数据类型
					List<String> informResultList = new ArrayList<String>();
					informResultList.add(EnumConstants.LuJSnotifyCode.FQZTG.getCode());
					informResultList.add(EnumConstants.LuJSnotifyCode.FQZJJ.getCode());
					luJSInformParamMap.put("informResultList", informResultList);
					// 调用业务逻辑
					BMSLuJSInform luJSInform = luJSInformService.getBy(luJSInformParamMap);
					if(luJSInform == null){
						//无反欺诈结果，
						resLoanContractSignVo.setLujsAntiFraud("1");
					} else if(EnumConstants.LuJSnotifyCode.FQZTG.getCode().equals(luJSInform.getInformResult())){
						//反欺诈结果为通过，
						resLoanContractSignVo.setLujsAntiFraud("2");
					} else if(EnumConstants.LuJSnotifyCode.FQZJJ.getCode().equals(luJSInform.getInformResult())){
						//反欺诈结果为拒绝，
						resLoanContractSignVo.setLujsAntiFraud("0");
					}
				}
			}
			
			response.setData(resLoanContractSignVo);

		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage()
					+ e.getLocalizedMessage());
			
		}

		// 校验但前处理人是否正常 更新签约确认时间
		BMSLoanBaseEntity updateLoanBaseEntity = null;
		if (EnumConstants.RtfState.HTQY.getValue().equals(
				bmsLoanBaseEntity.getRtfState())) {// 合同签约校验签约客服
			if (StringUtils.isBlank(bmsLoanBaseEntity.getSignDate())) {
				updateLoanBaseEntity = new BMSLoanBaseEntity();
				updateLoanBaseEntity.setId(bmsLoanBaseEntity.getId());
				updateLoanBaseEntity.setSignDate(DateUtil
						.defaultFormatDate(new Date()));
				loanBaseEntityDao.update(updateLoanBaseEntity);
			}

			/*
			 * if(!reqLoanContractSignVo.getServiceCode().equals(bmsLoanBaseEntity
			 * .getSignCode())){ throw new
			 * BizException(BizErrorCode.OPRATEUSER_EOERR,"当前签约客服异常，请刷新"); }
			 */
		} else if (EnumConstants.RtfState.HTQR.getValue().equals(
				bmsLoanBaseEntity.getRtfState())) {// 合同确认校验确认经理

			if (StringUtils.isBlank(bmsLoanBaseEntity.getConfirmDate())) {
				updateLoanBaseEntity = new BMSLoanBaseEntity();
				updateLoanBaseEntity.setId(bmsLoanBaseEntity.getId());
				updateLoanBaseEntity.setConfirmDate(DateUtil
						.defaultFormatDate(new Date()));
				loanBaseEntityDao.update(updateLoanBaseEntity);
			}

			/*
			 * if(!reqLoanContractSignVo.getServiceCode().equals(bmsLoanBaseEntity
			 * .getManagerCode())){ throw new
			 * BizException(BizErrorCode.OPRATEUSER_EOERR,"当前确认经理异常，请刷新"); }
			 */
		}

		return response;

	}

	/**
	 * 根据合同模板Id返回填充合同模板 Str
	 * 
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public ResListVO<ResBMSContractTemplateVO> getContrctListByTempId(
			ReqLoanContractSignVo reqLoanContractSignVo) {
		ResListVO<ResBMSContractTemplateVO> response = new ResListVO<ResBMSContractTemplateVO>();
		// 参数校验
		if (reqLoanContractSignVo.getLoanNo() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "loanNO" });
		}
		if (reqLoanContractSignVo.getChannelCd() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "channelCd" });
		}

		try {
			// 构造请求参数
			List<BMSContractTemplate> listProduct = loanContractSignService
					.getContractUrlList(reqLoanContractSignVo);
			if (listProduct != null && listProduct.size() > 0) {
				List<ResBMSContractTemplateVO> records = new ArrayList<ResBMSContractTemplateVO>();
				for (BMSContractTemplate bmsContractTemplate : listProduct) {
					ResBMSContractTemplateVO vo = new ResBMSContractTemplateVO();
					BeanUtils.copyProperties(bmsContractTemplate, vo);
					records.add(vo);
				}
				response.setParamList(records);
			}
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.UNKNOWN_ERROR, e.getMessage());
		}
		return response;
	}

	@Override
	public Response<ResLoanContractSignVo> returnBoxChoiceTask(
			ReqLoanContractSignVo reqLoanContractSignVo) {

		Response<ResLoanContractSignVo> res = new Response<ResLoanContractSignVo>();
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(
				reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK,
					validateResponse.getRepMsg());
		}
		if (reqLoanContractSignVo.getId() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "id" });
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getTaskName())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK,
					new Object[] { "taskName" });
		}

		try {
			loanContractSignService.returnBoxChoiceTask(reqLoanContractSignVo);
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.UNKNOWN_ERROR, e.getMessage());
		}

		return res;
	}

	/**
	 * 
	 * 
	 * @param <T>
	 */
	public static <T> PageResponse<T> resSignyInfo(String errCode,
			String errMsg, PageResponse<T> response) {
		response.setRepCode(errCode);
		response.setRepMsg(errMsg);
		return response;
	}

	public Response<String> valiProductIsDisabled(
			ReqLoanContractSignVo reqLoanContractSignVo) {
		Response<String> res = new Response<String>();
		if (StringUtils.isBlank(reqLoanContractSignVo.getOwningBranchId())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK,
					new Object[] { "owningBranchId" });
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getChannelCd())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK,
					new Object[] { "channelCd" });
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getProductCd())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK,
					new Object[] { "productCd" });
		}
		if (reqLoanContractSignVo.getContractTrem() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "contractTrem" });
		}
		if (reqLoanContractSignVo.getContractLmt() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL,
					new Object[] { "contractLmt" });
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", reqLoanContractSignVo.getOwningBranchId());
		params.put("channelCd", reqLoanContractSignVo.getChannelCd());
		params.put("productCd", reqLoanContractSignVo.getProductCd());
		params.put("contractTrem", reqLoanContractSignVo.getContractTrem());
		params.put("contractLmt", reqLoanContractSignVo.getContractLmt());
		boolean isDisabled = ibmsOrgLimitChannelService
				.isDisabledInSign(params);
		if (isDisabled) {
			res.setData(EnumConstants.YES);
			;// 被禁
		} else {
			res.setData(EnumConstants.NO);// 未 被禁
		}
		return res;
	}

	@Override
	public Response<ResLoanContractInfoVo> findLoanProdcut(
			ReqLoanContractSignVo reqLoanContractSignVo) {
		Response<ResLoanContractInfoVo> response = new Response<ResLoanContractInfoVo>();
		// 参数校验
		if (reqLoanContractSignVo.getId() == null) {
			throw new BizException(CoreErrorCode.PARAM_ERROR,
					new Object[] { "id" });
		}
		try {
			// 调用业务逻辑
			BMSLoanContract bmsLoanContract = loanContractSignService
					.findLoanContractInfo(reqLoanContractSignVo);

			ResLoanContractInfoVo resLoanContractInfoVo = new ResLoanContractInfoVo();

			BeanUtils.copyProperties(bmsLoanContract, resLoanContractInfoVo);

			response.setData(resLoanContractInfoVo);

		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage()
					+ e.getLocalizedMessage());
		}
		return response;
	}

	@Override
	public ResListVO<ResLoanContractInfoVo> getPayPlanList(
			ReqLoanContractSignVo reqLoanContractSignVo) {
		ResListVO<ResLoanContractInfoVo> response = new ResListVO<ResLoanContractInfoVo>();
		// 参数校验
		if (reqLoanContractSignVo.getId() == null) {
			throw new BizException(CoreErrorCode.PARAM_ERROR,
					new Object[] { "id" });
		}
		try {
			// 调用业务逻辑
			List<BMSRepaymentDetail> repaymentDetails = loanContractSignService
					.getPayPlanList(reqLoanContractSignVo);
			// 构造响应参数
			if (repaymentDetails != null && repaymentDetails.size() > 0) {
				List<ResLoanContractInfoVo> records = new ArrayList<ResLoanContractInfoVo>();
				for (BMSRepaymentDetail repaymentDetail : repaymentDetails) {
					ResLoanContractInfoVo vo = new ResLoanContractInfoVo();
					BeanUtils.copyProperties(repaymentDetail, vo);
					records.add(vo);
				}
				response.setParamList(records);
			}

		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage()
					+ e.getLocalizedMessage());
		}
		return response;

	}

	@SuppressWarnings("finally")
	@Override
	public Response<Boolean> querySignContractStatus(ReqSignElectronic reqVo) {
		Response<Boolean> response = new Response<Boolean>();
		try {
			if (reqVo == null||StringUtils.isBlank(reqVo.getAppNo())) {
				throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL,"申请编号不能为空!");
			}
			Map<String, Object> result = httpClientService.querySign(getSignUrl,reqVo.getAppNo());
			if (result == null) {
				throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL,
						"电子签名接口调用异常!");
			}
			if ("000000".equals(result.get("resCode"))&&"OK".equals(result.get("attachment"))) {
				response.setRepCode("000000");
				response.setData(true);
			}else {
				response.setRepCode(String.valueOf(result.get("resCode")));
				response.setRepMsg(String.valueOf(result.get("attachment")));
				response.setData(false);
			}
		} catch(BizException e){
			logger.error("签名结果查询异常",e);
			response.setData(false);
			response.setRepCode(e.getRealCode());
			response.setRepMsg(e.getResMsg());
		} catch (Exception e) {
			logger.error("签名结果查询异常",e);
			response.setData(false);
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage()
					+ e.getLocalizedMessage());
		}finally{
			return response;
		}

	}

	@Override
	public ResListVO<ResBMSEnumCodeVO> findContractTypeList(ReqLoanContractSignVo reqLoanContractSignVo) {
		
		ResListVO<ResBMSEnumCodeVO> response = new ResListVO<ResBMSEnumCodeVO>();
		try {
		
			if (StringUtils.isBlank(reqLoanContractSignVo.getChannelCd()) && reqLoanContractSignVo.getChannelId() == null) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "channelCd","channelId" });
			}
			if (StringUtils.isBlank(reqLoanContractSignVo.getServiceCode())) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "serviceCode" });
			}
			if (StringUtils.isBlank(reqLoanContractSignVo.getServiceName())) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "serviceName" });
			}
			if (StringUtils.isBlank(reqLoanContractSignVo.getSysCode())) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "sysCode" });
			}
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("code", reqLoanContractSignVo.getChannelCd());
			paramMap.put("id", reqLoanContractSignVo.getChannelId());
			List<BMSChannel> channelList = channelService.findBy(paramMap);
			if(channelList ==  null || channelList.get(0) == null){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "channelCd","channelId" });
			}
			BMSChannel channel = channelList.get(0);
			//查询合同类型枚举
			List<ResBMSEnumCodeVO> enumCodeList = new ArrayList<ResBMSEnumCodeVO>();
			
			if(channel.getPcontractFlag() != null && channel.getPcontractFlag() == 0L){
				//如果配置为纸质合同 启用，返回纸质选项
				ResBMSEnumCodeVO zzb = new ResBMSEnumCodeVO();
				zzb.setCode(EnumConstants.ContractType.ZZB.getCode());
				zzb.setName(EnumConstants.ContractType.ZZB.getDesc());
				enumCodeList.add(zzb);
			}
			if(channel.getEcontractFlag() != null && channel.getEcontractFlag() == 0L){
				//如果配置为电子合同 启用，返回纸质和电子选项
				ResBMSEnumCodeVO dzb = new ResBMSEnumCodeVO();
				dzb.setCode(EnumConstants.ContractType.DZB.getCode());
				dzb.setName(EnumConstants.ContractType.DZB.getDesc());
				enumCodeList.add(dzb);
			}
			response.setParamList(enumCodeList);
			response.setRepMsg("success");
			response.setRepCode(Response.SUCCESS_RESPONSE_CODE);
		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		
		return response;
	}

	@Override
	public Response<String> valiContractTypeIsDisabled(ReqLoanContractSignVo reqLoanContractSignVo) {
		Response<String> response = new Response<String>();
		try {
		
			if (StringUtils.isBlank(reqLoanContractSignVo.getContractTypeCode())) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "contractTypeCode" });
			}
			if (StringUtils.isBlank(reqLoanContractSignVo.getChannelCd())) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "channelCd" });
			}
			if (StringUtils.isBlank(reqLoanContractSignVo.getServiceCode())) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "serviceCode" });
			}
			if (StringUtils.isBlank(reqLoanContractSignVo.getServiceName())) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "serviceName" });
			}
			if (StringUtils.isBlank(reqLoanContractSignVo.getIp())) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "ip" });
			}
			if (StringUtils.isBlank(reqLoanContractSignVo.getSysCode())) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "sysCode" });
			}
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("code", reqLoanContractSignVo.getChannelCd());
			List<BMSChannel> channelList = channelService.findBy(paramMap);
			if(channelList ==  null || channelList.get(0) == null){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "channelCd","channelId" });
			}
			BMSChannel channel = channelList.get(0);
			//查询合同类型枚举
			List<ResBMSEnumCodeVO> enumCodeList = new ArrayList<ResBMSEnumCodeVO>();
			
			if(channel.getPcontractFlag() != null && channel.getPcontractFlag() == 0L){
				//如果配置为纸质合同 启用，返回纸质选项
				ResBMSEnumCodeVO zzb = new ResBMSEnumCodeVO();
				zzb.setCode(EnumConstants.ContractType.ZZB.getCode());
				zzb.setName(EnumConstants.ContractType.ZZB.getDesc());
				enumCodeList.add(zzb);
			}
			if(channel.getEcontractFlag() != null && channel.getEcontractFlag() == 0L){
				//如果配置为电子合同 启用，返回纸质和电子选项
				ResBMSEnumCodeVO dzb = new ResBMSEnumCodeVO();
				dzb.setCode(EnumConstants.ContractType.DZB.getCode());
				dzb.setName(EnumConstants.ContractType.DZB.getDesc());
				enumCodeList.add(dzb);
			}
			//验证判断
			String result = "false";
			for (ResBMSEnumCodeVO resBMSEnumCodeVO : enumCodeList) {
				if(reqLoanContractSignVo.getContractTypeCode().equals(resBMSEnumCodeVO.getCode())){
					result = "true";
					break;
				}
			}
			response.setData(result);
			response.setRepMsg("success");
			response.setRepCode(Response.SUCCESS_RESPONSE_CODE);
		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		
		return response;
	}
	
	@Override
	public Response<ResLoanContractSignVo> findLoanChannelLockTarget(ReqLoanContractSignVo reqLoanContractSignVo) {
		Response<ResLoanContractSignVo> response = new Response<ResLoanContractSignVo>();
		try {
			if (StringUtils.isBlank(reqLoanContractSignVo.getLoanNo())) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "loanNo" });
			}
			if (StringUtils.isBlank(reqLoanContractSignVo.getChannelCd())) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "channelCd" });
			}
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
			paramMap.put("channelCode", reqLoanContractSignVo.getChannelCd());
			BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetService.getBy(paramMap);
			
			if(lockTargetEntity != null){
				ResLoanContractSignVo target = new ResLoanContractSignVo();
				
				BeanUtils.copyProperties(lockTargetEntity, target);
				
				response.setData(target);
			}
			
			response.setRepMsg("success");
		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		
		return response;
	}

	@Override
	public Response<ResLoanContractSignVo> aiteTerminationLoan(ReqLoanContractSignVo reqLoanContractSignVo) {
		Response<ResLoanContractSignVo> response = new Response<ResLoanContractSignVo>();
		try {
			if (StringUtils.isBlank(reqLoanContractSignVo.getLoanNo())) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "loanNo" });
			}
			if (StringUtils.isBlank(reqLoanContractSignVo.getChannelCd())) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "channelCd" });
			}
			if(!EnumConstants.channelCode.AITE.getValue().equals(reqLoanContractSignVo.getChannelCd())){
				throw new BizException(BizErrorCode.APPLY_VALUE_EOERR, "请传入来捞财宝渠道");
			}
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
			paramMap.put("channelCode", reqLoanContractSignVo.getChannelCd());
			BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetService.getBy(paramMap);
			
			boolean notifyFalg = false;
			// 推送判断,推送后标的锁定
			if (lockTargetEntity !=null && "Y".equals(lockTargetEntity.getLockTarget())) {
				// 调用终止借款接口，待联调
				Map<String, String> requestMap = new HashMap<String, String>();
				requestMap.put("borrowNo", reqLoanContractSignVo.getLoanNo());
				requestMap.put("reason", reqLoanContractSignVo.getFirstLevleReasons());
				HttpResponse httpResponse = aiTeHttpService.terminationLoan(requestMap);
				if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
					String content = httpResponse.getContent();
					Map contentMap = JsonUtils.decode(content, Map.class);
					if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("repCode"))){
						// 处理成功 // 同意终止，通知核心
						//标的解锁锁定
						notifyFalg = aiTeLoanContractService.unLockTarget(reqLoanContractSignVo);
						if (!notifyFalg) {
							throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0);
						}
					} else {
						throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"借款无法取消，请联系爱特");
					}
				} else {
					throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"捞财宝地址访问失败");
				}
			}
			
			response.setRepMsg("success");
		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		
		return response;
	}

	@Override
	public Response<ResLoanContractSignVo> saveLoanContractPlatformAcc(
			ReqLoanContractSignVo reqLoanContractSignVo) {
		if(SignFactory.containsChannelCd(reqLoanContractSignVo.getChannelCd())){
			return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(),
					EnumConstants.Sign.SAVE_PLATFROM_ACC.getValue());
		} else{
			Response<ResLoanContractSignVo> res = new Response<ResLoanContractSignVo>();
			res.setRepMsg("渠道方法未注册");
			res.setRepCode("111111111");
			return res;
		}
	}

	@Override
	public Response<List<ResBMSChannelVO>> getChannelByOrgProAltLoanCheck(ReqLoanContractSignVo reqLoanContractSignVo) {
		if(reqLoanContractSignVo.getLoanNo()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, "loanNo");
		}
		if(reqLoanContractSignVo.getId()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, "id");
		}
		Response<List<ResBMSChannelVO>> res = channelExecuter.getChannelByOrgProAlt(reqLoanContractSignVo);
		if(res.isSuccess()){
			List<ResBMSChannelVO> channelList = res.getData();
			List<ResBMSChannelVO> channelListRemove = new ArrayList<ResBMSChannelVO>();
			for (ResBMSChannelVO channelVO : channelList) {
				if(EnumConstants.channelCode.LUJS.getValue().equals(channelVO.getCode())){
					//陆金所内部校验
					Response<ResLoanContractSignVo> resLujs = new Response<ResLoanContractSignVo>();
					
					if(!luJSSaveLoanContractSign.lujsInnerCheck(reqLoanContractSignVo, resLujs)){
						
						channelListRemove.add(channelVO);
					}
				}
			}
			if(!channelListRemove.isEmpty()){
				channelList.removeAll(channelListRemove);
				res.setData(channelList);
			}
		}
		
		return res;
	}
	
}
