package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResProductBaseListVO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2110819733543795537L;
	/**
	 * 产品返回集合
	 */
	private List<ResBMSProductVO> listProduct = new ArrayList<ResBMSProductVO>();
	/**
	 * 产品审核期限集合
	 */
	private List<ResBMSProductAuditLimitVO> listProductLimt = new ArrayList<ResBMSProductAuditLimitVO>();
	
	/**
	 * 门店期限渠道集合
	 */
	private List<ResBMSOrgLimitChannelVO> listOrgLimitChannel = new ArrayList<ResBMSOrgLimitChannelVO>();
	
	
	/**
	 * 枚举值集合
	 */
	private List<ResBMSEnumCodeVO> listEnumCode = new ArrayList<ResBMSEnumCodeVO>();
	/**
	 * 地区集合
	 */
	private List<ResBMSBaseAreaVO> listBaseArea = new ArrayList<ResBMSBaseAreaVO>();
	
	/**
	 * 产品所属模块集合
	 */
	private List<ResBMSProductCodeModuleVO> listCodeModule = new ArrayList<ResBMSProductCodeModuleVO>();
	
	
	public List<ResBMSBaseAreaVO> getListBaseArea() {
		return listBaseArea;
	}

	public void setListBaseArea(List<ResBMSBaseAreaVO> listBaseArea) {
		this.listBaseArea = listBaseArea;
	}

	public List<ResBMSEnumCodeVO> getListEnumCode() {
		return listEnumCode;
	}

	public void setListEnumCode(List<ResBMSEnumCodeVO> listEnumCode) {
		this.listEnumCode = listEnumCode;
	}

	public List<ResBMSOrgLimitChannelVO> getListOrgLimitChannel() {
		return listOrgLimitChannel;
	}

	public void setListOrgLimitChannel(
			List<ResBMSOrgLimitChannelVO> listOrgLimitChannel) {
		this.listOrgLimitChannel = listOrgLimitChannel;
	}

	public List<ResBMSProductAuditLimitVO> getListProductLimt() {
		return listProductLimt;
	}

	public void setListProductLimt(List<ResBMSProductAuditLimitVO> listProductLimt) {
		this.listProductLimt = listProductLimt;
	}

	public List<ResBMSProductVO> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<ResBMSProductVO> listProduct) {
		this.listProduct = listProduct;
	}

	public List<ResBMSProductCodeModuleVO> getListCodeModule() {
		return listCodeModule;
	}

	public void setListCodeModule(List<ResBMSProductCodeModuleVO> listCodeModule) {
		this.listCodeModule = listCodeModule;
	}

	
	

}
