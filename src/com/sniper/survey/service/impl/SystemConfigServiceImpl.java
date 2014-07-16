package com.sniper.survey.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.SystemConfig;

@Repository("systemConfigService")
public class SystemConfigServiceImpl extends BaseServiceImpl<SystemConfig>
		implements SystemConfigService {

	@Override
	@Resource(name = "systemConfigDao")
	public void setDao(BaseDao<SystemConfig> dao) {
		super.setDao(dao);
	}

	@Override
	public Map<String, String> getAdminConfig(Boolean autoload) {

		Map<String, String> map = new HashMap<>();

		String hql = "select new SystemConfig(sc.keyName, sc.keyValue) from SystemConfig sc where sc.autoload = ?";
		List<SystemConfig> configs = this.findEntityByHQL(hql, autoload);
		for (SystemConfig config : configs) {
			map.put(config.getKeyName(), config.getKeyValue());
		}
		return map;
	}

}
