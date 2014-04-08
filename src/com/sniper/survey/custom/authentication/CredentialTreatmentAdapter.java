package com.sniper.survey.custom.authentication;

import org.hibernate.Query;

import com.sniper.survey.service.BaseService;

public class CredentialTreatmentAdapter<T> extends AbstractAdapter<T> {

	private String credentialTreatment = null;

	public String getCredentialTreatment() {
		return credentialTreatment;
	}

	public void setCredentialTreatment(String credentialTreatment) {
		this.credentialTreatment = credentialTreatment;
	}

	public CredentialTreatmentAdapter() {
	}

	/**
	 * 构造器
	 * 
	 * @param service
	 * @param identityColunm
	 * @param credentialcolumn
	 */
	public CredentialTreatmentAdapter(BaseService service,
			String identityColunm, String credentialcolumn,
			String credentialTreatment) {
		super(service, identityColunm, credentialcolumn);
		this.credentialTreatment = credentialcolumn;
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
		this.authenticateResultInfo.setCode(Result.SUCCESS);
		this.authenticateResultInfo.getMessage().add(
				"Authentication successful.");
		return authenticateCreateAuthResult();
	}

	@Override
	protected Query authenticateCreateSelect() {

		
		/* if (empty($this->credentialTreatment) || (strpos($this->credentialTreatment, '?') === false)) {
	            $this->credentialTreatment = '?';
	        }

	        $credentialExpression = new SqlExpr(
	            '(CASE WHEN ?' . ' = ' . $this->credentialTreatment . ' THEN 1 ELSE 0 END) AS ?',
	            array($this->credentialColumn, $this->credential, 'zend_auth_credential_match'),
	            array(SqlExpr::TYPE_IDENTIFIER, SqlExpr::TYPE_VALUE, SqlExpr::TYPE_IDENTIFIER)
	        );

	        // get select
	        $dbSelect = clone $this->getDbSelect();
	        $dbSelect->from($this->tableName)
	            ->columns(array('*', $credentialExpression))
	            ->where(new SqlOp($this->identityColumn, '=', $this->identity));

	        return $dbSelect;*/
	        
		if (this.credentialTreatment.isEmpty()
				|| (this.credentialTreatment.indexOf("?") == -1)) {
			this.credentialTreatment = "?";
		}

		String tname = getModel().getClass().getSimpleName();
		String hql = "SELECT auth FROM " + tname + " auth WHERE "
				+ getIdentityColunm() + "= ? AND " + getCredentialcolumn();
		Query query = getService().findEntityByHQLQuery(hql, getIdentity(),
				getCredential());
		return query;

	}
}
