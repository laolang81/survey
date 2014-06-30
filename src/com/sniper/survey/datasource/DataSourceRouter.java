package com.sniper.survey.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 分库数据源路由配置 自定义分布式数据库(数据库路由器)
 * 
 * @author laolang
 * 
 */
public class DataSourceRouter extends AbstractRoutingDataSource {

	/**
	 * 检测key 走那个数据源和这个key有关系
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		// 得到当前的令牌
		RightToken token = RightToken.getCurrentToken();
		System.out.println("经过了 DataSourceRouter");
		
		if (token != null) {

			Integer id = token.getRight().getId();
			// 解除令牌绑定
			RightToken.unbindToken();
			System.out.println(id);
			return ((id % 2) == 0) ? "even" : "odd";
		}
		return null;
	}

}
