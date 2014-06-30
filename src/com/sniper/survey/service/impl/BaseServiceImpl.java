package com.sniper.survey.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.service.BaseService;

/**
 * @Resource写法不能乱写，写在字段上和set上可以用子类覆盖，而字段不能被覆盖
 * @author laolang
 * 
 * @param <T>
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> dao;
	//
	private Class<T> clazz;

	public BaseDao<T> getDao() {
		return dao;
	}

	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	public BaseServiceImpl(BaseDao<T> dao, Class<T> clazz) {
		super();
		this.dao = dao;
		this.clazz = clazz;
	}

	// 注入
	@Resource
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}

	@Override
	public Session getOpenSession() {
		return dao.getOpenSesion();
	}
	
	public void saveEntiry(T t) {
		dao.saveEntiry(t);

	}

	public void saveOrUpdateEntiry(T t) {
		
		
		dao.saveOrUpdateEntiry(t);

	}

	@Override
	public void savePersist(T t) {
		dao.savePersist(t);

	}

	@Override
	public void saveMerge(T t) {
		dao.saveMerge(t);
	}

	public void updateEntiry(T t) {
		dao.updateEntiry(t);

	}

	public void deleteEntiry(T t) {
		dao.deleteEntiry(t);

	}

	public void batchEntiryByHQL(String hql, Object... Object) {
		dao.batchEntiryByHQL(hql, Object);

	}

	public T loadEntity(Integer id) {
		return dao.loadEntity(id);
	}

	public T getEntity(Integer id) {
		return dao.getEntity(id);
	}

	public List<T> findEntityByHQL(String hql, Object... Object) {

		return dao.findEntityByHQL(hql, Object);
	}

	@Override
	public Query findEntityByHQLQuery(String hql, Object... Object) {
		return dao.findEntityByHQLQuery(hql, Object);
	}

	@Override
	public SQLQuery findEntityBySQLQuery(String sql, Object... Object) {
		return dao.findEntityBySQLQuery(sql, Object);
	}

	@Override
	public Object uniqueResult(String hql, Object... objects) {

		return this.findEntityByHQLQuery(hql, objects).uniqueResult();
	}

	@Override
	public List<T> findAllEntitles() {
		String hql = "from " + clazz.getSimpleName();
		return this.findEntityByHQL(hql);
	}

	@Override
	public void executeSQL(Class clazz, String hql, Object... Object) {
		dao.executeSQL(clazz, hql, Object);
	}
	@Override
	public List<T> page(String hql, int firstResult, int maxResult,  Object... Object)
	{
		return dao.findEntityByHQL(hql, firstResult, maxResult, Object);
	}
}
