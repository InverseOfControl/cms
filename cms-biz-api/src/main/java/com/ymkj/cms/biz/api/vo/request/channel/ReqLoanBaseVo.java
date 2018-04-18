package com.ymkj.cms.biz.api.vo.request.channel;

import java.io.Serializable;
import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @author YM10189
 * @date 2017年5月5日
 * @Description:查询借款信息 request vo
 */
public class ReqLoanBaseVo  extends Request implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -4108713542012441722L;
	
	/**
	 * 渠道ID
	 */
	private String channelId;
	
	/**
	 * 审批通过时间上限
	 */
	private String accDateStart;	
	
	/**
	 * 审批通过时间下限
	 */
	private String accDateEnd;	
	
	/**
	 * 操作客服工号
	 */
	private String serviceCode;	
	
	/**
	 * 操作客服姓名
	 */
	private String serviceName;	
	
	/**
	 * 操作ip
	 */
	private String ip;	
	
	/**
	 * 当前页数
	 */
	private int pageNum;	
	
	/**
	 * 分页条数
	 */
	private int pageSize;

	/**
	 * 当前页数
	 */
	private int page;
	
	/**
	 * 每页显示条数 
	 */
	private int rows;
	
	/**
	 * 批次号
	 * @return
	 */
	private String batchNum;
	
	/**
	 *渠道名 
	 **/
	private String channelName;
	
	/**
	 * 查询类型0:未生成批次信息1：已生成批次信息
	 */
	private String queryType;
	
	
	/**
	 * 营业部ID（多个）
	 * @return
	 */
	private List<Long> orgIds;
	
	/**
	 * 借款编号
	 * @return
	 */
	private List<String> loanNos;
	
	
	/**
	 * 文件类型
	 * @return
	 * 对应EnumBH2Constants划拨申请书pdf
	 */
	private String fileType;
	
	
	
	/**
	 * 序号次数
	 * @return
	 */
	private Integer seqFile;
	
	
	private Long createId;
	private String create;
	
	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	public String getCreate() {
		return create;
	}

	public void setCreate(String create) {
		this.create = create;
	}

	public List<String> getLoanNos() {
		return loanNos;
	}

	public void setLoanNos(List<String> loanNos) {
		this.loanNos = loanNos;
	}

	public List<Long> getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(List<Long> orgIds) {
		this.orgIds = orgIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getAccDateStart() {
		return accDateStart;
	}

	public void setAccDateStart(String accDateStart) {
		this.accDateStart = accDateStart;
	}

	public String getAccDateEnd() {
		return accDateEnd;
	}

	public void setAccDateEnd(String accDateEnd) {
		this.accDateEnd = accDateEnd;
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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getSeqFile() {
		return seqFile;
	}

	public void setSeqFile(Integer seqFile) {
		this.seqFile = seqFile;
	}
	
	
	
	

}
