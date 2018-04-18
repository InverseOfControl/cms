package com.ymkj.cms.biz.api.vo.request.audit;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqQualityPassParameterVO extends Request{

	
		//初审人员CODE
		private List<String> userCode;
		//开始日期(审批日期)
		private String startDate;
		//结束日期(审批日期)
		private String endDate;
		//(进件门店ID)
		private List<Integer> owningBranchIds;
		//产品类型(贷款产品)
		private String productCd;
		//一级拒绝原因
		private List<String> firstLevleReasonsCode;
		//二级拒绝原因
		private List<String> twoLevleReasonsCode;
		//客户类型(申请类型)
		private String applyType;
		public List<String> getUserCode() {
			return userCode;
		}
		public void setUserCode(List<String> userCode) {
			this.userCode = userCode;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		public List<Integer> getOwningBranchIds() {
			return owningBranchIds;
		}
		public void setOwningBranchIds(List<Integer> owningBranchIds) {
			this.owningBranchIds = owningBranchIds;
		}
		public String getProductCd() {
			return productCd;
		}
		public void setProductCd(String productCd) {
			this.productCd = productCd;
		}
		public List<String> getFirstLevleReasonsCode() {
			return firstLevleReasonsCode;
		}
		public void setFirstLevleReasonsCode(List<String> firstLevleReasonsCode) {
			this.firstLevleReasonsCode = firstLevleReasonsCode;
		}
		public List<String> getTwoLevleReasonsCode() {
			return twoLevleReasonsCode;
		}
		public void setTwoLevleReasonsCode(List<String> twoLevleReasonsCode) {
			this.twoLevleReasonsCode = twoLevleReasonsCode;
		}
		public String getApplyType() {
			return applyType;
		}
		public void setApplyType(String applyType) {
			this.applyType = applyType;
		}
		
}
