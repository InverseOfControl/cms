package com.ymkj.cms.biz.facade.audit;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.audit.IContractSignExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqContractSignSearchVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqLoanReassignmentVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSContractSignSearchVO;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.audit.BMSContractSignSearchEntity;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.LoanBaseService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;


/**
 * 服务提供者demo:
 * 1. 请求信息验证
 * 2. 调用业务层实现业务操作
 * 3. 封装 响应response
 * 4. 对于必须捕获的异常采用 参考 listPage 的用法
 * @author user
 *		
 */
@Service
public class ContractSignExecuter implements IContractSignExecuter {
	
	 @Autowired
	 private LoanBaseService loanBaseService;
	 @Autowired
	 private IBMSSysLogService sysLogService;
	 @Autowired
	 private IBMSSysLoanLogService sysLoanLogService;
	 
	 

	@Override
	public Response reassignment(ReqLoanReassignmentVO  reqLoanReassignmentVO){
		Response  response = new Response();
		//参数校验
		if(org.apache.commons.lang.StringUtils.isBlank(	reqLoanReassignmentVO.getLoanNo())){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"loanNo"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqLoanReassignmentVO.getIp())){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"ip"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqLoanReassignmentVO.getOperator())){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"operator"});
		}
		if(	reqLoanReassignmentVO.getOperatorId()==null){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"operatorId"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqLoanReassignmentVO.getOperatorCode())){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"operatorCode"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqLoanReassignmentVO.getReassignmentOperator())){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"reassignmentOperator"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqLoanReassignmentVO.getReassignmentOperatorCode())){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"reassignmentOperator"});
		}
		
		LoanBaseEntity loanBaseEntity=new LoanBaseEntity();
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("loanNo", reqLoanReassignmentVO.getLoanNo());
		LoanBaseEntity	loanBaseEntitySelect =	loanBaseService.getBy(map);
		loanBaseEntity.setId(loanBaseEntitySelect.getId());
		loanBaseEntity.setModifierId(reqLoanReassignmentVO.getOperatorId());
//		loanBaseEntity.setModifier(reqLoanReassignmentVO.getOperator());
		loanBaseEntity.setModifier(reqLoanReassignmentVO.getOperatorCode());
		loanBaseEntity.setModifiedTime(new Date());
		loanBaseEntity.setServiceCode(reqLoanReassignmentVO.getReassignmentOperatorCode());
		loanBaseService.saveOrUpdate(loanBaseEntity);
		/*//记系统日志
		BMSSysLog sysLog = new BMSSysLog();
		sysLog.setFirstLevelDir(EnumConstants.OptionModule.APPLY_TASK.getValue());
		sysLog.setTwoLevelDir(EnumConstants.OptionModule.CONTRACT_SIGN.getValue());
		sysLog.setOperationType(EnumConstants.OptionType.REASSIGNMENT.getValue());
		sysLog.setOperatorCode(reqLoanReassignmentVO.getOperatorCode());
		sysLog.setOperator(reqLoanReassignmentVO.getOperator());            
		sysLog.setOperationTime(new  Date()); 
		sysLog.setIp(reqLoanReassignmentVO.getIp());
		sysLog.setRemark("借款编号："+reqEntryCancelVO.getLoanNo());
		sysLogService.saveOrUpdate(sysLog);*/
		//记借款日志
		BMSSysLoanLog sysLoanLog = new BMSSysLoanLog();
		 
		sysLoanLog.setOperationModule(EnumConstants.OptionModule.CONTRACT_SIGN.getValue());
		sysLoanLog.setOperationType(EnumConstants.OptionType.REASSIGNMENT.getValue());
		sysLoanLog.setLoanBaseId(loanBaseEntitySelect.getId());
		//根据去查code 查客服名称 
		String  name="";//loanBaseEntitySelect.getServiceCode;
		sysLoanLog.setOperator(reqLoanReassignmentVO.getOperator()); 
		sysLoanLog.setOperatorCode(reqLoanReassignmentVO.getOperatorCode()); 
		sysLoanLog.setOperationTime(new Date()); 
		sysLoanLog.setRemark("由"+name+"改派成"+reqLoanReassignmentVO.getReassignmentOperator()); 
		sysLoanLogService.saveOrUpdate(sysLoanLog);
		return response;
	}



	@Override
	public PageResponse<ResBMSContractSignSearchVO> listPage(
			ReqContractSignSearchVO contractSignSearchVO) {
		  PageResponse<ResBMSContractSignSearchVO> response = new PageResponse<ResBMSContractSignSearchVO>();
		// 参数校验
				if(contractSignSearchVO.getPageNum()==0 || contractSignSearchVO.getPageSize()==0){
					throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"pageNum | pageSize"});
				}
				if(StringUtils.isEmpty(contractSignSearchVO.getAgencyOrComplete())){
					throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"agencyOrComplete"});
				}
				
				try {
					// 构造请求参数
					PageParam pageParam = new PageParam(contractSignSearchVO.getPageNum(), contractSignSearchVO.getPageSize());
					Map<String, Object> paramMap = BeanKit.bean2map(contractSignSearchVO);
					if(contractSignSearchVO.getAgencyOrComplete().equals(EnumConstants.agencyOrComplete.agency.getValue())){
						paramMap.put("status", "签约未完成");
						paramMap.put("rtfStatus", "签约未完成");
					}else{
						paramMap.put("status", "签约未完成");
						paramMap.put("rtfStatus", "签约未完成");
						 
					}
					// 调用业务逻辑,得到list集合
					PageBean<BMSContractSignSearchEntity> pageBean = loanBaseService.listPageContractSignSearch(pageParam, paramMap);
					// 构造响应参数
					List<ResBMSContractSignSearchVO> records = new ArrayList<ResBMSContractSignSearchVO>();
					List<BMSContractSignSearchEntity> contractSignSearchEntitys = pageBean.getRecords();
					for (BMSContractSignSearchEntity contractSignSearchEntity : contractSignSearchEntitys) {
						ResBMSContractSignSearchVO resBMSContractSignSearchVO = new ResBMSContractSignSearchVO();
						BeanUtils.copyProperties(contractSignSearchEntity, resBMSContractSignSearchVO);
						records.add(resBMSContractSignSearchVO);
					}
					// 忽略 复制的字段
					BeanUtils.copyProperties(pageBean, response, "records");
					response.setRecords(records);

				} catch (Exception e) { 
					// 抛出自定义异常
					throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
				}
				return response;
	}

	 
}
