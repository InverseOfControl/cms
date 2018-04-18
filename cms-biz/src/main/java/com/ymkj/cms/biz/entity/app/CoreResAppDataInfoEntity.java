package com.ymkj.cms.biz.entity.app;

import java.util.List;

public class CoreResAppDataInfoEntity {
	 private List<AppDataInfoEntity> loanAppVoList;
	 private String userCode;
	 private  List<LoanVo> loans;
	 private String code;
	 private String msg;
	 private String message;
	 private Integer totalNo;

	public List<AppDataInfoEntity> getLoanAppVoList() {
		return loanAppVoList;
	}
	public void setLoanAppVoList(List<AppDataInfoEntity> loanAppVoList) {
		this.loanAppVoList = loanAppVoList;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Integer getTotalNo() {
		return totalNo;
	}
	public void setTotalNo(Integer totalNo) {
		this.totalNo = totalNo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public List<LoanVo> getLoans() {
		return loans;
	}
	public void setLoans(List<LoanVo> loans) {
		this.loans = loans;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

	 
	 
	/*private MsgEx msgEx;
	private String code;
public MsgEx getMsgEx() {
	return msgEx;
}
public void setMsgEx(MsgEx msgEx) {
	this.msgEx = msgEx;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
*/

}
class DataInfos{
	 private AppDataInfoEntity loanAppVoList;
	 private String userCode;
	 private String totalNo;
	public AppDataInfoEntity getLoanAppVoList() {
		return loanAppVoList;
	}
	public void setLoanAppVoList(AppDataInfoEntity loanAppVoList) {
		this.loanAppVoList = loanAppVoList;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getTotalNo() {
		return totalNo;
	}
	public void setTotalNo(String totalNo) {
		this.totalNo = totalNo;
	}
	 
}


class  MsgEx{
	private String respDesc;
	private String status;
	private DataInfos infos;
	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public DataInfos getInfos() {
		return infos;
	}
	public void setInfos(DataInfos infos) {
		this.infos = infos;
	}
	
}


