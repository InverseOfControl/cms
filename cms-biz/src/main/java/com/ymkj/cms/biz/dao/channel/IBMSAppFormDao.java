package com.ymkj.cms.biz.dao.channel;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.api.vo.request.channel.ReqFileBatchNumVo;
import com.ymkj.cms.biz.api.vo.response.channel.LoanApplyDetailVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResAppBookVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResRepaymentExpVo;
import com.ymkj.cms.biz.entity.channel.BMSAppForm;
import com.ymkj.cms.biz.entity.channel.RequestFileOperateRecord;

/**
 * @author YM10189
 * @date 2017年5月3日
 * @Description:申请书管理dao inerface
 */
public interface IBMSAppFormDao extends BaseDao<BMSAppForm> {

	/**
	 * 划拨申请书查询
	 * 
	 * @param paraMap
	 * @return
	 */
	public ResAppBookVo findAppBook(Map<String, Object> paraMap);

	/**
	 * 还款计划查询
	 * 
	 * @param paraMap
	 * @return
	 */
	public List<ResRepaymentExpVo> listLoanRepaymentExp(Map<String, Object> paraMap);

	/**
	 * 放款申请明细查询
	 * 
	 * @param paraMap
	 * @return
	 */
	public List<LoanApplyDetailVo> listLoanAppDetailExp(Map<String, Object> paraMap);

	/**
	 * 申请书记录查询
	 * 
	 * @param requestFileOperateRecord
	 * @return
	 */
	public List<RequestFileOperateRecord> findRequestFileOperateRecord(RequestFileOperateRecord requestFileOperateRecord);

	/**
	 * 申请书记录插入
	 * 
	 * @param requestFileOperateRecord
	 * @return
	 */
	public long insertRequestFileOpr(RequestFileOperateRecord requestFileOperateRecord);

	/**
	 * 申请书记录更新
	 * 
	 * @param requestFileOperateRecord
	 * @return
	 */
	public long updateRequestFileOpr(RequestFileOperateRecord requestFileOperateRecord);

	/**
	 * 查询当天文件下载批次号
	 * 
	 * @param paraMap
	 * @return
	 */
	public String findRequestFileBatchNum(ReqFileBatchNumVo requestVo);

	public List<RequestFileOperateRecord> queryCurrentDayRequestFileOperateRecordByAsc(Map<String, Object> paramMap);

}
