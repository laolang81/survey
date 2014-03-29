package com.sniper.survey.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("BaseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {

	// Spring 3.x 对 Hibernate 4.x 不提供
	// HibernateDaoSupport，所以在dao的实现层注入SessionFactory
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();// 增删改使用的session
	}

	public Session qryCurrentSesion() {
		return sessionFactory.openSession();// 查询使用的session
	}

	@SuppressWarnings("unchecked")
	public List<T> qryInfo(String hql) {
		return this.qryCurrentSesion().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> qryInfo(String hql, Object[] param) {
		Query qry = this.qryCurrentSesion().createQuery(hql);
		setQueryParams(qry, param);
		return qry.list();
	}

	public void Delete(T cls) {

		// 把脱管实例的状态复制到新实例，否则有时异常：illegally attempted to associate a proxy with
		// two open Sessions
		Object obj = this.getCurrentSession().merge(cls);
		this.getCurrentSession().delete(obj);
	}

	public void upd(T cls) {
		this.getCurrentSession().update(cls);
	}

	public void add(T cls) {
		this.getCurrentSession().saveOrUpdate(cls);
	}

	public void setQueryParams(Query qry, Object[] params) {
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				qry.setParameter(i, params[i]);
			}
		}
	}

}
