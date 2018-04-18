package com.ymkj.cms.web.boss.controller.channel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.cms.biz.api.constant.LinePaymentConst;
import com.ymkj.cms.biz.api.vo.request.channel.ExcelObjVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqUnderLinePaymentVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResBmsLineOfferVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLinePaymentOfferBatch;
import com.ymkj.cms.web.boss.common.DateUtil;
import com.ymkj.cms.web.boss.service.channel.IExcelExport;
import com.ymkj.cms.web.boss.service.channel.ILinePaymentService;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.sso.client.ShiroUtils;

/**
 * @author YM10189
 * @date 2017年5月17日
 * @Description:报盘控制器
 */
@Controller
@RequestMapping("linePayment")
public class LinePaymentController extends BaseController {

	public static String[] REPOFFERSUMMARY_EXP_FIELDNAMES = { "交易标志", "商户ID", "提交日期", "总记录数", "总金额", "业务类型" };
	public static String[] REPOFFERSUMMARY_FIELDCODES = { "tradeMark", "merchantId", "commitTime", "recordsTotal", "amountTotal", "businessType" };
	public static Class<?> REPOFFERSUMMARY_EXP_CLASS = ResLinePaymentOfferBatch.class;

	public static String[] REPOFFER_EXP_FIELDNAMES = { "记录序号", "通联支付用户编号", "银行代码", "帐号类型", "账号", "户名", "开户行所在省", "开户行所在市", "开户行名称", "账户类型", "金额",
			"货币类型", "协议号", "协议用户编号", "开户证件类型", "证件号", "手机号/小灵通", "自定义用户号", "备注", "反馈码", "原因" };
	public static String[] REPOFFER_FIELDCODES = { "recordNumber", "tlPaymentNumber", "bankCode", "accountNumberType", "accountNumber", "accountName",
			"bankProvince", "bankCity", "bankName", "accountType", "amount", "currencyType", "protocolNumber", "protocolUserNumber",
			"certificateType", "licenseNumber", "telNumber", "customUserNumber", "remark", "feedbackCode", "reason" };
	public static Class<?> REPOFFER_EXP_CLASS = ResBmsLineOfferVo.class;

	@Autowired
	private ILinePaymentService iLinePaymentService;

	@Autowired
	private IExcelExport iExcelExport;

	@RequestMapping("/createOffer")
	@ResponseBody
	public Response<Object> createHaTwoOffer(ReqUnderLinePaymentVo request) {
		Response<Object> response = iLinePaymentService.createOffer(request);
		return response;
	}

	@RequestMapping("/exportOffer")
	@SuppressWarnings("unchecked")
	public void exportOffer(HttpServletResponse response, ReqUnderLinePaymentVo request) {
		try {
			Response<Map<String, Object>> dataMap = iLinePaymentService.exportOffer(request);
			Response<Object> resp = new Response<Object>();
			// 新增：如果没有数据，直接对外返回
			if (dataMap.getData() == null) {
				resp.setRepMsg(dataMap.getRepMsg());
				resp.setRepCode(dataMap.getRepCode());
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(JSONObject.toJSONString(resp));
				return;
			}
			StringBuilder buildFileName = new StringBuilder();
			buildFileName.append(String.valueOf(dataMap.getData().get("merchantId")));
			buildFileName.append("_");
			buildFileName.append(LinePaymentConst.TRADE_MARK_F);
			buildFileName.append(LinePaymentConst.VERSION);
			buildFileName.append(DateUtil.getDate(new Date(), DateUtil.pattern_day));
			buildFileName.append("_");
			buildFileName.append(String.valueOf(dataMap.getData().get("batchNum")));
			buildFileName.append(".xls");

			ExcelObjVo excelVoSum = new ExcelObjVo(buildFileName.toString(), "", 1, 0);
			ExcelObjVo excelVo = new ExcelObjVo(buildFileName.toString(), "", 3, 0);
			Map<String, String[]> fieldsSum = new HashMap<String, String[]>();
			fieldsSum.put("fieldNames", LinePaymentController.REPOFFERSUMMARY_EXP_FIELDNAMES);
			fieldsSum.put("fieldCodes", LinePaymentController.REPOFFERSUMMARY_FIELDCODES);

			Map<String, String[]> fields = new HashMap<String, String[]>();
			fields.put("fieldNames", LinePaymentController.REPOFFER_EXP_FIELDNAMES);
			fields.put("fieldCodes", LinePaymentController.REPOFFER_FIELDCODES);

			List<ResLinePaymentOfferBatch> summaryList = (List<ResLinePaymentOfferBatch>) dataMap.getData().get("summaryData");
			List<ResBmsLineOfferVo> dataList = (List<ResBmsLineOfferVo>) dataMap.getData().get("data");

			Workbook workBook = iExcelExport.buildWork(summaryList, fieldsSum, LinePaymentController.REPOFFERSUMMARY_EXP_CLASS, excelVoSum);
			workBook = iExcelExport.buildWork(workBook, dataList, fields, LinePaymentController.REPOFFER_EXP_CLASS, excelVo);
			iExcelExport.wirteExcel(workBook, response, buildFileName.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/importOffer")
	@ResponseBody
	public Response<Object> importOffer(@RequestParam(value = "offerFile") MultipartFile file, HttpServletRequest request, HttpServletResponse response,
			ReqUnderLinePaymentVo requestVo) {
		Response<Object> resp = new Response<Object>();
		try {
			ResEmployeeVO user = ShiroUtils.getCurrentUser();
			List<Map<String, String>> dataList = iExcelExport.importOfferExcel(file.getInputStream());
			requestVo.setDatas(dataList);
			requestVo.setServiceCode(user.getUsercode());
			requestVo.setServiceCode(user.getName());
			Response<Object> resObj = iLinePaymentService.importCounteroffer(requestVo);
			resp.setRepCode(resObj.getRepCode());
			resp.setRepMsg(resObj.getRepMsg());
		} catch (Exception e) {
			e.printStackTrace();
			resp.setRepCode(e.getMessage());
		}
		return resp;
	}

}
