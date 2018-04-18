package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqLoanReviewVo extends Request {

	private static final long serialVersionUID = 8680071444072706079L;
	
	public ReqLoanReviewVo(String sysCode){
		super.setSysCode(sysCode);
	}
	
	public ReqLoanReviewVo(){}
	
	private long id;
	private long loan_base_id;
	/**
	 * 借款编号
	 */
	private String loan_no;
	/**
	 * 客户姓名
	 */
	private String name;
	/**
	 * 证件号码
	 */
	private String id_no;
	/**
	 * 客户id
	 */
	private long person_id;
	/**
	 * 申请期限
	 */
	private long apply_term;
	/**
	 * 申请产品
	 */
	private String product_cd;
	/**
	 * 申请产品名称
	 */
	private String product_name;
	/**
	 * 被拒绝时间
	 */
	private String refuse_date;
	/**
	 * 拒绝一级原因
	 */
	private String primary_reason;
	/**
	 * 客户经理code
	 */
	private String branch_manager_code;
	/**
	 * 客户经理名字
	 */
	private String branch_manager_name;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 是否已读
	 */
	private Integer is_read;
	/**
	 * 创建用户
	 */
	private String creator;
	/**
	 * 创建时间
	 */
	private String created_time;
	/**
	 * 创建用户ID
	 */
	private long creator_id;
	/**
	 * 修改用户
	 */
	private String modifier;
	/**
	 * 修改时间
	 */
	private String modified_time;
	/**
	 * 修改用户id
	 */
	private long modifier_id;
	/**
	 * 版本
	 */
	private Integer version;
	/**
	 * 默认是0,删除是1
	 */
	private Integer is_delete;
	/**
	 * 复议原因
	 */
	private String review_reason;
	/**
	 * 复议备注
	 */
	private String review_remark;
	/**
	 * 复议结果
	 */
	private int review_result;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getLoan_base_id() {
		return loan_base_id;
	}
	public void setLoan_base_id(long loan_base_id) {
		this.loan_base_id = loan_base_id;
	}
	public String getLoan_no() {
		return loan_no;
	}
	public void setLoan_no(String loan_no) {
		this.loan_no = loan_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId_no() {
		return id_no;
	}
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}
	public long getPerson_id() {
		return person_id;
	}
	public void setPerson_id(long person_id) {
		this.person_id = person_id;
	}
	public long getApply_term() {
		return apply_term;
	}
	public void setApply_term(long apply_term) {
		this.apply_term = apply_term;
	}
	public String getProduct_cd() {
		return product_cd;
	}
	public void setProduct_cd(String product_cd) {
		this.product_cd = product_cd;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getRefuse_date() {
		return refuse_date;
	}
	public void setRefuse_date(String refuse_date) {
		this.refuse_date = refuse_date;
	}
	public String getPrimary_reason() {
		return primary_reason;
	}
	public void setPrimary_reason(String primary_reason) {
		this.primary_reason = primary_reason;
	}
	public String getBranch_manager_code() {
		return branch_manager_code;
	}
	public void setBranch_manager_code(String branch_manager_code) {
		this.branch_manager_code = branch_manager_code;
	}
	public String getBranch_manager_name() {
		return branch_manager_name;
	}
	public void setBranch_manager_name(String branch_manager_name) {
		this.branch_manager_name = branch_manager_name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIs_read() {
		return is_read;
	}
	public void setIs_read(Integer is_read) {
		this.is_read = is_read;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreated_time() {
		return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public long getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(long creator_id) {
		this.creator_id = creator_id;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModified_time() {
		return modified_time;
	}
	public void setModified_time(String modified_time) {
		this.modified_time = modified_time;
	}
	public long getModifier_id() {
		return modifier_id;
	}
	public void setModifier_id(long modifier_id) {
		this.modifier_id = modifier_id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
	public String getReview_reason() {
		return review_reason;
	}
	public void setReview_reason(String review_reason) {
		this.review_reason = review_reason;
	}
	public String getReview_remark() {
		return review_remark;
	}

	public void setReview_remark(String review_remark) {
		this.review_remark = review_remark;
	}

	public int getReview_result() {
		return review_result;
	}
	public void setReview_result(int review_result) {
		this.review_result = review_result;
	}
}
