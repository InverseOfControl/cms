package com.ymkj.cms.biz.entity.audit.last;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.ymkj.base.core.biz.entity.BaseEntity;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSLoansAndVersionsVO;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.common.util.ValidataUtil;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;

public class ALastReassignmentUpdEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "000001,借款单号和锁标识集合不能为空")
	private List<ReqBMSLoansAndVersionsVO> list;//借款单号和锁标识集合
	
	
	@NotNull(message = "000001,审核人员code不能为空")
	private String auditPersonCode;//审核人员code
	
	@NotNull(message = "000001,操作人code不能为空")
	private String operatorCode;//操作人code
	
	
	@NotNull(message = "000001,操作人IP不能为空")
	private String operatorIP;//操作人IP
	
	
	public List<ReqBMSLoansAndVersionsVO> getList() {
		return list;
	}
	public void setList(List<ReqBMSLoansAndVersionsVO> list) {
		this.list = list;
	}
	public String getAuditPersonCode() {
		return auditPersonCode;
	}
	public void setAuditPersonCode(String auditPersonCode) {
		this.auditPersonCode = auditPersonCode;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getOperatorIP() {
		return operatorIP;
	}
	public void setOperatorIP(String operatorIP) {
		this.operatorIP = operatorIP;
	}
	
	public void check(){
		if (!ValidataUtil.isIP(this.operatorIP)) {
			throw new BizException(BizErrorCode.APPLY_VALUE_EOERR, new Object[]{"operatorIP"});
		}
		if(this.operatorCode.equals("")){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"operatorCode"});
		}
		if(this.auditPersonCode.equals("")){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"auditPersonCode"});
		}
		
		if(list == null || list.size() == 0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"list"});
		}else{
			for(ReqBMSLoansAndVersionsVO vo : list){
				if(StringUtils.isEmpty(vo.getLoanNo())){
					throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo"});
				}
				if (StringUtils.isEmpty(vo.getRtfNodeStatus())) {
					throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"rtfNodeStatus"});
				}
				if(vo.getVersion() == null){
					throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"version"});
				}
			}
		}
		
	}
}
