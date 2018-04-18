package com.ymkj.cms.web.boss.service.master;

import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

public interface ITmReasonService {
	
	public ResListVO<ReqBMSTMReasonVO> oneLevel(ReqBMSTMReasonVO reqBMSTMReasonVO);
	
	public ResListVO<ReqBMSTMReasonVO> twoLevel(ReqBMSTMReasonVO reqBMSTMReasonVO);
}
