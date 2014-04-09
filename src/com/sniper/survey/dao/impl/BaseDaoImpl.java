package com.sniper.survey.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sniper.survey.dao.BaseDao;

/**
 * 单例
 * 
 * @author laolang
 * 
 * @param <T>
 */
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	// 声明反射用，获取的类反射类型
	private Class<T> clazz;
	// 注入session
	@Resource
	private SessionFactory sessionFactory;

	public BaseDaoImpl() {
		// 得到泛型话的超类，
		Type type = this.getClass().getGenericSuperclass();
		// 如果type集成与ParameterizedType,以为内ParameterizedType可以带有参数，可以从他里面获取参数
		Type[] args = null;
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			// 获取参数列表
			args = parameterizedType.getActualTypeArguments();
			if (args != null && args.length > 0) {
				// 获取地一个参数
				Type arg = args[0];
				if (arg instanceof Class) {
					clazz = (Class<T>) arg;
				}
			}
		}
		// 以上简单写法
		// ParameterizedType Type = (ParameterizedType)
		// this.getClass().getGenericSuperclass();;
		// clazz = (Class<T>) Type.getActualTypeArguments()[0];
	}

	// 增删改使用的session
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	// 查询使用的session
	public Session getOpenSesion() {
		return sessionFactory.openSession();
	}

	public void saveEntiry(T t) {

		this.getCurrentSession().save(t);
	}

	public void saveOrUpdateEntiry(T t) {

		this.getCurrentSession().saveOrUpdate(t);

	}

	@Override
	public void savePersist(T t) {
		this.getCurrentSession().persist(t);

	}

	@Override
	public void saveMerge(T t) {
		this.getCurrentSession().merge(t);
	}

	@Override
	public void saveReplicata(T t, ReplicationMode obj) {
		this.getCurrentSession().replicate(t, obj);
	}

	public void updateEntiry(T t) {

		this.getCurrentSession().update(t);

	}

	public void deleteEntiry(T t) {

		this.getCurrentSession().delete(t);
	}

	public void batchEntiryByHQL(String hql, Object... Object) {

		Query query = this.getCurrentSession().createQuery(hql);
		if (Object != null && Object.length > 0) {
			for (int i = 0; i < Object.length; i++) {
				query.setParameter(i, Object[i]);
			}
		}
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc) 可以产生代理，load不存咋出异常
	 * 
	 * @see com.sniper.survey.dao.BaseDao#loadEntity(java.lang.Integer)
	 */

	public T loadEntity(Integer id) {
		System.out.println(clazz);
		return (T) this.getCurrentSession().load(clazz, id);

	}

	/**
	 * 不能产生代理，记录为空没有异常
	 */

	public T getEntity(Integer id) {

		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}

	public List<T> findEntityByHQL(String hql, Object... Object) {

		Query query = this.getCurrentSession().createQuery(hql);
		if (Object != null && Object.length > 0) {
			for (int i = 0; i < Object.length; i++) {
				query.setParameter(i, Object[i]);
			}
		}
		return query.list();
	}

	@Override
	public List<T> findEntityByHQL(String hql, int firstResult, int maxResult,
			Object... Object) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (Object != null && Object.length > 0) {
			for (int i = 0; i < Object.length; i++) {
				query.setParameter(i, Object[i]);
			}
		}
		return query.setFirstResult(firstResult).setMaxResults(maxResult)
				.list();
	}

	@Override
	public Query findEntityByHQLQuery(String hql, Object... Object) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (Object != null && Object.length > 0) {
			for (int i = 0; i < Object.length; i++) {
				query.setParameter(i, Object[i]);
			}
		}

		return query;
	}

	@Override
	public SQLQuery findEntityBySQLQuery(String sql) {
		SQLQuery query = this.getOpenSesion().createSQLQuery(sql);
		return query;
	}
}
