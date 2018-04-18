package com.ymkj.cms.biz.api.vo.request.integratedsearch;

import java.util.List;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ReqIntegratedSearchVO</p>
 * <p>Description:综合查询列表查询请求对象</p>
 * @uthor YM10159
 * @date 2017年3月9日 上午11:12:57
 */
public class ReqIntegratedSearchVO extends Request{

	private static final long serialVersionUID = -354104315340105777L;
	
	public ReqIntegratedSearchVO(String sysCode){
		super.setSysCode(sysCode);
	}
	
	public ReqIntegratedSearchVO(){}
	
	/**
	 * 客户姓名
	 */
	private String name;
	/**
	 * 证件号码
	 */
	private String idNo;
	/**
	 * 合同编号
	 */
	private String contractNo;
	/**
	 * 借款编号
	 */
	private String loanNo;
	/**
	 * 手机
	 */
	private String phone;
	/**
	 * 固定电话
	 */
	private String tel;
	/**
	 * 单位名称
	 */
	private String corpName;
	/**
	 * 案件标识
	 */
	private String caseIdentify;

	/**
	 * 交单日期起
	 */
	private String startTime;
	/**
	 * 交单日期止
	 */
	private String endTime;
	/**
	 * 申请类型
	 */
	private String applyType;
	/**
	 * 操作人工号
	 */
	@NotEmpty(message="操作人工号不能为空")
	private String serviceCode;
	/**
	 * 操作人姓名
	 */
	@NotEmpty(message="操作人姓名不能为空")
	private String serviceName;
	/**
	 * 操作ip
	 */
	@NotEmpty(message="操作ip不能为空")
	private String ip;
	/**
	 * 当前页数
	 */
	@Min(1)
	private int pageNum;
	/**
	 * 分页条数
	 */
	@Min(1)
	private int pageSize;
	
	private String[] teLoanNo;
	
	/**
	 * 排序字段
	 */
	private String fieldSort;
	/**
	 * 排序规则
	 */
	private int rulesSort;//0 倒序  1正序  其他 顺序
	
	//创建时间起
	private String startCreatedTime;
	
	//创建时间结束
	private String endCreatedTime;
	
	private String ifPreferentialUser; //是否优惠费率用户  Y 是  N 否
	
	private List<String> caseIdentifyList; //案件标识 0APP进件，1加急，2反欺诈，3优惠标识
	
	private List<String> addressList; //地址类型 1住宅地址,2.单位地址,3.房产地址
	
	private String address;
	
	/**
	 * 客户经理
	 */
	private List<String> customerManagerList;
	
	public String getFieldSort() {
		return fieldSort;
	}
	public void setFieldSort(String fieldSort) {
		this.fieldSort = fieldSort;
	}
	public int getRulesSort() {
		return rulesSort;
	}
	public void setRulesSort(int rulesSort) {
		this.rulesSort = rulesSort;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
	public String getCaseIdentify() {
		return caseIdentify;
	}
	public void setCaseIdentify(String caseIdentify) {
		this.caseIdentify = caseIdentify;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String[] getTeLoanNo() {
		return teLoanNo;
	}

	public void setTeLoanNo(String[] teLoanNo) {
		this.teLoanNo = teLoanNo;
	}

	public String getStartCreatedTime() {
		return startCreatedTime;
	}

	public void setStartCreatedTime(String startCreatedTime) {
		this.startCreatedTime = startCreatedTime;
	}

	public String getEndCreatedTime() {
		return endCreatedTime;
	}

	public void setEndCreatedTime(String endCreatedTime) {
		this.endCreatedTime = endCreatedTime;
	}

	public String getIfPreferentialUser() {
		return ifPreferentialUser;
	}

	public void setIfPreferentialUser(String ifPreferentialUser) {
		this.ifPreferentialUser = ifPreferentialUser;
	}

	public List<String> getCaseIdentifyList() {
		return caseIdentifyList;
	}

	public void setCaseIdentifyList(List<String> caseIdentifyList) {
		this.caseIdentifyList = caseIdentifyList;
	}

	public List<String> getCustomerManagerList() {
		return customerManagerList;
	}

	public void setCustomerManagerList(List<String> customerManagerList) {
		this.customerManagerList = customerManagerList;
	}

	public List<String> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
