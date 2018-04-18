package com.ymkj.cms.biz.api.vo.request.sign;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @Description:陆金所进件通知外部接口实体</p>
 * @uthor YM10159
 * @date 2017年7月14日 下午4:40:44
 */
public class ReqLufax800001Vo extends Request {

	private static final long serialVersionUID = 1L;

	/** 机构编号*/
	@NotEmpty(message = "机构编号不能为空！")
	private String dept_code;
	
	/** 产品大类*/
	@NotEmpty(message = "产品大类不能为空！")
	private String project_code;
	
	/** 产品小类*/
	@NotEmpty(message = "产品小类不能为空！")
	private String product_code;
	
	/** 进件系统流水号*/
	@NotEmpty(message = "进件系统流水号不能为空！")
	private String aps_apply_no;
	
	/** 申请单号*/
	@NotEmpty(message = "申请单号不能为空！")
	private String apply_no;
	
	/** 通知类型*/
	@NotEmpty(message = "通知类型不能为空！")
	private String notify_type;
	
	/** 通知结果*/
	@NotEmpty(message = "通知结果不能为空！")
	private String notify_code;
	
	/** 描述*/
	private String notify_desc;
	
    public String getDept_code() {
        return dept_code;
    }

    public void setDept_code(String dept_code) {
        this.dept_code = dept_code;
    }

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getAps_apply_no() {
        return aps_apply_no;
    }

    public void setAps_apply_no(String aps_apply_no) {
        this.aps_apply_no = aps_apply_no;
    }

    public String getApply_no() {
        return apply_no;
    }

    public void setApply_no(String apply_no) {
        this.apply_no = apply_no;
    }

    public String getNotify_type() {
        return notify_type;
    }

    public void setNotify_type(String notify_type) {
        this.notify_type = notify_type;
    }

    public String getNotify_code() {
        return notify_code;
    }

    public void setNotify_code(String notify_code) {
        this.notify_code = notify_code;
    }

    public String getNotify_desc() {
        return notify_desc;
    }

    public void setNotify_desc(String notify_desc) {
        this.notify_desc = notify_desc;
    }

}
