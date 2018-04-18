package com.ymkj.cms.biz.service.sign.lujs.impl;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.dao.apply.APPPersonInfoDao;
import com.ymkj.cms.biz.dao.sign.IBMSCreditRuleInformDao;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
import com.ymkj.cms.biz.entity.master.BMSChannel;
import com.ymkj.cms.biz.entity.master.BMSTmParameter;
import com.ymkj.cms.biz.entity.sign.BMSCreditRuleInformEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanApp;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSChannelService;
import com.ymkj.cms.biz.service.master.IBMSTmParameterService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;
import com.ymkj.cms.biz.service.sign.base.SaveLoanContractSignImpl;
import com.ymkj.cms.biz.service.sign.lujs.ifc.LUJSInterfaceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 保存合同签约信息
 * 
 * @author YM10138
 *
 */
@Service
public class LuJSSaveLoanContractSignImpl extends SaveLoanContractSignImpl {

	@Autowired
	private ILoanContractSignService loanContractSignService;
	
	@Autowired
	private LUJSInterfaceService lujsInterfaceService;
	
	@Autowired
	private IBMSChannelService channelService;
	
	@Autowired
	private IBMSLoanChannelLockTargetDao loanChannelLockTargetDao;
	
	@Autowired
	private APPPersonInfoDao appPersonInfoDao;
	
	@Autowired
	private ILoanBaseEntityDao loanBaseEntityDao;
	
	@Autowired
	private IBMSTmParameterService tmParameterService;
	
	@Autowired
	private IBMSCreditRuleInformDao creditRuleInformDao;
	
	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		if(super.before(reqLoanContractSignVo, res)){
			return lujsInnerCheck(reqLoanContractSignVo, res);
		}	
		return false;
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		//校验之前是否被陆金所拒绝过，若发生过拒绝（审核不通过），则提示“渠道审核未通过，请改签其他渠道！”
		// 签约信息查询 历史数据
		BMSLoanBaseEntity loanInfo = loanContractSignService.findSignInfo(reqLoanContractSignVo);
		//推送数据判断  标识已被陆金所拒绝过渠道
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanBaseId", reqLoanContractSignVo.getId());
		paramMap.put("channelCode", reqLoanContractSignVo.getChannelCd());
		BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
		
		if(lockTargetEntity != null && "N".equals(lockTargetEntity.getLockTarget())){
			setError(new BizException(BizErrorCode.REQUEST_PARAM_ERROR, "渠道审核未通过，请改签其他渠道！"), res);
			return false;
		}
		
		Map<String, Object> httpParamMap = new HashMap<String, Object>();
		httpParamMap.put("requestMoney",reqLoanContractSignVo.getContractLmt());
		httpParamMap.put("prodName",loanInfo.getProductName());
		httpParamMap.put("term",reqLoanContractSignVo.getContractTrem());
		
		ReqBMSChannelVO reqChannelVO = new ReqBMSChannelVO();
		reqChannelVO.setCode(reqLoanContractSignVo.getChannelCd());
		BMSChannel channel = channelService.findOne(reqChannelVO);
		if(channel == null){
			setError(new BizException(BizErrorCode.REQUEST_PARAM_ERROR,"对应渠道code对应数据不存在"), res);
			return false;
		}
		httpParamMap.put("contractSource",channel.getName());
		
		//用核心获取合同金额接口,校验
		if(lujsInterfaceService.queryContractMoney(httpParamMap, res)){
			return super.execute(reqLoanContractSignVo, res);
		}
		return false;
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.after(reqLoanContractSignVo, res);
	}
	
	public boolean lujsInnerCheck(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res){
		//代码开关限制
		List<BMSTmParameter> lujsApplyCreditReportRuleList = tmParameterService.findByCode("LUJS_APPLY_CREDIT_REPORT");
		
		if(lujsApplyCreditReportRuleList != null && !lujsApplyCreditReportRuleList.isEmpty()){
			BMSTmParameter lujsApplyCreditReportRule = lujsApplyCreditReportRuleList.get(0);
			if("1".equals(lujsApplyCreditReportRule.getParameterValue())){
				
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("loanBaseId", reqLoanContractSignVo.getId());
				
				// 基本信息
				BMSLoanApp tmAppMain = loanBaseEntityDao.findLoanEntity(param);
				if(tmAppMain == null){
					setError(new BizException(BizErrorCode.DB_RESULT_ISNULL, "借款主表信息为空"), res);
					return false;
				}
				// 申请人信息
				APPPersonInfoEntity tmAppPersonInfo = appPersonInfoDao.findPersonByLoanNo(param);
				if(tmAppPersonInfo == null){
					setError(new BizException(BizErrorCode.DB_RESULT_ISNULL, "借款对应申请人信息为空"), res);
					return false;
				}
	//			以下情况也不允许签约陆金所：①、②要同时满足
	//			①　“有无信用记录”字段：取值为“无”
	//			②　央行征信报告解读为空（不考虑征信报告的图片是否已上传）
				//有无信用记录 0无  1有
				if(tmAppMain.getIfCreditRecord() == null || "0".equals(tmAppMain.getIfCreditRecord())){
					//央行征信报告为空
					if(tmAppPersonInfo.getReportId() == null || tmAppPersonInfo.getReportId() == 0L){
						
						BMSCreditRuleInformEntity entity = new BMSCreditRuleInformEntity();
						entity.setLoanBaseId(tmAppPersonInfo.getLoanBaseId());
						entity.setLoanNo(tmAppPersonInfo.getLoanNo());
						entity.setChannelCode(reqLoanContractSignVo.getChannelCd());
						entity.setResult("false");
						entity.setCheckRule("LJS");
						entity.setMessage("“有无信用记录”字段：取值为“无”,且央行征信报告解读为空");
						entity.setCreator(reqLoanContractSignVo.getServiceCode());
						
						creditRuleInformDao.insert(entity);
						
						setError(new BizException(BizErrorCode.REQUEST_PARAM_ISNULL, "该客户不符合陆金所准入标准，请选择其他签约渠道"), res);
						return false;
					}
				}
				
				Map<String, Object> paramMap = new HashMap<String, Object>();
				if(tmAppPersonInfo.getReportId() == null || tmAppPersonInfo.getReportId() == 0L){
					return true;
				}
				paramMap.put("reportId", tmAppPersonInfo.getReportId());
				paramMap.put("loanBaseId", tmAppPersonInfo.getLoanBaseId());
				paramMap.put("loanNo", tmAppPersonInfo.getLoanNo());
				paramMap.put("channelCode", reqLoanContractSignVo.getChannelCd());
				paramMap.put("serviceCode", reqLoanContractSignVo.getServiceCode());
				paramMap.put("serviceName", reqLoanContractSignVo.getServiceName());

				//陆金所签约限制
				return lujsInterfaceService.lujsApplyCreditReportRule(paramMap, res);
			}
		}
		return true;
	}
}
