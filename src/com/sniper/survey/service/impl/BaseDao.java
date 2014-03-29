package com.sniper.survey.service.impl;

import java.util.List;

import org.hibernate.Query;

public interface BaseDao<T> {

	List<T> qryInfo(String hql);

	List<T> qryInfo(String hql, Object[] param);

	void Delete(T cls);

	void upd(T cls);

	void add(T cls);

	void setQueryParams(Query qry, Object[] params);

}
