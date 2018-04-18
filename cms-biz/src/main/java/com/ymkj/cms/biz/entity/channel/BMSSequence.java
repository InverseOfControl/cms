package com.ymkj.cms.biz.entity.channel;
import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * @author YM10189
 * @date 2017年5月19日
 * @Description:序列
 */
public class BMSSequence extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8962453693817210792L;

	private String id;

    private Integer seqStartNum;

    private String seqName;

    private Integer seqIncret;

    private Date updateTime;

    private Integer seqMaxNum;

    private Integer seqResetInterval;
    
    private Integer seqCurlNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getSeqStartNum() {
        return seqStartNum;
    }

    public void setSeqStartNum(Integer seqStartNum) {
        this.seqStartNum = seqStartNum;
    }

    public String getSeqName() {
        return seqName;
    }

    public void setSeqName(String seqName) {
        this.seqName = seqName == null ? null : seqName.trim();
    }

    public Integer getSeqIncret() {
        return seqIncret;
    }

    public void setSeqIncret(Integer seqIncret) {
        this.seqIncret = seqIncret;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSeqMaxNum() {
        return seqMaxNum;
    }

    public void setSeqMaxNum(Integer seqMaxNum) {
        this.seqMaxNum = seqMaxNum;
    }

    public Integer getSeqResetInterval() {
        return seqResetInterval;
    }

    public void setSeqResetInterval(Integer seqResetInterval) {
        this.seqResetInterval = seqResetInterval;
    }

	public Integer getSeqCurlNum() {
		return seqCurlNum;
	}

	public void setSeqCurlNum(Integer seqCurlNum) {
		this.seqCurlNum = seqCurlNum;
	}
    
    
}