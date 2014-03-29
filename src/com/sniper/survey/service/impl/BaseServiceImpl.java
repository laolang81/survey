package com.sniper.survey.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.service.BaseService;

/**
 * @Resource写法不能乱写，写在字段上和set上
 * @author laolang
 * 
 * @param <T>
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> dao;

	// 注入
	@Resource
	public BaseDao<T> getDao() {
		return dao;
	}

	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}

	@Override
	public void saveEntiry(T t) {
		dao.saveEntiry(t);

	}

	@Override
	public void saveOrUpdateEntiry(T t) {
		dao.saveOrUpdateEntiry(t);

	}

	@Override
	public void updateEntiry(T t) {
		dao.updateEntiry(t);

	}

	@Override
	public void deleteEntiry(T t) {
		dao.deleteEntiry(t);

	}

	@Override
	public void batchEntiryByHQL(String hql, Object... Object) {
		dao.batchEntiryByHQL(hql, Object);

	}

	@Override
	public T loadEntity(Integer id) {

		return dao.loadEntity(id);
	}

	@Override
	public T getEntity(Integer id) {

		return dao.getEntity(id);
	}

	@Override
	public List<T> findEntityByHQL(String hql, Object... Object) {
	
		return dao.findEntityByHQL(hql, Object);
	}

}
