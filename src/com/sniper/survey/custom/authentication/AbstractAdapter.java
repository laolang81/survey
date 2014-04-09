package com.sniper.survey.custom.authentication;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.sniper.survey.service.BaseService;

public abstract class AbstractAdapter<T> extends BaseAbstractAdapter {

	/**
	 * 处理数据的service
	 */
	private BaseService service;
	/**
	 * identityColunm列的名称
	 */
	private String identityColunm;
	/**
	 * 
	 */
	private String credentialcolumn;

	/**
	 * 返回结果
	 */
	public AuthenticateResultInfoInterface authenticateResultInfo = new AuthenticateResultInfo();

	/**
	 * 尸体类
	 */
	private T model;
	Class<T> clazz;

	/**
	 * $ambiguityIdentity - Flag to indicate same Identity can be used with
	 * different credentials. Default is FALSE and need to be set to true to
	 * allow ambiguity usage.
	 * 
	 * @var bool
	 */
	private boolean ambiguityIdentity = false;

	public AbstractAdapter() {

	}

	public AbstractAdapter(BaseService service, String identityColunm,
			String credentialcolumn) {
		super();
		this.service = service;
		this.identityColunm = identityColunm;
		this.credentialcolumn = credentialcolumn;
	}

	public BaseService getService() {
		return service;
	}

	public AbstractAdapter setService(BaseService service) {
		this.service = service;
		return this;
	}

	public String getIdentityColunm() {
		return identityColunm;
	}

	public AbstractAdapter setIdentityColunm(String identityColunm) {
		this.identityColunm = identityColunm;
		return this;
	}

	public String getCredentialcolumn() {
		return credentialcolumn;
	}

	public void setCredentialcolumn(String credentialcolumn) {
		this.credentialcolumn = credentialcolumn;
	}

	public AuthenticateResultInfoInterface getAuthenticateResultInfo() {
		return authenticateResultInfo;
	}

	public void setAuthenticateResultInfo(
			AuthenticateResultInfoInterface authenticateResultInfo) {
		this.authenticateResultInfo = authenticateResultInfo;
	}

	public T getModel() {

		if (model != null) {
			return model;
		}
		ParameterizedType Type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) Type.getActualTypeArguments()[0];
		try {
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return model;

	}

	public void setModel(T model) {
		this.model = model;
	}

	public boolean isAmbiguityIdentity() {
		return ambiguityIdentity;
	}

	public AbstractAdapter setAmbiguityIdentity(boolean ambiguityIdentity) {
		this.ambiguityIdentity = ambiguityIdentity;
		return this;
	}

	/**
	 * 返回结果
	 * 
	 * @param t
	 * 
	 * @param t
	 * @return
	 */
	abstract protected AuthenticateResultInfoInterface authenticateValidateResult(
			T t);

	/**
	 * 执行查询
	 * 
	 * @return
	 */
	abstract protected SQLQuery authenticateCreateSelect();

	/**
	 * 执行验证
	 */
	@Override
	public AuthenticateResultInfoInterface authenticate() {
		authenticateSetup();
		SQLQuery query = authenticateCreateSelect();
		List<T> resultIdentities = authenticateQuerySelect(query);
		AuthenticateResultInfoInterface authResult = null;
		if ((authResult = authenticateValidateResultSet(resultIdentities)) != null) {
			return authResult;
		}
		for (T t : resultIdentities) {
			System.out.println(t);
			authResult = authenticateValidateResult(t);
			if (authResult.isValid()) {
				break;
			}
		}
		return authResult;
	}

	/**
	 * 返回结果 这里只检查用户读取的记录是否准确，不检查密码验证
	 * 
	 * @param resultIdentities
	 * @return
	 */
	private AuthenticateResultInfoInterface authenticateValidateResultSet(
			List<T> resultIdentities) {



		if (resultIdentities.size() < 1) {
			authenticateResultInfo.setCode(Result.FAILURE_IDENTITY_NOT_FOUND);
			authenticateResultInfo.getMessage().add(
					"A record with the supplied identity could not be found.");
			return this.authenticateCreateAuthResult();
		} else if (resultIdentities.size() > 1 && !this.isAmbiguityIdentity()) {

			authenticateResultInfo.setCode(Result.FAILURE_IDENTITY_AMBIGUOUS);
			authenticateResultInfo.getMessage().add(
					"More than one record matches the supplied identity.");
			return this.authenticateCreateAuthResult();
		}

		return null;
	}

	protected AuthenticateResultInfoInterface authenticateCreateAuthResult() {

		return new AuthenticateResultInfo(
				this.authenticateResultInfo.getCode(),
				this.authenticateResultInfo.getMessage(),
				this.authenticateResultInfo.getObj());
	}

	/**
	 * 
	 * @param query
	 * @return
	 */
	private List<T> authenticateQuerySelect(Query query) {

		return query.list();
	}

	private void authenticateSetup() {

		this.authenticateResultInfo.setCode(Result.FAILURE);
		this.authenticateResultInfo.getMessage().add("null");
		this.authenticateResultInfo.setObj(getModel());

	}

	/**
	 * 获取表名字
	 * 
	 * @param classtype
	 * @return
	 */
	public String getTableName(Class classtype) {
		Annotation[] anno = classtype.getAnnotations();
		String tableName = "";
		for (int i = 0; i < anno.length; i++) {
			if (anno[i] instanceof Table) {
				Table table = (Table) anno[i];
				System.out.println(table.name());
				tableName = table.name();
			}
		}
		return tableName;
	}

}
