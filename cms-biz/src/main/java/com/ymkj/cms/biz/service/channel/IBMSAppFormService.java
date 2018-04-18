package com.ymkj.cms.biz.service.channel;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.channel.ReqFileBatchNumVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqdealEsignatureVo;
import com.ymkj.cms.biz.api.vo.response.channel.LoanApplyDetailVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResAppBookVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResRepaymentExpVo;
import com.ymkj.cms.biz.entity.channel.BMSAppForm;
import com.ymkj.cms.biz.entity.channel.BMSApplyBookInfo;

/**
 * @author YM10189
 * @date 2017年5月3日
 * @Description:申请书service interface
 */
public interface IBMSAppFormService extends BaseService<BMSAppForm>{
	public ResAppBookVo findAppBook(Map<String,Object> paraMap);
	public List<ResRepaymentExpVo> listLoanRepaymentExp(Map<String,Object> paraMap);
	public List<LoanApplyDetailVo> listLoanAppDetailExp(Map<String,Object> paraMap);
	public String findRequestFileBatchNum(ReqFileBatchNumVo requestVo);
    public long createApplyBookInfos(Map<String,Object> paraMap);
    public boolean isUploadApplyBook(String batchNum);
	public boolean isCurrentDayDownloadFirst(Map<String, Object> paraMap);
	public boolean isCurrentDayFirstBarApplyBookInfo(Map<String, Object> paraMap);
	public BigDecimal getDiffMoney(Map<String, Object> paraMap);
	public long  insertApplyBookInfo(BMSApplyBookInfo applyBookInfo);
	public String getFundsSources(String projectCode);
	public String getProjectCode(String batchNum);
	public boolean importAppBook(ReqdealEsignatureVo requestVo);
}