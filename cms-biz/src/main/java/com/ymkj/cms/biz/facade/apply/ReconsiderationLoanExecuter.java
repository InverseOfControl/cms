package com.ymkj.cms.biz.facade.apply;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.bds.biz.api.service.IBlackListExecuter;
import com.ymkj.bds.biz.api.vo.request.RemoveReqVo;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.apply.IReconsiderationLoanExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqLoanReviewVo;
import com.ymkj.cms.biz.api.vo.request.apply.ReqQueryReviewMessageCountVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqReconsiderationLoanSearchVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqUpdReviewVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqUpdateReviewReadStatusVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResReconsiderationLoanSearchVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResReassignmentUpdVO;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.dao.audit.BMSFirstAuditDao;
import com.ymkj.cms.biz.entity.apply.LoanReviewEntity;
import com.ymkj.cms.biz.entity.audit.first.BlackGreyVO;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.ILoanReviewService;
import com.ymkj.cms.biz.service.apply.LoanBaseService;
import com.ymkj.cms.biz.service.apply.LoanExtService;
import com.ymkj.cms.biz.service.audit.BMSQualityInspectionService;
import com.ymkj.cms.biz.service.client.PMSClientService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.pms.biz.api.service.ICalendarExecuter;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqCalendarVO;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResCalendarVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:LoanReviewExecuter</p>
 * <p>Description:复议接口服务方</p>
 * @uthor YM10159
 * @date 2017年3月1日 下午4:11:08
 */
@Service
public class ReconsiderationLoanExecuter implements IReconsiderationLoanExecuter {

	private Log log = LogFactory.getLog(ReconsiderationLoanExecuter.class);
	public static String LOG_TAG = "ReconsiderationLoanExecuter.";

	@Autowired
	private ILoanReviewService loanReviewService;

	@Autowired
	private IBMSSysLogService sysLogService;

	@Autowired
	private IEmployeeExecuter iEmployeeExecuter;

	@Autowired
	private ICalendarExecuter calendarExecuter;
	
	@Autowired
	private LoanBaseService loanBaseService;
	
	@Autowired
	private LoanExtService loanExtService;
	
	@Autowired
	private BMSFirstAuditDao firstAuditDao;
	@Autowired
	private IBlackListExecuter iBlackListExecuter;
	@Autowired
	private PMSClientService pmsClientService;
	@Autowired
	private BMSQualityInspectionService bmsQualityInspectionService;
	
	//信审复议查询
	private final String QUERY_XS_SQL = "xsListPage";
	private final String COUNT_XS_SQL = "xsCountByPageParam";
	
	@Override
	public Response<Object>  insert(ReqLoanReviewVo reqLoanReviewVo){

		Map<String,Object> paramMap =new HashMap<String,Object>();
		Response<Object> response = new Response<Object>(); 
		List<LoanReviewEntity> reviewResultList = new ArrayList<LoanReviewEntity>();

		ReqCalendarVO reqCalendarVO = new ReqCalendarVO();
		reqCalendarVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);

		//获取信审环节被拒绝的借款申请信息
		String today = DateUtil.defaultFormatDay(DateUtil.getToday());
		paramMap.put("endTime", today+" 23:59:59");

		List<LoanReviewEntity> LoanReviewEntityList = loanReviewService.getReviewList(paramMap);

		for (LoanReviewEntity loanReviewEntity : LoanReviewEntityList) {
			//计算拒绝之后的第三个工作日日期
			String afterThreeDayDate = "";
			String refuseDate = loanReviewEntity.getRefuse_date();
			try {
				reqCalendarVO.setPageSize(3);
				reqCalendarVO.setSomeDay(DateUtil.getDate(DateUtil.strToDate(refuseDate, "yyyy-MM-dd"), "yyyy-MM-dd"));
				Response<ResCalendarVO> nextWorkdayResponse = calendarExecuter.getNextWorkday(reqCalendarVO);
				if(nextWorkdayResponse.isSuccess()){
					afterThreeDayDate = ObjectUtils.toString(nextWorkdayResponse.getData().getSomeDay());
				}
			} catch (Exception e1) {}

			loanReviewEntity.setThree_workday_date(afterThreeDayDate);
			
			
			reviewResultList.add(loanReviewEntity);

		}

		if(CollectionUtils.isEmpty(reviewResultList)){
			log.info(LOG_TAG+"insert:【没有需要复议的案件！】");
			response.setRepMsg("复议定时任务执行成功！");
			return response;
//			throw new BizException(BizErrorCode.EOERR, "没有需要复议的案件！");
		}

		//把拒绝的借款申请信息同步到复议表中
		boolean result = loanReviewService.insert(reviewResultList);

		// 数据同步失败
		if(!result){
			log.info(LOG_TAG+"insert:【复议数据同步失败！】");
			throw new BizException(BizErrorCode.EOERR, "复议数据同步失败！");
		}

		//系统日志
		sysLogService.recordSysLog(reqLoanReviewVo,"复议|复议|复议|插入","ReconsiderationLoanExecuter/insert","复议定时任务");

		response.setRepMsg("复议定时任务执行成功！");
		return response;
	}

	@Override
	public PageResponse<ResReconsiderationLoanSearchVO> search( ReqReconsiderationLoanSearchVO reqReconsiderationLoanSearchVO) {

		PageResponse<ResReconsiderationLoanSearchVO> response = new PageResponse<ResReconsiderationLoanSearchVO>();
		List<ResReconsiderationLoanSearchVO> records = new ArrayList<ResReconsiderationLoanSearchVO>();

		// 参数校验
		Response<Object> validateResponse = Validate.getInstance().validate(reqReconsiderationLoanSearchVO);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}

		PageParam pageParam = new PageParam(reqReconsiderationLoanSearchVO.getPageNum(), reqReconsiderationLoanSearchVO.getPageSize());
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanKit.bean2map(reqReconsiderationLoanSearchVO);
		} catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}

		//根据工号查询所在营业部
		String userCode = reqReconsiderationLoanSearchVO.getServiceCode();

		ReqEmployeeVO reqEmployeeVO = new ReqEmployeeVO();
		reqEmployeeVO.setSysCode("bms");
		reqEmployeeVO.setUsercode(userCode);

		Response<List<ResOrganizationVO>> res=	iEmployeeExecuter.findDeptsByAccount(reqEmployeeVO);
		List<ResOrganizationVO> resOrganizationVOList=	res.getData();

		if(CollectionUtils.isEmpty(resOrganizationVOList)){
			log.info(LOG_TAG+"search：【查询员工所在营业部信息为空！】");
			return response;
		}
		//一个人在多个机构下，需要显示多机构的数据
		List<String> orgIdList = new ArrayList<String>();
		for (ResOrganizationVO resOrganizationVO : resOrganizationVOList) {
			orgIdList.add(resOrganizationVO.getId().toString());
		}
//		ResOrganizationVO resOrganizationVO = resOrganizationVOList.get(0);
//
//		paramMap.put("orgId", resOrganizationVO.getId());
		paramMap.put("orgIdList", orgIdList);
		paramMap.put("currentDate",DateUtil.getDate(DateUtil.getToday(), "yyyy-MM-dd"));

		// 调用业务逻辑
		PageBean<LoanReviewEntity> pageBean = loanReviewService.listPage(pageParam, paramMap);

		List<LoanReviewEntity> loanReviewEntityList = pageBean.getRecords();
		for (LoanReviewEntity loanReviewEntity : loanReviewEntityList) {
			ResReconsiderationLoanSearchVO resReconsiderationLoanSearchVO = new ResReconsiderationLoanSearchVO();
			resReconsiderationLoanSearchVO.setPersonName(loanReviewEntity.getName());
			resReconsiderationLoanSearchVO.setIdNo(loanReviewEntity.getId_no());
			resReconsiderationLoanSearchVO.setLoanNo(loanReviewEntity.getLoan_no());
			resReconsiderationLoanSearchVO.setApplyLmt((int)loanReviewEntity.getApply_term());
			resReconsiderationLoanSearchVO.setProductName(loanReviewEntity.getProduct_name());
			resReconsiderationLoanSearchVO.setRejectedTime(loanReviewEntity.getRefuse_date());
			resReconsiderationLoanSearchVO.setFirstLevelReason(loanReviewEntity.getPrimary_reason());
			resReconsiderationLoanSearchVO.setStatus(ObjectUtils.toString(loanReviewEntity.getStatus()));
			resReconsiderationLoanSearchVO.setIsRead(ObjectUtils.toString(loanReviewEntity.getIs_read()));
			resReconsiderationLoanSearchVO.setRemark(loanReviewEntity.getReview_remark());
			records.add(resReconsiderationLoanSearchVO);
		}
		// 忽略 复制的字段
		BeanUtils.copyProperties(pageBean, response, "records");

		response.setRecords(records);
		return response;
	}

	@Override
	public Response<Object> updateStatus(ReqLoanReviewVo reqLoanReviewVo) {
		Response<Object> response = new Response<Object>(); 
		LoanReviewEntity loanReviewEntity = new LoanReviewEntity();

		String loan_no = reqLoanReviewVo.getLoan_no();
		String status = ObjectUtils.toString(reqLoanReviewVo.getStatus());

		if (StringUtils.isBlank(loan_no) || StringUtils.isBlank(status)) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "loan_no | status" });
		}

		loanReviewEntity.setLoan_no(loan_no);
		loanReviewEntity.setStatus(Integer.valueOf(status));
		loanReviewEntity.setModified_time(DateUtil.defaultFormatMss(DateUtil.getTodayHHmmss()));
		int i = loanReviewService.updateStatus(loanReviewEntity);
		if(i == 0){
			log.info(LOG_TAG+"updateStatus：【复议状态修改失败！】");
			throw new CoreException(CoreErrorCode.DB_INSERT_RESULT_0.getErrorCode(),CoreErrorCode.DB_INSERT_RESULT_0.getDefaultMessage());
		}

		//系统日志
		sysLogService.recordSysLog(reqLoanReviewVo,"复议|复议|复议|修改","ReconsiderationLoanExecuter/updateStatus","复议状态修改");

		return response;
	}

	@Override
	public Response<Object> insertReviewReason(ReqLoanReviewVo reqLoanReviewVo) {
		Response<Object> response = new Response<Object>(); 

		LoanReviewEntity loanReviewEntity = new LoanReviewEntity();
		BeanUtils.copyProperties(reqLoanReviewVo, loanReviewEntity);

		int i = loanReviewService.insertReviewReason(loanReviewEntity);
		if(i == 0){
			log.info(LOG_TAG+"insertReviewReason：【复议原因插入失败！】");
			throw new CoreException(CoreErrorCode.DB_INSERT_RESULT_0.getErrorCode(),CoreErrorCode.DB_INSERT_RESULT_0.getDefaultMessage());
		}

		//系统日志
		sysLogService.recordSysLog(reqLoanReviewVo,"复议|复议|复议|插入","ReconsiderationLoanExecuter/insertReviewReason","复议原因和备注信息插入");

		return response;
	}

	@Override
	public Response<Object> queryMessageCount(ReqQueryReviewMessageCountVO reqQueryReviewMessageCountVO) {

		Response<Object> response = new Response<Object>(); 

		// 参数校验
		Response<Object> validateResponse = Validate.getInstance().validate(reqQueryReviewMessageCountVO);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}

		//根据工号查询所在营业部
		String userCode = reqQueryReviewMessageCountVO.getUserCode();

		ReqEmployeeVO reqEmployeeVO = new ReqEmployeeVO();
		reqEmployeeVO.setSysCode("bms");
		reqEmployeeVO.setUsercode(userCode);

		Response<List<ResOrganizationVO>> res=	iEmployeeExecuter.findDeptsByAccount(reqEmployeeVO);
		List<ResOrganizationVO> resOrganizationVOList=	res.getData();

		if(CollectionUtils.isEmpty(resOrganizationVOList)){
			log.info(LOG_TAG+"queryMessageCount：【查询组织机构信息为空！】");
			return response;
		}

		ResOrganizationVO resOrganizationVO = resOrganizationVOList.get(0);

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("orgId", resOrganizationVO.getId());
		paramMap.put("flag", reqQueryReviewMessageCountVO.getMessageFlag());
		paramMap.put("currentDate",DateUtil.getDate(DateUtil.getToday(), "yyyy-MM-dd"));

		long i = loanReviewService.queryMessageCount(paramMap);

		response = new Response<Object>(i);

		return response;
	}

	@Override
	public Response<Object> updateIsReadStatus(ReqUpdateReviewReadStatusVO reqUpdateReviewReadStatusVO) {

		Response<Object> response = new Response<Object>(); 

		// 参数校验
		Response<Object> validateResponse = Validate.getInstance().validate(reqUpdateReviewReadStatusVO);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}

		List<String> loanNoList = reqUpdateReviewReadStatusVO.getLoanNoList();

		for (String loanNo : loanNoList) {
			loanReviewService.updateIsReadStatus(loanNo);
		}

		response.setRepMsg("修改已读状态成功");
		response.setData(loanNoList);

		//系统日志
		sysLogService.recordSysLog(reqUpdateReviewReadStatusVO,"复议|复议|复议|修改","ReconsiderationLoanExecuter/updateIsReadStatus","修改未读数据的状态为已读");

		return response;
	}

	@Override
	public Response<Object> updateOrSaveReviewStatus(ReqUpdReviewVO reqUpdateReviewReadStatusVO) {
		// 参数校验
		Response<Object> validateResponse = Validate.getInstance().validate(reqUpdateReviewReadStatusVO);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}

		Response<Object> response=new Response<Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", reqUpdateReviewReadStatusVO.getLoanNo());

		//1.复议同意
		if(reqUpdateReviewReadStatusVO.getFlag() == 1){
			paramMap.put("newRejectFirstReason", reqUpdateReviewReadStatusVO.getNewRejectFirstReason());	
			paramMap.put("newRejectFirstReasonCode", reqUpdateReviewReadStatusVO.getNewRejectFirstReasonCode());
			paramMap.put("newRejectTwoReason",reqUpdateReviewReadStatusVO.getNewRejectTwoReason());
			paramMap.put("newRejectTwoReasonCode",reqUpdateReviewReadStatusVO.getNewRejectTwoReasonCode());
			paramMap.put("remark",reqUpdateReviewReadStatusVO.getRemark());
			paramMap.put("reviewResult", reqUpdateReviewReadStatusVO.getReviewResult());
			//删除灰名单
			removeBlackListId(reqUpdateReviewReadStatusVO.getLoanNo());
			BlackGreyVO blackGreyVO=new BlackGreyVO();
			blackGreyVO.setFirstLevelReasons(reqUpdateReviewReadStatusVO.getNewRejectFirstReason());
			blackGreyVO.setFirstLevelReasonsCode(reqUpdateReviewReadStatusVO.getNewRejectFirstReasonCode());
			blackGreyVO.setTwoLevelReasons(reqUpdateReviewReadStatusVO.getNewRejectTwoReason());
			blackGreyVO.setTwoLevelReasonsCode(reqUpdateReviewReadStatusVO.getNewRejectTwoReasonCode());
			blackGreyVO.setOperatorCode(reqUpdateReviewReadStatusVO.getModifierId());
			blackGreyVO.setLoanNo( reqUpdateReviewReadStatusVO.getLoanNo());  //借款编号
			ResReassignmentUpdVO vo=bmsQualityInspectionService.ifSaveBlackGrey(blackGreyVO);
			if(vo.isIfSuccessful()==true){
				paramMap.put("blackList", vo.getMsg());
			}

			//2.复议退回
		}else if(reqUpdateReviewReadStatusVO.getFlag() == 2){
			paramMap.put("status", reqUpdateReviewReadStatusVO.getReviewResult());
			paramMap.put("remark",reqUpdateReviewReadStatusVO.getRemark());
			//3.复议拒绝
		}else{
			paramMap.put("reviewResult", reqUpdateReviewReadStatusVO.getReviewResult());

		}
		ReqEmployeeVO reqEmployeeVO=new ReqEmployeeVO();
		reqEmployeeVO.setUsercode(reqUpdateReviewReadStatusVO.getModifierId());
		ResEmployeeVO resVO=pmsClientService.findEmployeeByCode(reqEmployeeVO);
		if(resVO!=null){
			paramMap.put("modifier", resVO.getName());	
		}
		paramMap.put("modifierDate",DateUtil.getTodayHHmmss());
		paramMap.put("modifierId", reqUpdateReviewReadStatusVO.getModifierId());
		int i=loanReviewService.updateOrSaveReviewStatus(paramMap);
		if(i == 0){
			log.info(LOG_TAG+"insertReviewReason：【复议原因插入失败！】");
			throw new CoreException(CoreErrorCode.DB_INSERT_RESULT_0.getErrorCode(),CoreErrorCode.DB_INSERT_RESULT_0.getDefaultMessage());
		}else{
			response.setRepCode("000000");
			response.setRepMsg("更新成功");
		}
		//添加系统日志
		sysLogService.recordSysLog(reqUpdateReviewReadStatusVO,"复议|复议|复议|修改","ReconsiderationLoanExecuter/updateOrSaveReviewStatus","复议办理修改成功");
		//添加借款日志
		loanReviewService.saveLoanLog(reqUpdateReviewReadStatusVO);
		return response;
	}
	
	
	public void removeBlackListId(String loanNo){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", loanNo);
		LoanReviewEntity loanExt = loanReviewService.selectByLoanNo(loanNo);
		if(!StringUtils.isEmpty(loanExt.getBlacklist_id())){
			log.info("-------扩展表存在黑名单ID["+loanExt.getBlacklist_id()+"]，调用行为库删除----------");
			RemoveReqVo bdsReq = new RemoveReqVo();
			bdsReq.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
			bdsReq.setRemoveId(loanExt.getBlacklist_id());
			Response<Object> bdsResponse = iBlackListExecuter.removeBlackGreyList(bdsReq);
			if (bdsResponse.isSuccess()) {
				log.info("-------删除行为库灰名单成功----------");
			} else {
				log.info("-------["+loanNo+"]删除行为库灰名单失败----------"+bdsResponse.getRepMsg());
			}
			//不管成功不成功都删除先
			loanReviewService.updateBlackListIdByLoanNo(paramMap);
		}
	}

	@Override
	public PageResponse<ResReconsiderationLoanSearchVO> xsSearch(ReqReconsiderationLoanSearchVO reqReconsiderationLoanSearchVO) {
		PageResponse<ResReconsiderationLoanSearchVO> response = new PageResponse<ResReconsiderationLoanSearchVO>();
		List<ResReconsiderationLoanSearchVO> records = new ArrayList<ResReconsiderationLoanSearchVO>();

		// 参数校验
		Response<Object> validateResponse = Validate.getInstance().validate(reqReconsiderationLoanSearchVO);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}

		PageParam pageParam = new PageParam(reqReconsiderationLoanSearchVO.getPageNum(), reqReconsiderationLoanSearchVO.getPageSize());
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanKit.bean2map(reqReconsiderationLoanSearchVO);
		} catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		paramMap.put("currentDate",DateUtil.getDate(DateUtil.getToday(), "yyyy-MM-dd"));

		// 调用业务逻辑
		PageBean<LoanReviewEntity> pageBean = loanReviewService.listPage(pageParam, paramMap,QUERY_XS_SQL,COUNT_XS_SQL);

		List<LoanReviewEntity> loanReviewEntityList = pageBean.getRecords();
		for (LoanReviewEntity loanReviewEntity : loanReviewEntityList) {
			//进件营业部,和提交信审时间
			ResReconsiderationLoanSearchVO resReconsiderationLoanSearchVO = new ResReconsiderationLoanSearchVO();
			resReconsiderationLoanSearchVO.setPersonName(loanReviewEntity.getName()); //申请人姓名
			resReconsiderationLoanSearchVO.setIdNo(loanReviewEntity.getId_no());    //身份证号
			resReconsiderationLoanSearchVO.setLoanNo(loanReviewEntity.getLoan_no());   //借款编号
			resReconsiderationLoanSearchVO.setProductName(loanReviewEntity.getProduct_name()); //借款产品
			resReconsiderationLoanSearchVO.setRejectedTime(loanReviewEntity.getRefuse_date()); //拒绝时间
			if(loanReviewEntity.getPrimary_reason()!=null && loanReviewEntity.getSecode_reason()!=null){
				resReconsiderationLoanSearchVO.setFirstLevelReason(loanReviewEntity.getPrimary_reason()+"|"+loanReviewEntity.getSecode_reason()); //拒绝原因	
			}else{
				resReconsiderationLoanSearchVO.setFirstLevelReason(loanReviewEntity.getPrimary_reason());
			}
			resReconsiderationLoanSearchVO.setStatus(ObjectUtils.toString(loanReviewEntity.getStatus()));
			resReconsiderationLoanSearchVO.setEnterBranch(loanReviewEntity.getEnter_branch());  //进件营业部
			resReconsiderationLoanSearchVO.setSubmitXsDate(loanReviewEntity.getSubmit_xs_date()); //提交信审时间
			if(loanReviewEntity.getNew_reject_first_reason()!=null && loanReviewEntity.getNew_reject_two_reason()!=null){
				resReconsiderationLoanSearchVO.setNewRejectReason(loanReviewEntity.getNew_reject_first_reason()+"|"+loanReviewEntity.getNew_reject_two_reason());	
			}else{
				resReconsiderationLoanSearchVO.setNewRejectReason(loanReviewEntity.getNew_reject_first_reason());
			}
			resReconsiderationLoanSearchVO.setRejectPersonName(loanReviewEntity.getReject_person_name());
			resReconsiderationLoanSearchVO.setModifier(loanReviewEntity.getModifier());
			resReconsiderationLoanSearchVO.setModifierDate(loanReviewEntity.getModified_time());
			resReconsiderationLoanSearchVO.setResult(ObjectUtils.toString(loanReviewEntity.getReview_result()));
			resReconsiderationLoanSearchVO.setReviewReason(loanReviewEntity.getReview_reason());
			resReconsiderationLoanSearchVO.setRemark(loanReviewEntity.getReview_remark());
			records.add(resReconsiderationLoanSearchVO);
		}
		// 忽略 复制的字段
		BeanUtils.copyProperties(pageBean, response, "records");
		response.setRecords(records);
		return response;
	}

}
