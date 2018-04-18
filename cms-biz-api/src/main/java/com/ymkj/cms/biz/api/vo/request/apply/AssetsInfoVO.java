package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 申请录入请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 
 *  
 * @author haowp
 *
 */
public class AssetsInfoVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;
	
	private EstateInfoVO estateInfoVO;     // 房产信息
	private CarInfoVO carInfoVO;        //车辆信息	
	private PolicyInfoVO policyInfoVO;         // 保单信息		
	private ProvidentInfoVO providentInfoVO;         // 公积金信息		
	private CardLoanInfoVO cardLoanInfoVO;         // 	卡友贷信息		
	private SalaryLoanInfoVO salaryLoanInfoVO;         // 随薪贷信息		
	private MasterLoanInfoVO masterLoanInfoVO;         // 网购达人贷信息		
	private MerchantLoanInfoVO merchantLoanInfoVO;         // 淘宝商户贷信息
	private MasterLoanInfoAVO masterLoanInfoAVO;     //网购达人贷A信息
	private MasterLoanInfoBVO masterLoanInfoBVO;     //网购达人贷B信息
	 
	
	public EstateInfoVO getEstateInfoVO() {
		return estateInfoVO;
	}
	public void setEstateInfoVO(EstateInfoVO estateInfoVO) {
		this.estateInfoVO = estateInfoVO;
	}
	public CarInfoVO getCarInfoVO() {
		return carInfoVO;
	}
	public void setCarInfoVO(CarInfoVO carInfoVO) {
		this.carInfoVO = carInfoVO;
	}
	public PolicyInfoVO getPolicyInfoVO() {
		return policyInfoVO;
	}
	public void setPolicyInfoVO(PolicyInfoVO policyInfoVO) {
		this.policyInfoVO = policyInfoVO;
	}
	public ProvidentInfoVO getProvidentInfoVO() {
		return providentInfoVO;
	}
	public void setProvidentInfoVO(ProvidentInfoVO providentInfoVO) {
		this.providentInfoVO = providentInfoVO;
	}
	public CardLoanInfoVO getCardLoanInfoVO() {
		return cardLoanInfoVO;
	}
	public void setCardLoanInfoVO(CardLoanInfoVO cardLoanInfoVO) {
		this.cardLoanInfoVO = cardLoanInfoVO;
	}
	public SalaryLoanInfoVO getSalaryLoanInfoVO() {
		return salaryLoanInfoVO;
	}
	public void setSalaryLoanInfoVO(SalaryLoanInfoVO salaryLoanInfoVO) {
		this.salaryLoanInfoVO = salaryLoanInfoVO;
	}
	public MasterLoanInfoVO getMasterLoanInfoVO() {
		return masterLoanInfoVO;
	}
	public void setMasterLoanInfoVO(MasterLoanInfoVO masterLoanInfoVO) {
		this.masterLoanInfoVO = masterLoanInfoVO;
	}
	public MerchantLoanInfoVO getMerchantLoanInfoVO() {
		return merchantLoanInfoVO;
	}
	public void setMerchantLoanInfoVO(MerchantLoanInfoVO merchantLoanInfoVO) {
		this.merchantLoanInfoVO = merchantLoanInfoVO;
	}
	public AssetsInfoVO(){
		
	}
	public AssetsInfoVO(String sysCode){
		super.setSysCode(sysCode);
	}
	public MasterLoanInfoAVO getMasterLoanInfoAVO() {
		return masterLoanInfoAVO;
	}
	public void setMasterLoanInfoAVO(MasterLoanInfoAVO masterLoanInfoAVO) {
		this.masterLoanInfoAVO = masterLoanInfoAVO;
	}
	public MasterLoanInfoBVO getMasterLoanInfoBVO() {
		return masterLoanInfoBVO;
	}
	public void setMasterLoanInfoBVO(MasterLoanInfoBVO masterLoanInfoBVO) {
		this.masterLoanInfoBVO = masterLoanInfoBVO;
	}
	
 
}
