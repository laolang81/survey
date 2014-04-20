package com.sniper.survey.service.impl;

import java.util.List;

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

		this.executeSQL(Log.class, sql, t.getUser(), t.getName(), t.getParams(),
				t.getResult(), t.getResultMsg(), t.getTime());
	}

	public void te() {
		String sql = "select table_name from tables where table_schema = 'jsbc_demo' "
				+ " and table_name like 'mc_logs_%' and table_name < '2014_8'"
				+ " order table_name";
	}

	public List<Log> findNearesLogs(int n) {
		String tableName = LogUtil.generateLogTableName(0);

		// 查询最近的日志表名称
		String sql = "select table_name from information_schema.tables "
				+ " where table_schema = 'jdbc_demo' "
				+ " and table_name like 'mc_logs_%' "
				+ " and table_name <= '" + tableName + "'"
				+ " order by table_name desc limit 0," + n;
		List list = (List) this.findEntityBySQLQuery(sql).uniqueResult();
		
		return null;
	}
}
