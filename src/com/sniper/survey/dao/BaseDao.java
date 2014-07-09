package com.sniper.survey.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 * 主控操作和关联操作的先后顺序是“先保存one，再保存many；先删除many，再删除one；先update主控方，再update被动方”
 * 
 * @author sniper
 * 
 * @param <T>
 */
public interface BaseDao<T> {

	/**
	 * 测试专用
	 */
	public Session getOpenSesion();
	
	// 写操作
	/**
	 * save会立即执行sql语句 保存一个新的对象
	 * 
	 * @param t
	 */
	public void saveEntiry(T t);

	/**
	 * save 和update的
	 * 
	 * @param t
	 */
	public void saveOrUpdateEntiry(T t);

	/**
	 * 保存游离状态的对象 执行完毕之后当前对象会变成持久对象
	 * 
	 * @param t
	 */
	public void updateEntiry(T t);

	public void deleteEntiry(T t);

	public void batchEntiryByHQL(String hql, Object... Object);

	// 级联关系保存
	/**
	 * persist执行不会立即执行insert语句 执行时间可能会推到flush时间上 可以用在一个长久会话上
	 * 
	 * @param t
	 */
	public void savePersist(T t);

	public void saveMerge(T t);

	/**
	 * obj有四个可选值 xml配置中的unsaved-value any 总是储存 none 总是更新 null ID为null时储存 valid
	 * ID为null或者指定值储存
	 * 
	 * @param t
	 * @param obj
	 */
	public void saveReplicata(T t, ReplicationMode obj);

	// 读操作
	public T loadEntity(Integer id);

	public T getEntity(Integer id);

	public List<T> findEntityByHQL(String hql, Object... Object);

	public List<T> findEntityByHQL(String hql, int firstResult, int maxResult,
			Object... Object);

	public Query findEntityByHQLQuery(String hql, Object... Object);

	public SQLQuery findEntityBySQLQuery(String sql, Object... Object);
	
	public void executeSQL(Class clazz, String hql, Object... Object);
	
	public Criteria criteria();
}
