package com.ymkj.cms.biz.facade.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.master.IBMSRejectReasonExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSRejectReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSRejectReasonListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSRejectReasonVO;
import com.ymkj.cms.biz.entity.master.BMSRejectReason;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSRejectReasonService;
@Service
public class BMSRejectReasonExecuter implements IBMSRejectReasonExecuter{
	@Autowired
	private IBMSRejectReasonService sysRejectReasonService;

	@Override
	public Response<ResBMSRejectReasonListVO>  listBy(ReqBMSRejectReasonVO reqRejectReasonVO) {
		Response<ResBMSRejectReasonListVO> response = new Response<ResBMSRejectReasonListVO>();
		ResBMSRejectReasonListVO listVO = new ResBMSRejectReasonListVO();
		// 参数校验 第二种方式 
		if(StringUtils.isEmpty(reqRejectReasonVO.getType())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"type"});
		}else{
			if(StringUtils.isEmpty(reqRejectReasonVO.getParentId())){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"parentId"});
			}
			
		}
		try {
			// 构造请求参数
			Map<String, Object> paramMap = BeanKit.bean2map(reqRejectReasonVO);
			List<BMSRejectReason> list = sysRejectReasonService.listBy(paramMap);
			if (list != null && list.size() > 0) {
				List<ResBMSRejectReasonVO> records = new ArrayList<ResBMSRejectReasonVO>();
				for (BMSRejectReason log : list) {
					ResBMSRejectReasonVO vo = new ResBMSRejectReasonVO();
					BeanUtils.copyProperties(log, vo);
					records.add(vo);
				}
				listVO.setResBMSRejectReasonList(records);
			}
			response.setData(listVO);
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	
	}

	 

	 

}
