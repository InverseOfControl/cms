package com.ymkj.cms.biz.api.vo.request.master;



import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSdebtRadioVO  extends Request {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8818214561219465749L;
	
	private int id;
    private BigDecimal totalDebtRadio;  //总负债率
	
	private BigDecimal internalDebtRadio;  //内部负债率
	private int page;     // 当前页数
	private int rows; //每页显示条数 
	
	private int pageNum;     // 当前页数
	private int pageSize;

	private String modifier; 

	private Date modifierDate;

	private String modifierId;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
		this.setPageNum(page);
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
		this.setPageSize(rows);
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getTotalDebtRadio() {
		return totalDebtRadio;
	}
	public void setTotalDebtRadio(BigDecimal totalDebtRadio) {
		this.totalDebtRadio = totalDebtRadio;
	}
	public BigDecimal getInternalDebtRadio() {
		return internalDebtRadio;
	}
	public void setInternalDebtRadio(BigDecimal internalDebtRadio) {
		this.internalDebtRadio = internalDebtRadio;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Date getModifierDate() {
		return modifierDate;
	}
	public void setModifierDate(Date modifierDate) {
		this.modifierDate = modifierDate;
	}
	public String getModifierId() {
		return modifierId;
	}
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}
	
}
