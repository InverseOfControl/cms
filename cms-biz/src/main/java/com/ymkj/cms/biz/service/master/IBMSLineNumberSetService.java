package com.ymkj.cms.biz.service.master;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.master.ReqLineNumberSetVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLineNumberSetVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLineNumberUploadExcelResultVO;
import com.ymkj.cms.biz.entity.master.BMSLimitChannel;
import com.ymkj.cms.biz.entity.master.BMSOffLineOfferBankDic;

public interface IBMSLineNumberSetService extends BaseService<BMSOffLineOfferBankDic>{
	/**
	 * 设置行别行号
	 */
	public Response<Integer> updateLineNumber(ReqLineNumberSetVO reqLineNumberSetVO);
	/**
	 * 根据导入EXCEL生成对应的集合对象新增或者更新对应的线下报盘银行字典表
	 */
	public void insertOrUpdateDic(List<ResLineNumberUploadExcelResultVO> LineNumberUploadVOs,
			String importExcelAreaType,String name,String id);
}
