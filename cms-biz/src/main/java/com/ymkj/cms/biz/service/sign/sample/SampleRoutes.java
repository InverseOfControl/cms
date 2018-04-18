package com.ymkj.cms.biz.service.sign.sample;

import com.ymkj.cms.biz.common.sign.SignRoutes;

public class SampleRoutes extends SignRoutes {

	@Override
	public void config() {
		setChannelCd("渠道CODE");

		add("save1", Sample1.class);
		add("save2", Sample2.class);
		add("node", SampleNode.class);
		add("node", SampleNode2.class, "2.0"); // 节点不变，添加节点版本号,调用方式 ResLoanContractSignVo.channelCdVersion=2.0
	}

}
