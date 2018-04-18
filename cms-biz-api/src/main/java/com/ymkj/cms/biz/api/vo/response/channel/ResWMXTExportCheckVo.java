package com.ymkj.cms.biz.api.vo.response.channel;

import java.io.Serializable;

public class ResWMXTExportCheckVo implements Serializable{

	//机构代啊
		private String orgCode;
		//合同编号
		private String contractNum;
		//当前期数
		private String currentTerm;
		//还款日期
		private String returnDate;
		//每期还款金额 
		private String returneterm;
		//当前利息
		private String currentAccrual;
		//本金金额
		private String principalBalance;
		public String getOrgCode() {
			return orgCode;
		}
		public void setOrgCode(String orgCode) {
			this.orgCode = orgCode;
		}
		public String getContractNum() {
			return contractNum;
		}
		public void setContractNum(String contractNum) {
			this.contractNum = contractNum;
		}
		public String getCurrentTerm() {
			return currentTerm;
		}
		public void setCurrentTerm(String currentTerm) {
			this.currentTerm = currentTerm;
		}
		public String getReturnDate() {
			return returnDate;
		}
		public void setReturnDate(String returnDate) {
			this.returnDate = returnDate;
		}
		public String getReturneterm() {
			return returneterm;
		}
		public void setReturneterm(String returneterm) {
			this.returneterm = returneterm;
		}
		public String getCurrentAccrual() {
			return currentAccrual;
		}
		public void setCurrentAccrual(String currentAccrual) {
			this.currentAccrual = currentAccrual;
		}
		public String getPrincipalBalance() {
			return principalBalance;
		}
		public void setPrincipalBalance(String principalBalance) {
			this.principalBalance = principalBalance;
		}
		
		
		
}
