package com.ymkj.cms.biz.facade.apply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.service.apply.ITrialBeforeCreditExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqTrialBeforeCreditVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResTrialBeforeCreditVO;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.ILoanTrialService;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:LoanTrialExecuter</p>
 * <p>Description:贷前试算服务方实现类</p>
 * @uthor YM10159
 * @date 2017年3月7日 下午5:35:27
 */
@Service
public class TrialBeforeCreditExecuter implements ITrialBeforeCreditExecuter {

	@Autowired
	private ILoanTrialService iLoanTrialService;

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public Response<List<ResTrialBeforeCreditVO>> trial(ReqTrialBeforeCreditVO reqLoanTrialVO) {

		boolean bool = false;
		Response<List<ResTrialBeforeCreditVO>> response = new Response<List<ResTrialBeforeCreditVO>>();
		Map<String, Object> paramsMap = new HashMap<>();
		HttpResponse httpResponse = null;
		Gson gson = new Gson();

		// 参数校验
		Response<Object> validateResponse = Validate.getInstance().validate(reqLoanTrialVO);
		if (validateResponse.isSuccess()) {
			bool = true;
		}else{
			bool = false;
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}

		if(bool){
			paramsMap.put("name", "0000");
			paramsMap.put("loanType", reqLoanTrialVO.getProductCode());
			paramsMap.put("money", reqLoanTrialVO.getApplyLmt());
			paramsMap.put("time", reqLoanTrialVO.getApplyTerm());
			paramsMap.put("firstRepaymentDate", reqLoanTrialVO.getFirstRepaymentDate());
			paramsMap.put("fundsSources", reqLoanTrialVO.getChannelCode());
			paramsMap.put("isRatePreferLoan", reqLoanTrialVO.getIfPreferentialUser());
			
			httpResponse = iLoanTrialService.createLoanTrial(paramsMap);
			
			Map<String,Object> contentMap = JsonUtils.decode(httpResponse.getContent(), Map.class);
			if(contentMap.get("code").equals("000000")){
				List<ResTrialBeforeCreditVO> resTrialBeforeCreditList = JsonUtils.decode(JsonUtils.encode(contentMap.get("repaymentDetail")), List.class);
				response.setData(resTrialBeforeCreditList);
			}else{
				response.setRepCode(ObjectUtils.toString(contentMap.get("code")));
			}
			response.setRepMsg(ObjectUtils.toString(contentMap.get("message")));
		}
		return response;
	}

}
