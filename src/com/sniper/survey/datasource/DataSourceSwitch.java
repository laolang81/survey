package com.sniper.survey.datasource;

/**
 * 数据源切换
 * 在数据操作中切换数据源代码
 * DataSourceSwitch.setDataSource(DataSourceSwitch.DATA_SOURCE_MASTER);
 * @author sniper
 *
 */
public class DataSourceSwitch {

	public static final String DATA_SOURCE_MASTER = "master";
	public static final String DATA_SOURCE_SLAVE_A = "salve_a";

	public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

	
	
	public static void setDataSource(String dataSourceType) {
		System.out.println("数据源切换到" + dataSourceType);
		THREAD_LOCAL.set(dataSourceType);
	}
	
	public static String getDataSource() {
		System.out.println("获取数据源" + THREAD_LOCAL.get());
		return THREAD_LOCAL.get();
	}

	public static void clearDataSource() {
		THREAD_LOCAL.remove();
	}
}
