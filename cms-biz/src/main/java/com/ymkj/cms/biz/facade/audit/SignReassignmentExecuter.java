package com.ymkj.cms.biz.facade.audit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.audit.ISignReassignmentExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqSignReassignVO;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqIntegratedSearchVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseVO;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.master.BMSSysLogRecordEntity;
import com.ymkj.cms.biz.service.audit.ISignReassignmentService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;

@Service
public class SignReassignmentExecuter implements ISignReassignmentExecuter {

	@Autowired
	private ISignReassignmentService signReassignmentService;

	@Autowired
	private IEmployeeExecuter iEmployeeExecuter;

	@Autowired
	private IBMSSysLogService sysLogService;

	@Autowired
	private IBMSSysLoanLogService sysLoanLogService;

	@Override
	public Response<Object> signReassign(ReqSignReassignVO reqSignReassignVO) {

		//日志记录
		//sysLogService.recordSysLog(reqSignReassignVO,"签约分派|签约分派|签约分派|修改","SignReassignmentExecuter\\signReassign","定时任务");

		List<Map<String,Object>> resultList = new ArrayList<>();
		//借款系统接口响应
		Response<Object> response = new Response<Object>();
		//权限系统接口响应
		Response<List<ResEmployeeVO>> employeeResonse = null; 
		//从权限系统根据机构Id获取机构下的所有客服集合
		List<ResEmployeeVO> resEmployeeList = null;
		//借款系统中已签约分派的客服集合
		List<Map<String,Object>> signServiceList = null;

		//需要改派的借款列表
		List<LoanBaseEntity> loanBaseEntityList = signReassignmentService.getSignReassignList();

		if(CollectionUtils.isNotEmpty(loanBaseEntityList)){
			for (LoanBaseEntity loanBaseEntity : loanBaseEntityList) {

				String applyInputFlag = ObjectUtils.toString(loanBaseEntity.getApplyInputFlag());
				String owningBranchId = ObjectUtils.toString(loanBaseEntity.getOwningBranchId());
				final String loanNo =  ObjectUtils.toString(loanBaseEntity.getLoanNo());

				LoanBaseEntity loanBaseTemp = new LoanBaseEntity();

				loanBaseTemp.setLoanNo(loanNo);
				loanBaseTemp.setContractBranchId(Long.valueOf(owningBranchId));

				String signName = "";

				if(applyInputFlag.equals("applyInput")){//普通进件
					String signCode = ObjectUtils.toString(loanBaseEntity.getSignCode());
					
					if(StringUtils.isNotBlank(signCode)){//如果已经改派过，直接取改派的人，不再进行分派
						loanBaseTemp.setSignCode(loanBaseEntity.getSignCode());
						loanBaseTemp.setSignName(loanBaseEntity.getSignName());
						signName = loanBaseEntity.getSignName();
					}else{
						loanBaseTemp.setSignCode(loanBaseEntity.getServiceCode());
						loanBaseTemp.setSignName(loanBaseEntity.getServiceName());
						signName = loanBaseEntity.getServiceName();
					}

					int result = signReassignmentService.signReassign(loanBaseTemp);
					if(result > 0){
						resultList.add(new HashMap<String,Object>(){{put("loanNo", loanNo);put("isSuccess", "true");put("applyInputFlag", "applyInput");}});
					}else{
						resultList.add(new HashMap<String,Object>(){{put("loanNo", loanNo);put("isSuccess", "false");put("applyInputFlag", "applyInput");}});
					}
				}else {//直通车进件
					ReqParamVO reqParamVO = new ReqParamVO();
					//直通车进件，签约门店必选，然后在已选的门店中进行自动分派
					Long contractBranchId = loanBaseEntity.getContractBranchId();
					loanBaseTemp.setContractBranchId(contractBranchId);
					
					reqParamVO.setSysCode(EnumConstants.BMS_SYSCODE);
					reqParamVO.setOrgId(Long.valueOf(contractBranchId));
					reqParamVO.setRoleCodes(new ArrayList<String>(){{add("customerService"); }});
					reqParamVO.setStatus(0);// 可用
					reqParamVO.setInActive("t");
					employeeResonse = iEmployeeExecuter.findByDeptAndRole(reqParamVO);
					resEmployeeList = employeeResonse.getData();

					String signCode = ObjectUtils.toString(loanBaseEntity.getSignCode());
					if(StringUtils.isNotBlank(signCode)){//如果已经改派过，直接取改派的人，不再进行分派
						loanBaseTemp.setSignCode(loanBaseEntity.getSignCode());
						loanBaseTemp.setSignName(loanBaseEntity.getSignName());
						signName = loanBaseEntity.getSignName();
					}else{//没有改派过，进行分派
						if(CollectionUtils.isNotEmpty(resEmployeeList)){
							Map<String,Object> paramMap = new HashMap<String,Object>();
							paramMap.put("contractBranchId", contractBranchId);
							
							List<String> signCodeList = new ArrayList<String>();
							for (ResEmployeeVO vo : resEmployeeList) {
								signCodeList.add(vo.getUsercode());
							}
							
							paramMap.put("signCodeList", signCodeList);
							//借款系统中已签约分派的客服集合
							signServiceList = signReassignmentService.getSignCodeListBy(paramMap);

							if(CollectionUtils.isEmpty(signServiceList)){//借款系统中已签约分派的客服列表为空，从所有客服中随便选一个即可

								ResEmployeeVO resEmployeeVO = resEmployeeList.get(0);
								loanBaseTemp.setSignCode(resEmployeeVO.getUsercode());
								loanBaseTemp.setSignName(resEmployeeVO.getName());

								signName = resEmployeeVO.getName();

							}else if(signServiceList.size() < resEmployeeList.size()){//已经分派的客服总数小于机构下的客服总数,从所有客服中除去已经分派的客服，再剩余的客服中分派

								//从所有客服中排出已经签约改派的客服,剩下的就是没有签约分派的客服
								Iterator<ResEmployeeVO> iteratorTmp = resEmployeeList.iterator();

								while(iteratorTmp.hasNext()){
									String userCode = iteratorTmp.next().getUsercode();
									for (Map<String, Object> map : signServiceList) {
										if(userCode.equals(ObjectUtils.toString(map.get("sign_code")))){
											iteratorTmp.remove();
											break;
										}
									}
								}

								//从没有签约分派的客服中随机选一个
								ResEmployeeVO resEmployeeVO = resEmployeeList.get(0);
								loanBaseTemp.setSignCode(resEmployeeVO.getUsercode());
								loanBaseTemp.setSignName(resEmployeeVO.getName());

								signName = resEmployeeVO.getName();

							}else if(signServiceList.size() == resEmployeeList.size()){//所有客服都已分派，此时要“平均分派”

								this.sort(signServiceList);

								Map<String,Object> signServiceMap = signServiceList.get(0);
								String sign_code = ObjectUtils.toString(signServiceMap.get("sign_code"));

								for (ResEmployeeVO resEmployeeVO : resEmployeeList) {
									if(sign_code.equals(resEmployeeVO.getUsercode())){
										loanBaseTemp.setSignCode(resEmployeeVO.getUsercode());
										loanBaseTemp.setSignName(resEmployeeVO.getName());

										signName = resEmployeeVO.getName();
										break;
									}
								}
							}
						}
					}
					//改派
					int result = signReassignmentService.signReassign(loanBaseTemp);

					if(result > 0){
						resultList.add(new HashMap<String,Object>(){{put("loanNo", loanNo);put("isSuccess", "true");put("applyInputFlag", "applyInput");}});
					}else{
						resultList.add(new HashMap<String,Object>(){{put("loanNo", loanNo);put("isSuccess", "false");put("applyInputFlag", "directApplyInput");}});
					}
					
				}

				//记录借款日志
				if(StringUtils.isNotBlank(signName)){
					insertLoanLog(loanNo,signName);
				}
			}
			response.setData(resultList);

		}else{
			response = new Response<Object>(CoreErrorCode.DEFAULT.getErrorCode(),
					CoreErrorCode.DEFAULT.getDefaultMessage());
		}

		return response;
	}

	/**
	 * <p>Description:list升序排序</p>
	 * @uthor YM10159
	 * @date 2017年3月23日 下午1:50:16
	 * @param @param list 要排序的集合
	 */
	public void sort(List<Map<String, Object>> list) {

		Collections.sort(list, new Comparator<Map<String,Object>>() {

			@Override
			public int compare(Map<String,Object> o1, Map<String,Object> o2) {
				Integer a = Integer.valueOf(o1.get("count").toString());
				Integer b = Integer.valueOf(o2.get("count").toString());

				return a.compareTo(b);
			}
		});
	}

	public void insertLoanLog(String loanNo, String signName){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		BMSSysLoanLog sysLoanLog = new BMSSysLoanLog();
		try{
			sysLoanLog.setLoanNo(loanNo);
			sysLoanLog.setRtfState(EnumConstants.RtfState.HTQY.getValue());
			sysLoanLog.setRtfNodeState(EnumConstants.RtfNodeState.XSZSPASS.getValue());
			sysLoanLog.setStatus(EnumConstants.LoanStatus.PASS.getValue());
			sysLoanLog.setOperator("系统");
			sysLoanLog.setOperatorCode("系统");
			sysLoanLog.setOperationTime(df.parse(df.format(new Date())));
			sysLoanLog.setRemark("分派至"+signName);
			sysLoanLog.setOperationType(EnumConstants.OptionType.SIGN_ASSIGNMENT.getValue());
			sysLoanLogService.saveOrUpdate(sysLoanLog);	
		}catch(Exception e){}
	}
}
