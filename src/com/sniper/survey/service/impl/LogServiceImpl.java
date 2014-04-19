package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.Log;

@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {

	@Resource(name = "logDao")
	public void setDao(BaseDao<Log> dao) {
		super.setDao(dao);
	}

}
