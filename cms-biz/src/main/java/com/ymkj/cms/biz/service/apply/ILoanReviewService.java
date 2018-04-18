package com.ymkj.cms.biz.service.apply;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.apply.ReqUpdReviewVO;
import com.ymkj.cms.biz.entity.apply.LoanReviewEntity;

public interface ILoanReviewService extends BaseService<LoanReviewEntity> {
	
	/**
	 * <p>Description:获取信申环节被拒绝的贷款记录</p>
	 * @uthor YM10159
	 * @date 2017年3月30日 下午3:54:02
	 * @param @param paramMap
	 */
	public List<LoanReviewEntity> getReviewList(Map<String,Object> paramMap);
	
	/**
	 * <p>Description:同步信审拒绝的贷款申请数据</p>
	 * @uthor YM10159
	 * @date 2017年3月1日 下午5:11:27
	 * @param @param loanReviewEntity
	 */
	public boolean insert(List<LoanReviewEntity> loanReviewEntityList);
	
	/**
	 * <p>Description:修改复议的贷款申请记录的状态</p>
	 * @uthor YM10159
	 * @date 2017年3月6日 上午10:31:39
	 * @param @return
	 */
	public int updateStatus(LoanReviewEntity loanReviewEntity);
	
	/**
	 * <p>Description:插入复议原因和备注</p>
	 * @uthor YM10159
	 * @date 2017年3月6日 下午2:07:29
	 * @param @param loanReviewEntity
	 * @param @return
	 */
	public int insertReviewReason(LoanReviewEntity loanReviewEntity);
	
	/**
	 * <p>Description:查询复议未处理的信息数</p>
	 * @uthor YM10159
	 * @date 2017年3月28日 下午4:17:58
	 * @param @param reqLoanReviewVo
	 */
	public int queryMessageCount(Map<String,Object> paramMap);
	
	/**
	 * <p>Description:修改已读未读状态</p>
	 * @uthor YM10159
	 * @date 2017年3月31日 上午10:12:11
	 * @param @param loanNoList
	 */
	public int updateIsReadStatus(String loanNo);
	
	
	public int updateOrSaveReviewStatus(Map<String,Object> map);
	
	public int updateBlackListIdByLoanNo(Map<String, Object> map);
	
	public LoanReviewEntity selectByLoanNo(String loanNo);
	
	public int saveLoanLog(ReqUpdReviewVO reqUpdateReviewReadStatusVO);
	
	public int updateReasonByNo(Map<String, Object> map);
}
