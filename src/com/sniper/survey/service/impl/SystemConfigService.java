package com.sniper.survey.service.impl;

import java.util.Map;

import com.sniper.survey.model.SystemConfig;
import com.sniper.survey.service.BaseService;

public interface SystemConfigService extends BaseService<SystemConfig> {

	public Map<String, String> getAdminConfig(Boolean autoload);
}
