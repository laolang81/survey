package com.sniper.survey.custom.authentication;

import com.sniper.survey.service.BaseService;

public class DbTable<T> extends CredentialTreatmentAdapter<T> {

	public DbTable() {
	}

	public DbTable(BaseService service, String identityColunm,
			String credentialcolumn, String credentialTreatment) {
		super(service, identityColunm, credentialcolumn, credentialTreatment);
	}
}
