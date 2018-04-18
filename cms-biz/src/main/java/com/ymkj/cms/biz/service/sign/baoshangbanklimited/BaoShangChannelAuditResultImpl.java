package com.ymkj.cms.biz.service.sign.baoshangbanklimited;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.BaseSign;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.service.master.IBMSLoanExtEntityService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 包商银行渠道审核通过与否查询
 * Created by YM10140 on 2017/6/20.
 */
@Service
public class BaoShangChannelAuditResultImpl extends BaseSign<ResLoanContractSignVo> {
    private static final Log log = LogFactory.getLog(BaoShangChannelAuditResultImpl.class);
    @Autowired
    private IBMSLoanExtEntityService ibmsLoanExtEntityService;

    @Override
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        // 包商渠道是否被拒绝过
        log.info("*********************包商渠道是否被拒绝过************************loanNo:"+reqLoanContractSignVo.getLoanNo());
        Map<String, Object> param = new HashMap<>();
        param.put("loanNo", reqLoanContractSignVo.getLoanNo());
        BMSLoanExt bmsLoanExt = ibmsLoanExtEntityService.findLoanExtByLoanNo(param);
        if (bmsLoanExt != null) {
            String byState = bmsLoanExt.getByState();
            String auditingState = bmsLoanExt.getAuditingState();
            /********选择包商银行渠道后，点击“保存”按钮，要进行以下两个校验（优先校验②，若②校验通过，才校验①）********/
            //0黑名单拒绝 1黑名单通过 2银行卡鉴权拒绝 3鉴权通过 4风控信息推送失败 5风控信息推送成功
            //黑名单校验未通过、风控审核未通过、影像资料审核返回拒绝
            /**包银状态值 0黑名单拒绝 1黑名单通过 2银行卡鉴权拒绝 3鉴权通过 4风控信息推送失败 5风控信息推送成功 6影像资料推送失败 7影像资料推送成功*/
            /**0审核中（风控和合同签订） 1图像资料待上传（风控通过）  2拒绝（风控和合同签订） 3补件（合同签订）  4通过（合同签订）**/
            if (EnumConstants.BaoYinSatus._000.getCode().equals(byState) || EnumConstants.BaoYinSatus._002.getCode().equals(byState)
                    || EnumConstants.BaoyinAuditingState._002.getCode().equals(auditingState)) {
                //包银渠道审核未通过
                res.setRepMsg("包银渠道审核未通过");
                res.setRepCode("999999");
            }else{
                res.setRepMsg("包银渠道审核通过");
                res.setRepCode("000000");
            }
        } else {
            res.setRepCode("999999");
            res.setRepMsg("输入loanNO有误,未查到相关记录！");
        }
        return true;
    }

}
