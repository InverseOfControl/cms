package com.ymkj.cms.biz.api.vo.request.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.audit.ReqSignReassignVO;

/**
 * 签约改派请求实体
 * @author YM10166
 *
 */
public class ReqBMSContractChangeVo extends ReqSignReassignVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 368301519621620410L;
	
	/**
	 * LOAN_BASE_ID
	 */
	private Integer id;
	
	/**
	 * 批量签约改派时使用
	 */
	private String ids;

	/**
	 * 借款编号
	 */
	private String loanNo;
	
	/**
	 * 批量签约改派时使用,多个用‘，’
	 */
	private String loanNos;
	
	/**
	 * 客户姓名
	 */
	private String personName;
	
	/**
	 * 渠道名称
	 */
	private String channelName;
	
	/**
	 * 渠道id
	 */
	private String channelId;
	
	/**
	 * 渠道code
	 */
	private String channelCode;
	
	/**
	 * 产品名称
	 */
	private String productName;
	
	/**
	 * 产品id
	 */
	private String productId;
	/**
	 * 产品code
	 */
	private String productCode;
	
	/**
	 * 签约网点id
	 */
	private String contractBranchId;
	
	/**
	 * 签约网点
	 */
	private String contractBranch;
	
	/**
	 * 签约客服Code
	 */
	private String signCode;
	
	/**
	 * 签约客服
	 */
	private String signName;
	
	/**
	 * 当前页数
	 */
	private int pageNum;
	
	/**
	 * 每页显示条数
	 */
	private int pageSize;
	
	/**
	 * 行数（页面传值）
	 */
	private int rows;
	
	/**
	 * 页数(页面传值)
	 */
	private int page;
	
	/**
	 * 签约客服，code集合
	 */
	private List<String> customerServiceList;
	/**
	 * 是否加急   0不加急   1加急
	 */
	private String ifPri;
	/**
	 *  APP进件标识     app_input:进件
	 */
	private String appInputFlag;
	/**
	 * 是否疑似欺诈  Y 是  N否
	 */
	private String ifSuspectCheat;
	/**
	 * 当前登录人account
	 */
	private String loginUser;
	/**
	 * 签约网点，id集合
	 */
	private List<String> contractBranchIdList;
	/**
	 * 录单网点，id集合
	 */
	private List<String> owningBranchIdList;
	
	/**
	 * 签约客服code,排除集合  ','拼接
	 */
	private String signCodeRejects;
	/**
	 * 签约客服code,排除集合
	 */
	private List<String> signCodeRejectList;
	/**
	 * 操作人员code
	 */
	private String serviceCode;
	/**
	 * 操作人员姓名
	 */
	private String serviceName;
	/**
	 * 需要要显示的借款环节集合
	 */
	private List<String> rtfStateList;
	/**
	 * 需要要显示的借款环节节点状态集合
	 */
	private List<String> rtfNodeStateList;
	/**
	 * 业务环节
	 */
	private String businessSegment;
	/**
	 * 联合状态   status+rtfState+rtfNodeState
	 */
	private List<String> concatRtfStateList;
	/**
	 * 营业部类型(0:普通, 1直通车)
	 */
	private Integer saleDeptType;
	/**
	 * 直通车机构id集合
	 */
	private List<String> orgTZCList;
	/**
	 * 需要要剔除的借款状态集合
	 */
	private List<String> concatRtfNodeStateNoCheckList;
	
	public ReqBMSContractChangeVo() {
		super.setSysCode(EnumConstants.BMS_SYSCODE);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getContractBranchId() {
		return contractBranchId;
	}

	public void setContractBranchId(String contractBranchId) {
		this.contractBranchId = contractBranchId;
	}

	public String getContractBranch() {
		return contractBranch;
	}

	public void setContractBranch(String contractBranch) {
		this.contractBranch = contractBranch;
	}

	public String getSignCode() {
		return signCode;
	}

	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
		this.setPageNum(rows);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		this.setPageSize(page);
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public List<String> getCustomerServiceList() {
		return customerServiceList;
	}

	public void setCustomerServiceList(List<String> customerServiceList) {
		this.customerServiceList = customerServiceList;
	}

	public String getIfPri() {
		return ifPri;
	}

	public void setIfPri(String ifPri) {
		this.ifPri = ifPri;
	}

	public String getAppInputFlag() {
		return appInputFlag;
	}

	public void setAppInputFlag(String appInputFlag) {
		this.appInputFlag = appInputFlag;
	}

	public String getIfSuspectCheat() {
		return ifSuspectCheat;
	}

	public void setIfSuspectCheat(String ifSuspectCheat) {
		this.ifSuspectCheat = ifSuspectCheat;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public List<String> getContractBranchIdList() {
		return contractBranchIdList;
	}

	public void setContractBranchIdList(List<String> contractBranchIdList) {
		this.contractBranchIdList = contractBranchIdList;
	}

	public String getSignCodeRejects() {
		return signCodeRejects;
	}

	public void setSignCodeRejects(String signCodeRejects) {
		this.signCodeRejects = signCodeRejects;
	}

	public List<String> getSignCodeRejectList() {
		return signCodeRejectList;
	}

	public void setSignCodeRejectList(List<String> signCodeRejectList) {
		this.signCodeRejectList = signCodeRejectList;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public List<String> getRtfStateList() {
		return rtfStateList;
	}

	public void setRtfStateList(List<String> rtfStateList) {
		this.rtfStateList = rtfStateList;
	}

	public List<String> getRtfNodeStateList() {
		return rtfNodeStateList;
	}

	public void setRtfNodeStateList(List<String> rtfNodeStateList) {
		this.rtfNodeStateList = rtfNodeStateList;
	}

	public String getLoanNos() {
		return loanNos;
	}

	public void setLoanNos(String loanNos) {
		this.loanNos = loanNos;
	}

	public String getBusinessSegment() {
		return businessSegment;
	}

	public void setBusinessSegment(String businessSegment) {
		this.businessSegment = businessSegment;
	}

	public List<String> getOwningBranchIdList() {
		return owningBranchIdList;
	}

	public void setOwningBranchIdList(List<String> owningBranchIdList) {
		this.owningBranchIdList = owningBranchIdList;
	}

	public List<String> getConcatRtfStateList() {
		return concatRtfStateList;
	}

	public void setConcatRtfStateList(List<String> concatRtfStateList) {
		this.concatRtfStateList = concatRtfStateList;
	}

	public Integer getSaleDeptType() {
		return saleDeptType;
	}

	public void setSaleDeptType(Integer saleDeptType) {
		this.saleDeptType = saleDeptType;
	}

	public List<String> getOrgTZCList() {
		return orgTZCList;
	}

	public void setOrgTZCList(List<String> orgTZCList) {
		this.orgTZCList = orgTZCList;
	}

	public List<String> getConcatRtfNodeStateNoCheckList() {
		return concatRtfNodeStateNoCheckList;
	}

	public void setConcatRtfNodeStateNoCheckList(List<String> concatRtfNodeStateNoCheckList) {
		this.concatRtfNodeStateNoCheckList = concatRtfNodeStateNoCheckList;
	}

	
}
