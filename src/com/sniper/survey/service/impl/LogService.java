package com.sniper.survey.service.impl;

import com.sniper.survey.model.Log;
import com.sniper.survey.service.BaseService;

public interface LogService extends BaseService<Log> {

	void createLogTable(String tableName);
	
	@Override
	public void saveEntiry(Log t);

}
