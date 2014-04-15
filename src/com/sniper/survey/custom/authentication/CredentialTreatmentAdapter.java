package com.sniper.survey.custom.authentication;

import java.math.BigInteger;
import java.util.Map;

import org.hibernate.SQLQuery;

import com.sniper.survey.service.BaseService;
import com.sniper.survey.util.BeanToMapUtil;

@SuppressWarnings("rawtypes")
public class CredentialTreatmentAdapter<T> extends AbstractAdapter<T> {

	private String credentialTreatment = null;

	public CredentialTreatmentAdapter() {
		super();
	}

	/**
	 * 构造器 用户参数赋值
	 * 
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
	protected AuthenticateResultInfoInterface authenticateValidateResult(Map m) {

		if (m == null) {
			this.authenticateResultInfo
					.setCode(Result.FAILURE_CREDENTIAL_INVALID);
			this.authenticateResultInfo.getMessage().add(
					"Supplied credential is invalid.");
			return authenticateCreateAuthResult();
		}
		// setModel(m);
		// 密码正确
		// System.out.println(m.get("auth"));
		// System.out.println(m.get("auth").getClass().getName());
		BigInteger authbig = (BigInteger) m.get("auth");
		Integer auth = authbig.intValue();

		if (auth != null && auth == 1) {
			m.remove("auth");
			T t = null;
			try {
				t = (T) BeanToMapUtil.convertMap(clazz, m, "au_");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// System.out.println(m);
			setModel(t);
			this.authenticateResultInfo.setObj(t);
			this.authenticateResultInfo.setCode(Result.SUCCESS);
			this.authenticateResultInfo.getMessage().add(
					"Authentication successful.");
		} else {

			this.authenticateResultInfo.setCode(Result.FAILURE_UNCATEGORIZED);
			this.authenticateResultInfo.getMessage().add(
					"Authentication faild.");
		}

		return authenticateCreateAuthResult();
	}

	@Override
	protected SQLQuery authenticateCreateSelect() {

		if (this.credentialTreatment == null
				|| (this.credentialTreatment.indexOf("?") == -1)) {
			this.credentialTreatment = "?";
		}

		String sqlExpr = "(CASE WHEN " + getCredentialcolumn() + " = "
				+ this.credentialTreatment + " THEN 1 ELSE 0 END) AS auth";

		String hql = "SELECT *, " + sqlExpr + " FROM " + getTableName(clazz)
				+ " as u WHERE " + getIdentityColunm() + "= ? ";

		SQLQuery query = (SQLQuery) getService().findEntityBySQLQuery(hql)
		// .setResultTransformer(new AliasToBeanResultTransformer (clazz))
		// .addEntity("u", clazz)
		// .addScalar("auth", IntegerType.INSTANCE)
				.setString(0, getCredential()).setString(1, getIdentity());

		return query;

	}
}
