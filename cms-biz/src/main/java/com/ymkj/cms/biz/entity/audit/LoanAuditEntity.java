package com.ymkj.cms.biz.entity.audit;

import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:LoanAuditEntity</p>
 * <p>Description:借款审批实体类</p>
 * @uthor YM10159
 * @date 2017年3月1日 下午4:00:26
 */
public class LoanAuditEntity extends BaseEntity {

	private static final long serialVersionUID = -2274819542502499085L;

	private long id;
	private long loan_base_id;
	private BigDecimal acc_lmt;
	private String acc_term;
	private String acc_date;
	private String app_no;
	private float point_result;
	private String pro_num;
	private String pro_name;
	private String rtf_state;
	private String refuse_code;
	private String primary_reason;
	private String loanNo;
	private String second_reason;
	private String check_person;
	private String final_person_code;
	private String check_person_code;
	private String check_node_state;
	private Date check_allot_date;
	private String final_person;
	private String final_role;
	private Date final_allot_date;
	private String appeoval_person;
	private String approval_person;
	private float amout_income;
	private float sys_check_lmt;
	private long sys_acc_trem;
	private float sys_acc_lmt;
	private String  isrollback;
	private float min_approval_amt;
	private String if_credit_record;
	private float  max_approval_amt;
	private long creator_id;
	private String creator;
	private Date created_time;
	private long modifier_id;
	private String modifier;
	private	Date  modified_time;
	private	Integer  version;
	private	Integer  is_delete;
	private	Date  start_date;
	private	Date end_date;
	private Date refuse_date;
	
	private Integer adviceVerifyIncome;//建议核实收入
	private Integer adviceAuditLines;//建议审批额度
	private BigDecimal internalDebtRatio;//  内部负债率
	private BigDecimal totalDebtRatio;// 总负债率
	private Integer scorecardScore;//  评分卡分数
	private String ccRuleSet;// 经过的CC规则集的名称
	private String isAntifraud;//是否欺诈可疑 
	
	private String ifCheckReturn;//是否复核不通过（不通过1 否则null）
	private String ifLastCheckReturn;//是否终审退回(1:退回初审,2:退回门店)
	
	public Integer getAdviceVerifyIncome() {
		return adviceVerifyIncome;
	}
	public void setAdviceVerifyIncome(Integer adviceVerifyIncome) {
		this.adviceVerifyIncome = adviceVerifyIncome;
	}
	public Integer getAdviceAuditLines() {
		return adviceAuditLines;
	}
	public void setAdviceAuditLines(Integer adviceAuditLines) {
		this.adviceAuditLines = adviceAuditLines;
	}
	public BigDecimal getInternalDebtRatio() {
		return internalDebtRatio;
	}
	public void setInternalDebtRatio(BigDecimal internalDebtRatio) {
		this.internalDebtRatio = internalDebtRatio;
	}
	public BigDecimal getTotalDebtRatio() {
		return totalDebtRatio;
	}
	public void setTotalDebtRatio(BigDecimal totalDebtRatio) {
		this.totalDebtRatio = totalDebtRatio;
	}
	public Integer getScorecardScore() {
		return scorecardScore;
	}
	public void setScorecardScore(Integer scorecardScore) {
		this.scorecardScore = scorecardScore;
	}
	public String getCcRuleSet() {
		return ccRuleSet;
	}
	public void setCcRuleSet(String ccRuleSet) {
		this.ccRuleSet = ccRuleSet;
	}
	public String getFinal_person_code() {
		return final_person_code;
	}
	public void setFinal_person_code(String final_person_code) {
		this.final_person_code = final_person_code;
	}
	public String getCheck_person_code() {
		return check_person_code;
	}
	public void setCheck_person_code(String check_person_code) {
		this.check_person_code = check_person_code;
	}
	public String getCheck_node_state() {
		return check_node_state;
	}
	public void setCheck_node_state(String check_node_state) {
		this.check_node_state = check_node_state;
	}
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
	public BigDecimal getAcc_lmt() {
		return acc_lmt;
	}
	public void setAcc_lmt(BigDecimal acc_lmt) {
		this.acc_lmt = acc_lmt;
	}
	public String getAcc_term() {
		return acc_term;
	}
	public void setAcc_term(String acc_term) {
		this.acc_term = acc_term;
	}
	public String getAcc_date() {
		return acc_date;
	}
	public void setAcc_date(String acc_date) {
		this.acc_date = acc_date;
	}
	public String getApp_no() {
		return app_no;
	}
	public void setApp_no(String app_no) {
		this.app_no = app_no;
	}
	public float getPoint_result() {
		return point_result;
	}
	public void setPoint_result(float point_result) {
		this.point_result = point_result;
	}
	public String getPro_num() {
		return pro_num;
	}
	public void setPro_num(String pro_num) {
		this.pro_num = pro_num;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getRtf_state() {
		return rtf_state;
	}
	public void setRtf_state(String rtf_state) {
		this.rtf_state = rtf_state;
	}
	public String getRefuse_code() {
		return refuse_code;
	}
	public void setRefuse_code(String refuse_code) {
		this.refuse_code = refuse_code;
	}
	public String getPrimary_reason() {
		return primary_reason;
	}
	public void setPrimary_reason(String primary_reason) {
		this.primary_reason = primary_reason;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getSecond_reason() {
		return second_reason;
	}
	public void setSecond_reason(String second_reason) {
		this.second_reason = second_reason;
	}
	public String getCheck_person() {
		return check_person;
	}
	public void setCheck_person(String check_person) {
		this.check_person = check_person;
	}
	public Date getCheck_allot_date() {
		return check_allot_date;
	}
	public void setCheck_allot_date(Date check_allot_date) {
		this.check_allot_date = check_allot_date;
	}
	public String getFinal_person() {
		return final_person;
	}
	public void setFinal_person(String final_person) {
		this.final_person = final_person;
	}
	public String getFinal_role() {
		return final_role;
	}
	public void setFinal_role(String final_role) {
		this.final_role = final_role;
	}
	public Date getFinal_allot_date() {
		return final_allot_date;
	}
	public void setFinal_allot_date(Date final_allot_date) {
		this.final_allot_date = final_allot_date;
	}
	public String getAppeoval_person() {
		return appeoval_person;
	}
	public void setAppeoval_person(String appeoval_person) {
		this.appeoval_person = appeoval_person;
	}
	public String getApproval_person() {
		return approval_person;
	}
	public void setApproval_person(String approval_person) {
		this.approval_person = approval_person;
	}
	public float getAmout_income() {
		return amout_income;
	}
	public void setAmout_income(float amout_income) {
		this.amout_income = amout_income;
	}
	public float getSys_check_lmt() {
		return sys_check_lmt;
	}
	public void setSys_check_lmt(float sys_check_lmt) {
		this.sys_check_lmt = sys_check_lmt;
	}
	public long getSys_acc_trem() {
		return sys_acc_trem;
	}
	public void setSys_acc_trem(long sys_acc_trem) {
		this.sys_acc_trem = sys_acc_trem;
	}
	public float getSys_acc_lmt() {
		return sys_acc_lmt;
	}
	public void setSys_acc_lmt(float sys_acc_lmt) {
		this.sys_acc_lmt = sys_acc_lmt;
	}
	public String getIsrollback() {
		return isrollback;
	}
	public void setIsrollback(String isrollback) {
		this.isrollback = isrollback;
	}
	public float getMin_approval_amt() {
		return min_approval_amt;
	}
	public void setMin_approval_amt(float min_approval_amt) {
		this.min_approval_amt = min_approval_amt;
	}
	public String getIf_credit_record() {
		return if_credit_record;
	}
	public void setIf_credit_record(String if_credit_record) {
		this.if_credit_record = if_credit_record;
	}
	public float getMax_approval_amt() {
		return max_approval_amt;
	}
	public void setMax_approval_amt(float max_approval_amt) {
		this.max_approval_amt = max_approval_amt;
	}
	public long getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(long creator_id) {
		this.creator_id = creator_id;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}
	public long getModifier_id() {
		return modifier_id;
	}
	public void setModifier_id(long modifier_id) {
		this.modifier_id = modifier_id;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Date getModified_time() {
		return modified_time;
	}
	public void setModified_time(Date modified_time) {
		this.modified_time = modified_time;
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
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Date getRefuse_date() {
		return refuse_date;
	}
	public void setRefuse_date(Date refuse_date) {
		this.refuse_date = refuse_date;
	}
	public String getIfCheckReturn() {
		return ifCheckReturn;
	}
	public void setIfCheckReturn(String ifCheckReturn) {
		this.ifCheckReturn = ifCheckReturn;
	}
	public String getIfLastCheckReturn() {
		return ifLastCheckReturn;
	}
	public void setIfLastCheckReturn(String ifLastCheckReturn) {
		this.ifLastCheckReturn = ifLastCheckReturn;
	}
	public String getIsAntifraud() {
		return isAntifraud;
	}
	public void setIsAntifraud(String isAntifraud) {
		this.isAntifraud = isAntifraud;
	}
	
}
