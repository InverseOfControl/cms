package com.ymkj.cms.biz.service.http;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;

import org.json.JSONObject;

import java.util.Map;

public interface BaoShangHttpService {
	
	/**
	 * 
	 * @Title: baoShangUrl 
	 * @Description: 包商银行提供的接口
	 * @author lisc@yuminsoft.com
	 * @date: 2017年6月9日 上午9:55:49 
	 * @param httpParamMap
	 * @return
	 */
	public HttpResponse baoShangUrl(String interfaceCode,JSONObject dataJson);
	
	/**
	 * 
	 * @Title: baoShangPic 
	 * @Description: 调用pic接口获取业务环节下的目录
	 * @author lisc@yuminsoft.com
	 * @date: 2017年6月12日 下午2:43:57 
	 * @param map
	 * @return
	 */
	public HttpResponse baoShangPic(Map<String, String> map);
	
	/**
	 * 
	 * @Title: baoShangPicFile 
	 * @Description: 调用pic接口目录下上传的文件
	 * @author lisc@yuminsoft.com
	 * @date: 2017年6月14日 下午1:55:48 
	 * @param map
	 * @return
	 */
	public HttpResponse baoShangPicFile(Map<String, String> map);
	
	/**
	 * 
	 * @Title: loanRepeal 
	 * @Description: 撤销申请
	 * @author lisc@yuminsoft.com
	 * @date: 2017年6月15日 上午9:48:53 
	 * @param idType
	 * @param idNo
	 * @param custName
	 * @param busNumber
	 * @return
	 */
	public Map<String, String> loanRepeal(String idType,String idNo,String custName,String busNumber);

	/**
	 * 包银公用的撤销接口
	 * @param loanNo
	 * @return
	 */
	public boolean BaoShangLoanRepeal(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res);

	/**
	 * 调用pic上传文档接口
	 * @param map
	 * @return
	 */
	public HttpResponse picFileUpload(Map<String, String> map);

	/**
	 * 根据签约营业部ID获取营业部所在城市
	 * @param id
	 * @return
	 */
	public String getContractBranchCityNameById(Long id);
}
