package com.ymkj.cms.biz.facade.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.enums.EnumAppStateConstants;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.app.IAPPExecuter;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600001;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600002;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600003;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600004;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600005;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600006;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600007;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600008;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600013;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_900005;
import com.ymkj.cms.biz.api.vo.response.app.Res_VO_600006;
import com.ymkj.cms.biz.api.vo.response.app.Res_VO_600013;
import com.ymkj.cms.biz.api.vo.response.apply.ResTrialBeforeCreditVO;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.entity.app.AppDataInfoEntity;
import com.ymkj.cms.biz.entity.app.BMSGetImageFileUploadEntity;
import com.ymkj.cms.biz.entity.app.CoreResAppDataInfoEntity;
import com.ymkj.cms.biz.entity.app.LoanVo;
import com.ymkj.cms.biz.entity.apply.APPWhitePersonnelEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.app.IAppDataInfoService;
import com.ymkj.cms.biz.service.app.IAppGetImageFileUploadStatusService;
import com.ymkj.cms.biz.service.app.IAppValidateService;
import com.ymkj.cms.biz.service.app.IInitFieldService;
import com.ymkj.cms.biz.service.app.IInitProductListService;
import com.ymkj.cms.biz.service.app.ISaveApplyInfoService;
import com.ymkj.cms.biz.service.apply.ILoanTrialService;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.service.IMessageExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResRoleVO;


@Service
public class APPExecuter<T> implements IAPPExecuter {
	
	private Log log = LogFactory.getLog(APPExecuter.class);
	
	public static String LOG_TAG="APPExecuter.";

	@Autowired
	private IInitProductListService iInitProductListService;

	@Autowired
	private ILoanTrialService iLoanTrialService;

	@Autowired
	private IInitFieldService initFieldService;

	@Autowired
	private ISaveApplyInfoService saveApplyInfoService;

	@Autowired
	private IAppDataInfoService appDataInfoService;
	
	@Autowired
	private ICoreHttpService coreHttpService;
	
	@Autowired
	private IEmployeeExecuter employeeExecuter;
	
	@Autowired
	private IAppValidateService appValidateService; 
	
	@Autowired
	private IAppGetImageFileUploadStatusService iAppGetImageFileUploadStatusService;
	
	@Override
	public Response<Object> initField(Req_VO_600001 vo_600001) {
		Response<Object> response = new Response<Object>();

		Map<String,Object> fieldMap = initFieldService.initField(vo_600001.getAppCurrentTime());
		if(fieldMap.isEmpty()){
			log.info(log+"initField：【APP控件初始化失败！】");
			throw new BizException(CoreErrorCode.DEFAULT);
		}
		response.setData(fieldMap);
		return response;
	}

	@Override
	public Response<Object> getProductListData(Req_VO_600002 vo_600002) {
		Response<Object> response = new Response<Object>();

		Response<Object> validateResponse = Validate.getInstance().validate(vo_600002);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}

		Map<String,Object> resultMap = iInitProductListService.getProductListData(vo_600002.getUserCode());
		if(resultMap.isEmpty()){
			log.info(log+"getProductListData：【APP产品列表信息初始化失败！】");
			throw new BizException(CoreErrorCode.DEFAULT);
		}
		response.setData(resultMap);
		return response;
	}

	@Override
	public Response<Object> saveApplyInfo(Req_VO_600003 vo_600003) {
		Response<Object> response = new Response<Object>();

		Map<String,Object> allApplyInfoMap = null;

		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(vo_600003);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}
		allApplyInfoMap = vo_600003.getApplyInfoMap();
		allApplyInfoMap.put("userCode", vo_600003.getUserCode());
		allApplyInfoMap.put("applyDate", vo_600003.getApplyDate());
		allApplyInfoMap.put("loanNo", vo_600003.getAppNo());
		
		//撤销时不做字段校验
		String reasonCode = ObjectUtils.toString(allApplyInfoMap.get("refuseReason"));//撤销原因code
		if(StringUtils.isBlank(reasonCode)){
			response = saveApplyInfoService.validateField(vo_600003);
			if(!response.isSuccess()){
				return response;
			}
		}

		//老系统提交新展业app校验
		int applength = vo_600003.getAppNo().length();
		if(StringUtils.isBlank(reasonCode) && applength == 20){
			throw new BizException(BizErrorCode.EOERR, "该申请件不是新系统录入的，请撤销重新录");
		}
		
		//插入申请信息
		try {
			response = saveApplyInfoService.insertApplyInfo(allApplyInfoMap);
			log.info(log+"saveApplyInfo：【APP申请信息保存成功！】");
		} catch (Exception e) {
			log.info(log+"saveApplyInfo：【APP申请信息保存失败！】 ");
			log.error(e);
			throw new BizException(BizErrorCode.EOERR,e.getMessage());
		}

		return response;
	}
	
	
	@Override
	public Response<Object> saveLoanBaseInfo(Req_VO_600005 vo_600005) {
		Response<Object> response = new Response<Object>();

		Map<String,Object> allApplyInfoMap = null;

		Response<Object> validateResponse = Validate.getInstance().validate(vo_600005);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}

		allApplyInfoMap = vo_600005.getApplyInfoMap();
		allApplyInfoMap.put("userCode", vo_600005.getUserCode());
		allApplyInfoMap.put("applyDate", DateUtil.getTodayHHmmss());

		//插入申请信息
		try {
			response = saveApplyInfoService.saveLoanBaseInfo(allApplyInfoMap);
			log.info(log+"saveApplyInfo：【APP申请信息保存成功！】");
		} catch (Exception e) {
			log.info(log+"saveApplyInfo：【APP申请信息保存失败！】 ");
			log.error(e);
			throw new BizException(BizErrorCode.EOERR,e.getMessage());
		}

		return response;
	}

	@Override
	public Response<Object> checkIDCard(Req_VO_600005 vo_600005){
		Response<Object> response = null;
		try {
			response = appValidateService.validate(vo_600005);
			log.info(log+"saveApplyInfo：【APP录单校验成功！】");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(log+"saveApplyInfo：【APP录单校验失败！】");
			log.error(e);
			throw new BizException(BizErrorCode.EOERR, e.getMessage());
		}
		return response;
	}
	
	@Override
	public Response<Object> saveCancelApplyInput(Req_VO_600004 vo_600004) {
		Response<Object> response = new Response<Object>();

		Map<String,Object> allApplyInfoMap = null;

		Response<Object> validateResponse = Validate.getInstance().validate(vo_600004);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}

		allApplyInfoMap = vo_600004.getApplyInfoMap();
		allApplyInfoMap.put("userCode", vo_600004.getUserCode());
		allApplyInfoMap.put("applyDate", vo_600004.getApplyDate());
		allApplyInfoMap.put("operatorCode", vo_600004.getOperatorCode());
		allApplyInfoMap.put("operatorName", vo_600004.getOperatorName());

		//插入申请信息
		try {
			response = saveApplyInfoService.insertCancelApplyInfo(allApplyInfoMap);
			log.info("==========App申请拒绝信息录入成功！");
		} catch (Exception e) {
			log.error(e);
			response.setRepCode("000001");
			response.setRepMsg("申请信息拒绝录入失败!");
			return response;
		}

		return response;
	}
	
	@Override
	public PageResponse<Res_VO_600006> getHisApplyInputList(Req_VO_600006 vo_600006) {
		PageResponse<Res_VO_600006> response = new PageResponse<Res_VO_600006>();
		if(StringUtils.isBlank(vo_600006.getUserCode())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"serviceCode"});
		}
		if(StringUtils.isBlank(vo_600006.getStatus())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"status"});
		}
		String oldStatus=	vo_600006.getStatus();
		String status=null;
		String rtfState=null;
		List<String> rtfNodeStates =null;
		List<String> rtfStates=null;
		ReqEmployeeVO reqEmployeeVO = new ReqEmployeeVO();
		reqEmployeeVO.setUsercode(vo_600006.getUserCode());
		reqEmployeeVO.setSysCode(EnumConstants.BMS_SYSCODE);
		Response<List<ResRoleVO>> resRoles=  employeeExecuter.findRolesByAccount(reqEmployeeVO);
		List<ResRoleVO> roles = resRoles.getData();	
		try {
		Map paramMap = BeanKit.bean2map(vo_600006);
		PageParam pageParam = new PageParam(vo_600006.getPageNum(), vo_600006.getPageSize());
		List<AppDataInfoEntity> demoEntitys =null;
		List<Res_VO_600006> records =null;
		PageBean<AppDataInfoEntity> pageBean =new PageBean<AppDataInfoEntity>();
		if(EnumAppStateConstants.YQ.getValue().equals(oldStatus)
				||EnumAppStateConstants.JQ.getValue().equals(oldStatus)
				||EnumAppStateConstants.ZC.getValue().equals(oldStatus)){
			records = new ArrayList<Res_VO_600006>();
			for (ResRoleVO resRoleVO : roles) {
				//如果是客户经理
				if("customerManager".equals( resRoleVO.getCode())){
					Map httpMap =new HashMap();
					httpMap.put("userCode",vo_600006.getUserCode());
					httpMap.put("userType",1);//1， 客户经理 2，客服
					httpMap.put("pageNum", vo_600006.getPageNum());
					httpMap.put("pageSize",vo_600006.getPageSize());
					httpMap.put("status", oldStatus);//1：逾期 2：结清
					HttpResponse res=coreHttpService.findLoanByState(httpMap);
					CoreResAppDataInfoEntity resCore = JsonUtils.decode(res.getContent(), CoreResAppDataInfoEntity.class);
					//返回记录总数
					response.setTotalCount(resCore.getTotalNo());
					 for (AppDataInfoEntity demoEntity : resCore.getLoanAppVoList()) {
						 	changeStatus2OldStatus(demoEntity);
							Res_VO_600006 resDemoVO = new Res_VO_600006();
							BeanUtils.copyProperties(demoEntity, resDemoVO);
							records.add(resDemoVO);
					}
				}else if("customerService".equals( resRoleVO.getCode())){
					Map httpMap =new HashMap();
					httpMap.put("userCode",vo_600006.getUserCode());
					httpMap.put("userType",2);//1， 客户经理 2，客服
					httpMap.put("pageNum", vo_600006.getPageNum());
					httpMap.put("pageSize",vo_600006.getPageSize());
					httpMap.put("status", oldStatus);//1：逾期 2：结清
					HttpResponse res=coreHttpService.findLoanByState(httpMap);
					CoreResAppDataInfoEntity resCore = JsonUtils.decode(res.getContent(), CoreResAppDataInfoEntity.class);
					 for (AppDataInfoEntity demoEntity : resCore.getLoanAppVoList()) {
						 //只返回认领申请的单子
						 if(appDataInfoService.isAppClaim(demoEntity.getAppNo())){
							 	changeStatus2OldStatus(demoEntity);
								Res_VO_600006 resDemoVO = new Res_VO_600006();
								BeanUtils.copyProperties(demoEntity, resDemoVO);
								records.add(resDemoVO);
						 }
					}
				}
			}
			 response.setRepMsg("suceess"); 
			 response.setRecords(records);
			//待签约 已结清 拒绝 逾期 取消 申请中 正常 待放款
		}else{
			if(EnumAppStateConstants.DQY.getValue().equals(oldStatus)){
				status="PASS";
				rtfStates = Arrays.asList(EnumConstants.RtfState.QYFP.getValue(),EnumConstants.RtfState.HTQY.getValue(),EnumConstants.RtfState.HTQR.getValue());
			}else if(EnumAppStateConstants.DFK.getValue().equals(oldStatus)){
				status="PASS";
				rtfStates = Arrays.asList(EnumConstants.RtfState.FKSH.getValue(),EnumConstants.RtfState.FKQR.getValue());
			}else if(EnumAppStateConstants.SQZ.getValue().equals(oldStatus)){
				status="APPLY";
			}else if (EnumAppStateConstants.QX.getValue().equals(oldStatus)){
				status="CANCEL";
			}else if (EnumAppStateConstants.JJ.getValue().equals(oldStatus)){
				status="REFUSE";
			}else if(EnumAppStateConstants.XSTH.getValue().equals(oldStatus)){				
				//CSFP-RETURN，XSCS-RETURN,XSZS-RETURN
				rtfNodeStates = Arrays.asList("CSFP-RETURN",EnumConstants.RtfNodeState.XSCSRETURN.getValue(),EnumConstants.RtfNodeState.XSZSRETURN.getValue());
			}
			records = new ArrayList<Res_VO_600006>();
			for (ResRoleVO resRoleVO : roles) {
				//如果是客户经理
				if("customerManager".equals( resRoleVO.getCode())){
					Map map =new HashMap();
					map.put("branchManagerCode", vo_600006.getUserCode());
					map.put("status", status);
					map.put("rtfStates", rtfStates);
					map.put("rtfNodeStates", rtfNodeStates);
					map.put("nowDate", DateUtil.getTodayHHmmss());
					pageBean = appDataInfoService.listPage(pageParam,map);
					demoEntitys = pageBean.getRecords();
					 for (AppDataInfoEntity demoEntity : demoEntitys) {
						    changeStatus2OldStatus(demoEntity);
							Res_VO_600006 resDemoVO = new Res_VO_600006();
							BeanUtils.copyProperties(demoEntity, resDemoVO);
							records.add(resDemoVO);
					}
				}
				//如果是客服
				if("customerService".equals( resRoleVO.getCode())){
					Map map =new HashMap();
					map.put("serviceCode", vo_600006.getUserCode());
					map.put("status", status);
					map.put("rtfStates", rtfStates);
					map.put("nowDate", DateUtil.getTodayHHmmss());
					pageBean = appDataInfoService.listPage(pageParam,map);
					demoEntitys = pageBean.getRecords();
					 for (AppDataInfoEntity demoEntity : demoEntitys) {
						    changeStatus2OldStatus(demoEntity);
							Res_VO_600006 resDemoVO = new Res_VO_600006();
							BeanUtils.copyProperties(demoEntity, resDemoVO);
							records.add(resDemoVO);
					}
				}
			}
				// 忽略 复制的字
				BeanUtils.copyProperties(pageBean, response, "records");
				response.setRepMsg("suceess");
				/*List<Res_VO_600006> oldrecords =changeStatus2OldStatus(records);*/
				response.setRecords(records);
		}
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());	
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage()+e.getLocalizedMessage());	
		}
		return response;
	}

	@Override
	public Response<Object> getfirstPageData(Req_VO_600007 vo_600007){
		Response<Object> response = new Response<Object>();
		if(StringUtils.isBlank(vo_600007.getUserCode())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"serviceCode"});
		}
		ReqEmployeeVO reqEmployeeVO = new ReqEmployeeVO();
		reqEmployeeVO.setUsercode(vo_600007.getUserCode());
		reqEmployeeVO.setSysCode(EnumConstants.BMS_SYSCODE);
		long overNum = 0;
		long rejectNum =0;
		Response<List<ResRoleVO>> resRoles=  employeeExecuter.findRolesByAccount(reqEmployeeVO);
		List<ResRoleVO> roles = resRoles.getData();	
		List<Map<String, Object>> statusCountList =null;
		Map paramMap =new HashMap<>();
		for (ResRoleVO resRoleVO : roles) {
			//如果是客户经理
			if("customerManager".equals( resRoleVO.getCode())){
				paramMap.put("branchManagerCode", vo_600007.getUserCode());
				statusCountList =appDataInfoService.getStatusCountByUserCode(paramMap);
				for (Map<String, Object> map : statusCountList) {
					if("APPLY".equals(map.get("STATUS"))){
						overNum=(long) map.get("APPCOUNT");
					}else if("REFUSE".equals(map.get("STATUS"))){
						rejectNum=(long) map.get("APPCOUNT");
					}
				}
			}else if("customerService".equals( resRoleVO.getCode())){
				paramMap.put("serviceCode", vo_600007.getUserCode());
				statusCountList =appDataInfoService.getStatusCountByUserCode(paramMap);
				for (Map<String, Object> map : statusCountList) {
					if("APPLY".equals(map.get("STATUS"))){
						overNum=(long) map.get("APPCOUNT");
					}else if("REFUSE".equals(map.get("STATUS"))){
						rejectNum=(long) map.get("APPCOUNT");
					}
				}
			}
		}
		Map firstPageData = new HashMap();
		firstPageData.put("overNum", overNum);
		firstPageData.put("rejectNum", rejectNum);
		response.setData(firstPageData);
		return response;

	}

	@Override
	public Response<List<ResTrialBeforeCreditVO>> getTrialBeforeCredit(Req_VO_600008 req_VO_600008) {

		Response<List<ResTrialBeforeCreditVO>> response = new Response<List<ResTrialBeforeCreditVO>>();
		HttpResponse httpResponse = null;

		Response<Object> validateResponse = Validate.getInstance().validate(req_VO_600008);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, validateResponse.getRepMsg());
		}

		Map<String, Object> paramsMap = new HashMap<>();

		paramsMap.put("name", "0000");
		paramsMap.put("loanType", req_VO_600008.getProductCd());
		paramsMap.put("money", req_VO_600008.getApplyLmt());
		paramsMap.put("time", req_VO_600008.getApplyTerm());
		paramsMap.put("firstRepaymentDate", req_VO_600008.getFristPaymentDate());
		paramsMap.put("fundsSources", req_VO_600008.getChannelCode());

		httpResponse = iLoanTrialService.createLoanTrial(paramsMap);
		Map<String,Object> contentMap = JsonUtils.decode(httpResponse.getContent(), Map.class);

		if(contentMap.get("code").equals("000000")){
			List<ResTrialBeforeCreditVO> resTrialBeforeCreditList = JsonUtils.decode(JsonUtils.encode(contentMap.get("repaymentDetail")), List.class);
			response.setData(resTrialBeforeCreditList);
		}else{
			response.setRepCode(ObjectUtils.toString(contentMap.get("code")));
		}

		return response;
	}

	public AppDataInfoEntity changeStatus2OldStatus(AppDataInfoEntity demoEntity ){
		String oldstatus = null;
		String 	newStatus =demoEntity.getStatus();
		String 	rtfState =demoEntity.getRtfState();
		String 	rtfNodeState =demoEntity.getRtfNodeState();
		if("CSFP-RETURN".equals(rtfNodeState)||EnumConstants.RtfNodeState.XSCSRETURN.getValue().equals(rtfNodeState)
				||EnumConstants.RtfNodeState.XSZSRETURN.getValue().equals(rtfNodeState)){
			oldstatus=EnumAppStateConstants.XSTH.getValue();
		}else if((EnumConstants.RtfState.QYFP.getValue().equals(rtfState)||EnumConstants.RtfState.HTQY.getValue().equals(rtfState)
				||EnumConstants.RtfState.HTQR.getValue().equals(rtfState))&&"PASS".equals(newStatus)){
			oldstatus=EnumAppStateConstants.DQY.getValue();
		}else if((EnumConstants.RtfState.FKSH.getValue().equals(rtfState)||EnumConstants.RtfState.FKQR.getValue().equals(rtfState))&&"PASS".equals(newStatus)){
			oldstatus=EnumAppStateConstants.DFK.getValue();
		}else if("APPLY".equals(newStatus)){
			oldstatus=EnumAppStateConstants.SQZ.getValue();
		}else if ("CANCEL".equals(newStatus)){
			oldstatus=EnumAppStateConstants.QX.getValue();
		}else if ("REFUSE".equals(newStatus)){
			oldstatus=EnumAppStateConstants.JJ.getValue();
		}else if("NORMAL".equals(newStatus)){
			oldstatus=findCoreState(demoEntity.getAppNo());
		}else{
			String 	applyTime =demoEntity.getApplyTime();
			String fmApplyTime=applyTime==null?"":applyTime.substring(0,19);
			demoEntity.setApplyTime(fmApplyTime);
			oldstatus=EnumAppStateConstants.getAppStateValueByCode(newStatus);
		}
		demoEntity.setStatus(oldstatus);
		return demoEntity;
	}

	private String findCoreState(String appNo) {
		Map httpParamMap= new HashMap();
		/*loanNos.add(appNo);
			httpParamMap.put("loanNos",loanNos);*/
		httpParamMap.put("loanNos",appNo);
		HttpResponse  res =coreHttpService.queryLoanState(httpParamMap);
		CoreResAppDataInfoEntity resCore = JsonUtils.decode(res.getContent(),CoreResAppDataInfoEntity.class);
		if(resCore!=null && "000000".equals(resCore.getCode())){
			List<LoanVo> loans= resCore.getLoans();
			if(loans != null && loans.size()>0){
				String state =loans.get(0).getLoanState();
				if("结清".equals(state)){
					state ="已"+state;
				}
				return state;
			}else{
				return EnumAppStateConstants.WZ.getValue();
			}
		}else{
			return EnumAppStateConstants.WZ.getValue();
		}
	}
	public static void main(String[] args) {
		String jsonStr ="{\"loans\":[{\"loanFlowState\":\"正常\",\"loanId\":150001266,\"loanNo\":\"20170608155357519750\",\"loanState\":\"正常\"}],\"message\":\"查询成功\",\"code\":\"000000\"}";
		CoreResAppDataInfoEntity resCore = JsonUtils.decode(jsonStr,CoreResAppDataInfoEntity.class);
		System.out.println(resCore);

	}
	@Override
	public Response<Object> saveRefuseApplyInput(Req_VO_600004 vo_600004) {
		Response<Object> response = new Response<Object>();

		Map<String,Object> allApplyInfoMap = null;

		Response<Object> validateResponse = Validate.getInstance().validate(vo_600004);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}

		allApplyInfoMap = vo_600004.getApplyInfoMap();
		allApplyInfoMap.put("userCode", vo_600004.getUserCode());
		allApplyInfoMap.put("applyDate", vo_600004.getApplyDate());
		allApplyInfoMap.put("operatorCode", vo_600004.getOperatorCode());
		allApplyInfoMap.put("operatorName", vo_600004.getOperatorName());
		
		//插入申请信息
		try {
			response = saveApplyInfoService.insertRefuseApplyInfo(allApplyInfoMap);
			log.info("==========App申请拒绝信息录入成功！");
		} catch (Exception e) {
			log.error(e);
			response.setRepCode("000001");
			response.setRepMsg("申请信息拒绝录入失败!");
			return response;
		}

		return response;
	}
	
	@Override
	public Response<Res_VO_600013> getImageFileUpload(Req_VO_600013 req_VO_600013) {
		
		
		log.info("BMS接口查询视频上传开始"+System.currentTimeMillis());
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userCode", req_VO_600013.getUserCode());
		map.put("applicantName", req_VO_600013.getApplicantName());
		map.put("appNo", req_VO_600013.getAppNo());
		map.put("uploadState", req_VO_600013.getUploadState());
		//map.put("status", req_VO_600013.getStatus());
		PageParam pageParam = new PageParam(req_VO_600013.getPageNum(),
				req_VO_600013.getPageSize());
		log.info("查询SQL开始"+System.currentTimeMillis());
		PageBean<BMSGetImageFileUploadEntity> pageBean=iAppGetImageFileUploadStatusService.listPage(pageParam, map);
		log.info("查询SQL结束"+System.currentTimeMillis());
		Response<Res_VO_600013> response=new Response<Res_VO_600013>();
		Res_VO_600013 vo=new Res_VO_600013();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		for(BMSGetImageFileUploadEntity entity:pageBean.getRecords()){
			try {
				if(null!=entity.getApplyTimeOld()){
					entity.setApplyTime(sdf.format(entity.getApplyTimeOld()));
				}
				Map<String, Object> paramMap = BeanKit.bean2map(entity);
				vo.getAppInputListData().add(paramMap);
			} catch (Exception e) {
				log.info("实体转换MAP失败--------------------------"+e.getMessage());
			} 
		}
		vo.setTotalNo(pageBean.getTotalCount());
		vo.setUserCode(req_VO_600013.getUserCode());
		response.setData(vo);
		log.info("BMS接口查询视频上传结束"+System.currentTimeMillis());
		return response;
	}

	@Override
	public Response<Object> insertLoanLog(Req_VO_600001 vo_600001) {
		return saveApplyInfoService.insertLoanLog(vo_600001);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response<Object> validatePhoneRepeat(Req_VO_900005 req_VO_900005) {
		Response<Object> response = new Response<Object>();

		Response<Object> validateResponse = Validate.getInstance().validate(req_VO_900005);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}
		
		Map<String,Object> allApplyInfoMap = req_VO_900005.getApplyInfoMap();
		String validCellphone = req_VO_900005.getCellphone();
		String cellphone = validCellphone.split("\\|")[2];
		Map<String,Object> resultMap = saveApplyInfoService.getValidatePhoneList(allApplyInfoMap);
		
		List<String> cellphoneList = (List<String>) resultMap.get("cellphoneList");
		List<String> cellphoneInfoList = (List<String>) resultMap.get("cellphoneInfoList");
		//排除自身
		for(int i = cellphoneList.size() - 1; i >= 0; i--){
			String cellphoneTmp = cellphoneList.get(i);
			String cellphoneInfoTmp = cellphoneInfoList.get(i);
			
			if(validCellphone.equals(cellphoneTmp)){
				cellphoneList.remove(cellphoneTmp);
				cellphoneInfoList.remove(cellphoneInfoTmp);
			}
		}
		
		Map<String,Object> tmpMap = new HashMap<>();
		for (int i = 0; i < cellphoneList.size(); i++) {
			if(cellphone.equals(cellphoneList.get(i).split("\\|")[2])){
				tmpMap.put("validFlag", "1");
				tmpMap.put("validMsg", "此手机号与"+cellphoneInfoList.get(i)+"重复");
				break;
			}
		}
		if(tmpMap.isEmpty()){
			tmpMap.put("validFlag", "0");
			tmpMap.put("validMsg", "");
		}
		response.setData(tmpMap);
		return response;
	}
}
