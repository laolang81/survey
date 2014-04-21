package com.sniper.survey.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 分库数据源路由配置
 * 自定义分布式数据库(数据库路由器)
 * @author laolang
 *
 */
public class DataSourceRouter extends AbstractRoutingDataSource {

	/**
	 * 检测key
	 * 走那个数据源和这个key有关系
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		
		return null;
	}

}
