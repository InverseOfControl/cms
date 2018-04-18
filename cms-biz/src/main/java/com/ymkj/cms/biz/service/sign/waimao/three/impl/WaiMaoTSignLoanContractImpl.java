package com.ymkj.cms.biz.service.sign.waimao.three.impl;

import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.TaskOpinion;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLufax800001Vo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLufax800001Vo;
import com.ymkj.cms.biz.common.sign.BaseSign;
import com.ymkj.cms.biz.common.sign.SignFactory;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.dao.sign.ILujsDao;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.HttpContractReturnEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;
import com.ymkj.cms.biz.service.sign.base.SignLoanContractImpl;
import com.ymkj.cms.biz.service.sign.lujs.LUJSUtils;
import com.ymkj.cms.biz.service.sign.waimao.three.WaiMaoTUtils;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;

import org.apache.commons.lang.ObjectUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 签约 签订
 * 
 * @author YM10138
 *
 */
@Service
public class WaiMaoTSignLoanContractImpl extends SignLoanContractImpl {

	@Autowired
	private ILoanBaseEntityService loanBaseEntityService;
	@Autowired
	private BaoShangHttpService baoShangHttpService;
	//pic业务环节
    @Value("#{env['nodeKey']?:''}")
    private String nodeKey;

	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		// 参数校验
		return super.before(reqLoanContractSignVo, res);
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		String loanNo = reqLoanContractSignVo.getLoanNo();
		
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
        listMap = WaiMaoTUtils.waiMaoTPicUpload(listMap);
        
        /**强制性校验附件是否上传，如果没有上传，系统需提示*/
        for (int i = 0; i < listMap.length(); i++) {
            JSONObject obj = listMap.getJSONObject(i);
            if(obj.getInt("fileAmount") == 0){
            	String codeCN = WaiMaoTUtils.picCodeFormat(obj.getString("code"));
            	setError(new BizException(BizErrorCode.REQUEST_PARAM_ERROR, "请上传"+codeCN), res);
            	return false;
            }
        }
		
		
		return super.execute(reqLoanContractSignVo, res);
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.after(reqLoanContractSignVo, res);
	}
	
}
