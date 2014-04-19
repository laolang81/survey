package com.sniper.survey.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.sniper.survey.service.impl.LogService;
import com.sniper.survey.util.LogUtil;

public class CreateLogTableTask extends QuartzJobBean {

	private LogService logService;

	/**
	 * 注入 logService
	 * 
	 * @param logService
	 */
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// 创建日志表
		System.out.println("cron ....... ");
		/*String tableName = LogUtil.generateLogTableName(0);
		logService.createLogTable(tableName);
		System.out.println(tableName + " create");
		// 下一个月
		tableName = LogUtil.generateLogTableName(1);
		logService.createLogTable(tableName);
		System.out.println(tableName + " create");
		// 下一个月、
		tableName = LogUtil.generateLogTableName(2);
		logService.createLogTable(tableName);
		System.out.println(tableName + " create");*/
	}

}
