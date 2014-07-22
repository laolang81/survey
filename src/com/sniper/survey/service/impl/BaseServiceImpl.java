package com.sniper.survey.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.service.BaseService;
import com.sniper.survey.util.StrutsPage;
import com.sniper.survey.util.ValidateUtil;

/**
 * @Resource写法不能乱写，写在字段上和set上可以用子类覆盖，而字段不能被覆盖
 * @author laolang
 * 
 * @param <T>
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	// 数据库死的一些变量
	private String entityAsName = "ean";
	private String where;
	private String join;
	private String having;
	private String group;
	private String order;
	private boolean distinct = true;

	private String pageHtml;
	private List<T> lists;

	private BaseDao<T> dao;
	//
	private Class<T> clazz;

	public BaseDao<T> getDao() {
		return dao;
	}

	public String getEntityAsName() {

		return entityAsName;
	}

	public void setEntityAsName(String entityAsName) {
		this.entityAsName = entityAsName;
	}

	public String getWhere() {
		if (ValidateUtil.isValid(where)) {
			return " WHERE " + where;
		}
		return "";
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getJoin() {
		if (ValidateUtil.isValid(join)) {
			return join;
		}
		return "";
	}

	public void setJoin(String join) {
		this.join = join;
	}

	public String getHaving() {
		if (ValidateUtil.isValid(having)) {
			return " HAVING " + having;
		}
		return "";
	}

	public void setHaving(String having) {
		this.having = having;
	}

	public String getGroup() {
		if (ValidateUtil.isValid(group)) {
			return " GROUP By " + group;
		}
		return "";
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getOrder() {
		if (ValidateUtil.isValid(order)) {
			return " ORDER BY " + order;
		}
		return "";
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public String getPageHtml() {
		return pageHtml;
	}

	public List<T> getLists() {
		return lists;
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

	@Override
	public Session getCurrentSession() {

		return dao.getCurrentSession();
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

	@Override
	public boolean deleteBatchEntityById(Integer[] id) {
		if (id.length == 0) {
			return false;
		}

		String hql = "DELETE FROM " + clazz.getSimpleName() + " where id in("
				+ StringUtils.join(id, ",") + ")";
		try {
			this.batchEntiryByHQL(hql);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * 根据id,批量修改一个字段的值
	 */
	@Override
	public void batchFiledChange(String filedName, Object changeValue,
			Integer[] id) {
		String hql = "UPDATE " + clazz.getSimpleName() + " SET " + filedName
				+ "=? WHERE id in(" + StringUtils.join(id, ",") + ") ";
		dao.batchEntiryByHQL(hql, changeValue);

	}

	public T loadEntity(Integer id) {
		return dao.loadEntity(id);
	}

	@Override
	public T loadCEntity(Integer id) {
		return dao.loadEntity(id);
	}

	public T getEntity(Integer id) {
		return dao.getEntity(id);
	}

	@Override
	public T getCEntity(Integer id) {
		return dao.getEntity(id);
	}

	public List<T> findEntityByHQL(String hql, Object... Object) {
		return dao.findEntityByHQL(hql, Object);
	}

	@Override
	public List<T> findcEntityByHQL(String hql, Object... Object) {
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

	@SuppressWarnings("rawtypes")
	@Override
	public void executeSQL(Class clazz, String hql, Object... Object) {
		dao.executeSQL(clazz, hql, Object);
	}

	private List<T> page(String hql, int firstResult, int maxResult,
			Object... Object) {
		return dao.findEntityByHQL(hql, firstResult, maxResult, Object);
	}

	/**
	 * 数据苦分页使用能完成基本的需求
	 */
	@Override
	public void pageList(int listRow, Object... Object) {
		String hql = "SELECT ";

		if (isDistinct()) {
			hql += " count(DISTINCT " + getEntityAsName() + ")";
		} else {
			hql += " count(" + getEntityAsName() + ")";
		}
		hql += " FROM " + clazz.getSimpleName() + " " + getEntityAsName()
				+ getJoin() + getWhere() + getHaving();
		long l = (long) this.uniqueResult(hql, Object);
		int totalNum = new Long(l).intValue();

		StrutsPage page = new StrutsPage(totalNum, listRow);
		pageHtml = page.show();

		String hql2 = "SELECT ";
		if (isDistinct()) {
			hql2 += " DISTINCT ";
		}
		hql2 += getEntityAsName() + " FROM " + clazz.getSimpleName() + " "
				+ getEntityAsName() + getJoin() + getWhere() + getHaving()
				+ getGroup() + getOrder();
		lists = this.page(hql2, page.getFristRow(), page.getListRow(), Object);

	}

	@Override
	public Criteria criteria() {
		Criteria criteria = dao.criteria();
		return criteria;
	}
}
