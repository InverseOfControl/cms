package com.ymkj.cms.biz.entity.audit.first;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.ymkj.base.core.biz.entity.BaseEntity;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsRefuseUpdStatusVO;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.common.util.ValidataUtil;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;

public class AFirstCsRefusePlupdateEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "000001,操作list不能为空")
	private List<ReqCsRefuseUpdStatusVO> list;
	@NotNull(message = "000001,操作人编码不能为空")
	private String operatorCode;//操作人编码
	@NotNull(message = "000001,操作人姓名不能为空")
	private String operatorName;//操作人名称
	@NotNull(message = "000001,操作人IP地址不能为空")
	private String operatorIP;//操作人IP地址
	@NotNull(message = "000001,一级原因文本不能为空")
	private String firstLevelReasons;//一级原因文本
	private String twoLevelReasons;//二级原因文本
	@NotNull(message = "000001,一级原因Code不能为空")
	private String FirstLevelReasonCode;//一级原因Code
	private String TwoLevelReasonCode;//二级原因Code
	private String remark;//备注
	
	
	
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public List<ReqCsRefuseUpdStatusVO> getList() {
		return list;
	}
	public void setList(List<ReqCsRefuseUpdStatusVO> list) {
		this.list = list;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getOperatorIP() {
		return operatorIP;
	}
	public void setOperatorIP(String operatorIP) {
		this.operatorIP = operatorIP;
	}
	public String getFirstLevelReasons() {
		return firstLevelReasons;
	}
	public void setFirstLevelReasons(String firstLevelReasons) {
		this.firstLevelReasons = firstLevelReasons;
	}
	public String getTwoLevelReasons() {
		return twoLevelReasons;
	}
	public void setTwoLevelReasons(String twoLevelReasons) {
		this.twoLevelReasons = twoLevelReasons;
	}
	public String getFirstLevelReasonCode() {
		return FirstLevelReasonCode;
	}
	public void setFirstLevelReasonCode(String firstLevelReasonCode) {
		FirstLevelReasonCode = firstLevelReasonCode;
	}
	public String getTwoLevelReasonCode() {
		return TwoLevelReasonCode;
	}
	public void setTwoLevelReasonCode(String twoLevelReasonCode) {
		TwoLevelReasonCode = twoLevelReasonCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public void chack(){
		if (!ValidataUtil.isIP(this.operatorIP)) {
			throw new BizException(BizErrorCode.APPLY_VALUE_EOERR, new Object[]{"operatorIP"});
		}
		if(this.operatorCode.equals("")){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"operatorCode"});
		}
		
		if(list == null || list.size() == 0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"list"});
		}else{
			for(ReqCsRefuseUpdStatusVO vo : list){
				if(StringUtils.isEmpty(vo.getLoanNo())){
					throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo"});
				}
				
				if(vo.getVersion() == null){
					throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"version"});
				}
			}
		}
		
	}
}
