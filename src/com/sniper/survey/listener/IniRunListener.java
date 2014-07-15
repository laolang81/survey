package com.sniper.survey.listener;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.sniper.survey.service.impl.SystemConfigService;

/**
 * 添加开始运行监听器，初始化一些参数
 * 
 * @author laolang
 * 
 */
@Component
@SuppressWarnings("rawtypes")
public class IniRunListener implements ApplicationListener, ServletContextAware {

	ServletContext context;

	@Resource
	SystemConfigService configService;

	@Override
	public void setServletContext(ServletContext arg0) {
		this.context = arg0;

	}

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		// 在spring初始化之后
		if (arg0 instanceof ContextRefreshedEvent) {
			// 保存系统参数配置

			Map<String, String> map = new HashMap<>();
			map = configService.getCAdminConfig(true);

			context.setAttribute("systemConfig", map);

			System.out.println("配置初始化");
		}
	}

}
