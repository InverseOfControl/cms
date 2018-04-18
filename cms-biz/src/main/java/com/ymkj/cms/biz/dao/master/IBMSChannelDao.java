package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.entity.master.BMSChannel;

public interface IBMSChannelDao extends BaseDao<BMSChannel>{

	BMSChannel findOne(ReqBMSChannelVO reqChannelVO);
	/**
	 * 根据用户code(门店id)获取数据
	 * @param paramMap
	 * @return
	 */
	public List<BMSChannel>  getChannelByOrgId(Map<String, Object> paramMap);
	
	/**
	 * <p>Description:根据借款产品、申请期限、申请额度、营业部获取渠道列表</p>
	 * @uthor YM10159
	 * @date 2017年3月16日 上午10:26:26
	 * @param @param paramMap
	 */
	public List<BMSChannel>  getChannelByProTermLmt(Map<String, Object> paramMap);
	
	
	/**
	 * <p>Description:根据进件营业部,借款产品,审批额度获取渠道列表</p>
	 * @uthor YM10159
	 * @date 2017年3月16日 上午10:26:26
	 * @param @param paramMap
	 */
	public List<BMSChannel>  getChannelByOrgProAlt(Map<String, Object> paramMap);
	
	
	/**
	 * 检测渠道是否已存在
	 */
	public int checkIsChennelExits(Map<String, Object> paramMap);
	/**
	 * 查找渠道，包含禁用，过期
	 * @param paramMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月18日上午10:04:29
	 */
	public List<BMSChannel> findBy(Map<String, Object> paramMap);
	/**
	 * 渠道产品配置禁用或启用
	 * @param paramMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月18日下午2:31:12
	 */
	public int disabledLimitChannel(Map<String, Object> paramMap);
	/**
	 * 网点产品配置禁用或启用
	 * @param paramMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月18日下午2:33:10
	 */
	public int disabledOrgLimitChannel(Map<String, Object> paramMap);
	
	/**
	 * 查询渠道起止日和和结束日期当中有当前系统日期的信息
	 */
	public List<BMSChannel> findChannelEqDate(Map<String, Object> paramMap);
	/**
	 * 签约银行配置禁用或启用
	 * @param paramMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月24日下午4:06:20
	 */
	int disabledChannelBank(Map<String, Object> paramMap);
	
	/**
	 * @Description:渠道网关适配接口</p>
	 * @uthor YM10159
	 * @date 2017年7月10日 下午2:16:34
	 * @param loanNo 借款编号
	 */
	public int isExistInBMS(String loanNo);
	
}
