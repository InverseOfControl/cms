package com.ymkj.cms.biz.common.loan;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.ymkj.cms.biz.common.sign.SignRoutes.SignRoute;

/**
 * 签约环节 路由
 * 
 * @author YM10138
 *
 */
public abstract class LoanRoutes {

	private String channelCd = null;
	@SuppressWarnings("rawtypes")
	private List<LoanRoute> routeItemList = new ArrayList<LoanRoute>();

	@PostConstruct
	public abstract void config();

	/**
	 * 指定渠道
	 * 
	 * @param code
	 *            渠道编号
	 */
	public void setChannelCd(String code) {
		this.channelCd = code;
		LoanFactory.addChannelCd(channelCd);
	}

	/**
	 * 添加 节点处理方法
	 * 
	 * @param node
	 *            业务节点
	 * @param controllerClass
	 *            节点处理方法
	 */
	public <T,M> void add(String node, Class<? extends Loan<T,M>> controllerClass) {
		// 缓存
		routeItemList.add(new LoanRoute(node, controllerClass));
		// 添加到工厂
		LoanFactory.addRoutes(this.channelCd, node, controllerClass);
	}

	public static class LoanRoute<T,M> {
		private String controllerKey;
		private Class<? extends Loan<T,M>> controllerClass;

	public LoanRoute(String controllerKey, Class<? extends Loan<T,M>> controllerClass) {
			this.controllerKey = controllerKey;
			this.controllerClass = controllerClass;
		}

		public String getControllerKey() {
			return controllerKey;
		}

		public void setControllerKey(String controllerKey) {
			this.controllerKey = controllerKey;
		}

	public Class<? extends Loan<T,M>> getControllerClass() {
			return controllerClass;
		}

		public void setControllerClass(Class<? extends Loan<T,M>> controllerClass) {
			this.controllerClass = controllerClass;
		}
	}
}
