package com.sniper.survey.custom.authentication;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.sniper.survey.service.BaseService;

@SuppressWarnings("rawtypes")
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
	protected Class<T> clazz;

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

	@SuppressWarnings("unchecked")
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
			Map t);

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
		// 获取转成对象,者获取的是用户对象数据
		List<Map> resultIdentities = authenticateQuerySelectMap(query);
		/*System.out.println("authenticate");
		System.out.println(resultIdentities);*/
		AuthenticateResultInfoInterface authResult = null;
		// 检测用户获取的数量是否等于1，这里只检测用户数量是否唯一，不唯一报错
		if ((authResult = authenticateValidateResultSet(resultIdentities)) != null) {
			return authResult;
		}
		// 检测用户密码是否正确，auth等于1表示用户密码正确
		for (Map m : resultIdentities) {
			/*System.out.println("0===");
			System.out.println(m);*/
			authResult = authenticateValidateResult(m);
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
			List<Map> resultIdentities) {

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
	 * 获取对象数据
	 * 
	 * @param query
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<T> authenticateQuerySelect(SQLQuery query) {
		return query.addEntity(clazz).list();
	}

	/**
	 * 获取map数据
	 * 
	 * @param query
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private List<Map> authenticateQuerySelectMap(SQLQuery query) {
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.list();
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
