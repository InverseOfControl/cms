package com.ymkj.cms.biz.api.service.app;

import java.util.List;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600001;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600002;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600003;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600004;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600005;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600006;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600007;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600008;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600013;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_900005;
import com.ymkj.cms.biz.api.vo.response.app.Res_VO_600006;
import com.ymkj.cms.biz.api.vo.response.app.Res_VO_600013;
import com.ymkj.cms.biz.api.vo.response.apply.ResTrialBeforeCreditVO;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:IAPPExecuter</p>
 * <p>Description:app接口</p>
 * @uthor YM10159
 * @date 2017年4月24日 上午11:05:12
 */
public interface IAPPExecuter {
	
	/**
	 * <p>Description:初始化app控件</p>
	 * @uthor YM10159
	 * @date 2017年4月13日 上午10:30:58
	 * @param vo_600001 请求对象
	 */
	public Response<Object> initField(Req_VO_600001 vo_600001);
	
	/**
	 * <p>Description:获取产品列表信息</p>
	 * @uthor YM10159
	 * @date 2017年4月24日 上午11:06:09
	 * @param vo_600002 请求对象
	 */
	public Response<Object> getProductListData(Req_VO_600002 vo_600002);
	
	/**
	 * <p>Description:申请录入</p>
	 * @uthor YM10159
	 * @date 2017年4月25日 上午11:45:42
	 * @param vo_600003 参数对象
	 */
	public Response<Object> saveApplyInfo(Req_VO_600003 vo_600003);
	
	/**
	 * <p>Description:对app录单进行规则校验</p>
	 * @uthor YM10159
	 * @date 2017年6月10日 上午11:51:53
	 * @param vo_600005 请求参数对象
	 */
	public Response<Object> checkIDCard(Req_VO_600005 vo_600005);
	
	/**
	 * <p>Description:保存取消信息</p>
	 * @uthor YM10139
	 * @date 2017年5月2日 上午11:45:42
	 * @param vo_600003 参数对象
	 */
	public Response<Object> saveCancelApplyInput(Req_VO_600004 vo_600004);
	
	
	/**
	 * <p>Description:历史申请状态查询接口</p>
	 * @uthor YM10139
	 * @date 2017年5月2日 上午11:45:42
	 * @param vo_600003 参数对象
	 */
	public PageResponse<Res_VO_600006> getHisApplyInputList(Req_VO_600006 vo_600006);
	
	
	/**
	 * <p>Description:查询首页信息</p>
	 * @uthor YM10139
	 * @date 2017年5月2日 下午15:45:42
	 * @param vo_600003 参数对象
	 */
	public	Response<Object> getfirstPageData(Req_VO_600007 vo_600007);
	
	/**
	 * <p>Description:贷前试算</p>
	 * @uthor YM10159
	 * @date 2017年4月24日 下午5:30:10
	 * @param req_VO_600008 请求对象
	 */
	public Response<List<ResTrialBeforeCreditVO>> getTrialBeforeCredit(Req_VO_600008 req_VO_600008);
	/**
	 * 保存拒绝信息
	 * @param vo_600004
	 * @return
	 * @author lix YM10160
	 * @date 2017年6月19日上午11:24:00
	 */
	public Response<Object> saveRefuseApplyInput(Req_VO_600004 vo_600004);
	
	/**
	 * 	影像文件补录申请件列表查询接口
	 * * @param vo_600013
	 * 	2017-07-18
	 */
	public Response<Res_VO_600013> getImageFileUpload(Req_VO_600013 req_VO_600013);
	
	/**
	 * @Description:插入借款日志</p>
	 * @uthor YM10159
	 * @date 2017年8月14日 下午6:04:32
	 * @param req_VO_600013
	 */
	public Response<Object> insertLoanLog(Req_VO_600001 vo_600001);
	
	/**
	 * @Description:点击下一步保存借款信息（综合查询用到）</p>
	 * @uthor YM10159
	 * @date 2017年8月14日 下午6:04:32
	 * @param req_VO_600013
	 */
	Response<Object> saveLoanBaseInfo(Req_VO_600005 vo_600005);
	
	/**
	 * @Description：手机号重复校验
	 * @uthor YM10159
	 * @date 2017年11月13日 下午6:04:32
	 * @param req_VO_900005
	 */
	Response<Object> validatePhoneRepeat(Req_VO_900005 req_VO_900005);

}
