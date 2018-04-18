package com.ymkj.cms.biz.service.finance.lujs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumLuJSConstants;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.entity.sign.BMSLuJSInform;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.finance.base.BackLoan2Impl;
import com.ymkj.cms.biz.service.sign.ILuJSInformService;
import com.ymkj.cms.biz.service.sign.lujs.ifc.LUJSInterfaceService;


@Service(value="luJSBackLoan2Impl")
public class LuJSBackLoan2Impl extends BackLoan2Impl{
	
	@Autowired
	private LUJSInterfaceService lujsInterfaceService;
	@Autowired
	private ILuJSInformService iLuJSInformService;
	@Autowired
	private IBMSLoanChannelLockTargetDao  loanChannelLockTargetDao;
	@Override
	public boolean before(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		return super.before(reqLoanVo, res);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean execute(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		boolean executeFlag =true;
		Map resMap =new HashMap();
		// 查询陆金所状态
		resMap.put("loanNo", reqLoanVo.getLoanNo());
		BMSLuJSInform bmsLuJSInform = iLuJSInformService.getBy(resMap);
		//陆金所返回状态为上架成功或投资成功时 
		if(EnumLuJSConstants.SJCG.getCode().equals(bmsLuJSInform.getInformResult())||EnumLuJSConstants.FKCG.getCode().equals(bmsLuJSInform.getInformResult())){
			setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "放款申请中，暂时不可退回，若需退回，请业务人员线下处理"), res);
			return false;
		}
		Map paramMap =new HashMap();
		//金所返回状态为确认合同额度不够时的人工取消时，可退回且退回时不需要调撤销接口
		if(EnumLuJSConstants.QREDQX.getCode().equals(bmsLuJSInform.getInformResult())){
			executeFlag =super.execute(reqLoanVo, res);	
			//執行完后锁定不可再签陆金所
			if(executeFlag){
				paramMap.put("loanBaseId", reqLoanVo.getId());
				paramMap.put("channelCode", reqLoanVo.getChannelCd());
				BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
				if(lockTargetEntity != null){
				    lockTargetEntity.setLockTarget("N");
				    lockTargetEntity.setModified(reqLoanVo.getServiceCode());
				    loanChannelLockTargetDao.update(lockTargetEntity);
	          }else {
	  			//锁定数据
	  			BMSLoanChannelLockTargetEntity saveEntity = new BMSLoanChannelLockTargetEntity();
	  			saveEntity.setLoanBaseId(reqLoanVo.getId());
	  			saveEntity.setLoanNo(reqLoanVo.getLoanNo());
	  			saveEntity.setChannelCode(reqLoanVo.getChannelCd());
	  			saveEntity.setLockTarget("N");
	  			saveEntity.setCreator(reqLoanVo.getServiceCode());
	  			saveEntity.setModified(reqLoanVo.getServiceCode());
	  			long udpateNum = loanChannelLockTargetDao.insert(saveEntity);
	  			if(udpateNum !=1){
	  				throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "插入错误");
	  			}
	  		}
			}
		}else{
			setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "放款申请中，暂时不可退回，若需退回，请业务人员线下处理"), res);
			return false;
		}
		
		return executeFlag;
	}
	@Override
	public boolean after(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		return super.after(reqLoanVo, res);
	}
	
}
