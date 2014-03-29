package com.sniper.survey.dao;

import java.util.List;

public interface BaseDao<T> {

	// 写操作
	public void saveEntiry(T t);

	public void saveOrUpdateEntiry(T t);

	public void updateEntiry(T t);

	public void deleteEntiry(T t);

	public void batchEntiryByHQL(String hql, Object... Object);

	// 读操作
	public T loadEntity(Integer id);

	public T getEntity(Integer id);

	public List<T> findEntityByHQL(String hql, Object... Object);

}
