package com.sniper.survey.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sniper.survey.dao.BaseDao;

/**
 * 单例
 * 
 * @author laolang
 * 
 * @param <T>
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

	// 声明反射用，获取的类反射类型
	private Class<T> clazz;
	// 注入session
	//@Resource
	@Autowired
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
		System.out.println(sessionFactory);
		System.out.println("sssssssss");
		//return null;
		return sessionFactory.openSession();
	}

	@Override
	public void saveEntiry(T t) {

		this.getCurrentSession().save(t);
	}

	@Override
	public void saveOrUpdateEntiry(T t) {

		this.getCurrentSession().saveOrUpdate(t);

	}

	@Override
	public void updateEntiry(T t) {

		this.getCurrentSession().update(t);

	}

	@Override
	public void deleteEntiry(T t) {

		this.getCurrentSession().delete(t);
	}

	@Override
	public void batchEntiryByHQL(String hql, Object... Object) {

		Query query = this.getCurrentSession().createQuery(hql);
		for (int i = 0; i < Object.length; i++) {
			query.setParameter(i, Object[i]);
		}
		query.executeUpdate();

	}

	/*
	 * (non-Javadoc) 可以产生代理，load不存咋出异常
	 * 
	 * @see com.sniper.survey.dao.BaseDao#loadEntity(java.lang.Integer)
	 */
	@Override
	public T loadEntity(Integer id) {
		return (T) this.getOpenSesion().load(clazz, id);

	}

	/**
	 * 不能产生代理，记录为空没有异常
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getEntity(Integer id) {

		return (T) this.getOpenSesion().get(clazz, id);
	}

	@Override
	public List<T> findEntityByHQL(String hql, Object... Object) {

		Query query = this.getOpenSesion().createQuery(hql);
		for (int i = 0; i < Object.length; i++) {
			query.setParameter(i, Object[i]);
		}
		return query.list();
	}

}
