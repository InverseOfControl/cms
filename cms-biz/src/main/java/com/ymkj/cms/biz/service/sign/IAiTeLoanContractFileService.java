package com.ymkj.cms.biz.service.sign;

import java.util.Map;

import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;

public interface IAiTeLoanContractFileService {
	/**
	 * 文件上传
	 * @param requestVo
	 * @author lix YM10160
	 * @date 2017年3月16日下午2:56:10
	 */
	public Map<String, Object> uploadFile(RequestVo requestVo);
	
	/**
	 * 文件作废，流标之后的文件作废
	 * @param requestVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月1日下午4:34:30
	 */
	public Map<String, Object> deleteFile(RequestVo requestVo);
	
	/**
	 * 文件上传信息保存
	 * @param resultMap
	 * @author lix YM10160
	 * @date 2017年4月5日下午7:41:53
	 */
	public Long saveAiteUploadFile(Map<String, Object> resultMap);
}
