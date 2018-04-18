package com.ymkj.cms.biz.test.facade.audit;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.audit.IQualityInspectionExecuter;
import com.ymkj.cms.biz.api.service.audit.first.ISplitContractFirstAuditExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqApplicationPartUpVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqApplicationPartVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAutomaticDispatchVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSLoansChackVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentBatchVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsRefusePlupdateStatusVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsRefuseUpdStatusVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsUpdVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqcsBMProductUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSApplicationPartVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAudiUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAutomaticDispatchVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResReassignmentUpdVO;
import com.ymkj.cms.biz.common.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class CsAuditTest {
	private static ReqCsUpdVO reqCsUpdVO = new ReqCsUpdVO();;

	// json 工具类
	private Gson gson = new Gson();
	
	@Autowired
	private IQualityInspectionExecuter iQualityInspectionExecuter;
	
	
	
	@Autowired
	private ISplitContractFirstAuditExecuter iSplitContractFirstAuditExecuter;
	
	static{
		reqCsUpdVO.setSysCode("ams");
		reqCsUpdVO.setOperatorCode("dongmingzhi");
		reqCsUpdVO.setVersion(1);
		reqCsUpdVO.setOperatorIP("127.0.0.1");

	}
	
	@Test
	public void csAutomaticDispatchList() {
		ReqAutomaticDispatchVO reqAutomaticDispatchVO = new ReqAutomaticDispatchVO();
		reqAutomaticDispatchVO.setSysCode("ams");
		Response<ResBMSAutomaticDispatchVO> csAutomaticDispatchList = iSplitContractFirstAuditExecuter.csAutomaticDispatchList(reqAutomaticDispatchVO);
		System.out.println("-------------结果: " + gson.toJson(csAutomaticDispatchList));
	}
	
	/**
	 * 初审拒绝
	 */
	@Test
	public void cSRejectUpd() {
		reqCsUpdVO.setLoanNo("20170622134519681345");
		reqCsUpdVO.setVersion(9);
		reqCsUpdVO.setFirstLevelReasonCode("RJ00044");
		reqCsUpdVO.setFirstLevelReasons("提供虚假资料/疑欺诈申请");
		reqCsUpdVO.setcSPersonCode("dongmingzhi");//初审人员
		Response<ResBMSAudiUpdVo> cSRejectUpd = iSplitContractFirstAuditExecuter.cSRejectUpd(reqCsUpdVO);
		System.out.println("-------------结果: " + gson.toJson(cSRejectUpd));
	}
	
	/**
	 * 初审提交
	 */
	@Test
	public void cSSubmitUpd() {
		//[{"loanNo":"20170823DB4CE0","checkNodeStatus":"CHECK","accDate":"2017-08-23 16:48:38","cSPersonCode":"zhouwen","complexPersonCode":"huwf","operatorCode":"zhouwen","version":2,"operatorIP":"127.0.0.1","remark":"dfdfdfd","flag":1,"sysCode":"ams"}]

		reqCsUpdVO.setLoanNo("201708238A8D6C");
		reqCsUpdVO.setVersion(4);
		reqCsUpdVO.setFirstLevelReasonCode("11");
		reqCsUpdVO.setcSPersonCode("zhouwen");//初审人员
		reqCsUpdVO.setFlag(Long.valueOf(1));
		reqCsUpdVO.setSysCode("ams");
		Response<ResBMSAudiUpdVo> cSRejectUpd = iSplitContractFirstAuditExecuter.cSSubmitUpd(reqCsUpdVO);
		System.out.println("-------------结果: " + gson.toJson(cSRejectUpd));
	}
	
	/**
	 * 初审挂起
	 */
	@Test
	public void cSHangupUpd() {
		reqCsUpdVO.setLoanNo("20170622134351166047");
		reqCsUpdVO.setVersion(14);
		reqCsUpdVO.setFirstLevelReasonCode("HA00075");
		reqCsUpdVO.setcSPersonCode("dongmingzhi");//初审人员
		Response<ResBMSAudiUpdVo> cSRejectUpd = iSplitContractFirstAuditExecuter.cSHangupUpd(reqCsUpdVO);
		System.out.println("-------------结果: " + gson.toJson(cSRejectUpd));
	}
	
	
	/**
	 * 初审退回
	 */
	@Test
	public void cSReturnUpd() {
		reqCsUpdVO.setLoanNo("20170513103733022568");
		reqCsUpdVO.setVersion(2);
		reqCsUpdVO.setFirstLevelReasonCode("11");
		reqCsUpdVO.setCheckNodeStatus("NO_CHECK");
		reqCsUpdVO.setcSPersonCode("dongmingzhi");//初审人员
		Response<ResBMSAudiUpdVo> cSRejectUpd = iSplitContractFirstAuditExecuter.cSReturnUpd(reqCsUpdVO);
		System.out.println("-------------结果: " + gson.toJson(cSRejectUpd));
	}
	
	
	/**
	 * 初审退回
	 */
	@Test
	public void csProductUpd() {
		ReqcsBMProductUpdVo reqcsBMProductUpdVo = new ReqcsBMProductUpdVo();
		reqcsBMProductUpdVo.setSysCode("ams");
		reqcsBMProductUpdVo.setLoanNo("20170513103741730211");;//	借款编号
		reqcsBMProductUpdVo.setIfCreditRecode("1");;//	有无信用记录
		reqcsBMProductUpdVo.setAmoutInconme((double)12);;//	收入证明金额
		reqcsBMProductUpdVo.setAccLmt((double)12);;//	审批额度
		reqcsBMProductUpdVo.setProductCd("00013");;//	产品编码
		reqcsBMProductUpdVo.setAccTerm(12);;//	审批期限
		reqcsBMProductUpdVo.setAccDate(new Date());;//	审批日期
		reqcsBMProductUpdVo.setVersion(new Long("2"));;//	版本号
		reqcsBMProductUpdVo.setOperatorCode("dongmingzhi");;//	操作人工号
		reqcsBMProductUpdVo.setOperatorIP("127.0.0.1");;//	操作人IP
		Response<Object> csProductUpd = iSplitContractFirstAuditExecuter.csProductUpd(reqcsBMProductUpdVo);
		System.out.println("-------------结果: " + gson.toJson(csProductUpd));
	}
	
	
	
	/**
	 * 初审复核确认(同意)(不同意)
	 */
	@Test
	public void cSConfirmationReviewAgreeOrDisagreeUpd() {
		reqCsUpdVO.setLoanNo("201707318423FE");
		reqCsUpdVO.setVersion(3);
		reqCsUpdVO.setFirstLevelReasonCode("dongmingzhi");
		reqCsUpdVO.setCheckNodeStatus("CHECK_PASS");
		reqCsUpdVO.setcSPersonCode("dongmingzhi");//初审人员
		reqCsUpdVO.setComplexPersonCode("fushangjun");
		Response<ResBMSAudiUpdVo> cSConfirmationReviewAgreeOrDisagreeUpd = iSplitContractFirstAuditExecuter.cSConfirmationReviewAgreeOrDisagreeUpd(reqCsUpdVO);
		System.out.println("-------------结果: " + gson.toJson(cSConfirmationReviewAgreeOrDisagreeUpd));
	}
	
	/**
	 * 批量改派
	 */
	@Test
	public void plCsReassignmentUpd() {
		ReqBMSReassignmentBatchVo reqBMSReassignmentUpdVo = new ReqBMSReassignmentBatchVo();
		reqBMSReassignmentUpdVo.setSysCode("ams");
		reqBMSReassignmentUpdVo.setAuditPersonCode("niexl");
		reqBMSReassignmentUpdVo.setOperatorCode("niexl");
		reqBMSReassignmentUpdVo.setOperatorIP("127.0.0.1");
		reqBMSReassignmentUpdVo.setOperatorName("聂学林");
		reqBMSReassignmentUpdVo.setAuditPersonName("小小");
		List<ReqBMSLoansChackVO> list = new ArrayList<ReqBMSLoansChackVO>();
		ReqBMSLoansChackVO vo = new ReqBMSLoansChackVO();
		vo.setLoanNo("20170622134351166047");
		vo.setOldAuditPersonName("dongmingzhi");
		vo.setVersion(new Long("18"));
		list.add(vo);
		reqBMSReassignmentUpdVo.setList(list);
		Response<List<ResReassignmentUpdVO>> plCsReassignmentUpd = iSplitContractFirstAuditExecuter.plCsReassignmentUpd(reqBMSReassignmentUpdVo);
		System.out.println("-------------结果: " + gson.toJson(plCsReassignmentUpd));
	}
	
	
	
	/**
	 * 初审批量拒绝
	 */
	@Test
	public void refusePlRejectUpdStatus() {
		ReqCsRefusePlupdateStatusVO reqCsPlupdateStatusVo = new ReqCsRefusePlupdateStatusVO();
		reqCsPlupdateStatusVo.setSysCode("ams");
		reqCsPlupdateStatusVo.setOperatorCode("dongmingzhi");;//操作人编码
		reqCsPlupdateStatusVo.setOperatorName("董明智");;//操作人名称
		reqCsPlupdateStatusVo.setOperatorIP("127.0.0.1");;//操作人IP地址
		reqCsPlupdateStatusVo.setFirstLevelReasons("提供虚假资料/疑欺诈申请");;//一级原因文本
//		reqCsPlupdateStatusVo.setTwoLevelReasons("虚假工作");;//二级原因文本
		reqCsPlupdateStatusVo.setFirstLevelReasonCode("RJ00026");;//一级原因Code
//		reqCsPlupdateStatusVo.setTwoLevelReasonCode("RJ0002500005");//二级原因Code
		reqCsPlupdateStatusVo.setRemark("备注111111");;//备注
		
		List<ReqCsRefuseUpdStatusVO> list = new ArrayList<ReqCsRefuseUpdStatusVO>();
		ReqCsRefuseUpdStatusVO vo = new ReqCsRefuseUpdStatusVO();
		vo.setLoanNo("20170624162404605799");
		vo.setVersion(new Long("1"));
		list.add(vo);
		reqCsPlupdateStatusVo.setList(list);
		
		Response<List<ResReassignmentUpdVO>> refusePlRejectUpdStatus = iSplitContractFirstAuditExecuter.refusePlRejectUpdStatus(reqCsPlupdateStatusVo);
		System.out.println("-------------结果: " + gson.toJson(refusePlRejectUpdStatus));
	}
	
	/**
	 * 初审批量退回
	 */
	@Test
	public void reassignmentPlReturnUpdStatus() {
		ReqCsRefusePlupdateStatusVO reqCsPlupdateStatusVo = new ReqCsRefusePlupdateStatusVO();
		reqCsPlupdateStatusVo.setSysCode("ams");
		reqCsPlupdateStatusVo.setOperatorCode("dongmingzhi");;//操作人编码
		reqCsPlupdateStatusVo.setOperatorName("董明智");;//操作人名称
		reqCsPlupdateStatusVo.setOperatorIP("127.0.0.1");;//操作人IP地址
		reqCsPlupdateStatusVo.setFirstLevelReasons("提供虚假资料/疑欺诈申请");;//一级原因文本
		reqCsPlupdateStatusVo.setTwoLevelReasons("虚假工作");;//二级原因文本
		reqCsPlupdateStatusVo.setFirstLevelReasonCode("RJ00026");;//一级原因Code
		reqCsPlupdateStatusVo.setTwoLevelReasonCode("RJ0002500005");//二级原因Code
		reqCsPlupdateStatusVo.setRemark("备注111111");;//备注
		
		List<ReqCsRefuseUpdStatusVO> list = new ArrayList<ReqCsRefuseUpdStatusVO>();
		ReqCsRefuseUpdStatusVO vo = new ReqCsRefuseUpdStatusVO();
		vo.setLoanNo("");
		vo.setVersion(new Long(""));
		list.add(vo);
		reqCsPlupdateStatusVo.setList(list);
		
		Response<List<ResReassignmentUpdVO>> refusePlRejectUpdStatus = iSplitContractFirstAuditExecuter.reassignmentPlReturnUpdStatus(reqCsPlupdateStatusVo);
		System.out.println("-------------结果: " + gson.toJson(refusePlRejectUpdStatus));
	}
	
	
	
	
	@Test
	public void updateApplicationInfoNew() {
		ReqApplicationPartUpVO reqApplicationPartUpVO = new ReqApplicationPartUpVO();
		reqApplicationPartUpVO.setSysCode("ams");
		
		reqApplicationPartUpVO.setLoanNo("2017072015D1F0");
		reqApplicationPartUpVO.setFlag(new Long("1"));
		reqApplicationPartUpVO.setFirstLevelReasons("信息无法核实");
		reqApplicationPartUpVO.setFirstLevelReasonsCode("RJ00059");
		reqApplicationPartUpVO.setTwoLevelReasons("身份信息无法明确核");
		reqApplicationPartUpVO.setTwoLevelReasonsCode("RJ0005900002");
		reqApplicationPartUpVO.setVersion(new Long("1"));
		reqApplicationPartUpVO.setOperatorIP("127.0.0.1");
		reqApplicationPartUpVO.setOperatorCode("dongmingzhi");
		reqApplicationPartUpVO.setOperatorName("东明智");
		
		Response<Object> updateApplicationInfoNew = iQualityInspectionExecuter.updateApplicationInfoNew(reqApplicationPartUpVO);
		System.out.println("-------------结果: " + gson.toJson(updateApplicationInfoNew));
	}
	
	/**
	 * 
	 */
	@Test
	public void getApplicationInfo() {
		ReqApplicationPartVO reqApplicationPartVO = new ReqApplicationPartVO();
		reqApplicationPartVO.setSysCode("ams");
		
		reqApplicationPartVO.setPageNum(1);
		reqApplicationPartVO.setPageSize(10);
		reqApplicationPartVO.setFlag("2");
		try {
			reqApplicationPartVO.setStartDate(DateUtil.strToDateTime("2017-09-12 00:00:00"));
			reqApplicationPartVO.setEndDate(new Date());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PageResponse<ResBMSApplicationPartVO> applicationInfo = iQualityInspectionExecuter.getApplicationInfo(reqApplicationPartVO);
		System.out.println("-------------结果: " + gson.toJson(applicationInfo));
	}
	
	@Test
	public void getHZReportIDInfo() {
		ReqCsUpdVO reqCsUpdVO = new ReqCsUpdVO();
		reqCsUpdVO.setLoanNo("201708238A8D6C");
		reqCsUpdVO.setSysCode("ams");
		reqCsUpdVO.setName("华征一");
		reqCsUpdVO.setIdNo("310104198001011079");
		reqCsUpdVO.setCellphone("13896969696");
		reqCsUpdVO.setCellPhoneSec("");
		reqCsUpdVO.setOperatorCode("zhouwen");
		
		Response<Object> response = iSplitContractFirstAuditExecuter.getHZReportIDInfo(reqCsUpdVO);
		
		System.out.println("-------------结果: " + gson.toJson(response));
	}
	
}
