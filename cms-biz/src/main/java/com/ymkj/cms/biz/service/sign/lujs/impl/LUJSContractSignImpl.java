package com.ymkj.cms.biz.service.sign.lujs.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.dao.apply.APPPersonInfoDao;
import com.ymkj.cms.biz.dao.sign.ILujsDao;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.base.SignLoanContractImpl;
import com.ymkj.cms.biz.service.sign.lujs.LUJSUtils;
import com.ymkj.cms.biz.service.sign.lujs.ifc.LUJSInterfaceService;

/**
 * @Description:合同签订</p>
 * @uthor YM10159
 * @date 2017年7月7日 上午11:15:33
 */
@Service
public class LUJSContractSignImpl extends SignLoanContractImpl {

	@Value("#{env['nodeKey']?:''}")
	private String nodeKey;

	@Autowired
	private BaoShangHttpService baoShangHttpService;

	@Autowired
	private ITaskService taskService;

	@Autowired
	private LUJSInterfaceService lujsInterfaceService;
	
	@Autowired
	private ILujsDao lujsDao;

	private Logger logger = LoggerFactory.getLogger(LUJSContractSignImpl.class);

	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.before(reqLoanContractSignVo, res);
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		String loanNo = reqLoanContractSignVo.getLoanNo();
		//原来的征信报告是实时查的，如果征信报告过期了，就查不到征信报告信息,现在查询之前绑定过的
		//String reportId = ObjectUtils.toString(lujsInterfaceService.getReportId(loanNo));
		String reportId = "";
		Map<String,Object> appPersonInfoMap = lujsDao.getAppPersonInfo(loanNo);
		if(null != appPersonInfoMap && !appPersonInfoMap.isEmpty()){
			reportId = ObjectUtils.toString(appPersonInfoMap.get("report_id"));
		}
		String productCode =  ObjectUtils.toString(reqLoanContractSignVo.getProductCd());
		String newIdLastDate = DateUtil.format(reqLoanContractSignVo.getIdLastDate(),"yyyy-MM-dd");
		
		//如果补件返回证件到期日有误，更新证件到期日
		String oldIdLastDate = ObjectUtils.toString(appPersonInfoMap.get("id_last_date"));
		logger.info("借款编号【"+loanNo+"】证件到期日：newIdLastDate="+newIdLastDate+" oldIdLastDate="+oldIdLastDate);
		if(StringUtils.isNotBlank(oldIdLastDate) && !newIdLastDate.equals(oldIdLastDate)){
			Map<String,Object> tempMap = new HashMap<>();
			tempMap.put("idLastDate", newIdLastDate);
			tempMap.put("loanNo", loanNo);
			lujsDao.updateIdLastDate(tempMap);
		}
		
		boolean reportFlag = false;
		if(StringUtils.isNotBlank(reportId)){
			reportFlag = true;
		}

		/**PIC系统附件校验*/
		Map<String, String> strMap = new HashMap<String, String>();
		strMap.put("nodeKey", nodeKey); //业务环节
		strMap.put("sysName", "bms"); //系统名称
		strMap.put("appNo", loanNo); //申请单号
		strMap.put("operator", reqLoanContractSignVo.getServiceName()); //操作人姓名
		strMap.put("jobNumber", reqLoanContractSignVo.getServiceCode()); //工号
		HttpResponse pic = baoShangHttpService.baoShangPic(strMap);

		JSONObject json = new JSONObject(pic.getContent());
		JSONArray listMap = new JSONArray();
		if (!"000000".equals(json.get("errorcode"))) {
			setError(new BizException(BizErrorCode.EOERR, "pic接口返回结果为null"), res);
			return false;
		}
		listMap = json.getJSONArray("result");
		listMap = LUJSUtils.lujsPicUpload(listMap,reportFlag,productCode);

		/**强制性校验附件是否上传，如果没有上传，系统需提示*/
		for (int i = 0; i < listMap.length(); i++) {
			JSONObject obj = listMap.getJSONObject(i);
			String code = obj.getString("code");
			logger.info("资料类型【"+LUJSUtils.picCodeFormat(obj.getString("code"))+"】:"+obj.getInt("fileAmount"));

			if(obj.getInt("fileAmount") == 0){
				String codeCN = LUJSUtils.picCodeFormat(obj.getString("code"));
				setError(new BizException(BizErrorCode.EOERR, "请上传"+codeCN), res);
				return false;
			}
		}
		return super.execute(reqLoanContractSignVo, res);
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		/*try {
			if("000000".equals(res.getRepCode())){
				taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(), EnumConstants.WFPH_HTQD);
			}
		} catch (Exception e) {
			setError(new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, e.getMessage()), res);
			return false;
		}*/
		return true;
	}
}
