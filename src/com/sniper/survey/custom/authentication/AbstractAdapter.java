package com.sniper.survey.custom.authentication;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;

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
	public AuthenticateResultInfoInterface authenticateResultInfo = null;

	/**
	 * 尸体类
	 */
	private T model;

	/**
	 * $ambiguityIdentity - Flag to indicate same Identity can be used with
	 * different credentials. Default is FALSE and need to be set to true to
	 * allow ambiguity usage.
	 * 
	 * @var bool
	 */
	private boolean ambiguityIdentity = false;

	public AbstractAdapter() {
		ParameterizedType Type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();

		Class clazz = (Class<T>) Type.getActualTypeArguments()[0];
		try {
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	abstract protected Query authenticateCreateSelect();

	/**
	 * 执行验证
	 */
	@Override
	public AuthenticateResultInfoInterface authenticate() {
		authenticateSetup();
		Query query = authenticateCreateSelect();
		List<T> resultIdentities = authenticateQuerySelect(query);
		AuthenticateResultInfoInterface authResult = null;
		if ((authResult = authenticateValidateResultSet(resultIdentities)) != null) {
			return authResult;
		}
		for (T t : resultIdentities) {
			
			authResult = authenticateValidateResult(t);
			if (authResult.isValid()) {
				break;
			}
		}
		return authResult;
	}

	/**
	 * 返回结果
	 * 这里只检查用户读取的记录是否准确，不检查密码验证
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
				this.authenticateResultInfo.getService());
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

		getAuthenticateResultInfo().setCode(Result.FAILURE);
		getAuthenticateResultInfo().getMessage().add("not found");
		getAuthenticateResultInfo().setService(this.service);

	}

}
