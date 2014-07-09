package com.sniper.survey.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public interface BaseService<T> {
	/**
	 * 获取新的session 测试用用
	 * 
	 * @return
	 */
	public Session getOpenSession();

	// 写操作
	public void saveEntiry(T t);

	public void saveOrUpdateEntiry(T t);

	public void updateEntiry(T t);

	public void deleteEntiry(T t);

	public void batchEntiryByHQL(String hql, Object... Object);

	// 级联关系保存
	public void savePersist(T t);

	public void saveMerge(T t);

	// 读操作
	public T loadEntity(Integer id);

	public T getEntity(Integer id);

	public List<T> findEntityByHQL(String hql, Object... Object);

	public Query findEntityByHQLQuery(String hql, Object... Object);

	public SQLQuery findEntityBySQLQuery(String sql, Object... Object);

	// 获取唯一的值
	public Object uniqueResult(String hql, Object... objects);

	// 查询所有实体
	public List<T> findAllEntitles();

	public void executeSQL(Class clazz, String hql, Object... Object);

	/**
	 * 分页
	 * 
	 * @param hql
	 * @param firstResult
	 * @param maxResult
	 * @param Object
	 * @return
	 */
	public List<T> page(String hql, int firstResult, int maxResult,
			Object... Object);

	/**
	 * 
	 * @param listRow
	 * @param Object
	 * @return
	 */
	public Map<String, Object> pageList(int listRow, Object... Object);

	public String getEntityAsName();

	public void setEntityAsName(String entityAsName);

	public String getWhere();

	public void setWhere(String where);

	public String getJoin();

	public void setJoin(String join);

	public String getHaving();

	public void setHaving(String having);

	public String getGroup();

	public void setGroup(String group);

	public String getOrder();

	public void setOrder(String order);

	public Criteria criteria();
}
