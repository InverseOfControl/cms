package com.ymkj.cms.biz.service.http.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;
import com.ymkj.base.core.common.http.CommonUtil;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.base.core.common.http.HttpUtil;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.entity.sign.PictureDetail;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.IPictureService;

/**
 * 封装PIC接口
 * 
 * @author CHENTW
 *
 */
@Service
public class PictureServiceImpl implements IPictureService {

  // 获取文件集合接口
  @Value("#{env['getIDCardPicUrl']?:''}")
  private String getIDCardPicUrl;

  // 文件删除地址
  @Value("#{env['deletePicUrl']?:''}")
  private String deletePicUrl;

  // 批量文件删除地址
  @Value("#{env['batchDeleteUrl']?:''}")
  private String batchDeleteUrl;
 
  /**
   * 根据文件类型编号、借款编号获取文件集合
   * 
   * @param requestMap
   * @return PictureDetail
   */
  @Override
  public List<PictureDetail> queryPicFileList(Map<String, String> requestMap) {
    // 调用pic查询接口
    List<PictureDetail> pictureDetailList = new ArrayList<PictureDetail>();
    HttpResponse httpResponse = httpPicList(requestMap);
    // 请求响应成功验证
    if (httpResponse != null && httpResponse.getContent() != null && EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()) {
  /*    JSONObject jsonObj = JsonUtils.fromObject(httpResponse.getContent());
      JSONArray jsonArr = jsonObj.getJSONArray("result");
      for (int i = 0; i < jsonArr.size(); i++) {
        JSONObject obj = jsonArr.getJSONObject(i);
        PictureDetail bean = (PictureDetail) JSONObject.toBean(obj, PictureDetail.class);
        pictureDetailList.add(bean);
      }*/
    	pictureDetailList =JsonUtils.decode(httpResponse.getContent(),ArrayList.class);
    } else {
      throw new BizException(BizErrorCode.DB_RESULT_ERROR, "PIC系统图片查询相应失败！");
    }
    return pictureDetailList;
  }

  /**
   * 根据文件类型编号、借款编号获取文件集合
   * 
   * @param httpParamMap
   * @return HttpResponse 返回原始响应值
   */
  @Override
  public HttpResponse httpPicList(Map<String, String> httpParamMap) {
    try {
      String urlParam = CommonUtil.map2UrlParams(httpParamMap);
      // 调用接口
      System.err.println("人身份证信息接口，借款请求PMS：[{}]" + urlParam);
      long currentTime = System.currentTimeMillis();
      System.err.println("调用人身份证信息接口前的时间：[{}]" + currentTime);

      HttpResponse httpResponse = HttpUtil.post(getIDCardPicUrl, urlParam, false);
      System.err.println("调用人身份证信息接口后的时间：[{}]" + System.currentTimeMillis());
      System.err.println("调用人身份证信息接口用时：[{}]" + (System.currentTimeMillis() - currentTime));
      System.err.println("人身份证信息接口，PMS返回json信息：[{}]" + httpResponse.getCode() + "|" + httpResponse.getMessage());
      return httpResponse;
    } catch (Exception e) {
      throw new BizException(CoreErrorCode.FACADE_ERROR, "调用人身份证信息接口发生异常");
    }
  }

  /**
   * 删除文件
   * 
   * @param loanNo
   * @return
   */
  @Override
  public Map<String, String> delPicFile(Map<String, String> requestMap) {
    Map<String, String> resultMap = new HashMap<String, String>();
    // // map.put("ids", "1398");//文件Id集合,12,13,14
    // map.put("ids", ids);// 文件Id集合,12,13,14
    // map.put("ifWaste", "Y");// 是否作废,Y或者N
    // map.put("operator", EnumConstants.AITE_USER_NAME);// 操作人姓名
    // map.put("jobNumber", EnumConstants.AITE_USER_CODE);// 工号

    HttpResponse httpResponse = delHttpPicFile(requestMap);
    if (EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()) {
      String content = httpResponse.getContent();
      Map<String, String> contentMap = JsonUtils.decode(content, Map.class);
      if (Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("errorcode"))) {
        resultMap.put("code", Response.SUCCESS_RESPONSE_CODE);
        resultMap.put("message", "success");
      } else {
        resultMap.put("code", contentMap.get("errorcode"));
        resultMap.put("message", contentMap.get("crrormsg"));
      }
    } else {
      throw new CoreException(CoreErrorCode.FACADE_RESPONSE_FAIL, "接口调用返回失败消息");
    }
    return resultMap;
  }

  /**
   * 删除图片
   * 
   * @param httpParamMap
   * @return
   */
  @Override
  public HttpResponse delHttpPicFile(Map<String, String> httpParamMap) {
    try {
      String urlParam = CommonUtil.map2UrlParams(httpParamMap);

      // 调用接口
      System.err.println("文件删除接口，借款请求PMS：[{}]" + urlParam);
      long currentTime = System.currentTimeMillis();
      System.err.println("调用文件删除接口前的时间：[{}]" + currentTime);

      HttpResponse httpResponse = HttpUtil.post(deletePicUrl, urlParam, false);

      System.err.println("调用文件删除接口后的时间：[{}]" + System.currentTimeMillis());
      System.err.println("调用文件删除接口用时：[{}]" + (System.currentTimeMillis() - currentTime));
      System.err.println("文件删除接口，PMS返回json信息：[{}]" + httpResponse.getCode() + "|" + httpResponse.getMessage());

      return httpResponse;
    } catch (Exception e) {
      throw new BizException(CoreErrorCode.FACADE_ERROR, "调用文件删除接口发生异常");
    }

  }

  @Override
  public HttpResponse batchDelPicFile(Map<String, String> httpParamMap) {
	 try {
	      String urlParam = CommonUtil.map2UrlParams(httpParamMap);
	      // 调用接口
	      BmsLogger.info("批量文件删除接口，借款请求PIC：[{}]" + urlParam);
	      long currentTime = System.currentTimeMillis();
	      BmsLogger.info("调用批量文件删除接口前的时间：[{}]" + currentTime);
	      HttpResponse httpResponse = HttpUtil.post(batchDeleteUrl, urlParam, false);
	      BmsLogger.info("调用批量文件删除接口后的时间：[{}]" + System.currentTimeMillis());
	      BmsLogger.info("调用批量文件删除接口用时：[{}]" + (System.currentTimeMillis() - currentTime));
	      BmsLogger.info("文件删除接口，PIC返回json信息：[{}]" + httpResponse.getCode() + "|" + httpResponse.getMessage());
	      return httpResponse;
	    } catch (Exception e) {
	      throw new BizException(CoreErrorCode.FACADE_ERROR, "调用文件删除接口发生异常");
	    }
}

}
