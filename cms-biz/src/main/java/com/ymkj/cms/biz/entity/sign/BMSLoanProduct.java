package com.ymkj.cms.biz.entity.sign;

import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.entity.master.BMSProductBaseEntity;

import java.math.BigDecimal;

public class BMSLoanProduct extends BMSProductBaseEntity{
	

	   /**
	 * 
	 */
	private static final long serialVersionUID = 3264971702232803762L;
	
	   
	   private Long id;
	   private String loanNo;
	   private Long loanBaseId;         //借款ID
	   private String contractSource ;  	//渠道
	   private String productCd   ;       	//
	   private BigDecimal applyLmt  ;         // '申请额度',
	   private Integer applyTerm  ;         // '申请期限',
	   private BigDecimal contractLmt ;       // '合同金额',
	   private Integer contractTrem;        // '合同期限',
	   private String bankPhone  ;          //银行预留手机号
	   private String applyBankCardNo ;     //银行卡账号
	   private String applyBankBranch ;   //开户行name
	   private Integer applyBankBranchId;  //开户行id
	   private String applyBankName   ;  //所属银行name
	   private Integer applyBankNameId ;  //所属银行id
	   private Long contractType ;  //合同类型（0：纸质版，1电子版）默认值0
	   private String contractNum;     //合同编号
	  

	
	public BMSLoanProduct() {
		super();
	}
	
	public BMSLoanProduct(ReqLoanContractSignVo reqLoanContractSignVo) {
		this.setContractLmt(reqLoanContractSignVo.getContractLmt());
		this.setContractTrem(reqLoanContractSignVo.getContractTrem());
		this.setContractSource(reqLoanContractSignVo.getChannelCd());
		
		
		this.setBankPhone(reqLoanContractSignVo.getBankPhone());
		this.setApplyBankBranch(reqLoanContractSignVo.getApplyBankBranch());
		this.setApplyBankBranchId(reqLoanContractSignVo.getApplyBankBranchId());
		this.setApplyBankCardNo(reqLoanContractSignVo.getApplyBankCardNo());
		this.setApplyBankName(reqLoanContractSignVo.getApplyBankName());
		this.setApplyBankNameId(reqLoanContractSignVo.getApplyBankNameId());
		this.setLoanBaseId(reqLoanContractSignVo.getId());
		this.setContractType(Long.valueOf(reqLoanContractSignVo.getContractTypeCode()==null?"0":reqLoanContractSignVo.getContractTypeCode()));
	}
	public String getBankPhone() {
		return bankPhone;
	}
	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
	}
	public String getApplyBankCardNo() {
		return applyBankCardNo;
	}
	public void setApplyBankCardNo(String applyBankCardNo) {
		this.applyBankCardNo = applyBankCardNo;
	}
	public String getApplyBankBranch() {
		return applyBankBranch;
	}
	public void setApplyBankBranch(String applyBankBranch) {
		this.applyBankBranch = applyBankBranch;
	}

	public String getApplyBankName() {
		return applyBankName;
	}
	public void setApplyBankName(String applyBankName) {
		this.applyBankName = applyBankName;
	}

	public String getContractSource() {
		return contractSource;
	}
	public void setContractSource(String contractSource) {
		this.contractSource = contractSource;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

	public BigDecimal getApplyLmt() {
		return applyLmt;
	}
	public void setApplyLmt(BigDecimal applyLmt) {
		this.applyLmt = applyLmt;
	}

	public BigDecimal getContractLmt() {
		return contractLmt;
	}
	public void setContractLmt(BigDecimal contractLmt) {
		this.contractLmt = contractLmt;
	}
	public Integer getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(Integer applyTerm) {
		this.applyTerm = applyTerm;
	}
	public Integer getContractTrem() {
		return contractTrem;
	}
	public void setContractTrem(Integer contractTrem) {
		this.contractTrem = contractTrem;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public Integer getApplyBankBranchId() {
		return applyBankBranchId;
	}
	public void setApplyBankBranchId(Integer applyBankBranchId) {
		this.applyBankBranchId = applyBankBranchId;
	}
	public Integer getApplyBankNameId() {
		return applyBankNameId;
	}
	public void setApplyBankNameId(Integer applyBankNameId) {
		this.applyBankNameId = applyBankNameId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getLoanBaseId() {
		return loanBaseId;
	}

	public void setLoanBaseId(Long loanBaseId) {
		this.loanBaseId = loanBaseId;
	}

	public Long getContractType() {
		return contractType;
	}

	public void setContractType(Long contractType) {
		this.contractType = contractType;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	


}
