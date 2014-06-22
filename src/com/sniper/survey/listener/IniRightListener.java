package com.sniper.survey.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.sniper.survey.model.AdminRight;
import com.sniper.survey.service.impl.AdminRightService;

/**
 * sping监听器
 * 初始化监听器，咋spring初始化完成后立即调用
 * 负责完成权限的初始化
 * @author laolang
 * 
 */

@Component
@SuppressWarnings("rawtypes")
public class IniRightListener implements ApplicationListener,
		ServletContextAware {

	@Resource
	private AdminRightService service;

	private ServletContext context;

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		// 上下文刷新事件,在spring容器启动之后
		if (arg0 instanceof ContextRefreshedEvent) {
			// 查处所有权限
			List<AdminRight> rights = service.findAllEntitles();
			Map<String, AdminRight> map = new HashMap<>();
			for (AdminRight r : rights) {
				map.put(r.getUrl(), r);
			}
			if (context != null) {
				context.setAttribute("all_rights_map", map);
				System.out.println("初始化所有权限到appliaction中");
			}
		}
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.context = arg0;

	}

}
