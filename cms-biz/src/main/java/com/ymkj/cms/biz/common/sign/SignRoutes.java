package com.ymkj.cms.biz.common.sign;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

/**
 * 签约环节 路由
 * 
 * @author YM10138
 *
 */
public abstract class SignRoutes {

	private String channelCd = null;
	@SuppressWarnings("rawtypes")
	private List<SignRoute> routeItemList = new ArrayList<SignRoute>();

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
		SignFactory.addChannelCd(channelCd);
	}

	/**
	 * 添加 节点处理方法
	 * 
	 * @param node
	 *            业务节点
	 * @param controllerClass
	 *            节点处理方法
	 */
	public <T> void add(String node, Class<? extends Sign<T>> controllerClass) {
		// 缓存
		routeItemList.add(new SignRoute<T>(node, controllerClass));
		// 添加到工厂
		SignFactory.addRoutes(this.channelCd, node, controllerClass);
	}

	/**
	 * 添加 节点处理方法
	 * 
	 * @param node
	 *            业务节点
	 * @param controllerClass
	 *            节点处理方法
	 * @param version
	 *            节点版本号
	 */
	public <T> void add(String node, Class<? extends Sign<T>> controllerClass, String version) {
		// 缓存
		routeItemList.add(new SignRoute<T>(version, node, controllerClass));
		// 添加到工厂
		SignFactory.addRoutes(this.channelCd, node.concat(".").concat(version), controllerClass);
	}

	public static class SignRoute<T> {
		private String version;
		private String controllerKey;
		private Class<? extends Sign<T>> controllerClass;

		public SignRoute(String controllerKey, Class<? extends Sign<T>> controllerClass) {
			this.controllerKey = controllerKey;
			this.controllerClass = controllerClass;
		}

		public SignRoute(String version, String controllerKey, Class<? extends Sign<T>> controllerClass) {
			this.version = version;
			this.controllerKey = controllerKey;
			this.controllerClass = controllerClass;
		}

		public String getControllerKey() {
			return controllerKey;
		}

		public void setControllerKey(String controllerKey) {
			this.controllerKey = controllerKey;
		}

		public Class<? extends Sign<T>> getControllerClass() {
			return controllerClass;
		}

		public void setControllerClass(Class<? extends Sign<T>> controllerClass) {
			this.controllerClass = controllerClass;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}
	}
}
