package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.Log;
import com.sniper.survey.util.LogUtil;

@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {

	@Resource(name = "logDao")
	public void setDao(BaseDao<Log> dao) {
		super.setDao(dao);
	}

	@Override
	public void createLogTable(String tableName) {
		String sql = "create table if not exists " + tableName + "like mc_logs";
		this.findEntityBySQLQuery(sql);
	}

	/**
	 * 重写该方法动态插入数据表
	 */
	@Override
	public void saveEntiry(Log t) {
		String sql = "insert into "
				+ LogUtil.generateLogTableName(0)
				+ " (ml_user,ml_name,ml_param,ml_result,ml_result_msg,ml_time) "
				+ "values(?,?,?,?,?,?)";

		this.executeSQL(sql, t.getUser(), t.getName(), t.getParams(),
				t.getResult(), t.getResultMsg(), t.getTime());
	}
}
