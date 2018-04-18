package com.ymkj.cms.biz.api.service.master;

import java.util.List;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSUrgentLimitListVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqLineNumberSetVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanUrgentConfigVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLineNumberSetVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLineNumberUploadExcelResultVO;

public interface IBMSLineNumberSetExecuter {
	/**
	 * 查询行别行号信息
	 * @param reqBMSLoanBaseVO
	 * @return
	 */
	public PageResponse<ResLineNumberSetVO> listPage(ReqLineNumberSetVO reqLineNumberSetVO);
	/**
	 * 设置行别行号
	 */
	public Response<Integer> updateLineNumber(ReqLineNumberSetVO reqLineNumberSetVO);
	
	/**
	 * 根据导入EXCEL生成的集合对象新增或者更新对应的线下报盘字典表
	 */
	public void insertOrUpdateDic(ReqLineNumberSetVO reqLineNumberSetVO);

}
