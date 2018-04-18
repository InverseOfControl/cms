package com.ymkj.cms.biz.service.finance.baoyin;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.service.finance.base.PassAuditLoan2Impl;
import com.ymkj.cms.biz.service.master.IBMSLoanExtEntityService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
/**
 * 包银放款审核  通过
 * @author YM10138
 *
 */
@Service
public class ByPassAuditLoan2Impl extends PassAuditLoan2Impl{
	@Autowired
	private IBMSLoanExtEntityService ibmsLoanExtEntityService;
	private static final Log log = LogFactory.getLog(ByPassAuditLoan2Impl.class);

	@Override
	public boolean execute(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		log.debug("*************************包银渠道放款审核通过前状态值判断开始*************************loanNo:"+reqLoanVo.getLoanNo());
		Map<String, Object> param = new HashMap<>();
		param.put("loanNo", reqLoanVo.getLoanNo());
		BMSLoanExt loanExtByLoanNo = ibmsLoanExtEntityService.findLoanExtByLoanNo(param);
		//合同签订通过
		if(EnumConstants.BaoyinAuditingState._004.getCode().equals(loanExtByLoanNo.getAuditingState())){
			return super.execute(reqLoanVo,res);
		//需要补件
		}else if(EnumConstants.BaoyinAuditingState._003.getCode().equals(loanExtByLoanNo.getAuditingState())){
			res.setRepCode("999999");
			String str = loanExtByLoanNo.getWindControlResult();
			res.setRepMsg(str);
			return false;
		//渠道审核未通过
		}else if(EnumConstants.BaoyinAuditingState._002.getCode().equals(loanExtByLoanNo.getAuditingState())){
			res.setRepCode("999999");
			res.setRepMsg("渠道审核未通过，请改签其他渠道！");
			return false;
		//渠道审核中
		}else if(EnumConstants.BaoyinAuditingState._000.getCode().equals(loanExtByLoanNo.getAuditingState())){
			res.setRepCode("999999");
			res.setRepMsg("包商银行未返回审核信息，请等待！");
			return false;
		}else{
			res.setRepCode("999999");
			res.setRepMsg("包商银行返回状态值不正确，请改签其他渠道！");
			return false;
		}
	}
}
