package com.ymkj.cms.biz.service.sign.wmxt.ifc;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.MD5UtilLujs;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.ILuJSHttpService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * @Description:陆金所接口服务</p>
 * @uthor YM10159
 * @date 2017年7月7日 上午11:15:33
 */
@Service
public class WaiMaoXTInterfaceService{

	private Logger logger = LoggerFactory.getLogger(WaiMaoXTInterfaceService.class);

	/**
	 * 异常返回值处理
	 * 
	 * @param biz
	 * @param res
	 * @return
	 */
	protected Response setError(BizException biz, Response res) {
		res.setRepCode(biz.getRealCode());
		res.setRepMsg(biz.getMessage());
		return res;
	}
}
