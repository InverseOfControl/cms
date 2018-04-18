package com.ymkj.cms.biz.api.service.sign.aite;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;

public interface IAiTeLoanContractFileExecuter {
	/**
	 * 文件上传，满标之后的文件上传
	 * @param requestVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月16日下午3:05:03
	 */
	public Response<RequestVo> uploadFiles(RequestVo requestVo); 
	
	/**
	 * 文件作废，流标之后的文件作废
	 * @param requestVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月1日下午4:33:19
	 */
	public Response<RequestVo> deleteFile(RequestVo requestVo); 
}
