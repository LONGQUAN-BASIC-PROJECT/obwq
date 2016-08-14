package com.desksoft.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class WebContextUtil implements ServletContextListener {
	private static WebApplicationContext springContext;

	public WebContextUtil() {
		super();
	}

	public void contextInitialized(ServletContextEvent event) {
		springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	}


	public void contextDestroyed(ServletContextEvent event) {
	}

	public static ApplicationContext getApplicationContext() {
		return springContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanId) {
		return (T) WebContextUtil.springContext.getBean(beanId);
	}

}

