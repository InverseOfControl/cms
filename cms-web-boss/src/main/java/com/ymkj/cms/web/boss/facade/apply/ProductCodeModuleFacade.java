package com.ymkj.cms.web.boss.facade.apply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.cms.biz.api.service.master.IBMSProductCodeModuleExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductCodeModuleVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductCodeModuleVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;
import com.ymkj.cms.web.boss.exception.BusinessException;

@Component
public class ProductCodeModuleFacade extends BaseFacade{

	@Autowired
	private IBMSProductCodeModuleExecuter ibmsProductCodeModuleExecuter;
	
	/**
	 * 添加产品模块
	 * @param reqProductAuditLimitVO
	 * @return
	 * @throws BusinessException
	 */
	public boolean saveProductCodeModules(ReqBMSProductCodeModuleVO reqProductCodeModuleVO) throws BusinessException {
				// 请求参数构造
				reqProductCodeModuleVO.setSysCode("1111111");
		    	// 接口调用
		 		Response<ResBMSProductCodeModuleVO> response = new Response<ResBMSProductCodeModuleVO>();
		 		response=ibmsProductCodeModuleExecuter.saveProductCodeModules(reqProductCodeModuleVO);
		 		
		 		// 响应结果处理, 如果失败 则抛出 处理失败异常
		 		if (response.isSuccess()) {
		 			return true;
		 		} else {
		 			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		 		}
    	
    }
	
	public ResProductBaseListVO findProCodeModulesByProId(ReqBMSProductCodeModuleVO reqProductCodeModuleVO) {
		
		// 请求参数构造
		reqProductCodeModuleVO.setSysCode("1111111");
    	// 接口调用
		Response<ResProductBaseListVO> response=ibmsProductCodeModuleExecuter.findProCodeModulesByProId(reqProductCodeModuleVO);
		
		ResProductBaseListVO rsBaseListVO=response.getData();
		
		return rsBaseListVO;
 	
		
	}
}
