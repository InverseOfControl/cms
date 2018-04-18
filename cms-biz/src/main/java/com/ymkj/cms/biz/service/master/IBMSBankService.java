package com.ymkj.cms.biz.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.entity.master.BMSBank;
import com.ymkj.cms.biz.entity.master.BMSChannel;

public interface IBMSBankService extends BaseService<BMSBank> {

	public long insert(BMSBank bank);

	public void delete(BMSBank bank);

	public void update(BMSBank bank);

	public List<BMSBank> selectAllBank(Map<String, Object> param);

	public BMSBank findOne(ReqBMSBankVO reqBankVO);
	/**
	 * 根据渠道获取银行
	 * @param paramMap
	 * @return
	 */
	public List<BMSBank> getBankByChannelId(Map<String, Object> paramMap);
	
	public boolean checkBankIsExits(Map<String, Object> paramMap);
	/**
	 * 根据银行ID查询该银行是都有签约
	 * @param reqBankVO
	 * @return
	 */
	public Boolean findChannelBankById(ReqBMSBankVO reqBankVO);
	
	/**
	 * 根据银行是否启/禁用（通过银行ID），更新签约银行里面对应的该银行是否启/禁用
	 */
	public void  accordingBankIdUpdateChannelBank(ReqBMSBankVO reqBankVO);

}
