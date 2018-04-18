package com.ymkj.cms.biz.service.finance.lujs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.dao.apply.LoanExtDao;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.entity.sign.BMSLuJSInform;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.finance.base.BackAudit2Impl;
import com.ymkj.cms.biz.service.sign.ILuJSInformService;
import com.ymkj.cms.biz.service.sign.lujs.ifc.LUJSInterfaceService;

@Service
public class LuJSBackLoanAudit2Impl extends BackAudit2Impl{
	@Autowired
	private LUJSInterfaceService lujsInterfaceService;
	@Autowired
	private ILuJSInformService iLuJSInformService;
	@Autowired
	private ILoanBaseEntityDao  loanBaseEntityDao;
	@Autowired
	private IBMSLoanChannelLockTargetDao  loanChannelLockTargetDao;
	@Override
	public boolean before(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		
		return super.before(reqLoanVo, res);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean execute(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		boolean executeFlag=true;
		Map paramMap =new HashMap();
		//查询借款最新的通知记录
		paramMap.put("loanNo", reqLoanVo.getLoanNo());
		BMSLuJSInform bmsLuJSInform = iLuJSInformService.getBy(paramMap);
		//陆金所信审已返回信息
		if("005".equals(bmsLuJSInform.getInformResult())||"013".equals(bmsLuJSInform.getInformResult())
				||"00201".equals(bmsLuJSInform.getInformResult()) ||"00401".equals(bmsLuJSInform.getInformResult())){
			//查询借款签约信息
			BMSLoanBaseEntity  baseEntity =loanBaseEntityDao.findSignInfo(paramMap);
			paramMap.put("aps_apply_no",baseEntity.getLujsApplyNo()); //baseEntity.getLujsApplyNo());		//进件流水号LujsApplyNo
			paramMap.put("apply_no", "ZDJR_"+reqLoanVo.getLoanNo());  			//申请单号
			paramMap.put("notify_code", "2");  	//通知状态1：订单成功；2：订单终止
			paramMap.put("loanBaseId", reqLoanVo.getId());
	        paramMap.put("channelCode", reqLoanVo.getChannelCd());
	        //通知陆金所撤销接口(005通过的借款需要调用)
	        if("005".equals(bmsLuJSInform.getInformResult())){
	        	executeFlag=lujsInterfaceService.requestNotifyLujs(paramMap,res);
	        }
			if(executeFlag){
				executeFlag =super.execute(reqLoanVo, res);	
			}else{
				setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "通知陆金所借款取消失败，请重试"), res);
				return executeFlag;
			}
			if(executeFlag){
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
		//陆金所信审未返回信息
		}else{
			res.setRepCode("999999");
			res.setRepMsg("陆金所未返回审核信息，请等待！");
			return false;
		}
		return executeFlag;
	}
	@Override
	public boolean after(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {

		
		
		return super.after(reqLoanVo, res);
	}

}
