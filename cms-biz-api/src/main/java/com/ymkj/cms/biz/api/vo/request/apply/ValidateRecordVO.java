package com.ymkj.cms.biz.api.vo.request.apply;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class ValidateRecordVO extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<RecordVO> recordvo;

	public List<RecordVO> getRecordvo() {
		return recordvo;
	}

	public void setRecordvo(List<RecordVO> recordvo) {
		this.recordvo = recordvo;
	}
}


