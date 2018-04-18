package com.ymkj.cms.biz.service.sign.lujs.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.FTPUtils;
import com.ymkj.cms.biz.dao.apply.APPPersonInfoDao;
import com.ymkj.cms.biz.dao.sign.ILujsDao;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.sign.base.ConfirmLoanContractImpl;
import com.ymkj.cms.biz.service.sign.lujs.LUJSUtils;
import com.ymkj.cms.biz.service.sign.lujs.ifc.LUJSInterfaceService;

/**
 * @Description:合同确认（陆金所）</p>
 * @uthor YM10159
 * @date 2017年7月7日 上午11:15:33
 */
@Service
public class LUJSContractConfirmImpl extends ConfirmLoanContractImpl {
	private Logger logger = LoggerFactory.getLogger(LUJSContractConfirmImpl.class);
	
	@Autowired
	private LUJSInterfaceService interfaceService;
	
	@Autowired
	private BaoShangHttpService baoShangHttpService;
	
	@Value("#{env['nodeKey']?:''}")
	private String nodeKey;
	
	@Autowired
	private FTPUtils fTPUtils;
	
	@Value("#{env['peopleBankCreditURL']?:''}")
	private String peopleBankCreditURL;
	
	@Autowired
	private APPPersonInfoDao appPersonInfoDao;
	
	@Autowired
	private ILujsDao lujsDao;
	
	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.before(reqLoanContractSignVo, res);
	}
	
	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		String loanNo = reqLoanContractSignVo.getLoanNo();
		String reportId = "";
		Map<String,Object> appPersonInfoMap = lujsDao.getAppPersonInfo(loanNo);
		if(null != appPersonInfoMap && !appPersonInfoMap.isEmpty()){
			reportId = ObjectUtils.toString(appPersonInfoMap.get("report_id"));
		}
		String productCode = ObjectUtils.toString(reqLoanContractSignVo.getProductCd());
		JSONArray jsonArray = new JSONArray();
	    JSONObject jsonObject = null;
	    
	    //上传征信报告给陆金所
	    boolean reportFlag = false;
	    if(StringUtils.isNotBlank(reportId)){
	    	reportFlag = true;
	    	jsonObject = new JSONObject();
	    	String resKey = getZXReportInfo(loanNo,reportId);
	    	if(StringUtils.isBlank(resKey)){
	    		logger.info("借款编号【"+loanNo+"】上传html格式的征信报告失败！");
	    	}else{
	    		jsonObject.put("subclassSort", "L");
	    		jsonObject.put("resKey", resKey);
	    		jsonArray.put(jsonObject);
	    	}
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
        
        JSONArray uploadListMap = LUJSUtils.lujsPicUpload(listMap,reportFlag,productCode);
        //新增附件上传 add by ym10159 2017-10-31
        uploadListMap = LUJSUtils.lujsOtherPicUpload(listMap,uploadListMap,productCode,reportId);
        logger.info("借款编号【"+loanNo+"】需要上传的文件夹code:"+uploadListMap);
        
        for (int i = 0; i < uploadListMap.length(); i++) {
            JSONObject ob = uploadListMap.getJSONObject(i);
            Map<String, String> mapfile = new HashMap<String, String>();
            mapfile.put("subclass_sort", ob.getString("code"));
            mapfile.put("appNo", reqLoanContractSignVo.getLoanNo());//申请单号
            mapfile.put("operator", reqLoanContractSignVo.getServiceName());//操作人姓名
            mapfile.put("jobNumber", reqLoanContractSignVo.getServiceCode());//工号
            
            //调用PIC系统获取文件集合接口
            HttpResponse picFile = baoShangHttpService.baoShangPicFile(mapfile);
            
            logger.info("借款编号【"+loanNo+"】文件类型【"+ob.getString("code")+"】需要上传的文件内容:"+picFile.getContent());
            JSONObject jsonfile = new JSONObject(picFile.getContent());
            JSONArray listfile = new JSONArray();
            if ("000000".equals(jsonfile.get("errorcode"))) {
                listfile = jsonfile.getJSONArray("result");
            } else {
                setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "pic目录下文件接口调用返回result为null"), res);
                return false;
            }
            
            /**影像资料上传至iobs*/
            for (int j = 0; j < listfile.length(); j++) {
                JSONObject obfile = listfile.getJSONObject(j);
                String url = "http:" + obfile.getString("url");
                logger.info("借款编号【"+loanNo+"】上传文件到陆金所前："+"【文件名："+obfile.getString("savename")+" 文件路径："+url+"】");
                String resKey = interfaceService.upLoadFileByLujs(url,loanNo, obfile.getString("savename"), obfile.getString("subclassSort"));
                logger.info("借款编号【"+loanNo+"】上传文件到陆金所后："+"【文件名："+obfile.getString("savename")+" 文件路径："+url+" 结果："+resKey+"】");
                if(StringUtils.isBlank(resKey)){
                	setError(new BizException(BizErrorCode.EOERR, "影像文件上传失败，请重新上传"), res);
                	return false;
                }
                jsonObject = new  JSONObject();
                jsonObject.put("subclassSort", obfile.getString("subclassSort"));
                jsonObject.put("resKey", resKey);
                jsonArray.put(jsonObject);
            }
        }
        
		/**陆金所借款申请信息提交接口*/
        Map<String,Object> submitMap = new HashMap<>();
        submitMap.put("loanNo", loanNo);
        submitMap.put("jsonArray", jsonArray);
        submitMap.put("applyType", reqLoanContractSignVo.getApplyType());
        submitMap.put("serviceCode", reqLoanContractSignVo.getServiceCode());
        submitMap.put("productCode", reqLoanContractSignVo.getProductCd());
        submitMap.put("reportId", reportId);
		Map<String,Object> resultMap = interfaceService.lujsSignInfoSubmit(submitMap);
		if(!"000000".equals(resultMap.get("resCode"))){
			throw new BizException(BizErrorCode.EOERR, resultMap.get("resMsg"));
		}
		
		//跟新借款状态和核心状态
		return super.execute(reqLoanContractSignVo, res);
	}
	
	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.after(reqLoanContractSignVo, res);
	}
	
	/**
	 * 获取征信报告html内容信息
	 * @param loanNo 借款编号
	 * @param reportId 征信报告ID
	 * @return 返回征信报告html内容
	 */
	public String getZXReportInfo(String loanNo, String reportId){
		logger.info("借款编号【"+loanNo+"】上传征信报告到陆金所："+"【报告ID："+reportId);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
    	APPPersonInfoEntity tmAppPersonInfo = appPersonInfoDao.findPersonByLoanNo(param);
    	
    	Date auditEndTime = tmAppPersonInfo.getAuditEndTime();
    	if(null == auditEndTime){
    		try {
				auditEndTime = DateUtil.strToDate("2017-01-01", "yyyy-MM-dd");
			} catch (ParseException e) {}
    	}
    	String url = peopleBankCreditURL + "?" + "reportId=" + reportId +"&sources="+EnumConstants.BMS_SYSCODE+"&queryDate="+ df.format(auditEndTime);
    	
    	logger.info("借款编号【"+loanNo+"】上传征信报告到陆金所："+"请求参数："+url);
        String resKey = interfaceService.upLoadFileByLujs(url,loanNo, "zxbg.html", "L");
        logger.info("借款编号【"+loanNo+"】上传征信报告到陆金所："+"响应 结果："+resKey+"】");
    	
        return resKey;
	}
}
