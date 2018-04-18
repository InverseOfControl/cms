package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 1.demo请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 2.其中必须包含 对应实体类 {DemoEntity}中的 所有字段, 业务不同实体不同  
 * 3.如有需要可 自行扩展
 * @author haowp
 *
 */
public class ReqDemoVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;
	
	private Long id;         // 主键ID
	private String name;     // 名字
	private String address;  // 地址
	private int status;      // 状态
	
	private int pageNum;     // 当前页数
	private int pageSize;    // 每页记录数
	
	public ReqDemoVO(){
		
	}
	public ReqDemoVO(String sysCode){
		super.setSysCode(sysCode);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public void setPageSize(int numPerPage) {
		this.pageSize = numPerPage;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
