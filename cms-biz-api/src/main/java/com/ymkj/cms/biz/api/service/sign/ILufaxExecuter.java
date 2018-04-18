package com.ymkj.cms.biz.api.service.sign;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLufax800001Vo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLujsInformVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLufax800001Vo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLujsInformVo;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ILufaxExecuter</p>
 * <p>Description:陆金所相关接口</p>
 * @uthor YM10159
 * @date 2017年7月14日 下午4:44:19
 */
public interface ILufaxExecuter {
	
	
	/**
	 * @Description:陆金所进件通知外部接口</p>
	 * @uthor YM10159
	 * @date 2017年7月14日 下午5:29:29
	 * @param reqLoanContractSignVo
	 */
	public Response<ResLufax800001Vo> inNoticeExternal(ReqLufax800001Vo lufax800001Vo);
	
	/**
	 * 查询陆金所返回信息
	 * @param lujsInformVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年7月19日下午1:42:35
	 */
	public Response<ResLujsInformVo> findlujsInform(ReqLujsInformVo lujsInformVo);
	
	/**
	 * 陆金所通知保存接口
	 * @param lufax800001Vo
	 * @return
	 * @author lix YM10160
	 * @date 2017年7月19日下午6:15:09
	 */
	public Response<ResLufax800001Vo> inNoticeExternalSave(ReqLufax800001Vo lufax800001Vo);
	
	/**
	 * @Description:根据人工审核结果做不同操作</p>
	 * @Description:录单点击办理的时候，根据审核结果到不同页面做不同操作
	 * @uthor YM10159
	 * @date 2017年7月20日 下午7:47:42
	 * @param lufax800001Vo
	 */
	public Response<ResLujsInformVo> dealWithAuditResult(ReqLujsInformVo lujsInformVo);
	
	
}
