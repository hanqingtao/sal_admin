package com.ambition.agile.common.listener;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import com.ambition.agile.common.BaseConfigHolder;

/*
 * @author: hqt
 * @see: 初始化 数据 使用监听器 
 * @date:2016-5-24 下午02:49:18
 */
public class InitDataListener implements InitializingBean, ServletContextAware {

	@Override
	public void setServletContext(ServletContext arg0) {
		BaseConfigHolder.intiSystemValues();
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
	}
}