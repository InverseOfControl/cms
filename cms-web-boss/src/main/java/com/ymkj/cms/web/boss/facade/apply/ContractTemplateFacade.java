package com.ymkj.cms.web.boss.facade.apply;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.master.IBMSContractTemplateExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractTemplateVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractTemplateVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.web.boss.exception.BusinessException;
@Component
public class ContractTemplateFacade extends BaseFacade{
	@Autowired
	private IBMSContractTemplateExecuter contractExecuter;
	
	public PageResult<ResBMSContractTemplateVO> listPage(ReqBMSContractTemplateVO reqBMSContractTemplateVO) {
		/* reqDemoVO.setSysCode("1111111"); */
		// 业务调用
		PageResponse<ResBMSContractTemplateVO> pageResponse = contractExecuter.listPage(reqBMSContractTemplateVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSContractTemplateVO> pageResult = new PageResult<ResBMSContractTemplateVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}
	/**
	 * 保存数据
	 * @param reqBMSContractTemplateVO
	 * @return
	 */
	public String saveTemplate(ReqBMSContractTemplateVO reqBMSContractTemplateVO){
		Response<ResBMSContractTemplateVO> response = contractExecuter.saveTemplate(reqBMSContractTemplateVO);
		// 响应结果处理
		return response.getRepMsg();
	}
	
	/**
	 * 更新数据
	 * @param reqBMSContractTemplateVO
	 * @return
	 */
	public String updateTemplate(ReqBMSContractTemplateVO reqBMSContractTemplateVO){
		Response<ResBMSContractTemplateVO> response = contractExecuter.updateTemplate(reqBMSContractTemplateVO);
		// 响应结果处理
		return response.getRepMsg();
	}
	
	/**
	 * 获取所有的模板数据根据条件
	 * @param reqChannelVO
	 * @return
	 */
	public ResListVO<ResBMSContractTemplateVO> getAllTemplate(ReqBMSContractTemplateVO reqBMSContractTemplateVO){
		ResListVO<ResBMSContractTemplateVO> resList = contractExecuter.getAllTemplate(reqBMSContractTemplateVO);
		return resList;
	}
	
	
	public ResBMSContractTemplateVO findByVO(ReqBMSContractTemplateVO reqContractTemplateVO) {
		// 请求参数构造
		reqContractTemplateVO.setSysCode(EnumConstants.BMS_RESPONSE_SYSCODE);
		// 接口调用
		Response<ResBMSContractTemplateVO> response = contractExecuter.findByVO(reqContractTemplateVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}
}
