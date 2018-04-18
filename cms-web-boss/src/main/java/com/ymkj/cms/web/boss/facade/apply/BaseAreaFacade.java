package com.ymkj.cms.web.boss.facade.apply;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.cms.biz.api.service.master.IBMSBaseAreaExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBaseAreaVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBaseAreaVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;
import com.ymkj.cms.web.boss.exception.BusinessException;

@Component
public class BaseAreaFacade extends BaseFacade{
	@Autowired
	private IBMSBaseAreaExecuter bmsBaseAreaExecuter;
	
     public boolean deletelAllBaseArea(ReqBMSBaseAreaVO vo) {
    	 vo.setSysCode("1111111");
    	// 接口调用
    	 Response<ResBMSBaseAreaVO> response=bmsBaseAreaExecuter.deletelAllBaseArea(vo);
    	// 响应结果处理, 如果失败 则抛出 处理失败异常
 		if (response.isSuccess()) {
 			return new Boolean(response.getRepMsg());
 		} else {
 			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
 		}
	 }

	
	public ReqBMSBaseAreaVO addBaseArea(ReqBMSBaseAreaVO reqBaseAreaVO) throws BusinessException{
		// 请求参数构造
		reqBaseAreaVO.setSysCode("1111111");
   	    // 接口调用
		Response<ResBMSBaseAreaVO> response = bmsBaseAreaExecuter.addBaseArea(reqBaseAreaVO);
		
		/*reqDemoVO.setAreaId(response.getData().getAreaId());*/
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			ReqBMSBaseAreaVO req=new ReqBMSBaseAreaVO();
			req.setAreaId(response.getData().getAreaId());
			return req;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}
	}
	
	public boolean deleteById(String id) throws BusinessException{
		ReqBMSBaseAreaVO reqBaseAreaVO=new ReqBMSBaseAreaVO("1111111");
		reqBaseAreaVO.setAreaId(new Long((long)Integer.valueOf(id)));
    	// 请求参数构造
   	   // 接口调用
		Response<ResBMSBaseAreaVO> response = bmsBaseAreaExecuter.deleteById(reqBaseAreaVO);
		
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return new Boolean(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}
	}
	
	
	public ResListVO<ResBMSBaseAreaVO> findByName(ReqBMSBaseAreaVO reqBaseAreaVO){
		ResListVO<ResBMSBaseAreaVO> response=bmsBaseAreaExecuter.findByName(reqBaseAreaVO);
		
		/*if(response.isSuccess()){
			PageResult<ResProductBaseListVO> pageResult = new PageResult<ResProductBaseListVO>();
			BeanUtils.copyProperties(response, pageResult);*/
			return response;
		/*}else{
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}*/
		
	}
	
	
	public Response<ResBMSBaseAreaVO> getAllBaseAreaByCode(ReqBMSBaseAreaVO reqBaseAreaVO){
		reqBaseAreaVO.setSysCode("1111111");
		Response<ResBMSBaseAreaVO> response=bmsBaseAreaExecuter.getAllBaseAreaByCode(reqBaseAreaVO);
		if(response.isSuccess()){
			return response;
		}else{
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}
	
	
	public ResListVO<ResBMSBaseAreaVO> listBy(ReqBMSBaseAreaVO reqBaseAreaVO){
		reqBaseAreaVO.setSysCode("1111111");
		ResListVO<ResBMSBaseAreaVO> resp=bmsBaseAreaExecuter.listBy(reqBaseAreaVO);
		if(resp.isSuccess()){
			return resp;
		}else{
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(resp));
		}
	}
	

}
