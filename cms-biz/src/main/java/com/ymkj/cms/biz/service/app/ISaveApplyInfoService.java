package com.ymkj.cms.biz.service.app;

import java.util.Map;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600001;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600003;
import com.ymkj.cms.biz.entity.app.BMSSaveApplyInfoEntity;

public interface ISaveApplyInfoService extends BaseService<BMSSaveApplyInfoEntity> {
	
	//插入申请记录
	public Response<Object> insertApplyInfo(Map<String,Object> allApplyInfoMap) throws Exception;

	public Response<Object> insertCancelApplyInfo(Map<String, Object> allApplyInfoMap) throws Exception;
	/**
	 * 保存拒绝信息
	 * @param allApplyInfoMap
	 * @return
	 * @throws Exception
	 * @author lix YM10160
	 * @date 2017年6月19日上午11:25:59
	 */
	public Response<Object> insertRefuseApplyInfo(Map<String, Object> allApplyInfoMap) throws Exception;
	
	/**
	 * @Description:插入借款日志</p>
	 * @uthor YM10159
	 * @date 2017年8月14日 下午7:16:03
	 * @param req_VO_600001
	 */
	public Response<Object> insertLoanLog(Req_VO_600001 req_VO_600001);

	public Response<Object> saveLoanBaseInfo(Map<String, Object> allApplyInfoMap);
	
	/**
	 * @Description:app提交字段校验</p>
	 * @uthor YM10159
	 * @date 2017年11月06日 下午7:16:03
	 * @param req_VO_600003
	 */
	public Response<Object> validateField(Req_VO_600003 req_VO_600003);
	
	/**
	 * @Description:app提交字段校验</p>
	 * @uthor YM10159
	 * @date 2017年11月06日 下午7:16:03
	 * @param vo_600014
	 */
	public Map<String,Object> getValidatePhoneList(Map<String,Object> allApplyInfoMap);
}
