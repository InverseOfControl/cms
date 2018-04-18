package com.ymkj.cms.biz.process.provider;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.bstek.uflo.env.EnvironmentProvider;


@Component
public class BMSEnvironmentProvider implements EnvironmentProvider {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private PlatformTransactionManager platformTransactionManager;
	
	/**
     * @return 返回流程引擎需要使用的Hibernate SessionFactory
     */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	 /**
     * @return 返回与当前SessionFactory绑定的PlatformTransactionManager对象
     */
	public PlatformTransactionManager getPlatformTransactionManager() {
		return platformTransactionManager;
	}
	
	public void setPlatformTransactionManager(
	PlatformTransactionManager platformTransactionManager) {
		this.platformTransactionManager = platformTransactionManager;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	 /**
     * @return 返回当前系统的登录用户
     */
	public String getLoginUser() {
		return "admin";
	}

	  /**
     * @return 返回当前系统分类ID
     */
	public String getCategoryId() {
		return null;
	}
	
   
}
