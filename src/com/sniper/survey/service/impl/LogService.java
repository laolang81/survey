package com.sniper.survey.service.impl;

import java.util.List;

import com.sniper.survey.model.Log;
import com.sniper.survey.service.BaseService;

public interface LogService extends BaseService<Log> {

	void createLogTable(String tableName);
	
	public List<Log> findNearesLogs(int n);

}
