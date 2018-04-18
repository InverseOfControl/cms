package com.ymkj.cms.biz.entity.apply;

import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class LoanReviewEntity extends BaseEntity {

	private static final long serialVersionUID = 8203686843687907515L;
	
	/**
	 * 进件门店ID
	 */
	private long owning_branch_id;
	/**
	 * loan_base_id
	 */
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
	private String modifier_id;
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
	
	private String three_workday_date;
	
	private String blacklist_id;
	
	private String secode_reason;
	
	private String first_levle_reasons_code;
	
	private String two_levle_reasons_code;
	
	private String enter_branch;
	
	private Date submit_xs_date;
	
	private String new_reject_first_reason;
	
	private String new_reject_two_reason;
	
	private String new_reject_first_reason_code;
	
	private String new_reject_two_reason_code;
    
	private String reject_person_code;
	private String reject_person_name;

	public long getOwning_branch_id() {
		return owning_branch_id;
	}
	public void setOwning_branch_id(long owning_branch_id) {
		this.owning_branch_id = owning_branch_id;
	}
	public String getReview_remark() {
		return review_remark;
	}
	public void setReview_remark(String review_remark) {
		this.review_remark = review_remark;
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
	
	public String getModifier_id() {
		return modifier_id;
	}
	public void setModifier_id(String modifier_id) {
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
	public String getReview_reamrk() {
		return review_remark;
	}
	public void setReview_reamrk(String review_remark) {
		this.review_remark = review_remark;
	}
	public int getReview_result() {
		return review_result;
	}
	public void setReview_result(int review_result) {
		this.review_result = review_result;
	}
	public String getThree_workday_date() {
		return three_workday_date;
	}
	public void setThree_workday_date(String three_workday_date) {
		this.three_workday_date = three_workday_date;
	}
	public String getBlacklist_id() {
		return blacklist_id;
	}
	public void setBlacklist_id(String blacklist_id) {
		this.blacklist_id = blacklist_id;
	}
	public String getSecode_reason() {
		return secode_reason;
	}
	public void setSecode_reason(String secode_reason) {
		this.secode_reason = secode_reason;
	}
	public String getFirst_levle_reasons_code() {
		return first_levle_reasons_code;
	}
	public void setFirst_levle_reasons_code(String first_levle_reasons_code) {
		this.first_levle_reasons_code = first_levle_reasons_code;
	}
	public String getTwo_levle_reasons_code() {
		return two_levle_reasons_code;
	}
	public void setTwo_levle_reasons_code(String two_levle_reasons_code) {
		this.two_levle_reasons_code = two_levle_reasons_code;
	}
	public String getEnter_branch() {
		return enter_branch;
	}
	public void setEnter_branch(String enter_branch) {
		this.enter_branch = enter_branch;
	}
	public Date getSubmit_xs_date() {
		return submit_xs_date;
	}
	public void setSubmit_xs_date(Date submit_xs_date) {
		this.submit_xs_date = submit_xs_date;
	}
	public String getNew_reject_first_reason() {
		return new_reject_first_reason;
	}
	public void setNew_reject_first_reason(String new_reject_first_reason) {
		this.new_reject_first_reason = new_reject_first_reason;
	}
	public String getNew_reject_two_reason() {
		return new_reject_two_reason;
	}
	public void setNew_reject_two_reason(String new_reject_two_reason) {
		this.new_reject_two_reason = new_reject_two_reason;
	}
	public String getNew_reject_first_reason_code() {
		return new_reject_first_reason_code;
	}
	public void setNew_reject_first_reason_code(String new_reject_first_reason_code) {
		this.new_reject_first_reason_code = new_reject_first_reason_code;
	}
	public String getNew_reject_two_reason_code() {
		return new_reject_two_reason_code;
	}
	public void setNew_reject_two_reason_code(String new_reject_two_reason_code) {
		this.new_reject_two_reason_code = new_reject_two_reason_code;
	}
	public String getReject_person_code() {
		return reject_person_code;
	}
	public void setReject_person_code(String reject_person_code) {
		this.reject_person_code = reject_person_code;
	}
	public String getReject_person_name() {
		return reject_person_name;
	}
	public void setReject_person_name(String reject_person_name) {
		this.reject_person_name = reject_person_name;
	}
	
}
