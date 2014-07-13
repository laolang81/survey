package com.sniper.survey.service.impl;

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

}
