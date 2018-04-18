package com.ymkj.cms.biz.service.sign.waimao.three.ifc;

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
public class WaiMaoTInterfaceService{


	@Autowired
	private ILuJSHttpService luJSHttpService;
	@Autowired
	private ILoanSignDataOprateService loanSignDataOprateService;
	

	//陆金所网关
	@Value("#{env['requestByUrl']?:''}")
	private String requestByUrl;

	@Value("#{env['requestByKey']?:''}")
	private String sxKey;

	private Logger logger = LoggerFactory.getLogger(WaiMaoTInterfaceService.class);

	/**
	 * @Description:陆金所文件上传接口</p>
	 * @uthor YM10159
	 * @date 2017年7月7日 下午4:37:43
	 */
	public boolean preliminaryAudit(Map<String, Object> httpParamMap, Response<ResLoanContractSignVo> res) {
		
		Map<String, Object> params = (Map<String, Object>) httpParamMap.get("params");
		try{
			String dataTime =DateUtil.defaultFormatMss(new Date());
			httpParamMap.put("key", dataTime);
			params.put("requestId", "aps"+dataTime);
			String paramsString= JsonUtils.encode(params);
			String encryptionString="wm3_2004"+paramsString+dataTime+sxKey;
			logger.info("生成验签的字符串:{}，生成的签名{}",encryptionString,MD5UtilLujs.md5Hex(encryptionString));
			httpParamMap.put("sign", MD5UtilLujs.md5Hex(encryptionString));

			HttpResponse httpResponse = luJSHttpService.lufaxGate(httpParamMap);

			if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
				String content = httpResponse.getContent();
				Map contentMap = JsonUtils.decode(content, Map.class);
				if(contentMap != null && EnumConstants.RES_CODE_SUCCESS_SHORT.equals(contentMap.get("resCode"))){
					Map resInfo = (Map) contentMap.get("infos");
					if (resInfo != null && EnumConstants.RES_CODE_SUCCESS_SHORT.equals(resInfo.get("respCode")) && resInfo.get("content") != null) {
						Map resInfoCont = JsonUtils.decode(resInfo.get("content").toString(), Map.class);
						if(resInfoCont !=null && resInfoCont.get("dealSts") != null){
							if("1".equals(resInfoCont.get("dealSts"))){
								httpParamMap.put("lockTarget", "Y");
								return true;
							} else {
								httpParamMap.put("lockTarget", "N");
								setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,resInfoCont.get("dealDesc").toString()), res);
								return false;
							}
						}else if(resInfoCont != null){
							setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,resInfoCont.get("dealDesc").toString()), res);
							return false;
						} else {
							setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"返回参数错误resInfoCont"), res);
							return false;
						}
					} else if(resInfo != null){
						setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,resInfo.get("respDesc").toString()), res);
						return false;
					} else{
						setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"返回参数错误resInfo"), res);
						return false;
					}

				} else if(contentMap != null){
					setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,contentMap.get("respDesc").toString()), res);
					return false;
				} else {
					setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"返回参数错误contentMap"), res);
					return false;
				}

			} else{
				setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"接口响应失败"), res);
				return false;
			}

		}catch(Exception e){
			setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"获取反欺诈接口入参错误"+e), res);
			return false;
		}
	}
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
