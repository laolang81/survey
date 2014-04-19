package com.sniper.survey.util;

import java.util.Calendar;

/**
 * log工具类
 * @author laolang
 *
 */
public class LogUtil {

	/**
	 * 生成日志表名称 logs_2014_4
	 * offset偏移量
	 * @return
	 */
	public static String generateLogTableName(int offset)
	{
		// Calendar 日历类 
		Calendar c = Calendar.getInstance();
		// 2014
		int year = c.get(Calendar.YEAR);
		//0 -11 +1 变成1-12
		int month = c.get(Calendar.MONTH) + 1 + offset;
		
		if(month > 12){
			year++;
			month = month - 12;
		}
		if(month < 1){
			year--;
			month = month + 12;
		}
		
		return "mc_logs_" + year + "_" + month;
	}
}
