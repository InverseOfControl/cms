package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ReqTrialBeforeCreditChannelVO</p>
 * <p>Description:贷前试算-获取渠道响应对象</p>
 * @uthor YM10159
 * @date 2017年3月16日 上午9:36:45
 */
public class ResTrialBeforeCreditChannelVO implements Serializable {
	
	private static final long serialVersionUID = -55991933486372847L;
	
	public ResTrialBeforeCreditChannelVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 渠道code
	 */
	private String code;
	
	/**
	 * 渠道id
	 */
	private Integer id;
	/**
	 * 渠道名称
	 */
	private String name;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
}
