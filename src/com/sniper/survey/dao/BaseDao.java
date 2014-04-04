package com.sniper.survey.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.ReplicationMode;

public interface BaseDao<T> {

	// 写操作
	public void saveEntiry(T t);

	public void saveOrUpdateEntiry(T t);

	public void updateEntiry(T t);

	public void deleteEntiry(T t);

	public void batchEntiryByHQL(String hql, Object... Object);

	// 级联关系保存
	public void savePersist(T t);

	/**
	 * obj有四个可选值
	 * xml配置中的unsaved-value
	 * any 总是储存
	 * none 总是更新
	 * null ID为null时储存
	 * valid ID为null或者指定值储存
	 * @param t
	 * @param obj
	 */
	public void saveReplicata(T t, ReplicationMode obj);

	// 读操作
	public T loadEntity(Integer id);

	public T getEntity(Integer id);

	public List<T> findEntityByHQL(String hql, Object... Object);
	
	public Query 	findEntityByHQLQuery(String hql, Object... Object);

}
