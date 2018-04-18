package com.ymkj.cms.biz.entity.audit.first;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.ymkj.base.core.biz.entity.BaseEntity;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.common.util.BmsVerifyHelper;
import com.ymkj.cms.biz.common.util.ParametersType;
import com.ymkj.cms.biz.common.util.ValidataUtil;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;

public class AfirstBMProductUpdEntity extends BaseEntity  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message = "000001,借款编号不能为空")
	private String loanNo;//	借款编号
	
	@NotNull(message = "000001,有无信用记录不能为空")
	private String ifCreditRecode;//	有无信用记录
	
//	@NotNull(message = "000001,收入证明金额不能为空")
	private Double amoutInconme;//	收入证明金额
	
	@NotNull(message = "000001,审批额度不能为空")
	private Double accLmt;//	审批额度
	
	@NotNull(message = "000001,产品编码不能为空")
	private String productCd;//	产品编码
	
	@NotNull(message = "000001,审批期限不能为空")
	private Integer accTerm;//	审批期限
	
	@NotNull(message = "000001,审批日期不能为空")
	private Date accDate;//	审批日期
	
	@NotNull(message = "000001,版本号不能为空")
	private Long version;//	版本号
	
	@NotNull(message = "000001,操作人工号不能为空")
	private String operatorCode;//	操作人工号
	
	@NotNull(message = "000001,操作人IP不能为空")
	private String operatorIP;//	操作人IP
	
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getIfCreditRecode() {
		return ifCreditRecode;
	}
	public void setIfCreditRecode(String ifCreditRecode) {
		this.ifCreditRecode = ifCreditRecode;
	}
	public Double getAmoutInconme() {
		return amoutInconme;
	}
	public void setAmoutInconme(Double amoutInconme) {
		this.amoutInconme = amoutInconme;
	}
	public Double getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(Double accLmt) {
		this.accLmt = accLmt;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public Integer getAccTerm() {
		return accTerm;
	}
	public void setAccTerm(Integer accTerm) {
		this.accTerm = accTerm;
	}
	public Date getAccDate() {
		return accDate;
	}
	public void setAccDate(Date accDate) {
		this.accDate = accDate;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
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
	
	public void checkValue(){
		if(this.loanNo.equals("")){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo"});
		}
		
		if(this.productCd.equals("")){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"productCd"});
		}
		
		if (!ValidataUtil.isIP(this.operatorIP)) {
			throw new BizException(BizErrorCode.APPLY_VALUE_EOERR, new Object[]{"operatorIP"});
		}
		
		BmsVerifyHelper.verifyContainsKeyName("有无信用记录 ifCreditRecode", this.ifCreditRecode, ParametersType.IfCreditRecode.class);
	}

}
