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
import com.sniper.survey.service.impl.LogService;
import com.sniper.survey.util.LogUtil;

/**
 * sping监听器
 * 初始化监听器，咋spring初始化完成后立即调用
 * 负责完成权限的初始化
 * @author laolang
 * 
 */


public class IniLogListener implements ApplicationListener {

	/*@Resource
	private LogService logService;*/

	

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		// 上下文刷新事件
		/*if (arg0 instanceof ContextRefreshedEvent) {
			//创建日志表
			
			String tableName = LogUtil.generateLogTableName(0);
			logService.createLogTable(tableName);
			//下一个月
			tableName = LogUtil.generateLogTableName(1);
			logService.createLogTable(tableName);
			//下一个月、
			tableName = LogUtil.generateLogTableName(2);
			logService.createLogTable(tableName);
			
			System.out.println("初始化表");
		}*/

	}



}
