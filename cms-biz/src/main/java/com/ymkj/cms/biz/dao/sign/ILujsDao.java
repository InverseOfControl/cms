package com.ymkj.cms.biz.dao.sign;

import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLufax800001Vo;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ILujsDao</p>
 * <p>Description:陆金所相关查询</p>
 * @uthor YM10159
 * @date 2017年7月11日 下午2:58:00
 */
public interface ILujsDao extends BaseDao<BMSLoanBaseEntity>{
	
	/**
	 * @Description:获取陆金所申请信息</p>
	 * @uthor YM10159
	 * @date 2017年7月11日 下午3:00:18
	 */
	public Map<String,Object> getApplicationList(String loanNo);
	
	/**
	 * @Description:保存陆金所进件流水号</p>
	 * @uthor YM10159
	 * @date 2017年7月13日 下午7:26:32
	 */
	public int saveApsApplyNo(Map<String,Object> paramsMap);
	
	/**
	 * @Description:保存陆金所外部进件通知接口响应数据</p>
	 * @uthor YM10159
	 * @date 2017年7月15日 下午4:46:30
	 * @param paramsMap
	 */
	public int saveLufaxNoticeExternal(ReqLufax800001Vo reqVo);
	
	/**
	 * @Description:通过借款编号获取loanBaseId</p>
	 * @uthor YM10159
	 * @date 2017年7月18日 下午3:40:19
	 * @param loanNo
	 */
	public String getLoanBaseIdByLoanNo(String loanNo);
	
	/**
	 * @Description:获取人工审核返回结果</p>
	 * @uthor YM10159
	 * @date 2017年7月20日 下午5:52:06
	 * @param loanNo
	 */
	public Map<String,Object> getAuditReturnResult(String loanNo);
	
	/**
	 * @Description:删除陆金所人工审核结果</p>
	 * @uthor YM10159
	 * @date 2017年7月23日 下午3:15:39
	 * @param loanNo
	 */
	public void delLujsManualAnditInfo(String loanNo);
	
	/**
	 * 获取申请人信息
	 * @param loanNo
	 */
	public Map<String,Object> getPersonInfo(String loanNo);
	
	public Map<String,Object> getAppPersonInfo(String loanNo);
	
	/**
	 * 
	 * @Description:修改证件到期日</p>
	 * @uthor YM10159
	 * @date 2017年10月19日 上午10:51:28
	 * @param loanNo 借款编号
	 */
	public int updateIdLastDate(Map<String,Object> map);
	
}
