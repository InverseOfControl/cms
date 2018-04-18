package com.ymkj.cms.biz.service.sign.baoshangbanklimited;

import com.bstek.uflo.model.task.Task;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.PICUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.master.IBMSLoanExtEntityService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.base.SignLoanContractImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: BaoShangBackConfirmImpl
 * @Description: 合同签订-包银渠道
 * @author: lisc@yuminsoft.com
 * @date: 2017年6月15日 上午10:38:54
 */
@Service
public class BaoShangSignContractImpl extends SignLoanContractImpl {
    //pic接口地址
    @Value("#{env['patchBoltUrl']?:''}")
    private String patchBoltUrl;
    //提示语
    @Value("${byPicDKFileNameMsg}")
    private String byPicDKFileNameMsg;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private BaoShangHttpService baoShangHttpService;
    @Autowired
    private IBMSLoanExtEntityService loanExtService;

    private static final Log log = LogFactory.getLog(BaoShangSignContractImpl.class);

    @Override
    public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        // 参数校验
        @SuppressWarnings("unchecked")
        Response<Object> validateResponse = Validate.getInstance().validate(reqLoanContractSignVo);
        if (!validateResponse.isSuccess()) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg()), res);
            return false;
        }
        if (reqLoanContractSignVo.getId() == null) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"id"}), res);
            return false;
        }
        if (StringUtils.isBlank(reqLoanContractSignVo.getLoanNo())) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"}), res);
            return false;
        }

        if (StringUtils.isBlank(reqLoanContractSignVo.getServiceCode())) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"serviceCode"}), res);
            return false;
        }
        if (StringUtils.isBlank(reqLoanContractSignVo.getChannelCd())) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"channelCd"}), res);
            return false;
        }
        // 流程任务校验（非当前节点抛出异常）
        Task task = taskService.findTaskByLoanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
        if (task != null && EnumConstants.WF_TJX.equals(task.getTaskName())) {
            return true;
        } else if (task == null || !EnumConstants.WF_HTQD.equals(task.getTaskName())) {
            setError(new BizException(BizErrorCode.UFLOTASK_EOERR), res);
            return false;
        }
        return true;
    }

    @Override
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        //查询包银的状态值
        Map<String, Object> param = new HashMap<>();
        param.put("loanNo", reqLoanContractSignVo.getLoanNo());
        BMSLoanExt ext = loanExtService.findLoanExtByLoanNo(param);
        String state = ext.getAuditingState();//包银状态值
        String byRefusalResult = ext.getByRefusalResult();//补件类型

        if (EnumConstants.BaoyinAuditingState._003.getCode().equals(state) && byRefusalResult != null) {
            //获取补件的文件夹
            JSONArray pushFolder = PICUtils.getPatchFolder(byRefusalResult);
            //循环目录取目录下的文件
            Map<String, String> map = new HashMap<>();
            map.put("appNo", reqLoanContractSignVo.getLoanNo());//申请单号
            map.put("operator", reqLoanContractSignVo.getServiceName());//操作人姓名
            map.put("jobNumber", reqLoanContractSignVo.getServiceCode());//工号
            if (null != pushFolder && pushFolder.length() > 0) {
                //依次遍历文件夹目录
                for (int i = 0; i < pushFolder.length(); i++) {
                    int patchCount = 0;//补件的数量
                    map.put("subclass_sort", pushFolder.getString(i)); //文件类型编号
                    HttpResponse response = baoShangHttpService.baoShangPicFile(map);
                    JSONObject jsonFile = new JSONObject(response.getContent());
                    JSONArray listFiles;
                    if ("000000".equals(jsonFile.get("errorcode"))) {
                        listFiles = jsonFile.getJSONArray("result");
                    } else {
                        log.info("pic目录下文件接口调用返回result为null，" + jsonFile);
                        setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "pic目录下文件接口调用返回result为null"), res);
                        return false;
                    }
                    //校验附件数量
                    if (null != listFiles && listFiles.length() > 0) {
                        for (int j = 0; j < listFiles.length(); j++) {
                            JSONObject obFile = listFiles.getJSONObject(j);
                            if ("N".equals(obFile.getString("ifWaste")) && "Y".equals(obFile.getString("ifPatchBolt"))) {
                                patchCount++;
                            }
                        }
                        log.info(PICUtils.getFoldName(pushFolder.getString(i)) + "文件夹补件数量为：" + patchCount);
                        if (patchCount <= 0) {
                            log.info(PICUtils.getFoldName(pushFolder.getString(i)) + "文件夹补件数量为空，请上传需要的补件！loanNo=" + reqLoanContractSignVo.getLoanNo());
                            setError(new BizException(CoreErrorCode.DB_RESULT_ISNULL, PICUtils.getFoldName(pushFolder.getString(i)) + "文件夹补件数量为空，请上传需要的补件！"), res);
                            return false;
                        }
                    } else {
                        log.info(PICUtils.getFoldName(pushFolder.getString(i)) + "文件夹为空，请上传附件！loanNo=" + reqLoanContractSignVo.getLoanNo());
                        setError(new BizException(CoreErrorCode.DB_RESULT_ISNULL, PICUtils.getFoldName(pushFolder.getString(i)) + "文件夹为空，请上传附件！"), res);
                        return false;
                    }
                }
            }
        }

        //非补件状态
        if (!EnumConstants.BaoyinAuditingState._003.getCode().equals(state)) {
            //需要检验一下贷款合同和面签照片 贷款合同文件夹
            Map<String, String> paramMapPic = new HashMap<>();
            paramMapPic.put("subclass_sort", "S1");//文件类型编号
            paramMapPic.put("appNo", reqLoanContractSignVo.getLoanNo());//业务编号-借款编号
            paramMapPic.put("operator", reqLoanContractSignVo.getServiceName());//操作人姓名
            paramMapPic.put("jobNumber", reqLoanContractSignVo.getServiceCode());//工号
            HttpResponse httpResponse = baoShangHttpService.baoShangPicFile(paramMapPic);
            JSONObject jsonResS1 = new JSONObject(httpResponse.getContent());
            if (!"000000".equals(jsonResS1.get("errorcode"))) {
                setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "pic目录下文件接口调用返回result为null"), res);
                return false;
            }
            JSONArray jsonQArray = jsonResS1.getJSONArray("result");//贷款合同
            if (jsonQArray.length() > 0) {
                List<String> list = new ArrayList<>();
                list.add("1");
                list.add("2");
                list.add("3");
                list.add("4");
                list.add("5");
                for (int i = 0; i < jsonQArray.length(); i++) {
                    JSONObject jb = new JSONObject(jsonQArray.get(i).toString());
                    String imgname = jb.getString("imgname");
                    if ("N".equals(jb.getString("ifWaste")) && !imgname.endsWith("pdf")) {//是否作废
                        String str = imgname.substring(imgname.length() - 1);
                        if (imgname.endsWith("1") || imgname.endsWith("2") || imgname.endsWith("3") || imgname.endsWith("4") || imgname.endsWith("5")) {
                            if (list.contains(str))
                                list.remove(str);
                            else {
                                setError(new BizException(CoreErrorCode.PARAM_ERROR, byPicDKFileNameMsg), res);
                                return false;
                            }
                        }
                    }
                }

                if (list.size() > 0) {
                    setError(new BizException(CoreErrorCode.PARAM_ERROR, byPicDKFileNameMsg), res);
                    return false;
                }
            } else {
                setError(new BizException(CoreErrorCode.DB_RESULT_ISNULL, "贷款合同文件夹为空！"), res);
                return false;
            }
        }

        // 流程任务校验（非当前节点抛出异常）
        Task task = taskService.findTaskByLoanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
        if (task != null && EnumConstants.WF_TJX.equals(task.getTaskName())) {
            try {
                taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(), EnumConstants.WF_HTQD);
            } catch (Exception e) {
                throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, e.getMessage());
            }
        }
        return super.execute(reqLoanContractSignVo, res);
    }

    @Override
    public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        return super.after(reqLoanContractSignVo, res);
    }
}
