package com.ymkj.cms.web.boss.service.master;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqLineNumberSetVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLineNumberSetVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLineNumberUploadExcelResultVO;

public interface ILineNumberSetService {
	
	/**
	 * 根据请求VO 查询分页信息(已经签过合同的申请件)
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResLineNumberSetVO> listPage(ReqLineNumberSetVO reqLineNumberSetVO);
	
	/**
	 * 设置行别行号
	 */
	public Response<Integer> updateLineNumber(ReqLineNumberSetVO reqLineNumberSetVO);
	/**
	 * EXCEL转成实体
	 * @param fileName
	 * @param Mfile
	 * @return
	 */
	public Map<String, Object> Analytical(String fileName, MultipartFile Mfile);
	
	/**
	 * 根据导入的数据新增或者更新对应的线下报盘银行字典表
	 */
	public void insertOrUpdateDic(List<ResLineNumberUploadExcelResultVO> LineNumberUploadVOs,String importExcelAreaType);
}
