package com.sniper.survey.custom.authentication;

import org.hibernate.SQLQuery;

import com.sniper.survey.service.BaseService;

public class CredentialTreatmentAdapter<T> extends AbstractAdapter<T> {

	private String credentialTreatment = null;

	public CredentialTreatmentAdapter() {
		super();
	}

	/**
	 * 构造器
	 * 用户参数赋值
	 * @param service
	 * @param identityColunm
	 * @param credentialcolumn
	 */
	public CredentialTreatmentAdapter(BaseService service,
			String identityColunm, String credentialcolumn,
			String credentialTreatment) {
		super(service, identityColunm, credentialcolumn);
		this.credentialTreatment = credentialTreatment;

	}

	@Override
	protected AuthenticateResultInfoInterface authenticateValidateResult(T t) {
		
		if (t == null) {

			this.authenticateResultInfo
					.setCode(Result.FAILURE_CREDENTIAL_INVALID);
			this.authenticateResultInfo.getMessage().add(
					"Supplied credential is invalid.");
			return authenticateCreateAuthResult();
		}
		setModel(t);
		
		this.authenticateResultInfo.setObj(t);
		this.authenticateResultInfo.setCode(Result.SUCCESS);
		this.authenticateResultInfo.getMessage().add(
				"Authentication successful.");
		return authenticateCreateAuthResult();
	}

	@Override
	protected SQLQuery authenticateCreateSelect() {

		if (this.credentialTreatment == null
				|| (this.credentialTreatment.indexOf("?") == -1)) {
			this.credentialTreatment = "?";
		}

		String sqlExpr = "(CASE WHEN ? = " + this.credentialTreatment
				+ " THEN 1 ELSE 0 END) AS ?";


		String hql = "SELECT *, " + sqlExpr + " FROM "
				+ getTableName(getModel().getClass()) + " as u WHERE "
				+ getIdentityColunm() + "= ? ";

		SQLQuery query = (SQLQuery) getService().findEntityBySQLQuery(hql)
				.addEntity("u", clazz).setParameter(0, getCredentialcolumn())
				.setParameter(1, getCredential()).setParameter(2, "auth")
				.setParameter(3, getIdentity());
		
		return query;

	}
}
