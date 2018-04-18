package com.ymkj.cms.biz.service.finance.lujs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.dao.apply.LoanExtDao;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLuJSInform;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.finance.base.PassAuditLoan2Impl;
import com.ymkj.cms.biz.service.sign.ILuJSInformService;
import com.ymkj.cms.biz.service.sign.lujs.ifc.LUJSInterfaceService;

@Service
public class LuJSPassAuditLoan2Impl extends PassAuditLoan2Impl{
	
	@Autowired
	private LUJSInterfaceService lujsInterfaceService;
	
	@Autowired
	private ILoanBaseEntityDao  loanBaseEntityDao;
	
	
	@Autowired
	private ILuJSInformService iLuJSInformService;
	
	@Override
	public boolean before(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		
		return super.before(reqLoanVo, res);
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean execute(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		boolean passFlag=false;
		Map resMap =new HashMap();
		// 查询陆金所状态
		resMap.put("loanNo", reqLoanVo.getLoanNo());
		BMSLuJSInform bmsLuJSInform = iLuJSInformService.getBy(resMap);
		// 签约信息查询 历史数据
		resMap.put("loanBaseId", reqLoanVo.getId());
		BMSLoanBaseEntity bmsLoanBaseEntity = loanBaseEntityDao.findSignInfo(resMap);	
		if(bmsLoanBaseEntity == null){
		  setError(new BizException(CoreErrorCode.DB_RESULT_ISNULL, "未查询到借款信息"), res);
		}
			//陆金所信审通过
		  if("005".equals(bmsLuJSInform.getInformResult())){
			Map paramMap =new HashMap();
			paramMap.put("loanNo", bmsLoanBaseEntity.getLoanNo());	//借款编号 
			paramMap.put("apsApplyNo",bmsLoanBaseEntity.getLujsApplyNo());	//进件流水号
			paramMap.put("applyNo", "ZDJR_"+bmsLoanBaseEntity.getLoanNo());	//申请单号
			paramMap.put("lufaxLoanReqId",bmsLoanBaseEntity.getLujsLoanReqId()); //借款申请ID
			//调用合同确认接口
			boolean confirmFlag=lujsInterfaceService.lujsConfirmContract(paramMap,res);
			//合同确认成功
			if(confirmFlag){
				BmsLogger.info("推送陆金所成功");
				passFlag=super.execute(reqLoanVo, res);
			}
			//补充材料
			}else if("013".equals(bmsLuJSInform.getInformResult())){
				res.setRepCode("999999");                //loanExtByLoanNo.getWindControlResult();
				res.setRepMsg(bmsLuJSInform.getInformDesc()+",请补充！"); //"缺少"+str.substring(0,str.indexOf("材料缺失，请补充！"))+"材料，请补充！");
				return false;
			//申请进件额度不够
			}else if("00201".equals(bmsLuJSInform.getInformResult())){
				res.setRepCode("999999");
				res.setRepMsg("申请进件额度不够，陆金所人工取消，请改签其他合同来源！");
				return false;
			//未通过
			}else if("00401".equals(bmsLuJSInform.getInformResult())){
				res.setRepCode("999999");
				res.setRepMsg("陆金所审核未通过，请改签其他合同来源！");
				return false;
			}else{
				res.setRepCode("999999");
				res.setRepMsg("陆金所未返回审核信息，请等待！");
				return false;
			}
		return passFlag;
	}

	@Override
	public boolean after(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {

		return  super.after(reqLoanVo, res);
	}
	

}
