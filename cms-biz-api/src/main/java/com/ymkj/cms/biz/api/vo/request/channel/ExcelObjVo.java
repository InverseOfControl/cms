package com.ymkj.cms.biz.api.vo.request.channel;

import java.io.Serializable;

/**
 * @author YM10189
 * @date 2017年5月9日
 * @Description:excel vo
 */
public class ExcelObjVo implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -777925947335403094L;

	/**
	 * sheet name
	 */
	private String sheetName;

	/**
	 * 文件名称
	 */
	private String excFileName;

	/**
	 * 文件标题
	 */
	private String excFileTitle;

	/**
	 * 开始行
	 */
	private int begRow;

	/**
	 * 开始列
	 */
	private int begCell;

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getExcFileName() {
		return excFileName;
	}

	public void setExcFileName(String excFileName) {
		this.excFileName = excFileName;
	}

	public String getExcFileTitle() {
		return excFileTitle;
	}

	public void setExcFileTitle(String excFileTitle) {
		this.excFileTitle = excFileTitle;
	}

	public int getBegRow() {
		return begRow;
	}

	public void setBegRow(int begRow) {
		this.begRow = begRow;
	}

	public int getBegCell() {
		return begCell;
	}

	public void setBegCell(int begCell) {
		this.begCell = begCell;
	}
	
	public ExcelObjVo() {
	}

	public ExcelObjVo(String excFileName, String excFileTitle, int begRow, int begCell) {
		this.excFileName = excFileName;
		this.excFileTitle = excFileTitle;
		this.begRow = begRow;
		this.begCell = begCell;
	}

}
