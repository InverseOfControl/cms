package com.ymkj.cms.biz.service.http;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.entity.sign.PictureDetail;


public interface IPictureService {
  /**
   * 根据文件类型编号、借款编号获取文件集合
   * 
   * @param requestMap
   * @return PictureDetail
   */
  public List<PictureDetail> queryPicFileList(Map<String, String> requestMap);

  /**
   * 根据文件类型编号、借款编号获取文件集合
   * 
   * @param httpParamMap
   * @return HttpResponse 返回原始响应值
   */
  public HttpResponse httpPicList(Map<String, String> httpParamMap);

  /**
   * 删除文件
   * 
   * @param loanNo
   * @return
   */
  public Map<String, String> delPicFile(Map<String, String> requestMap);

  /**
   * 删除图片
   * 
   * @param httpParamMap
   * @return
   */
  public HttpResponse delHttpPicFile(Map<String, String> httpParamMap);

  /**
   * 批量删除图片
   * 
   * @param httpParamMap
   * @return
   */
   public HttpResponse batchDelPicFile(Map<String, String> requestDel);
}
