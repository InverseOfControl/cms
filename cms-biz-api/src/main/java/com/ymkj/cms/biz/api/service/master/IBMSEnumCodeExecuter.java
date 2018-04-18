package com.ymkj.cms.biz.api.service.master;

import java.util.List;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

public interface IBMSEnumCodeExecuter {
	/**
	 * 查询出所有的符合条件的枚举信息根据条件 （现在条件有枚举类型）
	 * 
	 * @param reqBMSEnumCodeVO
	 * @return
	 */
	public ResListVO<ResBMSEnumCodeVO> listEnumCodeBy(ReqBMSEnumCodeVO reqBMSEnumCodeVO);

	/**
	 * 根据 id 查询 枚举信息
	 * 
	 * @param reqBMSEnumCodeVO
	 * @return
	 */
	public Response<ResBMSEnumCodeVO> findById(ReqBMSEnumCodeVO reqBMSEnumCodeVO);

	/**
	 * 查询枚举信息根据产品ID
	 * 
	 * @param paramMap
	 * @return
	 */
	public ResListVO<ResBMSEnumCodeVO> findEnumCodeByCondition(ReqBMSEnumCodeVO reqBMSEnumCodeVO);

	/**
	 * 分页查询 reqBMSEnumCodeVO
	 * 
	 * @param reqBMSEnumCodeVO
	 * @return
	 */
	public PageResponse<ResBMSEnumCodeVO> listPage(ReqBMSEnumCodeVO reqBMSEnumCodeVO);

	public Response<ResBMSEnumCodeVO> addEnumCode(ReqBMSEnumCodeVO reqBMSEnumCodeVO);

	public Response<ResBMSEnumCodeVO> updateEnumCode(ReqBMSEnumCodeVO reqBMSEnumCodeVO);
	
	public Response<ResBMSEnumCodeVO> getAllEnumCode(ReqBMSEnumCodeVO reqBMSEnumCodeVO);
	
	/**
	 * 按vo类传进来的信息查询
	 * @param reqBMSEnumCodeVO
	 * @return
	 */
	public Response<ResBMSEnumCodeVO> findByVo(ReqBMSEnumCodeVO reqBMSEnumCodeVO);
	
	/**
	 * 根据产品ID，获取资产配置条件
	 * @param reqBMSEnumCodeVO
	 * @return
	 */
	public Response<List<ResBMSEnumCodeVO>> getProducAssetsInfo(ReqBMSEnumCodeVO reqBMSEnumCodeVO);
	
	/**
	 * 根据codeType查询枚举信息
	 */
	public ResListVO<ResBMSEnumCodeVO> listBy(ReqBMSEnumCodeVO reqBMSEnumCodeVO);
	
	/**
	 * 根据客户工作类型查询单位性质枚举
	 * 
	 * 传客户工作类型CODE
	 */
	public Response<List<ResBMSEnumCodeVO>> findCodeByUnit(ReqBMSEnumCodeVO reqBMSEnumCodeVO);
	
	
	/**
	 * 根据单位性质 类型查询职业枚举
	 * 
	 * 传单位性质类型CODE和parentCode(客户工作类型CODE)
	 * 
	 */
	public Response<List<ResBMSEnumCodeVO>> findCodeByProfession(ReqBMSEnumCodeVO reqBMSEnumCodeVO);
	
	
	/**
	 * 查询七个职业方法 
	 * @param reqBMSEnumCodeVO 
	 */
	public Response<List<ResBMSEnumCodeVO>> findByservenProfession(ReqBMSEnumCodeVO reqBMSEnumCodeVO);
}
