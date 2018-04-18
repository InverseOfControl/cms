package com.ymkj.cms.biz.service.sign.baoshangbanklimited;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.PICUtils;
import com.ymkj.cms.biz.common.util.HttpKit;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.master.IBMSLoanExtEntityService;
import com.ymkj.cms.biz.service.sign.base.ConfirmLoanContractImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class PatchPushContract extends ConfirmLoanContractImpl {
    @Autowired
    private BaoShangHttpService baoShangHttpService;
    @Autowired
    private IBMSLoanExtEntityService loanExtService;
    @Autowired
    private IBMSLoanExtEntityDao ibmsLoanExtEntityDao;
    @Autowired
    private HttpKit httpKit;

    //pic接口地址
    @Value("#{env['patchBoltUrl']?:''}")
    private String patchBoltUrl;
    //系统名称
    @Value("#{env['sysName']?:''}")
    private String sysName;
    //业务环节
    @Value("#{env['nodeKey']?:''}")
    private String nodeKey;
    @Value("#{env['byAesKey']?:''}")
    private String byAesKey;
    //pic包银资料上传网关地址
    @Value("#{env['byPicGateWayUrl']?:''}")
    private String byPicGateWayUrl;
    //文件上传接口pic
    @Value("#{env['uploadPicFileUrl']?:''}")
    private String uploadPicFileUrl;
    //文件删除接口
    @Value("#{env['deletePicFileUrl']?:''}")
    private String deletePicFileUrl;
    @Value(value = "#{env['windows_contractPath']?:''}")
    private String windows_contractPath;
    @Value(value = "#{env['linux_contractPath']?:''}")
    private String linux_contractPath;
    private static final Log log = LogFactory.getLog(PatchPushContract.class);

    @Override
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {

        log.info("***********************推送影像资料开始************************loanNo:" + reqLoanContractSignVo.getLoanNo());

        /** 包银状态值 0审核中（风控和合同签订） 1图像资料待上传（风控通过）  2拒绝（风控和合同签订） 3补件（合同签订）  4通过（合同签订 */
        Map<String, Object> param = new HashMap<>();
        param.put("loanNo", reqLoanContractSignVo.getLoanNo());
        BMSLoanExt ext = loanExtService.findLoanExtByLoanNo(param);
        String state = ext.getAuditingState();

        //需要传给包银的图片地址集合
        JSONArray toByListMap = new JSONArray();
        //获取需要补件的材料类型
        String patchType = ext.getByRefusalResult();
        if (patchType != null && EnumConstants.BaoyinAuditingState._003.getCode().equals(state)) {
            toByListMap = PICUtils.getPatchFolder(patchType);
        } else if (EnumConstants.BaoyinAuditingState._001.getCode().equals(state)) {
            toByListMap = PICUtils.getPushFolder();
        }

        //影像资料上传接口传入参数对象
        JSONObject dataJson = new JSONObject();
        dataJson.put("busNumber", ext.getBusNumber());// 业务申请流水号
        dataJson.put("txncd", "BYXY0015"); // 接口编号
        //存储的pdf文件夹地址
        String pdfFilePath = null;
        //获取系统配置的存储文件路径
        String contractPath = getContractPath();
        //推送给包银的影像资料地址集合
        JSONArray imageArray = new JSONArray();

        //由于pic系统退回时所有附件会打上补件的标志，需要重新判断推送的附件
        Map<String, String> mapfile = new HashMap<>();
        mapfile.put("appNo", reqLoanContractSignVo.getLoanNo());//申请单号
        mapfile.put("operator", reqLoanContractSignVo.getServiceName());//操作人姓名
        mapfile.put("jobNumber", reqLoanContractSignVo.getServiceCode());//工号
        //循环目录取目录下文件
        if (null != toByListMap && toByListMap.length() > 0) {
            pdfFilePath = createFilePath(reqLoanContractSignVo);
            //清除原来生成的pdf目录
            cleanPdf(pdfFilePath);
            //依次遍历文件夹目录
            for (int i = 0; i < toByListMap.length(); i++) {
                //文件类型编号
                mapfile.put("subclass_sort", toByListMap.getString(i));
                HttpResponse picFile = baoShangHttpService.baoShangPicFile(mapfile);
                JSONObject jsonfile = new JSONObject(picFile.getContent());
                JSONArray listfile;
                if ("000000".equals(jsonfile.get("errorcode"))) {
                    listfile = jsonfile.getJSONArray("result");
                } else {
                    setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "pic目录下文件接口调用返回result为null"), res);
                    log.info("pic目录下文件接口调用返回result为null，" + jsonfile);
                    return false;
                }
                //获取各个文件夹下的图片地址
                if (null != listfile && listfile.length() > 0) {
                    int f = 0;//面签照片取值
                    for (int j = 0; j < listfile.length(); j++) {
                        JSONObject obfile = listfile.getJSONObject(j);
                        String ifWaste = obfile.getString("ifWaste");//是否作废
                        //作废的附件不要了，过滤掉
                        if ("N".equals(ifWaste)) {
                            String ifPatchBolt = obfile.getString("ifPatchBolt");//是否补件
                            JSONObject jsonImgList = new JSONObject();
                            String url = "http:" + obfile.getString("url");
                            String imageType = PICUtils.getImageType(obfile.getString("subclassSort"), f, patchType);

                            //如果是贷款合同文件夹则需要删除原来生成的pdf文件
                            if ("04".equals(imageType) && obfile.getString("savename").endsWith(".pdf")) {//过滤掉之前上传的pdf文件
                                Map<String, String> delMap = new HashMap<>();
                                delMap.put("jobNumber", reqLoanContractSignVo.getServiceCode());
                                delMap.put("operator", reqLoanContractSignVo.getServiceName());
                                delMap.put("ids", String.valueOf(obfile.get("id")));
                                log.info("调用PIC系统删除原来的贷款合同参数" + JsonUtils.encode(delMap) + "，调用地址url：" + deletePicFileUrl);
                                String post = httpKit.post(deletePicFileUrl, delMap);
                                log.info("调用PIC系统删除原来的贷款合同返回结果：" + post);
                            }

                            try {
                                //非补件的
                                if (EnumConstants.BaoyinAuditingState._001.getCode().equals(state)) {
                                    //如果是贷款合同则需要将图片转化成PDF格式
                                    if ("04".equals(imageType) && !obfile.getString("savename").endsWith(".pdf")) {//过滤掉之前上传的pdf文件
                                        PICUtils.imgToPdf(url, pdfFilePath + "/" + obfile.getString("imgname") + ".pdf");
                                    } else {
                                        String aesUrl = PICUtils.encryptAES(url, byAesKey);
                                        jsonImgList.put("imageUrl", byPicGateWayUrl + URLEncoder.encode((aesUrl), "UTF-8"));
                                        jsonImgList.put("imageType", imageType);
                                        imageArray.put(jsonImgList);
                                    }
                                    //补件的
                                } else if ("Y".equals(ifPatchBolt) && EnumConstants.BaoyinAuditingState._003.getCode().equals(state)) {
                                    //如果是贷款合同则需要将图片转化成PDF格式
                                    if ("04".equals(imageType) && !obfile.getString("savename").endsWith(".pdf")) {//过滤掉之前上传的pdf文件
                                        PICUtils.imgToPdf(url, pdfFilePath + "/" + obfile.getString("imgname") + ".pdf");
                                    } else {
                                        String aesUrl = PICUtils.encryptAES(url, byAesKey);
                                        jsonImgList.put("imageType", imageType);
                                        jsonImgList.put("imageUrl", byPicGateWayUrl + URLEncoder.encode((aesUrl), "UTF-8"));
                                        imageArray.put(jsonImgList);
                                    }
                                }else{
                                    f=0;//面签照片取值
                                    continue;
                                }
                            } catch (Exception e) {
                                setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "影像资料地址加密异常"), res);
                                log.info("影像资料地址加密异常：", e);
                                return false;
                            }
                            f++;//面签照片取值
                        }
                    }
                } else {
                    log.info(PICUtils.getFoldName(toByListMap.getString(i)) + "文件夹为空，请上传附件！");
                    setError(new BizException(CoreErrorCode.DB_RESULT_ISNULL, PICUtils.getFoldName(toByListMap.getString(i)) + "文件夹为空，请上传附件！"), res);
                    return false;
                }
            }

            log.info("贷款合同图片格式转pdf格式后的存储地址：" + pdfFilePath);
            //获取生成的贷款合同pdf文件
            String[] files;
            JSONObject jsonImgList = new JSONObject();
            if (pdfFilePath != null) {
                try {
                    files = PICUtils.getFiles(pdfFilePath);
                    if (files.length > 0) {
                        //多个pdf转化成一个pdf文件
                        String folderPath = contractPath + reqLoanContractSignVo.getChannelCd() + "/" + reqLoanContractSignVo.getLoanNo() + "/signed";
                        //清除原来生成的pdf目录
                        cleanPdf(folderPath);
                        File file = new File(folderPath);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        String finalPath = folderPath + "/" + reqLoanContractSignVo.getLoanNo() + "_" + "包银渠道贷款合同.pdf";
                        boolean topdf = PICUtils.morePdfTopdf(files, finalPath);
                        if (!topdf) {
                            setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "贷款合同转pdf合并异常！"), res);
                            return false;
                        }
                        String returnUrl = pdfUploadPic(reqLoanContractSignVo, finalPath, res);
                        log.info("生成的pdf格式贷款合同文件上传到pic系统返回的url：" + returnUrl);
                        if (returnUrl == null) {
                            setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, res.getRepMsg()), res);
                            log.info("生成的pdf格式贷款合同文件上传到pic系统返回的url为空！");
                            return false;
                        }
                        try {
                            String aesUrl = PICUtils.encryptAES("http:" + returnUrl, byAesKey);
                            jsonImgList.put("imageUrl", byPicGateWayUrl + URLEncoder.encode((aesUrl), "UTF-8"));
                            jsonImgList.put("imageType", "04");
                            imageArray.put(jsonImgList);
                        } catch (Exception e) {
                            setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "影像资料地址加密异常"), res);
                            log.info("影像资料地址加密异常：", e);
                            return false;
                        }
                    }
                } catch (Exception e) {
                    log.info("获取生成的pdf文件地址列表异常：", e);
                    setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "获取生成的pdf文件地址列表异常"), res);
                    return false;
                }
            }
        } else {
            log.info("推送给包银的影像资料文件夹为空！");
            setError(new BizException(BizErrorCode.DB_RESULT_ISNULL, "推送给包银的影像资料文件夹为空！"), res);
            return false;
        }
        //设置影像资料列表
         dataJson.put("imgNameList", imageArray);
        if (EnumConstants.BaoyinAuditingState._003.getCode().equals(state)) {
            dataJson.put("uploadType", "02"); // 补件
        } else if (EnumConstants.BaoyinAuditingState._001.getCode().equals(state)) {
            /*************推送之前校验影像资料数量********************/
            log.info("*******************开始校验推送给包银的影像资料数量************************");
            if (imageArray.length() < 7) {
                String missImageType = PICUtils.getMissImageType(imageArray);
                log.info("推送给包银的材料缺失：loanNo" + reqLoanContractSignVo.getLoanNo() + "，" + missImageType);
                String msg = missImageType.substring(0, missImageType.length() - 1) + "材料缺失，请补充！";
                setError(new BizException(BizErrorCode.BAOYIN_IMAGE_MISS, msg), res);
                return false;
            }
        }
        //将影像资料推送给包银
        HttpResponse bs = baoShangHttpService.baoShangUrl("bsb100015", dataJson);
        JSONObject bsJson = new JSONObject(bs.getContent());
        JSONObject infos = bsJson.getJSONObject("infos");
        //保存包银返回的状态值参数map
        Map<String, Object> paramExt = new HashMap<>();
        paramExt.put("loanNo", reqLoanContractSignVo.getLoanNo());
        if (infos.has("data")) {
            /**包银状态值 0黑名单拒绝 1黑名单通过 2银行卡鉴权拒绝 3鉴权通过 4风控信息推送失败 5风控信息推送成功 6影像资料推送失败 7影像资料推送成功*/
            JSONObject jo = infos.getJSONObject("data");
            String respcd = jo.getString("respcd");
            String resptx = jo.getString("resptx");
            if ("0000".equals(respcd)) {
                paramExt.put("auditingState", EnumConstants.BaoyinAuditingState._000.getCode());//审核中（风控和合同签订）
                paramExt.put("byState", EnumConstants.BaoYinSatus._007.getCode());//影像资料推送成功
                paramExt.put("orgAuditState", EnumConstants.OrgAuditState.SHZ.getCode());//机构审核状态
                res.setRepMsg(resptx);
                res.setRepCode("000000");
            } else {
                //[3901]影像资料重复上传
                paramExt.put("byState", EnumConstants.BaoYinSatus._006.getCode());//影像资料推送失败
                res.setRepCode("999999");
                res.setRepMsg(resptx);
            }
        } else {
            paramExt.put("byState", EnumConstants.BaoYinSatus._006.getCode());//影像资料推送失败
            res.setRepCode("999999");
            res.setRepMsg(bsJson.getString("respDesc"));
        }
        //更新借款表包银状态值
        long update = ibmsLoanExtEntityDao.updateBySatus(paramExt);
        if (update != 1) {
            log.info("*********保存包银渠道状态，更新借款扩展表失败************");
            //抛异常保证事务回滚
            throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "保存包银渠道返回的状态值失败");
        }
        log.info("***********************推送影像资料完成************************");
        if ("000000".equals(res.getRepCode())) {
            //推送成功则推送核心
            return super.execute(reqLoanContractSignVo, res);
        } else {
            return false;
        }
    }

    /**
     * 获取系统配置的存储文件路径
     *
     * @return
     */
    private String getContractPath() {
        String contractPath = "";
        try {
            String osType = StringUtils.osType();
            if (osType.equals("windows")) {
                contractPath = windows_contractPath;
            } else {
                contractPath = linux_contractPath;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contractPath;
    }

    /**
     * 创建pdf文件存储的目录
     *
     * @param reqLoanContractSignVo
     * @return
     */
    private String createFilePath(ReqLoanContractSignVo reqLoanContractSignVo) {
        //创建目录
        String contractPath = getContractPath();
        String realPath = contractPath + reqLoanContractSignVo.getChannelCd();
        //先创建一级目录
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //再创建二级目录
        String realPath2 = contractPath + reqLoanContractSignVo.getChannelCd() + "/" + reqLoanContractSignVo.getLoanNo() + "/" + "sign";
        File realFile = new File(realPath2);
        if (!realFile.exists()) {
            realFile.mkdirs();
        }
        return realPath2;
    }

    /**
     * 生成的pdf上传到pic
     *
     * @param reqLoanContractSignVo
     * @param finalPath
     * @return
     */
    private String pdfUploadPic(ReqLoanContractSignVo reqLoanContractSignVo, String finalPath, Response res) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("sysName", "aps");//系统编号
        map.put("nodeKey", "contractAward");//业务代号   contractConfirm
        map.put("appNo", reqLoanContractSignVo.getLoanNo());
        map.put("operator", reqLoanContractSignVo.getServiceName());
        map.put("jobNumber", reqLoanContractSignVo.getServiceCode());
        map.put("code", "S1");
        map.put("dataSources", EnumConstants.dataSources.PC.getValue());
        log.info("调用pic系统上传文件参数url：" + uploadPicFileUrl);
        //解析file，已文件流的形式上传
        String post = httpKit.post(uploadPicFileUrl, map, new File(finalPath));
        log.info("调用pic系统上传文件返回结果：" + post);
        JSONObject picJson = new JSONObject(post);
        //上传后返回的url
        String returnUrl = null;
        if ("000000".equals(picJson.getString("errorcode"))) {//上传成功
            JSONObject result = picJson.getJSONObject("result");
            returnUrl = result.getString("url");
            res.setRepCode("000000");
        } else {//上传失败
            log.info("pic上传文件接口调用返回失败信息：" + post);
            res.setRepCode("999999");
            res.setRepMsg(picJson.getString("errormsg"));
            return null;
        }
        log.info("调用pic系统上传文件返回的图片地址url：" + returnUrl);
        return returnUrl;
    }

    /**
     * 清空之前转化的pdf
     *
     * @param filePath
     */
    private void cleanPdf(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            delAllFile(filePath);
        }
    }

    /**
     * 删除指定文件夹下所有文件
     *
     * @param path 文件夹完整绝对路径
     * @return
     */
    private boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 删除文件夹
     *
     * @param folderPath 文件夹完整绝对路径
     */
    private void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
